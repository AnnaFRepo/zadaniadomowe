package weekend2.homework.GameExercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.SecureRandom;

public class Game {

//    Napisz grę będąca metodą statyczną w klasie Game. Najpierw wylosuj dolny zakres
//    od 0 do 50 i górny zakres od 500 do 1000. Następnie wylosuj liczbę z tego zakresu.
//    Zwróć informację: wylosowałem liczbę z zakresu {dolny zakres} - {górny zakres}.
//    Poproś użytkownika aby zgadł liczbę. Jeśli będzie zbyt niska lub zbyt wysoka -
//    wyświetl wskazówkę : Za mało / Za dużo!
//    Użytkownik ma 5 szans. Jeśli mu się uda wyświetl komunikat: Wygrana! Jeśli
//    przekroczy 5 prób wyświetl: Przegrana. Po każdej próbie jest wyświetlana ilość
//    pozostałych szans.

    private static final String INFO_GUESS = "Zgadnij, jaka liczba została wylosowana (masz %d prób):";
    private static final String INFO_TRY_AGAIN = "Zła odpowiedź! Spróbuj zgadnąć jeszcze raz:";
    private static final String INFO_TOO_SMALL = "Podana liczba jest za mała! ";
    private static final String INFO_TOO_MUCH = "Podana liczba jest za duża! ";
    private static final String INFO_GUESS_AGAIN = "Spróbuj zgadnąć jeszcze raz! Pozostała ilość prób: %d";
    private static final String INFO_WIN = "WYGRANA! Brawo! TAK, wlosowana liczba to %d";
    private static final String INFO_LOST = "Przegrana... Spróbuj ponownie!\nWylosowana liczba to: ";
    private static final String INFO_WRONG_INPUT = "Nie podano liczby lub podano niewłaściwą liczbę, podaj liczbę ponownie:";
    private static final String INFO_OUT_OF_RANGE = "Podano niewłaściwą liczbę - spoza wylosowanego zakresu, podaj liczbę ponownie:";
    private static final int ATTEMPTS = 12;

    private static SecureRandom secureRandom = new SecureRandom();
    private int lowerRange;
    private int highRange;
    private int drawnNumber;

    public Game() {
        this.lowerRange = secureRandom.nextInt(51);
        this.highRange = 500 + secureRandom.nextInt(501);
        this.drawnNumber = lowerRange + secureRandom.nextInt(highRange - lowerRange);
        System.out.printf("Wylosowana została liczba z zakresu od %d do %d\n", lowerRange, highRange);
    }

    public static void play() throws IOException {
        Game game = new Game();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.printf(INFO_GUESS, ATTEMPTS).println();
        int userAnswer = 0;
        int counter = 0;
        while (counter < ATTEMPTS) {
            try {
                userAnswer = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e) {
                System.out.println(INFO_WRONG_INPUT);
                continue;
            }
            if (game.lowerRange > userAnswer || game.highRange < userAnswer) {
                System.out.println(INFO_OUT_OF_RANGE);
            } else {
                if (game.drawnNumber < userAnswer) {
                    counter++;
                    System.out.println(INFO_TOO_MUCH);
                    if (counter != ATTEMPTS) {
                        System.out.printf(INFO_GUESS_AGAIN, (ATTEMPTS - counter)).println();
                    }
                } else if (game.drawnNumber > userAnswer) {
                    counter++;
                    System.out.println(INFO_TOO_SMALL);
                    if (counter != ATTEMPTS) {
                        System.out.printf(INFO_GUESS_AGAIN, (ATTEMPTS - counter)).println();
                    }
                } else {
                    System.out.printf(INFO_WIN, game.drawnNumber).println();
                    break;
                }
            }
            if (counter == ATTEMPTS) {
                System.out.printf(INFO_LOST, game.drawnNumber).println();
            }
        }
    }
}
