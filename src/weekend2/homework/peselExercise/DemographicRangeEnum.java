package weekend2.homework.peselExercise;

public enum DemographicRangeEnum {

    CHILDREN(0, 17),
    YOUNGER_ADULTS(18, 30),
    ADULTS(31, 64),
    THE_ELDERLY(65, 130);

    int lowerAgeLimit;
    int upperAgeLimit;

    DemographicRangeEnum(int lowerAgeLimit, int upperAgeLimit) {
        this.lowerAgeLimit = lowerAgeLimit;
        this.upperAgeLimit = upperAgeLimit;
    }

    public int getLowerAgeLimit() {
        return lowerAgeLimit;
    }

    public int getUpperAgeLimit() {
        return upperAgeLimit;
    }
}
