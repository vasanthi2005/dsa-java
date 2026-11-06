
// Letter combinations of a phone number
//
// A String[] BEATS A MAP here — the keys are digits 0-9, a small fixed range,
//   so the array index IS the key. Slots 0 and 1 are empty placeholders so
//   the indexing lines up.
//     {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"}
//   (Same rule as the hashing section: array for a small known range, map for
//    arbitrary keys.)
//
// NEW IDEA: A LOOP INSIDE THE RECURSION.
//   Every problem so far had exactly TWO choices — take/skip, '0'/'1' — so two
//   if-branches covered it. Here each digit offers THREE OR FOUR letters, and
//   the number varies by digit. A loop handles whatever's there.
//
// TWO SEPARATE MECHANISMS — don't confuse them:
//     the INDEX i   = which digit you're on. The RECURSION advances it (i+1).
//     the FOR LOOP  = which letter of that digit. Runs 3 or 4 times.
//
//     how DEEP you go  = number of digits
//     how WIDE a level = letters on that digit
//
//   "23" → depth 2, width 3 → 9 answers: ad ae af bd be bf cd ce cf
//   You never loop over the digits; the recursion walks them.
//
// NO UNDO NEEDED — current + c builds a NEW string and hands it down; the
//   original is untouched, so every loop iteration starts from the same prefix
//   automatically.
//     String  current + c      → nothing to undo (a printed card: you make a
//                                new one, you don't write on the old)
//     List    current.add(x)   → needs current.remove(...) (a notebook: it
//                                changed, rub it out)
//   THE TELL: does the line change what you already have, or produce a new
//   one?
//
// digits.charAt(i) - '0' — charAt gives the CHARACTER. '2' as a char is 50,
//   so letters[50] runs off the array. The - '0' converts it to the digit.
//   (Same trick as atoi and hash[c - 'a'].)
//
// EMPTY INPUT: digits = "" must return [], not [""]. Without a guard the base
//   case fires immediately (0 == 0) and saves the empty string. Check it in
//   the PUBLIC method before starting the recursion — build is void and can't
//   return a list.
//
// Time: O(4^n * n), Space: O(n) recursion depth.
import java.util.ArrayList;
import java.util.List;

public class LetterCombination {
    public List<String> letterCombinations(String digits) {
        String letters[] = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
        List<String> result = new ArrayList<>();
        if (digits.isEmpty())
            return result;
        build(result, letters, 0, "", digits);
        return result;
    }

    public static void build(List<String> result, String letters[], int i, String current, String digits) {

        if (digits.length() == i) {
            result.add(current);
            return;
        }
        int digit = digits.charAt(i) - '0';
        for (char c : letters[digit].toCharArray()) {
            build(result, letters, i + 1, current + c, digits);
        }
    }
}
