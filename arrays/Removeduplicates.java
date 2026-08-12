// Remove duplicates from a sorted array (in-place)
// Approach: two pointers — i scans forward, start marks where the next unique
//           value goes. Since the array is sorted, duplicates are adjacent, so
//           comparing nums[i] with nums[i-1] is enough to detect a new value.
// Time: O(n), Space: O(1)
// Note: start is an INDEX, so the count of unique elements is start + 1
//       start never overtakes i, so overwriting the front while reading ahead is safe
//       only works because the input is sorted — unsorted would need a HashSet

package arrays;

public class Removeduplicates {
    public int removeDuplicates(int[] nums) {
        int start = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                start++;
                nums[start] = nums[i];
            }
        }
        return start + 1;
    }
}
