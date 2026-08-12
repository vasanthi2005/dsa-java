// Largest element in an array
// Approach: single pass, track the max seen so far
// Time: O(n), Space: O(1) — optimal, every element must be examined
// Note: assumes n >= 1; arr[0] would throw on an empty array
package arrays;

public class Largestnum {
    public static int largest(int[] arr) {
        if (arr.length == 0)
            return -1;
        // code here
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i])
                max = arr[i];
        }
        return max;
    }
}
