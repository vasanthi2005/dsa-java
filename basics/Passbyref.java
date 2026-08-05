// Reverse an array in place (pass by reference demo)
// Approach: two pointers from both ends, swap and move inward until they meet
// Time: O(n), Space: O(1) — modifies the original array, no copy
// Note: Java is pass-by-value, but for arrays the value is a reference,
//       so changes to elements are visible to the caller
package basics;

public class Passbyref {
    public void reverse(int[] arr) {
        int start = 0, end = arr.length - 1;
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
}
