// Minimum number of days to make m bouquets
//
// UNDERSTANDING THE INPUT
//   bloomDay is a row of plants. INDEX = where the plant is, VALUE = the day it
//   flowers. Once flowered, it stays flowered.
//   A bouquet needs k flowered plants standing NEXT TO EACH OTHER. Each flower
//   can only go in one bouquet. Need m bouquets — what's the earliest day?
//
// Approach: BINARY SEARCH ON THE ANSWER SPACE — search over days.
//
//   1. Impossible check first: m bouquets of k flowers = m*k flowers total.
//      If the garden has fewer, no amount of waiting helps → return -1.
//      Use (long) m * k — both can be large and the product overflows int.
//
//   2. Range: min(bloomDay) .. max(bloomDay)
//      Before the first bloom, nothing has flowered — zero bouquets.
//      After the last, everything has — waiting longer changes nothing.
//      (Unlike Koko, where low was 1: a speed below the smallest pile is still
//       valid. Here a day before the first bloom is genuinely useless.)
//      Initialise min to Integer.MAX_VALUE — starting at 0 means nothing is
//      ever smaller, so it never changes.
//
//   3. Test: walk the row on day `mid` and count bouquets.
//        run      = flowers in your hand right now
//        bouquets = finished bouquets in your basket
//        plant flowered (d <= day) → run++; if run hits k, bouquets++, run = 0
//        not flowered              → run = 0 (gap breaks adjacency, drop hand)
//
//   4. Direction: flowers never un-bloom, so if day d works, every later day
//      works too. Lower-bound shape:
//        bouquets >= m → record mid, try EARLIER → high = mid - 1
//        not enough    → wait LONGER            → low  = mid + 1
//
// Time: O(n log(max - min)), Space: O(1)
//
// ans starts at high, not -1: the impossible case is already handled, so the
//   last day is guaranteed to work. The search only ever improves on it.
//
// MISTAKES I MADE — both comparisons backwards:
//   day <= d   should be   d <= day    ("this plant blooms by the day")
//   <= m       should be   >= m        ("at least m bouquets")
//   Check any comparison by reading it aloud as a sentence. If the sentence
//   doesn't match what you mean, it's the wrong way round.
class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        int min = 0, max = 0;
        for (int i = 0; i < bloomDay.length; i++) {
            if (bloomDay[i] < min)
                min = bloomDay[i];
            if (bloomDay[i] > max)
                max = bloomDay[i];
        }
        int low = min, high = max;
        int ans = high;
        if ((long) m * k > bloomDay.length)
            return -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (countbouquets(bloomDay, mid, k) >= m) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    public static int countbouquets(int bloomDay[], int day, int k) {
        int run = 0;
        int bouquets = 0;
        for (int d : bloomDay) {
            if (d <= day) {
                run++;
                if (run == k) {
                    bouquets++;
                    run = 0;
                }
            } else {
                run = 0;
            }
        }
        return bouquets;
    }
}