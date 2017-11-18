package com.krypto.rsa;

import java.math.BigInteger;

/**
 * Classe com os métodos do RSA
 * @author Romildo Júnior
 */
public class RSA {
	/**
	 * Método para cifrar uma mensagem
	 * @param messageCode
	 * @param encryptKey
	 * @param n
	 * @return Mensagem crifrada
	 * @author Romildo Júnior
	 */
	public static BigInteger encrypt(BigInteger messageCode, BigInteger encryptKey, BigInteger n) {
		return messageCode.modPow(encryptKey, n);
	}
	/**
	 * Método para remover criptografia de uma mensagem
	 * @param encryptedMessage
	 * @param decryptKey
	 * @param n
	 * @return Texto claro
	 * @author Romildo Júnior
	 */
	public static BigInteger decrypt(BigInteger encryptedMessage, BigInteger decryptKey, BigInteger n) {
		return encryptedMessage.modPow(decryptKey, n);
	}
	
	/**
	 * Construtor privado para evitar instâncias da classe
	 * @author Romildo Júnior
	 */
	private RSA() {
		
	}
	
}