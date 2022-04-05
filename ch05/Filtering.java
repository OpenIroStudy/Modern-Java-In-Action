package ch05;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import ch04.Dish;
import ch04.Menu;

public class Filtering {
    public static void main(String[] args) {
        List<Dish> vegetarianMenu = Menu.menu.stream()
                .filter(Dish::isVegetarian) // 채식 요리인지 확인하는 메서드 참조
                .collect(Collectors.toList());

        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
        .filter(i -> i % 2 == 0) // 짝수를 선택 2,2,4
        .distinct() // 중복 제거
        .forEach(System.out::println); // 2, 4
    }
}
