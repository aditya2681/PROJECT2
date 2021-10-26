package com.cognizant.clientportal.proxyExchange;

import java.text.ParseException;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cognizant.clientportal.model.PaymentCredential;
import com.cognizant.clientportal.model.ProcessRequest;
import com.cognizant.clientportal.model.ProcessResponse;
/**
 * 
 * @author POD 4
 *
 * @apiNote 
 *          This Interface calls methods of another Component Processing service 
 *          using Feign client With provided URL and name of microservice it 
 *          interact with that microservice.
 *
 */
@FeignClient(name = "componentp-service", url = "${componentp-service:http://localhost:8081}")
public interface ComponentProcessingProxy {

	/**
	 * 
	 * @param token
	 * @param request
	 * @return ResponseEntity
	 * @throws ParseException
	 * @throws Exception
	 * To get the process details request from user is provided to service with this method.
	 */
	@RequestMapping(path = "/ProcessDetail", method = RequestMethod.POST)
	public ResponseEntity<ProcessResponse> getProcessDetails(@RequestHeader(name = "Authorization",required = true)String token,@RequestBody ProcessRequest request) throws ParseException, Exception;
	/**
	 * 
	 * @param token
	 * @param cred
	 * @return ResponseEntity
	 * @throws Exception
	 * To complete the processing and payment this method calls service with PaymentCredential as parameter.
	 */
	@PostMapping("/CompleteProcessing")
	public ResponseEntity<String> completeProcessing(@RequestHeader(name = "Authorization",required = true)String token,@RequestBody PaymentCredential cred) throws Exception;
}
