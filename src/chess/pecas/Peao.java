package chess.pecas;

import boardgame.Posicao;
import boardgame.Tabuleiro;
import chess.Cor;
import chess.PecaChess;

public class Peao extends PecaChess{

	public Peao(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public boolean[][] possibleMoves() {

		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao p = new Posicao(0, 0);
		
		if (getCor() == Cor.BRANCA) {
			p.setValues(posicao.getFileira() - 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().haUmaPeca(p)) {
				mat[p.getFileira()][p.getColuna()] = true;
			}
			p.setValues(posicao.getFileira() - 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getFileira() - 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().haUmaPeca(p) && getTabuleiro().posicaoExiste(p2) && !getTabuleiro().haUmaPeca(p2) && getMoveConta() == 0) {
				mat[p.getFileira()][p.getColuna()] = true;
			}
			p.setValues(posicao.getFileira() - 1, posicao.getColuna() - 1);
			if (getTabuleiro().posicaoExiste(p) && isThereOpponentPieace(p)) {
				mat[p.getFileira()][p.getColuna()] = true;
			}
			p.setValues(posicao.getFileira() - 1, posicao.getColuna() + 1);
			if (getTabuleiro().posicaoExiste(p) && isThereOpponentPieace(p)) {
				mat[p.getFileira()][p.getColuna()] = true;
			}
		}
		else {
			p.setValues(posicao.getFileira() + 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().haUmaPeca(p)) {
				mat[p.getFileira()][p.getColuna()] = true;
			}
			p.setValues(posicao.getFileira() + 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getFileira() + 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().haUmaPeca(p) && getTabuleiro().posicaoExiste(p2) && !getTabuleiro().haUmaPeca(p2) && getMoveConta() == 0) {
				mat[p.getFileira()][p.getColuna()] = true;
			}
			p.setValues(posicao.getFileira() + 1, posicao.getColuna() - 1);
			if (getTabuleiro().posicaoExiste(p) && isThereOpponentPieace(p)) {
				mat[p.getFileira()][p.getColuna()] = true;
			}
			p.setValues(posicao.getFileira() + 1, posicao.getColuna() + 1);
			if (getTabuleiro().posicaoExiste(p) && isThereOpponentPieace(p)) {
				mat[p.getFileira()][p.getColuna()] = true;
			}
		}
		
		return mat;
	}

	@Override
	public String toString() {
		return "P";
	}
	
	

}
