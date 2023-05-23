package reflection;

/**
 * 动态创建对象
 */
public class ObjectCreationDemo {

    public static void main(String[] args) {

        // 动态创建对象
        Class<Animal> animalClass = Animal.class;
        try {

            // 使用newInstance 方法要求
            // 1. 要有无参构造器
            // 2. 无参构造器要有访问权限
            Animal animal = animalClass.newInstance();
            System.out.println("animal = " + animal);

        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
