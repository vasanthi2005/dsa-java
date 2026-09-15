// Search in rotated sorted array II — duplicates allowed
// Approach: same as version I — find which half is sorted, range-test the
//   target against it — plus one extra case that duplicates create.
//
// THE NEW PROBLEM: when nums[low] == nums[mid] == nums[high], you cannot tell
//   which half is sorted. [3,1,2,3,3,3,3] — all three endpoints are 3, and
//   `nums[low] <= nums[mid]` says "left is sorted", but the left half is
//   [3,1,2,3] which clearly isn't. Equal endpoints hide a dip between them.
//
// THE FIX: detect that case and shrink BOTH ends by one, then continue.
//     if (nums[low] == nums[mid] && nums[mid] == nums[high]) {
//         low++; high--; continue;
//     }
//   Safe to discard them: nums[mid] was already tested for equality, and both
//   endpoints hold that same value, so neither can be the answer.
//   Only two elements are dropped, not half — with identical endpoints there's
//   no information about where the break is, so that's all you can justify.
//
// CONSEQUENCE: worst case becomes O(n), not O(log n). [3,3,3,3,3,3,1] forces
//   peeling one element off each end repeatedly. Duplicates destroy the
//   guarantee that the endpoints reveal the structure, and no cleverness gets
//   it back — the information isn't there.
//   Average case is still O(log n); the shrink branch rarely fires.
//
// Boundary conditions — inclusive against the far end, STRICT against mid
//   (mid was already checked for equality at the top):
//     left sorted:   nums[low] <= k  &&  k <  nums[mid]
//     right sorted:  nums[mid] <  k  &&  k <= nums[high]
//
// Space: O(1)
package binarysearch;

public class Sortedarray2 {
    public boolean search(int[] nums, int target) {
        int low=0,high=nums.length-1;
      while(low<=high)
      {
        int mid=(low+high)/2;
        if(nums[mid]==target) return true;
        if(nums[low]==nums[mid] && nums[mid]==nums[high])
        {
            low++;
            high--;
            continue;
        }
        if(nums[low]<=nums[mid])
        {
            if(nums[low]<=target && nums[mid]>target)
            high=mid-1;
            else
            low=mid+1;
        }
        else{
            if(nums[mid]<target && nums[high]>=target )
            low=mid+1;
            else
            high=mid-1;
        }
      }
      return false;
    }
}
