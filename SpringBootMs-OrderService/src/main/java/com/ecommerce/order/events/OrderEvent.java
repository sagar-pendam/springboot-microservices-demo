package com.ecommerce.order.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent {
    private Long orderId;
    private String productCode;
    private Integer quantity;
    private Double amount;
    private String status;  // e.g. "ORDER_CREATED"
}
