package ch02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Filtering {

    interface Predicate<T> {
        boolean test(T t);
    }

    public static <T> List<T> filterGreenApples(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T e : list) {
            if (p.test(e)) {
                result.add(e);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80, Color.GREEN), new Apple(80, Color.GREEN), new Apple(80, Color.GREEN));

        filterGreenApples(inventory, A -> A.getColor().equals(Color.RED));

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        List<Integer> evenNumbers = filterGreenApples(numbers, (Integer i) -> i % 2 == 0);
    }

}
