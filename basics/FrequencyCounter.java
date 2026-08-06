// Character frequency counter
// Approach: HashMap char -> count, getOrDefault handles first occurrence
// Time: O(n), Space: O(k) where k = distinct characters
package basics;

import java.util.*;

public class FrequencyCounter {
    public static void main(String args[]) {
        String s = "hello";
        HashMap<Character, Integer> freq = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        System.out.println(freq);
    }

}
