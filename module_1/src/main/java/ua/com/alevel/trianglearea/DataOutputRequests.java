package ua.com.alevel.trianglearea;

import java.util.Scanner;

public class DataOutputRequests {

    private static final String REQUEST_FOR_ORIGINAL_FIRST_ABSCISSA = "Please input the first abscissa point coordinates: ";
    private static final String REQUEST_FOR_ORIGINAL_FIRST_ORDINATE = "Please input the first ordinate point coordinates: ";
    private static final String REQUEST_FOR_ORIGINAL_SECOND_ABSCISSA = "Please input the second abscissa point coordinates: ";
    private static final String REQUEST_FOR_ORIGINAL_SECOND_ORDINATE = "Please input the second ordinate point coordinates: ";
    private static final String REQUEST_FOR_ORIGINAL_THIRD_ABSCISSA = "Please input the third abscissa point coordinates: ";
    private static final String REQUEST_FOR_ORIGINAL_THIRD_ORDINATE = "Please input the third ordinate point coordinates: ";

    public static int getsFirstAbscissaPoint() {
        Scanner inputFirstAbscissaPoint = new Scanner(System.in);
        System.out.println(REQUEST_FOR_ORIGINAL_FIRST_ABSCISSA);
        return inputFirstAbscissaPoint.nextInt();
    }

    public static int getsFirstOrdinatePoint() {
        Scanner inputFirstOrdinatePoint = new Scanner(System.in);
        System.out.println(REQUEST_FOR_ORIGINAL_FIRST_ORDINATE);
        return inputFirstOrdinatePoint.nextInt();
    }

    public static int getsSecondAbscissaPoint() {
        Scanner inputSecondAbscissaPoint = new Scanner(System.in);
        System.out.println(REQUEST_FOR_ORIGINAL_SECOND_ABSCISSA);
        return inputSecondAbscissaPoint.nextInt();
    }

    public static int getsSecondOrdinatePoint() {
        Scanner inputSecondOrdinatePoint = new Scanner(System.in);
        System.out.println(REQUEST_FOR_ORIGINAL_SECOND_ORDINATE);
        return inputSecondOrdinatePoint.nextInt();
    }

    public static int getsThirdAbscissaPoint() {
        Scanner inputThirdAbscissaPoint = new Scanner(System.in);
        System.out.println(REQUEST_FOR_ORIGINAL_THIRD_ABSCISSA);
        return inputThirdAbscissaPoint.nextInt();
    }

    public static int getsThirdOrdinatePoint() {
        Scanner inputThirdOrdinatePoint = new Scanner(System.in);
        System.out.println(REQUEST_FOR_ORIGINAL_THIRD_ORDINATE);
        return inputThirdOrdinatePoint.nextInt();
    }
}
