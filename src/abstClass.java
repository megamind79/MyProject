/**
 * Created by user on 21/11/16.
 */
public class abstClass {

    private static int value = 0;
    abstClass(){
        System.out.println("abstClass constructor value = " + value);
        addValue ();
    }

    int addValue () {
        System.out.println("abstClass addValue = " + value);
        return value+=10;
    }

    int getValue () {
        System.out.println("abstClass getValue = " + value);
        return value;
    }

    protected static void newMe() {
        System.out.println("newMe");
    }

}
