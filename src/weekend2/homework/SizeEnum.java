package weekend2.homework;

public enum SizeEnum {

    S ("Small size"),
    M ("Medium size"),
    L("Large size"),
    Xl ("Extra large size");

    private String sizeInfo;

    SizeEnum(String sizeInfo) {
        this.sizeInfo = sizeInfo;
    }

    public static void showSize(SizeEnum size) {
        System.out.println(size + " is a " + size.sizeInfo);
    }

}
