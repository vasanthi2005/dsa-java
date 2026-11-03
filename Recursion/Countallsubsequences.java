// Count subsequences with target sum k
//
// Same TAKE/LEAVE branching as subsets and the yes/no version. THIRD SHAPE:
//   not collecting, not deciding — COUNTING. You ADD what the two branches
//   return.
//
//     if (i == nums.length) return (sum == k) ? 1 : 0;
//     return subsets(i+1, sum + nums[i], nums, k)    // how many if I TAKE it
//          + subsets(i+1, sum, nums, k);             // how many if I SKIP it
//
// THE LEAVES DECIDE, EVERYONE ELSE JUST ADDS. Each complete path returns 1
//   (match) or 0 (not). No call counts anything itself — it only sums what
//   came back from its two children, and the totals grow as they merge
//   upward.
//
//     nums=[1,1], k=1:
//       take → (take→0) + (skip→1) = 1
//       skip → (take→1) + (skip→0) = 1
//       top  → 1 + 1 = 2  ✓
//
// 0, NOT -1, for a non-match. -1 would SUBTRACT and cancel out real answers.
//   0 means "contributed nothing."
//
// `count` CANNOT BE A PARAMETER. Each call gets its own COPY, so a deeper
//   call's increment never reaches the caller. Data flows DOWN through
//   parameters and UP through return values — a parameter can't carry
//   anything back.
//   (A static field would work instead — that's what count-inversions did in
//    merge sort — but then it needs resetting to 0 at the top of each call,
//    since static fields survive between test cases. Returning the count is
//    cleaner: no shared state, no reset.)
//
// No `current` list and no undo — only the running sum matters, so pass
//   `sum + nums[i]` down one branch and `sum` down the other. A plain int
//   parameter is each call's own copy, so there's nothing to undo.
//
// Time: O(2^n), Space: O(n) call stack.

package Recursion;

public class Countallsubsequences {
    public int countSubsequenceWithTargetSum(int[] nums, int k) {
        return subsets(0, 0, nums, k);
    }

    public static int subsets(int i, int sum, int nums[], int k) {
        if (i == nums.length) {
            return (sum == k) ? 1 : 0;
        }
        return subsets(i + 1, sum + nums[i], nums, k)
                + subsets(i + 1, sum, nums, k);

    }
}
