자바 API는 복잡한 애플리케이션을 만드는 데 필요한 여러 가지 유용한 컴포넌트를 제공한다.  
Date 클래스는 특정 시점을 날짜가 아닌 밀리초 단위로 표현한다.  
<br><br>  

```java
Date date = new Date(117,8,21);

//결과값 - Thu Sep 21 00:00:00 CET 2017

```
결과가 직관적이지 않다.   

##### 12.1.1 LocalDate와 LocalTime의 사용  
LocalDate 인스턴스는 시간을 제외한 날짜를 표현하는 불변 객체다.   

```java
        LocalDate date = LocalDate.of(2017, 9, 11); //2017-09-11
        int year = date.getYear();
        Month month = date.getMonth();
        int day = date.getDayOfMonth();
        DayOfWeek dow = date.getDayOfWeek();
        int len = date.lengthOfMonth();
        boolean leap = date.isLeapYear();
```

