/**
 * 
 */
package com.krypto.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Scanner;

import com.krypto.exceptions.*;
import com.krypto.rsa.*;
import com.krypto.util.*;

/**
 * Classe principal
 * @author Romildo Júnior
 *
 */
public class Main {
	static MessageEncoder encoder = new MessageEncoder();
	/**
	 * Método main
	 * @param args
	 * @author Romildo Júnior
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean exit = false;
		boolean isValidFile = true;
		String choice;

		long start, end;

		InputStream inputFile;
		OutputStream outputFile;
		String address;
		BigInteger eKey = null, dKey = null, n = null, eMessage, messageCode;
		String message = null;

		do {
			System.out.println();
			System.out.println("O que deseja fazer?");


			System.out.println();
			System.out.println("1 - Cifrar mensagem");
			System.out.println("2 - Decifrar mensagem");
			System.out.println("3 - Gerar par de chaves");
			System.out.println("4 - Sair");

			System.out.print("\n>> ");
			choice = scanner.next();
			System.out.println();

			switch(choice.charAt(0)) {
			case '1':
				do {
					System.out.println("Informe o local do arquivo que contém a mensagem: ");
					System.out.print(">> ");
					address = scanner.next();
					try {
						inputFile = new FileInputStream(address);
						Scanner scan = new Scanner(inputFile);
						message = scan.nextLine();
						scan.close();
						isValidFile = true;
					} catch (FileNotFoundException e) {
						System.out.println("\nArquivo não encontrado.");
						isValidFile = false;
					}

				}while(isValidFile == false);

				do {
					System.out.println("\nInforme o local do arquivo que contém a chave de encriptação: ");
					System.out.print(">> ");
					address = scanner.next();
					try {			
						inputFile = new FileInputStream(address);
						Scanner scan = new Scanner(inputFile);
						eKey = new BigInteger(scan.nextLine());
						n = new BigInteger(scan.nextLine());
						scan.close();

					} catch (FileNotFoundException e) {
						System.out.println("Arquivo não encontrado.");
						isValidFile = false;
					}
				}while(isValidFile == false);

				System.out.println("Criptografando mensagem...\n");
				start = System.currentTimeMillis();

				try {
					eMessage = RSA.encrypt(encoder.strToCode(message), eKey, n);
				} catch (InvalidCharacterException e1) {
					System.out.println("Há caracteres não reconhecidos na mensagem. Processo abortado.");
					eMessage = BigInteger.valueOf(-1);
				}

				if(eMessage.equals(BigInteger.valueOf(-1)) == false) {

					end = System.currentTimeMillis();

					System.out.println("Concluido!");
					System.out.println("Tempo gasto: "+ (end-start)/1000+"s\n");

					isValidFile = true;

					do {
						System.out.println("Informe o local do arquivo para armazenar a mensagem criptografada: ");
						System.out.print(">> ");
						address = scanner.next();
						try {
							outputFile = new FileOutputStream(address);
							PrintStream printer = new PrintStream(outputFile);
							printer.print(eMessage.toString());
							printer.close();
							isValidFile = true;
						} catch (FileNotFoundException e) {
							System.out.println("Não foi possível criar o arquivo.");
							isValidFile = false;
						}

					}while(isValidFile == false);
				}
				else
					System.out.println("Processo Abortado.\n");

				break;
			case '2':
				do {
					System.out.println("Informe o local do arquivo que contém a mensagem criptografada: ");
					System.out.print(">> ");
					address = scanner.next();
					try {
						inputFile = new FileInputStream(address);
						Scanner scan = new Scanner(inputFile);
						eMessage = new BigInteger(scan.nextLine());
						scan.close();
						isValidFile = true;
					} catch (FileNotFoundException e) {
						System.out.println("\nArquivo não encontrado.");
						isValidFile = false;
						eMessage = BigInteger.valueOf(-1);
					}
				}while(isValidFile == false);

				if(eMessage.equals(BigInteger.valueOf(-1)) == false) {

					do {
						System.out.println("\nInforme o local do arquivo que contém a chave de remoção da criptografia: ");
						System.out.print(">> ");
						address = scanner.next();
						try {			
							inputFile = new FileInputStream(address);
							Scanner scan = new Scanner(inputFile);
							dKey = new BigInteger(scan.nextLine());
							n = new BigInteger(scan.nextLine());
							scan.close();
							isValidFile = true;
						} catch (FileNotFoundException e) {
							System.out.println("Arquivo não encontrado.");
							isValidFile = false;
						}
					}while(isValidFile == false);

					System.out.println("Descriptografando mensagem...\n");
					start = System.currentTimeMillis();

					messageCode = RSA.decrypt(eMessage, dKey, n);

					end = System.currentTimeMillis();

					System.out.println("Concluido!");
					System.out.println("Tempo gasto: "+ (end-start)+"ms\n");

					try {
						message = encoder.codeToStr(messageCode);
					} catch (InvalidCharCodeException e1) {
						System.out.println("A codificação da mensagem é inválida. Processo abortado.");
						message = "NULL";
					}

					do {
						System.out.println("Informe o local do arquivo para armazenar a mensagem decifrada: ");
						System.out.print(">> ");
						address = scanner.next();
						try {
							outputFile = new FileOutputStream(address);
							PrintStream printer = new PrintStream(outputFile);
							printer.print(message.toString());
							printer.close();
							isValidFile = true;
						} catch (FileNotFoundException e) {
							System.out.println("Não foi possível criar o arquivo.");
							isValidFile = false;
						}

					}while(isValidFile == false);

				}
				else
					System.out.println("Processo Abortado.\n");
				break;
			case '3':

				RSAKeys keys;
				int keyLength;
				System.out.println("Informe o tamanho máximo, em bits, das chaves: ");
				System.out.print(">> ");
				keyLength = scanner.nextInt();
				System.out.println("\nGerando chaves...");
				start = System.currentTimeMillis();
				keys = new RSAKeys(keyLength);
				end = System.currentTimeMillis();

				System.out.println("Concluido!");
				System.out.println("Tempo gasto: "+ (end-start)+"ms\n");

				do {
					System.out.println("Informe o local do arquivo para guardar a chave d: ");
					System.out.print(">> ");
					address = scanner.next();

					try {
						outputFile = new FileOutputStream(address);
						PrintStream printer = new PrintStream(outputFile);
						printer.println(keys.getDKey().toString());
						printer.println(keys.getN().toString());
						printer.close();
						isValidFile = true;
					} catch (FileNotFoundException e) {
						System.out.println("Não foi possível criar o arquivo.");
						isValidFile = false;
					}

				}while(isValidFile == false);

				do {
					System.out.println("Informe o local do arquivo para guardar a chave e: ");
					System.out.print(">> ");
					address = scanner.next();

					try {
						outputFile = new FileOutputStream(address);
						PrintStream printer = new PrintStream(outputFile);
						printer.println(keys.getEKey().toString());
						printer.println(keys.getN().toString());
						printer.close();
					} catch (FileNotFoundException e) {
						System.out.println("Não foi possível criar o arquivo.");
						isValidFile = false;
					}

				}while(isValidFile == false);

				break;
			case '4':
				exit = true;
				break;
			default:
				System.out.println("Opção inválida! Abortado.");
				exit = true;
			}

		}while(exit != true);
		scanner.close();
	}
}