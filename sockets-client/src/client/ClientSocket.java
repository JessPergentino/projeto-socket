package client;

import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientSocket {

	public static void main(String[] args) {
		try {
			Socket cliente = new Socket("127.0.0.1", 3001);
			ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
			System.out.println("Conectei de novo");
			entrada.close();
			cliente.close();
			System.out.println("Conexão encerrada");
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}

	}

}
