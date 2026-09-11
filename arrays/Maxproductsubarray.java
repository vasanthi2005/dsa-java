// Maximum product subarray
// Approach: track TWO running values at each position —
//     max = best product of a subarray ending here
//     min = worst product of a subarray ending here
//   plus `result` for the best seen anywhere.
//
// WHY min IS TRACKED: with sums, a negative prefix only drags you down, so
//   Kadane's resets on it. With products a large NEGATIVE is valuable — one
//   more negative flips it to a large positive.
//     [-2, 3, -4]: best ending at 3 is 3, so 3 * -4 = -12. Bad.
//                  worst ending at 3 is -6, so -6 * -4 = 24. That's the answer.
//   Kadane's would have discarded the -2 and found only 3.
//
// THREE CANDIDATES at each element, for both max and min:
//     nums[i]            start fresh from here
//     max * nums[i]      extend the best run
//     min * nums[i]      extend the worst run  <- flips sign on a negative
//   New max = largest of the three. New min = smallest of the same three.
//   The calculations CROSS OVER: when nums[i] is negative, multiplying by the
//   min gives the max. Omitting the min term is the mistake that makes this
//   look like Kadane's and fail.
//
// WHY prevMax: computing the new min needs the OLD max, but the line above has
//   already overwritten it. Without saving, the min line multiplies by the
//   brand-new max, producing a value that corresponds to no real subarray.
//     [-2,3,-4] at i=-4:  correct min is -12, unsaved gives -96.
//   Same shape as needing `temp` in a swap — one value is destroyed before the
//   other can use it.
//
// Zeros need no special case: multiplying by 0 sets both max and min to 0,
//   which acts as a natural reset. The `nums[i]` candidate then restarts the
//   run from the next element.
//
// Time: O(n), Space: O(1)
// Note: all three of max, min and result start at nums[0], and the loop starts
//   at index 1 — a single-element array must return that element.
package arrays;

public class Maxproductsubarray {
    public int maxProduct(int[] nums) {
        int max=nums[0];
        int min=nums[0];
        int result=nums[0];

        for(int i=1;i<nums.length;i++)
        {
            int prevmax=max;
            max=Math.max(nums[i],Math.max(max*nums[i],min*nums[i]));
            min=Math.min(nums[i],Math.min(prevmax*nums[i],nums[i]*min));
            result=Math.max(result,max);
        }
        return result;
    }
    
}
