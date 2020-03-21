package servidor;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TrataCliente implements Runnable {

	private Socket cliente;

	public TrataCliente(Socket cliente) {
		this.cliente = cliente;
	}

	@Override
	public void run() {
		ObjectOutputStream saida;
		try {
			saida = new ObjectOutputStream(this.cliente.getOutputStream());
			saida.flush();
			saida.writeObject("Mensagem Servidor");
			Scanner s = new Scanner(this.cliente.getInputStream());
			while (s.hasNextLine()) {
				System.out.println(s.nextLine());
			}
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
