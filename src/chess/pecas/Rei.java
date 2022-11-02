package chess.pecas;

import boardgame.Posicao;
import boardgame.Tabuleiro;
import chess.ChessMatch;
import chess.Cor;
import chess.PecaChess;

public class Rei extends PecaChess {
	
	private ChessMatch chessMatch;

	public Rei(Tabuleiro tabuleiro, Cor cor, ChessMatch chessMatch) {
		super(tabuleiro, cor);
		this.chessMatch = chessMatch;
	}

	@Override
	public String toString() {
		return "R";
	}

	private boolean canMove(Posicao posicao) {
		PecaChess p = (PecaChess)getTabuleiro().peca(posicao);
		return p == null || p.getCor() != getCor();
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao p = new Posicao(0, 0);

		// Acima
		p.setValues(posicao.getFileira() - 1, posicao.getColuna());
		if (getTabuleiro().posicaoExiste(p) && canMove(p)) {
			mat[p.getFileira()][p.getColuna()] = true;
		}

		// Abaixo
		p.setValues(posicao.getFileira() + 1, posicao.getColuna());
		if (getTabuleiro().posicaoExiste(p) && canMove(p)) {
			mat[p.getFileira()][p.getColuna()] = true;
		}

		// Esquerda
		p.setValues(posicao.getFileira(), posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExiste(p) && canMove(p)) {
			mat[p.getFileira()][p.getColuna()] = true;
		}

		// Direita
		p.setValues(posicao.getFileira(), posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExiste(p) && canMove(p)) {
			mat[p.getFileira()][p.getColuna()] = true;
		}

		// Noroeste
		p.setValues(posicao.getFileira() - 1, posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExiste(p) && canMove(p)) {
			mat[p.getFileira()][p.getColuna()] = true;
		}

		// Nordeste
		p.setValues(posicao.getFileira() - 1, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExiste(p) && canMove(p)) {
			mat[p.getFileira()][p.getColuna()] = true;
		}

		// Sudoeste
		p.setValues(posicao.getFileira() + 1, posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExiste(p) && canMove(p)) {
			mat[p.getFileira()][p.getColuna()] = true;
		}

		// Sudeste
		p.setValues(posicao.getFileira() + 1, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExiste(p) && canMove(p)) {
			mat[p.getFileira()][p.getColuna()] = true;
		}

		return mat;
	}

}
