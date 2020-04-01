package dispositivo;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Scanner;

import enumeracoes.EstadosEnum;

public class Dispositivo {
	public static final String COMANDO = "CONECTAR";
	private String nome;
	private String ip;
	private int porta;
	private EstadosEnum estado;
	private int atualizacao;

	public Dispositivo() {
	}

	public Dispositivo(String nome, String ip, int porta, EstadosEnum estado, int atualizacao) {
		this.nome = nome;
		this.ip = ip;
		this.porta = porta;
		this.estado = estado;
		this.atualizacao = atualizacao;
	}
	
	public Dispositivo(String nome, String ip, int porta) {
		this.nome = nome;
		this.ip = ip;
		this.porta = porta;
	}
	
	public void conectarAoutroDispositivo(String nome) throws UnknownHostException, IOException {
		Dispositivo temperatura = buscarDispositivo(nome);

		Socket cliente = new Socket(temperatura.getIp(), temperatura.getPorta());

		PrintWriter out = new PrintWriter(cliente.getOutputStream(), true);
		out.flush();
		out.println(this.getNome() + " " + COMANDO + " " + temperatura.getNome());

		Scanner s = new Scanner(cliente.getInputStream());
		while (s.hasNextLine()) {
			System.out.println(s.nextLine());
		}
		s.close();
	}
	
	public void escutarDispositivos() throws IOException {
			ServerSocket servidor = new ServerSocket(this.getPorta());
			System.out.println("Serviço " + this.getNome() + " ouvindo porta " + this.getPorta());

			while (true) {
				Socket cliente = servidor.accept();

				TrataCliente tc = new TrataCliente(cliente, this);
				new Thread(tc).start();

			}
	}
	
	private static Dispositivo buscarDispositivo(String nome) {
		List<Dispositivo> dipDisponiveis = Leitor.buscarDispositivos();

		for (Dispositivo dispositivo : dipDisponiveis) {
			if (dispositivo.getNome().trim().equalsIgnoreCase(nome)) {
				return dispositivo;
			}
		}
		return null;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPorta() {
		return porta;
	}

	public void setPorta(int porta) {
		this.porta = porta;
	}

	public EstadosEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadosEnum estado) {
		this.estado = estado;
	}

	public int getAtualizacao() {
		return atualizacao;
	}

	public void setAtualizacao(int atualizacao) {
		this.atualizacao = atualizacao;
	}

	@Override
	public String toString() {
		return "Dispositivo [Nome=" + nome + ", IP=" + ip + ", Porta=" + porta + "]";
	}

}
