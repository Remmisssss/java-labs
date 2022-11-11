package lab1;

import java.util.Arrays;

public class Problem5 {

    public static boolean isGeometricProgression(String numbers) {

        String [] str = numbers.split(",");
        int[] a = Arrays.stream(str)
                .mapToInt(Integer::parseInt)
                .toArray();
        Arrays.sort(a);

        int r=a[1]/a[0];
        if (Math.abs(a[1])>Math.abs(a[0])){
        for(int i=2;i<a.length-1;i++)
        {
            if((a[i+1]/a[i])==r)
                return true;
        }
    }
        return false;
    }
}
