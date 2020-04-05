package main;

import java.io.IOException;

import dispositivo.Dispositivo;

public class Teste {

	public static void main(String[] args) throws IOException {
		Dispositivo caminhao = new Dispositivo(1, "192.168.1.67", 8080);
		caminhao.escutarCentral();

	}

}
