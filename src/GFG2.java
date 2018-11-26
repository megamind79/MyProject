import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.*;

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) {
         val = x;
         left=null;
         right=null;
     }

    @Override
    public String toString() {
        return "TreeNode("+val+")";
    }
}

class GFG2 {
    private static final Scanner scanner = new Scanner(System.in);
    private HashMap<Integer, ArrayList<Integer>> columns = new HashMap<>();

    private void verticalOrderTraversal(TreeNode A, int level) {
        if (A == null)
            return;
        ArrayList<Integer> col_set = columns.getOrDefault(level, new ArrayList<Integer>());
        col_set.add(A.val);
        columns.put(level, col_set);
        verticalOrderTraversal(A.left, level-1);
        verticalOrderTraversal(A.right, level+1);
    }

    public ArrayList<ArrayList<Integer>> verticalOrderTraversal(TreeNode A) {
        verticalOrderTraversal(A, 0);
        ArrayList<ArrayList<Integer>> response = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> key_set = new ArrayList<>(columns.keySet());
        Collections.sort(key_set);
        for (int key : key_set) {
            response.add(columns.get(key));
        }
        return response;
    }

    public static void main(String[] args) throws Exception {
//        int t = scanner.nextInt();
//        while (t-->0) {
//            int n = scanner.nextInt();
//            ArrayList<Integer> stream = new ArrayList<>();
//            while (n-->0) {
//                stream.add(scanner.nextInt());
//            }
//            System.out.println();
//        }

//        for (int i = 1; i<=24; i++) {
//            System.out.println(i+". " + new GFG2().getPermutation(4, i));
//        }
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.left.right = new TreeNode(5);
        System.out.println(new GFG2().verticalOrderTraversal(root));
    }

}
/*
[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
*/