// Matrix median — rows are sorted, the matrix as a whole is not
//
// WHY NOT SEARCH THE CELLS: only rows are sorted. Read flat you get
//   1,4,9,2,5,6,... — no order at all, so there's no sequence to search.
//
// SO SEARCH NUMBERS INSTEAD (answer space, same move as ships/books/gas).
//   The median is a number somewhere between the smallest and largest value in
//   the matrix. THAT range is ordered — numbers always are. Nothing about the
//   matrix needs sorting for that.
//
//   Guess a number, ask: how many cells are <= it?
//     [[1,4,9],[2,5,6],[3,7,8]] — 9 cells, median is the 5th smallest
//     guess 4 → count 4 → not enough below it → median is higher
//     guess 5 → count 5 → enough → answer is 5
//
// RANGE: smallest value .. largest value in the matrix.
//   Unlike ships (max..sum), the answer here IS an actual element, so the range
//   is just the matrix's own span. Outside it, guesses tell you nothing —
//   below, nothing qualifies; above, everything does.
//   Rows are sorted, so each row's smallest is in COLUMN 0 and its largest in
//   the LAST COLUMN. One loop over the rows gets both bounds — no full scan.
//   low starts at MAX_VALUE and high at MIN_VALUE so the first comparison
//   always updates them.
//
// need = (m*n)/2. For 9 cells that's 4 — the median has 4 cells below it. You
//   want the smallest number with MORE than `need` cells at or below it.
//     count <= need → too small → low = mid + 1
//     count >  need → might be it, or lower → high = mid - 1
//   `<= need`, not `< need` — a count of exactly 4 is still too few.
//
// THE COUNTER: rows are sorted, so "how many <= x in this row" is upper bound —
//   one binary search per row, not a scan. low lands on the boundary, and the
//   boundary IS the count.
//   `row[mid] <= x` with the equals — you're counting cells <= x, so a cell
//   holding exactly x must be included.
//   `for (int[] row : matrix)` — each element of a 2D array is a ROW (an int[]),
//   not an int.
//
// Time: O(log(max-min) * m log n), Space: O(1)
//
// BOTH LOOPS USE THE SAME TEMPLATE. The pairing that matters:
//     while (low <= high)  with  mid + 1 / mid - 1    pointers CROSS
//     while (low <  high)  with  mid + 1 / high = mid pointers MEET
//   Never mix. If a pointer might not move (high = mid), the loop must stop
//   before they're equal, or it spins forever.
public class Matrixmedian {
    public int findMedian(int[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;

    int low = Integer.MAX_VALUE, high = Integer.MIN_VALUE;
    for (int i = 0; i < m; i++) {
        low = Math.min(low, matrix[i][0]);        
        high = Math.max(high, matrix[i][n - 1]);  
    }

    int need=(m*n)/2;
    while(low<=high)
    {
        int mid=(low+high)/2;
        if(count(matrix,mid)<=need) low=mid+1;
        else high=mid-1;
    }
    return low;
    }

    public static int count(int matrix[][],int x)
    {
        int count=0;
        for(int row[]:matrix)
        {
            int low=0,high=row.length-1;
            while(low<=high)
            {
                int mid=(low+high)/2;
                if(row[mid]<=x) low=mid+1;
                else
                high=mid-1;
            }
            count+=low;
        }
        return count;
    }
}
