package dispositivo;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import enumeracoes.ComandosEnum;
import enumeracoes.EstadosEnum;

public class Dispositivo {
	private int id;
	private String ip;
	private int porta;
	private EstadosEnum estado = EstadosEnum.LIVRE;
	private int tempoDeslocamento;

	public Dispositivo() {
	}

	public Dispositivo(int id, String ip, int porta) {
		this.id = id;
		this.ip = ip;
		this.porta = porta;
	}

	public void conectarCentral(int id, ComandosEnum comando) throws UnknownHostException, IOException {
		Dispositivo disp = buscarDispositivo(id);

		Socket cliente = new Socket(disp.getIp(), disp.getPorta());

		PrintWriter out = new PrintWriter(cliente.getOutputStream(), true);
		out.flush();
		out.println(comando + " " + this.getId());
		cliente.close();
	}

	public void conectarConteiner(int id, ComandosEnum comando) throws UnknownHostException, IOException {
		Dispositivo disp = buscarDispositivo(id);

		Socket cliente = new Socket(disp.getIp(), disp.getPorta());

		PrintWriter out = new PrintWriter(cliente.getOutputStream(), true);
		out.flush();
		out.println(comando + " " + id);
		cliente.close();
	}

	public void escutarCentral() throws IOException {
		ServerSocket servidor = new ServerSocket(this.getPorta());
		System.out.println("Serviço " + this.getId() + " ouvindo porta " + this.getPorta());

		while (true) {
			Socket cliente = servidor.accept();

			TrataCentral tc = new TrataCentral(cliente, this);
			new Thread(tc).start();

		}
	}

	private static Dispositivo buscarDispositivo(int id) {
		List<Dispositivo> dipDisponiveis = Leitor.buscarDispositivos();

		for (Dispositivo dispositivo : dipDisponiveis) {
			if (dispositivo.getId() == id) {
				return dispositivo;
			}
		}
		return null;
	}

	public int getId() {
		return id;
	}

	public String getIp() {
		return ip;
	}

	public int getPorta() {
		return porta;
	}

	public EstadosEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadosEnum estado) {
		this.estado = estado;
	}

	public int getTempoDeslocamento() {
		return tempoDeslocamento;
	}

	public void setTempoDeslocamento(int tempoDeslocamento) {
		this.tempoDeslocamento = tempoDeslocamento;
	}

	@Override
	public String toString() {
		return "Dispositivo [Id=" + id + ", IP=" + ip + ", Porta=" + porta + "]";
	}

}
