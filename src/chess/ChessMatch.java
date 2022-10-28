package chess;

import boardgame.Tabuleiro;
import chess.pecas.Rei;
import chess.pecas.Torre;

public class ChessMatch {
	
	private Tabuleiro tabuleiro;
	
	public ChessMatch() {
		tabuleiro = new Tabuleiro(8, 8);
		iniciarSetup();
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
	
	private void colocarNovaPeca(char coluna, int linha, PecaChess peca) {
		tabuleiro.coloarPeca(peca, new ChessPosicao(coluna, linha).toPosicao());
	}
	
	private void iniciarSetup() {
		colocarNovaPeca('b', 6, new Torre(tabuleiro, Cor.BRANCA));
		colocarNovaPeca('e', 8, new Rei(tabuleiro, Cor.PRETA));
		colocarNovaPeca('e', 1, new Rei(tabuleiro, Cor.BRANCA));
	}

}
