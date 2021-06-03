# **Java**

>   참고 : https://github.com/gyoogle/tech-interview-for-developer
>
>   전반적인 흐름은 gyoogle님의 깃허브를 참고하였으며, 추가 레퍼런스는 글을 진행하며 알려드릴 것입니다. 파이팅 !

- [자바 컴파일 과정](#자바-컴파일과정)
- [Call by value vs Call by reference](#call-by-value-vs-call-by-reference)
- [String 친구들](#string-stringbuffer-stringbuilder)
- [JVM](#jvm)
- [Casting](#casting)
- [Java에서의 Thread](#java에서의-thread)
- [Intrinsic Lock](#intrinsic-lock)
- [Garbage Collection](#garbage-collection)
- [Promotion & Casting](#promotion--casting)
- [Primitive type & Reference type](#primitive-type--reference-type)
- [Error & Exception](#error--exception)
- [JAVA Stream](#java-stream)

# 자바 컴파일과정

---

[위로](#java)

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

[위로](#java)

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

[위로](#java)

| 분류         | String                       | StringBuffer                    | StringBuilder        |
| ------------ | ---------------------------- | ------------------------------- | -------------------- |
| Storage Area | Heap or Constant String Pool | Heap                            | Heap                 |
| 변경         | No (Immutable)(불변)         | Yes (Mutable)(가변)             | Yes(Mutable)(가변)   |
| 동기화       | Yes                          | Synchronized 가능 (Thread-safe) | Synchronized 불가능. |

## String 특징

-   new 연산을 통해 생성된 인스턴스의 메모리 공간은 변하지 않습니다.(**`Immutable`**)
    *ex. `+ Boolean, Integer, Float, Long`*
-   `Garbage Collector`로 제거되어야 합니다.
-   문자열 연산시 새로 객체를 만드는 Overhead가 발생합니다.
-   문자열 연산이 많이 일어나는 경우 더 이상 참조되지 않는 기존 객체는 **Garabae Collector**에 의해 제거되어야 하기 때문에 성능이 좋지 않습니다.
-   객체가 불변하므로, 멀티쓰레드에서 동기화를 신경 쓸 필요가 없습니다.(조회 연산에 매우 큰 장점!)
-   객체가 가지는 값마다 새로운 객체가 필요하기 때문에, 메모리 누수와 새로운 객체를 계속 생성해야하기 때문에 성능저하를 발생시킬 수 있습니다.

***String 클래스 : 문자열 연산이 적고, 조회가 많은 멀티쓰레드 환경에서 좋다***

## StringBuffer, StringBuilder 특징

-   공통점
    -   new 연산으로 클래스를 한 번만 만듭니다.(**`Mutable`**)
    -   문자열 연산 시 새로 객체를 만들지 않고, 크기를 변경시킵니다.
    -   StringBuffer와 StringBuilder 모두 동일한 메서드를 가지고 있습니다.
-   차이점
    -   StringBuffer는 각 메서드별로 `Synchronized` Keyword가 존재하여 멀티 쓰레드 환경에서 동기화를 지원합니다.
    -   따라서 StringBuffer는 `Thread-Safe`하고, StringBuilder는 `Thread-Safe`하지 않습니다.

***StringBuffer 클래스 : 문자열 연산이 많은 멀티쓰레드 환경***

***StringBuilder 클래스 : 문자열 연산이 많은 싱글쓰레드 혹은 Thread를 신경 안쓰는 환경***

# JVM

---

[위로](#java)

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

---

[위로](#java)

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

---

[위로](#java)

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

---

[위로](#java)

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

# Garbage Collection

---

[위로](#java)

> - Garbage Collection의 역할에 대해 설명하라
> - Garabage Collection의 메모리 해제 과정을 3단계로 설명하라
> - Generational Garbage Collections에 대해 설명하라
> - Generational Garbage Collection 과정에 대해 설명하라
> - Minor GC와 Major GC의 차이점에 대해 설명하라

`C/C++` 프로그래밍을 할 때 메모리 누수(Memory Leak)를 막기 위해 객체를 생성한 후 사용하지 않는 객체의 메모리를 프로그래머가 직접 해제해야 했습니다. 하지만 JAVA에서는 **`JVM`(Java Virtual Machine)**이 구성된 **`JRE`(Java Runtime Environment)**가 제공되며, 그 구성 요소 중 하나인 **Garbage Collection(이하 `GC`)**이 자동으로 사용하지 않는 객체를 파괴합니다.

`GC`에 대해 알아보기 전에 `stop-the-world`라는 용어를 알아야 합니다. `stop-the-world`란, `GC`를 실행하기 위해 `JVM`이 에플리케이션 실행을 멈추는 것입니다. **어떤 `GC` 알고리즘을 사용하더라도 `stop-the-world`는 발생하게 되는데, 대개의 경우 `GC`튜닝은 이 `stop-the-world` 시간을 줄이는 것**이라고 합니다.

`GC`를 해도 더 이상 사용 가능한 메모리 영역이 없는데 계속 메모리를 할당하려고 하면, `OutOfMemoryError`가 발생하여 `WAS`가 다운될 수 도 있습니다. **행(Hang)** 즉, 서버가 요청을 처리 못하고 있는 상태가 됩니다.

따라서 규모 있는 JAVA 애플리케이션을 효율적으로 개발하기 위해서는 `GC`에 대해 잘 알아야 합니다. Lets go!



## GC

`C/C++`과 달리 자바는 개발자가 명시적으로 객체를 해제할 필요가 없습니다. 자바 언어의 큰 장점이죠. 사용하지 않는 객체는 메모리에서 삭제하는 작업을 `GC`라고 부르며 `JVM`에서 `GC`를 수행합니다.

기본적으로 `JVM`의 메모리는 총 5가지 영역(`class, stack, heap, native method, PC`)로 나뉘는데, **`GC`는 `heap` 메모리만 다룹니다.**

일반적으로 다음과 같은 경우에 `GC`의 대상이 됩니다.

1. 객체가 NULL인 경우 (ex. `String str = null;`)
2. 블럭 실행 종료 후, 블럭 안에서 생성된 객체
3. 부모 객체가 NULL인 경우, 포함하는 자식 객체

GC는 **`Weak Generational Hypothesis`**에 기반합니다. 우선 `GC`의 메모리 해제 과정에 대해 살펴봅시다.



## GC의 메모리 해제 과정

1. **Marking**

[![img](https://github.com/GimunLee/tech-refrigerator/raw/master/Language/JAVA/resources/java-gc-001.png)](https://github.com/GimunLee/tech-refrigerator/blob/master/Language/JAVA/resources/java-gc-001.png)

- 프로세스는 마킹을 호출합니다. 이것은 GC가 메모리가 사용되는지 아닌지를 찾아냅니다. 참조되는 객체는 파란색으로, 참조되지 않는 객체는 주황색으로 보여집니다. 모든 오브젝트는 마킹 단계에서 결정을 위해 스캔되어집니다. 모든 오브젝트를 스캔하기 때문에 매우 많은 시간을 소모하게 됩니다.

2. **Normal Deletion**

[![img](https://github.com/GimunLee/tech-refrigerator/raw/master/Language/JAVA/resources/java-gc-002.png)](https://github.com/GimunLee/tech-refrigerator/blob/master/Language/JAVA/resources/java-gc-002.png)

- 참조되지 않는 객체를 제거하고, 메모리를 반환합니다. 메모리 Allocator는 반환되어 비어진 블럭의 참조 위치를 저장해 두었다고 새로운 오브젝트가 선언되면 할당되도록 합니다.

3. **Compacting**

[![img](https://github.com/GimunLee/tech-refrigerator/raw/master/Language/JAVA/resources/java-gc-003.png)](https://github.com/GimunLee/tech-refrigerator/blob/master/Language/JAVA/resources/java-gc-003.png)

- 퍼포먼스를 향상시키기 위해, 참조되지 않는 객체를 제거하고 또한 남은 참조되어지는 객체들을 묶습니다. 이들을 묶음으로서 공간이 생기므로 새로운 메모리 할당 시에 더 쉽고 빠르게 진행 할 수 있습니다.



## Generational GC 배경

위와 같이 모든 객체를 `Mark & Compact` 하는 `JVM`은 비효율적입니다. 다음과 같은 그래프를 봅시다

[![img](https://github.com/GimunLee/tech-refrigerator/raw/master/Language/JAVA/resources/java-gc-004.png)](https://github.com/GimunLee/tech-refrigerator/blob/master/Language/JAVA/resources/java-gc-004.png)

Y축은 할당된 바이트의 수이고 X축은 바이트가 할당될 때의 시간입니다. 보시다시피 시간이 갈수록 적은 객체만이 남습니다. 위와 같은 그래프에 기반한 것이 바로 `Weak Genrational Hypothesis`입니다.

### Weak Generational Hypothesis

신규로 생성한 객체의 대부분은 금방 사용하지 않은 상태가 되고, 오래된 객체에서 신규 객체로의 참조는 매우 적게 존재한다는 가설입니다.

이 가설에 기반하여 자바는 `Young` 영역과 `Old` 영역으로 메모리를 분할하고, 신규로 생성되는 객체는 `Young` 영역에 보관하고, 오랫동안 살아남은 객체는 `Old` 영역에 보관합니다.

## Generational GC

[![img](https://github.com/GimunLee/tech-refrigerator/raw/master/Language/JAVA/resources/java-gc-006.png)](https://github.com/GimunLee/tech-refrigerator/blob/master/Language/JAVA/resources/java-gc-006.png)

1. **Young 영역(Young Generation 영역)**
   - 새롭게 생성한 객체의 대부분이 여기에 위치합니다.
   - 대부분의 객체가 금방 접근 불가능 상태가 되기 때문에 매우 많은 객체가 Young 영역에 생성되었다가 사라집니다.
   - 이 영역에서 객체가 사라질 때 **`Minor GC`**가 발생한다고 말합니다.
2. **Old 영역(Old Generation 영역)**
   - 접근 불가능 상태로 되지 않아 Young 영역에서 살아남은 객체가 여기로 복사됩니다.
   - 대부분 Young 영역보다 크게 할당하며, 크기가 큰 만큼 Young 영역보다 `GC`는 적게 발생합니다.
   - 이 영역에서 객체가 사라질 때 **`Major GC`(혹은 `Full GC`)**가 발생한다고 말합니다.
3. **Permanent 영역**
   - `Method Area`라고도 합니다.
   - `JVM`이 클래스들과 메소드들을 설명하기 위해 필요한 메타데이터들을 포함하고 있습니다.
   - `JDK 8 `부터는 `PermGen`은 **Metsapace**로 교체됩니다.



## Generational GC 과정

1. 어떠한 새로운 객체가 들어오면 `Eden Space`에 할당합니다.

   [![img](https://github.com/GimunLee/tech-refrigerator/raw/master/Language/JAVA/resources/java-gc-007.png)](https://github.com/GimunLee/tech-refrigerator/blob/master/Language/JAVA/resources/java-gc-007.png)

2. `Eden space`가 가득차게 되면, **Minor Garbage Collection**이 시작됩니다.

   [![img](https://github.com/GimunLee/tech-refrigerator/raw/master/Language/JAVA/resources/java-gc-008.png)](https://github.com/GimunLee/tech-refrigerator/blob/master/Language/JAVA/resources/java-gc-008.png)

3. 참조되는 객체들은 첫 번째 survivor(`S0`)로 이동되어 지고, 비 참조 객체는 `Eden space`가 clear될 때 반환됩니다.

   [![img](https://github.com/GimunLee/tech-refrigerator/raw/master/Language/JAVA/resources/java-gc-009.png)](https://github.com/GimunLee/tech-refrigerator/blob/master/Language/JAVA/resources/java-gc-009.png)

4. 다음 **`Minor GC`** 떄, Eden space에서는 같은 일이 일어 납니다. 비 참조 객체는 삭제되고 참조 객체는 `survivor space`로 이동하는 것입니다.
   그러나, 이 케이스에서 참조객체는 두 번째 `survivor space`로 이동하게 됩니다.
   게다가 최근 `Minor GC`에서 첫 번째 `survivor space`로 이동된 객체들도 age가 증가하고 `S1` 공간으로 이동하게 됩니다.
   한번 모든 surviving 객체들이 `S1`으로 이동하게 되면 `S0`와 `Eden` 공간은 clear 됩니다.
   주의해야 할 점은 이제 우리는 다른 aged 객체들을 survivor space에 가지게 되었다는 것입니다.

   [![img](https://github.com/GimunLee/tech-refrigerator/raw/master/Language/JAVA/resources/java-gc-010.png)](https://github.com/GimunLee/tech-refrigerator/blob/master/Language/JAVA/resources/java-gc-010.png)

5. 다음 **`Minor GC`** 때, 같은 과정이 방복 됩니다.
   그러나 이번엔 `survivor space`들은 **switch**됩니다.
   참조되는 객체들은 `S0`로 이동합니다. 살아남은 객체들은 **aged**되고 `Eden`과 `S1` 공간은 Clear 됩니다.

   [![img](https://github.com/GimunLee/tech-refrigerator/raw/master/Language/JAVA/resources/java-gc-011.png)](https://github.com/GimunLee/tech-refrigerator/blob/master/Language/JAVA/resources/java-gc-011.png)

6. 아래 그림은 promotion을 보여줍니다.
   `Minor GC` 이후 aged 오브젝트들이 일정한 age threshold를 넘게 되면 그 들은 young generation에서 old 로 promotion 되어집니다. 여기서는 `8`을 예로 들었습니다.

   [![img](https://github.com/GimunLee/tech-refrigerator/raw/master/Language/JAVA/resources/java-gc-012.png)](https://github.com/GimunLee/tech-refrigerator/blob/master/Language/JAVA/resources/java-gc-012.png)

7. **`Minor GC`**가 계속 되고 계속해서 객체들이 `Old Generation`으로 이동합니다.

   [![img](https://github.com/GimunLee/tech-refrigerator/raw/master/Language/JAVA/resources/java-gc-013.png)](https://github.com/GimunLee/tech-refrigerator/blob/master/Language/JAVA/resources/java-gc-013.png)

8. 아래 그림은 전 과정을 보여줍니다.
   결국 **`Major GC`**가 `old generation`에 시행되고, `old generation`은 clear 되고, 공간이 compact 되어집니다.

   [![img](https://github.com/GimunLee/tech-refrigerator/raw/master/Language/JAVA/resources/java-gc-014.png)](https://github.com/GimunLee/tech-refrigerator/blob/master/Language/JAVA/resources/java-gc-014.png)

# Promotion & Casting

---

[위로](#java)

> - Promotion에 대해 설명하라.
> - Casting에 대해 설명하라.
> - 형변환할 때 발생할 수 있는 에러에 대해 설명하라.

## 데이터 타입 형변환 (타입 변환)

Java에서 연산은 "2(byte 데이터 타입) + 3(byte 데이터 타입)" 과 같이 동일한 데이터 타입에서 가능합니다.

하지만, 프로그램을 만들다 보면 "2(byte 데이터 타입) + 3.5(double 데이터 타입)"과 같이 서로 다른 데이터 타입끼리의 연산이 필요할 때가 있습니다.

이럴 경우 변수의 데이터 타입을 바꿔주는 작업이 필요한데, 이것이 데이터 타입의 형변환(`타입 변환`)입니다.

이러한 형변환(타입변환)에는 크게 **자동 형변환(`Promotion`)**과 **강제 형변환(`Casting`)**이 있습니다.
또 다른 말로 자동형변환은 묵시적 타입 변환, 강제 형변환은 명시적 타입 변환이라고도 합니다.

## Promotion

> 자동 형변환(묵시적 형변환)

자동 형변환(`Promotion`)은 프로그램 실행 도중에 자동적으로 형변환(타입 변환)이 일어나는 것을 말합니다.

또한, 자동 형변환(`Promotion`)은 작은 메모리 크기의 데이터 타입을 큰 메모리 크기의 데이터 타입으로 변환하는 행위를 말합니다.

```java
byte a = 10; // 정수 10을 byte 데이터 타입의 변수인 a에 저장
int b = a;	// byte 데이터 타입의 변수인 a를 int 데이터 타입의 변수인 b에 저장
```

위에 작성한 예시처럼 작은 메모리 크기의 데이터 타입(byte 데이터 타입)에서 큰 메모리 크기의 데이터 타입(int 데이터 타입)에 값을 저장하면 별다른 문법 없이 형변환(타입 변환)이 일어납니다. 이러한 형변환을 **"자동 형변환(`Promotion`)"**이라고 합니다.

자동 형변환(`Promotion`)이 이루어지는 순서를 알아보겠습니다.

[![img](https://github.com/GimunLee/tech-refrigerator/raw/master/Language/JAVA/resources/java-promotion-casting-001.png)](https://github.com/GimunLee/tech-refrigerator/blob/master/Language/JAVA/resources/java-promotion-casting-001.png)

보시면, long 데이터 타입의 메모리 크기는 8byte이고, float 데이터 타입의 메모리 크기는 4byte인데, long 데이터 타입에서 float 데이터 타입으로 자동 형변환(`Promotion`)이 가능합니다.

그 이유는 표현할 수 있는 값의 범위가 float가 더 크기 때문입니다.

주의 할 점은 메모리 크기가 큰 데이터 타입 이어도, 타입 범위를 포함하지 못한다면 자동 형변환(`Promotion`)이 불가능합니다.

- `byte` 데이터 타입 -> `char` 데이터 타입 자동 형변환 불가
- `float` 데이터 타입 -> `long` 데이터 타입 자동 형변환 불가



## Casting

> 강제 형변환(명시적 형변환)

특정 조건을 갖추지 못했지만, 형변환을 하고 싶을 때 사용하는 것이 **`Casting` (강제 형변환)**입니다.

```java
int intValue = 1;
byte byteVlaue = intValue;
```

위의 경우 `intValue`에 저장된 1이라는 값은 byte 데이터 타입에도 저장 가능한 값입니다.

하지만, 위 코드를 실행하면 컴파일 에러가 발생합니다. 그 이유는 저장될 값 1에 상관없이 `int` 데이터 타입이 `byte` 데이터 타입보다 메모리 크기가 크기 때문입니다. 그림으로 봅시다.

[![img](https://github.com/GimunLee/tech-refrigerator/raw/master/Language/JAVA/resources/java-promotion-casting-002.png)](https://github.com/GimunLee/tech-refrigerator/blob/master/Language/JAVA/resources/java-promotion-casting-002.png)

이와 같은 그림이 나옵니다. `int` 데이터 타입에 저장된 정수 1의 값은 실제 메모리에 저장될 때 
`00000000 00000000 00000000 00000001`의 값을 가집니다.

이 값을 `byte` 데이터 타입에 끝에 1byte(`00000001`) 영역만 넣자니 앞에 있는 3byte(`00000000 00000000 00000000`)가 날아갑니다. 그림으로 보면 이렇습니다.

[![img](https://github.com/GimunLee/tech-refrigerator/raw/master/Language/JAVA/resources/java-promotion-casting-003.png)](https://github.com/GimunLee/tech-refrigerator/blob/master/Language/JAVA/resources/java-promotion-casting-003.png)

앞에 3byte의 공간을 삭제하는 시점에서 많은 데이터가 날아가 정상적인 값이 저장될 수 없을 것입니다.

이와 같이 메모리 크기가 큰 `int` 데이터 타입에서 메모리 크기가 작은 `byte` 데이터 타입으로 자동 형변환(`Promotion`)이 된다면, 정상적이지 않은 값이 나올 수 있기 때문에 Java에서 자동 형변환(`Promotion`)을 하지 않습니다.

하지만, 우리가 형변환 하려는 정수 값은 1이므로 byte 데이터 타입 범위 안에 충분히 들어가는 값입니다.

우린 그걸 머릿속으로 알고 있기 때문에 `byte`데이터 타입으로 변환된다 하더라도 값이 정상적일거라고 판단할 수 있습니다. 이럴 때 **강제 형변환**은 아래와 같이 하면 됩니다.

```java
int intValue = 1;
byte byteValue = (byte) intValue;
```



## 형변환 연산

`+, -, %, /`과 같은 기본적인 사칙연산은 같은 타입의 피연산자 간에만 수행되기 때문에 서로 다른 데이터 타입의 피연산자가 있을 경우 두 피연산자 중 크기가 큰 타입으로 자동 형변환(`Promotion`)된 후 연산이 수행됩니다.

예를 들어 `int` 데이터 타입의 피연산자와 `double` 타입의 피연산자를 덧셈하면 `int` 데이터 타입의 피연산자가 `double` 데이터 타입으로 **자동 형변환(`Promotion`)** 되고 연산이 수행됩니다. 연산의 결과도 물론 `double` 데이터 타입이 됩니다.

```java
int intValue = 10;
double doubleValue = 5.5;
double result = intValue + doubleValue;
// intValue 변수값과 doubleValue 변수 값을 더해서 double 타입의 result 변수에 저장
```

만약 int 데이터 타입의 연산 결과를 알고 싶다면, **강제 형변환(`Casting`)**을 통해 아래와 같이 작성하면 됩니다.

```java
int intValue = 10;
double doubleValue = 5.5;
int result = intValue + (int) doubleValue;
// intValue 변수값과 doubleValue 변수값을 더해서 int 타입의 result 변수에 저장
```

# Primitive type & Reference type

---

[위로](#java)

> - Primitive type에 대해 설명하라
> - Reference type에 대해 설명하라

자바에는 기본형(`Privitive type`)과 참조형(`Reference type`)이 있다.

일반적으로 다음처럼 분류가 된다.

```bash
Java Data Type 
ㄴ Primitive Type
    ㄴ Boolean Type(boolean)
    ㄴ Numeric Type
        ㄴ Integral Type
            ㄴ Integer Type(short, int, long)
            ㄴ Floating Point Type(float, double)
        ㄴ Character Type(char)
ㄴ Reference Type
    ㄴ Class Type
    ㄴ Interface Type
    ㄴ Array Type
    ㄴ Enum Type
    ㄴ etc.
```



## Primitive type

> 기본형 타입

- JAVA에서는 총 8가지의 `Primitive type`을 미리 정의하고 제공합니다.
- JAVA에서 기본 자료형은 반드시 사용하기 전에 선언(Declared) 되어야 합니다.
- OS에 따라 자료형의 길이가 변하지 않습니다.
- **비객체** 타입으로, `NULL`값을 가질 수 없습니다.
- 만약 `Primitive type`에 `NULL`값을 넣고 싶다면 **Wrapper Class**를 활용해야 합니다.
- **스택(Stack)** 메모리에 저장됩니다.

[![img](https://github.com/GimunLee/tech-refrigerator/raw/master/Language/JAVA/resources/java-type-001.png)](https://github.com/GimunLee/tech-refrigerator/blob/master/Language/JAVA/resources/java-type-001.png)



- `boolean`

  - 논리형인 boolean의 기본값은 false이며 참과 거짓을 저장하는 타입입니다.
  - 주로 yes/no, on/off 등의 논리 구현에 사용되며 두 가지 값만 표현하므로 가장 크기가 작습니다.
  - boolean은 실제로 `1bit`면 충분하지만, 데이터를 다루는 최소 단위가 `1byte` 이므로 메모리 크기가 `1byte`입니다.

- `byte`

  - byte는 주로 이진데이터를 다루는데 사용되는 타입입니다.

- `short`

  - short는 C언어와의 호환을 위해 사용되는 타입으로 잘 사용되지 않는 타입입닏.ㅏ

- `int`

  - int형은 자바에서 정수 연산을 하기 위한 기본 타입입니다.
  - 즉, `byte` 혹은 `short`의 변수가 연산을 하면 연산의 결과는 `int`형이 됩니다.

- `long`

  - 수치가 큰 데이터를 다루를 프로그램(은행 및 우주와 관련된 프로그램)에서 주로 사용합니다.
  - `long` 타입의 변수를 초기화 할 때에는 정수값 뒤에 알파벳 L을 붙여서 long 타입(즉, 8byte)의 정수 데이터임을 알려주어야 합니다
  - 만일 정수값이 `int`의 값의 저장범위를 넘는 중시에서 **L**을 붙이지 않는다면 컴파일 에러가 발생합니다.

  ```java
  long l = 2147483648;	// 컴파일 에러 발생
  long l = 2147483648L;
  ```

- `float, double`

  - 실수를 가수와 지수 형식으로 저장하는 부동소수점 방식으로 저장됩니다.
  - 가수를 표현하는 데 있어 `double` 형이 `float`형보다 표현 가능 범위가 더 크므로 보다 정밀하게 표현가능합니다.
  - 자바에서 실수의 기본 타입은 `double` 형이므로 `float`형에는 알파벳 **F**를 붙여서 `float`형임을 명시해주어야 합니다.

  ```java
  float f = 1234.567;	// 무조건 double 타입으로 이해하려고 하므로 컴파일 에러가 발생합니다.
  float f = 1234.567F; // float type이라는 것을 표시해야 합니다.
  ```



## Reference type

> 참조형 타입

- JAVA에서 `Primitive type`을 제외한 타입들이 모두 **`Reference type`**입니다.
- `Reference type`은 JAVA에서 최상인 `java.lang.Object` 클래스를 상속하는 모든 클래스들을 말합니다.
  - 물론 **new**로 인하여 생성하는 것들은 메모리 영역인 Heap 영역에 생성을 하게 되고, `Garbage Collector`가 돌면서 메모리를 해제합니다.
- **클래스 타입(class type), 인터페이스 타입(interface type), 배열 타입(array type), 열거 타입(enum type)**이 있습니다.
- 빈 객체를 의미하는 `NULL`이 존재합니다.
- 문법상으로는 에러가 없지만 실행시켰을 때 에러가 나는 런타임 에러가 발생합니다. 예를 들어 객체나 배열을 NULL값으로 받으면 `NullPointException(NPE)`이 발생하므로 변수 값을 넣어야 합니다.
- **Heap** 메모리에 생성된 인스턴스는 메소드나 각종 인터페이스에서 접근하기 위해 JVM의 **Stack** 영역에 존재하는 Frame에 일종의 포인터(C의 포인터와는 다름!)인 참조값을 가지고 있어 이를 통해 인스턴스를 핸들링합니다.

[![img](https://github.com/GimunLee/tech-refrigerator/raw/master/Language/JAVA/resources/java-type-002.png)](https://github.com/GimunLee/tech-refrigerator/blob/master/Language/JAVA/resources/java-type-002.png)



## String Class

클래스형 중에서도 `String Class`는 조금 특별합니다.

이 클래스는 참조형에 속하지만 기본적인 사용은 **기본형처럼 사용합니다.**

그리고 **불변(immutable)**하는 객체입니다.

String 클래스에는 값을 변경해주는 메소드들이 존재하지만 해당 메소드를 통해 데이터를 바꾼다 해도 새로운 String Class 객체를 만들어내는 것입니다.

일반적으로 기본형 비교는 `==` 연산자를 사용하지만 String 객체간의 비교는 `.equals()` 메소드를 사용해야 합니다.



**String Class**는 `equals()`가 오버라이딩 되어있고, **StringBuilder(or StringBuffer) Class**는 `equals()`가 오버라이딩되어 있지않다.

때문에, StringBuilder에서 sb1, sb2 두 인스턴스를 생성하게 되고 둘다 "abc"값을 주고 `equals`를 해보면 false가 나오고
`==`를 사용하면 true가 나오게 된다.

```java
StringBuilder sb1 = new StringBuilder("abc");
StringBuilder sb2 = new StringBuilder("abc");

boolean check = sb1.eqauls(sb2); // false // (주소값을 비교 (동등성))
boolean check = sb1 == sb2;		// true // (실제 값 비교(동일성))
```

String에서는 equals가 오버라이딩 되어있기 때문에, String str1.equals(str2)를 하게 되어도 true가 나오는 것이다.

오버라이딩 하지 않은 equals는 주소를 비교하는거고 String에서는 equals를 오버라이딩 하여 값을 비교하게 만들어 준 것이다.

```java
String str1 = "abc";
String str2 = "abc";

boolean check = str1.equals(str2); // true // String class에서 equals가 오버라이딩 되었기 때문에 true
boolean check = str1 == str2;	// true //
```

# Error & Exception

---

[위로](#java)

> - Error와 Exception의 차이점에 대해 설명하라
> - 어떻게 Exception Handling을 할 것인가

Error와 Exception은 같다고 생각할 수 있지만 사실 큰 차이가 있습니다.

**`Error`**는 컴파일 시 문법적인 오류와 런타임 시 널포인트 참조와 같은 오류로 프로세스에 심각한 문제를 야기시켜 프로세스를 종료시킬 수 있습니다.

**`Exception`**은 컴퓨터 시스템의 동작 도중 예기치 않았던 이상 상태가 발생하여 수행중인 프로그램이 영향을 받는 것으로 예를 들면, 연산도중 넘침에 의해 발생한 끼어들기 등이 이에 해당합니다.

프로그램이 실행 중 어떤 원인에 의해서 오작동을 하거나 비정상적으로 종료되는 경우를 프로그램 오류라 하고, 프로그램 오류에는 **에러(Error)**와 **예외(Exception)** 두 가지로 구분할 수 있습니다.

**`Error`**는 메모리 부족이나, `Stack Over Flow`와 같이 발생하면 복구할 수 없는 심각한 오류이고,

**`Exception`**은 발생하더라도 수습할 수 있는 비교적 덜 심각한 오류입니다. 이 `예외(Exception)`는 프로그래머가 적절히 코드를 작성해 주면 비정상적인 오류를 막을 수 있습니다.

*`Error`의 상황을 미리 미연에 방지하기 위해 `Exception` 상황을 만들어 줄 수 있으며, JAVA에서는 `Try-Catch`문으로 `Exception Handling`을 할 수 있습니다.*



## Exception Handling

잘못된 하나로 인해 전체 시스템이 무너지는 결과를 방지하기 위한 기술적인 처리 입니다. 

JAVA에서는 에러와 예외도 객체로 처리합니다.

**예외가 발생하는 주 원인**

- 사용자의 잘못된 데이터 입력
- 잘못된 연산
- 개발자가 잘못된 로직을 작성
- 하드웨어, 네트워크 오작동
- 시스템 과부하



## Throwable Class

[![img](https://github.com/GimunLee/tech-refrigerator/raw/master/Language/JAVA/resources/java-error-exception-001.png)](https://github.com/GimunLee/tech-refrigerator/blob/master/Language/JAVA/resources/java-error-exception-001.png)

**`Throwable Class`**는 예외처리를 할 수 있는 최상위 클래스입니다.

`Exception`과 `Error`는 `Throwable`의 상속을 받습니다.



### - Error

[![img](https://github.com/GimunLee/tech-refrigerator/raw/master/Language/JAVA/resources/java-error-exception-003.png)](https://github.com/GimunLee/tech-refrigerator/blob/master/Language/JAVA/resources/java-error-exception-003.png)

`Error`는 시스템 레벨에서 발생하여, 개발자가 어떻게 조치할 수 없는 수준을 의미합니다.

- **OutOfMemoryError**
  - `JVM`에 설정된 메모리의 한계를 벗어난 상황일 때 발생합니다.
  - 힙 사이즈가 부족하거나, 너무 많은 CLASS를 로드할 때, 가용가능한 SWAP이 없을 때, 큰 메모리의 `native` 메소드가 호출될 때 등이 있습니다.
  - 이를 해결하기 위해 dump 파일 분석, `JVM` 옵션 수정 등이 있습니다.



### - Exception

[![img](https://github.com/GimunLee/tech-refrigerator/raw/master/Language/JAVA/resources/java-error-exception-002.png)](https://github.com/GimunLee/tech-refrigerator/blob/master/Language/JAVA/resources/java-error-exception-002.png)

예외는 개발자가 구현한 로직에서 발생하며 개발자가 다른 방식으로 처리가능한 것들로 `JVM`은 정상 동작합니다.



## Exception의 2가지 종류

1. `Checked Exception`

   - 예외처리가 필수이며, 처리하지 않으면 컴파일 되지 않습니다.
   - JVM 외부와 통신(네트워크, 파일시스템 등)할 때 주로 쓰입니다.

   - **Runtime Exception** 이외에 있는 모든 예외
   - **IOException, SQLException** 등

2. `Unchecked Exception`

   - 컴파일 때 체크되지 않고 Runtime에 발생하는 Exception
   - **RuntimeException** 하위의 모든 예외
   - **NullPointerException,  IndexOutOfBoundException** 등



## 대표 Exception Class

- `NullPointerException` : NULL 레퍼런스를 참조할 때 발생, 뭔가 동작시킬 때 발생합니다.
- `IndexOutOfBoundsException` : 배열과 유사한 자료구조(문자열, 배열, 자료구조)에서 범위를 벗어난 인덱스 번호를 사용할 때 발생합니다.
- `FormatException` : 문자열, 숫자, 날짜 변환시 잘못된 데이터 (ex. "123A" -> 123으로 변환 시)로 발생하며, 보통 사용자의 입력, 외부 데이터 로딩, 결과 데이터의 변환 처리에서 자주 발생합니다.
- `ArthmeticException` : 정수를 0으로 나눌 때 발생합니다.
- `ClassCastException` : 변환할 수 없는 타입으로 객체를 변환할 때 발생합니다.
- `IllegalArgumentException` : 잘못된 인자 전달 시 발생합니다.
- `IOException` : 입출력 동작 실패 또는 인터럽트 시 발생합니다.
- `IllegalStateException` : 객체의 상태가 메소드 호출에는 부적절한 경우에 발생합니다.
- `ConcurrentModificationException` : 금지된 곳에서 객체를 동시에 수정하는 것이 감지될 경우 발생합니다.
- `UnsupportedOperationException` : 객체가 메소드를 지원하지 않는 경우 발생합니다.



## 주요 Method

- `printStackTrace()` : 발생한 `Exception`의 출처를 메모리상에서 추적하면서 결과를 알려줍니다. 발생한 위치를 정확히 출력해주기 때문에 가장 많이 사용되고 **void를 반환**합니다.
- `getMessage()` : 한줄로 요약된 메세지를 **String으로 반환**합니다.
- `getStackTrace()` : `JDK1.4` 부터 지원하며 `printStackTace()`를 보완합니다. **StackTraceElement[]**라는 문자열 배열로 변경해서 출력하고 저장합니다.



## Exception Handling

JAVA에서 모든 예외가 발생하면 `~~~Exception` 객체를 생성합니다. 예외를 처리하는 방법에는 크게 2가지가 있습니다.

1. 직접 `try ~ catch`문을 사용해서 예외에 대한 최종적인 책임을 지고 처리하는 방식
2. `throws Exception`을 이용해서 발생한 예외의 책임을 호출하는 쪽이 책임지도록 하는 방식
   (주로 호출하는 쪽에 예외를 보고할 때 사용)

다른 메소드의 일부분으로 동작하는 경우엔 던지는(`throws`) 것을 추천합니다.

### 1. Try ~ Catch

로직 중에 예외가 발생할지도 모르는 부분에 `try ~ catch` 구문으로 보험처리를 해줍니다.

- `try` 에는 위험한 로직이 들어가고, catch에는 예외 발생시 수행할 로직이 들어갑니다.
- `try` 중이라도 예외가 발생한 다음의 코드들은 실행되지 않으며 바로 `catch`구문으로 넘어갑니다.
- `catch` 구문은 `else if` 처럼 여러 개 쓸 수 있습니다.
- `finally`는 마지막에 실행하고 싶은 로직이 들어가며, 대표적으로 `.close()`가 있습니다.

### 2. Throws

예외 처리를 현재 메소드가 직접 처리하지 않고 호출한 곳에다가 예외의 발생 여부를 통보합니다.

호출한 메소드는 이걸 또 던질건지 직접 처리할 건지 정해야 합니다. (return보다 강력합니다.)

```java
public class ThrowsEx {
    public void call_A() throws Exception {
        call_B();
    }
    
    private void call_B() throws Exception {
        call_C();
    }
    
    private void call_C() throws Exception {
        System.out.println(1 / 0);
    }
    
    public static void main(String[] args) throws Exception {
        ThrowsEx test = new ThrowsEx();
        test.call_A();
    }
}
```

```bash
# 실행결과
Exception in thread "main" java.lang.ArithmeticException: / by zero
    at exception.ThrowsEx.call_C(ThrowsEx.java:13)
    at exception.ThrowsEx.call_B(ThrowsEx.java:9)
    at exception.ThrowsEx.call_A(ThrowsEx.java:5)
    at exception.ThrowsEx.main(ThrowsEx.java:18)
```

# JAVA Stream

---

[위로](#java)

> JAVA 8 이상부터 Stream API를 지원합니다.

JAVA에서도 8버전 이상부터 람다를 사용한 함수형 프로그래밍이 가능해졌습니다.

기존에 존재하던 `Collection`과 `Stream`은 무슨 차이가 있을까요?

=> 바로 **`데이터 계산 시점`** 입니다.



## Collection

- 모든 값을 메모리에 저장하는 자료구조.
- 따라서, Collection에 추가하기 전에 미리 계산이 완료되어 있어야 합니다.
- **외부 반복**을 통해 사용자가 직접 반복 작업을 거쳐 요소를 가져올 수 있습니다. (`for-each`)

## Stream

- 요청할 때만 요소를 계산합니다.
- **내부 반복**을 사용하므로 추출 요소만 선언해주면 알아서 반복 처리를 진행한다.
- 스트림에 요소를 따로 추가 혹은 제거하는 작업은 불가능하다.

*`Collections`은 핸드폰에 음악 파일을 미리 저장하여 재생하는 플레이어라면, `Stream`은 필요할 때 검색해서 듣느 멜론과 같은 음악 어플이라고 생각하면 됩니다!*

### 외부반복 & 내부반복

`Collection`은 외부 반복, `Stream`은 내부반복 입니다. 그 차이를 알아봅시다.

**성능 면에서는 내부 반복이 비교적으로 좋습니다.**

내부 반복은 작업을 병렬 처리하면서 최적화된 순서로 처리해줍니다.

하지만, 외부반복은 명시적으로 컬렉션 항목을 하나씩 가져와서 처리해야 하기 때문에 최적화에 불리합니다.

즉, `Collection`에서 병렬성을 이용하려면 직접 **`synchronized`**를 통해 관리해야만 한다.

[![img](https://camo.githubusercontent.com/cafbe058a588b4c8e9e382f0fba3e93fabcced8da8ad0f3125c607a613799d8a/68747470733a2f2f6d656469612e766c70742e75732f696d616765732f6164616d322f706f73742f35656361623839612d346336302d346261362d626333362d3361353839313564386231622f696d6167652e706e67)](https://camo.githubusercontent.com/cafbe058a588b4c8e9e382f0fba3e93fabcced8da8ad0f3125c607a613799d8a/68747470733a2f2f6d656469612e766c70742e75732f696d616765732f6164616d322f706f73742f35656361623839612d346336302d346261362d626333362d3361353839313564386231622f696d6167652e706e67)



### Stream 연산

스트림은 연산 과정이 '중간'과 '최종'으로 나누어집니다.

`filter, map, limit` 등 **파이프라이닝**이 가능한 연산을 **중간 연산**
`count, collect` 등 스트림을 닫는 연산을 **최종 연산** 이라고 합니다.

둘로 나누는 이유는, 중간 연산들은 스트림을 반환해야 하는데, 모두 한꺼번에 병합하여 연산을 처리한 다음 최종 연산에서 한꺼번에 처리하게 된다.

ex) Item 중에 가격이 1000 이상인 이름을 5개 선택한다.

```java
List<String> itmes = item.steram()
    					.filter(d -> d.getPrices() >= 1000)
    					.map(d -> d.getName())
    					.limit(5)
    					.collect(tpList());
// filter와 map은 다른 연산이지만, 한 과정으로 병합됩니다.
```

만약 Collection 이었다면, 우선 가격이 1000 이상인 아이템을 찾은 다음, 이름만 따로 저장한 뒤 5개를 선택해야 합니다.

연산 최적화는 물론, 가독성 면에서도 `Stream`이 더 좋습니다



#### - Stream 중간연산

> 중간연산은 모두 Stream을 반환합니다.

- `filter(Predicate)`  : Predicate를 인자로 받아 true인 요소를 포함한 스트림 반환
- `distinct()` : 중복 필터링
- `limit(n)` : 주어진 사이즈 이하 크기를 갖는 스트림 반환
- `skip(n)` : 처음 n개의 요소를 제외한 스트림 반환
- `map(func)` : 매핑 함수의 result로 구성된 스트림 반환
- `flatMap()` : 스트림의 콘텐츠로 매핑합니다. map과 달리 평면화된 스트림 반환

#### - Stream 최종연산

- `(boolean) allMatch(Predicate)` : 모든 스트림 요소가 Predicate와 일치하는지 검사
- `(boolean) anyMatch(Predicate)` : 하나라도 일치하는 요소가 있는지 검사
- `(boolean) noneMatch(Predicate)` : 매치되는 요소가 없는지 검사
- `(Optional) findAny()` : 현재 스트림에서 임의의 요소 반환
- `(Optional) findFirst()` : 스트림의 첫번째 요소 반환
- `reduce()` : 모든 스트림 요소를 처리해 값을 도출. 두개의 인자를 가짐
- `collect()` : 스트림을 reduce하여 list, map, 정수 형식 컬렉션을 만듦
- `(void) forEach()` : 스트림의 각 요소를 소비하며 람다 적용
- `(Long) count` : 스트림의 요소 개수를 반환

```bash
# Optional Class 란 ?
> 값의 존재나 여부를 표현하는 컨테이너 Class
- NULL로 인한 버그를 막을 수 있는 장점이 있다.
- isPresent() : Optional이 값을 포함할 때 TRUE를 반환
```



### Stream 활용 예제

1. `map()`

   ```java
   List<String> names = Arrays.asList("Sunghyun", "Insun", "Jaemin", "Seungjae");
   
   names.stream()
       .map(name -> name.toUpperCase())
       .forEach(name -> System.out.println(name));
   ```

2. `filter()`

   ```java
   List<String> startWithN = names.stream()
       .filter(name -> name.startsWithN("S"))
       .collect(Collectors.toList());
   ```

3. `reduce()`

   ```java
   Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
   Optional<Integer> sum = numbers.reduce((x, y) -> x + y);
   sum.ifPresent(s -> System.out.println("sum: " + s));
   // 55
   ```

4. `collect()`

   ```java
   System.out.println(names.stream()
                     .map(String::toUpperCase)
                     .collect(Collectors.joining(", ")));
   ```

   