// Remove outermost parentheses
//
// A "primitive" piece is a chunk that balances on its own. You spot the
//   boundaries by tracking DEPTH: '(' adds one, ')' subtracts one, and a piece
//   ends whenever the depth returns to 0.
//     "()()"  →  depth 1,0 | 1,0  →  two pieces, each a bare pair
//                strip both → "" + "" → "" (empty, nothing was inside them)
//
// THE RULE — keep a bracket unless it's an outermost one:
//     '(' is outermost when depth is 0 BEFORE it
//     ')' is outermost when depth is 1 BEFORE it
//
//     "(()())"
//      (  depth 0 → outermost, skip
//      (  depth 1 → keep
//      )  depth 2 → keep
//      (  depth 1 → keep
//      )  depth 2 → keep
//      )  depth 1 → outermost, skip
//     → "()()"
//
// Time: O(n), Space: O(n) for the output
//
// ORDERING: for '(' check THEN increment; for ')' decrement THEN check. That's
//   what makes both tests read `depth > 0`. Swap the order on either and the
//   number must change to `depth > 1` — the test has to match when you look.
//
// STRINGBUILDER, not String concatenation. A String is FIXED once made, so
//   s += c throws the old one away and copies everything into a new one —
//   O(n²) over a loop, which times out at n = 10^5. StringBuilder appends into
//   a buffer in place: O(n) total. Call .toString() at the end.
//   (StringBuffer is the same thing with thread-safety locking — slower, and
//    never needed in DSA.)
//   RULE: building a string in a loop → StringBuilder. Otherwise → String.
public class RemoveOutermostParentheses {
    public String removeOuterParentheses(String s) {
        StringBuilder sb=new StringBuilder();
       int depth=0;
       for(char c:s.toCharArray())
       {
            if(c=='(')
            {
                if(depth>0)sb.append('(');
                depth++;
            }
            else
            {
                depth--;
                if(depth>0)sb.append(')');
            }
       }
       return sb.toString();
        
    }
}
