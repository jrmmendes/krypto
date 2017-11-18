/**
 * 
 */
package com.krypto.exceptions;

/**
 * @author Romildo Júnior
 *
 */
public class InvalidCharacterException extends Exception{

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 1796830735977872716L;
	
	/**
	 * Construtor da classe
	 * @param message
	 * @author Romildo Júnior
	 */
	public InvalidCharacterException(final String message) {
		super(message);
	}	
}
