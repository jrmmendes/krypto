package com.krypto.test;

import java.math.BigInteger;

import org.junit.Test;

import com.krypto.exceptions.InvalidCharCodeException;
import com.krypto.exceptions.InvalidCharacterException;
import com.krypto.rsa.RSA;
import com.krypto.util.MessageEncoder;

public class Teste2 {
	@Test
	public void test() throws InvalidCharacterException, InvalidCharCodeException {

		MessageEncoder encoder = new MessageEncoder();
		String mensagemOriginal = "ataque amanha no local combinado";
		BigInteger yA, yB, m, xB, xA;

		BigInteger eA = new BigInteger("41510185932681364715273971047972672304855533412380343553934095289014450195474253997793878812442062145260511085149289754232353101023099257527039619769062495181479601233651182439850506206580320451155176094262018438710958944395013783472976247241917399529327854579706457240578030762050525351760585188147072980505");
		BigInteger dA = new BigInteger("3455889202900768205252597083039461395273523533211273716293217737380234308839568434216256965675648508736901172391011683050075702211413529416381974667202960347291772987340729951365762561121694012522370205288664628669284821724554764819744000404811880252839011219196024414986202506420058745886254480176605464870034393750115606284071895769658022144990061888639368505802250123769383254517757247989186767997521880714257432002314376866959314061328992807026541885160750802935243546104648658481511034319057970809207060429040469953170412356148206626172493148209982416590214327724488954711385561884851726944127976273752910273593");
		BigInteger nA = new BigInteger("57362624161218156029129412252740742250227832315792259741303102588852914351023149981564590569791562024694576575584949107279579467691085164049738522679930334481613130172373324571918081129851543221863508500725063647819076952129198181917807842882456906415922601298698066425248028316136630567315712270342773527453");

		BigInteger dB = new BigInteger("7726438379978258977812983143274310935506142506713145411404139101275305815080238744255747610656512458954648293051311249502382456683551707422284050768642352964155263087991265825674536859576106690330604528047108079348491865243679429026278741058730843981002543215949479134065682473867788482982452896318922519019348270390588244209527197350860968980432856516385636878474336102321673492937246360248003593464950692763951247139557039190857719986241421814072400679728107914738242525659257042840030569951838114332938939048390143749361542781800569913229469216015435367592186305988885851408752394813550758644577965826835700597859");
		BigInteger eB = new BigInteger("13958075183417870890069038427846603616225912565617978564855735183330244700817027354454467212394724363314369375246141182694889361220688771358131793562791175511564790106358303586051597651312678725646584334109810865257957377002610491257771528900995242760505062684248139457785308931789320121549297550398393077059");
		BigInteger nB = new BigInteger("15023636347435897275825601478220924166200938182123189993854842307496019897602936130858621253896412233011623464178685643584801923802433633384750858206009755351063561629123104882687047370697321515296461828201714346809198453567539001070611722723562410742654192208435041387285504584960958360118042141530165662571");

		System.out.println("Mensagem: "+mensagemOriginal);

		//Codificacao da mensagem
		m = encoder.strToCode(mensagemOriginal);
		System.out.println("\nCodificação: "+m.toString());

		//Assinatura
		long start = System.currentTimeMillis();
		System.out.println("Assinando...");
		yA = RSA.encrypt(m, eA, nA);

		long end = System.currentTimeMillis();

		System.out.println("Processo finalizado!");
		System.out.println("Tempo decorrido: " +(end - start) + "ms");

		System.out.println("Mensagem assinada: "+yA.toString());


		//Criptografia
		start = System.currentTimeMillis();
		System.out.println("Criptografando...");
		yB = RSA.encrypt(yA, eB, nB);

		end = System.currentTimeMillis();

		System.out.println("Processo finalizado!");
		System.out.println("Tempo decorrido: " +(end - start) + "ms");

		System.out.println("Criptograma: "+yB.toString());

		//Decifração
		System.out.println("\nDecifrando...");
		start = System.currentTimeMillis();
		xB = RSA.decrypt(yB, dB, nB);
		end = System.currentTimeMillis();

		System.out.println("Processo finalizado!");
		System.out.println("Tempo decorrido: " +(end - start) + "ms");
		System.out.println("Mensagem original assinada: " + xB.toString());
		
		//Verificação da assinatura da mensagem original
		System.out.println("\nDecifrando...");
		start = System.currentTimeMillis();
		xA = RSA.decrypt(xB, dA, nA);
		end = System.currentTimeMillis();

		System.out.println("Processo finalizado!");
		System.out.println("Tempo decorrido: " +(end - start) + "ms");
		System.out.println("Mensagem original: " + encoder.codeToStr(xA));


	}
}
