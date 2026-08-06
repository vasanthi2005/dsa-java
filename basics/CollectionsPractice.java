package basics;

import java.util.*;

class CollectionsPractice {
    public static void main(String args[]) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(3);
        System.out.println(list);
        System.out.println(list.get(0));
        System.out.println(list.size());

        HashMap<String, Integer> map = new HashMap<>();
        map.put("apple", 3);
        System.out.println(map.get("apple"));
        System.out.println(map.getOrDefault("banana", 0));
        System.out.println(map.containsKey("apple"));

        HashSet<Integer> set = new HashSet<>();
        set.add(1);
        set.add(1);
        System.out.println(set.size());

    }
}