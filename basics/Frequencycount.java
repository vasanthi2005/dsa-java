// Count frequencies of array elements
// Approach: HashMap value -> count in one pass, then convert entries
//           to [value, count] pairs
// Time: O(n), Space: O(k) for k distinct values
// Note: new inner list created inside the loop — declaring it outside
//       would mean every pair references the same list
package basics;
import java.util.*;
class Frequencycount {
    public List<List<Integer>> countFrequencies(int[] nums) {
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<nums.length;i++)
        {
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        }
        List<List<Integer>> result = new ArrayList<>();
        for(Map.Entry<Integer,Integer> e : map.entrySet())
        {
            List <Integer> pair= new ArrayList<>();
            pair.add(e.getKey());
            pair.add(e.getValue());
            result.add(pair);
        }
        
        return result;
    }
}