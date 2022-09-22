package jogo;

import java.util.Arrays;

/**
 * Classe responsável pela representação do tabuleiro no jogo da velha.
 * 
 * @author felipe
 *
 */
@SuppressWarnings("resource")
public class Tabuleiro {
	private char[][] tabuleiro;

	/**
	 * Contrutor que inicia a matriz vazia - preenchida com espaços em branco (' ').
	 */
	public Tabuleiro() {
		this.tabuleiro = new char[3][3];
		zerar();
	}

	/**
	 * Limpa todas as posições do tabuleiro.
	 */
	private void zerar() {
		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro[0].length; j++) {
				tabuleiro[i][j] = ' ';
			}
		}
	}

	/**
	 * Exibe de maneira amigável ao usuário a matriz do tabuleiro.
	 */
	@Override
	public String toString() {
		String line1 = "\\x  0   1   2 \n";
		String line2 = "y\\ \n";
		String line3 = String.format("0   %c | %c | %c\n", tabuleiro[0][0], tabuleiro[0][1], tabuleiro[0][2]);
		String line4 = "   ___|___|___\n";
		String line5 = String.format("1   %c | %c | %c\n", tabuleiro[1][0], tabuleiro[1][1], tabuleiro[1][2]);
		String line6 = "   ___|___|___\n";
		String line7 = String.format("2   %c | %c | %c\n", tabuleiro[2][0], tabuleiro[2][1], tabuleiro[2][2]);
		String line8 = "      |   |   \n";

		return line1 + line2 + line3 + line4 + line5 + line6 + line7 + line8;

	}

	/**
	 * Recebe a jogada efetuada pelo jogador e a coloca no tabuleiro.
	 * 
	 * @param jogada
	 * @param simbulo
	 * @return retorna o resultado da verificação do tabuleiro.
	 * @throws JogadaInvalidaException
	 */
	public boolean putJogada(Jogada jogada, char simbulo) throws JogadaInvalidaException {
		int x = jogada.getX();
		int y = jogada.getY();

		if (tabuleiro[x][y] == ' ') {
			tabuleiro[x][y] = simbulo;
		} else {
			throw new JogadaInvalidaException("Campo já preenchido por um jogador!");
		}
		return isCompleto();
	}

	/**
	 * Verifica em que condições o tabuleiro sera considerado completo.
	 * 
	 * @return
	 */
	public boolean isCompleto() {
		if (tabuleiro[0][0] == tabuleiro[0][1] && tabuleiro[0][1] == tabuleiro[0][2] && tabuleiro[0][2] != ' ') {
			return true;
		} else if (tabuleiro[1][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[1][2] && tabuleiro[1][2] != ' ') {
			return true;
		} else if (tabuleiro[2][0] == tabuleiro[2][1] && tabuleiro[2][1] == tabuleiro[2][2] && tabuleiro[2][2] != ' ') {
			return true;
		} else if (tabuleiro[0][0] == tabuleiro[1][0] && tabuleiro[1][0] == tabuleiro[2][0] && tabuleiro[2][0] != ' ') {
			return true;
		} else if (tabuleiro[0][1] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][1] && tabuleiro[2][1] != ' ') {
			return true;
		} else if (tabuleiro[0][2] == tabuleiro[1][2] && tabuleiro[1][2] == tabuleiro[2][2] && tabuleiro[2][2] != ' ') {
			return true;
		} else if (tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2] && tabuleiro[2][2] != ' ') {
			return true;
		} else if (tabuleiro[2][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[0][2] && tabuleiro[0][2] != ' ') {
			return true;
		}

		return false;
	}
}
