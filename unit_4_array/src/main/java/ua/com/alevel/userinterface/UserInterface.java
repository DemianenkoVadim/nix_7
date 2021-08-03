package ua.com.alevel.userinterface;

import ua.com.alevel.chessgame.ChessGame;
import ua.com.alevel.chessgame.ChessPosition;
import ua.com.alevel.chessgame.ChessStature;
import ua.com.alevel.chessgame.SquareColorOnTheChessBoard;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    public final static int INDEX = 0;
    public final static int BEGIN_INDEX = 1;
    private final static int NUMBER_OF_FIELDS = 8;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";

    public final static String CLEAN_CONSOLE = "\033[H\033[2J";

    public final static String ERROR_READING_CHESS_POSITION = "Wrong input. It must be values from a1 to h8";
    public final static String NAME_OF_THE_VERTICALS = "   a   b   c   d   e   f   g   h";
    public final static String SPACE = " ";
    public final static String FIELD_ON_CHESSBOARD = " - ";
    public final static String FIELD = "-";

    public static void cleansScreen() {
        System.out.print(CLEAN_CONSOLE);
        System.out.flush();
    }

    public static ChessPosition readChessPosition(Scanner sc) {
        try {
            String input = sc.nextLine();
            char horizontal = input.charAt(INDEX);
            int vertical = Integer.parseInt(input.substring(BEGIN_INDEX));
            return new ChessPosition(horizontal, vertical);
        } catch (RuntimeException e) {
            throw new InputMismatchException(ERROR_READING_CHESS_POSITION);
        }
    }

    public static void printGame(ChessGame chessMatch, List<ChessStature> captured) {
        printBoard(chessMatch.getChessFiguresOnTheChessBoard());
        System.out.println();
        printCapturedPieces(captured);
        System.out.println("\nTurn: " + chessMatch.getQueueOrder());
        if (chessMatch.getCheckMate()) {
            System.out.println("Waiting player: " + chessMatch.getCurrentPlayer());
            if (chessMatch.getCheck()) {
                System.out.println("CHECK!");
            }
        } else {
            System.out.println("CHECKMATE!");
            System.out.println("Winner: " + chessMatch.getCurrentPlayer());
        }
    }

    public static void printBoard(ChessStature[][] figure) {
        for (int initialPoint = INDEX; initialPoint < figure.length; initialPoint++) {
            System.out.print((NUMBER_OF_FIELDS - initialPoint) + SPACE);
            for (int startPoint = INDEX; startPoint < figure.length; startPoint++) {
                printPiece(figure[initialPoint][startPoint], false);
            }
            System.out.println();
        }
        System.out.println(NAME_OF_THE_VERTICALS);
    }

    public static void printBoard(ChessStature[][] figure, boolean[][] possibleMoves) {
        for (int initialPoint = INDEX; initialPoint < figure.length; initialPoint++) {
            System.out.print((NUMBER_OF_FIELDS - initialPoint) + SPACE);
            for (int startPoint = INDEX; startPoint < figure.length; startPoint++) {
                printPiece(figure[initialPoint][startPoint], possibleMoves[initialPoint][startPoint]);
            }
            System.out.println();
        }
        System.out.println(NAME_OF_THE_VERTICALS);
    }

    private static void printPiece(ChessStature piece, boolean background) {
        if (background) {
            System.out.print(ANSI_BLUE_BACKGROUND);

            if (piece == null) {
                System.out.print(ANSI_RESET + SPACE + ANSI_BLUE_BACKGROUND + FIELD + ANSI_RESET + SPACE);
            } else {
                if (piece.getColor() == SquareColorOnTheChessBoard.WHITE) {
                    System.out.print(ANSI_RESET + SPACE + ANSI_BLUE_BACKGROUND + ANSI_CYAN + piece + ANSI_RESET + SPACE);
                } else {
                    System.out.print(ANSI_RESET + SPACE + ANSI_BLUE_BACKGROUND + ANSI_YELLOW + piece + ANSI_RESET + SPACE);
                }
            }
        } else {

            if (piece == null) {
                System.out.print(FIELD_ON_CHESSBOARD);
            } else {
                if (piece.getColor() == SquareColorOnTheChessBoard.WHITE) {
                    System.out.print(SPACE + ANSI_CYAN + piece + ANSI_RESET + SPACE);
                } else {
                    System.out.print(SPACE + ANSI_YELLOW + piece + ANSI_RESET + SPACE);
                }
            }
        }
        System.out.print(SPACE);
    }

    private static void printCapturedPieces(List<ChessStature> captured) {
        System.out.println("Captured ChessFigures:");
        System.out.print("White: ");
        System.out.print(ANSI_CYAN);
        System.out.println(Arrays.toString(captured.stream().filter(x -> x.getColor() == SquareColorOnTheChessBoard.WHITE).toArray()));
        System.out.print(ANSI_RESET);
        System.out.print("Black: ");
        System.out.print(ANSI_YELLOW);
        System.out.println(Arrays.toString(captured.stream().filter(x -> x.getColor() == SquareColorOnTheChessBoard.BLACK).toArray()));
        System.out.print(ANSI_RESET);
    }
}
