package arrays;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle {
    // ============================================================
// Pascal's Triangle I — value at row r, column c (1-indexed)
// ============================================================
// Approach: Pascal's triangle entries ARE binomial coefficients. The value at
//           (r, c) is (r-1) choose (c-1) — the -1s because the problem counts
//           from 1.
//           Computed multiplicatively rather than with factorials: take one
//           numerator term and one denominator term per iteration, so the
//           running value never grows large.
//             nCk = n!/(k!(n-k)!)  simplifies to  (n × n-1 × ...) / (1 × 2 × ...)
//             with k terms top and bottom
// Time: O(k), Space: O(1)
// Note: the naive factorial version overflows — int breaks at 13!, long at 21! —
//       even though the ANSWER fits comfortably in an int. Only the
//       intermediates are huge, which is why dividing as you go fixes it.
//       Division is exact at every step: the running value is always itself a
//       valid binomial coefficient.

public int pascalTriangleI(int r, int c) {
    int n = r - 1;
    int k = c - 1;

    long result = 1;
    for (int i = 0; i < k; i++) {
        result = result * (n - i) / (i + 1);
    }
    return (int) result;
}


// ============================================================
// Pascal's Triangle II (LC 118) — generate first numRows
// ============================================================
// Approach: build each row from the previous one. Row i has i+1 elements;
//           positions 0 and i are always 1, every interior position j is
//           prev.get(j-1) + prev.get(j).
// Time: O(n²), Space: O(n²) for the output
// Note: i picks the ROW, j picks the POSITION within it. The sum indexes prev
//       by j-1 and j, not i-1 and i.
//       result.add(row) goes AFTER the inner loop, not inside it.
//       Edge check is j == 0 || j == i — the last index of row i is i.

public List<List<Integer>> generate(int numRows) {
    List<List<Integer>> result = new ArrayList<>();

    for (int i = 0; i < numRows; i++) {
        List<Integer> row = new ArrayList<>();
        for (int j = 0; j <= i; j++) {
            if (j == 0 || j == i) {
                row.add(1);
            } else {
                List<Integer> prev = result.get(i - 1);
                row.add(prev.get(j - 1) + prev.get(j));
            }
        }
        result.add(row);
    }
    return result;
}


// ============================================================
// Pascal's Triangle III (LC 119) — row at rowIndex (0-indexed)
// ============================================================
// Approach: row n is nC0, nC1, ... nCn. Each element follows from the previous
//           by one multiply and one divide, so the row builds in a single pass
//           from a running value — no triangle above needed.
// Time: O(n), Space: O(1) beyond the output. The naive approach builds the
//       whole triangle in O(n²) and discards all of it.
// Note: the leading 1 is added before the loop, so the loop produces the
//       remaining rowIndex elements — bound is i < rowIndex, not <=. With <=
//       the extra iteration multiplies by (rowIndex - rowIndex) = 0.
//       The trailing 1 falls out of the arithmetic; no edge case needed.
//       0-INDEXED here, unlike variant I which was 1-indexed and needed r-1.
//       Always verify indexing against the worked examples in the statement.

public List<Integer> getRow(int rowIndex) {
    List<Integer> list = new ArrayList<>();
    long result = 1;
    list.add(1);

    for (int i = 0; i < rowIndex; i++) {
        result = result * (rowIndex - i) / (i + 1);
        list.add((int) result);
    }
    return list;
}
}
