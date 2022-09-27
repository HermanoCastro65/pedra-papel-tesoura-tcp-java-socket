package jogo;

// Exception que representa uma jogada inválida efetuada pelo jogador.
public class JogadaInvalidaException extends JogoException {

	/**
	 * @param message
	 */
	public JogadaInvalidaException(String message) {
		super(message);
	}
}
