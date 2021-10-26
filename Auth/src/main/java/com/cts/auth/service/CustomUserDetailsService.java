package com.cts.auth.service;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
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
@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {
	/**
	 * This field is used to communicate with the database. It has the annotation
	 * autowired which will automatically inject the bean.
	 * 
	 * @see UserDAO
	 */
	@Autowired
	private UserDao userDao;
	/**
	 * This method is used to load the user details from database and checking
	 * whether the user resides in database or not based on the given id. If the
	 * user not present in the database it will throw an exception
	 * UsernameNotFoundException. And if user is present in the database it will
	 * simply return the {@link User} object
	 * 
	 * @exception UsernameNotFoundException
	 */

	@Autowired
	Environment env;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info(env.getProperty("string.start"));
		log.info(username+env.getProperty("string.hello"));
		UserRetail custUser = userDao.findById(username).orElse(null);
		log.info(env.getProperty("string.userfound"));
		log.info(env.getProperty("string.end"));
		return new org.springframework.security.core.userdetails.User(custUser.getUserId(), custUser.getPassword(), new ArrayList<>());
	}


}
