package main;
import java.io.IOException;

import dispositivo.Dispositivo;
import enumeracoes.EstadosEnum;

public class Umidade {

	static Dispositivo umidade;

	public static void main(String[] args) {
		umidade = new Dispositivo("Umidade", "192.168.1.67", 3001, EstadosEnum.ATIVADO, 10);
		new Thread(r1).start();

	}

	private static Runnable r1 = new Runnable() {
		public void run() {
			try {
				umidade.escutarDispositivos();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	};

}
