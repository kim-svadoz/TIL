# Nandflash

>  Nandflash datsheet와 Nandflash driver source code를 기반으로 낸드플래시를 이해해보자

본격적으로 낸드플래시를 설명하기 전에 **DRAM과 플레시메모리(Flash Memory)의 차이에** 관해 설명하겠다.

저장방식에 있어서 DRAM은 **캐패시터**에 저장하는 반면 플레시메모리는 **플로팅게이트(Floating Gate:FG)**라는 곳에 데이터를 저장한다.

따라서 DRAM은 **휘발성**, 플래시메모리는 **비휘발성**의 특징을 가지게 된다.

# 낸드 플래시 메모리

---

> NAND Flash Memory
>
> 반도체의 셀이 직렬로 배열되어 있는 **플래시 메모리**의 한 종류

- 플래시 메모리(Flash Memory)는 반도체 칩 내부의 전자회로 형태에 따라 직렬로 연결된 **낸드 플래시**와 병렬로 연결된 **노어플래시**로 구분된다. 
- 낸드플래시는 용량을 늘리기 쉽고 쓰기 속도가 빠른반면 노어플래시는 읽기속도가 빠른 장점을 갖고 있다.(NOR가 쓰기가 수천배 느리다 -> 치명적 단점)
- 낸드 플래시는 저장단위인 셀을 수직으로 배열하는 구조이기 때문에 좁은 면적에 많은 셀을 만들 수 있어 **대용량화**가 가능하다.
- 또한 데이터를 순차적으로 찾아가 읽기 때문에 노어플래시보다 읽기 속도는 느리지만 별도로 **셀의 주소를 기억할 필요가 없어 쓰기속도는 훨씬 빠르다.**
- 제조단가가 노어플레시보다 싸다. 이론적으로 한 셀당 면적이 NOR형의 40% 수준이다. 수많은 셀을 집적해서 대용량을 구현하는 플래시 메모리에 있어서 작은 면적은 엄청난 장점이다. ( **면적=단가** )

=> 이처럼 낸드플래시는 소형화, 대용량화가 가능하기 때문에 다양한 모바일 기기 및 전자제품의 저장장치로 사용되고 있다!

![image-20200814135540833](https://user-images.githubusercontent.com/58545240/90222170-7fb70800-de46-11ea-9def-3269f98e7830.png)



## 낸드플래시 메모리 구조

> NAND 플래시 메모리는 기본 구조로 하나의 페이지를 가지며, 이 페이지가 여러 개 모여서 블록을 구성한다.

블록은 소블록과 대블록, 두 개의 형태를 갖는다.

소블록은 512바이트 크기의 페이지가 32개로 이루어져있으며

대블록은 용량에 따라 2K 또는 4KB의 크기를 갖는 페이지가 64개 모여서 하나의 블록을 구성한다.



하나의 페이지는 주 데이터 영역과 보조 데이터 영역으로 구분된다.

주 데이터 영역은 소블록 NAND 플래시 메모리의 경우 512바이트이며 , 대블록 NAND 플래시 메모리 는 2KB 또는 4KB이다.

보조 데이터 영역은 소블록 NAND 플래시 메모리는 16바이트지만 대블록 NAND 플래시 메모리는 64바이트 또는 128바이트이다.



주 데이터 영역은 주로 프로그램이나 리소스 데이터를 저장하는 용도로 사용된다.

보조데이터 영역은 주 데이터 영역의 보조적인 역할을 담당하며, `bad-block`에 대한 정보나 `ECC(Error Correction Code)`값을 저장하는 용도로 주로 사용된다.

하지만, 이 같은 역할을 수행해도 남는 영역이 존재하게 되는데, 이러한 영역은 사용자의 임의 목적으로 사용될 수 있으며 여러가지 컨트롤 정보를 저장할 수 있다.

| 소블록 플래시 메모리                                         | 대블록 플래시 메모리                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| - 16KB 크기의 블록들로 구성<br />- 각 블록은 32개의 페이지로 구성<br />- 각 페이지는 (512+16) Byte로 구성 | - 128KB 크기의 블록들로구성(MLC의 경우 256KB)<br />- 각 블록은 64개의 페이지로 구성(MLC의 경우 128개)<br />- 각 페이지는 (2048+64) Byte로 구성 |

- 즉, **Page가 모여 Block이 되고, Block이 모여 Plane이 되고, Plane이 모인 것이 NAND 플래시 메모리 칩**이다. 
  - `Page` : 데이터 저장의 기본단위로 셀의 집합 -> *읽기, 쓰기 작업의 최소 단위*
  - `Block` : 페이지의 집합 -> *삭제 작업의 최소 단위*
  - `Plane` : 블록의 집합 -> 연산 처리의 단위



## 낸드플래시 작동 원리

![image-20200814135952749](https://user-images.githubusercontent.com/58545240/90222177-847bbc00-de46-11ea-85be-1674beef49cd.png)

> 기본적인 모스펫 구조에 플로팅 게이트(부유 게이트)가 추가된 형테이다.
>
> 저 플로팅 게이트에 전자를 저장함으로써 데이터를 저장하는 것이다.
>
> - 플로팅 게이트에 전자가 있으면(많으면) **0**으로 인식 ( programmed )
> - 플로팅 게이트에 전자가 없으면(적으면) **1**로 인식( erased, unprogrammed )
>
> 기본 구조는 이렇지만 현재는 플로팅게이트가 아닌 산화막에 전자를 저장하고 플로팅게이트를 생략해버리는 식(삼성, CTF) 등의 발전된 방식이 많이 존재한다.

**`Control Gate`에 전압을 인가하면** `Source`에서 `Drain`으로 이동하던 전자가 **`tunneling`으로 `Floating Gate`로 들어가게 된다.**

```bash
# Note
Tunneling이란 낮은 에너지를 갖는 입자가 에너지가 높으나 공간폭이 작은 포텐셜 장벽을 횡단하는것을 의미.
```

`Floating Gate`는 산화막에 의해 `Isolation`이 되어 전원이 끊겨도 데이터가 `Floating Gate`에 그대로 남아있게 되는 방식이다.

### - Write

![image-20200814140336680](https://user-images.githubusercontent.com/58545240/90222186-8a719d00-de46-11ea-88d4-4d2aaed52098.png)

`Control Gate`와 `Drain`에 `Positive Voltage`를 인가하여 `Channel`에서 이동하는 전자들을 `Floating Gate`로 끌어당김으로써 낸드플래시 상에 Write할 수 있다.

```bash
# Note
1. 중간에 절연층(산화막)이 버티고 있는데 산화막은 기본적으로 전자가 통과할 수 없다.
2. 그래서 12~24V의 고전압을 걸어주고 드레인 측에서도 그정도로 높은 전압을 걸어준다.
3. 이정도의 강력한 전계가 형성되면 전자가 충분한 에너지를 얻어서 산화막을 통과한다.
4. 통과한 전자는 플로팅게이트에 저장되어 전계가 사라져도 산화막에 의해 외부로 유출되지 않는다.
```



### - Read

![image-20200814135918305](https://user-images.githubusercontent.com/58545240/90222192-8f365100-de46-11ea-8284-17b91a72d5e1.png)

하나의 Bit Line에 다수의 셀이 직렬로 연결되어 있다. 따라서 Bit Line에 전압을 인가하면 직렬로 연결된 모든 셀에 전압이 인가가 되고 `Control Gate`에 전압을 인가한 셀에서만 `Channel`이 형성된다.

이 때, `Floating Gate`상에 전자가 있는 셀은 `Control Gate`와 `Floating Gate`간 **Electric Field 간섭으로 인해** `Channel`에 전자가 잘 흐르지 못해 **Threshold Voltage가 상대적으로 높아지게** 된다.( Vth > 0 )

반면에 `Floating Gate`에 전자가 없는 셀은 **Electric Field 간섭이 일어나지 않으므로** **Threshold Voltage가 낮다.**( Vth < 0)

따라서 읽고자 하는 셀에는 0V의 전압을 인가하고 나머지 셀에는 6 ~ 6.5V 전압을 인가한다.

만약 `Floating Gate`상에 전자가 있다면 0V 전압을 인가하였으므로 나머지 셀은 `Channel`이 형성되는 반면, 읽고자 하는 셀에서는 `Open` 상태가 된다. 그러므로 전류가 잘 흐르지 않게 되고 이를 `'0'`이라는 데이터로 인식하게 된다.

반대로 `Floating Gate`상에 전자가 없다면 0V의 전압을 인가하더라도 `Channel`이 형성되어 전류가 잘 흐르게 되어 이를 `'1'`이라는 데이터로 인식하게 된다.

### - Erase

`Floating Gate`에 있는 전자를 밖으로 방출하기 위해 `Body`와 `Source`에 `Positive Voltage`를 역으로 인가하는 방식이다.

- 플래시메모리에 데이터를 저장하기 위해서는 플로팅게이트를 비울 필요가 있다.

- 저장될 데이터가 `0`(전자저장필요)인지 `1`(전자저장불필요)인지 알 수 없는 상황에서 `Floating Gate`에 전자가 남아있다면 제대로 데이터를 기록할 수 없다.

  => 따라서 `Floating Gate`를 완전히 비워야한다.

- **방법은 쓰기의 반대이다.**

- 바디쪽에 12~24V의 강력한 전압을 걸어 쓰기때와 반대방향의 전계를 형성해서 `Floating Gate`내의 전자를 바디쪽으로 끌어낸다.

- 소스측에도 `Positive Voltage`를 걸어 쓰기 때와 반대 현상이 일어나도록 유도한다.

***SSD의 경우 이렇게 각 셀의 데이터를 지우는 작업을 쓰기작업이 발생하기 전에 미리 진행해 놓는데 이를 `Trim`이라고 한다. 이는 쓰기 속도향상에 필수적인 과정이다.***



## 낸드플래시의 수명

낸드플래시의 읽기, 쓰기, 지우기 과정을 보면 왜 낸드플래시에 수명이 존재하는지 알 수 있다.

`Floating Gate` 내의 전자가 외부로 유출되지 않고 외부전자가 `Floating Gate`로 유입되지 않도록 막는 것이 산화막의 역할임을 생각했을 때, **산화막의 수명이 곧 플래시메모리셀의 수명**이다.

읽기, 쓰기 과정에서 전자는 산화막을 통과하는 데 앞서 말했듯이 산화막은 본래 전자가 통과할 수 없는 장벽이다. 이를 억지로 통과했으니 통과할 때마다 산화막에 손상이 발생하는 것이다.

그리고 읽기/쓰기 횟수가 늘어날 수록 전자의 통과횟수는 증가할 것이고 산화막의 손상도 누적될 것이다.

그러다가 손상이 어느 수준을 넘어서면 더이상 산화막은 제역할을 못하고 읽기/쓰기 횟수가 제한 되는 것이다. => 수명이 있는것이다..

```bash
# Note
- 읽기 과정을 보면 전자가 산화막을 통과하지 않는다.
- 읽기 과정에서 전자가 유출될 수 있고, 셀의 열화가 진행된 상태에서 읽기만으로 데이터가 유실되면 그걸 복구하기위한 재기록 과정에서 열화가 가중되고, 그 과정에서 전압의 영향을 일부지만 다른 셀도 받기 때문에 읽기와 수명이 완전히 연관이 없다고 볼 수는 없지만, 과정이 수명 감소에 끼치는 영향이 쓰기/지우기에 비해 적은건 사실이다.
```

# MLC와 TLC

---

앞서 설명한 플래시메모리는 한 셀에 1비트만 저장이 가능했다. 0아니면 1이었으니까.

이런 셀을 무수히 많이 집적해서 플래시 메모리를 만드는데 셀이 10억개 집적되어 있으면 `1Gb(128MB)`의 용량을 갖는식으로.

플래시메모리는 저장매체이니 더 많은 저장용량을 원하는건 당연한 요구이다. 그래서 머리를 굴리는 것이 **"한 셀에 2비트씩 저장해보자!!"**

한셀에 저장되는 용량이 2배면 셀의 개수(메모리 다이의 크기)를 늘리지 않아도 저장용량이 단숨에 2배로 늘어난다.



?? 그럼 이것을 어떻게 구현할 것인가?

`전자있다(0)`, `전자없다(1)` 의 단순한 구분을 뛰어넘어서

`전자없다(11)`, `전자적다(10)`, `전자많다(01)`, `전자아주많다(00)`로 구분을 세분화 한것이다.

이를 기록하기 위해서 쓰기 과정에서 각 신호별 `Control Gate` 전압이 세분화된다.

`Control Gate`의 전압에 따라 저장되는 전자의 양이 결정되고 전자의 양으로 신호를 구분하게 된다. 

![image-20200814143821558](https://user-images.githubusercontent.com/58545240/90222202-94939b80-de46-11ea-89d8-770d3efdd245.png)

이렇게 한 셀에 2비트를 저장하게 된 제품을 **MLC(Multi Level Cell)**라고 한다.

여기서 더 나아간 것이 **TLC(Triple Level Cell)**이다. 한 셀에 3비트를 저장하는 것이다.

구분해야할 신호가 8개로 늘었꼬 신호별 `Control Gate` 전압은 더욱 촘촘해졌다.

![image-20200814143925178](https://user-images.githubusercontent.com/58545240/90222214-98bfb900-de46-11ea-97d3-34b92e0691bd.png)

## 왜 MLC, TLC로 갈 수록 쓰기 속도가 느린가?

쓰기를 위한 `Control Gate` 전압이 한방에 구현되지 않기 때문이다.

![image-20200814144028230](https://user-images.githubusercontent.com/58545240/90222226-9d846d00-de46-11ea-8a84-5c7657f3a32e.png)

TLC에서 요구하는 8가지 상태를 기록하기 위해서는 7개의 전압이 필요한데 (전자가 없는 상태는 CG전압이 필요없으니 하나가 빠져서 7개 )

이 7가지 단계가 한번에 기록되는 것이 아니다.

위 단계를 보면 3단계에서 걸쳐서 이루어지는데

1. erased상태를 벗어나고
2. 7개의 상태로 대략적으로 조정하고
3. 다른 신호와 겹치지 않게 더 정밀하게 조정한다.

**SLC(Single Level Cell)**에 비해 기록단계를 더 거치기 때문에 쓰기에 더 긴 시간이 걸리고 쓰기 속도가 느려지는 것이다.

읽기 속도에서 `SLC`, `MLC`, `TLC` 모두 큰 차이가 없는 것은 읽기원리를 보면 답이 나온다. 그 과정은 비슷하기 때문에!



## 왜 MLC, TLC로 갈 수록 수명이 급격히 줄어드는가?

상황을 가정해보겠다. 산화막의 손상이 발생했다.

- SLC에서 **`0`**을 기록하기 위해 전압을 걸지 않았다. 산화막 손상때문에 약간 전자가 들어왔지만 어차피 신호는 전자가 많고 적은 상태만 구분하면 되기 때문에 읽기 과정에서 문제없이 **`0`**으로 인식되었다.
- MLC에서 **`01`**을 기록하기 위한 전압을 걸었다. 산화막 손상때문에 예상보다 많은 전자가 들어오긴했지만 **`10`**상태의 전자량보다 적다. 읽기과정에서 정상적으로 **`01`**이라고 인식되었다.
- TLC에서 **`001`**이라는 내용을 기록하기 위한 전압을 걸었다. 그런데 산화막이 손상되어 예상보다 많은 전자가 `Floating Gate`에 저장되었다. 늘어난 전자량 때문에 읽기과정에서 **`001`**이 아닌 **`010`**으로 인식되었다. 이러면서 오류가 생긴다. 특별한 조치가 없는 한 이셀은 수명을 다한것이다.

=> `SLC`, `MLC`, `TLC`로 갈수록 각 신호의 구분은 더 촘촘해지고, 더 정밀하게 전자의 수가 조절되어야 한다. 산화막의 손상으로 인해 전자의 수가 컨트롤되지 않았을 때 가장 민감하게 반응하는것이 **`TLC`**일 수 밖에 없다. 이 때문에 `MLC, TLC`로 갈수록 수명이 기하급수 이상으로 빠르게 감소하는 것이다.

# 공정미세화가 진행될 수록 수명이 줄어드는 이유

---

다이 크기를 줄이기 위해서 공정미세화는 필수 불가결한 요소이지만 문제는 그에 따라 수명이 감소한다는 것이다. 

제조 공정이 발전할 수록 셀의 크기는 줄어들고 한 셀에 저장할 수 있는 전자의 수도 줄어든다. 더욱 더 미세하게 각 신호별 전자수를 조절해야 한다.

누설로 인한 전자 수의 변화에 더욱 민감해질 수 밖에 없다.

# 생명연장의 꿈

---

> 그렇다고 손놓고 있을 엔지니어들이 아니다. **대용량**이라는 절대 무적의 메리트를 쉽게 포기할 수는 없다. 갖가지 수명연장 대책이 나오지만 여기서는 그 일부만 소개하겠다.

## 웨어레벨링

> Wear-Leveling

각 셀의 산화막의 수명은 대부분 비슷하다. 이런 상황에서 특정 셀만 집중적으로 사용하면 그 셀의 산화막만 집중적으로 손상받게 되고, 그 셀만 먼저 수명을 다할것이다.

이러면 전체용량이 감소되는 것이기 때문에 달갑지 않다. 이런 사태를 예방하는 것이 **웨어레벨링**이다.

쉽게말하면, **각 셀을 골고루 사용해서 전체 셀의 수명을 일정하게 관리**해주는 것이다. 전체적인 수명을 최대로 사용할 수 있는 것!

## ECC

> Error Check and Correct : 에러 수정 기능

`Controller`의 에러 수정 기능을 강화한다.

공정이 작거나 `TLC`인 경우 같이 수명이 짧을 수록 더 높은 정밀도의 `ECC`가 필요해진다.

현재는 `16~24bit ECC`를 사용하는 것으로 알려져있다.

![image-20200814150032343](https://user-images.githubusercontent.com/58545240/90222235-a37a4e00-de46-11ea-836d-f6d2c2b6b685.png)

![image-20200814150042337](https://user-images.githubusercontent.com/58545240/90222242-a8d79880-de46-11ea-90dc-26b09e01ed66.png)

SLC에서 MLC, TLC, QLC로 갈수록 각 데이터를 지정하고자 하는 그 간격이 좁아진다. 때문에 데이터를 저장하고 읽는 과정에서 생기는 오류도 많아지게 되는데, `ECC`는 이런 데이터의 오류를 검출하고 수정하기 위해서 오류정보를 저장하는 것이다.

`ECC`값은 여분의 공간에 입력하고 우리는 그 값과 비교를 하게 된다. 그렇지 않은 경우 error를 수정하거나 보정하여 출력하게 되는 것 그래서 더욱 정밀해질수록 복잡해진다.

저장해야할 `ECC code`의 용량 또한 증가하게 되고 그만큼 오류가 많이 생기니 해당하는 것을 하나씩 해석하기 위한 시간또한 필요하게 된다..

![image-20200819111502694](https://user-images.githubusercontent.com/58545240/90584527-bd80ab80-e20d-11ea-89d3-d2101b791bee.png)

## 디지털신호처리

> DSP

기록전압에 따라 전자저장량이 달라지는 걸 감지해서 각 신호별 기록전압을 셀 상태에 맞춰 변화시켜주는 것이다.

산화막 손상으로 이전보다 전자가 더 많이 저장되면 기록전압을 낮춰서 저장되는 전자수를 줄여서 원래 수준으로 맞추는 식으로 쓰기/지우기 전압을 변화시킨다.

![image-20200814150144608](https://user-images.githubusercontent.com/58545240/90222264-b2f99700-de46-11ea-991b-9d5f60fc3f00.png)

## 오버 프로비저닝

> Over Provisioning

이 방법은 셀의 수명연장이라기보다 **제품의 수명연장**이라고 할 수 있다.

위의 방식들을 사용한다 해도 결국 메모리셀의 사망을 피할 수는 없다.

**`Over Provisioning`은 여분의 메모리셀을 확보해놓고 사망하는 셀을 대체**하는 기술이다.

예비용량을 확보해뒀다가 수명이 다해서 `Bad-Block`이 발생한 셀이 나타나면 예비 셀로 대체해서 전체 용량을 일정하게 유지하는 것이다.

# NANDFLASH_F59L2G81A:zap: 

---

## 특징

- 전압공급 : 3.3V(2.7V ~ 3.3V)
- 조직
  - 메모리 셀 어레이 : (256M + 16M) X 8bit
  - 데이터 레지스터 : (2K + 64) X 8bit
- 자동 프로그램 및 지우기
  - 페이지 프로그램 : (2K + 64) Byte
  - 블록 지우기 : (128 + 4K) Byte
- 페이지 읽기 작업
  - 페이지 크기 : (2K+64) Byte
  - 랜덤 읽기 : 25us(최대)
  - 직렬 액세스 : 25ns(최소)
- 메모리 셀 : 1bit /메모리 셀
- Fast Write Cycle Time(빠른 주기 시간)
  - 프로그램 시간 : 250us (Typ.)
  - 블록 지우기 시간 : 2ms (Typ.)
- 명령 / 주소 / 데이터 다중 I/O포트
- 하드웨어 데이터 보호
  - 전원 전환 중 프로그램 / 삭제 잠금
- 신뢰할 수 있는 CMOS 플로팅 게이트 기술
  - ECC 요구사항 : 4bit / 512Byte
  - 내구성 : 100K 프로그램 / 지우기 주기
  - 데이터 보유 : 10년
- 명령 레지스터 작업
- 파워업 옵션에서 자동 페이지 0 읽기
  - NAND 지원에서 부팅
  - 자동 메모리 다운로드
- NOP : 4 cycles
- 고성능 프로그램을 위한 캐시 프로그램 운영
- 캐시 읽기 작업
- 카피 백 작업
  - EDO 모드
  - OTP 운영
  - Two-Plane 운영

### Copy-Back

> - 페이지를 읽고 읽은거를 계속 쓰는 것.
> - 데이터의 복사를 칩 내부에서 해결해 데이터의 전송 오버헤드를 줄임.

Copy-Back Program은 저장된 데이터에서 비트 오류가 감지되지 않은 경우 시간 소모적 인 데이터 재로드없이 메모리 셀에 저장된 데이터를 효율적으로 복사 할 수 있도록 설계되었습니다.

특히 블록의 일부가 업데이트되고 나머지 블록은 새로 할당 된 빈 블록에 복사되어야 할 때 이점이 분명합니다.

Copy-Back 작업은 Copy-Back에 대한 읽기 및 대상 주소가있는 Copy-Back 프로그램의 순차적 실행입니다.

"35h"명령과 소스 주소를 사용하여 Copy-Back 작업을 읽기는 전체 2,112 바이트 데이터를 내부 버퍼로 이동합니다.

호스트 컨트롤러는 데이터 출력을 순차적으로 읽어 비트 오류를 감지 할 수 있습니다.

Copy-Back 프로그램은 Destination 주소와 함께 Page-Copy Data-Input 명령 (85h)을 실행하여 시작됩니다.

비트 오류를 수정하고 오류 전파를 방지하기 위해 데이터 수정이 필요한 경우 대상 주소 뒤에 데이터를 다시로드 할 수 있습니다.

다음 그림과 같이 데이터 수정을 여러 번 반복 할 수 있습니다.

프로그램 확인 명령 (10h)이 발행되면 실제 프로그래밍 작업이 시작됩니다.

프로그램 프로세스가 시작되면 상태 레지스터를 읽기 위해 상태 읽기 명령 (70h)을 입력 할 수 있습니다.
호스트 컨트롤러는 R / B 출력 또는 상태 레지스터의 상태 비트 (I / O6)를 모니터링하여 프로그램주기의 완료를 감지 할 수 있습니다.

Copy-Back 프로그램이 완료되면 상태 비트 (I / O0)를 확인할 수 있습니다.

명령 레지스터는 다른 유효한 명령이 기록 될 때까지 읽기 상태 모드로 유지됩니다.

## 주문정보

![image-20200818102647562](https://user-images.githubusercontent.com/58545240/90494416-726b8780-e17e-11ea-8a1b-d7ef47bc5bd2.png)

## 일반적인 설명

이 장치는 여분의 16Mx8bit**(Spare Byte Size)** 용량이있는 256Mx8bit**(Main Byte Size)**입니다.

이 장치는 3.3V VCC 전원 공급 장치로 제공됩니다.
NAND 셀은 솔리드 스테이트 대용량 스토리지 시장에 가장 비용 효율적인 솔루션을 제공합니다.

메모리는 독립적으로 지울 수있는 블록으로 나누어 져있어 오래된 데이터는 지워지는 동안 유효한 데이터를 보존 할 수 있습니다.

이 32개의 시리즈로 연결된 플래시 셀의 두 개의 NAND구조로 구성된 64 페이지**(Block Page Size)**로 구성된 2048 개의 블록 **(Plane Block Size * Total Planes)**이 있습니다.

프로그램 작업을 통해 2112-Word 페이지를 일반적인 250us로 쓸 수 있으며 지우기 작업은 X8 장치 블록의 경우 128K-Byte에서 일반적인 2ms로 수행 할 수 있습니다.

페이지 모드의 데이터는 워드 당 25ns 주기로 읽을 수 있습니다.

II / O 핀은 주소 및 명령 입력과 데이터 입력 / 출력을위한 포트 역할을합니다.

카피 백 기능을 사용하면 결함 블록 관리를 최적화 할 수 있습니다. 페이지 프로그램 작업이 실패하면 시간 소모적 인 직렬 데이터 삽입 단계없이 동일한 어레이 섹션 내의 다른 페이지에서 데이터를 직접 프로그래밍 할 수 있습니다.

캐시 프로그램 기능을 사용하면 데이터 레지스터가 플래시 어레이에 복사되는 동안 캐시 레지스터에 데이터를 삽입 할 수 있습니다.

이 파이프 라인 된 프로그램 작업은 긴 파일이 메모리 내부에 기록 될 때 프로그램 처리량을 향상시킵니다.

캐시 읽기 기능도 구현됩니다.

이 기능을 사용하면 연속 페이지를 스트리밍해야 할 때 읽기 처리량을 크게 향상시킬 수 있습니다.

이 장치에는 추가 기능이 포함되어 있습니다. 전원을 켤 때 자동 읽기.

---

## PIN CONFIGURATION

> TSOPI 48L, 12mm X 20mm Body, 0.5mm Pin Pitch

![image-20200818110910822](https://user-images.githubusercontent.com/58545240/90494440-79929580-e17e-11ea-9419-42a3fb284fee.png)

## BALL CONFIGURATION

> BGA 63 BALL, 9mm X 11mm Body, 0.8 Ball  Pitch

![image-20200818110932476](https://user-images.githubusercontent.com/58545240/90494483-81ead080-e17e-11ea-9cc8-c18db03d82b4.png)

## Pin Description

![image-20200818111034890](https://user-images.githubusercontent.com/58545240/90494501-88794800-e17e-11ea-8760-dd931535f0d9.png)

**=>** 

| Symbol    | Pin Name              | Functions                                                    |
| --------- | --------------------- | ------------------------------------------------------------ |
| I/O0~I/O7 | Data Inputs / Outputs | I / O 핀은 명령, 주소 및 데이터를 입력하고 읽기 작업 중에 데이터를 출력하는 데 사용됩니다.<br/>I / O 핀은 칩이 선택 해제되거나 출력이 비활성화되면 Hi-Z로 플로팅됩니다. |
| CLE       | Command Latch Enable  | LE 입력은 내부 명령 레지스터로 전송 된 명령의 활성화 경로를 제어합니다.<br/>명령은 CLE가 높은 WE 신호의 상승 에지에있는 I / O 포트를 통해 명령 레지스터에 래치됩니다. |
| ALE       | Address Latch Enable  | ALE 입력은 내부 주소 레지스터로 전송되는 주소의 활성화 경로를 제어합니다.<br/>주소는 ALE가 높은 WE의 상승 에지에있는 I / O 포트를 통해 주소 레지스터에 래치됩니다. |
| CE        | Chip Enable           | CE 입력은 장치 선택 컨트롤입니다. 장치가 Busy 상태에있을 때 CE high는 무시되고 장치는 프로그램 또는 지우기 작업에서 대기 모드로 돌아 가지 않습니다.<br/>읽기 동작 중 CE 제어에 대해서는 장치 작동의 '페이지 읽기'섹션을 참조하십시오. |
| RE        | Read Enable           | RE 입력은 직렬 데이터 출력 제어이며 활성 로우 일 때 데이터를 I / O 버스로 구동합니다.<br/>데이터는 내부 열 주소 카운터를 1 씩 증가시키는 RE의 하강 에지 이후에 유효한 tREA입니다. |
| WE        | Write Enable          | WE 입력 컨트롤은 I / O 포트에 기록합니다.<br/>명령, 주소 및 데이터는 WE 펄스의 상승 에지에서 래치됩니다. |
| WP        | Write Project         | WP 핀은 전원 전환 중에 의도하지 않은 프로그램 / 삭제 보호 기능을 제공합니다.<br/>내부 고전압 발생기는 WP 핀이 액티브 로우 일 때 리셋됩니다. |
| R/B       | Ready / Busy Output   | R / B 출력은 장치 작동 상태를 나타냅니다.<br/>낮으면 프로그램, 지우기 또는 임의 읽기 작업이 진행 중임을 나타내며 완료되면 높음 상태로 돌아갑니다.<br/>이는 오픈 드레인 출력이며 칩이 선택 해제되거나 출력이 비활성화 될 때 Hi-Z 상태로 플로팅되지 않습니다. |
| Vcc       | Power                 | VCC는 장치의 전원 공급 장치입니다.                           |
| Vss       | Ground                |                                                              |
| NC        | No Connection         | 리드는 내부적으로 연결되어 있지 않습니다.                    |

```bash
# Note
각 장치의 모든 VCC 및 VSS 핀을 공통 전원 공급 장치 출력에 연결합니다. VCC 또는 VSS를 분리 된 상태로 두지 마십시오.
```

### Hi-Z 상태

**디지털 회로에서의 하이 임피던스**

- 흔히들 MCU를 사용하여 Port제어를 할 때 `Hi-Z`상태라는 말을 자주 사용한다. 일단 하이 임피던스는 말 뜻 그대로 임피던스(교류저항)이 크다는 말이다.

  **> 하이 임피던스가 필요한 이유**

  ​	만약 한 개의 Data Bus에 여러 개의 메모리 소자가 연결되어 있다고 가정할 경우 메모리 소자가 모두 Data를 출력 하도록 설정되어 있을 경우 메모리 소자	간에 충돌이 발생하게 된다.

  ​	따라서 이를 해결하기 위해서 `Hi-Z`상태가 있어 다른 메모리가 Data를 출력하는 경우 출력 하는 Data와 상관없도록 하기 위해 필요한 것이다.

  ​	**다시 정리하면 같은 Data 버스를 공유하는 메모리들이 서로간의 충돌을 막기 위해 필요한 것이 `Hi-Z` 상태라고 생각하면 된다.**

  

- 디지털 로직 회로에서 하이임피던스라 함은 출력단자에 적용되는 것이다. 로직이 0이나 1을 나타내는 것은 전압을 가지고 나타내는 것인데, 해당 로직이 출력을 아예 내보내지 않을 때는 하이임피던스 상태로 두게 된다. 다시 말해서 출력 임피던스를 매우 높은 값이 되게 한다는 뜻이고, 그 말은 로직의 출력에 매우 높은 임피던스의 저항이 직렬로 연결된 것처럼 동작하게 한다는 뜻이고, 결과적으로는 전기 회로상에서 거의 단절된 것과 같게 만든다는 뜻이다.

- 이것을 tri-state(3가지 상태) output이라고 부를 수 있다. 여러 개의 로직 디바이스가 연결되어 있다고 하고 그 출력이 하나의 버스에 병렬로 묶여있다고 하면, 각각의 디바이스가 0 혹은 1 상태만을 내보낸다고 하면 모든 디바이스의 출력이 같다고 하면 모를까 서로 다른 경우에는 문제가 발생하게 된다. 그럴 때 출력을 내보낼 디바이스만 0 혹은 1을 내보내고 나머지 디바이스의 출력을 Hi-Z상태로 두면 사실상 이들 디바이스는 회로로부터 단절된 상태가 되니까 다른 디바이스의 출력에 영향을 주지 않게 된다.

- 다시 말해서 Hi-Z 출력을 둠으로써 하나의 버스(=와이어의 묶음)를 여러 개의 디바이스가 공유할 수 있게 되는 것이다. 이 얘기는 출력 임피던스가 크게 잡혀 있으면 실질적으로 외부에 미치는 영향이 미미해진다는 것도 아울러 이해할 수 있다.

**오디오 기기에서의 하이 임피던스**

- 반대로 오디오 기기에서는 하이 임피던스 입력은 중요한 의미를 갖는다. 이것은 출력 임피던스가 큰 장치가 그것에 연결된 다른 디바이스를 드라이브하는 힘이 미약한 경우에도 그 출력을 잘 받아들일 수 있다는 것을 의미한다. 출력 임피던스가 작은 경우는 말할 것도 없이 잘 받아낼 수 있다.
- 이것은 입력단에 입력임피던스가 매우 큰 FET(Field effect transistor:전계효과 트랜지스터:전기장(electric field)의 성질을 이용하여 전류를 제어하는 트랜지스터)가 달려있다는 것을 의미한다.
- 여기서 FET와 일반 TR(BJT: bipolar junction TR:쌍극 접합 트랜지스터: n과 p형 반도체를 각각 양극으로 붙여놓은 트랜지스터)이 어떻게 다른지 이야기하고 넘어가야 할 것 같다.
- 일반적인 TR(BJT)는 3개의 반도체가 접합되어있는 그림으로 주로 나타내는데, 실제 접합이 그렇게 되어있진 않지만 이해를 돕기 위해 그렇게 나타낸다. 중요한 것은 이들이 서로 붙어있다는 것이고, 접합면이 일종의 다이오드처럼 동작하기 때문에 3개의 단자로 모두 전류가 흘러야 정상정으로 작동한다.

![image-20200818132119967](https://user-images.githubusercontent.com/58545240/90494553-975ffa80-e17e-11ea-81cf-4bb0af6f306a.png)

- 일반적인 증폭작용은 E와 C단자 사이로 흐르는 전류를 B단자에서 컨트롤하는 식으로 이루어지게 되는 것인데, E와 C단자를 흐르는 전류의 양을 B로 들어가는 전류의 양으로 컨트롤 하게 된다. 다시 말해서 B단자로 어느 정도의 전류를 흘려 넣어야 증폭작용(=전류의 흐름제어)을 하게 만들 수 있단 말이다. 또, 다이오드이기 때문에 접합면을 넘기 위한 문턱전압(Threshold Voltage)이 넘어서야 전류를 흘릴 수 있다. 증폭회로에서 입력단으로 어느 정도의 전류가 흘러들어가야 한다는 것은 그 입력에 물리는 어떤 장치가 전류를 어느정도 내보낼 수 있는 능력이 있어야 함을 의미하고, 그렇지 못한다면 정상적인 동작을 하지 못하게 된다거나 신호가 급격히 약해지게 된다는 것도 의미한다. 쉽게 말해서 그 장치의 출력 전압이 고스란히 입력단으로 전달되지 못하는 것을 의미한다.
- 반면에 FET는 D,S 두 단자간의 전류의 흐름을 G(Gate)라는 단자의 전압으로 컨트롤한다. G단자로도 매우 약하게나마 전류가 흐르게 되지만 매우 작기 때문에 사실상 전압으로 전류흐름을 제어하는 성질을 갖게 된다. 따라서, 입력 임피던스가 매우 크다(왜냐면 입력단으로 거의 전류가 흘러갈 필요가 없으므로)고 할 수있고 입력단에 걸리는 전압을 입력단에 물린 장치의 출력 임피던스가 높더라도 잘 전달시켜줄 수 있다는 뜻이 된다.

![image-20200818132208115](https://user-images.githubusercontent.com/58545240/90494589-9f1f9f00-e17e-11ea-9c6d-51e82bcbe638.png)

- 여기서 흔히 나올 수 있는 질문은, 임피던스 문제로 신호가 작아지게 되도 다시 증폭해내면 되지 않느냐 할 수 있다. 증폭해내면 음량을 회복시킬 수 있겠지만, 임피던스가 매칭되지 않는 상황에서 손실되는 신호는 모든 주파수에 대해 일정하지 않기 때문에 특정 음역의 신호가 손실된 상태로 증폭되고, 신호가 손실된 만큼 증폭하다 보면 그 부분의 잡음이 부각되어 나타날 수 밖에 없다.
- 전공자들의 입장이 되면 TR/FET는 전자회로 해석 문제로 변질되어 실제 응용보단 증폭률과 입력/출력 임피던스, 주파수 응답을 얼마나 빠르고 정확하게 계산해 내느냐가 주된 문제가 되고, 반도체쪽 전공자 입장에서는 요구 사항에 맞게 어떻게 설계해야 (그려내야) 하는 가의 문제, 또 어떻게 하면 수율을 높일 수 있느냐의 문제 등이 관심사가 될 것이다. 오히려 비전공자들이 바라보는 세계가 더 재미있다. 전공자들에겐 이게 ‘재미’ 보단 ‘일’에 가깝다. 또 이미 일정 수준 이상의 기술은 사실상 그 수준이 포화에 이르러 ‘이미 다 되어있는데 뭘 더 해야 하나?’하는 생각을 하게 될지도 모를 것이다.
- 요새 많은 오디오용 OPAMP는 입력단이 HI-Z이다. 과거엔 그렇지 않은 opamp가 더 많았지만. 그래서 보급형 오디오카드들도 대부분 HI-Z 입력이다. 다이내믹 마이크 (배터리가 들어가지 않는 마이크, 혹은 팬텀 파워가 필요없는 마이크)를 곧바로 연결해서 쓸 수 있다. 피에조 센서를 사용하거나 매그네틱 픽업을 사용하는 기타를 연결해도 손실이 별로 없이 입력할 수 있다.

### 레지스터

> - 프로세서 레지스터(Processor Register)는 컴퓨터의 프로세서 내에서 자료를 보관하는 아주 빠른 기억 장소.
>
> - 일반적으로 현재 계산을 수행중인 값을 저장하는데 사용된다.
>
> - 대부분의 현대 프로세서는 메인 메모리에서 레지스터로 데이터를 옮겨와 데이터를 처리한 후 그 내용을 다시 레지스터에서 메인 메모리로 저장하는 `로드-스토어 설계`를 사용한다.
> - 레지스터는 메모리계층의 최상위에 위치하며 가장 빠른 속도로 접근 가능한 메모리이다.

**명령 레지스터**

​	현재 실행중인 명령어를 저장한다.

**주소 레지스터**

​	메모리 주소를 저장하여 메모리 접근에 사용되는 레지스터. 어떤 프로세서에서는 주소를 저장하는 것이 아니라 조작하기 위한 목적으로 색인레지스터를 사용

## BLOCK DIAGRAM

![image-20200818135618633](https://user-images.githubusercontent.com/58545240/90494637-a8a90700-e17e-11ea-8630-4fc7350d4601.png)

## ARRAY ORGANIZATION

![image-20200818135642713](https://user-images.githubusercontent.com/58545240/90494680-b78fb980-e17e-11ea-8a4c-4cc787dbc8e5.png)

![image-20200818135716043](https://user-images.githubusercontent.com/58545240/90494697-bd859a80-e17e-11ea-9911-c5e82f31a34a.png)

![image-20200818143357533](https://user-images.githubusercontent.com/58545240/90494712-c1b1b800-e17e-11ea-8295-1cf934ace78e.png)

![image-20200818175133362](https://user-images.githubusercontent.com/58545240/90494729-c5ddd580-e17e-11ea-9afc-ded9ce9f6f5f.png)

Column Address => cycle 2개

Row Address => cycle 3개

Id Cycle => cycle 5개

![image-20200818135752248](https://user-images.githubusercontent.com/58545240/90494750-cc6c4d00-e17e-11ea-8548-bd2e7611a3fa.png)



## PRODUCT INTRODUCTION

이 장치는 2,112x8 열에 의해 128K 행 (페이지)로 구성된 2,112Mbit 메모리입니다.

여분의 64x8 컬럼은 2,048 ~ 2,111의 컬럼 주소에서 위치합니다.

2,112 바이트 데이터 레지스터는 페이지 읽기 및 페이지 프로그램 작업 동안 I / O 버퍼와 메모리 사이의 데이터 전송을 수용하는 메모리 셀 어레이에 연결됩니다.

프로그램 및 읽기 작업은 페이지 단위로 실행되고 삭제 작업은 블록 단위로 실행됩니다.

메모리 어레이는 별도로 지울 수있는 2048 개의 128K 바이트 블록로 구성됩니다.

장치에서 비트 단위 지우기 작업이 금지되었음을 나타냅니다.

장치에는 8 개의 I / O로 멀티플렉싱 된 주소가 있습니다.

이 체계는 핀 수를 극적으로 줄이고 시스템 보드 설계의 일관성을 유지함으로써 향후 밀도로 시스템을 업그레이드 할 수 있도록합니다.

명령, 주소 및 데이터는 모두 CE가 낮을 때 WE를 낮게 설정하여 I / O를 통해 기록됩니다.

그것들은 WE의 상승 에지에 고정되어 있습니다.

명령 래치 활성화 (CLE) 및 주소 래치 활성화 (ALE)는 각각 I / O 핀을 통해 명령과 주소를 다중화하는 데 사용됩니다.

일부 명령에는 하나의 버스 사이클이 필요합니다.
예를 들어, 리셋 명령, 상태 읽기 명령 등은 하나의 사이클 버스 만 필요합니다. 페이지 읽기 및 차단과 같은 다른 명령
삭제 및 페이지 프로그램에는 두 개의 사이클이 필요합니다. 하나는 설정을위한 것이고 다른 하나는 실행을위한 것입니다.

향상된 아키텍처 및 인터페이스 외에도이 장치는 한 페이지에서 다른 페이지로 카피 백 프로그램 기능을 통합합니다.
외부 버퍼 메모리로 (부터) 데이터를 전송할 필요가 없습니다.

## Command Set

![image-20200818153218689](https://user-images.githubusercontent.com/58545240/90494767-d2fac480-e17e-11ea-9f1a-53e29a85859a.png)

## Timing Characteristic for Command

![image-20200818153347590](https://user-images.githubusercontent.com/58545240/90494808-ddb55980-e17e-11ea-8f6d-a71421f2c8c6.png)

![image-20200818153413675](https://user-images.githubusercontent.com/58545240/90494829-e1e17700-e17e-11ea-9861-ad3142ba42ca.png)

## ===Operation Diagram===:ballot_box_with_check: 

> 크게 CLE로 동작되는 커맨드 사이클, ALE로 동작되는 어드레스 사이클, 데이터사이클(WE, RE)로 나뉘어진다.
>
> - 보통 낸드플래시의 작업 순서는 삭제 - 쓰기 - 읽기 이다.

### > ERASE

![image-20200818154115162](https://user-images.githubusercontent.com/58545240/90494853-e73ec180-e17e-11ea-9dd4-0e619e8032b2.png)

1. CLE로 전체 사이클을 시작하고 `ERASE`의 의미로 60h를 때려준다
2. 그 다음은 ALE 사이클이다. 이번에는 `Column Address` 없이 `Row Address`만 적어준다. 그 이유는 블럭단위로 `ERASE`가 일어나기 때문이다.
3. 커맨드 버스 사이클이 CLE 진행되어 지우기 2nd 커맨드가 기록된다.
4. 기둘기둘.....(`tBERS`)
5. `R/B`신호를 GPIO 포트 단자로 감시하다가 종료되는걸 캐치
6. 상태비트 체크!



블록 기반 지우기 작업은 Erase Setup 명령 (60h)에 의해 시작되고 3주기 행 주소가 뒤 따릅니다. 여기서 Plane 주소와 Block 주소 만 유효하고 페이지 주소는 무시됩니다.

행 주소 다음의 지우기 확인 명령 (D0h)은 내부 지우기 프로세스를 시작합니다.

2 단계 명령 시퀀스는 외부 노이즈에 의해 메모리 내용이 실수로 변경되는 것을 방지하도록 설계되었습니다.

Erase Confirm 명령 입력 후 WE의 상승 에지에서 내부 제어 로직은 삭제 및 삭제 확인을 처리합니다.

지우기 작업이 완료되면 호스트 컨트롤러는 상태 비트 (I / O0)를 확인하여 지우기 작업이 성공적으로 완료되었는지 확인할 수 있습니다.

다음 그림은 블록 지우기 시퀀스를 보여 주며, 주소 입력 (선택된 블록의 첫 번째 페이지 주소)은 명령 60h와 D0h 사이에 배치됩니다.

tBERASE 삭제 시간 후 R / B는 준비 상태로 어설 션을 해제합니다.

읽기 상태 명령 (70h)은 삭제 작업의 실행 상태를 확인하기 위해 D0h 직후에 실행할 수 있습니다.

### > PROGRAM(Write)

![image-20200818154654251](https://user-images.githubusercontent.com/58545240/90494873-ec9c0c00-e17e-11ea-9ac0-a27bf8625bd0.png)

1. `READ`와 똑같이 CLE로 전체 사이클을 시작하고, 기록 커맨드로 80h를 때린다.
2. CLE가 Low가 되고 ALE가 High가 되면서 `Column Address`와 `Low Address`를 차례로 기록한다.
3. ALE를 Low해주고 바로 쓸 데이터를 WE의 `rising edge`에 맞추어 올려서 보내준다.
4. 쓰기의 두번 째 command인 10h을 때려주면, 그 전에 쓰기 큐에 넣어놨던 데이터들이 타겟 어드레스에 쭉 써진다. 
5. 물론 이 때도 `BUSY` 상태가 된다. 읽기 동작에 비해서 긴 wait가 필요하다. 이걸 **`tPROG`**라고 한다.
6. 그리고 마이크로프로세서가 Busy상태가 종료한 것을 알아차리면 Read Status(70h)로 프로그램이 정상적으로 수행되었는지 확인한다.
7. 읽어낸 상태바이트가 0이면 성공, 1이면 실패를 의미한다.

- 1st 커맨드 사이클(CLE) => 어드레스 사이클(ALE) => 데이터 사이클 => 2nd 커맨드 사이클(CLE) => 대기(BUSY) => 3rd 커맨드 사이클(CLE) => 상태비트 체크



이 장치는 페이지 단위를 기준으로 프로그래밍되며, 삭제 작업을 중단하지 않고 한 페이지에 연속적인 부분 페이지 프로그래밍을 엄격히 금지합니다.

블록 내 페이지 프로그램 작업의 주소 지정은 순차적이어야합니다.

전체 페이지 프로그램주기는 최대 2,112 바이트 (1,056 워드)의 데이터를 캐시 레지스터를 통해 데이터 레지스터에로드 할 수있는 직렬 데이터 입력주기와로드 된 데이터가 지정된 메모리에 프로그래밍되는 프로그래밍 기간으로 구성됩니다. 세포.

직렬 데이터 입력주기는 직렬 데이터 입력 명령 (80h)으로 시작하여 5주기 주소 입력 후 직렬 데이터로드가 이어집니다.

페이지에 프로그래밍되지 않을 바이트는로드 할 필요가 없습니다.

다음 데이터의 열 주소는 임의 데이터 입력 명령 (85h)에 따라 주소로 변경할 수 있습니다.

임의 데이터 입력 명령은 한 페이지에서 여러 번 반복 될 수 있습니다.

페이지 프로그램 확인 명령 (10h)은 프로그래밍 프로세스를 시작합니다.

데이터를 입력하지 않고 10h 만 작성하면 프로그래밍 프로세스가 시작되지 않습니다.

내부 쓰기 엔진은 해당 알고리즘을 자동으로 실행하고 프로그래밍 및 검증 타이밍을 제어하여 호스트 컨트롤러가 다른 작업을 수행 할 수 있도록합니다.

프로그램 프로세스가 시작되면 호스트 컨트롤러는 R / B 출력을 모니터링하거나 상태 읽기 명령을 사용하여 상태 비트 (I / O6)를 읽어 프로그램 사이클의 완료를 감지 할 수 있습니다.

프로그래밍 중에는 상태 읽기 및 재설정 명령 만 유효합니다.

페이지 프로그램 작업이 완료되면 호스트 컨트롤러는 상태 비트 (I / O0)를 확인하여 페이지 프로그램 작업이 성공적으로 완료되었는지 확인할 수 있습니다.

명령 레지스터는 다른 유효한 명령이 기록되지 않는 한 읽기 상태 모드로 유지됩니다.

페이지 프로그램 시퀀스는 다음 그림에 설명되어 있습니다. 여기서 열 주소, 페이지 주소 및 데이터 입력은 80h와 10h 사이에 있습니다.

tPROG 프로그램 시간 후 R / B는 준비 상태로 어설 션을 해제합니다.

상태 읽기 명령 (70h)은 10 시간 후 바로 실행할 수 있습니다.

### > READ

![image-20200818155350680](https://user-images.githubusercontent.com/58545240/90494904-f58cdd80-e17e-11ea-84fc-17d6d5219596.png)

1. WE 신호의 `Rising Edge`시간에 낸드 플래시 읽기 1st(00h) 커맨드가 시작... CLE가 On되어있을 때는 WE의 라이징 엣지에 걸리는 버스에 걸리는 Data를 `command latch`로 전달해준다.
2. CLE가 Low가 되고 ALE가 High가 되면서 어드레스를 `Column`, `Row`의 순으로 팍팍 넣어준다. WE가 라이징 엣지일 때 낸드플래시에 `Column`과 `Row` 어드레스가 차례대로 기록될 것이다.
3. 마지막으로 커맨드 버스 사이클 CLE가 High가 되고 읽기 2nd(30h) 커맨드가 똭 들어간다.
4. 읽는다.. 이 때는 데이터가 대기 버퍼 큐에 준비가 되는 시간으로 보면 된다. 이 때 마이크로 프로세서는 낸드 플래시의 `R/B` 신호를 GPIO포트 단자로 계속 감시한다.
5. `Busy`가 끝나면서 마이크로 프로세서는 RE의 `Rising Edge`에 맞춰 팍팍 읽어주면 된다.

- 1st 커맨드 사이클(CLE) => 어드레스 사이클(ALE) => 2nd 커맨드 사이클(CLE) => 대기(BUSY) => 데이터 사이클(Address)



초기 장치 전원을 켜면 장치는 기본적으로 읽기 모드로 설정됩니다.

이 작업은 00h 명령, 5주기 주소 및 30h 명령을 작성하여 시작됩니다.

초기 전원을 켠 후 00h 명령은 명령 레지스터에 래치되었으므로 건너 뛸 수 있습니다.

한 페이지에있는 2,112Byte의 데이터는 다음을 통해 캐시 레지스터로 전송됩니다.
25us (tR) 이내의 데이터 레지스터.

호스트 컨트롤러는 R / B 출력을 확인하여이 데이터 전송 완료를 감지 할 수 있습니다.

선택한 페이지의 데이터가 캐시 레지스터에로드되면 RE를 지속적으로 펄싱하여 25ns주기 시간 내에 각 바이트를 읽을 수 있습니다.

RE 클럭 신호의 반복적 인 high-to-low 전환은 장치가 지정된 열 주소에서 시작하여 마지막 열 주소로 데이터를 출력하게합니다.

장치는 Random Data Output 명령을 사용하여 순차적 열 주소 대신 임의의 열 주소에 데이터를 출력 할 수 있습니다.

임의 데이터 출력 명령은 한 페이지에서 여러 번 실행할 수 있습니다.

전원을 켠 후 장치는 읽기 모드에 있으므로 읽기 작업을 시작하는 데 00h 명령주기가 필요하지 않습니다.

페이지 읽기 시퀀스는 다음 그림에 설명되어 있습니다. 여기서 열 주소, 페이지 주소는 명령 00h와 30h 사이에 있습니다.

tR 읽기 시간 후에 R / B는 준비 상태로 어설 션을 해제합니다. 상태 읽기 명령 (70h)은 30 시간 후에 바로 실행할 수 있습니다.

호스트 컨트롤러는 RE를 전환하여 지정된 열 주소와 연속 바이트로 시작하는 데이터에 액세스 할 수 있습니다.

### > COPY BACK

![image-20200818174552824](https://user-images.githubusercontent.com/58545240/90494947-ff164580-e17e-11ea-87b1-2fda6b41fb65.png)

Copy-Back Program은 저장된 데이터에서 비트 오류가 감지되지 않았을 때 시간 소모적 인 데이터 재로드없이 메모리 셀에 저장된 데이터를 효율적으로 복사 할 수 있도록 설계되었습니다.

특히 블록의 일부가 업데이트되고 나머지 블록은 새로 할당 된 빈 블록에 복사되어야 할 때 이점이 분명합니다.

Copy-Back 작업은 Copy-Back 및 Copy-Back 프로그램을 대상 주소로 읽기를 순차적으로 실행하는 것입니다.

"35h"명령과 소스 주소를 사용하여 Copy-Back 작업을 읽기는 전체 2,112 바이트 데이터를 내부 버퍼로 이동합니다.

호스트 컨트롤러는 데이터 출력을 순차적으로 읽어 비트 오류를 감지 할 수 있습니다.

Copy-Back 프로그램은 Destination 주소와 함께 Page-Copy Data-Input 명령 (85h)을 실행하여 시작됩니다.

비트 오류를 수정하고 오류 전파를 방지하기 위해 데이터 수정이 필요한 경우 대상 주소 뒤에 데이터를 다시로드 할 수 있습니다.

다음 그림과 같이 데이터 수정을 여러 번 반복 할 수 있습니다.

실제 프로그래밍 작동은 프로그램 확인 명령 (10h)이 발행 될 때 시작됩니다.

프로그램 프로세스가 시작되면 상태 레지스터를 읽기 위해 상태 읽기 명령 (70h)을 입력 할 수 있습니다.

호스트 컨트롤러는 R / B 출력 또는 상태 레지스터의 상태 비트 (I / O6)를 모니터링하여 프로그램주기의 완료를 감지 할 수 있습니다.

Copy-Back 프로그램이 완료되면 상태 비트 (I / O0)를 확인할 수 있습니다.

명령 레지스터는 다른 유효한 명령이 기록 될 때까지 읽기 상태 모드로 유지됩니다.

### > READ ID

![image-20200818175017295](https://user-images.githubusercontent.com/58545240/90494970-050c2680-e17f-11ea-9df1-edcf701ed1b7.png)

이 장치에는 명령 레지스터에 90h를 쓰고 00h 주소를 입력하여 시작되는 제품 식별 모드가 포함되어 있습니다.

4 개의 읽기 사이클은 제조업체 코드 (C8h)와 장치 코드 및 3rd, 4th, 5th 사이클 ID를 각각 순차적으로 출력합니다.
명령 레지스터는 추가 명령이 실행될 때까지 읽기 ID 모드로 유지됩니다.

### > READ STATUS

장치의 상태 레지스터는 프로그램 또는 지우기 작업이 완료되었는지 여부와 작업이 성공적으로 완료되었는지 확인하는 데 사용됩니다.

70h / F1h 명령을 명령 레지스터에 쓴 후 읽기 사이클은 상태 레지스터의 내용을 CE 또는 RE의 하강 에지 중 마지막에 발생하는 I / O 핀에 출력합니다.

이 두 명령을 사용하면 R / B 핀이 공통 배선 된 경우에도 시스템이 여러 메모리 연결에서 각 장치의 진행 상황을 폴링 할 수 있습니다.

RE 또는 CE는 상태 변경을 위해 전환 할 필요가 없습니다.

명령 레지스터는 다른 명령이 실행되지 않는 한 읽기 상태 모드로 유지됩니다.

따라서 임의 읽기주기 동안 상태 레지스터를 읽으면 읽기주기를 시작하기 위해 읽기 명령 (00h)이 필요합니다.

![image-20200819175857722](https://user-images.githubusercontent.com/58545240/90615738-7e6f4c00-e247-11ea-99a6-c07e05124749.png)

### > Mask Out Initial Invalid Block(s)

초기 무효 블록은 신뢰성이 ESMT에 의해 보장되지 않는 하나 이상의 초기 무효 비트를 포함하는 블록으로 정의됩니다.

초기 무효 블록에 대한 정보를 초기 무효 블록 정보라고합니다.

초기 유효하지 않은 블록이있는 장치는 모든 유효한 블록이있는 장치와 품질 수준이 같고 AC 및 DC 특성이 동일합니다.

초기 유효하지 않은 블록 (들)은 선택 트랜지스터에 의해 비트 라인과 공통 소스 라인과 분리되어 있기 때문에 유효한 블록의 성능에 영향을 미치지 않습니다.

시스템 설계는 주소 매핑을 통해 초기 유효하지 않은 블록을 마스킹 할 수 있어야합니다.

00h 블록 주소에 배치 된 첫 번째 블록은 4bit / 512Byte ECC를 사용하여 최대 1K 프로그램 / 지우기 사이클까지 유효한 블록이됩니다.

### > Reset

![image-20200819181035655](https://user-images.githubusercontent.com/58545240/90615772-85965a00-e247-11ea-9f9d-f7cd1b5f71db.png)

이 장치는 명령 레지스터에 FFh를 기록하여 실행되는 재설정 기능을 제공합니다.

임의 읽기, 프로그램 또는 지우기 모드 동안 장치가 사용 중 상태에 있으면 재설정 작업이 이러한 작업을 중단합니다.

변경되는 메모리 셀의 내용은 데이터가 부분적으로 프로그래밍되거나 지워 지므로 더 이상 유효하지 않습니다.
명령 레지스터는 다음 명령을 기다리기 위해 지워지고 WP가 높으면 상태 레지스터가 값 C0h로 지워집니다.

장치가 이미 재설정 상태에있는 경우 명령 레지스터에서 새 재설정 명령을 수락합니다.

R / B 핀은 리셋 명령이 기록 된 후 tRST에 대해 로우로 변경됩니다. 다음 그림을 참조하십시오.

# AMBA code:zap: 

---

## [ AmbaNAND.C ]

### > __PRE_ATTRIB_ALIGN()

```C
/* Work Buffer for 1 Block.  */
__PRE_ATTRIB_ALIGN__(AMBA_CACHE_LINE_SIZE) __PRE_ATTRIB_NOINIT__
static UINT8 PseudoCBBufMain[64 * 2 * 1024]
__POST_ATTRIB_NOINIT__ __POST_ATTRIB_ALIGN__(AMBA_CACHE_LINE_SIZE);

__PRE_ATTRIB_ALIGN__(AMBA_CACHE_LINE_SIZE) __PRE_ATTRIB_NOINIT__
static UINT8 PseudoCBBufSpare[64 * 128]
__POST_ATTRIB_NOINIT__ __POST_ATTRIB_ALIGN__(AMBA_CACHE_LINE_SIZE);

/*-----------------------------------------------------------------------------------------------*\
 *  @RoutineName:: AmbaNAND_Lock
 *
 *  @Description:: NAND Lock
 *
 *  @Input      :: none
 *
 *  @Output     :: none
 *
 *  @Return     ::
 *          int : OK(0)/NG(-1)
\*-----------------------------------------------------------------------------------------------*/
int AmbaNAND_Lock(void)
{
#if 1
    if (AmbaLink_Enable) {
        AmbaIPC_MutexTake(AMBA_IPC_MUTEX_NAND, AMBA_KAL_WAIT_FOREVER);
        AmbaINT_SetCpuTarget(AMBA_INT_SPI_ID104_FIO_CMD, 0x1);
        AmbaINT_SetCpuTarget(AMBA_INT_SPI_ID105_FIO_DMA, 0x1);
        AmbaINT_SetCpuTarget(AMBA_INT_SPI_ID102_FDMA, 0x1);

        AmbaINT_IntEnable(AMBA_INT_SPI_ID104_FIO_CMD);
        AmbaINT_IntEnable(AMBA_INT_SPI_ID102_FDMA);
        AmbaINT_IntEnable(AMBA_INT_SPI_ID102_FDMA);

        return OK;
    }
#endif
    /*---------------------------------------------------*\
     * Take the Mutex
    \*---------------------------------------------------*/
    if (AmbaKAL_MutexTake(&_AmbaNAND_Ctrl.Mutex, AMBA_KAL_WAIT_FOREVER) != OK)
        return NG;  /* should never happen */

    return OK;
}
```

### > AmbaNAND_Unlock()

```C
/*-----------------------------------------------------------------------------------------------*\
 *  @RoutineName:: AmbaNAND_Unlock
 *
 *  @Description:: Unlock NAND
 *
 *  @Input      :: none
 *
 *  @Output     :: none
 *
 *  @Return     ::
 *          int : OK(0)/NG(-1)
\*-----------------------------------------------------------------------------------------------*/
int AmbaNAND_Unlock(void)
{
#if 1
    if (AmbaLink_Enable) {
        AmbaIPC_MutexGive(AMBA_IPC_MUTEX_NAND);

        return OK;
    }
#endif
    /*---------------------------------------------------*\
     * Release the Mutex
    \*---------------------------------------------------*/

    return AmbaKAL_MutexGive(&_AmbaNAND_Ctrl.Mutex);
}
```

### > AmbaNAND_Init()

```c
/*-----------------------------------------------------------------------------------------------*\
 *  @RoutineName:: AmbaNAND_Init
 *
 *  @Description:: Initialize NAND data structure.
 *
 *  @Input      :: none
 *
 *  @Output     :: none
 *
 *  @Return     ::
 *          int : OK(0)/NG(-1)
\*-----------------------------------------------------------------------------------------------*/
int AmbaNAND_Init(void)
{
    // _AmbaNAD_Ctrl을 _Amaba_Ctrl의 사이즈 크기만큼 0x0으로 설정
    memset(&_AmbaNAND_Ctrl, 0x0, sizeof(_AmbaNAND_Ctrl));

    /* Create Event Flag */
    if (AmbaKAL_EventFlagCreate(&_AmbaNAND_Ctrl.EventFlag) != OK)
        return NG;  /* should never happen ! */

    /* Create Mutex */
    if (AmbaKAL_MutexCreate(&_AmbaNAND_Ctrl.Mutex) != OK)
        return NG;  /* should never happen ! */

    if (AmbaRTSL_FioIsRandomReadMode()) {
        // AMBA_I2S_CAHNNEL0에 FDMA를 사용하겠다.
        AmbaRTSL_FdmaEnable();
        AmbaRTSL_FioReset();
        // DMA 방식을 사용하겠다.
        AmbaCSL_FioDmaFifoModeEnable(); /* Enable DMA Mode for FIO-DMA FIFO */
    }

    AmbaRTSL_FioInit();             /* Initialize the FIO controller */

    AmbaRTSL_FioNandCmdIsrRegister(NAND_FioNandCmdIsr); /* Call back function when NAND Command done */
    AmbaRTSL_FioDmaIsrRegister(NAND_FioDmaIsr);         /* Call back function when FIO-DMA done */
    AmbaRTSL_FdmaIsrRegister(NAND_FdmaIsr);             /* Call back function when FDMA done */

    
```

### > AmbaNAND_ReadID()

```C
/*-----------------------------------------------------------------------------------------------*\
 *  @RoutineName:: AmbaNAND_ReadID
 *
 *  @Description:: Read device ID
 *
 *  @Input      ::
 *      NumReadCycle: number of cycles
 *      pDeviceID:    pointer to the buffer of Device ID
 *      TimeOut:      Time out value
 *
 *  @Output     ::
 *      pDeviceID: pointer to the Device ID
 *
 *  @Return     ::
 *          int : OK(0)/NG(-1)
\*-----------------------------------------------------------------------------------------------*/
int AmbaNAND_ReadID(int NumReadCycle, UINT8 *pDeviceID, UINT32 TimeOut)
{
    int RetVal;
    UINT32 ActualFlags = 0;

    /*---------------------------------------------------*\
     * Take the Mutex
    \*---------------------------------------------------*/
    if (AmbaNAND_Lock() != OK)
        return NG;

    AmbaNAND_Reset(TimeOut);

    AmbaKAL_EventFlagClear(&_AmbaNAND_Ctrl.EventFlag, AMBA_FIO_NAND_CMD_DONE_FLAG);
    AmbaRTSL_NandSendReadIdCmd(NumReadCycle);

    /* wait for Command Done: Event Flag ! */
    RetVal = AmbaKAL_EventFlagTake(&_AmbaNAND_Ctrl.EventFlag, AMBA_FIO_NAND_CMD_DONE_FLAG,
                                   AMBA_KAL_AND_CLEAR, &ActualFlags, TimeOut);
    if (RetVal == OK)
        AmbaRTSL_NandGetReadIdResponse(NumReadCycle, pDeviceID);

    /*---------------------------------------------------*\
     * Release the Mutex
    \*---------------------------------------------------*/
    AmbaNAND_Unlock();

    return RetVal;
}
```

### > AmbaNAND_Config()

```C
/*-----------------------------------------------------------------------------------------------*\
 *  @RoutineName:: AmbaNAND_Config
 *
 *  @Description:: NAND software configurations
 *
 *  @Input      ::
 *      pNandConfig: pointer to NAND software configurations
 *
 *  @Output     :: none
 *
 *  @Return     ::
 *          int : OK(0)/NG(-1)
\*-----------------------------------------------------------------------------------------------*/
int AmbaNAND_Config(AMBA_NAND_CONFIG_s *pNandConfig)
{
    int RetVal;

    RetVal = AmbaRTSL_NandConfig(pNandConfig);

    /* Workaround: 1st data of NAND read is incorrect. */
    AmbaNAND_Read(2, 1, NULL, _AmbaRTSL_FioCtrl.pWorkBufSpare, 1000);

    /* Init BBT and System/User Partition Tables */
    RetVal |= AmbaNAND_InitPtbBbt(1000);

    RetVal |= AmbaNAND_LoadNvmRomFileTable();   /* Load NAND ROM File Table */

    return RetVal;
}
```

### > AmbaNAND_GetDevInfo()

```C
/*-----------------------------------------------------------------------------------------------*\
 *  @RoutineName:: AmbaNAND_GetDevInfo
 *
 *  @Description:: get the pointer to current NAND device information
 *
 *  @Input      :: none
 *
 *  @Output     :: none
 *
 *  @Return     ::
 *      AMBA_NAND_DEV_INFO_s * : the pointer to current NAND device information
\*-----------------------------------------------------------------------------------------------*/
AMBA_NAND_DEV_INFO_s *AmbaNAND_GetDevInfo(void)
{
    return _AmbaRTSL_NandDevInfo;
}
```

### > AmbaNAND_Reset()

```C
/*-----------------------------------------------------------------------------------------------*\
 *  @RoutineName:: AmbaNAND_Reset
 *
 *  @Description:: Reset NAND data structure.
 *
 *  @Input      ::
 *      TimeOut:   The timeout value
 *
 *  @Output     :: none
 *
 *  @Return     ::
 *          int : OK(0)/NG(-1)
\*-----------------------------------------------------------------------------------------------*/
int AmbaNAND_Reset(UINT32 TimeOut)
{
    UINT32 ActualFlags = 0;
    int RetVal;

    AmbaKAL_EventFlagClear(&_AmbaNAND_Ctrl.EventFlag, AMBA_FIO_NAND_CMD_DONE_FLAG);

    AmbaRTSL_NandSendResetCmd();

    RetVal = AmbaKAL_EventFlagTake(&_AmbaNAND_Ctrl.EventFlag, AMBA_FIO_NAND_CMD_DONE_FLAG,
                                   AMBA_KAL_AND_CLEAR, &ActualFlags, TimeOut);

    return RetVal;
}
```

### > AmbaNAND_ReadStatus()

```C
/*-----------------------------------------------------------------------------------------------*\
 *  @RoutineName:: AmbaNAND_ReadStatus
 *
 *  @Description:: Get Nand current status.
 *
 *  @Input      ::
 *      pStatus: pointer to the Status
 *
 *  @Output     ::
 *      pStatus: pointer to the Status
 *
 *  @Return     ::
 *          int : OK(0)/NG(-1)
\*-----------------------------------------------------------------------------------------------*/
int AmbaNAND_ReadStatus(AMBA_NAND_STATUS_u *pStatus, UINT32 TimeOut)
{
    UINT32 ActualFlags = 0;
    int RetVal;

    if (pStatus == NULL)
        return NG;

    /*---------------------------------------------------*\
     * Take the Mutex
    \*---------------------------------------------------*/
    if (AmbaNAND_Lock() != OK)
        return NG;

    AmbaKAL_EventFlagClear(&_AmbaNAND_Ctrl.EventFlag, AMBA_FIO_NAND_CMD_DONE_FLAG);

    AmbaRTSL_NandSendReadStatusCmd();

    RetVal = AmbaKAL_EventFlagTake(&_AmbaNAND_Ctrl.EventFlag, AMBA_FIO_NAND_CMD_DONE_FLAG, AMBA_KAL_AND_CLEAR, &ActualFlags, TimeOut);
    if (RetVal == OK)
        AmbaRTSL_NandGetCmdResponse((UINT8*)pStatus);

    /*---------------------------------------------------*\
     * Release the Mutex
    \*---------------------------------------------------*/
    AmbaNAND_Unlock();

    return RetVal;
}
```

### > AmbaNAND_Read()

```C
/*-----------------------------------------------------------------------------------------------*\
 *  @RoutineName:: AmbaNAND_Read
 *
 *  @Description:: Read data from NAND flash
 *
 *  @Input      ::
 *      PageAddr:  The first page address to read
 *      NumPage:   Number of pages to read
 *      pMainBuf:  pointer to DRAM buffer for main area data
 *      pSpareBuf: pointer to DRAM buffer for spare area data
 *      TimeOut:   The timeout value
 *
 *  @Output     ::
 *      pMainBuf:  pointer to main area data
 *      pSpareBuf: pointer to spare area data
 *
 *  @Return     ::
 *          int : OK(0)/NG(-1)
\*-----------------------------------------------------------------------------------------------*/
int AmbaNAND_Read(UINT32 PageAddr, UINT32 NumPage, UINT8 *pMainBuf, UINT8 *pSpareBuf, UINT32 TimeOut)
{
    int RetVal;
    AMBA_RTSL_FDMA_CTRL_s *pFdmaCtrl = (AMBA_RTSL_FDMA_CTRL_s *) &_AmbaRTSL_FioCtrl.FdmaCtrl;
    UINT32 ActualFlags = 0;

    if (NumPage == 0 || (pMainBuf == NULL && pSpareBuf == NULL) )
        return NG;  /* wrong parameter */

    /*---------------------------------------------------*\
     * Take the Mutex
    \*---------------------------------------------------*/
    if (AmbaNAND_Lock() != OK)
        return NG;

    AmbaKAL_EventFlagClear(&_AmbaNAND_Ctrl.EventFlag, AMBA_FIO_EVENT_MASK );
    RetVal = AmbaRTSL_NandReadStart(PageAddr, NumPage, pMainBuf, pSpareBuf);

    RetVal |= AmbaKAL_EventFlagTake(&_AmbaNAND_Ctrl.EventFlag, AMBA_FIO_EVENT_MASK ,
                                    AMBA_KAL_AND_CLEAR, &ActualFlags, TimeOut);

    if (RetVal == OK) {
        AmbaCache_Invalidate((void *) pFdmaCtrl->pMainBuf, pFdmaCtrl->MainByteCount);
        AmbaCache_Invalidate((void *) pFdmaCtrl->pSpareBuf, pFdmaCtrl->SpareByteCount);
        RetVal = AmbaRTSL_NandCheckDeviceStatus(NumPage);
    }

    if (RetVal == OK) {
        /* Copy back through DMA if output buf is not 8-Byte aligned. */
        if (pMainBuf != NULL && pMainBuf != pFdmaCtrl->pMainBuf) {
            memmove(pMainBuf, pFdmaCtrl->pMainBuf, pFdmaCtrl->MainByteCount);
        }

        if (pSpareBuf != NULL && pSpareBuf != pFdmaCtrl->pSpareBuf) {
            memmove(pSpareBuf, pFdmaCtrl->pSpareBuf, pFdmaCtrl->SpareByteCount);
        }
    } else {
        AmbaNAND_Reset(5000);
    }

    /*---------------------------------------------------*\
     * Release the Mutex
    \*---------------------------------------------------*/
    AmbaNAND_Unlock();

    return RetVal;
}
```

### > AmbaNAND_Program()

```C
/*-----------------------------------------------------------------------------------------------*\
 *  @RoutineName:: AmbaNAND_Program
 *
 *  @Description:: Perform NAND write data cmd flow setup
 *
 *  @Input      ::
 *      PageAddr:  The first page address to write
 *      NumPage:   Number of pages to write
 *      pMainBuf:  pointer to DRAM buffer for main area data
 *      pSpareBuf: pointer to DRAM buffer for spare area data
 *      TimeOut:   The timeout value
 *
 *  @Output     :: none
 *
 *  @Return     ::
 *          int : OK(0)/NG(-1)
\*-----------------------------------------------------------------------------------------------*/
int AmbaNAND_Program(UINT32 PageAddr, UINT32 NumPage, UINT8 *pMainBuf, UINT8 *pSpareBuf, UINT32 TimeOut)
{
    int RetVal;
    UINT32 ActualFlags = 0;

    if (NumPage == 0 || (pMainBuf == NULL && pSpareBuf == NULL) )
        return NG;  /* wrong parameter */

    /*---------------------------------------------------*\
     * Take the Mutex
    \*---------------------------------------------------*/
    if (AmbaNAND_Lock() != OK)
        return NG;

    AmbaCSL_NandDisableWriteProtect();

    AmbaKAL_EventFlagClear(&_AmbaNAND_Ctrl.EventFlag, AMBA_FIO_EVENT_MASK);
    RetVal = AmbaRTSL_NandProgramStart(PageAddr, NumPage, pMainBuf, pSpareBuf);
    if (RetVal == OK)
        RetVal |= AmbaKAL_EventFlagTake(&_AmbaNAND_Ctrl.EventFlag, AMBA_FIO_EVENT_MASK,
                                        AMBA_KAL_AND_CLEAR, &ActualFlags, TimeOut);

    if (RetVal == OK) {
        RetVal = AmbaRTSL_NandCheckDeviceStatus(NumPage);
    }

    AmbaCSL_NandEnableWriteProtect();

    /*---------------------------------------------------*\
     * Release the Mutex
    \*---------------------------------------------------*/
    AmbaNAND_Unlock();

    return RetVal;
}
```

### > AmbaNAND_PseudoCB()

```C
/*-----------------------------------------------------------------------------------------------*\
 *  @RoutineName:: NandPseudoCB
 *
 *  @Description:: Copyback by read and program command.
 *
 *  @Input      ::
 *          UINT32 BlockFrom    : Copy from
 *          UINT32 Page         : Page to copied
 *          UINT32 BlockTo      : Copy to
 *
 *  @Output     :: none
 *
 *  @Return     ::
 *          int : OK(0)/NG(-1)
\*-----------------------------------------------------------------------------------------------*/
static int AmbaNAND_PseudoCB(UINT32 DestPageAddr, UINT32 SrcPageAddr, UINT32 TimeOut)
{
    int RetVal = OK;
    UINT8 *pMainBuf = NULL, *pSpareBuf = NULL;

    pMainBuf = PseudoCBBufMain;
    pSpareBuf = PseudoCBBufSpare;

    RetVal = AmbaNAND_Read(SrcPageAddr, 1, pMainBuf, pSpareBuf, TimeOut);
    if (RetVal != OK) {
        goto Done;
    }

    /* Program main and spare area */
    RetVal = AmbaNAND_Program(DestPageAddr, 1, pMainBuf, pSpareBuf, TimeOut);
    if (RetVal != OK) {
        goto Done;
    }

Done:
    return RetVal;
}
```

### > AmbaNAND_CopyBack()

```c
/*-----------------------------------------------------------------------------------------------*\
 *  @RoutineName:: AmbaNAND_CopyBack
 *
 *  @Description:: Copy data from source page to destinaiton page
 *
 *  @Input      ::
 *      DestPageAddr: Destination page address
 *      SrcPageAddr: Source page address
 *      TimeOut:     Time out value
 *
 *  @Output     :: none
 *
 *  @Return     ::
 *          int : OK(0)/NG(-1)
\*-----------------------------------------------------------------------------------------------*/
int AmbaNAND_CopyBack(UINT32 DestPageAddr, UINT32 SrcPageAddr, UINT32 TimeOut)
{
#if !defined(DISABLE_COPYBACK)
    AMBA_NAND_DEV_INFO_s *pNandDevInfo = _AmbaRTSL_NandDevInfo;
    UINT32 MainByteSize = 0;
    UINT32 ActualFlags = 0;
    int RetVal = OK;
#endif

#if !defined(DISABLE_COPYBACK)
    if (pNandDevInfo == NULL)
        return NG;

    AmbaCSL_NandDisableWriteProtect();

    MainByteSize = pNandDevInfo->MainByteSize;
    AmbaKAL_EventFlagClear(&_AmbaNAND_Ctrl.EventFlag, AMBA_FIO_NAND_CMD_DONE_FLAG);
    AmbaCSL_NandSendCopyBackCmd(SrcPageAddr * MainByteSize, DestPageAddr * MainByteSize);

    RetVal = AmbaKAL_EventFlagTake(&_AmbaNAND_Ctrl.EventFlag, AMBA_FIO_NAND_CMD_DONE_FLAG , AMBA_KAL_AND_CLEAR, &ActualFlags, TimeOut);
    if (RetVal != OK) {
        return RetVal;
    }

    AmbaCSL_NandGetCmdResponse((UINT8 *) & (_AmbaRTSL_NandCtrl.Status[0]));

    AmbaCSL_NandEnableWriteProtect();

    return _AmbaRTSL_NandCtrl.Status[0].Bits[0].LastCmdFailed ? NG : OK;
#else
    return AmbaNAND_PseudoCB(DestPageAddr, SrcPageAddr, TimeOut);
#endif
}
```

### > AmbaNAND_EraseBlock()

```C
/*-----------------------------------------------------------------------------------------------*\
 *  @RoutineName:: AmbaNAND_EraseBlock
 *
 *  @Description:: To erase the block of data identified by the block address parameter
 *
 *  @Input      ::
 *      BlkAddr: Block address
 *      TimeOut: Time out value
 *
 *  @Output     :: none
 *
 *  @Return     ::
 *          int : OK(0)/NG(-1)
\*-----------------------------------------------------------------------------------------------*/
int AmbaNAND_EraseBlock(UINT32 BlkAddr, UINT32 TimeOut)
{
    UINT32 ActualFlags = 0;
    int RetVal;

    /*---------------------------------------------------*\
     * Take the Mutex
    \*---------------------------------------------------*/
    if (AmbaNAND_Lock() != OK)
        return NG;

    AmbaCSL_NandDisableWriteProtect();

    AmbaKAL_EventFlagClear(&_AmbaNAND_Ctrl.EventFlag, AMBA_FIO_NAND_CMD_DONE_FLAG);
    AmbaCSL_NandSendBlockEraseCmd(BlkAddr * _AmbaRTSL_NandCtrl.BlkByteSize);

    RetVal = AmbaKAL_EventFlagTake(&_AmbaNAND_Ctrl.EventFlag, AMBA_FIO_NAND_CMD_DONE_FLAG, AMBA_KAL_AND_CLEAR, &ActualFlags, TimeOut);
    if (RetVal == OK) {
        AmbaCSL_NandGetCmdResponse((UINT8 *) & (_AmbaRTSL_NandCtrl.Status[0]));
        RetVal = _AmbaRTSL_NandCtrl.Status[0].Bits[0].LastCmdFailed ? NG : OK;
    }

    AmbaCSL_NandEnableWriteProtect();

    /*---------------------------------------------------*\
     * Release the Mutex
    \*---------------------------------------------------*/
    AmbaNAND_Unlock();

    return RetVal;
}
```

## [ AmbaNAND_Partition.C ]

### > AmbaNAND_InitPtbBbt()

### > AmbaNAND_CreatePTB()

### > AmbaNAND_LoadNvmRomFileTable()

### > AmbaNAND_GetRomFileSize()

### > AmbaNAND_GetRomFileInfo()

### > AmbaNAND_ReadRomFile()

### > AmbaNAND_ReadSysPartitionTable()

### > AmbaNAND_WriteSysPartitionTable()

### > AmbaNAND_ReadUserPartitionTable()

### > AmbaNAND_WriteUserPartitionTable()

### > AmbaNAND_ReadPartitionInfo()

### > AmbaNAND_ReadPartitionPartial()

### > AmbaNAND_ReadPartition()

### > AmbaNAND_WritePartition()

### > AmbaNAND_InvalidatePartition()

### > AmbaNAND_ErasePatition()

### > AmbaNAND_ReadVendorData()

### > AmbaNAND_WriteVendorData()

### > AmbaNAND_IsBldMagicCodeSet()

### > AmbaNAND_SetBldMagicCode()

### > AmbaNAND_EraseBldMagicCode()

### > AmbaNAND_ReadBldMagicCode()

### > AmbaNAND_SetWritePtbFlag()

