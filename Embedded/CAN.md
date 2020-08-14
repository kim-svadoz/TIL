



# CAN통신이란?

> Controller Area Network

## 개요

- 1985년 벤츠의 의뢰로 `Bosch`사에서 차량 네트워크용으로 최초로 개발
- 1980년대까지 자동차는 대부분 기계식
- 기술의 발전으로 자동차에 다양한 모듈(ECU)들이 생기기 시작
- UART의 통신은 각 모듈이 1:1통신을 해서 모듈이 추가될 때마다 더 많은 연결선이 필요
- **CAN통신은 자동차 내부 전장 및 산업용 제어기에 많이 사용 된다. 노이즈에 강하고 프레임을 하드웨어적으로 처리하므로 스프퉤어 처리가 비교적 단순하다**

## 특징

- Multi-Master 구조
  - 통신 신호 충돌 대책이 있음(CSMA/CA)
  - 메시지 ID간 우선 순위가 있다
  - 데이터 송신 충돌 정지 시, 선로가 비어 있을때 자동 재 전송 기능
- 통신 속도 : 최대 1MBPS 까지 통신 가능
- 통신 데이터 수 : 8바이트 (산업용/차량용 제어 장치에 적합)
- 통신 프로토콜/에러 처리를 `HW` 적으로 처리
- 다수의 장치(Standara : 11bit, Extended: 29bit ID구분)간 통신 가능
- 노이즈 환경에 강하다
- 비용이 경제적
  - 여러 회사의 MCU내에 기본 장착이 늘고 있음

## CAN통신 포맷

![image-20200814105939875](https://user-images.githubusercontent.com/58545240/90207168-89c61000-de20-11ea-851b-6b3d453b59fd.png)

![![image-20200814105951299](https://user-images.githubusercontent.com/58545240/90207175-90ed1e00-de20-11ea-8aa1-b7660180f3d5.png)

## CAN통신 동작 방식

![![image-20200814110123172](https://user-images.githubusercontent.com/58545240/90207205-9ea2a380-de20-11ea-86a4-2734c087230e.png)

![![image-20200814110130916](https://user-images.githubusercontent.com/58545240/90207214-a6fade80-de20-11ea-818f-c6e4040d7748.png)

여러장치가 신호를 함께 보내고 있는데 신호 상태를 피드백 받으면서 진행, 보낸 신호와 동일하면 다음 비트 진행 그런데 내가 High상태를 보내고 있는데 다른장치가 Low를 보내면 선로 상태는 Low가 됨. 그러면 본인은 전송을 즉시 중지함.

따라서 Low값을 보낸 장치가 우선 순위가 높게 진행

중간에 전송을 중지한 장치는 다음에 선로가 비어있을 때 재전송 시도.

전송을 잘 끝마친 장치는 송신 OK!!

## 응용 예

- 선박 및 항해 제어 시스템(Marin Control & Navigation System)
- 엘리베이터 제어 시스템(Elevator Control System)
- 농업용 기계(Agricultural Machinery)
- 제조 라인(Production line control system)
- 공작 기계(Machine tools)
- 대형 망원경(Large optical telescopes)
- 복사기(Photo copier)
- 의료기기(Medical system)
- 완구(Toys for children) 등등..

## 활용 예

- CAN 프로토콜은 Multi Master 통신을 한다. 통신버스를 공유하고 있는 CAN컨트롤러들은 모두가 Master역할을 하여 언제든지 버스를 사용하고 싶을 때 사용할 수 있다.
- 노이즈에 강하다.
- 국제 표준 프로토콜이다.
- 하나의 통신 선로에 다수의 장치를 연결하여 데이터 공유가 가능하다

![image-20200814103529428](https://user-images.githubusercontent.com/58545240/90207223-ac582900-de20-11ea-8ff8-e889efb89c30.png)

![image-20200814103547424](https://user-images.githubusercontent.com/58545240/90207235-b11cdd00-de20-11ea-8650-01926d989565.png)

=> 배선의 증가 => **유지보수 문제**

=> 배선 증가로 인한 무게 증가 => **연비하락**

# CAN 데이터 송수신

- CAN 통신에서 데이터는 **메시지 프레임**을 사용하여 송수신이 이루어진다
- 메시지 프레임은 하나 또는 그 이상의 송신 노드로부터 데이터를 수신노드로 전송한다.

## 통신 규칙

- 각각의 Message는 자신의 고유 ID를 갖는다.
- CAN 2.0B = 29bit
- Message 충돌의 **중재**는 ID를 통하여 우선 순위를 결정
- 수신노드는 message ID를 check하여 무시 또는 저장
- 각 ECU는 고유의 ID가 존재하는데 이 ID값이 작을수록 우선순위가 높다.

## 데이터 프레임

![image-20200814103859524](https://user-images.githubusercontent.com/58545240/90207254-be39cc00-de20-11ea-9793-efe00ba04351.png)

- 메시지 frame의 형태로 데이터를 송수신하며 메시지frame이란 송수신하는 `bit`들을 의미한다.
- 통신 프로토콜 frame에서 시작 문자, 끝 문자를 제외한 나머지를 모두 더한 후 `0xff`로 `And(&)`연산
- 연산한 결과의 1바이트 값에 대응하는 Hex ASCII 문자열

## Check Sum

- 통신 프로토콜 frame에서 시작 문자, 끝 문자를 제외한 나머지를 모두 더한 후 `0xff`로 `And(&)`연산한 결과의 1바이트값에 대응하는 `Hex ASCII` 문자열

  ex) 통신 프로토콜 Frame(시작 문자, 끝 문자 제외) : "G10"인 경우

  **Check Sum** = (`G` + `1` + `0`) `&` `0xff`한 Hex ASCII 문자열

# 데이터 수신 동작 시작

## 동작 요청 명령

![![image-20200814104212517](https://user-images.githubusercontent.com/58545240/90207266-c5f97080-de20-11ea-970e-17b5108f1684.png)

## 정상응답

![![image-20200814104223172](https://user-images.githubusercontent.com/58545240/90207270-c98cf780-de20-11ea-849c-9d78ffbe58d7.png)

# 데이터 수신하기

- 현재 수신된 CAN데이터를 나타내며 이 명령은 CANPro에서 CAN 데이터 수신 동작이 시작된 이후 CAN데이터 수신 시 자동으로 PC측에 전송한다.

## 정상응답

![![image-20200814104356886](https://user-images.githubusercontent.com/58545240/90207283-d0b40580-de20-11ea-9a5c-5447790f1e97.png)

# 데이터 송신하기

- CAN 네트워크상에 특정 CAN Message를 보내고자 할 때 사용하는 명령

## 동작 요청 명령

![![image-20200814104429706](https://user-images.githubusercontent.com/58545240/90207289-d6a9e680-de20-11ea-8231-923b13f6de15.png)

## 정상응답

![![image-20200814104437678](https://user-images.githubusercontent.com/58545240/90207306-e3c6d580-de20-11ea-9e80-651e586422df.png)





# OBD-II PIDs

> 현재 차에서 쓰이고 있는 캔통신 프로토콜 중 하나.

## Overview

- **OBD-II PIDs (On-board diagnostics Parameter IDs) are codes used to request data from a vehicle, used as a diagnostic tool.**
- `SAE` standard J1979 defines many OBD-II PIDs.
- a standardized `data link connector` defined by `SAE J1962`

## OBD II Connector

![![image-20200814104850899](https://user-images.githubusercontent.com/58545240/90207336-f7723c00-de20-11ea-838c-60af79f6faf8.png)

![![image-20200814104857480](https://user-images.githubusercontent.com/58545240/90207343-fb9e5980-de20-11ea-9a73-17d060b0f103.png)

- **Pinout**
  - `5` : Signal gorund
  - `6` : CAN-High (ISO 15765-4 and SAE J2284)
  - `14` : CAN-Low (ISO 15765-4 and SAE J2284)



## PID

자기 단수의 상태를 계속 뿌리기만 하면된다?

자기 엔진의 아이디랑 rpm상태를 계속 뿌리기만 하면된다?

![![image-20200814105031708](https://user-images.githubusercontent.com/58545240/90207350-ffca7700-de20-11ea-85e9-523f12faabbd.png)

## CAN bus format

- The PID **query** and **response** occurs on the **vehicle's CAN bus**.
- The diagnostic reader initiates a **query** using **CAN ID** **7DFh**, which acts as a broadcast address, and accepts responses from any ID in the range 7E8h to 7EFh. ECUs that can respond to OBD queries listen both to the functional broadcast ID of 7DFh and one assigned ID in the range 7E0h to 7E7h. Their **response** has an ID of their assigned ID plus 8 e.g. **7E8h through 7EFh**.

## Query

- The functional PID query is sent to the vehicle on the CAN bus at ID 7DFh, using 8 data bytes.

![image-20200814105110548](https://user-images.githubusercontent.com/58545240/90207356-0658ee80-de21-11ea-8f52-d30e83470545.png)

## Service

![![image-20200814105118220](https://user-images.githubusercontent.com/58545240/90207368-0e189300-de21-11ea-99e5-e95bccdb5e4e.png)

### ex) Query Speed

```java
msgID = 7DF;									//	Transmit message ID (HEX)
msgData = {02010D5555555555}; 						// Message data (HEX)
```

![![image-20200814105306980](https://user-images.githubusercontent.com/58545240/90207372-140e7400-de21-11ea-9ada-15f8ab2ab50d.png)

## Respond

- The vehicle responds to the PID query as 8h higher than PID address.

![![image-20200814105344453](https://user-images.githubusercontent.com/58545240/90207391-1b358200-de21-11ea-8ee4-07bab5f3045c.png)

### ex) Respond Speed

```java
msgID = 7E8;							// Transmit message ID (HEX)
msgData = {02010D5555555555}; 			// Message data (HEX)
```

![![image-20200814105446924](https://user-images.githubusercontent.com/58545240/90207402-2092cc80-de21-11ea-8498-c5ed7650255f.png)

## Application data

![![image-20200814105507425](https://user-images.githubusercontent.com/58545240/90207412-24265380-de21-11ea-9620-a7140c389498.png)





# CAN 통신 IN 멀티캠퍼스

*CAN통신을 만드는 건 어렵지 않은데 RS232 데이터를 모니터링하며 프로토콜을 구현해야 한다.*

- BroadCasting : 메시지를 보내면 전체 캔 장비에서 수신이 가능함.
  - 지금은 다 받을 수 있도록 하지만 많아지면 부하가 많아질 것임.
  - 캔통신도 선으로 연결되어있는 시리얼통신
  - 선의 용량이 점점 더 많아진다. ( 무게라던가 부피라던가 )
    - 하나의 선에 장비들이 붙어서 연결하는 방식 ( Bus )
    - 특정 선을 선별해서 수신할 수 있음.
- 우리가 만든 자바프로그램으로 CAN장비끼리 송수신하는 것이 목적
  - 전달할 수 있는 데이터의 크기는 제한적
- 웹&앱 모두 요청해야함.(서버로)
  - 서버는 모든 사람들이 붙어서 이용하기 때문에.. 
  - 내 차에만 전송될 수 있도록 해야함.

![![image-20200814110654180](https://user-images.githubusercontent.com/58545240/90207430-32746f80-de21-11ea-9724-88fa54b7c0dc.png)

![![image-20200814110707737](https://user-images.githubusercontent.com/58545240/90207436-37d1ba00-de21-11ea-8e8c-2ddce7fcea06.png)

![![image-20200814110712126](https://user-images.githubusercontent.com/58545240/90207443-3bfdd780-de21-11ea-8dea-af3410e8d88e.png)