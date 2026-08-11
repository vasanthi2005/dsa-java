// Quick sort
// Approach: pick arr[low] as pivot, partition so smaller elements go left
//           and larger go right, then recurse on both sides excluding the pivot
// Partition: two pointers search from each end for misplaced elements and
//            swap them, until they cross; then the pivot swaps into position end
// Time: O(n log n) average, O(n²) worst — worst case hits when the pivot is
//       always smallest/largest, e.g. an already-sorted array with arr[low] pivot
// Space: O(log n) average call stack, O(n) worst. In-place — no temp array,
//        unlike merge sort. Not stable.
// Note: inner conditions are deliberately asymmetric — <= on start, > on end.
//       Matching them either loops forever or walks end past low on duplicates.
package sorting;

import java.util.*;

public class Quicksort {
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int p = partition(arr, low, high);
            quickSort(arr, low, p - 1);
            quickSort(arr, p + 1, high);
        }
    }

    public static int partition(int arr[], int low, int high) {
        int pivot = arr[low];
        int start = low + 1;
        int end = high;

        while (start <= end) {
            while (start <= high && arr[start] <= pivot) {
                start++;
            }
            while (end >= low && arr[end] > pivot) {
                end--;
            }
            if (start < end) {
                int temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
            }
            System.out.println("start=" + start + " end=" + end + " " + Arrays.toString(arr));
        }

        int temp = arr[low];
        arr[low] = arr[end];
        arr[end] = temp;
        return end;
    }

    public static void main(String[] args) {
        int[] arr = { 5, 3, 8, 1, 9, 2 };
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}