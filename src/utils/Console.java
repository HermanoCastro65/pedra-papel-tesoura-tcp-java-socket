package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Classe utilitária usada para ler dados dá partir do console
 * 
 * @author felipe Campos
 *
 */
public class Console {
	/**
	 * Lê uma string do console
	 * 
	 * @return String lida
	 */
	public static String readString() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		} catch (IOException e) {
			throw new RuntimeException("Erro ao ler dados do teclado");
		}
	}
}
