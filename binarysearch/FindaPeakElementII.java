// Find a peak element II (2D)
//
// BINARY SEARCH ON COLUMNS, linear scan within each → O(m log n), Space O(1).
//   (The required complexity in the problem statement tells you the shape.)
//
// NOT SORTED — and binary search still works. The real condition for binary
//   search is NOT "is it sorted" but:
//       after looking at the middle, can I throw away half?
//   Sorting is just the usual way to get that. Here the UPHILL ARGUMENT gives
//   it instead.
//
// THE UPHILL ARGUMENT: if your neighbour is higher, walk that way and you MUST
//   hit a peak. Every step climbs, you can't climb forever (values are finite,
//   border is -1), so you stop somewhere — and where you stop, nothing around
//   is bigger. That's a peak, and it's on the side you walked.
//
// THE TRICK: in the middle column, find the row holding the LARGEST value.
//   Because it's the biggest in its column, it already beats the cells above
//   and below it. Two of the four neighbours are handled for free — only left
//   and right can beat it.
//     bigger than both → it's a peak, return {row, mid}
//     left is bigger   → a peak exists leftward  → high = mid - 1
//     otherwise        → a peak exists rightward → low  = mid + 1
//
// "FIND ANY PEAK" is what makes discarding safe. The discarded half may well
//   contain peaks too — doesn't matter, you only need one. If the problem
//   asked for ALL peaks, binary search wouldn't work at all.
//
// It examines only about log n COLUMNS, not all of them. Each one it does look
//   at is scanned fully down its m rows.
//
// MISTAKES I MADE:
//   mat[i][mid] > mat[i][row] — compares two different COLUMNS in the same row.
//     Want the same column, different rows: mat[i][mid] > mat[row][mid]
//   row declared OUTSIDE the while loop — it must reset to 0 for each new
//     column, or a stale row carries over.
//   No pointer updates in the else branches — the loop spins forever. The
//     else-ifs ARE the search.
//
// Guards: (mid - 1 >= 0), NOT (mid - 1 > 0). Column 0 is a REAL column.
//   With > 0 and mid = 1, you'd treat column 0 as a border and return a
//   non-peak. The guard asks "does a neighbour exist" — index 0 is valid.
//   -1 is the border value the problem specifies; all real values are positive,
//   so -1 never wins and edge columns need no special case.
//
// new int[]{row, mid} builds the coordinate pair inline — the problem wants
//   [i, j], not the value.
public class FindaPeakElementII {
    public int[] findPeakGrid(int[][] mat) {
        int m=mat.length;
        int n=mat[0].length;
        int low=0,high=n-1;
        while(low<=high)
        {
            int row=0;
            int mid=(low+high)/2;
            for(int i=0;i<m;i++)
            {
                if(mat[i][mid]>mat[row][mid]) row=i;
            }
            int left= (mid-1>=0) ? mat[row][mid-1]:-1;
            int right= (mid+1<n) ? mat[row][mid+1]:-1;
            if(left<mat[row][mid] && right<mat[row][mid]) return new int[]{row,mid};
            else if (left > mat[row][mid]) high = mid - 1;
            else low = mid + 1;
        }
        return new int[]{-1,-1};
    }
}
