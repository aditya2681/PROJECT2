package com.cognizant.clientportal.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 
 * @author POD 4
 * @apiNote This model class is used to store the data of
 * Defective Component to be repair/replace.
 *
 */

@Data @NoArgsConstructor @AllArgsConstructor 
public class DefectiveComponentDetail {
	/**
	 * Variable  requestId is used to store the ID of request
	 * received from user.
	 * The data type is integer.
	 */

	private int requestId;
	/**
	 * Variable componentType is used to store type of component.
	 * The data type is String.
	 */
	private String componentType;
	/**
	 * Variable componentName is used to store the name of the component.
	 * The data type is String.
	 */
	private String componentName;
	
	/**
	 * Variable quantity is used to store the quantity of components.
	 * The data type is integer.
	 */
	private int quantity;

	
}
