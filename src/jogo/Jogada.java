package jogo;

// Classe que representa a jogada de um jogador.
@SuppressWarnings("resource")
public class Jogada {

	public Jogada(String str) throws JogadaInvalidaException {
		getJogada(str);
	}

	/**
	 * @param jogada
	 * @throws JogadaInvalidaException
	 */
	private String getJogada(String jogada) throws JogadaInvalidaException {
		try {
			return jogada;
		} catch (Exception e) {
			throw new JogadaInvalidaException("Jogada inválida!");
		}
	}
}
