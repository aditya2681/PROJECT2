package com.cts.auth.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import lombok.extern.slf4j.Slf4j;
/**
 * @author POD4
 * @version 1.8
 * @apiNote This class is used to hold the login credentials which will be sent
 *          by the user in the request body for getting the token
 *
 */
@RunWith(SpringRunner.class)
@ContextConfiguration @Slf4j
public class UserLoginCredentialsTest {

	UserLoginCredential loginCredential = new UserLoginCredential();
	/**
	 * to test the all param constructor of UserLoginCredential
	 */
	@Mock
	Environment env;
	@Test
	public void testUserLoginCredentialAllConstructor() {
		log.info(env.getProperty("string.start"));
		UserLoginCredential credential = new UserLoginCredential("abc", "abc");
		assertEquals(credential.getUserId(), "abc");
		log.info(env.getProperty("string.end"));
	}
	/**
	 * to test the getter setter of Uid
	 */
	@Test
	public void testGetUid() {
		log.info(env.getProperty("string.start"));
		loginCredential.setUserId("abc");
		assertEquals(loginCredential.getUserId(), "abc");
		log.info(env.getProperty("string.end"));
	}
	/**
	 * to test the getter setter of Password
	 */
	@Test
	public void testUserPassword() {
		log.info(env.getProperty("string.start"));
		loginCredential.setPassword("abc");
		assertEquals(loginCredential.getPassword(), "abc");
		log.info(env.getProperty("string.end"));
	}
	/**
	 * to test the getter setter of toString()
	 */
	@Test
	public void testoString() {
		log.info(env.getProperty("string.start"));
		String string = loginCredential.toString();
		assertEquals(loginCredential.toString(), string);
		log.info(env.getProperty("string.end"));
	}

}
