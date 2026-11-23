// Sudoku Solver — fill every '.' so each row, column and 3x3 box holds 1-9 once
//
// Backtracking. Same shape as graph colouring:
//     colouring:  for (c = 1; c <= m; c++)       if (isSafe(vertex, c))
//     sudoku:     for (d = '1'; d <= '9'; d++)   if (isValid(r, c, d))
//
// CHOICE vs CHECK — two different questions, easy to blur:
//     choice = WHICH DIGIT goes in this cell → loop '1' to '9'
//     check  = is it already in my row, column, or box → isValid
//
// The board is char[][], so cells hold '5', not 5. Digits are chars and
//   increment fine: for (char d = '1'; d <= '9'; d++).
//
// FINDING THE CELL: unlike N-Queens there's no fixed march — most cells are
//   pre-filled. Scan for the first '.', work on that one, and let recursion
//   handle the rest.
//
//     for (int r = 0; r < 9; r++)
//         for (int c = 0; c < 9; c++)
//             if (board[r][c] == '.') {
//                 for (char d = '1'; d <= '9'; d++)
//                     if (isValid(board, r, c, d)) {
//                         board[r][c] = d;
//                         if (solve(board)) return true;
//                         board[r][c] = '.';
//                     }
//                 return false;          // ← inside the if!
//             }
//     return true;
//
//   THE `return false` IS INSIDE the empty-cell block, so ONE CALL HANDLES
//   EXACTLY ONE CELL — the loops never reach c+1. The next cell is reached by
//   the recursive call, which restarts the scan at (0,0) and sails past the
//   now-filled cell. Re-walking filled cells each time is wasteful but it's
//   81 checks on a 9x9; not worth optimising.
//
// TWO DIFFERENT `return true`s:
//     inside  → a deeper call said solved; just relaying the news upward
//     at end  → BASE CASE. Both loops finished with no '.' found, so every
//               cell is filled. This is where success is first decided, and
//               it fires exactly once, at the bottom.
//
// WHY RECURSE WHEN THERE ARE ALREADY LOOPS: the loops offer the choices; the
//   recursion explores the consequences AND gives you the way back. Plain
//   loops only go forward — fill cell 3 with a 1, jam twenty cells later, and
//   there's no mechanism to return and make it a 2.
//
// WHY PENCIL IN A DIGIT WHEN isValid ALREADY PASSED: because "legal right
//   now" is not "correct". isValid only sees the grid as it stands — it
//   cannot look ahead.
//     Cell A can legally be 4 or 7. Cell C, further down the same column, can
//     ONLY ever be 4. Put 4 in A and C is doomed — but isValid had no way to
//     know. The real answer was 7.
//   If a local check could settle it, you'd walk the grid once and never need
//   recursion at all.
//     isValid  = "breaks no rule at this moment"
//     solve()  = "and the rest still works out"
//
// THE UNDO — board[r][c] = '.' — is the ERASER. You lightly pencil a 3, carry
//   on, hit a dead end, rub it out, try a 4.
//   `return true` EXITS THE METHOD ON THE SPOT, so the eraser line is
//   unreachable on success — a correct digit stays written. Only wrong
//   guesses get rubbed out.
//   Leave a wrong digit in place and every later isValid plans around a
//   number already proven wrong.
//
// isValid — THREE CHECKS, TWO LOOPS (different lengths, so they can't nest):
//     row:  board[r][i], i = 0..8    r fixed, columns vary
//     col:  board[i][c], i = 0..8    c fixed, rows vary
//     box:  board[startRow+i][startCol+j], i,j = 0..2
//   Nesting the row/col checks inside the 3x3 loops caps i at 2 — only the
//   first three cells of the row get checked. (Same mistake as nesting the
//   edge loop inside the box-creation loop in graph colouring.)
//
//   WHICH BOX AM I IN:
//     int startRow = (r / 3) * 3;
//     int startCol = (c / 3) * 3;
//   The 3s DON'T CANCEL — integer division drops the remainder FIRST.
//     r:          0 1 2 3 4 5 6 7 8
//     (r/3)*3:    0 0 0 3 3 3 6 6 6
//   It rounds down to the nearest 3, so every row in a band gets its band's
//   start. Cell (4,7) → box starting at (3,6), covering (3,6) to (5,8).
//   You need the coordinates because checking "the box" means visiting its
//   nine cells, and board[startRow+i][startCol+j] needs real numbers.
//   (Same truncation trick as (low+high)/2 and mid/n for matrix flattening.)
//
//   The row and column checks span the FULL grid — nine cells each, crossing
//   three boxes. They overlap your own box on six cells; the box check is
//   still needed for the four corners, which no line through your cell
//   reaches.
//
// solveSudoku returns void, so it can't report success — wrap the work in a
//   boolean helper. Same split as graphColoring calling solve.
//
// Time: O(9^(empty cells)) worst case, Space: O(81) recursion depth.
public class Sudoku {
    public void solveSudoku(char[][] board) {
        solve(board);
    }

    public static boolean solve(char[][] board) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] == '.') {
                    for (char d = '1'; d <= '9'; d++) {
                        if (isValid(board, r, c, d)) {
                            board[r][c] = d;
                            if (solve(board))
                                return true;
                            board[r][c] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isValid(char[][] board, int r, int c, char d) {
        for (int i = 0; i < 9; i++) {
            if (board[r][i] == d)
                return false;
            if (board[i][c] == d)
                return false;
        }
        int startRow = (r / 3) * 3;
        int startCol = (c / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[startRow + i][startCol + j] == d)
                    return false;
            }
        }
        return true;
    }
}
