package com.ecommerce.demo.exceptions;


public class ItemExistException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ItemExistException() {
		super();
	}

	public ItemExistException(String message) {
		super(message);
	}

	public ItemExistException(String message, Throwable cause) {
		super(message, cause);
	}
}

