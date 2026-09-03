// 4 Sum — all unique quadruplets summing to target
// Approach: 3-Sum with one more loop. Sort, fix two elements with nested
//           outer loops, then two-pointer inward on the remainder looking for
//           a pair summing to (target - nums[i] - nums[j]).
// Time: O(n³), Space: O(1) excluding output
// Note: FOUR duplicate skips now — one for i, one for j, two inner after
//       recording a quadruplet.
//       The j guard is j > i + 1, not j > 0. At j == i+1 there is no previous
//       j in this inner loop, so nums[j-1] would be nums[i] — a different role.
//       Cast to long: four ints can overflow when summed.
//       Bounds n-3 and n-2 leave room for the remaining elements. Not strictly
//       necessary (a for loop with a start past its bound just doesn't run)
//       but clearer about intent.
package arrays;

public class 4sum {
        public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> list=new ArrayList<>();
        Arrays.sort(nums);
        for(int i=0;i<nums.length;i++)
        {
            if(i>0 && nums[i]==nums[i-1]) continue;
            for(int j=i+1;j<nums.length-1;j++)
            {
                if(j>i+1 && nums[j]==nums[j-1]) continue;
                int left=j+1;
                int right=nums.length-1;
                while(left<right)
                {
                long sum=(long)nums[left]+nums[right]+nums[i]+nums[j];
                if(sum==target)
                {
                    List<Integer> result=new ArrayList<>();
                    result.add(nums[i]);
                    result.add(nums[j]);
                    result.add(nums[left]);
                    result.add(nums[right]);
                    list.add(result);
                    left++;
                    right--;
                    while(left<right && nums[left]==nums[left-1]) left++;
                    while(left<right && nums[right]==nums[right+1]) right--;
                }
                else if(sum<target) left++;
                else right--;
                }
            }
        }
        return list;
    }

}
