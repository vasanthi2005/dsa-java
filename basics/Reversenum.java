// Reverse a number
// Approach: extract last digit with n % 10, build result with rev * 10 + digit
// Time: O(log n), Space: O(1)
package basics;

public class Reversenum {
    public int reverse(int x) {
        long rev = 0;
        while (x != 0) {
            rev = (rev * 10) + (x % 10);
            x /= 10;
        }
        if (rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE)
            return 0;
        return (int) rev;
    }
}
