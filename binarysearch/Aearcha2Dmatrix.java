// Search a 2D matrix
//
// KEY IDEA: this matrix has a special property — each row is sorted, AND the
//   first element of a row is larger than the last element of the row above.
//     [ 1,  3,  5,  7]
//     [10, 11, 16, 20]
//     [23, 30, 34, 60]
//   Read left to right, top to bottom: 1,3,5,7,10,11,16,20,23,30,34,60.
//   ENTIRELY SORTED. It's a sorted array that's been wrapped into rows.
//
// So: pretend it's flat and do one ordinary binary search over all m*n cells.
//   Think of a chocolate bar — 12 squares numbered 0 to 11:
//       row 0:  0  1  2  3
//       row 1:  4  5  6  7
//       row 2:  8  9 10 11
//   Where is square 6? Each row holds 4 squares, so:
//       row    = 6 / 4 = 1
//       column = 6 % 4 = 2
//
//     int row = mid / n;      // n = COLUMNS, how many cells before wrapping
//     int col = mid % n;      // position within that row
//
// Time: O(log(m*n)), Space: O(1)
//
// TWO THINGS TO GET RIGHT:
//   n must be matrix[0].length (columns), NOT matrix.length (rows). You divide
//     by how many cells fit in a row. Dividing by the row count lands you on
//     the wrong cell entirely.
//   high must be m*n - 1, NOT matrix.length - 1. You're searching all the
//     cells, not just the rows — otherwise most of the matrix is invisible.
//
// Alternative: two searches — find the right row, then search inside it. Same
//   complexity, more code.
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m=matrix.length;
        int n=matrix[0].length;
        int low=0,high=m*n-1;
        while(low<=high)
        {
            int mid=(low+high)/2;
            int row=mid/n;
            int column=mid%n;
            if(matrix[row][column]==target)
            return true;
            else if(matrix[row][column]<target)
            low=mid+1;
            else
            high=mid-1;
        }
        return false;
    }
}