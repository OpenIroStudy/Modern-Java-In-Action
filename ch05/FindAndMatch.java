package ch05;

import java.util.Optional;

import ch04.Dish;
import ch04.Menu;

public class FindAndMatch {
    public static void main(String[] args) {
        if (Menu.menu.stream().anyMatch(Dish::isVegetarian))
            System.out.println("this is vegetarian friendly!!");

        boolean isHealthy = Menu.menu.stream().allMatch(dish -> dish.getCalories() < 1000);

        boolean isHealthy2 = Menu.menu.stream()
                .noneMatch(dish -> dish.getCalories() >= 1000);


        Optional<Dish> dish = Menu.menu.stream()
                .filter(Dish::isVegetarian)
                .findAny();

    }
}
