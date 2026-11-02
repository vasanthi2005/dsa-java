// Count good numbers — even indices must hold an even digit, odd indices a
//   prime digit (2,3,5,7). Count strings of length n, modulo 10^9 + 7.
//
// DON'T GENERATE — COUNT. n can be 10^15, so nothing can be listed. This looks
//   like a "generate all" problem and isn't.
//
// EACH POSITION IS AN INDEPENDENT CHOICE, SO YOU MULTIPLY:
//     even index → 0,2,4,6,8  → 5 choices
//     odd index  → 2,3,5,7    → 4 choices
//   n=4: 5 × 4 × 5 × 4 = 400 ✓
//
//   answer = 5^(even positions) × 4^(odd positions)
//
// COUNTING THE POSITIONS — one formula covers both cases, no if/else:
//     even = (n + 1) / 2
//     odd  = n / 2
//   Integer division drops the remainder, which is exactly right. When n is
//   odd the extra position is an EVEN one (positions 0,2,4 for n=5 → three).
//
// FAST EXPONENTIATION — same halving trick as Pow(x,n). Can't loop 10^15 times.
//     exp even → half = power(base, exp/2); return half*half
//     exp odd  → return base * power(base, exp-1)
//   `half` must hold the RECURSIVE CALL's result, not exp/2. Writing
//   `long half = exp / 2` squares the exponent instead of the answer — the
//   same slip as `ans = val` instead of `ans = mid` in smallest-divisor:
//   grabbing the input where the result belongs.
//   Simpler than Pow: exponent is always positive, so no reciprocal, no sign
//   flip, no MIN_VALUE overflow worry.
//
// MODULO — what it means and why:
//   The true answer is astronomical (n=50 → ~10^22), bigger than any Java
//   type. So the problem asks for the REMAINDER after dividing by 10^9+7.
//   That's why the expected output for n=50 is a small number, 564908303.
//
//   MOD AFTER EVERY MULTIPLICATION, not just at the end. You can't compute the
//   real value first — it overflows long before you get there. Taking the
//   remainder as you go gives the same answer, and keeps every intermediate
//   under 10^9, so the next multiply is at most 10^9 × 10^9 = 10^18 — fits in
//   a long, barely. (Which is why int won't do, and why `base` must be long:
//   int operands mean int arithmetic. Cast an OPERAND, not the result.)
//
//   THE RULE IS PER MULTIPLICATION, NOT PER FUNCTION. The final
//   power(5,even) * power(4,odd) happens OUTSIDE power(), so it needs its own
//   mod — each factor is under 10^9 but their product reaches 10^18, and
//   casting that to int gives garbage. Mod first, THEN cast.
//
// Time: O(log n), Space: O(log n) for the call stack.
class Solution {
    public int countGoodNumbers(long n) {
        long even = (n + 1) / 2;
        long odd = n / 2;
        return (int)((power(5, even) * power(4, odd)) % 1000000007);
    }
    public static long power(long base,long exp)
    {
        if(exp==0)
        return 1;
        if(exp%2==0)
        {
            long half=power(base,exp/2);
            return (half * half) % 1000000007;
        }
        return base*power(base,exp-1)%1000000007;

    }
}