// GCD of two numbers — brute force
// Approach: loop i from 1 to min(a,b), keep the largest i dividing both
// Time: O(min(a,b)), Space: O(1)
// Note: TIMES OUT on large inputs (~10^9). Passed 1063/1115 test cases.

// GCD of two numbers — Euclidean algorithm
// Approach: gcd(a,b) = gcd(b, a % b); repeat until b == 0, then a is the GCD
// Why it works: any d dividing a and b also divides a % b, so the pair
//               (b, a % b) has the same common divisors as (a, b)
// Time: O(log n), Space: O(1)
// Note: handles 0 for free — gcd(0, n) returns n since everything divides 0
package basics;

public class Gcd {
    //brute force approach
    public static int gcd(int a, int b) {
        // code here
        if(a==0) return b;
        if(b==0) return a;
        int min=Math.min(a,b);
        int gcf=0;
        for(int i=1;i<=min;i++)
        {
            if((a%i==0) && (b%i==0))
            {
                gcf=i;
            }
        }
        return gcf;
    }
    
    //optimized approach
    public static int gcdOptimized(int a, int b) {
        // code here
        int temp;
        while(b!=0)
        {
            temp=b;
            b=a%b;
            a=temp;
        }
        return a;
    }
}
