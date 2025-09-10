package com.example.ecommerce.order.repository;

import com.example.ecommerce.order.enity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderServiceRepo extends  JpaRepository<Order,Long> {
}
