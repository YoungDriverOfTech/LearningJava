package reflection;

/**
 * 怎么获取Class类对象
 */
public class ObtainClassInstanceDemo {
    public static void main(String[] args) {
        // take String as an example

        // 方法1： 用.class
        Class stringClass = String.class;
        System.out.println("stringClass = " + stringClass);

        Class<Animal> animalClass = Animal.class;
        System.out.println("animalClass = " + animalClass);

        // 方法2： 使用对象的getClass()方法来获得class
        String str = "hello world";
        Animal animal = new Animal();

        Class<? extends String> stringClass2 = str.getClass();
        Class animalClass2 = animal.getClass();

        System.out.println("stringClass2 = " + stringClass2);
        System.out.println("animalClass2 = " + animalClass2);

        // 方法3： Class.forName()方法来获得class
        try {
            Class stringClass3 = Class.forName("java.lang.String");
            Class animalClass3 = Class.forName("reflection.Animal");

            System.out.println("stringClass3 = " + stringClass3);
            System.out.println("animalClass3 = " + animalClass3);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // 方法4： 用classloader (了解即可)
        ClassLoader classLoader = ObtainClassInstanceDemo.class.getClassLoader();
        try {
            Class stringClass4 = classLoader.loadClass("java.lang.String");
            Class animalClass4 = classLoader.loadClass("reflection.Animal");

            System.out.println("stringClass4 = " + stringClass4);
            System.out.println("animalClass4 = " + animalClass4);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // 特殊获取Class对象（只能用于基本类型的包装类）
        Class intClass = Integer.TYPE;


        // 注意： Class对象是在编译期间生成的，所以每个类在JVM中只有一个Class对象
        String s1 = "hello";
        String s2 = "world";

        Class s1Class = s1.getClass();
        Class s2Class = s2.getClass();

        // 结果现实always true。 因为s1/s2取到的Class对象都是String，且在编译的时候只能有一个String的Class
        System.out.println("(s1Class == s2Class) = " + (s1Class == s2Class));
    }
}
