package HomeWork1;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        String[] stringArray = {"A", "B", "C", "D"};
        Integer[] integerArray = {1, 2, 3, 4};

        //#1
        changeArrayData(stringArray, 1, 2);
        changeArrayData(integerArray, 1, 2);

        System.out.println("integerArray:");
        for (Integer integer : integerArray) {
            System.out.print(integer + " ");
        }

        System.out.println("\nstringArray:");
        for (String s : stringArray) {
            System.out.print(s + " ");
        }

        //#2
        ArrayList<Integer> integerArrayList = arrayToArrayList(integerArray);
        ArrayList<String> stringArrayList = arrayToArrayList(stringArray);

        System.out.println("\nintegerArrayList:");
        System.out.println(integerArrayList.toString());

        System.out.println("stringArrayList:");
        System.out.println(stringArrayList.toString());

        //#3
        Box<Apple> appleBox = new Box<>(new Apple[]{new Apple(), new Apple(), new Apple(), new Apple(), new Apple(), new Apple() });
        Box<Apple> appleBox1 = new Box<>(new Apple[0]);
        Box<Orange> orangeBox = new Box<>(new Orange[]{ new Orange(), new Orange(), new Orange(), new Orange()});

        System.out.println(appleBox.getWeight());
        System.out.println(orangeBox.getWeight());

        System.out.println(appleBox.compare(orangeBox));

        appleBox.shiftingFruits(appleBox1, 10);
        System.out.println(appleBox.getWeight());
        System.out.println(appleBox1.getWeight());
    }

    public static <E> void changeArrayData(E[] data, int first, int second) {
        E tmp = data[first];
        data[first] = data[second];
        data[second] = tmp;
    }

    public static <E> ArrayList<E> arrayToArrayList(E[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }

}
