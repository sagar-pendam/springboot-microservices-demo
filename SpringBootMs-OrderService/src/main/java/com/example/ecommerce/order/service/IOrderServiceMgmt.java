package com.example.ecommerce.order.service;

import com.example.ecommerce.order.enity.Order;

import java.util.List;

public interface IOrderServiceMgmt {

    public Order createOrder(Order order);
    public Order findOrderById(Long id);
    public List<Order> findAllOrders();
}
