// Median of two sorted arrays — O(log(min(m,n)))
//
// WHAT A MEDIAN IS: sort everything, take the middle. Even count → average the
//   two middle values.  [1,3,5] → 3.   [1,3,5,7] → (3+5)/2 = 4.
//
// The easy way is to merge both arrays and pick the middle — O(m+n). The
//   problem forbids that, so you never actually merge anything.
//
// THE IDEA — FIND A CUT, NOT A VALUE.
//   Draw one vertical line through BOTH arrays, splitting everything into a
//   left half and a right half, such that:
//     - both halves hold the same number of elements
//     - everything on the left is <= everything on the right
//   If you find that cut, the median is sitting right at the boundary.
//
//       nums1:  1  3  |  8  9
//       nums2:  2  7  |  10
//       left: 1,3,2,7      right: 8,9,10
//
//   THE KEY: once you decide how many to take from nums1, the count from nums2
//   is FORCED — the halves must be equal. So there's only ONE number to search
//   for. That's what makes it binary-searchable.
//
// SEARCH SPACE: cut1 = how many elements to take from nums1.
//   low = 0, high = m.  It's a COUNT, not an index — you can take none (0) or
//   all of them (m), so high is m and NOT m-1. Taking all of nums1 is often the
//   answer.
//   (cut1 is also used AS an index in nums1[cut1], which is why those lines
//    need the == 0 and == m guards. Same variable, two roles.)
//
// ALWAYS SEARCH THE SMALLER ARRAY — swap at the top if needed. Otherwise cut2
//   can come out negative.
//
// THE FOUR VALUES around the cut:
//     l1 = last taken from nums1      r1 = first NOT taken from nums1
//     l2 = last taken from nums2      r2 = first NOT taken from nums2
//   Took none → no l → use MIN_VALUE, so it never fails a comparison.
//   Took all  → no r → use MAX_VALUE.
//
// VALIDITY — check the two DIAGONALS, not within each array:
//     l1 <= r2   and   l2 <= r1
//
//       nums1:   ... l1 | r1 ...
//                        X
//       nums2:   ... l2 | r2 ...
//
//   Checking l1 <= r1 is pointless — nums1 is already sorted, so that's always
//   true. It makes the condition pass immediately and returns garbage. The only
//   thing that can go wrong is ACROSS the arrays, which is exactly what the two
//   crossings test.
//
// WHICH FAILURE MEANS WHICH DIRECTION:
//     l1 > r2  → took something too big from nums1's left → take FEWER →
//                high = cut1 - 1
//     otherwise → took too few from nums1 → low = cut1 + 1
//
// READING OFF THE ANSWER — the two lines must MATCH each other:
//
//     half = (total + 1) / 2  →  odd total puts the extra element LEFT
//                             →  odd case: return Math.max(l1, l2)
//
//     half = total / 2        →  odd total puts the extra element RIGHT
//                             →  odd case: return Math.min(r1, r2)
//
//   9 numbers 1..9, median is 5.
//     left gets 5 → left is 1,2,3,4,5 → median is the BIGGEST on the left
//     left gets 4 → left is 1,2,3,4   → median is the SMALLEST on the right
//   Both cuts are valid; the code has to know where to look. Mixing the two
//   conventions is the bug.
//
//   EVEN total is the same either way — the split is genuinely equal, so:
//     (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0
//   `/ 2.0` not `/ 2` — integer division would truncate the average.
//
// Space: O(1). Nothing is merged or copied.
public class Medianoftwosortedarrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length>nums2.length) return findMedianSortedArrays(nums2,nums1);
        int m=nums1.length,n=nums2.length;
        int total=n+m;
        int half=(total+1)/2;
        int low=0,high=m;
        while(low<=high)
        {
            int cut1=(low+high)/2;
            int cut2=half-cut1;
            int l1= (cut1==0) ? Integer.MIN_VALUE:nums1[cut1-1];
            int r1= (cut1==m) ? Integer.MAX_VALUE:nums1[cut1];
            int l2= (cut2==0) ? Integer.MIN_VALUE:nums2[cut2-1];
            int r2= (cut2==n) ? Integer.MAX_VALUE:nums2[cut2];
            if(l1<=r2 && l2<=r1)
            {
                if(total%2==1) return Math.max(l1,l2);
                return (Math.max(l1,l2)+Math.min(r1,r2))/2.0;
            }
            else if(l1> r2) high=cut1-1;
            else
            low=cut1+1;
        }
        return 0.0;

    }
    
}
