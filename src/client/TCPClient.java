package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import utils.Console;

public class TCPClient {
	private String host;
	private int port;
	
	public TCPClient(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	public void start() throws IOException {
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
			
			System.out.println("=================");
			System.out.println("| JOGO DA VELHA |");
			System.out.println("=================");
			System.out.println();
			
			while(true) {
				// Aguarda mensagens do servidor
				String msg = in.readLine();
				System.out.println(msg);
				
				if(msg.equals("play")) {
					// Chegou sua vez de jogar
					System.out.print("Efetue sua jogada: ");
					String jogada = Console.readString();
					System.out.println();
					
					// Envia a jogada ao servidor 
					out.println(jogada);
				
				} else if(msg.startsWith("error")) {
					// A última jogada foi inválida
					System.out.println(msg.substring(6));
				
				} else if(msg.startsWith("tabuleiro")) {
					// Carrega o tabuleiro para o lado cliente
					String tabuleiro = msg.substring(10);
					System.out.println(tabuleiro);
				
				} else if(msg.equals("draw")) {
					System.out.println("O jogo terminou empatado!\n");
					continue;
				
				} else if(msg.startsWith("win")) {
					System.out.println("O jogador '" + msg.substring(4) + "' venceu a partida!\n");
					continue;
					
				} else if(msg.startsWith("pontuacao:")) {
					System.out.println(msg.substring(12) + "\n");
					continue;
				
				}
			}
		}
	}
}

