// Move zeroes to the end (in-place, preserving non-zero order)
// Approach: two pointers — walk the array collecting non-zeros at the front,
//           then fill the remainder with zeros. The reframe: don't move the
//           zeros (they're interchangeable, so there's nothing to relocate) —
//           collect the non-zeros and pad after.
// Time: O(n), Space: O(1)
// Note: same read/write pointer shape as remove-duplicates. The write pointer
//       never overtakes the read pointer, so overwriting the front while
//       reading ahead is safe.
//       Second loop: use ONE counter. Incrementing both i and start does the
//       same job twice and invites a bug the moment they diverge.
package arrays;

public class Movezeros {
    public void moveZeroes(int[] nums) {
        int start = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[start] = nums[i];
                start++;
            }
        }
        for (int i = start; i < nums.length; i++) {
            nums[i] = 0;

        }
    }

}
