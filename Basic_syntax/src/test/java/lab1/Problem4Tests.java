package lab1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Problem4Tests {
    @Test
    void Test_nxn(){
        int [][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
        };
        int []res = Problem4.flattenMatrix(matrix);
        assertArrayEquals(res,new int[]{1, 4, 7, 2, 5, 8, 3, 6, 9});
    }
    @Test
    void Test_nxm(){
        int [][] matrix = {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
        };
        int []res = Problem4.flattenMatrix(matrix);
        assertArrayEquals(res,new int[] {1, 5, 9, 2, 6, 10, 3, 7, 11, 4, 8, 12});
    }
}
