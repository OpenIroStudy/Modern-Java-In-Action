# maven 기본 개념
Maven을 이해하기 위해서는 Plugin, Phase, Goal, LifeCycle을 이해해야한다.  

### Plugin, Goal
Maven에서 수행되는 실제 동작 단위는 Goal이다.  
Goal은 plugin에 1개 이상 포함되어 있으며, Maven에서 무언가 수행된다는 것은 Plugin의 goal이 실행된다고 보면됨.  
Java로 치면 Plugin은 class이고 goal이 method로 생각하면 됨.  

### Phase
사전적 의미는 '단계'이다.  
Maven에서도 이와 동일한 의미로 사용되는데 순서와 의존관계가 추가된 Goal로 생각하면 됨.  
![image](https://user-images.githubusercontent.com/67637716/167544418-5ccb1a54-4fc0-41cf-a0d2-ede1600180c2.png)  
위처럼 Phase는 라이프사이클의 각 단계를 의미하는데 Phase를 통해 Goal를 수행하면 처음부터 해당 단계까지의 Phase가 모두 순차적으로 수행.  

예를 들어 mvn test라는 Phase를 통해 빌드하면 log를 통해 Phase가 모두 순차적으로 수행된다.  

### LifeCycle
Maven에는 기본으로 제공하는 빌드 단계가 있다.  
일반적으로 프로젝트를 빌드할 때의 과정을 보면  
``` 
빌드 결과물 삭제 -> 컴파일에 필요한 자원을 복사 -> 소스코드 컴파일 -> 테스트 -> 압축(패키지) -> 배포
```  
위의 과정을 거치는데 이런 빌드 단계를 Maven은 미리 정의하고 있다.  

이와 같이 미리 정의하고 있는 빌드 순서를 LifeCycle이라 한다.  
Maven에서는 총 3개의 LifeCycle을 제공한다.  

1. 소스 코드를 컴파일, 테스트, 압축, 배포를 담당하는 기본 LifeCycle  
![image](https://user-images.githubusercontent.com/67637716/167544751-b601fb28-f7be-4d7a-9d66-7521f4bfc0af.png)  
기본 LifeCycle의 각 Phase는 아래와 같이 동작을 수행  
- compile : 소스 코드를 컴파일
- test : Junit과 같은 단위 테스트 프레임워크로 단위 테스트를 한다. 기본 설정은 단위 테스트가 실패하면 빌드 실패로 간주
- package : 단위 테스트가 성공하면 pom.xml 의 <packagin /> 엘리먼트 값(jar, war, ear 등)에 따라 압축
- install : 로컬 저장소에 압축한 파일 배포.
- deploy : 원격 저장소에 압축한 파일을 배포, 원격저장소는 외부에 위치한 메이븐 저장소를 의미.

2. 빌드한 결과물을 제거하기 위한 clean LifeCycle  
![image](https://user-images.githubusercontent.com/67637716/167545074-03642aae-5b35-482f-b77e-e667be78721f.png)  
clean Phase를 실행하면 메이븐 빌드를 통하며 생성된 모든 산출물을 삭제.  
메이븐은 기본으로 모든 살출물을 target 디렉토리에 생성하기 때문에 clean Phase를 실행하면 target 디렉토리가 삭제

3. 프로젝트 문서 사이트를 생성하는 site LifeCycle
![image](https://user-images.githubusercontent.com/67637716/167545186-1a171fbf-11c0-4469-bd3b-4193cd249550.png)  
site LifeCycle은 site와 site-deploy Phase를 이용하여 실행.  
메이븐 설정 파일의 정보를 활용하여 프로젝트에 대한 문서 사이트를 생성할 수 있도록 지원.  
site phase는 문서 사이트를 생성하고, site-deploy phase는 생성한 문서 사이트를 설정되어있는 서버에 배포하는 역할을 함.  




