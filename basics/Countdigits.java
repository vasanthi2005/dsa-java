// Count all digits of a number
// Approach: divide by 10 repeatedly until 0, counting iterations
// Time: O(log n) — one step per digit, Space: O(1)
// Note: assumes n > 0; returns 0 for input 0, which should be 1
package basics;

public class Countdigits {
    int countDigits(int n) {
        // Code here
        int count = 0;
        while (n != 0) {
            count++;
            n = n / 10;
        }
        return count;
    }

}
