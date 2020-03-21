package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocket {

	static final int PORTA = 3001;

	public static void main(String[] args) throws IOException {
		ServerSocket servidor = new ServerSocket(PORTA);
		System.out.println("Servidor ouvindo a porta " + PORTA);

		while (true) {
			Socket cliente = servidor.accept();
			System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());

			TrataCliente tc = new TrataCliente(cliente);
			new Thread(tc).start();

		}
	}

}
