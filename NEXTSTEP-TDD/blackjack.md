# 02/14

## 함수형 프로그래밍

함수형 프로그래밍을 연습하는 법

-> 프로그래밍의 기본 틀은 객체 지향 프로그래밍, 함수 내부 구현은 함수형 프로그래밍을 지향, 객체의 상태 관리는 불변을 지향

<br>

하지만, 오해하고 있는 부분이 있다.

```java
class Number {
  //private final int value;
  private int value;

  public Number(final int value) {
    this.value = value;
  }

  public Number add(final Number number) {
    return new Number(value + number.value);
  }
}

public class Calculator {
  private final Number result;

  public Calculator(final Number result) {
    this.result = result;
  }

  public void add(final Number number1, final Number number2) {
    number.add(number2);
  }
}
```

<br>

왠만하면 불변객체로 만드는 것이 좋지만, 객체지향 프로그래밍을 하는 이상 가변 객체가 필요할 때도 있다.

이럴 때는 가변객체로 만들어 줘도 된다! 모든 것을 불변객체로 만들 수는 없음을 이해하라.

프로그래머가 적당히 어디서 불변객체를 끊을 줄 알아야 한다!

<br>

## Stream, Lambda, Optional

> 자바에서의 함수형 프로그래밍

<br>

forEach의 파라미터 인자는 기본적으로 하나만 받는다.


<br>

**메서드 참조**

객체 참조뿐만 아니라 메서드 참조를 쓸 수 있다.

어떤경우에?

```java
numbers.forEach((number) -> System.out.println(number));
```

이렇게 파라미터로 넘긴 number가 body에서도 똑같이 쓰이는 경우에 메서드 참조로 변환할수 있다.

-> 위의 예에서 number가 들어가서 number가 쓰이는 경우 !

<br>

```java
numbers.forEach(System.out::println); // 메서드 참조
```

### 람다를 활용한 중복제거

람다를 활용해 중복을 제거할 수 있다.

아래와 같은 코드가 있다고 하자.

<br>


```java
public class LambdaTest {

    private List<Integer> numbers;

    @BeforeEach
    void setUp() {
        numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
    }

    @Test
    public void sumAll() {
        final int result = sumAll(numbers);
        assertThat(result).isEqualTo(21);
    }

    @Test
    public void sumAllEven() {
        final int result = sumAllEven(numbers);
        assertThat(result).isEqualTo(12);
    }

    @Test
    public void sumAllOverThree() {
        final int result = sumAllOverThree(numbers);
        assertThat(result).isEqualTo(15);
    }

    private int sumAll(List<Integer> numbers, Predicate<Integer> predicate) {
        int total = 0;
        for (int number : numbers) {
            if (predicate.test(number)) {
                total += number;
            }
        }
        return total;
    }

    private int sumAll(List<Integer> numbers) {
        int total = 0;
        for (int number : numbers) {
            if (true) {
                total += number;
            }
        }
        return total;
    }

    private int sumAllEven(List<Integer> numbers) {
        int total = 0;
        for (int number : numbers) {
            if (true) {
                total += number;
            }
        }
        return total;
    }

    private int sumAllOverThree(List<Integer> numbers) {
        int total = 0;
        for (int number : numbers) {
            if (true) {
                total += number;
            }
        }
        return total;
    }
}

```

<br>

에서 `Predicate<T>`를 사용해서 아래와 같이 중복을 제거할 수 있다.

<br>

```java
public class LambdaTest {

    private List<Integer> numbers;

    @BeforeEach
    void setUp() {
        numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
    }

    @Test
    public void sumAll() {
        // final int result = sumAll(numbers, new Predicate<Integer>() {
        //     @Override
        //     public boolean test(Integer integer) {
        //         return true;
        //     }
        // });
        final int result = sumAll(numbers, number -> true);
        assertThat(result).isEqualTo(21);
    }

    @Test
    public void sumAllEven() {
        // final int result = sumAll(numbers, new Predicate<Integer>() {
        //     @Override
        //     public boolean test(Integer integer) {
        //         return integer % 2 == 0;
        //     }
        // });
        final int result = sumAll(numbers, number -> number % 2 == 0);
        assertThat(result).isEqualTo(12);
    }

    @Test
    public void sumAllOverThree() {
        // final int result = sumAll(numbers, new Predicate<Integer>() {
        //     @Override
        //     public boolean test(Integer integer) {
        //         return integer > 3;
        //     }
        // });
        final int result = sumAll(numbers, number -> number > 3);
        assertThat(result).isEqualTo(15);
    }

    private int sumAll(List<Integer> numbers, Predicate<Integer> predicate) {
        int total = 0;
        for (int number : numbers) {
            if (predicate.test(number)) {
                total += number;
            }
        }
        return total;
    }
}
```

<br>

자바에서 제공하는 `Predicate<T>` 인터페이스 스펙이다!

```java
@FunctionalInterface
public interface Predicate<T> {

    boolean test(T t);

    ...
}
```

<br>

### Stream

for 문과 stream의 차이점.

stream은 최종으로 내가 끝내기 전까진 절대 연산이 되지 않는다.

어차피 처음부터 끝까지 다 계산할 것이기 때문에 중간연산 시 중간에 따로 저장을 하지 않고 최종연산에서 Stream API가 동작하게 된다.

<br>

이러한 이유로 Stream API를 사용한다.

<br>

위에서 진행한 람다 테스트를 스트림으로 변경해보자.

```java
public class StreamTest {

    private List<Integer> numbers;

    @BeforeEach
    void setUp() {
        numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
    }

    @Test
    public void sumAll_mapToInt() {
        final int result = numbers.stream()
            .mapToInt(number -> number)
            .sum();

        assertThat(result).isEqualTo(21);
    }

    @Test
    public void sumAll_reduce() {
        final int result = numbers.stream()
            .reduce((number1, number2) -> number1 + number2)
            .orElse(0);

        assertThat(result).isEqualTo(21);
    }

    @Test
    public void sumAllEven() {
        final int result = numbers.stream()
            .mapToInt(number -> number)
            .filter(number -> number % 2 == 0)
            .sum();

        assertThat(result).isEqualTo(12);
    }

    @Test
    public void sumAllOverThree() {
        final int result = numbers.stream()
            .mapToInt(Integer::valueOf)
            .filter(number -> number > 3)
            .sum();

        assertThat(result).isEqualTo(15);
    }

    @Test
    void sumOverThreeAndDouble() {
        // 3보다 큰 수를 2배하여 더하는
        final int result = numbers.stream()
            .filter(number -> number > 3)
            .mapToInt(number -> number * 2)
            .sum();

        assertThat(result).isEqualTo(30);
    }

    @Test
    void mapToDouble() {
        final List<Integer> actual = numbers.stream()
            .map(number -> number * 2)
            .collect(Collectors.toList());

        assertThat(actual).containsExactly(2, 4, 6, 8, 10, 12);
    }
}
```

<br>

### Optional

프로그래머들의 주적.. NPE...

Optional... 슈뢰딩거의 고양이... 내가 상자를 열어보기 전까진 있을 수도 있고 없을 수도 있고.

하지만 그 책임을 전가한다.

```java
public class OptionalTest {

    @Test
    void optional() {
        final Optional<String> maybeName = Optional.ofNullable("jason");
        final String name = maybeName.orElse("test");
        Assertions.assertThat(name).isEqualTo("jason");
    }

    @Test
    void optional_null() {
        final Optional<String> maybeName = Optional.ofNullable(null);
        final String name = maybeName.orElse("test");
        Assertions.assertThat(name).isEqualTo("test");
    }

    @Test
    void optional_throw() {
        final Optional<String> maybeName = Optional.ofNullable(null);
        Assertions.assertThatThrownBy(() -> maybeName.orElseThrow(IllegalArgumentException::new))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
```

<br>


### 함수형 인터페이스의 default 메서드

default 메서드는 하위 호환성을 위해 나온 메서드이다.

이는 추상메서드의 메서드와 디펄드 메서드는 용도가 다른, 다른 이유로 생겨졌다.

<br>


# 02/21

불변객체의 메모리를 걱정하는 것 보다 가변객체가 더 심하다.

불변객체는 우리가 생각하는 걱정보다 훨씬 더 많은 이점을 준다!

**따라서 자주 사용하는 인스턴스는 미리 생성하자.**

<br>

```java
public class Card {
    static {
        // make card instance
    }

    public static String toKey(final Suit suit, final Denomination denomination) {
        return suit.name() + denomination.name()
    }
}
```

카드는 어떻게 서로 다른 객체임을, 서로 같은 객체임을 알 수 있을까?

카드의 문양과 숫자를 합친 것을 키값으로, 카드의 id로 설정할 수 있다. (ex. 복합키)


-> 또한 카드팩처럼 불변객체이고, 조건이 정해져 있다면 미리 만들어 놓으면 메모리를 최소화할 수 있겠다.

<br>

## 객체 지향의 다형성을 이용해 조건문 줄이기 - 상태패턴

반복되는 조건문을 제거하는 방법 중 하나는 객체지향의 다형성을 활용해 해결할 수 없는지 검토해보자!!

- 게임 내 규칙을 자바 객체로 추상화 해보자.
  - 힛(hit): 처음 2장의 상태에서 카드를 더 뽑는 것
  - 스테이(stay): 카드를 더 뽑지 않고 차례를 마치는 것
  - 블랙잭(Blackjack): 처음 두 장의 카드 합이 21인경우, 베팅 금액의 1.5배
  - 버스트(bust): 카드 총합이 21을 넘는 경우 배당금을 잃는다.
- 현재 상태에서 다음 상태의 객체를 생성하는 역할을 현재 상태가 담당하도록 한다.

```java
public class Hit extends Running {
    public Hit(final Cards cards) {
        super(cards);
    }

    @Override
    public State draw(final PlayingCard card) {
        cards.add(card);
        if (cards.isBust()) {
            return new Bust(cards);
        }
        return new Hit(cards);
    }

    @Override
    public State stay() {
        return new Stay(cards);
    }
}

public class Blackjack extends Finished {
    public Blackjack(final Cards cards) {
        super(cards);
    }

    @Override
    public double earningRate() {
        return 1.5;
    }
}
```

<br>

FirstTurn을 구현해보자

```java
public class Cards {

    private final List<PlayingCard> cards;

    public Score score() {
        Score score = new Score(sum());
        if (hasAce()) {
            score = score.plusAceScore();
        }
        return score;
    }

    public boolean drawable() {
        return score().isPlaying();
    }

    private boolean hasAce() {
        return cards.stream()
            .anyMatch(PlayingCard::isAce);
    }

    private int sum() {
        return cards.stream()
            .mapToInt(PlayingCard::getScore).sum();
    }
}

public class Score {
    private static final int BLACKJACK_SCORE = 21;
    private static final int ACE_ADDITIONAL_SCORE = 10;

    private final int value;

    public Score(final int value) {
        this.value = value;
    }

    public boolean isPlaying() {
        return value < BLACKJACK_SCORE;
    }

    public Score plusAceScore() {
        return new Score(value + ACE_ADDITIONAL_SCORE);
    }

    @Override
    // equals and hashcode
}
```

```java
public class Hit extends Running {
    private final Cards cards;

    public Hit(final PlayingCard ... cards) {
        this(Arrays.asList(cards))
    }

    public Hit(final List<PlayingCard> cards) {
        this(new Cards(cards));
    }

    public Hit(final Cards cards) {
        this.cards = cards;
    }

    @Override
    public State draw(final PlayingCard card) {
        cards.add(card);
        if (cards.score.isPlaying()) {
            return new Hit(card);
        }
        return new Bust();
    }

    public State stay() {
        return new Stay();
    }
}
```

```java
public interface State {
    State draw(PlayingCard card);
}

public abstract class Running implements State {
    @Override
    public State draw(final PlayingCard card) {

    }
}

public abstract class Finished implements State {
    @Override
    public State draw(final PlayingCard card) {
        throw new UnsupportedOperationException("더이상 카드를 받을 수 없습니다");
    }

    // 템플릿 메서드 패턴
    public final double winning(final int money) {
        return money * earningRate();
    }

    abstract double earningRate();
}

public class Blackjack implements Finished {
    @Override
    double earningRate() {
        return 1.5;
    }
}

public class Stay implements Finished {
    @Override
    double earningRate() {
        return 1;
    }
}

public class Bust implements Finished {
    @Override
    double earningRate() {
        return 0;
    }
}
```

```java
public class FirstTurn {
    private static final int BLACKJACK_SCORE = 21;

    public State draw(final PlayingCard card1, final PlayingCard card2) {
        final Cards cards = new Cards(card1, card2);

        if (cards.score().isPlaying()) {
            return new Hit();
        }
        return new Blackjack();
    }
}
```

<br>

> private 메서드를 테스트하고 싶으면 ? -> 바로 **클래스 분리** !!
> - 코드의 가독성을 올리기 위한 private 메서드
> - 기능 단위로 메서드를 분리하기 위한 private 메서드
>  - 테스트 욕구가 클 것이다.

<br>

각 상태를 담당하는 객체를 추가하면 구현에서 발생하던 많은 `if/else` 문을 상태 객체로 만들 수 있다.!!

<br>

## 객체지향 패러다임의 핵심?!

TDD를 구현해나갈 때 top-down으로 개발하는 것이 좋을까, bottom-up으로 개발하는 것이 좋을까?

- Top-down(ex. controller-service-domain)
  - 책임에 초점을 맞춰서 전체적인 설계의 방향과 흐름을 결정한 후에 구현을 시작
  - 책임 주도 설계(Responsibility-Driven Design)
- Bototm-up(ex. domain-service-controller)
  - 구현에 초점을 맞춰서 일단 구현한 후 지속적인 리팩터링을 통해 **객체의 역할, 책임, 협력**을 찾아 나가면서 설계를 개선해 나가는 접근 방식

<br>

이런 개발 방식이 중요한게 아니라 내가 알고 있는 것부터 시작하는 것이 좋다.

TDD를 어떻게 현장에 적용할까?

결론은, 아는것에서 모르는 것으로 개발하는 것이 중요한다!

<br>

## 인수테스트 주도개발

특히, 내가 백엔드 개발자다? 이 인수테스트를 공부해보자.

https://www.youtube.com/watch?v=APC-G8EfqLM

백명석님이 강의하신 영상을 꼭 봐보자! 지금까지의 커리큘럼이 모두 녹아있으니 이 영상으로 완성시켜보자.

<br>

당장의 TDD가 어렵다면, 테스트 코드를 작성하는 버릇부터 키워보자.

내가 배운 클린코드를 배움으로써 좀 더 명확한 변수명을 지을 수 있다.

떄로는 너무 명확한 변수명은 한단계 더 추상화한 변수이름을 채택하여 확장성을 높여보자.

테스트하기 어려운 것들(Random, Shuffle)은 최대한 테스트코드로부터 멀리둬야 한다.(도메인과 벗어난 더 위로 올리자! -> 전략패턴을 사용해보자)

모르는 게 있으면 코드를 직접 열어보고, 학습테스트 코드를 작성해보며 공부해보자.

함께하는 스터디 많이 하자.

입사 초기 1년간은 회사일에만 집중하자.(자바를 쓰는데 고랭 공부? ㄴㄴ;)

주어진 일에 잘하진 모습을 보이고 팀원들의 신뢰를 쌓도록 하자.

<br>


## Unmodifiable

방어적 복사를 하려고 Unmodifiable을 쓰는게 모두 최선은 아니다. 그래서 상황에 따라서 적절하게 ArrayList<>()로 감싸는 것도 고려해보자.

```java
void test() {
    final List<Integer> numbers1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
    final List<Integer> numbers2 = Collections.unmodifiableList(numbers1);
    final List<Integer> numbers3 = new ArrayList<>(numbers1);

    numbers1.add(1, 1000);

    System.out.println(numbers2); // [1, 1000, 2, 3, 4, 5]
    System.out.println(numbers3); // [1, 2, 3, 4, 5]
}
```