
// Book Allocation — split books among m students, minimise the maximum pages
//   any one student gets. Books are allocated CONTIGUOUSLY, in the given order.
//
// THIS IS SPLIT-ARRAY-LARGEST-SUM IN DISGUISE (and that was ships in disguise):
//     student → piece → ship
//     pages   → sum   → weight
//     m       → k     → days
//
// "Minimise the MAXIMUM pages" — every allocation has a biggest pile; you want
//   the allocation whose biggest pile is smallest. So the answer goes DOWN.
//
//   CONTRAST WITH AGGRESSIVE COWS, which reads almost identically but is the
//   opposite: "maximum possible MINIMUM distance" — every arrangement has a
//   smallest gap, and you want that smallest gap to be BIG. Answer goes UP.
//     cows:  works → ans = mid; low  = mid + 1   (push up)
//     books: works → ans = mid; high = mid - 1   (push down)
//   The tell is which word comes LAST. After reading the question, say out loud
//   whether you want the answer big or small.
//
// Approach: binary search on the answer space — possible page limits.
//   range: max(nums) .. sum(nums)
//     below max(nums) no student could take the biggest book
//     at sum(nums) one student takes everything
//   test:  studentsNeeded(limit) <= m ?
// Time: O(n log(sum)), Space: O(1)
//
// DO NOT SORT. The allocation must be contiguous — students get consecutive
//   books in the given order. Sorting destroys that and changes the answer.
//   (Aggressive cows DOES sort, because distance only means anything in order.
//    Another place these two differ.)
//
// Impossible: m > nums.length means fewer books than students → -1.
//
// Helper: walk in order, adding to the current pile. When the next book would
//   push past the limit, that student is done and a new one starts with it.
//     `>` not `>=` — a pile hitting the limit exactly still fits.
//     students starts at 1 — the first student exists from the first book.
//   Name it for what it RETURNS. maxPages sounds like a page count; it returns
//   a STUDENT count.

class Solution {
    public int findPages(int[] nums, int m) {
        if (m > nums.length) return -1;

        int max = 0, sum = 0;
        for (int p : nums) {
            if (p > max) max = p;
            sum += p;
        }

        int low = max, high = sum;
        int ans = high;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (studentsNeeded(nums, mid) <= m) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    public static int studentsNeeded(int[] nums, int limit) {
        int students = 1;
        int pages = 0;
        for (int p : nums) {
            if (pages + p > limit) {
                students++;
                pages = p;
            } else {
                pages += p;
            }
        }
        return students;
    }
}