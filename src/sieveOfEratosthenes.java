import java.util.Scanner;

/**
 * Prints prime numbers from 2 to n
 */
public class sieveOfEratosthenes {

    static boolean p[];

    /**
     * marks prime numbers as false and composite as true
     * @param n the number till which you want primes to be printed - excluding n
     */
    static void sieve (int n) {
        for (int i = 2; i<n;) {
            if (!p[i]) {
                for (int j = i*2; j<n; j += i) {
                    p[j] = true;
                }
            }
            i++;
        }
    }

    /**
     * Prints prime numbers from 2 (inclusive) to n (exclusive)
     * @param n the number till which you want primes to be printed - excluding n
     */
    static void printPrimes (int n) {
        sieve(n);
        for (int i = 2; i<n; i++)   if (!p[i])  System.out.print(i + " ");
        System.out.println();
    }

    public static void main (String args[]) {
        Scanner sc = new Scanner (System.in);
        int n = sc.nextInt();
        p = new boolean[n];
        printPrimes(n);
    }
}
