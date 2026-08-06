package basics;

import java.util.*;

public class SortingPractice {
    public static void main(String args[]) {
        int arr[] = { 5, 2, 8, 1 };
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));

        ArrayList<String> words = new ArrayList<>();
        words.add("banana");
        words.add("kiwi");
        words.add("apple");
        Collections.sort(words);
        System.out.println(words);

        words.sort((a, b) -> a.length() - b.length());
        System.out.println(words);

        words.sort((a, b) -> b.length() - a.length());
        System.out.println(words);

    }

}
