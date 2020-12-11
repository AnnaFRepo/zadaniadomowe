package weekend2.homework.peselExercise;

import java.security.SecureRandom;

public enum SexEnum {

    MALE, FEMALE;

    public static SexEnum findSexByNumber(int number) {
        if (number % 2 == 0) {
            return FEMALE;
        } else {
            return MALE;
        }
    }

    public static int returnNumberOfSexInPesel(SexEnum sex) {
        int[] female = new int[]{0, 2, 4, 6, 8};
        int[] male = new int[]{1, 3, 5, 7, 9};
        SecureRandom secureRandom = new SecureRandom();
        int draw = secureRandom.nextInt(5);
        if (SexEnum.FEMALE.equals(sex)) {
            return female[draw];
        } else {
            return male[draw];
        }
    }
}
