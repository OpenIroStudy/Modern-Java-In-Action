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

* TemporalField는 시간 관련해서 어떤 필드의 값에 접근할 지 정의하는 인터페이스다. 
```java
        int year = date.get(ChronoField.YEAR);
        int month = date.get(ChronoField.MONTH_OF_YEAR);
        int day = date.get(ChronoField.DAY_OF_MONTH);
```


시간도 LocalTime을 이용해 표현할 수 있다. 

```java
        LocalTime time = LocalTime.of(13,45,20);
        time.getHour();
        time.getMinute();
        time.getSecond();
```

 string을 사용해 LocalTime.of("2017-09-21"); 인스턴스를 만들 수 있다.   
 
 ###### 날짜와 시간의 조합
 LocalDateTime은 LocalDate와 LocalTime을 쌍으로 갖는 복합 클래스이다. 
 
 ```java
         LocalDateTime dt1 = LocalDateTime.of(2017, Month.SEPTEMBER, 21, 13, 45, 20);
        LocalDateTime dt2 = LocalDateTime.of(date, time);
        LocalDateTime dt3 = date.atTime(13,45,20);
        LocalDateTime dt4 = date.atTime(time);
        LocalDateTime dt5 = time.atDate(date);
 ```
 
 ##### Instatnt 클래스
 기계의 관점에서 연속된 시간에서 특정 지점을 하나의 큰 수로 표현하는 것이 가장 자연스러운 시간 표현 방법이다.   
 Instant 클래스는 에포크 시간(알아서 찾아보길) 기준으로 특정 지점까지의 시간을 초로 표현한다.  
 
   
 
