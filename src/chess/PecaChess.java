package chess;

import boardgame.Peca;
import boardgame.Posicao;
import boardgame.Tabuleiro;

public abstract class PecaChess extends Peca{
	
	private Cor cor;
	private int moveConta;

	public PecaChess(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}
	
	public int getMoveConta() {
		return moveConta;
	}
	
	public void incrementarMoveConta() {
		moveConta++;
	}
	
	public void decrementarMoveConta() {
		moveConta--;
	}
	
	public ChessPosicao getChessPosicao() {
		return ChessPosicao.fromPosicao(posicao);
	}
	
	protected boolean isThereOpponentPieace(Posicao posicao) {
		PecaChess p = (PecaChess) getTabuleiro().peca(posicao);
		return p != null && p.getCor() != cor;
	}

}
