package com.abc.api.exception;

public class ProductAlreadyExistException extends RuntimeException {
	
	public ProductAlreadyExistException(String msg) {
		super(msg);
	}

}
