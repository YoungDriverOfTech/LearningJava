package generics;

public class Demo3Impl<T, E> implements Demo1<T, E> {


    @Override
    public T method1(E e) {
        return null;
    }

    // 对某个方法单独使用范型
    public <A> void method2(A config) {

    }

    // 对某个方法单独使用范型，有返回值
    public <A, B> B method3(A config) {
        return null;
    }

}
