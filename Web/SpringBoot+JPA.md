# SpringBoot & JPA

## boot dependencies 및 library 확인

프로젝트의 Gradle에서 디펜던시를 확인할 수 있다.

-   spring-boot-start
    -   spring core와 관련된 라이브러리 들을 웬만한 것을 가지고 있다. (엄청 많아)
-   jpa
-   aop
-   HikariCP : Spring boot 2.0부터 기본이다. (아주 좋다) (스프링 풀!)
-   hibernate
-   logging (보통은 slf4j를 쓰고(대세) 이를 위한 구현체로 LOGBACK을 꼽거나, ... 을 꼽거나 , (lib나 설정을 살짝 바꾸면!))
    -   logback



test

-   junit
-   mokito



요새는 jsp보다는 thymeleaf를 추세이다.

왜 thymeleaft를 쓰는가?

Natural한 templates이다.

마크업을 깨지 않고, 그대로 쓰는 것이 장점이다. (웹 브라우저에서 열린다!)

2점대에서는 단점이 있었지만 3점대에서는 괜찮아 졌다.

```html
<br> : 2.0에서는 에러
<br> : 3.0에서는 가능
```

요새는 서버사이드에서 만든다기 보다는, 리액트나 vue.js가 많기 때문에 그런 것을 쓰기도 한다.



스프링을 쓸대 Guides가 있는데, 공식 (#spring.io/guides) 를 참고하며 공부하는 것이 좋다.



## h2 database

h2 폴더로 가서 .sh를 실행시키고 배치파일을 실행 시킨 후에 DB 연결하여 사용한다.



## JPA & DB 설정

application.properties를 써도 되는데, 김영한 강사님은 yml 을 좋아해서 properties를 지우고, yml을 생성한다. (둘중에 하나만 쓰면 된다)

`jpa.properties.hibernate.show_sql: TRUE`는 시스템 아웃으로 sql 로그를 보는 것이고,
`logging.level.orghibernate.SQL: debug`는 로거를 통해서 로그를 보는 것이므로, 후자를 사용하도록 하자.



## 도메인 모델과 테이블 설계

양방향 연관관계에서 외래키가 연관관계의 주인이다.
연관관계 주인쪽에서 값을 세팅해야 값이 변경된다. (반대쪽(거울쪽)은 그냥 단순히 조회용으로 사용된다.)