package reflection.invoke;

import java.lang.reflect.Constructor;

public class ConstructorInvocationDemo {


    public static void main(String[] args) throws Exception{
        // 动态调用构造器初始化对象（可以调用有参数的构造器）
        Class personClass = Person.class;

        // 1. 获取构造器
        Constructor constructor = personClass.getDeclaredConstructor(String.class, int.class);

        // 2. 设置可方法属性
        constructor.setAccessible(true);

        // 3. 初始化对象，传入的参数要和构造器对应
        Person obj = (Person) constructor.newInstance("wahaha", 1000);
        System.out.println("obj = " + obj);
    }
}
