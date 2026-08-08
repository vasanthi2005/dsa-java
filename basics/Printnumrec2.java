
// descending order
package basics;

public class Printnumrec2 {
    public void printNumbers(int n) {
       System.out.println(n);

       if(n==1)return;
       printNumbers(n-1);
    }
}
