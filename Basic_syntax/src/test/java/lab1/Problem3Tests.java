package lab1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Problem3Tests {

    @Test
    void Test() {
        double[] values = {2.0, 1.0, 5.0};
        double[] weights = {1.0, 1.0, 2.0};
        double maximumWeightCapacity = 2.5;
        double res = Problem3.solveKnapsackProblem(values, weights, maximumWeightCapacity);
        assertEquals(res, 5.0);
    }

}
