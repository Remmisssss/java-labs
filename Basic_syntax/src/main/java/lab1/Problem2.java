package lab1;

import java.util.Arrays;

public class Problem2 {

    public static int[] segregateEvenAndOddNumbers(int[] array) {

        StringBuilder even = new StringBuilder();
        StringBuilder odd = new StringBuilder();
        for (int tmp: array) {
            if (tmp < 0) {
                return null;
            }}

            Arrays.stream(array).forEach(e -> {
                if (e % 2 == 0) {
                    even.append(e).append(" ");
                }
                if (e % 2 != 0) {
                    odd.append(e).append(" ");
                }
            });
            String str = even.toString().concat(odd.toString());

            System.out.println("Четный массив: " + even.toString());
            System.out.println("Нечетный массив: " + odd.toString());

            String[] strArray = null;
            strArray = str.split(" ");
            for (int i = 0; i < strArray.length; i++) {
                System.out.print(strArray[i]);
            }

            int[] values = Arrays.stream(strArray)
                    .mapToInt(Integer::parseInt)
                    .toArray();
            System.out.println("\n" + Arrays.toString(values));
            return values;

    }
}