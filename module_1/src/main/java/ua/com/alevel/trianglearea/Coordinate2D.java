package ua.com.alevel.trianglearea;

public class Coordinate2D {

    private final int pointAbscissa;
    private final int pointOrdinate;

    public Coordinate2D(int pointAbscissa, int pointOrdinate) {
        this.pointAbscissa = pointAbscissa;
        this.pointOrdinate = pointOrdinate;
    }

    public int getPointAbscissa() {
        return pointAbscissa;
    }

    public int getPointOrdinate() {
        return pointOrdinate;
    }
}
