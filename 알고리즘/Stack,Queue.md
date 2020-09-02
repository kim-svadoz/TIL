# Stack & Queue :fist_oncoming:

# **> 스택(STACK)이란?**

---

## 스택의 개념

![image-20200902102128158](https://user-images.githubusercontent.com/58545240/91925086-3fdd9500-ed0f-11ea-8350-16423aeedd3c.png)

스택(stack)이란 **쌓아 올린다는 것**을 의미한다.

따라서 스택 자료구조라는 것은 책을 쌓는 것처럼 차곡차곡 쌓아 올린 형태의 자료구조를 말한다.

## 스택의 특징

스택은 위의 사진처럼 **같은 구조와 크기의 자료**를 **정해진 방향으로만** 쌓을 수 있고, **top으로 정한 곳을 통해서만 접근**할 수 있다.

top에는 가장 위에있는 자료는 가장 최근에 들어온 자료를 가리키고 있으며, 삽입되는 새 자료는 top이 가리키는 자료의 위에 쌓이게 된다.

스택에서 자료를 삭제할 때도 `top`을 통해서만 가능하다.

스택에서 `top`을 통해 **삽입하는 연산**을 **`push`**, `top`을 통한 **삭제하는 연산**을 **`pop`**이라고 한다.

따라서 스택은 시간 순서에 따라 자료가 쌓여서 가장 **마지막에 삽입된 자료가 가장 먼저 삭제된다**는 구조적 특징을 가지게 된다.

이러한 스택의 구조를 **후입 선출(LIFO, Last-In-First-Out) 구조**라고 한다.

비어있는 스택에서 원소를 추출하려고 할 때는 `stack underflow`라고 하며, 스택이 넘치는 경우에는 `stack overflow`라고 한다.(~~그 유명한 사이트 ***"stack overflow"***의 이름이 여기서 유래된 것이다.~~)

## 스택의 활용 예시

스탭의 특징인 후입선출(LIFO)를 활용하여 여러 분야에서 활용 가능하다.

- 웹 브라우저 방문기록(뒤로가기) : 가장 나중에 열린 페이지부터 다시 보여준다.
- 역순 문자열 만들기 : 가장 나중에 입력된 문자부터 출력한다.
- 실행 취소(undo) : 가장 나중에 실행된 것부터 실행을 취소한다.
- 후위 표기법 계산
- 수식의 괄호 검사(연산자 우선순위 표현을 위한 괄호 검사)

## Stack Class

Stack 클래스는 List 컬렉션 클래스의 Vector클래스를 상속받아, 전형적인 스택 메모리 구조의 클래스를 제공한다.

스택메모리 구조는 선형 메모리 공간에 데이터를 저장하면서 후입선출(LIFO)의 시멘틱을 따르는 자료구조이다.

![image-20200902102824134](https://user-images.githubusercontent.com/58545240/91925095-44a24900-ed0f-11ea-97b6-2b269b0729ed.png)

`Stack` 클래스는 스택 메모리 구조를 표현하기 위해, `Vector` 클래스의 메소드를 5개만 상속받아 사용한다.

| 메소드                     | 설명                                                         |
| -------------------------- | ------------------------------------------------------------ |
| **`boolean empty()`**      | 해당 스택이 비어 있으면 true를, 비어 있지 않으면 false 반환  |
| **`E peek()`**             | 해당 스택의 제일 상단에 있는(제일 마지막으로 저장된) 요소를 반환 |
| **`E pop()`**              | 해당 스택의 제일 상단에 있는(제일 마지막으로 저장된) 요소를 반환하고, 요소를 스택에서 제거 |
| **`E push(E item)`**       | 해당 스택의 제일 상단에 전달된 요소를 삽입함                 |
| **`int search(Object O)`** | 해당 스택에서 전달된 객체가 존재하는 위치의 인덱스를 반환<br />이 때 인덱스는 제일 상단에 있는(제일 마지막으로 저장된) 요소의 위치부터 0이 아닌 1부터 시작함 |



*더욱 복잡하고 빠른 스택을 구현하고 싶다면 `Deque` 인터페이스를 구현한 `ArrayDeque`클래스를 사용하면 된다.*

```java
Deque<Integer> st = new ArrayDeque<Integer>();
```



### - 구현

```java
import java.util.*;
public class Stack{
    
    public static void main(String[] args){
        Stack<Integer> st = new Stack<Integer>();		// 스택의 생성
        //Deque<Integer> st = new ArrayDeque<Integer>();

        // push() 메소드를 이용한 요소의 저장
        st.push(4);
        st.push(2);
        st.push(3);
        st.push(1);

        // peek() 메소드를 이용한 요소의 반환
        System.out.println(st.peek());
        System.out.println(st);

        // pop() 메소드를 이용한 요소의 반환 및 제거
        System.out.println(st.pop());
        System.out.println(st);

        // search() 메소드를 이용한 요소의 위치 검색
        System.out.println(st.search(4));
        System.out.println(st.search(3));
	}
}

```

```bash
# 실행결과
1
[4, 2, 3, 1]
1
[4, 2, 3]
3
1
```



# **> 큐(QUEUE)란?**

---

## 큐의 개념

![image-20200902103401510](https://user-images.githubusercontent.com/58545240/91925103-49ff9380-ed0f-11ea-84f9-94cfcb860f5d.png)

`Queue`의 사전적의미는 1.(무엇을 기다리는 사람, 자동차 등의) **줄**, 혹은 **줄을 서서 기다리는 것**을 의미한다.

따라서 일상생활에서 놀이동산에서 줄을 서서 기다리는 것, 은행에서 먼저 온 살마의 업무를 창구에서 처리하는 것과 같이 **선입선출(FIFO, First in First out) 방식**의 자료구조를 말한다.

## 큐의 특징

정해진 한 곳(top)을 통해서 삽입, 삭제가 이루어지는 스택과는 달리 큐는 한쪽 끝에서 삽입 작업이, 다른 쪽 끝에서 삭제 작업이 양쪽으로 이루어진다.

이 때 **삭제연산만 수행되는 곳을 프론트(front), 삽입연산만 이루어지는 곳을 리어(rear)**로 정하여 각각의 연산작업만 수행된다.

이 때, 큐의 리어에서 이루어지는 삽입연산을 **인큐(enQueue)**, 프론트에서 이루어지는 삭제연산을 **디큐(deQueue)**라고 부른다.

- 큐의 가장 첫 원소를 `front` / 가장 끝 원소를 `rear`
- 큐는 들어올 때 `rear`로 들어오지만 나올 때는 `front`부터 빠지는 특성
- **접근 방법은 가장 첫 원소와 끝 원소로만 가능**
- 가장 먼저 들어온 프론트 원소가 가장 먼저 삭제

즉, 큐에서 프론트 원소는 가장 먼저 큐에 들어왔떤 첫 번째 원소가 되는 것이며, 리어 원소는 가장 늦게 큐에 들어온 마지막 원소가 되는 것이다.

## 큐의 활용 예시

큐는 주로 데이터가 입력된 시간 순서대로 처리해야 할 필요가 있는 상황에 이용한다.

- 우선순위가 같은 작업 예약 (프린터의 인쇄 대기열)
- 은행 업무
- 콜센터 고객 대기시간
- 프로세스 관리
- 너비 우선 탐색(BFS) 구현
- 캐시(Cache) 구현

## Queue Interface

클래스로 구현된 스택과는 달리 자바에서 큐 메모리 구조는 별도의 인터페이스 형태로 제공된다.

이러한 `Queue` 인터페이스를 상속받는 하위 인터페이스는 다음과 같다.

1. `Deque<E>`
2. `BlockingDeque<E>`
3. `BlockingQueue<E>`
4. `TransferQeue<E>`

따라서 Queue 인터페이스를 직간접적으로 구현한 클래스는 상당히 많다. 그중에서도 `Dequeue` 인터페이스를 구현한 **`LinkedList`** 클래스가 큐 메모리 구조를 구현하는 데 가장 많이 사용된다.

큐 메모리 구조는 선형 메모리 공간에 데이터를 저장하면서 선입선출(FIFO)의 시멘틱을 따르는 자료구조이다.

즉, 가장 먼저 저장된(`push`) 데이터가 가장먼저 인출(`pop`)되는 구조이다.

![image-20200902105443415](https://user-images.githubusercontent.com/58545240/91925109-4d931a80-ed0f-11ea-9482-69625480cc1f.png)

Queue 인터페이스는 큐 메모리 구조를 표현하기 위해, 다음과 같은 Collection 인터페이스 메소드만을 상속받아 사용한다.

| 메소드                   | 설명                                                         |
| ------------------------ | ------------------------------------------------------------ |
| **`boolean add(E e)`**   | 해당 큐읜 맨 뒤에 전달된 요소를 삽입.<br />삽입에 성공하면 true , 큐에 여유공간이 없어 삽입에 실패하면 `illegalStateException`을 발생시킴 |
| **`E element()`**        | 해당 큐의 맨 앞에 있는(제일 먼저 저장된) 요소 반환           |
| **`boolean offer(E e)`** | 해당 큐의 맨 뒤에, 전달된 요소를 삽입                        |
| **`E peek()`**           | 해당 큐의 맨 앞에 있는(제일 먼저 저장된) 요소를 반환<br />만약 큐가 비어 있으면 null 반환 |
| **`E poll()`**           | 해당 큐의 맨 앞에 있는(제일 먼저 저장된) 요소를 반환하고 해당 요소를 큐에서 제거<br />만약 큐가 비어 있으면 null 반환 |
| **`E remove()`**         | 해당 큐의 맨 앞에 있는(제일 먼저 저장된) 요소를 제거         |



*더욱 복잡하고 빠른 큐를 구현하고 싶다면 `Deque` 인터페이스를 구현한 `ArrayDeque`클래스를 사용하면 된다.*

```java
Deque<Integer> q = new ArrayDeque<Integer>();
```



### -구현

```java
import java.util.*;
public class Queue{
    
    public static void main(String[] args){
        LinkedList<String> q = new LinkedList<String>()	// 큐의 생성
        //Deque<String> q = new ArrayDeque<String>();

        // add() 메소드를 이용한 요소의 저장
        q.add("넷");
        q.add("둘");
        q.add("셋");
        q.add("하나");

        // peek() 메소드를 이용한 요소의 반환
        System.out.println(q.peek());
        System.out.println(q);

        // poll() 메소드를 이용한 요소의 반환 및 제거
        System.out.println(q.poll());
        System.out.println(q);

        // remove() 메소드를 이용한 요소의 제거
        q.remove("하나");
        System.out.println(q);
	}
}
```

```bash
# 실행결과
넷
[넷, 둘 셋, 하나]
넷
[둘, 셋, 하나]
[둘, 셋]
```



