package client;

import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocket {

	static final String IP = "192.168.1.67";
	static final int PORTA = 3001;
	
	public static void main(String[] args) {
		try {	
			Socket cliente = new Socket(IP, PORTA);
			
			ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
			String string = (String) entrada.readObject();
			
			System.out.println("Mensagem recebida do servidor: " + string);
			
			Scanner teclado = new Scanner(System.in);
	        PrintStream saida = new PrintStream(cliente.getOutputStream());

	        saida.flush();
	        while (teclado.hasNextLine()) {
	            saida.println(teclado.nextLine() + "\r\n");
	        }
	        
	        saida.close();
	        teclado.close();
			entrada.close();
			cliente.close();
			System.out.println("Conexão encerrada");
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}

	}

}
