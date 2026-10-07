// Roman to integer
//
// Adding every symbol is wrong — IV is 4, not 6. Roman numerals use SUBTRACTIVE
//   pairs: IV, IX, XL, XC, CD, CM.
//
// THE RULE: a symbol is SUBTRACTED when the symbol after it is LARGER.
//   Otherwise it's added.
//     IV → I(1) before V(5), 5 is bigger  → -1, then +5 = 4
//     VI → V(5) before I(1), 1 is smaller → +5, then +1 = 6
//
// That means you must look at the NEXT character, so index with a normal for
//   loop — a for-each can't see ahead.
//
//     if (i + 1 < s.length() && curr < map.get(s.charAt(i + 1))) num -= curr;
//     else                                                       num += curr;
//
// The guard `i + 1 < s.length()` MUST come first in the &&. Short-circuit
//   evaluation stops at the first false, so charAt(i+1) never runs on the last
//   character. Reversed, it throws out of bounds.
//   The last symbol has no successor, so it is always ADDED.
//
// Time: O(n), Space: O(1) — the map holds 7 fixed entries.
//
// A Map<Character,Integer> beats seven else-ifs for readability; the if-chain
//   works too, just put it in a helper that returns the value for a char.
class Solution {
public int romanToInt(String s) {
    Map<Character, Integer> map = Map.of(
        'I', 1, 'V', 5, 'X', 10, 'L', 50,
        'C', 100, 'D', 500, 'M', 1000
    );

    int num = 0;
    for (int i = 0; i < s.length(); i++) {
        int curr = map.get(s.charAt(i));
        if (i + 1 < s.length() && curr < map.get(s.charAt(i + 1))) {
            num -= curr;
        } else {
            num += curr;
        }
    }
    return num;
}
}