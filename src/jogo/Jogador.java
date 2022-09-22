package jogo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Classe que representa o jogador dentro do jogo da velha.
 * 
 * @author felipe
 *
 */
@SuppressWarnings("resource")
public class Jogador {

	/**
	 * Nome de jogador.
	 */
	private String nome;

	/**
	 * Simbulo que representa o jogador('X' ou 'O').
	 */
	private char simbolo;

	/**
	 * Canais de untrada e saida de dados.
	 */
	private BufferedReader in;
	private PrintStream out;

	public Jogador(String nome, char simbulo, BufferedReader in, PrintStream out) {
		this.nome = nome;
		this.simbolo = simbulo;
		this.in = in;
		this.out = out;
	}

	public String getNome() {
		return nome;
	}

	public char getSimbolo() {
		return simbolo;
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
		out.println("play");

		// recebe a jgada a partir do client-side.
		String str = in.readLine();

		return new Jogada(str);
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
