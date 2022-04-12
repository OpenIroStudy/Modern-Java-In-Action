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

        List<Dish> slicedMenu1 = sortedDishMenu.stream()
                .takeWhile(dish->dish.getCalories()<320)
        .collect(Collectors.toList());

//        System.out.println(slicedMenu1);

        List<Dish> slicedMenu2 = sortedDishMenu.stream()
                .dropWhile(dish->dish.getCalories()<320)
                .collect(Collectors.toList());

//        System.out.println(slicedMenu2);


        List<Dish> slicedMenu3 = sortedDishMenu.stream()
                .filter(dish->dish.getCalories()>320)
                .limit(3)
                .collect(Collectors.toList());

//        System.out.println(slicedMenu3);

        List<Dish> slicedMenu4 = Menu.menu.stream()
                .filter(dish->dish.getCalories()>320)
                .limit(3)
                .collect(Collectors.toList());

//        System.out.println(slicedMenu4);


        List<Dish> slicedMenu5 = Menu.menu.stream()
                .filter(d->d.getCalories() > 300)
                .skip(2).limit(2)
                .collect(Collectors.toList());

        System.out.println(slicedMenu5);
    }
}
