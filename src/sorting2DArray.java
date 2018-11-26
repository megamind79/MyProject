import java.util.Arrays;
import java.util.Comparator;

/**
 * To sort a 2D array
 */
public class sorting2DArray {

    public static void main (String args[]) {
        int a[][] = {{2, 3}, {4, 2}, {2, 1}};
        Arrays.sort(a, Comparator.<int[]>comparingInt(b -> b[0]).thenComparingInt(b -> b[1]));
        // or
//        Comparator <Integer[]> comparator = new Comparator<Integer[]>() {
//            @Override
//            public int compare(Integer[] o1, Integer[] o2) {
//                return o1[0].equals(o2[0])?(o1[1] - o2[1]):(o1[0] - o2[0]);
//            }
//        };
//        Arrays.sort(a, comparator); // provided a is of type Integer instead of int
        for (int i = 0; i<3; i++) {
            System.out.println (a[i][0] + " " + a[i][1]);
        }
    }
}
