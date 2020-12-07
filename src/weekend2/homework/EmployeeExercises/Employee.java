package weekend2.homework.EmployeeExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.SecureRandom;

public class Employee {

    //    3. Stwórz klasę Employee która zawiera:
//    a) prywatne pola opisujące imię i nazwisko pracownika, pole określające jego pensję.
//    b) metoda niestatyczną show która wyświetla tekst: Hello {name} {surname} in our
//    company! Your salary is {salary}.
//    c) dodać metodę statyczną hire, która wczytuje z klawiatury za pomocą klasy
//    Scanner imię i nazwisko i tworzy pracownika zapisując imię w polu name i nazwisko
//    w polu surname. Pensja powinna być wylosowana spośród trzech wartości
//      6000,7000,8000
//    d) wyświetl pracownika

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private String name;
    private String surname;
    private int salary;

    public void show() {
        System.out.println("Hello " + name + " " + surname + " in our company! Your salary is " + salary + " PLN.");
    }

    public static Employee hire() {
        Employee employee = new Employee();
        employee.name = employee.receiveName();
        employee.surname = employee.receiveSurname();
        employee.salary = employee.allocateSalary();
        return employee;
    }

    private String receiveName() {
        String name = "";
        while (name.length() < 2) {
            try {
                System.out.println("Proszę podać imię:");
                name = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (name.charAt(0) != name.toUpperCase().charAt(0)) {
            name = name.replace(name.charAt(0), name.toUpperCase().charAt(0));
        }
        return name;
    }

    private String receiveSurname() {
        String surname = "";
        while (surname.length() < 2) {
            try {
                System.out.println("Proszę podać nazwisko:");
                surname = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (surname.charAt(0) != surname.toUpperCase().charAt(0)) {
            surname = surname.replace(surname.charAt(0), surname.toUpperCase().charAt(0));
        }

        return surname;
    }


    private int allocateSalary() {
        int[] values = new int[]{6000, 7000, 8000};
        SecureRandom secureRandom = new SecureRandom();
        int i = secureRandom.nextInt(3);
        return values[i];
    }
}
