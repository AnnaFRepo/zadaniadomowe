package weekend2.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.util.Locale;

public class HomeworkWeekend2 {

    private static final String NAME_INTRO = "Imię to ";

    public static void main(String[] args) {

        module1();
    }

    private static void module1() {
//        ex1mod1();
//        ex2mod1();
//        ex3mod1();
        ex6mod1();

    }

    private static void ex6mod1() {

//    6. Stwórz enum o nazwie Size. Powinien posiadać trzy rozmiary: S,M,L.
//    Następnie stwórz metodę showSize, która przyjmować będzie argument będący
//    enumem. Zwróć komunikat o rozmiarze.

        for (SizeEnum size : SizeEnum.values()) {
            SizeEnum.showSize(size);
        }
    }

    private static void ex3mod1() {
//        3. Stwórz metodę losującą liczbę zmiennoprzecinkową od 10 do 15. Następnie spytaj
//        użytkownika o precyzję. Dostępne mają być opcje: 1,2,3,4 miejsca po przecinku. W
//        przypadku podania innej wartości wypisać pierwotnie wylosowaną liczbę. Użyj
//                stałych

        drawAndShowDecimalNumber();
    }

    private static void drawAndShowDecimalNumber() {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        SecureRandom secureRandom = new SecureRandom();
        System.out.println("Program będzie losował liczby i wyświetlał je zgodnie z wybranem stopniem precyzji.");
        System.out.println("Aby zakończyć, wpisz 'exit'");

        while (true) {
            double number = 10.0 + secureRandom.nextInt(5) + secureRandom.nextDouble();

            System.out.println("Wylosowano liczbę. Proszę podać stopień precyzji, z jaką liczba ma zostać wyświetlona:");
            for (AnswerEnum answer : AnswerEnum.values()) {
                System.out.println(answer.getNumber() + " - " + answer.getInfo());
            }

            String userAnswer = "";
            try {
                userAnswer = reader.readLine();
                if ("exit".equalsIgnoreCase(userAnswer)) {
                    break;
                }
            } catch (IOException e) {
                System.out.println(number);
            }

            try {
                int userChoice = Integer.parseInt(userAnswer);
                showFormattedNumber(userChoice, number);

            } catch (NumberFormatException e) {
                System.out.println(number);
            }
        }
    }

    public static void showFormattedNumber(int userChoice, double number) {

        if (AnswerEnum.DECIMAL_1.getNumber() == userChoice) {
            System.out.printf(Locale.ROOT, "%.1f", number).println();
        } else if (AnswerEnum.DECIMAL_2.getNumber() == userChoice) {
            System.out.printf(Locale.ROOT, "%.2f", number).println();
        } else if (AnswerEnum.DECIMAL_3.getNumber() == userChoice) {
            System.out.printf(Locale.ROOT, "%.3f", number).println();
        } else if (AnswerEnum.DECIMAL_4.getNumber() == userChoice) {
            System.out.printf(Locale.ROOT, "%.4f", number).println();
        } else {
            System.out.println(number);
        }
    }

    private static void ex2mod1() {
//        2. Wyświetl następujące imiona wyrównane do prawej krawędzi: Piotrek, Magda,
//                Hubert, Stanisław, Ola. Każde imię posiada poprzedzające zdanie: Imię to

        String[] names = new String[]{"Piotrek", "Magda", "Hubert", "Stanisław", "Ola"};
        for (String name : names) {
            System.out.printf("%35s", NAME_INTRO + name);
            System.out.println();
        }
    }

    private static void ex1mod1() {

//    1. Przepisz następującą pętle for na pętle foreach:
//            for(int i=0;i<array.length;i++){
//        System.out.println(array[i]);
//    }
//    Policz liczbę elementów w następującej pętli
//    String[] arrayOfStrings ={“a”,”b”,”c”};
//    for(String element : arrayOfStrings ){
//        System.out.println(element);
//        }

        String[] array = new String[]{"1a", "2b", "3c", "4d"};
        for (String s : array) {
            System.out.println(s);
        }

        String[] arrayOfStrings = {"a", "b", "c"};
        int counter = 0;
        for (String element : arrayOfStrings) {
            System.out.println(element);
            counter++;
        }
        System.out.println(counter);
    }

}

enum AnswerEnum {
    DECIMAL_1(1, "1 miejsce po przecinku"),
    DECIMAL_2(2, "2 miejsca po przecinku"),
    DECIMAL_3(3, "3 miejsca po przecinku"),
    DECIMAL_4(4, "4 miejsca po przecinku");

    private final int number;
    private final String info;

    AnswerEnum(int number, String info) {
        this.number = number;
        this.info = info;
    }

    public int getNumber() {
        return number;
    }

    public String getInfo() {
        return info;
    }
}
