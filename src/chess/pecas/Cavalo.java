package chess.pecas;

import boardgame.Posicao;
import boardgame.Tabuleiro;
import chess.ChessMatch;
import chess.Cor;
import chess.PecaChess;

public class Cavalo extends PecaChess {
	
	public Cavalo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	private ChessMatch chessMatch;

	@Override
	public String toString() {
		return "C";
	}

	private boolean canMove(Posicao posicao) {
		PecaChess p = (PecaChess)getTabuleiro().peca(posicao);
		return p == null || p.getCor() != getCor();
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao p = new Posicao(0, 0);

		// MOVIMENTOS
		
		p.setValues(posicao.getFileira() - 1, posicao.getColuna() - 2);
		if (getTabuleiro().posicaoExiste(p) && canMove(p)) {
			mat[p.getFileira()][p.getColuna()] = true;
		}

		p.setValues(posicao.getFileira() - 2, posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExiste(p) && canMove(p)) {
			mat[p.getFileira()][p.getColuna()] = true;
		}

		p.setValues(posicao.getFileira() - 2, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExiste(p) && canMove(p)) {
			mat[p.getFileira()][p.getColuna()] = true;
		}

		p.setValues(posicao.getFileira() - 1, posicao.getColuna() + 2);
		if (getTabuleiro().posicaoExiste(p) && canMove(p)) {
			mat[p.getFileira()][p.getColuna()] = true;
		}

		p.setValues(posicao.getFileira() + 1, posicao.getColuna() + 2);
		if (getTabuleiro().posicaoExiste(p) && canMove(p)) {
			mat[p.getFileira()][p.getColuna()] = true;
		}

		p.setValues(posicao.getFileira() + 2, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExiste(p) && canMove(p)) {
			mat[p.getFileira()][p.getColuna()] = true;
		}

		p.setValues(posicao.getFileira() + 2, posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExiste(p) && canMove(p)) {
			mat[p.getFileira()][p.getColuna()] = true;
		}

		// Sudeste
		p.setValues(posicao.getFileira() + 1, posicao.getColuna() -2);
		if (getTabuleiro().posicaoExiste(p) && canMove(p)) {
			mat[p.getFileira()][p.getColuna()] = true;
		}

		return mat;
	}

}
