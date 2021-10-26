package com.cts.auth.Controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.auth.controller.AuthController;
import com.cts.auth.exception.LoginFailedException;
import com.cts.auth.jwt.JwtUtil;
import com.cts.auth.model.AuthResponse;
import com.cts.auth.model.UserRetail;
import com.cts.auth.model.UserLoginCredential;
import com.cts.auth.repository.UserDao;
import com.cts.auth.service.CustomUserDetailsService;

import lombok.extern.slf4j.Slf4j;
/**
 * @author Pod4 This class contains test cases for the AuthController
 *         class which are written using junit and mockito
 */
@RunWith(SpringRunner.class)
@ContextConfiguration @Slf4j
public class AuthControllerTest {

	@InjectMocks
	AuthController authController;

	AuthResponse authResponse;

	@Mock
	UserDetails userdetails;
	@Mock
	UserLoginCredential cred;
	@Mock
	JwtUtil jwtutil;

	@Mock
	CustomUserDetailsService custdetailservice;
	@Mock
	Environment env;
	@Mock
	UserDao userservice;
	/**
	 * This method is used to check the credentials whether the provided credentials
	 * are correct or not. For this we will call authenticate method. If user
	 * credentials are correct then we will fetch the data from database based on
	 * userid and return it to the user with a token
	 * */
	@Test
	public void validLoginTest() throws LoginFailedException, Exception {
		log.info(env.getProperty("string.start"));
		UserLoginCredential user = new UserLoginCredential("admin", "admin");
		UserDetails loadUserByUsername = custdetailservice.loadUserByUsername("admin");
		UserDetails value = new User(user.getUserId(), user.getPassword(), new ArrayList<>());
		when(custdetailservice.loadUserByUsername("admin")).thenReturn(value);
		when(jwtutil.generateToken(loadUserByUsername)).thenReturn("token");
		ResponseEntity<?> login = authController.login(user);
		assertEquals(login.getStatusCodeValue(), 200);
		log.info(env.getProperty("string.end"));

	}
	/**
     * to test the token validity
    */
	@Test(expected = LoginFailedException.class)
	public void invalidLoginTest() throws LoginFailedException, Exception {

		UserLoginCredential user = new UserLoginCredential("admin", "admin");
		UserDetails loadUserByUsername = custdetailservice.loadUserByUsername("admin");
		UserDetails value = new User(user.getUserId(), "admin11", new ArrayList<>());
		when(custdetailservice.loadUserByUsername("admin")).thenReturn(value);
		when(jwtutil.generateToken(loadUserByUsername)).thenReturn("token");
		when(authController.login(user)).thenThrow(new LoginFailedException());
	}



	@Test
	public void validateTestValidtoken() {

		log.info(env.getProperty("string.start"));

		when(jwtutil.validateToken("token")).thenReturn(true);
		when(jwtutil.extractUsername("token")).thenReturn("admin");
		UserRetail user1 = new UserRetail("admin", "admin", "admin");
		Optional<UserRetail> data = Optional.of(user1);
		when(userservice.findById("admin")).thenReturn(data);
		ResponseEntity<?> validity = authController.getValidity("bearer token");
		assertEquals(validity.getBody().toString().contains("true"), true);
		log.info(env.getProperty("string.end"));

	}	
}