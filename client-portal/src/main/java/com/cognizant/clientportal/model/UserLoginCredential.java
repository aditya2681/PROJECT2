package com.cognizant.clientportal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/**
 * 
 * @author POD 4
 * @apiNote This model class is used to store the data which is retrieved from user for login in application.
 * This contains information of user login credentials.
 *
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginCredential {

	/**
	 * userId variable store user ID of user.
	 * Datatype is String.
	 */
	private String userId;

	/**
	 * password variable store password of user.
	 * Datatype is String.
	 */
	private String password;

}
