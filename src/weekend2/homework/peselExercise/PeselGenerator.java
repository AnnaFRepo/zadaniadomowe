package weekend2.homework.peselExercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class PeselGenerator {

//    2. Stwórz klasę PeselGenerator.
//    Program ma kilka trybów:
//            - prosi o podanie daty urodzenia w formacie DD-MM-RRRR oraz o płeć.
//            - prosi o podanie przedziału wiekowego oraz o płeć
//            - prosi o podanie dokładnego wieku oraz o płeć
//            - prosi o podaniu min/max wieku oraz o płeć
//    Pamiętaj o tym że większość cyfr w nr PESEL ma specjalne znaczenie. Pierwsze 6
//    cyfr, a także dwie ostatnie są możliwe do szybkiego ustalenia.

    private static final String WRONG_DATA = "Podano nieprawidłowe dane.";
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static SecureRandom secureRandom = new SecureRandom();
    private String pesel;
    private int[] peselAsArray = new int[11];

    public void generatePeselFromOptions() {
        int userAnswer = askUserForOption();
        switch (userAnswer) {
            case 1:
                LocalDate birthDate = askForBirthDate();
                int year = birthDate.getYear();
                placeYearToPeselArray(year);
                int month = birthDate.getMonthValue();
                int day = birthDate.getDayOfMonth();
                placeMonthAndDayToPeselArray(month, day);
                break;
            case 2:
                DemographicRangeEnum ageRange = askForAgeRange();
                int age = drawAgeFromAgeRange(ageRange);
                year = getThisYear() - age;
                placeYearToPeselArray(year);
                placeMonthAndDayToPeselArray(0, 0);
                break;
            case 3:
                age = askForAge();
                year = getThisYear() - age;
                placeYearToPeselArray(year);
                placeMonthAndDayToPeselArray(0, 0);
                break;
            case 4:
                age = askForMinMaxAge();
                year = getThisYear() - age;
                placeYearToPeselArray(year);
                placeMonthAndDayToPeselArray(0, 0);
                break;
        }
        fillRandomCellsOfPeselArray();
        SexEnum sex = askForSex();
        peselAsArray[9] = SexEnum.returnNumberOfSexInPesel(sex);
        peselAsArray[10] = calculatLastNumber(peselAsArray);
        peselAsString();
        showPesel();
    }

    private int askUserForOption() {
        System.out.println("Rozpoczęto działanie programu PeselGenerator.");
        System.out.println("Proszę wybrać tryb, dla którego ma zostać wygenerowany nr PESEL:");
        System.out.println("1 - podam datę urodzenia w formacie DD-MM-RRRR oraz o płeć");
        System.out.println("2 - podam przedział wiekowy oraz płeć");
        System.out.println("3 - podam dokładny wiek oraz płeć");
        System.out.println("4 - podam min/max wiek oraz płeć");
        int answer = 0;
        while (answer < 1 || answer > 4) {
            try {
                answer = Integer.parseInt(reader.readLine());
            } catch (IOException | NumberFormatException e) {
                System.out.println(WRONG_DATA);
            }
            if (answer < 1 || answer > 4) {
                System.out.println(WRONG_DATA);
            }
        }
        return answer;
    }

    private int askForMinMaxAge() {
        int minAge = -1;
        while (minAge < 0 || minAge > 130) {
            System.out.println("Proszę podać wiek minimalny przedziału:");
            try {
                minAge = Integer.parseInt(reader.readLine());
            } catch (IOException | NumberFormatException e) {
                System.out.println(WRONG_DATA);
            }
            if (minAge < 0 || minAge > 130) {
                System.out.println(WRONG_DATA);
            }

        }
        int maxAge = -1;
        while (maxAge < 0 || maxAge > 130) {
            System.out.println("Proszę podać wiek maksymalny przedziału:");
            try {
                maxAge = Integer.parseInt(reader.readLine());
            } catch (IOException | NumberFormatException e) {
                System.out.println(WRONG_DATA);
            }
            if (maxAge < 0 || maxAge > 130) {
                System.out.println(WRONG_DATA);
            }
        }
        return drawAgeFromAgeRange(minAge, maxAge);
    }

    private DemographicRangeEnum askForAgeRange() {
        DemographicRangeEnum range;
        System.out.println("Proszę podać swój przedział wiekowy:");
        System.out.printf("1 - dziecko (%d-%d lat)", DemographicRangeEnum.CHILDREN.lowerAgeLimit, DemographicRangeEnum.CHILDREN.upperAgeLimit).println();
        System.out.printf("2 - młody dorosły (%d-%d lat)", DemographicRangeEnum.YOUNGER_ADULTS.lowerAgeLimit, DemographicRangeEnum.YOUNGER_ADULTS.upperAgeLimit).println();
        System.out.printf("3 - dorosły (%d-%d lat)", DemographicRangeEnum.ADULTS.lowerAgeLimit, DemographicRangeEnum.ADULTS.upperAgeLimit).println();
        System.out.printf("4 - starszy dorosły (%d-%d lat)", DemographicRangeEnum.THE_ELDERLY.lowerAgeLimit, DemographicRangeEnum.THE_ELDERLY.upperAgeLimit).println();
        int answer = 0;
        while (answer < 1 || answer > 4) {
            try {
                answer = Integer.parseInt(reader.readLine());
            } catch (IOException | NumberFormatException e) {
                System.out.println(WRONG_DATA);
            }
            if (answer < 1 || answer > 4) {
                System.out.println(WRONG_DATA);
            }
        }
        switch (answer) {
            case 1:
                range = DemographicRangeEnum.CHILDREN;
                break;
            case 2:
                range = DemographicRangeEnum.YOUNGER_ADULTS;
                break;
            case 3:
                range = DemographicRangeEnum.ADULTS;
                break;
            default:
                range = DemographicRangeEnum.THE_ELDERLY;
                break;
        }
        return range;
    }

    private SexEnum askForSex() {
        SexEnum s;
        System.out.println("Proszę podać płeć:");
        System.out.println("1 - kobieta");
        System.out.println("2 - mężczyzna");
        int answer = 0;
        while (answer < 1 || answer > 2) {
            try {
                answer = Integer.parseInt(reader.readLine());
            } catch (IOException | NumberFormatException e) {
                System.out.println(WRONG_DATA);
            }
            if (answer < 1 || answer > 2) {
                System.out.println(WRONG_DATA);
            }
        }
        if (answer == 1) {
            s = SexEnum.FEMALE;

        } else {
            s = SexEnum.MALE;
        }
        return s;
    }

    private int askForAge() {
        int age = -1;
        while (age < 0 || age > 130) {
            System.out.println("Proszę podać swój wiek:");
            try {
                age = Integer.parseInt(reader.readLine());
            } catch (IOException | NumberFormatException e) {
                System.out.println(WRONG_DATA);
            }
            if (age < 0 || age > 130) {
                System.out.println(WRONG_DATA);
            }
        }
        return age;
    }

    private LocalDate askForBirthDate() {
        LocalDate date = null;
        while (date == null) {
            System.out.println("Proszę podać właściwą datę urodzenia w formacie DD-MM-RRRR:");
            String line = null;
            try {
                line = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (line == null || line.length() != 10) {
                System.out.println(WRONG_DATA);
                continue;
            }
            try {
                date = LocalDate.parse(line, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            } catch (DateTimeParseException e) {
                System.out.println(WRONG_DATA);
            }
        }
        return date;
    }

    private void fillRandomCellsOfPeselArray() {
        for (int i = 6; i < 9; i++) {
            peselAsArray[i] = secureRandom.nextInt(10);
        }
    }

    private void placeMonthAndDayToPeselArray(int month, int day) {
        if (month == 0) {
            month = 1 + secureRandom.nextInt(12);
        }
        String monthAsString = String.valueOf(month);
        if (monthAsString.length() == 1) {
            peselAsArray[3] = Integer.parseInt(monthAsString);
        } else {
            peselAsArray[2] += Integer.parseInt(String.valueOf(monthAsString.charAt(0)));
            peselAsArray[3] = Integer.parseInt(String.valueOf(monthAsString.charAt(1)));
        }
        if (day == 0) {
            day = 1 + secureRandom.nextInt(28);
        }
        String dayAsString = String.valueOf(day);
        if (dayAsString.length() == 1) {
            peselAsArray[5] = Integer.parseInt(dayAsString);
        } else {
            peselAsArray[4] = Integer.parseInt(String.valueOf(dayAsString.charAt(0)));
            peselAsArray[5] = Integer.parseInt(String.valueOf(dayAsString.charAt(1)));
        }
    }

    private void placeYearToPeselArray(int year) {
        String yearAsString = String.valueOf(year);
        peselAsArray[0] = Integer.parseInt(String.valueOf(yearAsString.charAt(2)));
        peselAsArray[1] = Integer.parseInt(String.valueOf(yearAsString.charAt(3)));
        if (year > 1999) {
            peselAsArray[2] = Integer.parseInt(String.valueOf(yearAsString.charAt(0)));
        }
    }

    private int drawAgeFromAgeRange(int lowerAgeLimit, int upperAgeLimit) {
        return lowerAgeLimit + secureRandom.nextInt(upperAgeLimit - lowerAgeLimit + 1);
    }

    private int drawAgeFromAgeRange(DemographicRangeEnum ageRange) {
        int lowerLimit = ageRange.getLowerAgeLimit();
        int upperAgeLimit = ageRange.getUpperAgeLimit();
        return lowerLimit + secureRandom.nextInt(upperAgeLimit - lowerLimit + 1);
    }

    private int calculatLastNumber(int[] peselAsArray) {
        int sumOfControlNumber = 1 * peselAsArray[0] +
                3 * peselAsArray[1] +
                7 * peselAsArray[2] +
                9 * peselAsArray[3] +
                1 * peselAsArray[4] +
                3 * peselAsArray[5] +
                7 * peselAsArray[6] +
                9 * peselAsArray[7] +
                1 * peselAsArray[8] +
                3 * peselAsArray[9];
        String sumOfControlNumberAsString = String.valueOf(sumOfControlNumber);
        return 10 - Integer.parseInt(String.valueOf(
                sumOfControlNumberAsString.charAt(sumOfControlNumberAsString.length() - 1)));
    }

    private void showPesel() {
        System.out.println("Wygenerowany numer PESEL to: " + pesel);
    }

    private void peselAsString() {
        pesel = String.format("%d%d%d%d%d%d%d%d%d%d%d", peselAsArray[0],
                peselAsArray[1], peselAsArray[2], peselAsArray[3], peselAsArray[4],
                peselAsArray[5], peselAsArray[6], peselAsArray[7], peselAsArray[8],
                peselAsArray[9], peselAsArray[10]);
    }

    private int getThisYear() {
        LocalDate localDate = LocalDate.now();
        return localDate.getYear();
    }
}


