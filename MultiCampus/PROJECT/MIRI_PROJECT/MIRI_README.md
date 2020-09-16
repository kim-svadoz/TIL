# :fist_oncoming: MIRI - CONNECTED CAR PROJECT

> 안드로이드를 통한 집과 차를 연결하는 양방향 커넥티드카 시스템 프로젝트
>
> *집이나 차에 도착하기 전에 필요한 동작들을 미리 제공한다하여 **MIRI***

# 1. 기획

> **주제** : 기존 단방향 커넥티드 서비스를 보완하기 위해 스마트 디바이스를 활용하여 커넥티드카와 홈 IoT 모두를 제어할 수 있는 양방향 플랫폼 구축
>
> **기획의도** : 
>
> - 대부분의 차량용 인포테인먼트 서비스를 보완하여 더 많은 소비를 이끌어내기 위함
> - 단순히 차량에만 한정된 단방향 제어를 보완한 시스템 구성
> - 스마트 홈 구축과 차량 연결 서비스를 제공하여 고객의 삶의 질 향상
>
> **지원기능**
>
> - *Home*
>   - CAN통신을 활용하여 집의 환경 정보를 수집
>   - 안드로이드폰을 이용한 가전제품 원격 제어
>   - 인체감지 센서와 홈 CCTV를 활용하여 불범침입을 감지하고 스마트폰으로 사용자에게 알려준다.
> - *Car*
>   - CAN통신을 활용하여 차량 환경정보 수집
>   - 안드로이드폰을 이용하여 시동, 공조장치, 네이게이션 등을 원격으로 설정하고 인포테인먼트에 표시
>   - 인포테인먼트에서 차량의 방향지시등, 비상등을 제어하고, 공조장치, 시트각도를 설정하고 화면에 표시
>   - GPS Sensor를 이용하여 현재 차량위치를 수집하고 필요시 호출
>   - 개인별 차량의 설정상태를 DB에 저장하고 필요 시 불러올 수 있다.
>   - GPIO와 센서를 활용해 집의 환경정보를 인포테인먼트, 스마트폰에게 전달
> - 음성인식
>   - 앞에서 제시한 차량, 홈 제어 서비스를 음성으로 제어
>
> *차량, 집을 공동으로 소유할 수 있도록 서버와 DB를 설계하였다.*

# 2. 요구사항

## 2.1 개발 기술 환경

- Arduino(ATMega328)
- Android 5.0이상에서 동작
- Spring 4 이상

## 2.2 기능적 요구사항

**<집>**

- 스마트폰을 이용한 원격 제어 : 가전제품 전원관리 및 온도제어
- 집 환경 정보 수집 : 수집 데이터를 기반으로 실시간 환경 제어
- 홈 CCTV 구축 : 웹 페이지를 이용하여 실시간 스트리밍

**<자동차>**

- 스마토픈올 이용한 원격제어 : 네이게이션, 시동, 공조장치, 도어락 제어
- 차량 정보 수집 : 실내 온도 제어를 위한 차량 내부 온,습도 / 소모품(연료, 배터리) 상태
- 현재 차량 위치 수집

**<안드로이드>**

- 공동소유관리
- 집 - 안드로이드 - 자동차의 *양방향통신*
- 음성인식을 통한 통합 제어

## 2.3 비기능적 요구사항

**<직관적이고 간결한 사용자 환경>**

- 사용자는 개인의 스마트폰 앱을 이용하여 서비스 이용
- 사용자가 앱을 사용하는 것에 최소한의 클릭과 낮은 지연 시간을 요구하며 서비스 이용에 대한 표현 문구가 어렵지 않아야 한다.
- 간편하고 직관적인 UI 셋팅
- 연령대와 상관없이 누구나 쉽게 배우고 익힐 수 있어야 한다.
- 한국형 웹 콘텐츠 접근성 지침을 준수하여야 한다.

# 3. 시스템 아키텍쳐

![image-20200916133931769](https://user-images.githubusercontent.com/58545240/93296287-dfc81200-f829-11ea-8642-5d2a75ad65fa.png)

![image-20200916134200524](https://user-images.githubusercontent.com/58545240/93296299-e48cc600-f829-11ea-88bc-b93cfc42871b.png)

# 4. 프로젝트 구조

1. **공동소유관리**

![image-20200916134200524](https://user-images.githubusercontent.com/58545240/93296396-0ede8380-f82a-11ea-928c-d7757b9bdac0.png)

- 집과 차량을 공동으로 소유하고 있는 사용자를 구분하기 위해 DB에 정보를 저장하고 차량과 안드로이드에서 토큰값을 사용해서 인증기능을 구현한다.
- 가족 구성원간에는 차량, 홈을 공동소유 하므로 데이터와 명령이 공유됨
- 서로 다른 가족 구성원끼리는 차량, 홈에 접근할 수 없음.

2. **시나리오**

![image-20200916134305025](https://user-images.githubusercontent.com/58545240/93296346-fc644a00-f829-11ea-848e-a25a9b5be832.png)

- 사용자는 스마트폰을 활용해 집과 차량을 제어
- 인포테인먼트를 활용해 차량을 제어하고 상태를 확인

# 5. DB 설계

1. **Oracle**

![image-20200916134534613](https://user-images.githubusercontent.com/58545240/93296365-025a2b00-f82a-11ea-836d-528f7cef38b9.png)

2. **Mongo DB**

![image-20200916134630415](https://user-images.githubusercontent.com/58545240/93296377-06864880-f82a-11ea-927d-eca1427faf39.png)

# 6. 나의 기여도

## 6.1 안드로이드

**DB를 위한 Spring서버와 양방향 통신을 위한 TCP서버 구축**

**로그인/회원가입**

![image-20200916141018587](https://user-images.githubusercontent.com/58545240/93296421-2158bd00-f82a-11ea-9ae3-890bb46e1f5d.png)

![image-20200916141830158](https://user-images.githubusercontent.com/58545240/93296427-26b60780-f82a-11ea-9140-ab1c75d21c3e.png)

**회원가입 시 인증번호를 통한 본인인증**

![image-20200916141255546](https://user-images.githubusercontent.com/58545240/93296435-2d447f00-f82a-11ea-94e3-16c17a9f0082.png)

**안드로이드의 모든 화면 설계**

![image-20200916141332652](https://user-images.githubusercontent.com/58545240/93296447-333a6000-f82a-11ea-8be4-df17339aad74.png)

**OpenWeatherMap API를 통한 현재날씨 출력**

![image-20200916141430074](https://user-images.githubusercontent.com/58545240/93296462-39304100-f82a-11ea-81b0-9e3c55e70f7a.png)

**모형차 무인주행 구현 및 스트리밍 화면 설계**

![image-20200916141101804](https://user-images.githubusercontent.com/58545240/93296477-43523f80-f82a-11ea-8e5b-30e136bb187d.png)

**DB에 있는 차량상태 불러오기 및 차량 조작 후 DB로 상태값 전달하기**

![image-20200916144146927](https://user-images.githubusercontent.com/58545240/93296817-f7ec6100-f82a-11ea-8711-e414a1830fe2.png)

![image-20200916142205407](https://user-images.githubusercontent.com/58545240/93296490-4baa7a80-f82a-11ea-85f0-a87b0a1f39b3.png)

![image-20200916142213114](https://user-images.githubusercontent.com/58545240/93296527-5f55e100-f82a-11ea-979c-f5f6b312ffd0.png)

![image-20200916141413710](https://user-images.githubusercontent.com/58545240/93296497-506f2e80-f82a-11ea-9de7-d461d499f275.png)

**CAN통신을 구현하여 안드로이드에서 차량 조작**

![image-20200916141413710](https://user-images.githubusercontent.com/58545240/93296578-798fbf00-f82a-11ea-8fb6-85a697ae9e9e.png)

## 6.2 WEB

**Spring 서버 구축 및 DB 설계**

**로그인/회원가입**

![image-20200916140944227](https://user-images.githubusercontent.com/58545240/93296600-844a5400-f82a-11ea-849a-a8d0991cd880.png)

**웹페이지 메인 화면 구현**

![image-20200916141715684](https://user-images.githubusercontent.com/58545240/93296612-890f0800-f82a-11ea-8a0e-78e930b5987d.png)

![image-20200916141720088](https://user-images.githubusercontent.com/58545240/93296620-8d3b2580-f82a-11ea-88bc-1d649e81f7d8.png)

**무인주행 스트리밍을 위한 화면 설계**

![image-20200916141122995](https://user-images.githubusercontent.com/58545240/93296632-93310680-f82a-11ea-8792-d19f2440be87.png)

