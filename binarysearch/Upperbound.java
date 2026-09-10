// Upper bound — first index where nums[i] > x (or nums.length if none)
// Approach: identical to lower bound, with a strict > instead of >=. Record
//           the candidate and keep searching LEFT for a smaller index.
// Time: O(log n), Space: O(1)
// Note: lower bound uses >=, upper bound uses >. That single character is the
//       whole difference.
//       [1,2,2,3] with x=2 → lower bound 1, upper bound 3. The gap between
//       them (3-1 = 2) is the number of occurrences of x — which is why the
//       two are often used as a pair.
package binarysearch;
public class Upperbound {
    public int upperBound(int[] nums, int x) {
        int low=0,high=nums.length-1;
        int ans=nums.length;
        while(low<=high)
        {
            int mid=(low+high)/2;
            if(nums[mid]>x)
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