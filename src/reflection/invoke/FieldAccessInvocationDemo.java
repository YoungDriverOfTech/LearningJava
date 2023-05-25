package reflection.invoke;

import java.lang.reflect.Field;

public class FieldAccessInvocationDemo {

    public static void main(String[] args) throws Exception {
        Person person = new Person();
        System.out.println("person = " + person);

        // 获取age字段，将age改为60
        Field ageField = person.getClass().getDeclaredField("age");
        ageField.setAccessible(true);

        // set属性
        ageField.set(person, 60);
        System.out.println("person = " + person);
    }
}
