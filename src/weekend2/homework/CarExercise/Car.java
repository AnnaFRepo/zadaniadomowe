package weekend2.homework.CarExercise;

public class Car {

//    5. Stwórz klasę Car oraz kilka przykładowych publicznych pol takich jak: obroty
//    silnika, rocznik, marka. Następnie stwórz kilka egzemplarzy tej klasy oraz:
//    a) wyświetl wartości zdefiniowanych przez Ciebie pól w głównej klasie
//    b) zmodyfikuj swój program tak aby pola były prywatne. Dodaj do klasy gettery i
//    settery. Zmodyfikuj w ten sposób wartości a następnie wyświetl je ponownie na
//    ekranie.

    private String brand;
    private int yearOfProduction;
    private int rpm;

    public Car() {
    }

    public Car(String brand, int yearOfProduction, int rpm) {
        this.brand = brand;
        this.yearOfProduction = yearOfProduction;
        this.rpm = rpm;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public int getRpm() {
        return rpm;
    }

    public void setRpm(int rpm) {
        this.rpm = rpm;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand = '" + brand + '\'' +
                ", yearOfProduction = " + yearOfProduction +
                ", rpm = " + rpm +
                '}';
    }
}
