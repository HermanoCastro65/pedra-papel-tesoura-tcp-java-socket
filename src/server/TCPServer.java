package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import jogo.Jogador;

public class TCPServer {
	private int port;
	private ServerSocket serverSocket;
	private List<Jogador> jogadores = new ArrayList<Jogador>();

	public TCPServer(int port) {
		this.port = port;
	}

	// Classe que serve o jogo aos clientes (jogadores)
	public void startServer() throws Exception {
		serverSocket = new ServerSocket(this.port);
		System.out.println("Endereço local: " + serverSocket.getLocalSocketAddress());
		System.out.println("Serviço disponível. Porta: " + port + ". Aguardando os jogadores...");

		var jogo = new Jogo();
		jogo.start();

		System.out.println("A partida terminou!");

		// Fechar as conexões
		for (Jogador jogador : jogadores) {
			jogador.closeConnection();
		}
	}

	// Envia mesagem a toda lista de jogadores.
	public void sendAll(String msg) {
		jogadores.forEach(jogador -> jogador.send(msg));
	}

	// Classe responsavel pela lógica de jogo
	private class Jogo {
		private Jogador vencedor;

		public Jogo() {
			this.vencedor = null;
		}

		public void start() throws Exception {
			aguardarJogadores();
			iniciarRodada();
		}

		// TCPServer estabelece a conexão com dois jogadores na rede 'localhost'
		private void aguardarJogadores() throws Exception {
			int numJogadores = 0;

			// rotina para aceitar e estabelecer o numero de jogadores
			while (numJogadores < 2) {
				Socket clientSocket = TCPServer.this.serverSocket.accept();

				// Estabelece os fluxos de entrada e saida com os clientes/jogadores
				var in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				var out = new PrintStream(clientSocket.getOutputStream());

				// Recebe o nome do jogador a partirdo fluxo de entrada
				String nome = in.readLine();

				System.out.println("Jogador '" + nome + "' conectado!");
				TCPServer.this.jogadores.add(new Jogador(nome, in, out));

				++numJogadores;
			}
		}

		private void iniciarRodada() throws Exception {

			sendAll("start");
			int index = 0;
			Jogador jogador1 = jogadores.get(index);
			Jogador jogador2 = jogadores.get(index + 1);

			while (jogador1.getGame() && jogador2.getGame()) {
				try {
					placar(jogador1, jogador2);

					String jogada1 = jogador1.obterJogada().getJogada();
					String jogada2 = jogador2.obterJogada().getJogada();

					jogadores.forEach(jogador -> {
						jogador.getOut().println(jogador1.getNome() + ": " + jogada1);
						jogador.getOut().println(jogador2.getNome() + ": " + jogada2);
					});

					if (jogada1.equals(jogada2)) {
						jogadores.forEach(jogador -> jogador.getOut().println("Empate"));
						System.out.println("Empate");
					} else {
						if (jogada1.equals("pedra"))
							if (jogada2.equals("papel"))
								vencedor = jogador2;
							else
								vencedor = jogador1;

						if (jogada1.equals("papel"))
							if (jogada2.equals("pedra"))
								vencedor = jogador1;
							else
								vencedor = jogador2;

						if (jogada1.equals("tesoura"))
							if (jogada2.equals("pedra"))
								vencedor = jogador2;
							else
								vencedor = jogador1;

						vencedor.plusPontos();

						jogadores.forEach(jogador -> {
							jogador.getOut().println(vencedor.getNome() + " venceu");

							try {
								jogador.endGame();
							} catch (Exception error) {
								System.out.println(error.getMessage());
							}
						});

						System.out.println(vencedor.getNome() + " venceu");
					}
				} catch (Error error) {
					throw error;
				}
			}
		}

		private void placar(Jogador jogador1, Jogador jogador2) {
			jogadores.forEach(jogador -> {
				jogador.getOut().println("PLACAR ");
				jogador.getOut().println(
						jogador1.getNome() + " " + jogador1.getPontos() + " X " + jogador2.getPontos() + " " + jogador2.getNome());
			});
		}
	}
}