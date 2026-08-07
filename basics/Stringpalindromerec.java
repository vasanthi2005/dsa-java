/*
 * Problem : Check if a string is a palindrome (recursion)
 * Source  : Striver A2Z — Recursion — Basics
 *
 * Approach: Two pointers as parameters, moving inward from both ends.
 *           Mismatch at any pair -> false immediately, no need to look
 *           further. Pointers meeting or crossing means every pair
 *           matched -> true. Case-sensitive comparison, so 'a' != 'A'.
 *
 * Note    : Base case returns the answer directly; no reversed string
 *           is ever built.
 *
 * Time    : O(n)   — n/2 comparisons
 * Space   : O(n)   — recursion stack depth
 */
package basics;

public class Stringpalindromerec {
    public boolean palindromeCheck(String s) {
        return helper(s, 0, s.length() - 1);

    }

    public boolean helper(String str, int start, int end) {
        if (start >= end)
            return true;
        if (str.charAt(start) != str.charAt(end))
            return false;
        return helper(str, start + 1, end - 1);

    }
}
