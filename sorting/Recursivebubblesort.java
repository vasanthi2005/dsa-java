// Recursive bubble sort
// Approach: one pass bubbles the largest element to the end, then
//           recurse on the first n-1 elements
// Base case: n == 1, a single element is sorted
// Time: O(n²), Space: O(n) for the call stack — worse than iterative,
//       which is O(1). No practical advantage; a recursion exercise.
package sorting;

public class Recursivebubblesort {
    static void bubbleSort(int[] arr, int n) {
        if (n <= 1)
            return;
        for (int j = 1; j < n; j++) {
            if (arr[j - 1] > arr[j]) {
                int temp = arr[j - 1];
                arr[j - 1] = arr[j];
                arr[j] = temp;
            }

        }
        bubbleSort(arr, n - 1);
    }

    // optimal approach
    static void bubbleSort(int[] arr, int n) {
        if (n <= 1)
            return;
        boolean swapped = false;
        for (int j = 1; j < n; j++) {
            if (arr[j - 1] > arr[j]) {
                int temp = arr[j - 1];
                arr[j - 1] = arr[j];
                arr[j] = arr[j - 1];
                swapped = true;
            }
        }
        if (!swapped)
            return;
        bubbleSort(arr, n - 1);
    }
}
