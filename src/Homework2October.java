import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Homework2October {

    private static final String APPEND = "append";
    private static final String EXIT = "exit";
    private static final String REVERSE = "reverse";
    private static final String SHOW = "show";
    private static final String ADDITION = "+";
    private static final String SUBTRACTION = "-";
    private static final String MULTIPLICATION = "*";
    private static final String DIVISION = "/";
    private static final String OPENING_BRACKET = "(";
    private static final String CLOSING_BRACKET = ")";

    public static void main(String[] args) {

//        module1();
//        module2();
//        module3();
//        module4();
//        module5();
        module6();

    }

    private static void module6() {
//        ex1mod6();
        ex2mod6();
    }

    private static void ex2mod6() {
//        2. Napisać parser, gdzie użytkownik wpisuje 1 ciąg znaków odpowiadający wyrażeniu
//        matematycznemu, a program liczy jego wartość uwzględniając kolejność
//        wykonywania działań arytmetycznych.

        String infixExpression = receiveMathOperation();
        String postfixExpression = infixToPostfix(infixExpression);
        double result = calculatePostfixExpression(postfixExpression);
        showResults(postfixExpression, result);
    }

    private static void showResults(String postfixExpression, double result) {
        System.out.println("Wprowadzone wyrażenie zamienione do wersji postfiksowej to:");
        System.out.println(postfixExpression);
        System.out.println("Wynik podanego działania matematycznego wynosi:");
        System.out.printf("%.6f", result);
        System.out.println();
    }

    private static double calculatePostfixExpression(String postfixExpression) {

        Deque<Double> stack = new ArrayDeque<>();
        StringTokenizer tokenizer = new StringTokenizer(postfixExpression, " ");

        while (tokenizer.hasMoreTokens()) {
            String nextElement = tokenizer.nextToken();

            if (!ADDITION.equals(nextElement) &&
                    !MULTIPLICATION.equals(nextElement) &&
                    !SUBTRACTION.equals(nextElement) &&
                    !DIVISION.equals(nextElement)) {
                double value = Double.parseDouble(nextElement);
                stack.offerLast(value);
            } else {
                double value1 = stack.removeLast();
                double value2 = stack.removeLast();
                switch (String.valueOf(nextElement.charAt(0))) {
                    case ADDITION:
                        stack.offerLast(value2 + value1);
                        break;
                    case MULTIPLICATION:
                        stack.offerLast(value2 * value1);
                        break;
                    case SUBTRACTION:
                        stack.offerLast(value2 - value1);
                        break;
                    case DIVISION:
                        if (value1 != 0) {
                            stack.offerLast(value2 / value1);
                        } else {
                            System.out.println("Nie dzielimy przez O, łosiu!");
                            System.exit(-2);
                        }
                        break;
                }
            }
        }
        return stack.removeLast();
    }

    private static String infixToPostfix(String infixExpression) {
        StringBuilder postfixExpression = new StringBuilder();
        Deque<String> stack = new ArrayDeque<>();
        StringTokenizer tokenizer = new StringTokenizer(infixExpression, "+-*/()", true);

        while (tokenizer.hasMoreTokens()) {
            String nextElement = tokenizer.nextToken();

            switch (nextElement) {
                case ADDITION:
                case MULTIPLICATION:
                case SUBTRACTION:
                case DIVISION:
                    while (!stack.isEmpty() && signPriority(stack.getLast()) >= signPriority(nextElement)) {
                        postfixExpression.append(stack.removeLast()).append(" ");
                    }
                    stack.offerLast(nextElement);
                    break;
                case OPENING_BRACKET:
                    stack.offerLast(nextElement);
                    break;
                case CLOSING_BRACKET:
                    while (!OPENING_BRACKET.equals(stack.getLast()))  {
                        postfixExpression.append(stack.removeLast()).append(" ");
                    }
                    stack.removeLast();
                    break;
                default:
                    postfixExpression.append(nextElement).append(" ");
                    break;
            }
        }

        while (!stack.isEmpty()) {
            postfixExpression.append(stack.removeLast()).append(" ");
        }

        return postfixExpression.toString();
    }

    private static int signPriority(String sign) {

        if (ADDITION.equals(sign) || SUBTRACTION.equals(sign)) {
            return 1;
        } else if (MULTIPLICATION.equals(sign) || DIVISION.equals(sign)) {
            return 2;
        } else return 0;
    }

    private static String receiveMathOperation() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Proszę wpisać działanie matematyczne do obliczenia:");
        String input = scanner.nextLine();
        input = input.replace(" ", "");
        if (input.contains(",")) {
            input = input.replace(",", ".");
        }
        if (input.contains("=")) {
            input = input.replace("=", "");
        }

        System.out.println("Wprowadzone wyrażenie:");
        System.out.println(input);

        if (input.isEmpty()) {
            System.out.println("Nie wpisano żadnych danych!");
            receiveMathOperation();
        }

        return input;
    }

    private static void ex1mod6() {
//        1. Stworzyć program w którym stworzysz potrzebną ilość metod
//        Ich zadaniem będzie:
//        a) przyjęcie zdania (długie ze spacjami) oraz zwrócenie tablicy Stringów, w
//        którym każdy element będzie oddzielnym wyrazem.
//        b) zmodyfikuj metodę tak aby w zależności od trybu wywołania elementy były
//        posortowane alfabetycznie albo po długości znaków.
//        c) dodaj jeszcze jeden tryb pozwalający na wyświetlanie elementów tablicy w
//        kolejności odwrotnie - alfabetycznej. W każdym wyrazie występują na zmianę
//        małe i duże litery.

        System.out.println(Arrays.toString(
                sentenceToArray()
        ));
    }

    private static String[] sentenceToArray() {

        String[] result = null;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Proszę wpisać długie zdanie:");
        String sentence = scanner.nextLine();
        List<String> sentenceAsArray = new ArrayList<>();
        Pattern pat = Pattern.compile("[\\p{L}]+");
        Matcher mat = pat.matcher(sentence);
        while (mat.find()) {
            sentenceAsArray.add(mat.group());
        }

        printInfo();
        int userChoice = scanner.nextInt();

        switch (userChoice) {
            case 1:
                result = returnSentenceAsArray(sentenceAsArray);
                break;
            case 2:
                result = arraySortedAZ(sentenceAsArray);
                break;
            case 3:
                result = arraySortedByWordsLenght(sentenceAsArray);
                break;
            case 4:
                result = arraySortedZAChangedCaseLetter(sentenceAsArray);
                break;
        }
        return result;
    }

    private static void printInfo() {
        System.out.println("Proszę wybrać opcję wyświetlenia tablicy:");
        System.out.println("1 - Elementy tablicy w kolejności wprowadzania");
        System.out.println("2 - Elementy tablicy posortowane alfabetycznie");
        System.out.println("3 - Elementy tablicy posortowane po długości znaków");
        System.out.println("4 - Elementy tablicy w kolejności odwrotnie - alfabetycznej. W każdym wyrazie  na zmianę małe i duże litery");
    }

    private static String[] arraySortedZAChangedCaseLetter(List<String> sentenceAsArray) {

        String[] result = new String[sentenceAsArray.size()];

        for (int j = 0; j < sentenceAsArray.size(); j++) {
            String tempString = sentenceAsArray.get(j);
            char[] tempCharArray = tempString.toCharArray();
            tempString = String.valueOf(tempCharArray[0]);
            for (int i = 1; i < tempCharArray.length; i++) {
                if (tempCharArray[i - 1] != Character.toUpperCase(tempCharArray[i - 1])) {
                    tempCharArray[i] = Character.toUpperCase(tempCharArray[i]);
                    tempString += tempCharArray[i];
                } else {
                    tempCharArray[i] = Character.toLowerCase(tempCharArray[i]);
                    tempString += tempCharArray[i];
                }
            }
            result[j] = tempString;
        }
        Arrays.sort(result);
        Collections.reverse(Arrays.asList(result));

        return result;
    }

    private static String[] arraySortedByWordsLenght(List<String> sentenceAsArray) {
        sentenceAsArray.sort((a, b) -> (a.length() > b.length() ? 1 : a.length() < b.length() ? -1 : 0));
//        sentenceAsArray.sort((a, b) -> (Integer.compare(a.length(), b.length())));    --> To samo, co powyżej
//        sentenceAsArray.sort(Comparator.comparingInt(String::length));    --> To samo, co powyżej

        return sentenceAsArray.toArray(new String[0]);
    }

    private static String[] arraySortedAZ(List<String> sentenceAsArray) {
        sentenceAsArray.sort(String::compareTo);
        return sentenceAsArray.toArray(new String[0]);
    }

    private static String[] returnSentenceAsArray(List<String> sentenceAsArray) {
        return sentenceAsArray.toArray(new String[0]);
    }

    private static void module5() {
        ex1mod5();
        ex2mod5();
        ex3mod5();
        ex4mod5();
        ex5mod5();
        ex6mod5();
        ex7mod5();
    }

    private static void ex7mod5() {
//   7. Napisz program, który rysuje na konsoli okrąg o promieniu r za pomocą znaku c.
        int r = 6;
        circle2(r);
    }

    private static void circle2(int r) {
        for (int y = -r; y <= r; y++) {
            for (int x = -r; x <= r; x++) {
                if (Math.pow(y, 2) + Math.pow(x, 2) > Math.pow((r + 0.3), 2) || Math.pow(y, 2) + Math.pow(x, 2) < Math.pow((r - 0.3), 2)) {
                    // Metodą prób i błędów musiałam trochę przerobić równanie, żeby dostać okrąg, bo "kwadratowe właściwości" konsoli wyświetliły
                    // trochę za mało elementów, żeby można uznać, że to ładny okrąg, gdy wykorzystywałam w warunku wzór (Math.pow(y, 2) + Math.pow(x, 2) == Math.pow (r, 2))
                    System.out.printf("%3s", "");
                } else {
                    System.out.printf("%3s", "c");
                }
            }
            System.out.println();
        }
    }

    private static void ex6mod5() {
//    6. Napisz program, który rysuje koło o promieniu r za pomocą znaku c.
        int r = 6;
        circle(r);
    }

    private static void circle(int r) {

//        Korzystam ze wzoru na równanie okręgu:
//        (x-a)2+(y-b)2=r2 - okrąg o środku w punkcie (a,b) i promieniu długości r.
//        W moim przykładzie środek to (0, 0), czyli x2+y2=r2
//        Żeby wypełniony był i okrąg i wszystko w środku, przekształcam równanie na: x2+y2<=r2

        for (int y = -r; y <= r; y++) {
            for (int x = -r; x <= r; x++) {
                if (Math.pow(y, 2) + Math.pow(x, 2) <= Math.pow((r + 0.1), 2)) {   // Dodałam do r 0.1, bo wtedy "kwadratura koła" wygląda lepiej (najpierw próbowałam bez, wyglądało gorzej) ;-)
                    System.out.printf("%3s", "c");
                } else {
                    System.out.printf("%3s", "");
                }
            }
            System.out.println();
        }
    }

    private static void ex5mod5() {
//    5. Napisz program, który wypisuje liczby pierwsze z przedziału [1, n].
        int n = 113;
        printPrimeNumbers(n);
    }

    private static void printPrimeNumbers(int n) {
        List<Integer> primeNumbers = new ArrayList<>();
        if (n >= 1) {
            primeNumbers.add(1);
        }
        if (n >= 2) {
            primeNumbers.add(2);
        }
        boolean isPrimeNumber = true;
        int number = 3;
        while (number <= n) {
            for (int i = 2; i < number; i++) {
                if (number % i != 0) {
                    isPrimeNumber = true;
                } else {
                    isPrimeNumber = false;
                    break;
                }
            }
            if (isPrimeNumber) {
                primeNumbers.add(number);
            }
            number++;
        }
        System.out.println("Liczby pierwsze z przedziału od 1 do " + n + ":");
        System.out.println(primeNumbers);
    }

    private static void ex4mod5() {
//    4. Napisz metodę rekurencyjną, która liczy sumę ciągu Fibonacciego.
        int n = 10;
        System.out.print("Suma ciągu Fibonacciego dla liczby " + n + (" wynosi: "));
        System.out.println(fibonacciRecur(n));

    }

    private static int fibonacciRecur(int n) {
        if (n == 2 || n == 3) {
            return 1;
        }
        return fibonacciRecur(n - 1) + fibonacciRecur(n - 2);
    }

    private static void ex3mod5() {
//    3. Napisz metodę rekurencyjną, która liczy silnię przyjmując liczbę n
        int n = 9;
        System.out.print("Silnia dla liczby " + n + (" wynosi: "));
        System.out.println(factorialRecur(n));
    }

    private static int factorialRecur(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return n * factorialRecur(n - 1);
    }

    private static void ex2mod5() {
//        2. Napisz metodę rekurencyjną, która liczy sumę liczb od 1 do n
        int n = 10;
        System.out.print("Suma liczb od 1 do " + n + (" wynosi: "));
        System.out.println(sumRecur(n));
    }

    private static int sumRecur(int n) {
        if (n == 1) {
            return 1;
        }
        return n + sumRecur(n - 1);
    }

    private static void ex1mod5() {
//        Napisz program, który tworzy i wypisze tablicę trójwymiarową - każdy wymiar ma n
//        elementów, zawierającą kolejne liczby naturalne

        int n = 5;
//        create3DTable(n);
        create3DTable2(n);
    }

    private static void create3DTable2(int n) {
        int[][][] table3D = new int[n][n][n];

        for (int i = 0; i < table3D.length; i++) {
            for (int j = 0; j < table3D[i].length; j++) {
                for (int k = 0; k < table3D[i][j].length; k++) {
                    table3D[i][j][k] = k + j * n + i * n * n;
                }
            }
        }
        System.out.println("Tablica trójwymiarowa o wymiarach liczących " + n + " elementów:");
        for (int[][] table2D : table3D) {
            for (int[] table1D : table2D) {
                for (int number : table1D) {
                    System.out.printf("%3s", number);
                    System.out.print(", ");
                }
                System.out.println();
            }
            System.out.println("------------------------");
        }
    }

    private static void create3DTable(int n) {
        int[][][] table3D = new int[n][n][n];
        int counter = 0;

        for (int i = 0; i < table3D.length; i++) {
            for (int j = 0; j < table3D[i].length; j++) {
                for (int k = 0; k < table3D[i][j].length; k++) {
                    table3D[i][j][k] = counter++;
                }
            }
        }
        System.out.println("Tablica trójwymiarowa o wymiarach liczących " + n + " elementów:");
        for (int[][] table2D : table3D) {
            for (int[] table1D : table2D) {
                for (int number : table1D) {
                    System.out.printf("%3s", number);
                    System.out.print(", ");
                }
                System.out.println();
            }
            System.out.println("------------------------");
        }
    }

    private static void module4() {
        ex1mod4();
        ex2mod4();
        ex3mod4();
        ex4mod4();
        ex5mod4();
    }

    private static void ex5mod4() {
//        5. Przerób kalkulator stworzony w poprzedniej pracy domowej. Każda operacja ma
//        swoją metodę. Dodaj operację silni i ręcznie stwórz operację będącą potęgowaniem
//                (liczby typu int)

        calculator();
    }

    private static void calculator() {

        System.out.println("Program kalkulator został uruchomiony.");
        System.out.println("Kalkulator umożliwia wykonanie następujących operacji:");
        System.out.println("+ \t\t dodawanie");
        System.out.println("- \t\t odejmowanie");
        System.out.println("* \t\t mnożenie");
        System.out.println("/ \t\t dzielenie");
        System.out.println("! \t\t obliczanie silni");
        System.out.println("sqr \t obliczanie kwadratu liczby");
        System.out.println("pow \t potęgowanie");
        System.out.println("ce \t\t zerowanie wyniku");
        System.out.println("exit \t wyjście z programu");

        double number = receiveNumber();
        double result = number;
        boolean condition = true;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Wpisz kod wybranej operacji.");
            String operation = scanner.nextLine().toLowerCase();
            switch (operation) {
                case "+":
                    result = sum(number);
                    showResult(result);
                    break;
                case "-":
                    result = subtraction(number);
                    showResult(result);
                    break;
                case "*":
                    result = multiplication(number);
                    showResult(result);
                    break;
                case "/":
                    result = division(number);
                    showResult(result);
                    break;
                case "!":
                    System.out.println("Uwaga! Wybrano operację obliczania silni. Jeśli liczba nie jest liczbą całkowitą, zostanie zaokrąglona do liczby całkowitej.");
                    int intNumber = (int) Math.round(number);
                    result = calculateFactorial(intNumber);
                    showResult(result);
                    break;
                case "sqr":
                    result = Math.pow(number, 2);
                    showResult(result);
                    break;
                case "pow":
                    result = Math.pow(number, receiveNumber());
                    showResult(result);
                    break;
                case "ce":
                    result = 0;
                    showResult(result);
                    break;
                case "exit":
                    System.out.println("Zakończono działanie programu.");
                    condition = false;
            }
            number = result;

        } while (condition);
    }

    private static void showResult(double result) {
        System.out.println(result);
    }

    private static double division(double number) {
        return number / receiveNumber();
    }

    private static double multiplication(double number) {
        return number * receiveNumber();
    }

    private static double subtraction(double number) {
        return number - receiveNumber();
    }

    private static double sum(double number) {
        return number + receiveNumber();
    }

    private static double receiveNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj liczbę.");
        String input = scanner.nextLine();
        double number = 0;
        try {
            number = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Wprowadzono niewłaśniwą wartość.");
            receiveNumber();
        }
        return number;
    }

    private static void ex4mod4() {
//        4. Napisz program, w którym używając pętli oraz instrukcji switch należy stworzyć 2
//        Stringi, jednego będącego komunikatem i jednego będącego operacją.
//        Jeśli: a) operacja będzie równa "append" to stwórz metodę której zadaniem będzie
//        dopisanie do Stringa komunikat kolejnej litera alfabetu
//        b) operacja będzie równa "exit" to program zakończy swoje działanie
//        c) operacja będzie równa "reverse" to program odwróci kolejność wszystkich liter w
//        Stringu komunikat. Stwórz metodę która realizuje to zadanie
//        d) operacja będzie równa "show" to program wyświetli na ekranie Stringa komunikat.
//                Stwórz do tego zadania metodę
//        e) do komunikatu wpiszą się wszystkie litery alfabetu, program pyta czy go
//        zakończyć. Jeśli tak to program kończy działanie, jeśli nie to do komunikatu dopisują
//        się litery alfabetu począwszy ponownie od litery a
//        Wskazówka: zamiast String możesz użyć StringBuilder (poszukać w dokumentacji)

        messageOperationMethod();
    }

    private static void messageOperationMethod() {

        String message = "";

        Scanner scanner = new Scanner(System.in);

        System.out.println("Wpisz wybrane działanie:");
        System.out.println("append");
        System.out.println("exit");
        System.out.println("reverse");
        System.out.println("show");

        String operation = scanner.nextLine().toLowerCase();

        while (!operation.equalsIgnoreCase(EXIT)) {
            switch (operation) {

                case APPEND:
                    message = addNextLetter(message);
                    System.out.println("Wybierz kolejne działanie:");
                    operation = scanner.nextLine().toLowerCase();
                    break;

                case REVERSE:
                    message = reverseMessage(message);
                    System.out.println("Wybierz kolejne działanie:");
                    operation = scanner.nextLine().toLowerCase();
                    break;

                case SHOW:
                    show(message);
                    System.out.println("Wybierz kolejne działanie:");
                    operation = scanner.nextLine().toLowerCase();
                    break;

                default:
                    message = addAlphabet(message);

                    System.out.println("Do wiadomości został dodany alfabet. Czy chcesz kontynuować wypełnianie?");
                    System.out.println("yes");
                    System.out.println("no");
                    System.out.println("exit");
                    operation = scanner.nextLine().toLowerCase();

                    if (operation.equalsIgnoreCase("no")) {
                        System.out.println("Wybierz kolejne działanie:");
                        System.out.println("append");
                        System.out.println("exit");
                        System.out.println("reverse");
                        System.out.println("show");
                        System.out.println("fill");
                        operation = scanner.nextLine().toLowerCase();

                    } else if (operation.equalsIgnoreCase("exit")) {
                        operation = EXIT;
                    }
            }
        }
    }

    private static String addAlphabet(String message) {
        StringBuilder stringBuilder = new StringBuilder(message);
        char a = 'a';
        while (a <= 'z') {
            stringBuilder.append(a++);
        }
        message = stringBuilder.toString();
        return message;
    }

    private static void show(String message) {
        System.out.println(message);
    }

    private static String reverseMessage(String message) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            stringBuilder.append(message.charAt(message.length() - i - 1));
        }
        message = stringBuilder.toString();
        return message;
    }

    private static String addNextLetter(String message) {
        StringBuilder stringBuilder = new StringBuilder(message);
        char lastChar = 'a';
        if (message.length() == 0) {
            stringBuilder.append(lastChar);
        } else {
            lastChar = message.charAt(message.length() - 1);
            if (lastChar != 'z') {
                ++lastChar;
                stringBuilder.append(lastChar);
            } else {
                lastChar = 'a';
                stringBuilder.append(lastChar);
            }
        }
        message = stringBuilder.toString();
        return message;
    }

    private static void ex3mod4() {
//        3. Ciąg geometryczny - to taki ciąg liczb, w którym każda kolejna liczba różni się od
//        poprzedniej q razy. Liczbę q (q: double, q != 0) nazywamy ilorazem ciągu
//        geometrycznego. Przykład: 1, 2, 4, 8, 16; q = 2.
//        a) Napisać metodę, która przyjmuje 5 argumentów i sprawdza czy tworzą one ciąg
//        geometryczny. Jeżeli tak, proszę wypisać iloraz q. Zakładamy, że kolejność
//        podawanych argumentów jest zgodna z kolejnością wyrazów ciągu.

        receiveQFromGeomSeq(1, 3, 9, 18, 27);

//        b) Napisać metodę, która przyjmuje 5 argumentów i sprawdza czy mogą one
//        utworzyć ciąg geometryczny. Jeżeli tak, proszę wypisać iloraz q oraz wyraz
//        początkowy. Kolejność podawanych argumentów nie musi być zgodna z kolejnością
//        wyrazów ciągu.

        checkIfSeqIsGeom(27, 1, 9, 3, 81);

//        c) Wykorzystując pętle (for, while lub do-while) napisać metodę, która przyjmuje 3
//        argumenty (wyraz początkowy, q, n) i zwraca n-ty wyraz ciągu geometrycznego (n:
//        int, n > 0).

        returnNOfGeomSeq(2.0, 4.0, 5);

//        d) Wykorzystując pętle (for, while lub do-while) napisać metodę, która przyjmuje 3
//        argumenty (wyraz początkowy, q, n) i zwraca sumę n (n: int, n > 0) wyrazów ciągu
//        geometrycznego

        returnSumOfGeomSeq(1, 2, 8);
    }

    private static double returnSumOfGeomSeq(double a, double q, int n) {
        double valueOfN = a;
        double sum = a;
        System.out.println("Dla ciągu o wyrazie początkowym: " + a + ", ilorazie: " + q + ", liczącym " + n + " wyrazów");
        for (int j = 1; j < n; j++) {
            valueOfN *= q;
            sum += valueOfN;
            System.out.println(j + 1 + " wyraz ciągu to: " + valueOfN + ", suma wyrazów: " + sum);
        }
        return sum;
    }

    private static double returnNOfGeomSeq(double a, double q, int n) {
        double valueOfN = a;
        System.out.println("Dla ciągu o wyrazie początkowym: " + a + ", ilorazie: " + q + ", liczącym " + n + " wyrazów");
        for (int j = 1; j < n; j++) {
            valueOfN *= q;
            System.out.println(j + 1 + " wyraz ciągu to: " + valueOfN);
        }
        return valueOfN;
    }

    private static void checkIfSeqIsGeom(double a1, double a2, double a3, double a4, double a5) {
        double[] numbers = new double[]{a1, a2, a3, a4, a5};
        Arrays.sort(numbers);
        receiveQFromGeomSeq(numbers);
        System.out.println("Wyraz początkowy to: " + numbers[0]);
    }

    private static void receiveQFromGeomSeq(double... numbers) {   // Wiem, że w treści jest, że przyjmuje 5 args. W metodzie checkIfSeqIsGeom trzymam się polecenia, a tu chciałam zrobić elastyczniej ;-)
        double q = numbers[numbers.length - 1] / numbers[numbers.length - 2];
        boolean condition = true;
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i + 1] / numbers[i] != q) {
                System.out.println("Podane liczby NIE tworzą ciągu geometrycznego");
                condition = false;
                break;
            }
        }
        if (condition) {
            System.out.println("Podane liczby tworzą ciąg geometryczny");
            System.out.println("Iloraz q wynosi: " + q);
        }
    }

    private static void ex2mod4() {
//        2. Napisać metodę, która przyjmuje 1 argument, będący tablicą liczb
//        zmiennoprzecinkowych, oraz zwraca tablicę liczb wejściowych posortowanych wg
//        wartości rosnąco.
//        Dla chętnych: zwróć uwagę na złożoność obliczeniową

        double[] tableOfDoubles = new double[]{1.1, 0.9, 15.5, -8.1, -0.5};
        System.out.println(Arrays.toString(tableOfDoubles));
        sortTableOfDoubles(tableOfDoubles);
        System.out.println(Arrays.toString(tableOfDoubles));
    }

    private static double[] sortTableOfDoubles(double[] tableOfDoubles) {
        Arrays.sort(tableOfDoubles);
        return tableOfDoubles;
    }

    private static void ex1mod4() {
//        1. Napisz metodę, która przyjmie argument będący ilością elementów w tablicy.
//                Następnie zwróci tablicę typu int wypełnioną wybranymi przez Ciebie różnymi od
//        siebie wartościami. Następnie napisz kolejną metodę pozwalającą wypisać
//        najmniejszy i największy element tablicy

        int qtyOfElements = 6;
        int[] tab = tabOfInts(qtyOfElements);
        System.out.println(Arrays.toString(tab));
        printMinAndMaxOfTable1(tab);
        printMinAndMaxOfTable2(tab);
    }

    private static void printMinAndMaxOfTable1(int[] tab) {
        Arrays.sort(tab);
        int min = tab[0];
        int max = tab[tab.length - 1];
        System.out.println("Najmniejszy element tablicy to: " + min);
        System.out.println("Największy element tablicy to: " + max);
    }

    private static void printMinAndMaxOfTable2(int[] tab) {
        int min = Integer.MAX_VALUE;
        for (int integer : tab) {
            if (integer < min) {
                min = integer;
            }
        }
        int max = Integer.MIN_VALUE;
        for (int integer : tab) {
            if (integer > max) {
                max = integer;
            }
        }
        System.out.println("Najmniejszy element tablicy to: " + min);
        System.out.println("Największy element tablicy to: " + max);
    }

    private static int[] tabOfInts(int qtyOfElements) {
        int[] numbers = new int[qtyOfElements];
        Random random = new Random();
        int counter = 0;

        while (counter < qtyOfElements) {
            int nextValue = random.nextInt();
            if (!contains(numbers, nextValue)) {
                numbers[counter] = nextValue;
                counter++;
            }
        }
        return numbers;
    }

    private static boolean contains(int[] numbers, int nextValue) {
        for (int integer : numbers) {
            if (integer == nextValue) {
                return true;
            }
        }
        return false;
    }


    private static void module3() {
        ex1mod3();
        ex2mod3();
        ex3mod3();
        ex4mod3();
        ex5mod3();
        ex6mod3();
    }

    private static void ex6mod3() {
//        6. Napisz metodę, która przyjmuje nieokreśloną liczbę argumentów typu int.
//                Zwróć średnią arytmetyczną.

        countAverage(5, 9, 4, 0, 15);
    }

    private static double countAverage(int... values) {
        double average = 0;

        int count = 0;
        int sum = 0;

        for (int i = 0; i < values.length; i++) {
            sum += values[i];
            count++;
        }
        average = (double) sum / count;
        System.out.println(Arrays.toString(values));
        System.out.println("Ilość podanych liczb: " + count + ", średnia: " + average);

        return average;
    }

    private static void ex5mod3() {
//        Napisz metodę, która pobiera n i wyświetla tablicę o n elementach. Elementy w
//        tablicy powinny spełniać równanie tab[i] = iEi
//        np. n = 5
//        Output: [0, 10, 200, 3000, 40000]

        int n = 10;
        System.out.println(Arrays.toString(makeTabFromN(n)));
    }

    private static double[] makeTabFromN(int n) {
        double[] tab = new double[n];
        for (int i = 0; i < tab.length; i++) {
            tab[i] = i * Math.pow(10, i);
        }
        return tab;
    }

    private static void ex4mod3() {
//        4. Napisz metodę, która liczy za pomocą pętli sumę ciągu arytmetycznego n-wyrazów o
//        wyrazie początkowym a, z krokiem k (a, a+k, a+k+k, a+3k, …, a+(n-1)k)
        double a = 2;
        double n = 14;
        double k = 3;

        System.out.println(arithmeticSum(a, n, k));
    }

    private static double arithmeticSum(double a, double n, double k) {
        double sum = a;
        double next = a;
        System.out.println("Wyraz " + 1 + " | liczba: " + next + " | suma: " + sum);

        for (int i = 1; i < n; i++) {
            next += k;
            sum += next;
            System.out.println("Wyraz " + (i + 1) + " | liczba: " + next + " | suma: " + sum);
        }
        return sum;
    }

    private static void ex3mod3() {
//        3. Napisz program który wyświetli na ekranie choinkę o zadanej wysokości n za
//        pomocą znaku *
//        Dla chętnych: choinka powinna posiadać pieniek oraz gwiazdkę o znaku @. Pieniek
//        nie wlicza się w wysokość choinki

        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj wysokość choinki:");
        int n = scanner.nextInt();
        christmasTreeDraft1(n);
        christmasTreeDraft2(n);
    }

    private static void christmasTreeDraft2(int n) {
        for (int i = 0; i < n; i++) {
            for (int k = n - 2 - i; k >= 0; k--) {
                System.out.print(" ");
            }
            for (int j = 1; j < i + i + 2; j++) {
                if (j == 1 && i == 0) {
                    System.out.print("@");
                } else {
                    System.out.print("*");
                }
            }
            System.out.println();
        }
        for (int l = n - 2; l >= 0; l--) {
            System.out.print(" ");
        }
        System.out.println("*");
        System.out.println();
    }

    private static void christmasTreeDraft1(int n) {
        for (int i = 0; i < n; i++) {
            for (int k = n - 2 - i; k >= 0; k--) {
                System.out.print(" ");
            }
            for (int j = 1; j < i + i + 2; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void ex2mod3() {
//        2. Napisz program który wyświetli na ekranie kwadrat o zadanym boku n
//        Output: n=4

        int n = 4;
        squareDraft(n);
    }

    private static void squareDraft(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print("*  ");
            if (i == n - 1) {
                System.out.println();
            }
        }
        for (int i = 1; i < n - 1; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0 || j == n - 1) {
                    System.out.print("*  ");
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }
        for (int i = 0; i < n; i++) {
            System.out.print("*  ");
            if (i == n - 1) {
                System.out.println();
            }
        }
    }

    private static void ex1mod3() {
//        Niech int i = 0, j = 0; Jakie wartości przyjmują zmienne i oraz j po wykonaniu
//        poniższych instrukcji? Uzasadnij.
//        a) j = (i < j && i++ < ++j) ? ++i : i++;      // i = 1, j = 0 -> Warunek nieprawdziwy, więc zwiększenie i przez postinkrementację, j bez zmian, bo przypisano mu i zanim zaszła inkrementacja
//        b) j = (i < j || i++ < ++j) ? ++i : i++;      // i = 2, j = 2 -> Warunek zawsze prawdziwy, zwiększenie i i przypid=sanie go do j, potem jeszcze raz przypisanie zwiększonego i do j
//                                                                                      if (i >= j) {
        //            i++;
        //            ++j;
        //        }
        //        j = ++i;
//        c) j = j++;                                   // i = 0, j = 0 -> i bez zmian, j też, bo do j przypisane j przed postinkrementacją
//        d) j += j++;                                  // i = 0, j = 0 -> i bez zmian, j też, bo do j przypisane j przed postinkrementacją, a dodawanie 0 do 0 daje 0
//        e) j += (i < j || i++ < ++j) ? ++i : i++;     // i = 2, j = 2 -> jak w pkt b) tylko dodawanie do 0, więc nie zmienia to wyniku
//        f) j = --i | -i++ + + + ++j | i--;            // i = -1, j = -1 -> Zmniejszenie i i przypisanie jego zmniejszonej wartości do j
    }

    private static void module2() {
        ex1mod2();
        ex2mod2();
        ex3mod2();
        ex4mod2();
        ex5mod2();
    }

    private static void ex5mod2() {
//        5. Napisz program, w którym:
//        a) stworzysz zmienną int i nadasz jej wartość pobraną od użytkownika
//        b) narysujesz tabliczkę mnożenia od 1 do wartości z a).
        Scanner scanner = new Scanner(System.in);
        System.out.println("Proszę podać dowolną liczbę całkowitą, do której wyświetlona zostanie tabliczka mnożenia");
        int inputNumber = scanner.nextInt();
        for (int i = 1; i <= inputNumber; i++) {
            for (int j = 1; j <= inputNumber; j++) {
                System.out.print(i * j + "\t");
            }
            System.out.println();
        }
    }

    private static void ex4mod2() {
//        4. Napisz program liczący silnię dla n wskazanego przez użytkownika
        Scanner scanner = new Scanner(System.in);
        System.out.println("Proszę podać liczbę całkowitą, dla której zostanie obliczona silnia");
        int inputN = scanner.nextInt();
        int factorialForInputN = calculateFactorial(inputN);
        System.out.println("Silnia dla liczby " + inputN + " wynosi " + factorialForInputN);
    }

    private static int calculateFactorial(int inputN) {

        if (inputN <= 0) {
            return 0;
        }
        int result = 1;
        for (int i = 1; i <= inputN; i++) {
            result = result * i;
        }
        return result;
    }

    private static void ex3mod2() {
//        3. Napisz program wypisujący liczby od 100 do -25, co 25.
//        Output: 100,75,50,25,0,-25
//        a) używając pętli while
        int number = 100;
        while (number >= -25) {
            System.out.print(number);
            if (number != -25) {
                System.out.print(", ");
            } else {
                System.out.println();
            }
            number -= 25;
        }

//        b) używając pętli do-while
        number = 100;
        do {
            System.out.print(number);
            if (number != -25) {
                System.out.print(", ");
            } else {
                System.out.println();
            }
            number -= 25;
        } while (number >= -25);

//        c) używając pętli for
        for (int i = 100; i >= -25; i -= 25) {
            System.out.print(i);
            if (i != -25) {
                System.out.print(", ");
            } else {
                System.out.println();
            }
        }
    }

    private static void ex2mod2() {
//        2. Napisz program który:
//        a) za pomocą pętli wyświetli na ekranie kolejne liczby naturalne od 1 do 10
//        b) dla liczb podzielnych przez 2 wyświetli stosowny komunikat
//        c) wyświetli na ekranie sumę liczb podzielnych przez 3

        int loopLimit = 10;
        int sum = 0;
        for (int i = 1; i <= loopLimit; i++) {
            System.out.print(i);
            if (i % 2 == 0) {
                System.out.print(" (liczba jest podzielna przez 2)");
            }
            if (i % 3 == 0) {
                sum += i;
            }
            System.out.println();
        }
        System.out.println("Suma liczb podzielnych przez 3 wynosi: " + sum);
    }

    private static void ex1mod2() {
//        1. Stworzyć program w którym:
//        a) zadeklarujesz 3 zmienne String oraz zmienna int ( zmienna int ma mieć
//        wartość zainicjalizowaną)
//        b) wartość String nr 3 zostanie zainicjalizowana poprzez wybór użytkownika
//        c) wartość String nr 1 zostanie zainicjalizowana z użyciem operatora
//        trójargumentowego w taki sposób, że jeśli wartość String nr 2(zdefiniowanego
//        na sztywno) jest równa wyrazowi “indeks” to zostanie zwrócony napis “ Trzeci
//        znak wyrazu (String nr3) to (znak jaki stoi na 3 indeksie String nr3)”. W
//        przeciwnym razie sprawdz czy wartość zmiennej int z a) jest większa niż 5 lub
//        mniejsza od -5, jesli tak, to wypisz napis: Jest ok. W innym przypadku: Jest
//        dalej w porządku
//        Do rozwiązania nie używaj instrukcji warunkowej if, else if, else w postaci if(warunek
//        ){} itd.
        String string1;
        final String string2;
        String string3;
        int number = 9;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Proszę podać dowolny wyraz");
        string3 = scanner.nextLine();
        string2 = "index";
        string1 = string2.equals("indeks") ? "Trzeci znak wyrazu " + string3 + " to " + string3.charAt(3 - 1) : (number > 5 || number < -5) ? "Jest ok" : "Jest dalej w porządku";
        System.out.println(string1);
    }

    private static void module1() {

        ex1mod1();
        ex2mod1();
        ex3mod1();
        ex4mod1();
        ex5mod1();
    }

    private static void ex5mod1() {
//        5. Napisz program wypisujący elementy tablicy arr={1,5,2,7,3} począwszy od końca za
//        pomocą pętli
        int[] arr = {1, 5, 2, 7, 3};

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[arr.length - 1 - i]);
            if (i != arr.length - 1) {
                System.out.print(", ");
            } else {
                System.out.println();
            }
        }
    }

    private static void ex4mod1() {
//        4. Stworzyć program, w którym stworzysz tablicę jednowymiarową o ilości elementów
//        wskazanej przez użytkownika. Jeśli liczba elementów jest większa o 3 to wyświetl cała
//        tablicę za pomocą jednej instrukcji. W przeciwnym razie wyświetl pierwszy element tablicy
//        oraz ostatni. Program ma być uniwersalny dla tablic różnej długości

        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj rozmiar tablicy");
        int tableSize = scanner.nextInt();
        Object[] table = new Object[tableSize];

        if (tableSize > 3) {
            System.out.println(Arrays.toString(table));
        } else {
            System.out.println("Pierwszy element tablicy to: " + table[0] + ", ostatni element tablicy to: " + table[table.length - 1]);
        }
    }

    private static void ex3mod1() {
//        3. Stworzyć program, w którym:
//        a) stworzysz zmienna String i dasz jej wartość od użytkownika
//        b) w zależności od trybu:
//        a) wyświetlisz tylko wielkimi literami napis: ----(zmienna)----
//        b) wyświetlisz tylko małymi literami napis: ****(zmienna)****

        Scanner scanner = new Scanner(System.in);
        System.out.println("Proszę podać dowolny tekst");
        String inputText = scanner.nextLine();
        System.out.println("Podany tekst to: " + inputText);
        System.out.println("Proszę wybrać odpowiednią operację na tekście, wpisując wybrany numer działania:");
        System.out.println("1 - by wyświetlić: \n ----TWÓJ TEKST----");
        System.out.println("2 - by wyświetlić: \n ****twój tekst****");

        int inputValue = scanner.nextInt();

        switch (inputValue) {
            case 1:
                System.out.println("----" + inputText.toUpperCase() + "----");
                break;
            case 2:
                System.out.println("****" + inputText.toLowerCase() + "****");
                break;
            default:
                System.out.println("Nie wybrałeś właściwej odpowiedzi, nie zostaną dokonane żadne akcje.");
        }
    }

    private static void ex2mod1() {

//        2. Stworzyć program, w którym:
//        a) stworzysz zmienna String z jakąś wartością
//        b) w zależności od wybranego przez użytkownika trybu :
//        a) poprosić o dodatkową wartość i wyświetlić wartość zmiennej String z indeksem
//        począwszy od podanej wartości
//        b) poprosić o dodatkową wartość i wyświetlić wartość 3 znaki ze zmiennej String z
//        indeksem począwszym od wartości zadanej przez użytkownika
//        c) nie prosić użytkownika o żadną zmienną a jedynie sprawdzić czy zmienna String
//        zaczyna się od znaków “abc”. Jeśli nie to dodatkowo sprawdzić czy kończy się
//        kropką. Jeśli żaden z tych warunków nie jest spełniony wyświetlić komunikat.

        String text = "AbCdEfGhIjKlMn";

        Scanner scanner = new Scanner(System.in);
        System.out.println("Program wykona operacje na tekście - zmiennej String. " +
                "\nProszę wybrać dalsze działanie, wpisując numer wybranego działania");
        System.out.println("1. Proszę podać dodatkową wartość by wyświetlić wartość zmiennej String z indeksem\n" +
                "począwszy od podanej wartości");
        System.out.println("2. Proszę podać dodatkową wartość by wyświetlić wartość 3 znaki ze zmiennej String z\n" +
                "indeksem począwszym od wartości zadanej");
        System.out.println("3. Proszę sprawdzić czy zmienna String\n" +
                "zaczyna się od znaków “abc”. Jeśli nie to dodatkowo sprawdzić czy kończy się\n" +
                "kropką.");

        int inputAction = scanner.nextInt();

        switch (inputAction) {
            case 1:
                System.out.println("Proszę podać dowolną liczbę od 0 do " + (text.length() - 1));
                int inputValue1 = scanner.nextInt();
                String case1answer = text.substring(inputValue1);
                System.out.println(case1answer);
                break;
            case 2:
                System.out.println("Proszę podać dowolną liczbę od 0 do " + (text.length() - 4));
                int inputValue2 = scanner.nextInt();
                String case2answer = text.substring(inputValue2, inputValue2 + 3);
                System.out.println(case2answer);
                break;
            case 3:
                if (text.toLowerCase().startsWith("abc".toLowerCase())) {
                    System.out.println("Tekst zaczyna się od liter 'abc'");
                } else if (text.endsWith(".")) {
                    System.out.println("Tekst kończy się kropką");
                } else {
                    System.out.println("Tekst nie zaczyna się od liter 'abc', ani nie kończy się kropką.");
                }
                break;

            default:
                System.out.println("Wybrano niewłaściwą akcję.");

        }
    }

    private static void ex1mod1() {
//        1. Stwórz program, w którym:
//        a) zadeklarujesz zmienna String
//        b) zadeklarujesz dwie zmienne typu int których wartość ma zostać pobrana od
//                użytkownika
//        c) wartość zmiennej z a) zostanie zainicjalizowana z użyciem operatora
//        trójargumentowego napisem określającym relacje między dwoma
//        wprowadzonymi liczbami

        int greaterNumber;
        int smallerNumber;
        String information;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Proszę podać pierwszą liczbę całkowitą");
        int inputNumber1 = scanner.nextInt();
        System.out.println("Proszę podać drugą liczbę całkowitą");
        int inputNumber2 = scanner.nextInt();

        information = inputNumber1 > inputNumber2 ? "Liczba " + inputNumber1 + " jest większa od liczby " + inputNumber2 : (inputNumber2 > inputNumber1) ? "Liczba " + inputNumber2 + " jest większa od liczby " + inputNumber1 : "Liczby są równe";
        System.out.println(information);
    }
}
