package main;

import java.io.IOException;

import dispositivo.Dispositivo;
import enumeracoes.EstadosEnum;

public class Servidor {

	static Dispositivo disp;
	
	public static void main(String[] args) {
		disp = new Dispositivo("Temperatura_J", "192.168.1.67", 3000, EstadosEnum.ATIVADO, 5);
		new Thread(r1).start();
	}

	private static Runnable r1 = new Runnable() {
		public void run() {
			try {
				disp.escutarDispositivos();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	};

}
