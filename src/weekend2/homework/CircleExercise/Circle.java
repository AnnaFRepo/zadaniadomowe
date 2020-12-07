package weekend2.homework.CircleExercise;

import java.util.regex.Matcher;

public class Circle {

//    4. Stwórz klasę Circle.
//     a) zdefiniuj dwa prywatne pola double i String opisujące promień (radius) oraz kolor
//    (color)
//    b) stwórz bezparametrowy konstruktor ustawiający pole radius na wartość 1.5 oraz
//    kolor na czerwony
//    c) stwórz konstruktor sparametryzowany pozwalający na ustawienie promienia i
//    koloru
//    d) stwórz gettery do pól reprezentujących promień i kolor
//    e) stwórz metodę pozwalającą na obliczenie pola koła
//    f) stwórz w głównej klasie kilka obiektów klasy Circle oraz dla promieni 1.5,3,4.5
//    wyświetl ich pole.
//    g) dorób dwa dodatkowe pola x i y oraz dorób wszystkie możliwe konstruktory w
//    klasie. Jeśli dane będą wystarczające to wyświetlaj pole, jeśli nie to współrzędne.

    private double radius;
    private String color;
    private double x;
    private double y;
    double circleArea;

    public Circle() {
        this.radius = 1.5;
        this.color = "red";
        this.circleArea = countCircleArea();
    }

    public Circle(double radius) {
        this.radius = radius;
        this.circleArea = countCircleArea();
    }

    public Circle(double radius, String color) {
        this(radius);
        this.color = color;
    }

    public Circle(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Circle(double radius, String color, double x, double y) {
        this(radius, color);
        this.x = x;
        this.y = y;
    }

    public double countCircleArea() {
        if (radius > 0) {
            circleArea = Math.PI * Math.sqrt(radius);
        } else {
           circleArea = 0;
        }
        return circleArea;
    }

    public double getRadius() {
        return radius;
    }

    public String getColor() {
        return color;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        if (radius != 0 && color != null) {
            return "Circle {" +
                    "radius = " + radius +
                    ", color = '" + color + '\'' +
                    ", x = " + x +
                    ", y = " + y +
                    ", circleArea = " + circleArea +
                    '}';
        } else if (radius != 0) {
            return "Circle {" +
                    "radius = " + radius +
                    ", x = " + x +
                    ", y = " + y +
                    ", circleArea = " + circleArea +
                    '}';
        } else {
            return "Circle {" +
                    "x = " + x +
                    ", y = " + y +
                    '}';
        }
    }
}
