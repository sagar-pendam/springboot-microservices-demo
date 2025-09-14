package com.ecommerce.payment.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.ecommerce.payment.dto.PaymentResponse;
import com.ecommerce.payment.events.OrderEvent;
import com.ecommerce.payment.service.PaymentService;

@Component
public class PaymentConsumer {

    @Autowired
    private PaymentService paymentService;

    @KafkaListener(topics = "order-events", groupId = "payment-group")
    public void consumeOrderEvent(OrderEvent event) {
        System.out.println("📩 Received Order Event: " + event);

        if ("ORDER_CREATED".equals(event.getStatus())) {
            // Process payment automatically
            PaymentResponse paymentEvent = paymentService.processPayment(event.getOrderId(), event.getAmount());
            System.out.println("💳 Payment processed: " + paymentEvent);
        }
    }
}
