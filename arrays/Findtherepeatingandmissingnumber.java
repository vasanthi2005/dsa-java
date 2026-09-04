package arrays;

public class Findtherepeatingandmissingnumber {
    // ============================================================
    // Find the repeating and missing number
    // Values are 1..n, one appears twice, one is absent.
    // ============================================================

    // ---------- Version 1: frequency array ----------
    // Approach: use the VALUES as indices into a count array. First pass tallies
    // occurrences, second scans values 1..n for the one with count 2 and
    // the one with count 0.
    // Time: O(n), Space: O(n)
    // Note: array is size n+1 because values run 1..n, so slot n must exist;
    // slot 0 goes unused.
    // Second loop runs 1 to n INCLUSIVE — with < you'd never check value n,
    // and if n were the missing or repeating one you'd return 0.

    public int[] findMissingRepeatingNumbersCount(int[] nums) {
        int n = nums.length;
        int[] count = new int[n + 1];
        int r = 0, m = 0;

        for (int i = 0; i < n; i++) {
            count[nums[i]]++;
        }

        for (int i = 1; i <= n; i++) {
            if (count[i] == 2)
                r = i;
            if (count[i] == 0)
                m = i;
        }

        return new int[] { r, m };
    }

    // ---------- Version 2: two equations, O(1) space ----------
    // Approach: let x = repeating, y = missing.
    // actualSum - expectedSum = x - y
    // actualSqSum - expectedSqSum = x² - y² = (x + y)(x - y)
    // Dividing the second by the first gives x + y. Then:
    // x = (diff + sum) / 2, y = sum - x
    // Expected values: sum of 1..n = n(n+1)/2
    // sum of squares 1..n = n(n+1)(2n+1)/6
    // Time: O(n) single pass, Space: O(1) — beats the frequency array on space
    //
    // OVERFLOW — the reason this needs care:
    // `long n = nums.length` not int. n*(n+1)*(2n+1) is ~2×10^15 for n = 10^5,
    // far past int range, and it wraps BEFORE being assigned to a long.
    // `(long) i * i` not `i * i`. Same issue — an int multiply overflows and is
    // only then widened.
    // THE RULE: the variable's type does not affect how the expression is
    // evaluated. Java computes the right-hand side in int arithmetic and widens
    // afterwards. Cast an OPERAND, not the result.
    // (Third time this has bitten me — also in nCr and 4-Sum.)

    public int[] findMissingRepeatingNumbers(int[] nums) {
        long n = nums.length;

        long expSum = n * (n + 1) / 2;
        long expSqSum = n * (n + 1) * (2 * n + 1) / 6;

        long actSum = 0;
        long actSqSum = 0;

        for (int i : nums) {
            actSum += i;
            actSqSum += (long) i * i;
        }

        long diff = actSum - expSum; // x - y
        long sqDiff = actSqSum - expSqSum; // x² - y²
        long sum = sqDiff / diff; // x + y

        long x = (diff + sum) / 2; // repeating
        long y = sum - x; // missing

        return new int[] { (int) x, (int) y };
    }
}
