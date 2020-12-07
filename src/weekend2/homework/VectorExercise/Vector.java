package weekend2.homework.VectorExercise;

public class Vector {

//    5. Stwórz klasę opisująca wektor o nazwie Vector. Klasa powinna zawierać:
//    a) trzy pola prywatne x,y,z opisujące wektor
//    b) konstruktor pozwalający na ustawienie wartości współrzędnych
//    c) metodę statyczną add zwracająca nowy wektor i pozwalająca zsumować wartości
//    dwóch wektorów podanych jako argumenty
//    d) metodę niestatyczną add pozwalającą dodać wartosci do istniejacego wektora
//    e) gettery i settery dla poszczególnych pól
//    f) metodę showParameters wyświetlająca parametry
//    g) przetestuj funkcjonalność napisanej klasy w głównej klasie

    private double x;
    private double y;
    private double z;

    public Vector() {
    }

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vector add(Vector v1, Vector v2) {
        Vector v3 = new Vector();
        v3.x = v1.x + v2.x;
        v3.y = v1.y + v2.y;
        v3.z = v1.z + v2.z;
        return v3;
    }

    public void add(double x, double y, double z) {
        this.x += x;
        this.y += y;
        this.z += z;
    }

    public void showParameters() {
        System.out.println("Vector parameters:");
        System.out.println("x: " + x + ", y: " + y + ", z: " + z);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
