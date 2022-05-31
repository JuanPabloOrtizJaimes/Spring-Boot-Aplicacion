package com.juanOrtizJaimes.springBootAplicacion.exception;

public class UsernameOrIdNotFound extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4415913124566550496L;

	public UsernameOrIdNotFound() {
		super("Usuario o Id no encontrado");
	}
	
	public UsernameOrIdNotFound(String message) {
		super(message);
	}
	

}
