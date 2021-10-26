package com.cts.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/**
 * @author POD4
 * @version 1.8
 * @apiNote This class is used to hold the login credentials which will be sent
 *          by the user in the request body for getting the token
 *
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginCredential {
	/**
	 * The userid will be unique and it will hold the value which user will send
	 * from the request body for getting the token
	 */
	private String userId;
	/**
	 * This field represents the password and it will hold the value which user will send
	 * from the request body for getting the token
	 */
	private String password;

}
