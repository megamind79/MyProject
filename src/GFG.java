import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GFG {
    private static Scanner scanner = new Scanner (System.in);

    Stack<Integer> getSeparation(int n) {
        Stack<Integer> seperations = new Stack<>();

        int multiplier = 1;
        while (n>0) {
            int prod = n%10;
            n = n/10;
            prod *= multiplier;
            multiplier *= 10;
            if (prod>0)
                seperations.push(prod);
        }

        return seperations;
    }

    HashMap<Integer, String> getRomanMap() {
        HashMap<Integer, String> map = new HashMap<>();

        map.put(1, "I");
        map.put(4, "IV");
        map.put(5, "V");
        map.put(9, "IX");
        map.put(10, "X");
        map.put(40, "XL");
        map.put(50, "L");
        map.put(90, "XC");
        map.put(100, "C");
        map.put(400, "CD");
        map.put(500, "D");
        map.put(900, "CM");
        map.put(1000, "M");

        return map;
    }

    int getLargestBase(int n) {
        int base = 1;
        while (n/base>0) {
            base *= 10;
        }
        base /= 10;
        if (n/(base*5)>0)
            base *= 5;

        return base;
    }

    void getRomanNumber(int n) {
        HashMap<Integer, String> map = getRomanMap();
        Stack<Integer> seperations = getSeparation(n);

        StringBuilder roman_numeral = new StringBuilder();

        while (!seperations.isEmpty()) {
            int number = seperations.pop();
            String roman = map.get(number);
            if (roman == null) {
                StringBuilder new_roman = new StringBuilder();
                while (number > 0) {
                    int base = getLargestBase(number);
                    new_roman.append(map.get(base));
                    number -= base;
                }
                roman = new_roman.toString();
            }
            roman_numeral.append(roman);
        }

        System.out.println(roman_numeral);
    }

    public static void main (String[] args) throws IOException{
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            new GFG().getRomanNumber(n);
        }
    }
}