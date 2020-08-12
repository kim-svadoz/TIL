# Embedded_Basic

# ** 무엇을 배워야 임베디드 리눅스 개발을 잘할 수 있을까? **

---

좁게 보면 임베디드 리눅스 개발자가 알아야할 지식은 다음과 같다.

- 리눅스 디바이스 드라이버
- 리눅스 커널
- CPU 아키텍쳐
- SoC

조금 넓게 보면 다응 내용도 알면 좋다.

- 유저 공간 HAL(Hardware Abstraction Layer) 코드 구현
- 빌드 스크립트 구현
- 테스트용 디바이스 드라이버 구현
- git과 형상관리

## < 디바이스 드라이버 >

> 임베디드 리눅스 개발을 시작하면 바로 디바이스 드라이버를 맡는 경우가 많다. 물론 프로세스나 개발 회사 규모에 따라 드라이버 개발 범위가 다르다. 제품을 이루는 디바이스 드라이버의 특정 분야를 맡을 수도 있고 어떤 경우 한 명의 임베디드 개발자가 임베디드 리눅스를 채임지는 경우도 있다.

디바이스 드라이버의 사니로와 제어하는 하드웨어의 종류가 다양하다. 하지만 다응 내용 정도는 알아두는 것이 좋다.

- **인터럽트 핸들러 함수와 인터럽트를 처리하는 방식**
- **디바이스 파일로 open/read/write 연산 함수 등록하는 방법**
- **디바이스 트리를 읽어 디바이스 속성을 저장하는 방식**

개발에 투입이 되면 자신이 작성한 디바이스 드라이버 코드보다 다른사람이 작성한 코드를 읽을 가능성이 높다. 따라서 디바이스 드라이버 코드를 빨리 읽고 이해하는 능력을 키우는 것도 중요하다.

## < 리눅스 커널 >

디바이스 드라이버는 리눅스 커널에서 제공하는 함수로 이루어져 있다. 호출한 함수 원리를 알려면 자연히 리눅스 커널 코드를 분석할 수 밖에 없다. 어떤 분은 "리눅스 디바이스 드라이버는 리눅스 커널 그자체"라고 말하는데 그 의견에 동의한다.

또한 디바이스 드라이버 개발 과정은 코드를 입력해 드라이버를 구현하는 데 그치지 않는다. 인증 테스트 부서를 통해 드라이버 안정화 테스트 과정을 거치고 그 과정에서 다양한 버그나 문제 증상이 리포트 된다.

카메라 리눅스 디바이스의 경우 사진을 찍은 후 저장한 화면이 모두 검은색일 수 도 있고 그래픽 디바이스의 경우 화면이 깨질 수도 있다.

이 과정에서 만나는 다양한 버그를 수정해 문제를 해결하기 위해서는 리눅스 커널을 잘 알아야 한다.

## < CPU 아키텍쳐 >

리눅스 커널 코드를 조금 깊게 들여다 보면 만날 수 밖에 없는 것이 있다.

***바로 어셈블리 코드이다.***

리눅스 커널의 핵심 개념들은 대부분 어셈블리 코드로 구현돼 있다. 그 이유는 리눅스 커널 핵심동작은 CPU와 연관된 부분이 많기 때문이다.

- **컨텍스트 스위칭**
- **익셉션 벡터**
- **시스템 콜**
- **시그널 핸들러**
- **메모리 관리(MMU)**

처음 리눅스를 접하는 분은 무리해서 CPU 세부원리와 어셈블리 코드를 공부할 필요는 없다. 일단 리눅스 시스템과 커널 로그와 ftrace에 친숙해지는 것이 먼저이다.

위에 언급된 내용은 중간 정도만 하면 된다. 대신 시간을 모두 쏟아 부을 필요는 없다. 임베디드 및 BSP 리눅스 개발자의 핵심은 디바이스 드라이버와 커널이다. 하지만 위에서 언급한 기술에 신경을 안 쓰면 실전 개발에서 많은 걸림돌은 만나게 될 것이다.





# 임베디드 기본적인 몇가지 

## 버스

통신을 하기 위한 물리적(회로) 신호의 묶음(Bus). 전기적 신호 논할 때 사용

## 버스 프로토콜

버스 신호를 이용하여 통신 시작/종료, 방법 등 구체적으로 동작하는 버스 신호의 변화를 칭함



간단히 말하면 **버스는 전기신호를 말하고 프로토콜은 신호를 제어해서 통신하는 방법**을 말하는 것이다 !

대부분의 프로콜은 H/W에서, 즉 IC레벨에서 구현되어 있다. 제어하는 엔지니어가 무언가를 바꾸거나 할 수는 없다. 하지만 개발자는 이것을 알아야 하는데 그 이유는 **H/W가 잘못 된 경우 어떤 부분이 잘못 되었는지 디버깅을 하기 위함**이다. 이미 구현되었으니 출력신호는 정해져 있으며 만일 정해진 대로 출력이 안되면 해당 부분을 디버깅하는 식으로 개발이 진행된다.

시스템이 항상 잘 동작하기만 한다면 단순 구현만 하면 되지만 반대인 경우 원인을 찾기 위해서는 그 배경 지식인 개념을 이해하고 있어야 시스템을 볼 수 있는 눈이 생기게 된다.



진짜진짜 시작하기에 앞서.... LSB와 MSB에대해서 알아보자. 우선 이들은 어떠한 크기의 데이터 형이 있다면 그 데이터에서 가장 왼쪽에 있는 비트인지 혹은 가장 오른쪽에 있는 비트인지를 나타내는 비트이다.

## LSB란?

Least Significnat Bit의 약자로 하나의 데이터 형에서 가장 낮은 위치의 Bit를 의미한다. 예를 들어 `signed char` 데이터 형과 `unsigned char` 데이터 형에 대해서 예를 들면 아래와 같다.

![image-20200728152327402](https://user-images.githubusercontent.com/58545240/89979211-29ad5d80-dcaa-11ea-81a4-21cdebd22f09.png)

일단 LSB의 위치는 가장 값이 작은 비트인 2^0에 위치하고 잇는 것을 알 수 있다. 이 LSB의 값을 이용하여 해당 데이터 형에 들어 있는 실제 숫자가 짝수인지 홀수인지 손쉽게 알아낼 수 있다.

이런 LSB는 프로그래밍 시 주로 난수발생 함수, 해시 함수, 검사합(CheckSum) 함수 등에서 많이 쓰이고 있다. 왜냐하면 LSB는 값이 조금이라도 변경된다면, 데이터형의 최하위 비트이므로 그 값이 거의 100% 변화가 발생하기 때문이다.

![image-20200728152551110](https://user-images.githubusercontent.com/58545240/89979228-35008900-dcaa-11ea-96cb-bd281c802d6c.png)

다음은 `signed char` 데이터 형에서의 LSB에 대해 알아보자. signed char 데이터 형의 LSB역시 unsigned char 데이터 형에서의 큰 차이점은 없다. 즉 LSB에서 중요한 점은 바로 특정한 데이터 형의 가장 최하위 비트라는 점이다.

## MSB란?

Most Significant Bit의 약자로 어떠한 데이터 형의 최상위 비트를 의미한다. 이 MSB는 데이터 형에 따라서 특징이 조금 나뉘는데 우선 위에서 소개했던 unsigned char 데이터형의 MSB에 대해 살펴보면 2^7이라는 값을 지니고 있는 위치의 비트이다. 그 외에는 unsigned char 데이터 형의 가장 값이 큰 위치의 최상위 비트정도로 정의를 내릴수가 있다.

다음은 가장 중요하게 살펴볼 signed char 데이터 형에서의 MSB이다. 위에 소개했던 signed char 데이터 형의 MSB를 살펴보면 MSB위치가 부호자리를 나타내고 있는 것을 알 수 있다. 따라서 해당 데이터 형의 값이 MSB만 확인하면, 양수인지 혹은 음수인지를 손쉽게 알아낼 수 있다.

### 중요점

LSB와 MSB의 그 뜻 자체를 이해하는 것도 중요하지만 LSB나 MSB를 집적적으로 프로그래밍을 할 때 조사를 하는 경우는 매우 드물다. 따라서 LSB와 MSB의 보다 실무적인 본질을 알아야 한다 !

그것은 바로.. 임베디드 시스템 등에서 `시리얼 통신`을 할 때 시리얼 통신의 경우 송수신할 데이터의 각 비트를 단위시간에 따라서 순차적으로 보내게 된다. 즉 하나의 단위시간 동안 0혹은 1밖에 보낼 수 없는 구조라는 것이다.

이를 다시 전자에서 예를 들었던 unsigned char나 signed char 데이터 형에 비추어 바라봤을 때는 어떤 위치의 비트들이 단위시간 동안 가장 먼저 보내지고 가장 늦게 보내질지를 알야아 한다. 즉, MSB의 비트부터 순차적으로 데이터 비트를 보내 맨 마지막에 LSB의 비트를 보내는 것인지, 혹은 정반대로 LSB의 비트부터 차례로 보낸 다음 MSB를 맨 마지막에 보내는지를 명확히 알아야 할 때 매우 중요한 사항이 될것이다.

![image-20200728153414336](https://user-images.githubusercontent.com/58545240/89979236-3d58c400-dcaa-11ea-8a38-91b18fd35b43.png)

위의 그림과 같이 LSB부터 송신할 때와 MSB부터 송신할 때 수신지에서 받아들이는 값은 LSB부터 송신할 때와 MSB부터 송신할 때에 상이한 것을 알 수 있다. 즉 어떠한 데이터의 가장 최상위 비트부터 송신할 때는 수신지에서 해당 MSB를 LSB위치로 밀어넣게 되어있기 때문에 위와 같은 그림으로 데이터 송수신이 발생한다.

# 메모리

---

1. RAM
   - 컴퓨터를 종료하면 데이터가 날아가는 휘발성 메모리
   - 하드 디스크나 CD와는 달리 속도가 매우 빠르다.(데이터에 랜덤하게 접근할 수 있음)
   - 컴퓨터는 대부분의 데이터들은 메모리에 보관해 놓고 작업을 한다. 틈틈이 하드디스크에 저장!
2. ROM
   - 컴퓨터를 종료해도 데이터가 날아가지 않는 비휘발성 메모리
   - ex) CD-ROM, DVD-ROM, 하드디스크 => (데이터에 순차적으로 접근한다)

컴퓨터의 한 개의 메모리 소자는 0 혹은 1의 값을 보관할 수 있다. 이 이진수 한 자리를 가리켜 비트(Bit)라고 한다. 따라서, 1개의 비트는 0 또는 1의 값을 보관할 수 있다. 8bit = 1byte

8bit(1byte)로 나타낼 수 있는 최대의 수는 0 ~ 0xFF. 0부터 255로 총 256개의 수를 나타내게 된다.

컴퓨터에서 연산을 담당하는 CPU에는 **레지스터(register)**라는 작은 메모리 공간이 있는데 이곳에다가 값을 불러다 놓고 연산을 수행하게 된다. 예를 들어 a+b를 하기 위해서는 a와 b의 값을 어디다 적어놓아야지, a+b를 할 수 있는 것처럼 CPU에서 연산을 수행하기 위해 잠시 써놓는 부분을 레지스터라고 한다.

이러한 레지스터의 크기는 컴퓨터 상에서 연산이 실행되는 최소 단위라고 볼 수 있고, 이 크기를 **워드**라고 부른다.

1워드는 64비트, 즉 8바이트가 된다.

# 프로세스(Process)

---

## 1. 프로세스란?

- 프로세스(Process)란 실행중에 있는 프로그램(Program)을 말한다.
- 스케쥴링의 대상이 되는 작업(task)와 같은 의미로 쓰인다.
- 프로세스 내부에는 최소 하나의 스레드(thread)를 가지고 있는데, 실제로는 스레드단위로 스케쥴링을 한다.
- 하드디스크에 있는 프로그램을 실행하면, 실행을 위해서 **메모리할당**이 이루어지고, 할당된 메모리 공간으로 바이너리 코드가 올라가게 되는데 이 순간부터 프로세스라 불린다.
- 모든 프로세스는 각각 4G의 가상 주소공간(메모리공간X)을 부여받는다.
  - 운영체제 약 2G, 나머지 약 2G 응용프로그램의 고유 영역
  - 개발자는 이 가상주소공간 4G가 진짜 메모리인것 처럼 사용한다.
- 가상주소공간(virtual addres space) != 가상 메모리(virtual memory)

## 2. 가상주소공간

### 가상주소공간의 구조

![image-20200729135756106-1596589351106](https://user-images.githubusercontent.com/58545240/89979399-9d4f6a80-dcaa-11ea-91f0-7588ef3ea810.png)

- Code 영역 : 프로그램을 실행시키는 실행 파일 내의 명령어들이 올라간다.
- Data 영역 : 전역변수, static 변수의 할당
- Heap 영역 : 프로그래머의 **동적할당**을 위한 메모리 영역
  - C언어 => malloc & free
  - C++ => new & delete
  - JAVA => new & Garbage Collector
- Stack 영역 : 지역변수, 함수 호출시 전달되는 인자(파라미터)를 위한 메모리 영역
- page : 가상주소공간 4G를 4096byte(4K) 단위로 나눈 하나의 메모리 블록을 page라고 한다.
- 연산을 할 때는 값을 stack으로부터 레지스터로 가져오고 ALU로 넘겨서 연산을 수행한다. 연산 결과 값은 레지스터에 먼저 저장하고, stack영역에 재전달 한다.
- 레지스터의 스택포인터(SP)는 stack 프레임을 위해서 미리 공간을 확보한다.

### virtual machine

- register based machine(연산결과를 레지스터에 저장)
- virtual stack machine(연산결과를 stack에 저장하고 이를 다시 heap으로 반환)
- 연산결과를 임시로 젖아하는 공간을 operand stack이라고 부른다.
- 인터프린터 언어는 heap에 데이터를 저장하고 연산은 레지스터 ALU에서 실행 후 실행 결과를 stack에 저장. 해당값을 heap에 전달하려면 다시 레지스터를 거쳐야 한다.

### stack vs heap

- 가상주소공간 중 stack은 빠르고 heap은 상대적으로 느리다.
- stack은 그냥 데이터를 쌓지만 heap은 도중에 del등을 통해서 지울 수 있다. 빈공간이 생기면 그 곳에 새로운 데이터가 추가 된다.(spacial locality 보장이 어렵다)
- 또한, heap은 메타데이터 정보를 함께 저장한다. 따라서 더 많은 용량을 사용하며 할당시 매번 metadata에게 여분 공간이 있는지 묻기 때문에 상대적으로 느리다.
- **heap의 최대장점**
  - 할당시점과 지우는 시점을 마음대로 저장할 수 있다.
  - 프로세스 도중에 용량을 변경할 수 있다.(stack의 경우 프로세스 실행 전에만 변경 가능하다. 용랑이 넘치는 경우 stack overflow가 발생한다.)

## 3. 가상메모리

- 가상메모리 : 물리적인 RAM + 하드디스크
- page frame : 가상메모리(물리메모리 = RAM+페이징파일)를 4096byte(4K) 단위로 나눈 후, 그 한단위를 페이지 프레임이라 한다.
- 페이지 테이블 : 가상주소공간과 가상메모리를 매핑한다. 프로세스 별로 각각 하나씩 존재한다.
- RAM의 프레임이 모두 차있을 때, 추가 요청이 들어오면 RAM에서는 교체 알고리즘에 따라서 프레임 하나를 `페이징파일`로 내리고, 새롭게 요청된 페이지에 비워진 프레임을 할당한다.
- 가상메모리 운영방식 : LRU(least, recently, uses - 최근 최소 사용). 필요한것만 RAM으로 가져오고 안쓰는건 하드디스크에 내려놓는다.

## 4. 프로세스 스케쥴링

- CPU는 하나인데 동시에 실행되어야 할 프로세스가 여러개??

  => CPU가 고속으로 여러 프로세스를 일정한 기준으로 순서를 정해서 실행한다.

- **스케쥴링(Scheduling)**

  - CPU 할당 순서 및 방법을 결정하는 일.(어떤 프로세스를 running으로 보낼까?)
  - 일정한 기준 : 스케쥴링 알고리즘을 통해서.

*대부분의 OS에서는 **우선순위(Priority algorithm) 알고리즘**과 **라운드 로빈(Round Robin) 알고리즘**을 혼합해서 스케쥴링*

## 5. 프로세스 상태변화

![image-20200729141825350-1596589351107](https://user-images.githubusercontent.com/58545240/89979418-a6d8d280-dcaa-11ea-8fdd-d0485a702caa.png)

프로세스의 상태는 ready(준비), blocked(대기), running(실행) 상태가 있다.

- 생성(new) -> 준비(ready)

  => new 상태에서 프로세스가 생성되게 되면 OS 커널에 존재하는 Ready Queue에 올라가게 된다.

- 준비(ready) -> 실행(running)

  => Ready Queue에 있는 프로세스들을 OS가 위에서 말한 프로세스 스케쥴링 알고리즘에 의해서 Running 상태로 가야할 프로세스를 CPU로 할당하게 된다. 그러면 프로세스는 실행(Running)상태가 된다.

- 실행(running) -> 준비(ready)

  => 현재 running 상태에 있는 프로세스A보다 Ready Queue에서 대기하고 있는 프로세스 B 우선순위가 높으면, preemptive schedule(선점형)인 경우 프로세스A는 ready상태로 오게되고 프로세스B가 running상태로 가서 CPU를 할당받는다.

- 실행(running) -> 대기(blocked)

  => 현재 running 상태에 있는 프로세스A에서 입출력(I/O) 이벤트가 발생했을 때 프로세스A가 blocked상태로 가게된다.

- 대기(blocked) -> 준비(ready)

  => 입출력(I/O) 이벤트가 종료된 프로세스는 다시 Ready상태로 오게된다.

- 실행(running) -> terminate(종료)

  => 프로세스 종료

*ready, blocked 상태에는 여러 프로세스가 존재할 수 있다.*

*하지만, **싱글코어CPU**에서, running상태의 프로세스는 단 하나만 존재한다.*



# I2C란?

---

**1. alc5633q가 무엇이냐?**

- I2C + I2S Stereo Audio Codec 기능을 가지고 있는 반도체 부품 중의 하나.

**2. I2C는 무엇이냐?**

![image-20200716151853623](../../work/images/image-20200716151853623.png)

- I2C(Inter-Intergrated Circuit, 또는 TWI - Two Wire Interface)는 복수 개의 슬레이브 장치가 복수개의 마스터 장치와 통신하기 위한 프로토콜이다. SPI와 마찬가지로 하나의 완성품을 구성하는 요소들 간의 근거리 통신을 위해 고안되었음.

- 비동기식 시리얼통신(이하 UART)은 클럭을 맞춰줘야 하고 데이터 라인으로 들어오는 신호를 항상 주시해야 하기 때문에 오버헤드가 있으며 하드웨어가 복잡해지는 단점이 있다. 결정적으로 UART통신은 1:1통신만 가능하다.

- 반면 동기식 시리얼 통신인 SPI는 클럭(CLK)라인을 이용해 데이터 라인을 동기화 하므로 하드웨어 구조도 간단하고 1:N통신이 가능하다. 송신용 핀과 수신용 핀이 분리되어 있고, full-duplex(동시 송수신) 연결을 이용해 10만 비트의 전송 속도를 지원하기도 한다. 하지만 SPI통신은 통신에 필요한 핀이 많아지는 단점이 있다. 하나의 장치를 연결하는 데 4개의 라인이 필요하고 추가로 장치를 더할 때 마다 라인이 하나씩 추가된다. 그리고 N:N통신은 불가.
- I2C 통신은 SPI의 통신의 이런 단점들을 보완할 수 있는 동기식(synchronous) 시리얼 통신 방법이다. UART통신처럼 단 두라인만 사용하고 1008개의 슬레이브 장치를 지원한다. 또한 N:N통신도 지원이 가능하다.(단 마스터장치 끼리 통신은 불가) 하드웨어 요구사항이 SPI보다 복잡하긴 하지만 UART통신보다는 간단하다. 통신 속도면에서도 SPI와 UART통신의 중간쯤 된다.
- SCL은 클럭신호를 생성해서 전달하는 역할을 하고 SDA는 실제 데이터가 전달되는 라인이다. 클럭 신호는 반드시 마스터 장치가 생성하는데 슬레이브 장치가 데이터 전송을 지연하기 위해 클럭신호를 만드는 경우도 있다. 이것을 clock stretching이라고 한다.
- I2C통신은 UART/SPI통신과는 달리 I2C 하드웨어(bus driver) 장치가 `open drain` 속성을 가진다. 이 말은 I2C통신을 사용하는 장치 하나가 데이터 전송을 위해 라인에 LOW신호를 넣고 있으면 다른 장치들은 이걸 강제로 HIGH로 바꿀 수 없다는 의미이다. I2C통신의 두 라인 SCL, SDA은 모두 풀업 저항에 연결되어 있으므로 통신라인이 사용이 끝나면 자동으로 HIGH상태로 복귀한다.
- 또한 풀업 저항은 동작 전압이 다른 장치들이 연결되어 통신하는 데 발생하는 문제점들을 없애준다. 풀업 저항이 두 장치가 사용하는 전압보다 낮은 레벨로 유지시켜주기 때문에 별도의 level-shifting 회로가 필요하지 않다. (두 장치의 전압차가 큰 경우는 I2C level shifter가 필요하다)

## I2C Protocol

> I2C 통신의 데이터 구조는 UART/SPI보다 복잡하다. UART/SPI에서 문제가 되는 부분들을 소프트웨어적으로 처리하기 때문이다

1. 기본구조

   ```bash
   I2C가 전송하는 메시지는 2개의 프레임 - 주소(address)프레임과 데이터(data)프레임으로 구분할 수 있다. 주소 프레임은 마스터가 전송하는 데이터가 어떤 슬레이브로 가는지 알려주기 위한 프레임이다. 데이터 프레임은 실제 전송하려는 데이터가 담긴 프레임이다. 
   ```

2. 통신 시작 조건

   ```bash
   데이터 통신을 시작하기 위해서는 주소 프레임부터 전송해야 한다. 이를 위해 마스터 장치는 SCL(클럭)라인을 HIGH상태로 두고, SDA(데이터)라인을 LOW로 변경한다. 이 신호는 모든 슬레이브 장치들에게 데이터를 전송할 준비가 되었다는 신호로 인식된다. 만약 회로에 마스터 장치가 여러개 붙어 있다면 먼저 SDA라인을 LOW로 잡는 이가 제어권을 갖는다.
   ```

3. 주소 프레임(address frame)

   ```bash
   주소 프레임은 항상 모든 통신 데이터의 앞에 위치한다. 데이터 라인으로 처음 수신되는 1byte 중 앞선 7bit 데이터(MSB, most significant bit)가 I2C주소를 나타낸다. 그리고 마지막 1bit는 읽기(read 1, 데이터 요청) 또는 쓰기(write 0, 데이터 전송) 동작임을 표시한다.
   
   프레임의 9번째 bit 값은 NACK/ACK 비트이다. 이 비트는 마스터가 전송한 주소 정보(8 bit)에 해당하는 장치가 만들어줘야 하는 신호이다.(즉, 해당하는 슬레이브 장치가 잠시 SDA 라인의 컨트롤 권한을 가짐). 마스터 장치는 이 신호를 보고 데이터가 전송이 성공적인지, 실패라면 앞으로 어떤 동작을 할지 판단해야 한다. NACK/ACK 비트는 주소 프레임, 데이터 프레임의 끝에 항상 붙는다.
   ```

4. 데이터 프레임(data frame)

   ```bash
   주소 프레임의 전송이 완료되면 이제 데이터 프레임의 전송을 시작한다. 마스터 장치는 SCL 라인의 클럭 신호를 계속 생성하며 SDA라인으로는 데이터가 오고간다. 이 때 SDA 라인의 데이터 프레임은 read, write 상태를 나타내는 bit값에 따라 마스터 또는 슬레이브가 사용한다.
   ```

5. 통신 종료 조건

   ```bash
   모든 데이터 프레임이 전송되면 마스터 장치는 종료 신호를 만들어야 한다. 종료 신호는 클럭 라인이 SCL을 0 -> 1(Low to HIGH) 상태로 변경한 뒤, SCL 라인이 HIGH인 상태에서 SDA 라인을 0 -> 1(Low to HIGH)로 변경하면 된다. 따라서 반드시 모든 장치들은 반드시 SCL 라인이 HIGH인 상태에서 SDA라인의 상태를 변경해서는 안된다. 이런 동작은 잘못 종료신호로 인식될 수 있기 때문이다.
   ```

   

## I2C의 구성

```bash
- I2C통신은 데이터를 주고 받기 위한 선(SDA) 하나와 송수신 타이밍 동기화를 위한 클럭선(SCL)하나로 이루어져 있음.
- 하나의 마스터(Master)와 하나 이상의 슬레이브(Slave)로 이루어져 있다.
- 위 그림에서 "Device n"들 중 하나가 마스터기기가 되고 나머지는 슬레이브.
- R1과 R2 저항이 중요하다. 통신을 위해서는 SDA선과 SCL선이 모두 기본 HIGH상태여야 하기 때문에 풀업 저항으로 HIGH상태를 만들어 주는 것이 중요하다.
- ** I2C통신에서는 한 라인에 하나 이상의 마스터가 존재할 수도 있다. **
- ** 마스터와 슬레이브 모두 데이터를 전송하고 수신하기 때문에 SDA 선은 INPUT과 OUTPUT을 모두 사용하게 되고 이로 인해 플로팅(Floating)현상이 발생할 수 있기 때문에 풀업 저항이 필요하다. **
- 데이터 송수신은 마스터에서 주도하며, 데이터를 전송하거나 읽어오기 전 반드시 슬레이브의 주소를 명시해준 후 통신을 시작해야 하기 때문에 긴 데이터 통신보다는 짧은 데이터 통신에 주로 사용됨.
- 시리얼(UART)통신과 비교했을 때 I2C통신의 장점은 동기화 통신이라는 점.
- 동기화 통신방식이란 클럭(Clock)신호를 이용해서 데이터의 전송 타이밍을 맞추는 방식인데, 때문에 시리얼 통신처럼 통신속도가 따로 정해지지 않아도 된다는 장점이 있다.
```

## 데이터의 동작방식 알아보기

![image-20200716152542545](../../work/images/image-20200716152542545.png)

```bash
- I2C통신은 시작 신호와 데이터 신호, 정지 신호로 이루어진다.
- SDA와 SCL 선의 신호는 풀업 저항에 의해 기본적으로 HIGH. 그러다 SDA신호만 LOW로 떨어지면 그때부터 시작 신호(S)라고 판단한다. 그 후에 SCL선으로 클럭신호가 만들어지는데, 클럭 신호가 LOW일 때가 SDA신호를 비트 신호로 바꾸는 시간(파란색 부분), 클럭 신호가 HIGH일 때가 SDA신호를 읽는시간이다. 다시 SCL신호가 LOW가 되면 다음 비트 신호로 바꾸고 다시 HIGH에서 읽고...
- 한 클럭에 한 비트씩 데이터 신호를 만들고, 모든 비트의 전송이 끝난 후 SCL 신호가 HIGH신호가 되면 SDA 신호 역시 HIGH로 만들어 정지 신호(P)를 만든다.

- 시작 신호 뒤에 나오는 첫 7비트는 슬레이브의 주소 값이어야만 하며, 8번째 비트는 데이터를 읽어오기 위한
신호인지, 쓰기 위한 신호인지를 나타내는 비트로 사용된다.
- 슬레이브의 주소 값과 읽기/쓰기 비트는 마스터에서 생성할 수 있으며, '쓰기'일 경우에 마스터에서 이후 데이터를 생성, '읽기'일 경우에는 슬레이브에서 데이터를 생성한다. 8비트데이터 전송 후에는 슬레이브에서 응답 신호(ACK)를 만들어 수신을 확인해준다.

- 응답신호는 기본적으로 LOW여야 하며, 만일 슬레이브가 데이터를 전송하는 상태에서 모든 데이터의 전송이 끝났을 경우에는 HIGH상태가 된다. 이를 NACK라고 하는데, 이 외의 경우에 NACK가 발생하는 경우는 통신 에러나 데이터 에러의 경우이다.
```

## 데이터 동작 순서

1. 데이터 전송시작(Start) - 1비트

2. 주소(Address) - 7비트

`슬레이브 디바이스는 자신의 주소(Addr) 비트를 인식하면 ACK를 회신한다!!`

3. 읽기 또는 쓰기(R/W) - 1비트

4. 상대편 수신여부 - 1비트

5. 데이터 -  8비트

6. 상대편 수신여부(Ack) - 1비트

7. 종료(Stop) - 1비트



​	**데이터를 쓸 경우**

![image-20200716153312929](../../work/images/image-20200716153312929.png)

​	**데이터를 읽는 경우**

![image-20200716153338512](../../work/images/image-20200716153338512.png)

​	=> 대부분의 칩에서 I2C통신은 하드웨어 기능으로 구성되어 있어 I2C관련 레지스터의 비트를 설정하는 것만으로도 시작신호, 데이터신호, 클럭신호, 정지신호를 출력할 수 있다.

​	**안정화 구간**

​	![image-20200720105534228](../../work/images/image-20200720105534228.png)

​	=> I2C 프로토콜에서 SCL이 '0'인 구간에서는 SDA의 상태 변화가 허용되지만 SCL이 '1'인 구간에서는 SDA는 안정된 논리 상태를 유지해야 한다. Master가 	Slave로 데이터를 출력할 때 SCL이 '0'인 구간에서 SDA의 비트 전환을 하며 SCL이 '1'인 구간에서는 SDA의 상태를 그대로 유지한다. 그래서 SCL이 '1'인 	구간은 데이터가 안정한 구간.

​	=> I2C 프로토콜에서 SCL이 '1'을 유지하고 있는 구간에서 SDA의 상태가 변하는 것은 일반 데이터 전송이 아닌 특별한 조건을 의미한다. **SCL이 '1'인동	안 SDA가 '1'에서 '0'으로 바뀌는 것을 시작조건(start condition, S)라고 하며 SCL이 '1'인 동안 SDA가 '0'에서 '1'로 바뀌는 것을 정지조건(stop condition, 	P)**라고 한다. I2C통신을 원하는 Master는 SCL과 SDA가 모두 논리 '1'일 때 SDA의 상태를 '0'으로 바꾸어 시작 조건을 출력하며 다른 장치들에게 통신의	시작을 알린다. 마찬가지로 통신을 끝낼 때에는 SCL이 '1'인 동안 SDA를 '0'에서 '1'로 바꾸어 정지 조건을 출력하며 버스 소유권의 반납을 다른 장치에게 알린다.

## 예약된 주소

![image-20200728150822309](../../work/images/image-20200728150822309.png)

위의 Slave address는 I2C에서 예약된 주소이다. 만약 Slave에서 7bit address를 설정할 수 있는 경우 예약된 주소는 피해야 한다. 특히 하드웨어로 주소를 설정하는 경우 하드웨어 디버깅이 불가피하게필요할 수 있다.



- `ALC5633Q DataSheet_V0.9.pdf`

  => alc5633q 메인 데이터시트

  ![image-20200730175252892](../../work/images/image-20200730175252892.png)

  => slave 주소가 0x38인것을 알 수 있다.

- `OSTRICH_FCAM_V30_19210-cadfarm.pdf`

- `OSTRICH_MAIN_V30_190210.pdf`

  ![image-20200720113212577](../../work/images/image-20200720113212577.png)

  => 메인보드회로에 의해 SCL은 28번, SDA는 29번 핀을 사용한다!

- `OSTRICH_RCAM_V30_190210-cadfarm.pdf`

- `OSTRICH_SUB_V31_190408.pdf`

나머지는 그와 관련된 회로들.

`AmbaGPIO.c`, `t_tx.c`, 프로젝트는 h22만 받아서 사용하기

---

## I2C GPIO test with source insight

메인보드 회로에 따라 다르지만 a5633q의 경우 AUDIO_SCL은 28번핀, AUDIO_SDA는 29번핀을 이용한다.

0x0101 0x2020 ?

구현해야 할 것 : START와 STOP!! READ할지 WRITE할지?! (송/수신은 통신 확인까지 해주어야함.)

최종목적 : I2C의 파형을 만들어 보는 것...?!

- `AmbaCSL_I2C.c`

  - AMBA_CORTEX_I2C0_BASE_ADDR : 0xe80000000 + 0x003000 (I2C Master 0)
  - AMBA_CORTEX_I2C1_BASE_ADDR : 0xe80000000 + 0x001000 (I2C Master 1)
  - AMBA_I2C_CHANNEL0 = (AMBA_I2C_REG_s) AMBA_CORTEX_I2C0_BASE_ADDR
  - AMBA_I2C_CHANNEL1 = (AMBA_I2C_REG_s) AMBA_CORTEX_I2C1_BASE_ADDR

- `AmbaGPIO.c`

- `AmbaGPIO_Def.h`

  ```c++
  /* I2C */
  GPIO_PIN_28_I2C0_CLK            = GPIO_SET_PIN_FUNC(1, GPIO_PIN_28),    /* Clock pin of the 1st I2C master port */
  GPIO_PIN_29_I2C0_DATA           = GPIO_SET_PIN_FUNC(1, GPIO_PIN_29),    /* Data pin of the 1st I2C master port */
  ```

  - GPIO_SET_PIN_FUNC(FuncNo, PinNo) ((FuncNo <<12) | PinNo)

- `t_tx.c` : 여기서 내가 짠 I2C GPIO TEST                                                                                                                                                           ***하드코딩ver***

  ```C
  /*************************** ksh TEST *******************************/
  /*	 SCL(클락) : 28, SDA(데이터) : 29
   	 SDA는 input, Ouput 다 설정해주어야 한다. SCL은 OUTPUT만 설정하면 됨
   	 slave : 0 0 1 1 1 0 1 (R/W)  0x38
   	 0x80  : 1 0 0 0 0 0 0
   	 READ는 1, WRITE는 0 */
  
  #define pSCL GPIO_PIN_28
  #define pSDA GPIO_PIN_29
  #define slave 0x38
  #define reg 0x0C
  #define data 0x42
  
  int gpio(AMBA_SHELL_PRINT_f print_f, int argc, char **argv)
  {
  	int rval = 0;
  	void write_word(unsigned char mSlave, unsigned char mReg, unsigned char mData);
  	unsigned char read_word(unsigned char mSlave, unsigned char mReg);
  
  	write_word(slave, reg, data);
  	read_word(slave, reg);
  
  	return rval;
  }
  
  int gpio_write(AMBA_SHELL_PRINT_f print_f, int argc, char **argv)
  {
  	int rval = 0;
  	void write_word(unsigned char mSlave, unsigned char mReg, unsigned char mData);
  	write_word(slave, reg, data);
  	return rval;
  }
  
  int gpio_read(AMBA_SHELL_PRINT_f print_f, int argc, char **argv)
  {
  	int rval = 0;
  	unsigned char read_word(unsigned char mSlave, unsigned char mReg);
  	read_word(slave, reg);
  	return rval;
  }
  
  void i2c_start(void)
  {
  	AmbaPrint("============================i2c_start==========================");
  	AmbaGPIO_ConfigOutput(pSDA, AMBA_GPIO_LEVEL_HIGH);
  	AmbaKAL_TaskSleep(1);
  	AmbaGPIO_ConfigOutput(pSCL, AMBA_GPIO_LEVEL_HIGH);
  	AmbaKAL_TaskSleep(1);
  
  	AmbaGPIO_ConfigOutput(pSDA, AMBA_GPIO_LEVEL_LOW);
  	AmbaKAL_TaskSleep(1);
  	AmbaGPIO_ConfigOutput(pSCL, AMBA_GPIO_LEVEL_LOW);
  	AmbaKAL_TaskSleep(1);
  }
  
  void i2c_stop(void)
  {
  	AmbaPrint("============================i2c_stop==========================");
  	AmbaGPIO_ConfigOutput(pSDA, AMBA_GPIO_LEVEL_LOW);
  	AmbaKAL_TaskSleep(1);
  
  	AmbaGPIO_ConfigOutput(pSCL, AMBA_GPIO_LEVEL_HIGH);
  	AmbaKAL_TaskSleep(1);
  	AmbaGPIO_ConfigOutput(pSDA, AMBA_GPIO_LEVEL_HIGH);
  	AmbaGPIO_ConfigInput(pSDA);
  	AmbaKAL_TaskSleep(1);
  }
  
  void i2c_write_byte(unsigned char byte) // 슬레이브에게 데이터 보내는 곳
  {
  	unsigned char i = 0;
  	int i2c_getACK(AMBA_GPIO_PIN_ID_e pin);
  	AmbaPrint("============================i2c_write_byte==========================");
  
  	for(i=0; i<8; i++){
  		((byte & 0x80)==0x80) ? AmbaGPIO_ConfigOutput(pSDA, AMBA_GPIO_LEVEL_HIGH) : AmbaGPIO_ConfigOutput(pSDA, AMBA_GPIO_LEVEL_LOW);
  //		AmbaKAL_TaskSleep(1);
  
  		AmbaGPIO_ConfigOutput(pSCL, AMBA_GPIO_LEVEL_HIGH); // SCL 1
  		AmbaKAL_TaskSleep(1);
  		byte = byte << 1;
  		AmbaGPIO_ConfigOutput(pSCL, AMBA_GPIO_LEVEL_LOW); // SCL 0
  		AmbaKAL_TaskSleep(1);
  	}
  
  	AmbaGPIO_ConfigInput(pSDA);
  
  	if(i2c_getACK(pSDA)==1)
  	{
  		AmbaPrint("ACK success!");
  	};
  
  
  	AmbaGPIO_ConfigOutput(pSCL, AMBA_GPIO_LEVEL_HIGH); // SCL 1
  	AmbaKAL_TaskSleep(1);
  	AmbaGPIO_ConfigOutput(pSCL, AMBA_GPIO_LEVEL_LOW); // SCL 0
  }
  
  unsigned char i2c_read_byte(unsigned char byte)
  {
  	unsigned char i, buff = 0;
  	int readNum = -1;
  	AMBA_GPIO_PIN_INFO_s PinInfo;
  	int i2c_getACK(AMBA_GPIO_PIN_ID_e pin);
  	AmbaGPIO_ConfigInput(pSDA); // SDA set INPUT @@@@@
  	AmbaPrint("============================i2c_read_byte==========================");
  
  	for(i=0; i<8; i++){
  		AmbaGPIO_ConfigOutput(pSCL, AMBA_GPIO_LEVEL_HIGH); // SCL 1
  		AmbaKAL_TaskSleep(1);
  
  		AmbaGPIO_GetPinInfo(pSDA, &PinInfo);
  		readNum = PinInfo.Level;
  		AmbaPrint("%u번째 readNum = %d", i, readNum);
  
  		if(readNum == 1) buff = buff | 0x00;
  		else if(readNum == 2) buff = buff | 0x01;
  
  		AmbaPrint("cur buff => %u", buff);
  		if(i<7) buff <<= 1;
  
  		AmbaGPIO_ConfigOutput(pSCL, AMBA_GPIO_LEVEL_LOW); // SCL 0
  		AmbaKAL_TaskSleep(1);
  	}
  	if(byte==0)
  	{
  		AmbaGPIO_ConfigOutput(pSDA, AMBA_GPIO_LEVEL_LOW); // SDA 0
  	}
  	else
  	{
  		AmbaGPIO_ConfigOutput(pSDA, AMBA_GPIO_LEVEL_HIGH); // SDA 1
  		AmbaKAL_TaskSleep(1);
  		AmbaGPIO_ConfigOutput(pSDA, AMBA_GPIO_LEVEL_LOW); // SDA 0
  	}
  
  	if(i2c_getACK(pSDA)==1)
  	{
  		AmbaPrint("read - ACK success!");
  	};
  
  	AmbaGPIO_ConfigOutput(pSCL, AMBA_GPIO_LEVEL_HIGH); // SCL 1
  	AmbaKAL_TaskSleep(1);
  	AmbaGPIO_ConfigOutput(pSCL, AMBA_GPIO_LEVEL_LOW); // SCL 0
  
  	AmbaPrint("result Buff : %u", buff);
  	return buff;
  }
  
  unsigned char read_word(unsigned char mSlave, unsigned char mReg)
  {
  	unsigned char temp;
  	unsigned char ucHibyte = 0;
  	unsigned char ucLobyte = 0;
  	unsigned char unNack = 0;
  	void i2c_AltFunc(void);
  	AmbaPrint("============================read_word==========================");
  
  	ucHibyte = 0;
  	ucLobyte = 0;
  
  	i2c_start();
  	i2c_write_byte(mSlave);
  	i2c_write_byte(mReg);
  
  	i2c_start();
  	i2c_write_byte(mSlave+1);
  
  	ucHibyte = i2c_read_byte(0);
  	ucLobyte = i2c_read_byte(1);
  
  	i2c_stop();
  
  	temp = (ucHibyte << 8) + ucLobyte;
  	AmbaPrint("temp ::::: %u", temp);
  
  	i2c_AltFunc();
  	return temp;
  
  }
  
  void write_word(unsigned char mSlave, unsigned char mReg, unsigned char mData)
  {
  	void i2c_AltFunc(void);
  	AmbaPrint("============================write_word==========================");
  	i2c_start();
  	i2c_write_byte(mSlave);
  	i2c_write_byte(mReg);
  	i2c_write_byte(0x00);
  	i2c_write_byte(mData);
  	i2c_stop();
  	i2c_AltFunc();
  }
  
  int i2c_getACK(AMBA_GPIO_PIN_ID_e pin)
  {
  	int ACK = -1;
  	AMBA_GPIO_PIN_INFO_s PinInfo;
  	AmbaGPIO_GetPinInfo(pin, &PinInfo);
  	ACK = PinInfo.Level;
  	AmbaPrint("my ACK Lv : %d", ACK);
  	return ACK;
  }
  
  void i2c_AltFunc(void)
  {
  	AmbaGPIO_ConfigAltFunc(GPIO_PIN_28_I2C0_CLK);
  	AmbaGPIO_ConfigAltFunc(GPIO_PIN_29_I2C0_DATA);
  }
  ```

### > 오류상황

![image-20200724111807088](../../work/images/image-20200724111807088.png)

WRITE 후 READ 시 ACK를 보내주었지만 버퍼를 읽을 수 없다... 타이밍의 문제 같아 보이는데 아직 어떻게 해결해야 할지 모르겠다. < 첫번째 과제

=> read_byte()에서 SDA의 핀을 어떻게 읽는지를 몰랐었는데, AmbaGPIO_GetPinInfo를 이용해 핀의 high, low를 판별하여 1바이트를 구성하였다. 그것을 buff에 담아서 출력하는작업!!

```C
for(i=0; i<8; i++){
		AmbaGPIO_ConfigOutput(pSCL, AMBA_GPIO_LEVEL_HIGH); // SCL 1
		AmbaKAL_TaskSleep(1);

		AmbaGPIO_GetPinInfo(pSDA, &PinInfo);
		readNum = PinInfo.Level;
		AmbaPrint("%u번째 readNum = %d", i, readNum);

		if(readNum == 1) buff = buff | 0x00;
		else if(readNum == 2) buff = buff | 0x01;

		AmbaPrint("cur buff => %u", buff);
		if(i<7) buff <<= 1;

		AmbaGPIO_ConfigOutput(pSCL, AMBA_GPIO_LEVEL_LOW); // SCL 0
		AmbaKAL_TaskSleep(1);
	}
```

![image-20200727180442702](../../work/images/image-20200727180442702.png)

++ 문서를 보면 2바이트씩 데이터를 주고 받기 때문에, WRITE / READ 모두 2바이트씩 데이터를 처리해야한다.

++ 문서를 통해 reg 주소가 0x0C인 것을 알 수 있다.

![image-20200727180602999](../../work/images/image-20200727180602999.png)

![image-20200727180631923](../../work/images/image-20200727180631923.png)

이상으로 기본 WRITE / READ 메소드 구성은 끝!

### > Write Data를 인자로 받아서 메소드 처리하기

```C
// static int test.tw
else if (strcmp(argv[1], "gpio") == 0) {
            if(strcmp(argv[2], "write") == 0) {
				rval = gpio_write(print_f, argc, argv, argv[3]);
				goto _done;
            } else if(strcmp(argv[2], "read") == 0) {
				rval = gpio_read(print_f, argc, argv);
				goto _done;
            }
  
int gpio_write(AMBA_SHELL_PRINT_f print_f, int argc, char **argv, char *hexString)
{
	int rval = 0;
	unsigned long value;

	void write_word(unsigned char mSlave, unsigned char mReg, unsigned char mData, int i2cDataSize);

	AmbaPrint("input argument hexString ::: %s", hexString);

    // strtoull() :: 정수 문자열을 진수 선택하여 unsigned long으로 변환해주는 함수
	value = strtoull(hexString, argv, 16);
	AmbaPrint("value : %u", value);

	write_word(i2c_slave, i2c_reg, value, i2c_size);

	return rval;
}
```



### > size를 인자로 받아 범용 가능한  I2C 프로토콜 만들기

```C
#define i2c_size 2

int gpio_write(AMBA_SHELL_PRINT_f print_f, int argc, char **argv, char *hexString)
{
	...

	write_word(i2c_slave, i2c_reg, value, i2c_size);

	...
}

int gpio_read(AMBA_SHELL_PRINT_f print_f, int argc, char **argv)
{
	...
        
	read_word(i2c_slave, i2c_reg, i2c_size);
    
	...
}

void write_word(unsigned char mSlave, unsigned char mReg, unsigned char mData, int i2cDataSize)
{
	...

	for(int i=0; i<i2cDataSize; i++)
	{
		if(i==i2cDataSize-1) i2c_write_byte(mData);
		else i2c_write_byte(0x00);
	}

	...
}

unsigned char read_word(unsigned char mSlave, unsigned char mReg, int i2cDataSize)
{
	...

	for(int i=0; i<i2cDataSize; i++)
	{
		if(i==i2cDataSize-1) ucHibyte = i2c_read_byte(1);
		else ucLobyte += i2c_read_byte(0);
	}

	...
}
```

로 했으나 size가 2일때는 잘 작동을 하지만 3일때부터는 ACK를 받지 못하고 READ가 아예 안됨 => ***어떻게 처리하지?***

### > Refactoring

> 주 메소드들을 헤더파일로 분리하였고, 변수명을 통일하였음.

#### 1. i2c_test.h

```C
#ifndef __I2C_TEST_H__
#define __I2C_TEST_H__

#include <string.h>
#include <stdlib.h>
#include <AmbaI2C.h>
#include <stdio.h>
#include "AmbaGPIO_Def.h"
#include "AmbaDataType.h"
#include "AmbaKAL.h"
#include "AmbaRTSL_GPIO.h"
#include "AmbaRTSL_GPIO_Ctrl.h"
#include "AmbaGPIO.h"
#include "AmbaLink.h"

#define SCL GPIO_PIN_28
#define SDA GPIO_PIN_29
#define I2C_SLAVE 0x38
#define I2C_REG 0x0C
#define I2C_SIZE 2
#define HIGH AMBA_GPIO_LEVEL_HIGH
#define LOW AMBA_GPIO_LEVEL_LOW

void I2c_Write_Word(unsigned char mSlave, unsigned char mReg, unsigned char mData, int i2cDataSize)
{
	void I2c_Start(void);
	void I2c_Write_Byte(unsigned char byte);
	void I2c_Stop(void);
	void I2c_AltFunc(void);

	I2c_Start();
	I2c_Write_Byte(mSlave);
	I2c_Write_Byte(mReg);

	for(int i=0; i<i2cDataSize; i++)
	{
		if(i==i2cDataSize-1) I2c_Write_Byte(mData);
		else I2c_Write_Byte(0x00);
	}

	I2c_Stop();
	I2c_AltFunc();
}

unsigned char I2c_Read_Word(unsigned char mSlave, unsigned char mReg, int i2cDataSize)
{
	unsigned char temp;
	unsigned char ucHibyte = 0;
	unsigned char ucLobyte = 0;

	void I2c_Start(void);
	void I2c_Write_Byte(unsigned char byte);
	unsigned char I2c_Read_Byte(unsigned char byte);
	void I2c_Stop(void);
	void I2c_AltFunc(void);

	ucHibyte = 0;
	ucLobyte = 0;

	I2c_Start();
	I2c_Write_Byte(mSlave);
	I2c_Write_Byte(mReg);

	I2c_Start();
	I2c_Write_Byte(mSlave+1);

	for(int i=0; i<i2cDataSize; i++)
	{
		if(i==i2cDataSize-1) ucHibyte = I2c_Read_Byte(1);
		else ucLobyte += I2c_Read_Byte(0);
	}

	I2c_Stop();

	temp = (ucHibyte << (8*i2cDataSize-1)) + ucLobyte;
	AmbaPrint("temp ::::: %u", temp);

	I2c_AltFunc();
	return temp;
}

void I2c_Start(void)
{
	AmbaGPIO_ConfigOutput(SDA, HIGH);
	AmbaKAL_TaskSleep(1);
	AmbaGPIO_ConfigOutput(SCL, HIGH);
	AmbaKAL_TaskSleep(1);

	AmbaGPIO_ConfigOutput(SDA, LOW);
	AmbaKAL_TaskSleep(1);
	AmbaGPIO_ConfigOutput(SCL, LOW);
	AmbaKAL_TaskSleep(1);
}

void I2c_Stop(void)
{
	AmbaGPIO_ConfigOutput(SDA, LOW);
	AmbaKAL_TaskSleep(1);

	AmbaGPIO_ConfigOutput(SCL, HIGH);
	AmbaKAL_TaskSleep(1);
	AmbaGPIO_ConfigOutput(SDA, HIGH);
	AmbaGPIO_ConfigInput(SDA);
	AmbaKAL_TaskSleep(1);
}

void I2c_Write_Byte(unsigned char byte)
{
	unsigned char i = 0;
	int I2c_GetACK(AMBA_GPIO_PIN_ID_e pin);

	for(i=0; i<8; i++){
		((byte & 0x80)==0x80) ? AmbaGPIO_ConfigOutput(SDA, HIGH) : AmbaGPIO_ConfigOutput(SDA, LOW);

		AmbaGPIO_ConfigOutput(SCL, HIGH);
		AmbaKAL_TaskSleep(1);
		byte = byte << 1;
		AmbaGPIO_ConfigOutput(SCL, LOW);
		AmbaKAL_TaskSleep(1);
	}

	AmbaGPIO_ConfigInput(SDA);
	if(I2c_GetACK(SDA)==1)
	{
		AmbaPrint("ACK success!");
	};

	AmbaGPIO_ConfigOutput(SCL, HIGH);
	AmbaKAL_TaskSleep(1);
	AmbaGPIO_ConfigOutput(SCL, LOW);
}

unsigned char I2c_Read_Byte(unsigned char byte)
{
	unsigned char i, buff = 0;
	int readNum = -1;
	AMBA_GPIO_PIN_INFO_s PinInfo;
	int I2c_GetACK(AMBA_GPIO_PIN_ID_e pin);
	AmbaGPIO_ConfigInput(SDA);

	for(i=0; i<8; i++){
		AmbaGPIO_ConfigOutput(SCL, HIGH);
		AmbaKAL_TaskSleep(1);

		AmbaGPIO_GetPinInfo(SDA, &PinInfo);
		readNum = PinInfo.Level;
		AmbaPrint("%u번째 readNum = %d", i, readNum);

		if(readNum == 1) buff = buff | 0x00;
		else if(readNum == 2) buff = buff | 0x01;

		AmbaPrint("cur buff => %u", buff);
		if(i<7) buff <<= 1;

		AmbaGPIO_ConfigOutput(SCL, LOW);
		AmbaKAL_TaskSleep(1);
	}
	if(byte==0)
	{
		AmbaGPIO_ConfigOutput(SDA, LOW);
	}
	else
	{
		AmbaGPIO_ConfigOutput(SDA, HIGH);
		AmbaKAL_TaskSleep(1);
		AmbaGPIO_ConfigOutput(SDA, LOW);
	}

	I2c_GetACK(SDA);

	AmbaGPIO_ConfigOutput(SCL, HIGH);
	AmbaKAL_TaskSleep(1);
	AmbaGPIO_ConfigOutput(SCL, LOW);

	AmbaPrint("result Buff : %u", buff);
	return buff;
}


int I2c_GetACK(AMBA_GPIO_PIN_ID_e pin)
{
	int ACK = -1;
	AMBA_GPIO_PIN_INFO_s PinInfo;
	AmbaGPIO_GetPinInfo(pin, &PinInfo);
	ACK = PinInfo.Level;
	AmbaPrint("my ACK Lv : %d", ACK);
	if(ACK==1)
	{
		AmbaPrint("ACK success!!");
	}
	return ACK;
}

void I2c_AltFunc(void)
{
	AmbaGPIO_ConfigAltFunc(GPIO_PIN_28_I2C0_CLK);
	AmbaGPIO_ConfigAltFunc(GPIO_PIN_29_I2C0_DATA);
}

#endif
```

#### 2. t_tw.c

```C
#include "i2c_test.h"

static int t_tw_i2c_write(AMBA_SHELL_PRINT_f print_f, int argc, char **argv, char *hexString)
{
	int rval = 0;
	unsigned long value;

	AmbaPrint("input argument hexString ::: %s", hexString);

	value = strtoull(hexString, argv, 16);
	AmbaPrint("value : %u", value);

	I2c_Write_Word(I2C_SLAVE, I2C_REG, value, I2C_SIZE);
	return rval;
}

static int t_tw_i2c_read(AMBA_SHELL_PRINT_f print_f, int argc, char **argv)
{
	int rval = 0;
	I2c_Read_Word(I2C_SLAVE, I2C_REG, I2C_SIZE);
	return rval;
}

static int test_tw(int argc, char** argv, AMBA_SHELL_PRINT_f printf){
    ...
        
    else if (strcmp(argv[1], "i2c") == 0) {
            if(strcmp(argv[2], "write") == 0) {
				rval = t_tw_i2c_write(print_f, argc, argv, argv[3]);
				goto _done;
            } else if(strcmp(argv[2], "read") == 0) {
				rval = t_tw_i2c_read(print_f, argc, argv);
				goto _done;
            }
        }    
}
```



# 워치독 타이머(WDT)

> Watchdog Timer

## 1. 고신뢰성 시스템을 위한, 워치독 타이머

### 가. WDT의 개념

- 비정상, 무한루프 등에 빠진 경우 시스템 통제가 불가능한 상황에서 자동으로 시스템을 리셋하는 하드웨어 기능
- 타임아웃이 되기 전 S/W명령으로 그 값을 clear시켜주지 않으면 MCU를 reset시켜 시스템을 정상적으로 동작하고 있는지 감시하고 지속적인 오동작을 방지 신뢰성 향상 기술

### 나. WDT의 필요성

1. 제어 실패 방지 메커니즘 필요
   - 불필요한 반복 또는 제어 실패를 방지하는 메커니즘 필요
2. 예상치 못한 실패 안전모드 필요
   - 시스템 일부가 예상 못한 제어 실패 시 안전모드로 전환 필요

## 2. WDT 개념도 및 구성요소

### 가. WDT 구성도

![image-20200807170209219](https://user-images.githubusercontent.com/58545240/89979546-fddea780-dcaa-11ea-9812-ccdb3034ef7c.png)

### 나. WDT 구성요소

- `Clock` : HW 디바이스를 동작시키는 외부 Clock Source
- `Clear(Res-tart/Kick)` : HW 디바이스가 정상 동작함을 알려주는 주기적 Alive 신호
- `Timeout` : 타이머가 종료되었음을 알려주는 Output신호
- `Reset` : HW 디바이스를 초기화 할 수 있는 입력 시그널

### 다. WDT 동작방식

- Watchdog 타이머는 HW를 주기적으로 감시하며 시간을 쟤는 계수 회로. 디바이스로부터 일정 시간동안 입력값을 받지 못 하는 경우 시스템의 오동작 상황으로 간주하고 초기화 수행

## 3. WDT 유형 및 사례

### 가. WDT의 유형

![image-20200807170451892](https://user-images.githubusercontent.com/58545240/89979560-06cf7900-dcab-11ea-8cae-09f913ee24de.png)

- 외부 워치독 타이머는 고가이므로 높은 신뢰성 요구하는 시스템에 주로 사용

### 나. WDT 구현 방법

![image-20200807170528165](https://user-images.githubusercontent.com/58545240/89979575-0e8f1d80-dcab-11ea-9903-75f8c6ab0eba.png)

- 워치독 타이머는 고정 또는 프로그래밍 가능한 시간 간격 가능, 다단계 워치독의 각 타이머는 각 다른 시간 간격 가능

## 4. WDT 하드웨어 내부구조

![image-20200807170626944](https://user-images.githubusercontent.com/58545240/89979593-1ea6fd00-dcab-11ea-9d03-509967d06e73.png)

- `Watchdog Control Register (WDCR)`
  - 워치독을 컨트롤 하는 레지스터
  - 리셋 상태 설정, 사용여부, 로직 체크
- `Watchdog Counter Register (WDCNTR)`
  - 워치독 카운터의 상태 레지스터
  - 현재까지 카운팅 된 값 확인 가능
- `Watchdog Reset Key Register (WDKEY)`
  - 워치독 카운터를 Clear 하는 역할
- `System Control and Status Register(SCSR)`
  - 워치독 카운터 출력 신호 결과를 리셋 / 인터럽트에 이용 여부 결정

## 5. WDT 소프트웨어

### 가. WDT SW 동작 방식

![image-20200807170817579](https://user-images.githubusercontent.com/58545240/89979602-2666a180-dcab-11ea-8d22-095cf6e2ac56.png)



- 워치독 소프트웨어는 타이머 만료 시 작업 정의 및 하드웨어 타이머 시작 후 주기적 타이머 초기화 수행

### 나. WDT SW 사례

![image-20200807171644205](https://user-images.githubusercontent.com/58545240/89979616-2cf51900-dcab-11ea-838a-edf314b19a38.png)