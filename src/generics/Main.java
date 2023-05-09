package generics;

public class Main {
    public static void main(String[] args) {

        // 第一个实现类已经制定了范型类型
        Demo1<String, String> demo1 = new Demo1Impl();

        // 第一个实现类没有指定范型类型，所以可以自己传
        Demo1<Long, Integer> demo2 = new Demo2Impl<>();
    }
}
