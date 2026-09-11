// Reverse pairs — count (i, j) where i < j and nums[i] > 2 * nums[j]
// Approach: merge sort with a SEPARATE counting pass before each merge.
//           For each element in the left half, advance a pointer through the
//           right half past everything it forms a reverse pair with. Both
//           halves are sorted, so once the condition fails it fails for every
//           larger element too — stop there.
//             count += r - (mid + 1)
// Time: O(n log n), Space: O(n) — brute force is O(n²)
//
// Why a SEPARATE pass, unlike count-inversions: there the counting condition
//       (arr[left] > arr[right]) WAS the merge comparison, so counting fell
//       out of the merge for free. Here it's arr[left] > 2*arr[right], which
//       the merge never tests — so it needs its own pass, and the merge loop
//       must contain NO counting.
//
// Why BEFORE the merge: the merge overwrites arr[low..high] with the combined
//       result, destroying the two separate sorted halves the counting needs.
//
// Why the counting pass needs its OWN pointer: sharing `right` with the merge
//       leaves it partway through the right half when merging starts.
//
// Why `r` isn't reset per iteration of i: the left half is sorted too, so a
//       larger arr[i] pairs with at least as many right elements as the one
//       before it. r only moves forward — that's what keeps the pass O(n).
//
// FORMULA — check which half you're counting:
//       left half  = low .. mid       right half = mid+1 .. high
//       Fixed on a LEFT element, counting right elements passed:
//           r - (mid + 1)      no +1: r stops one PAST the last match
//       Fixed on a RIGHT element, counting left elements remaining
//       (this is count-inversions):
//           mid - left + 1     +1: both endpoints included
//
// Note: 2L not 2 — 2 * arr[right] overflows int for large values and wraps
//       negative, producing false matches.
class Reversepairs {
    static long count=0;
    public static void mergesort(int nums[],int low,int high)
    {
        if(low<high)
        {
            int mid=(low+high)/2;
            mergesort(nums,low,mid);
            mergesort(nums,mid+1,high);
            merging(nums,low,mid,high);
        }
    } 
    public static void merging(int arr[],int low,int mid,int high)
    {
        ArrayList<Integer> list=new ArrayList<>();
        int left=low;
        int r=mid+1;
        for(int i=low;i<=mid;i++)
        {
            while(r<=high && arr[i]> 2L * arr[r])
            {
                r++;
            }
            count+=r-(mid+1);
        }
        int right=mid+1;
        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                list.add(arr[left]);
                left++;
            } else {
                list.add(arr[right]);
                right++;
            }
        }
        while (left <= mid) {
            list.add(arr[left]);
            left++;
        }
        while (right <= high) {
            list.add(arr[right]);
            right++;
        }
        int k = 0;
        for (int i = low; i <= high; i++) {
            arr[i] = list.get(k);
            k++;
        }
    }
    public int reversePairs(int[] nums) {
         count=0;
        mergesort(nums,0,nums.length-1);
        return (int)count;
    }
}