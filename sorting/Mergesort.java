// Merge sort
// Approach: divide and conquer — recursively split the range in half until
//           single elements, then merge sorted halves back together
// mergeSort: splits only; merge does all the actual sorting
// merge: two pointers walking the two sorted halves, taking the smaller
//        each time into a temp list, then copying back into arr[low..high]
// Time: O(n log n) always — log n levels of splitting, O(n) merge per level
// Space: O(n) for the temp list
// Stable: takes from the left half on ties
// Note: high is the last INDEX, so call with arr.length - 1, not arr.length
//       copy-back uses two counters — i walks arr from low, k walks list from 0
package sorting;

import java.util.*;

public class Mergesort {
    public static void mergeSort(int arr[], int low, int high) {
        if (low >= high)
            return;
        int mid = (low + high) / 2;
        mergeSort(arr, low, mid);
        mergeSort(arr, mid + 1, high);
        merge(arr, low, mid, high);
    }

    public static void merge(int arr[], int low, int mid, int high) {
        ArrayList<Integer> list = new ArrayList<>();
        int left = low;
        int right = mid + 1;
        while (left <= mid && right <= high) {
            if (arr[left] > arr[right]) {
                list.add(arr[right]);
                right++;
            } else {
                list.add(arr[left]);
                left++;

            }
        }
        while (left <= mid) {
            list.add(arr[left]);
            left++;
        }
        while (right <= high) {
            list.add(arr[right]);
            right++;
        }
        int k = 0;
        for (int i = low; i <= high; i++) {
            arr[i] = list.get(k);
            k++;
        }

    }

    public static void main(String[] args) {
        int arr[] = new int[] { 5, 2, 9, 1 };
        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
