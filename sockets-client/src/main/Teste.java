package main;

import java.io.IOException;

import dispositivo.Dispositivo;
import enumeracoes.ComandosEnum;

public class Teste {

	static Dispositivo caminhao;

	public static void main(String[] args) throws IOException {
		caminhao = new Dispositivo(3, "192.168.1.67", 8080);
		new Thread(r1).start();
		new Thread(r2).start();

	}

	private static Runnable r1 = new Runnable() {
		public void run() {
			try {
				caminhao.conectarCentral(1, ComandosEnum.LIVRE);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	};

	private static Runnable r2 = new Runnable() {
		public void run() {
			try {
				caminhao.escutarCentral();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	};

}
