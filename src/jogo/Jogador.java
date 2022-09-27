package jogo;

import java.io.BufferedReader;
import java.io.PrintStream;

// Classe que representa o jogador dentro do jogo
public class Jogador {

	private String nome;

	// Canais de entrada e saída de dados
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

	// Recebe a jogada a partir canal de comunicaçâo com cliente
	public Jogada obterJogada() throws Exception {

		// sinaliza ao Cliente o momento de efetuar a jogada
		out.println("Escolha entre pedra papel tesoura");
		out.println("play");

		// recebe a jogada a partir do client-side
		String str = in.readLine();
		if (str.equals("pedra") || str.equals("papel") || str.equals("tesoura")) {
			return new Jogada(str);
		}
		return obterJogada();
	}

	public void send(String msg) {
		out.println(msg);
	}

	public void closeConnection() throws Exception {
		in.close();
		out.close();
	}
}