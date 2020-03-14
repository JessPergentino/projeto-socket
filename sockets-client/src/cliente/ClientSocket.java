package cliente;

import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;
import java.util.Random;

import enumeracoes.ConfigConexaoEnum;
import enumeracoes.EstadosEnum;
import leitor.Dispositivo;
import leitor.Leitor;

public class ClientSocket {

	public static void main(String[] args) {
		Dispositivo temperatura = new Dispositivo("Temperatura_1", ConfigConexaoEnum.IP.getValor(),
				ConfigConexaoEnum.PORTA.getValor(), EstadosEnum.ATIVADO, 30000);

		try {
			Socket cliente = new Socket(ConfigConexaoEnum.IP.getValor(),
					Integer.parseInt(ConfigConexaoEnum.PORTA.getValor()));
			PrintStream saida = new PrintStream(cliente.getOutputStream());

			ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
			String mensagem = (String) entrada.readObject();

			Dispositivo disp = tratarConexao(mensagem);

			if (verificarConexao(disp)) {
				saida.flush();
				if (temperatura.getEstado().equals(EstadosEnum.ATIVADO)) {
					saida.println(temperatura.getNome() + " " + temperatura.getEstado() + " "
							+ temperatura.getAtualizacao() + "\r\n");
					while (true) {
						new Thread();
						Thread.sleep(disp.getAtualizacao());
						saida.println(numeroAleatorio() + "\r\n");
					}
				} else {
					saida.println(temperatura.getEstado());
					saida.close();
					cliente.close();
				}
			} else {
				saida.println("Conexão Recusada");
				saida.close();
				cliente.close();
			}

			saida.close();
			entrada.close();
			cliente.close();
			System.out.println("Conexão encerrada");
		} catch (

		Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}

	}

	private static Dispositivo tratarConexao(String mensagem) {
		String[] dString = mensagem.split(" ");
		return new Dispositivo(dString[0]);
	}

	private static int numeroAleatorio() {
		return new Random().nextInt(50);
	}

	private static boolean verificarConexao(Dispositivo disp) {
		List<Dispositivo> dipDisponiveis = Leitor.buscarDispositivos();

		for (Dispositivo dispositivo : dipDisponiveis) {
			if (dispositivo.getNome().trim().equalsIgnoreCase(disp.getNome().trim())) {
				return true;
			}
		}
		return false;
	}
}
