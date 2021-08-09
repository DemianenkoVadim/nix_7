package ua.com.alevel.chessgame;

import ua.com.alevel.chessboard.ChessBoard;
import ua.com.alevel.chessboard.Location;
import ua.com.alevel.chessboard.Stature;
import ua.com.alevel.chessfigure.*;
import ua.com.alevel.chessboard.ChessBoardNameFields;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChessGame {

    private int queueOrder;
    private SquareColorOnTheChessBoard currentPlayer;
    public ChessBoard chessDesk;
    private boolean check;
    private boolean checkMate;
    private ChessStature walkThroughVulnerability;
    private ChessStature advancedChessFigure;

    private List<Stature> piecesOnTheBoard = new ArrayList<>();
    private List<Stature> capturedPieces = new ArrayList<>();

    private static final int INDEX_OF_FIRST_FIELD = 0;
    private static final int FIRST_START_INDEX = 0;
    private static final int ONE_SIMPLE_FIELD = 1;
    private static final int FIRST_PLAYER = 1;

    private static final int ONE_FIELD_SKIP = 1;
    private static final int TWO_FIELD_SKIP = 2;
    private static final int TREE_FIELD_SKIP = 3;
    private static final int FOUR_FIELD_SKIP = 4;

    private static final int LOCATION_ON_HORIZONTAL_NUMBER_FOUR = 3;
    private static final int LOCATION_ON_HORIZONTAL_NUMBER_FIVE = 4;

    private final static int NUMBER_OF_FIELDS_ON_THE_HORIZONTAL = 8;
    private final static int NUMBER_OF_FIELDS_ON_THE_VERTICAL = 8;

    private final static String ROOK = "R";
    private final static String KNIGHT = "N";
    private final static String BISHOP = "B";
    private final static String QUEEN = "Q";

    private final static String ERROR_MOVEMENT = "You cant put yourself in check";
    private final static String ERROR_MOVEMENT_SECOND = "No figure to promote";
    private final static String ERROR_MOVEMENT_THIRD = "Wrong type figure for promotion";
    private final static String ERROR_STARTING_POSITION = "There is no piece on start position";
    private final static String ERROR_CHOOSE_CHESS_FIGURE = "The chosen piece is not yours";
    private final static String ERROR_MOVEMENT_CHOSEN_CHESS_FIGURE = "No possible moves for the chosen Chess Figure";
    private final static String ERROR_MOVEMENT_CHOSEN_TARGET_POSITION = "The chosen CHESS FIGURE cant move to target position";

    public ChessGame() {
        chessDesk = new ChessBoard(NUMBER_OF_FIELDS_ON_THE_HORIZONTAL, NUMBER_OF_FIELDS_ON_THE_VERTICAL);
        queueOrder = FIRST_PLAYER;
        currentPlayer = SquareColorOnTheChessBoard.WHITE;
        initialPositionOfTheFigures();
    }

    public int getQueueOrder() {
        return queueOrder;
    }

    public SquareColorOnTheChessBoard getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean getCheck() {
        return check;
    }

    public boolean getCheckMate() {
        return !checkMate;
    }

    public ChessStature getWalkThroughVulnerability() {
        return walkThroughVulnerability;
    }

    public ChessStature getAdvancedChessFigure() {
        return advancedChessFigure;
    }

    public ChessStature[][] getChessFiguresOnTheChessBoard() {

        ChessStature[][] isMate = new ChessStature[chessDesk.getHorizontals()][chessDesk.getVerticals()];

        for (int pointOfHorizontalField = INDEX_OF_FIRST_FIELD; pointOfHorizontalField < chessDesk.getHorizontals(); pointOfHorizontalField++) {
            for (int pointOfVerticalField = INDEX_OF_FIRST_FIELD; pointOfVerticalField < chessDesk.getVerticals(); pointOfVerticalField++) {
                isMate[pointOfHorizontalField][pointOfVerticalField] = (ChessStature) chessDesk.returnsPositionFigureOnBoardSquare(pointOfHorizontalField, pointOfVerticalField);
            }
        }
        return isMate;
    }

    public boolean[][] possibleMovesOfChessFigure(ChessPosition sourcePosition) {
        Location originField = sourcePosition.toPosition();
        checksSourcePosition(originField);
        return chessDesk.returnsPositionFigureOnBoardSquare(originField).possibleMovesOfAChessFigure();
    }

    public ChessStature performChessMove(ChessPosition startingPosition, ChessPosition targetPosition) {
        Location originField = startingPosition.toPosition();
        Location targetField = targetPosition.toPosition();
        checksSourcePosition(originField);
        checksTargetPosition(originField, targetField);
        Stature capturedPiece = makesMovementAChessFigure(originField, targetField);

        if (testsCheck(currentPlayer)) {
            undoMovementOfTheChessFigure(originField, targetField, capturedPiece);
            throw new ChessException(ERROR_MOVEMENT);
        }

        ChessStature movedChessFigure = (ChessStature) chessDesk.returnsPositionFigureOnBoardSquare(targetField);

        advancedChessFigure = null;
        if (movedChessFigure instanceof Pawn) {
            if ((movedChessFigure.getColor() == SquareColorOnTheChessBoard.WHITE && targetField.getHorizontal() == INDEX_OF_FIRST_FIELD)
                    || (movedChessFigure.getColor() == SquareColorOnTheChessBoard.BLACK && targetField.getHorizontal() == NUMBER_OF_FIELDS_ON_THE_HORIZONTAL - ONE_SIMPLE_FIELD)) {
                advancedChessFigure = (ChessStature) chessDesk.returnsPositionFigureOnBoardSquare(targetField);
                advancedChessFigure = replacesPromotedChessFigure(QUEEN);
            }
        }

        check = testsCheck(contestant(currentPlayer));

        if (testsCheckmate(contestant(currentPlayer))) {
            checkMate = true;
        } else {
            changesQueue();
        }

        if (movedChessFigure instanceof Pawn && (targetField.getHorizontal() == originField.getHorizontal() - TWO_FIELD_SKIP || targetField.getHorizontal() == originField.getHorizontal() + TWO_FIELD_SKIP)) {
            walkThroughVulnerability = movedChessFigure;
        } else {
            walkThroughVulnerability = null;
        }

        return (ChessStature) capturedPiece;

    }

    public ChessStature replacesPromotedChessFigure(String type) {
        if (advancedChessFigure == null) {
            throw new IllegalStateException(ERROR_MOVEMENT_SECOND);
        }
        if (!type.equals(BISHOP)
                && !type.equals(KNIGHT)
                && !type.equals(ROOK)
                && !type.equals(QUEEN)) {
            throw new InvalidParameterException(ERROR_MOVEMENT_THIRD);
        }

        Location position = advancedChessFigure.getChessPosition().toPosition();
        Stature fieldPosition = chessDesk.removesChessFigure(position);
        piecesOnTheBoard.remove(fieldPosition);

        ChessStature newFigure = returnsANewChessFigure(type, advancedChessFigure.getColor());
        chessDesk.showsPlaceOfChessFigure(newFigure, position);
        piecesOnTheBoard.add(newFigure);
        return newFigure;
    }

    private ChessStature returnsANewChessFigure(String typeOfChessFigure, SquareColorOnTheChessBoard colorSquareOfDeskBoard) {
        if (typeOfChessFigure.equals(QUEEN)) return new Queen(chessDesk, colorSquareOfDeskBoard);
        if (typeOfChessFigure.equals(KNIGHT)) return new Knight(chessDesk, colorSquareOfDeskBoard);
        if (typeOfChessFigure.equals(BISHOP)) return new Bishop(chessDesk, colorSquareOfDeskBoard);
        return new Rook(chessDesk, colorSquareOfDeskBoard);
    }

    private Stature makesMovementAChessFigure(Location startLocationChessFigure, Location targetLocationChessFigure) {
        ChessStature chessStature = (ChessStature) chessDesk.removesChessFigure(startLocationChessFigure);
        chessStature.increaseMoveCount();
        Stature capturedChessFigure = chessDesk.removesChessFigure(targetLocationChessFigure);
        chessDesk.showsPlaceOfChessFigure(chessStature, targetLocationChessFigure);

        if (capturedChessFigure != null) {
            piecesOnTheBoard.remove(capturedChessFigure);
            capturedPieces.add(capturedChessFigure);
        }

        if (chessStature instanceof King && targetLocationChessFigure.getVertical() == startLocationChessFigure.getVertical() + TWO_FIELD_SKIP) {
            Location startPositionKingSideRook = new Location(startLocationChessFigure.getHorizontal(), startLocationChessFigure.getVertical() + TREE_FIELD_SKIP);
            Location targetPositionKingSideRook = new Location(startLocationChessFigure.getHorizontal(), startLocationChessFigure.getVertical() + ONE_FIELD_SKIP);
            ChessStature rook = (ChessStature) chessDesk.removesChessFigure(startPositionKingSideRook);
            chessDesk.showsPlaceOfChessFigure(rook, targetPositionKingSideRook);
            rook.increaseMoveCount();
        }

        if (chessStature instanceof King && targetLocationChessFigure.getVertical() == startLocationChessFigure.getVertical() - TWO_FIELD_SKIP) {
            Location sourcePositionRookQueenSide = new Location(startLocationChessFigure.getHorizontal(), startLocationChessFigure.getVertical() - FOUR_FIELD_SKIP);
            Location targetPositionRookQueenSide = new Location(startLocationChessFigure.getHorizontal(), startLocationChessFigure.getVertical() - ONE_FIELD_SKIP);
            ChessStature rook = (ChessStature) chessDesk.removesChessFigure(sourcePositionRookQueenSide);
            chessDesk.showsPlaceOfChessFigure(rook, targetPositionRookQueenSide);
            rook.increaseMoveCount();
        }

        if (chessStature instanceof Pawn) {
            if (startLocationChessFigure.getVertical() != targetLocationChessFigure.getVertical() && capturedChessFigure == null) {
                Location pawnPosition;
                if (chessStature.getColor() == SquareColorOnTheChessBoard.WHITE) {
                    pawnPosition = new Location(targetLocationChessFigure.getHorizontal() + ONE_SIMPLE_FIELD, targetLocationChessFigure.getVertical());
                } else {
                    pawnPosition = new Location(targetLocationChessFigure.getHorizontal() - ONE_SIMPLE_FIELD, targetLocationChessFigure.getVertical());
                }
                capturedChessFigure = chessDesk.removesChessFigure(pawnPosition);
                capturedPieces.add(capturedChessFigure);
                piecesOnTheBoard.remove(capturedChessFigure);
            }
        }
        return capturedChessFigure;
    }

    private void undoMovementOfTheChessFigure(Location startPosition, Location targetPosition, Stature capturedFigure) {
        ChessStature position = (ChessStature) chessDesk.removesChessFigure(targetPosition);
        position.decreaseMoveCount();
        chessDesk.showsPlaceOfChessFigure(position, startPosition);

        if (capturedFigure != null) {
            chessDesk.showsPlaceOfChessFigure(capturedFigure, targetPosition);
            capturedPieces.remove(capturedFigure);
            piecesOnTheBoard.add(capturedFigure);
        }

        if (position instanceof King && targetPosition.getVertical() == startPosition.getVertical() + TWO_FIELD_SKIP) {
            Location startRooKPositionOnKingSide = new Location(startPosition.getHorizontal(), startPosition.getVertical() + TREE_FIELD_SKIP);
            Location targetRookPositionOnKingSide = new Location(startPosition.getHorizontal(), startPosition.getVertical() + ONE_FIELD_SKIP);
            ChessStature rook = (ChessStature) chessDesk.removesChessFigure(targetRookPositionOnKingSide);
            chessDesk.showsPlaceOfChessFigure(rook, startRooKPositionOnKingSide);
            rook.decreaseMoveCount();
        }

        if (position instanceof King && targetPosition.getVertical() == startPosition.getVertical() - TWO_FIELD_SKIP) {
            Location startRooKPositionOnQueenSide = new Location(startPosition.getHorizontal(), startPosition.getVertical() - FOUR_FIELD_SKIP);
            Location targetRookPositionOnQueenSide = new Location(startPosition.getHorizontal(), startPosition.getVertical() - ONE_FIELD_SKIP);
            ChessStature rook = (ChessStature) chessDesk.removesChessFigure(targetRookPositionOnQueenSide);
            chessDesk.showsPlaceOfChessFigure(rook, startRooKPositionOnQueenSide);
            rook.decreaseMoveCount();
        }

        if (position instanceof Pawn) {
            if (startPosition.getVertical() != targetPosition.getVertical() && capturedFigure == walkThroughVulnerability) {
                ChessStature pawn = (ChessStature) chessDesk.removesChessFigure(targetPosition);
                Location pawnPosition;
                if (position.getColor() == SquareColorOnTheChessBoard.WHITE) {
                    pawnPosition = new Location(LOCATION_ON_HORIZONTAL_NUMBER_FOUR, targetPosition.getVertical());
                } else {
                    pawnPosition = new Location(LOCATION_ON_HORIZONTAL_NUMBER_FIVE, targetPosition.getVertical());
                }
                chessDesk.showsPlaceOfChessFigure(pawn, pawnPosition);
            }
        }
    }

    private void checksSourcePosition(Location position) {
        if (!chessDesk.definesAChessFigure(position)) {
            throw new ChessException(ERROR_STARTING_POSITION);
        }
        if (currentPlayer != ((ChessStature) chessDesk.returnsPositionFigureOnBoardSquare(position)).getColor()) {
            throw new ChessException(ERROR_CHOOSE_CHESS_FIGURE);
        }
        if (!chessDesk.returnsPositionFigureOnBoardSquare(position).checksIfAPossibleMoveExists()) {
            throw new ChessException(ERROR_MOVEMENT_CHOSEN_CHESS_FIGURE);
        }
    }

    private void checksTargetPosition(Location startPosition, Location target) {
        if (!chessDesk.returnsPositionFigureOnBoardSquare(startPosition).showsThePossibleMovesOfAChessFigure(target)) {
            throw new ChessException(ERROR_MOVEMENT_CHOSEN_TARGET_POSITION);
        }
    }

    private void changesQueue() {
        queueOrder++;
        currentPlayer = (currentPlayer == SquareColorOnTheChessBoard.WHITE) ? SquareColorOnTheChessBoard.BLACK : SquareColorOnTheChessBoard.WHITE;
    }

    private SquareColorOnTheChessBoard contestant(SquareColorOnTheChessBoard color) {
        return (color == SquareColorOnTheChessBoard.WHITE) ? SquareColorOnTheChessBoard.BLACK : SquareColorOnTheChessBoard.WHITE;
    }

    private ChessStature king(SquareColorOnTheChessBoard color) {
        List<Stature> chessPiece = piecesOnTheBoard.stream()
                .filter(x -> ((ChessStature) x).getColor() == color)
                .collect(Collectors.toList());

        for (Stature chessFigure : chessPiece) {
            if (chessFigure instanceof King) {
                return (ChessStature) chessFigure;
            }
        }
        throw new IllegalStateException("There is no " + color + " king on the board");
    }

    private boolean testsCheck(SquareColorOnTheChessBoard color) {
        Location kingPosition = king(color).getChessPosition().toPosition();
        List<Stature> contestantPieces = piecesOnTheBoard.stream()
                .filter(x -> ((ChessStature) x).getColor() == contestant(color))
                .collect(Collectors.toList());

        for (Stature chessFigure : contestantPieces) {
            boolean[][] mat = chessFigure.possibleMovesOfAChessFigure();
            if (mat[kingPosition.getHorizontal()][kingPosition.getVertical()]) {
                return true;
            }
        }
        return false;
    }

    private boolean testsCheckmate(SquareColorOnTheChessBoard color) {
        if (!testsCheck(color)) {
            return false;
        }

        List<Stature> chessFigures = piecesOnTheBoard.stream()
                .filter(x -> ((ChessStature) x).getColor() == color)
                .collect(Collectors.toList());

        for (Stature figure : chessFigures) {
            boolean[][] mat = figure.possibleMovesOfAChessFigure();
            for (int startPointHorizontal = FIRST_START_INDEX; startPointHorizontal < chessDesk.getHorizontals(); startPointHorizontal++) {
                for (int startPointVertical = FIRST_START_INDEX; startPointVertical < chessDesk.getVerticals(); startPointVertical++) {
                    if (mat[startPointHorizontal][startPointVertical]) {
                        Location source = ((ChessStature) figure).getChessPosition().toPosition();
                        Location target = new Location(startPointHorizontal, startPointVertical);
                        Stature capturedPiece = makesMovementAChessFigure(source, target);
                        boolean testCheck = testsCheck(color);
                        undoMovementOfTheChessFigure(source, target, capturedPiece);
                        if (!testCheck) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    protected void placeNewPiece(char column, int row, ChessStature piece) {
        chessDesk.showsPlaceOfChessFigure(piece, new ChessPosition(column, row).toPosition());
        piecesOnTheBoard.add(piece);
    }

    private void initialPositionOfTheFigures() {
        placeNewPiece(ChessBoardNameFields.VERTICAL_A, ChessBoardNameFields.HORIZONTAL_1, new Rook(chessDesk, SquareColorOnTheChessBoard.WHITE));
        placeNewPiece(ChessBoardNameFields.VERTICAL_B, ChessBoardNameFields.HORIZONTAL_1, new Knight(chessDesk, SquareColorOnTheChessBoard.WHITE));
        placeNewPiece(ChessBoardNameFields.VERTICAL_C, ChessBoardNameFields.HORIZONTAL_1, new Bishop(chessDesk, SquareColorOnTheChessBoard.WHITE));
        placeNewPiece(ChessBoardNameFields.VERTICAL_D, ChessBoardNameFields.HORIZONTAL_1, new Queen(chessDesk, SquareColorOnTheChessBoard.WHITE));
        placeNewPiece(ChessBoardNameFields.VERTICAL_E, ChessBoardNameFields.HORIZONTAL_1, new King(chessDesk, SquareColorOnTheChessBoard.WHITE, this));
        placeNewPiece(ChessBoardNameFields.VERTICAL_F, ChessBoardNameFields.HORIZONTAL_1, new Bishop(chessDesk, SquareColorOnTheChessBoard.WHITE));
        placeNewPiece(ChessBoardNameFields.VERTICAL_G, ChessBoardNameFields.HORIZONTAL_1, new Knight(chessDesk, SquareColorOnTheChessBoard.WHITE));
        placeNewPiece(ChessBoardNameFields.VERTICAL_H, ChessBoardNameFields.HORIZONTAL_1, new Rook(chessDesk, SquareColorOnTheChessBoard.WHITE));
        placeNewPiece(ChessBoardNameFields.VERTICAL_A, ChessBoardNameFields.HORIZONTAL_2, new Pawn(chessDesk, SquareColorOnTheChessBoard.WHITE, this));
        placeNewPiece(ChessBoardNameFields.VERTICAL_B, ChessBoardNameFields.HORIZONTAL_2, new Pawn(chessDesk, SquareColorOnTheChessBoard.WHITE, this));
        placeNewPiece(ChessBoardNameFields.VERTICAL_C, ChessBoardNameFields.HORIZONTAL_2, new Pawn(chessDesk, SquareColorOnTheChessBoard.WHITE, this));
        placeNewPiece(ChessBoardNameFields.VERTICAL_D, ChessBoardNameFields.HORIZONTAL_2, new Pawn(chessDesk, SquareColorOnTheChessBoard.WHITE, this));
        placeNewPiece(ChessBoardNameFields.VERTICAL_E, ChessBoardNameFields.HORIZONTAL_2, new Pawn(chessDesk, SquareColorOnTheChessBoard.WHITE, this));
        placeNewPiece(ChessBoardNameFields.VERTICAL_F, ChessBoardNameFields.HORIZONTAL_2, new Pawn(chessDesk, SquareColorOnTheChessBoard.WHITE, this));
        placeNewPiece(ChessBoardNameFields.VERTICAL_G, ChessBoardNameFields.HORIZONTAL_2, new Pawn(chessDesk, SquareColorOnTheChessBoard.WHITE, this));
        placeNewPiece(ChessBoardNameFields.VERTICAL_H, ChessBoardNameFields.HORIZONTAL_2, new Pawn(chessDesk, SquareColorOnTheChessBoard.WHITE, this));

        placeNewPiece(ChessBoardNameFields.VERTICAL_A, ChessBoardNameFields.HORIZONTAL_8, new Rook(chessDesk, SquareColorOnTheChessBoard.BLACK));
        placeNewPiece(ChessBoardNameFields.VERTICAL_B, ChessBoardNameFields.HORIZONTAL_8, new Knight(chessDesk, SquareColorOnTheChessBoard.BLACK));
        placeNewPiece(ChessBoardNameFields.VERTICAL_C, ChessBoardNameFields.HORIZONTAL_8, new Bishop(chessDesk, SquareColorOnTheChessBoard.BLACK));
        placeNewPiece(ChessBoardNameFields.VERTICAL_D, ChessBoardNameFields.HORIZONTAL_8, new Queen(chessDesk, SquareColorOnTheChessBoard.BLACK));
        placeNewPiece(ChessBoardNameFields.VERTICAL_E, ChessBoardNameFields.HORIZONTAL_8, new King(chessDesk, SquareColorOnTheChessBoard.BLACK, this));
        placeNewPiece(ChessBoardNameFields.VERTICAL_F, ChessBoardNameFields.HORIZONTAL_8, new Bishop(chessDesk, SquareColorOnTheChessBoard.BLACK));
        placeNewPiece(ChessBoardNameFields.VERTICAL_G, ChessBoardNameFields.HORIZONTAL_8, new Knight(chessDesk, SquareColorOnTheChessBoard.BLACK));
        placeNewPiece(ChessBoardNameFields.VERTICAL_H, ChessBoardNameFields.HORIZONTAL_8, new Rook(chessDesk, SquareColorOnTheChessBoard.BLACK));
        placeNewPiece(ChessBoardNameFields.VERTICAL_A, ChessBoardNameFields.HORIZONTAL_7, new Pawn(chessDesk, SquareColorOnTheChessBoard.BLACK, this));
        placeNewPiece(ChessBoardNameFields.VERTICAL_B, ChessBoardNameFields.HORIZONTAL_7, new Pawn(chessDesk, SquareColorOnTheChessBoard.BLACK, this));
        placeNewPiece(ChessBoardNameFields.VERTICAL_C, ChessBoardNameFields.HORIZONTAL_7, new Pawn(chessDesk, SquareColorOnTheChessBoard.BLACK, this));
        placeNewPiece(ChessBoardNameFields.VERTICAL_D, ChessBoardNameFields.HORIZONTAL_7, new Pawn(chessDesk, SquareColorOnTheChessBoard.BLACK, this));
        placeNewPiece(ChessBoardNameFields.VERTICAL_E, ChessBoardNameFields.HORIZONTAL_7, new Pawn(chessDesk, SquareColorOnTheChessBoard.BLACK, this));
        placeNewPiece(ChessBoardNameFields.VERTICAL_F, ChessBoardNameFields.HORIZONTAL_7, new Pawn(chessDesk, SquareColorOnTheChessBoard.BLACK, this));
        placeNewPiece(ChessBoardNameFields.VERTICAL_G, ChessBoardNameFields.HORIZONTAL_7, new Pawn(chessDesk, SquareColorOnTheChessBoard.BLACK, this));
        placeNewPiece(ChessBoardNameFields.VERTICAL_H, ChessBoardNameFields.HORIZONTAL_7, new Pawn(chessDesk, SquareColorOnTheChessBoard.BLACK, this));
    }
}
