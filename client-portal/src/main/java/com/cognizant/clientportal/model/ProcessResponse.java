package com.cognizant.clientportal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 
 * @author POD 4
 * @apiNote This model class is used to store the data which is to provide as response
 * This contains information to show to user after processing the request.
 *
 */
@Data @NoArgsConstructor @AllArgsConstructor
public class ProcessResponse {
	/**
	 * Variable  requestId is used to store the ID of request
	 * received from user.
	 * The data type is integer.
	 */
	private int requestId; 
	/**
	 * Variable processingCharge is used to store charges to pay for processing.
	 * The data type is float.
	 */
	private float processingCharge; 
	/**
	 * Variable packagingAndDeliveryCharge is used to store charges to pay for packaging and Delivery.
	 * The data type is float.
	 */
	private float packagingAndDeliveryCharge; 
	/**
	 * Variable dateOfDelivery is used to store date of Delivery.
	 * The data type is String.
	 */
	private String dateOfDelivery; 

}

