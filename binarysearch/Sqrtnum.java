// Square root (floor) of n, without Math.sqrt
// Approach: BINARY SEARCH ON THE ANSWER SPACE — the key new idea in this
//   section. There's no array. The answer lies somewhere in the range 1..n,
//   and any candidate can be tested directly: is mid*mid <= n?
//     feasible (mid*mid <= n)  → record it, try LARGER  → low = mid + 1
//     too big                   → answer is smaller      → high = mid - 1
//   Same record-a-candidate template as lower bound, applied to a range of
//   possible answers rather than array indices.
// Time: O(log n), Space: O(1)
//
// Why this generalises: binary search needs a range and a condition that
//   reliably eliminates half of it. It does NOT need an array, and it does not
//   need sorted data (see find-peak-element). Here the "sortedness" comes free:
//   if mid*mid <= n then every smaller candidate also satisfies it, so the
//   feasible values form a contiguous block with a boundary to find.
//
// This unlocks Koko eating bananas, nth root, capacity-to-ship-packages, and
//   most of the harder problems in this section — all "find the smallest/
//   largest feasible answer" with a different feasibility test.
//
// Use long — mid*mid overflows int as n approaches 2^31. Cast an OPERAND, not
//   the result. (Sixth time: nCr, 4-Sum, missing-and-repeating, reverse pairs,
//   count inversions, here.)
public class Sqrtnum {
    public int floorSqrt(int n) {
      long low=1,high=n;
      long ans=0;
      while(low<=high)
      {
        long mid=(low+high)/2;
        if(mid*mid<=n)
        {
            ans=mid;
            low=mid+1;
        }
        else
        {
            high=mid-1;
        }
      }
      return (int)ans;
    }
}
