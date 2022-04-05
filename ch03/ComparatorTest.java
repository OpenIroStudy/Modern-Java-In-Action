package ch03;

import java.util.Comparator;

import ch02.Apple;

public class ComparatorTest {

    Comparator<Apple> compare = (Apple a1, Apple a2) -> {
        if(a1.getWeight()>a2.getWeight()) return 0;
        else return -1;
    };

}
