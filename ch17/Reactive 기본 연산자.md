Reactive Programming에서 꽃이라고 할 수 있는 Reactive Operator에 대해 알아보는 시간~!  

# Reactive Operator
Reactive Operator는 java에서만 존재하는 것이 아니라 .NET, Scala, Swift 등 Reactive Programming을 지원하는 언어라면 어디서든 사용가능하다.  

* 다양한 연산 함수의 존재 : map, filter, reduce 등 400개 이상의 연산 함수가 존재하는데 대부분 map, filter, reduce에서 파생된 것
* 언어의 특성과는 무관 : 대부분의 reactive programming을 지원하는 언어에서 이러한 함수 이름을 그대로 사용

![image](https://user-images.githubusercontent.com/67637716/168550559-b9334106-38f7-4918-a19b-2ecb13e93572.png)  

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

