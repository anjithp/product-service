package com.bulletproof.onlineshop.inventoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages= {"com.bulletproof.onlineshop.core"})
//@ComponentScan(basePackages="com.bulletproof.onlineshop.core")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
