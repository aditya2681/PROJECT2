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
 * @apiNote This class is used to hold the values which will come as a response
 *          when we will send the user login credentials from request body to
 *          the method login() in {@link AuthController}. The response will be
 *          containing the userid and token.
 *
 */
@RunWith(SpringRunner.class)
@ContextConfiguration @Slf4j
public class UserTokenTest {

	UserToken userToken=new UserToken();
	/**
	 * to test the all param constructor of userToken
	 */
	@Mock
	Environment env;
	@Test
	public void testUserTokenAllConstructor()
	{
		log.info(env.getProperty("string.start"));
		UserToken token=new UserToken("ab", "AB");
		assertEquals(token.getUserId(), "ab");
		log.info(env.getProperty("string.end"));
	}
	/**
	 * to test the getter setter of Uid
	 */
	@Test
	public void testGetUid()
	{
		log.info(env.getProperty("string.start"));
		userToken.setUserId("abc");
		assertEquals(userToken.getUserId(), "abc");
		log.info(env.getProperty("string.end"));
	}
	/**
	 * to test the getter setter of Auth Token
	 */
	@Test
	public void testGetAuthToken()
	{
		log.info(env.getProperty("string.start"));
		userToken.setToken("ABC");
		assertEquals(userToken.getToken(), "ABC");
		log.info(env.getProperty("string.end"));
	}
	/**
	 * to test the getter setter of toString
	 */
	@Test
	public void testoString() {
		log.info(env.getProperty("string.start"));
		String string = userToken.toString();
		assertEquals(userToken.toString(),string);
		log.info(env.getProperty("string.end"));
	}


}
