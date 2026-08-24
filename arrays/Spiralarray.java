// Spiral matrix traversal
// Approach: four boundaries — top, bottom, left, right — marking the edges of
//           the unvisited region. Each circuit does four traversals and moves
//           one boundary inward after each:
//             top row, left -> right      then top++
//             right column, top -> bottom then right--
//             bottom row, right -> left   then bottom--
//             left column, bottom -> top  then left++
//           Loop while top <= bottom && left <= right.
// Time: O(m*n), Space: O(1) excluding the output
// Note: the inward spiral is EMERGENT — no code says "go inward". Each
//       boundary update shrinks the rectangle, and the same four traversals
//       then run on the smaller box.
//       Each traversal starts from the CURRENT boundary values, which is how
//       already-visited cells are skipped without tracking them.
//       Guards needed before traversals 3 and 4 on non-square matrices. The
//       loops self-guard (a for with a false condition just doesn't run), but
//       the boundary update after them fires regardless — so the `if` is
//       protecting the decrement, not the loop.
package arrays;

import java.util.ArrayList;
import java.util.List;

public class Spiralarray {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int top = 0, bottom = matrix.length - 1, left = 0, right = matrix[0].length - 1;
        while (top <= bottom && left <= right) {
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            top++;
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--;
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    result.add(matrix[bottom][i]);
                }
                bottom--;
            }
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++;
            }
        }
        return result;

    }
}
