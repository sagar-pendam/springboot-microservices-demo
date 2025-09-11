package com.ecommerce.order.service;

import java.util.List;

import com.ecommerce.order.model.Order;

public interface IOrderServiceMgmt {

    public Order createOrder(Order order);
    public Order findOrderById(Long id);
    public List<Order> findAllOrders();
}
