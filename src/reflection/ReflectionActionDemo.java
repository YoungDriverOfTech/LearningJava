package reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class ReflectionActionDemo {

    public static void main(String[] args) {
        // 1. 获取类名
        System.out.println("1. 获取类名");
        Class<Bird> birdClass = Bird.class;
        System.out.println("birdClass.getName() = " + birdClass.getName());
        System.out.println("birdClass.getSimpleName() = " + birdClass.getSimpleName());
        System.out.println();

        // 2. 获取包名字
        System.out.println("2. 获取包名字");
        Package birdClassPackage = birdClass.getPackage();
        System.out.println("birdClassPackage = " + birdClassPackage);
        System.out.println();

        // 3. 获取构造器
        // 获取当前类所有的public构造器
        System.out.println("3. 获取构造器-获取当前类所有的public构造器");
        Constructor<?>[] publicConstructors = birdClass.getConstructors();
        for (Constructor<?> publicConstructor : publicConstructors) {
            System.out.println("publicConstructor = " + publicConstructor);
        }
        System.out.println();

        // 获取当前类所有的构造器
        System.out.println("3. 获取构造器-获取当前类所有的构造器");
        Constructor<?>[] allConstructors = birdClass.getDeclaredConstructors();
        for (Constructor<?> allConstructor : allConstructors) {
            System.out.println("allConstructor = " + allConstructor);
        }
        System.out.println();

        // 4. 获取属性相关信息
        // 获取所有的public属性
        System.out.println("4. 获取属性相关信息-获取所有的public属性");
        Field[] publicFields = birdClass.getFields();
        for (Field publicField : publicFields) {
            System.out.println("publicField = " + publicField);

            // 变量名字
            System.out.println("publicField.getName() = " + publicField.getName());

            // 访问修饰符
            System.out.println("publicField.getModifiers() = " + publicField.getModifiers());
            System.out.println("Modifier.toString(publicField.getModifiers()) = " + Modifier.toString(publicField.getModifiers()));

            // 数据类型
            System.out.println("publicField.getType() = " + publicField.getType());
        }
        System.out.println();

        // 获取所有自身类的属性，但是不包括父类的树属性
        System.out.println("4. 获取属性相关信息-获取所有自身类的属性，但是不包括父类的树属性");
        Field[] allFields = birdClass.getDeclaredFields();
        for (Field allField : allFields) {
            System.out.println("allField = " + allField);

            // 变量名字
            System.out.println("allField.getName() = " + allField.getName());

            // 访问修饰符
            System.out.println("allField.getModifiers() = " + allField.getModifiers());
            System.out.println("Modifier.toString(allField.getModifiers()) = " + Modifier.toString(allField.getModifiers()));

            // 数据类型
            System.out.println("allField.getType() = " + allField.getType());
        }
        System.out.println();


        // 5. 获取方法相关信息
        // 获取该类及其父类的public方法
        Method[] publicMethods = birdClass.getMethods();
        for (Method publicMethod : publicMethods) {
            System.out.println("publicMethod = " + publicMethod);

            // 获取方法的注解
            Annotation[] annotations = publicMethod.getAnnotations();
            for (Annotation annotation : annotations) {
                System.out.println("annotation = " + annotation);
            }

            // 获取方法信息
            System.out.println("publicMethod.getName() = " + publicMethod.getName());
            System.out.println("publicMethod.getReturnType() = " + publicMethod.getReturnType());
            System.out.println("publicMethod.getParameters() = " + Arrays.toString(publicMethod.getParameters()));
            System.out.println("publicMethod.getExceptionTypes() = " + Arrays.toString(publicMethod.getExceptionTypes()));
        }
        System.out.println();

        // 获取本类的所有方法，包含私有方法，但是不会获取父类的方法
        Method[] allMethods = birdClass.getDeclaredMethods();
        for (Method allMethod : allMethods) {
            System.out.println("allMethod = " + allMethod);

            // 获取具体的注解，判断某种类型的注解
            Annotation[] annotations = allMethod.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof  Markable) {
                    Markable markableAnnotation = (Markable) annotation;
                    System.out.println("markableAnnotation = " + markableAnnotation.value());
                }
            }
        }

        // 6. 获取类的annotation
        Markable classAnnotation = birdClass.getAnnotation(Markable.class);
        System.out.println("classAnnotation = " + classAnnotation.value());


    }
}
