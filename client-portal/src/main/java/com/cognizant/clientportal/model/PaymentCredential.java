package com.cognizant.clientportal.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author POD 4
 * @apiNote This model class is used to store the data which is provided to {@link ComponentProcessingProxy}
 * to process the payment.
 * This contains information required for the Payment for component processed.
 *
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PaymentCredential {
	/**
	 * Variable creditCardNumber is used to store credit Card Number of user.
	 * The data type is integer.
	 */
	private String creditCardNumber;
	/**
	 * Variable creditLimit is used to store credit Limit.
	 * The data type is float.
	 */
	private float creditLimit;
	/**
	 * Variable processingCharge is used to store charges to pay for processing.
	 * The data type is float.
	 */
	private float processingCharge;
	
}
