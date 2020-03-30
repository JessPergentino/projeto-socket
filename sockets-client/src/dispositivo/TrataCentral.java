package dispositivo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Random;

import enumeracoes.ComandosEnum;
import enumeracoes.EstadosEnum;

public class TrataCentral implements Runnable {
	private Socket cliente;
	private Dispositivo dispositivo;

	public TrataCentral(Socket cliente, Dispositivo dispositivo) {
		this.cliente = cliente;
		this.dispositivo = dispositivo;
	}

	@Override
	public void run() {
		try {
			BufferedReader buffer = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			String comando = buffer.readLine();
			System.out.println("Comando: " + comando);
			String[] comandoSeparado = comando.split(" ");
			int idConteiner = Integer.parseInt(comandoSeparado[2]);

			if (comando.contains(ComandosEnum.COLETAR.name())) {
				dispositivo.setEstado(EstadosEnum.OCUPADO);

				dispositivo.setTempoDeslocamento(numeroAleatorio());
				new Thread();
				Thread.sleep((long) (dispositivo.getTempoDeslocamento() * 1000));

				dispositivo.conectarConteiner(idConteiner, ComandosEnum.CHEGUEI_CONTEINER);

				dispositivo.setTempoDeslocamento(numeroAleatorio());
				new Thread();
				Thread.sleep((long) (dispositivo.getTempoDeslocamento() * 1000));

				dispositivo.conectarCentral(1, ComandosEnum.COLETA_FINALIZADA);
				dispositivo.setEstado(EstadosEnum.LIVRE);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private static int numeroAleatorio() {
		return new Random().nextInt(20);
	}
}
