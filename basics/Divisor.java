// Print all divisors of a number
// Approach: divisors come in pairs straddling √n, so loop i from 1 to √n;
//           when i divides n, add both i and n/i
// Time: O(√n), Space: O(k) for the k divisors
// Note: use i*i <= n, not Math.sqrt() — avoids floating-point edge errors
//       skip the duplicate when i == n/i (perfect squares)
//       output is unordered; add Collections.sort() if order matters
// Brute force was O(n) — same pair insight cuts it to O(√n)
package basics;

public class Divisor {
    public int[] divisors(int n) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                list.add(i);
                if (i != n / i)
                    list.add(n / i);

            }
        }
        int result[] = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }
}
