// String to integer (atoi)
//
// FOUR STAGES IN ORDER, one index `i` flowing through all of them (declared
//   before them, never reset):
//     1. skip leading whitespace — s.trim() handles it (trim takes both ends,
//        but trailing spaces would stop the digit loop anyway)
//     2. sign — ONE check, not a loop. Only one +/- is allowed, and only
//        before any digits. Consume it with i++.
//     3. read digits, building the number arithmetically
//     4. clamp if out of 32-bit range
//
// SIGN IS A MULTIPLIER, not something you append. Store sign = 1 or -1, build
//   the digits as a positive number, and return sign * num at the end.
//   (Building a string with the sign in it would need parseInt to convert,
//    which the problem forbids.)
//
// BUILDING THE NUMBER:
//     num = num * 10 + (s.charAt(i) - '0');
//   Each digit shifts everything left one place and adds the new one.
//   `c - '0'` works because a CHAR IS A NUMBER underneath — '0' is 48, '7' is
//   55, so '7' - '0' = 7. Same trick as hash[c - 'a'] in the hashing section.
//   (Strings can't do this — "7" + "0" glues to "70", and "7" - "0" won't
//    compile. charAt returns a char, not a String.)
//
// THE OVERFLOW CHECK — the real difficulty. int maxes out at 2147483647, and
//   past that it silently WRAPS NEGATIVE. So you must catch it BEFORE the
//   multiply, never after.
//
//     if (num > Integer.MAX_VALUE / 10 ||
//        (num == Integer.MAX_VALUE / 10 && digit > 7))
//         return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
//
//   MAX_VALUE / 10 = 214748364 (the limit with its last digit knocked off).
//     num > that            → times 10 already blows past, whatever the digit
//     num == that exactly   → 2147483640, so digit 7 fits (= the limit) but
//                             8 or 9 overflows. The 7 IS the limit's last digit.
//   Return IMMEDIATELY — once overflowed, later digits can't bring it back.
//
//   `cond ? a : b` is shorthand for if/else. Too big and positive → MAX_VALUE,
//   too big and negative → MIN_VALUE. That's the problem's rounding rule.
//
// Time: O(n), Space: O(1)
//
// MISTAKES I MADE:
//   Sign check INSIDE the digit loop — a '-' after digits would flip the sign.
//   `i < s.length() && a == '+' || a == '-'` — && binds tighter than ||, so
//     the second charAt isn't protected. Bracket the ||:
//     i < s.length() && (a == '+' || a == '-')
//   No i++ in the while loop — infinite loop.
//   `> '0' && < '9'` excludes 0 and 9 themselves. Need >= and <=.
class Solution {
    public int myAtoi(String s) {
        int i=0;
        int num=0;
        s=s.trim();
        int sign=1;
        if(i<s.length()&&((s.charAt(i)=='+' )|| s.charAt(i)=='-'))
        {
            if(s.charAt(i)=='-')
            sign=-1;
            i++;
        }
        while(i<s.length()&& s.charAt(i)>='0' &&s.charAt(i)<='9')
        {
            int digit=(s.charAt(i)-'0');
            if(num>Integer.MAX_VALUE/10 || (num==Integer.MAX_VALUE/10 && digit>7))
            {
                return (sign==1) ? Integer.MAX_VALUE:Integer.MIN_VALUE;
            }
            num=(num*10)+digit;
            i++;
        }
        return sign*num;
    }
}
