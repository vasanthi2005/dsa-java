// Maximum subarray sum — Kadane's Algorithm
// Approach: track a running sum and the best sum seen. At each element, add it
//           to the running sum, compare against the max, then reset the running
//           sum to 0 if it has gone negative — a negative prefix can only drag
//           down any subarray continuing through it, so start fresh instead.
// Time: O(n), Space: O(1) — beats the O(n²) all-subarrays brute force
// Note: max must start at Integer.MIN_VALUE, not 0, or an all-negative array
//       returns 0 instead of its largest element (subarrays must be non-empty)
//       compare BEFORE resetting, or that largest negative is never recorded
package arrays;

public class Kadanesalg {
    public int maxSubArray(int[] nums) {
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i : nums) {
            sum += i;
            if (sum > max) {
                max = sum;
            }
            if (sum < 0) {
                sum = 0;
            }
        }
        return max;
    }
}
