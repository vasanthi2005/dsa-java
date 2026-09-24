// Capacity to ship packages within D days
// Approach: BINARY SEARCH ON THE ANSWER SPACE — search over ship capacities.
//   `days` is the LIMIT you're given; capacity is what you're solving for.
//
//   THE SHOP PICTURE: you're buying a ship. You can't walk in and say "give me
//   the ship for 3 days" — nobody can answer that. You point at a ship size and
//   the shopkeeper tells you how many days it'd take. Then YOU decide if that's
//   good enough. Binary search just makes you clever about which ships you
//   point at.
//     mid    = the ship you're pointing at
//     helper = the shopkeeper
//     ans    = best ship found so far
//
//   Range: max(weights) .. sum(weights)
//     below max(weights) the heaviest package never fits — impossible, not
//     merely slow
//     at sum(weights) everything goes in one trip — nothing bigger helps
//   Test: daysNeeded(capacity) <= days ?
//
// Time: O(n log(sum)), Space: O(1)
//
// THE HELPER: walk the packages IN ORDER (they can't be rearranged), keeping a
//   running load.
//     if (load + w > capacity) { day++; load = w; }   would overflow → ship
//                                                      sails, this package
//                                                      starts the next load
//     else                       load += w;            fits → put it on
//   Check the total AFTER adding, before committing — weigh the bag before
//   dropping the tin in.
//   `>` not `>=` — a package that fills the ship exactly still fits.
//   day starts at 1 — you're loading a ship from the very first package.
//   The helper does NOT take `days`. Its only job is capacity → days. The
//   comparison against the limit happens in the search.
//
// Why binary search works: a bigger ship never needs MORE days, so if capacity
//   c works, everything above it works too. One block — find where it starts.
//     fits in time → record mid, try SMALLER → high = mid - 1
//     too slow     → need a bigger ship      → low  = mid + 1
//
// Record mid (a capacity), not the day count.
//
// vs the bouquet helper: same shape — one pass, a running total that resets —
//   but there a gap DISCARDS the run (run = 0), here the package that didn't
//   fit CARRIES OVER (load = w). And bouquets start at 0 while days start at 1.
class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int sum=0;
        int max=0;
        for(int i:weights)
        {
            if(max<i) max=i;
            sum+=i;
        }
        int low=max,high=sum;
        int ans=high;
        while(low<=high)
        {
            int mid=(low+high)/2;
            int val=capacity(weights,mid);
            if(val<=days)
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
    public static int capacity(int weights[],int capacity)
    {
      int load=0;
      int days=1;
      for(int w:weights)
      {
        if(load+w>capacity)
        {
            days++;
            load=w;
        }
        else
        {
            load+=w;
        }
      } 
      return days; 
    }
}