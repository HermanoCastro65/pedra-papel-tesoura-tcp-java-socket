package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import jogo.Jogada;
import jogo.JogadaInvalidaException;
import jogo.Jogador;
import jogo.Tabuleiro;
import pontuacao.ArquivoGerenciadorPontuacao;
import pontuacao.GerenciadorPontuacao;
import pontuacao.PontuacaoException;

public class TCPServer {
	private int port;
	private ServerSocket serverSocket;

	private List<Jogador> jogadores = new ArrayList<Jogador>();

	public TCPServer(int port) {
		this.port = port;
	}

	/**
	 * Classe que serve o jogo aos clientes (jogadores).
	 * 
	 * @throws Exception
	 */
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

	/**
	 * Envia mesagem a toda lista de jogadores.
	 * 
	 * @param msg
	 */
	public void sendAll(String msg) {
		jogadores.forEach(e -> e.send(msg));

	}

	/**
	 * Inner class responsavel pela lógica de jogo
	 * 
	 * @author felipe
	 *
	 */
	private class Jogo {
		private Tabuleiro tabuleiro;
		private GerenciadorPontuacao gerenciadorPontuacao;
		private Jogador vencedor;

		public Jogo() throws PontuacaoException {
			this.tabuleiro = new Tabuleiro();
			this.gerenciadorPontuacao = new ArquivoGerenciadorPontuacao();
			this.vencedor = null;
		}

		public void start() throws IOException {
			aguardarJogadores();
			iniciarRodada();
		}

		/**
		 * A partir dos serviços da classe TCPServer estabelece a conexão com dois
		 * jogadores na rede 'localhost'.
		 * 
		 * @param serverSocket
		 * @throws IOException
		 */
		private void aguardarJogadores() throws IOException {
			int numJogadores = 0;
			char simbulo = 'X';

			// rotina para aceitar e estabelecer o numero de jogadores
			while (numJogadores < 2) {
				Socket clientSocket = TCPServer.this.serverSocket.accept();

				// Estabelece os fluxos de entrada e saida com os clientes/jogadores
				var in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				var out = new PrintStream(clientSocket.getOutputStream());// BufferedWriter(new
																			// OutputStreamWriter(clienteSocket.getOutputStream()));

				// Recebe o nome do jogador a partirdo fluxo de entrada
				String nome = in.readLine();

				System.out.println("Jogador '" + nome + "' conectado!");
				TCPServer.this.jogadores.add(new Jogador(nome, simbulo, in, out));

				simbulo = 'O';
				++numJogadores;
			}

		}

		private void iniciarRodada() {
			obterPontuacao();

			sendAll("start");
			boolean finalizado = false;
			int indexJogadorAtual = 0;

			while (!finalizado) {
				Jogador jogador = jogadores.get(indexJogadorAtual);

				sendAll("tabuleiro " + tabuleiro.toString());

				boolean sequenciaEncontrada = false;
				boolean jogadaValida = false;

				while (!jogadaValida) {
					try {
						Jogada jogada = jogador.obterJogada();
						sequenciaEncontrada = tabuleiro.putJogada(jogada, jogador.getSimbolo());
						jogadaValida = true;

					} catch (IOException | JogadaInvalidaException e) {
						jogador.send("error " + e.getMessage());
						continue;
					}
				}

				if (sequenciaEncontrada) {
					vencedor = jogador;
					finalizado = true;

				} else if (tabuleiro.isCompleto()) {
					finalizado = true;

				}

				indexJogadorAtual = (indexJogadorAtual + 1) % jogadores.size();

			}

			sendAll("tabuleiro " + tabuleiro.toString());

			if (vencedor == null) {

				// O jogo terminou empatado. Avisar os jogadores.
				sendAll("draw");
			} else {

				// O jogo teve um vencedor. Avisar os jogadores.
				sendAll("win " + vencedor.getNome());

				try {
					gerenciadorPontuacao.gravarPontuacao(vencedor.getNome());
				} catch (PontuacaoException e) {
					sendAll("error " + e.getMessage());
				}
			}

		}

		private void obterPontuacao() {
			for (var jogador : TCPServer.this.jogadores) {
				Integer pontuacao = gerenciadorPontuacao.getPontuacao(jogador.getNome());

				if (pontuacao != null) {
					String msg = "pontuacao: O jogador '%s' já possui %d %s!\n";

					msg = String.format(msg, jogador.getNome(), pontuacao, pontuacao == 1 ? "vitória" : "vitórias");

					sendAll(msg);

				} else {
					String msg = String.format("pontuacao: O jogador '%s' nao possui pontuação registrada",
							jogador.getNome());

					sendAll(msg);
				}
			}
		}

	}

}
