# 02/07

jason님의 로또게임 첫 피드백이 있는 날이다. 필요한거만 정리해보자 !

<br>

`assertThat(LottoNumber(1)).equalsTo(LottoNumber(1))`

-> false !

<br>

자바는 주소값을 비교하기 때문에 서로 다른 객체로 판단하고 있다.

따라서 동등성을 보장하기 위해 `equals & hashcode`를 오버라이딩한다.

<br>

tip: **sonarlint** 를 사용하면 컨벤션들을 다 잡아준다 !

<br>

가변인자를 사용한 로또 생성자 체이닝

```java
public class Lotto {

    private static final int LOTTO_SIZE = 6;

    private Set<LottoNumber> numbers;

    public Lotto(final int ... numbers) {
        Arrays.stream(numbers)
            .mapToObj(LottoNumber::new)
            .collect(Collectors.)
    }

    public Lotto(final List<LottoNumber> numbers) {
        this(new HashSet<>(numbers));
    }

    public Lotto(final Set<LottoNumbers> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException();
        }
        this.numbers = numbers;
    }

    public boolean contains(final LottoNumbers lottoNumber) {
        return numbers.contains(lottoNumber);
    }

    public Set<LottoNumber> getNumbers() {
        return numbers;
    }
}
```

<br>

가변인자를 사용하면 로또를 생성하는 테스트도 훨씬 심플해진다.


```java
// test
void contains() {
    final Lotto lotto = new Lotto(1, 2, 3, 4, 5, 6);
    ...
}
```

<br>

## private 함수를 테스트하는 방법

테스트하기 위해서 private의 API를 public으로 두는 것은 굉장히 위험한 발상이다.

private메서드를 테스트하고 싶을 때는 또다른 메서드로 분리하라는 신호이다.


> http://shoulditestprivatemethods.com/

<br>


## 상속과 조합?

> https://edu.nextstep.camp/s/TGCF47df/ls/JvuM0GJC

상속이 적절한 경우란 언제일까? 클래스의 행동을 확장(extend)하는 것이 아니라 정제(refine)할 때다.

확장이란 새로운 행동을 덧붙여 기존의 행동을 부분적으로 보완하는 것을 의미하고 정제란 부분적으로 불완전한 행동을 완전하게 만드는 것을 의미한다.

<br>

객체 지향 초기에 가장 중요시 여기는 개념은 재사용성(reusability)이었지만, 지금은 워낙 시스템이 방대해지고 잦은 변화가 발생하다 보니 유연성(flexiblity)이 더 중요한 개념이 되었다.

오히려 읽기 좋은 코드를 더 중요시하는 것이 현대 개발자가 가져야할 마음가짐이다.

**클래스 B가 정말 A 인지 물어보고 그게 아니라면 조합을 사용하라!**

<br>

## Stream API

### **reduce()**

Stream을 사용할 때 내부 값을 변경하지 않고 연산을 수행할 수 있는 메서드이다.

```java
Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
Optional<Integer> = numbers().reduce((x, y) -> x + y);
```

아래와 같이 초기값을 미리 지정할 수도 있따.

```java
private Integer resolveGroupByRank(List<Rank> ranks, Rank rank) {
    return ranks.stream()
        .filter(result -> result == rank)
        .map(result -> INCREASE)
        .reduce(ZERO, Integer::sum);
}
```

<br>

추가적으로 병렬적으로 연산하기 위해선 `parallel`을 사용할 수 있다.

<br>


### **Collections.toMap()**

`toMap()` 메서드는 총 3개의 인터페이스가 존재한다.

가장 기본적인 형태는 키와 밸류를 인자로 받는다.

`Function.identity()` 를 사용하면 현재 들어온 값을 그대로 반환하게 된다.

<br>


사용하던 도중 `Java Stream Collectors.toMap IllegalStateException duplicate keys` 에러가 발생했다.

이 건 키가 중복되어서 에러가 나는데 세 번째 인자로, `(o1, o2) -> o1` 으로 두 개의 키 중 이전에 생성한 키를 그대로 사용한다는 코드를 추가해주면 된다.


```java
private Map<Rank, Integer> groupByRank(List<Rank> ranks) {
    return Arrays.stream(Rank.values())
        .filter(rank -> rank != Rank.NONE)
        .collect(Collectors.toMap(Function.identity(),
            rank -> resolveGroupByRank(ranks, rank),
            (p1, p2) -> p1,
            TreeMap::new));
}
```

여기에 위와 같이 마지막 인자로 생성자를 추가해주면 해당타입으로 반환해줄 수가 있다.

<br>