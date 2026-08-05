package basics;

public class Pattern4 {
    public void pattern4(int n) {
        int x = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(x + " ");
            }
            x++;
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Pattern4 p = new Pattern4();
        p.pattern4(5);
    }
}
