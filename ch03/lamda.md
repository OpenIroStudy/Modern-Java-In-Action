#### 람다란 무엇인가?
---------------------------------
<b> 람다 표현식 </b> 은 메서드로 전달할 수 있는 익명 함수를 단순화한 것이라고 할 수 있다. 람다 표현식에는 이름은 없지만, 파라미터 리스트, 바디, 반환 형식, 발생할 수 있는 예외 리스트는 가질 수 있다.

* 익명 :  보통의 메서드와 달리 이름이 없으므로 익명이라 표현한다. 구현해야 할 코드에 대한 걱정거리가 줄어든다.
* 함수 : 람다는 메서드처럼 특정 클래스에 종속되지 않으므로 함수라고 부른다. 하지만 메서드처럼 파라미터 리스트, 바디, 반환 형식, 가능한 예외 리스트를 포함한다. 
* 전달 : 람다 표현식을 메서드 인수로 전달하거나 변수로 저장할 수 있다.
* 간결성 : 익명 클래스처럼 많은 자질구레한 코드를 구현할 필요가 없다. 


```java
Comparator<Apple> byWeight = new Comparator<Apple>() { //기존 코드
  public int compare(Apple a1, Apple a2) {
    return a1.getWeight().compareTo(a2.getWeight());
    }
}
Comparator<Apple> byWeight = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());

```

람다는 세 분류로 이루어진다.   

![image](https://user-images.githubusercontent.com/43237961/163168064-2f87344f-0236-44d9-bccd-3bd6dd667ac9.png)  

파라미터 리스트  :Comparator의 compare 메서드 파라미터 (사과 두 개)  
화살표 : ->는 람다의 파라미터 리스트와 바디를 구분한다.  
람다 바디 : 두 사과의 무게를 비교한다. 람다의 반환 값에 해당하는 표현식이다.  

```java
(String s) s.length(); //String 형식의 파라미터 하나를 가지며 int를 반환
(Apple a) a.getWeight() > 140; //Apple 형식의 파라미터를 하나 가지고 boolean을 반환
(int x, int y) -> { //int 형식의 파라미터 두 개를 가지며 리턴값이 없다. (void), 람다는 여러 행의 문장을 포함할 수 있다. 
    System.out.println("Result");
    System.err.println(x+y);
}
()->42 //파라미터가 없으며 int 42를 반환한다.
(Apple a1, Apple a2) -> a1.getWeight().compareTo(a1.getWeight()) //Apple 형식의 파라미터 두 개를 가지며 int를 반환한다. 
        

```

* 표현식 스타일 : (parameters) -> expression
* 블록 스타일 : (parametes) -> {statement;}

<h3> Quiz </h3> 

```
람다 규칙에 맞지 않은 람다식은?

1. () -> {}
2. () -> "Raoul"
3. () -> { return "Mario"; }
4. (Integer i) -> return "Alan" + i;
5. (String s) -> { "Iron Man"; }



Answer : 안알랴줌

4. return은 흐름 제어문이다. (Integer i) -> {return "Alan" + i;}
5. "Iron Man"은 구문이 아니라 표현식이다. (String s) -> "Iron Man"처럼 되어야 올바른 람다 표현식이다. 

``` 

람다 예제

* 불리언 표현식 :(List<String> list) -> list.isEmpty()
* 객체 생성 : () -> new Apple(10)
* 객체에서 소비 : (Apple a) -> { System.out.println(a.getWEIGHT()); }
* 객체에서 선택/추출 : (String s) -> s.length()
* 두 값을 조합 : (int a, int b) -> a * b
* 두 객체 비교 : (Apple a1, Apple a2) -> a1.getWeight().compareTo(a1.getWeight())

  
##### 람다를 어디에 사용할 까?
----------------------------------------------------------------------------------------
- 함수형 인터페이스
  함수형 인터페이스란 <b> 1 개의 추상 메소드 </b> 를 갖는 인터페이스를 말합니다.  
Java8 부터 인터페이스는 기본 구현체를 포함한 디폴트 메서드 (default method) 를 포함할 수 있습니다.  
  <b> 여러 개의 디폴트 메서드가 있더라도 추상 메서드가 오직 하나면 </b> 함수형 인터페이스입니다.  
자바의 람다 표현식은 함수형 인터페이스로만 사용 가능합니다.  
<br> <br> 
함수형 인터페이스는 위에서도 설명했듯이 추상 메서드가 오직 하나인 인터페이스를 의미합니다.  
  추상 메서드가 하나라는 뜻은 <b> default method 또는 static method 는 여러 개 </b> 존재해도 상관 없다는 뜻입니다.  
그리고 @FunctionalInterface 어노테이션을 사용하는데, 이 어노테이션은 해당 인터페이스가 함수형 인터페이스 조건에 맞는지 검사해줍니다.  
@FunctionalInterface 어노테이션이 없어도 함수형 인터페이스로 동작하고 사용하는 데 문제는 없지만, <b> 인터페이스 검증과 유지보수를 위해 붙여주는 게 좋습니다. </b>  

```java
  @FunctionalInterface
interface CustomInterface<T> {
    // abstract method 오직 하나
    T myCall();

    // default method 는 존재해도 상관없음
    default void printDefault() {
        System.out.println("Hello Default");
    }

    // static method 는 존재해도 상관없음
    static void printStatic() {
        System.out.println("Hello Static");
    }
}
```
위 인터페이스는 함수형 인터페이스입니다.  

default method, static method 를 넣어도 문제 없습니다.  

어차피 함수형 인터페이스 형식에 맞지 않는다면 @FunctionalInterface 이 다음 에러를 띄워줍니다.  

|  Multiple non-overriding abstract methods found in interface com.practice.notepad.CustomFunctionalInterface  
  
```java
//실제 사용
CustomInterface<String> customInterface = () -> "Hello Custom";

// abstract method
String s = customInterface.myCall();
System.out.println(s);

// default method
customInterface.printDefault();

// static method
CustomFunctionalInterface.printStatic();
```  
함수형 인터페이스라서 람다식으로 표현할 수 있습니다.  

String 타입을 래핑했기 때문에 myCall() 은 String 타입을 리턴합니다.  

마찬가지로 default method, static method 도 그대로 사용할 수 있습니다.  

위 코드를 실행한 결과값은 다음과 같습니다.    
  
```java
Hello Custom
Hello Default
Hello Static
```


#### 2. Java 에서 기본적으로 제공하는 Functional Interfaces  
매번 함수형 인터페이스를 직접 만들어서 사용하는 건 번거로운 일입니다.  

그래서 Java 에서는 기본적으로 많이 사용되는 함수형 인터페이스를 제공합니다.  

기본적으로 제공되는 것만 사용해도 웬만한 람다식은 다 만들 수 있기 때문에 개발자가 직접 함수형 인터페이스를 만드는 경우는 거의 없습니다.    
    
![image](https://user-images.githubusercontent.com/43237961/163173970-1d9162d3-e29c-4113-950f-0a962db99bcf.png)  

  
* Supplier
```java
@FunctionalInterface
public interface Supplier<T> {
    T get();
}
```
Supplier 는 아무런 인자를 받지 않고 T 타입의 객체를 리턴합니다.  
람다식으로는 () -> T 로 표현합니다.  
공급자라는 이름처럼 아무것도 받지 않고 특정 객체를 리턴합니다.  
  
* Runnable
```java
@FunctionalInterface
public interface Runnable {
    public abstract void run();
}
```
Runnable 은 아무런 객체를 받지 않고 리턴도 하지 않습니다.  
람다식으로는 () -> void 로 표현합니다.    
Runnable 이라는 이름에 맞게 "실행 가능한" 이라는 뜻을 나타내며 이름 그대로 실행만 할 수 있다고 생각하면 됩니다.  

* Callable
```java 
@FunctionalInterface
public interface Callable<V> {
    V call() throws Exception;
}
```
Callable 은 아무런 인자를 받지 않고 T 타입 객체를 리턴합니다.  
람다식으로는 () -> T 로 표현합니다.    
Runnable 과 비슷하게 Callable 은 "호출 가능한" 이라고 생각하면 좀 더 와닿습니다.  
  
- Supplier vs Callable
-------------------------------------------------------------------
Supplier 와 Callable 은 완전히 동일합니다.  
아무런 인자도 받지 않고 특정 타입을 리턴해줍니다.  
사실 그냥 차이가 없다고 생각하시면 됩니다.  
단지 Callable 은 Runnable 과 함께 병렬 처리를 위해 등장했던 개념으로서 ExecutorService.submit 같은 함수는 인자로 Callable 을 받습니다.   
  
  
  
  
* 두 개의 인자를 받는 Bi 인터페이스
---------------------------------------------------------
특정 인자를 받는 Predicate, Consumer, Function 등은 두 개 이상의 타입을 받을 수 있는 인터페이스가 존재합니다.  
  
- 함수형 인터페이스	    Descripter	             Method  
* BiPredicate	        (T, U) -> boolean	       boolean test(T t, U u)  
* BiConsumer	        (T, U) -> void	         void accept(T t, U u)  
* BiFunction	        (T, U) -> R	             R apply(T t, U u)  
  
  
- 지금까지 확인한 함수형 인터페이스를 제네릭 함수형 인터페이스라고 합니다.

- 자바의 모든 형식은 참조형 또는 기본형입니다.

* 참조형 (Reference Type) : Byte, Integer, Object, List
* 기본형 (Primitive Type) : int, double, byte, char
<br> <br> 
Consumer<T> 에서 T 는 참조형만 사용 가능합니다.  

Java 에서는 기본형과 참조형을 서로 변환해주는 박싱, 언박싱 기능을 제공합니다.  

* 박싱 (Boxing) : 기본형 -> 참조형 (int -> Integer)  
* 언박싱 (Unboxing) : 참조형 -> 기본형 (Integer -> int)  

  
  <br> <br> <br> 
  
게다가 개발자가 박싱, 언박싱을 신경쓰지 않고 개발할 수 있게 자동으로 변환해주는 오토박싱 (Autoboxing) 이라는 기능도 제공합니다.  
예를 들어 List<Integer> list 에서 list.add(3) 처럼 기본형을 바로 넣어도 사용 가능한 것도 오토박싱 덕분입니다.  
하지만 이런 변환 과정은 비용이 소모되기 때문에, 함수형 인터페이스에서는 이런 오토박싱 동작을 피할 수 있도록 기본형 특화 함수형 인터페이스 를 제공합니다.  

* Predicate (T -> boolean)
  * 기본형을 받은 후 boolean 리턴

    * IntPredicate
    * LongPredicate
    * DoublePredicate

* Consumer (T -> void)
  * 기본형을 받은 후 소비

    * IntConsumer
    * LongConsumer
    * DoubleConsumer
  
```java
// Quiz 함수형 인터페이스를 고르시오.
  
public interface Adder {
    int add(int a, int b);
}
public interface SmartAdder extends Adder {
    int add(double a, double b);
}
public interface Nothing() {
    
}  
  
  
  
  
  
  
  
  
  
  
  
Answer : 안알랴줌
  
Adder만 함수형 인터페이스이다. 
SmartAdder는 두 추상 add 메서드를 포함하므로 함수형 인터페이스가 아니다.
Nothing은 추상 메서드가 없으므로 함수형 인터페이스가 아니다. 
```  
  
  
  
람다 표현식으로 함수형 인터페이스의 추상 메소드 구현을 직접 전달할 수 있으므로 전체 표현식을 함수형 인터페이스의 인스턴스로 취급할 수 있다.      
함수형 인터페이스보다는 덜 깔끔하지만 익명 내부 클래스로도 같은 기능을 구현할 수 있다.  

```java
        Runnable r1 = () -> System.out.println("Hello Workd"); //람다 사용
        
        Runnable r2 = new Runnable() { //익명 클래스 사용
            @Override
            public void run() {
                System.out.println("Hello World2");
            }
        };

        public static void process(Runnable r) {
            r.run();
        }

        process(r1);
        process(r2);
        process(() -> System.out.println("Hello World3 ")); //직접 전달된 람다 표현  
```  
  
  
#### 함수 디스크립터
함수형 인터페이스의 추상 메서드 시그니처는 람다 표현식의 시그니처를 가리킨다.   
![image](https://user-images.githubusercontent.com/43237961/163178671-ef3e0438-4c71-4bf9-9ffa-75290bfc8526.png)  
    
Runnable의 함수 디스크립터 : () -> void  
<br><br> 
process(()-> System.out.println("Hello World3 ")); 은 인수가 없는 void를 반환하는 람다 표현식이다. 이는 Runnable 인터페이스의 run 메서드 시그니처와 같다.
 
  
  
  
```java
//Quiz
  
  
1. execute(() -> {});
public void execute(Runnable r) {
    r.run();
}
        
2. public Callable<String> fetch() {
    return () -> "Tricky example";
}
  
3. Predicate<Apple> p = (Apple a) -> a.getWeight();

  
  
  
  
  
  
Answer : 안알랴줌
  
1번, 2번은 유효한 람다식이다.

첫 번째 예제에서 람다 표현식 () -> {}의 시그니처 () -> void며 Runnable의 추상 메서드 run의 시그니처와 일치하므로 유효한 람다 표현식이다.
다만 람다의 바디가 비어있으므로 이 코드를 실행하면 아무것도 일어나지 않는다.

두번째 fetch 메섣의 반환 형식은 Callable<String>이다. T를 String으로 대치했을 떄 Callable<String>메서드의 시그니처는 () -> String이 된다.   
  
  
세번 째 예제에서는 (Apple) -> Integer이므로 Predicate<Apple> : (Apple) -> boolean의 test 메서드의 시그니처와 일치하지 않는다.   
따라서 유효한 람다식이 아니다.  
  
  
```

![image](https://user-images.githubusercontent.com/43237961/163180444-fbab97c4-4cd1-4041-b91e-a9236ec1f86b.png)  

- 1단계 :  동작 파라미터화
 
```java
public String processFile() throws IOEception {
	try(BufferedReader br = 
                  new BufferedReader(new FileReader("data.txt"))) {
	   return br.readLine();	
    }
}
``` 

- 2단계 :  함수형 인터페이스를 통하여 동작 전달 
 
```java
public interface BufferedReaderProcessor {
	String process(BufferedReader b) throws IOException;
}
 

public String processFile(BufferedReaderProcessor p) throws IOException {
  ... 
}
``` 

- 3단계:  동작실행 
 
```java
public String processFile(BufferedReaderProcessor p)
            throws IOException {
	try(BufferedReader br = 
               new BufferedReader(new FileReader("data.txt"))) {
    	return p.process(br)
    }       
}
``` 

- 4단계:  람다 전달
 
```java
String oneLine = processFile((BUfferedReader br) -> br.readLine()); 
String twoLine = processFile((BUfferedReader br) -> br.readLine() + String oneLine = processFile((BUfferedReader br) -> br.readLine()); 
);
```  
  
```
Quiz
다음과 같은 함수형 디스크립터가 있을 때 어떤 함수형 인터페이스를 사용할 수 있는 가? + 유효한 람다식 정의
  
  
1. T -> R
2. (int, int) -> int
3. T -> void
4. () -> T
5. (T, U) -> R

1. Function<T, R> 
2. IntBinaryOperator (int, int) -> int
3. Consumer<T>
4. Supplier<T>
5. BiFunction<T, U, R> <T, U> -> R

  
```
  
106pg 대응표 잘 보십삼요  
  
##### 형식 검사, 형식 추론, 제약
--------------------------------------------------------
* 형식 검사 : 람다가 사용되는 context를 이용해서 람다의 형식을 추론할 수 있다. 
  어떤 콘텍스트 ( ex. 람다가 전달될 메서드 파라미터나 람다가 할당되는 변수)에서 기대되는 람다 표현식의 형식을 대상 형식이라고 부른다.  

```java
  
List<Apple> heavierThan150g = 
    filter(inventory, (Apple apple) -> apple.getWeight() > 150);
```
  
1. filter 메서드의 선언을 확인한다.
2. filter 메서드는 두 번째 파라미터로 Predicate<Apple> 형식을 기대한다.
3. Predicate<Apple>은 test라는 한 개의 추상 메서드를 정의하는 함수형 인터페이스다.
4. test 메서드 Apple을 받아 boolean을 반환하는 함수 디스크립터를 묘사한다.
5. filter 메서드로 전달된 인수는 이와 같은 요구사항을 만족해야 한다. 
  
  
![image](https://user-images.githubusercontent.com/43237961/163184802-7132ddce-8705-408c-ba94-d56c9290c56d.png)  
그림 109pg 참조
  

대상 형식이라는 특징 때문에 같은 람다 표현식이라도 호환되는 추상 메서드를 가진 다른 함수형 인터페이스로 사용될 수 있다.  
```java
        Comparator<Apple> c1 = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
        ToIntBiFunction<Apple, Apple> c2 =  (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
        BiFunction<Apple, Apple, Integer> c3v =  (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
```
  
* 특별한 void 호환 규칙

람다의 바디에 일반 표현식이 있으면 void를 반환하는 함수 디스크립터와 호환된다. (물론 파라미터 리스트도 호환되어야 함).   
  ex. 
  Predicate<Stinrg> p = s -> list.add(s);  
  Consumer<String> b = s -> s.list.add(s);  
  
List의 add 메서드는 Consumer 콘텍스트가 기대하는 void 대신 boolean을 반환하지만 유효한 코드다.  

```
Quiz

Object o = () -> { System.out.println("Tricky example"); };
  
Answer : 책 111pg 확인 ㅋ-ㅋ  
```
  
  

대상 형식을 이용해서 함수 디스크립터를 알 수 있으므로 컴파일러는 람다의 시그니처도 추론할 수 있다.  
결과적으로 컴파일러는 람다 표현식의 파라미터 형식에 접근할 수 있으므로 람다 문법에서 이를 생략할 수 있다.  

``java
List<Apple> greenApples = filter(inventory, apple -> Green.equals(apple.getColor())); //파라미터 a에는 형식을 명시적으로 지정하지 않았다.   
Comparator<Apple> c = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()); //형식을 추론하지 않음
Comparator<Apple> c = (a1, a2) -> a1.getWeight().compareTo(a2.getWeight());	 //형식을 추론함
	
	
```
	
	

* 람다캡처림: 자유 변수를 활용할 수 있다. 
```java
int portNum = 1337;
Runnable r = () -> System.out.println(portNum);
	
```
	
	
람다는 인스턴스 변수와 지역 변수를 자유롭게 캡쳐할 수 있다. 하지만 그러려면 지역 변수는 명시적으로 final로 선언되거나 실질적으로 final로 선언된 변수와 똑같이 사용되어야 한다. 
람다 표현식은 한 번만 할당할 수 있는 지역 변수를 캡쳐할 수 있다.  

```java
	int portNum = 123;
        Runnable r = () -> System.out.println(portNum);
	portNum = 456; //컴파일 에러
```
인스턴스 변수는 힙에 저장되는 반면 지역변수는 스택에 위치한다.   
람다에서 지역변수에 바로 접근할 수 있다는 가정하에 람다가 스레드에서 실행된다면 변수를 할당한 스레드가 사라져서   
변수 할당이 해제되었는데도 람다를 실행하는 스레드에서는 해당 변수에 접근하려 할 수 있다.    
따라서 자바 구현에서는 원래 변수에 접근을 허용하는 것이 아니라 자유 지역 변수의 복사본을 제공한다.    
복사본의 값이 바뀌지 않아야 하므로 지역 변수에는 한 번만 값을 할당해야 하는 제약이 생긴다.      
