package chess;

import boardgame.Peca;
import boardgame.Posicao;
import boardgame.Tabuleiro;

public abstract class PecaChess extends Peca{
	
	private Cor cor;

	public PecaChess(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}
	
	protected boolean isThereOpponentPieace(Posicao posicao) {
		PecaChess p = (PecaChess) getTabuleiro().peca(posicao);
		return p != null && p.getCor() != cor;
	}

}
