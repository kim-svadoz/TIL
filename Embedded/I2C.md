# I2C란?

---

**1. alc5633q가 무엇이냐?**

- I2C + I2S Stereo Audio Codec 기능을 가지고 있는 반도체 부품 중의 하나.

**2. I2C는 무엇이냐?**

![image-20200716151853623](https://user-images.githubusercontent.com/58545240/90083792-79ddfb80-dd4e-11ea-9229-c178e9d4867f.png)

- I2C(Inter-Intergrated Circuit, 또는 TWI - Two Wire Interface)는 복수 개의 슬레이브 장치가 복수개의 마스터 장치와 통신하기 위한 프로토콜이다. SPI와 마찬가지로 하나의 완성품을 구성하는 요소들 간의 근거리 통신을 위해 고안되었음.
- 비동기식 시리얼통신(이하 UART)은 클럭을 맞춰줘야 하고 데이터 라인으로 들어오는 신호를 항상 주시해야 하기 때문에 오버헤드가 있으며 하드웨어가 복잡해지는 단점이 있다. 결정적으로 UART통신은 1:1통신만 가능하다.
- 반면 동기식 시리얼 통신인 SPI는 클럭(CLK)라인을 이용해 데이터 라인을 동기화 하므로 하드웨어 구조도 간단하고 1:N통신이 가능하다. 송신용 핀과 수신용 핀이 분리되어 있고, full-duplex(동시 송수신) 연결을 이용해 10만 비트의 전송 속도를 지원하기도 한다. 하지만 SPI통신은 통신에 필요한 핀이 많아지는 단점이 있다. 하나의 장치를 연결하는 데 4개의 라인이 필요하고 추가로 장치를 더할 때 마다 라인이 하나씩 추가된다. 그리고 N:N통신은 불가.
- I2C 통신은 SPI의 통신의 이런 단점들을 보완할 수 있는 동기식(synchronous) 시리얼 통신 방법이다. UART통신처럼 단 두라인만 사용하고 1008개의 슬레이브 장치를 지원한다. 또한 N:N통신도 지원이 가능하다.(단 마스터장치 끼리 통신은 불가) 하드웨어 요구사항이 SPI보다 복잡하긴 하지만 UART통신보다는 간단하다. 통신 속도면에서도 SPI와 UART통신의 중간쯤 된다.
- SCL은 클럭신호를 생성해서 전달하는 역할을 하고 SDA는 실제 데이터가 전달되는 라인이다. 클럭 신호는 반드시 마스터 장치가 생성하는데 슬레이브 장치가 데이터 전송을 지연하기 위해 클럭신호를 만드는 경우도 있다. 이것을 clock stretching이라고 한다.
- I2C통신은 UART/SPI통신과는 달리 I2C 하드웨어(bus driver) 장치가 `open drain` 속성을 가진다. 이 말은 I2C통신을 사용하는 장치 하나가 데이터 전송을 위해 라인에 LOW신호를 넣고 있으면 다른 장치들은 이걸 강제로 HIGH로 바꿀 수 없다는 의미이다. I2C통신의 두 라인 SCL, SDA은 모두 풀업 저항에 연결되어 있으므로 통신라인이 사용이 끝나면 자동으로 HIGH상태로 복귀한다.
- 또한 풀업 저항은 동작 전압이 다른 장치들이 연결되어 통신하는 데 발생하는 문제점들을 없애준다. 풀업 저항이 두 장치가 사용하는 전압보다 낮은 레벨로 유지시켜주기 때문에 별도의 level-shifting 회로가 필요하지 않다. (두 장치의 전압차가 큰 경우는 I2C level shifter가 필요하다)

**UART, SPI, I2C 를 테이블 형식으로 비교 합니다.**

| 항목                             | UART                                                         | SPI                                                          | I2C                                                          |
| -------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 전체 이름                        | Universal Asyncronous Receiver/Transmitter                   | Serial Peripheral Interface                                  | Inter-Integrated Circuit                                     |
| Interface Diagram                | ![image-20200716151853623](https://user-images.githubusercontent.com/58545240/89985324-dc36ed80-dcb5-11ea-8e4d-b8d579b1db3f.png) | ![SPI%2BInterface%2BDiagram](https://user-images.githubusercontent.com/58545240/89985376-ece76380-dcb5-11ea-85d8-badd470c6531.jpg) | ![I2C%2BInterface%2BDiagram](https://user-images.githubusercontent.com/58545240/89985409-fa045280-dcb5-11ea-83ed-390aad7bdc20.jpg) |
| 핀                               | TxD: Transmit Data<br /> RxD: Receive Data                   | SCLK: Serial Clock<br />MOSI: Master Output, Slave Input<br />MISO: Master Input, Slave Output<br />SS: Slave Select | SDA: Serial Data<br />SCL: Serial Clock                      |
| Data Rate<br />데이터 전송 속도  | 비동기(asynchronous) 통신이기 때문에 공유하는 Clock 이 없어서 통신하는 양쪽 기기의 속도를 동일하게 맞춰 주어야 한다. 동일하지 않아도 통신이 되는 경우가 있지만 의도하지 않은 데이터가 들어갈 경우가 있다.  Maximum 통신 속도는 Model 에 따라 다르며, 일반적으로 115,200bps 이다. | SPI에서 최대 데이터 속도는 정해져 있지 않다. 보통 10Mbps 에서 20Mbps 사이 이다. | I2C는 100Kbps, 400Kbps, 3.4Mbps 를 주로 사용하며 10Kbps 나 1Mbps를 사용하는 경우도 있다. |
| Distance 거리                    | 5미터 이하                                                   | 가장 길다.                                                   | 길다.                                                        |
| Type of communication통신의 종류 | 비동기통신을 하는 기기가 같은 clock을 공유하지 않는다는 의미로, 통신하는 두 기기가 동일한 속도를 맞춰야 한다. 편리한 점은 속도만 맞추면 어떤 기기와도 통신이 가능하다는 점 | 동기같은 clock을 사용하여 통신을 한다는 의미로, 개발 단계에서 연결이 되어야 한다. | 동기                                                         |
| Number of masters                | Master가 없다. 각자 주고(Tx), 받는(Rx) 다.                   | Master 는 하나이며 변하지 않는다.                            | Master가 하나 이상이 될 수 있다.                             |
| Clock                            | 공유하는 Clock이 없다. 각 기기는 각자의 Clock을 사용하며 통신 시에는 동일한 데이터 전송 속도를 설정하여 송수신 해야 한다. | Master와 Slave들은 동일한 Clock을 사용한다.                  | Master들과 Slave들은 동일한 클럭을 사용한다.                 |
| Hardware Complexity              | 가장 복잡하지 않다.                                          | 복잡하지 않다.                                               | 복잡하다.                                                    |
| Protocol                         | Data는 8bit로 보내며 앞에 Start bit와 뒤에 Stop bit를 붙인다. | 통일된 Protocol이 있지 않다. 그래서 해당 기기의 datasheet를 참조해야 한다. 예를 들어 microcontroller와 EEPROM의 통신을 한다면 EEPROM 의 datasheet를 봐야 한다. | Start bit와 Stop bit를 사용하며 데이터는 8bit를 전송한다. 8bit를 보내고 나면 (slave가?) ack를 사용하여 데이터가 잘 받아졌는지를 확인한다. 아래 그림에 표시 되어있다.<br />![I2C%2BProtocol](https://user-images.githubusercontent.com/58545240/89985552-33d55900-dcb6-11ea-8917-55f8f528a9a8.jpg) |
| Software addressing              | 1:1 통신만을 하기 때문에 addressing은 필요 없다.             | Master에는 slave 개수만큼 slave select 라인이 있기 때문에 해당 line을 통해서 slave를 선택한다. | 다수의 master와 다수의 slave가 존재하고, 각 master는 모든 slave에 접근할 수 있다. 27개의 slave까지 지원이 되며 master는 고유 주소값을 통해 접근한다. |
| 장점                             | 하드웨어가 간단해서 거의 모든 장치에서 UART를 지원하기 때문에 (9개의 핀이 연결되거나, USB가 있는 장치라면) 편리하다. RS232 라고도 불린다. RS232는 protocol의 이름이며 UART는 그것을 가능하게 해 주는 송수신기를 의미하기 때문이다. | 간단한 프로토콜이기 때문에 구현하는데 어렵지 않다. Full duplex(전체 동시 송수신, 전이중) 통신을 지원한다.나뉘어진 slave select 라인이 있기 때문에 같은 종류의 칩들이 회로에 사용될 수 있다.SPI는 push-pull을 사용하기 때문에 높은 데이터 전송률을 가지며 긴 거리도 가능하다.SPI는 I2C와 비교하면 적은 파워를 사용한다. | Open collector(?) 디자인이기 때문에 slew rate(출력 전압의 최대 변화율, 모양이 이상해 지는 것)가 제한적이다.한 개 이상의 master가 가능하다.선이 두 개 필요하다.Addressing 하는 방법이 간단해서 SPI 같이 여러 라인이 필요하지 않다.Open collector bus concept를 가지고 있어 bus의 voltage 가 유연(다른 voltage level 가능)하다.Flow control을 사용한다. (ack를 말하는 듯) Mixed speed 가 가능하다([Link](https://www.i2c-bus.org/highspeed/)) |
| 단점                             | 1:1 통신만 지원한다.동일한 속도를 맞추고 시작해야 한다. 그렇지 않으면 데이터가 깨질 것이다. Voltage level 이 동일해야 한다. | Slave가 많아지면 각 slave별 라인이 필요하기 때문에 hardware 구성이 복잡해진다.만약 slave가 추가 된다면 선을 새로 연결해야 하며 addressing을 위해 software의 디자인이 변경되어야 한다.Master와 slave가 고정되어 있기 때문에 I2C에서 하는 것처럼 역할 변경을 할 수 없다.Flow control을 할 수 없다. Voltage level 이 동일해야 한다. | Master와 slave가 많아지면 복잡성이 증가한다.I2C 는 half duplex(반이중) 이다. 이 말은 하나의 선 SDA를 통해서 Data가 양 방향으로 갈 수 있지만 한번에는 하나의 데이터만 간다는 뜻이다. UART는 항상 한 방향으로만 가는데 반해 반 이중은 동시는 아니지만 양 방향이 가능하다. |
| 참조                             | [RS232 Interface>>](http://www.rfwireless-world.com/Terminology/RS232-interface.html) UART는 회로를 의미하는 단어로, 일반적으로 EIA RS-232, RS-422, RS-485와 같은 통신 표준과 함께 사용한다. | [SPI Interface>>](http://www.rfwireless-world.com/Terminology/SPI-interface.html) | [I2C Interface>>](http://www.rfwireless-world.com/Terminology/I2C-vs-I2S.html) |


**I2C**

- I2C 의 Slave 주소는 해당 Chip의 Datasheet에 Idx의 구성에 따라 여러가지로 될 수 있다는 것이 명시되어 있다. 여러 Chip이 같은 Bus를 통해서 연결될 수 있기 때문이다. 그래서 실제 IDx 의 회로 구성을 확인하여 주소값을 확인해야 한다.
- Slave I2C의 속도는 해당 Chip의 Register Setting 에 의해 변경될 수 있다. Clock 과 Data의 최대 속도를 확인하고 그 이상으로 셋팅하지 않아야 한다.
- I2C 에서 1Byte 단위로 데이터를 송신하기 때문에 9번째 bit를 보면 ACK 여부를 확인할 수 있다. Start와 Stop 신호를 제외하면 모두 이 1byte(8bit) 단위로 송신이 되며 보통 Slave Address(1byte), Register Address(1~2 Byte), Value(1Byte) 로 한 셋트를 송신하지만 인접한 Address 의 경우에는 연속으로 쓸 수 있다. 이를 Burst Write 혹은 Sequential Write 라고 부른다.
- I2C 를 여러 Chip 끼리 연결하려고 하면(같은 Bus) 신호의 Level 을 맞춰 주어야 한다. 보통 1.8V, 3.3V, 5V 등 이 있으며 이를 맞춰주기 위해서는 Level Shifter 가 회로에 구성되어 있어야 하며 그렇지 않으면 통신이 정상적으로 이루어 지지 않는다.
- I2C 를 이해할 때는 몇 V 로 동작하는지, Start Condition은 SDA 가 default 1인 상태에서 0으로 갈 때 신호가 시작되고, SDA, SCL 모두 default 1인 상태에서 0으로 갈 때가 동작을 진행한 상태이므로 우선순위가 높다 그래서 ACK 신호가 low(SCL high, SDA low) 인 것이다. Start 와 Stop(SDA가 0으로 먼저 떨어지고 SCL high 가 된 상태) 를 제외하면 총 9bit를 사용하는데 8bit 가 하나의 1byte 이므로 이를 보내기 위해서 사용되며 나머지 1bit 가 반대쪽의(write 시 slave 에서 잘 받았다고 보내는 신호) ACK 이다. Mater에서 read 시에는 데이터를 slave 쪽에서 보내는 데 이 때 데이터를 전부 보내고 나면 NACK 를 보내 더이상 보낼 데이터가 없다는 것을 알려준다.

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

![image-20200716152542545](https://user-images.githubusercontent.com/58545240/89989937-9893b200-dcbc-11ea-9adf-4c2afde8f3a1.png)

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

![image-20200716153312929](https://user-images.githubusercontent.com/58545240/89992438-3ccb2800-dcc0-11ea-811e-c8bffa2b6d8b.png)

​	**데이터를 읽는 경우**

![image-20200716153338512](https://user-images.githubusercontent.com/58545240/89992494-4a80ad80-dcc0-11ea-9101-6305600b1ef0.png)

​	=> 대부분의 칩에서 I2C통신은 하드웨어 기능으로 구성되어 있어 I2C관련 레지스터의 비트를 설정하는 것만으로도 시작신호, 데이터신호, 클럭신호, 정지신호를 출력할 수 있다.

​	**안정화 구간**

![image-20200720105534228](https://user-images.githubusercontent.com/58545240/90083872-a98d0380-dd4e-11ea-9bc0-4682da199b65.png)

​	=> I2C 프로토콜에서 SCL이 '0'인 구간에서는 SDA의 상태 변화가 허용되지만 SCL이 '1'인 구간에서는 SDA는 안정된 논리 상태를 유지해야 한다. Master가 	Slave로 데이터를 출력할 때 SCL이 '0'인 구간에서 SDA의 비트 전환을 하며 SCL이 '1'인 구간에서는 SDA의 상태를 그대로 유지한다. 그래서 SCL이 '1'인 	구간은 데이터가 안정한 구간.

​	=> I2C 프로토콜에서 SCL이 '1'을 유지하고 있는 구간에서 SDA의 상태가 변하는 것은 일반 데이터 전송이 아닌 특별한 조건을 의미한다. **SCL이 '1'인동	안 SDA가 '1'에서 '0'으로 바뀌는 것을 시작조건(start condition, S)라고 하며 SCL이 '1'인 동안 SDA가 '0'에서 '1'로 바뀌는 것을 정지조건(stop condition, 	P)**라고 한다. I2C통신을 원하는 Master는 SCL과 SDA가 모두 논리 '1'일 때 SDA의 상태를 '0'으로 바꾸어 시작 조건을 출력하며 다른 장치들에게 통신의	시작을 알린다. 마찬가지로 통신을 끝낼 때에는 SCL이 '1'인 동안 SDA를 '0'에서 '1'로 바꾸어 정지 조건을 출력하며 버스 소유권의 반납을 다른 장치에게 알린다.

## 예약된 주소

![image-20200728150822309](https://user-images.githubusercontent.com/58545240/90083886-b3af0200-dd4e-11ea-8938-7945e5e4583c.png)

위의 Slave address는 I2C에서 예약된 주소이다. 만약 Slave에서 7bit address를 설정할 수 있는 경우 예약된 주소는 피해야 한다. 특히 하드웨어로 주소를 설정하는 경우 하드웨어 디버깅이 불가피하게필요할 수 있다.



- `ALC5633Q DataSheet_V0.9.pdf`

  => alc5633q 메인 데이터시트

  ![image-20200730175252892](https://user-images.githubusercontent.com/58545240/89994987-d1835500-dcc3-11ea-9d7c-71e041bbcba8.png)

  => slave 주소가 0x38인것을 알 수 있다.

- `OSTRICH_FCAM_V30_19210-cadfarm.pdf`

- `OSTRICH_MAIN_V30_190210.pdf`

  ![image-20200720113212577](https://user-images.githubusercontent.com/58545240/89995037-ec55c980-dcc3-11ea-8ada-16bd01dcdc48.png)

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

![90201577-377ff180-de16-11ea-9927-5c8a14b81518](https://user-images.githubusercontent.com/58545240/90457294-10872f80-e136-11ea-9a61-7aaf4f9d1d45.png)

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

![image-20200727180442702](https://user-images.githubusercontent.com/58545240/89995532-8ddd1b00-dcc4-11ea-92e1-09ec1276e294.png)

++ 문서를 보면 2바이트씩 데이터를 주고 받기 때문에, WRITE / READ 모두 2바이트씩 데이터를 처리해야한다.

++ 문서를 통해 reg 주소가 0x0C인 것을 알 수 있다.

![image-20200727180602999](https://user-images.githubusercontent.com/58545240/90457185-d87fec80-e135-11ea-8e82-ed9523e7ba6a.png)

![image-20200727180631923](https://user-images.githubusercontent.com/58545240/90457197-df0e6400-e135-11ea-896e-c380d1305419.png)

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

unsigned int I2c_Read_Word(unsigned char mSlave, unsigned char mReg, int i2cDataSize)
{
	unsigned int temp;
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
		else ucLobyte = I2c_Read_Byte(0);
	}

	I2c_Stop();

	temp = (ucLobyte << (8*i2cDataSize-1)) + ucHibyte;
	AmbaPrint("temp ::::: %d", temp);

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

#### 3. 실행결과(teraterm)

![image-20200813171705105](https://user-images.githubusercontent.com/58545240/90110998-fc83ac80-dd88-11ea-903a-02f986edac3a.png)

![image-20200813171716569](https://user-images.githubusercontent.com/58545240/90111008-02798d80-dd89-11ea-8843-dbce2e9c1e69.png)