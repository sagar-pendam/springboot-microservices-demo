package com.ecommerce.payment.events;

import com.ecommerce.payment.model.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEvent {
    private Long orderId;
    private Double amount;
    private PaymentStatus status;  // Use enum instead of String
}
