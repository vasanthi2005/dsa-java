// Kth missing positive number
// Approach: binary search on ARRAY INDICES (not an answer space).
//
//   THE KEY IDEA: at any position you can count how many numbers are missing
//   before it with one subtraction:
//       missing = arr[i] - (i + 1)
//   If nothing were missing, index i would hold the value i+1. Whatever it
//   holds instead, the gap is how many numbers were skipped.
//     [2,3,4,7,11]:  2-1=1,  3-2=1,  4-3=1,  7-4=3,  11-5=6
//     (index 3 holds 7, and 1, 5, 6 are missing before it — three of them)
//   The i+1 is because indices count from 0 but the numbers count from 1.
//   Brackets matter: arr[mid] - (mid + 1), not arr[mid] - mid + 1.
//
//   That count only grows left to right, so binary search for the boundary —
//   the first position where the count reaches k. Lower-bound shape:
//       count >= k → high = mid - 1   (gone far enough, look left)
//       count <  k → low  = mid + 1   (answer is further right)
//
// Time: O(log n), Space: O(1)
//
//   CONVERTING THE POSITION TO THE ANSWER: when the loop ends, `low` is how
//   many array elements come before the answer. So:
//       answer = k + (elements in the way) = low + k
//   The kth missing number has to make room for k missing numbers PLUS every
//   array element that pushed it further along.
//     [2,3,4,7,11], k=5 → answer 9. Elements before it: 2,3,4,7 → four.
//     low ends at 4, so 4 + 5 = 9.
//
// NO EARLY RETURN. Returning as soon as count >= k uses `low` before the loop
//   has moved it. It passes on many inputs by luck, then fails:
//     [1,10,21,22,25], k=12 → first mid already has count 18 >= 12, so it
//     returns 0 + 12 = 12. But 1 and 10 come first, so the answer is 14.
//   Only when the loop ENDS does low hold the true count of preceding elements.
//
// No `ans` variable needed — unlike the answer-space problems, the pointer
//   itself lands on the answer.
class Solution {
    public int findKthPositive(int[] arr, int k) {
        int low=0,high=arr.length-1;
        while(low<=high)
        {
            int mid=(low+high)/2;
            int val=arr[mid]-(mid+1);
            if(val>=k)
            {
                high=mid-1;
            }
            else
            {
                low=mid+1;
            }
        }
        return low+k;
    }
}