// Next permutation (in-place, O(1) extra space)
// Approach: three steps.
//   1. From the right, find the first index where nums[i] < nums[i+1] — the
//      pivot. Everything after it is descending, so already at its maximum.
//   2. From the right, find the first element bigger than nums[pivot] and swap.
//      Because the tail is descending, the first one found is the SMALLEST
//      element still greater than the pivot — the minimum possible increase.
//   3. The tail is still descending (at its largest), so reverse it to get the
//      smallest arrangement after the increase.
// Time: O(n), Space: O(1)
// Note: pivot == -1 means the whole array is descending — the last permutation.
//       Reverse the whole thing to wrap around to the first.
//       Change as far RIGHT as possible: that's what makes it the NEXT
//       permutation rather than a larger jump.
package arrays;

public class Nextpermutation {
    public void nextPermutation(int[] nums) {
        int pivot = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                pivot = i;
                break;
            }
        }
        if (pivot == -1) {
            reverse(nums, 0, nums.length - 1);
            return;
        }
        for (int i = nums.length - 1; i > pivot; i--) {
            if (nums[i] > nums[pivot]) {
                int temp = nums[i];
                nums[i] = nums[pivot];
                nums[pivot] = temp;
                break;
            }
        }
        reverse(nums, pivot + 1, nums.length - 1);

    }

    public static void reverse(int nums[], int low, int high) {
        while (low < high) {
            int temp = nums[low];
            nums[low] = nums[high];
            nums[high] = temp;
            low++;
            high--;
        }
    }
}
