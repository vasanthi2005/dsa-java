// Longest subarray with sum k (negatives allowed)
// Approach: prefix sums. A subarray's sum is the DIFFERENCE between two
//           running totals — if the total was 10 at index 0 and is 25 at
//           index 4, everything between sums to 15. Map stores
//           runningSum -> first index it occurred. At each step, look for
//           (sum - k): if present, the elements between there and here sum
//           to exactly k, with length i - map.get(sum - k).
// Time: O(n), Space: O(n)
// Note: store only the FIRST occurrence of each sum — an earlier start means
//       a longer subarray
//       sum == k needs its own case: the subarray starts at index 0 and there
//       is no earlier index to subtract from. Alternative: seed map.put(0, -1)
//       before the loop, so the lookup finds the imaginary "before the array"
//       position and i - (-1) = i + 1 falls out naturally.
// Sliding window is O(1) space but only works when all values are positive —
//       shrinking must reliably reduce the sum, which negatives break.
package arrays;
class Longestsubarraynegatives
{
     public int longestSubarray(int[] nums, int k) {
        HashMap<Integer,Integer> map=new HashMap<>();
        int sum=0;
        int maxlen=0;
        for(int i=0;i<nums.length;i++)
        {
            sum+=nums[i];
            if(!map.containsKey(sum))
            map.put(sum,i);
            if(sum==k)
            maxlen=i+1;
            if(map.containsKey(sum-k))
            {
                int len=i-map.get(sum-k);
                if(len>maxlen) maxlen=len;
            }
        }
        return maxlen;
    }
}