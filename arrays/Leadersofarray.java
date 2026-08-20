// Leaders in an array (elements greater than everything to their right)
// Approach: walk right-to-left tracking a running max. Everything already seen
//           IS everything to the right, so nums[i] > max means it's a leader.
// Time: O(n), Space: O(k) for the output
// Note: comparing with only the next element is wrong — a leader must beat ALL
//       elements to its right, not just its neighbour.
//       Results come out reversed; one Collections.reverse at the end is O(k),
//       cheaper than list.add(0, x) each time which would be O(k²).
package arrays;

import java.util.*;

public class Leadersofarray {

    public List<Integer> leaders(int[] nums) {
        ArrayList<Integer> list = new ArrayList<>();
        int max = nums[nums.length - 1];
        list.add(max);
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > max) {
                list.add(nums[i]);
                max = nums[i];
            }
        }
        Collections.reverse(list);
        return list;

    }

}
