 // ============================================================
// First and last occurrence of target in a sorted array
// ============================================================
// Approach: two binary searches using the same template.
//   lowerBound = first index where nums[i] >= target  → the FIRST occurrence
//   upperBound = first index where nums[i] >  target  → minus 1 gives the LAST
// They differ by one character: >= versus >.
// Time: O(log n), Space: O(1)
//
// Validity check: lowerBound returns the first index with a value >= target,
//   which may be past the end or hold something LARGER than target. Check
//   `lower >= nums.length || nums[lower] != target` before trusting it.
//
// THE TEMPLATE (both helpers, identical shape):
//   ans starts at nums.length — the value to return when nothing qualifies
//   on a HIT:  ans = mid; high = mid - 1;   record it, then look LEFT for
//                                           an earlier index that also works
//   on a MISS: low = mid + 1;               this position is too small
//
// Mistakes I made getting here:
//   - assigned to the RESULT variable (`lower`) when I meant to move the
//     SEARCH POINTER (`low`)
//   - swapped the pointer movements — searched right after a hit, left after
//     a miss. Backwards: a hit means look left for something better.
//   - started upperBound's default at -1 instead of nums.length. Breaks when
//     the target is the largest value: [5,7,8] target 8 → returns -2.
//   - wrote a correct LAST-OCCURRENCE search and named it upperBound, which
//     then broke the caller's -1. Make sure a method does what its name says.

 package binarysearch;
 class Solution {
    public int[] searchRange(int[] nums, int target) {
       int lower=lowerbound(nums,target);
       if(lower>=nums.length || nums[lower]!=target)
       return new int[]{-1,-1};
       int upper=upperbound(nums,target)-1;
       return new int[]{lower,upper};
    }
    public static int lowerbound(int nums[],int target)
    {
        int low=0,high=nums.length-1;
        int lower=nums.length;
        while(low<=high)
        {
            int mid=(low+high)/2;
            if(nums[mid]>=target)
            {
                lower=mid;
                high=mid-1;
            }
            else if(nums[mid]<target)
            {
                low=mid+1;
            }
        }
        return lower;
    }
    public static int upperbound(int nums[],int target)
    {
        int low=0,high=nums.length-1;
        int upper=nums.length;
        while(low<=high)
        {
            int mid=(low+high)/2;
            if(nums[mid]>target)
            {
                upper=mid;
                low=mid+1;
            }
            else 
            {
                high=mid-1;
            }
        }
        return upper;
    }
}