package com.ecommerce.inventoryservice.service;

public interface IInventoryMgmtService {

	public boolean isInStock(String productCode, int quantity);
}
