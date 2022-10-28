package chess;

import boardgame.Tabuleiro;

public class ChessMatch {
	
	private Tabuleiro tabuleiro;
	
	public ChessMatch() {
		tabuleiro = new Tabuleiro(8, 8);
	}
	
	public PecaChess[][] getPecas(){
		PecaChess[][] mat = new PecaChess[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for(int i=0; i<tabuleiro.getLinhas(); i++) {
			for(int j=0; j<tabuleiro.getColunas(); j++) {
				mat[i][j] = (PecaChess) tabuleiro.peca(i, j);
			}
		}
		return mat;
	}

}
