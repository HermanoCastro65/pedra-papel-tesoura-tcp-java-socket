package jogo;

import java.io.BufferedReader;
import java.io.PrintStream;

// Classe que representa o jogador dentro do jogo
public class Jogador {

	private String nome;
	private int pontos;

	// Canais de entrada e saída de dados
	private BufferedReader in;
	private PrintStream out;

	// Variável que define se jogador permanece no jogo
	private boolean game;

	public Jogador(String nome, BufferedReader in, PrintStream out) {
		this.nome = nome;
		this.pontos = 0;
		this.in = in;
		this.out = out;
		this.game = true;
	}

	public String getNome() {
		return nome;
	}

	public int getPontos() {
		return pontos;
	}

	public BufferedReader getIn() {
		return in;
	}

	public PrintStream getOut() {
		return out;
	}

	public boolean getGame() {
		return game;
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

	public void endGame() throws Exception {

		// pergunta ao Cliente se deseja jogar novamente
		out.println("end");

		// recebe a resposta a partir do client-side
		String resp = in.readLine();
		if (resp.equals("nao")) {
			this.game = false;
		}
	}

	public void plusPontos() {
		++pontos;
	}

	public void send(String msg) {
		out.println(msg);
	}

	public void closeConnection() throws Exception {
		in.close();
		out.close();
	}
}