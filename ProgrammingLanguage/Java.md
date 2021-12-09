# **Java**

>   참고 : https://github.com/gyoogle/tech-interview-for-developer
>
>   전반적인 흐름은 gyoogle님의 깃허브를 참고하였으며, 추가 레퍼런스는 글을 진행하며 알려드릴 것입니다. 파이팅 !

- [자바 컴파일 과정](#자바-컴파일-과정)
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
- [Stream](#stream)
- [Annotaion](#annotation)
- [Generic](#generic)
- [Final keyword](#final)
- [함수형 프로그래밍](#함수형-프로그래밍)
- [익명 클래스보다는 람다](#익명-클래스보다는-람다)
- [lambda 와 effectively final](#lambda-와-effectively-final)
- [Overriding vs Overloading](#overriding-vs-overloading)
- [Access Modifier](#access-modifier)
- [Optional Class](#optional-class)
- [일급컬렉션](#일급컬렉션)
- [자바의 예외](#자바의-예외에-대해서)

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
-   문자열 연산이 많이 일어나는 경우 더 이상 참조되지 않는 기존 객체는 **Garabage Collector**에 의해 제거되어야 하기 때문에 성능이 좋지 않습니다.
-   객체가 불변하므로, 멀티쓰레드에서 동기화를 신경 쓸 필요가 없습니다.(조회 연산에 매우 큰 장점!)
-   객체가 가지는 값마다 새로운 객체가 필요하기 때문에, 메모리 누수와 새로운 객체를 계속 생성해야하기 때문에 성능저하를 발생시킬 수 있습니다.
-   String은 reference 타입입니다.

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
            -   **primitive type이 스택에 저장되고, reference type은 참조값(인스턴스의 주소값)만 저장.**
        -   네이티브 메서드 스택
            -   실제 실행할 수 있는 기계어로 작성된 프로그램을 실행시키는 영역
        -   힙
            -   런타임에 동적으로 할당되는 데이터가 저장되는 영역
            -   객체나 배열 생성이 여기에 해당
            -   **stack 영역에서 참조값을 이용해 참조형 변수가 heap영역에 있는 인스턴스를 가리켜 제어한다.**
            -   **어떠한 참조 변수도 힙 영역의 인스턴스를 참조하지 않는다면 GC에 의해서 소멸된다.**
        -   메서드 영역
            -   JVM이 시작될 때 생성되고, JVM이 읽은 각각의 클래스와 인터페이스에 대한 런타임 상수 풀, 필드 및 메서드 코드, 정적 변수, 메서드의 바이트 코드 등을 보관
            -   패키지나 클래스는 프로그램 시작과 동시에 모두 올라가는 게 아니라, 실제로 호출될 때 올라간다.
            -   class 영역 혹은 method영역, static 영역으로 불리운다.
            -   static 영역에 자리잡게 되면 JVM이 종료될 때까지 사라지지 않고, 고정된(static) 상태로 유지.
            -   static 영역에 있는것은 어떤 곳에서나 접근이 가능해지기 때문에 "전역"이라는 키워드를 사용한다.
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

>   Promotion
>
>   캐스팅이 자동으로 발생 (업캐스팅)
>
>   작은 타입이 큰 타입으로 변환

```java
Parent p = new Child(); 
```

-   (Parent) new Child()할 필요가 없다.
-   Parent를 상속받은 Child는 Parent의 속성을 포함하고 있기 때문에

### 명시적 형변환

>   Casting
>
>   캐스팅할 내용을 적어줘야 하는 경우 (다운캐스팅)
>
>   큰 타입을 작은타입으로 변환

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



## 묵시적 형변환과 바인딩

작은 타입이 큰 타입으로 변환될 때 데이터 앞에 따로 타입을 명시하지 않아도 자동으로 형변환 되는 것이 묵시적 형변환(`Promotion`)이라고 했다.

```java
int a = 10;
float b;

b = a;
```

이는 객체에서도 타입변환이 가능하다.

```java
// Subclass는 Superclass를 상속 받고 있는 상태
Superclass var = new Subclass();
```

여기서 `JVM ` 지식이 필요한데, `var` 변수는 메모리의 어디영역에 저장되고, `new` 키워드로 생성된 `Subclass`는 메모리의 어디 영역에 저장되는지 알고있어야 한다.

`var`는 메모리의 **`Stack Area`**에 저장되며 `Subclass`는 **`Heap Area`**에 저장된다.

그리고, `Stack Area`에 저장된 `var`는 `Heap Area`의 `Subclass`를 가리킨다.

하지만, var변수로 접근가능한 멤버는 Superclass이다.

만약, 하위 클래스에서 상위 클래스의 메서드를 오버라이딩해서 구현한 상태면 어떻게 될까요? var 변수로 접근가능한 멤버가 Superclass니까 부모메서드가 호출될까요?

정답은 **NO**입니다.



이것은 또 예외가 있는데, 바로 **하위 클래스에서 상위 클래스의 메서드를 오버라이딩하여 구현한 경우에는 `var`변수가 오버라이딩한 자식 클래스의 메서드를 호출하게 된다.**

하지만, 예외가 하나 더 있다. 바로 상위 클래스에 **`static`** 메서드가 전언된 경우이다.

여기서 **`동적 바인딩(Dynamic Binding)`**과 **`정적 바인딩(Static Binding)`** 개념이 등장하는데 이걸 또 이해해야 한다.
예시를 보자.

```java
class Polymorphism {
    public static void main(String[] args) {
        SuperClass var = new SubClass(); // Promotion : 자동 타입 변환, Polymorphism
        // 동적 바인딩(Dynmaic Binding)
        var.methodA(); // Runtime 시에 결정된다. SubClass의 메서드 호출
        // 정적 바인딩(Static Binding)
        var.staticMethodA(); // static 메서드는 compile 시에 결정, SuperClass의 메서드 호출
    }
}
class SuperClass {
    public void methodA();
    public static void staticMethodA();
}

class SubClass extends SuperClass {
    @Override
    void methodA() {
        System.out.println("SubClass");
    }
    
    /*
    	아래 코드는 Error 발생
    	static 으로 선언된 메서드는 오버라이딩 불가능
    */
    @Override
    static void staticMethodA() {
        System.out.println("SubClass");
    }
}
```

위 코드를 보면 상위 클래스를 만들고 하나는 인스턴스 메서드 하나는 정적 메서드를 만들고 하위 클래스는 상위 클래스를 상속받아서 메서드 오버라이딩 하는 코드이다.

주석을 보면 알 수 있듯이, **`static` 키워드로 선언된 메서드는 하위클래스에서 오버라이딩이 불가능하다.**

그 이유는, 동적 바인딩(Dynamic Binding)은 실행시에 성격이 결정되고 정적 바인딩(Static Binding)은 컴파일시에 성격이 결정되는데, `static` 키워드가 붙은 애들은 `JVM`에서 객체가 생성되기 전에 먼저 메모리에 올리기 때문에, 객체가 생성되지 않아도, `클래스명.변수명 혹은 클래스명.메소드명` 으로 접근이 가능했던 이유가 이것이다.

`static` 변수의 값 할당은 `JVM`의 클래스 로더 시스템의 과정(로딩, 링크, 초기화) 중 **초기화** 과정에서 진행된다.

> 동적바인딩은 런타임(Runtime, 실행) 시점에 객체 타입을 기준으로 실행될 함수를 호출하는 것을 의미하고
> 정적바인딩은 컴파일(Compile) 시점에 객체 타입을 기준으로 실행될 함수를 호출하는 것을 의미한다.

따라서 `static` 메서드는 `new Subclass()`가 메모리에 등록되기 전에 생성되기 때문에 오버라이딩 자체가 불가능 한 것이다.
반면 `instance` 메서드는 런타임 시에 성격이 결정되기 때문에 `var`변수로 하위 클래스의 오버라이딩 된 메서드를 호출할 수 있게 되는 것이다.



## 명시적 형변환

명시적 형변환은 큰 타입을 작은 타입으로 바꿔야 하는 경우에, 데이터 앞에 타입을 '명시'해줌으로써 타입 변환이 가능하게 하는 기법이다.

원시 타입(Primitive Type)의 경우에는 데이터 앞에 타입만 명시하면 바꿀 수 있다.

```java
int a;
float b = 1.1;

a = (int) b;
```

문제는 객체간의 Casting인데 예제로 보자.

```java
public void casting(Parent parent) {
    if (parent instanceof Child) {
        Child child = (Child) parent; // Casting
    }
}
```

객체간 Casting을 하기 위해서는 항상 **`instanceof`**를 사용하여 상속관계에 있는지 확인해야 한다.

상속관계에 있지 않은 객체를 형변환 하려면 에러가 발생한다.



# Java에서의 Thread 활용

---

[위로](#java)

요즘 OS는 모두 멀티태스킹을 지원한다.

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

  - short는 C언어와의 호환을 위해 사용되는 타입으로 잘 사용되지 않는 타입입니다.

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



*++ 2021-11-29 추가내용*

### > String Constant Pool

**동일하다(`==`)**는 두 개의 실제 인스턴스가 완전히 같을 경우(메모리 주소값이 같음)이고
**동등하다(`eqauls`)**는 두 개의 값이 같다라는 의미이다.

```java
String s1 = new String("aaa");
String s2 = new String("aaa");

System.out.println(s1 == s2); // false
System.out.println(s1.equlas(s2)); // true
```

위의 예에서 s1과 s2는 각각 `new` 연산자로 새로운 오브젝트 메모리에 생성되었기 때문에 두 주소는 당연히 동일하지 않다.

하지만 String 클래스는 eqauls 메소드가 오버라이딩 되어있으므로 같은 문자열이기 때문에 true가 출력된다.



하지만?



```java
String s1 = "aaa";
String s2 = "aaa";

System.out.println(s1 == s2); // true
System.out.println(s1.equals(s2)); // true
```

자바에서는 String에게 `new` 연산자가 아니라 primitive 타입 변수를 선언하듯이 문법적으로 허락하고 있다.

그리고 실제로 값을 비교해보면 primitive 타입 변수와 같이 비교가 가능하다.



**자바에서는 이처럼 문자열 상수에 대해서 문자열이 동일한 경우 하나의 인스턴스만 생성하고 이를 공유하도록 한다.**

이 때 문자열이 저장되는 곳이 바로 **`String Constant Pool`**이다. 이렇게 생성된 String 값은 Heap 영역 내에 있는 `String Constant Pool`에 저장되어서 재사용된다.

하지만, new 연산자로 생성하면 같은 내영이라도 여러 개의 객체가 각각 Heap 영역을 차지하기 때문에 `new` 연산자로 생성하지 않는 것이 효율적이다.



primitive type 처럼 생성하는 것을 **String literal로 생성한다**고 하는데, 이렇게 생성한 객체의 값(ex. "aaa")이 이미 String pool에 존재한다면 해당 객체는 String pool의 reference를 참조하기 때문에 s1과 s2는 같은 곳을 가리키고 있는 것이다.



이 **String Constant Pool**의 위치는 Java6까지는 `Perm` 영역 이었지만 Java7에서 `Heap`영역으로 변경되었다. 그 덕분에 바로 String Constant Pool의 모든 문자열도 GC의 대상이 될 수 있게 되어 성능이 높아졌다고 할 수 있다.

`Perm`영역에 있었던 시절에는 String Pool에 문자열 객체가 많이 생성된다거나 이 영역이 가득 차게 되면 런타임 환경에서는 메모리를 동적으로 늘리지 못해 `OutofMemory`에러가 발생했다. 하지만 Java7 이후 부터는 `OOM`가 발생 위험을 줄였다고 한다.

(*`new`로 생성해도 일반 객체들과 마찬가지로 String Pool이 아닌 `Heap`의 영역에 생성된다.*)



> 결론은 String 객체를 `new` 연산자로 생성하면 같은 값이라 할지라도 Heap영역에 매번 새로운 객체가 생성된다. 따라서 String이 갖는 불변성이라는 장점을 누리지 못한다!
>
> 따라서 메모리를 효율적으로 사용하기 위해서는 **String literal(큰 따옴표)로 생성하는 것이** 좋다!.



## Wrapper Class

**기본 자료형(Primitive data type)**에 대한 클래스 표현을 `Wrapper Class`라고 한다.

`Integer`, `Float`, `Boolean` 등이 있다.

int를 `Integer`라는 객체로 감싸서 저장해야 하는 이유가 있을까?

- 일단 컬렉션에서 제네릭을 사용하기 위해서는 Wrapper Class를 사용해야 한다.
- 또한, `NULL`값을 반환해야만 하는 경우에는 return type을 Wrapper Class로 지정하여 `NULL`을 반환할 수 있도록 할 수 있다.

하지만, 이러한 상황을 제외하고 일반적인 상황에서 `Wrapper Class`를 사용해야 하는 이유는 객체지향적인 프로그램을 위한 프로그래밍이 아니고서야 없다.

일단 해당 **값을 비교할 때, `Primitive data type`인 경우에는 `==`로 바로 비교할 수 있다.**

하지만, `Wrapper Class`인 경우에는 `.intValue()` 메소드를 통해 해당 Wrapper class의 값을 가져와 비교해줘야 한다.

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

# Stream

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

   

# Annotation

---

[위로](#java)

**`Annoation`**이란 본래 주석이란 뜻으로, 인터페이스를 기반으로 한 문법이다. 주석과는 그 역할이 다르지만 주석처럼 코드에 달아 클래스에 특별한 의미를 부여하거나 기능을 주입할 수 있다.

또 해석되는 시점을 정할 수 있다.(**Retention Policy**)

어노테이션에는 크게 세 가지 종류가 존재한다.

- `built-in annotation` : JDK에 내장 되어 있음. 상속받아서 메소드를 오버라이드할 때 나타나는 `@Override` 어노테이션이 대표적이다.
- `Meta annotation` : 어노테이션에 대한 정보를 나타내기 위한 어노테이션
- `Custom annotation` : 개발자가 직접 만든 어노테이션

# Generic

---

[위로](#java)

**`Generic`**은 자바에서 **안정성**을 맡고 있다고 할 수 있다.

다양한 타입의 객체들을 다루는 메서드나 컬렉션 클레스에서 사용하는 것으로, **컴파일 과정에서 타입체크를 해주는 기능이다.**

객체의 타입을 컴파일 시에 체크하기 때문에 객체의 타입 안정성을 높이고 형변환의 번거로움을 줄여준다.

자연스럽게 코드도 더 간결해진다.

예를 들면, `Collection`에 특정 객체만 추가될 수 있도록, 또는 특정한 클래스의 특징을 갖고 있는 경우에만 추가될 수 있도록 하는 것이 **제네릭**이다.

이로 인한 장점은 `Collection` 내부에서 들어온 값이 내가 원하는 값인지 별도의 로직 처리를 구현할 필요가 없어진다. 또한, API를 설계하는데 있어서 보다 명확한 의사전달이 가능해진다.



정리를 해보자.

**Generic의 장점**

1. 제네릭을 사용하면 잘못된 타입이 들어올 수 있는 것을 컴파일 단계에서 방지할 수 있다.
2. 클래스 외부에서 타입을 정해주기 때문에 따로 타입을 체크하고 변환해줄 필요가 없어서 관리하기가 편하다.
3. 비슷한 기능을 지원하는 경우에 코드의 재사용성이 높아진다.



## Generic 사용방법

| 타입 | 설명    |
| ---- | ------- |
| <T>  | Type    |
| <E>  | Element |
| <K>  | Key     |
| <V>  | Value   |
| <N>  | Number  |

제네릭은 암묵적으로 위와 같은 규칙으로 쓰이게 된다.

## 1. 클래스 및 인터페이스 선언

```java
public class ClassName <T> {...}
public interface InterfaceName <T> {...}
```

기본적으로 제네릭 타입의 클래스나 인터페이스의 사용법이다.

이 `<T>` 타입은 해당 블럭에서까지 유효하게 사용가능하다.



여기서 제네릭 타입을 두개로 둘 수 도 있다.
(대표적으로 타입 인자를 두 개 받는 컬렉션인 `HashMap`)

```java
public class ClassName <T, K> {...}
public interface InterfaceName <T, K> {...}

public class HashMap <K, V> {...}
```

이렇듯 데이터 타입을 미리 정해놓는 것이 아니라 외부로부터 지정할 수 있도록 하는 것이다.



그럼 어떻게 사용하는지 보자.

```java
public class ClassName <T, K> {...}

public class Main {
    public static void main(String[] args) {
        ClassName<String, Integer> c = new ClassName<String, Integer>();
    }
}
```

이 때 주의할 점은 타입으로 **참조 타입(Reference Type)**밖에 올 수 없다.

즉, int나 double, char와 같은 기본 타입(primitive type)은 올 수가 없다. 기본형을 사용하려면 Wrapper Class로 사용해야 한다.



## 2. 제네릭 클래스 사용

```java
class ClassName<E> {
    private E element; // 변수타입
    
    void set(E element) {	// 파라미터타입
        this.element = element;
    }
    
    E get() {	// 반환타입
        return element;
    }
}
class Main {
    public static void main(String[] args) {
        
        ClassName<String> a = new ClassName<String>();
        ClassName<Integer> b = new ClassName<Integer>();
        
        a.set("10");
        b.set(10);
    }
}
```

여기서 제네릭 타입을 두개쓰고 싶으면 두개를 사용하면 된다.



## 3. 제네릭 메소드

2번 과정은 클래스 이름 옆에 `<E>` 제네릭 타입을 붙여서 해당 클래스 내에서 사용하는 E 타입으로 일반화를 했다.

그 외에도 별도로 메소드에 한정된 제네릭도 사용할 수 있다.



일반적으로 다음과 같이 선언한다.

```java
public <T> T genericMethod(T o) {
    ...
}
```

일반 메소드와는 다르게 반환 타입 이전에 <GenericType>을 선언한다.



이를 위에서 다른 클래스에서 활용해보자.

```java
class ClassName<E> {
    // 동일
    
    <T> T genericMethod(T o) { // 제네릭 메소드
        return o
    }
}
public class Main {
    public static void main(String[] args) {
        ClassName<String> a = new ClassName<String>();
        ClassName<Integer> b = new ClassName<Integer>();

        a.set("10");
        b.set(10);
    }
}
```

ClassName 객체를 생성할 때 `<> ` 안에 타입 파라미터를 지정한다.

그러면 a객체의 ClassName의 E 제네릭 타입은 모두 String으로 변환되고,

b객체의 ClassName의 E 제네릭 타입은 모두 Integer로 변환된다.

따라서, **만들어준 `<T> T genericMethod(T o)`는 이 파라미터 타입에 따라서 T 타입이 결정된다.**



즉, 클래스에서 지정한 제네릭 유형과 별도로 메소드에서 독립적으로 제네릭 유형을 선언해서 쓸 수가 있다는 말이다.



왜 이게 필요한지 생각해보면 바로 **정적 메소드로 선언할 때 필요**하기 떄문이다.

제네릭은 유형을 외부에서 지정한다고 했는데, 즉 클래스 객체가 인스턴스화 했을 때, 쉽게 말해서 new 생성자로 클래스 객체를 생성하고 `<>` 괄호 사이에 파라미터로 넘겨준 타입으로 지정이 된다는 뜻이다.



하지만 static은 ? 정적이라는 뜻이다. **static이 붙은 것들은 기본적으로 프로그램 실행시에 메모리에 이미 올라가 있는 상태이다.**



**이 말은 객체 생성을 통해 접글할 필요 없이 이미 메모리에 올라가 있기 때문에 클래스 이름을 통해 바로 쓸 수 있다는 말이다.**



아니 그렇다면, static 메소드는 프로그램 실행시에 미리 메모리에 올라가는데 타입을 어디서 정해주는건가???

```java
class ClassName<E> {
    /*
    클래스와 같은 E 타입이어도
    static 메소드는 객체가 생성되기 이전 시점에
    메모리에 먼저 올라가기 때문에
    E라는 유형을 클래스로부터 얻어올 방법이 없다..
    */
    static E genericMethod(E o) { // 에러가 발생한다.
        return o;
    }
}

public class Main {
    public static void main(String[] args) {
        // static으로 인해서 객체가 생성되기 이전에 접근을 할 수는 있으나 유형을 지정할 방법이 없어서 에러발생
        ClassName.genericMethod(3);
    }
}
```

때문에, **제네릭이 사용되는 메서드를 정적메서드로 사용하고 싶은 경우에는 제네릭 클래스와 별도로 독립적인 제네릭이 사용되어야 한다**는 것이다!!!

```java
class ClassName<E> {
    private E element;
    
    ...
        
    // 이 메소드의 E, T 타입은 제네릭 클래스의 E타입과는 다른 독립적인 타입이다.
    static <E> E genericMethod1(E o) {
        return o;
    }
    
    static <T> T genericMethod2(T o) {
        return o;
    }
}
```

**제네릭 메소드 타입은 제네릭 클래스 타입과 별도로 지정해주는 것, `<>` 괄호 안에 타입을 파라미터로 보내서 제네릭 타입을 지정해주는 것이 바로 제네릭 프로그래밍이다.**



여기에 특정 범위만 허용하고 나머지 타입은 제한할 수도 있다.



## 4.제한된 Generic과 와일드 카드

지금까지는 제네릭의 가장 일반적인 예시였고, 만약 특정 범위내로 좁혀서 제한하고 싶다면 어떻게 해야할까?



이 때 필요한 것이 바로 `extends`와 `super` 그리고 `?`이다. `?`는 와일드라고 해서 쉽게 말해 **알수 없는 타입**이라는 의미이다.

먼저 예시를 보면 크게 세가지 방식이 있다.

```java
<K extends T> // T와 T의 자손타입만 가능(K는 들어오는 타입으로 지정) : T가 최상위 타입
<K super T> // T와 T의 부모(조상)타입만 가능(K는 들어오는 타입으로 지정) : T가 최하위 타입
    
<? extends T> // T와 T의 자손타입만 가능
<? super T> // T와 T의 부모(조상)타입만 가능
<?> // 모든 타입 가능 == <? extends Object>
```

쉽게 말해서

`extends T` : 상한경계
`? super T` : 하한경계
`<?>` : 와일드 카드

라고 부른다.



이 떄 주의해야 할 것은 `K extends T`와 `? extends T`는 비슷하지만 다른점이 있다.

유형의 경계를 지정하는 것은 같지만 **경계가 지정되고 K는 특정 타입으로 지정되지만 ?는 타입이 지정되지 않는다.**



제네릭의 단점은 처음 마주했을 때 코드를 이해하고 분석하기가 어렵고, 계층구조가 복잡해지면 어려워진다는 특징이 있다.

때문에, 제네릭에 대한 이해를 정확하게 잡는 것이 좋아보인다.



참조

https://st-lab.tistory.com/153



# final

---

[위로](#java)

- `final class`

  : 다른 클래스에서 상속하지 못한다.

- `final method`

  : 다른 메소드에서 오버라이딩 하지 못한다.

- `final variable`

  : 변하지 않는 상수값이 되어 새로 할당할 수 없는 변수가 된다.

혼동하기 쉬운 `finally`와 `finalize()`도 살펴보자.

- `finally`
  - `try-catch` or `try-catch-resource` 구문을 사용할 때, 정상적으로 작업을 한경우와 에러가 발생했을 경우를 포함하여 마무리 해줘야하는 작업이 존재하는 경우 해당 코드를 작성해주는 코드블럭이다.
- `finalize()`
  - keyword도 아니고 code block도 아닌 **method**이다.
  - `GC`에 의해 함수로 절대 호출해서는 안되는 함수이다.
  - `Object` 클래스에 정의되어 있으며 `GC`가 발생하는 시점이 불분명하기 때문에 해당 메소드가 실행된다는 보장이 없다.
  - 또한, `finalize()` 메소드가 오버라이딩 되어 있으면 GC가 이루어질 때 바로 Garbage Collection 되지 않는다. GC가 지연되면서 OOME(Out out Memory Exception)이 발생할 수 있다.

# 함수형 프로그래밍

---

프로그래밍의 패러다임은 프로그래머에게 프로그래밍의 관점을 갖게 하고 어떻게 코드를 작성할지 결정하게 하기 때문에 큰 역할을 한다고 볼 수 있다.

> - **명령형 프로그래밍** : 무엇(What)을 할 것인지 보다는, 어떻게(How)할 건지 설명
>   - 절차적 프로그래밍 (Tow-Down)
>     - 단순히 순차적으로 프로그래밍 하는 것이 아니라, 프로시저 콜이라는 함수 호출을 통해 명령을 수행하는 것. (C, C++)
>   - 객체지향 프로그래밍
>     - 실제 세계를 모델링하여 객체 간의 상호작용을 나타내는 개발 방법(C++, Java, C#)
>
> - **선언형 프로그래밍** : 어떻게(How)를 나타내기 보다 무엇(What)을 할 건지 설명
>   - 함수형 프로그래밍
>     - 순수 함수를 조합하고 소프트웨어를 만드는 방식(클로저, 하스켈, 리스프)

## > 함수형 프로그래밍의 등장

명령형 프로그래밍을 기반으로 개발했던 개발자들은, 소프트웨어의 크기가 커짐에 따라서 복잡하게 엉켜있는 스파게티 코드를 유지보수하는 것이 매우 힘들다는 것을 깨닫게 되었다.

이를 해결하기 위해서 `함수형 프로그래밍`이라는 새로운 패러다임에 관심을 갖게 된다.

함수형 프로그래밍은 거의 모든 것을 **순수 함수**로 나누어서 문제를 해결하는 기법으로, 작은 문제를 해결하기 위한 함수를 작성해 **가독성을 높이고 유지보수를 용이**하게 해주는 것이 장점이다.

> 클린 코드(Clean Code)의 저자 Robert C.Martin은 함수형 프로그래밍을 대입문이 없는 프로그래밍으로 정의하였다.
>
> *Functionl Programming is programming without assignment statements.*



위와 같이 함수형 프로그래밍은 **대입문을 사용하지 않는 프로그래밍이며, 작은 문제를 해결하기 위한 함수를 작성**한다고 하였다.

명령형 프로그래밍에서는 메소드를 호출하면 상황에 따라 내부의 값이 바뀔 수 있다. 즉, 우리가 개발한 함수 내에서 선언된 변수의 메모리에 할당된 값이 바뀌는 등의 변화가 생길 수 있다.

하지만, 함수형 프로그래밍에서는 대입문이 없기 때문에 한 번 할당된 값은 새로운 값으로 변할 수 없다는 특징이 있다.

함수형 프로그래밍의 기본 원리는 함수를 1급 시민(First-Class Citizen) 또는 1급 객체(First-Class Object)로 관리한다.



함수형 프로그래밍의 특징을 한줄로 요약하면 이와 같다.

> **부수 효과가 없는 순수 함수 1급 객체로 간주하여 파라미터로 넘기거나 반환값으로 사용할 수 있으며, 참조 투명성을 지킬 수 있다.**

여기서 부수 효과(Side Effect)란 다음과 같은 변화 또는 변화가 발생하는 작업을 의미한다.

- 변수의 값이 변경됨
- 자리에서 자료 구조를 수정함
- 객체의 필드값을 섲렁함
- 예외나 오류가 발생하여 실행이 중단됨
- 콘솔 또는 파일 I/O가 발생함



이러한 **부수 효과(Side Effect)를 제거한 함수들을 순수 함수(Pure Function)이라고 하며, 함수형 프로그래밍에서 사용하는 함수는 이러한 순수 함수들이다.**

- Memory나 I/O 관점에서 Side Effect가 없는 함수.
- 함수의 실행이 외부에 영향을 끼치지 않는 함수.



이러한 순수 함수(Pure Function)을 이용해서 얻을 수 있는 효과는 다음과 같다.

- 함수 자체가 독립적이며 Side Effect가 없기 때문에 안정적인 쓰레드를 보장할 수 있다.
- Thread가 안정하기 때문에 병렬 처리를 동기화 없이 진행할 수 있다.



또한, 1급 객체란 다음과 같은 것들이 가능한 객체를 의미한다.

- 변수나 데이터 구조 안에 담을 수 있고
- 파라미터로 전달할 수 있어야 하고
- 반환값으로 사용할 수 있어야 하며
- 할당에 사용된 이름과 문관하게 고유한 구별이 가능해야 한다.



함수형 프로그래밍에서 기본적으로 함수는 1급 객체로 취급받기 때문에 함수를 파라미터로 넘기는 작업이 가능하다.


*우리가 일반적으로 알고 개발했던 함수들은 함수형 프로그래밍에서 정의하는 순수 함수들과는 다르다는 것을 인지해야 한다.*

> **참조 투명성(Referential Transparency)**
>
> - 동일한 인자에 대해 항상 동일한 결과를 반환해야 한다.
> - 참조 투명성을 통히 기존의 값은 변경되지 않고 유지된다.(Immutable Data)



명령형 프로그래밍과 함수형 프로그래밍에서 사용하는 함수는 사이드이펙트의 유/무에 따라 차이가 있다. 그에 따라서 함수가 참조에 투명한지 안한지 나뉘어 지게 되는데, **참조에 투명하다는 것은 말 그대로 함수를 실행해도 어떠한 상태변화 없이 항상 동일한 결과를 반환하여 동일하게(투명하게) 실행 결과를 참조(예측)할 수 있다는 것을 의미**한다.



즉, 어떤 함수 `f`에 어떠한 인자 `x`를 넣고 `f`를 실행하게 되면, `f`는 입력된 인자에만 의존하므로 항상 `f(x)`라는 동일한 결과를 얻는다.

이처럼 **부작용을 제거해서 프로그램의 동작을 이해하고 예측을 용이하게 하는것**이 함수형 프로그래밍으로 개발하려는 핵심 동기 중 하나이다. 또한, 값의 대입 없이 항상 동일한 실행에 대해 동일한 결과를 반환하기 때문에, 병렬 처리 환경에서 개발할 때 `Race Condition`에 대한 비용을 줄여준다.



## > Java에서의 함수형 프로그래밍

자바에서 어떤 List에 저장된 단어들의 접두사가 각각 몇개씩 있는지 Map으로 저장하는 코드를 작성해보자.



### - 함수형 적용 X

```java
import java.util.*;

public class Main {
	private static List<String> Words = Arrays.asList("APPLE", "BANANA", "orange", "PINEAPPLE", "korea");
    
    private static Map<String, Integer> setPrefixFreq() {
        Map<String, Integer> map = new HashMap<>();
        String prefex;
        Integer cnt;
        for (String word : Words) {
            prefix = word.substring(0, 1);
            cnt = map.get(prefix);
            if (cnt == null) {
                map.put(prefix, 1);
            } else {
                map.put(prefix, cnt + 1);
            }
        }
        
        return map;
    }
    
    public static void main(String[] args) {
        final Map<String, Integer> map = setPrefixFreq();
        map.keySet.forEach(k -> System.out.println(k + ":" + map.get(k)));
    }
}
```

함수형 프로그래밍을 적용하지 않은 코드는 List에서 루프를 돌면서 접두사 하나 잘라내고 그 갯수를 Map에 저장한다.

이 코드에 함수형 프로그래밍을 적용하면 더욱 간결하고 가독성있는 코드로 변경할 수 있다.



### - 함수형 적용 O

Java는 대표적으로 객체지향 프로그래밍 언어이기 때문에 함수형으로 개발하기 위해서 별도의 도구가 필요하다.

JDK8에서부터 Stream API와 함수형 인터페이스(Functional Inteface)의 람다 등을 제공하기 때문에 Java 8 이상의 버전을 사용해야 함수형 프로그래밍을 사용할 수 있다.

```java
import java.util.*;

public class Main {
    private static List<String> Words = Arrays.asList("APPLE", "BANANA", "orange", "PINEAPPLE", "korea");
    
    private static Map<String, Integer> setPrefixFreq() {
        Map<String, Integer> map = new HashMap<>();
        Words.stream().map(w -> w.substring(0, 1)).forEach(prefix -> map.merge(prefix, 1, (v1, v2) -> (v2 += v1)));
        return map;
    }
    
    public static void main(String[] args) {
        final Map<String, Integer> map = setPrefixFreq();
        map.keySet.forEach(k -> System.out.println(k + ":" + map.get(k)));
    }
}
```

`stream()`을 통해 함수형 프로그래밍을 위한 Stream 객체를 생헝하고, `map()`을 통해 Stream 객체의 단어들을 prefix로 변형시키고 있다.

그리고 `forEach`로 prefix를 map에 추가하고 있다.



다음과 같은 추가 요구 사항이 생겼다고 가정해 보자.

- 단어의 크기가 2 이상인 경우에만 처리할 것.
- 모든 단어를 대문자로 변환하여 처리할 것.
- 스페이스로 구분한 하나의 문자열로 변환 할 것

이러한 요구사항을 기존의 코드에 반영한다면 상당이 코드가 길어지고 복잡해질테지만, 함수형 프로그래밍을 적용하면 비교적 간단하게 처리할 수 있다.

```java
import java.util.*;

public class Main {
    private static List<String> Words = Arrays.asList("APPLE", "BANANA", "orange", "PINEAPPLE", "korea");
    
    private static Map<String, Integer> setPrefixFreq() {
        return Words.stream().filter(w -> w.length() > 1).map(String::toUpperCase).map(w -> w.substring(0, 1)).collect(Collectors.joining(" "));
    }
    
    public static void main(String[] args) {
        final Map<String, Integer> map = setPrefixFreq();
        map.keySet.forEach(k -> System.out.println(k + ":" + map.get(k)));
    }
}
```



참조

https://mangkyu.tistory.com/111

# 익명 클래스보다는 람다

---

자바에서 함수 타입을 표현할 때는 **추상 메서드를 하나만 담은 인터페이스(또는 추상 클래스)**를 사용했다. 이러한 인터페이스의 인스턴스를 **함수 객체**라고 해서 특정 함수나 동작을 나타내는데 썼다.



## 익명 클래스

`JDK 1.1` 버전부터 함수 객체를 만들 때 **익명 클래스(Anonymous Class)**를 주로 사용했다. 하지만 익명 클래스 방식은 코드가 너무 길기 때문에 이 떄까지의 자바는 함수형 프로그래밍에 적합하지 않았다.

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> Words = Arrays.asList("APPLE", "Banana", "orange", "korea");
        
        Collections.sort(Words, new Comparator<String>() {
            public int compare(String s1, String s2) {
                return Integer.compare(s1.length(), s2.length());
            }
        });
    }
}
```



## 람다

`JDK 1.8` 버전부터 추상 메서드 하나 짜리 인터페이스, 즉 함수형 인터페이스를 말하는데 그 인터페이스의 인스턴스를 람다식(`lambda expression`)으로 사용해서 만들 수 있게 되었다.

> - 기본적인 람다식 구조
>
> ```java
> // ( 매개변수 ) -> { 표현식 };
> (int a, int b) -> { return a + b; }
> (String str) -> System.out.println(str);
> ```
>
> - 메소드 참조 표현식 -> **`::`**
>
> ```java
> // 람다식에서 파라미터를 중복해서 사용하기 싫을 경우 사용한다.
> // 람다표현식에서만 사용 가능 하며 이름만으로 특정 메소드를 호출할 수 있는 기능이다.
> 
> // 기존
> stream.forEach(e -> System.out.println(e));
> // ::
> stream.forEach(System.out::pri\ntln);
> ```

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> Words = Arrays.asList("APPLE", "Banana", "orange", "korea");
        
        Collections.sort(Words, (s1, s2) -> Integer.compare(s1.length(), s2.length()));
    }
}
```

여기서 람다의 타입은`Comparator<String>` 이고 매개변수 (s1, s2)의 타입은 `String`이며 반환 값의 타입은 `int`이다.

하지만 컴파일러가 코드의 문맥을 살펴서 타입추론을 했기 때문에 코드 상에는 이 타입들이 명시되어 있지 않다. 타입을 명시해야 코드가 명확할 때를 제외하고는 **람다의 모든 매개변수 타입은 생략하고 상황에 따라서 컴파일러가 타입을 결정하지 못해서 오류가 발생할 때는 타입을 명시하면 된다.**

컴파일러가 타입을 추론할 떄 필요한 정보들은 대부분 **제네릭**을 통해서 얻게 된다.

위 코드는 아래처럼 더 간단하게 표현할 수 있다.

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> Words = Arrays.asList("APPLE", "Banana", "orange", "korea");
        
        Collections.sort(Words, Comparator.comparingInt(String::length));
    }
}
```



더 나아가서 `JDK 1.8` 이상을 사용하면 `List` 인터페이스에 추가된 `sort` 메서드를 사용할 수 있다.

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> Words = Arrays.asList("APPLE", "Banana", "orange", "korea");
        Words.sort(Comparator.comparingInt(String::length));
    }
}
```



### - 열거타입에서의 람다

```java
enum Operation {
    PLUS("+") {
        public double apply(double x, double y) { return x + y; }
    },
    MINUS("-") {
        public double apply(double x, double y) { return x - y; }
    },
    TIMES("*") {
        public double apply(double x, double y) { return x * y; }
    },
    DIVIDE("/") {
        public double apply(double x, double y) { return x / y; }
    };
    
    private final String symbol;
    
    Operation(String symbol) { this.symbol = symbol; }
    
    @Override
    public String toString() { return symbol; }
    public abstract double apply(double x, double y);
}
```

열거 타입에서 람다를 이용하면 열거 타입의 인스턴스 필드를 이용하는 방식으로 상수별로 다르게 동작하는 코드를 쉽게 구현할 수 있다.

```java
import java.util.function.DoubleBinaryOperator;

enum Operation {
    PLUS("+", (x, y) -> x + y),
    MINUS("-", (x, y) -> x - y),
    TIMES("*", (x, y) -> x * y),
    DIVIDE("/", (x, y) -> x / y);
    
    private final String symbol;
    private final DoubleBinaryOperator op;
    
    Operation (String symbol, DoubleBinaryOperator op) {
        this.symbol = symbol;
        this.op = op;
    }
    
    @Override
    public String toString() { return symbol; }
    
    public double apply(double x, double y) {
        return op.applyAsDouble(x, y);
    }
}

public class Main {
    public static void main(String[] args) {
        Operation.PLUS.apply(2, 3);
    }
}
```

`DoubleBinaryOperator`는 `java.util.function` 패키지에 있는 `Double` 타입 인수를 2개 받아서 `Double` 타입 결과를 반환해주는 인터페이스이다.



## 람다의 한계

> 1. 추사클래스의 인스턴스를 만들 때는 람다사용 불가능
> 2. 추상메서드가 여러개인 인터페이스의 인스턴스로 람다 표현 불가능
> 3. 람다의 this는 바깥의 인스턴스를 가리킨다.

람다를 무조건적으로 사용하는 것은 좋지 않은 경우도 있다.

람다는 이름도 없고, 메서드나 클래스와 다르게 문서화도 할 수 없기 때문에 코드 자체로 동작이 명확하게 설명되지 않거나 코드 라인 수가 많아지면 사용하는 것을 고려해봐야 한다.

**람다가 너무 길거나 읽기 어렵다면 오히려 쓰지 않는 방향으로 리팩토링 하는 것을 권장한다.**



그리고 **추상 클래스의 인스턴스**를 만들 때 람다를 사용할 수 없다. 이 때는 **익명클래스를 사용**해야 한다.

```java
abstract class Hello {
    public void sayHello() {
        System.out.println("Hello!");
    }
}

public class Main {
    public static void main(String[] args) {
        // 이건 원래 안된다
        // Hello hello = new Hello();
        
        Hello instance1 = new Hello() {
            private String msg = "Hi";
            @Override
            public void sayHello() {
                System.out.println(msg);
            }
        }
        
        Hello instance2 = new Hello() {
            private String msg = "HolaHola";
            @Override
            public void sayHello() {
                System.out.println(msg);
            }
        }
        
        instance1.sayHello(); // Hi
        instance2.sayHello(); // HolaHola
        
        System.out.println(instance1 == instance2); // false
    }
}
```



또한, 람다는 자기 자신 참조가 안된다. `this` 키워드는 바깥의 인스턴스를 가리키게 된다.

반면에 익명클래스에서의 `this`는 익명 클래스의 인스턴스 자신을 가리킨다.

```java
import java.util.*;

class Anonymous {
    public void say() {}
}

public class Main {
    public void testMethod() {
        List<Anonymous> list = Arrays.asList(new Anonymous());
        
        Anonymous anonymous = new Anonymous() {
            @Override
            public void say() {
                System.out.println("this instanceof Anonymous : " + (this instanceof Anonymous));
            }
        };
        
        anonymous.say(); // this instanceof Anonymous : true
        
        // this instanceof Main : true
        list.forEach(o -> System.out.println("this instanceof Main: " + (this instanceof Main)));
    }
    
    public static void main(String[] args) {
        new Main().someMethod();
    }
}
```

람다도 익명 클래스와 동일하게 `직렬화(Serialization)`의 형태가 구현별(ex. 가상머신)로 다를 수 있으므로 주의해야 한다.

`Comparator`처럼 직렬화해야만 하는 함수 객체가 있으면 `private static 중첩 클래스`의 인스턴스를 사용하면 된다.



참조

https://madplay.github.io/post/prefer-lambdas-to-anonymous-classes



# lambda 와 effectively final

> `JDK 1.8`에서 추가된 람다식에는 규칙이 있다.
>
> 1. 람다식은 외부 block에 있는 변수에 접근할 수 있다.
> 2. 외부에 있는 변수가 지역 변수 일 경우 **final** 혹은 **effectively final**인 경우에만 접근할 수 있다.



## effectively final이란?

> *A non-final local variable or method parameter whose value is never changed after initialization is known as effectively fianl.*

Java8에 추가된 syntatic sugar의 일종으로, **초기화 된 이후 값이 한번도 변경되지 않았다면 `effectively final`**이라고 할 수 있다.

effectively final 변수는 final 키워드가 붙지 않았지만 final 키워드를 붙힌것과 동일하게 컴파일러에서 처리하므로 **'의미상 final'하다**고 이해해도 좋다.



effectively final은 anonymous class나 람다식에서 코드를 더 간결하게 해준다.

java  7에서는 anonymous class가 외부지역변수 가 final인 경우에만 접근이 가능했기 때문에 항상 final 키워드를 추가해줘야 했다. 

java 8에서는 effectively final인 경우에도 접근이 가능하도록 바뀌어서 조건을 만족한다면 final 키워드를 생략할 수 있다.

```java
// Java 7
public void add() {
    final int number = 1;
    
    Addable addableImple = new Addable() {
        @Override
        public int addOne() {
            return number + 1;
        }
    };
}

// Java 8
public void add() {
    int number = 1; // Effectively final하다.
    
    Addable addableImple = new Addable() {
        @Override
        public int addOne() {
            return number + 1;
        }
    }
}
```

이는 `lambda`에서도 동일하다.

```java
// Java 8
public void add() {
    int number = 1; // Effectively final하다.
    
    Addable addableImple = () -> number + 1;
}
```



## 람다가 사용하는 지역변수는 왜 final(effectively final) 이어야 할까?

> 람다식에서 참조하는 **외부 지역 변수**는 final 혹은  effectively fianl이어야 한다.

외부 변수라는 단어에는 지역변수, 인스턴스 변수, 클래스 변수가 모두 포함될 수 있는데, 인스턴스 변수나 클래스 변수는 final 혹은 effectively final하지 않아도 람다식에서 사용할 수 있다.

```java
private int instanceNumber = 1;
private static int staticNumber = 1;

// Error : 외부 지역변수는 final 혹은 effectively final 이어야 람다에서 사용가능하다.
public void addByLocalVariable() {
    int localNumber = 1;
    
    localNumber = 2;
    Addable addalbeImple = () -> localNumber + 1;
}

// OK : 클래스 변수(static)는 값을 변경하더라도 문제없다.
public void addByInstanceVariable() {
    instanceNumber = 2;
    Addable addableImple = () -> instanceNumber + 1;
}

// OK : 인스턴스 변수(non-static)는 값을 변경하더라도 문제 없다.
public void addByStaticVariable() {
    staticNumber = 2;
    Addable addableImple = () -> staticNumber + 1;
}
```



람다식에서 사용되는 지역변수가 final 혹은 effectively final 이어야 하는 이유를 알기 위해서는 **Capturing lambda**라는 키워드를 알아야 한다.



## Capturing lambda vs Non-Capturing lambda

람다에는 2가지 타입이 존재한다.

- Capturing lambda

  - 외부 변수를 이용하는 람다식이다.
  - 외부 변수는 지역변수, 인스턴스 변수, 클래스 변수를 모두 포함한다.

  ```java
  String msg = "CapturingLambda";
  Runnable runnable = () -> System.out.println(msg);
  ```

- Non-Captuing lambda

  - 외부 변수를 사용하지 않는 람다식이다.

  ```java
  Runnable runnable = () -> System.out.println("NonCapturingLambda");
  
  Runnable runnable = () -> {
      String msg = "NonCapturingLambda";
      System.out.println(msg);
  }
  ```



Capturing lambda는 다시 local Capturing lambda와 non-local Capturing lambda로 구분된다.

local <-> non-local 로 다시 구분하는 이유는 지역 변수가 가지는 특징으로 내부 동작 방식이 다르기 대문이다.



### - Local Capturing Lambda

```java
public void addByLocalVariable() {
    int localNumber = 1;
    Addable addableImple = () -> localNumber + 1;
}
```

외부변수로 지역변수를 이용하는 람다식을 의미하며 다음과 같은 특징을 갖는다.

- 람다식에서 사용되는 **외부 지역 변수는 복사본이다.**
- fianl 혹은 effectively fianl인 지역 변수만 람다식에서 사용할 수 있다.
- 복사된 지역 변수 값은 람다식 내부에서도 변경할 수 없다. 즉, final 변수로 다뤄야 한다.



각 특징에 대해 조금 더 자세히 알아보자.



**1. 람다식에서 사용되는 외부 지역변수는 복사본이다.**

람다식에서 외부 지역변수를 그대로 사용하지 못하고 복사본을 사용하는 이유는 다음과 같다.

- 지역 변수는 스택영역에 생성된다. 따라서 지역 변수가 선언된 block이 끝나면 스택에서 제거된다.
  - 메소드 내 지역변수를 참조하는 람다식을 리턴하는 메소드가 있을 경우, 메소드 block이 끝나면 지역 변수가 스택에서 제거되므로 추후에 람다식이 수행될 때 참조할 수 없다.
- 지역 변수를 관리하는 쓰레드와 람다식이 실행되는 쓰레드가 다를 수 있다.
  - 스택은 각 쓰레드의 고유 공간이고, 쓰레드끼리 공유되지 않기 때문에 마찬가지로 람다식이 수행될 때 값을 참조할 수 없다.

이러한 이유로, 람다식에서는 외부 지역 변수를 직접 참조하지 않고 복사본을 전달받아서 사용하게 된다.



**2. final 혹은 effectively final인 지역 변수만 람다식에서 사용할 수 있다.**

만약 참조하고자 하는 지역변수가 final이나 effectively final이 아닐 경우 즉, 변경이 가능할 경우 어떤 문제가 일어날까?

```java
public void executelocalVariableInMultiThread() {
    boolean flag = true;
    executor.execute(() -> {
        while(flag) {
            // do
        }
    });       
    flag = false;
}
```

람다식이 정확히 어떤 쓰레드에서 수행되는지 미리 알 수 없다. 즉, 외부 지역변수를 다루는 쓰레드와 람다식이 수행되는 쓰레드가 다를 수 있다.



지역 변수 값(`flag`) 을 제어하는 쓰레드를 **A**, 람다식이 수행되는 쓰레드를 **B**라고 가정하면 문제는 다음과 같다.



쓰레드 B의 flag 값이 가장 최신 값으로 복사되어 전달됐는지 확신할 수 없다.

왜냐하면 **flag는 변경 가능한 지역 변수이고, 지역변수를 쓰레드 간에 동기화해주는 것은 불가능하기 때문**이다.

*지역 변수는 쓰레드 A의 스택영역에 존재하기 때문에 다른 쓰레드에서 접근이 불가능하다. volatile과 같은 키워드가 로컬 변수에서 사용될 수 없는 이유도 이와 같다.*



값이 보장되지 않는 다면 매번 다른 결과가 도출될 수 있고 예측할 수 없는 코드는 사용할 수 없다.

이러한 이유로 외부 지역 변수는 전달되는 복사본이 변경되지 않은 최신 값임을 보장하기 위해 **final** 혹은 **effectively final**이어야 한다.



**3. 복사된 지역 변수 값은 람다식 내부에서 변경할 수 없다. 즉, final처럼 다뤄야 한다.**

이미 복사가 된 값이므로 변경해도 문제가 없는게 아닌가 생각할 수 있지만 아니다.

복사될 값의 변조를 막아 항상 최신의 값임을 보장하기 위해서 fianl 제약을 걸었는데, 내부에서 변경가능하다면 말짱 도루묵이 된다.

또한, 컴파일 된 람다식은 `static` 메소드 형태로 변경되는데, 이 때 복사된 값이 파라미터로 전달되므로 마찬가지로 스택영역에 존재하기 때문에 동기화 해주는 게 불가능하다.



따라서 람다식 내부에서도 값이 변경 되어서는 안되며 컴파일러 레벨에서 앞, 뒤로 final 제약을 걸어줌으로써 멀티 쓰레드 환경에서 대응하기 어려운 이슈를 미연에 방지하는 것이다.



### - Non-Local Capturing Lambda

```java
private int instanceNumber = 1;
private static int staticNumber = 1;

public void addByInstanceVariable() {
    instanceNumber = 2;
    Addable addableImple = () -> instanceNumber + 1;
}
public void addByStaticVariable() {
    staticNumber = 2;
    Addable addableImple = () -> staticNumber + 1;
}
```

외부 변수로 지역 변수가 아닌, 인스턴스 변수나 클래스 변수를 이용하는 람다식이다.

final 제약 조건이 없고, 외부 변수 값도 복사해서 사용하지 않는다.



> **인스턴스 변수(non-static)** : 인스턴스가 생성될 때마다 **heap 영역**에 매번 새로 생성되고 GC에 의해 소멸
>
> **클래스 변수(static)** :  클래스가 메모리에 올라갈 때 **method 영역**에 한 개만 생성되고 프로그램 종료 시 소멸
>
> **지역 변수** : 메서드 수행시 **stack 영역**에 생성 후 메소드 종료 시 소멸
>
> *하나의 쓰레드는 다른 쓰레드로 접근할 수 없지만, static(method) 영역과 heap 영역은 공유해서 사용할 수 있다.*

그 이유는 인스턴스 변수나 클래스 변수가 저장하고 있는 메모리 영역은 공통 영역이고 값이 메모리에서 바로 회수되지 않기 때문에 여러 스레드나 람다식에서 바로 참조가 가능한 것이다.

따라서, 복사 과정이 불필요하고 참조 시 최신 값 임을 보장할 수 있다. 다만 멀티 쓰레드 환경일 경우에는 `volatile`이나 `synchronized` 등을 이용해서 동기화를 맞춰주는 작업을 해야한다.



## 결론

람다식에서 외부 지역 변수를 사용하는 경우 final 혹은 effectively fianl이어야 하는 이유는 지역변수가 스택에 저장되기 때문에 람다식에서 값을 바로 참조하는 것에 제약이 있어 복사된 값을 사용하는데, 이 때 멀티 쓰레드 환경에서 복사된(될) 값이 변경 가능할 경우 동시성 이슈를 대응할 수 없기 때문이다.





참조

https://vagabond95.me/posts/lambda-with-final/

# Overriding vs Overloading

---

[위로](#java)

둘 다 다형성을 높여주는 개념이고 비슷한 이름이지만, 전혀 다른 개념이라고 봐도 무방할 만큼 차이가 있다.
(오버로딩은 다른 시그니쳐를 만든다는 관점에서 다형성으로 보지 않는 의견도 있다.)

공통점으로는 같은 이름의 다른 함수를 호출한다는 것이다.

## 오버라이딩

상위클래스 혹은 인터페이스에 존재하는 메소드를 하위 클래스에서 필요에 맞게 재정의하는 것을 의미한다.

**자바의 경우는 오버라이딩 시 `동적바인딩(Dynamic Binding)`된다.**

ex) 아래와 같은경우 SuperClass의 fun이라는 인터페이스를 통해 SubClass의 fun이 실행된다.

```java
SuperClass object = new SubClass();
object.fun();
```



## 오버로딩

메소드 이름과 return 타입은 동일하지만, 매개변수만 다른 메소드를 만드는 것을 의미한다.

다양한 상황에서 메소드가 호출될 수 있도록 하는 것이다.

언어마다 다르지만, 자바의 경우 오버로딩은 다른 시그니쳐를 만드는 것으로, 아예 다른 함수를 만든것과 비슷하다고 생각하면 된다.

시그니쳐가 다르므로 **정적바인딩으로 처리가 가능하며, 자바의 경우 정적으로 바인됭된다.**

ex) 아래와 같은경우 `fun(SuperClass super)`이 실행된다.

```java
main(~~) {
    SuperClas object = new SubClass();
    fun(object);
}

fun (SuperClass super) {
    ~~
}

fun(SubClass sub) {
    ~~
}
```

# Access Modifier

---

[위로](#java)

> 접근 지정자
>
> 변수 또는 메소드의 접근 범위를 설정해주기 위해 사용되는 JAVA의 예약어를 의미하며 총 네가지 종류가 있다.

- `public`

  : 어떤 클래스에서라도 접근이 가능하다.

- `protected`

  : 클래스가 정의되어 있는 해당 패키지 내 그리고 해당 클래스를 상속받은 외부 패키지의 클래스에서 접근이 가능하다.

- `(default)`

  : 클래스가 정의되어 있는 해당 패키지 내에서만 접근이 가능하다.

- `private`

  : 정의된 해당 클래스에서만 접근이 가능하다.

# JAVA와 C의 차이점

---

|                      |                              C                               |                             JAVA                             |
| :------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
| 절차지향 vs 객체지향 |              순차적으로 읽어가는 절차지향 언어               |             설계적인 측면이 강조된 객체지향 언어             |
|       개발환경       |                        Visual Studio                         |                      Eclipse, IntelliJ                       |
|       처리속도       |                       상대적으로 빠름                        |                       상대적으로 느림                        |
|       유지보수       |           어려움<br />꼬이게 되면 순차적으로 수정            |           용이함<br />해당 객체만 찾아서 수정 가능           |
|         용도         | 임베디드 또는 운영체제 처럼 속도나 용량 등에서 최적화가 필요한 곳에서 주로 사용 | 웹사이트나 운영체제에 상관없이 실행되어야 하는 응용SW, 안드로이드 앱 등에서 사용 |
|   안정성 & 확장성    |                       상대적으로 낮음                        |                       상대적으로 높음                        |
|     메모리 제어      |           메모리를 직접 조절해 메모리 낭비가 적음            |           각각의 클래스로 인해 메모리 낭비가 높음            |

# JAVA와 C++의 차이점

---

|                          | C++                                                          | JAVA                                                         |
| ------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 플랫폼 독린              | 플랫폼 종속적<br />C++로 작성된 소스 코드는 모든플랫폼에서 컴파일 되야함 | 플랫폼 독립적<br />바이트 코드로 컴파일 되면 모든 플랫폼에서 실행가능 |
| 컴파일러와 인터프리터    | 컴파일 된 언어이다.<br />작성된 소스 프로그램<br />출력을 생성하기 위해 실행될 수 있는 객체 코드로 컴파일 된다. | 컴파일되고 해석되는 언어이다.<br />java소스 코드의 컴파일 된 출력은 플랫폼에 독립적인 바이트 코드이다. |
| 이식성                   | 이식할 수 없다.<br />각 플랫폼에 대해 컴파일 해야 함         | java는 코드를 바이트 코드로 변환<br />이 바이트 코드는 이식 가능하며 모든 플랫폼에서 실행가능 |
| 메모리 관리              | 수동으로 메모리 관리<br />new / delete 연산자를 사용해 메모리를 수동으로 할당 / 해제 | 자동으로 메모리 관리(GC)                                     |
| 다중 상속                | 단일 및 다중 상속을 포함해 다양한 유형의 상속을 지원한다<br />다중 상속으로 인해 문제가 발생하더라도 C++는 virtual 키워드를 사용해 문제를 해결 | 단일 상속만 지원<br />JAVA의 인터페이스를 사용해 다중 삭속의 효과를 얻을 수 있다. |
| 과부하                   | 메서드와 연산자는 오버로드 될 수 있고 이것은 정적 다형성이다. | 메서드 오버로딩만 오버로딩이 허용되고 연산자 오버로딩은 허용하지 않는다. |
| 가상 키워드              | 동적 다형성의 일부로 C++에서 가상 키워드는 파생 클래스에서 재정의 될 수 있는 함수를 나타내는 함수와 함께 사용한다.<br />이렇게 하면 다형성을 얻을 수 있음 | JAVA에서는 virtual 키워드가 없다<br />그러나 기본적으로 모든 비 정적 메서드를 재정의 할 수 있다.<br />또는 간단히 말해서 JAVA의 모든 비 정적 메서드는 기본적으로 가상이다. |
| 포인터                   | 포인터를 강력하게 지원하며 포인터를 사용하여 많은 유용한 프로그래밍이 가능하다. | 포인터를 제한적으로 지원한다<br />처음 JAVA에는 포인터가 전혀 없었지만 이후 버전에서는 포인터에 대한 지원을 제공하기 시작했다.<br />JAVA에서 포인터를 C++에서 사용할 수 있는 것만큼 여유롭게 사용할 수 없다. |
| 문서 주석                | 문서 주석을 지원하지 않는다.                                 | 문서 주석에 대한 기본지원이 있다.(/ **... * /)<br />이렇게 하면 java 소스 파일이 자체 문서를 가질 수 있다. |
| 스레드 지원              | 내장 스레드 지원이 없다.<br />대 부분 타사 스레딩 라이브러리에 의존한다. | thread class로 내장된 스레드를 지원한다.<br />스래드 클래스를 상속한 다음 run 메서드를 재정의 할 수 있다. |
| 루트 계층                | 절차 적이며 객체 지향 프로그래밍 언어이다><br />따라서 특정 계층 구조를 따르지 않는다. | 순수한 객체 지향 프로그래밍 언어이며<br />단일 루트 계층 구조를 가진다. |
| 소스 코드 및 클래스 관계 | 소스 코드와 파일 이름은 모두 관계가 없다.<br />C++ 프로그램에 많은 클래스를 가질 수 있고 파일 이름은 무엇이든 될 수 있음을 의미한다.<br />클래스 이름과 같을 필요는 없다. | JAVA에서는 소스 코드 클래스와 파일이름 사이에 밀접한 관계가 있다.<br />소스코드와 파일이름을 포함하는 클래스는 동일해야 한다. |
| 개념                     | C++ 프로그램의 개념은 한 번 작성되었으며 C++ 은 플랫폼 독립적이지 않기 때문에 어디서나 컴파일 된다. | 반대로 JAVA는 컴파일러에서 생성된 바이트 코드가 플랫폼에 독립적이며 모든 시스템에서 실행 될 수 있으므로 한 번 작성되고 어디서나 실행된다. |
| 다른 언어와의 호환성     | C를 기반으로 하고, 대부분의 다른 고급 언어와 호환된다.       | 다른 언어와 호환되지 않는다.<br />JAVA는 C/C++에서 영감을 얻었으므로 구문은 이들과 유사하다. |
| 프로그래밍 언어 유형     | 절차 적 및 객체 지향 프로그래밍 언어로 절차 언어와관련된 객체 지향 프로그래밍 언어의 기능이 있다. | 완전한 객체 지향 프로그래밍 언어이다.                        |
| 도서관 인터페이스        | 네이티브 시스템 라이브러리에 대한 직접 호출을 허용한다.<br />따라서 시스템 수준 프로그래밍에 더 적합하다 | JAVA는 기본 라이브러리에 대한 직접적인 호출 지원이 없다.<br />Java Native Interface 또는 Java Native Access를 통해 라이브러리를 호출 할 수 있다. |
| 차별화된 기능            | 절차적 언어 및 객체 지향 언어와 관련된 기능은 C++의 차별화된 기능이다. | 자동 Garbage Colletions은 JAVA의 차별화된 기능이다.<br />한편 java는 소멸자를 지원하지 않는다. |
| 유형 의미                | 기본 유형과 객체 유형은 일관성이 있다.                       | 기본 유형과 객체 유형간에 일관성이 없다.                     |
| 입력 메커니즘            | cin, cout                                                    | System.in / out                                              |
| 액세스 제어 및 개체 보호 | 액세스를 제어하는 액세스 지정자와 보호를 보장하는 강력한 캡슐화가 있는 개체에 대한 유연한 모델이 있다. | 캡슐화가 약한 비교적 번거로운 객체 모델을 가지고 있다.       |
| Go to 문                 | goto문을 지원하지만 사용을 최소화 해야 한다                  | goto문 지원 안함                                             |
| 범위 해결 연산자         | 범위 확인 연산자는 전역 변수에 액세스 하고 클래스 외부의 메서드를 정의하는데 사용하는데 C++은 이를 지원한다. | 지원하지 않는다.                                             |
| 런타임 오류 감지         | 런타임 오류 감지는 프로그래머의 책임                         | 런타임 오류 감지는 시스템에 의해 제어                        |
| 하드웨어                 | 하드웨어에 가깝고 하드웨어 리소스를 조작할 수 있는 많은 라이브러리가 있다. 종종 시스템 프로그래밍, 게임 응용 프로그램, 운영체제 및 컴파일러에 사용된다. | 대부분 응용 프로그램 개발 언어이며 하드웨어에 가깝지 않다.   |

# 정적 팩토리 메서드

---

우리가 어떤 인스턴스를 새로 생성할 때는 보통 생성자를 이용한다. 보통 실제 개발에 가면 public 생성자(혹은 빌더패턴)를 주로 이용해서 사용하는데, 이보다 좀 더 나은 방법이 있어서 포스팅한다.

```java
public class Product {
    private String name;
    
    public Product(String name) {
        this.name = name;
    }
    
    public static void main(String[] args) {
        Product product = new Product("book");
    }
}
```

위의 예는 생성자를 이용한 매개변수로 객체를 생성하는 모습이다.

매개변수 하나일 때는 쉽게 예상이 가겠지만 매개변수가 점점 늘어나고 복잡해지면 하지만 개발자가 생성자의 매개변수만 보고 어떤 객체를 반환할지 예측하는 것은 쉽지 않을 것이다.



정적 팩토리 메서드를 한마디로 정의하자면 **객체 생성의 역할을 하는 클래스 메서드이다.**

```java
public class Product {
    private String name;
    
    public Product (String name) {
        this.name = name;
    }
    
    public static Product nameOf(String name) {
        return new Product(name);
    }
    
    public static void main(String[] args) {
        Product product = nameOf("book");
    }
}
```

 이와 같이 생성자와 별도로 객체를 생성하는 메소드를 정적으로 만들어서 객체 생성을 캡슐화 해 제공할 수 있다.



예를 들어

- Boolean Class의 `valueOf()`
- LocalTime Class의 `of()`
- enum Class의 `valueOf()`

와 같은 것들이 바로 정적 팩토리 메서드의 일종이다.

이는 미리 생성된 객체를 "조회"하는 메서드이기 때문에 객체를 생성하는 팩토리 역할을 한다고 볼 순 없지만, 외부에서 원하는 객체를 반환하고 있으므로 결과적으로는 정적팩토리 라고 간주한다고 한다.



이펙티브 자바의 첫 아티클이 바로 **"생성자 대신 정적 패토리 메서드를 고려하라"**인 점을 고려하면 이 개념은 굉장히 중요한 것이라고 느낌이 올 것이다.

그럼 본격적으로 그 장점을 알아보자.



## 1. 이름을 가질 수 있다.

생성자로 넘기는 매개변수 만으로는 반환될 객체의 특성을 정확하게 표현하기가 어렵다.

하지만, 정적 팩토리 메서드를 사용하면, 이름만 잘 짓는다면 반환될 객체의 특성을 한번에 유추할 수 있다.

```java
public class Product {
    private String name;
    
    public Product (String name) {
        this.name = name;
    }
    
    static Product nameOf(String name) {
        return new Product(name);
    }
}
```

```java
public class Main {
    public static void main(String[] args) {
        Product p1 = new Product("book");
        Product p2 = Product.nameOf("pencil");
    }
}
```

그냥 생성자로 만드는 것보다 의미를 가진 메소드를 이용하면 훨씬 객체 생성의 의미를 파악하기 쉽다는 것을 알 수 있을 것이다.



## 2. 호출할 때마다 새로운 객체를 생성할 필요가 없다.

**불변 클래스(immutable class)**는 인스턴스를 미리 만들어두거나, 새로 생성한 인스턴스를 캐싱해서 재활용하기 때문에 불필요한 객체 생성을 줄일 수 있다.

정적 팩터리 메서드와 캐싱구조를 함께 사용하면 매번 새로운 객체를 만들 필요가 없다.

```java
class Singleton {
    private static Singleton singleton = null;
    
    private Singleton() {}
    
    static Singlenton getInstance() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }
}
```

```java
public class Main {
    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        System.out.println(s1 == s2); // true
    }
}
```

생성자를 private으로 제한해서 새로운 객체 생성을 제한하고 `getInstance()` 메서드를 static으로 선언해서 인스턴스를 생성하도록 한다.

위의 예에서 싱글톤 객체 s1과 s2는 같은 인스턴스이다.



대표적으로 **Boolean Class**도  `TRUE`, `FALSE`를 상수로 정의해서 `valueOf(boolean)` 메서드 사용 시 객체를 새로 생성하는 것이 아니라 상수를 반환하는 것이다.

따라서, 객체 생성 비용이 큰 객체가 자주 요청이 된다면 성능상에서 이점을 볼 수 있게 되는 것이다.



이렇게 **인스턴스를 통제**하는 것은 인스턴스가 단 하나뿐임을 보장하는 것이고, **플라이웨이트 패턴**의 근간이 되는 것이다.

> ***플라이웨이트 패턴(Flyweight Pattern)?***
>
> 데이터를 공유해서 메모리를 절약하는 패턴으로 공통으로 사용되는 객체는 한 번만 사용되고, Pool에 의해서 관리/사용 되는 디자인패턴이다.
>
> JVM의 String Constant Pool이 바로 그 예이다



## 3. 하위 자료형 객체를 반환할 수 있다.

이는 상속을 활용할 때 나타나는 특징이다.

이렇게 바의 다형성 특징을 이용하면 인터페이스 자체를 반환하도록 할 수 있어, 하위 클래스(구현체)를 노출하지 않고도 반환할 수 있다.

```java
class Order {
    public static Discount createDiscountProduct(String code) throws Exception {
        if (!isValidCode(code)) {
            throw new Exception("잘못된 할인 코드");
        }
        if (isUsableCoupon(code)) {
            return new Coupon(1000);
        } else if (isUsablePoint(code)) {
            return new Point(500);
        }
        throw new Exception("이미 사용한 코드");
    }
}
class Coupon extends Discount {}
class Point extends Discount {}
```

이는 GoF 에서 소개하는 팩토리 패턴과 유사하게 객체 생성을 조건에 따라 분기한다는 개념이다.



또한 이는 인터페이스를 정적 팩토리 메서드의 반환 타입으로 사용하는 **인터페이스 기반 프레임워크**를 만드는 핵심 기술이다.

그 예로 자바의 **Collection** 프레임워크는 핵심 인터페이스들에 수정 불가나 동기화 등의 기능을 덧붙인 45개의 util 구현체를 제공한다.

이 구현체는 `java.util.Collections` 클래스를 굳이 만들지 않고도 인터페이스 자체에서 정적 팩토리 메서드를 통해 얻도록 구현해놓은 것이다.

```java
public interface List<E> extends Collection<E> {
    static <E> List<E> of() {
        return (List<E>) ImmutableCollections.ListN.EMPTY_LIST;
    }
}
```

자바 9의 List 인터페이스의 `of()` 메서드는 인터페이스를 반환하는 정적 팩토리 메서드이다.

**클라이언트의 입장에서는 반환되는 클래스가 어떤 건지 알 필요 없이 그냥 `of()` 메서드의 기능이 무엇인지만 알고, `List.of()`의 형태로 사용**하면 되는 것이다.



자바 8에서부터 인터페이스에 정적 메소드를 사용할 수 있게 되는 데 그래서 자바 8 이전에는 인터페이스의 유사 클래스를 만들어서 그 안에 정적 메소드를 정의하는 방식으로 우회해서 사용했다.

```java
public class Collections {
    private Collections() {}
    ...
	public static final List EMPTY_LIST = new EmptyList<>();
    
	public static final <T> List<T> emptyList() {
        return (List<T>) EMPTY_LIST;
    }
}
```



## 4. 객체 생성을 캡슐화할 수 있다.

생성자를 사용하는 경우에 외부에 내부 구현을 드러내야 하는데, 정적 팩토리 메서드 패턴을 사용 하면 **내부 구현을 캡슐화** 하여 사용할 수 있다.

자주 사용하는 DTO와 Entity간의 형변환이 그 예시이다.

```java
@Builder
public class ProductDto {
    private String name;
    private String date;
    
    public static ProductDto from(Product product) {
        return new ProductDto(product.getName(), product.getDate());
    }
}
```

```java
public class Main {
    public static void main(String[] args) {
        Product product = repository.getById(id);
        
        ProductDto productDto = new ProductDto(product.getName(), product.getDate()); // 생성자
        ProductDto productDto = ProductDto.from(product); // 정적 팩토리 메서드
    }
}
```



이렇 게 정적 팩토리 메서드를 사용하면 **단순히 생성자의 역할을 대신하는 것 뿐만 아니라, 좀 더 가독성 좋은 코드를 작성하고 객체지향적으로 프로그래밍**할 수 있도록 도와준다.

> *>> 추가적으로 롬복을 활용하면 좀 더 쉽게 정적 팩터리 메서드 패턴을 만들 수 있다.*
>
> ```java
> @RequiredArgsConstructor(staticName = "of")
> public class Product {
>     private final Long id;
>     private final String name;
> }
> ```



## 단점 ?

하지만 상속을 하려면 `public` 이나 `protected` 생성자가 필요한 데, 정적 팩토리 메소드만 제공하면 하위 클래스를 만들 수 없다는 단점이 존재한다.

위에서 Collections 클래스를 보면 생성자의 접근제어자가 `private`이다. 그렇기 때문에 이 클래스는 누군가의 부모 클래스가 될 수 없다.



정적 팩토리 메서드가 다른 정적 메서드와 잘 구분되지 않는다는 특징 때문에 구분을 쉽게 하기 위한 네이밍 컨벤션이 존재한다.

- `from` : 하나의 매개 변수를 받아서 객체를 생성
- `of` : 여러개의 매개 변수를 받아서 객체를 생성
- `getInstance | instance` : 인스턴스를 생성. 이전에 반환했던 것과 같을 수 있다.
- `newInstance | create` : 새로운 인스턴스를 생성
- `get[OrderType]` : 다른 타입의 인스턴스를 생성. 이전에 반환했던 것과 같을 수 있다.
- `new[OrderType]` : 다른 타입의 새로운 인스턴스를 생성



*정적 팩터리 메서드와, public 생성자는 각각 장단점을 이해하고 사용하는 것이 좋으나 대부분 정적 팩토리를 사용하는 게 유리한 경우가 더 많다. **따라서 무작정 public 생성자를 만드는 것보다는 정적 팩토리 메서드를 우선 고려해보자!***

# Optional Class

---

먼저 Optional Class는 값이 존재하는지 여부를 명시적으로 체크하기 위해서 등장하였다.

`Optional<T>` 클래스는 Integer나 Double 클래스 처럼 `T` 타입의 객체를 포장해주는 래퍼 클래스(Wrapper Class)이다. 따라서 Optional의 인스턴스는 모든 타입의 참조 변수를 저장할 수 있다.

메소드 실행 시 반환하는 값을 알 수 없을 때 혹은 그 값이 존재하지 않는 경우에 Optional Class는 매우 유용하게 사용될 것이다.

자바에서는 이 컨셉을 특정 값(null을 포함할 수 있는)을 담는 컨테이너 객체를 **Optional**로 표현했다.



*Optional의 인스턴스는 불변 인스턴스이며 hashCode, equals, toString 메서드는 인스턴스 상태(value)에 따라 달라지고 Optional의 인스턴스는 value의 eqauls 메서드에 의해서 동일성이 판단된다.*

>   :ballot_box_with_check: Optional의 의도는 **반환값이 "없음"을 나타내는 것이 주 목적**이다.
>
>   :ballot_box_with_check: 또한, 의도하지 않는 NPE를 방지할 수 있고, 개발자로 하여금 값의 의도를 알려줌으로써 더 나은 API를 설계할 수 있도록 사용됩니다.
>
>   **API Note:**
>   *Optional is primarily intended for use as a method return type where there is a clear need to represent “no result,” and where using null is likely to cause errors. A variable whose type is Optional should never itself be null; it should always point to an Optional instance.*
>
>   : 메서드가 반환할 결과값이 "없음"을 명백하게 표현할 필요가 있고, `null`을 반환하면 에러를 유발할 가능성이 높은 상황에서 메서드의 반환타입으로 `Optional`을 사용하자는 것이 `Optional`을 만든 주된 목적이다.
>   이 타입의 변수 값은 절대 `null`이어서는 안되고, 항상 `Optional` 인스턴스를 가리켜야 한다.





## Optional Instance 생성

Optional 객체의 인스턴스를 생성하는 방법에는 여러가지가 있는데,

Optional은 불변 객체이므로 모든 방법들은 생성자 대신에 **factroy methods**를 사용한다.



**`Using Of()`**

가장 흔하게 사용되는 방법으로 `of()` factory method를 사용하는 방법이다. 

이 메서드는 인자로 주어진 값을 가지는 optional instance를 반환한다. 여기에 Null을 넣을 수는 없으며 Null이 들어간다면 `NullPointerException`이 발생한다.

```java
Optional<Integer> result = Optional.of(10);
```



**`From Streams`**

몇몇 Stream API의 종료 메서드로 Optional 인스턴스를 결과로 반환하는데, 이를 통해서 결과값을 다루거나 존재 여부를 확인할 수 있다.

```java
List<Integer> numbers = new ArrayList<>(1, 2, 3, 4, 5);
Optional<Integer> result = numbers.stream().filter(n -> n > 2).findFirst();
```



**`From Nullable`**

첫 번쨰로 소개한 factory  method인 `of()`는 null이 아닌 값만 다룰 수 있다는 단점이 있다.

만약 값이 null일 수도 있다면 `ofNullable` 메소드를 사용하는것을 권장한다.

이 메서드는 주어진 value를 담는 Optional 인스턴스를 생성하는데 null일 경우 empty optional을 반환한다.

```java
String name = null;
Optional<String> result = Optional.ofNullable(name);
```



**`Create empty Optional`**

마지막으로, 명시적으로 empty optional을 생성할 수 있다.

```java
Optional<String> result = Optional.empty();
```



## Optioanl 활용

Optional을 사용하면 Null Checking을 로직속에 넣을 수 있다는 장점으로 `NPE` 발생확률을 줄일 수 있다.

명시적으로 Optional Class의 Method를 활용해서 Null checking 하는 방법에 대해서 알아본다.

-   IfPresent()

value가 존재할 대만 수행되는 로직이 있을 때는 다음과 같이 `ifPresent()` 메서드를 사용하면 된다.

이 메서드는 인자로 값이 존재할 때만 실행하는 콜백함수를 넣어줄 수 있다.

```java
String name = "Sunghyun";
Optional<String> result = Optional.of(name);
result.ifPresent(n -> System.out.println(n));
```



-   IfPresentOrElse()

`IfPresnet()` 메서드는 값이 존재할 때만 실행 로직을 넣어줄 수 있는데, 값이 존재하지 않을때도 로직을 사용하고 싶다면 `IfPresentOrElse()`를 사용해보자. 
*(JDK9 이상부터 사용가능)*

이 함수는 두 개의 인자를 받는다.

1.  값이 존재할 때 실행될 콜백함수와
2.  값이 존재하지 않을 때 실행될 Runnable 인터페이스

```java
Optional<Integer> result = Optional.empty();
result.ifPresentOrElse(n -> System.out.println("SUCCESS")
                      , () -> System.out.println("FAIL"));
```



-    get() , orElse()

Optional 객체는 value를 담고 있는 컨테이너 객체이다.

Optional에 담긴 value를 얻기 위한 **unpack** 메서드로 `get()`과 `orElse()` 메소드가 있다.

`get()` 메소드는 value를 반환하고 value가 없다면 `NPE`를 발생시킨다.

`orElse()` 메서드는 value를 반환하고 value가 없다면 인자로 받은 값을 반환한다.

```java
Optional<Integer> result = Optional.empty();
Integer value = result.orElse(10);
Integer value2 = result.get(10);
```



-   orElseThrow()

`JDK11`부터 추가된 메서드로, 값이 있다면 반환하고 없으면 `NoSuchElementException`을 반환한다.

```java
Optional<String> result = Optional.of("hello");
return result.orElseThrow(() -> new Exception());
```



-   map()

Optional은 mapping을 위한 `map()` 메서드를 제공한다. 이 메서드는 값이 존재할 때 실행된 `mapper function`을 인자로 받고, mapper함수를 실행한 뒤 만든 value 값을 optional에 담아서 반환한다.

```java
Optional<String> name = Optional.of("Sunghyun");
Optional<String> result = name.map(n -> n.toUpperCase());
```



-   Optional to Stream

Optional 객체를 Stream으로 만드는 메서드가 존재한다.

```java
Optional<Integer> num = Optional.of(10);
long result = number.stream().count();
```



## 1. isPresent()-get() 대신 orElseThrow()

```java
// 안좋음
Optional<Member> member = ...;
if (member.isPresent()) {
    return member.get();
} else {
    return null;
}

// 좋음
Optional<Member> member = ...;
return member.orElseThrow(() -> new Exception());
```



## 2. orElse(new ...) 대신 orElseGet(() -> new...)

`orElse(...)`에서 `(...)`는 Optional에 값이 있든 없든 무조건 실행되기 때문에, `(...)`가 새로운 객체를 생성하거나 새로운 연산을 수행하는 경우에는 `orElse()` 대신 `orElseGet()`을 써야한다.

Optional에 값이 없으면  `orElse()`의 인자로서 실행된 값이 반환되므로 의미가 있지만 Optional에 값이 있다면 `orElse()`의 인자로 실행된 값이 버려지게된다. 따라서, `orElse()`는 `(...)`가 새 객체 생성이나 새로운 연산을 유발하지 않고 이미 생성되었거나 이미 계산된 값일 때만 사용하는 것이 좋다.



`orElseGet(Supplier)`에서 `Supplier`는 Optional에 값이 없을 때만 실행된다. 따라서 Optional에 값이 없을 때만 새 객체를 생성하거나 새 연산을 수행하므로 불필요한 오버헤드가 없는 것이다.

```java
// 안 좋음
Optional<Member> member = ...;
return member.orElse(new Member()); // member값이 있든 없든 new Member()가 무조건 실행된다

// 좋음
Optional<Member> member = ...;
return member.orElseGet(Member::new); // member에 값이 없을 때만 new Member()가 실행된다.
```



## 3. 단지 값을 얻을 목적이라면 Optional 대신 Null 비교

Optional은 비싸기 때문에 단순히 값을 얻을 목적이라면 Optional 대신 Null 비교를 쓰는 것이 좋다.

```java
// 안 좋음
return Optional.ofNullable(status).orElse(READY);

// 좋음
return status != null ? status : READY;
```



## 4. Optional 대신 비어있는 컬렉션 반환.

Optional은 비싸기 때문에 null이 아니라 비어있는 컬렉션을 반환하는 것이 좋다.

따라서 컬렉션은 Optional로 감싸서 반환하지 말고 비어있는 컬렉션을 반환하자.

```java
// 안 좋음
List<Member> members = team.getMembers();
return Optional.ofNullable(members);

// 좋음
List<Member> members = team.getMembers();
return members != null ? members : Collections.emptyList();
```

**이와 같은 이유로 Spring DATA JPA Repository 메서드 선언 시 다음과 같이 컬렉션을 Optional로 감싸서 반환하는 것은 좋지 않다.**

**컬렉션을 반환하는 Spring DATA JPA Repository 메서드는 `null`을 반환하지 않고 비어있는 컬렉션을 반환해주므로 Optional을 감싸서 반환할 필요가 없다.**

```java
// 안 좋음
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<List<Member>> findAllByNameContaining(String part);
}

// 좋음
public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findAllByNameContaining(String part); // null이 반환되지 않으므로 Optional은 불필요하다.
}
```



## 5. Optional은 필드 사용 금지

Optional은 필드에 사용할 목적으로 만들어 지지 않았으며, `Serializable`을 구현하지 않았기 때문에 Optional을 필드로 사용하지 말자.



## 6. Optional을 생성자나 메서드 인자로 사용 금지

Optional을 생성자나 메서드 인자로 사용하면, 호출할 때마다 Optional을 생성해서 인자로 전달해줘야 한다.

하치만 호출되는 쪽에서는 인자가 Optional이든 아니든 null체크를 해줘야하는 것이 안전하므로, 굳이 비싼 Optional을 사용하지 말고 호출되는 쪽에서 null 체크의 책임을 남겨두는 것이 좋다.

```java
// 안 좋음
public class HRManager {
    public void increaseSalary(Optional<Member> member) {
        member.ifPresent(member -> member.increaseSalary(10));
    }
}
hrManager.increaseSalary(Optional.ofNullabe(member));

// 좋음
public class HRManager {
    public void increaseSalary(Member member) {
        if (member != null) {
            member.increaseSalary(10);
        }
    }
}
hrManager.increaseSalary(member);
```



## 7. Optional을 컬렉션의 원소로 사용 금지

컬렉션에는 많은 원소가 들어갈 수 있다.

따라서 비싼 Optional을 원소로 사용하지 말고 원소를 꺼낼 때 map의 `getOrDefault()`, `putIfAbsent()`, `computeIfAbsent()`, `computeIfPresent()` 처럼 메서드를 활용하는 것이 좋다.

```java
// 안 좋음
Map<String, Optional<String>> sports = new HashMap<>();
sports.put("100", Optional.of("BasketBall"));
sports.put("101", Optional.ofNullable(someOtherSports));
String basketBall = sports.get("100").orElse("BasketBall");
String unknown = sports.get("101").orElse("");

// 좋음
Map<String, String> sports = new HashMap<>();
sports.put("100", "BasketBall");
sports.put("101", null);
String basketBall = sports.getOrDefault("100", "BasketBall");
String unknown = sports.computeIfAbsent("101", k -> "");
```



## 8. of(), ofNullable() 혼동 주의

`of(x)`는 x가 null이 아님이 확실할 때만 사용해야 하며, x가 null이면 `NPE`가 발생한다.

`ofNullable(x)`은 x가 null일 수도 있을 때만 사용해야 하며, x가 null이 아님이 확실하다면 `of(x)`를 사용하라.

```java
// 안 좋음
return Optional.of(member.getEmail());  // member의 email이 null이면 NPE 발생

// 좋음
return Optional.ofNullable(member.getEmail());


// 안 좋음
return Optional.ofNullable("READY");

// 좋음
return Optional.of("READY");
```



## 9. Optional<T> 대신 Optional+Int/Long/Double

Optional에 담길 값이 int, long, double이라면 `Boxing/Unboxing`이 발생하는 Optional<Integer>, Optional<Long>, Optional<Double>을 사용하지말고, OptionalInt, OptionalLong, OptionalDouble을 사용하자.

```java
// 안 좋음
Optional<Integer> cnt = Optional.of(11); // boxing 발생
for (int i = 0; i < cnt.get(); i++) {...} // unboxing 발생

// 좋음
OptionalInt cnt = OptionalInt.of(11); // boxing X
for (int i = 0; i < cnt.getAsInt(); i++) {...} // unboxing X
```





참조 : 

https://homoefficio.github.io/2019/10/03/Java-Optional-%EB%B0%94%EB%A5%B4%EA%B2%8C-%EC%93%B0%EA%B8%B0/

https://brunch.co.kr/@kd4/153

# 일급컬렉션

---

> First Class Collection
>
> Collection을 Wrapping 하면서, 그 외 다른 멤버 변수가 없는 상태

말 그대로, 컬렉션 객체를 Wrapping하는 것을 얘기합니다.

```java
public class FirstClassCollection {
    private Map<String, Integer> map;
    
    public FirstClassCollection (Map<String, Integer> map) {
        this.map = map;
    }
}
```

이렇게 **Collection울 Wrapping**하면서, **그 외 다른 멤버 변수가 없는 상태**를 일급 컬렉션이라 합니다.



이러한 작업을 통해 얻을 수 있는 이점이 네 가지 있습니다.

1. 비즈니스에 종속적인 자료구조

2. Collection의 불변성을 보장

3. 상태와 행위를 한 곳에서 관리

4. 이름이 있는 컬렉션

   

## 1. 비즈니스에 종속적인 자료구조

상위 클래스에서 컬렉션을 선언하게 되면 해당 컬렉션이 필요한 모든 장소에서 검증로직이 들어가게 됩니다. 하지만 모든 코드와 도메인을 알고 있지 않다면 객체지향관점에서 비효율적이며 유지보수에 어려움이 생기겠죠.

비즈니스에 종속적이라는 말은 생성된 클래스의 컬렉션을 관리하는 클래스(일급 컬렉션)를 따로 만들어서 해당 클래스는 **클래스 내부적으로 비즈니스 로직에서 검증하는 로직을 처리하여 관리**할 수 있다는 말입니다.

일급컬렉션으로 로직을 관리하면 해당 로직이 필요한 부분에서 일급 **컬렉션의 선언만으로 로직을 관리**할 수 있습니다.



## 2. 불변

일급 컬렉션은 **컬렉션의 불변을 보장**합니다.

Java의 `final`은 정확히 말하자면 불변으로 만드는 것이 아니라, **재할당을 금지**합니다.

단순히 컬렉션의 재할당을 금지하는 것이지, 불변을 보장할 수 없습니다.

```java
@Test
public void final도_값변경이_가능합니다() {
    // given
    final Map<String, Boolean> map = new HashMap<>();
    
    // when
    map.put("1", true);
    map.put("2", true);
    map.put("3", true);
    map.put("4", true);
    
    // then
    assertThat(map.size()).isEqualTo(4); // SUCCESS
}
```

위와 같이 단순 `final`은 값이 추가되는 것을 막을 수 없습니다. 이미 비어 있는 `HashMap`으로 컬렉션이 선언되었음에도 값이 변경될 수 있다는 것이죠.



소프트웨어의 규모가 커질수록 **불변 객체**는 중요합니다. 각 **객체들의 값이 절대 바뀔일이 없다는 것이 보장**된다면 그만큼 코드를 이해하고 수정하는 데 **사이드 이펙트가 최소화**됩니다.



따라서 Java에서는 final로 그 문제를 해결할 수 없기 때문에 **일급 컬렉션(First Class Collection)**과 **래퍼 클래스(Wrapper Class)** 등의 방법으로 해결해야 합니다.



즉, 아래와 같이 **컬렉션의 값을 변경할 수 있는 메소드가 없는 컬렉션**을 만들면 **불변 컬렉션**이 됩니다!

```java
public class Orders {
    private final List<Order> orders;
    
    public Orders(List<Order> orders) {
        this.orders = orders;
    }
    
    public long getAmountSum() {
        return orders.stream().
            	.mapToLong(Order::getAmount)
            	.sum();
    }
}
```

이 클래스는 생성자와 getAmountSum() 외에 다른 메소드가 없습니다. 단지 **새로 만들거나 값을 가져오는 것**의 기능만 하게 됩니다.

List라는 컬렉션에 접근할 수 있는 방법이 없기 때문에 **값의 변경/추가**가 불가능한 것이죠

이렇게 일급 컬렉션을 사용하면, **불변 컬렉션**을 만들 수 있습니다.



## 3. 상태와 행위를 한 곳에서 관리

일급 컬렉션의 세 번째 장점은 **값과 로직이 한 곳에 존재**한다는 것입니다.

해당 컬렉션에 대한 상태와 행위를 클래스 안에 정의함으로써 일급 컬렉션 사용 시 메소드의 중복 생성을 막을 수 있으며, 필요한 로직을 만들어놔 추후 필요할 때 편리하고 알아보기 쉽게 사용할 수 있습니다.

```java
public class itemGroups {
    private List<Item> items;
    
    public Groups(List<Item> items) {
        this.items = items;
    }
    
    public Long getSeoulItemSum() {
        return items.stream()
            	.filter(item -> ItemType.isSeoulItem(item.getItemType()))
            	.mapToLong(Item::getAmount)
            	.sum();
    }
}
```

만약 서울시의 아이템이 아니라 다른 곳의 아이템을 가져와야한다면 손쉽게 추가할 수 있습니다.

```java
public class itemGroups {
    private List<Item> items;
    
    public Groups(List<Item> items) {
        this.items = items;
    }
    
    public Long getSeoulItemSum() {
        return getFilterItems(item -> ItemType.isSeoulItem(item.getItemType()));
    }
    
    public Long getBusanItemSum() {
        return getFilterItems(item -> ItemType.isBusanItem(item.getItemType()));
    }
    
    public Long getFilterItems(Predicate<Item> predicate) {
		return items.stream()
            	.filter(predicate)
            	.mapToLong(Item::getAmount)
            	.sum();
    }
}
```

이렇게 `itemGroups`라는 **일급 컬렉션이 생김으로 상태와 로직이 한곳에서 관리할 수 있게 됩니다.**



## 4. 이름이 있는 컬렉션

컬렉션에 이름을 붙일 수 있습니다.

같은 Item의 모임이지만 Seoul의 list와 Busan의 list는 엄연히 다릅니다.

이 둘을 구분하기 위한 흔한 방법은 변수명을 다르게 하는 것이죠.

```java
@Test
public void 컬렉션을_변수명으로() {
    //given
    List<Item> seoulItems = createSeoulItems();
    List<Item> busanItems = createBusanItems();
    
    //when
    
    //then
}
```

이 코드는 오직 변수명만으로 검색해야 하고 또, 개발마자마 그 뜻을 다르게 사용할 수 있습니다. 따라서 명확한 표현이 불가하다는 단점이 있습니다.



이런 문제를 일급컬렉션으로 쉽게 해결할 수 있습니다.

```java
@Test
public void 일급컬렉션의_이름으로() {
    //given
    SeoulItems seoulItems = new SeoulItems(createSeoulItems());
    BusanItems busanItems = new BusanItems(createBusanItems());
    
    //when
    
    //then
}
```

이렇게 서울그룹과 부산그룹 각각의 일급 컬렉션을 만들면 이 **일급컬렉션을 기반으로 용어사용과 검색을 하면됩니다.** 클래스 단위로 관리되기 때문에 검색의 수월함을 느낄 수 있습니다.

이는 협업간에도 많은 도움이 될 것입니다.



> ***이처럼 좋은 객체지향 코드를 위해서 일급컬렉션을 적극 활용해봅시다!***



참조 : https://jojoldu.tistory.com/412



# 자바의 예외에 대해서

---

## > Java 에서의 3가지 Exception

-   **`Check Exception`**
-   **`Error`**
    -   에러는 자바 프로그램 밖에서 발생한 예외를 말합니다.
    -   그 예로, 서버의 디스크가 고장났다던지 메인보드가 나가서 자바 프로그램이 동작하지 않는다던지가 이에 속합니다.
    -   **`Exception.class`는 에러가 아닙니다.**
    -   자바에서 Error로 끝나면 에러이고, Exception으로 끝나면 예외이다.
    -   Error와 Exception으로 끝나는 오류의 가장 큰 차이는 프로그램 밖에서 발생했는지(Error), 안에서 발생했는지(Exception)의 여부입니다.
    -   더 큰 차이는 프로그램이 멈추어 버리느냐(Error : 프로세스에 영향), 계속 실행할 수 있느냐의 차이(Exception : 쓰레드에 영향)입니다.
-   **`Runtime Exception(Unchecked Exception)`**
    -   런타임 예외는 예외가 발생할 것을 미리 감지하지 못했을때 발생하는 예외입니다.
    -   이 런타임 예외에 해당하는 예외들은 컴파일 할 때 발생하지 않고, 실행 시에 발생할 가능성이 있습니다.
    -   따라서 컴파일 시에 체크를 하지 않기 때문에 Unchecked Exception이라고도 합니다.

![image-20211001000836892](https://user-images.githubusercontent.com/58545240/135672213-1bbce8b3-6081-4ee1-bb37-8c072e733e7c.png)

>   -   Error
>
>       -   잡히지 말아야 할 것들입니다.
>
>       잡히면 안되며, 복구불가능한 오류로 선언될 수 있다.
>
>   -   Runtime Exception
>
>       -   프로그래머가 잘못한 일을 위한 것
>
>       잡아야 하며, 복구 가능한 오류로 선언될 수 있다.
>
>   -   Checked Exceptions
>
>       -   프로그래머가 제어할 수 없는 것들을 위한 것
>
>       반드시 잡아야 하며, 복구 가능한 오류로 선언되어야 한다.



## > Throwable class

그리고 이 Error와 Exception의 공통 부모 클래스는 `Throwable` 클래스입니다.

그래서 Error나 Exception을 처리할 때 Throwable로 처리해도 무관합니다.

상속관계나 이렇게 되어 있는 이유는 Exception이나 Error의 성격은 다르지만, 모두 동일한 이름의 메소드를 사용해서 처리할 수 있도록 하기 위함입니다.



그러면 `Throwable`에 어떤 생성자가 선언되어 있는지 봅시다.

-   Throwable()
-   Throwable(String message)
-   Throwable(String message, Throwable cause)
-   Throwable(Throwable cause)

아무런 매개 변수가 없는 생성자가 있고, 예외 메세지를 String으로 넘겨줄 수도 있다. 별도로 예외의 원인을 Throwable 객체로 넘겨줄 수도 있습니다.

`Throwable` 클래스에 선언되어 있고, `Exception` 클래스에서 Overriding한 메소드는 10개가 넘지만 그 중 가장 많이 쓰는 메소드를 보죠.

1.  **`getMessage()`**

    예외 메세지를 String형태로 제공받습니다.

    예외가 출력되었을 때 어떤 예외가 발생되었는지 확인할 때 유용합니다.

2.  **`toString`**

    예외 메세지를 String형태로 제공받지만, `getMessage()`보다 자세하고, 예외 클래스 이름도 같이 제공됩니다.

3.  **`printStackTrace()`**

    가장 첫 줄에는 예외 메세지를 출력하고, 두 번째 줄부터는 예외가 발생하게 된 메소드들의 호출 관계를 출력합니다.



## > throws와 throw

자바에서는 예외를 직접 발생시킬 수 가 있다.

try 블록 내에서 `throw` 라고 명시 한 후 개발자가 예외 클래스의 객체를 생성하면 된다.

```java
try {
    if (foo) {
        throw new FooException("Foo..");
    }
} catch (Exception e) {
    e.printStackTrace();
}
```

예외가 발생하고 throw한 문장 이후에 있는 모든 try 블록의 문장들은 수행되지 않고 catch 블록으로 이동한다.

catch 블록중에서 throw한 예외와 동일하거나 상속 관계에 있는 예외가 있다면 그 블록에서 예외를 처리할 수 있다.



여기서는 e.printStackTrace() 메소드를 호출하기 때문에 예외 스택 정보가 출력되고, 해당하는 예외가 없다면 예외는 메소드 밖으로 던져 버린다. 즉, **예외가 발생한 메소드를 호출한 메소드로 던진다는 의미**이다. 이럴 때 사용하는 것이 `throws` 구문이다.

```java
public void throwsException(int param) throws Exception {
    if (foo) {
        throw new Exception("Foo..");
    }
}
```

이렇게 메소드 선언 시 `throws`를 사용하면 예외가 발생했을 때 `try~catch`로 묶어주지 않아도 그 메소드를 호출한 메소드로 예외처리를 위임하는 것이기 때문에 전혀 문제가 되지 않는다.

이렇게 `try ~ catch` 블록으로 묶지 않고 예외를 `throw` 한다고 해도 `throws`가 선언되어 있기 때문에 전혀 문제 없이 컴파일 및 실행이 가능하다.



하지만 이렇게 `throws`로 메소드를 선언하면 개발이 어려워 진다.

이 **`throwsException()`이라는 메소드는 Exception을 던진다고 메소드 선언부에 `throws` 선언을 해놓았기 때문에, `throwsException()` 메소드를 호출한 메소드에서는 반든시 `try ~ catch` 블록으로 `throwsException()` 메소드를 감싸주어야 한다.**

`try ~ catch` 블록으로 묶지 않으면 컴파일 에러가 발생한다. 이 때 컴파일 오류가 생겼을 경우에는 두 가지 방법이 있다.

1.  다음과 같이 `try ~ catch`로 묶는다.

    ```java
    public static void main(String[] args) {
        ThrowSample sample = new ThrowSample();
        sample.throwException(13);
        try {
            sample.throwsException(13);
        } catch(Exception e) {
    
        }
    }
    ```

2.  호출한 메소드에서도 다시 `throws`를 선언한다.

    ```java
    public static void main(String[] args) throws Exception{
        ThrowSample sample = new ThrowSample();
        sample.throwException(13);
        sample.throwsException(13);
    }
    ```



하지만 이미 `throws`한 것을 다시 `throws`하는 것은 좋은 습관은 아니고, 가장 좋은 방법은 `throws` 하는 메소드를 호출 하는 메소드에서 `try ~ catch`로 처리하는 것이다.

이 클래스를 컴파일하고 실행하면 `throwsException()` 메소드를 호출할 경우 예외 메세지가 나타나는 것을 볼 수 있다.



## > 실제 예외 처리 전략

나는 Exception를 확장해서 나만의 예외 클래스를 만들었다. 그런데 이 예외가 항상 발생하지 않고, 실행시에 발생할 확률이 높은 경우에는 런ㅌ타임 예외로 만드는 것이 나을 수 도 있다.

즉, 클래스 선언 시 `extends Exception` 대신에 `extends RuntimException`으로 선언하는 것이 낫다.

이렇게 하면 해당 예외를 던지는(throw하는) 메소드를 사용하더라도 `try ~ catch`로 묶지 않아도 컴파일시에 예외가 발생하지 않는다.

하지만 이 경우에는 예외가 발생할 경우 해당 클래스를 호출하는 다른 클래스에서 예외를 처리하도록 주조적인 안정 장치가 있어야만 한다. 여기서 안전장치라고 하는 것은 `try ~ catch`로 묶지 않은 메소드를 호출하는 메소드에서 예외를 처리하는 `try ~ catch`가 되어 있는것이다.



`Unchecked Exception`인 RunteimException이 발생하는 메소드가 있다면 그 메소드를 호출하는 메소드는 `try ~ catch`로 묶어주지 않아도 컴파일할 때 문제가 발생하지 않는다. 하지만 예외발생 확률이 높으므로 `try ~ catch`로 묶어두는 것이 좋다.



참조 : https://velog.io/@jsj3282/%EC%9E%90%EB%B0%94%EC%9D%98-%EC%98%88%EC%99%B8%EC%9D%98-%EC%A2%85%EB%A5%98-3%EA%B0%80%EC%A7%80

# 얕은복사와 깊은복사

---

>  Java에서 변수를 복사하고자 할 때 일어날 수 있는 두 가지 경우인 Shallow Copy(얕은 복사)와 Deep Copy(깊은 복사)에 대해서 알아보겠다.
>
> 
>
> **Shallow Copy**는 "주소값"을 복사한다. 이 말은 기존에 참조하고 있던 실제 값은 바뀌지 않고 동일하다는 뜻이다. 즉, 한 쪽 배열에서 수정이 일어나면 나머지 다른쪽 배열에서도 수정이 반영된다.
>
> 이에 반해 **Deep Copy**는 "실제값"을 복사해서 이 값을 새로운 메모리 공간에 복사하게 된다. 즉, 한쪽 배열에서 수정이 일어나더라도 다른쪽 배열에는 아무런 영향을 끼치지 못한다.



코드를 통해서 알아보자.

```java
public class Copy {
    String name;
    long cnt;
    public Copy(String name, long cnt) {
        this.name = name;
        this.cnt = cnt;
    }
    
    public void changeName(String name) {
        this.name = name;
    }
    
    public void minusCnt(long cnt) {
        this.cnt -= cnt;
    }
}
```



## - Shallow Copy

```java
void shallowCopy() {
    Copy origin = new Copy("spring", 10);
    Copy copy = copy;
    
    origin.changeName("django");
    origin.minusCnt(5);
}
```

이런 과정을 거치고 난 후 origin의 이름은 "django"이고 개수가 5개라는 것은 자명하다.

하지만 copy 객체는 어떨까?



결과는 copy의 객체도 origin과 동일한 값(`Copy(name="django", cnt=5)`)을 가지게 된다.

그 이유는 얕은복사는 주소값을 복사하기 때문에 주소로 값을 참조해서 값이 변경되면 해당 값을 참조하고 있는 배열들의 값이 변경된다.

**copy 인스턴스가 참조하고 있는 실제값이 수정되었기 때문에 바뀐 값을 참조하는 것이다!**



즉, 복사된 배열이나 원본 배열이 변경될 때 함께 변경된다.



## - Deep Copy

깊은 복사는 주소값을 참조하는 것이 아니라, 새로운 메모리 공간에 값을 복사하기 때문에 원본 배열이 변경되어도 복사된 배열에 전혀 상관을 끼치지 않는다!

따라서 실제 값을 복사하려면 Deep Copy를 이용해야 한다.



깊은복사에 대한 세 가지 방법이 있다.

### 1. 복사 팩터리

```java
public class Copy {
    String name;
    long cnt;
    
    public Copy(Copy copy) {
        this.name = copy.name;
        this.cnt = copy.cnt;
    }
    
    public static Copy newInstance(Copy copy) {
        Copy c = new Copy();
        c.name = copy.name;
        c.cnt = copy.cnt;
        return c;
    }
}
```

### 2. 직접 객체를 생성한 후 복사

```java
void deeCopy() {
    Copy origin = new Copy("spring", 10);
    Copy copy = new Copy();
    copy.setName(origin.getName());
    copy.setCnt(origin.getCnt());
    
    origin.changeName("django");
    origin.minusCnt(5);
}
```

### 3. clone() 재정의

```java
public class Copy implements Cloneable {
    String name;
    long cnt;
    
    @Override
    protected Copy clone() throws CloneNotSupportedException {
        return (Copy) super.clone();
    }
}

void deepCopy() throws CloneNotSupportedException {
    Copy origin = new Copy("spring", 10);
    Copy copy = origin.clone();
    
    origin.changeName("django");
    origin.minusCnt(5);
}
```

이 떄 2차원 배열에서 `System.arraycopy()`를 수행하고자 하는 경우에는, 기본 자료형이 아닌 2차원 배열은 `arraycopy()` 메소드를 사용할 수 없다. 이 때는 각 객체의 값을 참조해서 new 연산자로 생성을 한 뒤 대입해줘야 한다.



참조 : https://jackjeong.tistory.com/100



# Iterable vs Iterator

---

이 주제를 공부하게 된 이유는 Java로 알고리즘을 구현하면서 Iterator를 자주 사용했었는데 문득, Iterable과의 차이에 대해서 알지 못해 이번 기회에 학습하기 위해 기록을 시작한다.



먼저 **Collection Framework**에 대해서 알아야 하는데 이 컬렉션 프레임워크는 여러 데이터를 효과적으로 처리하기 위한 방법을 제공하는 클래스의 집합이다. 즉, 데이터를 저장하는 자료구조와 그를 처리하는 알고리즘을 구조화해서 클래스로 구현한 것이다.

이 컬렉션 프레임워크는 자바에서 **인터페이스(Interface)**를 사용해서 구현되고, 컬렉션 프레임워크에서는 다음에 더 자세하게 알아볼 예정이다.



## Iterable

![image-20211209170602415](../../tmpImg/image-20211209170602415.png)

다시 돌아와서

```java
public interface Collection<E> extends Iterable<E> {
    // ...
}
```

위 그림처럼 **Iterable**은 **Collection의 상위 인터페이스**이다. Iterable 인터페이스를 좀 더 자세히 보자

```java
public interface Iterable<T> {
    Iterator<T> iterator();
    
    default void forEach(Consumer<? super T> action) {
        Obejcts.requireNonNull(action);
        for (T t : this) {
            action.accept(t);
        }
    }
    // ...
}
```



이 Iterable 인터페이스 안에는 자바8부터 디폴트 메소드로 `forEach()`가 추가 되었다. 그 덕분에

```java
List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
list.stream().forEach(System.out::println);
```

처럼 stream으로 **forEach** 하는 것이 아니라

```java
List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
list.forEach(System.out::println);
```

stream을 생성하는 비용 없이 List에서 곧바로 **forEach**를 사용할 수 있게 되었다.



또한, **`iterator`** 메소드가 추상메소드로 선언이 되어 있다. 그래서, Iterable을 구현한 클래스는 `iterator()`를 사용해서 Iterable 인터페이스를 반환할 수 있고, `iterator()` 메소드를 통해 `for-each loop`을 사용할 수 있게 되는 것이다.

그 이유는 **`for-each loop`가 내부적으로 `iterator()` 메소드를 객체에 호출하는 로직이기 때문이다.**



결론적으로는, Iterable을 구현한 객체에서만 for-each loop을 사용할 수 있다.



이 때문에 Collection 인터페이스를 상속받는 구현체(ex. List, Set, Queue 등)들은 `iterator()` 메소드를 가지고 있는 것이다.

따라서, **Iterable 인터페이스의 역할은 `iterator()` 메소드를 하위 클래스에서 무조건 구현을 하게 만들기 위함인 것이라고 볼 수 있다.**



> 추가적으로, Iterable 인터페이스를 구현한 객체는 for-each loop를 사용할 수 있다는 것은 자명하다.
>
> 하지만, Iterable 인터페이스를 구현하지 않은 객체가 for-each loop를 사용하려고 하면 어떻게 될까?
>
> -> **자바 컴파일러가 for-each loop를 for loop으로 적절히 번역을 한다고 한다.**



## Iterator

그러면 이제 **Iterator** 인터페이스 내부를 보자

```java
public interface Iterator<E> {
    boolean hasNext();
    E next();
    default void remove() {
        throw ...
    }
    default void forEachRemaining(Consumer<? super E> action) {
        Objects.requireNonNull(action);
        while (hasNext())
            action.accept(next());
    }
}
```

디폴트 메소드로 `remove`와 `forEachRemaining`가 정의되어 있고 **Iterator 인터페이스를 구현하고자 하는 클래스는 `hasNext()` 메소드와 `next()` 메소드를 오버라이딩**하면 된다.



이 Iterator 인터페이스를 사용하는 이유는 컬렉션 구현 방법을 외부로 노출시키지 않고, 해당 메소드를 사용하기 위해서 이 Iterator 인터페이스가 존재한다.

*참고: Iterator 패턴*





***# Reference***

https://dundung.tistory.com/170

https://devlog-wjdrbs96.tistory.com/84

