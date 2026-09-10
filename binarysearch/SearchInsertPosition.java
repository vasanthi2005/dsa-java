// Search insert position — index of target, or where it would be inserted
// Approach: standard binary search, returning `low` when the target isn't
//           found. The loop exits when low > high, and at that moment `low`
//           has been pushed past everything smaller than the target while
//           `high` has been pulled back past everything larger — so `low` is
//           exactly the insertion point.
// Time: O(log n), Space: O(1)
//
// [1,3,5,6] with target 2 → returns 1. Inserting there gives [1,2,3,5,6].
//
// Note: this is LOWER BOUND in disguise. "Where does it belong" and "first
//       index where nums[i] >= target" are the same question, so the lower
//       bound code solves this unchanged. Several problems in this section
//       are the same search wearing different words — worth spotting rather
//       than re-deriving each one.
package binarysearch;

public class SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        int low=0,high=nums.length-1;
        while(low<=high)
        {
            int mid=(low+high)/2;
            if(nums[mid]>target)
            high=mid-1;
            else if(nums[mid]<target)
            low=mid+1;
            else
            return mid;
        }
        return low;
        
    }
}
