package generics;

import java.util.ArrayList;
import java.util.List;

public class Main {

    /**
     *  范型使用
     */
    public static void main(String[] args) {

        // 第一个实现类已经制定了范型类型
        Demo1<String, String> demo1 = new Demo1Impl();

        // 第一个实现类没有指定范型类型，所以可以自己传
        Demo1<Long, Integer> demo2 = new Demo2Impl<>();

        ArrayList<String> list = new ArrayList<>();

    }

    /**
     * Java范型实现的时候面临的问题
     *
     * 使用list的时候，明明传入了范型，但是初始化的时候却使用了Object，为什么呢？
     * private static final Object[] EMPTY_ELEMENTDATA = {};
     *
     * 原因： 1 后向兼容：还需要支持原来无范型的容器
     *       2 不得不这样做：二进制后向兼容性是明确写入java语言规范的严肃承诺。JDK1编译出来的class文件，jdk19也要支持
     * 面临的选择： 1 需要范型话的容器类型，以前的保持不变，新写一套支持范型话的代码 - C#的选择，因为C#当时很年轻，很容易转型
     *            2 直接把已经有的类型范型化（类型擦除） - java的选择，java已经有十多年了，无法重构
     */
    public void demo1() {
        // 类型擦除 Type erasure
        List<String> list = new ArrayList<>();

        // 原本这句是会被检查住的，但是这种检查只存在编译的阶段，等编译成子节码以后代码就变成了 List list，类型被擦掉了.
        // 也正因为这样高版本的java，可以运行低版本java编译出来的子节码
        // 查看子节码，无论 List<T> list 这个T是什么，都会被擦掉变成 List list
        // list.add(11);
    }
}
