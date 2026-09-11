
// ============================================================
// Count occurrences of target in a sorted array
// ============================================================
// Approach: same two searches. The count is the gap between the bounds.
//     count = last - first + 1
//   which is also just upperBound - lowerBound, since
//     (upper - 1) - lower + 1 = upper - lower
// Time: O(log n), Space: O(1) — beats a linear scan's O(n)
//
// Note: the +1 is the inclusive-range count — positions first through last,
//   both endpoints included. Same rule as mid - left + 1 in count-inversions.
//   Return 0 when the target is absent, not -1 — it's a count.
package binarysearch;
class Solution {
    public int countOccurrences(int[] arr, int target) 
    {
        int lower=lowerbound(arr,target);
        if(lower>=arr.length || arr[lower]!=target)
        return 0;
        int upper=upperbound(arr,target)-1;
        return (upper-lower+1);
        
    }
    public static int lowerbound(int nums[],int target)
    {
        int low=0,high=nums.length-1;
        int ans=nums.length;
        while(low<=high)
        {
            int mid=(low+high)/2;
            if(nums[mid]>=target)
            {
                ans=mid;
                high=mid-1;
            }
            else
            {
                low=mid+1;
            }
            
        }
        return ans;
    }
    public static int upperbound(int nums[],int target)
    {
        int low=0,high=nums.length-1;
        int ans=nums.length;
        while(low<=high)
        {
            int mid=(low+high)/2;
            if(nums[mid]>target)
            {
                ans=mid;
                high=mid-1;
            }
            else
            {
                low=mid+1;
            }
        }
        return ans;
    }
}
