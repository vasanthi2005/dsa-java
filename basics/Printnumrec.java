/*
 * Problem : Print 1 to N using recursion
 * Approach: Recurse down to the base case, print on the way back up.
 * Printing after the recursive call gives increasing order.
 * Time    : O(n)
 * Space   : O(n)  — recursion stack
 */
package basics;

class Printnumrec{

    public void printNumbers(int n) 
    {
        if(n==0)
        return ;
        
        printNumbers(n-1);
        System.out.println(n);
    }
}