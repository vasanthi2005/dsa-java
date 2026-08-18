// Two Sum (return indices, unsorted array)
// Approach: one pass with a HashMap of value -> index. At each element the
//           needed partner is exactly target - nums[i], so instead of
//           searching for a pair, ask "have I already seen that number?"
// Time: O(n), Space: O(n) — beats the O(n²) nested-loop brute force
// Note: look up BEFORE storing, or an element matches itself (target 8,
//       element 4). Only "already seen" matters — every pair is found at
//       its second element, so you never look forward.
// The unreachable return at the end exists for the compiler; the problem
//       guarantees exactly one solution.
package arrays;

import java.util.HashMap;

public class Twosum {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i]))
                return new int[] { map.get(target - nums[i]), i };
            map.put(nums[i], i);
        }
        return new int[] {};
    }
}
