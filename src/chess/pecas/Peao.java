package chess.pecas;

import boardgame.Posicao;
import boardgame.Tabuleiro;
import chess.ChessMatch;
import chess.Cor;
import chess.PecaChess;

public class Peao extends PecaChess{
	
	private ChessMatch chessMatch;

	public Peao(Tabuleiro tabuleiro, Cor cor, ChessMatch chessMatch) {
		super(tabuleiro, cor);
		this.chessMatch = chessMatch;
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
			
			//Movimento especial en passant brancas
			if (posicao.getFileira() == 3) {
				Posicao left = new Posicao(posicao.getFileira(), posicao.getColuna() - 1);
				if (getTabuleiro().posicaoExiste(left) && isThereOpponentPieace(left) && getTabuleiro().peca(left) == chessMatch.getEnPassantVulneravel()) {
					mat[left.getFileira() - 1][left.getColuna()] = true;
				}
				Posicao right = new Posicao(posicao.getFileira(), posicao.getColuna() + 1);
				if (getTabuleiro().posicaoExiste(right) && isThereOpponentPieace(right) && getTabuleiro().peca(right) == chessMatch.getEnPassantVulneravel()) {
					mat[right.getFileira() - 1][right.getColuna()] = true;
				}
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
			
			//Movimento especial en passant pretas
			if (posicao.getFileira() == 4) {
				Posicao left = new Posicao(posicao.getFileira(), posicao.getColuna() - 1);
				if (getTabuleiro().posicaoExiste(left) && isThereOpponentPieace(left) && getTabuleiro().peca(left) == chessMatch.getEnPassantVulneravel()) {
					mat[left.getFileira() + 1][left.getColuna()] = true;
				}
				Posicao right = new Posicao(posicao.getFileira(), posicao.getColuna() + 1);
				if (getTabuleiro().posicaoExiste(right) && isThereOpponentPieace(right) && getTabuleiro().peca(right) == chessMatch.getEnPassantVulneravel()) {
					mat[right.getFileira() + 1][right.getColuna()] = true;
				}
			}
			
		}
		
		return mat;
	}

	@Override
	public String toString() {
		return "P";
	}
	
	

}
