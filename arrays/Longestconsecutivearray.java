// Longest consecutive sequence
// Approach: put everything in a HashSet for O(1) lookups, then for each value
//           check whether it STARTS a sequence — i.e. whether num-1 is absent.
//           Only then walk forward (num+1, num+2, ...) counting the run.
// Time: O(n), Space: O(n)
// Why it's O(n) despite the nested loop: the inner walk only runs for sequence
//           starters, and each element is visited by exactly one such walk, so
//           the total inner work across the whole run is O(n), not O(n²).
//           Without the num-1 check it WOULD be O(n²) — every element would
//           re-walk its whole sequence.
// Note: the set deduplicates for free, so repeated values need no handling.
//       Iterate the SET, not the array — with duplicates the array repeats work.
//       Need a separate `current` variable to walk: the loop variable can't
//       advance, and a while whose condition doesn't consult the moving
//       variable never terminates.
// TreeSet alternative: dedups AND sorts, so you just count runs in order —
//       simpler but O(n log n). This problem exists to teach the O(n) trick.
package arrays;

class Longestconsecutivearray {
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        int max = 0;
        for (int i : nums) {
            set.add(i);
        }
        for (int i : set) {
            if (!set.contains(i - 1)) {
                int count = 1;
                int current = i;
                while (set.contains(current + 1)) {
                    count++;
                    current++;
                }
                if (max < count)
                    max = count;
            }

        }
        return max;

    }
}