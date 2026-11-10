// Word Search — does `word` exist in the grid along adjacent cells, no cell
//   reused within a path?
//
// Backtracking again, but the CHOICES ARE THE FOUR DIRECTIONS — up, down,
//   left, right. No take/skip here. (Like letter-combinations having 3-4
//   letters instead of two options.)
//
// DECIDING SHAPE, not collecting: one yes/no answer, so the helper returns
//   boolean and the answers thread back up through ||.
//     boolean found = search(up) || search(down) || search(left) || search(right);
//   || SHORT-CIRCUITS — if "up" finds it, the other three never run.
//
// PARAMETERS: r, c = where you're standing. i = how many letters matched so far.
//
// THREE BASE CASES, IN THIS ORDER:
//     i == word.length()  → TRUE. Matched the whole word — this is SUCCESS.
//                           (I wrote `return false` here; every complete match
//                            then reported failure and the method always
//                            returned false.)
//     off the grid        → false
//     wrong letter        → false
//   The grid check MUST come before board[r][c], or you index out of bounds.
//
// THE THREE MARKING LINES — don't step on the same cell twice in one path:
//     char saved = board[r][c];   // remember the letter
//     board[r][c] = '#';          // block the cell
//     ... explore ...
//     board[r][c] = saved;        // unblock it
//   Like dropping a stone on each square you walk on, then picking it up when
//   you back out.
//
//   WHY IT'S NEEDED: board = [[A,B,C,E],[S,F,C,S],[A,D,E,E]], word "ABCB"
//   should be FALSE. After A(0,0) B(0,1) C(0,2) you need another B — and
//   (0,1) is a neighbour holding a B. Without blocking you'd step back onto
//   it and wrongly return true. With '#' there, the letter check fails
//   ('#' != 'B') and the path is rejected.
//
//   WHY YOU MUST RESTORE: that cell is only off-limits for THIS path. A
//   different path may legitimately need it. Never restoring fills the board
//   with '#' and later searches fail for no reason.
//
//   Same undo as subsets, on the grid instead of a list:
//     current.add(x)     ...  current.remove(...)
//     board[r][c] = '#'  ...  board[r][c] = saved
//
// OUTER DOUBLE LOOP over every cell — the word could start anywhere. (This is
//   a real loop over starts, unlike the for(i) I wrongly added in palindrome
//   partitioning; there the index advanced through one input, here each cell
//   is a separate independent attempt.)
//
// Time: O(m * n * 4^L) where L = word length. Space: O(L) recursion depth.
public class Wordsearch {
    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (search(board, word, r, c, 0))
                    return true;
            }
        }
        return false;
    }

    public static boolean search(char[][] board, String word, int r, int c, int i) {
        if (i == word.length())
            return true;

        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length)
            return false;

        if (board[r][c] != word.charAt(i))
            return false;

        char saved = board[r][c];
        board[r][c] = '#';

        boolean found = search(board, word, r - 1, c, i + 1) ||
                search(board, word, r + 1, c, i + 1) ||
                search(board, word, r, c - 1, i + 1) ||
                search(board, word, r, c + 1, i + 1);

        board[r][c] = saved;

        return found;
    }

}
