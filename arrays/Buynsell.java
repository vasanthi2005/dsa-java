// Best time to buy and sell stock
// Approach: single pass tracking two separate things — the minimum price seen
//           so far (the best buy point) and the maximum profit found. At each
//           day, profit if sold today = arr[i] - min.
// Time: O(n), Space: O(1) — beats the O(n²) all-pairs brute force
// Note: a price and a profit are different quantities and need different
//       variables. My first attempt used one variable for both, so it stored
//       a price on iteration 0 and then subtracted from it as if it were a profit.
package arrays;

public class Buynsell {
    public int stockBuySell(int[] arr, int n) {
        int min = arr[0];
        int max = 0;
        for (int i = 1; i < n; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
            max = Math.max(max, arr[i] - min);
        }
        return max;
    }
}
