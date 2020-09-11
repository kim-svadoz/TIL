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
