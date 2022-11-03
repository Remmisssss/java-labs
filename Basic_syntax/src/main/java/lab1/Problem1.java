package lab1;

public class Problem1 {
    public static boolean containsDigitAInHexadecimalRepresentation(int number) {
        String hex = Integer.toHexString(number);

        System.out.println("Входное число: " + number);
        System.out.println("Есть ли символ 'A' в "+ hex + "?");
        if (hex.indexOf('A')>-1 || hex.indexOf('a')>-1 ){
            System.out.println("Есть");
            return true;
        }
        else {
            System.out.println("Нет");
            return false;
        }
}
}
