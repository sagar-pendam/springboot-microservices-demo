package com.ecommerce.order.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.ecommerce.order.enums.InventoryStatus;
import com.ecommerce.order.enums.OrderStatus;
import com.ecommerce.order.enums.PaymentStatus;
import com.ecommerce.order.events.InventoryEvent;
import com.ecommerce.order.events.PaymentEvent;
import com.ecommerce.order.model.Order;
import com.ecommerce.order.service.IOrderServiceMgmt;

@Service
public class PaymentEventListener {

    @Autowired
    private IOrderServiceMgmt orderService;

    @Autowired
    private KafkaTemplate<String, InventoryEvent> kafkaTemplate;

    @KafkaListener(topics = "payment-events", groupId = "order-group")
    public void handlePaymentEvents(PaymentEvent event) {
        System.out.println("📩 Received PaymentEvent: " + event);

        // Fetch the order
        Order order = orderService.findOrderById(event.getOrderId());

        if (event.getStatus() == PaymentStatus.FAILED) {
            order.setStatus(OrderStatus.CANCELLED);
            orderService.createOrder(order); // save updated status
            System.out.println("❌ Payment failed, order " + order.getId() + " cancelled.");
        } else if (event.getStatus() == PaymentStatus.SUCCESS) {
            order.setStatus(OrderStatus.PENDING); // Payment confirmed, move to inventory
            orderService.createOrder(order);

            System.out.println("✅ Payment successful, moving to inventory reservation.");

            InventoryEvent inventoryEvent = new InventoryEvent(
                    order.getId(),
                    order.getProductCode(),
                    order.getQuantity(),
                    InventoryStatus.RESERVE
            );

            kafkaTemplate.send("inventory-events", inventoryEvent);
        }
    }
}
