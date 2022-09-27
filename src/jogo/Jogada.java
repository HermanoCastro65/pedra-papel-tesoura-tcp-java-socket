package jogo;

// Classe que representa a jogada de um jogador
public class Jogada {

	private String jogada;

	public Jogada(String jogada) {
		this.jogada = jogada;
	}

	public String getJogada() {
		try {
			return this.jogada;
		} catch (Error error) {
			throw error;
		}
	}
}