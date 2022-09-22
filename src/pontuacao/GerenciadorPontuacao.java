package pontuacao;

public interface GerenciadorPontuacao {

	public Integer getPontuacao(String nome);

	public void gravarPontuacao(String nome) throws PontuacaoException;

}
