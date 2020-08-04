package com.orderService.exception;

public class OrderIDNotNullException extends RuntimeException {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public OrderIDNotNullException(){
	super(String.format("ORDER ID CANNOT BE NULL"));
}
}
