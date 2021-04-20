package com.fakhri.productorder;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition
@SpringBootApplication
public class ProductOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductOrderApplication.class, args);
	}

}
