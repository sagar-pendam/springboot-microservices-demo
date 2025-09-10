package com.example.ecommerce.order.enity;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Double totalAmount;
    private String status; // PENDING, COMPLETED, CANCELLED

    // getters & setters
}
