package ua.com.alevel.chessfigure;

import ua.com.alevel.chessboard.ChessBoard;
import ua.com.alevel.chessboard.Location;
import ua.com.alevel.chessgame.ChessStature;
import ua.com.alevel.chessgame.SquareColorOnTheChessBoard;

public class Queen extends ChessStature {

    private static final int ZERO_HORIZONTAL_POSITION_BOARD = 0;
    private static final int ZERO_VERTICAL_POSITION_BOARD = 0;
    private static final int ONE_SQUARE = 1;
    private static final String QUEEN = "Q";

    public Queen(ChessBoard chessDesk, SquareColorOnTheChessBoard colorSquareOnAChessBoard) {
        super(chessDesk, colorSquareOnAChessBoard);
    }

    @Override
    public String toString() {
        return QUEEN;
    }

    @Override
    public boolean[][] possibleMovesOfAChessFigure() {
        boolean[][] isMate = new boolean[getChessDesk().getHorizontals()][getChessDesk().getVerticals()];

        Location positionOnTheBoard = new Location(ZERO_HORIZONTAL_POSITION_BOARD, ZERO_VERTICAL_POSITION_BOARD);

        positionOnTheBoard.setValues(position.getHorizontal() - ONE_SQUARE, position.getVertical());
        while (getChessDesk().checksLocationExists(positionOnTheBoard) && !getChessDesk().definesAChessFigure(positionOnTheBoard)) {
            isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
            positionOnTheBoard.setHorizontal(positionOnTheBoard.getHorizontal() - ONE_SQUARE);
        }
        if (getChessDesk().checksLocationExists(positionOnTheBoard) && isThereOpponentChessFigure(positionOnTheBoard)) {
            isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
        }

        positionOnTheBoard.setValues(position.getHorizontal(), position.getVertical() - ONE_SQUARE);
        while (getChessDesk().checksLocationExists(positionOnTheBoard) && !getChessDesk().definesAChessFigure(positionOnTheBoard)) {
            isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
            positionOnTheBoard.setVertical(positionOnTheBoard.getVertical() - ONE_SQUARE);
        }
        if (getChessDesk().checksLocationExists(positionOnTheBoard) && isThereOpponentChessFigure(positionOnTheBoard)) {
            isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
        }

        positionOnTheBoard.setValues(position.getHorizontal(), position.getVertical() + ONE_SQUARE);
        while (getChessDesk().checksLocationExists(positionOnTheBoard) && !getChessDesk().definesAChessFigure(positionOnTheBoard)) {
            isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
            positionOnTheBoard.setVertical(positionOnTheBoard.getVertical() + ONE_SQUARE);
        }
        if (getChessDesk().checksLocationExists(positionOnTheBoard) && isThereOpponentChessFigure(positionOnTheBoard)) {
            isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
        }

        positionOnTheBoard.setValues(position.getHorizontal() + ONE_SQUARE, position.getVertical());
        while (getChessDesk().checksLocationExists(positionOnTheBoard) && !getChessDesk().definesAChessFigure(positionOnTheBoard)) {
            isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
            positionOnTheBoard.setHorizontal(positionOnTheBoard.getHorizontal() + ONE_SQUARE);
        }
        if (getChessDesk().checksLocationExists(positionOnTheBoard) && isThereOpponentChessFigure(positionOnTheBoard)) {
            isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
        }

        positionOnTheBoard.setValues(position.getHorizontal() - ONE_SQUARE, position.getVertical() - ONE_SQUARE);
        while (getChessDesk().checksLocationExists(positionOnTheBoard) && !getChessDesk().definesAChessFigure(positionOnTheBoard)) {
            isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
            positionOnTheBoard.setValues(positionOnTheBoard.getHorizontal() - ONE_SQUARE, positionOnTheBoard.getVertical() - ONE_SQUARE);
        }
        if (getChessDesk().checksLocationExists(positionOnTheBoard) && isThereOpponentChessFigure(positionOnTheBoard)) {
            isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
        }

        positionOnTheBoard.setValues(position.getHorizontal() - ONE_SQUARE, position.getVertical() + ONE_SQUARE);
        while (getChessDesk().checksLocationExists(positionOnTheBoard) && !getChessDesk().definesAChessFigure(positionOnTheBoard)) {
            isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
            positionOnTheBoard.setValues(positionOnTheBoard.getHorizontal() - ONE_SQUARE, positionOnTheBoard.getVertical() + ONE_SQUARE);
        }
        if (getChessDesk().checksLocationExists(positionOnTheBoard) && isThereOpponentChessFigure(positionOnTheBoard)) {
            isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
        }

        positionOnTheBoard.setValues(position.getHorizontal() + ONE_SQUARE, position.getVertical() + ONE_SQUARE);
        while (getChessDesk().checksLocationExists(positionOnTheBoard) && !getChessDesk().definesAChessFigure(positionOnTheBoard)) {
            isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
            positionOnTheBoard.setValues(positionOnTheBoard.getHorizontal() + ONE_SQUARE, positionOnTheBoard.getVertical() + ONE_SQUARE);
        }
        if (getChessDesk().checksLocationExists(positionOnTheBoard) && isThereOpponentChessFigure(positionOnTheBoard)) {
            isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
        }

        positionOnTheBoard.setValues(position.getHorizontal() + ONE_SQUARE, position.getVertical() - ONE_SQUARE);
        while (getChessDesk().checksLocationExists(positionOnTheBoard) && !getChessDesk().definesAChessFigure(positionOnTheBoard)) {
            isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
            positionOnTheBoard.setValues(positionOnTheBoard.getHorizontal() + ONE_SQUARE, positionOnTheBoard.getVertical() - ONE_SQUARE);
        }
        if (getChessDesk().checksLocationExists(positionOnTheBoard) && isThereOpponentChessFigure(positionOnTheBoard)) {
            isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
        }
        return isMate;
    }
}
