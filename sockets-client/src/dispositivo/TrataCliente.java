package dispositivo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Random;

import enumeracoes.EstadosEnum;

public class TrataCliente implements Runnable {
	private Socket cliente;
	private Dispositivo dispositivo;

	public TrataCliente(Socket cliente, Dispositivo dispositivo) {
		this.cliente = cliente;
		this.dispositivo = dispositivo;
	}

	@Override
	public void run() {
		try {
			BufferedReader buffer = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			System.out.println("Comando: " + buffer.readLine());

			PrintStream saida = new PrintStream(cliente.getOutputStream());
			saida.flush();
			if (dispositivo.getEstado().equals(EstadosEnum.ATIVADO)) {
				saida.println(dispositivo.getNome() + " " + dispositivo.getEstado() + " " + dispositivo.getAtualizacao());
				while (true) {
					new Thread();
					Thread.sleep( (long) (dispositivo.getAtualizacao() * 1000));
					Integer temperatura = numeroAleatorio() + 1;
					saida.println(temperatura.toString());
				}
			} else {
				saida.println(dispositivo.getEstado());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private static int numeroAleatorio() {
		return new Random().nextInt(50);
	}
}
