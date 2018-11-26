public class Par extends abstClass {

    static int y = 4;

    private static int value = 3;

    Par () {
        System.out.println("Par constructor value = " + value);
        addValue ();
    }

    int addValue () {
        System.out.println("Par addValue value = " + value);
        return value+=20;
    }

    public void change (int z) {
        System.out.println("Prev y = " + y);
        y = z;
        System.out.println("New y = " + y);
    }

    public String toString () {
        return "y = " + y + ", value = " + value;
    }

    public static void newMe() {
        System.out.println("newMe Par");
    }

    public static void main(String args[]) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        int k = sc.nextInt();

        abstClass t = new Par ();
//        abstClass t = new abstClass ();
        System.out.println(t.getValue());
        t.addValue();
        System.out.println(t.getValue());
        Par.newMe();
    }
}
