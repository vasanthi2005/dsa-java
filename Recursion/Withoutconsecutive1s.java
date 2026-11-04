// Generate binary strings without consecutive 1s
//
// Same shape as generate-parentheses: build the string one character at a
//   time, two choices per step, recurse. Only the RULES differ.
//
//     '0' → ALWAYS allowed. Nothing can make a zero illegal.
//     '1' → only if the last character isn't already a '1'.
//
//     helper(result, s + "0", n);                           // unconditional
//     if (s.isEmpty() || s.charAt(s.length()-1) != '1')
//         helper(result, s + "1", n);
//
// ASK WHICH CHOICE ACTUALLY NEEDS PROTECTING. I put the guard on the '0'
//   branch and wrote it backwards. In parentheses BOTH branches had
//   conditions, so I assumed symmetry — here only one does.
//
// s.isEmpty() comes FIRST in the || — an empty string has no last character,
//   so charAt would crash. Short-circuit ordering, same rule as the atoi sign
//   check and the roman-numeral lookahead.
//
// `s + "0"`, not `"0"` — pass down what you've built PLUS the new character.
//   `0 + ""` throws the whole prefix away.
//
// Base case must ADD the string, not just return — otherwise result stays
//   empty. (Same slip as the first subsets attempt.)
//
// LEXICOGRAPHIC ORDER COMES FREE by trying '0' before '1', since '0' sorts
//   first. n=3 → 000, 001, 010, 100, 101 ✓. No sorting needed.
//
// No undo step: strings can't be modified, so `s + "0"` makes a new one each
//   time and each branch gets its own copy. (Lists would need add/remove —
//   see subsets.)
//
// Java: '1' in single quotes is a char; "1" is a String. charAt returns a
//   char, so compare with single quotes.
//
// Time: O(2^n) worst case (actually Fibonacci-many strings), Space: O(n) stack.
class Solution {
    public List<String> generateBinaryStrings(int n) {
        // Your code goes here
        List<String> result = new ArrayList<>();
        helper(result, "", n);
        return result;
    }

    public static void helper(List<String> result, String s, int n) {
        if (s.length() == n) {
            result.add(s);
            return;
        }
        helper(result, s + "0", n);
        if (s.isEmpty() || s.charAt(s.length() - 1) != '1') {
            helper(result, s + "1", n);
        }
    }
}