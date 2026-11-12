// N-Queens — place n queens on an n x n board, none attacking another
//
// THE KEY INSIGHT: EVERY COLUMN HOLDS EXACTLY ONE QUEEN. Two in a column
//   would attack each other, and you need n queens across n columns — so one
//   each, no exceptions.
//   That collapses the problem: you're not searching the whole grid for
//   places to put queens. You go COLUMN BY COLUMN, and the only question per
//   column is WHICH ROW.
//
//     for (int row = 0; row < n; row++)        // loop ACROSS the choices
//         if (isSafe(board, row, col, n)) {
//             board[row][col] = 'Q';           // place
//             place(result, board, col + 1, n);// recurse FORWARD a column
//             board[row][col] = '.';           // remove
//         }
//
//   Same shape as letter-combinations: a loop over the options at one
//   position, recursion to move to the next position.
//
// THE UNDO HAPPENS AFTER THE RECURSIVE CALL RETURNS, not before. The queen
//   stays on the board for the whole of place(col+1) — which is exactly what
//   isSafe needs to see when checking later columns. Removing it means "I've
//   finished exploring everything that starts with a queen here", not "take
//   it off while I look ahead."
//   Without the removal, the next loop iteration adds a SECOND queen to the
//   same column and the board fills with leftovers.
//
//     subsets      current.add(x)        ...  current.remove(...)
//     word search  board[r][c] = '#'     ...  board[r][c] = saved
//     n-queens     board[row][col] = 'Q' ...  board[row][col] = '.'
//
// isSafe — THREE CHECKS, ALL LEFTWARD. Nothing is placed to the right yet, so
//   there's nothing there to find.
//     ←   same row:    row fixed,  j--
//     ↖   up-left:     i--,        j--
//     ↙   down-left:   i++,        j--
//   EVERY ONE HAS j DECREASING. That's the tell — if a loop's j goes up,
//   it's wrong.
//   NO COLUMN CHECK NEEDED — one-queen-per-column is built into the structure.
//
//   A DIAGONAL IS ONE LOOP WITH BOTH COUNTERS MOVING, not two nested loops.
//     from (3,3) going up-left: (2,2) (1,1) (0,0) — both -1 each step
//   Nested loops would check the whole RECTANGLE above-left, far too much.
//
//   MISTAKES I MADE: checked the column instead of the row (varied the first
//   index while holding col fixed); used nested loops for the diagonals; wrote
//   the third check as down-RIGHT (j++) instead of down-left; wrote
//   `i = row+1, j = col-1;` — a comma only works in a DECLARATION
//   (int i = 1, j = 2;), not when assigning to existing variables.
//
// OUTPUT FORMAT: List<List<String>> — a list of boards, each board a list of
//   ROW strings. Build as char[][], then new String(board[r]) per row when
//   saving. Arrays.fill(row, '.') to start each row empty.
//
// Base case col == n: all columns filled, so it's a solution.
// n = 2 and n = 3 return an empty list — no arrangement exists, and the code
//   handles that with no special case.
//
// Time: O(n!) roughly, Space: O(n²) for the board.
class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result=new ArrayList<>();
        char board[][]=new char[n][n];
        for(char row[]:board) Arrays.fill(row,'.');
        place(result,board,0,n);
        return result;
    }
    static void place(List<List<String>> result,char[][]board,int col,int n)
    {
        if(col==n)
        {
            List<String> one =new ArrayList<>();
            for(char row[]:board)
            one.add(new String(row));
            result.add(one);
            return ;
        }
        for(int row=0;row<n;row++)
        {
            if(isSafe(board,row,col,n))
            {
                board[row][col]='Q';
                place(result,board,col+1,n);
                board[row][col]='.';
            }
        }
    }
    static boolean isSafe(char[][] board, int row, int col, int n) 
    {
        for(int i=col-1;i>=0;i--)
        {
            if(board[row][i]=='Q')
            return false;
        }
        int i=row-1,j=col-1;
        while(i>=0 && j>=0)
        {
            if(board[i][j]=='Q') return false;
            i--;
            j--;

        }
        i=row+1;j=col-1;
        while(i<n && j>=0)
        {
            if(board[i][j]=='Q') return false;
            i++;
            j--;
        }
        return true;
    }
}