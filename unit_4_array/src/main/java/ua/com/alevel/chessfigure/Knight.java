package ua.com.alevel.chessfigure;

import ua.com.alevel.chessboard.ChessBoard;
import ua.com.alevel.chessboard.Location;
import ua.com.alevel.chessgame.ChessStature;
import ua.com.alevel.chessgame.SquareColorOnTheChessBoard;

public class Knight extends ChessStature {

    private static final int ZERO_HORIZONTAL_POSITION_BOARD = 0;
    private static final int ZERO_VERTICAL_POSITION_BOARD = 0;
    private static final int ONE_SQUARE = 1;
    private static final int TWO_SQUARES = 2;
    private static final String KNIGHT = "N";

    public Knight(ChessBoard chessDesk, SquareColorOnTheChessBoard colorSquareOnAChessBoard) {
        super(chessDesk, colorSquareOnAChessBoard);
    }

    @Override
    public String toString() {
        return KNIGHT;
    }

    private boolean showsThePossibilityOfStroke(Location position) {
        ChessStature chessPlace = (ChessStature) getChessDesk().returnsPositionFigureOnBoardSquare(position);
        return chessPlace == null || chessPlace.getColor() != getColor();
    }

    @Override
    public boolean[][] possibleMovesOfAChessFigure() {
        boolean[][] isMate = new boolean[getChessDesk().getHorizontals()][getChessDesk().getVerticals()];

        Location positionOnTheBoard = new Location(ZERO_HORIZONTAL_POSITION_BOARD, ZERO_VERTICAL_POSITION_BOARD);

        positionOnTheBoard.setValues(position.getHorizontal() - ONE_SQUARE, position.getVertical() - TWO_SQUARES);
        if (getChessDesk().checksLocationExists(positionOnTheBoard) && showsThePossibilityOfStroke(positionOnTheBoard)) {
            isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
        }

        positionOnTheBoard.setValues(position.getHorizontal() - ONE_SQUARE, position.getVertical() + TWO_SQUARES);
        if (getChessDesk().checksLocationExists(positionOnTheBoard) && showsThePossibilityOfStroke(positionOnTheBoard)) {
            isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
        }

        positionOnTheBoard.setValues(position.getHorizontal() + ONE_SQUARE, position.getVertical() - TWO_SQUARES);
        if (getChessDesk().checksLocationExists(positionOnTheBoard) && showsThePossibilityOfStroke(positionOnTheBoard)) {
            isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
        }

        positionOnTheBoard.setValues(position.getHorizontal() + ONE_SQUARE, position.getVertical() + TWO_SQUARES);
        if (getChessDesk().checksLocationExists(positionOnTheBoard) && showsThePossibilityOfStroke(positionOnTheBoard)) {
            isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
        }

        positionOnTheBoard.setValues(position.getHorizontal() - TWO_SQUARES, position.getVertical() - ONE_SQUARE);
        if (getChessDesk().checksLocationExists(positionOnTheBoard) && showsThePossibilityOfStroke(positionOnTheBoard)) {
            isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
        }

        positionOnTheBoard.setValues(position.getHorizontal() - TWO_SQUARES, position.getVertical() + ONE_SQUARE);
        if (getChessDesk().checksLocationExists(positionOnTheBoard) && showsThePossibilityOfStroke(positionOnTheBoard)) {
            isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
        }

        positionOnTheBoard.setValues(position.getHorizontal() + TWO_SQUARES, position.getVertical() - ONE_SQUARE);
        if (getChessDesk().checksLocationExists(positionOnTheBoard) && showsThePossibilityOfStroke(positionOnTheBoard)) {
            isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
        }

        positionOnTheBoard.setValues(position.getHorizontal() + TWO_SQUARES, position.getVertical() + ONE_SQUARE);
        if (getChessDesk().checksLocationExists(positionOnTheBoard) && showsThePossibilityOfStroke(positionOnTheBoard)) {
            isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
        }

        return isMate;
    }
}
