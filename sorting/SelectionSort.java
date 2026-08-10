// Selection sort
// Approach: on each pass, scan the unsorted portion to find the index of
//           the minimum, then swap it into position i. The inner loop only
//           tracks minIndex — it does not swap.
// Time: O(n²) always — the inner loop runs fully regardless of input order
// Space: O(1), in-place
// Swaps: exactly one per outer iteration, so n-1 total. Contrast with
//        exchange sort, which swaps on every comparison — same O(n²)
//        comparisons but O(n²) swaps. Matters when swapping is expensive.
// Note: not stable in its basic form (equal elements can be reordered)
class SelectionSort {
    public static void selectionSort(int arr[]) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }
}