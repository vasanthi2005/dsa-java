// Single number (every element appears twice except one)
// Approach: XOR everything together. x ^ x = 0 so pairs cancel, and x ^ 0 = x
//           so the lone number survives. Order-independent — pairs don't need
//           to be adjacent.
// Time: O(n), Space: O(1)
// Note: a HashMap frequency count also works in O(n) time but O(n) space.
//       XOR is the reason this problem is worth doing — same complexity in
//       time, strictly better in space.
package arrays;

public class Singlenum {
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int i : nums) {
            result ^= i;
        }
        return result;
    }
}
