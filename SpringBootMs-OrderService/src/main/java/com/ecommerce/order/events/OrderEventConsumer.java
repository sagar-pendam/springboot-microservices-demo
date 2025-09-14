package com.ecommerce.order.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.ecommerce.order.enums.OrderStatus;
import com.ecommerce.order.model.Order;
import com.ecommerce.order.service.IOrderServiceMgmt;
import com.ecommerce.order.enums.InventoryStatus;

@Service
public class OrderEventConsumer {

    @Autowired
    private IOrderServiceMgmt orderService;

    @KafkaListener(topics = "inventory-response-events", groupId = "order-group")
    public void handleInventoryResponse(InventoryEvent event) {
        Order order = orderService.findOrderById(event.getOrderId());

        // Use enum instead of strings
        if (event.getStatus() == InventoryStatus.INVENTORY_CONFIRMED) {
            order.setStatus(OrderStatus.COMPLETED);
        } else if (event.getStatus() == InventoryStatus.OUT_OF_STOCK) {
            order.setStatus(OrderStatus.CANCELLED);
        } else {
            order.setStatus(OrderStatus.PENDING);
        }

        orderService.createOrder(order); // save updated order
        System.out.println("Order ID " + order.getId() + " status updated to " + order.getStatus());
    }
}
