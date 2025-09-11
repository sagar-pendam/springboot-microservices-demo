package com.ecommerce.order.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.order.model.Order;
import com.ecommerce.order.repository.IOrderServiceRepo;

import java.util.List;

@Service
public class IOrderServiceMgmtImple implements IOrderServiceMgmt {
    @Autowired
    private IOrderServiceRepo OrderRepo;
    @Override
    public Order createOrder(Order order) {
        Order newOrder = OrderRepo.save(order);
        return newOrder;
    }

    @Override
    public Order findOrderById(Long id) {

        return OrderRepo.findById(id).orElseThrow(()->new IllegalArgumentException("Order Not Found"));
    }

    @Override
    public List<Order> findAllOrders() {

        return OrderRepo.findAll();
    }
}
