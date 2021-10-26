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
 * @apiNote This model class is used to store the response from Auth feign client.
 * This contains information of user Authentication token.
 *
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserToken {
	/**
	 * userId variable store user ID of user.
	 * Datatype is String.
	 */
	private String userId;
	/**
	 * token variable store token.
	 * Datatype is String.
	 */
	private String token;
}
