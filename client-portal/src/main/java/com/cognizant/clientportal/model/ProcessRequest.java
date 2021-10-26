package com.cognizant.clientportal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 
 * @author POD 4
 * @apiNote This model class is used to store the data which is retrieved from user 
 * This contains information of request from user to process.
 *
 */
@Data @NoArgsConstructor @AllArgsConstructor
public class ProcessRequest {
	/**
	 * Variable  requestId is used to store the ID of request
	 * received from user.
	 * The data type is integer.
	 */
	private int requestId;
	/**
	 * Variable name is used to store name of user.
	 * The data type is String.
	 */
	private String name;
	
	/**
	 * Variable contactNumber is used to store contact Number of user.
	 * The data type is String.
	 */
	private String contactNumber;
	
	/**
	 * Variable creditCardNumber is used to store credit Card Number of user.
	 * The data type is integer.
	 */
	private String creditCardNumber;
	
	/**
	 * Variable dc is used to store Defective Component Details.
	 * The data type is DefectiveComponentDetail.
	 */

	private DefectiveComponentDetail dc;
	
	/**
	 * Variable priorityRequest is used to store whether request is of priority or not.
	 * The data type is boolean.
	 */
	private boolean priorityRequest;

	public ProcessRequest(String name, String contactNumber, String creditCardNumber,boolean priorityRequest) {
		super();
		this.name = name;
		this.contactNumber = contactNumber;
		this.creditCardNumber = creditCardNumber;
		this.priorityRequest = priorityRequest;
	}

	
	
}
