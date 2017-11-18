package com.krypto.util;
import java.math.BigInteger;

/**
 * Classe com diversos métodos úteis
 * @author Romildo Júnior
 *
 */
public class Function {

	/**
	 * Método para realizar exponenciação por quadrados repetidos
	 * @param base
	 * @param exponent
	 * @return base ^ exponent
	 * @author Romildo Júnior
	 */
	public static BigInteger repeatedSquare(BigInteger base, BigInteger exponent) {
		BigInteger result = BigInteger.ONE;
		
		while (exponent.signum() > 0) {
		    if (exponent.testBit(0)) 
		    	result = result.multiply(base);
		    
		    base = base.multiply(base);
		    
		    exponent = exponent.shiftRight(1);
		}
		return result;
	}
	/**
	 * Construtor privado para evitar instancias da classe
	 * @author Romildo Júnior
	 */
	private Function() {
		
	}
}
