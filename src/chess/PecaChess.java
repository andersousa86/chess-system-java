package chess;

import boardgame.Peca;
import boardgame.Tabuleiro;

public class PecaChess extends Peca{
	
	private Cor cor;

	public PecaChess(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}

}
