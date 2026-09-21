// Smallest divisor given a threshold
// Approach: BINARY SEARCH ON THE ANSWER SPACE. You're not searching the array —
//   you're searching over possible divisors.
//     range:  1 .. max(nums)
//       divisor 1 gives the largest possible sum
//       divisor = max(nums) makes every element round up to 1, the smallest
//       possible sum — nothing larger can do better
//     test:   sum of ceil(nums[i] / divisor) across the array <= threshold?
// Time: O(n log max), Space: O(1)
//
// Why binary search works: a LARGER divisor always gives a SMALLER sum. So if
//   one divisor works, every bigger one works too — the feasible divisors form
//   a single block, and you want where it begins. That's lower-bound shape:
//     works        → record mid, then search LEFT for a smaller one
//     sum too big  → divisor too small, search RIGHT
//
// Record mid, NOT the sum. The question asks for the divisor. Returning the sum
//   gives 7 on example 1 instead of 3. Same slip as returning the count instead
//   of the element in highest-occurring-element.
//
// Rounding up: integer division truncates, so 7/3 is already 2 before
//   Math.ceil sees it — and ceil(2) is 2. Cast an operand first:
//       (int) Math.ceil((double) nums[i] / divisor)
//   Same rule as the overflow bugs: operand types decide how the arithmetic
//   happens, not what you do afterwards.
//   Integer-only alternative: (nums[i] + divisor - 1) / divisor
class Solution {
    public int smallestDivisor(int[] nums, int threshold) {
        int max = 0;
        for (int num : nums) 
        {
            if (num > max) max = num;
        }
       int low=1,high=max;
       int ans=0;
       while(low<=high)
       {
        int mid=(low+high)/2;
        int val=division(nums,mid);
        if(val<=threshold)
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
    public int division(int nums[],int divisor)
    {
        int sum=0;
        for(int i=0;i<nums.length;i++)
        {
            sum+=Math.ceil((double)nums[i]/divisor);
        }
        return sum;
    }
    
}