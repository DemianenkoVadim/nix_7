package ua.com.alevel.chessboard;

public abstract class Stature {

    protected Location position;
    private final ChessBoard chessDesk;

    private final static int START_POINT = 0;

    public Stature(ChessBoard chessDesk) {
        this.chessDesk = chessDesk;
        position = null;
    }

    protected ChessBoard getChessDesk() {
        return chessDesk;
    }

    public abstract boolean[][] possibleMovesOfAChessFigure();

    public boolean showsThePossibleMovesOfAChessFigure(Location figurePosition) {
        return possibleMovesOfAChessFigure()[figurePosition.getHorizontal()][figurePosition.getVertical()];
    }

    public boolean checksIfAPossibleMoveExists() {
        boolean[][] mate = possibleMovesOfAChessFigure();
        for (boolean[] booleans : mate) {
            for (int secondInitialPoint = START_POINT; secondInitialPoint < mate.length; secondInitialPoint++) {
                if (booleans[secondInitialPoint]) {
                    return true;
                }
            }
        }
        return false;
    }
}
