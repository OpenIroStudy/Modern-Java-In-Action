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
  * 여기서는 `gradleApp`라는 이름으로 디렉터리를 만들어 본다.
  * mkdir gradleApp
3. 프로젝트 디렉터리로 이동
  * cd gradleApp
4. 마지막으로 프로젝트를 초기화
  * gradle init --type java-application
  * `gradle init` 이라는 것이 gradle 초기화를 위한 명령어.
  * `--type java-application`은 java응용 프로그램 프로젝트 유형을 지정

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

* gradle wrapper
  * gradle 설치 없이 프로젝트의 gradle 작업을 수행할 수 있도록 하는 파일
  * os에 따라 gradlew혹은 gradlew.bat 실행
  * 일반적으로 설치된 gradle보다 gradle wrapper을 사용하는 것이 권장 (작업 실행을 표준화함)

# gradle init 명령과 type 종류
* java-application : java 애플리케이션 프로젝트 작성에 대한 타입, 기본적으로 Application.java 파일 제공
* java-library : java 라이브러리 프로젝트 생성, 샘플로 제공되는 소스 코드 파일이 응용프로그램의 메인 클래스가 되어있지 않다는 정도의 차이

### 컴파일 및 실행, tasks
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
  * ![image](https://user-images.githubusercontent.com/67637716/167527155-333721da-b9ba-4a23-8f59-24d3ca315029.png)

* 프로젝트 클린
  * gradle clean
  * 프로젝트를 빌드할 때 build 디렉토리에 여러 파일이 저장된다.  
  * clean작업은 이 파일들을 제거하고 빌드 이전 상태로 되돌린다. 


* check : 테스크 등의 검증 작업 실행
* assemble : 배포할 결과물 생성

* build
  * gradle build
  * 테스트 -> 컴파일(.class 생성) -> .jar 파일 생성
  * check와 assemble 실행


# Gradle 플러그인
Project에 Java 애플리케이션 빌드에 필요한 기능을 추가하는 Plugin  
* Java 플러그인 추가
``` java
apply plugin: 'java'
```  
처음에 `apply plugin:`이라는 것은 gradle 플러그인을 사용하기 위한 것  
`java`는 java 프로그램을 위한 기능을 제공하는 플러그인.  
complieJava라는 테스크는 java플러그인에서 제공되는 것.  

* application 플러그인 추가
``` java
apply plugin: 'application'
```  
`application`은 응용 프로그램에 대한 기능을 제공하는 플러그인 
run 으로 응용프로그램을 실행할 수 있었던 것도 이 mainClassName 메인 클래스가 지정되어 있었기 때문.  

# 저장소(repositories)
build.gradle에 기술된 내용에는 "의존 라이브러리"에 대한 기술이 있다.  
gradle에는 프로그램으로 필요한 라이브러리를 자동으로 다운로드하고 통합하는 기능이 있다.  
따라서 `저장소(repository)`가 중요하다.  
<br>
저장소라는 것은 각종 프로그램들이 저장되는 위치.  
이 저장소는 "어떤 저장소를 사용하는지"를 빌드 파일에 저장하여 설정할 수 있음.  
``` java
repositories {
    .... 저장소 설정 .....
}
```  
위 내용이 저장소를 지정하기 위한 것.  
{}안에 저장소를 설정한다.  
온라인으로 접속하여 사용할 수 있는 저장소로 gradle에서는 대체로 다음 2개의 저장소 서비스를 이용.  

* maven 중앙 저장소
``` java
mavenCentral()
```  
Apache Maven 중앙 저장소를 이용하기 위한 것.  
gradle은 중앙 저장소를 그대로 사용할 수 있음.  
<br>
``` java
jcenter()
```  
JCenter라는 저장소를 사용.  
이것은 maven, gradle 등 각종 빌드 도구에서 사용할 수 있는 공개 저장소.  
mavenCentral(), jcenter()는 gradle 메서드.  
이러한 repositories 안에서 호출하여 지정된 저장소를 사용할 수 있음.  

# 의존 라이브러리(dependencies)
저장소에서 필요한 라이브러리를 사용할 수 있는 것이 `dependencies`라는 문장.  
``` java
dependencies {
 ... 라이브러리 지정 ...
} 
```  

### 의존성 옵션들
* implementation
  * 의존 라이브러리 수정시 본 모듈까지만 재빌드한다.
  * 본 모듈을 의존하는 모듈은 해당 라이브러리의 api 를 사용할 수 없음
  * A모듈을 수정하게 되면 A를 직접적으로 의존하는 모듈까지만 rebuild 한다.(빠르다)
  * API 노출을 막는다.
    * 사용자에게 필요이상의 API를 노출하는 것은 불필요하다.(Facade 패턴)
    * compile을 사용하면 연결된 모든 모듈의 API가 노출된다.

* api
  * 의존 라이브러리 수정시 본 모듈을 의존하는 모듈들도 재빌드
  * 본 모듈을 의존하는 모듈들도 해당 라이브러리의 api 를 사용할 수 있음

* compileOnly
  * 이름에서 알 수 있듯이 compile 시에만 빌드하고 빌드 결과물에는 포함하지 않는다.
  * runtime 시 필요없는 라이브러리인 경우 (runtime 환경에 이미 라이브러리가 제공되고 있는가 하는 등의 경우)

* runtimeOnly
  * runtime 시에만 필요한 라이브러리인 경우

* annotationProcessor
  * annotation processor 명시 (ex:lombok)

* testImplementation 
  * 테스트 코드를 수행할 때만 적용.


# 테스크 정의
gradle은 명령에 의해 `테스크(task)`를 수행하는 프로그램.  
위에서 gradle compileJava라던지 gradle run와 같은 명령어를 사용하였는데, 이들도 모두 `compileJava task수행`, `run task수행`이라는 것.  

### 테스크 정의 기본
테스크는 사용자가 정의할 수 있다.  
build.gradle에서 테스크의 처리를 기술해두면, 그것을 gradle명령으로 호출 실행 시킬 수 있음.  
``` java
task 테스크명{
 ... 수행할 처리 ...
}

/// 또는

task(테스크명){...}
```  
`task`라는 키워드를 사용하여 정의.  

#### example
``` java
task hello {
 println("이것은 hello task를 실행한 것")
} 
```  
cmd나 터미널에서 다음과 같이 실행. 혹은 spring boot gradle task.  
``` java
gradle hello
```  
이렇게 하면 hello 테스크가 실행.  
실행해 보면, println으로 출력하는 문장 이외에도 다양한 문장이 출력됨.  
```  
// quite모드로 테스크를 수행하면 많은 부분이 사라짐. -q 옵션을 지정하고 아래와 같이 실행.
gradle -q hello
```  

### doFirst와 doLast
``` java
task 테스크명 {

    doFirst {
        ...... 수행할 처리 ......
    }

    doLast {
        ...... 수행할 처리 ......
    }
}
```  

* doFirst : 최초에 수행하는 액션
* doLast : 최후에 수행하는 액션.  

task는 준비된 액션을 순서대로 실행해 나가는 역할.  
액션이란 구체적으로 처리의 실행 단위 같은 것.  

### 매개 변수 전달
테스크는 수행할 때 필요한 값을 매개 변수로 전달할 수 있다.  

``` java
gradle msg -Px=값
```  
-P 다음에 변수명을 지정하고 그 뒤에 등호로 값을 지정  
변수 name에 hong 값을 전달하고 싶다면 -Pname=hong 식으로 기술하면 됨. 

``` java
task hello {
    doLast {
        def n = max.toInteger()
        for (def i in 1..n) {
          println("No," + i + " count.")
        }
        println("end.")
     }
}

// 테스크는 "max"라는 변수를 사용하여 최대값을 지정.
gradle hello -Pmax=5
``` 

### 다른 테스크 호출
테스크에서 다른 테스크를 호출하는 경우.  
``` java
task a { ... }
task b { ... }
```  
a,b라는 테스크가 있을 때 task a에서 task b를 호출할 때는 tasks에 있는 작업 객체안의 메소드를 호출하여 실행해야함.  
작업하는 것은 모든 tasks라는 객체에 정리하고 있음.  
a,b테스크는 tasks.a, tasks.b로 지정가능.  
이 테스크 객체 안에 있는 `execute`라는 메서드를 호출하여 테스크를 수행할 수 있음.

``` java
tasks.a.execute()
tasks.b.execute()
```  
이런식으로 실행하여 테스크 a,b를 호출한다.  


# build.gradle
## 원리
build.gradle은 파일 자체가 Project 오브젝트(객체)로, project 오브젝트는 project 인터페이스를 구현하는 구현체이다.  
Project 오브젝트는 Project 단위에서 필요한 작업을 수행하기 위해 모든 메서드와 프로퍼티를 모아놓은 슈퍼 객체이다. 
![image](https://user-images.githubusercontent.com/67637716/167541142-2e1a9f8c-4e3f-4bc7-b516-bf08b046ee29.png)  

우리가 build.gradle에 작성하는 수많은 코드들은 모두 Project 오브젝트의 프로퍼티와 메서드가 되며,  
Project 오브젝트는 프로젝트 이름부터 변수, 메서드를 모두 포함하는 객체가 된다.  

Project 메서드 중에 대표적인 것은 모든 java application용 build.gradle이 가진 plugins, repositories, dependencies, application 메서드 이다.  
우리가 gradle task를 이용해 java application을 빌드하게 되면 build task는 이 메서드들을 수행시킨다.  
![image](https://user-images.githubusercontent.com/67637716/167541317-e24791cc-39b3-484b-a1e4-80373f134894.png)  
{}로 감싸여진 부분은 메서드의 인자로 받아지는 Groovy의 클로저(Closure)인데, Groovy의 클로저는 Java나 Kotlin의 람다와 같다.  
따라서 {} 블록 내부의 메서드들은 메서드의 인자로 넘겨질 수도 있다.  

※ 자바에서 클로저는 람다식이나 내부 클래스 같은 영역 안에서 외부에서 정의된 변수를 사용하는 방식을 의미한다.  

## build.gradle 작성
* plugins
프로젝트를 빌드하기 위해 여러가지 작업을 처리해줘야한다. (컴파일, jar파일 생성 등)  
이런작업들을 해주는 플러그인 추가( 플러그인은 필요한 과정들을 task로 포함하고 있음)  
빌드 시 필요한 모든 과정을 플로그인의 내부 task가 진행  

* repositories
저장소 정보를 관리  
로컬환경이나 네트워크에 라이브러리를 공개하고 그 주소를 저장소로 등록하면 저장소에 있는 라이브러리를 gradle이 취득하여 이용가능.  
대표적으로 maven 중앙 저장소, JCenter저장소, maven 원격저장소(Nexus)가 있음.  
Nexus는 일반적으로 인터넷망에 접속하지 못하는 망분리환경에서 내부적으로 의존성들을 관리하기 위해 사용됨.  
매번 외부망에서 의존성을 끌어오지 않고 내부망 Nexus를 proxy(cache)로 사용함으로써 좀 더 빠르게 의존성을 다운로드 할 수 있으며  
조직 내부에서만 사용하는 의존성을 배포해서 여러팀간 공유하는 목적으로도 활용된다.  

* dependencies
의존성에 관한 설정을 관리하는 프로퍼티.  
여기에 필요한 라이브러리 등의 정보를 기술하면 그 라이브러리를 참조할 수 있다,  

* ext
ext블록은 gradle의 모든 task에서 사용할 수 있는 일종의 전역 변수를 선언하는 블록

* buildscript
buildscript는 빌드하는 동안 필요한 처리를 모아놓은 곳.
이 안에서 dependencies와 repositories가 포함 할 수 있음

* allprojects, subprojects, project
멀티 모듈일 경우 새로운 블록이 등장한다.  
allprojects는 전체 프로젝트에, subprojects는 하위 프로젝트에, 그리고 프로젝트 이름을 사용한 project는 해당하는 프로퍼티에만 동작.

* task
사용자가 임의로 task를 작성해서 사용


=======================================================================================================

# gradle 멀티 프로젝트 만들기
1. root 프로젝트를 새로 만든후, 필요 없는 src폴더 등 삭제  
2. root 프로젝트 안에 프로젝트들을 복사 후 이동 ( 이 때 IDE의 workspace에 같은 이름의 프로젝트가 있으면 인식 안됨.)
3. 멀티 프로젝트도 필요 없는 파일 삭제  
![image](https://user-images.githubusercontent.com/67637716/167521171-9add1c33-3978-42eb-82e5-3a5f3be8c4c5.png)  
4. root의 settings.gradle에 밑의 그림과 같이 작성   
![image](https://user-images.githubusercontent.com/67637716/167521227-b9395e90-0b19-4de3-a7d1-ef1670836ad3.png)  
5. build.gradle 작성법
    * 기본적으로 root의 build.gradle에 작성시 멀티 프로젝트에 공통으로 적용됨.
    * 멀티 프로젝트 : 특별하게 의존성이 필요한 것만 주입
    * ![image](https://user-images.githubusercontent.com/67637716/167521338-8dbf4ee8-d668-4fc6-8b9a-306a4fc6fe10.png)

## root build.gradle 작성
``` java
buildscript {
    ext {
        springBootVersion = '2.3.4.RELEASE'
        dependencyManagementVersion = '1.0.10.RELEASE'
    }

    repositories {
        mavenLocal()
        maven {
            url "$repoUrl/reop-public"
            allowInsecureProtocol = true
            credentials {
                username "$repoUsername" 
                password "$repoPassword"
            }
        }
    }
    
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
        classpath("io.spring.gradle:dependency-management-plugin:${dependencyManagementVersion}")
    }
}

plugins {
    id 'base'
    id 'java'
}

allprojects {
    group = 'kr.co.openIro'
    version = '0.0.1-SNAPSHOT'
    sourceCompatibility = '1.8'
}

subprojects {
    apply plugin: 'org.springframework.boot'
    apply plugin: 'io.spring.dependency-management'
    apply plugin: 'java-library'
    apply plugin: 'java'

	
    repositories {  
        mavenLocal()
        maven {
            url "$repoUrl/repo-public"
            allowInsecureProtocol = true
            credentials {
                username "$repoUsername" 
                password "$repoPassword"
            }
        }
    }

    dependencies {
	// 라이브러리를 등록할 때에 버젼 지정을 안하는 것은 ‘spring-boot’ 플러그인을 적용시에 관련된 라이브러리에 버젼이 이미 설정되어있기 때문에 생략이 가능하다.
        implementation('kr.co.repo:openIroStudy-project:0.0.1-SNAPSHOT') {
            changing=true
        }

        runtimeOnly 'com.oracle.ojdbc:ojdbc8:19.3.0.0'
        runtimeOnly 'mysql:mysql-connector-java'
        runtimeOnly 'org.mariadb.jdbc:mariadb-java-client'
        runtimeOnly 'org.postgresql:postgresql'

        implementation 'io.springfox:springfox-swagger2:3.0.0'
        implementation 'io.springfox:springfox-swagger-ui:3.0.0'
		
		implementation 'org.springframework.boot:spring-boot-starter-web'
		
		// mybatis        
		implementation 'org.mybatis:mybatis:3.5.6'
		implementation 'org.mybatis:mybatis-spring:1.3.2'
		
		implementation 'org.springframework.boot:spring-boot-starter-webflux'
    
        compileOnly 'org.projectlombok:lombok'
        developmentOnly 'org.springframework.boot:spring-boot-devtools'
        annotationProcessor 'org.projectlombok:lombok'
        testImplementation 'org.springframework.boot:spring-boot-starter-test'
        implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
        
        // https://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient
    	implementation 'org.apache.httpcomponents:httpclient:4.5.13'
    	
    	testImplementation 'org.junit.jupiter:junit-jupiter-api:5.8.1'
    	testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.8.1'
    }

    test {
        useJUnitPlatform()
    }
}

```  


![image](https://user-images.githubusercontent.com/67637716/167520964-0e5f4867-788d-4015-946f-856f67d6d0f7.png)




