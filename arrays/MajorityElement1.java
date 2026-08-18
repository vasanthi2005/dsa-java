// Majority element (appears more than n/2 times)
// Approach: frequency map, then scan the entries for a count exceeding n/2
// Time: O(n), Space: O(n)
// Note: integer division means nums.length/2 rounds down, which is what you
//       want — "more than half" for n=7 is >3, and 7/2 gives 3.
//BRUTE FORCE
package arrays;

import java.util.HashMap;
import java.util.Map;

public class MajorityElement1 {
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            if (e.getValue() > nums.length / 2)
                return e.getKey();
        }
        return -1;

    }
    // Majority element (appears more than n/2 times) — Moore's Voting Algorithm
    // Approach: keep a candidate and a counter. If count is 0, adopt the current
    // element as candidate. If the element matches the candidate,
    // increment; otherwise decrement.
    // Intuition: non-majority elements cancel majority ones one-for-one. Since the
    // majority appears more than n/2 times, there aren't enough others to
    // cancel it all — something of it always survives.
    // Time: O(n) single pass, Space: O(1) — beats the HashMap version's O(n) space
    // Note: the candidate can change several times mid-run; count hitting 0 means
    // everything so far cancelled out, so you start fresh from that point.
    // Branches must be mutually exclusive (else-if or nested else) — three
    // separate ifs double-count on the iteration where the candidate is set.
    // This only FINDS a candidate. If a majority isn't guaranteed to exist,
    // a second pass is needed to verify the count actually exceeds n/2.

    public int majorityElement(int[] nums) {
        int count = 0;
        int candidate = 0;
        for (int i : nums) {
            if (count == 0) {
                candidate = i;
                count++;
            } else if (i == candidate)
                count++;
            else
                count--;
        }
        return candidate;

    }
}
