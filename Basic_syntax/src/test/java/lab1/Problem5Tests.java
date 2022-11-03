package lab1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Problem5Tests {
    @Test
    void Test(){
        String numbers = "1,2,4,8,16";
        boolean res = Problem5.isGeometricProgression(numbers);
        assertTrue(res);
    }

    @Test
    void TestSort(){
        String numbers = "16,2,8,1,4";
        boolean res = Problem5.isGeometricProgression(numbers);
        assertTrue(res);
    }

    @Test
    void FalseTest(){
        String numbers = "1,2,3";
        boolean res = Problem5.isGeometricProgression(numbers);
        assertFalse(res);
    }
}
