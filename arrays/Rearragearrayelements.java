// Rearrange array elements by sign (equal positives and negatives, positive first)
// Approach: positives always land at even indices (0,2,4...) and negatives at
//           odd (1,3,5...), so no separation is needed. One pass, writing each
//           element to the next slot of its parity.
// Time: O(n), Space: O(n) for the output array
// Note: relative order is preserved for free, since elements are written in the
//       order encountered. Two counters stepping by 2 rather than two lists —
//       the brute force (split into positive/negative lists, then interleave)
//       is also O(n) but needs two extra collections and two passes.
package arrays;

class Rearrangearrayelements {
    public int[] rearrangeArray(int[] nums) {
        int arr[] = new int[nums.length];
        int p = 0, n = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                arr[p] = nums[i];
                p += 2;
            } else {
                arr[n] = nums[i];
                n += 2;
            }

        }
        return arr;
    }
}