package ch05;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ch04.Dish;
import ch04.Menu;

public class Mapping {
    public static void main(String[] args) {
        List<String> dishNames = Menu.menu.stream()
                .map(Dish::getName)
                .collect(Collectors.toList());

        //        System.out.println(dishNames);

        List<Integer> dishNameLength = Menu.menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(Collectors.toList());

        //        System.out.println(dishNameLength);

        List<String> words = Arrays.asList("Hello", "World");
        List<String[]> strArr = words.stream()
                .map(word -> word.split(""))
                .distinct()
                .collect(Collectors.toList());

//        System.out.println(strArr);

        List<Stream<String>> words2 = words.stream()
                .map(word->word.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());

//        System.out.println(words2);


        //두 개의 숫자 리스트가 있을 때 모든 숫자 쌍의 리스트를 반환하시오.
        // 예를 들어 두 개의 리스트 [1,2,3]과 [3,4]가 주어지면
        // [(1,3), (1,4), (2,3) ,(2,4) ,(3,3), (3,4)]를 반환해야 함.

        List<Integer> num1 = Arrays.asList(1,2,3);
        List<Integer> num2 = Arrays.asList(3,4);

        List<Stream<Integer[]>> s=num1.stream()
        .map(i-> num2.stream().map(j->new Integer[] {i, j}))
        .collect(Collectors.toList());

       s.stream().forEach(d->{
           System.out.println( d.map(arr->Arrays.asList(arr)).collect(Collectors.toList()));
       });

       List<Integer[]> s2 = num1.stream()
               .flatMap(i-> num2.stream().map(j->new Integer[] {i, j}))
               .collect(Collectors.toList());

       System.out.println(s2.stream().map(Arrays::asList).collect(Collectors.toList()));
    }
}
