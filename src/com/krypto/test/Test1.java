package com.krypto.test;

import java.math.BigInteger;
import com.krypto.exceptions.InvalidCharCodeException;
import com.krypto.exceptions.InvalidCharacterException;
import com.krypto.rsa.*;
import com.krypto.util.*;
import org.junit.Test;

/**
 * Teste de aplicação das chaves em uma mensagem
 * @author Romildo Júnior
 * 
 */
public class Test1 {
	@Test
	public void test() throws InvalidCharacterException, InvalidCharCodeException {
		
		MessageEncoder encoder = new MessageEncoder();
		String mensagemOriginal = "ataque amanha no local combinado";
		BigInteger y, m, x;
		BigInteger e = new BigInteger("28736213868553512890273340527625389157173470871338452397444621859759230980830444846407332708944954006445141754457522377270911330090401367634596689745832236399296758364435749923514527977424897606029959257128417667439156620561151017886062723858000571093135103897107718731995743041936751339683074106889064702077");
		BigInteger d = new BigInteger("15441729572895183023445556714791769994206600954497476303092771833475040613271632228654061828623400708630454139003437363727617415586040480346012313254343772307943104405481141580565795481311183221523765750431089102221271676333433321742592151209988479361031208039081111242604586263307989773746893172595665379566886179253262732213633969184275174618344486294493471704046359916451879543734950910166737552557241786093929720675369353071104718675773024670855129227703087240057843315853612571002481388822636364534474953732669135157817464594617432514881772247116823293567449168215143149256318982753846945368702356409890753870837");
		BigInteger n = new BigInteger("50355769997200769496535066346888451729773909695822904878605869887538317013505484877121538079708602668856656523687194899780346378317038508950274879853449320933252073261102260369097849244532843248187383981536529846849564234374070468669273687175121446237423670953962787136642913183552922998536086435472218947179");
		
		System.out.println("Mensagem: "+mensagemOriginal);
		
		//Codificacao da mensagem
		m = encoder.strToCode(mensagemOriginal);
		System.out.println("\nCodificação: "+m.toString());
		
		//Geração do criptograma
		long start = System.currentTimeMillis();
		System.out.println("Criptografando...");
		y = RSA.encrypt(m, e, n);
		
		long end = System.currentTimeMillis();

		System.out.println("Processo finalizado!");
		System.out.println("Tempo decorrido: " +(end - start) + "ms");

		System.out.println("Criptograma: "+y.toString());
		
		//Recuperação da mensagem original
		System.out.println("\nDecifrando...");
		start = System.currentTimeMillis();
		x = RSA.decrypt(y, d, n);
		end = System.currentTimeMillis();

		System.out.println("Processo finalizado!");
		System.out.println("Tempo decorrido: " +(end - start) + "ms");
		System.out.println("Mensagem original: " + encoder.codeToStr(x));
	}
}
