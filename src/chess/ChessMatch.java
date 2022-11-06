package chess;

import java.awt.Color;
import java.rmi.server.RemoteStub;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import boardgame.Peca;
import boardgame.Posicao;
import boardgame.Tabuleiro;
import chess.pecas.Rei;
import chess.pecas.Torre;

public class ChessMatch {

	private int turn;
	private Cor currentPlayer;
	private Tabuleiro tabuleiro;
	private boolean check;
	
	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> capturadasPecas = new ArrayList<>();

	public ChessMatch() {
		tabuleiro = new Tabuleiro(8, 8);
		turn = 1;
		currentPlayer = Cor.BRANCA;
		iniciarSetup();
	}
	
	public int getTurn() {
		return turn;
	}
	
	public Cor getCurrentPlayer() {
		return currentPlayer;
	}
	
	public boolean getCheck() {
		return check;
	}

	public PecaChess[][] getPecas() {
		PecaChess[][] mat = new PecaChess[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				mat[i][j] = (PecaChess) tabuleiro.peca(i, j);
			}
		}
		return mat;
	}
	
	public boolean[][] possibleMoves(ChessPosicao sourcePosicao){
		Posicao posicao = sourcePosicao.toPosicao();
		validarSourcePosicao(posicao);
		return tabuleiro.peca(posicao).possibleMoves();
	}
	
	public PecaChess performChessMove(ChessPosicao sourcePosicao, ChessPosicao targetPosicao) {
		Posicao source = sourcePosicao .toPosicao();
		Posicao target = targetPosicao.toPosicao();
		validarSourcePosicao(source);
		validateTargetPosition(source, target);
		Peca capturarPeca = makeMove(source, target);
		
		if (testCheck(currentPlayer)) {
			desfazerMove(source, target, capturarPeca);
			throw new ChessExcecao("Voce nao pode colcoar a peca em check!");
		}
		
		check = (testCheck(oponente(currentPlayer))) ? true : false;
		
		nexTurn();
		return (PecaChess) capturarPeca;
	}
	
	private Peca makeMove(Posicao source, Posicao target) {
		Peca p = tabuleiro.removaPeca(source);
		Peca capturarPeca = tabuleiro.removaPeca(target);
		tabuleiro.coloarPeca(p, target);
		if (capturarPeca != null) {
			pecasNoTabuleiro.remove(capturarPeca);
			capturadasPecas.add(capturarPeca);
		}
		return capturarPeca;
	}
	
	public void desfazerMove(Posicao origem, Posicao destino, Peca capturadaPeca) {
		Peca p = tabuleiro.removaPeca(destino);
		tabuleiro.coloarPeca(p, origem);
		if (capturadaPeca != null) {
			tabuleiro.coloarPeca(capturadaPeca, destino);
			pecasNoTabuleiro.add(capturadaPeca);
		}
	}
	
	private void validarSourcePosicao(Posicao posicao) {
		if (!tabuleiro.haUmaPeca(posicao)) {
			throw new ChessExcecao("Nao existe peca na posicao de origem!");
		}
		if (currentPlayer != ((PecaChess) tabuleiro.peca(posicao)).getCor()) {
			throw new ChessExcecao("A peca escolhida nao e a sua!");
		}
		if (!tabuleiro.peca(posicao).isThereAnyPossibleMove()) {
			throw new ChessExcecao("Nao existe movimentacao da peca na posicao de origem!");
		}
	}
	
	private void validateTargetPosition(Posicao source, Posicao target) {
		if (!tabuleiro.peca(source).possibleMoves(target)) {
			throw new ChessExcecao("Não é possível mover a peça para essa posição!");
		}
	}
		
	private void nexTurn() {
		turn++;
		currentPlayer = (currentPlayer == Cor.BRANCA) ? Cor.PRETA : Cor.BRANCA;
	}
	
	private Cor oponente(Cor cor) {
		return (cor == Cor.BRANCA) ? Cor.PRETA : Cor.BRANCA;
	}
	
	private PecaChess rei(Cor cor) {
		List<Peca> lista = pecasNoTabuleiro.stream().filter(x -> ((PecaChess)x).getCor() == cor).collect(Collectors.toList());
		for(Peca p : lista) {
			if (p instanceof Rei) {
				return (PecaChess)p;
			}
		}
		throw new IllegalStateException("Esta peca REI da cor " + cor + "nao esta no tabuleiro!");
	}

	private boolean testCheck(Cor cor) {
		Posicao reiPosicao = rei(cor).getChessPosicao().toPosicao();
		List<Peca> oponentePecas = pecasNoTabuleiro.stream().filter(x -> ((PecaChess)x).getCor() == oponente(cor)).collect(Collectors.toList());
		for(Peca p : oponentePecas) {
			boolean[][] mat = p.possibleMoves();
			if (mat[reiPosicao.getFileira()][reiPosicao.getColuna()]) {
				return true;
			}
		}
		return false;
	}

	private void colocarNovaPeca(char coluna, int linha, PecaChess peca) {
		tabuleiro.coloarPeca(peca, new ChessPosicao(coluna, linha).toPosicao());
		pecasNoTabuleiro.add(peca);
	}

	private void iniciarSetup() {
		colocarNovaPeca('c', 1, new Torre(tabuleiro, Cor.BRANCA));
		colocarNovaPeca('c', 2, new Torre(tabuleiro, Cor.BRANCA));
		colocarNovaPeca('d', 2, new Torre(tabuleiro, Cor.BRANCA));
		colocarNovaPeca('e', 2, new Torre(tabuleiro, Cor.BRANCA));
		colocarNovaPeca('e', 1, new Torre(tabuleiro, Cor.BRANCA));
		colocarNovaPeca('d', 1, new Rei(tabuleiro, Cor.BRANCA, this));

		colocarNovaPeca('c', 7, new Torre(tabuleiro, Cor.PRETA));
		colocarNovaPeca('c', 8, new Torre(tabuleiro, Cor.PRETA));
		colocarNovaPeca('d', 7, new Torre(tabuleiro, Cor.PRETA));
		colocarNovaPeca('e', 7, new Torre(tabuleiro, Cor.PRETA));
		colocarNovaPeca('e', 8, new Torre(tabuleiro, Cor.PRETA));
		colocarNovaPeca('d', 8, new Rei(tabuleiro, Cor.PRETA, this));
	}

}
