package lab1;

import java.util.Arrays;

public class Problem2 {

    public static int[] segregateEvenAndOddNumbers(int[] array) {

         int[] a = new int[array.length];
        int index = 0;
        for (int tmp: array) {
            if (tmp < 0) {
                return null;
            }}
        for (int i : array) {
            if (i % 2 == 0) {
                a[index] = i;
                index++;
            }
        }
        for (int i : array) {
            if (i % 2 != 0) {
                a[index] = i;
                index++;
            }
        }
        for (int i = 0; i < array.length; i++)
        {
            System.out.print(a[i] + " ");
        }
        System.out.print("\n");
        return a;

    }
}
