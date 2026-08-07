package basics;

public class Armstrongnum {
    // General method
    static boolean armstrongNumber(int n) {
        // code here
        int t = n;
        int sum = 0;
        while (t != 0) {
            int r = t % 10;
            sum += (r * r * r);
            t /= 10;
        }
        return (n == sum);
    }

    // anynum of digits
    static boolean armstrongNumber1(int n) {
        int digits = 0;
        int t = n;
        while (t != 0) {
            digits++;
            t /= 10;
        }

        t = n;
        int sum = 0;
        while (t != 0) {
            int r = t % 10;
            sum += Math.pow(r, digits);
            t /= 10;
        }

        return n == sum;
    }
}
