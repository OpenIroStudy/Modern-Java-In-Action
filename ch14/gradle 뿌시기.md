# gradle의 두두두둥장
기본적으로 java는 빌드 도구를 이용해 프로젝트 관리가 이루어졌고, Apache Ant라는 빌드 도구가 2000년에 두두등장 하셨다.  
이 후에 아직까지도 실무에서 많이 쓰고 있는 Apache Maven이라는 빌드 툴이 등장하였고, 현 시점 java 빌드 도구의 표준이라고 할 수 있음.  
<br>이 maven의 강력한 라이벌이 gradle인데, gradle은 groovy라는 언어를 기반으로 만들어진 빌드 도구이다.  
<b>groovy</b>는 java와 마찬가지로 jvm에서 실행되는 스크립트 언어.  
java와 달리 소스 코드를 컴파일 할 필요는 없음(소스 코드를 그대로 실행).  
java와 호환이 되고 java클래스 파일을 그대로 groovy 클래스로 사용 가능.  
문법도 java와 아주 가까워 java를 보다 사용하기쉽게 한 것으로 느낄 수 있음.  

이 간편하게 사용할 수 있는 groovy를 사용하여 빌드 처리를 작성하고, 실행하는 것이 gradle.  
<br>기존의 maven을 이용하면 xml기반으로 빌드 처리를 작성.  
내용이 복잡해지면 xml 기반의 묘사는 어렵다.  
gradle은 java와 비슷한 코드를 써서 빌드처리를 관리 할 수 있다.  


# gradle 프로젝트 초기화
gradle 개발을 하기 위해 먼저 프로젝트를 준비.  
1. 프로젝트를 생성할 위치로 이동
  * cd /Users/hong/workspace/dev
2. 프로젝트 디렉토리 만듬
  * 여기서는 'gradleApp'라는 이름으로 디렉터리를 만들어 본다.
  * mkdir gradleApp
3. 프로젝트 디렉터리로 이동
  * cd gradleApp
4. 마지막으로 프로젝트를 초기화
  * gradle init --type java-application
  * 'gradle init' 이라는 것이 gradle 초기화를 위한 명령어.
  * '--type java-application'은 java응용 프로그램 프로젝트 유형을 지정

! spring boot를 이용하면 쉽게 가능하지만 cl(command line)를 이용해 만드는 방법을 알아봄

# gradle 프로젝트 구조
![image](https://user-images.githubusercontent.com/67637716/167525305-458d0be0-c6d7-48f3-bf23-05054c1e465d.png)  
![image](https://user-images.githubusercontent.com/67637716/167525337-80c71f3f-0dcd-4b5b-82db-7365a608ee16.png)  

* .gradle 디렉토리
  * gradle이 사용하는 폴더, 작업(task)로 생성된 파일이 저장
  * 이 내용을 편집하는 일은 거의 없음

* gradle디렉토리
  * gradle이 필요한 경우 사용할 디렉토리
  * 기본적으로 gradle환경을 정리한 Wrapper 파일이라는 파일들이 저장되어있음.

* src 디렉토리
  * 프로젝트에서 만든 프로그램 관련 디렉토리
  * 프로젝트에서 사용하는 파일(소스코드, 각종 리소스 파일 등)은 모두 포함됨

* build.gradle
  * gradle 기본 빌드 설정 파일
  * 이 안에 프로젝트 빌드 처리에 대해 내용이 작성되어 있음
  * groovy 언어로 작성

* gradlew, gradlew.bat
  * 이 2개는 gradle명령
  * bat이 붙어있는것이 window용이고 , gradlew는 macOS 및 Linux 용

* setting.gradle
  * 프로젝트에 대한 설정 정보를 작성한 파일
  * MSA(Micro Service Architecture) 구조로 프로젝트를 구성할 경우, root 프로젝트 하위로 모듈을 추가할 경우 
  * settings.gradle 파일에 모듈을 추가한다고 명시를 해야 함. 


# gradle init 명령과 type 종류
* java-application : java 애플리케이션 프로젝트 작성에 대한 타입, 기본적으로 Application.java 파일 제공
* java-library : java 라이브러리 프로젝트 생성, 샘플로 제공되는 소스 코드 파일이 응용프로그램의 메인 클래스가 되어있지 않다는 정도의 차이

### 컴파일 및 실행
* 프로그램 컴파일
  * gradle compileJava
  * 컴파일은 compileJava라는 테스트로 제공, java 소스 코드를 컴파일 하기 위한 것

* 프로그램 실행
  * gradle run
  * java-application 타입의 프로젝트는 run task라는 것이 제공되고, 이를 실행하여 메인클래스를 실행가능

* 프로그램 패키지
  * gradle jar
  * jar 테스크는 그 이름대로 프로그램을 jar파일에 모와서 저장.
  * 프로젝트에 생성되는 build 디렉토리 하위에 libs 디렉토리에 저장
