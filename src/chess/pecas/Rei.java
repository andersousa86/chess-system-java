package chess.pecas;

import boardgame.Tabuleiro;
import chess.Cor;
import chess.PecaChess;

public class Rei extends PecaChess{

	public Rei(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "R";
	}

}