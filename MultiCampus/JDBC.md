# 1. JDBC

> JDBC(Java Database Connectivity)는 자바에서 데이터베이스를 접속하기 위한 기술

## 19-12-23 월

1. 드라이버로딩
   
* 드라이버를 로딩한다는 것은 JVM이 어 떤 DBMS를 사용하는지 인식시키는 작업으로 java에서 오라클을 연동할 수 있도록 오라클에서 제공해준 드라이버 파일을 JVM의 메모리에 로딩시키는 작업을 하는 단계
  
2. 커넥션설정하기

   * 우리가 프린트기를 설치하고 케이블로 연결하는 것처럼. 혹은 네트워크에서 공유된 프린트기를 찾아 연결하는 것과 같이 DBMS에 저장된 데이터에 접근하기 위해서는 DBMS에 접속을 해야 합니다.

   * getConnection

     

3. Statement객체 생성하기

4. SQL문실행하기

5. 결과처리

6. 자원반납



* statament의 주된 역할

=> dbms의 sql을 실행

* statement는 종속적이라 connection을 가지고 만들어야 한다. 

=> 개발자가 만드는게 아니라 =>있던 클래스를 가지고 객체를 통해 만든다.

## 19-12-26 목

### JDBC란?

>  JDBC로 DBMS연동하는 기술

(SQL문을 자바로 만든 시스템을 통해 실행할 수 있도록 만들어진 자바의 기술)

### [ JDBC api사용 전처리순서 ]

#### 1. jdbc 드라이버를 제조사 홈페이지에서 다운로드.

(=> C:\oraclexe\app\oracle\product\11.2.0\server\jdbc\lib 폴더에 있는 ojdbc6.jar 파일)

#### 2. JVM이 인식할 수 있는 위치에 연결

(1) 이클립스를 사용하는 경우(Application)

	1. 작업중인 프로젝트 선택
 2. 프로젝트에서 단축메뉴 선택 => [ Build path ] - [ Configure Build path ]
 3. 대화상자에서 세 번째 탭인 [ Libaares ]탭 선택
 4. [Add External jar... ] 선택하고 1번 위치에 있는 ojdbc6.jar파일을 등록

### [ JDBC API 사용하기 ]

#### 1. 오라클 드라이버 로딩

=> Jvm에서 드라이버내의 api를 접근해서 사용할 수 있도록 Class클래스의 forName메소드를 이용해서 핵심클래스를 로딩하는 작업

```java
Class.forName("DBMS드라이버의 핵심 클래스명")
    		-> 패키지명까지 명시
```

- 오라클 : oracle.jdbc.driver.oracleDriver
- mySQL : com.mysql.jdbc.Driver

#### 2. DBMS에 연결하기

=> DriverManager클래스의 getConnection메소드를 통해 작업

1. static메소드이므로 클래스이름으로 액세스

2. throws SQLException하고 있고 SQLException은 RuntimException의 하위가 아니므로 try-catch를 이용해서 exception에 대한 처리를 해야 한다.

3. 매개변수

   * url : DBMS내부에서 인식할 연결문자열( 어떤 DBMS를 쓰냐에 따라 달라진다)

   ```oracle
   [오라클]
   jdbc:oracle:thin:@70.12.115.65:1521:xe
   ---------------  ------------ ---- ---
   오라클에서사용하는 DBMS가 설치  port  서비스명
   프로코톨	      되어있는pc의
   					ip
   					
   ex)jdbc:oracle:thin@127.0.0.1:1521:xe
   			 	  ----------
   			 	  localhost와 동일 - 로컬에 연결
   ```

   ```mysql
   [mysql]
   jdbc:mysql://ip:port/데이터베이스명(port - 3306)
   ```

   * user : 접속계정
   * password : 접속할 계정의 패스워드

4. 리턴타입

   - 연결정보를 java.sql.Connection타입으로 리턴
   - DriverManager의 getConnection메소드를 이용하면 DBMS에 연결 후 연결정보를 객체로 만들어서 리턴한다.
   - 연결객체의 타입은 java.sql.Connection이지만 어떤 DBMS를 접속했냐에 따라 Connection의 하위 객체가 리턴된다.
   - 내부에서는 접속된 DBMS회사에서 제공하는 라이브러리 속 Connction이 리턴되도록 다형성이 적용되어 있다.

5. 사용방법

   ```java
   Connection con =
       	DriverManager.getConnection(url,user,password)
   ```

   * 어떤 DBMS를 쓰냐에 따라 다르게 리턴되는 Connection객체를 con이라는 참조변수를 통해 접근할 수 있도록 할당

#### 3. SQl을 실행하는 역할을 담당하는 Statement객체 생성

* Statement : 정적 SQL을 실행 , 보안에취약(SQLInjection에 취약)

* PrepareStatement : 동적SQL을 실행, 시큐어코딩에 적합

* CallableStatement : 각 DBMS에 특화된 SQL을 실행
  * ex ) 오라클 : PL-SQL

1. **Statement 객체를 이용**

   * Connection객체에 있는 createStatement메소드를 통해 생성
   * Connection정보를 유지해야 한다.

   ```java
   Statement stmt = con.createStatement()
   ```

   - java.sql.Statement타입이지만 드라이버 파일에 포함된 Statement객체가 리턴

2. **PreparedStatment 객체를 이용**

* 동적 SQL문을 사용해야 하기 때문에

* [ sql이 실행되는 과정은 ]

  => 쿼리문을 읽고 분석 - 컴파일 - 실행

* Statement는 위의 단계를 모두 반복해서 실행하고 작업하지만 PreparedStatement는 한 번 실행하고 캐쉬메모리에 저장하고 캐쉬에서 읽어서 작업

* PreparedStatement는 sql문을 실행하는 방식이 sql문을 미리 파싱한 후 동적으로 바인딩해서 작업해야 하는 값들만 나중에 연결해서 인식시키고 실행한다.

  1. sql문을 작성할 때 외부에서 입력받아서 처리해야 하는 부분을 ? 로 정의한다.
  2. sql문을미리 파싱해야 하므로 실행할 때 sql을 전달하지 않고 PreparedStatement 객체를 생성할 때 sql문을 전달한다.

  ```java
  PreparedStatement ptmt = con.prepareStatement("sql문")
  ```

3. ? 에 값을 셋팅
   * PreparedStatement 객체에 정의되어 있는 setXXXXX메소드를 이용
   * ResultSet과 동일한 방식으로 메소드를 구성
   * 오라클 타입과 매칭되는 setXXXX메소드
     * char, varchar2 => setString(1, "XXXX")
     * number, integer => setInt(1, 0000)
     * 소숫점이 있는 number => setDouble(1, 0.0)
     * date => setDate(1, java.sql.Date객체)

```java
setXXXXX(index, 값)
------  ------- ---
컬럼타입 ?순서  컬럼에 설정할 값
      1부터 시작
```

4. 실행메소드 호출

   * insert, delete, update

   ```java
   int result = ptmt.executeUpdate()
   ```

   * select

   ```java
   ResultSet rs = ptmt.executeQuery()
   ```

   

#### 4. SQL 실행

1. Statement 이용 // 정적 sql - 값을 일일이 다 넣어주는 것

   1. executeUpdate : insert, update, delete문을 실행

   ```java
   int 결과값 = stmt.executeUpdate(sql문)
   ---------					 ------
   sql문 실행결과				 insert, delete, update
   (몇개의 row가 변경됐는지 리턴)
   ```

   2. executeQuery : select 문을 실행
      * select문의 실행 결과로 리턴되는 2차원 표를 자바에서 사용할 수 있도록 모델링 해 놓은 객체가 ResultSet이고 executeQuery메소드는 결과로 ResultSet객체를 반환하므로 이 객체를 반환받을 수 있도록 정의한다.

   ``` java
   ResultSet rs = stmt.executeQuery("sql문")
   ```

   

2. PreparedStatement 이용 // 동적 sql - 외부에서 값을 입력받는다

   => 3번 참조

   

#### 5. 결과값 처리

1. insert, delete, update 모두 동일

   => int로 결과를 리턴하므로 결과값을 출력

2. select

   1. select문의 실행결과로 반환되는 ResulteSet을 참조할 수 있도록 정의

   ```java
   ResultSet rs = stmt.executeQuery("sql문")
   ```

   2. ResultSet안에서 레코드를 읽어서 처리할 수 있도록 반복문을 이용해서 처리
      * 처음 반환되는 ResultSet에서 Cursor가 레코드에 위치하지 않으므로 Cursor를 ResultSet안의 레코드에 위치할 수 있도록 내부 메소드를 이용해서 처리한다.

   ```java
   while(rs.next()){
   	------------ ㅡ> 다음 레코드로 Cursor를 이동하고 레코드가 존재하면 true를 리턴하고 레코드가 존재하지 					않으면 false를 리턴한다.
   }
   ```

   3. 레코드의 값을 읽는다.

      * 한번에 하나의 컬럼만 읽을 수 있다.

      * ResultSet내부에서 제공되는 getXXXXXX메소드를 이용

        * rs.getXXXXX(1)

          rs.get( 데이터타입 ) ( 테이블에 존재하는 컬럼의 원래 순서가 아니라 *조회된 컬럼의 순서* )

          ​	 							( index - 1부터 시작 )

      * 오라클(DMBS)의 타입과 매칭되는 자바의 타입으로 메소드명이 구성

      * varchar2 or char로 정의된 컬럼값 => *getString*(컬럼의 순서 or 컬럼명)

      * 소숫점 없는 number or integer => *getInt*(컬럼의 순서 or 컬럼명)

      * 소숫점 있는 number => *getDouble*(컬럼의 순서 or 컬럼명)

      * 날짜 데이터 => *getDate*(컬럼의 순서 or 컬럼명)

   ```java
   while(rs.next()){
   	------------ ㅡ> 조회된 테이블의 모든 레코드에 반복 작업 수행
           sysout(rs.getString(1)) => 조회된 레코드의 첫 번째 컬럼 값
           sysout(rs.getgString("ename")) => 조회된 레코드의 컬럼명이 ename인 컬럼의 값
   }
   ```

#### 6. 자원반납

* 자원을 반납하지 않으면 계속 메모리에 할당되어 있는 상태
* ResultSet, Statement, Connection모두 반납해야 한다.
* close메소드를 이용해서 자원해제
* 가장 마지막에 만들어진 객체부터 해제

## 19-12-27 금

### api.util

```java
**** 배열 ****
int[] myrarr = new int[size];

myarr[0] = 100; - 요소 저장
myarr.length - 요소의 갯수
s.o.p(myarr[0])
```

* ArrayList 

  * 동시접속 고려X => 웹에 적합 (StringBuilder와 비슷)

  ```java
  **** ArrayList ****
  ArrayList<Integer> list = new ArrayList<Integer>();
  list.add(10); - O
  list.add("java") - X
  => ArrayList에 Int데이터만 담겠다.
      
  list.add(100); - 요소 저장
  list.size(); - 요소의 갯수
  s.o.p(v.get(0))
  ```

  * collection에 저장할 요소의 타입을 generic형태로 정의한다.

  ```java
  collection클래스<데이터타입>;
  ```

* Vector

  * Vector는 API다 => method로 call로 접근해야 한다!!
  * 동시접속 고려O => 어플리케이션에 적합 (StringBuffer와 비슷)

  ```java
  **** Vector ****
  Vector<Integer> v = new Vector<Integer>();
  v.add(10); - O
  v.add("java") - X
  => Vector에 Int데이터만 담겠다.
      
  v.add(100); - 요소 저장
  v.size(); - 요소의 갯수
  s.o.p(v.get(0))
  ```

  * <> : generic 표현방식
  * 

## 19-12-30 월

* 실질적으로 DBMS와 연동하는 클래스는 DAO 하나 ( Data Access Object)
* ResultSet에서 사용하기 위해 데이터를 가공하고 변환하는 작업이 반드시 필요하다!
  * 배열 : 사이즈를 고정하고 들어가야 하기 때문에 부적합
  * 자료구조 : ArrayList
  * DTO : 타입이 다른 레코드 하나하나를 DTO에 담는다. ( Data Transfer Object )
* 사용자가 보는 화면 = view

