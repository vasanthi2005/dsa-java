package basics;

public class Pattern1 {
    public void pattern1(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Pattern1 p = new Pattern1();
        p.pattern1(5);
    }
}
