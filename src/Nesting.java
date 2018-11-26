
public class Nesting {
    private static int x = 1;
    private int y = 2;

    private static class staticNestedClass {
        private static int z = 3;
        private int a;
        int getX () {
            return x;
        }

        public static int getZ () {
            return z;
        }
    }

    class nestedClass {
        // can't have static variables as this can only exist as an instance of Nesting class.
        private int z = 4;
        int a = 5;
        int getX () {
            return x;
        }

        int getY () {
            return y;
        }
    }

    int getStaticZ () {
        return staticNestedClass.z;
    }

    int getStaticA () {
        return new staticNestedClass().a;
    }

    int getZ () {
        return new nestedClass().z;
    }

    static staticNestedClass getStaticNestedClassObject () {
        return new staticNestedClass();
    }
}

class TestNesting {

    public static void main (String args[]) {
        // if staticNestedClass isn't private
//        Nesting.staticNestedClass staticNestedClassObject = new Nesting.staticNestedClass();
//        staticNestedClassObject.getX();
//        Nesting.staticNestedClass.getZ();

        // if nestedClass isn't private
//        Nesting.nestedClass nestedClassObject = new Nesting().new nestedClass();
//        nestedClassObject.getX();

        // if staticNestedClass is private
        Object object = Nesting.getStaticNestedClassObject();
        System.out.println (object.getClass());
    }
}
