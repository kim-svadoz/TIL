# WEB

>   참고 : https://github.com/gyoogle/tech-interview-for-developer
>
>   전반적인 흐름은 gyoogle님의 깃허브를 참고하였으며, 추가 레퍼런스는 글을 진행하며 알려드릴 것입니다. 파이팅 !

- [브라우저 동작 방법](#브라우저-동작-방법)
- [쿠키 & 세션](#쿠키--세션)
- [HTTP status code](#http-status-code)
- [REST](#rest)
- [웹 서버와 WAS의 차이](#웹-서버와-was의-차이)
- [OAuth](#oauth)
- [Authentication & Authorization](#authentication--authorization)
- [Logging Level](#logging-level)

# 브라우저 동작 방법

---

[위로](#web)

*브라우저가 어떻게 동작하는지 아세요?*

웹서핑을 하다보면 우리는 여러 url을 통해 사이트를 돌아다닌다. 이 url이 입력되었을 때 어떤 과정을 거쳐서 출력되는걸까?

`WEB`의 기본적인 개념이지만 설명하기 어려울 것이다.. 렌더링? 파싱?

브라우저 주소 창에 [http://naver.com](http://naver.com/)을 입력했을 때 어떤 과정을 거쳐서 네이버 페이지가 화면에 보이는 지 알아보자



## 브라우저 주요 기능

-   사용자가 선택한 자원을 서버에 요청하여 브라우저에 표시된다.
-   자원은 html 문서, pdf, image 등 다양한 형태가 있다.
-   자원의 주소는 `url`에 의해 정해진다.

브라우저는 html과 css 명세에 따라 html 파일을 해석해서 표시한다.

이 '명세'는 웹 표준화 기구인 `W3C(World Wide Web Consortium)`에서 정해진다.

>   예 전 브라우저들은 일부만 명세에 따라 구현하고 독자적 방법으로 확장했는데, 그러다보니 심각한 호환성 문제가 발생하여 요즘은 대부분 표준 명세를 따른다.

브라우저가 가진 인터페이스는 비슷비슷한 요소들이 있다. 시간이 지나면서 사용자에게 필요한 서비스들로 서로 모방하며 갖춰지게 된것이다.
(ex. URl를 입력하는 주소 표시줄, 버튼, 북마크, 새로고침 버튼, 홈버튼 등...)



## 브라우저 기본 구조

[![img](https://camo.githubusercontent.com/0e3ac4e617b8eda5046f592c74a1f7e4cbfb82a5c6abe8d5699020002c1227b6/68747470733a2f2f64322e6e617665722e636f6d2f636f6e74656e742f696d616765732f323031352f30362f68656c6c6f776f726c642d35393336312d312e706e67)](https://camo.githubusercontent.com/0e3ac4e617b8eda5046f592c74a1f7e4cbfb82a5c6abe8d5699020002c1227b6/68747470733a2f2f64322e6e617665722e636f6d2f636f6e74656e742f696d616765732f323031352f30362f68656c6c6f776f726c642d35393336312d312e706e67)

-   `사용자 인터페이스`
    -   주소 표시줄, 이전/다음 버튼, 북마크 등 사용자가 활용하는 서비스들 (요청한 페이지를 보여주는 창을 제외한 나머지)부분
-   `브라우저 엔진`
    -   사용자 인터페이스와 렌더링 엔진 사이의 동작 제어
-   `렌더링 엔진`
    -   요청한 콘텐츠 표시 (html 요청이 들어오면 -> html, css 파싱해서 화면에 표시)
-   `통신`
    -   http요청과 같은 네트워크 호출에 사용(플랫폼의 독립적인 인터페이스로 구성되어 있다)
-   `UI 백엔드`
    -   플랫폼에서 명시하지 않은 일반적 인터페이스. 콤보 박스 창 같은 기본적 장치를 그린다.
-   `자바스크립트 해석기`
    -   자바스크립트 코드를 해석하고 실행한다.
-   `자료 저장소`
    -   쿠키 등 모든 자원을 하드 디스크에 저장하는 계층



## 렌더링이란?

웹 분야를 공부하다 보면 **렌더링**이라는 말을 많이 볼 것이다.

렌더링 엔진은 요청 받은 내용을 브라우저 화면에 표시해준다.

기본적으로 html, xml 문서와 미지를 표시할 수 있다.

추가로 플러그인이나 브라우저 확장 기능으로 pdf 등 다른 유형도 표시가 가능하다
(추가로 확장이 필요한 유형은 바로 뜨지 않고 팝업으로 확장 여부를 묻는 것을 볼 수 있을 것이다.)



### 렌더링 엔진 종류

-   `크롬, 사파리` : **웹킷(Webkit)** 엔진 사용
-   `파이어 폭스` : **게코(Gecko)** 엔진 사용

```bash
# 웹킷 엔진
최초의 리눅스 플랫폼에 동작하기 위한 오픈소스 엔진
(애플이 맥과 윈도우에서 사파리 브라우저를 지원하기 위해 수정을 더했음)
```



### 렌더링 동작 과정

[![img](https://camo.githubusercontent.com/b7b2e4f87463a5508b6cc434e99d58c48b5b8ba1aa8a4d917d9d240fd94aebf1/68747470733a2f2f64322e6e617665722e636f6d2f636f6e74656e742f696d616765732f323031352f30362f68656c6c6f776f726c642d35393336312d322e706e67)](https://camo.githubusercontent.com/b7b2e4f87463a5508b6cc434e99d58c48b5b8ba1aa8a4d917d9d240fd94aebf1/68747470733a2f2f64322e6e617665722e636f6d2f636f6e74656e742f696d616765732f323031352f30362f68656c6c6f776f726c642d35393336312d322e706e67)

1.  먼저 html 문서를 파싱한다.
2.  콘텐츠 트리 내부에서 태그를 모두 `DOM` 노드로 변환한다.
3.  외부 css파일과 함께 포함된 스타일 요소를 파싱한다.
4.  이 스타일 정보와 html 표시 규칙은 렌더 트리라고 부르는 또 다른 트리를 생성한다.
5.  이렇게 생성된 렌더트리는 정해진 순서대로 화면에 표시되는데, 생성 과정이 끝났을 때 배치가 진행되면서 노드가 화면의 정확한 위치에 표시되는 것을 의미한다.
6.  이후에 `UI 백엔드`에서 렌더 트리의 각 노드를 가로지으며 형상을 만드는 그리기 과정이 진행된다.
7.  이러한 과정이 점진적으로 진행되며, 렌더링 엔진은 좀더 빠르게 사용자에게 제공하기 위해  모든 HTML을 파싱할 때까지 기다리지 않고 배치와 그리기 과정을 시작한다. (마치 비동기 처럼?)
8.  전송을 받고 기다리는 동시에 받은 내용을 먼저 화면에 보여준다.
    (우리가 웹페이지에 접속할 때 한꺼번에 뜨지 않고 점점 화면에 나오는 것이 이 때문이다)



## DOM 이란?

>   Document Object Model(문서 객체 모델)

웹페이지 소스를 까보면 `<html>, <body>`와 같은 태그들이 존재한다. 이를 `Javascript`가 활용할 수 있는 객체로 만들면 바로 `문서 객체`가 된다.

모델은 말 그대로 모듈화로 만들었다거나 객체를 인식한다라고 해석하면 된다.

즉, **`DOM은 웹 브라우저가 html페이지를 인식하는 방식`**을 말한다.(트리 구조)



## 파싱과 DOM 트리 구축

파싱은 렌더링 엔진에서 매우 중요한 과정이다.

**`파싱(parsing)`**

문서 파싱은 브라우저가 코드를 이해하고 사용할 수 있는 구조로 변환하는 것이다.

문서를 가지고, **어휘 분석과 구문 분석 과정**을 거쳐 파싱 트리를 구축한다.

어휘 분석기를 통해 언어의 구문 규칙에 따라 문서 구조를 분석한다. 이 과정에서 구문 규칙과 일치하는 지 비교하고, 일치하는 노드만 파싱 트리에 추가시킨다.
(끝까지 규칙이 맞지 않는 부분은 문서가 유효하지 않고 구문 오류가 포함되어 있다는 것 !)

파서 트리가 나왔다고 해서 끝이 아니고, 컴파일의 과정일 뿐, 다시 기계코드 문서로 변환시키는 과정까지 완료되면 최종 결과물이 나온다.

보통 이런 **파서(Parser)**를 생성하는 것은 문법에 대한 규칙 부여 등 복잡하고 최적화 하기 힘드므로, 자동으로 생성해주는 **파서 생성기**를 많이 활용한다.

>   웹킷은 플렉스(flex)나 바이슨(bison)을 이용하여 유용하게 파싱이 가능하다.

우리가 head 태그를 실수로 빠트려도, 파서가 돌면서 오류를 수정해준다.(head 앨리먼트 객체를 암묵적으로 만들어준다.)

결국 이 파싱 과정을 거치면서 서버로부터 받은 문서를 브라우저가 이해하고 쉽게 사용할 수 있는 `DOM 트리구조`로 변환시켜주는 것이다.



## 요약

-   주소창에 url을 입력하고 Enter를 누르면 **서버에 요청이 전송**됨
-   해당 페이지에 존재하는 여러 자원들(text, image 등)이 보내짐
-   이제 브라우저는 해당 자원이 담긴 `html`과 스타일이 담긴 `css`를 `W3C`명세에 따라 해석함
    -   이 역할을 하는 것이 **렌더링 엔진**
    -   렌더링 엔진은 우선 `html` 파싱 과정을 시작함. html 파서가 문서에 존재하는 어휘와 구문을 분석하면서 `DOM 트리`를 구축
    -   다음엔 `css` 파싱 과정 시작. css 파서가 모든 css 정보를 스타일 구조체로 생성
    -   이 2가지를 연결시켜 **렌더 트리**를 만든다. 렌더 트리를 통해 문서가 시각적 요소를 포함한 형태로 구성된 상태
-   화면에 배치를 시작하고 `UI 백엔드`가 노드를 돌며 형상을 그린다.
-   이 때 빠른 브라우저 화면 표시를 위해 '배치와 그리는 과정'은 페이지 정보를 모두 받고 한꺼번에 진행되지 않는다. 자원을 전송받으면, **기다리는 동시에 일부분 먼저 진행하고 화면에 표시한다.**

# 쿠키 & 세션

---

[위로](#web)

>   Cookie & Session

|          |                     Cookie                      |     Session      |
| :------: | :---------------------------------------------: | :--------------: |
| 저장위치 |                     Client                      |      Server      |
| 저장형식 |                      Text                       |      Object      |
| 만료시점 | 쿠키 저장시 설정 (설정 없으면 브라우저 종료 시) | 정확한 시점 모름 |
|  리소스  |               클라이언트의 리소스               |  서버의 리소스   |
| 용량제한 |        한 도메인 당 20개, 한 쿠키당 4KB         |     제한없음     |

## 저장 위치

-   쿠키 : 클라이언트의 웹 브라우저가 지정하는 메모리 or 하드디스크
-   세션 : 서버의 메모리에 저장

## 만료 시점

-   쿠키 : 저장할 때 expires 속성을 정의해 무효화시키면 삭제될 날짜 정할 수 있음
-   세션 : 클라이언트가 로그아웃하거나, 설정 시간동안 반응이 없으면 무효화 되기 때문에 정확한 시점 알 수 없음

## 리소스

-   쿠키 : 클라이언트에 저장되고 클라이언트의 메모리를 사용하기 때문에 서버 자원 사용하지 않음
-   세션 : 세션은 서버에 저장되고, 서버 메모리로 로딩 되기 때문에 세션이 생길 때마다 리소스를 차지함

## 용량 제한

-   쿠키 : 클라이언트도 모르게 접속되는 사이트에 의하여 설정될 수 있기 때문에 쿠키로 인해 문제가 발생하는 걸 막고자 한 도메인당 20개, 하나의 쿠키 당 4KB로 제한해 둠
-   세션 : 클라이언트가 접속하면 서버에 의해 생성되므로 개수나 용량 제한 없음

# HTTP status code

---

[위로](#web)

>   클라우드 환경에서 HTTP API를 통신하는 것이 대부분이다.
>
>   이 때, 응답 상태 코드를 통해 성공/실패 여부를 확인할 수 있으므로 API 문서를 작성할 때 꼭 알아야 하는 것이 **HTTP status code**이다.
>
>   -   10x : 정보 확인
>   -   20x : 통신 성공
>   -   30x : 리다이렉트
>   -   40x : 클라이언트 오류
>   -   50x : 서버 오류

-   200번대 : 통신성공

    | 상태코드 |    이름     |           의미           |
    | :------: | :---------: | :----------------------: |
    |   200    |     OK      |      요청 성공(GET)      |
    |   201    |   Create    |     생성 성공(POST)      |
    |   202    |  Accepted   | 요청 접수O, 리소스 처리X |
    |   204    | No Contents |  요청 성공O, 내용 없음   |

-   300번대 : 리다이렉트

    | 상태코드 |       이름       |             의미              |
    | :------: | :--------------: | :---------------------------: |
    |   300    | Multiple Choice  | 요청 URI에 여러 리소스가 존재 |
    |   301    | Move Permanently |  요청 URI가 새 위치로 옮겨감  |
    |   304    |   Not Modified   |    요청 URI의 내용이 변경X    |

-   400번대 : 클라이언트 오류

    | 상태코드 |        이름        |               의미                |
    | :------: | :----------------: | :-------------------------------: |
    |   400    |    Bad Request     | API에서 정의되지 않은 요청 들어옴 |
    |   401    |    Unauthorized    |             인증 오류             |
    |   403    |     Forbidden      |        권한 밖의 접근 시도        |
    |   404    |     Not Found      |   요청 URI에 대한 리소스 존재X    |
    |   405    | Method Not Allowed | API에서 정의되지 않은 메소드 호출 |
    |   406    |   Not Acceptable   |             처리 불가             |
    |   408    |  Request Timeout   |        요청 대기 시간 초과        |
    |   409    |      Conflict      |               모순                |
    |   429    |  Too Many Request  |        요청 횟수 상한 초과        |

-   500번대 : 서버오류

    | 상태코드 |         이름          |         의미         |
    | :------: | :-------------------: | :------------------: |
    |   500    | Internal Server Error |    서버 내부 오류    |
    |   502    |      Bad Gateway      |   게이트웨이 오류    |
    |   503    |  Service Unavailable  |   서비스 이용 불가   |
    |   504    |    Gateway Timeout    | 게이트웨이 시간 초과 |

# REST

[위로](#web)

>   참고 : https://gmlwjd9405.github.io/2018/09/21/rest-and-restful.html
>
>   REST : 웹 (HTTP)의 장점을 활용한 아키텍쳐
>
>   **REpresentationaal State Transfer**



## REST란

***REST의 정의***

-   자원을 이름(자원의 표현)으로 구분하여 해당 자원의 상태(정보)를 주고 받는 모든 것을 의미.
-   즉, 자원(resource)의 표현(representation)에 의한 상태 전달
    -   **자원(resource)의 표현(representation)**
        -   `자원` : 해당 소프트가 관리하는 모든 것
            ex)  문서, 그림, 데이터, 소프트웨어 자체 등
        -   `자원의 표현` : 그 자원을 표현하기 위한 이름
            ex) DB의 학생 정보가 자원일 때, 'students'를 자원의 표현으로 정한다.
    -   **상태(정보) 전달**
        -   데이터가 요청되어지는 시점에서 자원의 상태(정보)를 전달한다.
        -   `JSON` 혹은 `XML`을 통해 데이터를 주고받는 것이 일반적
-   월드 와이드 웹(www)과 같은 분산 하이퍼 미디어 시스템을 위한 소프트웨어 개발 아키텍처의 한 형식
    -   `REST`는 기본적으로 웹의 기존 기술과 `HTTP` 프로토콜을 그대로 활용하기 때문에 **웹의 장점을 최대한 활용할 수 있는 아키텍처 스타일이다.**
    -   `REST`는 네트워크 상에서 Client와 Server 사이의 통신 방식 중 하나이다.



***REST의 구체적인 개념***

-   **`HTTP URI`**(Uniform Resource Identifer)를 통해 자원(Resource)를 명시하고, **`HTTP Method`**(POST, GET, PUT, DELETE)를 통해 해당 자원에 대한 `CRUD` Operation을 적용하는 것을 의미한다.
    -   즉, REST는 자원 기반의 구조(ROA) 설계의 중심에 Resource가 있고 `HTTP` Method를 통해 Resource를 처리하도록 설계된 아키텍쳐를 의미한다.
    -   웹 사이트의 이미지, 텍스트, DB 내용 등의 모든 자원에 고유한 ID인 `HTTP URI`를 부여한다.
    -   **CRUD Operation**
        -   `CREATE` : 생성(POST)
        -   `READ`: 조회(GET)
        -   `UPDATE` : 수정(PUT)
        -   `DELETE` : 삭제(DELETE)
        -   `HEAD` : header 정보 조회(HEAD)



## REST의 요소

-   `Resource`

    -   http://myweb/users와 같은 URI
    -   모든 자원에 고유한 ID가 존재하고, 이 자원은 Server에 존재한다.
    -   Client는 URI를 이용해 자원을 지정하고 해당 자원의 상태(정보)에 대한 조작을 Server에 요청한다.
    -   모든 것을 `Resource(명사)`로 표현하고, 세부  `Resource`에는 id를 붙인다.

-   `Method` : HTTP Method

    | Method |  의미  | Idempotent |
    | :----: | :----: | :--------: |
    |  POST  | Create |     No     |
    |  GET   | Select |    Yes     |
    |  PUT   | Update |    Yes     |
    | DELETE | Delete |    Yes     |

    `Idempotent` : 한번 수행 하나, 여러 번 수행했을 때 결과가 같나?

-   `Message` : 표현

    -   Client가 자원의 상태(정보)에 대한 조작을 요청하면 Server는 이에 적절한 응답을 한다.

    -   메시지 포맷이 존재

        :`JSON, XML, TEXT, RSS`과 같은 형태가 있다. (최근에는 `JSON`을 많이 사용한다)

        ```json
        HTTP POST, httmp://myweb/users/
        {
        	"users" : {
                "name" : "sunghyun"
            }
        }
        ```

## REST 장단점

### 장점

-   HTTP 프로토콜의 인프라를 그대로 사용하므로 REST API 사용을 위한 별도의 인프라를 구축할 필요가 없다.
-   HTTP 프로토콜의 표준을 최대한 활용하여 여러 추가적인 장점을 함께 가져갈 수 있게 해준다.
-   HTTP 표준 프로토콜에 따르는 모든 플랫폼에서 사용이 가능하다.
-   Hypermedia API의 기본을 충실히 지키면서 범용성을 보장한다.
-   REST API 메시지가 의도하는 바를 명확하게 나타내므로 의도하는 바를 쉽게 파악할 수 있다.
-   여러가지 서비스 디자인에서 생길 수 있는 문제를 최소화한다.
-   서버와 클라이언트의 역활을 명확하게 분리한다.

### 단점

-   표준이 존재하지 않는다.
-   사용할 수 있는 메소드가 4가지 밖에 없다.
    -   HTTP Method 형태가 제한적이다.
-   브라우저를 통해 테스트할 일이 많은 서비스라면 쉽게 고칠 수 있는 URL보다 Header 값이 왠지 더 어렵게 느껴진다.
-   구형 브라우저가 아직 제대로 지원해주지 못하는 부분이 존재한다.
    -   `PUT, DELETE`를 사용하지 못하는 점
    -   `pushState`를 지원하지 않는점



## REST가 필요한 이유

-   애플리케이션 분리 및 통합
-   다양한 클라이언트의 등장
-   최근의 서버 프로그램은 다양한 브라우저와 안드로이드폰, 아이폰과 같은 모바일 디바이스에서도 통신할수 있어야 한다.
-   이러한 멀티 플랫폼에 대한 지원을 위해 서비스 자원에 대한 아키텍처를 세우고 이용하는 방법을 모색한 결과 REST에 관심을 가지게 되었다.

## REST 특징

-   `Uniform Interface(인터페이스 일관성)`

    -   URI로 지정한 Resource에 대한 조작을 통일되고 한정적인 인터페이스로 수행한다.

    -   HTTP 표준만 맞는다면, 어떤 기술도 가능한 Interface 스타일이고, 특정 언어나 기술에 종속되지 않는다.

        ex ) REST API 정의를 `HTTP + JSON`으로 하였다면, C, JAVA, Python, IOS 플랫폼 등 특정 언어나 기술에 종속받지 않고, 모든 플랫폼에 사용이 가능한 **Lossely Coupling 구조**

    -   포함

        -   Self-Descriptive Messages
            -   API 메시지만 보고, API를 이해할 수 있는 구조
                (Resocure, Method를 이용해 무슨 행위를 하는지 직관적으로 이해할 수 있다)
        -   **`HATEOS`(Hyptermdediaa As The Engine Of Applicaation State)**
            -   Applicaation의 상태(State)는 Hyperliink를 통해 전이되어야 함
            -   서버는 현재 이용 가능한 다른 작업에 대한 하이퍼링크를 포함하여 응답해야 함.
        -   Resource Identification In Request
        -   Resource Manipulation Through Representations

-   `Statelessness(무상태)`

    -   즉, HTTP Session과 같은 컨텍스트 저장소에 **상태 정보 저장 안함**
    -   Client의 context를 Server에 저장하지 않는다.
    -   **Request만 Message로 처리**하면 되고, 세션과 쿠키와 같은 context 정보를 신경쓰지 않아도 되므로, **구현이 단순하다**
    -   따라서, REST API 실행 중 실패가 발생한 경우, `Transaction` 복구를 위해 기존의 상태를 저장할 필요가 없다.(**POST Method 제외**)
    -   Server는 각각의 요청을 완전히 별개의 것으로 인식하고 처리한다.
        -   각 API 서버는 Client의 요청만을 단순 처리한다.
        -   즉, 이전 요청이 다음 요청의 처리에 연관되어서는 안된다
        -   물론 이전 요청이 DB를 수정하여 DB에 의해 바뀌는 것은 허용한다.
        -   Server의 처리 방식에 일관성을 부여하고 부담이 줄어들며, 서비스의 자유도가 높아진다.

-   `Resource 지향 아키텍쳐` (**`ROA` : Resoucre Oriented Architecture**)

    -   Resource 기반의 복수형 명사 형태의 정의를 규정한다.

-   `Clinet-Server Architecture`

    -   자원이 있는쪽이 Server, 자원을 요청하는 쪽이 Client가 된다
        -   **REST server** : API를 제공하고 비즈니스 로직 처리 및 저장을 책임진다.
        -   Client : 사용자 인증이나 context(세션, 로그인 정보) 등을 직접 관리하고 책임진다.
    -   서로 간 의존성이 줄어든다.

-   `Cache Ability(캐시 처리 가능)`

    -   웹 표준 HTTP 프로토콜을 그대로 사용하므로 웹에서 사용하는 기존의 인프라를 그대로 활용할 수 있다.
        -   즉, HTTP가 가진 가장 강력한 특징 중 하나의 캐싱 기능을 적용할 수 있다.
        -   HTTP 프로토콜 표준에서 사용하는 Last-Modified 태그나 E-Tag를 이용하면 캐싱 구현이 가능하다.
    -   대랑의 요청을 효율적으로 처리하기 위해 캐시가 요구된다.
    -   캐시 사용을 통해 응답시간이 빨라지고 `REST server` 트랜잭션이 발생하지 않기 때문에 전체 응답시간, 성능, 서버의 자원 이용률을 향상시킬 수 있다.

-   `Layered System(계층화)`

    -   Client는 REST API Server만 호출한다.
    -   REST Server는 다중 계층으로 구성될 수 있다.
        -   **API Server는 순수 비즈니스 로직을 수행하고 그 앞단에 보안, 로드밸런싱, 암호화, 사용자 인증 등을 추가하여 구조상의 유연성을 줄 수 있다.**
        -   또한 로드밸런싱, 공유 캐시 등을 통해 확장성과 보안성을 향상시킬 수 있다.
    -   `PROXY`, `게이트웨이` 같은 네트워크 기반의 중간 매체를 사용할 수 있다.

-   `Code On Demand(Opitonal)`

    -   Server로부터 스크립트를 받아서 Client에서 실행한다.
    -   반드시 충족할 필요는 없다.

# REST API

>   참고 : https://gmlwjd9405.github.io/2018/09/21/rest-and-restful.html
>
>   그러면 REST API는 무엇인가?
>
>   -   API(Application Programming Interface)
>
>       : 데이터와 기능의 집합을 제공하여 컴퓨터 프로그램간 상호작용을 촉진하며, 서로 정보를 교환가능 하도록 하는 것
>
>   -   REST API
>
>       -   REST 기반으로 서비스API를 구현한 것
>       -   최근 OpenAPI, 마이크로 서비스(하나의 큰 애플리케이션을 여러 개의 작은 애플리케이션으로 쪼개어 변경과 조합이 가능하도록 만든 아키텍쳐) 등을 제공하는 업체 대부분은 REST API를 제공한다.

## REST API 특징

-   사내 시스템들도 REST 기반으로 시스템을 분산해 확장성과 재사용성을 높여 유지보수 및 운용을 편리하게 할 수 있다.
-   REST는 HTTP 표준을 기반으로 구현하므로, HTTP를 지원하는 프로그램 언어로 클라이언트, 서버를 구현할 수 있다.
-   즉, REST API를 제작하면 델파이 클라이언트 뿐만 아니라 자바, C#, 웹 등을 이용해 클라이언트를 제작할 수 있다.



## REST API 설계 기본 규칙

**참고 리소스 원형**

>   -   도큐먼트 : 객체 인스턴스나 데이터베이스 레코드와 유사한 개념
>   -   컬렉션 : 서버에서 관리하는 디렉터리라는 리소스
>   -   스토어 : 클라이언트에서 관리하는 리소스 저장소

1.  URI는 정보의 자원을 표현해야 한다.
    1.  resource는 동사보다는 명사를, 대문자보다는 소문자를 사용해야 한다.
    2.  resource의 도큐먼트 이름으로는 단수 명사를 사용해야 한다.
    3.  resource의 컬렉션 이름으로는 복수 명사를 사용해야 한다.
    4.  resource의 스토어 이름으로는 복수 명사를 사용해야 한다.
        ex) `GET/Member/1` -> `GET/members/1`
2.  자원에 대한 행위는 **HTTP Method**(`GET, PUT, POST, DELETE` 등)으로 표현한다.
    1.  URI에 HTTP Method가 들어가면 안된다.
        ex) `GET/members/delete/1` -> `DELETE/members/1`
    2.  URI에 행위에 대한 동사 표현이 들어가면 안된다.(즉, CRUD 기능을 나타내는 것은 URI에 사용하지 않는다.)
        ex) `GET/members/show/1` -> `GET/members/1`
        ex) `GET/members/insert/2` -> `POST/members/1`
    3.  경로 부분 중 변하는 부분은 유일한 값으로 대체한다.(즉, `:id`는 하나의 특정 resource를 나타내는 고유 값이다.)
        ex) student를 생성하는 route : `POST/students`
        ex) `id=12`인 student를 삭제하는 route : `DELETE/students/12`



## REST API 설계 규칙

1.  슬래시 구분자(`/`)는 계층 관계를 나타내는데 사용한다.
    ex) `http://restapi.example.com/houses/apartments`

2.  URI 마지막 문자로 슬래스(`/`)를 포함하지 않는다.

    -   URI에 포함되는 모든 글자는 리소스의 유일한 식별자로 사용되어야 하며 URI가 다르다는 것은 리소스가 다르다는 것이고, 역으로 리소스가 다르면 URI도 달라져야 한다.
    -   REST API는 분명한 URI를 만들어 통신을 해야 하기 때문에 혼동을 주지 않도록 URI 경로의 마지막에는 슬래스(`/`)를 사용하지 않는다.
        ex) `http://restapi.example.com/houses/apartments/`(X)

3.  하이픈(`-`)은 URI 가독성을 높이는 데 사용

    -   불가피하게 긴 URI경로를 사용하게 된다면 하이픈을 사용해 가독성을 높인다.

4.  밑줄(`_`)은 URI에 사용하지 않는다.

    -   밑줄은 보기 어렵거나 밑줄 때문에 문자가 가려지기도 하므로 가독성을 위해 밑줄은 사용하지 않는다.

5.  URI 경로에는 소문자가 적합한다.

    -   URI 경로에 대문자 사용은 피하도록 한다.

    -   `RFC 3986`(URI 문법 형식)은 URI 스키마와 호스트를 제외하고는 대소문자를 구별하도록 규정하기 때문

6.  파일확장자는 URI에 포함하지 않는다.

    -   REST API에서는 메시지 바디 내용의 포맷을 나타내기 위한 파일 확장자를 URI안에 포함시키지 않는다.
    -   Accept header를 사용한다.
    -   ex) `http:/restapi.example.com/members/soccer/345/photo.jpg` (X)
    -   ex) `GET/members/soccer/345 photo HTTP/1.1 Host: restapi.exaple.com Accept : image/jpg`(O)

7.  리소스 간에는 연관 관계가 있는 경우

    -   `/리소스명/리소스 ID/관계가 있는 다른  리소스명`
    -   ex) `GET : /users/{userid}/devices` (일반적으로 소유 'has'의 관계를 표현할 때)



## REST API 설계 예시

![img](https://gmlwjd9405.github.io/images/network/restapi-example.png)

>   **참고 응답상태 코드**
>
>   -   1xx : 전송 프로토콜 수준의 정보 교환
>   -   2xx : 클라이언트 요청이 성공적으로 수행됨
>   -   3xx : 클라이언트는 요청을 완료하기 위해 추가적인 행동을 취해야 함
>   -   4xx : 클라이언트의 잘못된 요청
>   -   5xx : 서버쪽 오류로 인한 상태코드



# RESTful

>   참고 : https://gmlwjd9405.github.io/2018/09/21/rest-and-restful.html

## RESTful 이란

-   `RESTful`은 일반적으로 REST라는 아키텍쳐를 구현하는 웹 서비스를 나타내기 위해 사용되는 용어이다.
    -   'REST API'를 제공하는 웹 서비스를 'RESTful'하다고 할 수 있다.
-   `RESTful`은 REST를 REST답게 쓰기 위한 방법으로, 누군가가 공식적으로 발표한 것이 아니다
    -   즉, REST 원리를 따르는 시스템은 RESTful이란 용어로 지칭된다.



## RESTful 의 목적

-   이해하기 쉽고 사용하기 쉬운 REST API를 만드는 것
-   RESTful한 API를 구현하는 근본적인 목적이 성능 향상에 있는 것이 아니라 일관적인 컨벤션을 통한 API의 이해도 및 호환성을 높이는 것이 주 동기이니, 성능이 중요한 상황에서는 굳이 RESTful한 API를 구현할 필요는 없다.



## RESTful 하지 못한 경우

-   ex) `CRUD` 기능을 모두 POST로만 처리하는 API
-   ex) route 에 resource, id, 외의 정보가 들어가는 경우 (`/students/updateName`)

# 웹 서버와 WAS의 차이

---

웹 서버와 was의 차이점은 무엇일까? 서버 개발에 있어서 기초적인 개념이다.

먼저, 정적 페이지와 동적 페이지를 알아보자

[![img](https://camo.githubusercontent.com/dac3eae0d749b45864d492ab82d046fbbfdf86891dc69dda6aaffdfabe9b5ea9/68747470733a2f2f676d6c776a64393430352e6769746875622e696f2f696d616765732f7765622f7374617469632d76732d64796e616d69632e706e67)](https://camo.githubusercontent.com/dac3eae0d749b45864d492ab82d046fbbfdf86891dc69dda6aaffdfabe9b5ea9/68747470733a2f2f676d6c776a64393430352e6769746875622e696f2f696d616765732f7765622f7374617469632d76732d64796e616d69632e706e67)

- **Static Pages**

>   바뀌지 않는 페이지

웹 서버는 파일 경로 이름을 받고, 경로와 일치하는 file contents를 반환함

항상 동일한 페이지를 반환함

```bash
image, html, css, javascript 파일과 같이 컴퓨터에 저장된 파일들
```

-   **Dynamic Pages**

>   인자에 따라 바뀌는 페이지

인자의 내용에 맞게 동적인 contents를 반환함

웹 서버에 의해 실행되는 프로그램을 통해 만들어진 결과물임 (Servlet : was 위에서 돌아가는 자바 프로그램)

개발자는 Servlet에 doGet() 메소드를 구현함



## 웹 서버와 WAS의 차이



[![img](https://camo.githubusercontent.com/4035eaec5cc3291e03f4468a9e039af0087b5543b5fbaa3750fc6df9fa6c99bf/68747470733a2f2f676d6c776a64393430352e6769746875622e696f2f696d616765732f7765622f7765627365727665722d76732d776173312e706e67)](https://camo.githubusercontent.com/4035eaec5cc3291e03f4468a9e039af0087b5543b5fbaa3750fc6df9fa6c99bf/68747470733a2f2f676d6c776a64393430352e6769746875622e696f2f696d616765732f7765622f7765627365727665722d76732d776173312e706e67)

### 웹 서버

개념에 있어서 하드웨어와 소프트웨어로 구분된다.

**하드웨어** : Web 서버가 설치되어 있는 컴퓨터

**소프트웨어** : 웹 브라우저 클라이언트로부터 HTTP 요청을 받고, 정적인 컨텐츠(html, css 등)를 제공하는 컴퓨터 프로그램



#### 웹 서버 기능

>   Http 프로토콜을 기반으로, 클라이언트의 요청을 서비스하는 기능을 담당

요청에 맞게 두가지 기능 중 선택해서 제공해야 한다.

-   정적 컨텐츠 제공

    >   WAS를 거치지 않고 바로 자원 제공

-   동적 컨텐츠 제공을 위한 요청 전달

    >   클라이언트 요청을 WAS에 보내고, WAS에서 처리한 결과를 클라이언트에게 전달



**웹 서버 종류** :`Apache, Nginx, IIS` 등



### WAS

Web Application Server의 약자

>   DB 조회 및 다양한 로직 처리 요구시 **동적인 컨텐츠를 제공**하기 위해 만들어진 애플리케이션 서버

HTTP를 통해 애플리케이션을 수행해주는 미들웨어다.

**WAS는 웹 컨테이너 혹은 서블릿 컨테이너**라고도 불림

(컨테이너란 JSP, Servlet을 실행시킬 수 있는 소프트웨어. 즉, WAS는 JSP, Servlet 구동 환경을 제공해줌)



#### 역할

WAS = 웹 서버 + 웹 컨테이너

웹 서버의 기능들을 구조적으로 분리하여 처리하는 역할

>   보안, 스레드 처리, 분산 트랜잭션 등 분산 환경에서 사용됨 ( 주로 DB 서버와 함께 사용 )



#### WAS 주요 기능

1.프로그램 실행 환경 및 DB 접속 기능 제공

2.여러 트랜잭션 관리 기능

3.업무 처리하는 비즈니스 로직 수행



**WAS 종류** : `Tomcat, JBoss` 등



## 그럼, 둘을 구분하는 이유는?



### 웹 서버가 필요한 이유

웹 서버에서는 정적 컨텐츠만 처리하도록 기능 분배를 해서 서버 부담을 줄이는 것

```bash
클라이언트가 이미지 파일(정적 컨텐츠)를 보낼 때..

웹 문서(html 문서)가 클라이언트로 보내질 때 이미지 파일과 같은 정적 파일은 함께 보내지지 않음
먼저 html 문서를 받고, 이에 필요한 이미지 파일들을 다시 서버로 요청해서 받아오는 것

따라서 웹 서버를 통해서 정적인 파일을 애플리케이션 서버까지 가지 않고 앞단에 빠르게 보낼 수 있음!
```



### WAS가 필요한 이유

WAS를 통해 요청에 맞는 데이터를 DB에서 가져와 비즈니스 로직에 맞게 그때마다 결과를 만들고 제공하면서 자원을 효율적으로 사용할 수 있음

```bash
동적인 컨텐츠를 제공해야 할때..

웹 서버만으로는 사용자가 원하는 요청에 대한 결과값을 모두 미리 만들어놓고 서비스하기에는 자원이 절대적으로 부족함

따라서 WAS를 통해 요청이 들어올 때마다 DB와 비즈니스 로직을 통해 결과물을 만들어 제공!
```



### 그러면 WAS로 웹 서버 역할까지 다 처리할 수 있는거 아닌가요?

```bash
WAS는 DB 조회, 다양한 로직을 처리하는 데 집중해야 함. 따라서 단순한 정적 컨텐츠는 웹 서버에게 맡기며 기능을 분리시켜 서버 부하를 방지하는 것

만약 WAS가 정적 컨텐츠 요청까지 처리하면, 부하가 커지고 동적 컨텐츠 처리가 지연되면서 수행 속도가 느려짐 → 페이지 노출 시간 늘어나는 문제 발생
```



또한, 여러 대의 WAS를 연결지어 사용이 가능하다.

웹 서버를 앞 단에 두고, WAS에 오류가 발생하면 사용자가 이용하지 못하게 막아둔 뒤 재시작하여 해결할 수 있음 (사용자는 오류를 느끼지 못하고 이용 가능)



자원 이용의 효율성 및 장애 극복, 배포 및 유지 보수의 편의성 때문에 웹 서버와 WAS를 분리해서 사용하는 것이다.



### 가장 효율적인 방법

>   웹 서버를 WAS 앞에 두고, 필요한 WAS들을 웹 서버에 플러그인 형태로 설정하면 효율적인 분산 처리가 가능함



[![img](https://camo.githubusercontent.com/0f95749bc0bb4c75cc8f2dd2676bf9fc23ee6bfe62aa440877140ed38253bc44/68747470733a2f2f676d6c776a64393430352e6769746875622e696f2f696d616765732f7765622f7765622d736572766963652d6172636869746563747572652e706e67)](https://camo.githubusercontent.com/0f95749bc0bb4c75cc8f2dd2676bf9fc23ee6bfe62aa440877140ed38253bc44/68747470733a2f2f676d6c776a64393430352e6769746875622e696f2f696d616765732f7765622f7765622d736572766963652d6172636869746563747572652e706e67)



클라이언트의 요청을 먼저 웹 서버가 받은 다음, WAS에게 보내 관련된 Servlet을 메모리에 올림

WAS는 web.xml을 참조해 해당 Servlet에 대한 스레드를 생성 (스레드 풀 이용)

이때 HttpServletRequest와 HttpServletResponse 객체를 생성해 Servlet에게 전달

>   스레드는 Servlet의 service() 메소드를 호출
>
>   service() 메소드는 요청에 맞게 doGet()이나 doPost() 메소드를 호출

doGet()이나 doPost() 메소드는 인자에 맞게 생성된 적절한 동적 페이지를 Response 객체에 담아 WAS에 전달

WAS는 Response 객체를 HttpResponse 형태로 바꿔 웹 서버로 전달

생성된 스레드 종료하고, HttpServletRequest와 HttpServletResponse 객체 제거

# OAuth

---

[위로](#web)

> Open Authentification

인터넷 사용자들이 비밀번호를 제공하지 않고, 다른 웹사이트 상의 자신들의 정보에 대해 웹사이트나 애플리케이션의 접근 권한을 부여할 수 있는 개방형 표준 방법

이러한 메커니즘은 구글, 페이스북, 트위터 등이 사용하고 있으며 타사 애플리케이션 및 웹사이트의 계정에 대한 정보를 공유할 수 있도록 허용해준다.

## 용어

- `사용자` : 계정을 가지고 있는 개인
- `소비자` : `OAuth`를 사용해 서비스 제공자에게 접근하는 웹사이트 or 애플리케이션
- `서비스 제공자` : `OAuth`를 통해 접근을 지원하는 웹 애플리케이션
- `소비자 비밀번호` : 서비스 제공자에서 소비자가 자신임을 인증하기 위한 키
- `요청 토큰` : 소비자가 사용자에게 접근권한을 인증받기 위해 필요한 정보가 담겨있음
- `접근 토큰` : 인증 후에 사용자가 서비스 제공자가 아닌 소비자를 통해 보호 자원에 접근하기 위한 키 값



## 인증 과정

> 소비자 <-> 서비스 제공자

1. 소비자가 서비스 제공자에게 요청토큰을 요청한다.
2. 서비스 제공자가 소비자에게 요청토큰을 발급해준다.
3. 소비자가 사용자를 서비스제공자로 이동시킨다. 여기서 사용자 인증이 수행된다.
4. 서비스 제공자가 사용자를 소비자로 이동시킨다.
5. 소비자가 접근토큰을 요청한다.
6. 서비스제공자가 접근토큰을 발급한다.
7. 발급된 접근토큰을 이용해서 소비자에서 사용자 정보에 접근한다.

# Authentication & Authorization

[위로](#web)

> 인증방식

## API KEY

> 서비스들이 거대해짐에 따라 기능들을 분리하기 시작했는데 이를 위해 Module이나 Application들간의 공유와 독립성을 보장하기 위한 기능들이 등장하기 시작했다. 그 중 제일 먼저 등장하고 가장 널리 보편적으로 쓰이는 기술이 **API Key**이다.

### 동작방식

1. 사용자는 `API Key`를 발급받는다.
   (발급 받는 과정은 서비스들마다 다르다. 예를들어 공공기관 API 같은 경우에는 신청에 필요한 양식을 제출하면 관리자가 확인후 Key를 발급해준다.)
2. 해당 API를 사용하기 위해 Key와 함께 요청을 보낸다.
3. Application은 요청이 오면 Key를 통해 User정보를 확인하여 누구의 Key인지 권한이 무엇인지를 확인한다.
4. 해당 Key의 인증과 인가에 따라 데이터를 사용자에게 반환한다.

### 문제점

`API Key`를 사용자에게 직접 발급하고 해당 Key를 통해 통신을 하기 때문에 통신구간이 암호화가 잘 되어 있더라도 Key가 유출될 경우에 대비하기 힘들다.

그렇기 때문에 주기적으로 Key를 업데이트 해야하기 때문에 번거롭고, 예기치 못한 상황(한쪽만 업데이트가 되어 서로 매치가 안된다는 등)이 발생할 수 있다.

또한, Key 한 가지로 정보를 제어하기 때문에 보안문제가 발생하기 쉬운 편인다.



## OAuth2

> API Key의 단점을 메꾸기 위해 동작한 방식이다.
>
> 대표적으로 페이스북, 트위터 등 SNS 로그인기능에서 쉽게 볼 수 있다.
>
> 요청하고 받는 단순한 방식이 아니라 인증하는 부분이 추가되어 독립적으로 세분화가 이루어진다.

### 동작방식

1. 사용자가 Application의 기능을 사용하기 위한 요청을 보낸다.
   (로그인 기능, 특정 정보 열람 등 다양한 곳에서 쓰일 수 있다. 여기에서는 로그인으로 통합하여 설명한다.)

2. Application은 해당 사용자가 로그인이 되어 있는지를 확인한다. 로그인이 되어있지 않다면 다음 단계로 넘어간다.

3. Application은 사용자가 로그인 되어 있지 않으면 사용자를 인증서버로 **Redirection**한다.

4. 간접적으로 `Authorize` 요청을 받은 인증서버는 해당 사용자가 회원인지 그리고 인증서버에 로그인 되어있는지를 확인한다.

5. 인증을 거쳤으면 사용자가 최초의 요청에 대한 권한이 있는지를 확인한다.
   이러한 과정을 **Grant**라고 하는데 대체적으로 인증서버는 사용자의 의지를 확인하는 Grant 처리를 하게 되고, 각 Application은 다시 권한을 관리할 수도 있다.
   이 과정에서 사용자의 Grant가 확인이 되지 않으면 다시 사용자에게 Grant 요청을 보낸다.

   ```bash
   # Grant란?
   인가와는 다른 개념이다.
   인가는 서비스 제공자 입장에서 사용자의 권한을 보는 것이지만
   Grant는 사용자가 자신의 인증정보(보통 이름, 이메일 등)를 Application에 넘길지 말지 결정하는 과정이다.
   ```

6. 사용자가 Grant 요청을 받게 되면 사용자는 해당 인증정보에 대한 허가를 내려준다.
   해당 요청을 통해 다시 인증서버에 인가 처리를 위해 요청을 보내게 된다.

7. 인증 서버에서 인증과 인가에 대한 과정이 모두 완료되면, Application에게 인가코드를 전달해준다. 인증서버는 해당 인가코드를 자신의 저장소에 저장을해둔다.
   해당 코드는 보안을 위해 매우 짧근 기간동안만 유효하다.

8. 인가 코드는 짧은 시간 유지되기 때문에 이제 Application은 해당 코드를 **Request Token**으로 사용하여 인증서버에 요청을 보내게 된다.

9. 해당 Request Token을 받은 인증서버는 자신의 저장소에 저장한 코드(7번 과정)과 일치하는지를 확인하고 긴 유효기간을 가지고 실제 리소스 접근에 사용하게 될 **Access Token**을 Application에게 전달한다.

10. 이제 Application은 Access Token을 통해 업무를 처리할 수 있다. 해당 Access Token을 통해 리소스 서버(인증서버와는 다름)에 요청을 하게 된다.
    하지만 이 과정에서도 리소스 서버는 바로 데이터를 전달하는 것이 아닌 인증서버에 연결하여 해당 토큰이 유효한지 확인을 거치게 된다.

    해당 토큰이 유효하다면 사용자는 드디어 요청한 정보를 받을 수 있다.



### 문제점

기존 API Key 방식에 비해 좀 더 복잡한 구조를 가진다.

물론 많은 부분이 개선되었지만 통신에 사용하는 `Token`은 무의미한 문자열을 가지고 기본적으로 정해진 규칙없이 발행되기 때문에 증명확인이 필요하다.

그렇기 때문에 인증서버에 어떤식이든 DBMS 접근이든 다른 API를 활용하여 접근하는 등의 유효성 확인 작업이 필요하다는 공증 여부 문제가 있다.

이러한 공증여부 문제 뿐만 아니라 유효기간 문제도 있다.



## JWT

> JSON Web Token
>
> 웹 표준(RFC 7519)으로서 두 개체에서 JSON 객체를 사용하여 가볍고 자가수용적인 방식으로 정보를 안정성 있게 전달해주는, **인증 흐름의 규약이 아닌 Token 작성에 대한 규약**이다.
>
> 기본적인 Access Token은 의미가 없는 문자열로 이루어져 있어 Token의 진위나 유효성을 매번 확인해야 하는것임에 반하여, `JWT`는 인증여부 확인을 위한 값, 유효성 검증을 위한 값 그리고 인증 정보 자체를 담고 있기 때문에 인증서버에 묻지 않고도 사용할 수 있다.

### 문제점

서버에 직접 연결하여 인증을 확인하지 않아도 되기 때문에 생기는 장점들이 많다.

하지만 토큰 자체가 인증 정보를 가지고 있기 때문에 민감한 정보는 인증서버에 다시 접속하는 과정이 필요하다.

# Logging Level

---

[위로](#web)

보통 **`log4j`** 라이브러리를 활용하며 크게 `ERROR`, `WARN`, `INFO`, `DEBUG`로 나누어 작성한다.

- `ERROR`
  - 에러 로그는, 프로그램 동작에 큰 문제가 발생했다는 것으로 즉시 문제를 조사해야 하는 것
    ex) DB를 사용할 수 없는 상태, 중요 에러가 나오는 상황
- `WARN`
  - 주의해야 하지만 프로세스는 계속 진행되는 상태, 하지만 `WARN`에서도 2가지의 부분에선 종료가 일어난다.
    - 명확한 문제 : 현재 데이터를 사용 불가, 캐시값 사용 등
    - 잠재적 문제 : 개발 모드로 프로그램 시작, 관리자 콘솔 비밀번호가 보호되지 않고 접속 등
- `INFO`
  - 중요한 비즈니스 프로세스가 시작될 때와 종료될 때를 알려주는 로그
    ex) `A가 B를 실행했어요~`
- `DEBUG`
  - 개발자가 기록할 가치가 있는 정보를 남기기 위해 사용하는 레벨