package basics;

public class Numberssum {
    public int NnumbersSum(int N) {

        if (N == 0)
            return 0;
        return N + NnumbersSum(N - 1);

    }
}
