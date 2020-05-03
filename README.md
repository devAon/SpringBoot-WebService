# SpringBoot-WebService


## ✔ 프로젝트 개발환경

프로젝트 개발 환경은 다음과 같습니다.

- IDE : IntelliJ IDEA Ultimate
- Git Tools : Git Bash
- OS : Window
- SpringBoot 2.2.6
- Java8
- Gradle

​    

​    

   

## **✔**프로젝트 코드 ( 깃허브 )

👉👉👉 https://github.com/devAon/SpringBoot-WebService

   


​        

## ✔ 프로젝트 코드 및 구현 내용 설명**

### **build.gradle**

```
plugins {
    id 'org.springframework.boot' version '2.2.6.RELEASE'
    id 'io.spring.dependency-management' version '1.0.9.RELEASE'
    id 'java'
}

group = 'com.devAon'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '1.8'

configurations {
    compileOnly {
        extendsFrom annotationProcessor
    }
}

repositories {
    mavenCentral()
}

//Spring Boot Overriding
ext['hibernate.version'] = '5.2.11.Final'

dependencies {
    implementation 'pl.allegro.tech.boot:handlebars-spring-boot-starter:0.3.0'
    implementation 'org.springframework.boot:spring-boot-starter-actuator'
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    compileOnly 'org.projectlombok:lombok'
    runtimeOnly 'com.h2database:h2'
    annotationProcessor 'org.projectlombok:lombok'
    testImplementation('org.springframework.boot:spring-boot-starter-test') {
        exclude group: 'org.junit.vintage', module: 'junit-vintage-engine'
    }

}

test {
    useJUnitPlatform()
}
```

---

   

   

### 🐥🐥🐥  중요한 개념 정리 🐥🐥🐥


## ✔ SpringBoot & JPA로 간단 API 만들기

* #### SpringBoot & JPA 사용하면 좋은점?

  진짜 집중해야할 비즈니스  로직에만 집중할 수 있다.

  


* ####  Entity의 PK는 Long 타입의 Auto_increment 사용이 좋다.

  이렇게 하면 MySQL 기준으로 bigint 타입이다.
  주민등록번호와 같은 비지니스상 유니크키나, 여러키를 조합한 복합키로 PK를 잡을 경우 난감한 상황이 발생할 수 있는데 이를 예방할 수 있다.



* #### Spring Boot 2.0 에선  PK의 Auto_increment 를 위해 

  #### strategy = GenerationType.IDENTITY 옵션을 추가해야한다.

  ```
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  ```

  Hibernate 5.0부터 MySQL의 AUTO는 IDENTITY가 아닌 **TABLE을 기본 시퀀스 전략**으로 선택된다.

  우리의 의도대로  Auto_increment  동작하도록 하기 위해 `@GeneratedValue`의 전략을 `GenerationType.IDENTITY`로 지정





* #### Entity 클래스를 생성시, 무분별한 setter 메소드 생성하지 말자

  무분별하게 getter/setter를 생성할 경우, 

  해당 클래스의 인스턴스 값들이 언제 어디서 변해야하는지 코드상으로 명확히 구분할수가 없어서

  차후 기능변경시 복잡해질 수 있다.



​		대신, 기본생성자를 @NoArgsConstructor(access = AccessLevel.PROTECTED)로 막아두고

​		setter 메소드 없이 DB에 insert를 하기 위해 

​		생성자 대신 @Builder를 통해 채워야할 필드가 무엇인지 명확하게 지정해주고

​		생성시점에 값을 채워주면 된다.



* #### 테스트 코드 작성하는 방법

```java
@SpringBootTest
public class PostsRepositoryTest {
    ...
        
@Test
    public void 게시글저장_불러오기() {
        //given
        postsRepository.save(Posts.builder()
                .title("테스트 게시글")
                .content("테스트 본문")
                .author("jojoldu@gmail.com")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle(), is("테스트 게시글"));
        assertThat(posts.getContent(), is("테스트 본문"));
    }
}
```

JUnit을 통해 어떤 값을 입력할 경우 어떤 결과가 반환되는지 검증할수 있다.



**BDD(Behaviour-Driven Development)**

- **given**
  - 테스트 기반 환경을 구축하는 단계
  - 여기선
  - `@builder`의 사용법도 같이 확인
- when
  - 테스트 하고자 하는 행위 선언
  - 여기선 Posts가 DB에 insert 되는것을 확인하기 위함
- **then**
  - 테스트 결과 검증
  - 실제로 DB에 insert 되었는지 확인하기 위해 조회후, 입력된 값 확인



* #### `@Autowired` 대신 `@AllArgsConstructor` 사용하기

  스프링프레임워크에선 Bean 을 주입받는 방식들이 아래와 같이 있다.

  - `@Autowired`
  - setter
  - 생성자

  

  이중 가장 권장하는 방식이 **생성자로 주입받는 방식**이다.
  (`@Autowired`는 **비권장방식**이다.)
  즉, 생성자로 Bean 객체를 받도록 하면 `@Autowired`와 동일한 효과를 볼 수 있다.

  생성자는 `@AllArgsConstructor` 에서 해결해준다.
  모든 필드를 인자값으로 하는 생성자를 Lombok의 `@AllArgsConstructor`이 대신 생성해 준 것입니다.

  또한, 생성자를 직접 안쓰고 Lombok 어노테이션을 사용한 이유가 있다.
  해당 클래스의 의존성 관계가 변경될때마다 생성자 코드를 계속해서 수정하는 번거로움을 해결하기 위해서다.

  





* #### Entity 클래스와 거의 유사한 형태여도 꼭 DTO 클래스 생성해야한다.

  절대로 테이블과 매핑되는 Entity 클래스를 Request/ Response 클래스로 사용해서는 안된다.   
  Entity 클래스는 가장 Core한 클래스이다.   
  수많은 서비스 클래스나 비지니스 로직들이 Entity 클래스를 기준으로 동작한다.   
  Entity 클래스가 변경되면 여러 클래스에 영향을 끼치게 되는 반면 Request와 Response용 DTO는 View를 위한 클래스라 정말 자주 변경이 필요하다.   
  따라서, View Layer와 DB Layer를 철저하게 역할 분리를 하는게 좋다.   
  실제로 Controller에서 결과값으로 여러 테이블을 조인해서 줘야할 경우가 빈번하기 때문에 Entity 클래스만으로 표현하기가 어려운 경우가 많다.   
  그러니 꼭 Entity 클래스와 Controller에서 쓸 DTO는 분리해서 사용해야한다.   
