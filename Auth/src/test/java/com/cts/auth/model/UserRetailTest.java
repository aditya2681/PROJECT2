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
 * @apiNote This class is an entity which we will be storing in the database. We
 *          will store the values already in the database for checking the user
 *          login credentials and this entity would help us to do that.
 * 
 *
 */

@RunWith(SpringRunner.class)
@ContextConfiguration @Slf4j
public class UserRetailTest {

	UserRetail userRetail=new UserRetail();
	/**
	 * to test the all param constructor of userRetail
	 */
	@Mock
	Environment env;
	@Test
	public void testUserAllConstructor()
	{
		log.info(env.getProperty("string.start"));
		UserRetail userRetail=new UserRetail("ab", "ab", "ab");
		assertEquals(userRetail.getUserId(), "ab");
		log.info(env.getProperty("string.end"));
	}
	/**
	 * to test the getter setter of Uid
	 */
	@Test
	public void testUserid()
	{
		log.info(env.getProperty("string.start"));
		userRetail.setUserId("abc");
		assertEquals(userRetail.getUserId(), "abc");
		log.info(env.getProperty("string.end"));
	}
	/**
	 * to test the getter setter of UserPassword
	 */
	@Test
	public void testUserPassword()
	{
		log.info(env.getProperty("string.start"));
		userRetail.setPassword("abc");
		assertEquals(userRetail.getPassword(), "abc");
		log.info(env.getProperty("string.end"));
	}
	/**
	 * to test the getter setter of UserName
	 */
	@Test
	public void testUserName()
	{
		log.info(env.getProperty("string.start"));
		userRetail.setUserName("abc");
		assertEquals(userRetail.getUserName(), "abc");
		log.info(env.getProperty("string.end"));
	}
	/**
	 * to test the toString
	 */
	@Test
	public void testoString() {
		log.info(env.getProperty("string.start"));
		String string = userRetail.toString();
		assertEquals(userRetail.toString(),string);
		log.info(env.getProperty("string.end"));
	}


}
