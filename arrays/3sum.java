// 3 Sum — all unique triplets summing to zero
// Approach: sort, then fix one element with an outer loop and run two pointers
//           inward on the remainder. With nums[i] fixed, the problem reduces to
//           "find two numbers summing to -nums[i]", and on sorted data the sum
//           tells you which pointer to move: too small → left++, too big → right--.
// Time: O(n²) — O(n log n) sort plus n outer iterations each doing O(n) work
// Space: O(1) excluding the output
//
// Sorting does TWO jobs: it enables the two-pointer direction logic, and it
// puts equal values adjacent so duplicates are caught by one comparison with
// a neighbour. Unsorted, you'd need a set for dedup at O(n²) space.
//
// Duplicate handling — three skips, covering two different kinds of repeat:
//   OUTER: if (i > 0 && nums[i] == nums[i-1]) continue;
//          stops the same fixed value starting a second identical search.
//          The i > 0 guard must come first — && short-circuits, so nums[-1]
//          is never accessed.
//   INNER: after recording, move BOTH pointers, then skip past repeats:
//            nums[left]  == nums[left - 1]   (left++ moved forward, so look back)
//            nums[right] == nums[right + 1]  (right-- moved back, so look forward)
//          stops the same PAIR being found again within one outer iteration,
//          e.g. [-2, 0, 0, 2, 2] would otherwise yield [-2,0,2] twice.
//   Both are needed — some inputs exercise only one of them.
//
// Duplicate VALUES within a triplet are allowed: [-1,-1,2] is valid because
// the two -1s are different elements at different indices, one used as the
// fixed element and one by the left pointer. Duplicate TRIPLETS in the output
// are what's forbidden.
//
// Brute force is O(n³) with a set for dedup.
package arrays;

public class 3sum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list=new ArrayList<>();
        Arrays.sort(nums);
        for(int i=0;i<nums.length;i++)
        {
            if(i>0 && nums[i]==nums[i-1]) continue;
            int left=i+1;
            int right=nums.length-1;
            while(left<right)
            {
                int sum=nums[i]+nums[left]+nums[right];
                if(sum==0)
                {
                    List<Integer> result=new ArrayList<>();
                    result.add(nums[i]);
                    result.add(nums[left]);
                    result.add(nums[right]);
                    list.add(result);
                    left++;
                    right--;
                    while(left<right && nums[left]==nums[left-1]) left++;
                    while(left<right && nums[right]==nums[right+1]) right--;
                }
                else if(sum<0)
                    left++;
                else
                    right--;
                
            }
        }
    }
}
