package ua.com.alevel.chessfigure;

import ua.com.alevel.chessboard.ChessBoard;
import ua.com.alevel.chessboard.Location;
import ua.com.alevel.chessgame.ChessGame;
import ua.com.alevel.chessgame.ChessStature;
import ua.com.alevel.chessgame.SquareColorOnTheChessBoard;

public class Pawn extends ChessStature {

    private static final int ZERO_HORIZONTAL_POSITION_BOARD = 0;
    private static final int ZERO_VERTICAL_POSITION_BOARD = 0;
    private static final int AMOUNT_MOVE_FIGURE = 0;
    private static final int ONE_SQUARE = 1;
    private static final int TWO_SQUARES = 2;
    private static final int SPECIAL_MOVE_WHITES = 3;
    private static final int SPECIAL_MOVE_BLACK = 4;

    private static final String PAWN = "P";

    private final ChessGame game;

    public Pawn(ChessBoard chessDesk, SquareColorOnTheChessBoard colorSquareOnAChessBoard, ChessGame game) {
        super(chessDesk, colorSquareOnAChessBoard);
        this.game = game;
    }

    @Override
    public String toString() {
        return PAWN;
    }

    @Override
    public boolean[][] possibleMovesOfAChessFigure() {
        boolean[][] isMate = new boolean[getChessDesk().getHorizontals()][getChessDesk().getVerticals()];

        Location positionOnTheBoard = new Location(ZERO_HORIZONTAL_POSITION_BOARD, ZERO_VERTICAL_POSITION_BOARD);

        if (getColor() == SquareColorOnTheChessBoard.WHITE) {
            positionOnTheBoard.setValues(position.getHorizontal() - ONE_SQUARE, position.getVertical());
            if (getChessDesk().checksLocationExists(positionOnTheBoard) && !getChessDesk().definesAChessFigure(positionOnTheBoard)) {
                isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
            }
            positionOnTheBoard.setValues(position.getHorizontal() - TWO_SQUARES, position.getVertical());
            Location anotherPositionOnTheBoard = new Location(position.getHorizontal() - ONE_SQUARE, position.getVertical());
            if (getChessDesk().checksLocationExists(positionOnTheBoard) && !getChessDesk().definesAChessFigure(positionOnTheBoard) &&
                    getChessDesk().checksLocationExists(anotherPositionOnTheBoard) && !getChessDesk().definesAChessFigure(anotherPositionOnTheBoard)
                    && getMoveCount() == AMOUNT_MOVE_FIGURE) {
                isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
            }
            positionOnTheBoard.setValues(position.getHorizontal() - ONE_SQUARE, position.getVertical() - ONE_SQUARE);
            if (getChessDesk().checksLocationExists(positionOnTheBoard) && isThereOpponentChessFigure(positionOnTheBoard)) {
                isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
            }
            positionOnTheBoard.setValues(position.getHorizontal() - ONE_SQUARE, position.getVertical() + ONE_SQUARE);
            if (getChessDesk().checksLocationExists(positionOnTheBoard) && isThereOpponentChessFigure(positionOnTheBoard)) {
                isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
            }

            if (position.getHorizontal() == SPECIAL_MOVE_WHITES) {
                Location leftSide = new Location(position.getHorizontal(), position.getVertical() - ONE_SQUARE);
                if (getChessDesk().checksLocationExists(leftSide)
                        && isThereOpponentChessFigure(leftSide)
                        && getChessDesk().returnsPositionFigureOnBoardSquare(leftSide) == game.getWalkThroughVulnerability()
                ) {
                    isMate[leftSide.getHorizontal() - ONE_SQUARE][leftSide.getVertical()] = true;
                }
                Location rightSide = new Location(position.getHorizontal(), position.getVertical() + ONE_SQUARE);
                if (getChessDesk().checksLocationExists(rightSide)
                        && isThereOpponentChessFigure(rightSide)
                        && getChessDesk().returnsPositionFigureOnBoardSquare(rightSide) == game.getWalkThroughVulnerability()
                ) {
                    isMate[rightSide.getHorizontal() - ONE_SQUARE][rightSide.getVertical()] = true;
                }
            }

        } else {
            positionOnTheBoard.setValues(position.getHorizontal() + ONE_SQUARE, position.getVertical());
            if (getChessDesk().checksLocationExists(positionOnTheBoard) && !getChessDesk().definesAChessFigure(positionOnTheBoard)) {
                isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
            }
            positionOnTheBoard.setValues(position.getHorizontal() + TWO_SQUARES, position.getVertical());
            Location anotherPositionOnTheBoard = new Location(position.getHorizontal() + ONE_SQUARE, position.getVertical());
            if (getChessDesk().checksLocationExists(positionOnTheBoard) && !getChessDesk().definesAChessFigure(positionOnTheBoard) &&
                    getChessDesk().checksLocationExists(anotherPositionOnTheBoard) && !getChessDesk().definesAChessFigure(anotherPositionOnTheBoard)
                    && getMoveCount() == AMOUNT_MOVE_FIGURE) {
                isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
            }
            positionOnTheBoard.setValues(position.getHorizontal() + ONE_SQUARE, position.getVertical() - ONE_SQUARE);
            if (getChessDesk().checksLocationExists(positionOnTheBoard) && isThereOpponentChessFigure(positionOnTheBoard)) {
                isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
            }
            positionOnTheBoard.setValues(position.getHorizontal() + ONE_SQUARE, position.getVertical() + ONE_SQUARE);
            if (getChessDesk().checksLocationExists(positionOnTheBoard) && isThereOpponentChessFigure(positionOnTheBoard)) {
                isMate[positionOnTheBoard.getHorizontal()][positionOnTheBoard.getVertical()] = true;
            }

            if (position.getHorizontal() == SPECIAL_MOVE_BLACK) {
                Location leftSide = new Location(position.getHorizontal(), position.getVertical() - ONE_SQUARE);
                if (getChessDesk().checksLocationExists(leftSide)
                        && isThereOpponentChessFigure(leftSide)
                        && getChessDesk().returnsPositionFigureOnBoardSquare(leftSide) == game.getWalkThroughVulnerability()
                ) {
                    isMate[leftSide.getHorizontal() + ONE_SQUARE][leftSide.getVertical()] = true;
                }
                Location right = new Location(position.getHorizontal(), position.getVertical() + ONE_SQUARE);
                if (getChessDesk().checksLocationExists(right)
                        && isThereOpponentChessFigure(right)
                        && getChessDesk().returnsPositionFigureOnBoardSquare(right) == game.getWalkThroughVulnerability()
                ) {
                    isMate[right.getHorizontal() + ONE_SQUARE][right.getVertical()] = true;
                }
            }
        }
        return isMate;
    }
}
