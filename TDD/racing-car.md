# racing-car

> 11st에서 주최하고 next-step에서 진행하는 **TDD/클린코드/리팩터링 with JAVA** 과정을 진행하고 미션을 수행하며 있었던 일을 회고한다.  
  <br>

# 01/20

## ㅠㅠ단위테스트가 뭐야?!

오늘은 java-racing-car 미션에 대한 1차 제출 날이고, 요구사항 중 단위테스트를 만들지 못한 채 PR을 날렸다. 단위테스트를 만드는 법에 대해서 익숙치 않았고 조교님께 문의를 구했다.

<br>

조교님 왈

*Test 커버리지를 100%로 맞춰야 한다는 사람과 사소한 테스트는 넘어가도 좋다는 사람이 존재한다. 100%로 맞춰야 한다는 말은, 정말 아주 작은 단위라도 단위테스트를 수행해야 하는 것이다.*

이 부분은 앞으로 개발하면서 계속 고민해봐야 할 문제인 것 같다.

<br>


아래와 같은 랜덤 함수를 가져오는 작고 소중한 클래스가 있다.

```java
import java.util.Random;

public class RandomManager {
  
    private static final int LIMIT = 10;

    public static int get() {
        return new Random().nextInt(LIMIT);
    }
}
```

이를 테스트 하기 위해서는 어떻게 해야하는 가?

먼저, 이는 실제로 랜덤으로 동작하는 것이기 때문에 테스트 작성 과정에서는 이 값을 제어할 수 있어야 한다.

<br>

두 가지 방법이 있다.

1. `get()` 함수를 **Overriding** 하여 구현하는것.
2. **interface**를 이용해서 테스트를 위한 구현체를 따로 만들어 사용하는 것.

1번의 방법은 테스트 코드 내에서 메소드의 시그니처를 변경하는 방법이다. 하지만 이미 나의 난수를 구하는 코드는 클래스로 따로 빼두었기 때문에 인터페이스를 활용한 방법을 추천해주셨다.

<br>

하지만 나는 한번에 이해가 가지 않았고 조교님은 두 개의 링크를 주시며 이해에 도움을 더해 주셨다.

> https://tecoble.techcourse.co.kr/post/2020-05-07-appropriate_method_for_test_by_parameter/
>
> https://tecoble.techcourse.co.kr/post/2020-04-28-test-without-method-change/

<br>

일단 이를 참고해서 단위테스트 케이스를 만들 생각이다.

그리고 RandomManager라는 이름보다는 RandomGenerator라는 이름이 더 직관적이라는 생각이 들었다^^.

<br>

## 싱글톤이에요? 정적팩토리메서드에요?

당시 모든 클래스의 생성자를 정적 팩토리 메소드로 구현하였는데, 이런 경우는 생성자를 private으로 두는 것이 좋을 것 같다는 말씀을 해주셨다.  
<br>


그렇지 않으면 다른 사람들이 봤을 때 어느 것을 사용할 지 혼란을 주기 때문일 것이다.

그렇기 때문에 현장에서는 **생성자**와 **정적팩토리메서드**를 고민한다고 되는데, 그 기준을 잡기 어렵기 때문이라고 한다. 만약 정적팩토리메서드를 사용한다면 싸그리 모두 정적팩토리를 사용하는 것이 좋을 것이라고 한다.  
<br>


그리고 `getInstance()` 라는 이름의 정적팩토리메서드를 만들었는데 이름 때문에 싱글톤으로 헷갈리셨다. 이 부분에 대해서도 좀 더 가독성있는 이름을 채택해야겠다고 생각했다.  
<br>

## 페어와 함께하는 클린코드

단위테스트에서 호되게 깨지고 난 후 더 큰 깨달음을 얻기 위해 우아한테크세미나에서 박재성(포비)님이 강연하신 영상을 봤다.  
<br>


기존에도 객체지향설계원칙에 대해 알고 있었지만 미션 중 

- indent는 2가 넘지 않게
- class의 인스턴스 변수는 최대 2개
- 함수와 클래스는 최대한 작게

등 과 같은 원칙을 지키기가 무척이나 어려웠던 것 같다.  
<br>

특히, 시간이 촉박하기도 했지만 페어와 함께 프로그래밍하는 게 처음이고 어색하다보니 쉽게 지나쳤던 것을 반성한다.  
<br>
세미나 영상을 보고 나서 다시 한번 동기부여를 얻을 수 있었고 내일부터 진행할 리팩토링 시간이 설렌다. 아주 작은 부분이라도 의식적인 연습을 하다보면 테스트코드도 잘 짜겠지!

---

# 01/21
## 전략패턴을 한번 써볼게요.

오늘은 어제 있었던 단위테스트에 대해 공부하고 위의 난수 테스트를 프로그래머가 적절히 제어하기 위해 어떤 방법이 있는지 알아보고 적용했다.  
<br>

그 중, 내가 구현한 것은 전략패턴을 활용하는 것이었다.  
<br>

먼저 전략에 대한 인터페이스를 하나 만들고,

```java
public interface RandomStrategy {

    int get();
}
```


구현체를 사용하는 팩토리 클래스를 하나 생성한다.

```java
public class RandomFactory {

    public RandomFactory() {}

    public int getRandom(RandomStrategy randomStrategy) {
        return randomStrategy.get();
    }
}
```



그리고 이제 나에겐 세 개의 전략 구현체가 존재한다.  
<br>


아래는 10 미만의 랜덤 값을 추출하는 클래스,

```java
import java.util.Random;

public class RandomGenerator implements RandomStrategy {

    private static final int LIMIT = 10;

    public int get() {
        return new Random().nextInt(LIMIT);
    }
}
```



단위테스트 시 항상 이동가능한 값을 반환하도록 제어하는 클래스,

```java
public class MoveGen implements RandomStrategy {

    public static final int MOVE = 5;

    @Override
    public int get() {
        return MOVE;
    }
}
```


항상 이동불가능한 값을 반환하도록 제어하는 클래스이다.

```java
public class NoMoveGen implements RandomStrategy {

    public static final int NOMOVE = 3;

    @Override
    public int get() {
        return NOMOVE;
    }
}
```



그리고 이를 실제로 활용하는 Move클래스이다.

```java
public class Move {

    private static final int BOUND = 4;
    private RandomFactory randomFactory;

    private Move() {
        randomFactory = new RandomFactory();
    }

    public boolean isSatisfiedMoveCondition(RandomStrategy randomStrategy) {
        return randomFactory.getRandom(randomStrategy) >= BOUND;
    }
}
```

실제 사용하는 곳이다. 여기서 중요한 것이, move 객체에서 **어떤 전략을 선택할지 주입하는 행위이다.**  
<br>

이를 위해서, **사용하고자 하는 메서드에서 인자로 전략 구현체를 받아서 사용하면 된다.**

실제 production 코드에서는 `RandomGenerator()` 라는 클래스를 주입해서 사용하고 있다.

```java
public RacingResult race(Participants participants) {
    for (int i = 0; i < participantCount; i++) {
        participants.moveCarIfPositionChanged(i, move.isSatisfiedMoveCondition(new RandomGenerator()));
    }

    return RacingResult.toRacingResult(participants);
}
```





마찬가지로 단위테스트에서는 `MoveGen()` 과 `NoMoveGen()`을 주입해서 사용하면 훨씬 효과적으로 제어할 수 있게 되고 코드의 중복제거 등 다양한 효과를 얻게 된다.

이를 조교님께 다시 한 번 보여드렸는데 또 한번 칭찬해주셔서 기분이 좋았다.

  
  <br>

## 그 외 개발 꿀팁 및 지식을 전수받았어요

이 외에도 단위테스트에 대해서 이것 저것 물어봤고, 여러가지 꿀팁을 전수 받을 수 있었다 !

1. 단위테스트 시 gradle보다 intelliJ로 설정해놓는 것이 빌드 속도도 더 빠르고 클래스에도 `displayName()`을 적용할 수 있어 더 좋다.
2. 테스트를 작성할 때 각 테스트 간 의존하고 있으면 안된다. 반드시 독립적으로 어느 때나 테스트를 돌려도 커버할 수 있는 테스트를 작성하자.
3. 시간 날 때마다 [AssertJ Docs](https://assertj.github.io/doc/) 를 참고하면 테스트 코드 짜는데 많이 도움이 될 거다 ^^!
4. 테스트 재활용은 필수다! 테스트 할 때 기본으로 해줘야 하는 셋팅이 필요한데 이를 재활용하기 위해 테스트코드에 클래스를 만들고 이를 상속받고.. 등등.. 그렇다고 한다. 이에 대해서는 나중에 좀 더 자세하게 공부해봐야겠다.
5. 변수명이나 함수명, 클래스명 어디에 두고 `shift + F6` 을 입력하면 **rename**이 된다. 이건 혁신이다...

  
  <br>

그리고 이 날은 다함께 회고를 진행했는데 사람마다 느끼는건 대부분 비슷한 것 같다.

회고 시간 덕분에 동기들이랑 한번이라도 더 대화할 수도 있고 다양한 생각을 들을 수 있어서 좋았던 시간이었다!

---

# 01/22

## 첫 PR, 그리고 혹독한 리뷰

오늘은 드디어 기다리고 기다리던 리뷰어님께서 나의 첫 PR에 리뷰를 달아주셨다.

마침 리팩토링 작업중이었기에 한걸음에 달려가 리뷰를 확인하고 코드를 수정했다 !  
<br>

리뷰 총평은 바로.. 안중요한 한줄 한줄이 없다는 것이다!!!!

직관적이지 않은 변수명/함수명을 사용했고, 접근제한자에 습관적으로 public을 달고, 반복문에 의미없는 인자를 사용하고 했던 것이 눈에 띄게 드러났다. :cry:

클래스의 인스턴스 변수는 재할당 될 필요가 있는가? 그렇지 않다면 final로 재할당을 방지하라!

변수명, 함수명을 축약해서 썼는가? 그러지 말고 풀네임을 사용하라!

필요없는 생성자, import 문, print문, 주석은 확실하게 제거하라!

내가 생각했던 관습은 남들에게는 관습이 아니다. 좀 더 의도를 명확하게 드러내도록 메서드를 분리하라! 그러면 자연스레 코드도 깔끔해질것이다!

하나의 메서드 혹은 클래스가 너무 많은 역할을 하면 안되다! 이것 또한 잘 분리하라!

  
  <br>

오늘의 리뷰는 대체적으로 리팩토링에 관한 것이었다. 그만큼 돌아가는 코드가 아니라 잘 읽히는 코드가 중요하다는 것을 다시 한번 느낄 수 있었다.

이제 테스트코드 만들러 가야지~

---

# 01/24

## 자동차 경주 미션 총 피드백

오늘은 자동차 경주 미션에 대해서 jason님의 총 피드백이 있었다.

그 중 인상깊었던 것만 체크해본다.

  
  <br>

### > 테스트 픽스처를 생성하자 !

**테스트 픽스처(test fixture)란 테스트를 반복적으로 수행할 수 있게 도와주고 매번 동일한 결과를 얻을 수 있게 도와주는 '기반이 되는 상태나 환경'을 의미한다.**

그래서, 여러 테스트에서 공용으로 사용할 수 있도록 테스트의 인스턴스 변수나 혹은 별도의 클래스에 모아두는 작업이 유용하다.  
<br>

```java
public class RacingGameTest {
    private final String[] testNames = {"a", "b", "c"};
    private final int testPosition = 5;
    private final String resultSamePositionString = ", b";
    private final String resultWinnersString = "a, b";
    RacingGame racingGame;
    private final Car firstWinner = new Car(testPosition, "a");
    private Car secondWinner;
    private final List<Car> cars = new ArrayList<Car>();

    ...

}

```

​	그리고 이런 테스트 픽스처를 위해서 필요하다면 프로덕션 코드에 필요한 생성자를 추가하는 것도 좋다.  
<br>

### > 문자열끼리 비교할 때는 `NPE` 방지를 위해서 상수를 앞에다 비교하자.

```java
String text = "동해물과백두산이";
if (text.equals("동해물과백두산이")) {} // text가 null이라면 NPE !
if ("동해물과백두산이".equals(text)) {} // text가 null이어도 NPE를 방지할 수 있다 !
```
  
  <br>

### > class file 까보는게 자바 공부에 도움된다

제곧내.  
<br>

### > Arrays.asList()는 기본적으로 add, remove가 안된다.

```java
final List<String> list = Arrays.asList("first", "second");
list.add("third"); // UnSupportedOperatedException 발생
list.remove("second"); // UnSupportedOperatedException 발생
```
  
  <br>

그렇다면 이를 수정할 수 있는 방법은 ????

바로 Arrays.asList<>() 의 인자로 넣어주는 것이다!

```java
final List<String> list = new ArrayList<>(Arrays.asList("first", "second"));
```
  
<br>

### > generic은 컴파일 타임에만 의미가 있다.

```java
List<String> list = new ArrayList<>();

List list = new ArrayList<>();
list.add(1);
list.add("1");

// 이렇게 추가하는 것도 전혀 문제가 없다.
```


메서드의 시그니처란? : 메서드의 이름과 타입이다.

generic의 특성 때문에 List를 메서드의 타입으로 받으면 어떤 타입이든지 같은 시그니처로 판단되어 중복이 나온다.  
<br>

```java
void of(List<String> list) {}

void of(List<Integer> list) {}
```

이거는 컴파일 될 때만 타입이 의미가 있지 같은 List로 들어가기 때문에 중복이 발생한다. 


따라서, 제네릭은 컴파일타임에 내가 안전한 용도로 사용하기 위함이다.  <br>


 ### enum 응용편

```java
Operator.PLUS.operate(3, 5);

enum Operator {
  PLUS("+") {
    @Override
    public int operate(final int number1, final int number2) {
      return number1 + number2;
    }
  },
  MINUS("-", (number1, number2) -> number1 - number2);
  MULTIPLY("*", (number1, number2) -> number1 * number2);
  
  private final String symbol;
  
  Operator(final String symbol) {
    this.symbol = symbol;
  }
  
  public abstract int operate(final int number1, final int number2);
}
```

`Bifunction<>` 을 사용하는 것 같다?

어렵다! 이 부분은 나중에 더 자세히 알려준다고 한다.

# 01/28

## TDD가 뭔데 !
  
<br>
크게 보면 프로그래밍 순서는 이와 같을것이다.

1. 실패하는 작은 테스트를 작성한다.
2. 일단 테스트가 빨리 통과하게끔 만든다.
3. 리팩터링한다.(중복 제거 등)
  
<br>
즉 원칙은

1. 실패하는 단위 테스틀 작성할 때까지 구현 코드를 작성하지 않는다.
2. 컴파일은 실패하지 않으면서 실행이 실패하는 정도로만 단위 테스트를 작성한다.
3. 현재 실패하는 테스트를 통과할 정도로만 실제 코드를 작성한다
  
<br>
TDD는... 인생 철학과 같다?
용기를 불어넣는다. 무슨 용기? 코드를 생산하는 두려움을 극복하게 한다.

애초에 완벽한 코드라는 것은 존재하지 않기 때문에 완벽한 코드로 리뷰요청을 한다는 것은 절대 오지 않는다.
  
<br>

TDD를 시작할 때의 감정 상태..

어디서 어떻게 시작할까 ㅎㅎ?
  
<br>

### 요구사항 분석 및 설계
- 요구 사항 분석을 통해 대략적인 설계와 객체를 추출한다.
- UI와 DB등 과 의존 관계에 있지 않은 핵심 도메인 영역을 집중해서 석례한다.
- 일차적으로는 도메인 로직을 테스트하는 것에 집중한다.
- 대략적인 도메인 객체를 설계한다.

### 아니 그래서 어떻게 하라고?~
- 일단 동작하는 쓰레기 코드를 만든다.
- 해당 도메인의 지식을 쌓는다.
- 구현한 모든 코드를 버린다.
  - 버리는 용기도 중요해 !!
  - 이미 한번 경험해 봤기 때문에 훨씬 좋은 코드를 만들 수 있다.
- 다시 기능 목록을 작성하고 간단한 도메인을 설계한다.
- 기능 목록 중 가장 만만한 녀석부터 TDD로 구현을 시작한다.
- 복잡도가 높아져서 리팩토링 하기 힘든 상태가 된다면.... 다시 버린다 !!
- 다시 도전, 반복
  
<br>

## 더 줘!

**여러 인자를 이용해 반복적으로 테스트**하기 위해 `@ValueSource()` 와 `@ParameterizedTest` 를 사용할 수 있다.
  
<br>

구현코드의 **널체크**는 `Objects.isNull`

테스트의 **널체크**는 `@NullAndEmptySource`를 사용할 수 있다.
  
<br>


primary Constructor와 secondary Constructor
를 활용해 `this()`를 활용한 **생성자 체이닝**을 사용할 수 있다. 또한, 주 생성자에 validate()와 같은 로직을 넣는 것이 좋다.
  
<br>

**방어적 복사 ??**

인자로 들어오는 List를 그냥 받아주는 것이 아니라 새로운 List를 만들어 할당했다? 이건 개인적으로 더 알아보자
  
<br>

**원시값 포장 !!**

원시값을 포장하는 게 어떤 장점이 있는가?
String name을 예로 들어서, name을로 validate를 하려한다면 중복 코드가 늘어난다.

하지만, Name이라는 클래스를 만들어서 해당 클래스에서 validate() 메서드를 실행시키면 되기 때문에 더 이상 중복이 일어나지 않는다.