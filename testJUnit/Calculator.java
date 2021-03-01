package HomeWork6;

import java.util.Arrays;

public class Calculator {

    public int[] methodA(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            if (array[i] == 4) {
                return Arrays.copyOfRange(array, i + 1, array.length);
            }
        }
        throw new RuntimeException();
    }

    public boolean methodB(int[] array) {
        for (int arr : array) {
            if (arr == 1 || arr == 4) {
                return true;
            }
        }
        return false;
    }
}
