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

			if (!comando.contains("ngrok")) {
				System.out.println("Comando: " + comando);
				String[] comandoSeparado = comando.split(" ");
				int idConteiner = Integer.parseInt(comandoSeparado[1]);

				if (comando.contains(ComandosEnum.COLETAR.name())) {
					System.out.println("Coletando");
					dispositivo.setEstado(EstadosEnum.OCUPADO);

					dispositivo.setTempoDeslocamento(numeroAleatorio());
					Thread.sleep((long) (dispositivo.getTempoDeslocamento() * 1000));

					dispositivo.conectarConteiner(idConteiner, ComandosEnum.CHEGUEI_CONTAINER);
					System.out.println("Cheguei");
					dispositivo.setTempoDeslocamento(numeroAleatorio());
					Thread.sleep((long) (dispositivo.getTempoDeslocamento() * 1000));

					dispositivo.conectarCentral(1, ComandosEnum.COLETA_FINALIZADA);
					dispositivo.setEstado(EstadosEnum.LIVRE);
					System.out.println("Finalizei Coleta");
				}
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