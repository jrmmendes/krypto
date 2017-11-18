package com.krypto.rsa;

import java.math.BigInteger;
import java.util.Random;

/**
 * Classe que descreve as chaves do RSA
 * @author Romildo Júnior
 */
public class RSAKeys {

	private static int isPrimeProb = 99;
	
	private BigInteger dKey;
	private BigInteger eKey;
	private BigInteger n;

	/**
	 * Método para calcular o valor da função Totiente de Euler
	 * @param q
	 * @param p
	 * @return (p-1)(q-1)
	 * @author Romildo Júnior
	 */
	private BigInteger calculatePhi(BigInteger q, BigInteger p) {
		return p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
	}
	/**
	 * Método para calcular o valor do parâmetro N
	 * @param p
	 * @param q
	 * @return pq
	 * @author Romildo Júnior
	 */
	private BigInteger calculateN(BigInteger p, BigInteger q) {
		return p.multiply(q);
	}

	/**
	 * Método para obter a chave privada
	 * @return Valor da chave privada
	 * @author Romildo Júnior
	 */
	public BigInteger getDKey() {
		return dKey;
	}

	/**
	 * Método para obter a chave pública
	 * @return Valor da chave pública
	 * @author Romildo Júnior
	 */
	public BigInteger getEKey() {
		return eKey;
	}

	/**
	 * Método para obter o valor de n
	 * @return Valor do parâmetro n
	 * @author Romildo Júnior
	 */
	public BigInteger getN() {
		return n;
	}

	/**
	 * Construtor da classe
	 * @param numberOfBits
	 * @author Romildo Júnior
	 */
	public RSAKeys(int numberOfBits) {
		Random rnd = new Random();
		BigInteger p = new BigInteger(512, rnd);
		BigInteger q = new BigInteger(512, rnd);
		BigInteger phi;
		BigInteger d = new BigInteger(numberOfBits, rnd);
		BigInteger e;

		// Cálculo dos primos p e q
		while(p.isProbablePrime(isPrimeProb) == false)
			p = p.nextProbablePrime();

		while(q.isProbablePrime(isPrimeProb) == false || q.equals(p) == true)
			q = q.nextProbablePrime();

		// Cálculo de phi e de n
		phi = this.calculatePhi(p, q);
		n = this.calculateN(p, q);

		// Cálculo das chaves
		while(d.gcd(phi).equals(BigInteger.ONE) == false)
			d = new BigInteger(numberOfBits, rnd);

		e = d.modInverse(phi);

		this.eKey = e;
		this.dKey = d;
	}
}
