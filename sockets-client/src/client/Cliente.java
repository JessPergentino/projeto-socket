package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
	static final String IP = "0.tcp.sa.ngrok.io";
	static final int PORTA = 14976;

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket cliente = new Socket(IP, PORTA);

		BufferedReader buffer = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
		System.out.println("Mensagem Cliente: " + buffer.readLine());

		Scanner teclado = new Scanner(System.in);
		PrintStream saida = new PrintStream(cliente.getOutputStream());

		saida.flush();
		while (teclado.hasNextLine()) {
			saida.println(teclado.nextLine() + "\r\n");
		}

	}

}
