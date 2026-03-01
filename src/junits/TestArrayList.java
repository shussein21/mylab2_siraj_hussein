package junits;

import utils.ArrayList;

public class TestArrayList {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();

        list.add(10);
        list.add(20);
        list.add(30);

        System.out.println(list.get(0)); // 10
        System.out.println(list.get(1)); // 20
        System.out.println(list.size()); // 3

        list.remove(1);
        System.out.println(list.get(1)); // 30
        System.out.println(list.size()); // 2
    }
}
