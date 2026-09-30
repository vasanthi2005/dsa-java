// Minimise the maximum distance to a gas station
//
// THE PICTURE: pumps on a road with gaps between them. The WORST gap is the
//   problem. You get k new pumps to place anywhere — where do they go to make
//   that worst gap as small as possible?
//
//   Flip the question to make it searchable: instead of "where do I put them",
//   ask "could I make every gap at most X?" Then binary search over X.
//
// Approach: BINARY SEARCH ON THE ANSWER SPACE — over possible max distances.
//   range: 0 .. the biggest EXISTING gap
//     0 is the floor — distance can't be negative
//     the biggest existing gap is the worst case with zero pumps added. Adding
//     pumps only ever improves things, so the answer never exceeds it.
//   test:  pumpsNeeded(dist) <= k ?
//
// "Find the MINIMUM value of dist", where dist is the MAXIMUM distance →
//   minimise a maximum → answer goes DOWN. Same direction as books/ships.
//   (NOT the same as aggressive cows, which maximises a minimum and goes UP.
//    These two read almost identically — check which word comes last.)
//
// THE HELPER: for each existing gap, how many pieces must it be cut into so
//   none exceeds dist? Then pumps = pieces - 1, because pumps sit BETWEEN
//   pieces.
//     gap 7, dist 2.5 → ceil(7/2.5) = 3 pieces → 2 pumps
//     |-------|-------|-------|
//     0     2.33    4.67      7
//   A gap already smaller than dist gives 1 piece and 0 pumps.
//
//   WHY EVEN SPACING: pumps can go anywhere, but even is always optimal. You
//   only care about the WORST piece. Bunching them up makes some pieces tiny —
//   which buys nothing, those were never the problem — while leaving one huge.
//
// THE DECIMAL LOOP — this is what's new:
//     while (high - low > 1e-6)
//   1e-6 is 0.000001. The problem accepts answers within that, so once the
//   range is narrower than 1e-6, any value in it is close enough.
//
//   Can't use low <= high — with decimals the pointers NEVER cross. You can
//   always halve again, forever.
//
//     high = mid   NOT  mid - 1
//   There's no "next number down" for a decimal. What's just below 2.5? 2.49?
//   2.4999? No answer. So the pointer lands ON mid and the range halves.
//
//   NO ans VARIABLE — the range converges on the answer, so high holds it.
//
//   RETURN high, because high always holds a distance PROVED to work (it's
//   only ever set when the helper said yes). low holds distances that failed.
//
// Time: O(n log(maxGap / 1e-6)), Space: O(1)
public class Minimisethemaxdist {
    public double minimiseMaxDistance(int[] arr, int k) {

    // 1. The biggest gap that already exists — that's the worst case, and
    //    our starting upper bound. Without adding any pump, the answer is this.
    double high = 0;
    for (int i = 1; i < arr.length; i++) {
        high = Math.max(high, arr[i] - arr[i - 1]);
    }

    double low = 0;

    // 2. Squeeze the range until it's tiny. Can't use low <= high with
    //    decimals — they'd never land exactly equal, so it would never stop.
    while (high - low > 1e-6) {
        double mid = (low + high) / 2;

        if (pumpsNeeded(arr, mid) <= k) {
            high = mid;      // this distance is achievable → try smaller
        } else {
            low = mid;       // needs too many pumps → aim bigger
        }
    }
    return high;
}

// Given a target distance, how many new pumps would I have to place?
static int pumpsNeeded(int[] arr, double dist) {
    int count = 0;
    for (int i = 1; i < arr.length; i++) {
        int gap = arr[i] - arr[i - 1];

        // how many PIECES do I need to break this gap into?
        // then pumps = pieces - 1
        int pieces = (int) Math.ceil(gap / dist);
        count += pieces - 1;
    }
    return count;
}
}
