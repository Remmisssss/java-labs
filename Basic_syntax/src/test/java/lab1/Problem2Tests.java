package lab1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Problem2Tests {

    @Test
    void Test(){
        int [] array = {2, 1, 5, 6, 8};
        int [] res = Problem2.segregateEvenAndOddNumbers(array);
        assertArrayEquals(res,new int[]{2, 6, 8, 1, 5});
    }

    @Test
    void FalseTest(){
        int [] array = {2, -1, 5, 6, 8};
        int [] res = Problem2.segregateEvenAndOddNumbers(array);
        assertArrayEquals(res,null);
    }


}