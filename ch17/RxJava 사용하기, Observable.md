# 리액티브 라이브러리 RxJava 사용하기
RxJava는 자바로 리액티브 애플리케이션을 구현하는데 사용하는 라이브러리다.  
역압력 기능(request 메서드)이 있는 Flow를 포함하는 Flowable 클래스가 있다.  
나머지 클래스는 역압력을 지원하지 않는 Observable 클래스다.  
이 클래스는 단순한 프로그램, 마우스 움직임 같은 사용자 인터페이스 이벤트에 적합하다.(역압력을 적용하기 어렵기 때문)  
이런 이유로 RxJava는 이벤트 스트림을 두 가지 구현 클래스로 제공한다.  
RxJava는 천 개 이하 요소를 가진 스트림이나 마우스 움직임, 터치 이벤트 등 역압력을 적용하기 힘든 GUI이벤트 , 자주 발생하지 않는 종류의 이벤트에 역압력을 적용하지 말 것을 권장.  
모든 구독자는 구독 객체의 request(Long.MAX_VALUE)메서드를 사용해 역압력 기능을 끌 수 있다.  
물론 Subscribe가 정해진 시간 안에 수신한 모든 이벤트를 처리할 수 있다고 확신할 수 있는 사오항이 아니라면 역압력 기능을 끄지 않는 것이 좋다.  

### Observable 만들고 사용하기
Observable, Flowable 클래스는 다양한 종류의 리액티브 스트림을 편리하게 만들 수 있도록 여러 팩토리 메서드를 제공한다.  
Obserable과 Flowable은 Publisher를 구현하므로 팩토리 메서드는 리액티브 스트림을 만든다.  
just() 메서드는 한 개 이상의 요소를 이용해 이를 방출하는 Observable로 변환한다.  

interval() 메서드는 subscribe한 시간 부터 주어진 시간 간격으로 0부터 1씩 증가하는 Long 객체를 발행한다.  



## RxJava 처음 시작하기
![image](https://user-images.githubusercontent.com/67637716/168542156-50e0efa9-20ed-4de4-ab14-ac0d9f59c7f0.png)  



``` java
<dependency>
    <groupId>io.reactivex.rxjava2</groupId>
    <artifactId>rxjava</artifactId>
    <version>2.2.19</version>
</dependency>
import io.reactivex.Observable;

public class FirstExample {
    public void emit() {
        Observable.just("Hello", "RxJava 2!!")
            .subscribe(System.out::println);
    }
    
    public static void main(String args[]) {
        FirstExample demo = new FirstExample();
        demo.emit();
    }
}
```  
소스 코드에서 쉽게 추측할 수 있듯이 "Hello"와 "RxJava 2!!" 문자열을 받아서 System.out.println()을 실행한다.  
import 키워드 부분을 살펴보면 RxJava2의 기본 패키지 이름은 io.reactivex이다.  
ReacitveX의 홈페이지 주소(http://reactivex.io)를 거꾸로 쓴것과 같다.  


#### Observable 클래스  
 
* just()
Observable 클래스의 just() 함수는 가장 단순한 Observable 선언 방식이다.  
위 예에서는 데이터 소스에서 "Hello"와 "RxJava 2!!"를 발행했다. Integer와 같은 래퍼타입부터 Order 같은 사용자 정의 클래스의 객체도 인자로 넣을 수 있다.  
최대 10개까지 넣을 수 있다.  

* subscribe()
subscribe() 함수는 Observable을 구독한다.  
Observable은 subscribe() 함수를 호출해야 비로소 변환한 데이터를 구독자에게 발행한다(just() 함수만 호출하면 데이터를 발행하지 않는다).  
이 부분은 옵저버 패턴과 동일하다고 생각하면 된다.  
반드시 데이터를 수신할 구독자가 subscribe() 함수를 호출해야 Observable에서 데이터가 발행된다.  

Observable의 구독자는 onNext("Hello"), onNext("RxJava2!!"), onComplete()의 순서로 메시지를 받는다.  


* emit()

동사 emit은 '어떤 것을 내보내다'라는 뜻인데 RxJava 개발 문서에서는 Observable이 subscribe() 함수를 호출한 구독자에게 데이터를 발행하는 것을 표현하는 용어로 사용한다.  
RxJava 관련 문서에 자주 등장하는 단어이다.  

* create()
just와 그 기능이 같지만, onNext, onComplete, onError등의 콜백 함수를 직접 만들어서 사용하는 방식.  
just는 위 3가지의 콜백함수가 기본적으로 구현되어있는 구현체를 가지고 있고, subscribe 처리만 넣어주면 되었다.  

``` java
 public static void main(String[] args) {
        Observable<String> observable = Observable.create(emitter -> {
            try {
                emitter.onNext("Hello");
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
        Disposable disposable = observable.subscribe(System.out::println, Throwable::getStackTrace);
        disposable.dispose();
    }
```  

#### Disposable 클래스
RxJava1에서 사용되던 Subscription이 RxJava2에서 Disposable로 바뀌었다.  
Observable을 통해 데이터 스트림을 발행하고 subscribe() 메서드를 사용하여 구독할 때에 반환하는 값이 Disposable 인터페이스의 객체이다.  

* dispose()
Disposable 함수를 통해 Observable에게 더 이상 데이터를 발행하지 않도록 할 수 있다.( 메모리 누수를 막을수 있다.)  

* clear()
add된 Observable 해지, 단 isDisposed()가 false이기 때문에 재사용 가능.


# Hot Observable/ Cold Observable
옵저버 패턴을 이용하여 만들어진 Observable에는 두 가지 종류가 있다.  
1. 데이터를 로딩하고 바로 그 로딩된 내용을 반영할 것인지
2. 사용자의 요청이 있을 때까지 기다릴 것인지




