package reflection.invoke;

public class MyClass {
    private int count;

    public MyClass() {

    }

    public MyClass(int count) {
        this.count = count;
    }

    public void doNothing() {
        System.out.println("do nothing");
    }

    public int sum(int num1, String strNum) {
        return num1 + Integer.parseInt(strNum) + this.count;
    }

    private String sum(String num1, String num2) {
        return "" + Integer.parseInt(num1) + Integer.parseInt(num2) + this.count;
    }

    private static String sum(String str) {
        return str + "world";
    }
}
