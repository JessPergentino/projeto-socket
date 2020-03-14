package leitor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import enumeracoes.EstadosEnum;

public class Leitor {
	static final String NOME_ARQUIVO = "lista_dispositivos.txt";
	private static List<Dispositivo> lista = new ArrayList<>();

	public static List<Dispositivo> buscarDispositivos() {
		try {
			FileReader arq = new FileReader(NOME_ARQUIVO);
			BufferedReader lerArq = new BufferedReader(arq);

			String linha = lerArq.readLine();
			while (linha != null) {
				String[] linhaSeparada = linha.split(" ");
				lista.add(
						new Dispositivo(linhaSeparada[0], linhaSeparada[1], linhaSeparada[2], EstadosEnum.ATIVADO, 10000));
				linha = lerArq.readLine();
			}
			arq.close();
		} catch (IOException e) {
			System.err.print("Erro na abertura do arquivo:" + e.getMessage());
		}
		return lista;
	}
}
