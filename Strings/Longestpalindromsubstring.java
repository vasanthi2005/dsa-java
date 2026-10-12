// Longest palindromic substring
//
// THE IDEA: every palindrome has a CENTRE. Stand at each position and grow
//   outwards while the characters match. The widest one found is the answer.
//     "bab" — centre on 'a', push left and right apart: b...b match, then
//             the pointers run off the edges. Done.
//
// TWO KINDS OF CENTRE — this is the catch:
//     odd-length  → centres ON a character:     expand(i, i)
//     even-length → centres BETWEEN two chars:  expand(i, i + 1)
//   "bab" has a middle letter; "bb" doesn't. So try both at every index.
//
// Time: O(n²) — n centres, each expanding up to n. Fine for n <= 1000.
// Space: O(1) beyond the output.
//
// THE EXPAND HELPER:
//     while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
//         left--; right++;
//     }
//     return s.substring(left + 1, right);
//
//   `left + 1` because the loop exits AFTER the pointers have gone one step
//   too far — step back in. `right` needs no adjustment: substring's end is
//   EXCLUSIVE, so it's already pointing one past the last good character.
//
//   The bounds guards come FIRST in the && so charAt never runs out of range.
//   (Short-circuit ordering — same rule as the atoi sign check.)
//
// Two-pointer family, but opposite-ends moving OUTWARD rather than inward.
class Solution {
    public String longestPalindrome(String s) {
        String best="";
        for(int i= 0;i<s.length();i++)
        {
            String odd=expand(s,i,i);
            String even=expand(s,i,i+1);
            if(odd.length()>best.length()) best=odd;
            if(even.length()>best.length()) best=even;
        }
        return best;
    }
    public static String expand(String s,int left,int right)
    {
        while(left>=0 && right<s.length()&&(s.charAt(left)==s.charAt(right)))
        {
            left--;
            right++;
        }
        return s.substring(left+1,right);
    }
}