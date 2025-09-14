package com.ecommerce.inventory.events;

import com.ecommerce.inventory.enums.InventoryStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryEvent {
    private Long orderId;
    private String productCode;
    private Integer quantity;
    private InventoryStatus status; // Use enum instead of String
}
