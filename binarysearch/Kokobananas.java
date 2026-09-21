// Koko eating bananas — minimum speed k to finish all piles within h hours
// Approach: BINARY SEARCH ON THE ANSWER SPACE. Search over possible speeds,
//   not over the array.
//     range:  1 .. max(piles)
//       speed 1 is the slowest meaningful speed — one banana an hour
//       at max(piles) every pile takes exactly one hour, the fastest possible,
//       so nothing above it can help
//     test:   total hours = sum of ceil(piles[i] / k)  <=  h ?
// Time: O(n log max), Space: O(1)
//
// Why ceil: each hour she eats from ONE pile only. If a pile has fewer than k
//   left, she finishes it and waits out the rest of the hour — she can't move
//   to another pile. So a pile of 7 at speed 4 takes 2 hours, not 1.75.
//
// Why binary search works: a faster speed never takes MORE hours. If speed k
//   finishes in time, every k above it does too — the feasible speeds form one
//   block, and you want where it starts. Lower-bound shape:
//     finishes in time → record mid, search LEFT for a slower speed
//     too slow         → search RIGHT
//
// low = 1, not min(piles) — the smallest pile doesn't limit the speed. A speed
//   below it is a valid candidate, just a slower one.
//
// Record mid (the speed), not the total hours.
//
// SAME PROBLEM as smallest-divisor-given-a-threshold — different story,
//   identical code. Sum of ceilings, compared to a limit, find the smallest
//   value that fits.
//
// Watch for overflow on the hours total if piles are large — use long.
public class Kokobananas {
public int minEatingSpeed(int[] piles, int h) {
       int max=0;
       for(int i:piles)
       {
        if(i>max) max=i;
       }
       int low=1,high=max;
       int ans=0;
       while(low<=high)
       {
        int mid=(low+high)/2;
        int val=eatingRate(piles,mid);
        if(val<=h)
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
    public static int eatingRate(int []p,int m)
    {
        int sum=0;
        for(int i=0;i<p.length;i++)
        {
            sum+=Math.ceil((double)p[i]/m);
        }
        return sum;
    }
    
}
