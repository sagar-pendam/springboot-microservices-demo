package com.ecommerce.order.events;

import com.ecommerce.order.enums.InventoryStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryEvent {
    private Long orderId;             // Which order this inventory action belongs to
    private String productCode;       // Product being reserved
    private Integer quantity;         // Quantity requested
    private InventoryStatus status;   // Use enum instead of String
}
