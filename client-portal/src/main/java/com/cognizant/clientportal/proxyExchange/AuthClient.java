package com.cognizant.clientportal.proxyExchange;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cognizant.clientportal.exception.LoginFailedException;
import com.cognizant.clientportal.model.UserLoginCredential;
import com.cognizant.clientportal.model.UserToken;

/**
 * 
 * @author POD 4
 *
 * @apiNote This class communicates with Auth microservice to verify the
 *          token. 
 *          Interface to call methods of another Auth microservice 
 *          using Feign client With provided URL and name of microservice it 
 *          interact with that microservice.
 *
 */@FeignClient(name = "authapp", url = "${authapp:http://localhost:8410}")
 public interface AuthClient {
	 /**
	  * 
	  * @param userLoginCredential
	  * @return ResponseEntity
	  * This method interact with method from other microservice with method mapping
	  *  given as parameter.
	  *  To get token userLoginCredential is provided.
	  */
	 @RequestMapping(path = "/authapp/login", method = RequestMethod.POST)
	 public ResponseEntity<UserToken> getToken(@RequestBody UserLoginCredential userLoginCredential)throws LoginFailedException;
	 /**
	  * 
	  * @param String token
	  * @return ResponseEntity
	  * This method interact with method from other microservice with method mapping
	  *  given as parameter 
	  *  To validate token String token is provided.
	  */
	 @PostMapping("/validate")
	 public ResponseEntity<?> getValidity(@RequestHeader("Authorization") final String token);
 }
