Reactive Programming에서 꽃이라고 할 수 있는 Reactive Operator에 대해 알아보는 시간~!  

# Reactive Operator
Reactive Operator는 java에서만 존재하는 것이 아니라 .NET, Scala, Swift 등 Reactive Programming을 지원하는 언어라면 어디서든 사용가능하다.  

* 다양한 연산 함수의 존재 : map, filter, reduce 등 400개 이상의 연산 함수가 존재하는데 대부분 map, filter, reduce에서 파생된 것
* 언어의 특성과는 무관 : 대부분의 reactive programming을 지원하는 언어에서 이러한 함수 이름을 그대로 사용

![image](https://user-images.githubusercontent.com/67637716/168550559-b9334106-38f7-4918-a19b-2ecb13e93572.png)  

## 기본 연산자
### map
``` java
public class MapOperator {

    private static Observable<Point> getPointObservable() {
        List<Point> list = new ArrayList<>(Arrays.asList(new Point(0, 0), new Point(0, 1), new Point(0, 2), new Point(1, 0), new Point(1, 1), new Point(1, 2),
                new Point(2, 0), new Point(2, 1), new Point(2, 2)));

        return Observable.fromIterable(list);
    }

    public static void main(String[] args) {
        Disposable disposable = getPointObservable().map(Point::toString).subscribe(System.out::println);
        disposable.dispose();
    }

}

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "[" + this.x + ", " + this.y + "]";
    }

}

```  
fromIterable() : Observable을 만드는 방법중 하나로 Iterable 인터페이스를 구현한 클래스에서 Observable객체를 생성하는 것.  
![image](https://user-images.githubusercontent.com/67637716/168556437-c8046b91-bbfb-450b-898a-fe2ae022e63b.png)  

### flatMap
flatMap은 연산 결과가 반드시 Observable로 나온다는것이 map과 다른점.  
map이 1:1 데이터 연산 함수라면 flatMap은 1:N, 1:1 Observable 함수  
map은 동기 방식이기 때문에 비동기와 같이 사용할 경우 그 효과를 보기 어려우며 비동기 작업으로 데이터를 처리하고자 하는경우 flatMap을 사용해야함.  
![image](https://user-images.githubusercontent.com/67637716/168556705-7e2e3a5e-bbf0-4697-9c69-cced00f20220.png)  

### filter
![image](https://user-images.githubusercontent.com/67637716/168556937-483afe73-55e7-4fc3-a477-a81d794a66d3.png)  

### reduce
두 개 이상의 여러 데이터를 하나의 데이터로 취합하는 연산 함수.  
예를 들어 발행된 데이터를 모두 취합하여 리스트로 만들고, 모든 데이터를 조건 취합해서 하나의 데이터로 합처줄때 reduce를 사용가능.  
``` java
public static void main(String[] args) {
        Disposable disposable = getPointObservable().reduce((p1, p2) -> {
            return new Point(p1.x + p2.x, p1.y + p2.y);
        }).map(Point::toString).subscribe(System.out::println);
        disposable.dispose();
    }

```  

## 생성 연산자
java에서 객체를 인스턴스화 할 때는 new 연산자를 이용했다.  
RxJava에서는 new 연산자를 이용하지 않고, just 메서드를 사용하는 등 팩토리 패턴 형태의 메서드를 사용하여 인스턴스화 하였다.  
<br>
#### interval
![image](https://user-images.githubusercontent.com/67637716/168558636-9d4a7d9a-f728-4659-b3da-2d277b49061b.png)  
interval 연산자는 일정 시간 간격으로 데이터를 생성하는 연산자.  
주어진 시간 간격으로 증가하는 데이터의 변화를 감지하고자할 때 쓰는 방법  
Java의 Generic을 사용하므로 Reference Class 사용  







