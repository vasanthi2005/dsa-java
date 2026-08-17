// Longest subarray with sum k — sliding window (positives only)
// Approach: two pointers marking a window, plus a running sum of what's inside.
//           Expand by adding nums[i] on the right; while the sum overshoots k,
//           shrink from the left by subtracting nums[left] and advancing it.
//           Record the length whenever the sum lands exactly on k.
// Time: O(n) — both pointers only move forward, at most 2n steps total despite
//       the nested loop. Space: O(1), no map needed.
// Note: window length is i - left + 1; both edges are inside the window
//       the shrink guard belongs in the while CONDITION, not as an if inside —
//       a loop's exit can't depend on a line that might be skipped
//       don't confuse the sum with the length: sum always equals k when you
//       record, so assigning it would be meaningless
// ONLY works on positive values. The technique rests on adding always
//       increasing the sum and removing always decreasing it, so "too big →
//       shrink left" is reliable. One negative breaks that — shrinking might
//       increase the sum. That case needs the prefix-sum map version:
//       O(n) space instead of O(1).
package arrays;

class Longestsubarraypositives {
    public int longestSubarray(int[] nums, int k) {
        int left = 0;
        int len = 0;
        int sum = 0;
        int maxlen = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum > k && left <= i) {
                sum -= nums[left];
                left++;
            }

            if (sum == k) {
                len = (i - left) + 1;
                if (maxlen < len)
                    maxlen = len;
            }

        }
        return maxlen;

    }
}