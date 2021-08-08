package ua.com.alevel.figureknight;

import java.util.ArrayList;

public class ChessHorseStroke {

    private static final int ONE_CELL = 1;
    private static final int TWO_CELLS = 2;
    private static final int START_ABSCISSA_POINT_KNIGHT = 0;
    private static final int START_ORDINATE_POINT_KNIGHT = 1;
    private static final int TARGET_ABSCISSA_POINT_KNIGHT = 2;
    private static final int TARGET_ORDINATE_POINT_KNIGHT = 3;

    private static final String RIGHT_MOVE = "Possible Move";
    private static final String WRONG_MOVE = "The chess knight doesn't walk like that";


    public static int getStartAbscissaPointChessKnight() {
        return DataRequests.printsARequestForDataEntryOnAbscissaAxis();
    }

    public static int getStartOrdinatePointChessKnight() {
        return DataRequests.printsARequestForDataEntryOnOrdinateAxis();
    }

    public static int getTargetAbscissaPointChessKnight() {
        return DataRequests.printsARequestForDataEntryTargetOnAbscissaAxis();
    }

    public static int getTargetOrdinatePointChessKnight() {
        return DataRequests.printsARequestForDataEntryTargetOnOrdinateAxis();
    }

    public static void createsCoordinatesVerticesOfTriangle() {
        ArrayList<Integer> chessKnightPositions = new ArrayList<>();
        chessKnightPositions.add(getStartAbscissaPointChessKnight());
        chessKnightPositions.add(getStartOrdinatePointChessKnight());
        chessKnightPositions.add(getTargetAbscissaPointChessKnight());
        chessKnightPositions.add(getTargetOrdinatePointChessKnight());
        checksAndPrintsResultMoveChessKnightPossible(chessKnightPositions);
    }

    public static int checksMoveChessKnightPossibleOnAbscissaAxis(ArrayList<Integer> chessKnightPosition) {
        return Math.abs(chessKnightPosition.get(START_ABSCISSA_POINT_KNIGHT) - chessKnightPosition.get(TARGET_ABSCISSA_POINT_KNIGHT));
    }

    public static int checksMoveChessKnightPossibleOnOrdinateAxis(ArrayList<Integer> chessKnightPosition) {
        return Math.abs(chessKnightPosition.get(START_ORDINATE_POINT_KNIGHT) - chessKnightPosition.get(TARGET_ORDINATE_POINT_KNIGHT));
    }

    public static void checksAndPrintsResultMoveChessKnightPossible(ArrayList<Integer> chessKnightPosition) {
        if (checksMoveChessKnightPossibleOnAbscissaAxis(chessKnightPosition) == ONE_CELL && checksMoveChessKnightPossibleOnOrdinateAxis(chessKnightPosition) == TWO_CELLS ||
                checksMoveChessKnightPossibleOnAbscissaAxis(chessKnightPosition) == TWO_CELLS && checksMoveChessKnightPossibleOnOrdinateAxis(chessKnightPosition) == ONE_CELL) {
            System.out.println(RIGHT_MOVE);
        } else {
            System.out.println(WRONG_MOVE);
        }
        DataRequests.chooseUserToContinue();
    }
}
