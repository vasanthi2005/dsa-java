// Highest occurring element in an array (smallest element on ties)
// Approach: build a frequency map in one pass, then iterate the entries
//           tracking both the highest count and the element that achieved it
// Time: O(n), Space: O(k) for k distinct elements
// Note: two variables needed — maxCount and result are different things.
//       Returning maxCount alone gives the count, not the element.
//       Tie-break on the smaller element, since HashMap order is arbitrary.
package basics;

import java.util.HashMap;
import java.util.Map;

public class Mostfreqelement {
    public int mostFrequentElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        int maxCount = 0;
        int result = 0;
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            int element = e.getKey();
            int count = e.getValue();

            if (count > maxCount) {
                maxCount = count;
                result = element;
            } else if (count == maxCount && element < result) {
                result = element;
            }
        }
        return result;
    }
}
