// Count subarrays with sum equal to k
// Approach: prefix sums with a FREQUENCY map (runningSum -> how many times it
//           has occurred). At each position, the number of subarrays ending
//           here that sum to k equals the number of earlier positions whose
//           running total was sum - k. Add that count, then record the current
//           sum.
// Time: O(n), Space: O(n) — brute force over all subarrays is O(n²)
// Note: seed map.put(0, 1) before the loop. The running total before adding
//       anything is 0, and it occurred once — that makes subarrays starting at
//       index 0 findable. Same job the `sum == k` branch did in the longest-
//       subarray version.
//       Look up BEFORE storing, or with k = 0 the current sum matches itself
//       and counts a zero-length subarray.
//       Values, not indices, and every occurrence is stored — unlike the
//       longest-subarray version, where only the FIRST occurrence mattered
//       because it gave the earliest start.
//       The running sum never resets. Prefix sums accumulate; subtraction
//       extracts any range you want.
// Works with negatives, so sliding window is not an option.
package arrays;

import java.util.HashMap;

public class Subarraywithgivensum {
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0;
        int count = 0;
        for (int i : nums) {
            sum += i;
            if (map.containsKey(sum - k))
                count += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);

        }
        return count;
    }
}
