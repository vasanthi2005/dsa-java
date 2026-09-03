// Largest subarray with sum 0
// Approach: prefix sums. If the running total is the same at two positions,
//           everything between them sums to zero. The map stores
//           runningSum -> FIRST index it occurred. At each step, if the
//           current sum has been seen before, the length is i - that index.
// Time: O(n), Space: O(n) — brute force over all subarrays is O(n²)
// Note: this is longest-subarray-with-sum-k with k = 0, so the lookup
//       simplifies from (sum - k) to just (sum).
//       Store only the FIRST occurrence — the earliest start gives the
//       longest span. The if/else does this naturally: look up if the sum
//       exists, store it only if it doesn't.
//       Seed map.put(0, -1): the running total before the array began is 0,
//       at imaginary index -1. Without it, subarrays starting at index 0 are
//       missed — [1, -1, 3] would return 0 instead of 2.
//       O(n) space is unavoidable here: negatives are allowed, so there is no
//       sliding-window alternative.
package arrays;

import java.util.HashMap;

public class LargestSubarraywithSum0 {
    public int maxLen(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        int count = 0;
        int maxcount = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum)) {
                count = i - map.get(sum);
                if (count > maxcount)
                    maxcount = count;
            } else {
                map.put(sum, i);
            }
        }
        return maxcount;
    }
}
