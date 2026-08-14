// Max consecutive ones
// Approach: single pass tracking the current streak; update max as the streak
//           grows, reset to 0 on any non-1
// Time: O(n), Space: O(1) — optimal
// Note: update maxcount inside the if, not on a zero — otherwise an array
//       ending mid-streak never records its final count ([1,1,1] returns 0)
//       reset must be unconditional in the else, or a streak that doesn't
//       beat the max leaves count uncleared and it carries into the next
// Pattern: "is this a new best?" and "the streak ended" are separate
//       decisions and belong in separate places
package arrays;

public class Maxconsecutiveones {
    public int findMaxConsecutiveOnes(int[] nums) {
        int maxcount = 0;
        int count = 0;
        for (int i : nums) {
            if (i == 1) {
                count++;
                if (count > maxcount)
                    maxcount = count;
            } else {
                count = 0;
            }

        }
        return maxcount;
    }
}
