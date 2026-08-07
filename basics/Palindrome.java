// Palindrome number
// Approach: reverse the digits into a copy, compare with the original
// Time: O(log n), Space: O(1)
// Note: reverses on a copy so the input isn't destroyed
package basics;

public class Palindrome {
    
    public boolean isPalindrome(int x) {
        if(x<0) return false;
        int rev=0;
        int t=x;
        while(t!=0)
        {
            rev=(rev*10)+(t%10);
            t=t/10;
        }

        return (rev==x);
    }

}
