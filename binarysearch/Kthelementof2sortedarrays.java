// Kth element of two sorted arrays — O(log(min(m,n)))
//
// THIS IS THE MEDIAN PROBLEM with the left half sized to k instead of
//   (total+1)/2. The median is just "the kth element where k is the middle."
//
// THE IDEA: you want the k smallest numbers across both arrays. You don't need
//   to merge — just take some from the front of each.
//     a = [2,3,6,7,9], b = [1,4,8,10], k = 5
//     take 3 from a (2,3,6) and 2 from b (1,4) → five numbers
//   Are they the RIGHT five? Check that nothing left behind is smaller than
//   something taken — the two diagonals:
//     l1 <= r2   (6 <= 8 ✓)      l2 <= r1   (4 <= 7 ✓)
//   Yes → the answer is the biggest one taken: max(l1, l2) = 6
//
// Choosing how many to take from `a` FORCES the count from `b` (they must add
//   to k), so there's only one number to search for.
//
// Range is tighter than the median version:
//     low  = max(0, k - n)   if b is shorter than k, you MUST take the rest
//                            from a
//     high = min(k, m)       can't take more than k, nor more than a holds
//
// Always search the SMALLER array — swap at the top.
//
// No odd/even branch and no averaging — you want one specific element, not a
//   middle value.
class Solution {
    public int kthElement(int[] a, int[] b, int k) {
         if (a.length > b.length) return kthElement(b, a, k);
        int m=a.length,n=b.length;
        int low=Math.max(0,k-n),high=Math.min(k,m);
        while(low<=high)
        {
            int cut1=(low+high)/2;
            int cut2=k-cut1;
            int l1 = (cut1 == 0) ? Integer.MIN_VALUE : a[cut1 - 1];
            int r1 = (cut1 == m) ? Integer.MAX_VALUE : a[cut1];
            int l2 = (cut2 == 0) ? Integer.MIN_VALUE : b[cut2 - 1];
            int r2 = (cut2 == n) ? Integer.MAX_VALUE : b[cut2];

            if(l1<=r2 && l2<=r1)
            return Math.max(l1,l2);
            else if(l1>r2) high=cut1-1;
            else low=cut1+1;
        }
        return -1;
    }
}
