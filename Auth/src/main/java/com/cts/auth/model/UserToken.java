package com.cts.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/**
 * @author POD4
 * @version 1.8
 * @apiNote This class is used to hold the values which will come as a response
 *          when we will send the user login credentials from request body to
 *          the method login() in {@link AuthController}. The response will be
 *          containing the userid and token.
 *
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserToken {
	/**
	 * This field will contain the userid
	 */
	private String userId;
	/**
	 * This field will contain the jwt token
	 */
	private String token;
}
