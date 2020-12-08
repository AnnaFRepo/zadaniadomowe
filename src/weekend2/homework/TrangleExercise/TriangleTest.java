package weekend2.homework.TrangleExercise;

public class TriangleTest {

    public static void main(String[] args) {

//        1. Stwórz klasę Point, która:
//        a) zawierać będzie publiczne pola x i y.
//                b) powinna posiadać również metodę statyczna distance z odpowiednimi
//        argumentami pozwalającą na obliczenie odległości między dwoma punktami.
//        Teraz stwórz klasę Triangle, która posiada:
//        a) 3 pola typu Point - współrzędne wierzchołków trójkąta
//        b) 3 pola typu double - długości boków trójkąta.
//        c) dodać do niej konstruktor, który przyjmuje 3 argumenty typu Point i ustawia pola
//        typu Point, a następnie liczy długości boków i na ich podstawie ustawia pola typu
//        double
//        d) dodać do niej metodę, która wypisuje współrzędne punktów oraz długości boków
//        e) dodać do niej metodę (niestatyczną), która liczy obwód na podstawie pól tej klasy
//        f) dodać do niej metodę statyczną, która liczy obwód na podstawie 3 argumentów
//        typu Point
//        g) stworzyć obiekt klasy Triangle reprezentujący trójkąt o trzech punktach
//        h) wywołać metody z pktów d), e) i f)

        Point p1 = new Point(0.0, 1.0);
        Point p2 = new Point(2.0, 3.0);
        Point p3 = new Point(4.0, 5.0);
        Triangle t1 = new Triangle(p1, p2, p3);
        t1.showTriangleInfo();
        double circuitOfTriangle1 = t1.calculateCircuitOfTriangle();
        System.out.println(circuitOfTriangle1);
        double circuitOfTriangle2 = Triangle.calculateCircuitOfTriangle(p1, p2, new Point(1,0));
        System.out.println(circuitOfTriangle2);
    }
}
