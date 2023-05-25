package reflection.invoke;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodInvokeDemo {


    public static void main(String[] args) throws Exception {
        /**
         * 动态调用无参数，无返回值的方法
         */
        // 1. 获取目标类的class对象
        MyClass obj = new MyClass(10);
        Class clazz = obj.getClass();

        // 2. 获取目标方法Method对象
        Method doNothingMethod = clazz.getMethod("doNothing");

        // 3. 调用method中的invoke方法
        doNothingMethod.invoke(obj);


        /**
         * 动态调用有参数，有返回值的方法
         */
        // 1. 获取目标类的class对象
        // 2. 获取目标方法Method对象
        Method sumMethod = clazz.getMethod("sum", int.class, String.class);

        // 3. 调用method中的invoke方法
        Object result = sumMethod.invoke(obj, 20, "20");
        System.out.println("result = " + result);


    }
}
