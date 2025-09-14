package com.ecommerce.payment.service;

import com.ecommerce.payment.dto.PaymentResponse;
import com.ecommerce.payment.events.PaymentEvent;
import com.ecommerce.payment.model.Payment;
import com.ecommerce.payment.model.PaymentStatus;
import com.ecommerce.payment.repository.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImple {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private KafkaTemplate<String, PaymentEvent> kafkaTemplate;

    public PaymentResponse processPayment(Long orderId, Double amount) {
        // 1. Save to DB
        Payment payment = new Payment();
        payment.setOrderId(orderId);
        payment.setAmount(amount);

        // dummy rule for demo: reject payments above 1000
        PaymentStatus status = amount > 1000 ? PaymentStatus.FAILED : PaymentStatus.SUCCESS;
        payment.setStatus(status);

        payment = paymentRepository.save(payment);

        // 2. Publish Kafka event
        PaymentEvent event = new PaymentEvent(orderId, amount, status);
        kafkaTemplate.send("payment-events", event);

        // 3. Return response DTO
        return new PaymentResponse(
                payment.getOrderId(),
                payment.getAmount(),
                payment.getStatus()
        );
    }

    public PaymentResponse getPaymentByOrder(Long orderId) {
        return paymentRepository.findByOrderId(orderId)
                .map(payment -> new PaymentResponse(
                        payment.getOrderId(),
                        payment.getAmount(),
                        payment.getStatus()))
                .orElse(null);
    }
}
