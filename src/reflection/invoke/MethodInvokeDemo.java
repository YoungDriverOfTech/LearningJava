package reflection.invoke;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodInvokeDemo {


    public static void main(String[] args) throws Exception {
        /**
         * 动态调用public无参数，无返回值的方法
         */
        // 1. 获取目标类的class对象
        MyClass obj = new MyClass(10);
        Class clazz = obj.getClass();

        // 2. 获取目标方法Method对象
        Method doNothingMethod = clazz.getMethod("doNothing");

        // 3. 调用method中的invoke方法
        doNothingMethod.invoke(obj);


        /**
         * 动态调用public有参数，有返回值的方法
         */
        // 1. 获取目标类的class对象
        // 2. 获取目标方法Method对象
        Method sumMethod = clazz.getMethod("sum", int.class, String.class);

        // 3. 调用method中的invoke方法
        Object result = sumMethod.invoke(obj, 20, "20");
        System.out.println("result = " + result);


        /**
         * 动态调用private有参数，有返回值的方法
         */
        // 1. 获取目标类的class对象
        MyClass obj2 = new MyClass(10);
        Class clazz2 = obj2.getClass();

        // 2. 获取目标方法Method对象
        Method sumMethod2 = clazz2.getDeclaredMethod("sum", String.class, String.class);

        // 3. 设置可访问flag
        sumMethod2.setAccessible(true);

        // 4. 调用invoke方法
        Object result2 = sumMethod2.invoke(obj2, "1", "2");
        System.out.println("result2 = " + result2);


        /**
         * 动态调用静态方法
         */
        // 1. 获取目标类的class对象
        MyClass obj3 = new MyClass(10);
        Class clazz3 = obj3.getClass();

        // 2. 获取目标方法Method对象
        Method sumMethod3 = clazz3.getDeclaredMethod("sum", String.class);

        // 3. 设置可访问flag
        sumMethod3.setAccessible(true);

        // 4. 调用invoke方法
        // Object result3 = sumMethod3.invoke(obj3, "1"); // 传对象也行，因为对象也能调用静态方法
        Object result3 = sumMethod3.invoke(null, "1"); // 传入null，调用的也是静态方法，因为静态方法是属于类的
        System.out.println("result3 = " + result3);

    }
}
