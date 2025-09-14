package com.ecommerce.order.events;

import com.ecommerce.order.enums.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEvent {
    private Long orderId;     // ID of the order related to the payment
    private Double amount;    // Payment amount
    private PaymentStatus status;  // Use enum instead of String
}
