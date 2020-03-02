package servidor;

import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ServidorSocket {

	public static void main(String[] args) {
		try {
			// Instancia o ServerSocket ouvindo a porta 3001
			ServerSocket servidor = new ServerSocket(3001);
			System.out.println("Servidor ouvindo a porta 3001");
			while (true) {
				// o método accept() bloqueia a execução até que
				// o servidor receba um pedido de conexão
				Socket cliente = servidor.accept();
				System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());
				ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());
				saida.flush();
				saida.writeObject(new Date());
				saida.close();
				cliente.close();
			}
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

}
