package weekend2.homework.ClassPointExercise;

public class PointTest {

    public static void main(String[] args) {

        Point p1 = new Point();
        p1.setX(0.0);
        p1.setY(0.0);
        System.out.println(p1.toString());

        Point p2 = new Point(5.55, 1.23);
        System.out.println(p2.toString());
    }

}
