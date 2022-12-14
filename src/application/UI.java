package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.ChessMatch;
import chess.ChessPosicao;
import chess.Cor;
import chess.PecaChess;

public class UI {

	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static ChessPosicao lerChessPosicao(Scanner sc) {
		try {
			String s = sc.nextLine();
			char coluna = s.charAt(0);
			int linha = Integer.parseInt(s.substring(1));
			return new ChessPosicao(coluna, linha);
		} catch (RuntimeException e) {
			throw new InputMismatchException("Erro ao ler posição do xadrez. É válido de a1 ao a8");
		}
	}

	public static void printMatch(ChessMatch chessMatch, List<PecaChess> capturadas) {
		printBoard(chessMatch.getPecas());
		System.out.println();
		printCapturadaPecas(capturadas);
		System.out.println();
		System.out.println("Turno: " + chessMatch.getTurn());
		if (!chessMatch.getCheckMate()) {
			System.out.println("Esperando jogador: " + chessMatch.getCurrentPlayer());
			if (chessMatch.getCheck()) {
				System.out.println("CHECK!");
			}
		}
		else {
			System.out.println("CHECKMATE!");
			System.out.println("Vencedor: " + chessMatch.getCurrentPlayer());
		}
	}

	public static void printBoard(PecaChess[][] pecas) {
		for (int i = 0; i < pecas.length; i++) {
			System.out.print(8 - i + " ");
			for (int j = 0; j < pecas.length; j++) {
				printPiece(pecas[i][j], false);
			}
			System.out.println();
		}
		System.out.println("  A B C D E F G H");
	}

	public static void printBoard(PecaChess[][] pecas, boolean[][] possibleMoves) {
		for (int i = 0; i < pecas.length; i++) {
			System.out.print(8 - i + " ");
			for (int j = 0; j < pecas.length; j++) {
				printPiece(pecas[i][j], possibleMoves[i][j]);
			}
			System.out.println();
		}
		System.out.println("  A B C D E F G H");
	}

	private static void printPiece(PecaChess peca, boolean background) {
		if (background) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		if (peca == null) {
			System.out.print("-" + ANSI_RESET);
		} else {
			if (peca.getCor() == Cor.BRANCA) {
				System.out.print(ANSI_WHITE + peca + ANSI_RESET);
			} else {
				System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
			}
		}
		System.out.print(" ");
	}

	private static void printCapturadaPecas(List<PecaChess> capturada) {
		List<PecaChess> branca = capturada.stream().filter(x -> x.getCor() == Cor.BRANCA).collect(Collectors.toList());
		List<PecaChess> preta = capturada.stream().filter(x -> x.getCor() == Cor.PRETA).collect(Collectors.toList());
		System.out.println("Pecas capturadas.");
		System.out.print("Brancas: ");
		System.out.println(ANSI_WHITE);
		System.out.println(Arrays.toString(branca.toArray()));
		System.out.print("Pretas: ");
		System.out.println(ANSI_YELLOW);
		System.out.println(Arrays.toString(preta.toArray()));
		System.out.println(ANSI_RESET);
	}

}
