package com.cognizant.clientportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
/**
 * 
 * @author POD4 -Return Order Management
 * This is Application class for Spring boot
 *
 */
@EnableCircuitBreaker
@SpringBootApplication
@EnableFeignClients
public class ClientPortalApplication {
	/**
	 * main Method- Spring Boot’s method to launch an application. 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ClientPortalApplication.class, args);
	}

}
