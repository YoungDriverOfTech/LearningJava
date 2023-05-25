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

    public int sum (int num1, String strNum) {
        return num1 + Integer.parseInt(strNum) + this.count;
    }
}
