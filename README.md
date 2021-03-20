## 📖 도서관 대출 시스템 
(2021.03.06 ~ 13)  
여러 책들을 보관중인 도서관에서 유저가 책을 빌리고 관리자가 책들을 관리하는 대출 시스템

> 본 프로젝트는 TDD를 이용하여 그동안 Pure Java 프로그램을 개발해왔던 두 프로젝트에 이어, MVC 패턴으로 확장하여 API 형식의 시스템을 개발하는 것을 목표로 한다. 

### Team1 Member
| 🙍‍♂️김혜준 | 🙍‍♀️우혜진 |
| :----: | :----: |
| [@Kim-Hye-Jun](https://github.com/Kim-Hye-Jun) | [@HJ-Woo](https://github.com/HJ-Woo) |

### ✔ 요구사항
- 유저는 아이디 비밀번호로 회원가입을 진행할 수 있다
- 유저는 이용자와 관리자로 나눌 수 있다
- 관리자는 책을 등록 삭제 조회 수정이 가능하다
- 이용자는 도서관에서 책을 빌릴 수 있다
- 이용자는 자신의 빌린 책의 목록을 확인 할 수 있다
- 이용자는 최대 5권의 책을 빌릴 수 있다
  > 우리 팀은 관리자도 포함하여 User가 책을 빌릴 수 있게 만들었다.
- 도서관은 책을 중복해서 가질 수 있다
- 책은 한 도서관에만 속할 수 있다
- 책은 제목과 작가와 도서 번호를 가진다

### 🛠 기술스택
- **Spring Boot** (2.4.3), **Java** (1.8), **Gradle**
- **Spring Data JPA**
- **H2 Database**
- **Lombok**
- **Test**: Mockito, TestRestTemplate
> 💁‍♀️ 기술 스택 선정 이유  
> - Java 기반으로 MVC 패턴을 적용한 API를 개발하기 위해 Spring Boot를 선택  
> 
> - OOP에 집중하고자 Database로의 접근은 ORM을 이용한 Spring JPA를, DB는 embedded H2를 사용
> 
> - Unit test를 위해 Mock&InjectMock를 사용, Integrate test를 위해 MockMvc와 RestTemplate을 활용 [둘의 차이점](https://stackoverflow.com/questions/25901985/difference-between-mockmvc-and-resttemplate-in-integration-tests)
### Entity 분석
  ![image](https://user-images.githubusercontent.com/59992230/111818053-176f5e00-8922-11eb-9ed9-a3a04944926a.png)

<details>
    <summary>간단한 설명</summary>

Book  

어느 도서관에 속해 있는지, 빌려진 상태인지 도서관에 있는 상태인지, 책의 고유 ID, 제목, 저자가 등록 되어있다.

BorrowedBook  

누가 빌려갔는지, 어제 빌려갔는지, 빌린책의 고유ID, Book이었을 때의 Book값과 함께 저장되어있다.

Libary  

소유 책리스트가 저장되어있다.

User  

추상클래스로 되어있는데 인터페이스로 했을때 @Entity로 등록이 되지 않아 추상클래스로 만들었고 Member,Admin이 구현되도록 한다. ID,PW,이름이 있다.

Admin  

관리자이다.

Member  

이용자이다.  
</details>  
    
    
### API document
  **User**
  |Function|URL|Method|
  |--------|---|------|
  |회원가입|/join|POST|

  **Book**
  |Function|URL|Method|
  |--------|---|------|
  |책 등록|/book/new|POST|
  |책 삭제|/book/delete|POST|
  |책 수정|/book/update|POST|
  |특정 책 조회|/book/find/{bookTitle}|GET|

  **Borrow**
  |Function|URL|Method|
  |--------|---|------|
  |책 빌리기|/book/borrow|POST|
  |빌린 책 목록 조회|/book/borrows|GET|

### ✍ issue
- JPA는 Interface를 Entity로 사용할 수 있을까?
 [참고링크](https://stackoverflow.com/questions/2912988/persist-collection-of-interface-using-hibernate/2918468#2918468)  
  <details>
    <summary>자세히</summary>

  > 이용자와 관리자가 공통적으로 수행하는 로직을 설계할 때 User를 interface를 활용하려 했으나, JPA annotation은 Interface에서 지원하지 않아 사용할 수 없었다.  
  TABLE_PER_CLASS 상속 전략과 User를 추상 엔티티로 활용하여 해당 문제를 해결하였다.
  </details>

- ``@RequestBody``와 ``@RequestParam``은 왜 동시에 파라미터로 받을 수 없을까? [참고링크](https://stackoverflow.com/questions/19468572/spring-mvc-why-not-able-to-use-requestbody-and-requestparam-together)
  <details>
    <summary>자세히</summary>

  > 해당 링크에서 소개된 바는 다음과 같다.  
  > 1. request body를 ``@RequestBody`` 어노테이션에 맵핑
  > 2. request body의 request param을 ``@RequestParam``에 맵핑
  >
  > 1, 2 순서로 paramter가 처리되는데, 2번을 수행할 때 이미 request를 읽어서 query string안에 request paramter가 없거나 reqeust body에 남아있는 항목이 없기 때문에, Handler에 의해 처리될 수 없다.
  >
  > 반대로 paramter의 순서를 바꿔서 2, 1 이 수행되더라도 ``@RequestParam``에서 먼저 ``HttpSerlvetRequest``과 ``InputStream``에서 맵핑할 수 있는걸 읽어온다. 그러면 1을 수행할 때 request body에 아무것도 남아있지 않아 읽어올 수 없다.
  </details>

- ``@RequestBody``에서 json과 객체가 맵핑이 안되는 문제 [참고링크1](https://2ssue.github.io/programming/json-and-requestBody/), [링크2](https://bactoria.github.io/2019/08/16/ObjectMapper%EB%8A%94-Property%EB%A5%BC-%EC%96%B4%EB%96%BB%EA%B2%8C-%EC%B0%BE%EC%9D%84%EA%B9%8C/)

  <details>
    <summary>자세히</summary>

  > join 로직 수행시 admin(관리자)와 사용자(member)를 구분하기 위한 것으로는 간단히 isAdmin flag를 사용하였다.
  > 
  > Testtemplate을 사용한 로직으로는 정상적으로 수행되었는데, Postman을 통해 값을 보냈더니 한 매개변수만 맵핑이 안되는 문제가 생긴 것이다.
  >   ![image](https://user-images.githubusercontent.com/59992230/111805779-b0977800-8914-11eb-8bcc-08c50933ac17.png)
  >   ![image](https://user-images.githubusercontent.com/59992230/111805793-b4c39580-8914-11eb-8bfa-ede893c25e60.png)
  > 
  > 해당 링크를 참고한 결과, Spring에서 Http 메세지의 형변환은 ``HTTPMessageConverter`` [문서](https://www.baeldung.com/spring-httpmessageconverter-rest)을 이용하는데, 그중에서도 JSON의 형변환을 담당하는 [MappingJackson2HttpMessageConverter](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/converter/json/MappingJackson2HttpMessageConverter.html)의 ``readJavaType``에서는 
  ![image](https://user-images.githubusercontent.com/59992230/111808244-2d2b5600-8917-11eb-8451-d09a5b7f146e.png) 이와같이
   [ObjectMapper](https://fasterxml.github.io/jackson-databind/javadoc/2.10/com/fasterxml/jackson/databind/ObjectMapper.html?is-external=true)를 이용한다.
   > 
   > 자 조금만 더 파고들어보자. ObjectMapper()에서는 ConfigOverrides()를 호출한다.
   > ![image](https://user-images.githubusercontent.com/59992230/111809623-86e05000-8918-11eb-9f1b-a39df9ee2e9d.png)
![image](https://user-images.githubusercontent.com/59992230/111809635-8a73d700-8918-11eb-828a-a1bb65ef8ead.png)
   > 
   > 다음으로 ConfigOverrides()에서는 VisibilityChecker의 defaultInstance()를 호출한다.
   > ![image](https://user-images.githubusercontent.com/59992230/111809647-8e9ff480-8918-11eb-888a-be56a8584d0e.png)
   >
   > 이제 다왔다! 
   > VisibilityChecker에서는 mapping시 접근지정자에 따른 객체의 field 접근 가능 방법에 대해 기술하고 있다.
   > 
   > ![image](https://user-images.githubusercontent.com/59992230/111809663-92cc1200-8918-11eb-9f42-02ca8bef370b.png)
   > 
   | How access/Type | private | protected |  public |
   | :----: | :----: | :----: | :----: |
   | Creator | o | o | o |
   | Getter | x | x | o |
   | is-Getter | x | x | o |
   | Setter | o | o | o |
   | Field | x | x | o |
 
   > JoinDTO는 모든 접근자를 private으로 선언해주었고, 생성자로 mapping하는 것이다.
   >   
   > 엥, 근데 이미 @AllArgsConstructor로 맵핑되는거 아닌가? **그렇다!!!**  
   > 아니란걸 정리하다가 뒤늦게 깨달았는데, 정리한게 아까워서 일단 적어보았다.  
   > **실제로 참고해야하는 링크**는 [이것](https://stackoverflow.com/questions/32270422/jackson-renames-primitive-boolean-field-by-removing-is) 의 **두번째 답변**이다. 참고로 Kotlin에서도 유사한 현상으로 [토론](https://github.com/FasterXML/jackson-module-kotlin/issues/80)이 일었다.
   > 
   > 정답은 바로바로.... [MapperFeature](https://fasterxml.github.io/jackson-databind/javadoc/2.8/index.html?com/fasterxml/jackson/databind/MapperFeature.html)에 있었다.
   >
   > ![image](https://user-images.githubusercontent.com/59992230/111813917-2dc6eb00-891d-11eb-820b-9916d436fde0.png)
   > 
   > 표준 자바 빈 naming convention에 따라 prefix가 'is'로 시작하는건 is getter로 판단한다고 나와있다. 즉, isAdmin으로 넘길 경우엔, 자동으로 getter로 생각한다는 것. is prefix가 붙어있는 경우엔 is를 제거한 필드명으로 맵핑을 받는다.
   > 고로 is를 제거하고 [관련링크](https://stackoverflow.com/questions/12316321/true-or-false-into-boolean-using-jackson-json-parsing) mapping해줄 경우엔 정상적으로 작동한다.
   >  
   > ![image](https://user-images.githubusercontent.com/59992230/111814789-3c61d200-891e-11eb-812b-b6f202078939.png)
   > ![image](https://user-images.githubusercontent.com/59992230/111814807-3ff55900-891e-11eb-9f7c-ffc8d795f626.png)
   > 
   > 번외로 ``@JsonPropert("isAdmin")``으로 추가해줘도 가능하다.  
   > 명명규칙에 근거해서 isAdmin field 명은 admin으로 변경하였다.

</details>