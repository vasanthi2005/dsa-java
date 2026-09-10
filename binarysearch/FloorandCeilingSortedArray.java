// Floor and ceil of x in a sorted array
//   floor = largest value <= x,  ceil = smallest value >= x,  -1 if none
// Approach: one binary search tracking both. Exact match returns immediately —
//           if x is present it is both the floor and the ceil. Otherwise
//           exactly one branch runs: too small makes it a floor candidate and
//           you search right; too large makes it a ceil candidate and you
//           search left.
// Time: O(log n), Space: O(1)
//
// Note: this problem asks for VALUES, unlike lower/upper bound which ask for
//       indices — so store nums[mid], not mid.
//       Branches must be exclusive (else-if). Two separate ifs both fire on an
//       exact match, collapsing the range from both sides at once. Same family
//       as the Dutch flag and Moore's voting bugs.
package binarysearch;

public class FloorandCeilingSortedArray {
     public int[] getFloorAndCeil(int[] nums, int x) {
       int floor=-1,ceil=-1;
       int low=0,high=nums.length-1;
       while(low<=high)
       {
            int mid=(low+high)/2;
            if (nums[mid] == x) 
            {
                return new int[]{nums[mid], nums[mid]};
            } 
            else if (nums[mid] < x) 
            {
                floor = nums[mid];
                low = mid + 1;
            } 
            else 
            {
                ceil = nums[mid];
                high = mid - 1;
            }
       }
       return new int[]{floor,ceil};
    }
}
