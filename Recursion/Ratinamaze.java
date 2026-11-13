// Rat in a Maze — all paths from (0,0) to (n-1,n-1), moving U/D/L/R through
//   cells valued 1, no cell revisited
//
// WORD SEARCH SHAPED, NOT N-QUEENS SHAPED. I reached for the N-Queens
//   template first and it doesn't fit — worth knowing why:
//     N-Queens: one queen per column, so you walk columns FORWARD and loop
//               over rows. Position decided one coordinate at a time.
//     Here:     the rat STANDS somewhere and steps to a neighbour. Both
//               coordinates change together, and the four directions ARE the
//               branches. No loop over positions.
//
//     build(result, current, row, col, grid)    ← row, col = where I'm standing
//
// THE FOUR MOVES — each shifts one coordinate and appends one letter:
//     D → row + 1      L → col - 1      R → col + 1      U → row - 1
//   In D, L, R, U order, so the output comes out ALPHABETICAL for free.
//   All four are needed even when the examples don't use L or U — a wall can
//   force a detour:
//       1 1 1 1
//       0 0 1 1      goal (3,3), and (2,3) is blocked, so RRRDLDDR —
//       1 1 1 0      the L steps back left because down from (1,3) is walled
//       1 1 1 1
//
// CHECK ORDER MATTERS — guards first, success LAST:
//     1. in bounds?        (>= not >, index n is already off the board)
//     2. walkable?         grid[row][col] != 1 → return
//     3. arrived?          row == n-1 && col == n-1 → save
//   Put the arrival check first and a BLOCKED destination still gets saved —
//   the success test fires before anything notices the cell is a wall. Same
//   bug on a 1x1 grid holding 0: it would save an empty path.
//   (Word search had the same ordering rule, for the same reason.)
//
// VISITED MARKING:
//     grid[row][col] = 0;    // mark — no `saved` variable needed, a walkable
//     ... four calls ...     //        cell is always 1
//     grid[row][col] = 1;    // unmark
//   The != 1 check covers both "wall" and "already on this path", since a
//   visited cell is temporarily 0.
//
// NO UNDO ON `current` — it's a String, so current + "D" builds a new one.
//   Only the grid cell needs restoring.
//
// Java spellings: array.length (field), string.length() (method), list.size().
//   And grid is int[][], so compare to 1, not '1' (which is 49).
//
// Time: O(4^(n²)) worst case, Space: O(n²) recursion depth.

import java.util.ArrayList;
import java.util.List;

public class Ratinamaze {
    public List<String> findPath(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        List<String> result = new ArrayList<>();
        build(result, "", 0, 0, grid);
        return result;
    }

    public static void build(List<String> result, String current, int row, int col, int grid[][]) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length)
            return;

        if (grid[row][col] != 1)
            return;

        if (row == grid.length - 1 && col == grid[0].length - 1) {
            result.add(current);
            return;

        }

        grid[row][col] = 0;
        build(result, current + "D", row + 1, col, grid);
        build(result, current + "L", row, col - 1, grid);
        build(result, current + "R", row, col + 1, grid);
        build(result, current + "U", row - 1, col, grid);

        grid[row][col] = 1;

    }
}
