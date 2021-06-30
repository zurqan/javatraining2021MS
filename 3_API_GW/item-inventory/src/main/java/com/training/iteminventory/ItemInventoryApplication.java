package com.training.iteminventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ItemInventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemInventoryApplication.class, args);
	}

}
