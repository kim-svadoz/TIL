# 알고리즘 이론:fist_oncoming:



대회 알고리즘 이론은

https://m.blog.naver.com/PostList.nhn?blogId=kks227&categoryNo=299&logCode=0&categoryName=%EB%8C%80%ED%9A%8C%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98

여기서 참조하자.

# **> 알고리즘의 분석**

---

> - 알고리즘의 자원(resource) 사용량을 분석
> - 자원이란 실행시간, 메모리, 저장장치, 통신 등
> - 여기서는 실행시간의 분석에 대해서 다룬다.

## 시간복잡도

- 실행시간은 실행환경에 따라 달라짐
  - 하드웨어, 운영체제, 언어, 컴파일러 등
- 실행시간을 측정하는 대신 연산의 실행 횟수를 카운트
- 연산의 실행 횟수는 입력 데이터의 크기에 관한 함수로 표현
- 데이터의 크기가 같더라고 실제 데이터에 따라서 달라짐
  - 최악의 경우 시간복잡도(worst-case analysis)
  - 평균 시간복잡도(average-case analysis)

## 점근적(Asymptotic) 분석

### 점근적 표기법을 사용

- 데이터의 개수 n → ∞ 일때 수행시간이 증가하는 growth rate로 시간복잡도를 표현하는 기법
- Θ-표기, Ο-표기 등을 사용

### 유일한 분석법도 아니고 가장 좋은 분석법도 아님

- 다만 (상대적으로) 가장 간단하며
- 알고리즘의 실행환경에 비의존적임
- 그래서 가장 광범위하게 사용됨

## 점근적 분석의 예: 상수 시간 복잡도

> 입력으로 n개의 데이터가 저장된 배열 data가 주어지고, 그 중 n/2번째 데이터를 반환한다.

```java
int sample(int data[], int n) {
  int k = n / 2;
  return data[k];
}
```

n에 관계없이 상수 시간이 소요된다. 이 경우 알고리즘의 시간복잡도는 Ο(1)이다.

## 점근적 분석의 예: 선형 시간복잡도

> 입력으로 n개의 데이터가 저장된 배열 data가 주어지고, 그 합을 구하여 반환한다.

```java
int sum(int data[], int n) {
  int sum = 0;
  for (int i = 0; i < n; i++)
    sum = sum + data[i];
  return sum;
}
```

선형 시간복잡도를 가진다고 말하고 Ο(n)이라고 표기한다.

- sum = sum + data[i];
  - 이 알고리즘에서 가장 자주 실행되는 문장이며, 실행 횟수는 항상 n번이다. 가장 자주 실행되는 문장의 실행횟수가 n번이라면 모든 문장의 실행 횟수의 합은 n에 선형적으로 비례하며, 모든 연산들의 실행횟수의 합도 역시 n에 선형적으로 비례한다.

## 선형 시간복잡도: 순차탐색

> 배열 data에 정수 target이 있는지 검색한다.

```java
int search(int n, int data[], int target) {
  for (int i = 0; i < n; i++) {
    if (data[i] == target) 
      return i;
  }
  return -1;
}
```

최악의 경우 시간복잡도는 Ο(n)이다.

- if (data[i] == target)
  - 이 알고리즘에서 가장 자주 실행되는 문장이며, 실행 횟수는 최악의 경우 n번이다.

## Quadratic

> 배열 x에 중복된 원소가 있는지 검사하는 함수이다.

```java
bool is_distinct(int n, int x[]) {
  for (int i = 0; i < n - 1; i++)
    for (int j = i + 1; j < n; j++)
      if (x[i] == x[j])
        return false;
  return true;
}
```

최악의 경우 배열에 저장된 모든 원소 쌍을 비교 하므로 비교 연산의 횟수는 n(n-1)/2이다. 최악의 경우 시간복잡도는 O(n²)으로 나타낸다.

- 이 알고리즘에서 가장 자주 실행되는 문장이며, 최악의 경우 실행 횟수는 n(n-1)/2번이다.

## 점근적 표기법

- 알고리즘에 포함된 연산들의 실행 횟수를 표기하는 하나의 기법
- 최고차항의 차수만으로 표시
- 따라서 가장 자주 실행되는 연산 혹은 문장의 실행횟수를 고려하는 것으로 충분

# **> 알고리즘을 위한 자바 IO**

---

> codeplus - 프로그래밍 대회에서 사용하는 Java 참고

## System.out

- System.out.println();
- System.out.printf("%d", n)
  - 실수형, 문자형 자료 출력 가능

## Scanner

- next[자료형]을 이용해서 입력을 받을 수 있고,
- hasNext[자료형]을 이용해서 입력받을 수 있는 자료형이 있는지 구할 수 있다.
- 두 수 입력

```java
public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int a, b;
    a = scanner.nextInt();
    b = scanner.nextInt();
    System.out.println(a + b);
  }
}
```

- 입력에서 정수가 주어지는 동안 계속 입력 받음

```java
public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int sum = 0;
    while(scanner.hasNextInt()) {
      sum += scanner.nextInt();
    }
    System.out.println(sum);
  }
}
```

- 정수와 문자열 동시 처리
  - 1의 뒤에 줄바꿈 \n이 존재하기 때문에, 줄바꿈을 읽어들여서 hi를 읽지 못한다.
  - 따라서, nextLine()으로 다음 문장을 읽기 전에, scanner.nextLine(); 줄바꿈을 입력받는 코드를 한 줄 작성해야 올바른 의도대로 코딩을 할 수 있다..

```bash
<입력>
1
hi
```

```java
public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    scanner.nextLine();
    String s = scanner.nextLine();
    System.out.println(n + "\n" + s);
  }
}
```

## BufferedReader

- Scanner는 매우 편리하지만 속도가 느리기 때문에, 입력을 많이 받아야 하는 경우에는 BufferedReader를 사용하는 것이 훨씬 좋다.
- bufferedReader에서는 read와 readLine만 있기 때문에, 정수는 파싱을 해야한다.

```java
public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    String[] line = bf.readLine().split(" ");
    String a = line[0] + line[1];
    String b = line[2] + line[3];
    long result = Long.valueOf(a) + Long.valueOf(b);
    System.out.println(result);
  }
}
```

```bash
10 20 30 40
4060
```

## StringTokenizer

- 문자열을 토큰으로 잘라야 할 때 사용하면 편하다.
- 수 N개의 합을 구하는 문제
  - 입력받은 수 N개의 합을 출력한다.
  - 예제입력

```bash
1 2 3 4 5
```

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    String line = bf.readLine();
    StringTokenizer st = new StringTokenizer(line, " ");
    int sum = 0;
    while(st.hasMoreTokens())
      sum += Integer.valueOf(st.nextToken());
    System.out.println(sum);
  }
}
```

```bash
15
```

- 문자열 S에 포함되어 있는 자연수의 합을 출력하라
  - 입력

```bash
10,20,30,40,50
```

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    String line = bf.readLine();
    StringTokenizer st = new StringTokenizer(line, ",");
    int sum = 0;
    while(st.hasMoreTokens())
      sum += Integer.valueOf(st.nextToken());
    System.out.println(sum);
  }
}
```

```bash
150
```

## StringBuilder

- 출력해야 하는 것이 많은 경우에는, 매번 출력하는 것 보다
- StringBuilder를 이용해 문자열을 만들고, 한번에 출력하는 것이 속도면에서 좋다.

```java
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int a = sc.nextInt();
    for (int i = 1; i <= a; i++)
      System.out.println(i);
  }
}

```

```bash
수행시간 : 676 MS, 메모리 : 30256 KB
```

```java
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int a = sc.nextInt();
    StringBuilder sb = new StringBuilder();
    for (int i = 1; i <= a; i++)
      sb.append(i + "\n");
    System.out.println(sb);
  }
}
```

```bash
수행시간 : 216 MS, 메모리 : 11532 KB
```

# **> Iterator**

---

**Iterator**는 자바의 컬렉션 프레임웍에서 컬렉션에 저장되어 있는 요소들을 읽어오는 방법을 표준화 하였는데 그중 하나가 **Iterator** 인터페이스이다.

```java
public interface iterator {
    boolean hasNext();
    Object next();
    void remove;
}
```

- **boolean hasNext()** : 읽어올 요소가 남아있는지 확인한다. 있으면 true, 없으면 false를 반환
- **Obejct next()** : 읽어올 요소가 남아있는지 확인하는 메소드. 있으면 true 없으면 false를 반환
- **void remove()** : `next()`로 읽어온 요소를 삭제한다. 따라서 `next()`를 호출한 다음에 `remove()`를 호출해야 한다. ( 선택적 기능임 )



## List 정렬시키는 방법

```java
ArrayList<Integer> list = new ArrayList<Integer>();

Iterator<Integer> it = list.iterator();
while(it.hasNext()){
    list.get(it.next());
}
```

**Iterator**를 활용해서 list의 모든 값을 가져온다.

**Iterator는 자동으로 Index를 관리해주기 때문에 사용헤 편리함이 있을수 있으나 Iterator를 열어보면 객체를 만들어 사용하기 때문에 느릴 수 밖에 없다.**

**따라서 list의 size를 받아서 사용하는 것이 더 좋다 !!**



## Map 정렬시키는 네가지 방법

```java
HashMap<String, String> map = new HashMap<String, String>();

...
    
// 1
Iterator<String> it = map.keySet().iterator();
while(it.hasNext()){
    String key = it.next();
}

// 2
for(Map.Entry<String, String> elem : map.entrySet()) {
    String key = elem.getKey();
    String value = elem.getValue();
}

// 3
for(String key : map.keySet()){
    String key = key;
    String value = map.get(key);
}

// 4
map.forEach((key, value) -> System.out.println("key: "+key+", value: "+value));
```

성능테스트 결과

4-> 2 -> 3 -> 1

# **> For Each**

배열, List, Map 등에 들어있는 값을 순서대로 꺼내거나 처리를 해야 할 때 for 문을 사용하는 경우가 많습니다.

forEach 함수는 for 같은 반복문을 처리할 때 사용하는 함수입니다.

람다식을 이용해 forEach를 사용하는 방법과 예제를 살펴보겠습니다.

```java
collection.forEach(변수 -> 반복처리(변수))
```

collection에는 배열이나 리스트 등 데이터를 저장해놓은 변수명을 지정합니다.

forEach 함수는 람다식을 사용해서 작성해야 하지만, 간단한 반복 처리 같은 경우에는 쉽게 이해할 수 있습니다.

예제 또한 간단한 반복 처리를 하는 소스로 설명을 하겠습니다.

```java
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("p");
		list.add("p");
		list.add("l");
		list.add("e");

		// 확장 for문
		System.out.println("확장 for문 출력");
		for (String s : list) {
			System.out.println(s);
		}

		// forEach 함수
		System.out.println("forEach 함수 출력");
		list.forEach(s -> System.out.println(s));
	}
}
```

```bash
# 결과
확장 for문 출력
a
p
p
l
e

forEach 함수 출력
a
p
p
l
e
```

for 문을 사용해서 출력한 결과와 forEach 함수를 사용해 출력한 결과는 같습니다.

forEach 함수는 람다식으로 사용하기 때문에 소스도 간결하게 작성할 수 있습니다.

그리고 **람다식**에서 사용하는 **->** 부분도 보입니다.

**->** 앞에 있는 **s**에 리스트 변수인 list에 저장되어 있는 값을 하나씩 대입합니다.

그리고 출력문은 println에서 값이 저장되어 있는 s를 출력하고 있습니다.

## 배열에서의 forEach

배열에서 forEach 함수를 사용하기 위해서는 Stream API를 이용해야 합니다.

```java
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {

		String[] strArray = { "a", "p", "p", "l", "e" };
		Arrays.stream(strArray).forEach(s -> System.out.println(s));
	}
}
```

```bash
# 결과
a
p
p
l
e
```

배열로 값을 저장해한 strArray 변수를 Arrays.stream() 안에 지정해서 forEach 함수로 출력하고 있습니다.

forEach 작성 방법은 동일합니다.

## Map에서의 forEach

이번에는 forEach 사용하여 Map에 들어있는 데이터를 출력하는 예제를 보겠습니다.

Map은 키(Key)와 값(Value)으로 구성되어 있습니다.

forEach를 사용하여 Map에 들어있는 모든 값을 간단하게 출력할 수 있습니다.

```java
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {

		Map<String, String> map = new HashMap<>();
		map.put("korea", "korean");
		map.put("usa", "english");
		map.put("japan", "japanese");

		map.forEach((key, value) -> System.out.println(key + " : " + value));
	}
}
```

```bash
# 결과
korea : korean
usa : english
japan : japanese
```

Map에 들어있는 모든 데이터가 출력되었습니다.

예제에서는 HashMap을 사용했기 때문에 출력 순서는 바뀔 수도 있습니다.

## List에서의 forEach

```java
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("p");
		list.add("p");
		list.add("l");
		list.add("e");

		list.forEach(s -> System.out.println(list.indexOf(s) + " : " + s));
	}
}
```

```bash
# 결과
0 : a
1 : p
2 : p
3 : l
4 : e
```

indexOf 함수를 사용해 인덱스 번호로 List에서 요소를 취득해 출력하도록 하고 있습니다.

List 값을 출력할 때에는 람다식을 생략하고 작성할 수 있습니다.

*List forEach 람다식 생략 예제*

```java
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("p");
		list.add("p");
		list.add("l");
		list.add("e");

		list.forEach(System.out::println);
	}
}
```

```bash
# 결과
a
p
p
l
e
```

forEach 함수에서 람다식을 생략해도 List에 들어있는 모든 값이 출력되었습니다.

> 반복 처리를 할 때 사용하는 for 문을 forEach 함수와 람다식을 이용해 작성하는 방법을 알아봤습니다.
>
> 람다식을 사용하기 때문에 소스를 간결하게 작성할 수 있습니다.
>
> 간단한 반복 처리를 할 때에는 forEach와 람다식을 활용해보는 것도 좋을거 같습니다!

# **> 분할 정복(Divide and Conquer) 알고리즘**

---

이번에 소개해 드릴 것은 역시 유명한 기법인 **분할 정복**(Divide and Conquer)입니다.

탐색, DP, 그리디 등에 비해서는 등장빈도가 좀 낮지만, 이 기법의 성질 자체가 직접 문제푸는데 뿐 아니라 다른 여러 효율적인 자료구조나 알고리즘에 기여할 때가 많습니다.



분할 정복은 말 그대로 문제를 두 단계, **①분할**과 **②정복**으로 나눠서 해결하는 것을 말합니다.

분할하는 단계에서는 말 그대로 주어진 문제를 여러 개의 부분 문제들로 나누는데,

문제가 작아지면 작아질수록 풀기 쉬워지는 성질을 이용한 겁니다.

또한 문제의 크기가 엄청나게 줄어든다면(N=1 또는 N=2 정도) 그야말로 바로 답을 구할 수 있는 수준이 되고, 이게 재귀호출로 문제를 풀 때의 기저 사례(base case)와 같습니다.

그렇습니다. 대체로 분할 정복은 재귀 호출과 아주 죽이 잘 맞습니다.

그리고 기저 사례들로 각 문제의 답을 풀고, 그 문제들을 불렀던 조금 더 큰 문제는 이 답들을 통해 비교적 간단한 연산처리만 해주면 자신의 답도 구할 수 있습니다.

이런 식으로 첫 문제까지 쌓아올려가며 답을 풀어내는 것이 최종 목적.

![image-20200914152139022](https://user-images.githubusercontent.com/58545240/93050981-baf36380-f69e-11ea-8569-d2953a537989.png)

이것이 분할 정복 알고리즘의 대략적인 묘사도입니다.

맨 위의 청록색 문제가 우리가 풀어야 할 문제이고, 그걸 각각 2개의 부분 문제들로 나눠갑니다.

그리고 분홍색까지 쪼개졌을 때 그들은 기저 사례가 되어서 바로 풀 수 있고, 푼 뒤 이들을 나눴던 대로 다시 합쳐 나갑니다.



그냥 풀었을 때보다 오히려 할 일이 많아지지 않을까? 하며 생각하실 수도 있습니다.

그러나 이 기법이 먹히는 경우는, 그냥 풀었을 때보다 저렇게 이미 풀린 부분 문제들을 합치는 게 월등히 빠른 경우입니다.

아주 대표적인 예가 **병합 정렬**(merge sort), **이분 탐색**(binary search), **거듭제곱 연산**(a^b) 등입니다.

![image-20200914152152826](https://user-images.githubusercontent.com/58545240/93050989-bf1f8100-f69e-11ea-9680-204e947acf3a.png)

가장 많이 보이는 경우인, 문제를 정확히 양분하여 2개의 절반 크기의 문제로 분할하는 경우를 생각해 봅시다.

이때 이 문제를 푸는 데는 얼마의 시간이 걸릴까요?

**주의!! 고등 수학 지식이 다소 필요합니다(로그, 수열의 합).**



먼저 문제의 크기(보통 값의 개수를 의미합니다)를 N이라 할 때, 한 단계 분할 후 문제의 크기는 N/2가 될 것입니다. 자명하죠.

그렇다면 기저 사례가 N=1인 문제라고 할 때, 이 문제는 몇 단계까지 분할될까요? log₂N 단계입니다.

N이 2의 거듭제곱꼴일 땐 정확히 log₂N 단계이고, 그것보다는 클 때는 조금 더 많이 분할될 수도 있지만 많아봐야 문제 크기가 N*2일 때를 넘지 못합니다.

즉, 2^(k-1) < N <= 2^k일 때 문제는 k단계까지 분할됩니다. 간단히 그냥 O(logN)단계로 표현할 수 있죠.

그리고 각 단계를 잘 살펴보면, 맨 위 문제부터 0단계, 1단계, ... k단계라고 분류하면 위에서부터 각 단계에 해당하는 부분 문제의 수는 1개, 2개, 4개, 8개, ... 2^k개입니다.

![image-20200914152205535](https://user-images.githubusercontent.com/58545240/93050994-c2b30800-f69e-11ea-9811-cc2f4f7254d6.png)



이제 병합 정렬의 경우를 예로 들어서 얼마나 시간이 단축되는지 설명해보겠습니다.

분할 정복 알고리즘의 수행시간은 문제마다 다 다른데, 여기서 필요한 것은 분할할 때 **①나누어지는 문제의 개수**, **②****분할 후 문제의 크기**, **③각 문제마다 병합(정복) 단계에서 걸리는 시간**이 결정하기 때문입니다.

병합 정렬의 경우, 분할할 **해당 문제 크기**를 N이라 할 때 **①2**, **②N/2**, **③O(N)**입니다. 일단 ①, ②번은 위 그림의 상황과 일치하고, ③ 정보를 통해 최종 시간복잡도를 구할 수 있습니다. (③번이 왜 O(N)인지는 여기서는 설명하지 않겠습니다. 병합 정렬에 대해 찾아보시길.)

분할을 다 했으니 이제 역순으로 저걸 병합해 나갈 차례인데, 아까 살펴본 것처럼 각 단계의 부분문제의 개수가 있습니다.

k-1단계에서는 2^(k-1)번, ... 2단계에서는 2^2=4번, 1단계에서는 2번, 0단계에서는 1번의 병합을 해야 하는데 각 병합에 걸리는 시간이 **해당 문제 크기**가 N일 때 O(N)이라 했죠.

그러므로 각 단계별로 드는 연산 횟수를 죽 늘어놓았을 때



0단계: 1 * O(N)

1단계: 2 * O(N/2)

2단계: 4 * O(N/4)

...

m단계: 2^m * O(N/(2^m)) = **O(N)**

...



따라서 단계별 식을 일반화해 보면 각 단계에서 드는 총합 연산량은 O(N)입니다. 이는 매 단계마다 각 문제의 크기 역시 지수적으로 줄어들기 때문이죠!

그리고 아까 봤듯이 단계는 총 O(logN)개 있으므로, 이를 곱해서 **O(NlogN)**이라는 시간복잡도를 구할 수 있습니다!

버블 정렬, 선택 정렬 등의 기본적인 알고리즘이 O(N^2)이라는 점에서 보면 굉장한 발전이라고 할 수 있으며, 현재 일반적인 알고리즘 중 가장 빠른 퀵 정렬 또한 분할 정복 기법을 사용합니다.

C++의 STL에 속한 sort 함수, C의 qsort 함수 등 여러 언어의 라이브러리에서 이 빠른 분할 정복을 베이스로 한 정렬 함수를 지원합니다.

![image-20200914152222053](https://user-images.githubusercontent.com/58545240/93051003-c6468f00-f69e-11ea-9b12-8f255534e11e.png)


이제 고등 수학 수준의 수열의 합에 대한 지식이 있다면, 위에서 언급한 ① ② ③이 무엇이냐에 따라 바로 전체 시간복잡도를 구해낼 수 있는, 즉 분할 정복 기법 시간복잡도의 일반화인 **마스터 이론**(Master Theorem)을 이해하는 것도 가능합니다.

① ② ③ 값이 각각 a, b, d와 대응합니다. 그러나 형태가 조금 바뀌었으므로 알아서 변환해 넣으셔야 합니다.

원리를 찾아보시면 위 과정과 비슷하게 진행되므로 시간을 들여 천천히 보시면 무리없이 이해 가능합니다.



그럼 아까 언급했던 다른 예를 조금만 봅시다.

**이분 탐색**이 간단한 예인데, N개의 정렬되어 있는 수들 중 어떤 값 K의 위치를 찾아내거나, 없다는 것을 판정하는 탐색입니다.

그냥 차례대로 훑어보면 O(N)이지만, 정말 놀랍게도 이분 탐색은 **O(logN)**의 시간복잡도를 자랑합니다.

![image-20200914152239010](https://user-images.githubusercontent.com/58545240/93051016-c9da1600-f69e-11ea-805b-0c2b3fa28230.png)

우리는 이 N=10인 배열에서 값 **8**을 찾으려고 합니다.

![image-20200914152250209](https://user-images.githubusercontent.com/58545240/93051031-ce063380-f69e-11ea-88cd-afd31a76a2bc.png)


먼저 우리가 할 일은 이렇게 가장 가운데 지점을 피벗으로 삼는 겁니다.

보통 시작 인덱스 번호와 끝 인덱스 번호의 평균을 내는데, 이게 홀수일 경우 맘대로 올림하거나 내림하면 됩니다. 그래도 절반에 가까운 건 변하지 않기 때문. 저는 이번 예제에서는 아무렇게나 하겠습니다.



이제 이 중심점의 값보다 8이 큰지 작은지, 아니면 중심점이 8인지 검사합니다. 중심점이 8이라면 5번 인덱스에서 찾은 것이죠.

일단 값이 15이고 우리가 찾을 값이 8이니까, **오름차순 정렬이 되어있다는 배열의 특성상 15 오른쪽에는 절대 8이 없습니다.** 따라서 우리는 15보다 왼쪽 값들만 찾으면 됩니다.

![image-20200914152302796](https://user-images.githubusercontent.com/58545240/93051045-d2325100-f69e-11ea-9692-b1d614017368.png)

자, 그럼 값이 절대 없다고 판명난 곳을 까맣게 칠해버리고 이어서 찾아봅시다.

이제 문제는 배열 [2, 5, 7, 8, 9]에서 값 8을 찾는, **더 작은 문제**로 바뀌었음을 알 수 있습니다. 그렇습니다. 문제가 분할된 거죠.

여기서도 마찬가지로 중심점을 찾아 그 값을 보니 7이고, 7보다 8이 크니까 7 왼쪽엔 절대 8이 없다는 걸 알 수 있습니다.

![image-20200914152318336](https://user-images.githubusercontent.com/58545240/93051054-d65e6e80-f69e-11ea-869b-8c8d7cbf3c23.png)

또 줄어들었습니다. 이제 뭐 값도 2개밖에 안 남았지만 말이죠, 일단 9를 중심점으로 뽑았다고 합시다.

8은 9보다 작죠? 그렇습니다. 8은 7 오른쪽, 9 왼쪽에 있을 수밖에 없는 겁니다.

![image-20200914152329936](https://user-images.githubusercontent.com/58545240/93051061-d9f1f580-f69e-11ea-875a-679cbe875818.png)


마지막에 남은 게 달랑 값 하나라 중심점 후보도 하나밖에 없는데, 그게 8이라 찾았습니다.

만약 값이 하나도 안 남았는데(구간 왼쪽 끝과 오른쪽 끝이 같아지거나 하는 점으로 판별 가능) 아직도 못 찾았다면, 그 값은 배열 안에 없다고 판정할 수 있습니다.

극단적인 예를 보여드리기 위해 마지막에 값을 찾는 걸 보여드렸지만 만약 여기서 7을 찾았다던가 하면 단 두 단계만에 찾았겠지요.



이 이분 탐색 예제는 굉장히 특이하게도 별도의 병합 과정조차 필요없습니다. 왜냐면 분할을 하긴 하는데 그 중 한 문제만 풀면 되기 때문.

따라서 마스터 이론에 a=1, b=2, d=0을 대입하면 되고 시간복잡도는 가운데 케이스가 되어 O(logN)이 됩니다.



이진 탐색을 해주는 함수 또한 여러 언어가 제공하는데, C++의 경우 존재하는지만 알려주는 binary_search(), 아예 위치를 찾아주는 lower_bound(), upper_bound() 등이 존재합니다.

lower_bound()는 찾을 값 이상의 값이 처음 등장하는 곳을, upper_bound()는 찾을 값보다 큰 값이 처음 등장하는 곳을 찾아줍니다.

![image-20200914152341512](https://user-images.githubusercontent.com/58545240/93051113-f1c97980-f69e-11ea-9cbb-0f568ed1d596.png)

또다른 유명한 문제로는 워낙 풀이가 여러가지이기로 소문난 히스토그램 문제입니다.

이렇게 넓이가 일정한 직사각형들이 이어붙여져 있는데, 이 영역들 사이에 숨어있는 가장 큰 면적의 직사각형을 찾으라는 겁니다.

직사각형 개수 N이 최대 100,000이나 되므로 O(N^2)는 무리, 최소한 O(NlogN), O(N) 정도는 되어야 풀 수 있을 겁니다.

로그가 잘 뜨는 분할 정복 특성상 O(NlogN)일 것 같죠? 그리고 놀라운 사실은 스택을 사용하는 O(N) 알고리즘이 있다는 것이지만, 일단 여기서는 분할 정복 방법을 살펴봅시다.



여기서는 방법이 좀 더 복잡합니다. 일단 여태 그래왔듯이 직사각형들을 왼쪽 반, 오른쪽 반으로 쪼개서 양쪽 문제를 풀면 좋을 것입니다.

그렇게 되면 왼쪽 영역에서 찾은 제일 큰 직사각형, 오른쪽 영역에서 찾은 제일 큰 직사각형 중 큰 게 답이겠...지요 는 잠깐.

![image-20200914152354096](https://user-images.githubusercontent.com/58545240/93051130-f726c400-f69e-11ea-963e-5286a8396a3a.png)

이 경우, **분할점에 진짜 답이 걸쳐 있을 수가 있습니다.** 즉 왼쪽 오른쪽 부분을 나누는 기준점을 직사각형이 포함하고 있는 경우, 단순히 양쪽 부분 문제들은 걔네를 못 찾을 확률이 큽니다.

사실, 매번 문제를 N=1일 때까지 쪼갤 거라서 이 경우를 처리해주지 않으면 기껏해야 넓이 1이고 가장 긴 직사각형밖에 못 찾을 겁니다...



따라서 조금 더 복잡한데, 매번 문제를 병합할 때마다 가장 중간부터 시작하여 영역을 확장해 나가며 양쪽 영역에 걸친 것들 중 가장 넓은 직사각형을 찾는 방법이 있습니다.

처음엔 가장 가운데의 넓이 1~2 정도로 시작한 후 직사각형을 양쪽으로 넓혀가는 것인데, 매 단계마다 넓이를 1씩 확장하되 바로 왼쪽과 오른쪽 중 높은 곳부터 확장해 나가야 합니다.

![image-20200914152418022](https://user-images.githubusercontent.com/58545240/93051138-faba4b00-f69e-11ea-9217-0594ccd5e0d8.png)

이 예제를 봅시다. 심히 찌부러지게 그려놨지만 왼쪽부터 높이가 [6, 2, 5, 4, 5, 1, 6]입니다.

예제는 http://www.geeksforgeeks.org/largest-rectangular-area-in-a-histogram-set-1/ 여기 있는 걸 그대로 썼습니다.

일단 제일 가운데의 직사각형부터 시작합니다. 넓이 1, 높이 4인 상태로 시작합니다.

![image-20200914152429990](https://user-images.githubusercontent.com/58545240/93051148-fdb53b80-f69e-11ea-8946-c53cbb6ff6bf.png)

그 다음, 빨간 직사각형 양옆의 높이가 5, 5인데 어느 쪽으로 확장해도 빨간 직사각형의 높이는 줄어들지 않으므로 아무 쪽으로나 확장합시다.

저는 오른쪽을 선택했고 현재 넓이는 2*4=8.

![image-20200914152444388](https://user-images.githubusercontent.com/58545240/93051154-0148c280-f69f-11ea-94ae-dd40952f1b86.png)

그 다음, 왼쪽 높이는 5고 오른쪽 높이는 1(!!!!)이니까 왼쪽으로 확장합니다. 넓이는 3*4=12.

![image-20200914152456374](https://user-images.githubusercontent.com/58545240/93051169-0574e000-f69f-11ea-809a-48e96e2ddc14.png)

그 다음, 왼쪽 2 오른쪽 1이니까 왼쪽으로 확장하는데, 이때는 양쪽 높이가 다 현재 높이 4보다 작으니까 높이가 줄어들 수밖에 없습니다. 넓이는 4*2=8이 됩니다.

![image-20200914152506501](https://user-images.githubusercontent.com/58545240/93051171-09086700-f69f-11ea-8482-de270b337b89.png)

그 다음 왼쪽으로 확장합니다. 넓이는 5*2=10. 이번엔 높이가 줄지 않죠.

![image-20200914152518908](https://user-images.githubusercontent.com/58545240/93051180-0c9bee00-f69f-11ea-8b9f-561e0f902680.png)

왼쪽으로는 더 확장할 수 없으므로 오른쪽으로. 이때 높이가 또 줄어듭니다.

넓이는 6*1=6......

![image-20200914152530714](https://user-images.githubusercontent.com/58545240/93051202-132a6580-f69f-11ea-8524-78b2702b645c.png)

마지막 확장을 하고 나면 넓이는 7*1=7이고,

지금까지 확장하면서 생겼던 최대 넓이인 12가 중간점에 걸친 직사각형 중 최대 영역입니다.

이걸 구하는 시간은 O(N)이라는 것을 어렵지 않게 알 수 있습니다.

또한 이 병합 과정에서 사용하는 방법도 하나의 그리디 기법이라 할 수 있으며, 이렇게 기초적인 기법들은 다른 좀 더 어려운 알고리즘에서 직/간접적으로 많이 등장합니다.



어쨌든 이렇게 문제를 병합할 때마다 해당 문제의 답은 **왼쪽 영역에서 얻어진 최대 영역**, **오른쪽 영역에서 얻어진 최대 영역**, **양쪽 영역에 걸친 직사각형 중 최대 영역** 이 3가지 중 최댓값이 됩니다.

마스터 이론에 대입해보면 a = 2, b = 2, d = 1이므로 시간복잡도는 O(NlogN)입니다.

```c++
#include <cstdio>
#include <algorithm>
using namespace std;
 
int N, H[100000];
 
// 분할 정복으로 히스토그램 [s, e) 문제를 푸는 함수
int histogram(int s, int e){
    if(s == e) return 0; // base case: 넓이 0
    if(s+1 == e) return H[s]; // base case: 넓이 1
 
    int mid = (s+e)/2;
    int result = max(histogram(s, mid), histogram(mid, e)); // 각 양쪽 구간의 최댓값
 
    // 양쪽 구간에 걸쳐 있는 답을 O(e-s)에 찾음
    int w = 1, h = H[mid], l = mid, r = mid;
    while(r-l+1 < e-s){
        int p = l>s ? min(h, H[l-1]) : -1; // 왼쪽으로 확장했을 경우의 높이
        int q = r<e-1 ? min(h, H[r+1]) : -1; // 오른쪽으로 확장했을 경우의 높이
        // 더 높은(같은) 높이를 가진 쪽으로 확장
        if(p >= q){
            h = p;
            l--;
        }
        else{
            h = q;
            r++;
        }
        // 최댓값 갱신
        result = max(result, ++w*h);
    }
    return result;
}
 
int main(){
    scanf("%d", &N);
    for(int i=0; i<N; i++)
        scanf("%d", H+i);
    printf("%d\n", histogram(0, N));
}
```

## 분할정복 추천문제

**1629번: 곱셈**

A^B를 구하는 문제입니다. 이때 B가 무지막지하게 크기 때문에 2147483647번 곱하다가는 걍 시간초과입니다.

잘 생각해 보면 B가 짝수일 때 A^B는 A^(B/2)의 제곱입니다. B가 홀수일 때는 A^(B/2)*A이고요.

이렇게 연산량을 대폭 줄일 수 있고 시간복잡도는 O(logB)가 됩니다.

중간에 발생할 수 있는 오버플로우를 조심하시고, 가급적이면 long long 자료형을 사용하시며 나머지 연산을 즉각즉각 해주시는 것이 좋습니다.



**2104번: 부분배열 고르기**

히스토그램 문제와 굉장히 유사한 풀이를 갖고 있습니다.

계속 문제를 양분하며, 왼쪽 구간에 답이 있는 경우, 오른쪽 구간에 답이 있는 경우, 정답을 가진 영역이 양쪽에 걸친 경우 3가지를 고려해 그 중 최댓값을 리턴하면 됩니다.



**1725번: 히스토그램**

위에서 설명한 그 문제입니다.

저는 스택을 써서 풀었기에 깃헙의 코드는 도움이 안 될지도...

위의 부분배열 구하기 문제와 풀이가 아주 유사하므로 그 코드를 참고하시는 게.



**1780번: 종이의 개수**

전체 영역이 모두 같은 값이라면 한 장만 사용해도 좋은 기저 사례이고,

그게 아니라면 가로 3, 세로 3 영역으로 9등분하여 각 영역에 대해 재귀호출해 분할하여 문제를 풀고,

마지막에 각 영역의 답을 모두 더하는 합병 과정을 거칩니다.

a = 9, b = 9, d = 1이므로 시간복잡도는 O(NlogN)이고, N이 최대 3^7 = 2187이므로 충분한 수치.



**1992번: 쿼드 트리**

역시 정말 유명한 분할 정복 응용 문제 중 하나이고, 바로 위 종이의 개수 문제와 풀이가 아주 유사합니다.

이 경우는 값에 따라 출력을 해야 하는데 문제에서 주어진 것처럼 좌상단->우상단->좌하단->우하단 순서로 부분문제를 풀어야만 하는 점만 지켜주면 어렵지 않습니다.



**1074번: Z**

영역을 정확히 좌상단, 우상단, 좌하단, 우하단으로 4등분할 수 있고 방문하는 순서도 순서대로입니다.

쿼드 트리 문제와 비슷하게 풀 수 있고 이 문제는 분할 후에도 단 하나의 문제만 찾아가 풀면 됩니다.

문제가 줄어들 때마다 해당 영역 안에 있는 좌표로 조정해주는 것이 풀기 쉽습니다. 그리고 지나쳐온 다른 영역의 크기를 다 더해주면 됩니다.



**2447번: 별 찍기 10**

전역 배열을 하나 만들고 재귀호출을 해가며 각 칸을 채우는 것이 효율적입니다.

역시 주어진 영역을 9등분하여 가운데는 채우지 않고 나머지 8개 영역은 똑같이 재귀적으로 채워 나갑니다. 영역의 크기가 1이 됐다면 *로 채웁니다.



**2339번: 석판 자르기 (★)**

구현하기 상당히 난감한 문제.

매번 가능한 모든 경우를 다 시도해 보며(즉, 있는 모든 불순물에 대해 한번씩 시도해 보며) 얻은 경우의 수를 다 합칩니다.

이때 불순물을 기준으로 석판을 2조각 낼 텐데 각 조각에 따라 다시 재귀호출로 문제를 풀어서 분할 정복 기법을 사용합니다.

양쪽의 경우의 수가 A, B일 때 이 문제의 최종 답은 경우의 수 성질에 의해 A*B임을 주의하세요.

# **> 회문판별**

---

> - 회문은 유전자 염기서열 분석에서 많이 쓰인다
> - 회문(palindrome)은 순서를 거꾸로 읽어도 제대로 읽은 것과 같은 단어와 문장을 말한다.

```C
#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <string.h>
#include <stdbool.h>

int main(){
    char word[30];				// 단어 저장 배열
    int length;					// 문자열 길이
    bool isPalindrome = true;	// 회문 판별값을 저장할 변수, 초깃값 TRUE
    
    printf("단어 입력하세요: ");
    scanf("%s", word);
    
    length = strlen(word);
    
    // 0부터 문자열 길이의 절반만큼 반복
    for(int i=0; i<length/2; i++){
        // 왼쪽 문자와 오른쪽 문자를 비교하여 문자가 다르면
        if(word[i] != word[length-1-i]){
            //회문이 아님
            isPalindrome = false;
            break;
        }
    }
    printf("%d\n", isPalindrome);	// 회문 판별값 출력
    
    return 0;
}
```

# **> N-gram**

---

> - 빅데이터 분석, 검색 엔진에서 많이 쓰인다. 구글은 책들을 스캔해서 N-gram viewer를 만들었는데 사람들의 언어 패턴을 시대별로 분석하기도 함.
> - N-gram은 문자열에서 N개의 연속된 요소를 추출하는 방법. 만약 "hello"라는 문자열을 2-gram으로 추출하면
>   - He, el, ll, lo

- 글자 단위 N-gram( 2gram )

```C
#include <stdio.h>
#include <string.h>

int main(){
    char text[30] = "Hello";
    int length;
    
    length = strlen(text);
    
    for (int i=0; i<length; i++){
        printf("%c%c\n", text[i], text[i+1]);		// 현재 문자와 그 다음 문자 출력
    }
    
    return 0;
}
```

- 단어 단위 N-gram( 2gram )

```C
#define _CRT_SECURE_NO_WARNINGS 	// strtok 보안 경고로 인한 컴파일 에러 방지
#include <stdio.h>
#include <string.h>

int main(){
    char text[100] = "this is c language";
    char *tokens[30] = { NULL, };		// 자른 문자열의 포인터를 보관활 배열, NULL로 초기화
    int count = 0;
    
    char *ptr = strtok(text, " ");		// " " 공백 문자를 기준으로 문자열을 자름, 포인터 반환
    
    while(ptr != NULL){	// 자른 문자열이 나오지 않을 때까지 반복
        token[count] = ptr;		// 문자열을 자른 뒤 메모리 주소를 포인터 배열에 저장
        count++;
        
        ptr = strtok(NULL, " ");		// 다음 문자열을 잘라서 포인터를 반환
    }
    
    // 2-gram이므로 배열의 마지막에서 요소 한 개 앞까지만 반복함
    for(int i=0; i<count-1; i++){
        printf("%s %s\n", tokens[i], tokens[i+1]);
    }
    return 0;
}
```

> **N-gram의활용**
>
> ```bash
> 4-gram을 쓰면 picked, picks, picking에서 pick만 추출하여 단어의 빈도를 세는 데 이용된다. 이런 특성 때문에 검색엔진, 빅데이터, 법언어학 분야에서 주로 활용된다.
> 
> 해리포터의 작가 조앤 롤링은 가명으로 <더 쿠쿠스 콜링>이라는 소설을 출간한 적이 있는데 그 작가가 조앤롤링 이라는 것을 밝혀내는데 N-gram을 비롯한 다양한 기법이 동원되었다. 즉, 사람마다 사용하는 문장에 패턴이 있는것. 그래서 같은 의미라 하더라도 사람마다 단어 선택이 다르다는 것을 통계적으로 분석해낸 사례이다.
> ```

# **> 두 점 사이 거리 구하기**

---

```C
#include <stdio.h>
#include <math.h> 		// sqrt함수

struct Point2D{
    int x;
    int y;
};

int main(){
    struct Point2D p1;
    struct Point2D p2;
    
    p1.x = 30;
    p1.y = 20;
    
    p2.x = 60;
    p2.y = 50;
    
    int a = p2.x - p1.x;
    int b = p2.y - p1.y;
    
    double c = sqrt((a * a) + (b * b));
    
    printf("%f\n", c);
    
    return 0;
}
```

# **> KMP**

---

KMP알고리즘에 대해 간략히 설명 하자면, 지금까지 알려진 문자열 알고리즘 가운데 가장 최저의 시간복잡도를 가진 알고리즘이다. 

일단, KMP알고리즘의 시간복잡도는 O(N+K) 여기서 N과 K는 비교할 문자열의 길이이다. 매칭을 하려면 최소한 비교대상과 타겟의 문자열을 한번씩 읽어봐야 할테니, 가장 최적의 시간복잡도이다.

알고리즘에 대한 기본적인 설명과 이해는 아래의 링크를 통해서 천천히 반복적으로 학습하는 것을 추천하고, 본인 역시 아래의 링크를 참고해서 학습한 내용에 이해에 필요한 설명을 추가하려 포스팅하려고 한다.

https://m.blog.naver.com/kks227/220917078260



## - BOJ[1786] : 찾기

https://www.acmicpc.net/problem/1786

- KMP알고리즘을 통해 구현한다
- 일치하는 문자열이 있는 경우, `Cnt++`를 시키고, 해당 Index를 Li리스트에 담는다
- 모두 검사한 후, Cnt와 Li에 있는 원소를 전부 출력한다.

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main_bj_1786_찾기 {
    static int cnt = 0;
    static List<Integer> li;
    static int[] getPi(String ptn) {
        int[] pi = new int[ptn.length()];
        int j = 0;
        for(int i=1; i<ptn.length; i++){
            while(j>0 && ptn.charAt(i)!=ptn.charAt(j)){
                j = pi[j-1];
            }
            if(ptn.charAt(i) == ptn.charAt(j))
                pi[i] = ++j;
        }
        return pi;
    }
    
    static void KMP(String org, String ptn) {
        int pi[] = getPi(ptn);
        int j = 0;
        for(int i=0; i<org.length(); i++) {
            while(j>0 && org.charAt(i)!=ptn.charAt(j)) {
                j = pi[j-1];
            }
            if(org.charAt(i)==ptn.charAt(j)) {
                if(j==ptn.length()-1) {
                    ++cnt;
                    li.add(i-j+1);
                    j=pi[j];
                } else {
                    j++;
                }
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(Sytem.in));
        String origin = br.readLine();
        String pattern = br.readLine();
        li = new ArrayList<>();
        KMP(origin, pattern);
        System.out.println(cnt);
        for(int i=0; i<cnt; i++)
            System.out.println(li.get(i));
    }
}
```

# **> 스위핑 기법(Sweeping Algorithm)**

---

이번에 소개해 드릴 기법은 **스위핑 알고리즘**(sweeping algorithm)이라고 하는데, 기법 개념 자체는 굉장히 간단하고 범용적인 대신에,

대부분 겁나게 어렵습니다. 기본적으로 다른 기법이나 자료구조와 반드시 얽힙니다.



스위핑이라는 건 그냥 어떤 선이나 공간을 한쪽에서부터 싹 쓸어버린다는 건데

한 번만 전체 공간을 스캔하면서 마주치는 요소들에 대해 뭔가를 해 주면 정답이 구해지는 형태입니다.

## - BOJ[2170] : 선 긋기

https://www.acmicpc.net/problem/2170

수평선 위에 선분을 여러 변 긋는데, 모든 선분을 긋고 나서 수평선 위에 덮인 구간 길이 총합을 구하라는 문제입니다.

당연히, 좌표가 20억 1개 구간이고 선이 백만 개니까 20억 1개 칸 배열을 만들고 무슨 짓을 하는 건 안 됩니다.

![image-20200922165012556](https://user-images.githubusercontent.com/58545240/93857036-ec021280-fcf4-11ea-8fb2-946b886e76ee.png)

일단 예제를 그림으로 나타내면 이와 같고, 답은 5가 됩니다.

이때, 구간이 2군데로 분리되어 나타내어지는 것을 볼 수 있고 각 길이가 4, 1이죠. 결과는 이들의 합 5고요.

이제 우리는 만약 이어졌거나 겹치는 선분들끼리 아예 한 구간으로 **합쳐서**, 마지막에 최종적으로 다 합쳐지고 남은 구간들의 길이만 세서 더하면 되지 않을까 하는 생각을 해봅니다.

그걸 쉽게 하려면 어떻게 해야 할까요?

**제일 먼저 시작하는**(왼쪽 끝점 좌표가 작은) 선분부터 순회하면 됩니다.

예제의 경우 [1, 3], [2, 5], [3, 5], [6, 7]의 순서대로 순회하면 됩니다.

![image-20200922165022996](https://user-images.githubusercontent.com/58545240/93857047-f02e3000-fcf4-11ea-8d1e-7d4282070ccf.png)

이제부터 선분들을 순회하면서, 현재의 구간을 이어붙일 수 있는 한 최대한 이어붙이다가

더 이어붙일 수 없으면 이번 구간의 길이를 결과에 더하는 식으로 알고리즘을 수행할 겁니다.

따라서 순회하면서 가지고 있어야 하는 정보는 현재 순회할 선분 번호, 현재 구간의 시작 좌표입니다.



먼저 첫 번째 선분은, 뭐... 첫 번째니까 현재 구간이 자신으로 초기화됩니다.

현재 구간은 [1, 3]이 됩니다.

![image-20200922165035008](https://user-images.githubusercontent.com/58545240/93857054-f4f2e400-fcf4-11ea-9a8e-cb3852ea85f0.png)

두 번째 선분 [2, 5]를 순회하면, 현재까지의 구간과 이번 선분이 겹치거나 이어져 있으므로 합칠 수가 있습니다!

현재 구간은 [1, 5]가 됩니다.

![image-20200922165053575](https://user-images.githubusercontent.com/58545240/93857064-f9b79800-fcf4-11ea-88ae-33159dcb0966.png)

세 번째 선분 [3, 5]는 이미 현재 구간에 포함이 됩니다. 현재 구간은 변함이 없습니다.

![image-20200922165105874](https://user-images.githubusercontent.com/58545240/93857075-fe7c4c00-fcf4-11ea-9b9e-8901abad0d1c.png)

네 번째 선분 [6, 7]을 봅시다. 이제 이번 선분과 현재 구간은 절대 합칠 수가 없습니다.

이때는 현재 구간이 [1, 5]니까 그 길이 4를 결과에 더하고, 구간을 다시 이번 선분인 [6, 7]로 초기화합니다.

![image-20200922165115058](https://user-images.githubusercontent.com/58545240/93857088-02a86980-fcf5-11ea-8544-001a4f55e2f7.png)

모든 선분을 다 순회하고 나서도, 마지막에 남은 구간 [6, 7]의 길이 1도 결과해 더해 줘야 합니다.

이렇게 결과는 4+1 = **5**가 됩니다.



지금 이렇게 한 번 더 합칠 수 없게 된 구간은 더 이상 절대 합쳐질 수 없는 이유가 선분을 왼쪽 끝 좌표 순서대로 순회했기 때문입니다.

왼쪽 끝 좌표가 같으면 어떤 것부터 순회해야 할까요? 그때는 아무 상관이 없다는 것을 어렵지 않게 알 수 있습니다.

현재 구간과 이번 선분이 합쳐질 수 있느냐의 여부를 결정하는 것은 이번 선분의 왼쪽 끝 좌표이기 때문입니다. 따라서 왼쪽 끝 좌표가 같은데 어떤 건 합쳐지고 어떤 건 합쳐지지 않거나 하여 순서에 따라 결과가 달라지는 일은 있을 수 없습니다.

따라서 이 알고리즘의 형태를 다르게 표현하면, **수평선의 왼쪽 끝에서부터 훑다가 마주치는 선분이 있으면 뭔가 처리를 해 주는 식**으로 말할 수 있습니다.

따라서 이 알고리즘을 완수하려면 선분을 왼쪽 끝 좌표순으로 정렬하여 순회하면 됩니다.

```C++
#include <cstdio>
#include <utility>
#include <algorithm>
using namespace std;
typedef pair<int, int> P;
const int INF = 1e9+1;
 
int main(){
    int N;
    scanf("%d", &N);
    P L[1000000];
    for(int i=0; i<N; i++){
        int s, e;
        scanf("%d %d", &s, &e);
        L[i] = P(s, e);
    }
    // 선분들을 왼쪽 끝 좌표 순으로 정렬
    sort(L, L+N);
 
    // [l, r]: 현재 합치고 있는 구간
    int result = 0, l = -INF, r = -INF;
    for(int i=0; i<N; i++){
        // 현재 구간과 이번 선분이 합쳐질 수 없음
        if(r < L[i].first){
            // 결과에 더함
            result += r-l;
            // 현재 구간을 이번 선분으로 초기화
            l = L[i].first;
            r = L[i].second;
        }
        // 합쳐짐: 현재 구간의 r을 늘릴 수 있으면 늘림
        else r = max(r, L[i].second);
    }
    result += r-l; // 마지막 구간도 결과에 더해 줌
    printf("%d\n", result);
}
```

소스 코드는 위와 같습니다.

위 문제는 수평선 하나만을 훑지만, 아예 2차원 평면을 쭉 훑는 경우도 있습니다.

이때는 대체로 평면을 하나의 수평선이나 수직선으로 훑으면서 마주치는 점, 선, 다각형 등의 도형을 차례차례 처리해 주는 형태를 띄게 됩니다.

물론 난이도는 1차원이었을 때보다 높은 편이고, 자료구조가 정말 중요합니다.

## - BOJ[5419] : 북서풍

https://www.acmicpc.net/problem/5419

이 문제에서는, 2차원 평면 안의 점들 중 **x좌표가 같거나 커지고, y좌표가 같거나 작아지는 점 쌍**이 몇 개나 되는지를 구하라고 합니다.

당연히, O(N^2) 브루트포스가 먹힐 리는 없죠. 75000*75000은 시간 안에 절대 불가능한 수치입니다.



이걸 스위핑으로 어떻게 풀 수 있을까요? 아이디어는 이러합니다.

만약 **y축에 평행한 수직선**을 제일 왼쪽 끝에서부터 제일 오른쪽 끝으로 훑으면서 **마주치는 점들마다 이 점으로 항해할 수 있는 점의 개수를 센다**고 해 봅시다. 이번에 마주친 점을 P라고 해보죠.

쉽게 생각하기 위해 x좌표가 같은 점은 없다고 하면, P로 항해 가능하려면 반대편 점은 **이미 수직선과 마주쳤어야 합니다.** 그래야 항해하면서 x좌표가 커지죠?

따라서, 지금까지 수직선과 마주쳤던 점들, 즉 x좌표가 P의 것보다 작은 점들 중에서 **y좌표는 P의 것보다 크거나 같은 점의 개수**를 세서 결과에 더하면 됩니다.



그럼 이제 문제를 좀 더 어렵게 해서, x좌표가 같은 점들이 존재할 수 있다면 x좌표가 같은 점들 중에서는 어떤 것을 먼저 방문하면 좋을까요?

y좌표가 큰 것부터 방문하는 게 좋습니다. 그렇다면 x좌표가 같은 점 두 개를 P, Q라 하고 P.y > Q.y라 하면 P->Q로만 항해가 가능하니까, Q를 방문할 때 P를 이미 방문한 상태인 게 구현하기 좀 더 쉬울 것 같지요?

![image-20200922165218006](https://user-images.githubusercontent.com/58545240/93857141-194ec080-fcf5-11ea-8fda-707c93a916d3.png)

이러한 문제가 있다고 해 봅시다. 이제 수직선을 왼쪽에 두고 시작해 오른쪽으로 훑으면서 마주치는 점들마다 자신으로 항해 가능한 다른 점의 개수를 셀 것입니다.

![image-20200922165229032](https://user-images.githubusercontent.com/58545240/93857149-1d7ade00-fcf5-11ea-9458-5f8832512ee8.png)

먼저 제일 왼쪽에 있는 점을 마주쳤습니다.

이걸 첫 번째로 방문했으니 당연히... 이 점으로 항해할 수 있는 다른 점은 없겠죠.

![image-20200922165238959](https://user-images.githubusercontent.com/58545240/93857157-22d82880-fcf5-11ea-813f-b3aca344a9e7.png)

두 번째로 마주친 점은, 방금 첫 번째 점에서 항해해올 수 있습니다.

그러므로 결과에 1을 더합니다.

![image-20200922165249155](https://user-images.githubusercontent.com/58545240/93857171-279cdc80-fcf5-11ea-9e30-96b6c66b064e.png)

세 번째 점은 역시 첫 번째 점에서 도달 가능하므로 결과 +1.

![image-20200922165259746](https://user-images.githubusercontent.com/58545240/93857185-2ec3ea80-fcf5-11ea-82dc-58098a673108.png)

자 이번엔 x좌표가 같은 두 개의 점을 만났습니다.

약속대로 y좌표가 큰 녀석부터 방문합시다. 이 점으로 항해할 수 있는 점은 없습니다.

![image-20200922165308725](https://user-images.githubusercontent.com/58545240/93857199-32f00800-fcf5-11ea-9faf-b0bad7a15a3b.png)

바로 아래의 점은, 자신과 x좌표가 같거나 y좌표가 같은 점까지 포함해 총 3곳에서 항해해올 수 있습니다.

이때, y좌표가 더 큰 점을 미리 방문했기에 아직까지도 **이번 점으로 항해할 수 있는 점은 이미 방문한 점들 중에만 있다**는 법칙이 깨지지 않은 겁니다.

![image-20200922165321551](https://user-images.githubusercontent.com/58545240/93857206-37b4bc00-fcf5-11ea-87a1-f4282c9be325.png)

여섯 번째 점.

![image-20200922165331929](https://user-images.githubusercontent.com/58545240/93857215-3daa9d00-fcf5-11ea-9ad4-35c987656747.png)

대망의 마지막 점. 이렇게 매번 항해 가능한 점 개수를 세서 더하면 결과가 되는데요.

자 그럼, 도대체 어떻게 빠른 시간 안에 반대편 점의 개수를 셀까요?

지금까지 과정을 돌이켜보면 우리가 센 점의 개수는 **이미 방문한 점들 중에서 현재 점보다 크거나 같은 y좌표를 가진 점의 개수**입니다.

조금만 더 힌트를 드리기 위해 말을 변형시켜 보면, 이미 방문한 점들 중에서 **구간 [P.y, ∞]**에 속하는 y좌표를 가지는 점의 **개수**입니다.



정답은 **세그먼트 트리**입니다.

점이 최대 75000개니까, 서로 구분되는 y좌표의 개수는 최대 75000개일 것이므로 각 y좌표마다 0~74999 사이로 재설정하는 과정을 거친 후, 각 y좌표마다 대응하는 (75000개의) 리프 노드를 가진 세그먼트 트리를 만듭니다.

수직선으로 점들을 훑으면서 일단 **구간 [P.y, MAX_Y]의 합**을 결과에 더하고(자신으로 항해 가능한 점의 개수를 세는 과정),

자신의 y좌표에 해당하는 칸을 +1하는 식으로 구현하면 알고리즘을 구현할 수가 있게 됩니다!

```C++
#include <cstdio>
#include <algorithm>
using namespace std;
const int SIZE = 1<<18; // 75000*2 이상
typedef pair<int, int> P;
 
// 세그먼트 트리 구조체
struct SegTree{
    int arr[SIZE];
    SegTree(){ fill(arr, arr+SIZE, 0); }
    // n번째 리프 노드를 1 증가시킴
    void inc(int n){
        n += SIZE/2;
        while(n > 0){
            arr[n]++;
            n /= 2;
        }
    }
    // 구간 [s, e)의 합
    int sum(int s, int e){ return sum(s, e, 1, 0, SIZE/2); }
    int sum(int s, int e, int node, int ns, int ne){
        if(e <= ns || ne <= s) return 0;
        if(s <= ns && ne <= e) return arr[node];
        int mid = (ns+ne)/2;
        return sum(s, e, node*2, ns, mid) + sum(s, e, node*2+1, mid, ne);
    }
};
 
int main(){
    int T;
    scanf("%d", &T);
    for(int t=0; t<T; t++){
        SegTree ST;
        int N, range = 0;
        P p[75000];
        scanf("%d", &N);
        for(int i=0; i<N; i++){
            int x, y;
            scanf("%d %d", &x, &y);
            p[i] = P(x, y);
        }
 
        // 점들을 y좌표 순으로 정렬
        sort(p, p+N, [](P &a, P &b){
            return a.second < b.second;
        });
        // 서로 구분되는 y좌표 개수를 세며 y좌표 재설정
        int newY[750000];
        for(int i=0; i<N; i++){
            if(i>0 && p[i].second != p[i-1].second) range++;
            newY[i] = range;
        }
        for(int i=0; i<N; i++)
            p[i].second = newY[i];
        // 점들을 다시 x좌표 순으로, x좌표가 같다면 y좌표가 작아지는 순으로 정렬
        sort(p, p+N, [](P &a, P &b){
            if(a.first == b.first) return a.second > b.second;
            return a.first < b.first;
        });
 
        // 점들을 차례대로 훑으면서 이 점으로 항해할 수 있는 점 개수 세기
        long long result = 0;
        for(int i=0; i<N; i++){
            result += ST.sum(p[i].second, SIZE/2);
            ST.inc(p[i].second);
        }
        // 결과 출력
        printf("%lld\n", result);
    }
}
```

휴 이제 한 문제 끝났는데요... 보통이 아닌 일이네요.

스위핑 알고리즘은... 사실 그냥 훑기만 한다면 어디든지 갖다붙이기 정말 좋은 단어이고,

실상은 알고리즘 종류부터 구현해야 하는 방법이나 필요한 자료구조까지 모든 게 너무나도 천차만별인 굉장히 포괄적인 개념.

다만, 훑는다는 개념이 **도형들을 일정 기준에 맞춰 정렬해 놓고 순서대로 훑는 것**과 동치가 되는 경우가 절대 다수입니다. 이런 공통점이 있습니다.

## - BOJ[3392] : 화성 지도

https://www.acmicpc.net/problem/3392

이 문제도 정말 정말 유명한 스위핑 문제 중 하나입니다.

존재하는 직사각형들의 합집합 영역의 넓이를 구하라는 건데요.

이번에도 한번 y축과 평행한 수직선으로 평면을 왼쪽에서 오른쪽에서 훑어봅시다.



구현 난이도에 상관없이 그냥 생각하기는 제일 쉬운 스위핑 풀이는 이것입니다.

y좌표가 0 이상 30000 이하이므로 30000개의 크기 1인 구간으로 나눠서 생각하기로 합시다.



**① 각 직사각형마다 세로변 2개씩을 추출한다. 둘 중 하나는 시작하는 세로변(x좌표가 작음), 하나는 끝나는 세로변이다. 이들을 x좌표 순으로 정렬하고 순서대로 훑으면서 ②③을 수행한다.**

**② 바로 전에 훑은 세로변과의 x좌표 차를 dx라 하자. 현재 y좌표 구간들 중 1 이상의 값을 가진 칸의 개수를 센 뒤 dx와 곱해서 결과에 더한다.**

**③ 이번 세로변이 시작하는 세로변이면, 세로변 안에 속하는 y좌표 구간들에 1을 더한다. 끝나는 세로변이면, 구간들에 1을 뺀다.**



~~어떻게 제일 쉬운 게 이 따위냐~~ 대충 이해는 가시리라고 믿습니다.

**③**을 수행하기 위해서는 최소한 **레이지 프로퍼게이션**을 도입한 세그먼트 트리는 필요할 것 같은데, 정말 다행히도 그냥 세그먼트 트리만으로도 문제를 풀 수는 있습니다.

**②**의 경우 dx를 곱하는 것이 자연스러운 과정이고, 의도한 바는 아니지만 이 행위가 x좌표가 같은 세로변 중에서는 어떤 것을 먼저 방문하더라도 결과가 같게 해 줍니다. 똑같은 x좌표의 세로변들을 연속해서 훑는 중에는 dx가 0이 되어서 결과엔 변함이 없기 때문이죠.

![image-20200922165510700](https://user-images.githubusercontent.com/58545240/93857253-4a2ef580-fcf5-11ea-9a1c-16679099cf75.png)

이러한 문제가 있다고 하면

![image-20200922165523068](https://user-images.githubusercontent.com/58545240/93857272-4f8c4000-fcf5-11ea-99fd-f4930a8798db.png)


이렇게 구간을 쪼갤 수 있습니다. 자세한 과정은 생략합니다.

이 문제를 풀기 위한, 레이지 프로퍼게이션이 없는 세그먼트 트리는 사실... 조금 복잡합니다. 사람에 따라서 레이지 프로퍼게이션을 그냥 쓰는 게 더 쉬워 보일 수도. 그래도 검색해 보시면 나올 겁니다.

소개드린 문제가 다들 세그먼트 트리를 쓰고 있는데, 2차원 평면에서 행하는 스위핑 알고리즘은 세그먼트 트리와 아주 죽이 잘 맞습니다. 항상 도형을 만날 때마다 지금까지 방문한 것들 중 ~~의 개수, 합 같은 것을 체크하기 때문이죠.

물론, 세그먼트 트리가 아닌 다른 걸 쓰는 경우도 절대 없지는 않은데 밑의 추천문제에서 만나보세요(?).

## 스위핑 관련 추천 문제

**2170번: 선 긋기**

위에서 설명한 문제입니다.



**2836번: 수상 택시 (★)**

먼저, M이 문제에 등장하는 모든 집들 중 가장 큰 번호를 가진 경우부터 생각해 봅시다. 이때는 어떻게 문제를 풀어야 할까요?

일단 태울 수 있는 사람 수에 제한이 없으니까, 오른쪽으로 가는 사람들은 그냥 0->M번 집으로 가기만 하면 저절로 다 도착하게 됩니다.

문제는 왼쪽으로 가는 사람들인데, 어떻게 해야 가능한 한 효율적으로 움직일 수 있을까요?



답은 선 긋기 문제와 비슷합니다. 4->2, 3->1번 집으로 가는 사람이 있다고 해 봅시다. 이때는 0->4->2->3->1->M으로 한 명씩 조달(?)하면서 움직이는 것보다, 0->4->1->M으로 한번에 움직이는 게 훨씬 빠릅니다.

즉, 왼쪽으로 가는 사람들의 시작점과 도착점으로 구간들을 만들었을 때 두 구간이 합쳐질 수 있으면 합쳐진 구간만 한번에 돌아가는 게 제일 빠르단 거죠.

이렇게 해서 선 긋기 문제처럼 합칠 수 있는 한 최대한 구간들을 합쳐서, 그 구간들만큼 돌아갔다가 와야 하므로 **M + (구간 길이 합)\*2**가 답이 됩니다.



그럼 좀 더 어려운 경우인, M보다 큰 번호의 집이 문제에 등장할 때는?

문제에 등장하는 가장 큰 번호의 집을 K번이라고 해 봅시다. 이때 일단 반드시 K번 집은 가야 하므로, 0->K->M번 집으로 가는 경로로 움직이고 시작합시다. 이러면 오른쪽으로 가는 사람들은 반드시 도착하게 되고, 왼쪽으로 가는데 목적지가 M번 이상인 집인 사람들도 반드시 도착하게 됩니다.

이제 아직도 도착하지 못한 사람들, 즉 **왼쪽으로 가면서 목적지 집 번호가 M보다 작은 사람들**만 태우면 되는데, 역시 위와 비슷하게 문제를 풀 수 있습니다.



**10000번: 원 영역 (★)**

원을 왼쪽 시작 좌표 순으로, 이게 같다면 반지름이 큰 걸 먼저 오도록 정렬한 후 훑습니다.

훑으면서 원을 **스택**에 쌓아 나가는데, 그 전에 자신을 포함하지 않는 원이 스택 top에 있는 한 전부다 pop합니다.

기본적으로 원이 하나 있을 때마다 영역 개수는 반드시 1개 늘어나는데, 1개가 더 늘어날 경우는 어떤 원 안에 있는 원들이 자신의 안쪽을 양분할 경우입니다. 이걸 각 원마다 판별해서 원이 pop될 때마다 경우에 따라 결과를 추가로 1 증가시켜야 합니다. 구현이 어려운 문제.



**5419번: 북서풍**

위에서 설명한 문제입니다.



**3392번: 화성 지도 (★)**

위에서 설명한 문제입니다.



**10534번: City Park (★)**

인접해 있는 직사각형 뭉치들 중 그 넓이 합이 제일 큰 걸 구하라고 합니다. 놀랍게도 한 모서리에서만 접촉해도 인접한 걸로 치네요.

이 문제는 상당히 어려운데, 일단 인접한 두 직사각형을 하나로 합치며 넓이도 합치는 것은 **유니온 파인드**를 사용해서 구현할 수 있지만, 인접 여부는 어떻게 판별하죠?

인접 여부는 두 직사각형의 가로나 세로변끼리 만나는 경우로 생각할 수 있습니다. 가로변이 겹치는 경우는 두 변의 y좌표가 같고, 두 변의 시작~끝 x좌표로 구간을 나타냈을 때 두 구간이 인접하거나 겹치면 됩니다. 세로변도 비슷하고요.

문제가 어려운 이유는 어떻게 가로변 겹침 여부와 세로변 겹침 여부를 다 판단할지를 모르겠어서입니다.



일단 세로변끼리 겹치는 놈들끼리만이라도 합쳐봅시다. 먼저 직사각형마다 **세로변 2개**를 추출해서 이 세로변들을 **x좌표 순, x좌표가 같으면 세로변의 두 y좌표 중 작은 y좌표 순**으로 정렬합니다. 이때 세로변마다 자신이 몇 번째 직사각형에 속해있는지를 저장하고 있으면 편할 겁니다.

y축에 평행한 수직선을 2차원 평면에 훑으면서 스위핑해 봅시다. 이것은 세로변을 위에서 정렬한 순서대로 훑는 것과 동일합니다. 이때 x좌표 같은 세로변들끼리 구간이 겹치면 양쪽 직사각형을 합치는 식으로 구현이 얼추 가능합니다.



그럼 가로변끼리 겹치는 놈들은 어떻게 합치죠? 사실... 해답은 의외로 간단합니다. **스위핑을 2번 하는 겁니다. 세로변에 대해 한 번, 가로변에 대해 한 번.** 이렇게 스위핑을 2번 하면 합쳐질 놈들끼리는 다 합쳐지게 됩니다.

# **> Levenshtein 알고리즘**

---

> **편집 거리 알고리즘 (edit distance)**

Levenshtein distance 는 string 간 형태적 유사도를 정의하는 척도입니다. 만약 우리가 단어 사전을 가지고 있고, 사전에 등록되지 않은 단어가 발생한다면 Levenshtein distance 가 가장 가까운 단어로 치환함으로써 오탈자를 교정할 수 있습니다. 그러나 Levenshtein distance 는 계산 비용이 비쌉니다. 이 때 간단한 inverted index 를 이용하여 비슷할 가능성이 있는 단어 후보만을 추린 뒤 몇 번의 Levenshtein distance 를 계산함으로써 효율적으로 오탈자를 교정할 수 있습니다.

String 간의 형태적 유사도를 정의하는 척도를 string distance 라 합니다. Edit distance 라는 별명을 지닌 Levenshtein distance 는 대표적인 string distance 입니다.

Levenshtein distance 는 한 string `s1s1` 을 `s2s2` 로 변환하는 최소 횟수를 두 string 간의 거리로 정의합니다. `s1s1 = ‘꿈을꾸는아이’` 에서 `s2s2 = ‘아이오아이’` 로 바뀌기 위해서는 `(꿈을꾸 -> 아이오)` 로 바뀌고, 네번째 글자 ‘는’ 이 제거되면 됩니다. Levenshtein distance 에서는 이처럼 string 을 변화하기 위한 edit 방법을 세 가지로 분류합니다

1. `delete`: ‘점심**을**먹자 →→ 점심먹자’ 로 바꾸기 위해서는 **을** 을 삭제해야 합니다.
2. `insert`: ‘점심먹자 →→ 점심**을**먹자’ 로 바꾸기 위해서는 반대로 **을** 을 삽입해야 합니다.
3. `substitution`: ‘점심먹**자** →→ 점심먹**장**’ 로 바꾸기 위해서는 **자**를 **장** 으로 치환해야 합니다.

이를 위해 동적 프로그래밍 (dynamic programming) 이 이용됩니다. d[0,0] 은 s1,s2s1,s2 의 첫 글자가 같으면 0, 아니면 1로 초기화 합니다. 글자가 다르면 **substitution cost** 가 발생한다는 의미입니다. 그리고 그 외의 d[0,j]에 대해서는 d[0,j] = d[0,j-1] + 1 의 비용으로 초기화 합니다. 한글자씩 insertion 이 일어났다는 의미입니다. 이후에는 좌측, 상단, 좌상단의 값을 이용하여 거리 행렬 d 를 업데이트 합니다. 그 규칙은 아래와 같습니다.

![image-20210330101437113](https://user-images.githubusercontent.com/58545240/112936241-6312da80-9160-11eb-99c8-11c0980cf63a.png)

```java
d[i,j] = Math.min(d[i-1,j] + deletion cost, Math.min(d[i,j-1] + insertion cost, d[i-1,j-1] + substitution cost)
```

예를 들어 "`FLOMAX`"와 "`VOLMAX`" 사이의 Levenshtein 거리는 **3**입니다. 세 가지 편집이 하나씩 이루어지고 그 미만으로 편집할 수 있는 방법이 없기 때문입니다.

"`GILY`"와 "`GELLY`"사이의 Levenshtein 거리는 **2**입니다.

![image-20210330101839455](https://user-images.githubusercontent.com/58545240/112936248-6908bb80-9160-11eb-972b-c68f4bdb774e.png)

"`HONDA`"와 "`HYUNDAI`" 사이의 Levenshtein 거리는 **3**입니다.

![image-20210330101933813](https://user-images.githubusercontent.com/58545240/112936259-6dcd6f80-9160-11eb-9933-3dcb09829198.png)

## Dynamic Programming으로 접근

이 **Levenshtein** 알고리즘은 다른 문자열을 얻기 위해 한 문자열을 수정하는 데 필요한 최소 편집 작업 수를 계산합니다. 이를 계산하는 가장 일반적인 방법은 동적 프로그래밍 방식입니다.

1. 행렬은 (m, n) 셀에서 하나의 m- 문자 접두사와 다른 단어의 n- 접두사 사이의 Levenshtein 거리를 측정하여 초기화됩니다.
2. 매트릭스는 왼쪽 상단에서 오른쪽 하단 모서리까지 채울 수 있습니다.
3. 각각의 수평 또는 수직 점프는 각각 삽입 또는 삭제에 해당합니다.
4. 비용은 일반적으로 각 작업에 대해 1로 설정됩니다.
5. 행과 열의 두 문자가 일치하면 0과 일치하지 않으면 대각선 점프는 하나의 비용이 발생할 수 있습니다. 각 셀은 항상 로컬 비용을 최소화합니다.
6. 이렇게하면 오른쪽 아래 모서리에있는 숫자가 두 단어 사이의 Levenshtein 거리입니다.

"`HONDA`"와 "`HYUNDAI`"를 비교 해보겠습니다.

![image-20210330102142723](https://user-images.githubusercontent.com/58545240/112936274-732aba00-9160-11eb-9ebf-2600ef6783d1.png)

이제 Levenshtein 알고리즘을 자바코드로 구현해봅시다.

```java
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, m = 0;
    static long dist[][];
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        dist = new long[n + 1][m + 1];

        String input = br.readLine();
        String answer = br.readLine();

        long cnt = levenshtein(input, answer);
        System.out.println(cnt);
    }

    static long levenshtein(String origin, String pattern) {
        for (int i = 1; i <= origin.length(); ++i) {
            dist[i][0] = i;
        }
        for (int j = 1; j <= pattern.length(); ++j) {
            dist[0][j] = j;
        }

        for (int j = 1; j <= pattern.length(); ++j) {
            for (int i = 1; i <= origin.length(); ++i) {
                if (origin.charAt(i - 1) == pattern.charAt(j - 1)) {
                    dist[i][j] = dist[i - 1][j - 1];
                } else {
                    dist[i][j] = Math.min(dist[i - 1][j - 1] + 1, Math.min(dist[i][j - 1] + 1, dist[i - 1][j] + 1));
                }
            }
        }
        return dist[origin.length()][pattern.length()];
    }
}
```

## 사용 사례

대부분의 문자열 매칭에서 우리의 목표는 적은 수의 차이가 예상되는 상황에서 긴 텍스트에서 짧은 문자열에 대한 일치여부를 찾는 것입니다. 예를 들어 문자열 중 하나는 일반적으로 짧고 다른 하나는 임의로 깁니다. 여기에는 맞춤법 검사기, 광학 문자 인식을위한 수정 시스템 및 번역 메모리를 기반으로 한 자연어 번역을 지원하는 소프트웨어와 같은 광범위한 응용 프로그램이 있습니다.

Levenshtein 거리는 두 개의 긴 문자열 사이에서도 계산할 수 있습니다. 그러나 이를 계산하는 데 드는 비용은 두 문자열 길이의 곱에 거의 비례하므로 비효율적이며 비현실적입니다. 따라서 Levenshtein이 사용되는 경우 비교 속도를 높이기 위해 일반적으로 비교 하는 문자열이 짧다는 것이 특징입니다.

하지만 Inverted index 를 이용하면 최소한 비슷할 수 있는 단어들만을 후보로 추린 뒤, 소수의 후보에 대해서만 계산 비용이 비싼 Levenshtein distance 를 계산함으로써, 효율적인 오탈자 교정을 할 수 있습니다.

### - BOJ[20542] : 받아쓰기

https://www.acmicpc.net/problem/20542



**참조**

https://lovit.github.io/nlp/2018/09/04/levenshtein_inverted_index/

https://www.cuelogic.com/blog/the-levenshtein-algorithm

# **> LCS**

---

> **Longest Common Subsequence** 
>
> **최장 공통 부분 수열**

여기서 **substring**은 연속된 부분 문자열이고, **subsequence**는 연속적이지 않은 부분 수열이다.

예로, "`iamstudent`" 라는 문자열에서 연속된 부분 문자열 `mstu`은 **substring**이 되고 연속적으로 이어지지는 않지만 순서는 맞는 `mtue`는 **subsequence**가 된다.

그렇다면 공통 부분 수열이란, 두 문자열이 공통으로 가지고 있는 부분 수열이다.

에를 들어 "`CDABE`" 와 "`CDEGT`"가 있다면 공통부분 수열은 

`{}, {C}, {D}, {E}, {C, D}, {D, E}, {C, E}, {C, D, E}` 일 것이다.

**최장 공통 부분 수열**이란 공통 부분 수열 중에서 길이가 가장 긴 부분 수열을 뜻한다. 대표적으로 쓰이는 곳은 `염기서열 유사성 분석` 및 `음파 단어 검색 및 교정` 등에 쓰인다.

## 1. LCS의 길이 구하기

위 예제에서 **LCS**는 `{C, D ,E}`이고, 길이가 `3`이다.

보통 DP(Dynamic Programming)으로 특정 범위까지의 값을 구하고 다른 범위까지의 값을 구할때 이전에 구해 둔 값을 이용하여 효율적으로 문제를 해결한다.

CS는 2개의 String을 비교하여 최장 공통 부분 문자열을 구해야한다. substring을 구하는 것과 다르게 연속적이지 않아도 되기 때문에 같은 길이의 다른 해가 나타날 수 있다.

예시를 가지고 생각해 보자. 문자열 'ACAYKP'와 'CAPCAK'가 있다고 가정해보자.

우선 하나의 String을 기준 String, 다른 String을 비교 String으로 둔다. 

![image-20210329161905909](https://user-images.githubusercontent.com/58545240/112806607-efb28f80-90b1-11eb-99b0-4cbeb1cff45c.png)

위 표를 보면 문자열 맨 앞에 0을 추가해 첫번째 행과 열이 모두 0이다. 두번째 열인 C열의 값을 집어 넣어 보자. 각 위치의 값은 LSC의 길이의 값이 들어간다.

![image-20210329161920671](https://user-images.githubusercontent.com/58545240/112806619-f50fda00-90b1-11eb-8d4b-68e0be7af811.png)

값을 다 집어 넣으면 위 표와 같이 된다. 표를 읽는 법을 다시 설명하면 [C,C]의 1은 'C'와 'AC' 사이의 LCS의 길이이다. 마찬가지로 [C,Y]의 1은 'C'와 'ACAY'의 LCS의 길이를 나타낸 것이다.

몇 번더 반복해 보자

![image-20210329161933800](https://user-images.githubusercontent.com/58545240/112806650-fb9e5180-90b1-11eb-9e0c-995449f1d613.png)

위 두표를 보면 알 수 있듯이 가로줄, **즉 행은 이전 행의 값을 기반으로 해서 계산된다.** 위의 두 표 중 1번째 표는 'CA'와 '`ACAYKP`'의 subsequence를 구한것이고, 2번째 표는 '`CAP`'와 '`ACAYKP`'의 subsequence를 구한 것이다.

 우리는 여기에서 표의 값을 구할 때 **같은 문자가 나오면** 이전까지의 LCS의 길이에 +1을 하는 것을 알 수 있다. 여기에서 이전까지의 LCS의 길이란 왼쪽값이 아니라 대각선(왼쪽 위)값을 말한다. 이는 두 문자열에서 해당 두 문자를 비교하기 전의 LCS의 길이에 +1을 하기 위해서 이다.

 또한 **비교한 문자가 다르다면**, 비교한 문자가 포함되어 있는 직전 LCS, 즉 표에서는 위와 왼쪽 값중 큰 값이 오게된다. 예를 들면 위의 2번째 표에서 [P,Y]의 값은 '`CAP`'와 '`ACAY`'를 비교한 값이 오게 되고, 'P'와 'Y'는 다르기 때문에 'CA'와 '`ACAY`'의 LCS의 길이와 '`CAP`'와 '`ACA`'의 LCS의 길이중 큰 값이 오게된다. 

**즉 표의 값을 구하는 규칙은 다음과 같다.**

1. String1[n], String2[k]가 같다면 : `[n, k] == [n-1, k-1] + 1`
2. String1[n], String2[k]가 다르면 : `[n, k] == [n-1, k]와 [n, k-1] 중 큰 값`

```java
// Top-Dwon (Recur)
public class Main {
 
	static char[] str1;
	static char[] str2;
 
	static Integer[][] dp;
	
	public static void main(String[] args) throws IOException {
    
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		str1 = br.readLine().toCharArray();
		str2 = br.readLine().toCharArray();
 
		dp = new Integer[str1.length][str2.length];
		
		System.out.println(LCS(str1.length - 1, str2.length - 1));
	}
	
	static int LCS(int x, int y) {
		// 인덱스 밖 (공집합)일 경우 0 반환
		if(x == -1 || y == -1) {
			return 0;
		}
 
		// 만약 탐색하지 않은 인덱스라면?
		if(dp[x][y] == null) {
			dp[x][y] = 0;
 
			// str1의 x번째 문자와 str2의 y번째 문자가 같은지 검사
			if(str1[x] == str2[y]) {
				dp[x][y] = LCS(x - 1, y - 1) + 1;
			}
			// 같지 않다면 LCS(dp)[x-1][y]와 LCS(dp)[x,y-1] 중 큰 값으로 초기화
			else {
				dp[x][y] = Math.max(LCS(x - 1, y), LCS(x, y - 1));
			}
		}
		return dp[x][y];
	}
}
```

```java
// Bottom-Up 효율면에서 조금 더 좋다.
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
 
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] str1 = br.readLine().toCharArray();
		char[] str2 = br.readLine().toCharArray();
		
		int length_1 = str1.length;
		int length_2 = str2.length;
		
		// 공집합 표현을 위해 인덱스가 한 줄씩 추가 됨 
		int[][] dp = new int[length_1 + 1][length_2 + 1];
		
		// 1부터 시작 (index 0 은 공집합이므로 0의 값을 갖고있음) 
		for(int i = 1; i <= length_1; i++) {
			for(int j = 1; j <= length_2; j++) {
				
				// (i-1)과 (j-1) 번째 문자가 서로 같다면  
				if(str1[i - 1] == str2[j - 1]) {
					// 대각선 위 (i-1, j-1)의 dp에 +1 한 값으로 갱신 
					dp[i][j] = dp[i - 1][j - 1] + 1;	
				}
				// 같지 않다면 이전 열(i-1)과 이전 행(j-1)의 값 중 큰 것으로 갱신  
				else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		System.out.println(dp[length_1][length_2]);
	}
}
```



## 2. LCS 구하기

문자열 '`ACAYKP`'와 '`CAPCAK`'를 이용해서 작성한 Table은 다음과 같다.

![image-20210329162213414](https://user-images.githubusercontent.com/58545240/112806677-02c55f80-90b2-11eb-970d-2a53f1815bc4.png)

우선 가장 큰 숫자가 시작 된 곳을 체크한다. 이후 이전 숫자를 찾아 가며 체크를 하되, 각각의 행과 열에는 하나의 체크만이 있어야 한다.또한 앞서 말했듯이 substring을 구하는 것과 다르게 연속적이지 않아도 되기 때문에 같은 길이의 다른 해가 나타날 수 있다.

![image-20210329162229155](https://user-images.githubusercontent.com/58545240/112806693-0822aa00-90b2-11eb-8884-3fc3c5d35dcd.png)

문자열 '`ACAYKP`'와 '`CAPCAK`'의 LCS는 '`ACAK`'이다.

**`Ai = Bj`**일 때 `LCS(i, j) = LCS(i - 1, j - 1) + 1` 이었으니 이를 고려해 역추적해 나가면된다.

다만 또 고려할 것이 있는데 `LCS(i, j) = Math.max(LCS(i - 1), j), LCS(i, j - 1))`의 경우이다.

**`Ai != Bj`**일 지라도 `LCS(i, j) = Math.max(LCS(i - 1), j), LCS(i, j - 1))`을 통해 `LCS(i - 1, j - 1) + 1`가 도출될 수 있다.

따라서 `LCS(i - 1, j), LCS(i, j - 1)` 둘다 `LCS(i, j)`보다 작으면서, `LCS(i - 1, j - 1)`이 `LCS(i, j)`보다 작은 경우가 `Ai == Bj`일 때이다.

즉, `LCS[i][j] > LCS[i - 1][j - 1] && LCS[i][j] > LCS[i][j - 1] && LCS[i][j] > LCS[i - 1][j]`를 조건으로 코드를 구현해야한다.!!!

```java
// Bottom - Up
public class Main {
    static char[] str1, str2;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str1 = br.readLine().toCharArray();
        str2 = br.readLine().toCharArray();
        int len1 = str1.length;
        int len2 = str2.length;
        dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (str1[i - 1] == str2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        System.out.println(dp[len1][len2]);
        Stack<Character> stack = new Stack<>();
        while (len1 >= 1 && len2 >= 1) {
            if (dp[len1][len2] == dp[len1 - 1][len2]) {
                len2--;
            } else if (dp[len1][len2] == dp[len1][len2 - 1]) {
                len1--;
            } else {
                stack.push(str1[len1 - 1]);
                len1--;
                len2--;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        System.out.println(sb.toString());
    }
}
```

```java
// Top-Down
String output = "";
void backTracking(int m, int n) {
    if (m == 0 || n == 0) return;
    if (dp[m][n] > dp[m - 1][n - 1] && dp[m][n] > dp[m][n - 1] && dp[m][n] > dp[m -1][n]) {
        // 문자열 인덱스는 dp 인덱스보다 1씩 더 작다.
        output = input[n - 1] + output;
    } else if (dp[m][n] > dp[m - 1][n] && dp[m][n] == dp[m][n - 1]) {
        backTracking(m, n - 1);
    } else {
        backTracking(m - 1, n);
    }
}
```

# **> LIS**

---

> **Longest Increasing Subsequence)**
>
> 최장 증가 부분 수열

임의의 수열이 주어졌을 때, 이 수열에서 몇 개의 수 들을 선택해 부분수열을 만들 수 있는데, 이 때 만들어진 부분수열 중 오름차순으로 정렬 된 가장 긴 수열을 **최장 증가 수열**이라고 한다.

여기서의 부분 수열은 반드시 연속적이거나 유일하지 않아도 된다.

예를 들어 `arr[ ] = {10, 20, 10, 30, 20, 50}`의 수열이 있다고 할 때 여기서의 LIS는 `{10, 20, 30, 50}`이고 길이는 **4**일 것이다. 

이제 이를 O(N<sup>2</sup>)과 O(nlogn)의 시간복잡도를 가지는 두 가지 방법을 알아보자.

## 1. DP

풀이 과정은 아래와 같다.

1. i번째 인덱스에서 끝나는 최장 증가 부분 수열의 마지막에 arr[k]를 추가했을 때의 LIS 길이와
2. 추가하지 않고 기존의 length[k] 값
3. **둘 중에 더 큰 값으로 length[k] 값을 업데이트**합니다.

이 방법은 가장 간편한 방법으로 O(N<sup>2</sup>)의 시간 복잡도를 가진다.

```java
for (int k = 0; k < n; k++){
	length[k] = 1;
    for (int i = 0; i < k; i++){
        if(arr[i] < arr[k]){
            length[k] = Math.max(length[k], length[i] + 1);
        }        
    }
}
```

### - BOJ[2565] : 전깃줄

https://www.acmicpc.net/problem/2565

```java
import java.io.*;
import java.util.*;
public class Main {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        int[][] wire = new int[n + 1][2];
        Integer[] dp = new Integer[n + 1];
        
        for (int i = 1; i < wire.length; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            
            wire[i][0] = u;
            wire[i][1] = v;
        }
        
        Arrays.sort(wire, new Comparator<int[]>() {
           public int compare(int[] o1, int[] o2) {
               return o1[0] - o2[0];
           }
        });
        
        for (int i = 1; i < dp.length; i++) {
            dp[i] = 1;
            /*
		   * i번째 전봇대를 기준으로 이전의 전봇대들의 전선을 연결하기 위한 탐색
		   * 즉, i번째 전봇대에 연결된 B전봇대는 탐색할 j번째 전봇대에 연결된 B전봇대보다 값이 커야함 
		   */
            for (int j = 1; j < i; j++) {
                if (wire[i][1] > wire[j][1]) {
	                dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        
        int max = 0;
        /*
		*  i번째 A전봇를 기준으로 연결가능한 개수 탐색
		*  및 최댓값 찾기
		*/
        for (int i = 1; i <= n; i++) {
            max = Math.max(dp[i], max);
        }
        // 최소 철거 개수 = 전체 개수 - 설치 가능한 전깃줄
        System.out.println(n - max);
    }
}
```

## 2. Binary Search

DP에서의 시간복잡도를 개선하기 위해 **이분탐색**을 활용한다.

즉, LIS 형태를 유지하기 위해 주어진 배열의 인덱스를 하나씩 살펴보며 그 **숫자가 들어갈 위치를 이분탐색으로 탐색하여 삽입**한다.

이분탐색의 시간복잡도는 O(logn) 이므로, 이 문제의 시간복잡도는 O(nlogn)이 된다.

기본적인 풀이 과정은 아래와 같다.

1. 탐색을 진행하며 수열의 최댓값 큰 수는 계속 뒤로 이어붙이고
2. 중간에 낄 수 있는 수는 이분탐색을 이용해 적절한 자리를 찾아 교체시키는 방식으로 수열을 만든다.
3. 수열에 포함된 숫자들보다 작은 숫자가 나오면 가장 처음 숫자를 교체한다.

`arr[ ] = {10, 20, 10, 30, 20, 50}` 를 예시로 들어 로직을 자세하게 보자.

- 첫 숫자 10은 가장 짧은 길이의 증가 부분 수열이다. 배열에 바로 포함시킨다. `{ 10 }`
- 다음 숫자 20은 10보다 크기 때문에 부분 수열 중 가장 큰 수인 10보다 크다. 바로 뒤에 이어 붙인다. `{ 10, 20 }`
- 다음 숫자 10은 제일 작은 수인 첫 번째 숫자 10보다 작은 것도 아니고 제일 큰 수인 20보다 큰 것도 아니므로 이분탐색으로 10의 위치를 찾아준다. 제일 첫번째 숫자인 10과 같으므로 이분탐색을 이용해서 교체 가능한 자리를 찾을 경우 첫번째 10의 자리가 리턴될 것이다. 10과 10을 교체한다. 부분 수열의 길이는 유지된다. `{ 10, 20 }`
- 다음 숫자 30은 부분 수열에서 가장 큰 수인 20보다 크다. 끝에 바로 붙여준다. `{ 10, 20, 30 }`
- 다음 숫자 20은 부분 수열에서 가장 작은 수인 10과 가장 큰 수인 30 사이에 있는 수이다. 앞에서와 같이 이분 탐색을 이용하여 자리를 찾고 자리를 교체한다. 부분 수열의 길이는 유지된다. `{ 10, 20, 30 }`
- 마지막 숫자 50은 부분 수열의 가장 큰 수인 30보다 크다. 끝에 바로 붙여준다. `{ 10, 20, 30, 50 }`

**이 문제에서는 우연히 정확한 부분 수열이 탄생했지만, 중간에 2자리의 10이 5로만 바껴도 부분수열이 달라졌을 것이다.**

**따라서, 이 방법만으로는 길이를 구하는 방법이지 부분수열을 구하는 방법이 아닌 것을 주의하자.**

**최종적으로 최장 부분 수열을 구하기 위해선 어떤 항목이 참조 되는지 Reference 배열을 정의하여 요소들을 Tracing하는 테크닉을 사용하여야 한다.**

```c
/* Find LIS NLogN */
int arr[] = new int[100001]; // 원본 수열
int lis[] = new int[100001]; // LIS 수열
int lisCnt = 0; // LIS의 길이
int trace[] = new int[100001]; // TRACE를 위한 수열
int findLIS(int n) {
    for (int i = 0; i < n; i++) {
        if (i == 0 || arr[i] > lis[lisCnt - 1]) {
            trace[arr[i]] = lisCnt;
            lis[lisCnt++] = arr[i];
        } else {
            int start = 0, end = lisCnt;
            int idx = lisCnt;
            while(start < end) {
                int mid = (start + end) / 2;
                if(lis[mid] >= arr[i]) {
                    idx = Math.min(idx, mid);
                    end = mid;
                } else {
                    start = mid + 1;
                }
            }
            lis[idx] = arr[i];
            trace[arr[i]] = idx;
        }
    }
    
    // trace 배열에서 가장 나중을 꺼내면 됨.
    int cur = lisCnt-1;
    for(int i=n-1; i>=0; i--) {
        if(trace[arr[i]] == cur) {
            lis[cur] = arr[i];
            cur--;
        }
    }
    return lisCnt;
}
```



### - BOJ[11053] : 가장 긴 증가하는 부분 수열

https://www.acmicpc.net/problem/11053

```java
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int[] A = new int[N];
		for (int i = 0; i < N; i++) {
			A[i] = sc.nextInt();
		}

		int[] lis = new int[N];
		lis[0] = A[0];
		int idx = 1;
		int tmp = 0;
		for (int i = 1; i < N; i++) {
			if (lis[idx - 1] < A[i])
				lis[idx++] = A[i];
			else if (lis[0] > A[i])
				lis[0] = A[i];
			else {
				tmp = Arrays.binarySearch(lis, 0, idx, A[i]);
				lis[tmp < 0 ? -tmp - 1 : tmp] = A[i];
			}
		}
		System.out.println(idx);
	}
}
```

### - BOJ[2352] : 반도체 설계

https://www.acmicpc.net/problem/2352

```java
import java.io.*;
import java.util.*;
public class Main {
    static int n, arr[], lis[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        lis = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int lis_cnt = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0 || arr[i] > lis[lis_cnt - 1]) {
                lis[lis_cnt++] = arr[i];
            } else {
                int idx = Arrays.binarySearch(lis, 0, lis_cnt, arr[i]);
                idx = (idx < 0) ? -idx -1 : idx;
                lis[idx] = arr[i];
            }
        }

        System.out.println(lis_cnt);
    }
}
```

## 3. Segment Tree

요소는 원래 인덱스를 유지하면서 오름차순으로 먼저 정렬됩니다. 엄격하게 증가하는 LIS의 경우 동일한 요소의 경우 인덱스가 높은 요소가 낮은 요소보다 초기 자리를 얻습니다. 이것은 쌍의 배열에 저장할 수 있습니다. 

이제 세그먼트 트리에 채워집니다. 정렬 된 배열에서의 위치에 따라 원래 인덱스에 해당하는 잎의 세그먼트 트리에 채워집니다.

처음에는 세그먼트 트리가 0으로 초기화되었습니다. 이제 정렬 된 배열에서 i 번째 요소를 처리했다고 가정 해 보겠습니다. (i + 1) 번째 반복에서 값의 원래 위치를 j로 둡니다.

그런 다음 값이 0에서 (j-1) +1 사이의 잎의 최대 값이 될 세그먼트 트리의 j 번째 잎을 채 웁니다. 
(앞의 하위 배열에서 그보다 작은 요소에 의해 형성된 LIS의 길이 및 포함에 대해 +1) 

```java
// Finding the Longest Increasing Subsequence
// using Segment Tree
import java.io.*;
import java.util.*;

class Pair {
	int first;
	int second;
}

class GFG {
// Building the entire Segment tree, the root of which
// contains the length of the LIS
    static void buildTree(int[] tree, int pos, int low,
                        int high, int index, int value) {

        // Index is the original index of current element
        // If the index is not present in the given range,
        // then simply return
        if (index < low || index > high)
            return;

        // If low == high then the current position
        // should be updated to the value
        if (low == high) {
            tree[pos] = value;
            return;
        }

        int mid = (high + low) / 2;

        // Recursively call the function on the
        // child nodes
        buildTree(tree, 2 * pos + 1, low, mid,
                index, value);
        buildTree(tree, 2 * pos + 2, mid + 1, high,
                index, value);

        // Assign the current position the max of
        // the 2 child nodes
        tree[pos] = Math.max(tree[2 * pos + 1],
                            tree[2 * pos + 2]);
    }

    // Function to query the Segment tree and
    // return the value for a given range
    static int findMax (int[] tree, int pos, int low, int high, int start, int end) {
        // Query: Same as the query function of Segment
        // tree. If the current range is totally inside
        // the query range, return the value of current
        // position
        if (low >= start && high <= end)
            return tree[pos];

        // If it is out of bound, return the minimum
        // which would be 0 in this case
        if (start > high || end < low)
            return 0;

        // Partial overlap
        int mid = (high + low) / 2;

        // Call findMax on child nodes recursively
        // and return the maximum of the two
        return Math.max(findMax(tree, 2 * pos + 1, low, mid, start, end),
                        findMax(tree, 2 * pos + 2, mid + 1, high, start, end));
    }

    static int findLIS(int arr[], int n) {
        // The array of pairs stores the integers
        // and indices in p[i]
        List<Pair> p = new ArrayList<Pair>();

        for(int i = 0; i < n; i++)
        {
            Pair p1 = new Pair();
            p1.first = arr[i];
            p1.second = i;
            p.add(p1);
        }

        // Sorting the array in increasing order
        // of the elements
        Collections.sort(p, (p1, p2) ->
        {

            /* For same values, element with the higher
            index appear earlier in the sorted array.
            This is for strictly increasing subsequence.
            For increasing subsequence, the lower index
                appears earlier in the sorted array. */
            if (p1.first == p2.first)
                return p2.second - p1.second;

            // Sorting the array according to their values.
            return p1.first - p2.first;
        });

        // Calculating the length of the segment-tree
        int len = (int)(Math.pow(2, (int)(Math.ceil(Math.sqrt(n))) + 1)) - 1;
        int[] tree = new int[len];

        // Building the segment-tree, the root node of
        // which contains the length of LIS for the n
        // elements
        for(int i = 0; i < n; i++) {
            buildTree(tree, 0, 0, n - 1, p.get(i).second, findMax(tree, 0, 0, n - 1, 0, p.get(i).second) + 1);
        }
        return tree[0];
    }

    // Driver Code
    public static void main(String[] args) {
        int arr[] = { 10, 22, 9, 33, 21, 50, 41, 60 };
        int n = arr.length;

        System.out.println("Length of the LIS: " + findLIS(arr, n));
    }
}
```

### - BOJ[12015] : 가장 긴 증가하는 부분 수열 2

https://www.acmicpc.net/problem/12015

```java
// 직접 풀어 봐요!
```



**참조**

https://code0xff.tistory.com/70

https://jins-dev.tistory.com/entry/%EC%B5%9C%EC%A0%81%ED%99%94%EB%90%9C-LISLongest-Increasing-Subsequence-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98%EA%B3%BC-%ED%95%B4-%EC%B0%BE%EA%B8%B0

https://www.geeksforgeeks.org/lis-using-segment-tree/

# **> Prefix Sum**

---

> 누적 합, 구간 합

누적합 또는 구간 합을 빠르게 구하는 알고리즘입니다. 여기서 주의해야할 것은

**부분 합** 은 **0 ~ k까지의 합**을 의미하는 것이고

**구간 합**은 **a ~ b**까지의 합을 의미 합니다.



이러한 알고리즘은 어디에 쓰일까요?

아래와 같은 문제를 예시로 봅시다.

```bash
int arr[10] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9} 배열이 존재한다.
이 때 a에서 b의 구간 합을 요구하는 쿼리 "2천만개"가 들어온다.
이러한 문제에 대해 어떻게 해결 할 것인가?
```



가장 쉬운 방식은 브루트 포스를 이용해 일일이 더하는 것입니다.하지만 쿼리 **2천만개**를 1초만에 해결해달라고 요구 했을 때, 이 코드의 시간복잡도를 보면

쿼리 2천만개 * (a에서 b의 합 구하는 for 문의 최악의 경우는 a = 0,  b= 9일 때 즉, 10)

따라서 `O(20,000,000 * 10) => O(200,000,000)` 이므로 대략 **2초**입니다.

결국 시간복잡도에 걸려 요구사항을 해결하지 못하게 됩니다. 이 때문에 우리는 **`Prefix Sum`**이라는 알고리즘을 공부하게 됩니다.

```java
public class Main{
    public Solution() {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, ,8, 9};
        int[] psum = new int[arr.length];
        
        for (int i = 1; i < 10; i++) {
            psum[i] = psum[i - 1] + arr[i];
        }
        
        // 쿼리 a에서 b까지의 합 구하기
        int result = prefixSum(a, b);
        System.out.println(result);
    }
    
    int prefixSum(int l, int r, int[] psum) {
        return psum[r] - psum[l - 1];
    }
}
```



이렇게 psum이라는 누적 합 배열을 만들어 합을 미리 계산하여 저장합니다.

이렇게 된다면 쿼리는 `psum[b] - psum[a - 1]`을 하게 되면 원하는 구간에서의 합을 구할 수 있습니다.!!



이 알고리즘만으로는 문제에 잘 등장하지 않고 보통 다른 알고리즘들과 함께 응용되어 나오는 경우가 많으므로 다양한 예제를 보겠습니다.

### - BOJ[11660] : 구간 합 구하기 5

https://www.acmicpc.net/problem/11660

```java
/*
	DP와 구간합을 활용하여야 한다.
	사각형의 테두리 안을 구하려면
	좌상 꼭짓점:(x1, y1) , 우하 꼭짓점:(x2, y2) 일때 구간 합은 다음과 같다
	DP[i][j] =  DP[x2][y2] - DP[x1-1][y2] - DP[x2][y1-1] + DP[x1-1][y1-1];
*/
import java.io.*;
import java.util.*;
public class p11660 {
    static int n, m, board[][], sum[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n + 1][n + 1];
        sum = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                sum[i][j] = sum[i][j - 1] + sum[i - 1][j] - sum[i - 1][j - 1] + board[i][j];
            }
        }
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int result = prefixSum(x1, y1, x2, y2);
            System.out.println(result);
        }
    }
    static int prefixSum(int x1, int y1, int x2, int y2) {
        return sum[x2][y2] - sum[x2][y1 - 1] - sum[x1 - 1][y2] + sum[x1 - 1][y1 - 1];
    }
}
```

### - BOJ[21318] : 피아노 체조

https://www.acmicpc.net/problem/21318

```JAVA
import java.io.*;
import java.util.*;
public class p21318 {
    static int n, q;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];
        int[] dp = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1];
            if (arr[i] < arr[i - 1]) {
                dp[i]++;
            }
            //System.out.println("i:"+i+", dp[i]:"+dp[i]);
        }
        
        StringBuilder sb = new StringBuilder();
        q = Integer.parseInt(br.readLine());
        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int result = prefixSum(x, y, dp);
            sb.append(result).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    static int prefixSum(int l, int r, int[] dp) {
        return dp[r] - dp[l];
    }

}
```

### - BOJ[2015] : 수들의 합 4

https://www.acmicpc.net/problem/2015

```java
/*
	수가 자연수일 때는 투 포인터로 구할 수 있다.
	하지만 이 문제는 음수와 0이 있기 때문에 구간 Map을 이용한 구간 합을 사용해야 한다.
*/
import java.io.*;
import java.util.*;
public class p2015 {
    static int n, k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        int[] arr = new int[n + 1];
        int[] psum = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            psum[i] = psum[i - 1] + arr[i];
        }
        // i		:	0	1	2	3
        // psum[i]	:	2	0	2	0
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        long ret = 0;
        for(int i = 1; i <= n; i++) {
            ret += map.getOrDefault(psum[i] - k, 0);
            
            // key에 해당하는 value가 없으면 1, 있으면 value + 1
            map.put(psum[i], map.getOrDefault(psum[i], 0) + 1);
        }

        System.out.println(ret);
    }

}
```

### - BOJ[10986] : 나머지 합

https://www.acmicpc.net/problem/10986

```java
/*
    구간 합
<<접근방식>>
1. A % M + B % M = (A + B) % M 이므로 입력 받은 배열을 Prefix Sum 기법을 통해 누적합시킨 배열로 바꾸고 모두 M으로 나눈 나머지로 치환시킨다.
2. 그럼 이제 sum 배열이 오른쪽 항이 된 것인데, Prefix Sum 배열에서 S[i] - S[i-2]는 i-2부터 i까지의 부분합이 된다. 이를 이용하여 sum[i] = sum[j] 인 부분을 찾으면 된다.
3. 'i부터 j까지의 합을 M으로 나눈 나머지가 0이다'라는 것이 증명 되었으니 개수를 계산하면 되는데, sum[i] = sum[j]인 부분에서 2개만 고르면 0이 되는 부분이므로 nC2가 된다.

문제의 예시를 통해 보자.
1 2 3 1 2를 Prefix Sum 배열로 바꾸고 M으로 나눈 나머지로 치환시키면 1 0 0 1 0이 된다.

이 배열의 의미가 무엇인지 파악해보자. 처음 1번째 인덱스의 0의 뜻은 처음부터 1번째까지 더하면 나누어 떨어진다는 뜻이다.
2번째 인덱스의 0의 뜻은 처음부터 2번째까지 더하면 나누어 떨어진다는 뜻이다.
3번째 인덱스의 1의 뜻은 자기와 같은 수의 0번째 인덱스의 다음 수부터 3번째까지 더하면 나누어 떨어진다는 뜻이다.
4번째 인덱스의 0의 뜻은 처음부터 4번째까지 더하면 나누어 떨어진다는 뜻과, 자기와 같은 수의 1번째 또는 2번째 인덱스의 다음 수부터
 4번째까지 더하면 나누어 떨어진다는 뜻이다.

즉 자기와 같은 수들 중 2개를 선택해서 부분합을 하면 나누어 떨어진다는 것을 응용하여 0이 3개이므로
 3C2 = 3, 1이 2개이므로 2C2 = 1, 총 4개에 자기까지 더해서 나누어 떨어지는 즉, 0이 3개를 더해서 7개이다.

 추가적으로 자료형을 long을 써야 통과가 된다.(생각해보자.)
*/
import java.io.*;
import java.util.*;
public class p10986 {
    static final int MAX = 1000000 + 1;
    static long[] psum, cnt;
    static long n, m, ret;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stol(st.nextToken());
        m = stol(st.nextToken());
        psum = new long[MAX];
        cnt = new long[(int)m];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            long num = stol(st.nextToken());
            // 부분합의 나머지를 저장
            psum[i] = (psum[i - 1] + num) % m;
            // 나머지가 같은 부분합을 그룹화
            cnt[(int) psum[i]]++;
            // 부분합의 나머지가 0인 경우에는 답이므로 바로 카운팅한다.
            if (psum[i] == 0) ret++;
        }

        // 각 부분합의 나머지가 0인 경우 같은 그룹에서 nC2를 구한다.
        for (int i = 0; i < m; i++) {
            ret += cnt[i] * (cnt[i] - 1) / 2;
        }
        System.out.println(ret);
    }

    static long stol(String s) {
        return Long.parseLong(s);
    }
}
```

# **> 비트마스킹**

---

> **bitmask**
>
> : 정수의 이진수 표현을 자료구조로 쓰는 기법

데이터들은 0과 1의 집합이라고 합니다. 비트는 네트워크 외에도 운영체제 등에서도 자주 접할 수 있는데요

때문에 최적의 성능을 위해 여러 곳에서 쓰이고 있습니다.

이진 숫자로 `0`, `1` 값을 가질 수 있고 이에 따라 `true/false`, `on/off` 와 같은 상태를 나타내는 것이 가능합니다.

## 비트마스크 장점

먼저 비트마스킹의 장점을 알아 보겠습니다.

1. **빠른 수행시간**

   시간복잡도 O(1)에 구현되는 것이 많습니다. 비트마스크를 사용할 수 있다는 말은 원소의 수가 많지 않다는 뜻이기 때문에, 큰 속도 향상을 기대할 수는 없지만 여러번 수행해야 하는 경우에는 작은 최적화도 큰 속도 향상을 가져올 수 있습니다.

2. **간결한 코드**

   양한 집합 연사자들을 반복문 없이 한 줄에 쓸 수 있습니다.

3. **작은 메모리 사용량**

    적은 메모리를 사용할 수 있다는 말은 데이터를 미리 계산하여 저장해 둘 수 있으므로 캐시 효율이 좋다는 말입니다.

   ```c
   // Array 사용 시
   boolean[] array1 = {1, 0, 0, 0};
   boolean[] array2 = {1, 1, 0, 0};
   boolean[] array3 = {0, 0, 1, 1};
   
   // 비트마스킹 사용 시
   {0} -> 1000
   {0, 1} -> 1100
   {2, 3} -> 0011
   ```

   

4. **연관 배열을 배열로 대체**

   예로 연관 배열 객체 `Map<Vector, int>`가 있다고 합시다. 이 때 비트마스크를 이용해 정수 변수로 나타내면 단ㄷ순 배열 `int[]`로 사용할 수 있습니다.

## 용어 정의

- `비트(bit)` : 0과 1로 나타내는 이진수의 한 자리
- `부호 없는 N비트 값` : 2<sup>0</sup> ~ 2<sup>N-1</sup>
- `최하위 비트(LSB)` : 2<sup>0</sup> 에 위치한 비트
- `최사우이 비트(MSB)` : 2<sup>N-1</sup> 에 위치한 비트
- `비트가 켜져있다` : 1
- `비트가 꺼져있다` : 0

## 비트 연산자

**AND 연산 (`&`)**

=> 대응하는 두 비트가 모두 1일 때 1 반환



**OR 연산 (`|`)**

=> 대응하는 두 비트 중 하나라도 1이라면 1, 아니면 0 반환



**XOR 연산 (`^`)**

=> 대응하는 두 비트가 다르면 1, 같으면 0을 반환



**NOT 연산 (`~`)**

=> 비트의 값을 반전



**Shift 연산 (`<<`, `>>`)**

=> 왼쪽 또는 오른쪽으로 비트를 이동

![image-20210409104558903](https://user-images.githubusercontent.com/58545240/114128289-fef9ce80-9936-11eb-9541-7423cdd7d6ab.png)

## 비트마스킹의 활용

### 1. 비트를 1로 (원소 추가)

```c
// 1010 부분집합에 2번째 요소를 삽입해 1110으로 만들고 싶어요
// bit = 1010
bit = bit | (1 << 2)
```

-> 시프트 연산을 통해 2번째 비트만 1로 할당되어 있는 이진수로 만들고, or 연산을 통해 원하는 결과를 얻게 됩다.

```cpp
unsigned short usA = 0xa00b;
usA |= (1 << 5) // 5번 비트만 1로 만든다.
0100 0001	// 65
0010 0000	// 32
-------------------
0110 0001	// 97
```

### 2. 비트를 0으로 (원소 삭제)

```c
// bit = 1110
bit = bit & ~(1 << 2)
```

```cpp
unsigend short usA = 0xa00b;
usA &= ~ (1 << 5) // 5번 비트만 0으로 만든다.
0110 0001	// 97
1101 1111	// 127
-------------------
0100 0001	// 65
```

### 3. 비트의 조회

```c
if (bit & (1 << i) != 0) // i 번째 비트는 1
else // i번째 비트는 0
```

### 4. 비트의 반전 (토글)

```c
bit = bit ^ (1 << i)
```

`xor`연산을 통해 다른 비트들은 1<sup>0</sup> = 0, 0<sup>0</sup> = 0이어서 영향을 받지 않고, 해당 비트는 1<sup>1</sup> = 1, 0<sup>1</sup> = 1 이기 때문에 전환할 수 있습니다.

*정리*

```java
int added = a | b;				// a와 b의 합집합
int intersection = a & b;		// a와 b의 교집합
int removed = a & ~b;			// a에서 b를 뺀 차집합
int toggled = a ^ b;			// a와 b 중 하나에만 포함된 원소들의 집합
```



## 비트마스크를 이용한 집합의 구현

N비트 정수 변수는 0부터 N-1 까지의 정수 원소를 가질 수 있는 집합이 됩니다.

원소 i는 2<sup>i</sup> 을 나타냅니다.

ex) `{1, 4, 5, 6, 7, 9} = 1011110010(2) = 754`



- **피자집 예제**

  0부터 19까지 번호를 갖는 20가지의 토핑이 있고, 주문시 토핑을 `넣기/넣지 않기` 를 선택할 수 있습니다.

  **공집합과 꽉 찬 집합 구하기**

  - 토핑을 올리지 않은 피자(공집합) : 상수 0

  - 모든 토핑을 올린 피자(꽉 찬 집합) : 20개의 비트가 모두 켜진 상태

    ```java
    int fullPizza = (1 << 20) - 1;
    // 1 << 20은 이진수로 1뒤에 20개의 0이 있는 정수 인데, 여기서 1을 빼면 20개의 비트가 모두 켜진수
    ```

  **집합의 크기 구하기**

  - 재귀 호출로 각 비트를 순회하면서 **켜져 있는** 비트의 수를 세는 방법입니다.

    ```java
    int bitCount(int x) {
        if (x == 0) return 0;
        return x % 2 + bitCount(x / 2);
    }
    ```

## 주의할 점

C99에 표준에 따르면 우측 피연산자 값이 음수이거나 좌측 피연산자의 범위보다 큰 경우의 행위는 정의되지 않았다고 합니다.

---

E1이 양수인 경우에는 E1 << E2 는 E1 을 E2 비트만큼 왼쪽으로 이동, 이것은 E1에 2<sup>E2</sup>을 곱한 것과 같다. **E1이 음수인 경우의 동작은 정의되지 않았다.**

E1이 양수인 경우 E1 >> E2 역시 E2 비트만큼 오른쪽으로 이동, 이것은 E2를 2<sup>E2</sup>을 나눈 것과 같다. 그러나 **E1이 음수인 경우, 결과는 구현에 따라 달라진다.**

---

결국 C99 표준에 정의되지 않은 사항은 CPU 명령어 정의에 따라 달라지게 되는데 `Intel CPU`에서의 동작은 다음과 같습니다.



`<<`, `>>` 의 연산자의 오른쪽 피연산자는 `short` 일때 4자리 (0 ~ 15), `int` 일때 5자리(0 ~ 31), 64 비트형인 `long long` 일때 6자리(0 ~ 63)의 범위에 대해서만 유효하다.

다시 말하면 오른쪽 피연산자는 `short`일 때 하위 4비트만 사용하며 `int`일때는 하위 5비트, `long long`일때는 하위 6비트만 사용합니다.

**`shift`연산**은 왼쪽 피연산자 값의 범위(비트 길이) 내에서만큼만 쉬프트 연산이 이루어집니다. 즉,

`int n = 1`, `n <<= 32` 에서 32는 `100000`이 되고, 하위 5비트는 `00000` 이므로 `n <<= 32`는 `n <<= 0`과 같습니다.

`n <<= 33`은 33이 `100001`이며 이중 하위 5비트는 `0000`이므로 `n <<= 33`은 `n <<= 1`과 같아집니다.

`n <<= 31`의 경우는 답이 1 * 2<sup>31</sup>이 아닌 -2147483648( -1 * 2<sup>31</sup>)이 됩니다. 이렇게 되는 이유는 **int의 경우 최상위 비트가 부호비트가 되어 부호비트가 0에서 1로 바뀌면서 음수값을 취하게 됩니다.**



이와 같이 비트연산은 다양한 최적화 테크닉에 있어 중요한 부분이지만 계속 봐도봐도 어려우므로 꾸준히 알아갑시다^^!

## 사용사례

그러면 마지막으로 비트연산을 활용할 수 있는 문제를 몇개 보고 가겠습니다.

### - BOJ[1174] : 줄어드는 숫자

https://www.acmicpc.net/problem/1174

```java
import java.io.*;
import java.util.*;
public class p1174 {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int[] num = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        ArrayList<Long> arr = new ArrayList<>();
        // 2^10 만큼의 모든 경우의 수를 만든다.
        for (int i = 1; i < (1<<10); i++) {
            long sum = 0;
            // 10 자리 만큼 탐색한다.
            for (int j = 0; j < 10; j++) {
                // i가 0^2, 1^2, 2^2 .... j^2 승보다 크다면
                // -> i에 해당하는 비트가 각각 어느 위치(몇 번째 자리)에 비트를 가지고 있는지 체크
                // if문을 만족한다면 해당 자릿수가 존재한다는 것이므로 sum*10 + num[j]를 추가.
                if ((i & (1 << j)) > 0) {
                    sum = sum * 10 + num[j];
                }
            }
            arr.add(sum);
        }

        Collections.sort(arr);
        if (n > arr.size()) {
            System.out.println("-1");
            return;
        }
        System.out.println(arr.get(n - 1));
    }
}
```



### - BOJ[1062] : 가르침

https://www.acmicpc.net/problem/1062

모든 단어는 "anta"로 시작하고 "tica"로 끝나기 때문에 우선 5개의 알파벳은 고정입니다.
따라서 k가 5보다 작으면 어느 단어도 읽을 수 없고, k가 26이라면 모든 단어를 읽을 수 있습니다.
따라서 나머지 문자들에 대해서 비트마스킹을 이용한 백트래킹을 구현하면 됩니다.

자세한 설명은 주석으로 달아놓겠습니다.

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Main {
    static int N, K;
    static int answer = 0;
    static int[] words;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] outline = br.readLine().split(" ");
        N = Integer.parseInt(outline[0]);
        K = Integer.parseInt(outline[1]);
        if (K < 5) {
            System.out.println(answer);
            return;
        } else if (K == 26) {
            System.out.println(N);
            return;
        }

        K -=5;
        words = new int[N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j <line.length(); j++) {
                char values = line.charAt(j);
                int index = values - 'a';
                words[i] |= 1 << index;
            }
        }
        int standard = 0;
        // 'char' - 'a'에 해당하는 비트를 1로 만든다. (TRUE의 개념)
        standard |= 1 << ('a' -'a');
        standard |= 1 << ('n' -'a');
        standard |= 1 << ('t' -'a');
        standard |= 1 << ('c' -'a');
        standard |= 1 << ('i' -'a');
        combination(0, 0,standard);
        System.out.println(answer);
    }

    public static void combination(int index,int start, int flag) {
        if(index == K) {
            int result = 0;
            for(int word : words) {
                // flag = 내가 읽을 수 있는 전체 집합 true면 새로운 단어는 없다.
                // word와 flag를 합집합했을 때 flag가 나온다면 word는 읽을 수 있는 단어이다.
                if( (word | flag) == flag)
                    result ++;
            }
            answer = answer >= result ? answer : result;
            return;
        }

        for(int i = start; i < 26; i++) {
            // 켜져있는 비트를 제외하고 모든 경우의 수를 담는다.
            if((flag & 1 << i) == 0) {
                combination(index+1, i+1, flag | 1 << i);
            }
        }
    }
}
```

# **> Kadane Algorithm**

>   카데인 알고리즘

`[6, -1, 5, -3, 9]` 와 같은 수열이 있다고 하자.

이 때 **각 수들을 더했을 때 가장 큰 수가 나오는 연속된 부분합**을 찾는 알고리즘을 카데인 알고리즘 이라고 한다.



풀이의 핵심 순서는 이러하다.

1.  수열의 각 요소를 하나씩 더하기
2.  더한 값을 변수에 저장
3.  더한 값이 마지막에 저장해놓은 변수보다 크다면 변수를 대입



자바 코드로 보자

```java
int nums = {6, -1, 5, -3, 8};

int getMaximumSubArray(int[] nums) {
    if (nums.length == 1) {
        return nums[0];
    }
    
    int maxNum = Integer.MAX_VALUE;
    int sum = 0;
    // 1. 배열 요소를 하나씩 탐색하면서 값을 넣어본다.
    for (int i = 0, j = nums.length; i < j; i++) {
        // 2. 더한 값 sum 변수에 대입
        sum += nums[i];
        
        // 3. 이전에 합산 값과 비교하여 최대값을 저장
        if (maxNum < sum) maxNum = sum;
        if (sum < 0) sum = 0;
    }
    
    return maxNum;
}
```

최대 부분합을 구하는 카데인 알고리즘은 `O(n)`의 시간복잡도를 가지게 되므로 Brute Force( O(n<sup>2</sup>) )보다 효율적이다.

이 알고리즘을 모른다 하더라도, 투포인터로 충분히 해결할 수 있으며 필자는 투포인터가 더 마음에 든다.