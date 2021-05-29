# **Java**

>   참고 : https://github.com/gyoogle/tech-interview-for-developer
>
>   전반적인 흐름은 gyoogle님의 깃허브를 참고하였으며, 추가 레퍼런스는 글을 진행하며 알려드릴 것입니다. 파이팅 !

# 자바 컴파일과정

---

>   자바는 OS에 독립적인 특징을 가지고 있다.
>
>   그게 가능한 이유는 JVM(Java Virtual Machine) 덕분인데, 그렇다면 JVM의 어떠한 기능 때문에, OS에 독립적으로 실행시킬 수 있는지 자바 컴파일 과정으로 알아보자.

![image-20210525210710504](https://user-images.githubusercontent.com/58545240/119519685-96ff2a80-bdb4-11eb-8db1-54db97c59bad.png)

![image-20210525210932332](https://user-images.githubusercontent.com/58545240/119519690-99618480-bdb4-11eb-84f0-8caa324f35ac.png)

## 자바 컴파일 순서

1.  개발자가 자바 소스 코드(`.java`)를 작성합니다.
2.  자바 컴파일러(Java Compiler)가 자바 소스파일을 컴파일한다.
    1.  이 때 나오는 파일은 자바 바이트 코드(`.class`) 파일로 아직 컴퓨터가 읽을 수 없는 자바 가상 머신이 이해할 수 있는 코드입니다.
    2.  바이트 코드의 각 명령어는 1바이트 크기의 `Opcode`와 추가 피연산자로 이루어져 있습니다.
3.  컴파일된 바이트 코드를 `JVM`의 **클래스 로더(Class Loader)**에게 전달합니다.
4.  클래스 로더는 동적로딩(Dynamic Loading)으로 필요한 클래스들을 로딩 및 링크하여 런타임 데이터 영역(Runtime Data Area), 즉 `JVM`의 메모리에 올립니다.
    -   클래스 로더 세부 동작
        -   `로드` : 클래스 파일을 가져와서 JVM의 메모리에 로드합니다.
        -   `검증` : 자바 언어 명세(Java Language Specification) 및 `JVM` 명세에 명시된 대로 구성되어 있는지 검증합니다.
        -   준비 : 클래스가 필요로 하는 메모리를 할당합니다. (필드, 메서드, 인터페이스 등등)
        -   분석 : 클래스의 상수 풀 내 모든 심볼릭 레퍼런스를 다이렉트 레퍼런스로 변경합니다.
        -   초기화 : 클래스 변수들을 적절한 값으로 초기화 합니다.(**static field**)
5.  실행엔진(Execution Engine)은 `JVM` 메모리에 올라온 바이트 코드들을 명령어 단위로 하나씩 가져와서 실행합니다. 이 때, 실행 엔진은 두 가지 방식으로 변경합니다.
    -   **인터프리터**
        -   바이트 코드 명령어를 하나씩 읽어서 해석하고 실행합니다.
        -   하나하나의 실행은 빠르나, 전체적인 실행 속도가 느리다는 단점을 가집니다.
    -   **JIT 컴파일러(Just-In-Time Compiler)**
        -   인터프리터의 단점을 보완하기 위해 도입된 방식으로 바이트 코드 전체를 컴파일하여 바이너리 코드로 변경하고 이후에는 해당 메서드를 더이상 인터프리팅 하지 않고, 바이너리 코드로 직접 실행하는 방식.
        -   하나씩 인터프리팅하여 실행하는 것이 아니라 바이트 코드 전체가 컴파일 된 바이너리 코드를 실행하는 것이기 때문에 전체적인 실행속도는 인터프리팅 방식보다 빠릅니다.

>   추가적으로 읽어보면 좋은 자료
>
>   [1] https://steady-snail.tistory.com/67
>
>   [2] https://aljjabaegi.tistory.com/387

# Call by value vs Call by reference

---

## call by value

>   값에 의한 호출

함수가 호출될 때, 메모리 공간 안에서는 함수를 위한 별도의 임시공간이 생성됩니다.(종료 해당 공간 사라짐)

`call by value` 호출 방식은 함수 호출 시 전달되는 변수 값을 복사해서 함수 인자로 전달합니다.

이 때 복사된 인자는 함수 안에서 지역적으로 사용되기 때문에 **local value** 속성을 가집니다.

따라서, 함수 안에서 인자 값이 변경되더라도, 외부 변수 값은 변경되지 않습니다.



**예시**

```c
void func(int n) {
    n = 20;
}
void main() {
    int n = 10;
    func(n);
    printf("%d", n);
}
```

```bash
10
```



## call by reference

>   참조에 의한 호출

call by reference 호출 방식은 함수 호출 시 인자로 전달되는 변수의 레퍼런스를 전달합니다.

따라서, 함수 안에서 인자 값이 변경되면 Argument로 전달된 객체의 값도 변경됩니다.

```c
void func(int *n) {
    *n = 20;
}
void main() {
    int n = 10;
    func(&n);
    printf("%d", n);
}
```

```bash
20
```

## Java의 함수 호출 방식

자바는 **항상 `call by value`**로 값을 넘깁니다.

`c/c++`와 같이 변수의 주소값 자체를 가져올 방법이 없으며, 이를 넘길 수 있는 방법 또한 없습니다.

`reference type(참조 자료형)`을 넘길 시에는 해당 객체의 주소값을 복사하여 이를 가지고 사용합니다.

따라서, **원본 객체의 Property**까지는 접근이 가능하나, 원본 객체 자체를 변경할 수는 없습니다.

```java
User a = new User("sunghyun"); // 1

foo(a);

public void foo(user b) {		// 2
    b = new User("kimchi");		// 3
}
```

-   **`1`** : a에 User 객체 생성 및 할당 (새로 생성된 객체의 주소값을 가지고 있다.)

    `a ---------------> User Object [name = "sunghyun"]`

-   **`2 `**: b라는 파라미터에 a가 가진 주소값을 복사하여 가짐

    `b ---------------> User Object [name = "sunghyuun"]`

-   **`3`** : 새로운 객체를 생성하고 새로 생성된 주소값을 b가 가지며 a는 그대로 원본 객체를 가리킨다.

    `a ---------------> User Object [name = "sunghyuun"]`

    `b ---------------> User Object [name = "kimchi"]`

파라미터에 객체/값의 주소값을 복사하여 넘겨주는 방식을 사용하고 있는 Java는 주소값을 넘겨 주소값에 저장되어 있는 값을 사용하는 **call by reference**라고 오해할 수 있는데요.

`c/c++`에서는 생성한 변수마다 새로운 메모리 공간을 할당하고 이에 값을 덮어 씌우는 형식으로 할당합니다. (`*` 포인터를 사용한다면, 같은 주소값을 가리킬 수 있도록 할 수 있다.)

`Java`에서 또한 생성한 변수마다 새로운 메모리 공간을 갖는 것은 마찬가지지만, 그 메모리 공간에 값 자체를 저장하는 것이 아니라 값을 다른 메모리 공간에 할당하고 이 주소값을 저장하는 것입니다.

이를 다음과 같이 나타낼 수 있습니다.

```shell
  C/C++        |        Java
               |
a -> [ 10 ]    |   a -> [ XXXX ]     [ 10 ] -> XXXX(위치)
b -> [ 10 ]    |   b -> [ XXXX ]
               |
             값 변경
a -> [ 11 ]    |   a -> [ YYYY ]     [ 10 ] -> XXXX(위치)
b -> [ 10 ]    |   b -> [ XXXX ]     [ 11 ] -> YYYY(위치)
```

`b = a;`일 때 a값을 b의 값으로 덮어 씌우는 것은 같지만, 실제 값을 저장하는 것과 값의 주소값을 저장하는 것의 차이가 존재합니다.

즉, Java에서의 변수는 `[할당된 값의 위치]`를 `[값]`으로 가지고 있는 것입니다.

`c/c++`에서는 주소값 자체를 인자로 넘겼을 때 값을 변경하면 새로운 값으로 덮어 쓰여 기존 값이 변경되고
`Java`에서는 주소값이 덮어 쓰여지므로 원본 값은 전혀 영향이 가지 않는 것입니다.(객체의 속성값에 접근하여 변경하는 것은 직접 접근하여 변경하는 것이므로 이를 가리키는 변수들에서 변경이 일어난다.)

```bash
객체 접근하여 속성값 변경

a : [ XXXX ]  [ Object [prop : ~ ] ] -> XXXX(위치)
b : [ XXXX ]

prop : ~ (이 또한 변수이므로 어딘가에 ~가 저장되어있고 prop는 이의 주소값을 가지고 있는 셈)
prop : [ YYYY ]    [ ~ ] -> YYYY(위치)

a.prop = * (a를 통해 prop를 변경) 

prop : [ ZZZZ ]    [ ~ ] -> YYYY(위치)
                   [ * ] -> ZZZZ

b -> Object에 접근 -> prop 접근 -> ZZZZ
```

위와 같은 이유로 Java에서 인자로 넘길  때는 주소값이란 값을 복사하여 넘기는 것이므로 **`call by value`**라 할 수 있다.

*( 출처 : [Is Java “pass-by-reference” or “pass-by-value”? - Stack Overflow](https://stackoverflow.com/questions/40480/is-java-pass-by-reference-or-pass-by-value?answertab=votes#tab-top) )*



## 정리

`Call by value`의 경우 데이터 값을 복사해서 함수로 전달하기 대문에 원본의 데이터가 변경될 가능성이 없다. 하지만 인자를 넘겨줄 때마다 메모리 공간을 할당해야 해서 메모리 공간을 더 잡아 먹습니다.

`Call by reference`의 경우 메모리 공간 할당 문제는 해결했지만, 원본 값이 변경될 수 있다는 위험이 존재합니다.

# String, StringBuffer, StringBuilder

---

| 분류   | String    | StringBuffer                    | StringBuilder        |
| ------ | --------- | ------------------------------- | -------------------- |
| 변경   | Immutable | Mutable                         | Mutable              |
| 동기화 |           | Synchronized 가능 (Thread-safe) | Synchronized 불가능. |

## String 특징

-   new 연산을 통해 생성된 인스턴스의 메모리 공간은 변하지 않습니다.(**`Immutable`**)
-   `Garbage Collector`로 제거되어야 합니다.
-   문자열 연산시 새로 객체를 만드는 Overhead가 발생합니다.
-   객체가 불변하므로, 멀티쓰레드에서 동기화를 신경 쓸 필요가 없습니다.(조회 연산에 매우 큰 장점!)

***String 클래스 : 문자열 연산이 적고, 조회가 많은 멀티쓰레드 환경에서 좋다***

## StringBuffer, StringBuilder 특징

-   공통점
    -   new 연산으로 클래스를 한 번만 만듭니다.(**`Mutable`**)
    -   문자열 연산 시 새로 객체를 만들지 않고, 크기를 변경시킵니다.
    -   StringBuffer와 StringBuilder 모두 동일한 메서드를 가지고 있습니다.
-   차이점
    -   StringBuffer는 `Thread-Safe`하고, StringBuilder는 `Thread-Safe`하지 않습니다.

***StringBuffer 클래스 : 문자열 연산이 많은 멀티쓰레드 환경***

***StringBuilder 클래스 : 문자열 연산이 많은 싱글쓰레드 혹은 Thread를 신경 안쓰는 환경***

# JVM

---

>   Java Virtual Machine
>
>   시스템 메모리를 관리하면서, 자바 기반 애플리케이션을 위해 이식 가능한 실행 환경을 제공

![image-20210525224802572](https://user-images.githubusercontent.com/58545240/119519699-9b2b4800-bdb4-11eb-8f7c-2192f135a984.png)

JVM은, 다른 프로그램을 실행시키는 것이 목적입니다.

크게 2가지 기능이 있습니다.

1.  자바 프로그램이 어느 기기나 운영체제 상에서도 실행될 수 있도록 하는 것
2.  프로그램 메모리를 관리하고 최적화 하는 것

```bash
JVM은 코드를 실행하고, 해당 코드에 대해 런타임 환경을 제공하는 프로그램에 대한 사양이다.
```



개발자들이 말하는 `JVM`은 보통 **어떤 기기상에서 실행되고 있는 프로세스, 특히 자바 앱에 대한 리소스를 대표하고 통제하는 서버**를 지칭합니다.

자바 애플리케이션을 클래스 로더를 통해 읽어들이고, 자바 API와 함께 실행하는 역할이며

`JAVA`와 `OS` 사이에서 중개자 역할을 수행하여 `OS`에 구애받지 않고 재사용을 가능하게 해줍니다.

## 메모리 관리

>   JVM 실행에 있어서 가장 일반적인 상호작용은, **힙과 스택의 메모리 사용을 확인**하는 것

**실행 과정**

1.  프로그램이 실행되면 `JVM`은 `OS`로부터 이 프로그램이 필요로하는 메모리를 할당받습니다.
    -   `JVM`은 이 메모리를 용도에 따라 여러 영역으로 나누어 관리합니다.
2.  자바 컴파일러(`JAVAC`)가 자바 소스코드를 읽고, 자바 바이트코드(`.class`)로 변환시킵니다.
3.  변경된 `.class `파일들을 클래스 로더를 통해 `JVM` 메모리 영역으로 로딩합니다.
4.  로딩된 `.class` 파일들은 `Execution Engine`을 통해 해석됩니다.
5.  해석된 바이트 코드는 메모리 영역에 배치되어 실질적인 수행이 이루어집니다.
    -   이러한 수행 과정 속 `JVM`은 필요에 따라 스레드 동기화나 가비지 컬렉션 같은 메모리 관리 작업을 수행합니다.

![image-20210525225248440](https://user-images.githubusercontent.com/58545240/119519703-9c5c7500-bdb4-11eb-8b8f-8d9735c62a35.png)



-   **`자바 컴파일러`**
    -   자바 소스코드(`.java`)를 바이트 코드(`.class`)로 변환시킨다.
-   **`클래스 로더`**
    -   JVM은 런타임시에 처음으로 클래스를 참조할 때 해당 클래스를 로드하고 메모리 영역에 배치시킵니다.
    -   이 동적 로딩(Dynamic Loading)을 담당하는 부분이 바로 클래스 로더
-   **`Runtime Data Area`**
    -   JVM이 운영체제 위에서 실행되면서 할당받는 메모리 영역
    -   총 5가지 영역으로 나뉘어 집니다
        -   `PC 레지스터`
            -   스레드가 어떤 명령어로 실행되어야 할지 기록하는 부분(JVM 명령의 주소를 가진다)
        -   JVM 스택
            -   지역변수, 매개변수, 메서드 정보, 임시 데이터 등을 저장
        -   네이티브 메서드 스택
            -   실제 실행할 수 있는 기계어로 작성된 프로그램을 실행시키는 영역
        -   힙
            -   런타임에 동적으로 할당되는 데이터가 저장되는 영역
            -   객체나 배열 생성이 여기에 해당
        -   메서드 영역
            -   JVM이 시작될 때 생성되고, JVM이 읽은 각각의 클래스와 인터페이스에 대한 런타임 상수 풀, 필드 및 메서드 코드, 정적 변수, 메서드의 바이트 코드 등을 보관
-   **`Garbage Collection`**
    -   자바 이전에는 프로그래머가 모든 프로그램 메모리를 관리했지만 자바에서는 `JVM`이 프로그램 메모리를 관리한다.
    -   JVM은 가비지 컬렉션이라는 프로세스를 통해 메모리를 관리한다.
    -   이 가비지 컬렉션은 자바 프로그램에서 사용되지 않는 메모리를 지속적으로 찾아내서 제거하는 역할을 한다.
        1.  참조되지 않은 객체들을 탐색 후 삭제
        2.  삭제된 객체의 메모리 반환
        3.  힙 메모리 재사용

# Casting

>   캐스팅이란?
>
>   변수가 원하는 정보를 다 갖고 있는 것

```java
int a = 0.1;			// (1) 에러 발생 X
int b = (int) true;		// (2) 에러 발생 O, boolean 은 int로 캐스트 불가
```

(1)은 0.1이 double 형이 지만, int로 될 정보 또한 가지고 있다.
(2)는 true는 int형이 될 정보를 가지고 있지 않다.

왜 캐스팅이 필요한가요?

-   **`다형성`** : 오버라이딩 된 함수를 분리해서 활용할 수 있다.
-   **`상속`** : 캐스팅을 통해 범용적인 프로그래밍이 가능하다.

## 형변환의 종류

### 묵시적 형변환

>   캐스팅이 자동으로 발생 (업캐스팅)

```java
Parent p = new Child(); 
```

-   (Parent) new Child()할 필요가 없다.
-   Parent를 상속받은 Child는 Parent의 속성을 포함하고 있기 때문에

### 명시적 형변환

>   캐스팅할 내용을 적어줘야 하는 경우 (다운캐스팅)

```java
Parent p = new Child();
Child c = (Child) p;
```

-   다운캐스팅은 업캐스팅이 발생한 이후에 작용한다.



## 예시

```java
class Parent {
    int age;
    
    Parent() {}
    
    Parent(int age) {
        this.age = age;
    }
    
    void printInfo() {
        System.out.println("Parent Call !!");
    }
}

class Child extends Parent {
    String name;
    
    Child() {}
    
    Child(int age, String name) {
        super(age);
        this.name = name;
    }
    
    @Override
    void printInfo() {
        System.out.println("Child Call !!");
    }
}

public class test {
    public static void main(String[] args) {
        Parent p = new Child();
        
        p.printInfo(); // 문제 1 : 출력 결과는?
        Child c = (Child) new Parent(); // 문제 2 : 에러 종류는?
    }
}
```

-   문제 1 : **Child Call !!**

    -> 자바에서는 오버라이딩된 함수를 동적 바인딩 하기 때문에, Parent에 담겼어도 Child의 printInfo()함수를 불러 오게 된다.

-   문제 2 : **Runtime Error**

    -> 컴파일 과정에서는 데이터형의 일치만 따진다.
    -> 프로그래머가 따로 (Child)로 명시적 형변환을 해줬기 때문에 컴파일러는 문법이 맞다고 생각해서 넘어간다.
    -> 하지만 런타임 과정에서 Child 클래스에 Parent 클래스를 넣을 수 없다는 것을 알게되고 런타임 에러가 발생하게 된다.

# Java에서의 Thread 활용

요즘 OS은 모두 멀티태스킹을 지원한다.

실제로 동시에 처리될 수 있는 프로세스의 개수는 CPU 코어의 개수와 동일한데, 이보다 많은 개수의 프로세스가 존재하기 때문에 모두 함께 동시에 처리할 수는 없다.

각 코어들은 아주 짧은 시간동안 여러 프로세스를 번갈아가며 처리하는 방식을 통해 동시에 동작하는 것 처럼 보이게 할 뿐인다.

이와 마찬가지로 멀티스레딩이란 하나의 프로세스 안에 여러개의 스레드가 동시에 작업을 수행하는 것을 말한다. 스레드는 하나의 작업 단위라고 생각하면 편하다.

## Thread 구현

자바에서의 스레드 구현방법에는 2가지가 있다.

1.  `Runnable` **인터페이스** 구현
2.   `Thread` **클래스** 상속

둘다 **`run()`** 메소드를 오버라이딩 하는 방식이다 !

```java
public class MyThread implements Runnable {
    @Override
    public void run() {
        // 수행 코드
    }
}
```

```java
public class MyThread extends Thread {
    @Override
    public void run() {
        // 수행 코드
    }
}
```



## Thread 생성

하지만 이 두가지 방법에는 **인스턴스 생성 방법에 차이가 있다.**

**Runnable**

`Runnable` 인터페이스를 구현한 경우는, 해당 클래스를 인스턴스화 해서 Thread 생성자에 argument로 넘겨줘야 한다.

그리고 `run()`을 호출하면 `Runnable` 인터페이스에서 구현한 `run()`이 호출되므로 따로 오버라이딩하지 않아도 되는 장점이 있다.

```java
public static void main(String[] args) {
    Runnable r = new MyThread();
    Thread t = new Thread(r, "mythread");
}
```



**Thread**

Thread 클래스를 상속받은 경우는, 상속받은 클래스 자체를 스레드로 사용할 수 있다.

또, `Thread` 클래스를 상속받으면 스레드 클래스의 메소드(`getName()`)를 바로 사용할 수 있지만, `Runnable` 구현의 경우 Thread 클래스의 static 메소드인 `currentThread()`를 호출하여 현재 스레드에 대한 참조를 얻어와야만 호출이 가능하다.

```java
public class ThreadTest implements Runnable {
    public ThreadTest(){}
    
    public ThreadTest(String name) {
        Thread t = new Thread(this, name);
        t.start();
    }
    
    @Override
    public void run() {
        for (int i = 0; i <= 50; i++) {
            System.out.print(i + ":" + Thread.currentThread().getName() + " ");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
```



## Thread 실행

스레드의 실행은 `run()` 호출이 아닌 `start()` 호출로 해야한다.

우리는 분명 `run()` 메소드를 정의했는데, 실제 스레드 작업을 시키려면 `start()`로 작업해야 한다고 한다.

 `run()`으로 작업 지시를 하면 스레드가 일을 안할까? 그렇지 않다. 두 메소드 모두 같은 작업을 한다. **하지만, `run()`메소드를 사용한다면, 이건 스레드를 사용하는 것이 아니다.**

Java에는 콜 스택(call stack)이 있는데 이 영역이 실질적인 명령어들을 담고 있는 메모리로, 하나씩 꺼내서 실행시키는 역할을 한다.

만약 동시에 두 가지 작업을 한다면, 두 개 이상의 콜 스택이 필요하게 된다.

**스레드를 이용한다는 건, `JVM`이 다수의 콜 스택을 번갈아가며 일처리**를 하고 사용자는 동시에 작업하는 것 처럼 보여준다.

즉, `run()`메소드를 이용한다는 것은 main()의 콜 스택 하나만 이용하는 것으로 스레드를 활용하는 것이 아니라, 스레드 객체의 run이라는 메소드를 호출하는 것 뿐이다.

`start()` 메소드를 호출하면, `JVM`은 알아서 스레드를 위한 콜 스택을 새로 만들어주고 **context switchin**을 통해 스레드답게 동작하도록 해준다.

따라서 우리는 새로운 콜 스택을 만들어 작업을 해야 스레드 일처리가 되는 것이기 때문에,`start()` 메소드를 써야하는 것이다.

*`start()`는 스레드가 작업을 실행하는데 필요한 **콜 스택**을 생성한 다음 `run()`을 호출해서 그 스택안에 `run()`을 저장할 수 있도록 해준다.*



## Thread 실행제어

스레드에는 5가지의 상태가 있다.

-   **`NEW`** : 스레드가 생성되고 아직 `start()`가 호출되지 않은 상태
-   **`RUNNABLE`** : 실행 중 또는 실행 가는 상태
-   **`BLOCKED`** : 동기화 블럭에 의해 일시정지된 상태(lock이 풀릴 때 까지 기다린다)
-   **`WAITING, TIME_WAITING`** : 실행가능하지 않은 일시정지 상태
-   **`TERMINATED`** : 스레드 작업이 종료된 상태

스레드로 구현하는 것이 어려운 이유는 바로 동기화와 스케쥴링 대문이다.

스케쥴링과 관련된 메소드는 `sleep(), join(), yield(), interrupt()`와 같은 것들이 있다.

`start()` 이후에 `join()`을 해주면 main 스레드가 모두 종료될 때까지 기다려주는 일도 한다.



## 동기화

멀티스레드로 구현을 하려면 동기화는 필수적이다.

**동기화가 필요한 이유는, 여러 스레드가 같은 프로세스 내의 자원을 공유하면서 작업할 때 서로의 작업이 다른 작업에 여향을 주기 때문이다.**

스레드의 동기화를 위해선, **임계영역(Critical Section)**과 **잠금(Lock)**을 활용한다.

임계영역을 지정하고, 임계영역을 가지고 있는 `lock`을 단 하나의 스레드에게만 빌려주는 개념으로 이루어져 있다.

따라서 임계구역 안에서 수행할 코드가 완료되면, `lock`을 반납해줘야 한다.

**스레드 동기화 방법**

-   **임계영역(Critical Section)** : 공유 자원에 단 하나의 스레드만 접근하도록 (하나의 프로세스에 속한 스레드만 가능)
-   **뮤텍스(Mutex)** : 공유 자원에 단 하나의 스레드만 접근하도록 (서로 다른 프로세스에 속한 스레드도 가능)
-   **이벤트(Event)** : 특정한 사건 발생을 다른 스레드에게 알린다.
-   **세마포어(Semaphore)** : 한정된 개수의 자원을 여러 스레드가 사용하려고 할 때 접근 제한
-   **대기 가능 타이머(Waitable Timer)** : 특정 시간이 되면 대기 중이던 스레드를 깨운다.

### Synchronized

`Synchronized` 를 활용해 임계영역을 설정할 수 있다.

서로 다른 두 객체가 동기화를 하지 않은 메소드를 같이 오버라이딩해서 이용하면, 두 스레드가 동시에 진행되므로 원하는 출력값을 얻지 못한다.

이 때 오버라이딩되는 부모 클래스의 메소드에 `synchronized` 키워드로 임계영역을 설정하여 해결할 수 있다.

```java
// synchronized : 스레드의 동기화. 공유자원에 Lock !
public synchronized void Deposit(int save) { // 입금
    int m = money;
    try {
        Thread.sleep(2000); // 지연시간 2초
    } catch (Exception e) {
        
    }
    money = m + save;
    System.out.println("입금 처리 완료");
}

public synchronized void WithDrawal(int minus) {
    int m = money;
    try {
        Thread.sleep(3000); // 지연시간 3초
    } catch (Exception e) {
        
    }
    money = m - minus;
    System.out.println("출금 완료");
}
```

### wait(), notify()

스레드가 서로 협력관계일 경우에는 무작정 대기시키는 것으로 올바르게 실행되지 않기 때문에 `wait()`와 `notify()`를 사용한다.

-   `wait()` : 스레드가 `lock`을 가지고 있으면 `lock` 권한을 반납하고 대기하게 만든다.
-   `notify()` : 대기 상태인 스레드에게 다시 `lock` 권한을 부여하고 수행하게 만든다.

이 두 메소드는 동기화 된 영역(임계 영역) 내에서 사용되어야 한다.

동기화 처리한 메소드들이 반복문에서 활용된다면, 의도한 결과가 나오지 않는다. 이 때 `wait()`와 `notify()`를 **try-catch**문에서 적절히 활용해 해결할 수 있따.

```java
/*
	스레드 동기화 중 협력관계 처리 작업 : wait(), notify()
	스레드 간 협력 작업 강화 !
*/
public synchronized void makeBread() {
    if (breadCount >= 10) {
        try {
            System.out.println("빵 생산 초과");
            wait();		// Thread를 Not Runnable 상태로 전환
        } catch (Exception e) {

        }
    }
    breadCount++;	// 빵 생산
    System.out.println("총 "+breadCount+" 개의 빵을 만들었다.");
    notify();		// Thread를 Runnable 상태로 전환
}

public synchronized void eatBread() {
    if (breadCount < 1) {
        try {
            System.out.println("빵이 없어서 기다린다..");
            wait();
        } catch (Exception e) {

        }
    }
    breadCount--;
    System.out.println("총 "+breadCount+" 개의 빵을 먹었다.");
    notify();
}
```

조건이 만족 할 시 `wait()`를 호출하고
조건이 만족 안할 시 `notify()`를 받아 수행한다.

# Intrinsic Lock

>   고유 락
>
>   = **monitor lock** = **monitor**

자바의 모든 객체는 `lock`을 갖고 있따.

*`Synchronized` 블록은 `Intrinsic Lock`을 이용해서 Thread의 접근을 제어한다.*

```java
public class Counter {
    private int count;
    
    public int increase() {
        return ++count;		// Thread-Safe 하지 않은 연산
    }
}
```

Q) `++count`문이 **atomic 연산**인가?

A)

1.  read (count 값을 읽음)
2.  modify (count 값 수정)
3.  write (count 값 저장)

의 과정에서 여러 Thread가 **공유 자원(count)으로 접근할 수 있으므로 동시성 문제가 발생**한다.

그러면 `Synchronized` 블록을 사용해서 **Thread-Safe**하게 만들어보자.

```java
public class Counter {
    private Object lock = new Object();	// 모든 객체가 가능 (Lock이 있음)
    private int count;
    
    public int increase() {
        // 단계 1
        synchronized(lock) { // lock을 이용하여, count 변수에의 접근을 막음
            return ++count;
        }
        /*
        단계 2
        synchronized (lock) { // this도 객체이므로 lock으로 사용가능
        	return ++count;
        }
        */
    }
    /*
    단계 3
    public synchronized int increase() {
    	return ++count;
    }
    */
}
```

*단계 3과 같이 `lock` 생성 없이 `synchronized` 블록 구현 가능*

## Reentrancy

>   재진입 : Lock을 획득한 Thread가 Lock을 얻기 위해 대기할 필요가 없는 것
>
>   Lock의 획득은 **호출 단위**가 아니라, **Thread 단위**로 일어난다.

```java
public class Reentrancy {
    // b가 Sycnhronized로 선언되어 있더라도, a 진입 시 lock을 획득하였음.
    // b를 호출할 수 있게 됨.
    public synchronized void a() {
        Sytsem.out.println("a");
        b();
    }
    
    public synchronized void b() {
        System.out.println("b");
    }
  	
    public static void main(String[] args) {
        new Reentrancy().a();
    }
}
```

## Structed Lock vs Reentrant Lock

>   `Structured Lock (구조적 Lock)` : 고유 lock을 이용한 동기화

Synchronized 블록 단위로 lock의 획득 / 해제가 일어나므로!

따라서,

A획득 -> B획득 -> B해제 -> A해제는 가능하지만
A획득 -> B회득 -> A해제 -> B해제는 불가능하다.

이것을 가능하게 하기 위해서는 **Reentrant Lock(명시적 Lock)**을 사용해야 한다.

## Visibility

-   가시성 : 여러 Thread가 동시에 작동하였을 때, 한 Thread가 쓴 값을 다른 Thread가 볼 수 있는지, 없는지 여부
-   문제 : 하나의 Thread가 쓴 값을 다른 Thread가 볼 수 있느냐 없느냐. (볼 수 없으면 문제가 된다.)
-   Lock : Structure Lock과 Reentrant Lock은 Visibility를 보장한다.
-   원인
    -   최적화를 위해 Compiler나 CPU에서 발생하는 코드 재배열로 인해
    -   CPU core의 cache 값이 Memory에 제때 쓰이지 않아 발생하는 문제









