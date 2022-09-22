package pontuacao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ArquivoGerenciadorPontuacao implements GerenciadorPontuacao {
	private static final String ARQUIVO = "pontuacao.txt";
	private Map<String, Integer> pontuacaoMap;

	public ArquivoGerenciadorPontuacao() throws PontuacaoException {
		pontuacaoMap = new HashMap<String, Integer>();
		inicializar();
	}

	private void inicializar() throws PontuacaoException {
		var arquivo = new File(ARQUIVO);

		if (!arquivo.exists()) {
			try {
				arquivo.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();

			}
		}

		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(arquivo));
			String line;

			while ((line = reader.readLine()) != null) {
				String[] tokens = line.split("\\|");

				pontuacaoMap.put(tokens[0].toUpperCase(), Integer.valueOf(tokens[1]));

			}
		} catch (IOException e) {
			throw new PontuacaoException(e.getMessage());
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					throw new PontuacaoException(e.getMessage());
				}
			}
		}

	}

	@Override
	public Integer getPontuacao(String nome) {
		return pontuacaoMap.get(nome.toUpperCase());
	}

	@Override
	public void gravarPontuacao(String nome) throws PontuacaoException {
		Integer pontuacao = getPontuacao(nome);

		if (pontuacao != null) {
			pontuacao = 0;
		}

		pontuacaoMap.put(nome.toUpperCase(), pontuacao + 1);

		try (var writer = new BufferedWriter(new FileWriter(ARQUIVO))) {
			for (Map.Entry<String, Integer> entry : pontuacaoMap.entrySet()) {
				writer.write(entry.getKey() + "|" + entry.getValue());

				writer.newLine();
			}
		} catch (IOException e) {
			throw new PontuacaoException(e.getMessage());
		}

	}

}
