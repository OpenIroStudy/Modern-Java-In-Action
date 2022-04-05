package ch05;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import ch04.Dish;
import ch04.Menu;

public class Slicing {
    public static void main(String[] args) {
        List<Dish> sortedDishMenu =  Menu.menu.stream()
        .sorted(Comparator.comparing(Dish::getCalories)) // 칼로리가 낮은 순서대로 정렬
        .collect(Collectors.toList());

        sortedDishMenu.stream()
    }
}
