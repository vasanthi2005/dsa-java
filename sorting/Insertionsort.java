// Insertion sort
// Approach: take each element and shift it backward past larger elements
//           until it sits in the correct position among the sorted prefix
// Time: O(n²) worst/average, O(n) best on nearly-sorted input
// Space: O(1), in-place. Stable.
// Note: inner loop moves BACKWARD and exits early once the element is
//       placed — that early exit is why it beats selection sort in practice
//       and why library sorts use it for small subarrays
package sorting;

public class Insertionsort {
    public static void insertionSort(int arr[]) {
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            while (j > 0 && arr[j - 1] > arr[j]) {

                int temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
                j--;

            }
        }
    }
}
