package com.cts.auth.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.auth.AuthApplication;
import com.cts.auth.exception.ExceptionTest;

import lombok.extern.slf4j.Slf4j;
/**
 * @author Pod4 Model class whose fields are used for the response for token
 *         validation. When we call the method getValidity() in
 *         {@link AuthController} then it will return {@link AuthResponse} type
 *         object
 */


@RunWith(SpringRunner.class)
@ContextConfiguration @Slf4j
public class AuthResponseTest {
	@Mock
	Environment env;
	AuthResponse authResponse=new AuthResponse();
	/**
	 * to test  the AuthResponseConstructor()
	 * all params constructor
	 */
	@Test
	public void testAuthResponseConstructor()
	{		
		log.info(env.getProperty("string.start"));

		AuthResponse response=new AuthResponse("abc", "ABC", true);
		assertEquals(response.getUserId(), "abc");
		log.info(env.getProperty("string.end"));

	}
	/**
	 * to test the setter getter for user ID
	 */
	@Test
	public void testUid()
	{
		log.info(env.getProperty("string.start"));

		authResponse.setUserId("abc");
		assertEquals(authResponse.getUserId(), "abc");
		log.info(env.getProperty("string.end"));

	}
	/**
	 * to test the setter getter for user Name
	 */
	@Test
	public void testName()
	{
		log.info(env.getProperty("string.start"));

		authResponse.setName("ABC");
		assertEquals(authResponse.getName(), "ABC");
		log.info(env.getProperty("string.end"));

	}
	/**
	 * to test the setter getter for validity
	 */
	@Test
	public void testIsValid()
	{
		log.info(env.getProperty("string.start"));

		authResponse.setValid(true);
		assertEquals(authResponse.isValid(), true);
		log.info(env.getProperty("string.end"));

	}
	/**
	 * to test the toString method 
	 */
	@Test
	public void testtoString() 
	{
		log.info(env.getProperty("string.start"));

		String s = authResponse.toString();
		assertEquals(authResponse.toString(), s);
		log.info(env.getProperty("string.end"));

	}


}
