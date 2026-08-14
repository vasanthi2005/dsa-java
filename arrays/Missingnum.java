// Missing number in range [0, n]
// Approach: the expected sum of 0..n is n*(n+1)/2. Subtract the actual
//           array sum from it — the difference is the missing value.
// Time: O(n) single pass, Space: O(1)
// Note: array has n elements but the range covers n+1 values (0 through n
//       inclusive), which is why n = nums.length works for the formula
// Alternatives: HashSet lookup is O(n) time but O(n) space; sorting is
//       O(n log n). XOR of all indices and values also works in O(1) space
//       and avoids the overflow risk on very large n.
package arrays;

public class Missingnum {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int totalsum = n * (n + 1) / 2;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        return totalsum - sum;
    }
}
