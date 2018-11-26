package MyHelperClasses;

public class Point {
    int x, y, z;

    Point(int x, int y) {
        this(x, y, 0);
    }

    Point(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return "("+x+", "+y+")";
    }
}
