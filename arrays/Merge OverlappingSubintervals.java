// Merge overlapping intervals
// Approach: sort by start time, then walk through comparing each interval
//           against the LAST one kept. If it starts after that one ended,
//           there's a gap — add it as a new entry. Otherwise they overlap,
//           so extend the last entry's end instead of adding anything.
// Time: O(n log n) dominated by the sort, Space: O(n) for the output
// Note: sorting by start is what makes comparing against only the last kept
//       interval sufficient — anything overlapping an earlier one must also
//       overlap the most recent one.
//       The else branch MODIFIES (set), it does not add. Adding would leave
//       the un-extended original in the result.
//       Math.max on the ends, not just the new end: [1,10] followed by [2,3]
//       would otherwise shrink to [1,3] and lose coverage.
//       new ArrayList<>(interval) copies, so set() later doesn't mutate the
//       input and doesn't hit fixed-size list restrictions.
package arrays;

public class Merge OverlappingSubintervals
{

    public List<List<Integer>> mergeOverlap(List<List<Integer>> intervals) {
        List<List<Integer>> result = new ArrayList<>();
        intervals.sort((a, b) -> a.get(0) - b.get(0));

        for (List<Integer> interval : intervals) {
            if (result.isEmpty() || interval.get(0) > result.get(result.size() - 1).get(1)) {
                result.add(new ArrayList<>(interval));
            } else {
                List<Integer> last = result.get(result.size() - 1);
                last.set(1, Math.max(last.get(1), interval.get(1)));
            }
        }
        return result;
    }
}
