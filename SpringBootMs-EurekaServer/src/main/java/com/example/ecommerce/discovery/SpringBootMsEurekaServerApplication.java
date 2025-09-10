package com.example.ecommerce.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringBootMsEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMsEurekaServerApplication.class, args);
    }

}
