package com.example.ecommerce.order.rest;

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

import com.example.ecommerce.order.enity.Order;
import com.example.ecommerce.order.service.IOrderServiceMgmt;

@RestController
@RequestMapping("/order-api")
public class OrderOperationController {
    @Autowired
    private IOrderServiceMgmt  orderService;
    @PostMapping("/create-order")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        return new ResponseEntity<Order>(createdOrder, HttpStatus.CREATED);
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
