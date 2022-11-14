package application;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessExcecao;
import chess.ChessMatch;
import chess.ChessPosicao;
import chess.PecaChess;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		ChessMatch chessMatch = new ChessMatch();
		List<PecaChess> capturadas = new ArrayList<>();
		
		while(!chessMatch.getCheckMate()) {
			try {
				UI.clearScreen();
				UI.printMatch(chessMatch, capturadas);
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
				if (capturadaPeca != null) {
					capturadas.add(capturadaPeca);
				}
				
				if (chessMatch.getPromovida() != null) {
					System.out.print("Informe a peca a ser promovida: (B/C/T/Q): ");
					String tipo = sc.nextLine().toUpperCase();
					while (!tipo.equals("B") && !tipo.equals("C") && !tipo.equals("T") && !tipo.equals("Q")) {
						System.out.print("Valor invalido! Informe a peca a ser promovida: (B/C/T/Q): ");
						tipo = sc.nextLine().toUpperCase();
					}
					chessMatch.replacePromovidaPeca(tipo);
				}
				
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
		
		UI.clearScreen();
		UI.printMatch(chessMatch, capturadas);

	}

}
