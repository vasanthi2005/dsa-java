// Subsets II — all subsets, where nums CAN CONTAIN DUPLICATES and the output
//   must not repeat a subset
//
// Plain subsets with the duplicate-skip from Combination Sum II bolted on.
//   Same two parts:
//     Arrays.sort(nums)  — puts equal values side by side
//     SKIP branch jumps past EVERY copy, not just one
//
//     current.add(nums[i]);
//     build(result, current, i + 1, nums);     // TAKE → i+1 as usual
//     current.remove(current.size() - 1);
//     int j = i;
//     while (j < nums.length && nums[j] == nums[i]) j++;
//     build(result, current, j, nums);         // SKIP → past the whole run
//
// WHY: [1,1] would otherwise give [1] twice — once from taking the first and
//   skipping the second, once from skipping the first and taking the second.
//   The two 1s are interchangeable, so both paths build the same subset.
//
// MISTAKE: wrote `if` instead of `while`. With `if`, j starts at i, the check
//   passes (a value always equals itself), j becomes i+1, and it stops —
//   identical to no deduplication at all. Only `while` walks the whole run.
//     [1,1,1,2], i=0:  if → j=1 (a 1 still ahead)   while → j=3 (past all)
//
// TAKE still goes to i+1 — two different 1s within ONE subset is fine
//   ([1,1] is a legitimate subset). You're forbidding the same subset being
//   REACHED twice.
//
// Base case saves at i == nums.length, so every leaf is a subset, including
//   the empty one.
//
// result.add(new ArrayList<>(current)) — the copy, not current itself.
//
// Time: O(2^n * n), Space: O(n) recursion depth.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        build(result, new ArrayList<>(), 0, nums);
        return result;
    }

    public static void build(List<List<Integer>> result, List<Integer> current, int i, int nums[]) {
        if (i == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        current.add(nums[i]);
        build(result, current, i + 1, nums);
        current.remove(current.size() - 1);
        int j = i;
        while (j < nums.length && nums[i] == nums[j])
            j++;
        build(result, current, j, nums);
    }
}
