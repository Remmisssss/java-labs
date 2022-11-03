package lab1;

import java.util.Arrays;

public class Problem4 {

    public static int[] flattenMatrix(int[][] matrix) {

        int []arr = new int[matrix.length*matrix[0].length];
        int index = 0;
        for(int i=0;i<matrix[0].length;i++) {
            for (int j = 0; j < matrix.length; j++) {
                arr[index] = matrix[j][i];
                index++;
            }
        }
        System.out.println("Одномерный массив: " + Arrays.toString(arr));
        return arr;
    }

}