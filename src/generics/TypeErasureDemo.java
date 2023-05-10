package generics;

import java.util.ArrayList;

public class TypeErasureDemo {

    public static void main(String[] args) {

        // 类型擦除： 最后两种编译完之后，都会变成第一种的裸类型.
        ArrayList list1 = new ArrayList();
        ArrayList<String> list2 = new ArrayList<>();
        ArrayList<Integer> list3 = new ArrayList<>();

        // 所以第一种可以被赋值成2和3的，且不报错. 且可以装任何类型的数据. 所以使用范型的时候，不要写裸类型
        list1 = list2;
        list1 = list3;
        list1.add("aa");
        list1.add(true);

        // 因为类型擦除，如果定义重载方法，参数类型是List<String> param 和 List<Integer> param。 会报错，就是因为类型被擦除掉了
        // 两个方法的参数类型都会被变成裸类型 List param
    }
}
