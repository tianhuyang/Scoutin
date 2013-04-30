package com.scoutin.application.exception;

public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private static final String message = "ApplicationException";
	
	public ApplicationException(){
		super(message);
	}
	
	public ApplicationException(String message){
		super(message);
	}

}
