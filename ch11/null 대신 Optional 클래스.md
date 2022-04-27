null인지 확인하려고 중첩 if 블록을 추가하면 코드 들여쓰기 수준이 증가되고, 코드의 구조가 엉망이 되고 가독성도 떨어진다.  
유지보수성도 어려워진다.  
또한 만약 null일 수 있다는 사실을 잊고 null검사를 하지 않았다면 NPE가 발생한다.  

``` java
 public String getCarInsuranceNameNullSafeV1(PersonV1 person) {
    if (person != null) {
      CarV1 car = person.getCar();
      if (car != null) {
        Insurance insurance = car.getInsurance();
        if (insurance != null) {
          return insurance.getName();
        }
      }
    }
    return "Unknown";
  }

  public String getCarInsuranceNameNullSafeV2(PersonV1 person) {
    if (person == null) {
      return "Unknown";
    }
    CarV1 car = person.getCar();
    if (car == null) {
      return "Unknown";
    }
    Insurance insurance = car.getInsurance();
    if (insurance == null) {
      return "Unknown";
    }
    return insurance.getName();
  }
```  

### null 때문에 발생하는 문제
* NullPointerException(NPE) : 에러의 근원, 가장 흔히 발생하는 에러
* 코드를 어지럽힌다. : NULL 확인 코드 때문에 가독성이 떨어진다.
* NULL은 아무 의미도 표현하지 않는다. : 값이 없음을 표현하는 방식으로는 적절하지 않다.  
* JAVA 철학에 위배 : 자바는 개발자로부터 모든 포인터를 숨겼다. 예외가 null 포인터.  
* 형식 시스템에 구멍 : null은 무형식이며 정보를 포함하고 있지 않으므로 모든 참조 형식에 null을 할당할 수 있다.  
                      null을 할당 하면서 시스템의 다른 부분으로 null이 퍼졌을 때 애초에 null이 어떤 의미로 사용되었는지 알 수 없다.  
                      

# Optional Class
java8은 java.util.Optional<T> 라는 새로운 클래스를 제공한다.  
Optional은 선택형값을 캡슐화 하는 클래스.  
![image](https://user-images.githubusercontent.com/67637716/165323713-c91516ac-1ed3-4c9e-84b9-72384fec4b0b.png)  
위의 그림에서 null 대신 빈 Optional을 할당.  
값이 있으면 Optional 클래스로 값을 감싼다.  
값이 없으면 Optional.empty 메서드로 Optional 반환.  

* null 참조 : NPE 발생
* Optional : 값이 없을 수 있음을 명시적으로 보여줌으로 다양하게 활용 가능.
  
``` java
public class Person {

  private Optional<Car> car;
  private int age;

  public Optional<Car> getCar() {
    return car;
  }

  public int getAge() {
    return age;
  }

}
  
  
public class Car {

  private Optional<Insurance> insurance;

  public Optional<Insurance> getInsurance() {
    return insurance;
  }

}
  
// 보험 회사의 이름은 무조건 있어야한다라는 것을 보여줌.  
// 보험회사 이름을 참조할 때 NPE가 발생할 수 있따는 것을 확인
// 예외를 처리하는 코드를 추가하는 것이 아니라 왜 NULL인지 확인하여 문제 해결해야함
// 모든 NULL 참조를 Optional로 대치하는것은 바람직하지 않다
public class Insurance {

  private String name;

  public String getName() {
    return name;
  }

}
```  
  
※ 모든 null 참조를 Optional로 대치하는것은 바람직 하지 않다.  
※ Optional을 unwrap해서 값이 없을 수 있는 상황에 적절하게 대응하도록 강제하는 효과가 있다.  
  
## Optional 적용 패턴
### Optional 객체 만들기
#### 빈 Optional
``` java
Optional<Car> optCar = Optional.empty();
```  
#### null이 아닌 값으로 Optional 만들기
``` java
 Optional<Car> optCar = Optional.of(car);
```  
car가 null이라면 즉시 NPE발생.  
Optional을 사용하지 않았다면, car의 프로퍼티에 접근하려 할 때 에러 발생.  

#### null값으로 Optional 만들기
``` java
 Optional<Car> optCar = Optional.ofNullable(car);
```  
null값을 저장할 수 있는 Optional.  
car가 null이면 빈 Optional 객체가 반환.  

* get()으로 Optional의 값을 가져올 수 있는데, Optional이 비어있으면 NPE가 발생한다.  
* Optional에서 제공하는 기능이 스트림연산에서 영감을 받았다.  
 
 
### Map으로 Optional 값을 추출하고 변환하기  
``` java
 String name = null;
		if(insurance != null) 
			name = insurance.getName();
 
 ----------------------------------
 Optional<Insurance> optInsurance = Optional.ofNullable(insurance);
	Optional<String> name = optInsurance.map(Insurance :: getName);
	
```  
Optional의 map은 Stream의 map 메서드와 비슷하다.  
Optional이 값을 포함하면 map의 인수로 제공된 함수가 값을 바꾼다.  
Optional이 비어있으면 아무 일도 일어나지 않는다.  
 
### flatMap으로 Optional 객체 연결  
``` java
 
 // 컴파일 되지 않음
 // getCar는 Optional<Car>형식 객체 반환
 Optional<String> name = optPerson.map(Person::getCar) // Optional<Optional<Car>>
				.map(Car :: getInsurance) // The type Car does not define getInsurance(Optional<Car>) that is applicable here
				.map(Insurance :: getName);
 
 -----------------------------------
 
 이차원 Optional을 일차원 Optional로 평준화
 Optional<String> optname = optPerson.flatMap(Person::getCar)
				.flatMap(Car :: getInsurance)
				.map(Insurance :: getName); // Insurance::getName은 평범한 문자열을 반환하므로 추가 "flatMap"은 필요가 없음.

String name = optname.orElse("UnKnown"); // 결과 optName이 비어있으면 기본값 사용
	
```
	
### Optional을 이용한 참조 체인
![image](https://user-images.githubusercontent.com/67637716/165416305-82fa7186-0c39-4987-9c5e-4a4e1026c50b.png)  
* Step 1 - flatMap()을 통해 Optional<Optional<Car>> 타입을 Optional<Car>로 평준화
* Step 2 - flatMap()을 통해 Optional<Optional<Insurance>> 타입을 Optional<Insurance>로 평준화
* Step 3 - map()을 통해 Optional<String> 반환
	* getName()은 String을 반환하므로 flatMap() 사용할 필요가 없음
* Step 4 - orElse() 메소드로 값이 없을 경우 기본값을 사용하도록 강제  

map연산일 경우 Optional<Optional<Car>>을 반환하는데 flatMap으로 Optional을 평준화한다.  
평준화 과정이란 두 Optional을 합치면서 하나라도 null이면 빈 Optional을 생성하는 연산이다.  
	

### Optional을 사용했을 때 데이터를 직렬화할 수 없는 이유
자바 언어 아키텍트 브라이언 고츠(Brian Goetz)는 Optional의 용도를 선택형 반환값을 지원하는 것이라고 명확하게 못박음  
Optional 클래스는 필드 형식으로 사용할 것을 가정하지 않았으므로 Serializable 인터페이스를 구현하지 않는다.  
따라서 도메인 모델에 Optional을 사용한다면 직렬화 모델을 사용하는 도구나 프레임워크에서 문제가 생길수 있다.  
이와 같은 단점에도 불구하고 Optional을 사용해서 도메인 모델을 구성하는것이 바람직하다고 한다.  
직렬화 모델이 필요하다면 Optional로 값을 반환받을 수 있는 메서드를 추가하는 방식이 있다.  
	
``` java
public class Person {
	private Car car;
	public Optional<Car> getCarAsOptional(){
		return Optional.ofNullable(car);
	}
}
```  
	
``` html
Java 직렬화란?  
Java 직렬화는 자바 시스템 내부에서 사용되는 객체 또는 데이터들을 외부의 자바 시스템에서도 사용할 수 있도록  
바이트(byte) 형태로 데이터 변환하는 기술과 바이트로 변환된 데이터를 다시 객체로 변환하는 역직렬화를 포함.  
시스템적으로 JVM의 Runtime Data Area(Heap 또는 스택영역)에 상주하고 있는 객체 데이터를 바이트 형태로 변환하는 기술과  
직렬화된 바이트 형태의 데이터를 객체로 변환해서 JVM으로 상주시키는 형태를 말한다.  
	
기본(primitive)타입과 java.io.Serializable 인터페이스를 상속받은 객체는 직렬화 할 수 있는 기본 조건을 가짐.  
https://velog.io/@sa1341/Java-%EC%A7%81%EB%A0%AC%ED%99%94%EB%A5%BC-%ED%95%98%EB%8A%94-%EC%9D%B4%EC%9C%A0%EA%B0%80-%EB%AC%B4%EC%97%87%EC%9D%BC%EA%B9%8C  

![image](https://user-images.githubusercontent.com/67637716/165417206-1d45d64a-c1ca-42a8-b41c-baba747c0c16.png)

```
	
	
	




