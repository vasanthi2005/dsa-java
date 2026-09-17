// Search in rotated sorted array
// Approach: binary search, but the ordinary rule doesn't work. In a sorted
//   array you pick a side with `target < nums[mid]`. After rotation that's
//   wrong — [4,5,6,7,0,1,2] with target 1: 1 < 7 says "go left", but 1 is on
//   the right. Values smaller than nums[mid] exist on BOTH sides of the break.
//
// So you need a different rule for choosing a side, and the only reliable one
//   requires a genuinely sorted stretch: "is the target between this range's
//   first and last value?" That question is only meaningful where the values
//   actually ascend — across the break they don't.
//
// KEY INSIGHT: rotation puts exactly ONE break in the array. So wherever you
//   split, the break lands in one half and the OTHER half is still properly
//   sorted. At least one side is always trustworthy.
//
// Each iteration:
//   1. Which half is sorted?  nums[low] <= nums[mid] → left, else → right
//      (this isn't asking where the target is — it's finding the half you're
//       ALLOWED to reason about)
//   2. Range-test the target against that sorted half
//   3. Inside it → search there.  Not inside → it must be in the other half,
//      since the target is somewhere.
//
// Time: O(log n), Space: O(1) — still halving the range every step
//
// Boundary details: target >= nums[low] is inclusive (a real unchecked
//   element), target < nums[mid] is strict (nums[mid] was already tested for
//   equality at the top). Mirrored on the right side.
//
// mid must be computed INSIDE the loop — the range shrinks every iteration,
//   so its midpoint moves. Computed once, it would never change and the search
//   would spin forever.

class Solution {
    public int search(int[] nums, int target) {
    int low = 0, high = nums.length - 1;
    while (low <= high) {
        int mid = (low + high) / 2;
        if (nums[mid] == target) return mid;

        if (nums[low] <= nums[mid]) {              // left half sorted
            if (target >= nums[low] && target < nums[mid]) high = mid - 1;
            else low = mid + 1;
        } else {                                   // right half sorted
            if (target > nums[mid] && target <= nums[high]) low = mid + 1;
            else high = mid - 1;
        }
    }
    return -1;
}
    }
