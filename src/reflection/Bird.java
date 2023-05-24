package reflection;

@Markable("main-bird")
public class Bird extends Animal implements Flying, Comparable<Bird>{

    @Markable("age")
    private int age;

    public Bird() {

    }

    private Bird(int age) {
        this.age = age;
    }

    public Bird(String type, int age) {
        super(type);
        this.age = age;
    }

    private boolean canEat(String name) {
        return true;
    }

    public void walk() {
        System.out.println("The bird is walking");
    }

    @Override
    public void fly() {
        System.out.println("The bird is flying quickly");
    }

    @Override
    public int compareTo(Bird o) {
        return this.age - o.age;
    }
}
