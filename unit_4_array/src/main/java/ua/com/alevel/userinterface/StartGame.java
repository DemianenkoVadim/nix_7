package ua.com.alevel.userinterface;

import ua.com.alevel.chessgame.ChessException;
import ua.com.alevel.chessgame.ChessGame;
import ua.com.alevel.chessgame.ChessPosition;
import ua.com.alevel.chessgame.ChessStature;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class StartGame {

    private final static String INPUT_STARTING_FIELD_CHESS_FIGURE = "Input starting field of Chess (e.x. e2), and push Enter: ";
    private final static String INPUT_TARGET_FIELD_CHESS_FIGURE = "Input target field of Chess (e.x. e4), and push enter: ";
    private final static String INPUT_CHESS_FIGURE_TO_RAISE = "Enter chess figure for raise (B/N/R/Q): ";

    public void startsChessGame() {
        Scanner userInput = new Scanner(System.in);
        ChessGame chessGame = new ChessGame();
        List<ChessStature> chessStatures = new ArrayList<>();

        while (chessGame.getCheckMate()) {
            try {
                UserInterface.cleansScreen();
                UserInterface.printGame(chessGame, chessStatures);
                System.out.println();
                System.out.print(INPUT_STARTING_FIELD_CHESS_FIGURE);
                ChessPosition source = UserInterface.readChessPosition(userInput);

                boolean[][] possibleMoves = chessGame.possibleMovesOfChessFigure(source);
                UserInterface.cleansScreen();
                UserInterface.printBoard(chessGame.getChessFiguresOnTheChessBoard(), possibleMoves);

                System.out.println();
                System.out.print(INPUT_TARGET_FIELD_CHESS_FIGURE);
                ChessPosition target = UserInterface.readChessPosition(userInput);

                ChessStature capturedPiece = chessGame.performChessMove(source, target);
                if (capturedPiece != null) {
                    chessStatures.add(capturedPiece);
                }
                if (chessGame.getAdvancedChessFigure() != null) {
                    System.out.print(INPUT_CHESS_FIGURE_TO_RAISE);
                    String type = userInput.nextLine();
                    chessGame.replacesPromotedChessFigure(type);
                }
            } catch (ChessException | InputMismatchException e) {
                System.out.println(e.getMessage());
                userInput.nextLine();
            }
        }
        UserInterface.cleansScreen();
        UserInterface.printGame(chessGame, chessStatures);
    }
}
