package com.krypto.util;

import java.math.BigInteger;
import java.util.List;
import java.util.Vector;
import com.krypto.exceptions.InvalidCharacterException;
import com.krypto.exceptions.InvalidCharCodeException;

/**
 * Classe que descreve o codificador de caracteres
 * @author Romildo Júnior
 */
public class MessageEncoder {
	/**
	 * Associa a cada caractere um valor módulo 27
	 * @param c
	 * @return Número associado ao caractere
	 * @author Romildo Júnior
	 */
	public int charToCode(char c) throws InvalidCharacterException {
		switch (c) {
		case ' ':
			return 0;
		case 'a':
			return 1;
		case 'b':
			return 2;
		case 'c':
			return 3;
		case 'd':
			return 4;
		case 'e':
			return 5;
		case 'f':
			return 6;
		case 'g':
			return 7;
		case 'h':
			return 8;
		case 'i':
			return 9;
		case 'j':
			return 10;
		case 'k':
			return 11;
		case 'l':
			return 12;
		case 'm':
			return 13;
		case 'n':
			return 14;
		case 'o':
			return 15;
		case 'p':
			return 16;
		case 'q':
			return 17;
		case 'r':
			return 18;
		case 's':
			return 19;
		case 't':
			return 20;
		case 'u':
			return 21;
		case 'v':
			return 22;
		case 'w':
			return 23;
		case 'x':
			return 24;
		case 'y':
			return 25;
		case 'z':
			return 26;
		default:
			throw new InvalidCharacterException("Invalid char at function charToCode");
		}
	}

	/**
	 * Retorna o caractere associado ao código informado
	 * @param code
	 * @return c tal que charToCode(c) = code
	 * @author Romildo Júnior
	 */
	public char codeToChar(int code) throws InvalidCharCodeException{
		switch (code) {
		case 0:
			return ' ';
		case 1:
			return 'a';
		case 2:
			return 'b';
		case 3:
			return 'c';
		case 4:
			return 'd';
		case 5:
			return 'e';
		case 6:
			return 'f';
		case 7:
			return 'g';
		case 8:
			return 'h';
		case 9:
			return 'i';
		case 10:
			return 'j';
		case 11:
			return 'k';
		case 12:
			return 'l';
		case 13:
			return 'm';
		case 14:
			return 'n';
		case 15:
			return 'o';
		case 16:
			return 'p';
		case 17:
			return 'q';
		case 18:
			return 'r';
		case 19:
			return 's';
		case 20:
			return 't';
		case 21:
			return 'u';
		case 22:
			return 'v';
		case 23:
			return 'w';
		case 24:
			return 'x';
		case 25:
			return 'y';
		case 26:
			return 'z';
		default:
			
			throw new InvalidCharCodeException("Invalid character code in function codeToChar");
		}
	}

	/**
	 * Calcula a codificação da frase
	 * @param text
	 * @return Codificação ou -1 caso seja informado algum caractere não conhecido.
	 * @author Romildo Júnior
	 * @throws InvalidCharacterException 
	 */
	public BigInteger strToCode(String text) throws InvalidCharacterException {
		BigInteger cod = BigInteger.ZERO;

		for(long i = 0; i < text.length() && cod.signum() >= 0; ++i) {
			BigInteger aux;
			
			aux = new BigInteger(Integer.toString((int)charToCode(text.charAt((int) i))));
			
			cod = cod.add(aux.multiply(Function.repeatedSquare(BigInteger.valueOf(27), BigInteger.valueOf(i))));
		}
		return cod.signum() >= 0 ? cod : BigInteger.valueOf(-1);
	}
	/**
	 * Restora a frase a partir da codificação
	 * @param strCode
	 * @return Mensagem
	 * @author Romildo Júnior
	 */
	public String codeToStr(BigInteger strCode) throws InvalidCharCodeException{
		List<Character> chars = new Vector<>();

		BigInteger q = strCode; 

		while(q.equals(BigInteger.ZERO) == false) {
			BigInteger r = q.mod(BigInteger.valueOf(27));
			q = q.divide(BigInteger.valueOf(27));
			chars.add(codeToChar(r.intValue()));
		}

		String message = new String();
		
		for(Character c : chars) {
			message += c;
		}
		return message;
	}
}

