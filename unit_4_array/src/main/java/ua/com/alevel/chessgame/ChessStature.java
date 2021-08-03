package ua.com.alevel.chessgame;

import ua.com.alevel.chessboard.ChessBoard;
import ua.com.alevel.chessboard.Location;
import ua.com.alevel.chessboard.Stature;

public abstract class ChessStature extends Stature {

    private SquareColorOnTheChessBoard color;
    private int moveCount;

    public ChessStature(ChessBoard chessDesk, SquareColorOnTheChessBoard color) {
        super(chessDesk);
        this.color = color;
    }

    public SquareColorOnTheChessBoard getColor() {
        return color;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void increaseMoveCount() {
        moveCount++;
    }

    public void decreaseMoveCount() {
        moveCount--;
    }

    public ChessPosition getChessPosition() {
        return ChessPosition.fromPosition(position);
    }

    protected boolean isThereOpponentChessFigure(Location position) {
        ChessStature positionChessFigure = (ChessStature) getChessDesk().returnsPositionFigureOnBoardSquare(position);
        return positionChessFigure != null && positionChessFigure.getColor() != color;
    }
}
