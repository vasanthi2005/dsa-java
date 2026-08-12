// Check if array is sorted (non-decreasing)
// Approach: single pass comparing each element with the next; return false
//           immediately on the first out-of-order pair
// Time: O(n) worst, O(1) best (fails on the first comparison), Space: O(1)
// Note: loop bound is size()-1 since we look at i+1
//       ArrayList uses size(), arrays use length, strings use length()
package arrays;

import java.util.ArrayList;

public class Checksortedarray {
    public boolean isSorted(ArrayList<Integer> nums) {
        for (int i = 0; i < nums.size() - 1; i++) {
            if (nums.get(i) > nums.get(i + 1))
                return false;
        }
        return true;
    }
}
