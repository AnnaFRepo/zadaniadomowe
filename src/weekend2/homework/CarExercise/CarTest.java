package weekend2.homework.CarExercise;

public class CarTest {

    public static void main(String[] args) {

        Car car1 = new Car();
        car1.setBrand("BMW");
        car1.setYearOfProduction(1980);
        car1.setRpm(750);
        System.out.println(car1);

        Car car2 = new Car("Volvo", 1999, 650);
        System.out.println(car2.getBrand());
        System.out.println(car2.getYearOfProduction());
        System.out.println(car2.getRpm());
        System.out.println(car2.toString());
    }
}
