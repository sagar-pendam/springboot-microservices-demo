package com.ecommerce.order.client;

import org.springframework.cloud.openfeign.FeignClient;   // 👈 Missing import
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service", url = "http://localhost:8081/inventory-api")
public interface InventoryClient {

    @GetMapping("/checkStock/{productCode}/{quantity}")
    Boolean checkStock(@PathVariable("productCode") String productCode,
                       @PathVariable("quantity") int quantity);
}
