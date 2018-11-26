import java.util.*;

class Node {
    int data;
    Node prev, next;

    Node(int data) {
        this.data = data;
        prev = next = null;
    }

    void attach(int data) {
        this.next = new Node(data);
        this.next.prev = this;
    }

    String printList() {
        String string = this.toString();
        if (this.next != null)
            string += this.next.printList();

        return string;
    }

    @Override
    public String toString() {
        return "("+data+")";
    }
}

class GFG1 {
    private static Scanner scanner = new Scanner(System.in);

    /*
    S: "barfoothefoobarman"
    L: ["foo", "bar"]

    Input: Digit string "aab"
    [
    ["a","a","b"]
    ["aa","b"],
  ]
  aaba
  [a a b a ] [a aba ] [aa b a ]

  When on index i, you incrementally check all substring starting from i for being palindromic. If found, you recursively solve the problem for the remaining
  string and add it to your solution. Start this recursion from starting position of the string.

PS: You can optimize your solution by not computing the answer for same index multiple times using Dynamic Programming.

Ordering the results in the answer : Entry i will come before Entry j if :
len(Entryi[0]) < len(Entryj[0]) OR
(len(Entryi[0]) == len(Entryj[0]) AND len(Entryi[1]) < len(Entryj[1])) OR
*
*
*
(len(Entryi[0]) == len(Entryj[0]) AND … len(Entryi[k] < len(Entryj[k]))


     */
    static ArrayList<ArrayList<String>> sol = new ArrayList<>();
    private static boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i <= j && i <= s.length()-1 && j >= 0)
            if (s.charAt(i++) != s.charAt(j--)) return false;
        return true;
    }

    private static void helper(String a, int start, ArrayList<String> list) {
        if (start == a.length()) sol.add(new ArrayList<String>(list));
        else
            for (int i = start + 1; i <= a.length(); i++) {
                String substr = a.substring(start, i);
                if (isPalindrome(substr)) {
                    list.add(substr);
                    helper(a, i, list);
                    list.remove(list.size()-1);
                }
            }
    }
    public ArrayList<ArrayList<String>> partition(String a) {
        sol = new ArrayList<>();
        if (a.length() == 0) return sol;
        helper(a, 0, new ArrayList<String>());
        return sol;
    }


    public static void main (String[] args) throws Exception {
        System.out.println(new GFG1().partition("ef"));
    }
}
