package basics;

public class Pattern5 {
    public void pattern5(int n) {
        for (int i = n; i > 0; i--) {
            for (int j = i; j > 0; j--) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Pattern5 p = new Pattern5();
        p.pattern5(5);
    }
}
