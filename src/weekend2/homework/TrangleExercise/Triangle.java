package weekend2.homework.TrangleExercise;

public class Triangle {

    private Point pointA;
    private Point pointB;
    private Point pointC;
    private double ab;
    private double ac;
    private double bc;

    public Triangle(Point pointA, Point pointB, Point pointC) {
        this.pointA = pointA;
        this.pointB = pointB;
        this.pointC = pointC;
        ab = Point.distance(pointA, pointB);
        ac = Point.distance(pointA, pointC);
        bc = Point.distance(pointC, pointB);
    }

    public void showTriangleInfo() {
        System.out.println("Triangle info:\nVertices of the triangle " +
                " pointA = [" + pointA.x + ", " + pointA.y + "]" +
                ", pointB = [" + pointB.x + ", " + pointB.y + "]" +
                ", pointC = [" + pointC.x + ", " + pointC.y + "]" +
                "\nLengths of the sides of the triangle:" +
                " side ab = " + ab +
                ", side ac = " + ac +
                ", side bc = " + bc);
    }

    public double calculateCircuitOfTriangle() {
        return ab + ac + bc;
    }

    public static double calculateCircuitOfTriangle(Point pointA, Point pointB, Point pointC) {
        return Point.distance(pointA, pointB) +
        Point.distance(pointA, pointC) +
        Point.distance(pointC, pointB);
    }
}
