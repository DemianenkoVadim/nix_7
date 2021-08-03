package ua.com.alevel.chessboard;

public class ChessBoard {

    private final int horizontals;
    private final int verticals;
    private final Stature[][] statures;

    private static final int ZERO_POSITION_BOARD_VERTICALLY = 0;
    private static final int ZERO_POSITION_BOARD_HORIZONTALLY = 0;
    private final static int ONE_HORIZONTAL = 1;
    private final static int ONE_VERTICAL = 1;

    private final static String ERROR_CHESSBOARD = "You`re got an error! There must be at least 1 horizontal and 1 vertical";
    private final static String OUT_OF_BOARD_POSITION = "Off-board position";
    private final static String POSITION_IS_TAKEN = "There is already a figure in position";


    public ChessBoard(int horizontals, int verticals) {
        if (horizontals < ONE_HORIZONTAL || verticals < ONE_VERTICAL) {
            throw new ChessBoardException(ERROR_CHESSBOARD);
        }
        this.horizontals = horizontals;
        this.verticals = verticals;
        statures = new Stature[horizontals][verticals];
    }

    public int getHorizontals() {
        return horizontals;
    }

    public int getVerticals() {
        return verticals;
    }

    public Stature returnsPositionFigureOnBoardSquare(int horizontal, int vertical) {
        if (!checksLocationExists(horizontal, vertical)) {
            throw new ChessBoardException(OUT_OF_BOARD_POSITION);
        }
        return statures[horizontal][vertical];
    }

    public Stature returnsPositionFigureOnBoardSquare(Location chessFigurePosition) {
        if (!checksLocationExists(chessFigurePosition)) {
            throw new ChessBoardException(OUT_OF_BOARD_POSITION);
        }
        return statures[chessFigurePosition.getHorizontal()][chessFigurePosition.getVertical()];
    }

    public void showsPlaceOfChessFigure(Stature piece, Location position) {
        if (definesAChessFigure(position)) {
            throw new ChessBoardException(POSITION_IS_TAKEN + position);
        }
        statures[position.getHorizontal()][position.getVertical()] = piece;
        piece.position = position;
    }

    public Stature removesChessFigure(Location chessFigurePosition) {
        if (!checksLocationExists(chessFigurePosition)) {
            throw new ChessBoardException(OUT_OF_BOARD_POSITION);
        }
        if (returnsPositionFigureOnBoardSquare(chessFigurePosition) == null) {
            return null;
        }
        Stature utility = returnsPositionFigureOnBoardSquare(chessFigurePosition);

        utility.position = null;
        statures[chessFigurePosition.getHorizontal()][chessFigurePosition.getVertical()] = null;

        return utility;
    }

    private boolean checksLocationExists(int horizontal, int vertical) {
        return horizontal >= ZERO_POSITION_BOARD_HORIZONTALLY && horizontal < horizontals && vertical >= ZERO_POSITION_BOARD_VERTICALLY && vertical < verticals;
    }

    public boolean checksLocationExists(Location position) {
        return checksLocationExists(position.getHorizontal(), position.getVertical());
    }

    public boolean definesAChessFigure(Location position) {
        if (!checksLocationExists(position)) {
            throw new ChessBoardException(OUT_OF_BOARD_POSITION);
        }
        return returnsPositionFigureOnBoardSquare(position) != null;
    }
}
