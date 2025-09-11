package com.ecommerce.order.model;

import java.io.Serializable;

import com.ecommerce.order.enums.OrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String productCode;   //  needed for Inventory check
    private int quantity;         //  needed for Inventory check
    private Double totalAmount;
    @Enumerated(EnumType.STRING)   // 👈 saves enum as string in DB
    private OrderStatus status;// PENDING, COMPLETED, CANCELLED
}
