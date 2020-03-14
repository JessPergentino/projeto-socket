package servidor;

import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import enumeracoes.ConfigConexaoEnum;
import leitor.Dispositivo;

public class ServidorSocket {

	public static void main(String[] args) {

		Dispositivo umidade = new Dispositivo("Umidade");
		Dispositivo temperatura = new Dispositivo("Temperatura_1");

		try {
			ServerSocket servidor = new ServerSocket(Integer.parseInt(ConfigConexaoEnum.PORTA.getValor()));
			System.out.println("Servidor ouvindo a porta " + ConfigConexaoEnum.PORTA.getValor());

			while (true) {
				Socket cliente = servidor.accept();

				ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());

				saida.flush();
				saida.writeObject(conectar(umidade, temperatura));

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
	
	private static String conectar(Dispositivo d1, Dispositivo d2) {
		return d1.getNome() + " CONECTAR " + d2.getNome();
	}

}
