package enumeracoes;

public enum ConfigConexaoEnum {
	PORTA("3001"), IP("192.168.1.67");

	private String valor;

	ConfigConexaoEnum(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
}
