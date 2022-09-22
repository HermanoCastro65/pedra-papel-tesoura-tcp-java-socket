package jogo;

/**
 * Exception que representa uma jogada inválida efetuada pelo jogador.
 * 
 * @author felipe
 *
 */
public class JogadaInvalidaException extends JogoDaVelhaException {

	/**
	 * @param message
	 */
	public JogadaInvalidaException(String message) {
		super(message);
	}

}
