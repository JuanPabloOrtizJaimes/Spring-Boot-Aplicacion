package com.juanOrtizJaimes.springBootAplicacion.exception;

public class CustomeFieldValidationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7915228338446388334L;

	private String fieldName;
	
	

	public CustomeFieldValidationException(String message,String fieldName) {
		super(message);
		this.fieldName = fieldName;
	}

	public String getFieldName() {
		return fieldName;
	}

	
	
	
}
