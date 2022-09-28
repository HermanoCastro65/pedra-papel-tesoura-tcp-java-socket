package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import console.Console;

public class TCPClient {
	private String host;
	private int port;

	public TCPClient(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void start() throws Exception {
		System.out.println("Conectando em " + host + ":" + port + "...");

		try (var socket = new Socket(host, port)) {
			var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			var out = new PrintStream(socket.getOutputStream());

			System.out.println("Conexão estabelecida!");

			System.out.println("Digite o nome do jogador: ");
			String nome = Console.readString();
			out.println(nome);

			System.out.println("aguardando o inicio da partida...");
			in.readLine();

			System.out.println("=======================");
			System.out.println("| PEDRA PAPEL TESOURA |");
			System.out.println("=======================");
			System.out.println();

			while (true) {
				// Aguarda mensagens do servidor
				String msg = in.readLine();
				System.out.println(msg);

				if (msg.equals("play")) {
					// Chegou sua vez de jogar
					System.out.print("Efetue sua jogada: ");
					String jogada = Console.readString();
					System.out.println();

					// Envia a jogada ao servidor
					out.println(jogada);
				}

				if (msg.equals("end")) {
					System.out.println("Deseja jogar outra rodada (sim ou não)");
					String end = Console.readString();
					System.out.println();

					out.println(end);
				}
			}
		} catch (Error error) {
			throw error;
		}
	}
}