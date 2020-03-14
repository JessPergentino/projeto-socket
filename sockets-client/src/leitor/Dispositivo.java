package leitor;

import enumeracoes.EstadosEnum;

public class Dispositivo {

	private String nome;
	private String ip;
	private String porta;
	private EstadosEnum estado;
	private int atualizacao;

	public Dispositivo() {
	}
	
	public Dispositivo(String nome) {
		this.nome = nome;
	}

	public Dispositivo(String nome, String ip, String porta, EstadosEnum estado, int atualizacao) {
		this.nome = nome;
		this.ip = ip;
		this.porta = porta;
		this.estado = estado;
		this.atualizacao = atualizacao;
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

	public String getPorta() {
		return porta;
	}

	public void setPorta(String porta) {
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
		return "Dispositivo [nome=" + nome + ", ip=" + ip + ", porta=" + porta + "]";
	}

}
