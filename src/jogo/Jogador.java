package jogo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

// Classe que representa o jogador dentro do jogo da velha.
public class Jogador {

	private String nome;

	// Canais de untrada e saida de dados.
	private BufferedReader in;
	private PrintStream out;

	public Jogador(String nome, BufferedReader in, PrintStream out) {
		this.nome = nome;
		this.in = in;
		this.out = out;
	}

	public String getNome() {
		return nome;
	}

	public BufferedReader getIn() {
		return in;
	}

	public PrintStream getOut() {
		return out;
	}

	/**
	 * Recebe a jogada a partir canal de comunicaçâo com cliente.
	 * 
	 * @throws IOException, JogadaInvalidaException
	 */
	public Jogada obterJogada() throws JogadaInvalidaException, IOException {

		// sinaliza ao Cliente o momento de efetuar a jogada.
		out.println("Escolha entre pedra papel tesoura");
		out.println("play");

		// recebe a jogada a partir do client-side.
		String str = in.readLine();
		if (str.equals("pedra") || str.equals("papel") || str.equals("tesoura")) {
			System.out.print("entrou no IF:");
			return new Jogada(str);
		}

		return obterJogada();
	}

	/**
	 * Envia mensagem ao cliente.
	 * 
	 * @param msg
	 */
	public void send(String msg) {
		out.println(msg);
	}

	/**
	 * Encerra a comunicação com o cliente.
	 * 
	 * @throws IOException
	 */
	public void closeConnection() throws IOException {
		in.close();
		out.close();
	}
}
