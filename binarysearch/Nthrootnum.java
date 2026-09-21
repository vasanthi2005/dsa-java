// Nth root of M — return X where X^N == M exactly, or -1 if no integer root
// Approach: binary search on the ANSWER SPACE, range 1..M. Same shape as square
//   root, but the feasibility test raises mid to the power N instead of squaring.
//     val == M  → exact hit, return mid
//     val <  M  → guess too small, search right  → low  = mid + 1
//     val >  M  → guess too big,   search left   → high = mid - 1
// Time: O(log M * N) — the power loop runs N times per binary search step
// Space: O(1)
//
// Differs from square root: that returned the FLOOR when no exact answer
//   existed, so it recorded a candidate as it went. This returns -1, so only an
//   exact match counts and nothing is recorded.
//
// THE OVERFLOW PROBLEM: mid^N explodes fast — 10^5 to the 10th power is far
//   past any integer type. The power helper bails out the moment the running
//   product exceeds the limit:
//       if (result > limit) return limit + 1;
//   Once it's over you already know the guess is too big; computing the exact
//   value would only overflow. Returning limit+1 is enough to signal "too big".
//
// Pointer direction: val < M means the guess is TOO SMALL, so low moves UP.
//   Getting these backwards makes the range grow instead of shrink → infinite
//   loop.
//
// Scope note: inside power(), the parameter is named separately — M belongs to
//   NthRoot and isn't visible there.
public class Nthrootnum {
     public int NthRoot(int N, int M) {
       long low=1,high=M;
       while(low<=high)
       {
        long mid=(low+high)/2;
        long val=power(mid,N,M);
        if(val==M)
            return (int) mid;
        else if(val<M)
            low=mid+1;
        else
            high=mid-1;
       }
       return -1;
    }
    public static long power(long mid,long n,long m)
    {
        long result=1;
        for(long i=1;i<=n;i++)
        {
            result*=mid;
            if(m<result) return m+1;
        }
        return result; 
    }
}
