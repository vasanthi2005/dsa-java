// Set matrix zeroes (in-place)
// Approach: two passes. First pass records WHICH rows and columns contain a
//           zero, in two boolean arrays. Second pass zeroes any cell whose row
//           or column is marked.
// Time: O(m*n), Space: O(m+n)
// Why two passes: zeroing as you scan makes the new zeros indistinguishable
//           from original ones, and the whole matrix cascades to zero. The
//           marking pass separates reading the original from writing the result.
// Note: rows and columns are never handled as units — every cell is visited
//           individually and decides from two lookups. A whole row zeroes
//           because all its cells share the same i.
// O(1) space version exists: use the matrix's own first row and column as the
//           marker arrays, plus one extra flag for the overlap at (0,0).
package arrays;

public class Setmatrixzeros {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean rowzero[] = new boolean[m];
        boolean colzero[] = new boolean[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    rowzero[i] = true;
                    colzero[j] = true;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rowzero[i] || colzero[j])
                    matrix[i][j] = 0;
            }

        }
    }
}
