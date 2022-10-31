package application;

import java.util.Scanner;

import boardgame.Posicao;
import chess.ChessMatch;
import chess.ChessPosicao;
import chess.PecaChess;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		ChessMatch chessMatch = new ChessMatch();
		
		while(true) {
			UI.printBoard(chessMatch.getPecas());
			System.out.println();
			System.out.print("Origem: ");
			ChessPosicao origem = UI.lerChessPosicao(sc);
			
			System.out.println();
			System.out.print("Destino: ");
			ChessPosicao destino = UI.lerChessPosicao(sc);
			
			PecaChess capturadaPeca = chessMatch.performChessMove(origem, destino);
		}
		

	}

}
