package main;

import java.io.IOException;

import dispositivo.Dispositivo;
import enumeracoes.EstadosEnum;

public class Temperatura {

	static Dispositivo temperatura;
	
	public static void main(String[] args) {
		temperatura = new Dispositivo("Temperatura_1", "192.168.1.67", 3000, EstadosEnum.ATIVADO, 10);
		new Thread(r1).start();
	}

	private static Runnable r1 = new Runnable() {
		public void run() {
			try {
				temperatura.escutarDispositivos();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	};

}
