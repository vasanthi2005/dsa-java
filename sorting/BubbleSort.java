// Bubble sort
// Approach: repeatedly compare adjacent pairs and swap if out of order;
//           each pass bubbles the largest remaining element to the end
// Time: O(n²) worst/average, O(n) best with the swapped-flag optimisation
// Space: O(1), in-place. Stable.
// Note: inner loop bound shrinks (j < i) since the tail is already sorted
//       early exit when a pass makes no swaps — the only reason bubble
//       sort beats selection sort on anything
package sorting;

public class BubbleSort {
    public static void bubbleSort(int arr[]) {
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // Optimal Bubble Sort : If a pass/loop completes without a swap that means the
    // arr is sorted
    // That makes bubble sort O(n) on already-sorted input — its only advantage over
    // selection sort, which is always O(n²).
    public static void optimalBubbleSort(int arr[]) {
        for (int i = arr.length - 1; i > 0; i--) {
            boolean swapped = false;
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    // swap
                    swapped = true;
                }
            }
            if (!swapped)
                break;

        }
    }
}
