package com.example.ecommerce.configServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class SpringBootMsConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMsConfigServerApplication.class, args);
	}

}
