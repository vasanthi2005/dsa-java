// Rotate array by k positions (left)
// Approach: reversal trick — reverse the first k, reverse the rest, then
//           reverse the whole array. Each block gets reversed twice so its
//           internal order is restored, while the single full reversal swaps
//           the two blocks into place.
// Time: O(n) — three passes, each element touched 3 times regardless of k
// Space: O(1), in-place
// Note: k = k % n first — rotating a 5-element array by 7 equals rotating by 2,
//       and without it reverse() would go out of bounds
// Brute force (rotate by one, k times) is O(n*k) and times out on large k
// For a RIGHT rotation the split point is n-k instead of k
package arrays;

public class Leftroatatebyk {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
        reverse(nums, 0, n - 1);
    }

    private void reverse(int nums[], int low, int high) {
        while (low < high) {
            int temp = nums[low];
            nums[low] = nums[high];
            nums[high] = temp;
            low++;
            high--;
        }
    }
}
