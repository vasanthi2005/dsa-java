/*
 * Problem : Reverse an array in place (recursion)
 * Source  : Striver A2Z — Recursion — Basics
 *
 * Approach: Two pointers as parameters instead of loop variables.
 *           Swap arr[start] and arr[end], then recurse inward with
 *           (start+1, end-1). Stop when the pointers meet or cross.
 *           No return value — the array is shared, so mutations are
 *           visible to the caller. Work happens on the way down.
 *
 * Time    : O(n)   — n/2 swaps
 * Space   : O(n)   — recursion stack depth
 */

package basics;

public class Reversearrrec {
    public void reverse(int[] arr, int n) {
        helper(arr, 0, n - 1);
    }

    public void helper(int[] a, int start, int end) {
        if (start >= end)
            return;
        int temp = a[end];
        a[end] = a[start];
        a[start] = temp;
        helper(a, start + 1, end - 1);
    }

}
