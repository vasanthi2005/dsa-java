package Recursion;

// Pow(x, n) — implement x raised to the power n
//
// Naive recursion x * myPow(x, n-1) is O(n), and n can be ~2 billion — that
//   blows the stack long before finishing.
//
// THE FIX — HALVE, DON'T DECREMENT:
//     x^10 = (x^5)²
//     x^5  = x · (x²)²
//   Each step cuts the exponent in half instead of shaving one off.
//   ~31 calls instead of 2 billion. O(log n).
//
//     n even → double half = helper(x, n/2); return half * half;
//     n odd  → return x * helper(x, n-1);    // odd drops to even in one step
//
//   COMPUTE half ONCE and square it. Writing
//       helper(x, n/2) * helper(x, n/2)
//   makes two identical calls and collapses straight back to O(n).
//
//   The odd branch must keep the x it strips off — `x * helper(x, n-1)`,
//   not just `helper(x, n-1)`, or x^5 returns x^4.
//
//   Same divide-and-conquer shape as merge sort: solve half, combine on the
//   way back up.
//
// NEGATIVE EXPONENTS — flip BOTH, once, before the recursion starts:
//     x^-3 = 1/x³ = (1/x)³
//     x = 1/x     the reciprocal part
//     N = -N      makes the exponent positive so it can count down to 0
//   Flip only one and you get a wrong answer or infinite recursion.
//
// WHY long N = n:  int range is -2147483648 .. 2147483647 — the negative end
//   goes ONE further. So -(Integer.MIN_VALUE) doesn't fit, wraps, and comes
//   back as the same negative number. The flip does nothing and the recursion
//   never terminates.
//   Copy into a long FIRST, then negate. The arithmetic happens in the type of
//   the OPERANDS, not the variable you assign to.
//   (Same rule as (long) m * k in the bouquet problem.)
//
// Hence two methods: the public one takes int and normalises; the helper takes
//   long and only ever sees positive exponents.
//
// Time: O(log n), Space: O(log n) for the call stack.
//
// Base case n == 0 returns 1 — anything to the power 0.
//
// NOTE: 307/309 tests pass. The two failures are off by 0.00002 on a huge
//   negative exponent — floating-point rounding, not a logic error. Doubles
//   store decimals approximately, and ~31 squarings amplify that tiny error
//   (squaring doubles the existing error each time). Rewriting it as an
//   iterative loop didn't change the result either. The algorithm is correct;
//   matching the judge's exact multiplication order is a curiosity, not a
//   lesson. Moved on deliberately.

class Solution {
    public double myPow(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        return helper(x, N);
    }

    public double helper(double x, long n) {
        if (n == 0)
            return 1;
        if (n % 2 == 0) {
            double half = helper(x, n / 2);
            return half * half;
        }
        return x * helper(x, n - 1);
    }
}
