// Combination Sum II — combinations summing to target, each element used
//   AT MOST ONCE, and the candidates CAN CONTAIN DUPLICATES
//
// TWO DIFFERENCES FROM COMBINATION SUM I:
//   1. Each number used once → the TAKE branch goes to i+1 (in I it stayed
//      at i, which is what allowed reuse).
//   2. The input can repeat → the output must not contain the same
//      combination twice.
//
// THE DUPLICATE PROBLEM. [1,1,2], target 3:
//     take the first 1, skip the second, take the 2  →  [1,2]
//     skip the first 1, take the second, take the 2  →  [1,2]   ← same!
//   Two different PATHS, identical ANSWER. The two 1s are interchangeable,
//   so which one you picked makes no difference to the combination.
//
// THE FIX — on the SKIP branch, jump past EVERY copy:
//     int j = i;
//     while (j < nums.length && nums[j] == nums[i]) j++;
//     build(result, current, j, sum, nums, k);
//
//   "I'm not using a 1 here" vs "I'm not using THIS PARTICULAR 1 here" are
//   different statements. Only the first avoids duplicates. Three 1s → skip
//   all three; the while handles however many there are.
//
//   THE TAKE BRANCH STILL GOES TO i+1. Using two different 1s WITHIN one
//   combination is fine ([1,1,2] summing to 4). You're forbidding the same
//   combination being REACHED twice, not repeated values inside it.
//
// WHY Arrays.sort(candidates) — the while loop only skips ADJACENT equal
//   values. On [1,2,1] it checks index 1, finds a 2, and stops after
//   skipping one 1; the other slips through and rebuilds the combination.
//   Sorting puts equal values side by side, which is what makes the
//   group-skip work.
//
//   WHY NO SORT IN THE EARLIER ONES: subsets said "unique elements",
//   combination sum I said "distinct integers". With no repeats in the input,
//   two paths can never produce the same combination — nothing to dedupe.
//   READ THE CONSTRAINTS FOR WHETHER DUPLICATES ARE POSSIBLE; it changes the
//   whole approach and the statement always says.
//   (Aggressive cows also sorted, but for a different reason — distance only
//    means something along a line. Same operation, different motive, both
//    coming from what the problem said.)
//
// Collecting shape: void, shared result list, current list with add/recurse/
//   remove. result.add(new ArrayList<>(current)) — the COPY, not `current`
//   itself, or every saved entry points at the same list.
//
// Pruning: sum > k returns early since all candidates are positive.
//
// Time: O(2^n) worst case, Space: O(n) for the recursion depth.
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        build(result, new ArrayList<>(), 0, 0, candidates, target);
        return result;
    }

    public static void build(List<List<Integer>> result, List<Integer> current, int i, int sum, int nums[], int k) {
        if (sum == k) {
            result.add(new ArrayList<>(current));
            return;
        }
        if (i == nums.length || sum > k) {
            return;
        }
        current.add(nums[i]);
        build(result, current, i + 1, sum + nums[i], nums, k);
        current.remove(current.size() - 1);
        int j = i;
        while (j < nums.length && nums[j] == nums[i])
            j++;
        build(result, current, j, sum, nums, k);
    }
}