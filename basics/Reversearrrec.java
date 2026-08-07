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
