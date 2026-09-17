// Find minimum in rotated sorted array
// Approach: binary search using the same "which half is sorted" idea as the
//   rotated search problem. The minimum sits exactly at the rotation point, so
//   you move toward whichever half contains the break.
//     left half sorted (nums[low] <= nums[mid]):
//         nums[low] is the smallest thing in that half — record it, then
//         search RIGHT, because the break must be over there
//     left half not sorted:
//         the break is in the left half, so nums[mid] is the best candidate
//         so far — record it, then search LEFT
// Time: O(log n), Space: O(1)
//
// Why a sorted half only contributes its FIRST element: it has no break, so
//   its smallest value is at its left end. Nothing smaller can be hiding in it.
//
// Math.min rather than plain assignment — later candidates can be larger than
//   one already found. [6,7,0,1,2] records 0 first, then 6, then 7.
//
// Every branch must move a pointer PAST mid (mid+1 or mid-1), never onto it.
//   high = mid leaves the range unchanged when low and high are adjacent →
//   infinite loop. Safe to exclude mid because it's already recorded in min.
class Solution {
    public int findMin(int[] nums) {
        int low = 0, high = nums.length - 1;
        int min = Integer.MAX_VALUE;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[low] <= nums[mid]) {
                min = Math.min(min, nums[low]);
                low = mid + 1;
            } else {
                min = Math.min(min, nums[mid]);
                high = mid - 1;
            }
        }
        return min;
    }
}