package ua.com.alevel.chessgame;

import ua.com.alevel.chessboard.ChessBoardException;

public class ChessException extends ChessBoardException {

    private static final long serialVersionUID = 1L;

    public ChessException(String msg) {
        super(msg);
    }
}
