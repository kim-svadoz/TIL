# :facepunch: DAMOIM - WEB Project

> WEB Application 구축 프로젝트

# 1. 기획

> **주제** : Spring Framework 기반 오프라인 모임 플랫폼 개발
>
> **기획의도** : 현존하는 소모임 어플들의 문제점을 보완하는 오프라인 모임 커뮤니티를 웹으로 재탄생
>
> **동기** : 기존에 있떤 웹, 어플리케이션들이 주로 온라인 모임을 위한 플랫폼으로 오프라임 모임을 지원해주기 위한 웹사이트를 개발
>
> **지원기능**
>
> - 회원관리(가입, 정보수정, 프로필사진추가, 로그인, 모임맞춤추천, 가입한 모임의 일정알림, 새 게시글 알림)
> - 모임관리(검색, 생성, 가입, 게시글(작성, 본인 게시글(수정, 삭제, 사진등록), 댓글(작성, 삭제))
> - 모임별, 오프라인정모관리(생성, 조회, 참가, 카카오맵 api를 이용한 장소검색, 추가, 조회)

# 2. 시스템 아키텍쳐

![image-20200916104927714](https://user-images.githubusercontent.com/58545240/93283596-cd3ee000-f80b-11ea-89b9-4b90e3d836fc.png)

# 3. DB 설계

- 전체 테이블

![image-20200916105010031](https://user-images.githubusercontent.com/58545240/93283601-d2039400-f80b-11ea-897b-31b2723a1e48.png)

- 나의 개발 영역 테이블

![image-20200916105015774](https://user-images.githubusercontent.com/58545240/93283611-d62fb180-f80b-11ea-8742-dd769ae49fa5.png)

# 4. 시스템 구축

![image-20200916105029603](https://user-images.githubusercontent.com/58545240/93283618-daf46580-f80b-11ea-80d4-394f8f3ff7a8.png)

## 4.1 서버 포트 연결

![image-20200916105041588](https://user-images.githubusercontent.com/58545240/93283628-dfb91980-f80b-11ea-8004-a0763f40d084.png)

## 4.2 Maven 라이브러리 추가

## 4.3 pom.xml에 라이브러리 추가

- **tiles, spring-jdbc, mybatis, JSON, validation**

## 4.4 web.xml에 spring 설정파일 추가

![image-20200916105143428](https://user-images.githubusercontent.com/58545240/93283638-e3e53700-f80b-11ea-9ad3-996da39bc37d.png)

## 4.5 tiles setting

![image-20200916105325608](https://user-images.githubusercontent.com/58545240/93283660-f1022600-f80b-11ea-98fa-6c391e40c844.png)

![image-20200916105330290](https://user-images.githubusercontent.com/58545240/93283672-f65f7080-f80b-11ea-91d2-2de66f997ae4.png)

## 4.6 mybatis setting

![image-20200916105341980](https://user-images.githubusercontent.com/58545240/93283683-fcede800-f80b-11ea-8587-4348dd855757.png)

![image-20200916105344750](https://user-images.githubusercontent.com/58545240/93283692-011a0580-f80c-11ea-8b88-7b295c7e40cf.png)

## 4.7 Oracle DB setting

![image-20200916105354623](https://user-images.githubusercontent.com/58545240/93283699-070fe680-f80c-11ea-9896-46a0711d2d6d.png)

# 5. 개발환경

![image-20200916105413013](https://user-images.githubusercontent.com/58545240/93283744-1ee76a80-f80c-11ea-985c-787c93e74be7.png)

# 6. site-map

![image-20200916105421195](https://user-images.githubusercontent.com/58545240/93283753-227af180-f80c-11ea-9adb-0653f3c6b72b.png)

# 7. 나의 기여도

1. **모임 오프라인 일정 페이지**

![image-20200916105455158](https://user-images.githubusercontent.com/58545240/93283761-26a70f00-f80c-11ea-92b8-85ccfd087c45.png)

![image-20200916105457815](https://user-images.githubusercontent.com/58545240/93283788-332b6780-f80c-11ea-91ea-7c0a6c677f08.png)

![image-20200916105503634](https://user-images.githubusercontent.com/58545240/93283801-39214880-f80c-11ea-8dbe-db7a5d5b50e7.png)

![image-20200916105506195](https://user-images.githubusercontent.com/58545240/93283812-3de5fc80-f80c-11ea-90a4-55e4969b2355.png)

- '모임'탭을 클릭하면 나오는 페이지로 직접 오프라인 모임일정을 개설할 수 있으며 가장 최근의 모임들을 조회하고 참석버튼을 눌러 참석리스트에 추가할 수 있음
- 해당 모임에서 지금까지 가장 참석을 많이 한 순서대로 list를 출력해줌

2. **오프라인 모임 생성 페이지**

![image-20200916105611111](https://user-images.githubusercontent.com/58545240/93283823-43dbdd80-f80c-11ea-8838-d025fcd22add.png)

![image-20200916105614257](https://user-images.githubusercontent.com/58545240/93283835-4807fb00-f80c-11ea-984b-7b3798880ba5.png)

3. **전체 오프라인 모임을 조회하고 해당 모임장은 기록을 삭제할 수 있는 페이지**

![image-20200916105640763](https://user-images.githubusercontent.com/58545240/93283844-4c341880-f80c-11ea-87a6-d2f26d8f0bd3.png)

- 현재까지 개설된 모든 오프라인 정모를 조회할 수 있고, 해당 모임을 개설한 모임장만이 기록을 삭제할 수 있음

4. **해당 날짜 클릭시 오프라인 모임정보와 참성명단 조회 페이지**

![image-20200916105722721](https://user-images.githubusercontent.com/58545240/93283854-4fc79f80-f80c-11ea-90ee-88f59ef99145.png)

![image-20200916105729813](https://user-images.githubusercontent.com/58545240/93283862-535b2680-f80c-11ea-84a2-624d2c9a9a81.png)

- jQuery의 DatePicker를 커스터마이징하였고 Ajax를 통해 비동기 화면 구현