// Split array largest sum — split nums into k pieces, minimise the largest
//   piece's sum
//
// THIS IS THE SHIP PROBLEM IN DISGUISE. Marked Hard on LeetCode; it's the same
//   code as the Medium one. Recognising the disguise is most of the difficulty.
//     ship          → piece
//     capacity      → largest allowed sum
//     days          → k
//   [7,2,5,10,8] with capacity 18 loads as (7,2,5) and (10,8) — exactly the
//   same cuts as splitting into two pieces of sums 14 and 18.
//
// Approach: BINARY SEARCH ON THE ANSWER SPACE — search over possible limits
//   for the largest piece. low and high hold SUMS, not indices.
//     range:  max(nums) .. sum(nums)
//       below max(nums), no piece could hold the largest element
//       at sum(nums), everything fits in one piece — nothing bigger helps
//     test:   piecesNeeded(limit) <= k ?
// Time: O(n log(sum)), Space: O(1)
//
// THE HELPER: walk in order, keeping a running total. When adding the next
//   element would exceed the limit, close that piece and start a new one with
//   that element.
//     if (sum + i > limit) { pieces++; sum = i; }
//     else                   sum += i;
//   pieces starts at 1, not 0 — you're filling the first piece from the very
//   first element. (Same as day = 1 in ships.)
//
// `<= k`, not `== k`. Fewer pieces than allowed is fine — you can always cut a
//   piece further to reach exactly k. Requiring equality rejects working limits.
//
// Why binary search works: a bigger limit never needs MORE pieces, so if a
//   limit works every larger one does. Lower-bound shape:
//     fits in k → record mid, try SMALLER → high = mid - 1
//     too many  → raise the limit         → low  = mid + 1
//
// Record mid (the limit), not the piece count.
//
// Name the helper for what it RETURNS — piecesNeeded, not maxSum. Calling it
//   maxSum invites grabbing the wrong variable, which is exactly the bug I hit
//   in smallest-divisor (ans = val instead of ans = mid).
//
// Watch for: `int low = max, high = sum = 0;` chains the assignment and sets
//   BOTH to 0, so the loop never runs.
public class SplitArrayLargestSum {
    public int splitArray(int[] nums, int k) {
        int max=0,sum=0;
        for(int i:nums)
        {
            if(max<i) max=i;
            sum+=i;
        }
        int low=max,high=sum;
        int ans=high;
        while(low<=high)
        {
            int mid=(low+high)/2;
            int val=piecesNeeded(nums,mid);
            if(val<=k)
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
    public static int piecesNeeded(int nums[],int maxsum)
    {
        int sum=0;
        int split=1;
        for(int i:nums)
        {
            if(sum+i>maxsum)
            {
                split++;
                sum=i;
            }
            else
            {
                sum+=i;
            }
        }
        return split;
    }
}
