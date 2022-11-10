package chess.pecas;

import boardgame.Posicao;
import boardgame.Tabuleiro;
import chess.Cor;
import chess.PecaChess;

public class Bispo extends PecaChess {

	public Bispo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "B";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao p = new Posicao(0, 0);

		// Noroeste
		p.setValues(posicao.getFileira() - 1, posicao.getColuna() - 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().haUmaPeca(p)) {
			mat[p.getFileira()][p.getColuna()] = true;
			p.setValues(p.getFileira() - 1, p.getColuna() - 1);
		}
		if (getTabuleiro().posicaoExiste(p) && isThereOpponentPieace(p)) {
			mat[p.getFileira()][p.getColuna()] = true;
		}

		// Nordeste
		p.setValues(posicao.getFileira() - 1, posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().haUmaPeca(p)) {
			mat[p.getFileira()][p.getColuna()] = true;
			p.setValues(p.getFileira() - 1, p.getColuna() + 1);;
		}
		if (getTabuleiro().posicaoExiste(p) && isThereOpponentPieace(p)) {
			mat[p.getFileira()][p.getColuna()] = true;
		}

		// Sudeste
		p.setValues(posicao.getFileira() + 1, posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().haUmaPeca(p)) {
			mat[p.getFileira()][p.getColuna()] = true;
			p.setValues(p.getFileira() + 1, p.getColuna() + 1);
		}
		if (getTabuleiro().posicaoExiste(p) && isThereOpponentPieace(p)) {
			mat[p.getFileira()][p.getColuna()] = true;
		}
		
		//Sudoeste
		p.setValues(posicao.getFileira() + 1, posicao.getColuna() - 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().haUmaPeca(p)) {
			mat[p.getFileira()][p.getColuna()] = true;
			p.setValues(p.getFileira() + 1, p.getColuna() - 1);
		}
		if (getTabuleiro().posicaoExiste(p) && isThereOpponentPieace(p)) {
			mat[p.getFileira()][p.getColuna()] = true;
		}

		return mat;
	}

}
