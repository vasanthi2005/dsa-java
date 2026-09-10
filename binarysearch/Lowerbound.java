// Lower bound — first index where nums[i] >= x (or nums.length if none)
// Approach: binary search for a BOUNDARY rather than an exact match. You can't
//           return on the first hit — there may be an earlier index that also
//           satisfies the condition. So record the candidate and keep searching
//           LEFT for a smaller one.
//             if (nums[mid] >= x) { ans = mid; high = mid - 1; }
//             else                { low = mid + 1; }
// Time: O(log n), Space: O(1)
//
// Why ans starts at nums.length: that's what the problem wants when nothing
//       satisfies the condition. If the loop never records anything, the
//       default stands.
//
// Why high = mid - 1 after a hit: mid works, but a smaller index might too.
//       Excluding mid is safe because it's already saved in ans.
//       [1,2,2,2,3] with x=2 → returns 1, not 2. Returning on the first match
//       would give the wrong answer.
//
// Note: mid, low, high and ans are POSITIONS. x and nums[mid] are VALUES.
//       The comparison is always nums[mid] vs x, never mid vs x.
//
// TEMPLATE for this section — "find the first/last thing satisfying a
//       condition" always has this shape: record a candidate, then shrink
//       toward the side you want. Upper bound, first/last occurrence, and
//       search-insert-position are all this with the condition changed.
package binarysearch;

public class Lowerbound {
    public int lowerBound(int[] nums, int x) {
       int low=0,high=nums.length-1;
       int ans=nums.length;
       while(low<=high)
       {
            int mid=(low+high)/2;
            if(nums[mid]>=x)
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
