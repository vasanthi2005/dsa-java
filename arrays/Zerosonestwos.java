// Sort array of 0s, 1s and 2s — counting approach
// Approach: two passes. Count occurrences of each value, then overwrite the
//           array with that many 0s, then 1s, then 2s, tracking a write index.
// Time: O(2n) = O(n), Space: O(1)
// Note: a for loop with a count of 0 runs zero times on its own — no guard
//       needed. My break was testing the counter (i == 0), not the count.
//       Only works because the value range is tiny and known. Wouldn't
//       generalise to arbitrary integers.
//BRUTE FORCE APPROACH
package arrays;

public class Zerosonestwos {
    public void sortZeroOneTwo(int[] nums) {
        int countzeros = 0;
        int countones = 0;
        int counttwos = 0;
        for (int i : nums) {
            if (i == 0)
                countzeros++;
            if (i == 1)
                countones++;
            if (i == 2)
                counttwos++;
        }
        int k = 0;
        for (int i = 0; i < countzeros; i++) {
            nums[k] = 0;
            k++;
        }
        for (int i = 0; i < countones; i++) {
            nums[k] = 1;
            k++;
        }
        for (int i = 0; i < counttwos; i++) {
            nums[k] = 2;
            k++;
        }

    }

    // OPTIMAL APPROACH
    // Sort array of 0s, 1s and 2s — Dutch National Flag (one pass)
    // Approach: three pointers maintaining an invariant across the array:
    // [0 .. low-1] all 0s (settled)
    // [low .. mid-1] all 1s (settled)
    // [mid .. high] unknown (still to process)
    // [high+1 .. n-1] all 2s (settled)
    // mid walks through the unknown region:
    // nums[mid] == 0 -> swap with nums[low], advance BOTH low and mid
    // nums[mid] == 1 -> already in place, advance mid only
    // nums[mid] == 2 -> swap with nums[high], decrement high, DON'T advance mid
    // Time: O(n) single pass, Space: O(1)
    //
    // Why mid doesn't advance on a 2: the value swapped in came from the
    // unexamined right region, so it must be looked at next. Swapping from the
    // left brings back a settled 1, which is why mid can advance there.
    //
    // Why mid <= high, not mid < high: high marks the LAST unprocessed element,
    // not the first settled one, so it must be included. [1,0] fails with <.
    //
    // Must be if / else if / else, not three separate ifs: each branch can change
    // nums[mid], and a following if would then act on the NEW value in the same
    // iteration — advancing mid when it shouldn't.
    public void sortColors(int[] nums) {
        if (nums.length <= 1)
            return;
        int low = 0, mid = 0, high = nums.length - 1;
        while (mid <= high) {
            if (nums[mid] == 0) {
                int temp = nums[low];
                nums[low] = nums[mid];
                nums[mid] = temp;
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                int temp = nums[high];
                nums[high] = nums[mid];
                nums[mid] = temp;
                high--;
            }

        }

    }
}
