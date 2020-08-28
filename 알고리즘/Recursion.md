# 재귀(Recursion) :fist_oncoming:

# **> Recursion의 기본 개념과 예제**

---

## Recursion

우리말로 순환 또는 재귀함수라고 한다. 즉, 자기 자신을 호출하는 함수, 메소드를 뜻한다.

```java
void func(){
    ...
    func();
    ...
}
```

**ex1 - 무한루프**

```java
public class Code01{
    public static void main(String[] args){
        func();
    }
    
    private static void func(){
        System.out.println("hello..");
        func();
    }
}
```

**ex2 - 항상 무한루프에 빠질까?**

- `recursion`이 적절한 구조를 가지고 있다면, 항상 무한루프에 빠지는 것은 아니다.
  - **Base Case** : 적어도 하나의 `recursion`에 빠지지 않는 경우가 존재해야 한다.
  - **Recursive Case** : `recursion`을 반복하다 보면 결국 Base Case로 수렴해야한다.

```java
public class Code2{
    public static void main(String[] args){
        int n = 4;
        func(n);
    }
    
    private static void func(int n) {
    if (n <= 0)
      return;
    else {
      System.out.println("Hello...");
      func(n - 1);
    }
  }
}
```

**ex3 - 1~n까지의 합**

- 입력으로 받은 정수 n에 대해서 1~ n까지의 합을 구한다.

```java
public class Code03 {
  public static void main(String[] args) {
    int result = func(4);
    System.out.println("result: " + result);
  }

  private static int func(int n) {
    if (n == 0)
      return 0;
    else
      return n + func(n - 1);
  }
}
```

```bash
result: 10

Process finished with exit code 0
```

### recursion의 해석

- 이 함수의 mission은 0~n까지의 합을 구하는 것이다.
- n = 0이라면 합은 0이다.
- n이 0보다 크다면 0에서 n까지의 합은 0에서 n-1까지의 합에 n을 더한 것이다.

```java
public static int func(int n) {
  if(n == 0) 
    return 0;
  else
    return n + func(n - 1);
}
```

### 순환함수와 수학적귀납법

- 정리
  - `func(int n)`은 음이 아닌 정수 n에 대해서 0에서 n까지의 합을 올바로 계산한다.
- 증명
  1. `n=0`인 경우 : n=0인 경우 0을 반환한다. 올바르다.
  2. 임의의 양의 정수 k에 대해서 `n<k`인 경우 0에서 n까지의 합을 올바르게 계산하여 반환한다고 가정하자.
  3. `n=k`인 경우를 고려해보자. func메소드는 먼저 `func(k-1)`을 호출하는데 2번에 가정에 의해서 0에서 k-1까지의 합이 올바로 계산되어 반환된다. 메소드 func는 그 값에 n을 더해서 반환한다. 따라서 메소드 func는 0에서 k까지의 합을 올바로 계산하여 반환한다.
- 항상 이런식으로 증명을 작성할 필요는 없지만, `recursion`에 대해서 고민할 때 위와같은 방식으로 고민하는 것이 상당히 도움이 된다.

## Factorial: n!

- `0!=1`
- `n! = nX(n-1)! n>0`
- Factorial이 가진 recursive한 성질을 그대로 이용하여 아래와 같은 코드를 작성할 수 있다.

```java
public static int factorial(int n){
    if(n==0){
        return 1;
    } else {
        return n * factorial(n - 1);
    }
}
```

### 순환함수와 수학적귀납법

- 정리
  - `factorial(int n)`은 음이 아닌 정수 n에 대해서 n!을 올바로 계산한다.
- 증명
  1. `n=0`인 경우 : n=0인 경우 1을 반환한다. 올바르다.
  2. 임의의 양의 정수 k에 대해서 `n<k`인 경우 `n!`을 올바르게 계산한다고 가정하자.
  3. `n=k`인 경우를 고려해보자. factorial은 먼저 `factorial(k-1)` 호출하는데 2번의 가정에 의해서 `(k-1)!`이 올바로 계산되어 반환된다. 따라서 메소드 factorial은 `k *(k-1)! = k!` 을 반환한다.

## Xⁿ

- `X⁰ = 1`
- `Xⁿ = X * Xⁿ⁻¹ if n>0`

```java
public static double power(double x, int n) {
  if (n == 0)
    return 1;
  else
    return x * power(x, n-1);
}
```

## Fibonacci Number

- `f₀ = 0`
- `f₁ = 1`
- `fn = fn₋₁ + fn₋₂ n>1`

```java
public int fibonacci(int n) {
  if(n < 2) 
    return n;
  else
    return fibonacci(n - 1) + fibonacci(n - 2);
}
```

## 최대 공약수: Euclid Method

- m >= n인 두 양의 정수 m과 n에 대해서 m이 n의 배수이면 gcd(m, n) = n이고,
- 그렇지 않으면 gcd(m, n) = gcd(n, m % n)이다.
  - n과 m을 n으로 나눈 나머지 간의 최대공약수이다.

```java
public static int gcd(int m, int n) {
  if (m < n) {
    int tmp = m;
    m = n;
    n = tmp;
  }
  if (m % n == 0)
    return n;
  else
    return gcd(n, m % n);
}
```

### Euclid Method : 좀 더 단순한 버전

- gcd(p, q)
  - `if q = 0 -> p`
  - otherwise gcd(q, p % q)

```java
public static int gcd(int p, int q) {
  if (q == 0)
    return p;
  else
    return gcd(q, p % q);
}
```

### 최대공약수 최소공배수 예제(유클리드 호제법)

```java
public class Code04 {
  public static void main(String[] args) {
    int result = gcd(12, 6);
    System.out.println("최대공약수: " + result);
    result = euclidGcd(12, 6);
    System.out.println("유클리드 호제법 최대공약수: " + result);
    result = lcm(12, 8);
    System.out.println("최소공배수: " + result);
  }

  private static int gcd(int m, int n) {
    if (m < n) {
      int tmp = m;
      m = n;
      n = tmp;
    }
    if (m % n == 0)
      return n;
    else
      return gcd(n, m % n);
  }

  private static int euclidGcd(int p, int q) {
    if (q == 0)
      return p;
    else
      return euclidGcd(q, p % q);
  }

  private static int lcm(int m, int n) {
    int bigger = 0;
    bigger = (m > n) ? m : n;
    while (true) {
      if ((bigger % m == 0) && (bigger % n == 0))
        return bigger;
      bigger++;
    }
  }
}
```

```bash
최대공약수: 6
유클리드 호제법 최대공약수: 6
최소공배수: 24

Process finished with exit code 0
```

# **> Recursion의 기본 개념과 예제2**

## Recursive Thinking - 순환적 사고

### Recursion은 수학함수 계산에만 유용한가?

- 수학함수뿐 아니라 다른 많은 문제들을 recursion으로 해결할 수 있다.

### 문자열의 길이 계산

- 순서대로 앞에서부터 하나씩 카운트
- 또는, 총 문자열의 길이는 첫 번째 문자를 뺀, 전체 문자열의 길이 +1(첫번째 문자)이다.

```java
if the string is empty	// base case
    return 0;
else
    return 1 plus the length of the string that excludes the first character;
```

```java
public static int length(String str){
    if(str.equals(""))
        return 0;
    else
        return 1 + length(str.substring(1));
}
```

#### - substring의 정의

```java
public String substring(int start)
```

Returns a new `String` that contains a subsequence of characters currently contained in this character sequence.

The substring begins at the specified index and extends to the end of this sequence.

- Parameters : `start` - The beginning index, inclusive
- Returns : The new string.
- Throws : `StringIndexOutOfBoundsException` - if `start` is less than zero, or greater than the length of this object.

### 문자열의 프린트

```java
public static void printChars(String str){
    if(str.length() == 0)
        return;
    else {
        System.out.print(str.charAt(0));
        printChars(str.substring(1));
    }
}
```

### 문자열을 뒤집어 플니트

- `recursive`하게 프린트 하려면
  - 먼저 첫 문자를 뺀 나머지 문자열을 뒤집어 프린트 한 후
  - 마지막으로 첫 문자를 프린트 한다.

```java
public static void printCharsReverse(String str){
    if(str.length() == 0)
        return;
    else {
        printCharsReverse(str.substring(1));
        System.out.print(str.charAt(0));
    }
}
```

### 2진수로 변환하여 출력

- 음이 아닌 정수 n을 이진수로 변환하여 인쇄한다.
- n을 2로 나눈 몫을 먼저 2진수로 변환하여 인쇄한 후
- n을 2로 나눈 나머지를 인쇄한다.

```java
public void printInBinary(int n) {
  if (n < 2)
    System.out.print(n);
  else {
    printInBinary(n / 2);
    System.out.print(n % 2)
  }
}
```

### 배열의 합 구하기

- data[0]에서 data[n-1]까지의 합을 구하여 반환한다.

```java
public static int sum(int n, int[] data) {
  int (n <= 0)
    return 0;
  else
    return sum(n-1, data) + data[n - 1];
}
```

### 데이터파일로부터 n개의 정수 읽어오기

- Scanner in이 참조하는 파일로부터 n개의 정수를 입력받아 배열 data의 data[0], ... , data[n-1]에 저장한다.

```java
public void readFrom(int n, int[] data, Scanner in) {
  if (n == 0)
    return;
  else {
    readFrom(n-1, data, in);
    data[n-1] = in.nextInt();
  }
}
```

## Recursive VS Iteration

- 모든 순환함수는 반복문(Iteration)으로 변경 가능
- 그 역도 성립함. 즉, 모든 반복문은 recursion으로 표현 가능함
- 순환함수는 복잡한 알고리즘을 단순하고 알기쉽게 표현하는 것을 가능하게 함
- 하지만 recursion은 함수 호출에 따른 오버헤드가 있음(매개변수 전달, 액티베이션 프레임 생성 등)

# **> Recursion의 기본 개념과 예제3**

---

## Desigining Recursion

### 순환적 알고리즘의 설계

- 적어도 하나의 base case, 즉 순환되지 않고 종료되는 case가 있어야 한다

- 모든 case는 결국 base case로 수렴해야 한다

  **ex - 가장 단순한 경우**

  ```java
  if(...){
      // basecase
  } else {
      // recursion
  }
  ```

- **암시적(implicit) 매개변수를 명시적(explicit) 매개변수로 바꾸어라!!**

### 순차탐색

이 함수의 미션은 `data[0]`에서 `data[n-1]`사이에서 target을 검색하는 것이다. 하지만 검색 구간의 시작 인덱스 0은 보통 생략한다. **즉, 0은 암시적 매개변수이다.**

```java
int search(int[] data, int n, int target){
    for(int i=0; i<n; i++){
        if(data[i] == target)
            return i;
    }
    return -1;
}
```

### 매개변수의 명시화 : 순차탐색

- 이 함수의 미션은 `data[begin]`에서 `data[end]`사이에서 target을 검색한다. **즉, 검색구간의 시작점을 명시적으로(explicit)으로 지정한다.**
- 이 함수를 `search(data, 0, n-1, target)으로 호출한다면 위에있는 순차탐색 함수와 완전히 동일한 일을 한다.

```java
int search(int[] data, int begin, int end, int target){
    if(begin > end)
        return -1;
    else if (target == data[begin])
        return begin;
    else
        return search(data, begin+1, end, target);
}
```

### 순차탐색 : 다른버전

- 이 함수의 미션은 `data[begin]`에서 `data[end]` 사이에서 target을 검색한다. 즉, 검색구간의 시작점을 명시적으로 지정한다.

```java
int search(int[] data, int begin, int end, int target){
    if(begin > end)
        return -1;
    else if (target == data[begin])
        return begin;
    else
        return search(data, begin, end-1, target);
}
```

### 순차탐색 : 다른버전2

- Binary Search 와는 다르다.

```java
int search(int[] data, int begin, int end, int target){
    if (begin > end)
        begin 01;
    else {
        int middle = (begin + end) / 2;
        if (data[middle] == target)
            return middle;
        int index = search(data, begin, middle-1, target);
        if (index != -1)
            return index;
        else
            return search(data, middle+1, end, target);
    }
}
```

### 매개변수의 명시화 : 최대값 찾기

- 이 함수의 미션은 `data[begin]`에서 `data[end]` 사이에서 최대값을 찾아 반환한다. `begin <= end`라고 가정한다.
- 여기서 최대값을 찾는 법은 첫번째 인덱스에 해당하는 값과, 첫번째 인덱스를 제외한 나머지 범위에서의 값을 비교한 것들중에 순환적으로 최대값을 찾는다.
- Base case 는 begin == end, 즉 데이터의 갯수가 1개인 경우다

```java
int findMax(int[] data, int begin, int end){
    if (begin == end)
        return data[begin];
    else
        return Math.max(data[begin], findMax(data, begin+1, end));
}
```

### 최대값 찾기 : 다른버전

```java
int findMax(int[] data, int begin, int end){
    if (begin == end)
        return data[begin];
    else {
        int middle = (begin + end) / 2;
        int max1 = findMax(data, begin, middle);
        int max2 = findMax(data, middle+1, end);
        return Math.max(max1, max2);
    }
}
```

### Binary Search

- `items[begin]`에서 `items[add]` 사이에서 target을 검색한다.
- 해당 메소드에 매개변수를 명시적으로 표시하지 않는다면, `recursion`으로 구현할 떄 내부에서 recursive하게 호출되는 함수를 표현할 수 있는 방법이 없어진다.

```java
public static int binarySearch(String[] items, String target, int begin, int end){
    if (begin > end)
        return -1;
    else {
        int middle = (begin + end) / 2;
        int compResult = target.compareTo(items[middle]);
        if (compResult == 0)
            return middle;
        else if (compResult < 0)
            return binarySearch(item, target, begin, middle -1);
        else
            return binarySearch(item, target, middle-1, end);
    }
}
```

### 따라서, 순환 알고리즘을 설계하는데에 있어서 가장 중요한 것은

- 적어도 하나의 base case, 즉 순환되지 않고 종료되는 case가 있어야 함
- 모든 case는 결국 base case로 수렴해야 함
- **암시적(implicit) 매개변수를 명시적(explicit) 매개변수로 바꾸어라!**

# **> Tail Call Recursion**

> C++

제가 이번에 설명할 것은 제가 검색하다가 발견한! `Tail Call Recursion` 이라는 새로운 재귀?적인 방법의 코딩입니다. 기존의 재귀함수와 비교하면서 설명하도록 하겠습니다.

## 1. 기존의 재귀함수

먼저 기존의 재귀함수를 보도록합니다. 여기서는 가장 대표적인 피보나치 수열을 이용한 재귀함수를 살펴보겠습니다.

```C++
#include <iostram>
using namespace std;

int f(int n){
    if(n < 0) return 0;
    if(n < 2) return n;
    return f(n-1) + f(n-2);
}

int main(void){
    cout << f(4) << '\n';
    
    return 0;
}
```

뭐.. 설명할 것이 많이 없네요. f라는 함수는 피보나치 수열의 n번째 항을 값으로 리턴해주는 함수입니다. 0번째 항은 0으로 정의 하겠습니다. f(4)를 호출하게 되면 피보나치수열의 4번째 값인 ‘3’이 잘 출력되는 프로그램입니다. 여기서! f(4)가 호출되었을 때, 일어나는 과정을 살펴보면.

```bash
f(4) 호출
    f(3) 호출
        f(2) 호출
            f(1) 호출 1 리턴
            f(0) 호출 0 리턴
        f(2) 가 1 리턴
        f(1) 호출 1리턴
    f(3) 이 2 리턴
    f(2) 호출
        f(1) 호출 1 리턴
        f(0) 호출 0 리턴
    f(2) 가 1 리턴
f(4) 가 3 리턴
```

f(4)만 호출해도 머리로만 따라가기엔 조금 벅찹니다.. 물론 컴퓨터는 이러한 연산을 빠르게 해서 답을 잘 내주는 것이죠. 그렇다면 여기서 f(40)을 호출하면 어떻게 될까요? 콘솔이.. 쉽게 답을 내주지 않습니다. 멈춘것이 아니라 계속 연산을 진행중인 것입니다. f(4)만 호출해도 이정도의 양을 연산해야 하는데 f(40)을 호출하게되면 엄청난 양의 연산을 진행해야 하기때문입니다. 게다가 엄청난 양의 Stack공간도 사용하게 됩니다. **왜냐하면 호출 스택에 자신을 호출했던 주소를 저장해놓고 그 위치로 다시 돌아가야 하기 때문이죠.** 100보다 큰 값을 호출하면 프로그램이 죽는 경우도 있을 겁니다..

## 2. Tail Call Recursion

### 2.1 Tail Call

먼저 `Tail Call` 이란 것을 살펴보도록 합시다. tail call 이란 말그대로 꼬리호출, 맨 마지막에서 호출한다는 뜻입니다. 예를 들어 보겠습니다.

```C++
#include <iostram>
using namespace std;

int f(int a){
    a = 0;
    return 0;
}

int foo1(int b){
    return f(b) + 1;
}

int foo2(int c){
    return f(c);
}

int main(void){
    cout << foo1(10) << '\n';
    cout << foo2(10) << '\n';
    
    return 0;
}
```

먼저 `f`라는 함수를 보겠습니다. f는 받은 값을 0으로 만들어 돌려주는 함수입니다. 여기서 `foo1` 을 보면 `f(b)` 를 호출하고 + 1을 한뒤에 리턴합니다. 이렇게 되면 `foo1` 에서 불린 `f(b)` 는 tail call이 **아닙니다.** 왜냐하면 `f(b)` 가 값을 리턴한 후에 다시 `foo1` 으로 **돌아와서** + 1을 해준후에 값을 리턴해야하기 때문입니다. 반면에 `foo2` 를 보게되면 `f(c)` 를 호출하고 바로 리턴합니다. **이렇게 되면 다시 `foo2`로 돌아와서 작업할 필요가 없고 그냥 `f(c)` 의 값만 리턴하면 됩니다.**

즉, `Tail Call` 이란건 함수를 바로 종료하기 위해서 함수의 마지막에서 함수를 호출하는 것을 말합니다. 위의 `foo1` 처럼 마지막에 함수를 호출했다고 다 tail call이 아니라 함수를 호출 했을 때, 함수를 호출한 함수가 바로 종료될 수 있도록 하는 기법입니다.

### 2.2 Tail Call Optimization

이렇게 `Tail Call` 을 사용하게 되면 무엇이 좋은가! 라고 생각하실 수 있습니다. 이렇게 함수의 마지막에서 함수를 호출하게 되면(다른 추가적인 일을 할 필요 없는 상태에서) 다시 이 호출한 함수로 돌아올 필요가 없어지게 됩니다. **함수에서 더 이상 할일이 없기 때문에 Stack에 저장할 필요없이 끝내도 된다는 뜻입니다..!** 그리고 사용한 Stack 공간을 재사용 할 수 있게 됩니다. 이러한 기법을 `Tail Call Optimiztion` 이라고 하는 것입니다. 하지만 이것은 언어가 지원하는 경우에만 해줍니다...

### 2.3 Tail Recursion

그렇다면 `Tail Recursion` 이란? tail call방식으로 함수를 작성하는데 호출하는 함수가 자기자신인 재귀함수인 것입니다. 위의 피보나치 코드를 tail recursion방식으로 작성하여 보면

```c++
#include <iostream>
using namespace std;

int f(int n, int prev=0;, int next =1){
    if(not n) return prev;
    return f(n-1, next, prev + next);
}

int main(){
    cout << f(4) << '\n';
    return 0;
}
```

이렇게 됩니다. 함수 `f` 에서 마지막에 자기자신만 호출하고 아무것도 안하므로 마지막에 호출하는 tail call recursion 형식이 됩니다. f(40)까지 빠르게 구할 수 있게됩니다. 여기에 tail call optimization까지 적용한다면 Stack을 더이상 낭비하지 않고 함수안의 피라미터만 바꿈으로써 추가적인 메모리도 필요하지 않게 됩니다. *wikipedia 에서는 이렇게 되면* `*goto*` *문의 사용과 비슷한 효과를 내며 기계어로* `*JUMP*`*와 같은 코드가 된다고 합니다.*

위의 피보나치 함수와 어떻게 다른지 살펴보면,

```bash
f(4, 0, 1) 호출
    f(3, 1, 1) 피라미터 변경 호출(추가적인 Stack공간의 사용 없음)
        f(2, 1, 2) 피라미터 변경 호출(추가적인 Stack공간의 사용 없음)
            f(1, 2, 3) 피라미터 변경 호출(추가적인 Stack공간의 사용 없음)
                f(0, 3, 5) 피라미터 변경 호출(추가적인 Stack공간의 사용 없음)
                return 3;
            return 3;
        return 3;
    return 3;
return 3;
```

위에서는 f(4)를 호출하게되면 총 9번의 함수호출이 있었지만 tail call recursion을 사용하니 호출이 5개로 확 줄은 것을 볼 수 있습니다. 또한 tail call optimization으로 Stack공간을 추가적으로 사용하지 않고 피라미터의 값만 변경함으로써 메모리공간도 확보되었습니다.

참고한 사이트를 첨부하며 글 마칩니다.

- [https://homoefficio.github.io/2015/07/27/%EC%9E%AC%EA%B7%80-%EB%B0%98%EB%B3%B5-Tail-Recursion](https://homoefficio.github.io/2015/07/27/재귀-반복-Tail-Recursion/)
- https://en.wikipedia.org/wiki/Tail_call

# **> 미로찾기 (Recursion 응용)**

---

- `(n-1, n-1)`의 좌표를 출구로 가정
- 흰색이 지날 수 있는 길, 파란색이 벽
- 입구에서 출구까지의 경로를 찾는 문제

![image-20200828095404950](https://user-images.githubusercontent.com/58545240/91522761-ffa89c00-e935-11ea-840e-79a9fdd5bd0e.png)

## Recursive Thinking

- 현재 위치에서 출구까지 가는 경로가 있으려면

  - 현재 위치가 출구이거나(이미 내가 출구에 와 있거나). 혹은,
  - **이웃한 셀들 중 하나에서 현재 위치를 지나지 않고 출구까지 가능경로**가 있꺼나,
    - 위의 경우를 `Recursive`하게 생각한다. 전체 문제를 해결하려고 하면 부분 문제의 해결이 이루어지면서 전체문제가 해결된다.

  => 위의 둘중에 하나가 성립해야 출구까지 가는 경로가 있다고 할 수 있다.

## 미로찾기(Decision Problem) - 답이 Yes or No

- 출발점에서 출구까지 가능 경로가 있느냐 없느냐?의 문제로 생각한다.
- `x'`, `y'`는 현재 셀과 이웃한 셀들
  - `x, y`의 `x', y'`(4개)를 각각 봤을 때, pathway가 없다면 출구까지 가는 경로가 없다.
- `recursion`을 고민할 때 가장 먼저 고민할 것은 무한 루프에 빠지지 않는 가이다.
  - Base case로 수렴하는가를 확인해야한다.
  - 현재 아래코드에서는 pathway인 두 셀끼리의 무한루프가 발생할 수 있다.(서로가 서로의 `x', y'`가 될 수 있으므로)

```java
boolean findPath(x, y)
    if(x, y) is the exit
        return true;
	else
        for each neighboring cell(x', y') of (x, y) do
            if(x', y') is on the pathway
                if findPath(x', y')
                    return true;
		return false;
```

- 따라서, `x, y`의 상황에서 고려할 때, `x', y'`에서 다시 `x, y`로 돌아오는 것을 막아줄 필요가 있다.
  - 그것을 방문한 위치와 방문하지 않은 위치로 구분하여 처리한다
  - 아래와 같이 코드가 변경될 수 있다.
  - 조건문에서 pathway체크와 함께 방문하지 않은 셀의 여부를 체크하기 때문에 무한 루프에 빠지지 않는다.

```java
boolean findPath(x, y)
    if (x, y) is the exit
        return true;
	else
        mark (x, y) as a visited cell
        for each neighbouring cell (x', y') of (x, y) do
            if (x', y') is on the pathway and not visited
                if findPath(x', y')
                    return true;
	return false;
```

- 다른 방법으로 `Recursion` 호출 `(findPath(x', y'))`을 하기 전에 pathway체크와 방문여부를 체크하지 않고, 메소드의 시작지점에서 pathway체크와 방문여부를 체크하여 false를 리턴하는 방법도 있다.
  - 이렇게하면 `recursion`이 호출되는 횟수는 더 많아 지지만 코드가 간결해지는 면이 있다.

```java
boolean findPath(x, y)
   if (x, y) is either on the wall or a visited cell
       return false;
	else if (x, y) is the exit
        return true;
	else 
        mark (x, y) as a visited cell;
        for each neighbouring cell (x', y') of (x, y) do
            if findPath(x', y')
                return true;
	return false;
```

## 구현

- PATH_COLOR : visited 이며 아직 출구로 가는 경로가 될 가능성이 있는 cell
- BLOCKED_COLOR : visited 이며 출구까지의 경로상에 있지 않음이 밝혀진 cell
- 일단 방문하면 PATH_COLOR로 칠하고 출구까지의 경로상에 있지 않음이 밝혀질 경우 BLOCKED_COLOR로 칠한다.

```JAVA
public class Maze{
    private static final int PATHWAY_COLOR = 0;
    private static final int WALL_COLOR = 1;
    private static final int BLOCKED_COLOR = 2;
    private static final int PATH_COLOR = 3;
    private static int N = 8;
    private static int[][] maze = {
        {0, 0, 0, 0, 0, 0, 0, 1},
        {0, 1, 1, 0, 1, 1, 0, 1},
        {0, 0, 0, 1, 0, 0, 0, 1},
        {0, 1, 0, 0, 1, 1, 0, 0},
        {0, 1, 1, 1, 0, 0, 1, 1},
        {0, 1, 0, 0, 0, 1, 0, 1},
        {0, 0, 0, 1, 0, 0, 0, 1},
        {0, 1, 1, 1, 0, 1, 0, 0}
    };
    
    public static boolean findMazePath(int x, int  y){
        if (x<0 || y<0 || x>=N || y>= N)
            return false;
        else if (maze[x][y] != PATHWAY_COLOR)
            return false;
        else if (x==N-1 && y==N-1){
            maze[x][y] = PATH_COLOR;
            return true;
        } else{
            maze[x][y] = PATH_COLOR;
            if (findMazePath(x-1, y) || findMazePath(x, y+1) || findMazePath(x+1, y) || 						findMazePath(x, y-1){
                return true;
            }
            maze[x][y] = BLOCKED_COLOR;
            return false;    
        }
    }
                
	public static void main(String[] args){
        printMaze();
        findMazePath(0, 0);
        System.out.println();
        printMaze();
    }
                
	private static void printMaze(){
        for(int x=0; x<N; x++){
            System.out.println("{");
            for(int y=0; y<N; y++){
                System.out.println(maze[x][y] + ", ");
            }
            System.out.println("}");
        }
    }
}
```

```java
// 실행결과
{0, 0, 0, 0, 0, 0, 0, 1, }
{0, 1, 1, 0, 1, 1, 0, 1, }
{0, 0, 0, 1, 0, 0, 0, 1, }
{0, 1, 0, 0, 1, 1, 0, 0, }
{0, 1, 1, 1, 0, 0, 1, 1, }
{0, 1, 0, 0, 0, 1, 0, 1, }
{0, 0, 0, 1, 0, 0, 0, 1, }
{0, 1, 1, 1, 0, 1, 0, 0, }

{3, 2, 2, 2, 2, 2, 2, 1, }
{3, 1, 1, 2, 1, 1, 2, 1, }
{3, 2, 2, 1, 2, 2, 2, 1, }
{3, 1, 2, 2, 1, 1, 2, 2, }
{3, 1, 1, 1, 2, 2, 1, 1, }
{3, 1, 3, 3, 3, 1, 2, 1, }
{3, 3, 3, 1, 3, 3, 3, 1, }
{0, 1, 1, 1, 0, 1, 3, 3, }
```

# **> Counting Cells in a Blob (Recursion 응용)**

---

- 입력으로 Binary 이미지가 주어진다.
- 각 픽셀은 background pixel(흰색)이거나 혹은 imagepixel(파란색)이다.
- 서로 연결된 image pixel들의 집합을 **Blob**이라고 부른다.
- 상하좌우 및 대각방향으로도 연결된것을 **Blob**으로 간주한다.

![image-20200828102416096](https://user-images.githubusercontent.com/58545240/91522775-0800d700-e936-11ea-8d74-a75f6653b983.png)

- 따라서 위 그림에서는 총 4개의 Blob이 존재한다.



![image-20200828102444307](https://user-images.githubusercontent.com/58545240/91522788-0df6b800-e936-11ea-9589-fa440cb258d3.png)

## 특정 좌표가 속한 Blob의 크기 count

- 입력
  - `N * N` 크기의 2차원 그리드(grid)
  - 하나의 좌표 `(x, y)`
- 출력
  - 픽셀 `(x, y)`가 포함된 blob의 크기
  - `(x, y)`가 어떤 blob에도 속하지 않는 경우에는 0

## Recursive Thinking

- 현재 픽셀이 속한 Blob의 크기를 카운트하려면
  - 현재 픽셀이 image color가 아니라면
    - 0을 반환한다
  - 현재 픽셀이 image color라면
    - 먼저 현재 픽셀을 카운트한다.( count = 1 )
    - 현재 픽셀이 중복 카운트 되는 것을 방지하기 위해 다른 색으로 칠한다.
    - 현재 픽셀에 이웃한 모든 픽셀들(8개 픽셀들) 각각에 대해서
      - 그 픽셀이 속한 Blob의 크기를 카운트하여 카운터에 더해준다.
    - 카운터를 반환한다.

## 순환적 알고리즘 -1 

- x=5, y=3이라ㅏ고 가정, 즉 이 픽셀이 포함된 blob의 크기를 계산하는 것이 목적이다.
- count = 0에서 시작
- 다음으로 현재 cell을 다른색으로 칠하고 count를 1증가한다. (중복 방지)
- 현재 count = 1

![image-20200828102834088](https://user-images.githubusercontent.com/58545240/91522798-13ec9900-e936-11ea-8ee6-a228cb23ca08.png)

- 그 다음 `(5, 3)` 픽셀에 인접한 8개의 픽셀에 대해 순서대로 그 픽셀이 포함된 Blob의 크기를 count한다. 북, 북동, 동 .... 순으로 고려

- 가장먼저 북쪽인 `(5, 2)` 픽셀이 포함된 Blob의 크기는 0이다. 따라서 count 값은 변화없이 1이다.

- 다음으로 북동쪽인 `(6, 2)` 픽셀이 속한 Blob의 크기를 count하고, count된 픽셀들을 빨간색으로 칠한다.

  - 여기서 보면 `(6,2)` 픽셀을 포함한 Blob의 count는 3이다. `(6,2), (6,2)`의 북서쪽인 `(5, 1), (5, 1)`의 남서쪽인 `(4, 2)` 총 3개이다.

    ![image-20200828103102732](https://user-images.githubusercontent.com/58545240/91522813-1bac3d80-e936-11ea-8934-ba1a17a2a20a.png)

  - 현재 count = 1 + 3 = 4

  - 카운트 된 모든 픽셀들은 빨간색으로 중복타운트 방지

- 동일한 방법으로 다음 순서에 해당하는 남쪽 픽셀 `(5, 4)이 속한 Blob의 크기는 9이다. 카운트하고 색칠한다.

  - count = 4 + 9 = 13

![image-20200828103333302](https://user-images.githubusercontent.com/58545240/91522839-2666d280-e936-11ea-9092-4b0ebacd32d7.png)

- 남서, 서, 북서 방향은 픽셀이 속한 Blob이 없거나 혹은 이미 카운트 되었기 때문에 count에 영향을 주지 않는다.
- 결과적으로 count 값 13 return

## 구현

- x  = 열, y = 행

```java
public class CountingCellsBlob{
    private static int BACKGROUND_COLOR = 0;
    private static int IMAGE_COLOR = 1;
    private static int ALREADY_COUNTED = 2;
    private static int N = 8;
    private static int[][] grid = {
      {1, 0, 0, 0, 0, 0, 0, 1},
      {0, 1, 1, 0, 0, 1, 0, 0},
      {1, 1, 0, 0, 1, 0, 1, 0},
      {0, 0, 0, 0, 0, 1, 0, 0},
      {0, 1, 0, 1, 0, 1, 0, 0},
      {0, 1, 0, 1, 0, 1, 0, 0},
      {1, 0, 0, 0, 1, 0, 0, 1},
      {0, 1, 1, 0, 0, 1, 1, 1},
    };
    
    public static int countCells(int x, int y){
        if(x<0 || x>=N || y<0 || y>=N)
            return 0;
        else if (gird[x][y] != IMAGE_COLOR)
            return 0;
        else {
            gird[x][y] = ALREADY_COUNTED;
            return 1 + countCells(x, y + 1) + countCells(x + 1, y + 1)
	          + countCells(x + 1, y) + countCells(x + 1, y - 1)
    	      + countCells(x, y - 1) + countCells(x - 1, y - 1)
        	  + countCells(x - 1, y) + countCells(x - 1, y + 1);
        }
    }
    
    public static vboid main(String[] args){
        printGrid();
        int blobCount = countCells(3, 5);
        System.out.println();
        System.out.println("blobCount : " + blobCount);
        printGrid();
    }
    
    private static void printGrid(){
        for(int x=0; x<N; x++){
            System.out.print("[]");
            for(int y=0; y<N; y++){
                System.out.print(gird[x][y] + ", ");
            }
            System.out.println("]");
        }
    }
}
```

```java
// 실행결과
[1, 0, 0, 0, 0, 0, 0, 1, ]
[0, 1, 1, 0, 0, 1, 0, 0, ]
[1, 1, 0, 0, 1, 0, 1, 0, ]
[0, 0, 0, 0, 0, 1, 0, 0, ]
[0, 1, 0, 1, 0, 1, 0, 0, ]
[0, 1, 0, 1, 0, 1, 0, 0, ]
[1, 0, 0, 0, 1, 0, 0, 1, ]
[0, 1, 1, 0, 0, 1, 1, 1, ]

BlobCount : 13
[1, 0, 0, 0, 0, 0, 0, 1, ]
[0, 1, 1, 0, 0, 2, 0, 0, ]
[1, 1, 0, 0, 2, 0, 2, 0, ]
[0, 0, 0, 0, 0, 2, 0, 0, ]
[0, 1, 0, 2, 0, 2, 0, 0, ]
[0, 1, 0, 2, 0, 2, 0, 0, ]
[1, 0, 0, 0, 2, 0, 0, 2, ]
[0, 1, 1, 0, 0, 2, 2, 2, ]
```

# **> N-Queens (Back tracking) (n = 8)**

---

- 아래의 예(n=8)에서는 `8x8` 체스보드에 8개의 말을 놓는데
- 그 중에 어떤 말들도 동일한 행, 동일한 열, 동일한 대각선 상에 오지 않도록
- n개의 말을 놓을 수 있는가에 대한 문제이다.
- 위의 방법대로 n개의 말을 놓으려면, 결국 조건을 만족하면서 하나의 행에 하나의 말이 와야한다.

![image-20200827172717888](https://user-images.githubusercontent.com/58545240/91420100-252e9a80-e88f-11ea-96e5-737db363bdde.png)

## Back Tracking

- 내가 지나온 길을 다시 되돌아 가면서 문제를 해결한다
- 어떠한 결정들을 내려가다가, 결정이 막다른 길이다. 즉, 그 결정을 내려서는 안된다라는 것이 분명해지면, 가장 최근에 내렸던 결정을 번복한다.
- 이런식으로 문제를 해결한다.

## 상태 공간트리

- 1번말이 `(1,1)`에 위치했을 때, 그리고 그 때 2번말이 `(2,1), (2,2), (2,3), (2,4)`에 위치했을 때, ....를 나타내는 트리이다.
- 실제로 완성된 상태공간트리를 그린다면, `4x4`의 체스보드에 n개의 말을 놓을 수 있는 모든 경우의 수를 나열하는 그림이 된다.

![image-20200827172931239](https://user-images.githubusercontent.com/58545240/91420116-2a8be500-e88f-11ea-9ff1-a88c741e4f46.png)

- **상태공간트리**란 찾는 해를 반드시 포함하는 트리이다.
- 즉, 해가 존재한다면 그것은 반드시 이 트리의 어떤 한 노드에 해당한다.
- 따라서, 이 트리를 체계적으로 탐색하면 해를 구할 수 있다.

### 상태공간트리의 모든 노드를 탐색하는 것은 아니다

- 상태공간트리를 탐색한다는 것은, 실제로 모든 트리를 탐색한다는 것이 아니다
- 그리고 실제로 트리를 그리거나, datastructure로 만든다는 뜻이 아니다.
- 상태공간트리는 하나의 개념적인, 논리적인 것이다.
- 이런 트리를 개념적으로 염두해두고, 실제로는 이 트리의 노드를 탐색하는 코드를 만들면 된다.

## Back Tracking

- 상태공간트리를 깊이 우선 방식으로 탐색하여 해를 찾는 알고리즘을 말한다.
- 알고리즘을 구현하는 방법은 `recursion`으로 구현하는 방법과 `stack`으로 구현하는 방법이 있다.
- `recursion`을 이용하여 구현하는 것이 쉽고, 간명하기 때문에 대부분의 경우 `recursion`으로 `backtracking` 알고리즘을 구현한다.

## Design Recursion

- arguments(매개변수)는 내가 현재 트리의 어떤 노드에 있는지를 지정해야 한다.
  - 즉, 상태공간트리에서 어떤 노드에 도착했다.를 나타내야 한다.
- 그리고 이 함수에서 수행할 코드는 어떤 노드에 도착했을 때, 그 이후에 행해져야할 작업들이다.
  1. `if` -> 가장 먼저 이 노드가 `non-promising` 또는 `infeasible`한 노드인지를 판단한다. 즉, 해당 노드의 아래 깊이를 더 탐색해볼 가치가 있는 노드인가를 판단한다. 아니라면 되돌아간다.
  2. `else if` -> 그렇지 않다면, 어떤 노드에 도착했는데 답을 찾은 경우라면 답을 출력하고 return 한다.
  3. `else` -> 그렇지 않다면, 그 노드가 실패도 아니고 답도 아니라면, 해야 할 일은 해당 노드의 자식들을 순서대로 방문해보는 것이다. `recursive`하게
- `Backtracking`을 구현하는 대부분의 함수들은 기본적으로 이런 구조를 가진다.

```java
return-type queens(arguments){
    if non-promising
        report failure and return;
    else if success
        report answer and return;
    else
        visit childeren recursively;
}
```

- 매개변수와 전역변수를 유지함으로써 현재 어떤 노드에 위치하고 있는지를 나타낸다.
  - 매개변수 `level`은 현재노드의 `level`을 표현하고, i번에서 `level`번째 말이 어디에 놓였는지는 전역변수 배열 `cols`로 표현한다. `cols[i]=j`는 i번말이 (i행, j열)에 놓였음을 의미한다.
- return type은 boolean으로 성공이냐 실패냐를 반환한다.
- non-promising 여부를 판단하는 함수를 만들었다고 가정하고, 검증 후 false를 리턴한다.

```java
int[] cols = new int [N+1];
boolean queens(int level){
    if(!promising(level))
        return false;
    else if success
        report answer and return;
    else
        visit children recursively;
}
```

- **promising 검증을 통과했다는 가정**(이말은 현재까지 놓인 말들이 충돌없이 잘 놓여졌다는 의미)하에 `level==N`이면 모든 말이 놓였다는 의미이고 따라서 성공이다.

```java
int[] cols = new int [N+1];
boolean queens(int level){
    if(!promising(level))
        return false;
    else if (level==N)
        return true;
    else
        visit children recursively;
}
```

- 마지막으로 자식노드들을 `recursive`하게 방문하는 코드이다.
  - 이 때는, 1번말 부터 level번째 말까지 잘 놓였다는 의미이고 따라서 `level+1`번째 말을 1열과 N열 사이에서 어느 곳에 놓아야 하는 가에 대한 고민을 해야한다. N가지 선택 가능한 경로가 있다.
  - `level+1`번째 말을 각각의 열에 놓은 후 `recursion`을 호출한다.
  - 코드에서 보면 `level+1`번의 말을 i에 놓아보고 `queens(level+1)`을 호출한다. 이것이 true라면 true를 리턴하고 실패라면 다시 돌아가서 `i+1`번째 열에 놓고 다시 검증한다.

```java
int[] cols = new int[N+1];
boolean queens(int level){
    if(!promising(level))
        return false;
    else if (level==N)
        return true;
    for(int i=1; i<=N; i++){
        cols[level+1] = i;
        if(queens(level+1)) return true;
    }
    return false;
}
```

## Promising Test를 어떻게 할 것인가

- 위 코드에서 보면 `queens()`메소드를 호출할 때마다 promising 여부를 검증하고 있다. 따라서 `cols[]`의 1,2,3에 해당하는 말들 간에는 충돌이 없다고 보장되어 있다.
- 따라서 마지막에 놓인(`cols[4]`) 이 말이 이전에 놓인 다른 말들과 충돌하는지 검사하는 것으로 충분하다.

![image-20200827174511493](https://user-images.githubusercontent.com/58545240/91422989-e0a4fe00-e892-11ea-8373-90c7032ff5b3.png)

- 코드는 다음과 같은 방식으로 작성한다.
  - 같은 열에 놓였는지,
    - `cols[i] == cols[level]`
  - 동일 대각선상에 놓였는지를 검사한다.
    - `(level-i) == Math.abs(cols[level]-cols[i])`

```java
boolean promising(int level){
    for (int i=1; i<level; i++){
        if(cols[i] == cols[level]) return false;
        else if((level-i) == Math.abs(cols[level] - cols[i])) return false;
    }
    return true;
}
```

## 구현

- 말이 놓인 위치 출력
- 첫 호출은 `queens[0]`

```java
public class NQueensProblem{
    private final static int N=8;
    private static int[] cols = new int[N + 1];
    
    public static void main(String[] args){
        queens[0];
    }
    
    private static boolean queens(int level){
        if(!promising(level)) return false;
        else if(level == N){
            for(int i=1; i<=N; i++){
                System.out.println("(" + i + ", " + cols[i] + ")");
            }
            return true;
        }
        for(int i=1; i<=N; i++){
            cols[level+1] = i;
            if(queens(level+1)) return true;
        }
        return false;
    }
    
    private static boolean promising(int level){
        for(int i=1; i<level; i++){
            if(cols[i] == cols[level]) return false;
            else if((level-i) == Math.abs(cols[level] - cols[i])) return false;
        }
        return true;
    }
}
```

```bash
# 실행결과
(1, 1)
(2, 5)
(3, 8)
(4, 6)
(5, 3)
(6, 7)
(7, 2)
(8, 4)
```

# **> 멱집합 (Recursion 응용)**

---

> PowerSet

- 어떤 집합의 모든 부분집합을 멱집합이라고 부른다.
- 임의의 집합 `data = {a, b, c, d}`의 모든 부분집합은
  - 2^n =16개이다.

## Recursion을 이용하여 모든 부분집합 나열

- `{a, b, c, d, e, f}`의 모든 부분집하을 나열하려면

  - 먼저 a를 포함하지 않는 부분집합과

  - a를 포함하는 부분집합으로 나눌수 있다.

    **따라서 아래와 같이 표현할 수 있다.**

    - a를 포함하지 않는 부분집합
      - a를 제외한 `{b, c, d, e, f}`의 모든 부분집합들을 나열하고
    - a를 포함하는 부분집합
      - **`{b, c, d, e, f}`의 모든 부분집합에 `{a}`를 추가한 집합들을 나열한다.**

    => 이 두가지를 합치면 경우의 수가 `2^n`개가 되는 것이다.

- 그렇다면, `{b, c, d, e, f}`의 모든 부분집합에 `{a}`를 추가한 집합들을 나열하려면

  - 다시 한번 b를 포함하지 않는 부분집합과

  - b를 포함하는 부분집합으로 나누어 생각한다.

    **따라서,**

    - b를 포함하지 않는 부분집합
      - `{c, d, e, f}`의 모든 부분집합들에 `{a}`를 추가한 집합들을 나열하고
    - b를 포함하는 부분집합
      - `{c, d, e, f}`의 모든 부분집합에 `{a, b}`를 추가한 집합들을 나열한다.

- 다시, `{c, d, e, f}`의 모든 부분 집합에 `{a}를 추가한 집합들을  나열하려면

  - `{d, e, f}`의 모든 부분집합들에 `{a}`를 추가한 집합들을 나열하고
  - `{d, e, f}`의 모든 부분집합에 `{a, c}`를 추가한 집합들을 나열한다.

## Recursion으로 표현

- S의 멱집합을 출력하라
  - 아래와 같이 S에서 t를 뺀 집합의 모든 부분집하을 구하고 return을 구하면
  - 모든 부분집합을 저장해야하기 때문에, 모든 부분집합을 출력하는 문제를 해결하는데에는 적합하지 않을 수 있다.

```java
powerSet(S)
    if S is an emtpy set
        print nothing;
	else
        let t be the first element of s;
        find all subsets of S-{t} by calling powerSet(S-{t});
        return all of them;
```

- 따라서 아래와 같이 변경한다
  - else문에서 보면 S에서 t를 뺀 모든 부분집합을 구하고 print해주는 부분이 있다.
  - 하지만, 이렇게 변경을 해도 모든 부분집합에 t를 추가한 집합들을 나타내는 subsets with adding t를 해결할 수 없다. 코드에서 모둔 부분집합을 저장하지 않았기 떄문에

```java
powerSet(S)
    if S is an empty set
        print nothing;
	else
        let t be the first element of s;
        find all subsets of S-{t} by calling powerSet(S-{t});
        print the subset;
        print the subsets with adding t;
```

## recursion의 설계 자체를 수정해야 한다.

- 위의 recursion 구현에서 결과적으로 모든 부분집합에 t를 추가한 집합들을 나타내는 작업을 수행하는데서 문제가 있었다. 아래의 단계이다.
- {b, c, d, e, f}의 모든 부분집합에 {a}를 추가한 집합들을 나열한다.
  - b를 포함하지 않는 부분집합
    - {c, d, e, f}의 모든 부분집합들에 {a}를 추가한 집합들을 나열하고
  - b를 포함하는 부분집합
    - **{c, d, e, f}의 모든 부분집합에 {a, b}를 추가한 집합들을 나열한다.**
- 위의 단계중에 **{c, d, e, f}의 모든 부분집합에 {a, b}를 추가한 집합들을 나열**하려면,
  - 어떤 집합의 모든 부분집합을 구해서, 그 결과에 또 다른 집합을 추가해서 출력하는 일을 할 수 있도록 설계가 바뀌어야 한다.
- Mission : S의 멱집합을 구한 후 각각에 집합 P를 합집합하여 출력하라
  - S : {c, d, e, f} - k번째 부터 마지막 원소까지 연속된 원소들이다.
  - P : {a, b} - 처음부터 k-1번째 원소들 중 일부이다.
  - recursion 함수가 두 개의 집합을 매개변수로 받도록 설계해야 한다는 의미이다. 두 번째 집합의 모든 부분집합들에 첫번째 집합을 합집합하여 출력한다.
  - 맨 처음 초기 호출은 powerSet(null, S)으로 하면 된다. S의 멱집합을 구하기 위한 호출이다.

```java
powerSet(P, S)
    if S is an emtpy set
        print P;
	else
        let t be the first element of S
        powerSet(P, S-{t});
        powerSet(P 합집합 {t}, S-{t});
```

- S와 P를
  - **S: k번째부터 마지막 원소까지 연속된 원소들이다.**
  - **P: 처음부터 k-1번째 원소들 중 일부이다.**
  - 아래와 같이 S와 P를 k라는 인덱스와 P라는 boolean 배열을 이용해서 나타낸다.

![image-20200828111302760](https://user-images.githubusercontent.com/58545240/91522876-3aaacf80-e936-11ea-837a-5ea58d2a9cdb.png)

## 구현

***Mission* : data[k], … , data[n-1]의 멱집합을 구한 후 각각에 include[i]=true, i=0,…,k-1, 인 원소를 추가하여 출력하라.**

- 처음 함수 호출은 powerSet(0)으로 호출한다. 즉, P는 공집합 S는 전체집합.

- data[] 배열 중에서 인덱스 k부터 마지막까지 집합 S이고,

- data[] 배열 중에서 인덱스 0부터 k-1까지의 집합중 true인 값을 가지고 있는 것 들이 집합 P이다. 그리고 이것은 include로 표현한다.

- Base case는 집합 S가 공집합일 경우이다. 그 경우에는 그냥 P를 출력하면 된다. data중에 include에서 유지하고 있는 값이 true인 요소들.

- 일반적인 경우에는

  - data[k]를 포함하지 않는 경우

    - data[k]가 'a'라고 할 때, include[k]를 false로 놓고 powerSet의 매개변수를 'b'부터 끝원소까지의 집합으로 재귀호출한다.

    ```
    include[k] = false;
    powerSet(k + 1);
    ```

  - data[k]를 포함하는 경우

    - data[k]가 'a'라고 할 때, include[k]를 true로 놓고 powerSet의 매개변수를 'b'부터 끝원소까지의 집합으로 재귀호출한다.

    ```
    include[k] = true;
    powerSet(k + 1);
    ```

```java
private static char data[] = {'a','b','c','d','e','f'};
private static int n = data.length;
private static boolean[] include = new boolean[n];

public static void powerSet(int k) {
  if (k == n) {
    for (int i = 0; i < n; i++)
      if (include[i])
        System.out.print(data[i] + " ");
    System.out.println();
    return;
  }
  include[k] = false;
  powerSet(k + 1);
  include[k] = true;
  powerSet(k + 1);
}
```

## 상태공간트리(state space tree)

- 원소가 {a, b, c}일 때, 구할 수 있는 모든 부분집합을 나타내는 트리이다.
- include와 exclude가 반대로 표기되어 있다.

![image-20200828111358334](https://user-images.githubusercontent.com/58545240/91522910-4c8c7280-e936-11ea-8725-5ce7c5e6da1d.png)

- 해를 찾기 위해 탐색할 필요가 있는 모든 후보들을 포함하는 트리이다.
- 트리의 모든 노드들을 방문하면 해를 찾을 수 있다.
- 루트에서 출발하여 체계적으로 모든 노드를 방문하는 절차를 기술한다.
- 즉, 위의 코드를 상태공간트리의 관점에서 보면

```java
private static char data[] = {'a', 'b', 'c', 'd', 'e', 'f'};
private static int n = data.length;
private boolean[] include = new boolean[n];	// 트리상에서 현재 위치를 표현

public static void powerSet(int k){	// 트리상에서 현재 위치를 표현한다
    if(k==n){	// 현재 위치가 리프 노드이면 출력하고 끝낸다.
        for(int i=0; i<N; i++){
            if(include[i]){
                System.out.print(data[i] + " ");
            }           
        }
        System.out.println();
        return;
    }
    
    // 그렇지 않고, 리프노드가 아닌 트리의 어디엔가 위치 한다면
    include[k] = false;		// 트리의 왼쪽노드 방문
    powerSet(k + 1);
    include[k] = true;		// 트리의 오른쪽노드 방문
    powerSet(k + 1);
}
```

- 상태공간트리는 이런 방식의 `Recursion`을 이해하는 데 도움을 주는 매우 강력한 도구이다.

# **> 멱집합2 ( 그 외 방법 )**

---

조합을 이용할 수 있다.

`n`개 중에서 `r`개를 순서 없이 뽑는 조합에서 `r`에 대한 `for`문을 돌리면 구할 수 있다.

하지만 단순히 부분집합을 확인하기 위한 용도라면 훨씬 빠르고 효율적인 코드가 있다.

## 재귀

조합을 구할때와 비슷하다

조합에서는 길이가 `r`일 때를 구하기 위해 여러가지 조건을 걸었지만 부분집합에서는 필요없다.

1. 현재 인덱스를 포함하는 경우
2. 현재 인덱스를 포함하지 않는 경우

두 가지로 경우에 대해 모두 확인한 후에 현재 인덱스가 `n`이 되면 출력

```java
static void powerSet(int[] arr, boolean[] visited, int n, int idx){
    if(idx==n){
        print(arr, visited, n);
        return;
    }
    
    visited[idx] = false;
    powerSet(arr, visited, n, idx+1);
    
    visited[idx] = true;
    powerSet(arr, visited, n, idx+1);
}
```

```bash
# 실행결과
3
2
2 3
1
1 3
1 2 
1 2 3
```

## 비트

`n`이 3이라고 할 때 `1<<n`은 `1000(2)`이다.

첫번 째 `for`문에서 `i`는 `1<<n` 전까지 증가하니까 `111(2)` 까지 증가한다.

즉 `i`는

```bash
000
001
010
010
100
101
110
111
```

이렇게 증가한다.

`j`를 통해서

```bash
001
010
100
```

위 숫자들과 `AND`연산을 통해 1인 비트들을 인덱스처럼 사용가능하다.

```java
static void bit(int[] arr, int n){
    for(int i=0; i< 1<<n; i++){
        for(int j=0; jMn; j++){
            if((i & 1<<j)!=0)
                System.out.print(arr[j] + " ");
        }
        System.out.println();
    }
}
```

```bash
# 실행결과
1
2
1 2
3
1 3
2 3 
1 2 3
```

# **> 조합**

---

조합이란 `n`개의 숫자 중에서 `r`개의 수를 순서 없이 뽑는 경우이다.

예를 들어 `[1, 2 ,3]`이란 숫자 배열에서 2개의 수를 순서 없이 뽑으면

```bash
1 2
1 3
2 3
```

순열을 뽑았을 때 나오는 `[2, 1], [3, 1], [3, 2]`는 중복이라 제거한다.

여러가지 방법이 있지만 핵심은 하나이다.

배열을 처음부터 마지막까지 돌며

1. 현재 인덱스를 선택하는 경우
2. 현재 인덱스를 선택하지 않는 경우

두가지로 모든 경우를 완전탐색 하면된다.

| 변수     | 설명                          |
| -------- | ----------------------------- |
| `arr`    | 조합을 뽑아낼 배열            |
| `output` | 조합에 뽑혔는지 체크하는 배열 |
| `n`      | 배열의 길이                   |
| `r`      | 조합의 길이                   |

순열과 달리 조합은 `r`을 유지할 필요가 없으므로 숫자를 하나 뽑을때마다 `r`을 하나씩 줄여준다.

`r==0`일 때가 `r`개의 숫자를 뽑은 경우이다.

```java
// 조합 : n개 중에서 r 개 선택
public class Combination{
    public static void main(String[] args){
        int n = 4;
        int[] arr = {1, 2, 3, 4};
        boolean[] visited = new boolean[n];
        
        for(int i=1; i<=n; i++){
            System.out.println("\n" + n + "개 중에서 " + i + " 개 뽑기");
            comb(arr, visited, 0, n, i);
        }
        
        for(int i=1; i<=n; i++){
            System.out.println("\n" + n + "개 중에서 " + i + "개 뽑기");
            combination(arr, visited, 0, n, i);
        }
    }
    
    // 백트래킹 사용
    // 사용 예시 : combination(arr, visited, 0, n, r);
    static void combination(int[] arr, boolean[] visited, int start, int n, int r){
        if(r==0){
            print(arr, visited, n);
            return;
        }
        
        for(int i=start; i<n; i++){
            visited[i] = true;
            combination(arr, visited, i+1, n, r-1);
            visited[i] = false;
        }
    }
    
    // 재귀 사용
    // 사용 예시 : comb(arr, visited, 0, n, r);
    static void comb(int[] arr, boolean[] visited, int depth, int n, int r){
        if(r==0){
            print(arr, visited, n);
            return;
        }
        
        if(depth==n){
            return;
        }
        
        visited[depth] = true;
        comb(arr, visited, depth+1, n, r-1);
        
        visited[depth] = false;
        comb(arr, visited, depth+1, n, r);
    }
}
```



