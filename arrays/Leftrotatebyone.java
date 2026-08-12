// Left rotate array by one position
// Approach: save nums[0], shift every element one position left, then place
//           the saved value at the end
// Time: O(n), Space: O(1)
// Note: loop stops at length-1 because it reads nums[i+1]
//       must save nums[0] first — the shift overwrites it immediately
package arrays;

public class Leftrotatebyone {
    public void rotateArrayByOne(int[] nums) {
        int temp = nums[0];
        for (int i = 0; i < nums.length - 1; i++) {
            nums[i] = nums[i + 1];
        }
        nums[nums.length - 1] = temp;
    }
}
