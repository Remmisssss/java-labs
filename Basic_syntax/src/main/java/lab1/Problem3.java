package lab1;

import java.util.Arrays;

public class Problem3 {

    public static double solveKnapsackProblem(double[] values, double[] weights, double maximumWeightCapacity) {

        double[][] arr_opt_cost = new double[values.length][3];
        double maxcost = arr_opt_cost[0][0];
        double res,cost,weight ;

        for(int i=0;i<values.length;i++){
            arr_opt_cost[i][0] = values[i];
            arr_opt_cost[i][1] = weights[i];
            arr_opt_cost[i][2] = values[i]/(weights[i]*maximumWeightCapacity);
        }

        for (int i=0;i<arr_opt_cost.length;i++) {
            cost = arr_opt_cost[i][0];
            weight = maximumWeightCapacity - arr_opt_cost[i][1];

            for (int j=i+1;j<arr_opt_cost.length;j++){
                if (weight-arr_opt_cost[j][1]>=0){
                    cost+=arr_opt_cost[j][0];
                    weight-=arr_opt_cost[j][1];
                }
            }
            if (cost > maxcost) maxcost = cost;
        }

        if (maxcost>0) res = maxcost;
        else res = 0;

        String s = String.valueOf(res);
        System.out.println("Максимальная ценность: " + s);

        return res;
    }

}