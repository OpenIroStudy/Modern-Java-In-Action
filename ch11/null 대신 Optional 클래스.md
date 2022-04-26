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
  


