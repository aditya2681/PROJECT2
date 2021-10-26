package com.cts.auth.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.auth.exception.LoginFailedException;
import com.cts.auth.jwt.JwtUtil;
import com.cts.auth.model.AuthResponse;
import com.cts.auth.model.UserLoginCredential;
import com.cts.auth.model.UserToken;
import com.cts.auth.repository.UserDao;
import com.cts.auth.service.CustomUserDetailsService;

import lombok.extern.slf4j.Slf4j;
/**
 * @author POD4
 * @version 1.8
 * @apiNote This class is having all the endpoints related to authorization
 *          purpose. For getting the token and validating the token this class
 *          willbe used.
 *
 */
@Slf4j
@RestController
@RequestMapping("/authapp")
public class AuthController {
	/**
	 * This is a private field of type {@link JwtUtil} class which provides the
	 * utilities for the token like get token, validate token, expiration time etc.
	 */
	@Autowired
	private JwtUtil jwtUtil;
	/**
	 * This is a private field of type {@link CustomerDetailsService} class which is
	 * used to fetch the user credentials from the database
	 */
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	/**
	 * This is a private field of type {@link UserDAO} class which
	 * {@link ExtendedAddShiftOp} JpaRepository interface
	 */
	@Autowired
	private UserDao userservice;
	@Autowired
	Environment env;

	/**
	 * This method is used to check the credentials whether the provided credentials
	 * are correct or not. For this we will call authenticate method. If user
	 * credentials are correct then we will fetch the data from database based on
	 * userid and return it to the user with a token
	 * 
	 * @param userlogincredentials An object of type {@link UserLoginCredential}
	 *                             which has two fields userid and password
	 * @return an object of type UserToken which has two fields userid and authtoken
	 * @throws Exception
	 */
	@PostMapping("/login")
	public ResponseEntity<UserToken> login(@RequestBody UserLoginCredential userLoginCredential)throws LoginFailedException {

		log.info(env.getProperty("string.start"));
		log.debug(userLoginCredential.toString());
		final UserDetails userDetails = customUserDetailsService.loadUserByUsername(userLoginCredential.getUserId());
		if(!(userLoginCredential.getPassword().equals(userDetails.getPassword())))
			throw new LoginFailedException(env.getProperty("login.fail"));
		log.info(env.getProperty("string.end"));
		return new ResponseEntity<>(new UserToken(userLoginCredential.getUserId(), jwtUtil.generateToken(userDetails)), HttpStatus.OK);
	}

	@PostMapping("/validate")
	public ResponseEntity<?> getValidity(@RequestHeader("Authorization") final String token) {
		log.info(env.getProperty("string.start"));
		log.debug(env.getProperty("string.auth.token"),token);
		String token1 = token.substring(7);
		if(jwtUtil.validateToken(token1)) {
			AuthResponse authResponse=new AuthResponse(jwtUtil.extractUsername(token1), 
					(userservice.findById(jwtUtil.extractUsername(token1)).orElse(null).getUserName()), true);
			log.info(env.getProperty("string.end"));
			return new ResponseEntity<>(authResponse, HttpStatus.OK);

		} else {
			AuthResponse authResponse=new AuthResponse(jwtUtil.extractUsername(token1), 
					(userservice.findById(jwtUtil.extractUsername(token1)).orElse(null).getUserName()), true);
			log.info(env.getProperty("string.end"));
			return new ResponseEntity<>(authResponse, HttpStatus.OK);

		}
	}
}
