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
 
   
 
```java
Instant.ofEpochSecond(3);
Instant.ofEpochSecond(3,0);
Instant.ofEpochSecond(2,1_000_000_000); //2초 이 후의 1억 나노초
Instant.ofEpochSecond(4,-1_000_000_000); //4초 이전의 1억 나노초

```


#### Duration과 Period의 정의 
지금까지의 인터페이스는 Temporal 인터페이스를 구현한 것이다. Temporal 인터페이스는 특정 시간을 모델링하는 객체의 값을 어떻게 읽고 조작할 지 정의한다. 
Duration 클래스의 정적 팩토리 메서드 between으로 두 시간 객체 사이의 지속 시간을 만들 수 있다. 

```java
Duration d1 = Duration.between(time1, time2);
Duration d2 = Duration.between(dateTime1, dateTime2);
Duration d3 = Duration.between(instant1, instant2);

```
LocalDateTime은 사람이 사용하도록, Instant는 기계가 사용하도록 만들어진 클래스로 두 인스턴스는 서로 혼합될 수 없다.   
Duration 클래스는 초와 나노초로 시간 단위를 표현하므로 between 메서드에 LocalDateTime을 전달할 수 없다.   

<br>
년 월 일로 시간을 표현할 때는 Period 클래스를 사용한다.    
즉, Period 클래스의 팩토리 메서드 between을 이용하면 두 LocalDate의 차이를 확인할 수 있다.  

```java
Period tenDays =Period.between(LocalDate.of(2017,9,11),LocalDate.of(2017,9,21) );
```


지금까짖 살펴본 모든 클래스는 불변이다. 불변 클래스는 함수형 프로그래밍 그리고 스레드 안전성과 도메인 모델의 일관성을 유지하는데 좋은 특징이다.   
<br><br>

##### 날짜 조정, 파싱, 포매팅

withAttribute - 절대적인 방식으로 LocalDate의 속성 바꾸기
```java
LocalDate date1 = LocalDate.of(2017,9,21);
LocalDate date1 = date.withYear(2011);
LocalDate date1 = date.withDayIfMonth(25);

```
특정 시점을 표현하는 날짜 시간 클래스의 공통 메서드   

![image](https://user-images.githubusercontent.com/43237961/167524740-8dff0f9f-835a-4abe-8f67-971489f8e90c.png)  


###### TemporalAdjusters
다음주 월요일, 돌아오는 평일, 등 좀 더 복잡한 날짜 조정 기능이 있다.
```java
import static java.time.temporal.TemporalAdjusters.*;
LocalDate date1 = LocalDate.of(2014, 3, 18); 
LocalDate date2 = date1.with(nextOrSame(DayOfWeek.SUNDAY)); 
LocalDate date3 = date2.with(lastDayOfMonth());

```

|메서드 | 설명|
|---|---|
|dayOfWeekInMonth|서수 요일에 해당하는 날짜를 반환하는 TemporalAdjuster를 반환함|
|firstDayOfMonth|현재 달의 첫 번째 날짜를 반환|
|firstDayOfNextMonth|다음 달의 첫 번째 날짜를 반환|
|firstDayOfNextYear|내년의 첫 번째 날짜를 반환|
|firstInMonth|올해의 첫 번째 날짜를 반환|
|lastDayOfMonth|현재 달의 마지막 날짜를 반환|
|lastDayOfNextMonth|다음 달의 마지막 날짜를 반환|
|lastDayOfNextYear|내년의 마지막 날짜를 반환|
|lastDayOfYear|올해의 마지막 날짜를 반환|
|lastInMonth|현재 달의 마지막 요일에 해당하는 날짜를 반환|
|next previous|현재 달에서 현재 날짜 이후로 지정한 요일이 청므으로 나타나는 날짜를 반환하는 TemporalAdjuster를 반환함|
|nextOrSame|현재 날짜 이후로 지정한 요일이 처음으로 나타나는 날짜를 반환하는 TemporalAdjuster를 반환함|
|previousOrSame|현재 날짜 이후로 지정한 요일이 이전으로 나타나는 날짜를 반환하는 TemporalAdjuster를 반환함|


##### 날짜와 시간 객체 출력과 파싱

날짜와 시간 관련 작업에서 포매팅과 파싱은 떨어질 수 없는 관계다.  
```java
LocalDate date = LocalDate.of(2014,4,18);
String s = date.format(DateTimeFormatter.BASIC_ISO_DATE); //20140418
String s = date.format(DateTimeFormatter.ISO_LOCAL_DATE); //2014-03-18

```

날짜와 시간을 표현하는 문자열을 파싱해 날짜 객체를 다시 만들 수 있다.  

```java
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
LocalDate date1 = LocalDate.of(2014, 3, 18); 
String formattedDate = date1.format(formatter); 
LocalDate date2 = LocalDate.parse(formattedDate, formatter);

```

DateTimeFormatterBuilder 클래스로 복합적인 포매터를 정의해서 좀 더 세부적으로 포매터를 제어할 수 있다.   
즉, DateTimeFormatterBuilder 클래스로 대소문자를 구분하는 파싱, 관대한 규칙을 적용하는 파싱, 패딩, 포매터의 선택사항 등을 활용할 수 있다.   


##### 다양한 시간대와 캘린더 활용 방법
시간대 사용하기  
표준 시간이 같은 지역을 묶어서 시간대(time zone) 규칙 집합을 정의한다.   
ZoneRules 클래스에는 약 40개 정도의 시간대가 들어있다. ZoneId의 getRules()를 이용해서 해당 시간대의 규정을 획득할 수 있다. 
다음처럼 지역 ID로 특정 ZoneId를 구분한다.  

ZoneId romeZone = ZoneId.of("Europe/Rome");  

