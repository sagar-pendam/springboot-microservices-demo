package com.ecommerce.order.rest;

import java.util.List;
import com.ecommerce.order.enums.OrderStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.order.client.InventoryClient;
import com.ecommerce.order.events.OrderEvent;
import com.ecommerce.order.model.Order;
import com.ecommerce.order.service.IOrderServiceMgmt;

@RestController
@RequestMapping("/order-api")
public class OrderOperationController {
    @Autowired
    private IOrderServiceMgmt  orderService;
    
    @Autowired
    private InventoryClient inventoryClient;
    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;
    @PostMapping("/create-order")
    public ResponseEntity<String> createOrder(@RequestBody Order order) {

        // Save order as PENDING
        order.setStatus(OrderStatus.PENDING);
        Order createdOrder = orderService.createOrder(order);

        // Publish ORDER_CREATED event to inventory
        OrderEvent orderEvent = new OrderEvent(
            createdOrder.getId(),
            createdOrder.getProductCode(),
            createdOrder.getQuantity(),
            createdOrder.getAmount(),
            "ORDER_CREATED"
        );

        kafkaTemplate.send("order-events", orderEvent);

        return new ResponseEntity<>("Order placed successfully with ID: " + createdOrder.getId() + " (Pending Inventory)", HttpStatus.CREATED);
    }

    @GetMapping("/show-order/{id}")
    public ResponseEntity<Order> showOrder(@PathVariable Long id) {
        Order showOrder = orderService.findOrderById(id);
        return new ResponseEntity<Order>(showOrder, HttpStatus.OK);
    }

    @GetMapping("/show-order/all")
    public ResponseEntity<List<Order>> showOrdersList()
    {
        List<Order> showOrders = orderService.findAllOrders();
        return new ResponseEntity<List<Order>>(showOrders, HttpStatus.OK);
    }

}
