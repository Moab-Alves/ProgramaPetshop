package ufpb.br.petshop;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;

public class GravadorDeDados {

	private String nomeArquivo;

	public GravadorDeDados(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public void gravaTextoEmArquivo(List<String> texto) throws IOException {
		BufferedWriter gravador = null;
		try {
			gravador = new BufferedWriter(new FileWriter(this.nomeArquivo));
			for (String s : texto) {
				gravador.write(s + "\n");
			}
		} finally {
			if (gravador != null) {
				gravador.close();
			}
		}
	}

	public List<String> recuperarTextoDeArquivo() throws IOException {
		BufferedReader leitor = null;
		List<String> texto = new ArrayList<>();
		try {
			leitor = new BufferedReader(new FileReader(this.nomeArquivo));
			String linha = null;
			do {
				linha = leitor.readLine();
				if (linha != null) {
					texto.add(linha);
				}
			} while (linha != null);
		} finally {
			if (leitor != null) {
				leitor.close();
			}
		}
		return texto;
	}

}
