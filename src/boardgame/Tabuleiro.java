package boardgame;

public class Tabuleiro {
	
	private int linhas;
	private int colunas;
	private Peca[][] pecas;
	
	public Tabuleiro(int linhas, int colunas) {
		if (linhas < 1 || colunas < 1) {
			throw new TabuleiroExcecoes("Erro ao criar tabuleiro: linhas e colunas devem ser maiores que 1!");
		}
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Peca[linhas][colunas];
	}

	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}
	
	public Peca peca(int linha, int coluna) {
		if (!posicaoExiste(linha, coluna)) {
			throw new TabuleiroExcecoes("Posição não existe no tabuleiro!");
		}
		return pecas[linha][coluna];
	}
	
	public Peca peca(Posicao posicao) {
		if (!posicaoExiste(posicao)) {
			throw new TabuleiroExcecoes("Posição não existe no tabuleiro!");
		}
		return pecas[posicao.getFileira()][posicao.getColuna()];
	}
	
	public void coloarPeca(Peca peca, Posicao posicao) {
		if (haUmaPeca(posicao)) {
			throw new TabuleiroExcecoes("Ja existe uma peca na posicao: " + posicao);
		}
		pecas[posicao.getFileira()][posicao.getColuna()] = peca;
		peca.posicao = posicao;
	}
	
	public Peca removaPeca(Posicao posicao) {
		if (!posicaoExiste(posicao)) {
			throw new TabuleiroExcecoes("Posição não existe no tabuleiro!");
		}
		Peca aux = peca(posicao);
		pecas[posicao.getFileira()][posicao.getColuna()] = null;
		return aux;
	}
	
	private boolean posicaoExiste(int linha, int coluna) {
		return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
	}
	
	public boolean posicaoExiste(Posicao posicao) {
		return posicaoExiste(posicao.getFileira(), posicao.getColuna());
	}
	
	public boolean haUmaPeca(Posicao posicao) {
		if (!posicaoExiste(posicao)) {
			throw new TabuleiroExcecoes("Posição não existe no tabuleiro!");
		}
		return peca(posicao) != null;
	}
	
}
