// Second largest distinct element in an array
// Approach: single pass tracking max1 and max2; when a new max is found,
//           the old max1 shifts down to max2
// Time: O(n), Space: O(1) — beats sorting and taking index n-2, which is O(n log n)
// Note: the else-if must exclude values equal to max1, or [5,5,3] returns 5
//       initialised to Integer.MIN_VALUE as a sentinel — must check for it
//       before returning, or all-duplicate input leaks -2147483648 instead of -1
package arrays;

public class Secondlargestnum {
    public int secondLargestElement(int[] nums) {
        if (nums.length < 2)
            return -1;
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max1) {
                max2 = max1;
                max1 = nums[i];
            } else if (nums[i] > max2 && nums[i] != max1) {
                max2 = nums[i];
            }
        }
        if (max2 == Integer.MIN_VALUE)
            return -1;
        return max2;

    }
}
