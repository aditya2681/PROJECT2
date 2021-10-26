package com.cts.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/**
 * @author POD4
 * @version 1.8
 * @apiNote This class is an entity which we will be storing in the database. We
 *          will store the values already in the database for checking the user
 *          login credentials and this entity would help us to do that.
 * 
 *
 */
@Entity(name = "user")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserRetail {
	/**
	 * This will be the id for the user credentials that user will have to enter in
	 * the request body
	 */
	@Id
	@Column(name = "userid", length = 20)
	private String userId;
	/**
	 * This field will be used to store the password that user will have to enter in
	 * the request body
	 */
	@Column(name = "uname", length = 20)
	private String userName;
	/**
	 * This field will be used to store the username that user will have to enter in
	 * the request body
	 */
	@Column(name = "upassword", length = 20)
	private String password;
}
