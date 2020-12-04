package weekend2.homework;

public enum Size {

    S ("Small size"),
    M ("Medium size"),
    L("Large size"),
    Xl ("Extra large size");

    private String sizeInfo;

    Size(String sizeInfo) {
        this.sizeInfo = sizeInfo;
    }

    public static void showSize(Size size) {
        System.out.println(size + " is a " + size.sizeInfo);
    }

}
