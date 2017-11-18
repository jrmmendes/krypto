package com.krypto.test;

import java.math.BigInteger;

import org.junit.Test;

import com.krypto.exceptions.InvalidCharCodeException;
import com.krypto.exceptions.InvalidCharacterException;
//import com.krypto.rsa.RSAUser;
import com.krypto.util.MessageEncoder;

public class EncoderTest{

	@Test
	public void test() throws InvalidCharacterException,InvalidCharCodeException {
		MessageEncoder encoder = new MessageEncoder();
		System.out.println("Starting encoding...");
		long start = System.currentTimeMillis();
		String message = new String();
		BigInteger messageCode = BigInteger.ZERO;

		messageCode = encoder.strToCode("ataque amanha no local combinado");

		long end = System.currentTimeMillis();
		System.out.println("Finished! \nElapsed time: "+ (end-start) + " ms");
		System.out.println("Message code: "+ messageCode.toString());

		System.out.println("Starting decoding...");
		start = System.currentTimeMillis();

		message = encoder.codeToStr(messageCode);
		end = System.currentTimeMillis();
		System.out.println();
		System.out.println("Finished! \nElapsed time: "+ (end-start) + " ms");
		System.out.println("Original message: "+ message);

	}
}
