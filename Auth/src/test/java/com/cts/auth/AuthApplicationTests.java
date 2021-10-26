package com.cts.auth;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
/**
 * 
 * @author POD4
 * Test class for AuthApplication
 *
 */
@RunWith(SpringRunner.class)
@ContextConfiguration
public class AuthApplicationTests {
	@Mock
	AuthApplication application;

	@Test
	public void contextLoads() {
		assertNotNull(application);
	}

}
