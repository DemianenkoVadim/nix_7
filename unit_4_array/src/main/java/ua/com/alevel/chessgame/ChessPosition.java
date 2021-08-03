package ua.com.alevel.chessgame;

import ua.com.alevel.chessboard.Location;

public class ChessPosition {
    private final char vertical;
    private final int horizontal;

    private final static char NAME_FIRST_VERTICAL = 'a';
    private final static char NAME_LAST_VERTICAL = 'h';
    private final static int NAME_FIRST_HORIZONTAL = 1;
    private final static int NAME_LAST_HORIZONTAL = 8;
    private final static String ERROR_POSITION_0N_THE_BOARD = "Error instantiating position on board. Valid values are a1 through h8";

    public ChessPosition(char vertical, int horizontal) {
        if (vertical < NAME_FIRST_VERTICAL || vertical > NAME_LAST_VERTICAL || horizontal < NAME_FIRST_HORIZONTAL || horizontal > NAME_LAST_HORIZONTAL) {
            throw new ChessException(ERROR_POSITION_0N_THE_BOARD);
        }
        this.vertical = vertical;
        this.horizontal = horizontal;
    }

    public char getVertical() {
        return vertical;
    }

    public int getHorizontal() {
        return horizontal;
    }

    protected Location toPosition() {
        return new Location(NAME_LAST_HORIZONTAL - horizontal, vertical - NAME_FIRST_VERTICAL);
    }

    protected static ChessPosition fromPosition(Location position) {
        return new ChessPosition((char) (NAME_FIRST_VERTICAL + position.getVertical()), NAME_LAST_HORIZONTAL - position.getHorizontal());
    }

    @Override
    public String toString() {
        return "" + vertical + horizontal;
    }
}
