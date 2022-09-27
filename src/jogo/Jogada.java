package jogo;

// Classe que representa a jogada de um jogador.
public class Jogada {

	private String jogada;

	public Jogada(String jogada) throws JogadaInvalidaException {
		this.jogada = jogada;
	}

	/**
	 * @param jogada
	 * @throws JogadaInvalidaException
	 */
	public String getJogada() throws JogadaInvalidaException {
		try {
			return this.jogada;
		} catch (Exception e) {
			throw new JogadaInvalidaException("Jogada inválida!");
		}
	}
}
