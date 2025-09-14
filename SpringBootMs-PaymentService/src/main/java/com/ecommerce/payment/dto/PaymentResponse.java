package com.ecommerce.payment.dto;

import com.ecommerce.payment.model.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {
    private Long orderId;
    private Double amount;
    private PaymentStatus status;
}
