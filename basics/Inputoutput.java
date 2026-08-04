package basics;
import java.util.Scanner;

class Inputoutput {
    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        int a = s.nextInt();
        System.out.print(a);
        s.close();
    }
}