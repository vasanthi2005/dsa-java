// Rotate matrix 90 degrees clockwise (in-place)
// Approach: transpose, then reverse each row.
//   transpose: swap matrix[i][j] with matrix[j][i] — flips across the main
//              diagonal, turning rows into columns
//   reverse:   two pointers per row moving inward
// Time: O(n²), Space: O(1)
// Note: the transpose inner loop must start at j = i+1. Starting at 0 swaps
//       every pair twice — once as (i,j), once as (j,i) — which undoes itself
//       and leaves the matrix unchanged. Diagonal cells (i == j) never move.
// Same shape as the rotate-by-k reversal trick: a hard rearrangement built
//       from two simple ones.
package arrays;
class Rotatearray90
{
     public void rotate(int[][] matrix) {
         int n = matrix.length;
         for(int i=0;i<n;i++)
         {
            for(int j=i+1;j<n;j++)
            {
                int temp=matrix[i][j];
                matrix[i][j]=matrix[j][i];
                matrix[j][i]=temp;           
            }
         }
         for(int i=0;i<n;i++)
         {
            int left=0,right=matrix.length-1;
            while(left<right)
            {
                int temp=matrix[i][left];
                matrix[i][left]=matrix[i][right];
                matrix[i][right]=temp;
                left++;
                right--;
            }
         }
    }

}