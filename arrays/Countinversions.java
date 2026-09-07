// Count inversions — pairs where i < j and nums[i] > nums[j]
// Approach: modified merge sort. During each merge, when an element is taken
//           from the RIGHT half, every element still remaining in the left
//           half is greater than it AND sits at an earlier position — so all
//           of them are inversions with that element, countable in a single
//           addition:  count += mid - left + 1
// Time: O(n log n), Space: O(n) — brute force is O(n²) nested loops
//
// Why the shortcut is valid: it relies on the left half being SORTED, so
//       "everything from left to mid is bigger" holds without checking each
//       one. Merge sort guarantees this — merge() sits on the line after both
//       recursive calls, so it cannot run until both halves are sorted.
//       The problem gives an unsorted array; the sorted halves are something
//       merge sort MANUFACTURES on the way back up.
//
// Note: mid - left + 1 counts positions from left to mid inclusive.
//       Subtraction alone gives the gap between them, not the count — hence
//       the +1. Same as window length = right - left + 1.
//       count must be a class field, not a local: the counting happens inside
//       merging(), several recursion levels below the entry point.
//       count = 0 at the top of the entry method — static fields persist
//       between calls, and the judge runs many test cases in one session.
//       Without the reset, test 2 inherits test 1's total.
//       long, not int — with n = 10^5 the count can reach ~5×10^9.
//
// First problem where merge sort is a TOOL rather than the exercise. Reverse
// Pairs uses the same modification.
class Countinversions {
    static long count = 0;

    public static void mergesort(int arr[], int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergesort(arr, low, mid);
            mergesort(arr, mid + 1, high);
            merging(arr, low, mid, high);
        }
    }

    public static void merging(int arr[], int low, int mid, int high) {
        ArrayList<Integer> list = new ArrayList<>();
        int left = low;
        int right = mid + 1;
        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                list.add(arr[left]);
                left++;
            } else {
                count += mid - left + 1;
                list.add(arr[right]);
                right++;
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

    public long numberOfInversions(int[] nums) {
        count = 0;
        mergesort(nums, 0, nums.length - 1);
        return count;

    }
}