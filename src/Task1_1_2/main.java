package Task1_1_2;

import java.util.ArrayList;
import java.util.Arrays;

public class main {
    public static void main(String[] args) {
        final int N = 10;
        Integer[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        replace(a, 3, 4);
        System.out.println("Массив целых: " +  Arrays.toString(a));
        Double[] b = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0};
        replace(b, 5, 6);
        System.out.println("Массив вещественных: " +  Arrays.toString(b));
        System.out.println("Array list: " +  toArrayList(a).toString());

    }
    public static final <T> void replace(T[] a, int x, int y) {
        T temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }

    private static final <T> ArrayList<T> toArrayList(T[] a){
        ArrayList<T> result = new ArrayList<>();
        for(int i = 0; i < a.length; ++i){
            result.add(a[i]);
        }
        return result;
    }
}
