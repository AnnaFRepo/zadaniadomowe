package weekend2.homework.CircleExercise;

public class CircleTest {

    public static void main(String[] args) {

        Circle circle1 = new Circle();
        System.out.println(circle1.toString());
        Circle circle2 = new Circle(1.5, "blue");
        System.out.println(circle2.toString());
        Circle circle3 = new Circle(3, "black", 3.3, 2.2);
        System.out.println(circle3.toString());
        Circle circle4 = new Circle(4.5);
        System.out.println(circle4.toString());
        Circle circle5 = new Circle(5, 4.5);
        System.out.println(circle5.toString());
    }
}
