
/*
 * Problem : Nth Fibonacci number (recursion)
 * Source  : Striver A2Z — Recursion — Basics
 *
 * Approach: Direct translation of the definition — fib(n) is the sum of
 *           the two preceding terms. Two base cases are needed, since
 *           the recursion reaches down two levels at once.
 *
 * Note    : Branches into two calls per level, so subproblems repeat.
 *           fib(3) is recomputed several times for n = 5. Memoizing the
 *           results brings this down to O(n) — the entry point to DP.
 *
 * Time    : O(2^n)  — call tree roughly doubles each level
 * Space   : O(n)    — recursion stack depth, not the call count
 */
package basics;

public class Fibrec {
    public int fib(int n) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        return fib(n - 1) + fib(n - 2);

    }
}
