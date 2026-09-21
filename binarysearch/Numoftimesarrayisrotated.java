// Find how many times a sorted array has been rotated
// Approach: the rotation count IS the index of the minimum element, so this is
//   find-minimum-in-rotated-array with the position tracked alongside the value.
//     left half sorted (nums[low] <= nums[mid]):
//         nums[low] is the smallest in that half — record (value, index), then
//         search RIGHT, because the break must be over there
//     left half not sorted:
//         the break is in the left half, so nums[mid] is the candidate —
//         record it, then search LEFT
// Time: O(log n), Space: O(1)
//
// Why index == rotation count: each RIGHT rotation shifts every element one
//   place right. The minimum starts at index 0 in the unrotated array, so after
//   k rotations it sits at index k.
//     [1,2,3,4,5] → [5,1,2,3,4] → [4,5,1,2,3] → [3,4,5,1,2]
//     minimum 1 moves 0 → 1 → 2 → 3
//   An unrotated array returns 0, which is correct.
//
// Math.min can't be used — it returns the smaller VALUE and discards which
//   argument it came from. When you need the minimum AND its position, use an
//   explicit if that updates both together.
//   (Third occurrence: majority element needed count + candidate, Kadane's
//   extension needed max + start/end indices.)
//
// Every branch moves a pointer PAST mid (mid+1 or mid-1), never onto it —
//   high = mid leaves the range unchanged when low and high are adjacent.

class Solution {
    public int findKRotation(int[] nums) {
        int low = 0, high = nums.length - 1;
        int min = Integer.MAX_VALUE;
        int index = -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (nums[low] <= nums[mid]) {
                if (nums[low] < min) {
                    min = nums[low];
                    index = low;
                }
                low = mid + 1;
            } else {
                if (nums[mid] < min) {
                    min = nums[mid];
                    index = mid;
                }
                high = mid - 1;
            }
        }
        return index;
    }
}
