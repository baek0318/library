# 도서관 대출 시스템
이용자가 책을 빌릴 수 있도록 서비스를 제공한다

## 💡 요구사항
- 유저는 아이디 비밀번호로 회원가입을 진행할 수 있다
- 유저는 이용자와 관리자로 나눌 수 있다
- 관리자는 책을 등록 삭제 조회 수정이 가능하다
- 이용자는 도서관에서 책을 빌릴 수 있다
- 이용자는 자신의 빌린 책의 목록을 확인 할 수 있다
- 이용자는 최대 5권의 책을 빌릴 수 있다
- 도서관은 책을 중복해서 가질 수 있다
- 책은 제목과 작가와 도서 번를다

## 🔨 기술 스택
- **Spring Framework**  
  Java를 이용해서 객체지향적인 애플리케이션을 만들기 위해서 사용
- **Spring MVC**  
  스프링을 기반으로 웹 서비스를 하기위해서 사용
- **Spring Data JPA**  
  개발 시간 단축과 DB말고 애플리케이션 코드에 더 집중하기 위해서 사용
- **H2**  
  in memory 데이터베이스로써 spring에서 자동 설정을 지원 가볍게 만들기 위해서 사용
- **Rest API**
- **Mockito**  
service layer를 repository로 부터 분리해서 독립적인 테스트를 진행하기 위해서 사용

## ER 다이어그램
![library-ER_Diagram](./img/library.png)

## 클래스 다이어그램

  
## ❌ 문제 해결 또는 궁금점 해결
- **Specification 오류**  
  [Specification 정리](./img/Specification.pdf)
- **Mockito any() 오류**
- **Spring boot h2 db 초기설정**
  [Spring_boot_H2_.pdf](https://github.com/Spring-Study-20210220/library/files/6127293/Spring_boot_H2_.pdf)
- **테스트가 안되는 상황** 
