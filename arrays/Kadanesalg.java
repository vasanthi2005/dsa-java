// Print subarray with maximum sum (extended Kadane's)
// Approach: Kadane's, plus index tracking. `start` is the TENTATIVE start —
//           updated whenever sum resets. ansStart/ansEnd are the CONFIRMED
//           answer, snapshotted only when a new max is found.
// Time: O(n), Space: O(1) for the tracking (O(k) for the output)
// Note: for-each gives values, not positions — a counted loop is required
//       when you need indices.
//       start and i keep moving after the best subarray is found, so they must
//       be frozen at the moment max updates. Same pattern as tracking both the
//       count AND the element in the majority problem.
public class Kadanesalg {
    public int[] maxSubArrayextended(int[] nums) {
        int sum = 0;
        int max = Integer.MIN_VALUE;
        int start=0;
        int ansStart=0;
        int ansEnd=0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum > max) {
                max = sum;
                ansStart=start;
                ansEnd=i;
            }
            if (sum < 0) {
                sum = 0;
                start=i+1;
            }
        }
          int[] result = new int[ansEnd - ansStart + 1];
    for (int i = 0; i < result.length; i++) {
        result[i] = nums[ansStart + i];
    }
    return result;
        
    }
    // Maximum subarray sum — Kadane's Algorithm
// Approach: track a running sum and the best sum seen. At each element, add it
//           to the running sum, compare against the max, then reset the running
//           sum to 0 if it has gone negative — a negative prefix can only drag
//           down any subarray continuing through it, so start fresh instead.
// Time: O(n), Space: O(1) — beats the O(n²) all-subarrays brute force
// Note: max must start at Integer.MIN_VALUE, not 0, or an all-negative array
//       returns 0 instead of its largest element (subarrays must be non-empty)
//       compare BEFORE resetting, or that largest negative is never recorded
    public int maxsubarray(int arr[])
    {
        int sum=0;
        int max=Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++)
        {
            sum+=i;
            if(sum>max)
            {
                max=sum;
            }
            if(sum<0)
            {
                sum=0;
            }

        }
        return max;
    }
}
