// Subsets (power set) — all possible subsets of an array of unique elements
//
// BACKTRACKING. In generate-parentheses the choice was WHICH CHARACTER to add.
//   Here, for each number, the choice is: TAKE IT or LEAVE IT.
//   Two choices × n numbers → 2^n subsets.
//
//                       []
//            1? ┌────────┴────────┐
//             take               skip
//             [1]                 []
//        2? ┌───┴───┐        ┌────┴────┐
//         [1,2]    [1]      [2]        []
//   ...and so on. Every LEAF is one subset.
//
// THE RECURSION NEEDS: an index i (which number am I deciding about?) and
//   `current` (what have I collected so far).
//
//     if (i == nums.length) { result.add(new ArrayList<>(current)); return; }
//     current.add(nums[i]);                  // pick it up
//     build(result, current, i + 1, nums);   // explore WITH it
//     current.remove(current.size() - 1);    // put it back down
//     build(result, current, i + 1, nums);   // explore WITHOUT it
//
//   PICK UP → EXPLORE → PUT BACK. Those three lines are the backtracking
//   pattern; they recur in nearly every problem in this section.
//
// WHY THE REMOVE: there is ONE basket, carried down the whole tree. If you
//   don't take the 1 back out before the skip branch, you'd explore [1,2] and
//   [1,3] instead of [2] and [3] — and never get the subsets that leave 1 out.
//
// WHY ONLY THE LAST ITEM: each call adds exactly one number (its own) and
//   removes exactly one. The deeper calls already cleaned up theirs on the way
//   back up, so the thing this call added is always sitting on top.
//   Like a stack of plates — you take off the one you put on.
//
// WHY new ArrayList<>(current): `current` keeps changing. result.add(current)
//   would store a POINTER to the same basket, so all the saved subsets would
//   be the same list — empty by the end. [[], [], [], []]
//   The copy is a PHOTO of the basket at this moment.
//   (Parentheses didn't need this: strings can't be modified, so current + "("
//    already made a new one every time. Lists change in place.)
//
// WHEN IT STOPS — two different stops:
//   One BRANCH stops at i == nums.length. Nothing to do with `current` being
//     empty — current is empty at the start, in the middle and at the end.
//     It's not a signal.
//   The WHOLE THING stops when the first call falls off the end of its body.
//     No "stop everything" condition exists. A call doesn't continue past a
//     build(...) line until that call has completely finished; when the
//     outermost one runs out of lines, there's nobody to return to.
//
//   It can't loop forever because every call passes i + 1 — i only climbs,
//   and the tree has exactly nums.length levels.
//
// The base case fires for EVERY leaf, including the one where nothing was
//   taken — that's where the empty subset [] comes from.
//
// Time: O(2^n * n) — 2^n subsets, each copied.  Space: O(n) for the recursion
//   depth plus the basket.
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        build(result, new ArrayList<>(), 0, nums);
        return result;
    }

    public static void build(List<List<Integer>> result, List<Integer> current, int i, int[] nums) {
        if (i == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        current.add(nums[i]);
        build(result, current, i + 1, nums);
        current.remove(current.size() - 1);
        build(result, current, i + 1, nums);
    }
}