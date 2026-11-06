// Combination Sum III — all combinations of EXACTLY k numbers from 1..9
//   that sum to n. Each number used at most once.
//
// NO ARRAY IS GIVEN. The candidates are just 1 through 9, so THE INDEX IS THE
//   NUMBER — use `i` directly instead of `nums[i]`, starting at i = 1.
//   (Building int[] {1..9} and reusing the earlier code also works; this is
//    just shorter, since nums[i] would always equal i.)
//
// TWO CONDITIONS MUST BOTH HOLD at the end, not one:
//     current.size() == k    exactly k numbers used
//     sum == n               and they add to n
//   A combination summing to n with the wrong count doesn't qualify.
//
// ORDER OF THE BASE CASES MATTERS — the completion check comes FIRST:
//     if (current.size() == k) {
//         if (sum == n) result.add(new ArrayList<>(current));
//         return;
//     }
//     if (i > 9) return;
//
//   With `i > 9` first, k=2 n=17 → [8,9] is MISSED: after taking the 9 you
//   recurse with i=10, and the out-of-range check fires before anything looks
//   at the combination.
//
// `i` IS THE VALUE, `i + 1` IS THE NEXT INDEX. Easy to blur:
//     current.add(i);                                 ← the number itself
//     build(..., i + 1, sum + i, ...);                ← next index, add i
//   Writing sum + (i+1) makes the list and the sum disagree, every element
//   off by one.
//
// No sort, no duplicate-skip — 1..9 are already distinct and in order. (Those
//   were only needed in Combination Sum II / Subsets II, where the INPUT could
//   repeat.)
//
// i+1 on BOTH branches — each number used at most once, like Combination
//   Sum II. (Combination Sum I stayed at i on the take branch, which is what
//   allowed reuse.)
//
// `build` is void — it can't `return result.add(...)`. Call it, then return.
//
// Time: O(2^9) at most — a fixed, tiny search space. Space: O(k).

class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        build(result, new ArrayList<>(), 1, 0, k, n);
        return result;
    }

    public static void build(List<List<Integer>> result, List<Integer> current, int i, int sum, int k, int n) {
        if (current.size() == k) {
            if (sum == n)
                result.add(new ArrayList<>(current));
            return;
        }
        if (i > 9)
            return;
        current.add(i);
        build(result, current, i + 1, sum + i, k, n);
        current.remove(current.size() - 1);
        build(result, current, i + 1, sum, k, n);

    }
}