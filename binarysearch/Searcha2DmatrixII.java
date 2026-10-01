// Search a 2D matrix II
//
// NOT BINARY SEARCH — no mid, no halving. Check which property the matrix has
//   before picking a technique:
//
//   Matrix I:  each row sorted AND the first element of a row > last element
//              of the row above → reading it flat gives ONE sorted sequence
//              → flatten and binary search, O(log(m*n))
//
//   Matrix II: rows sorted, columns sorted, but ROWS OVERLAP
//                [ 1,  4,  7, 11]
//                [ 2,  5,  8, 12]
//                [ 3,  6,  9, 16]
//              Read flat: 1,4,7,11,2,5,8,12... NOT sorted (2 comes after 11).
//              Flattening breaks → staircase walk, O(m+n)
//
// THE STAIRCASE: start at the TOP-RIGHT cell.
//     too big   → everything below it in that column is bigger still → go LEFT
//     too small → everything left of it in that row is smaller still → go DOWN
//   Each step eliminates an entire row or column. At most m steps down plus n
//   steps left, so O(m+n). Space O(1).
//
//   Looking for 5:  11 too big → left; 7 too big → left; 4 too small → down;
//                   found 5.
//
// WHY TOP-RIGHT: it's the only corner where the two directions do OPPOSITE
//   things — left always decreases, down always increases. From the top-left
//   both directions increase, so a mismatch tells you nothing about which way
//   to move. Bottom-left works too, by symmetry.
//
// Loop guard: row < m && col >= 0 — you walk off the bottom or off the left.

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;       // rows
        int n = matrix[0].length;    // columns
        int row = 0, column = n - 1;
        while (row < m && column >= 0) {
            if (matrix[row][column] == target) {
                return true;
            } else if (matrix[row][column] > target) {
                column--;
            } else {
                row++;
            }
        }
        return false;
    }
}
            else row++;
        }
        return false;
    }
}