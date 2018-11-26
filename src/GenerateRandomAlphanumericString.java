/**
 * Created by user on 29/11/16.
 */
public class GenerateRandomAlphanumericString {

    /**
     * Generates a random alphanumeric charater
     * @return random alphanumeric charater
     */
    private static char getRandCharacter() {
        int c = (int) (Math.random() * 10) % 3;
        char ch = '$';
        switch (c) {
            case 0 : // small letter
                int r1 = (int) (Math.random() * 100) % 26;
                ch = (char) ('a' + r1);
                break;
            case 1 : // capital letter
                int r2 = (int) (Math.random() * 100) % 26;
                ch = (char) ('A' + r2);
                break;
            case 2 : // number
                int r3 = (int) (Math.random() * 100) % 10;
                ch = (char) ('0' + r3);
                break;
        }
        return ch;
    }

    /**
     *
     * @param n the length of the random alphanumeric string to be generated
     * @return  random alphanumeric string
     */
    public static String getRandomAlphaNumericString (int n) {
        StringBuilder randomAlphanumericString = new StringBuilder();
        for (int i = 0; i<n; i++) {
            char c = getRandCharacter();
            randomAlphanumericString.append(c);;
        }
        String randString = new String(randomAlphanumericString);
        return randString;
    }
}
