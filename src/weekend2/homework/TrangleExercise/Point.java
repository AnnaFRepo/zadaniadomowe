package weekend2.homework.TrangleExercise;

public class Point {

    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static double distance(Point point1, Point point2) {
        return Math.sqrt((Math.pow((point2.x - point1.x), 2) + (Math.pow((point2.y - point1.y), 2))));
    }

}
