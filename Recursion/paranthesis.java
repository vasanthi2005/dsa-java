package Recursion;

// Generate parentheses — all well-formed combinations of n pairs
//
// FIRST BACKTRACKING PROBLEM. New shape: build the answer one character at a
//   time, and at each step there are at most TWO choices — add '(' or add ')'.
//   The choices branch out into a tree, and every leaf that reaches full
//   length is one answer.
//
// THE TWO RULES that decide which choices are legal:
//     open  < n      → may add '('   (haven't used all n yet)
//     close < open   → may add ')'   (only if an unmatched '(' is waiting)
//   Track both counts as you go.
//
// BOTH ifs RUN, not one or the other. That's what explores everything — it
//   goes all the way down the '(' branch, comes back, then tries the ')'
//   branch. `return` only ends THAT branch; control goes back up to the caller,
//   which carries on to its next if.
//
//     ""
//      ├─ "("  ──┬─ "((" → "(()" → "(())"  ← SAVED
//      │         └─ "()" → "()(" → "()()"  ← SAVED
//      └─ ")"  blocked: close < open is false
//
// BASE CASE: current.length() == 2*n  → result.add(current); return;
//   EXACTLY 2n, not > 2n — by the time it's longer you've gone too far.
//   And you must ADD the string, or the list stays empty.
//
//   Why == 2n is enough on its own: the two rules above already prevent
//   anything invalid from being built, so ANY string reaching length 2n is
//   automatically well-formed. No validity check needed at the end. You never
//   generate a bad string and throw it away — the rules stop you before you
//   make one.
//
// `current + "("` passes a NEW string down. Each branch gets its own copy, so
//   one branch's choices don't leak into another's.
//
// `result` is the SAME list throughout — passed into every call, so every
//   branch adds to the same collection and nothing gets lost.
//
// The helper is void. It returns nothing; it just adds to the list it was
//   handed. (`return result;` inside a void method won't compile.)
//
// n=2 → 2 answers.  n=3 → 5 answers.
//
// Time/Space: O(4^n / √n) — the Catalan number of valid strings, each of
//   length 2n. Not worth memorising; the point is the branching shape.
import java.util.ArrayList;
import java.util.List;

public class paranthesis {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        build(result, "", 0, 0, n);
        return result;
    }

    static void build(List<String> result, String current, int open, int close, int n) {
        if (current.length() == 2 * n) {
            result.add(current);
            return;
        }
        if (open < n) {
            build(result, current + "(", open + 1, close, n);
        }
        if (close < open) {
            build(result, current + ")", open, close + 1, n);
        }
    }
}
