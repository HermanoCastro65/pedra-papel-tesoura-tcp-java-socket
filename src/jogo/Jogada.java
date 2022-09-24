package jogo;

/**
 * Classe que representa a jogada de um jogador.
 * 
 * @author felipe
 * @version 1.0
 */
@SuppressWarnings("resource")
public class Jogada {

	public Jogada(String str) throws JogadaInvalidaException {
		getJogada(str);
	}

	/**
	 * Quebram a String recebida como parametro e abastecem os atributos x e y da
	 * classe.
	 * 
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
