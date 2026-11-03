// Check if a subsequence sums to k
//
// Same TAKE IT or LEAVE IT branching as subsets — for each number, two
//   choices, explored recursively. But the SHAPE is different: subsets
//   collects every answer, this one decides a single yes/no.
//
//     if (i == nums.length) return sum == k;          // decided one path
//     if (subsets(i+1, sum + nums[i], nums, k)) return true;   // TAKE it
//     return subsets(i+1, sum, nums, k);                        // SKIP it
//
// EVERY BRANCH'S ANSWER MUST TRAVEL BACK UP. A bare
//     subsets(i+1, sum, nums, k);
//   runs the whole branch, works out true or false, and throws it away —
//   nobody catches it. A recursive call is just a method call; its result
//   needs catching like any other (`Math.max(3,5)` alone discards the 5).
//
// WHY `return` on the LAST line specifically: if the take branch had worked
//   you'd already have returned true and never reached here. So reaching the
//   last line means taking the number failed — and whatever SKIPPING gives
//   back IS this call's answer.
//
// NO current LIST, NO UNDO. Subsets needed add/remove because it had to report
//   WHICH numbers made each set. Here only the running total matters, so just
//   pass `sum + nums[i]` down one branch and `sum` down the other.
//   A plain int parameter is each call's own copy — changing it inside doesn't
//   touch the caller's, so there's nothing to undo.
//
// Base case collapses to `return sum == k;` — that expression is already a
//   boolean.
//
// Java: `true`, not `True`. And `int[] nums`, not `int
class Solution {
    public boolean checkSubsequenceSum(int[] nums, int k) {

        return subsets(0, 0, nums, k);

    }

    public static boolean subsets(int i, int sum, int nums[], int k) {
        if (i == nums.length) {
            if (sum == k)
                return true;
            return false;
        }
        if (subsets(i + 1, sum + nums[i], nums, k))
            return true;
        return subsets(i + 1, sum, nums, k);
    }
}