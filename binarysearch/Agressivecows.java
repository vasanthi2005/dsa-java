// Aggressive cows — place k cows in stalls so the MINIMUM gap between any two
//   is as LARGE as possible
//
// "Maximum possible minimum" means two steps: for any arrangement, find its
//   smallest gap; then across all arrangements, take the largest of those.
//   [0,3,7,10] has gaps 3, 4, 3 → its minimum is 3. The 4 doesn't help — one
//   far-apart pair doesn't matter if another pair is close.
//
// Approach: BINARY SEARCH ON THE ANSWER SPACE — search over possible minimum
//   gaps. FIRST "maximise the minimum" problem, so the directions are MIRRORED
//   from the earlier ones:
//     works → record mid, try BIGGER  → low  = mid + 1
//     fails → try smaller             → high = mid - 1
//   (Ships, Koko, divisor, bouquets all minimised, so they went the other way.)
//
//   range:  1 .. nums[nums.length-1] - nums[0]
//           the whole span — the furthest two cows could ever be apart
//   test:   with this minimum gap, can I fit at least k cows?
//
// Time: O(n log(span)) plus O(n log n) to sort.  Space: O(1)
//
// SORT FIRST — the stalls come jumbled, and distance only means anything along
//   a line. Note this modifies the caller's array.
//
// THE HELPER: greedy walk. Place the first cow at the first stall, then place
//   one at every stall at least `mid` from the last cow placed.
//   Greedy is safe: placing each cow as early as possible leaves the most room
//   for the rest. Skipping a valid stall can only ever fit fewer.
//   count starts at 1 and the loop starts at i=1 — the first cow is already
//   placed, and checking stall 0 against itself is a wasted comparison.
//   `>=` not `>` — a gap larger than required is fine; you only need none
//   smaller.
//
// Test is `>= k`, not `== k` — fitting more cows than needed at this spacing
//   is fine.
//
// WATCH: high must be nums[nums.length-1] - nums[0], the last VALUE minus the
//   first. Using nums.length-1 gives the last INDEX. [1,1000] with k=2 would
//   set high to 1 when the answer is 999.
class Solution {
    public int aggressiveCows(int[] nums, int k) {
        Arrays.sort(nums);
        int low=1,high=nums[nums.length-1]-nums[0];
        int ans=0;
        while(low<=high)
        {
            int mid=(low+high)/2;
            if(placeHolder(nums,mid)>=k)
            {
                ans=mid;
                low=mid+1;
            }
            else
            {
                high=mid-1;
            }

        }
        return ans;
   
    }
    public static int placeHolder(int nums[],int mid)
    {
        int count=1;
        int last=nums[0];
        for(int i=1;i<nums.length;i++)
        {
            if(nums[i]-last>=mid)
            {
                count++;
                last=nums[i];
            }
        }
        return count;
    }
}
