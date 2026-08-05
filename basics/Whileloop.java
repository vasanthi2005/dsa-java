// Sum of first 50 positive integers ending with digit d
// Approach: iterate from 1, count matches where num % 10 == d, stop at 50
// Time: O(1) — bounded at 500 iterations first method, O(1) — optimal method, Space: O(1)
package basics;

public class Whileloop {
    public int whileLoop(int d) {
        // brute force approach
        int sum = 0;
        int count = 0;
        int num = 1;
        while (count < 50) {

            if (num % 10 == d) {
                sum += num;
                count++;
            }
            num++;
        }
        return sum;
    }

    // optimal approach
    public int whileLoopOptimal(int d) {
        int sum = 0;
        int num = (d == 0) ? 10 : d;
        for (int count = 0; count < 50; count++) {
            sum += num;
            num += 10;
        }
        return sum;
    }
}
