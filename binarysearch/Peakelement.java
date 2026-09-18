// Find peak element — strictly greater than both neighbours, any peak will do
// Approach: binary search on the SLOPE, not on values. The array isn't sorted,
//   and it doesn't need to be — binary search only needs a rule that reliably
//   eliminates half the range.
//     nums[mid] < nums[mid+1]  → uphill  → a peak exists to the RIGHT
//     nums[mid] > nums[mid+1]  → downhill → a peak is HERE or to the LEFT
// Time: O(log n), Space: O(1)
//
// Why uphill guarantees a peak to the right: you can't walk uphill forever
//   because the array ends. Either it keeps rising to the last element (which
//   is a peak, since nums[n] counts as -infinity), or it turns over somewhere,
//   and the element before that drop is a peak. No third option.
//   Mirror image on the downhill side.
//
// while (low < high), not <= — a peak is GUARANTEED to exist, so you're
//   converging on its position rather than testing candidates that might fail.
//   Same family as single-element-in-sorted-array and find-minimum-in-rotated.
//   With <= the loop would run with low == high and spin forever.
//
// high = mid, not mid - 1 — mid itself might be the peak.
//
// KEY TAKEAWAY: binary search doesn't require sorted data. It requires a
//   condition that lets you discard half the range with certainty.
class Solution {
    public int findPeakElement(int[] nums) {
        int low=0,high=nums.length-1;
        while(low<high)
        {
            int mid=(low+high)/2;
            if(nums[mid]<nums[mid+1])
            low=mid+1;
            else
            high=mid;
        }
        return low;
    }
}