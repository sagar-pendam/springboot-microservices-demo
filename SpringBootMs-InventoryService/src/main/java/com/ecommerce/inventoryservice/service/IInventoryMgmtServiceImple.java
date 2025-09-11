package com.ecommerce.inventoryservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.inventoryservice.repository.InventoryRepository;

@Service
public class IInventoryMgmtServiceImple implements IInventoryMgmtService {
	@Autowired
	private InventoryRepository repository;
	@Override
	public boolean isInStock(String productCode, int quantity) {
		// TODO Auto-generated method stub
		return repository.findByProductCode(productCode).map(inventory -> inventory.getQuantity() >= quantity).orElse(false);
	}

}
