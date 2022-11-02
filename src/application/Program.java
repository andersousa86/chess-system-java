package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import boardgame.Posicao;
import chess.ChessExcecao;
import chess.ChessMatch;
import chess.ChessPosicao;
import chess.PecaChess;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		ChessMatch chessMatch = new ChessMatch();
		
		while(true) {
			try {
				UI.clearScreen();
				UI.printBoard(chessMatch.getPecas());
				System.out.println();
				System.out.print("Origem: ");
				ChessPosicao origem = UI.lerChessPosicao(sc);
				
				boolean[][] possibleMoves = chessMatch.possibleMoves(origem);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPecas(), possibleMoves);
				System.out.println();
				System.out.print("Destino: ");
				ChessPosicao destino = UI.lerChessPosicao(sc);
				
				PecaChess capturadaPeca = chessMatch.performChessMove(origem, destino);
			}
			catch(ChessExcecao e){
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch(InputMismatchException e){
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		

	}

}
