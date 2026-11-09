// Palindrome Partitioning — all ways to cut s so every piece is a palindrome
//
// THE CHOICE IS WHERE TO CUT, not take-or-skip. Standing at position i, the
//   first piece could be s[i], or s[i..i+1], or s[i..i+2]... any length. So
//   you loop over the possible END positions j.
//     "aab" at i=0:  "a" ✓   "aa" ✓   "aab" ✗ (not a palindrome)
//   Only take a piece if it's a palindrome — that's what prunes the branches.
//
//     for (int j = i; j < s.length(); j++) {
//         if (isPalindrome(s, i, j)) {
//             current.add(s.substring(i, j + 1));   // pick up
//             build(result, current, j + 1, s);     // explore
//             current.remove(current.size() - 1);   // put back
//         }
//     }
//
// DON'T LOOP OVER i. I wrote a nested for(i)/for(j) — but i is "where the next
//   piece starts", and that's a PARAMETER the recursion advances. Only j needs
//   a loop.
//   THE RULE: an index that moves forward through the input is a parameter.
//   The loop is only for the choices available at one position.
//   (Same thing I got wrong in letter-combinations: you never loop over the
//    digits, the recursion walks them.)
//
// WHY j + 1, NOT i + 1: the piece ran from i to j, so the next one starts
//   right after it. A piece can be any length, so the jump isn't always one.
//   (Subsets used i+1 because each step consumed exactly one element. Here a
//    step consumes a whole chunk, and j tells you how big it was.)
//
// s.substring(i, j + 1) — the end index is EXCLUSIVE, so +1 to include j.
//
// Base case i == s.length() means the whole string got used up with nothing
//   left over. Branches that don't reach the end simply save nothing.
//
// THE JAVA TRAP — current.remove(s.substring(i, j+1)) is WRONG.
//   For a List<String>, passing a String calls remove(Object), which removes
//   the FIRST occurrence by value — not the last one you added. When current
//   already holds that same string earlier, you remove the wrong one and the
//   ORDER gets scrambled.
//   Breaks on s = "abaab": current is ["a","b","a"], the first "a" comes off
//   leaving ["b","a"], and later pieces get appended to a corrupted prefix —
//   saving ["b","a","aa","b"] instead of ["a","b","aa","b"].
//
//   current.remove(current.size() - 1) passes an int, so it calls remove(int)
//   — by INDEX, always the last element.
//
//   Same trap in reverse for List<Integer>: list.remove(2) removes INDEX 2,
//   list.remove(Integer.valueOf(2)) removes the VALUE 2.
//
// isPalindrome: two pointers moving inward, a plain loop. (The recursive
//   version from the basics section works too; the loop is shorter.)
//
// Time: O(n * 2^n), Space: O(n) recursion depth.
class Solution {
    public List<List<String>> partition(String s) {
            List<List<String>> result=new ArrayList<>();
            build(result,new ArrayList<>(),0,s);
            return result;
       }
    public static void build(List<List<String>> result,List<String> current,int i, String s)
    {
        if(i==s.length())
        {
            result.add(new ArrayList<>(current));
            return ;
        }
        for(int j=i;j<s.length();j++)
        {
            if(isPalindrome(s,i,j))
            {
                current.add(s.substring(i,j+1));
                build(result,current,j+1,s);
                current.remove(current.size()-1);
            }
        }
    }
    public static boolean isPalindrome(String s,int left,int right)
    {
        while(left<right)
        {
            if(s.charAt(left)!=s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

}