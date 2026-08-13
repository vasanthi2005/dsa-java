// Linear search
// Approach: single pass, return the index on first match
// Time: O(n) worst, O(1) best; Space: O(1)
// Note: optimal for UNSORTED data — no way to skip elements without ordering.
//       If the array were sorted, binary search gives O(log n).
package arrays;

public class Linearsort {
    public int linearSearch(int nums[], int target) {

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target)
                return i;
        }
        return -1;
    }
}
