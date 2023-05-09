package generics;

public class Demo2Impl<T, E> implements Demo1<T, E> {

    // 实现接口的时候，不确定使用哪一种类型
    @Override
    public T method1(E e) {
        return null;
    }
}
