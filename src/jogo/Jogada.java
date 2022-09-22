package jogo;

/**
 * Classe que representa a jogada de um jogador.
 * 
 * @author felipe
 * @version 1.0
 */
@SuppressWarnings("resource")
public class Jogada {

	/**
	 * Ambos os atriutos fazem feferência ao eixo x-y na matriz que representam
	 * o tabuleiro.
	 */
	private int x;
	private int y;

	public Jogada(String str) throws JogadaInvalidaException {
		parseString(str);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	/**
	 * Quebram a String recebida como parametro e abastecem os atributos x e y da
	 * classe.
	 * 
	 * @param jogada
	 * @throws JogadaInvalidaException
	 */
	private void parseString(String jogada) throws JogadaInvalidaException {
		try {
			// Separam a string em tokens delimitados a partir de ",", "." ou " ".
			String[] tokens = jogada.split("[\\,]|[\\.]|[\\s]");

			// Armazeam os toquens e elimina eventuais espaços residuais.
			this.x = Integer.parseInt(tokens[0].trim());
			this.y = Integer.parseInt(tokens[1].trim());

		} catch (Exception e) {
			throw new JogadaInvalidaException("Jogada inválida!");
		}
	}

}
