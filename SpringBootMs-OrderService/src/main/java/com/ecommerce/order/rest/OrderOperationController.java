package com.ecommerce.order.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.order.client.InventoryClient;
import com.ecommerce.order.model.Order;
import com.ecommerce.order.service.IOrderServiceMgmt;

@RestController
@RequestMapping("/order-api")
public class OrderOperationController {
    @Autowired
    private IOrderServiceMgmt  orderService;
    
    @Autowired
    private InventoryClient inventoryClient;
    @PostMapping("/create-order")
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        // Call InventoryService
        Boolean inStock = inventoryClient.checkStock(order.getProductCode(), order.getQuantity());

        if (Boolean.TRUE.equals(inStock)) {
            // Save order if product is in stock
            Order createdOrder = orderService.createOrder(order);
            return new ResponseEntity<>("Order placed successfully with ID: " + createdOrder.getId(),
                                        HttpStatus.CREATED);
        } else {
            // Reject order if product is out of stock
            return new ResponseEntity<>("❌ Product is out of stock!", HttpStatus.BAD_REQUEST);
        }
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
