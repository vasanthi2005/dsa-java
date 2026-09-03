// Count subarrays with XOR equal to k
// Approach: prefix XOR with a FREQUENCY map. XOR is its own inverse
//           (x ^ x = 0), so where sums use subtraction to cancel a prefix,
//           XOR cancels itself. At each position the number of subarrays
//           ending here equals the number of earlier prefixes whose XOR was
//           (xor ^ k).
//           Why xor ^ k: if current ^ earlier = k, then XOR both sides by
//           current — current cancels itself, leaving earlier = current ^ k.
// Time: O(n), Space: O(n)
//
// TWO SHAPES OF PREFIX PROBLEM — pick the map to match the question:
//
//   LENGTH  ("longest subarray with...")
//     map stores  value -> FIRST index it occurred
//     on a match  length = i - map.get(...)
//     store only the first occurrence (earliest start = longest span)
//     seed        map.put(0, -1)   // position before the array
//
//   COUNT   ("how many subarrays with...")
//     map stores  value -> HOW MANY TIMES it occurred
//     on a match  count += map.get(...)
//     store EVERY occurrence, incrementing the frequency
//     seed        map.put(0, 1)    // one occurrence of the empty prefix
//
//   I reached for the length version here. The tell is in the wording:
//   "return the total number of subarrays" is a count.
//
// Note: look up BEFORE storing, or with k = 0 the current prefix matches
//       itself and counts a zero-length subarray.
//       getOrDefault(key, 0) makes non-matches free — most iterations add 0.
package arrays;

import java.util.HashMap;

public class Countsubarrayswithgivenxork {
    public int subarraysWithXorK(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int xor = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            xor ^= nums[i];
            if (map.containsKey(xor ^ k)) {
                count += map.get(xor ^ k);
            }
            map.put(xor, map.getOrDefault(xor, 0) + 1);
        }
        return count;
    }

}
