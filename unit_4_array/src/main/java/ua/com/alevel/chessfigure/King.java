package ua.com.alevel.chessfigure;

import ua.com.alevel.chessboard.ChessBoard;
import ua.com.alevel.chessboard.Location;
import ua.com.alevel.chessgame.ChessGame;
import ua.com.alevel.chessgame.ChessStature;
import ua.com.alevel.chessgame.SquareColorOnTheChessBoard;

public class King extends ChessStature {

    private static final int ZERO_HORIZONTAL_POSITION_BOARD = 0;
    private static final int ZERO_VERTICAL_POSITION_BOARD = 0;
    private static final int MOVE_KING = 0;
    private static final int ONE_ITERATION_POSITION_ON_BOARD = 1;
    private static final int ONE_SQUARE = 1;
    private static final int TWO_SQUARES = 2;
    private static final int THREE_SQUARES = 3;
    private static final int FOUR_SQUARES = 4;
    private static final int TWO_SQUARE_CASTLING = 2;

    private static final String KING = "K";

    private final ChessGame game;

    public King(ChessBoard chessDesk, SquareColorOnTheChessBoard colorSquareOnAChessBoard, ChessGame game) {
        super(chessDesk, colorSquareOnAChessBoard);
        this.game = game;
    }

    @Override
    public String toString() {
        return KING;
    }

    private boolean showsThePossibilityOfStroke(Location figureLocation) {
        ChessStature chessPlace = (ChessStature) getChessDesk().returnsPositionFigureOnBoardSquare(figureLocation);
        return chessPlace == null || chessPlace.getColor() != getColor();
    }

    private boolean testRookCastling(Location position) {
        ChessStature chessPlace = (ChessStature) getChessDesk().returnsPositionFigureOnBoardSquare(position);
        return chessPlace instanceof Rook && chessPlace.getColor() == getColor() && chessPlace.getMoveCount() == MOVE_KING;
    }

    @Override
    public boolean[][] possibleMovesOfAChessFigure() {
        boolean[][] isMate = new boolean[getChessDesk().getHorizontals()][getChessDesk().getVerticals()];

        Location positionOnTheBoard = new Location(ZERO_HORIZONTAL_POSITION_BOARD, ZERO_VERTICAL_POSITION_BOARD);

        positionOnTheBoard.setValues(position.getHorizontal() - ONE_ITERATION_POSITION_ON_BOARD, position.getVertical());
        if (getChessDesk().checksLocationExists(positionOnTheBoard) && showsThePossibilityOfStroke(positionOnTheBoard)) {
            isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
        }

        positionOnTheBoard.setValues(position.getHorizontal() + ONE_ITERATION_POSITION_ON_BOARD, position.getVertical());
        if (getChessDesk().checksLocationExists(positionOnTheBoard) && showsThePossibilityOfStroke(positionOnTheBoard)) {
            isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
        }

        positionOnTheBoard.setValues(position.getHorizontal(), position.getVertical() - ONE_ITERATION_POSITION_ON_BOARD);
        if (getChessDesk().checksLocationExists(positionOnTheBoard) && showsThePossibilityOfStroke(positionOnTheBoard)) {
            isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
        }

        positionOnTheBoard.setValues(position.getHorizontal(), position.getVertical() + ONE_ITERATION_POSITION_ON_BOARD);
        if (getChessDesk().checksLocationExists(positionOnTheBoard) && showsThePossibilityOfStroke(positionOnTheBoard)) {
            isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
        }

        positionOnTheBoard.setValues(position.getHorizontal() - ONE_ITERATION_POSITION_ON_BOARD, position.getVertical() - ONE_ITERATION_POSITION_ON_BOARD);
        if (getChessDesk().checksLocationExists(positionOnTheBoard) && showsThePossibilityOfStroke(positionOnTheBoard)) {
            isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
        }

        positionOnTheBoard.setValues(position.getHorizontal() - ONE_ITERATION_POSITION_ON_BOARD, position.getVertical() + ONE_ITERATION_POSITION_ON_BOARD);
        if (getChessDesk().checksLocationExists(positionOnTheBoard) && showsThePossibilityOfStroke(positionOnTheBoard)) {
            isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
        }

        positionOnTheBoard.setValues(position.getHorizontal() + ONE_ITERATION_POSITION_ON_BOARD, position.getVertical() - ONE_ITERATION_POSITION_ON_BOARD);
        if (getChessDesk().checksLocationExists(positionOnTheBoard) && showsThePossibilityOfStroke(positionOnTheBoard)) {
            isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
        }

        positionOnTheBoard.setValues(position.getHorizontal() + ONE_ITERATION_POSITION_ON_BOARD, position.getVertical() + ONE_ITERATION_POSITION_ON_BOARD);
        if (getChessDesk().checksLocationExists(positionOnTheBoard) && showsThePossibilityOfStroke(positionOnTheBoard)) {
            isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
        }

        if (getMoveCount() == MOVE_KING && !game.getCheck()) {
            Location firstPositionForCastling = new Location(position.getHorizontal(), position.getVertical() + THREE_SQUARES);
            if (testRookCastling(firstPositionForCastling)) {
                Location firstVariantPositionKingSide = new Location(position.getHorizontal(), position.getVertical() + ONE_SQUARE);
                Location secondVariantPositionKingSide = new Location(position.getHorizontal(), position.getVertical() + TWO_SQUARES);
                if (getChessDesk().returnsPositionFigureOnBoardSquare(firstVariantPositionKingSide) == null && getChessDesk().returnsPositionFigureOnBoardSquare(secondVariantPositionKingSide) == null) {
                    isMate[position.getHorizontal()][position.getVertical() + TWO_SQUARES] = true;
                }
            }
            Location secondPositionForCastling = new Location(position.getHorizontal(), position.getVertical() - FOUR_SQUARES);
            if (testRookCastling(secondPositionForCastling)) {
                Location firstVariantPositionQueenSide = new Location(position.getHorizontal(), position.getVertical() - ONE_SQUARE);
                Location secondVariantPositionQueenSide = new Location(position.getHorizontal(), position.getVertical() - TWO_SQUARES);
                Location thirdVariantPositionQueenSide = new Location(position.getHorizontal(), position.getVertical() - THREE_SQUARES);
                if (getChessDesk().returnsPositionFigureOnBoardSquare(firstVariantPositionQueenSide) == null && getChessDesk().returnsPositionFigureOnBoardSquare(secondVariantPositionQueenSide) == null && getChessDesk().returnsPositionFigureOnBoardSquare(thirdVariantPositionQueenSide) == null) {
                    isMate[position.getHorizontal()][position.getVertical() - TWO_SQUARE_CASTLING] = true;
                }
            }
        }
        return isMate;
    }
}
