// Find the row with the maximum number of 1s
//   (rows are sorted ascending, so each row is some 0s then some 1s)
//
// KEY IDEA: because a row is sorted, it looks like  [0, 0, 0, 1, 1, 1].
//   Counting the 1s means finding WHERE THEY START:
//       count = n - (index of the first 1)
//   Finding the first 1 is just LOWER BOUND with target 1.
//
// Approach: loop over the rows normally, binary search within each one.
//   Time: O(m log n) — a plain scan of every cell would be O(m * n)
//   Space: O(1)
//
// Two variables, because you track a count AND the row that achieved it:
//     max      = best count so far
//     rowIndex = which row it came from        ← this is what's returned
//   (Same pairing as majority element, which needed count + candidate.)
//
// `count > max` with STRICT > — ties go to the smaller index, so only replace
//   when a row is genuinely better.
//
// rowIndex starts at -1 and max starts at 0, so a matrix with no 1s anywhere
//   returns -1 without a special case.
//
// WATCH: lower bound's default must be matrix.length, NOT matrix.length - 1.
//   A row of all zeros should return n, giving count = n - n = 0. With n-1 it
//   returns a count of 1, so [[0,0],[0,0]] would wrongly return row 0.
//
// mat[i] gives one row as an int[], which the helper takes like any other
//   array. With only 0s and 1s, `>= 1` and `== 1` are equivalent — the >= is
//   just the lower-bound template's general form.
class Solution {
    public int rowWithMax1s(int[][] mat) {
       int m=mat.length;
       int n=mat[0].length;
       int rowindex=-1;
       int max=0;
       for(int i=0;i<m;i++)
       {
        int firstcount=lowerbound(mat[i],1);
        int count=n-firstcount;
        if(count>max)
        {
            max=count;
            rowindex=i;
        }
       }
       return rowindex;
    }
    public static int lowerbound(int matrix[],int target)
    {
        int low=0,high=matrix.length-1;
        int ans=matrix.length;
        while(low<=high)
        {
            int mid=(low+high)/2;
            if(matrix[mid]>=target)
            {
                ans=mid;
                high=mid-1;
            }
            else
            {
                low=mid+1;
            }
        }
        return ans;
    }
}