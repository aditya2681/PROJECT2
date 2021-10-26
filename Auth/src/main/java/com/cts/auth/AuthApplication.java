package com.cts.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @author POD4 -Return Order Management
 * This is Application class for Spring boot
 *
 */
@SpringBootApplication @Slf4j
public class AuthApplication {

	/**
	 * main Method- Spring Boot’s method to launch an application. 
	 * @param args
	 */
	public static void main(String[] args) {
		log.info("Start");
		log.debug("Started AuthApplication Microservice");
		SpringApplication.run(AuthApplication.class, args);
		log.info("End");

	}
	 

}
