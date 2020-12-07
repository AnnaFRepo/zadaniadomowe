package weekend2.homework.VectorExercise;

public class VectorTest {

    public static void main(String[] args) {

        Vector vector1 = new Vector(1, 2, 3);
        vector1.showParameters();
        Vector vector2 = new Vector(3, 2, 1);
        vector2.showParameters();
        Vector vector3 = Vector.add(vector1, vector2);
        vector3.showParameters();
        vector3.add(5, 2, 0.5);
        vector3.showParameters();
        vector1.setX(0);
        vector1.showParameters();
    }
}
