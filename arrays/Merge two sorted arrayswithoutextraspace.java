// Merge sorted array (nums1 has m+n slots, last n are padding)
// Approach: fill from the BACK. Three pointers — left at m-1, right at n-1,
//           i at m+n-1. Take the larger of the two and write it at i, moving
//           all three inward.
// Time: O(m+n), Space: O(1) — merges in place
// Why backwards: the tail of nums1 is empty padding, so writing there destroys
//       nothing. Filling forwards would overwrite values still needed.
//       No swapping for the same reason — the slot at i holds a worthless zero.
// Only ONE drain loop needed. If nums1 still has elements when the main loop
//       ends, they're already in their correct positions. Only nums2's
//       leftovers need copying across.
// Note: both right AND i must decrement in the drain — one reads, one writes.
package arrays;

public class Merge
two sorted arrayswithoutextraspace
{

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int left = m - 1, right = n - 1;
        int i = m + n - 1;
        while (left >= 0 && right >= 0) {
            if (nums1[left] > nums2[right]) {
                nums1[i] = nums1[left];
                left--;

            } else {
                nums1[i] = nums2[right];
                right--;
            }
            i--;
        }
        while (right >= 0) {
            nums1[i] = nums2[right];
            right--;
            i--;
        }

    }

}
