package com.krypto.exceptions;

public class InvalidCharCodeException extends Exception{

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Construtor da classe
	 * @param message
	 * @author Romildo Júnior
	 */
	public InvalidCharCodeException(String message) {
		super(message);
	}	
}
