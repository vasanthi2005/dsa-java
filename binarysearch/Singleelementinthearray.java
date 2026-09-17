// Single element in a sorted array (every other element appears twice)
// Approach: binary search on the PAIRING PATTERN, not the values.
//   Before the single element, pairs start at EVEN indices.
//   After it, everything shifts by one and pairs start at ODD indices.
//   So: snap mid to an even index, then compare nums[mid] with nums[mid+1].
//     match   → pairing intact, single element is to the RIGHT  → low = mid + 2
//     differ  → shift already happened, it's HERE or LEFT       → high = mid
// Time: O(log n), Space: O(1) — as required
//
// Why mid must be even: the comparison only means something at a pair's START.
//   At an odd index you'd compare the END of one pair with the START of the
//   next, which always differ regardless of where the single element is.
//   An odd index CAN match (index 3 in [1,1,2,3,3,...] matches index 4) — but
//   there a match means the opposite thing, since the pairs have shifted.
//   Snapping to even gives one consistent rule instead of two.
//
// low = mid + 2, not mid + 1 — a whole pair was confirmed, skip both.
// high = mid, not mid - 1 — mid itself might BE the single element. Safe here
//   because the even-snap keeps mid strictly below high when the pair breaks.
// while (low < high), not <= — converging on one position, not testing each.
public int singleNonDuplicate(int[] nums) {
        int low=0,high=nums.length-1;
        while(low<high)
        {
            int mid=(low+high)/2;
            if(mid%2==1) mid--;
            if(nums[mid]==nums[mid+1])
            {
                low=mid+2;
            }
            else
            {
                high=mid;
            }
        }
        return nums[low];
    }