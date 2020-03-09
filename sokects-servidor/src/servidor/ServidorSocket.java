package servidor;

import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServidorSocket {

	static final int PORTA = 3001;
	
	public static void main(String[] args) {
		try {
			ServerSocket servidor = new ServerSocket(PORTA);
			System.out.println("Servidor ouvindo a porta 3001");
			
			while (true) {
				Socket cliente = servidor.accept();
				
				System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());
				ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());
				
				saida.flush();
				saida.writeObject("Mensagem Servidor");
				
				Scanner s = new Scanner(cliente.getInputStream());
		        while (s.hasNextLine()) {
		            System.out.println(s.nextLine());
		        }
				
		        s.close();
				saida.close();
				cliente.close();
			}
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

}
