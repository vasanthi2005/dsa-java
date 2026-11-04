// Combination Sum — all combinations of candidates summing to target,
//   each number reusable any number of times
//
// Same TAKE/SKIP branching as subsets, with ONE change that allows reuse:
//
//     TAKE → stay at i:   build(..., i,     sum + arr[i], ...)
//     SKIP → move to i+1: build(..., i + 1, sum,          ...)
//
//   THE TAKE BRANCH DOESN'T INCREMENT. Moving to i+1 after taking a number
//   would mean never using it again. Staying at i lets the same number be
//   picked again on the next level — that's the reuse.
//   (I had this backwards at first: thought the SKIP branch was the one that
//    stays put.)
//
//   SKIP always advances. Once you decide not to use a number you never come
//   back to it — that's what stops duplicates like [2,3] and [3,2] both
//   appearing.
//
// COLLECTING SHAPE (not counting, not deciding): void method, shared
//   List<List<Integer>>, plus a `current` list with the add / recurse /
//   remove undo.
//
// PRUNING — stop early when the branch can't work:
//     if (sum == target) { result.add(new ArrayList<>(current)); return; }
//     if (sum > target || i == arr.length) return;
//   All candidates are positive, so once sum passes target it only grows.
//   Cutting dead branches off early is what keeps backtracking affordable.
//
// new ArrayList<>(current) — PHOTOCOPY THE PAGE.
//   `current` is one notebook you keep rubbing out and rewriting.
//   result.add(current) files a note saying "see the notebook" — do that
//   twice and both entries point at the SAME list, which is empty by the end.
//   You'd get [[], []] instead of [[2,2,3], [7]].
//   The copy is frozen at that moment; the notebook carries on changing.
//   (Strings don't need this — s + "0" already makes a new one. Lists change
//    in place.)
//
// Watch the parameter names: inside build the array is `arr`, not
//   `candidates`, and the target is `target`, not `k`.
//
// Time: exponential (depends on target/min candidate), Space: O(target/min)
//   for the recursion depth.

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        build(result, new ArrayList<>(), 0, 0, candidates, target);
        return result;
    }

    public static void build(List<List<Integer>> result, List<Integer> current, int i, int sum, int arr[], int target) {
        if (sum == target) {
            result.add(new ArrayList<>(current));
            return;
        }
        if (sum > target || i == arr.length) {
            return;
        }
        current.add(arr[i]);
        build(result, current, i, sum + arr[i], arr, target);
        current.remove(current.size() - 1);
        build(result, current, i + 1, sum, arr, target);
    }
}
