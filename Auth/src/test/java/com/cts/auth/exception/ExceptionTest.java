package com.cts.auth.exception;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @author POD4
 *
 */
@RunWith(SpringRunner.class)
@ContextConfiguration @Slf4j
public class ExceptionTest {
	/**
	 * to test the InvalidAuthorization Exception
	 * 
	 */
	@Mock
	Environment env;
	@Test
	public void testInvalidAuthorizationException() {
		log.info(env.getProperty("string.start"));
		LoginFailedException invalidAuthorizationException = new LoginFailedException(env.getProperty("string.not.valid"));
		assertNotNull(invalidAuthorizationException);
		log.info(env.getProperty("string.end"));

	}

}
