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