package com.cts.auth.exception;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cts.auth.model.CustomErrorResponse;

import lombok.extern.slf4j.Slf4j;
/**
 * @author POD4 This class is used to handle all the errors. Here we are using
 *         class {@link CustomErrorResponse} for returning the response. It will
 *         handle generic as well as specific exceptions also.
 *
 */

@RestControllerAdvice @Slf4j
public class GlobalErrorHandler {
	/**
	 * This method will handle all type of exceptions
	 * 
	 * @param ex
	 * @return ResponseEntity<CustomErrorResponse>
	 */
	@Autowired
	Environment env;

	@ExceptionHandler(LoginFailedException.class)
	public ResponseEntity<CustomErrorResponse> handleIdNotFoundexception(LoginFailedException ex)
	{
		log.info(env.getProperty("string.start"));
		CustomErrorResponse response=new CustomErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND,
				env.getProperty("string.reason.loginfail"), ex.getMessage());
		log.info(env.getProperty("string.end"));
		return new ResponseEntity<CustomErrorResponse>(response,HttpStatus.NOT_FOUND);
	}
}
