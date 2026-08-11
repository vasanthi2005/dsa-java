// Recursive insertion sort
// Approach: for element i, shift it backward past larger elements until
//           placed, then recurse on i+1. The outer loop becomes the recursion;
//           the inner backward shift stays iterative.
// Base case: i == n — every element has been placed
// Time: O(n²) worst, O(n) on nearly-sorted input (inner while exits early)
// Space: O(n) call stack — worse than the iterative version's O(1)
// Note: the recursion parameter is i, not n. Base case must test i against n,
//       not n against 1, since n never changes.
package sorting;

public class Recursiveinsertionsort {
    static void insertionSort(int[] arr, int i, int n) {
        if (i == n)
            return;
        int j = i;
        while (j > 0 && arr[j - 1] > arr[j]) {
            int temp = arr[j];
            arr[j] = arr[j - 1];
            arr[j - 1] = temp;
            j--;
        }
        insertionSort(arr, i + 1, n);
    }
}
