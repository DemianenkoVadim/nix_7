package ua.com.alevel.figureknight;

import java.util.Scanner;

public class DataRequests {

    private static final int EXIT = 0;
    private static final int CONTINUE_CHOOSE = 1;
    private static final String CHESS_KNIGHT_POSITION_START = "Enter the starting position of the chess piece knight: ";
    private static final String CHESS_KNIGHT_START_ABSCISSA_POINT = "Firstly Enter the number on the abscissa";
    private static final String CHESS_KNIGHT_START_ORDINATE_POINT = "Secondly Enter the number on the ordinate";
    private static final String CHESS_KNIGHT_POSITION_TARGET = "Enter the target position of the chess piece knight: ";
    private static final String CHESS_KNIGHT_TARGET_ABSCISSA_POINT = "Firstly Enter the number on the abscissa";
    private static final String CHESS_KNIGHT_TARGET_ORDINATE_POINT = "Secondly Enter the number on the ordinate";
    private static final String REQUEST_TO_CONTINUE = "Do you want to continue ?";
    private static final String REQUEST_TO_MAKE_CHOOSE = "Press - 1 if YES or - 2 NO";
    private static final String END_PROGRAM = "Bye";

    public static int printsARequestForDataEntryOnAbscissaAxis() {
        System.out.println(CHESS_KNIGHT_POSITION_START);
        Scanner inputStartAbscissaNumber = new Scanner(System.in);
        System.out.println(CHESS_KNIGHT_START_ABSCISSA_POINT);
        return inputStartAbscissaNumber.nextInt();
    }

    public static int printsARequestForDataEntryOnOrdinateAxis() {
        Scanner inputStartOrdinateNumber = new Scanner(System.in);
        System.out.println(CHESS_KNIGHT_START_ORDINATE_POINT);
        return inputStartOrdinateNumber.nextInt();
    }

    public static int printsARequestForDataEntryTargetOnAbscissaAxis() {
        System.out.println(CHESS_KNIGHT_POSITION_TARGET);
        Scanner inputTargetAbscissaNumber = new Scanner(System.in);
        System.out.println(CHESS_KNIGHT_TARGET_ABSCISSA_POINT);
        return inputTargetAbscissaNumber.nextInt();
    }

    public static int printsARequestForDataEntryTargetOnOrdinateAxis() {
        Scanner inputFirstOrdinateNumber = new Scanner(System.in);
        System.out.println(CHESS_KNIGHT_TARGET_ORDINATE_POINT);
        return inputFirstOrdinateNumber.nextInt();
    }

    public static void chooseUserToContinue() {
        Scanner inputUserChoose = new Scanner(System.in);
        System.out.println(REQUEST_TO_CONTINUE);
        System.out.println(REQUEST_TO_MAKE_CHOOSE);
        if (inputUserChoose.nextInt() == CONTINUE_CHOOSE) {
            ChessHorseStroke.createsCoordinatesVerticesOfTriangle();
        } else {
            System.out.println(END_PROGRAM);
            System.exit(EXIT);
        }
    }
}
