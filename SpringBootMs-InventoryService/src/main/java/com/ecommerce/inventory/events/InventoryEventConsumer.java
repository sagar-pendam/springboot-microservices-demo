package com.ecommerce.inventory.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.ecommerce.inventory.enums.InventoryStatus;
import com.ecommerce.inventoryservice.service.IInventoryMgmtService;

@Service
public class InventoryEventConsumer {
	@Autowired
    private IInventoryMgmtService inventoryService;
	@Autowired
    private KafkaTemplate<String, InventoryEvent> kafkaTemplate;

    
	@KafkaListener(topics = "inventory-events", groupId = "inventory-group")
	public void handleInventoryEvent(InventoryEvent event) {
	    boolean reserved = inventoryService.reserveProduct(
	            event.getProductCode(),
	            event.getQuantity()
	    );

	    InventoryEvent response = new InventoryEvent(
	            event.getOrderId(),
	            event.getProductCode(),
	            event.getQuantity(),
	            reserved ? InventoryStatus.INVENTORY_CONFIRMED : InventoryStatus.OUT_OF_STOCK
	    );

	    kafkaTemplate.send("inventory-response-events", response);
	}

}
