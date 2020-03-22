package servidor;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TrataCliente implements Runnable {
	private Socket cliente;

	public TrataCliente(Socket cliente) {
		this.cliente = cliente;
	}

	@Override
	public void run() {
		try {
			PrintWriter out = new PrintWriter(cliente.getOutputStream(), true);
            out.flush();
            out.println("Mensagem Servidor." + "\r\n");
			
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
