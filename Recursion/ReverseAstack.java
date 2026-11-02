// Reverse a stack using recursion — no loops, no extra data structures
//
// STACK BASICS: a pile of plates. You can only touch the TOP.
//     push(x)   put x on top
//     pop()     take the top off and return it
//     peek()    look at the top without removing
//     isEmpty() is the pile empty?
//   No .length — it's isEmpty() or size().
//
// THE IDEA: take the top off and hold it, reverse what's left, then put the
//   held one at the BOTTOM.
//     [4,1,3,2]  (2 on top)
//     hold 2, remaining 4,1,3 → reverse → 3,1,4
//     put 2 at the bottom → 2,3,1,4  ✓
//
// THE DIFFICULTY: you can't reach the bottom of a stack. So "put it at the
//   bottom" needs its OWN recursion — pop everything off one at a time, push
//   the new item once the stack is empty, then push everything back.
//
//     void reverseStack(st) {
//         if (st.isEmpty()) return;
//         int top = st.pop();
//         reverseStack(st);
//         insertAtBottom(st, top);
//     }
//
//     void insertAtBottom(st, x) {
//         if (st.isEmpty()) { st.push(x); return; }
//         int top = st.pop();
//         insertAtBottom(st, x);
//         st.push(top);
//     }
//
// TWO BASE CASES, SAME TEST, DIFFERENT MEANINGS:
//     reverseStack:   empty = nothing left to reverse, stop
//     insertAtBottom: empty = you've REACHED the bottom, this is where x goes
//   Every recursive method needs its own base case.
//
// WHY IT REVERSES: reverseStack strips the stack to nothing, holding each
//   popped value in its own call frame. On the way back up, each held value is
//   pushed to the BOTTOM. The one taken off FIRST goes to the bottom LAST, so
//   it ends up on top — that's the reversal.
//
// PICK UP → GO DEEPER → PUT BACK. Same three lines as subsets, where it was
//   add / recurse / remove. Here it's pop / recurse / push.
//   (My first attempt popped everything and pushed nothing back, leaving an
//    empty stack — the pops have to be undone on the way up.)
//
// MISTAKE: no `return` after st.push(x) in the base case. The braces don't
//   protect what follows, so it fell through to st.pop(), popped the x it had
//   just pushed, and spun until it crashed. A base case must actually STOP.
//
// Time: O(n²) — insertAtBottom is O(n) and runs n times. Space: O(n) for the
//   call stack (which is the "extra structure" the rules allow).

import java.util.Stack;

public class ReverseAstack {
    public void reverseStack(Stack<Integer> st) {
        // Your code goes here
        if (st.isEmpty()) {
            return;
        }
        int top = st.pop();
        reverseStack(st);
        insertAtbottom(st, top);
    }

    public static void insertAtbottom(Stack<Integer> st, int x) {
        if (st.isEmpty()) {
            st.push(x);
            return;
        }
        int top = st.pop();
        insertAtbottom(st, x);
        st.push(top);
    }
}
