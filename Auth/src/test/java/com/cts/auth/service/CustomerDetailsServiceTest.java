package com.cts.auth.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.auth.AuthApplication;
import com.cts.auth.exception.ExceptionTest;
import com.cts.auth.model.UserRetail;
import com.cts.auth.repository.UserDao;

import lombok.extern.slf4j.Slf4j;
/**
 * @author POD4
 * @version 1.8
 * @apiNote This class is used to load the user details from database and
 *          checking whether the user resides in database or not based on the
 *          given id. This class will implement the {@link UserDetailsService}
 *          interface. The interface requires only one read-only method, which
 *          simplifies support for newdata-access strategies.
 *
 */

@RunWith(SpringRunner.class)
@ContextConfiguration @Slf4j
public class CustomerDetailsServiceTest {

	@Mock
	UserDetails userdetails;
	@Mock
	Environment env;
	@InjectMocks
	CustomUserDetailsService custdetailservice;

	@Mock
	UserDao userservice;
	/**
	 * This method is used to test  user details from database and checking
	 * whether the user resides in database or not based on the given id. If the
	 * user not present in the database it will throw an exception
	 * UsernameNotFoundException. And if user is present in the database it will
	 * simply return the {@link User} object
	 * 
	 * @exception UsernameNotFoundException
	 */
	@Test
	public void loadUserByUsernameTest() {
		log.info(env.getProperty("string.start"));
		UserRetail user1 = new UserRetail("admin", "admin", "admin");
		Optional<UserRetail> data = Optional.of(user1);
		when(userservice.findById("admin")).thenReturn(data);
		UserDetails loadUserByUsername2 = custdetailservice.loadUserByUsername("admin");
		assertEquals(user1.getUserId(), loadUserByUsername2.getUsername());
		log.info(env.getProperty("string.end"));
	}

}
