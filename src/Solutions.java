import MyHelperClasses.Node;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solutions {
    private static Scanner scanner = new Scanner(System.in);

    public void printTopView(Node root)
    {
        HashMap<Node, Integer> horizontal_distance = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        horizontal_distance.put(root, 0);
        LinkedList<Integer> top_view = new LinkedList<>();
        top_view.add(root.data);
        int min_dist = 0, max_dist = 0;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int current_horizontal_distance = horizontal_distance.getOrDefault(node, 0);
            if (current_horizontal_distance<min_dist) {
                top_view.addFirst(node.data);
                min_dist = current_horizontal_distance;
            } else if (current_horizontal_distance>max_dist) {
                top_view.addLast(node.data);
                max_dist = current_horizontal_distance;
            }
            if (node.left != null) {
                horizontal_distance.put(node.left, current_horizontal_distance-1);
                queue.add(node.left);
            }
            if (node.right != null) {
                horizontal_distance.put(node.right, current_horizontal_distance+1);
                queue.add(node.right);
            }
        }
        System.out.println (top_view);
    }

    public static void main (String[] args)
    {
//        int t = scanner.nextInt();
//        while (t-- > 0) {
//
//        }

    }
}

/*
5 2
3 2 1 5 4
1
2

 */
