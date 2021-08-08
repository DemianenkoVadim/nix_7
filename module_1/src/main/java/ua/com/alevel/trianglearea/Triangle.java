package ua.com.alevel.trianglearea;

import java.util.ArrayList;

public class Triangle {

    private static final int SECOND_DEGREE = 2;
    private static final int DIVISION_BY_TWO = 2;
    private static final int FIRST_INDEX_OF_THE_ELEMENT = 0;
    private static final int SECOND_INDEX_OF_THE_ELEMENT = 1;
    private static final int THIRD_INDEX_OF_THE_ELEMENT = 2;
    private static final String TRIANGLE_DOES_NOT_EXIST = "Triangle doesn't exist, check you input data";
    private static final String ROUND_TO_THE_SECOND_DECIMAL_PLACE = "%.2f";

    public int getFirstCoordinate_X() {
        return DataOutputRequests.getsFirstAbscissaPoint();
    }

    public int getFirstCoordinate_Y() {
        return DataOutputRequests.getsFirstOrdinatePoint();
    }

    public int getSecondCoordinate_X() {
        return DataOutputRequests.getsSecondAbscissaPoint();
    }

    public int getSecondCoordinate_Y() {
        return DataOutputRequests.getsSecondOrdinatePoint();
    }

    public int getThirdCoordinate_X() {
        return DataOutputRequests.getsThirdAbscissaPoint();
    }

    public int getThirdCoordinate_Y() {
        return DataOutputRequests.getsThirdOrdinatePoint();
    }

    public void createsCoordinatesVerticesOfTriangle() {
        ArrayList<Coordinate2D> triangleCoordinates = new ArrayList<>();
        triangleCoordinates.add(new Coordinate2D(getFirstCoordinate_X(), getFirstCoordinate_Y()));
        triangleCoordinates.add(new Coordinate2D(getSecondCoordinate_X(), getSecondCoordinate_Y()));
        triangleCoordinates.add(new Coordinate2D(getThirdCoordinate_X(), getThirdCoordinate_Y()));
        printsResultOfCalculatingTriangleArea(triangleCoordinates);
    }

    public void printsResultOfCalculatingTriangleArea(ArrayList<Coordinate2D> triangleVertices) {
        if ((findsLengthFirstSideOfTriangle(triangleVertices) + findsLengthOfSecondSideOfTriangle(triangleVertices) > findsLengthOfThirdSideOfTriangle(triangleVertices)) ||
                (findsLengthOfSecondSideOfTriangle(triangleVertices) + findsLengthOfThirdSideOfTriangle(triangleVertices) > findsLengthFirstSideOfTriangle(triangleVertices)) ||
                (findsLengthFirstSideOfTriangle(triangleVertices) + findsLengthOfThirdSideOfTriangle(triangleVertices) > findsLengthOfSecondSideOfTriangle(triangleVertices))) {
            System.out.println(findsAreaOfTriangle(triangleVertices));
        } else {
            System.out.println(TRIANGLE_DOES_NOT_EXIST);
        }
    }

    public String findsAreaOfTriangle(ArrayList<Coordinate2D> triangleVertices) {
        return String.format(ROUND_TO_THE_SECOND_DECIMAL_PLACE, Math.sqrt(findsSemiPerimeterOfTriangle(triangleVertices) *
                (findsSemiPerimeterOfTriangle(triangleVertices) - findsLengthFirstSideOfTriangle(triangleVertices)) *
                (findsSemiPerimeterOfTriangle(triangleVertices) - findsLengthOfSecondSideOfTriangle(triangleVertices)) *
                (findsSemiPerimeterOfTriangle(triangleVertices) - findsLengthOfThirdSideOfTriangle(triangleVertices))));
    }

    private double findsLengthFirstSideOfTriangle(ArrayList<Coordinate2D> triangleVertices) {
        return (Math.sqrt(Math.pow((triangleVertices.get(SECOND_INDEX_OF_THE_ELEMENT).getPointAbscissa() -
                triangleVertices.get(FIRST_INDEX_OF_THE_ELEMENT).getPointAbscissa()), SECOND_DEGREE) +
                Math.pow((triangleVertices.get(SECOND_INDEX_OF_THE_ELEMENT).getPointOrdinate() -
                        triangleVertices.get(FIRST_INDEX_OF_THE_ELEMENT).getPointOrdinate()), SECOND_DEGREE)));
    }

    private double findsLengthOfSecondSideOfTriangle(ArrayList<Coordinate2D> triangleVertices) {
        return (Math.sqrt(Math.pow((triangleVertices.get(THIRD_INDEX_OF_THE_ELEMENT).getPointAbscissa() -
                triangleVertices.get(SECOND_INDEX_OF_THE_ELEMENT).getPointAbscissa()), SECOND_DEGREE) +
                Math.pow((triangleVertices.get(THIRD_INDEX_OF_THE_ELEMENT).getPointOrdinate() -
                        triangleVertices.get(SECOND_INDEX_OF_THE_ELEMENT).getPointOrdinate()), SECOND_DEGREE)));
    }

    private double findsLengthOfThirdSideOfTriangle(ArrayList<Coordinate2D> triangleVertices) {
        return (Math.sqrt(Math.pow((triangleVertices.get(FIRST_INDEX_OF_THE_ELEMENT).getPointAbscissa() -
                triangleVertices.get(THIRD_INDEX_OF_THE_ELEMENT).getPointAbscissa()), SECOND_DEGREE) +
                Math.pow((triangleVertices.get(FIRST_INDEX_OF_THE_ELEMENT).getPointOrdinate() -
                        triangleVertices.get(THIRD_INDEX_OF_THE_ELEMENT).getPointOrdinate()), SECOND_DEGREE)));
    }

    private double findsSemiPerimeterOfTriangle(ArrayList<Coordinate2D> triangleVertices) {
        return (findsLengthFirstSideOfTriangle(triangleVertices) +
                findsLengthOfSecondSideOfTriangle(triangleVertices) +
                findsLengthOfThirdSideOfTriangle(triangleVertices)) / DIVISION_BY_TWO;
    }
}
