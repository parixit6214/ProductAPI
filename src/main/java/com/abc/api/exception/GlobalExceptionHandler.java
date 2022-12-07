package com.abc.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ProductAlreadyExistException.class)
	public ResponseEntity<String> productAlreadyExistException(ProductAlreadyExistException ex){
		
		String msg=ex.getMessage();
		return new ResponseEntity<String>(msg,HttpStatus.OK);
		
	}
	

}
