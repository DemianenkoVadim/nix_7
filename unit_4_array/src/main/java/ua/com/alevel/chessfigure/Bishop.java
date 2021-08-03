package ua.com.alevel.chessfigure;

import ua.com.alevel.chessboard.ChessBoard;
import ua.com.alevel.chessboard.Location;
import ua.com.alevel.chessgame.ChessStature;
import ua.com.alevel.chessgame.SquareColorOnTheChessBoard;

public class Bishop extends ChessStature {

    private static final int ZERO_HORIZONTAL_POSITION_BOARD = 0;
    private static final int ZERO_VERTICAL_POSITION_BOARD = 0;
    private static final int ONE_ITERATION_POSITION_ON_BOARD = 1;
    private static final String BISHOP = "B";

    public Bishop(ChessBoard chessDesk, SquareColorOnTheChessBoard colorSquareOnAChessBoard) {
        super(chessDesk, colorSquareOnAChessBoard);
    }

    @Override
    public String toString() {
        return BISHOP;
    }

    @Override
    public boolean[][] possibleMovesOfAChessFigure() {

        boolean[][] isMate = new boolean[getChessDesk().getHorizontals()][getChessDesk().getVerticals()];

        Location positionOnTheBoard = new Location(ZERO_HORIZONTAL_POSITION_BOARD, ZERO_VERTICAL_POSITION_BOARD);

        positionOnTheBoard.setValues(position.getHorizontal() - ONE_ITERATION_POSITION_ON_BOARD, position.getVertical() - ONE_ITERATION_POSITION_ON_BOARD);
        while (getChessDesk().checksLocationExists(positionOnTheBoard) && !getChessDesk().definesAChessFigure(positionOnTheBoard)) {
            isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
            positionOnTheBoard.setValues(positionOnTheBoard.getHorizontal() - ONE_ITERATION_POSITION_ON_BOARD, positionOnTheBoard.getVertical() - ONE_ITERATION_POSITION_ON_BOARD);
        }
        if (getChessDesk().checksLocationExists(positionOnTheBoard) && isThereOpponentChessFigure(positionOnTheBoard)) {
            isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
        }

        positionOnTheBoard.setValues(position.getHorizontal() - ONE_ITERATION_POSITION_ON_BOARD, position.getVertical() + ONE_ITERATION_POSITION_ON_BOARD);
        while (getChessDesk().checksLocationExists(positionOnTheBoard) && !getChessDesk().definesAChessFigure(positionOnTheBoard)) {
            isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
            positionOnTheBoard.setValues(positionOnTheBoard.getHorizontal() - ONE_ITERATION_POSITION_ON_BOARD, positionOnTheBoard.getVertical() + ONE_ITERATION_POSITION_ON_BOARD);
        }
        if (getChessDesk().checksLocationExists(positionOnTheBoard) && isThereOpponentChessFigure(positionOnTheBoard)) {
            isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
        }

        positionOnTheBoard.setValues(position.getHorizontal() + ONE_ITERATION_POSITION_ON_BOARD, position.getVertical() + ONE_ITERATION_POSITION_ON_BOARD);
        while (getChessDesk().checksLocationExists(positionOnTheBoard) && !getChessDesk().definesAChessFigure(positionOnTheBoard)) {
            isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
            positionOnTheBoard.setValues(positionOnTheBoard.getHorizontal() + ONE_ITERATION_POSITION_ON_BOARD, positionOnTheBoard.getVertical() + ONE_ITERATION_POSITION_ON_BOARD);
        }
        if (getChessDesk().checksLocationExists(positionOnTheBoard) && isThereOpponentChessFigure(positionOnTheBoard)) {
            isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
        }

        positionOnTheBoard.setValues(position.getHorizontal() + ONE_ITERATION_POSITION_ON_BOARD, position.getVertical() - ONE_ITERATION_POSITION_ON_BOARD);
        while (getChessDesk().checksLocationExists(positionOnTheBoard) && !getChessDesk().definesAChessFigure(positionOnTheBoard)) {
            isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
            positionOnTheBoard.setValues(positionOnTheBoard.getHorizontal() + ONE_ITERATION_POSITION_ON_BOARD, positionOnTheBoard.getVertical() - ONE_ITERATION_POSITION_ON_BOARD);
        }
        if (getChessDesk().checksLocationExists(positionOnTheBoard) && isThereOpponentChessFigure(positionOnTheBoard)) {
            isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
        }
        return isMate;
    }
}
