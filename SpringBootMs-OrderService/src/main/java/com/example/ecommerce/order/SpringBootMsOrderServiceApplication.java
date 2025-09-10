package com.example.ecommerce.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringBootMsOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMsOrderServiceApplication.class, args);
	}

}
