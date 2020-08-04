package com.orderService.exception;

public class InvalidOrderException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvalidOrderException(){
		super("INVALID ORDER , ENTER VALID DETAILS");
	}
}
