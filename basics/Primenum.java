// Check if a number is prime
// Approach: if no number from 2 to √n divides n, it's prime — any factor
//           above √n would have a partner below it, already tested
// Time: O(√n), Space: O(1)
// Note: guard n <= 1 first (1 is not prime, needs exactly two divisors)
//       condition must be i*i <= n, not <, or perfect squares of primes
//       (25, 49, 121) wrongly pass
package basics;

public class Primenum {
    static boolean isPrime(int n) {
        if (n <= 1)
            return false;
        // code here
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

}
