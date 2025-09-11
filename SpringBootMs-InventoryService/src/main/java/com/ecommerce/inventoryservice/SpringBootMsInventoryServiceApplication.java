package com.ecommerce.inventoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringBootMsInventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMsInventoryServiceApplication.class, args);
	}

}
