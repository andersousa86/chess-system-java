package chess.pecas;

import boardgame.Posicao;
import boardgame.Tabuleiro;
import chess.Cor;
import chess.PecaChess;

public class Torre extends PecaChess {

	public Torre(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "T";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao p = new Posicao(0, 0);

		// Above-Acima
		p.setValues(posicao.getFileira() - 1, posicao.getColuna());
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().haUmaPeca(p)) {
			mat[p.getFileira()][p.getColuna()] = true;
			p.setFileira(p.getFileira() - 1);
		}
		if (getTabuleiro().posicaoExiste(p) && isThereOpponentPieace(p)) {
			mat[p.getFileira()][p.getColuna()] = true;
		}

		// Left-Esquerda
		p.setValues(posicao.getFileira() - 1, posicao.getColuna() - 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().haUmaPeca(p)) {
			mat[p.getFileira()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}
		if (getTabuleiro().posicaoExiste(p) && isThereOpponentPieace(p)) {
			mat[p.getFileira()][p.getColuna()] = true;
		}

		// Right-Direita
		p.setValues(posicao.getFileira() - 1, posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().haUmaPeca(p)) {
			mat[p.getFileira()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}
		if (getTabuleiro().posicaoExiste(p) && isThereOpponentPieace(p)) {
			mat[p.getFileira()][p.getColuna()] = true;
		}
		
		//Below-Abaixo
		p.setValues(posicao.getFileira() + 1, posicao.getColuna());
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().haUmaPeca(p)) {
			mat[p.getFileira()][p.getColuna()] = true;
			p.setFileira(p.getFileira() + 1);
		}
		if (getTabuleiro().posicaoExiste(p) && isThereOpponentPieace(p)) {
			mat[p.getFileira()][p.getColuna()] = true;
		}

		return mat;
	}

}
