# THREADX (RTOS)

---

- [**Memory Usage**](#memory-usage)
- [**Initialization**](#initialization)
- [**Thread Execution**](#thread-execution)
- [**Message Queue**](#message-queues)
- [**Counting Semaphores**](#counting-semaphores)
- [**Mutexes**](#mutexes)
- [**Event Flags**](#event-flags)
- [**Memory Block Pools**](#memory-block-pools)
- [**RTOS 환경에서 워치독 사용하기**](#rtos-환경에서-워치독-사용하기)
- [**부속 개념**](#부속-개념)

> 참조글 : [THREADX.PDF](http://www.ece.ualberta.ca/~cmpe490/documents/ghs/405/threadxug_g40c.pdf)
>
> 여기도 도움이 된다. https://ppua.tistory.com/entry/%EC%8B%A4%EC%8B%9C%EA%B0%84-%EC%9A%B4%EC%98%81%EC%B2%B4%EC%A0%9C-RTOS%EC%9D%98-%EC%9D%B4%ED%95%B4

## Memory Usage

[위로](#threadx-rtos)

ThreadX resides along with the application program. As a result, the static memory (or fixed memory) usage of ThreadX is determined by the development tools; e.g., the compiler, linker, and locator. Dynamic memory (or run-time memory) usage is under direct control of the application.

> ThreadX는 응용 프로그램과 함께 상주합니다. 결과적으로 ThreadX의 정적 메모리 (또는 고정 메모리) 사용량은 개발 도구에 의해 결정됩니다. 예 : 컴파일러, 링커 및 로케이터. 동적 메모리 (또는 런타임 메모리) 사용량은 애플리케이션에서 직접 제어합니다.

- **Static Memory Usage**

Most of the development tools divide the application program image into five basic areas: instruction, constant, initialized data, uninitialized data, and system stack. Figure 3 on page 47 shows an example of these memory areas. 

It is important to realize that this is only an example. The actual static memory layout is specific to the processor, development tools, and the underlying hardware. 

The instruction area contains all of the program’s processor instructions. This area is typically the largest and is often located in ROM.

> 대부분의 개발 도구는 응용 프로그램 이미지를 명령어, 상수, 초기화 된 데이터, 초기화되지 않은 데이터 및 시스템 스택의 다섯 가지 기본 영역으로 나눕니다. 47 페이지의 그림 3은 이러한 메모리 영역의 예를 보여줍니다.
>
> 이것은 단지 예일 뿐이라는 것을 인식하는 것이 중요합니다. 실제 정적 메모리 레이아웃은 프로세서, 개발 도구 및 기본 하드웨어에 따라 다릅니다.
>
> 명령 영역에는 프로그램의 모든 프로세서 명령이 포함되어 있습니다. 이 영역은 일반적으로 가장 크고 ROM에있는 경우가 많습니다.

![image-20210210141640749](https://user-images.githubusercontent.com/58545240/107468058-a8784a00-6baa-11eb-8889-6a9daee4e527.png)

The constant area contains various compiled constants, including strings defined or referenced within the program. In addition, this area contains the “initial copy” of the initialized data area. During the compiler’s initialization process, this portion of the constant area is used to setup the initialized data area in RAM. The constant area usually follows the instruction area and is often located in ROM.

The initialized data and uninitialized data areas contain all of the global and static variables. These areas are always located in RAM. The system stack is generally setup immediately following the initialized and uninitialized data areas. 

The system stack is used by the compiler during initialization and then by ThreadX during initialization and subsequently in ISR processing.

> 상수 영역에는 프로그램 내에서 정의되거나 참조되는 문자열을 포함하여 다양한 컴파일 된 상수가 포함됩니다. 또한이 영역에는 초기화 된 데이터 영역의 "초기 복사"가 포함됩니다. 컴파일러의 초기화 프로세스 동안 상수 영역의이 부분은 RAM에서 초기화 된 데이터 영역을 설정하는 데 사용됩니다. 상수 영역은 일반적으로 명령 영역을 따르며 종종 ROM에 있습니다.
>
> 초기화 된 데이터 및 초기화되지 않은 데이터 영역에는 모든 전역 및 정적 변수가 포함됩니다. 이러한 영역은 항상 RAM에 있습니다. 시스템 스택은 일반적으로 초기화 및 초기화되지 않은 데이터 영역 직후에 설정됩니다.
>
> 시스템 스택은 초기화 중에 컴파일러에서 사용하고 초기화 중에 ThreadX에서 사용하고 이후에 ISR 처리에서 사용합니다.

- **Dynamic Memory Usage**

As mentioned before, dynamic memory usage is under direct control of the application. Control blocks and memory areas associated with stacks, queues, and memory pools can be placed anywhere in the target’s memory space. This is an important feature because it facilitates easy utilization of different types of physical memory.

For example, suppose a target hardware environment has both fast memory and slow memory. If the application needs extra performance for a high-priority thread, its control block (TX_THREAD) and stack can be placed in the fast memory area, which might greatly enhance its performance.

> 앞서 언급했듯이 동적 메모리 사용량은 애플리케이션에서 직접 제어합니다. 스택, 대기열 및 메모리 풀과 관련된 제어 블록 및 메모리 영역은 대상의 메모리 공간 어디에나 배치 할 수 있습니다. 이것은 다양한 유형의 물리적 메모리를 쉽게 활용할 수 있도록 해주기 때문에 중요한 기능입니다.
>
> 예를 들어 대상 하드웨어 환경에 빠른 메모리와 느린 메모리가 모두 있다고 가정합니다. 응용 프로그램에 우선 순위가 높은 스레드에 대한 추가 성능이 필요한 경우 제어 블록 (TX_THREAD) 및 스택을 빠른 메모리 영역에 배치 할 수 있으므로 성능이 크게 향상 될 수 있습니다.

[위로](#threadx-rtos)

## Initialization

[위로](#threadx-rtos)

Understanding the initialization process is very important. The initial hardware environment is setup here. In addition, this is where the application is given its initial personality. 

ThreadX attempts to utilize (whenever possible) the complete development tool’s initialization process. This makes it easier to upgrade to new versions of the development tools in the future.

>초기화 프로세스를 이해하는 것은 매우 중요합니다. 초기 하드웨어 환경은 여기에서 설정됩니다. 또한 이것은 응용 프로그램에 초기 성격이 부여되는 곳입니다.
>
>ThreadX는 가능한 한 전체 개발 도구의 초기화 프로세스를 활용하려고합니다. 따라서 향후 새로운 버전의 개발 도구로 쉽게 업그레이드 할 수 있습니다.

- **System Reset**

All microprocessors have reset logic. When a reset occurs (either hardware or software), the address of the application’s entry point is retrieved from a specific memory location. After the entry point is retrieved, the processor transfers control to that location. 

The application entry point is quite often written in the native assembly language and is usually supplied by the development tools (at least in template form). In some cases, a special version of the entry program is supplied with ThreadX.

> 모든 마이크로 프로세서에는 리셋 로직이 있습니다. 재설정 (하드웨어 또는 소프트웨어)이 발생하면 특정 메모리 위치에서 응용 프로그램의 진입 점 주소가 검색됩니다. 진입 점이 검색된 후 프로세서는 제어권을 해당 위치로 전송합니다.
>
> 응용 프로그램 진입 점은 기본 어셈블리 언어로 작성되는 경우가 많으며 일반적으로 개발 도구 (최소한 템플릿 형식)에서 제공합니다. 어떤 경우에는 특별한 버전의 진입 프로그램이 ThreadX와 함께 제공됩니다.

- **Development Tool Initialization**

After the low-level initialization is complete, control transfers to the development tool’s high-level initialization. This is usually the place where initialized global and static C variables are setup. Remember that their initial values are retrieved from the constant area. Exact initialization processing is development tool specific.

> 하위 수준 초기화가 완료되면 제어가 개발 도구의 상위 수준 초기화로 이전됩니다. 일반적으로 초기화 된 전역 및 정적 C 변수가 설정되는 곳입니다. 초기 값은 상수 영역에서 검색됩니다. 정확한 초기화 처리는 개발 도구에 따라 다릅니다.

- **main**

When the development tool initialization is complete, control transfers to the user-supplied main function. At this point, the application controls what happens next. For most applications, the main function simply calls tx_kernel_enter, which is the entry into ThreadX. However, applications can perform preliminary processing (usually for hardware initialization) prior to entering ThreadX. 

The call to tx_kernel_enter does not return, so don’t place any processing after it!

> 개발 도구 초기화가 완료되면 제어가 사용자가 제공 한 주 기능으로 이전됩니다. 이 시점에서 애플리케이션은 다음에 발생하는 작업을 제어합니다. 대부분의 애플리케이션에서 main 함수는 단순히 ThreadX의 항목 인 tx_kernel_enter를 호출합니다. 그러나 응용 프로그램은 ThreadX에 들어가기 전에 예비 처리 (일반적으로 하드웨어 초기화 용)를 수행 할 수 있습니다.
>
> tx_kernel_enter에 대한 호출이 반환되지 않으므로 이후에 처리를하지 마십시오!

- **`tx_kernel_enter`**

The entry function coordinates initialization of various internal ThreadX data structures and then calls the application’s definition function tx_application_define.

When tx_application_define returns, control is transferred to the thread scheduling loop. This marks the end of initialization!

> 입력 함수는 다양한 내부 ThreadX 데이터 구조의 초기화를 조정 한 다음 응용 프로그램의 정의 함수 tx_application_define을 호출합니다.
>
> tx_application_define이 반환되면 제어가 스레드 스케줄링 루프로 전송됩니다. 이것은 초기화의 끝을 표시합니다!

- **Application Definition Function**

The tx_application_define function defines all of the initial application threads, queues, semaphores, mutexes, event flags, memory pools, and timers. It is also possible to create and delete system resources from threads during the normal operation of the application. However, all initial application resources are defined here. 

The tx_application_define function has a single input parameter and it is certainly worth mentioning. The first-available RAM address is the sole input parameter to this function. It is typically used as a starting point for initial run-time memory allocations of thread stacks, queues, and memory pools. 

After initialization is complete, only an executing thread can create and delete system resources— including other threads. Therefore, at least one thread must be created during initialization.

> tx_application_define 함수는 모든 초기 애플리케이션 스레드, 큐, 세마포어, 뮤텍스, 이벤트 플래그, 메모리 풀 및 타이머를 정의합니다. 응용 프로그램이 정상적으로 작동하는 동안 스레드에서 시스템 리소스를 만들고 삭제할 수도 있습니다. 그러나 모든 초기 응용 프로그램 리소스가 여기에 정의됩니다.
>
> tx_application_define 함수에는 단일 입력 매개 변수가 있으며 확실히 언급 할 가치가 있습니다. 처음 사용 가능한 RAM 주소는이 기능에 대한 유일한 입력 매개 변수입니다. 일반적으로 스레드 스택, 큐 및 메모리 풀의 초기 런타임 메모리 할당을위한 시작점으로 사용됩니다.
>
> 초기화가 완료된 후에는 실행중인 스레드 만 다른 스레드를 포함한 시스템 리소스를 만들고 삭제할 수 있습니다. 따라서 초기화 중에 하나 이상의 스레드를 만들어야합니다.

- **Interrupts**

Interrupts are left disabled during the entire initialization process. If the application somehow enables interrupts, unpredictable behavior may occur. Figure 4 on page 51 shows the entire initialization process, from system reset through application-specific initialization.

> 인터럽트는 전체 초기화 프로세스 동안 비활성화 된 상태로 유지됩니다. 응용 프로그램이 어떻게 든 인터럽트를 활성화하면 예기치 않은 동작이 발생할 수 있습니다. 51 페이지의 그림 4는 시스템 재설정부터 애플리케이션 별 초기화까지 전체 초기화 프로세스를 보여줍니다.

![image-20210210143048768](https://user-images.githubusercontent.com/58545240/107469165-98616a00-6bac-11eb-899c-9b648a60b2cd.png)

[위로](#threadx-rtos)

## Thread Execution

[위로](#threadx-rtos)

Scheduling and executing application threads is the most important activity of ThreadX. What exactly is a thread? A thread is typically defined as semi-independent program segment with a dedicated purpose. The combined processing of all threads makes an application.

How are threads created? Threads are created dynamically by calling tx_thread_create during initialization or during thread execution. Threads are created in either a ready or suspended state.

> 애플리케이션 스레드를 예약하고 실행하는 것은 ThreadX의 가장 중요한 활동입니다. 스레드는 정확히 무엇입니까? 스레드는 일반적으로 전용 목적을 가진 반독립 프로그램 세그먼트로 정의됩니다. 모든 스레드의 결합 된 처리는 응용 프로그램을 만듭니다.
>
> 스레드는 어떻게 생성됩니까? 스레드는 초기화 또는 스레드 실행 중에 tx_thread_create를 호출하여 동적으로 생성됩니다. 스레드는 준비 또는 일시 중지 상태로 생성됩니다.

- **Thread Execution States**

Understanding the different processing states of threads is a key ingredient to understanding the entire multi-threaded environment. In ThreadX there are five distinct thread states, namely ready, suspended, executing, terminated, and completed. Figure 5 on page 53 shows the thread state transition diagram for ThreadX. 

A thread is in a ready state when it is ready for execution. A ready thread is not executed until it is the highest priority thread ready. When this happens, ThreadX executes the thread, which changes its state to executing.

If a higher-priority thread becomes ready, the executing thread reverts back to a ready state. The newly ready high-priority thread is then executed, which changes its logical state to executing. This transition between ready and executing states occurs every time thread preemption occurs. 

It is important to point out that at any given moment only one thread is in an executing state. This is because a thread in the executing state actually has control of the underlying processor. 

Threads that are in a suspended state are not eligible for execution. Reasons for being in a suspended state include suspension for time, queue messages, semaphores, mutexes, event flags, memory, and basic thread suspension. Once the cause for suspension is removed, the thread is placed back in a ready state.

> 스레드의 다양한 처리 상태를 이해하는 것은 전체 다중 스레드 환경을 이해하기위한 핵심 요소입니다. ThreadX에는 준비, 일시 중지, 실행, 종료 및 완료의 다섯 가지 스레드 상태가 있습니다. 53 페이지의 그림 5는 ThreadX의 스레드 상태 전환 다이어그램을 보여줍니다.
>
> 스레드는 실행 준비가되었을 때 준비 상태입니다. 준비 스레드는 우선 순위가 가장 높은 스레드 준비가 될 때까지 실행되지 않습니다. 이 경우 ThreadX는 스레드를 실행하여 상태를 실행 중으로 변경합니다.
>
> 우선 순위가 더 높은 스레드가 준비되면 실행중인 스레드가 다시 준비 상태로 돌아갑니다. 새로 준비된 우선 순위가 높은 스레드가 실행되고 논리 상태가 실행 중으로 변경됩니다. 준비 상태와 실행 상태 사이의 이러한 전환은 스레드 선점이 발생할 때마다 발생합니다.
>
> 주어진 순간에 오직 하나의 스레드 만이 실행 상태에 있음을 지적하는 것이 중요합니다. 실행 상태의 스레드가 실제로 기본 프로세서를 제어하기 때문입니다.
>
> 일시 중단 된 상태의 스레드는 실행할 수 없습니다. 일시 중단 상태에있는 이유에는 시간 일시 중단, 큐 메시지, 세마포어, 뮤텍스, 이벤트 플래그, 메모리 및 기본 스레드 일시 중단이 포함됩니다. 일시 중단 원인이 제거되면 스레드는 다시 준비 상태로 돌아갑니다.

![image-20210210132613248](https://user-images.githubusercontent.com/58545240/107464433-9f37af00-6ba3-11eb-9c30-7adc8e306a2b.png)

A thread in a completed state indicates the thread completed its processing and returned from its entry function. Remember that the entry function is specified during thread creation. A thread in a completed state cannot execute again. A thread is in a terminated state because another thread or itself called the tx_thread_terminate service.

A thread in a terminated state cannot execute again. If re-starting a completed or terminated thread is desired, the application must first delete the thread. It can then be re-created and re-started.

> 완료된 상태의 스레드는 스레드가 처리를 완료하고 입력 함수에서 반환되었음을 나타냅니다. 입력 함수는 스레드 생성 중에 지정됩니다. 완료된 상태의 스레드는 다시 실행할 수 없습니다. 다른 스레드 또는 자체가 tx_thread_terminate 서비스를 호출했기 때문에 스레드가 종료 된 상태입니다.
>
> 종료 된 상태의 스레드는 다시 실행할 수 없습니다. 완료되거나 종료 된 스레드를 다시 시작하려면 애플리케이션이 먼저 스레드를 삭제해야합니다. 그런 다음 다시 생성하고 다시 시작할 수 있습니다.

- **Thread Priorities**

As mentioned before, a thread is defined as a semiindependent program segment with a dedicated purpose. However, all threads are not created equal! The dedicated purpose of some threads is much more important than others. This heterogeneous type of thread importance is a hallmark of embedded realtime applications. 

How does ThreadX determine a thread’s importance? When a thread is created, it is assigned a numerical value representing its importance or priority. Valid numerical priorities range between 0 and 31, where a value of 0 indicates the highest thread priority and a value of 31 represents the lowest thread priority. 

Threads can have the same priority as others in the application. In addition, thread priorities can be changed during run-time.

> 앞서 언급했듯이 스레드는 전용 목적을 가진 반독립 프로그램 세그먼트로 정의됩니다. 그러나 모든 스레드가 동일하게 생성되는 것은 아닙니다! 일부 스레드의 전용 목적은 다른 스레드보다 훨씬 더 중요합니다. 이 이기종 유형의 스레드 중요도는 임베디드 실시간 애플리케이션의 특징입니다.
>
> ThreadX는 스레드의 중요성을 어떻게 결정합니까? 스레드가 생성 될 때 중요도 또는 우선 순위를 나타내는 숫자 값이 할당됩니다. 유효한 숫자 우선 순위 범위는 0에서 31 사이입니다. 여기서 0 값은 가장 높은 스레드 우선 순위를 나타내고 31 값은 가장 낮은 스레드 우선 순위를 나타냅니다.
>
> 스레드는 애플리케이션의 다른 스레드와 동일한 우선 순위를 가질 수 있습니다. 또한 스레드 우선 순위는 런타임 중에 변경할 수 있습니다.

- **Thread Scheduling**

ThreadX schedules threads based upon their priority. The ready thread with the highest priority is executed first. If multiple threads of the same priority are ready, they are executed in a first-in-first-out (FIFO) manner.

> ThreadX는 우선 순위에 따라 스레드를 예약합니다. 우선 순위가 가장 높은 준비 스레드가 먼저 실행됩니다. 우선 순위가 동일한 여러 스레드가 준비된 경우 FIFO (선입 선출) 방식으로 실행됩니다.

- **Round-Robing Scheduling**

Round-robin scheduling of multiple threads having the same priority is supported by ThreadX. This is accomplished through cooperative calls to tx_thread_relinquish. Calling this service gives all other ready threads at the same priority a chance to execute before the tx_thread_relinquish caller executes again.

> ThreadX는 우선 순위가 동일한 여러 스레드의 라운드 로빈 스케줄링을 지원합니다. 이는 tx_thread_relinquish에 대한 협력 호출을 통해 수행됩니다. 이 서비스를 호출하면 tx_thread_relinquish 호출자가 다시 실행되기 전에 동일한 우선 순위의 다른 모든 준비 스레드가 실행될 수 있습니다.

- **Time-Slicing**

Time-slicing provides another form of round-robin scheduling. In ThreadX, time-slicing is available on a per-thread basis. The thread’s time-slice is assigned during creation and can be modified during run-time.

What exactly is a time-slice? A time-slice specifies the maximum number of timer ticks (timer interrupts) that a thread can execute without giving up the processor. When a time-slice expires, all other ready threads of the same priority level are given a chance to execute before the time-sliced thread executes again. 

A fresh thread time-slice is given to a thread after it suspends, relinquishes, makes a ThreadX service call that causes preemption, or is itself time-sliced. 

When a time-sliced thread is preempted, it will resume before other ready threads of equal priority for the remainder of its time-slice. 

Using time-slicing results in a slight amount of system overhead. Since time-slicing is only useful in cases where multiple threads share the same priority, threads having a unique priority should not be assigned a time-slice.

> 시간 분할은 다른 형태의 라운드 로빈 스케줄링을 제공합니다. ThreadX에서는 스레드 단위로 시간 분할이 가능합니다. 스레드의 시간 분할 영역은 생성 중에 할당되며 런타임 중에 수정할 수 있습니다.
>
> 타임 슬라이스 란 정확히 무엇입니까? 타임 슬라이스는 스레드가 프로세서를 포기하지 않고 실행할 수있는 최대 타이머 틱 (타이머 인터럽트) 수를 지정합니다. 타임 슬라이스가 만료되면 동일한 우선 순위 레벨의 다른 모든 준비 스레드가 타임 슬라이스 스레드가 다시 실행되기 전에 실행될 기회가 주어집니다.
>
> 새로운 스레드 타임 슬라이스는 일시 중단, 포기, 선점을 유발하는 ThreadX 서비스 호출을 수행하거나 자체적으로 타임 슬라이스 된 후 스레드에 제공됩니다.
>
> 시간 분할 스레드가 선점되면 나머지 시간 분할에 대해 동일한 우선 순위의 다른 준비 스레드보다 먼저 재개됩니다.
>
> 시간 분할을 사용하면 약간의 시스템 오버 헤드가 발생합니다. 시간 분할은 여러 스레드가 동일한 우선 순위를 공유하는 경우에만 유용하므로 고유 한 우선 순위를 갖는 스레드에 시간 분할이 지정되지 않아야합니다.

- **Preemption**

Preemption is the process of temporarily interrupting an executing thread in favor of a higher-priority thread. This process is invisible to the executing thread. When the higher-priority thread is finished, control is transferred back to the exact place where the preemption took place.

This is a very important feature in real-time systems because it facilitates fast response to important application events. Although a very important feature, preemption can also be a source of a variety of problems, including starvation, excessive overhead, and priority inversion.

> 선점은 우선 순위가 높은 스레드를 위해 실행중인 스레드를 일시적으로 중단하는 프로세스입니다. 이 프로세스는 실행중인 스레드에 표시되지 않습니다. 우선 순위가 높은 스레드가 완료되면 제어가 선점이 발생한 정확한 위치로 다시 전송됩니다.
>
> 이는 중요한 애플리케이션 이벤트에 대한 빠른 응답을 용이하게하기 때문에 실시간 시스템에서 매우 중요한 기능입니다. 매우 중요한 기능이지만 선점은 기아, 과도한 오버 헤드, 우선 순위 반전 등 다양한 문제의 원인이 될 수도 있습니다.

- **Preemption-Threshold**

In order to ease some of the inherent problems of preemption, ThreadX provides a unique and advanced feature called preemption-threshold. 

What is a preemption-threshold? A preemptionthreshold allows a thread to specify a priority ceiling for disabling preemption. Threads that have higher priorities than the ceiling are still allowed to preempt, while those less than the ceiling are not allowed to preempt. 

For example, suppose a thread of priority 20 only interacts with a group of threads that have priorities between 15 and 20. During its critical sections, the thread of priority 20 can set its preemption-threshold to 15, thereby preventing preemption from all of the threads that it interacts with. This still permits really important threads (priorities between 0 and 14) to preempt this thread during its critical section processing, which results in much more responsive processing. 

Of course, it is still possible for a thread to disable all preemption by setting its preemption-threshold to 0. In addition, preemption-thresholds can be changed during run-time.

Note that using preemption-threshold disables timeslicing for the specified thread.

> 선점의 내재 된 문제를 완화하기 위해 ThreadX는 선점 임계 값이라는 고유하고 고급 기능을 제공합니다.
>
> 선점 임계 값이란 무엇입니까? preemptionthreshold를 사용하면 스레드가 선점을 비활성화하기위한 우선 순위 상한을 지정할 수 있습니다. 한도보다 우선 순위가 높은 스레드는 여전히 선점 할 수 있지만, 한도보다 낮은 스레드는 선점 할 수 없습니다.
>
> 예를 들어 우선 순위가 20 인 스레드가 우선 순위가 15에서 20 사이 인 스레드 그룹과 만 상호 작용한다고 가정합니다. 중요 섹션 동안 우선 순위 20의 스레드는 선점 임계 값을 15로 설정하여 모든 스레드에서 선점을 방지 할 수 있습니다. 상호 작용하는 스레드. 이것은 여전히 매우 중요한 스레드 (0에서 14 사이의 우선 순위)가 중요한 섹션 처리 중에이 스레드를 선점하도록 허용하여 훨씬 더 응답 성이 뛰어난 처리를 가능하게합니다.
>
> 물론 preemption-threshold를 0으로 설정하여 스레드가 모든 선점을 비활성화 할 수 있습니다. 또한 preemption-thresholds는 런타임 중에 변경할 수 있습니다.
>
> preemption-threshold를 사용하면 지정된 스레드에 대한 타임 라이 싱이 비활성화됩니다.

- **Priority Inheritance**

ThreadX also supports optional priority inheritance within its mutex services described later in this chapter. Priority inheritance allows a lower priority thread to temporarily assume the priority of a high priority thread that is waiting for a mutex owned by the lower priority thread. This capability helps the application to avoid un-deterministic priority inversion by eliminating preemption of intermediate thread priorities. Of course, preemption-threshold may be used to achieve a similar result.

> ThreadX는 또한이 장의 뒷부분에서 설명하는 뮤텍스 서비스 내에서 선택적 우선 순위 상속을 지원합니다. 우선 순위 상속을 통해 우선 순위가 낮은 스레드가 우선 순위가 낮은 스레드가 소유 한 뮤텍스를 기다리는 높은 우선 순위 스레드의 우선 순위를 일시적으로 가정 할 수 있습니다. 이 기능은 애플리케이션이 중간 스레드 우선 순위의 선점을 제거하여 비 결정적 우선 순위 반전을 방지하는 데 도움이됩니다. 물론 선점 임계 값을 사용하여 유사한 결과를 얻을 수 있습니다.

- **Thread Creation**

Application threads are created during initialization or during the execution of other application threads. There are no limits on the number of threads that can be created by an application.

> 응용 프로그램 스레드는 초기화 또는 다른 응용 프로그램 스레드 실행 중에 생성됩니다. 응용 프로그램에서 만들 수있는 스레드 수에는 제한이 없습니다.

- **Thread Control Block TX_THREAD**

The characteristics of each thread are contained in its control block. This structure is defined in the tx_api.h file. 

A thread’s control block can be located anywhere in memory, but it is most common to make the control block a global structure by defining it outside the scope of any function. 

Locating the control block in other areas requires a bit more care, just like all dynamically allocated memory. If a control block is allocated within a C function, the memory associated with it is part of the calling thread’s stack. In general, using local storage for control blocks should be avoided because once the function returns, then all of its local variable stack space is released—regardless of whether another thread is using it for a control block! 

In most cases, the application is oblivious to the contents of the thread’s control block. However, there are some situations, especially in debug, where looking at certain members is quite useful. The following are a few of the more useful control block members:

> 각 스레드의 특성은 제어 블록에 포함되어 있습니다. 이 구조는 tx_api.h 파일에 정의되어 있습니다.
>
> 스레드의 제어 블록은 메모리의 어느 위치 에나있을 수 있지만 제어 블록을 함수 범위 외부에서 정의하여 전역 구조로 만드는 것이 가장 일반적입니다.
>
> 다른 영역에서 제어 블록을 찾으려면 모든 동적 할당 메모리와 마찬가지로 좀 더주의가 필요합니다. 제어 블록이 C 함수 내에 할당 된 경우 이와 관련된 메모리는 호출 스레드 스택의 일부입니다. 일반적으로 제어 블록에 로컬 스토리지를 사용하는 것은 피해야합니다. 함수가 반환되면 다른 스레드가 제어 블록에이를 사용하는지 여부에 관계없이 모든 로컬 변수 스택 공간이 해제되기 때문입니다!
>
> 대부분의 경우 응용 프로그램은 스레드 제어 블록의 내용을 인식하지 못합니다. 그러나 특히 디버그에서 특정 멤버를 보는 것이 매우 유용한 일부 상황이 있습니다. 다음은 몇 가지 유용한 제어 블록 멤버입니다.

**tx_run_count** 

This member contains a counter of how many times the thread has been scheduled. An increasing counter indicates the thread is being scheduled and executed.

> 이 멤버는 스레드가 예약 된 횟수에 대한 카운터를 포함합니다. 카운터가 증가하면 스레드가 예약되고 실행되고 있음을 나타냅니다.

**tx_state**

This member contains the state of the associated thread. The following list represents the possible thread states:

> 이 멤버는 연관된 스레드의 상태를 포함합니다. 다음 목록은 가능한 스레드 상태를 나타냅니다.

![image-20210210135743791](https://user-images.githubusercontent.com/58545240/107466777-fd669100-6ba7-11eb-9c99-36714ca22e5b.png)

Of course there are many other interesting fields in the thread control block, including the stack pointer, time-slice value, priorities, etc. The user is welcome to review any and all of the control block members, but modification is strictly prohibited!

> 물론 스택 포인터, 시간 분할 값, 우선 순위 등을 포함하여 스레드 제어 블록에는 다른 많은 흥미로운 필드가 있습니다. 사용자는 모든 제어 블록 구성원을 검토 할 수 있지만 수정은 엄격히 금지됩니다!

There is no equate for the “executing” state mentioned earlier in this section. It is not necessary since there is only one executing thread at a given time. The state of an executing thread is also `TX_READY`

> 이 섹션의 앞부분에서 언급 한 "실행 중"상태에 해당하는 것은 없습니다. 주어진 시간에 실행 스레드가 하나만 있기 때문에 필요하지 않습니다. 실행중인 스레드의 상태도 TX_READY입니다.

- **Currently Executing Thread**

As mentioned before, there is only one thread executing at any given time. There are several ways to identify the executing thread, depending on who is making the request. 

A program segment can get the control block address of the executing thread by calling tx_thread_identify. This is useful in shared portions of application code that are executed from multiple threads. 

In debug sessions, users can examine the internal ThreadX pointer _tx_thread_current_ptr. It contains the control block address of the currently executing thread. If this pointer is NULL, no application thread is executing; i.e., ThreadX is waiting in its scheduling loop for a thread to become ready

> 앞서 언급했듯이 주어진 시간에 실행되는 스레드는 하나뿐입니다. 요청하는 사람에 따라 실행중인 스레드를 식별하는 방법에는 여러 가지가 있습니다.
>
> 프로그램 세그먼트는 tx_thread_identify를 호출하여 실행중인 스레드의 제어 블록 주소를 가져올 수 있습니다. 이것은 여러 스레드에서 실행되는 응용 프로그램 코드의 공유 부분에서 유용합니다.
>
> 디버그 세션에서 사용자는 내부 ThreadX 포인터 _tx_thread_current_ptr을 검사 할 수 있습니다. 현재 실행중인 스레드의 제어 블록 주소를 포함합니다. 이 포인터가 NULL이면 응용 프로그램 스레드가 실행되지 않습니다. 즉, ThreadX는 스레드가 준비 될 때까지 스케줄링 루프에서 기다리고 있습니다.

- **Thread Stack Area**

Each thread must have its own stack for saving the context of its last execution and compiler use. Most C compilers use the stack for making function calls and for temporarily allocating local variables. Figure 6 shows a typical thread’s stack. 

Where is a thread stack located? This is really up to the application. The stack area is specified during thread creation and can be located anywhere in the target’s address space. This is a very important feature because it allows applications to improve performance of important threads by placing their stack in high-speed RAM. 

How big should a stack be? This is one of the most frequently asked questions about threads. A thread’s stack area must be large enough to accommodate worst-case function call nesting, local variable allocation, and saving its last execution context. 

The minimum stack size, TX_MINIMUM_STACK, is defined by ThreadX. A stack of this size supports saving a thread’s context and minimum amount of function calls and local variable allocation. 

For most threads, the minimum stack size is simply too small. The user must come up with the worstcase size requirement by examining function-call nesting and local variable allocation. Of course, it is always better to error towards a larger stack area. 

After the application is debugged, it is possible to go back and tune the thread stacks sizes if memory is scarce. A favorite trick is to preset all stack areas with an easily identifiable data pattern like (0xEFEF) prior to creating the threads. After the application has been thoroughly put through its paces, the stack areas can be examined to see how much was actually used by finding the area of the stack where the preset pattern is still intact. Figure 7 on page 61 shows a stack preset to 0xEFEF after thorough thread execution.

> 각 스레드에는 마지막 실행 및 컴파일러 사용의 컨텍스트를 저장하기위한 자체 스택이 있어야합니다. 대부분의 C 컴파일러는 스택을 사용하여 함수를 호출하고 일시적으로 지역 변수를 할당합니다. 그림 6은 일반적인 스레드 스택을 보여줍니다.
>
> 스레드 스택은 어디에 있습니까? 이것은 실제로 응용 프로그램에 달려 있습니다. 스택 영역은 스레드 생성 중에 지정되며 대상 주소 공간의 어느 곳에 나 위치 할 수 있습니다. 이것은 응용 프로그램이 고속 RAM에 스택을 배치하여 중요한 스레드의 성능을 향상시킬 수 있도록하기 때문에 매우 중요한 기능입니다.
>
> 스택은 얼마나 커야합니까? 이것은 스레드에 대해 가장 자주 묻는 질문 중 하나입니다. 스레드의 스택 영역은 최악의 경우 함수 호출 중첩, 로컬 변수 할당 및 마지막 실행 컨텍스트 저장을 수용 할 수있을만큼 충분히 커야합니다.
>
> 최소 스택 크기 TX_MINIMUM_STACK은 ThreadX에 의해 정의됩니다. 이 크기의 스택은 스레드의 컨텍스트 저장과 최소 함수 호출 및 로컬 변수 할당을 지원합니다.
>
> 대부분의 스레드에서 최소 스택 크기는 너무 작습니다. 사용자는 함수 호출 중첩 및 지역 변수 할당을 검사하여 최악의 크기 요구 사항을 제시해야합니다. 물론 더 큰 스택 영역으로 오류를 일으키는 것이 항상 좋습니다.
>
> 응용 프로그램이 디버깅 된 후 메모리가 부족한 경우 돌아가서 스레드 스택 크기를 조정할 수 있습니다. 가장 좋아하는 트릭은 스레드를 만들기 전에 (0xEFEF)와 같이 쉽게 식별 할 수있는 데이터 패턴으로 모든 스택 영역을 미리 설정하는 것입니다. 응용 프로그램이 완전히 진행된 후 스택 영역을 검사하여 사전 설정된 패턴이 여전히 손상되지 않은 스택 영역을 찾아 실제로 얼마나 많이 사용되었는지 확인할 수 있습니다. 61 페이지의 그림 7은 스레드 실행 후 0xEFEF로 사전 설정된 스택을 보여줍니다.

![image-20210210140459733](https://user-images.githubusercontent.com/58545240/107467247-0310a680-6ba9-11eb-850c-98e0acbd23d6.png)

![image-20210210140641509](https://user-images.githubusercontent.com/58545240/107467373-39e6bc80-6ba9-11eb-999e-869c823fa59d.png)

- **Memory Pitfalls**

The stack requirements for threads can be quite large. Therefore, it is important to design the application to have a reasonable number of threads. Furthermore, some care must be taken to avoid excessive stack usage within threads. Recursive algorithms and large local data structures should generally be avoided. 

What happens when a stack area is too small? In most cases, the run-time environment simply assumes there is enough stack space. This causes thread execution to corrupt memory adjacent (usually before) its stack area. The results are very unpredictable, but most often result in an un-natural change in the program counter. This is often called “jumping into the weeds.” Of course, the only way to prevent this is to ensure that all thread stacks are large enough. 

> 스레드에 대한 스택 요구 사항은 상당히 클 수 있습니다. 따라서 적절한 수의 스레드를 갖도록 응용 프로그램을 설계하는 것이 중요합니다. 또한 스레드 내에서 과도한 스택 사용을 방지하려면 약간의주의를 기울여야합니다. 재귀 알고리즘과 대규모 로컬 데이터 구조는 일반적으로 피해야합니다.
>
> 스택 영역이 너무 작 으면 어떻게됩니까? 대부분의 경우 런타임 환경은 스택 공간이 충분하다고 가정합니다. 이로 인해 스레드 실행으로 인해 스택 영역에 인접한 (일반적으로 이전) 메모리가 손상됩니다. 결과는 매우 예측할 수 없지만 대개 프로그램 카운터가 부 자연스럽게 변경됩니다. 이것은 종종 "잡초 속으로 점프"라고 불립니다. 물론이를 방지하는 유일한 방법은 모든 스레드 스택이 충분히 큰지 확인하는 것입니다.

- **Reentrancy**

One of the real beauties of multi-threading is that the same C function can be called from multiple threads. This provides great power and also helps reduce code space. However, it does require that C functions called from multiple threads are reentrant. 

What does reentrant mean? Basically, a reentrant function stores the caller’s return address on the current stack and does not rely on global or static C variables that it previously setup. Most compilers place the return address on the stack. Hence, application developers must only worry about the use of globals and statics. 

An example of a non-reentrant function is the string token function “strtok” found in the standard C library. This function remembers the previous string pointer on subsequent calls. It does this with a static string pointer. If this function is called from multiple threads, it would most likely return an invalid pointer.

> 멀티 스레딩의 진정한 장점 중 하나는 동일한 C 함수를 여러 스레드에서 호출 할 수 있다는 것입니다. 이는 강력한 성능을 제공하고 코드 공간을 줄이는 데 도움이됩니다. 그러나 여러 스레드에서 호출 된 C 함수가 재진입 할 수 있어야합니다.
>
> 재진입이란 무엇을 의미합니까? 기본적으로 재진입 함수는 호출자의 반환 주소를 현재 스택에 저장하고 이전에 설정 한 전역 또는 정적 C 변수에 의존하지 않습니다. 대부분의 컴파일러는 스택에 반환 주소를 배치합니다. 따라서 응용 프로그램 개발자는 전역 및 정적 사용에 대해서만 걱정해야합니다.
>
> 재진입이 아닌 함수의 예는 표준 C 라이브러리에있는 문자열 토큰 함수 "strtok"입니다. 이 함수는 후속 호출에서 이전 문자열 포인터를 기억합니다. 정적 문자열 포인터로이를 수행합니다. 이 함수가 여러 스레드에서 호출되면 유효하지 않은 포인터를 반환 할 가능성이 높습니다.

- **Thread Priority Pitfalls**

Selecting thread priorities is one of the most important aspects of multi-threading. It is sometimes very tempting to assign priorities based on a perceived notion of thread importance rather than determining what is exactly required during run-time. Misuse of thread priorities can starve other threads, create priority inversion, reduce processing bandwidth, and make the application’s run-time behavior difficult to understand.

> 스레드 우선 순위를 선택하는 것은 멀티 스레딩의 가장 중요한 측면 중 하나입니다.
>
> 런타임에 정확히 필요한 것이 무엇인지 결정하기보다는 스레드 중요도에 대한 인식 된 개념을 기반으로 우선 순위를 할당하는 것이 때로는 매우 유혹적입니다.
>
> 스레드 우선 순위를 잘못 사용하면 다른 스레드가 고갈되고 우선 순위 반전이 생성되며 처리 대역폭이 줄어들고 응용 프로그램의 런타임 동작을 이해하기 어려울 수 있습니다.

As mentioned before, ThreadX provides a prioritybased, preemptive scheduling algorithm. Lower priority threads do not execute until there are no  Thread Execution 63 Express Logic, Inc. higher-priority threads ready for execution. If a higher-priority thread is always ready, the lowerpriority threads never execute. This condition is called thread starvation.

> 앞서 언급했듯이 ThreadX는 우선 순위 기반의 선점 스케줄링 알고리즘을 제공합니다.
>
> 우선 순위가 낮은 스레드는 스레드 실행이 없을 때까지 실행되지 않습니다. 63 Express Logic, Inc.
> 우선 순위가 더 높은 스레드를 실행할 준비가되었습니다.
> 우선 순위가 높은 스레드가 항상 준비되어 있으면 우선 순위가 낮은 스레드는 실행되지 않습니다.
> 이 상태를 스레드 부족이라고합니다.

Most starvation problems are detected early in debug and can be solved by ensuring that higher priority threads don’t execute ontinuously. 

Alternatively, logic can be added to the application that gradually raises the priority of starved threads until they get a chance to execute.

> 대부분의 기아 문제는 디버그 초기에 감지되며 우선 순위가 더 높은 스레드가 지속적으로 실행되지 않도록하여 해결할 수 있습니다.
>
> 또는 응용 프로그램에 논리를 추가하여 실행 기회를 얻을 때까지 부족한 스레드의 우선 순위를 점차적으로 높일 수 있습니다.

Another unpleasant pitfall associated with thread priorities is priority inversion. Priority inversion takes place when a higher-priority thread is suspended because a lower-priority thread has a needed resource. Of course, in some instances it is necessary for two threads of different priority to share a common resource. If these threads are the only ones active, the priority inversion time is bounded by the time the lower-priority thread holds the resource. This condition is both deterministic and quite normal. However, if threads of intermediate priority become active during this priority inversion condition, the priority inversion time is no longer deterministic and could cause an application failure.

> 스레드 우선 순위와 관련된 또 다른 불쾌한 함정은 우선 순위 반전입니다.
> 우선 순위가 낮은 스레드에 필요한 리소스가 있기 때문에 우선 순위가 높은 스레드가 일시 중단 될 때 우선 순위 반전이 발생합니다.
>
> 물론 어떤 경우에는 우선 순위가 다른 두 스레드가 공통 리소스를 공유해야합니다.
>
> 이 스레드가 유일한 활성 스레드 인 경우 우선 순위 반전 시간은 우선 순위가 낮은 스레드가 자원을 보유하는 시간에 의해 제한됩니다. 이 조건은 결정적이며 매우 정상적입니다.
>
> 그러나 중간 우선 순위의 스레드가 이 우선 순위 반전 조건 동안 활성화되면 우선 순위 반전 시간이 더 이상 결정적이지 않으며 응용 프로그램 오류가 발생할 수 있습니다.

There are principally three distinct methods of preventing un-deterministic priority inversion in ThreadX. First, the application priority selections and run-time behavior can be designed in a manner that prevents the priority inversion problem. Second, lower-priority threads can utilize preemptionthreshold to block preemption from intermediate threads while they share resources with higherpriority threads. Finally, threads using ThreadX mutex objects to protect system resources may utilize the optional mutex priority inheritance to eliminate un-deterministic priority inversion.

> ThreadX에서 비 결정적 우선 순위 반전을 방지하는 세 가지 다른 방법이 있습니다.
>
> 첫째, 우선 순위 반전 문제를 방지하는 방식으로 응용 프로그램 우선 순위 선택 및 런타임 동작을 설계 할 수 있습니다.
>
> 둘째, 우선 순위가 낮은 스레드는 preemptionthreshold를 활용하여 우선 순위가 높은 스레드와 리소스를 공유하는 동안 중간 스레드의 선점을 차단할 수 있습니다.
>
> 마지막으로 ThreadX 뮤텍스 객체를 사용하여 시스템 리소스를 보호하는 스레드는 선택적 뮤텍스 우선 순위 상속을 활용하여 비 결정적 우선 순위 반전을 제거 할 수 있습니다.

- **Priority Overhead**

One of the most overlooked ways to reduce overhead in multi-threading is to reduce the number of context switches. As previously mentioned, a context switch occurs when execution of a higherpriority thread is favored over that of the executing thread. It is worthwhile to mention that higher-priority threads can become ready as a result of both external events (like interrupts) and from service calls made by the executing thread.

> 멀티 스레딩에서 오버 헤드를 줄이는 가장 간과되는 방법 중 하나는 컨텍스트 전환 수를 줄이는 것입니다.
>
> 앞서 언급했듯이 컨텍스트 전환은 실행중인 스레드보다 우선 순위가 높은 스레드의 실행이 선호 될 때 발생합니다.
>
> 우선 순위가 더 높은 스레드는 외부 이벤트 (인터럽트와 같은)와 실행 스레드가 수행 한 서비스 호출의 결과로 준비 될 수 있다는 점을 언급 할 가치가 있습니다.

To illustrate the effects thread priorities have on context switch overhead, assume a three thread environment with threads named thread_1, thread_2, and thread_3. Assume further that all of the threads are in a state of suspension waiting for a message. When thread_1 receives a message, it immediately forwards it to thread_2. Thread_2 then forwards the message to thread_3. Thread_3 just discards the message. After each thread processes its message, they go back and wait for another.

> 스레드 우선 순위가 컨텍스트 전환 오버 헤드에 미치는 영향을 설명하기 위해 thread_1, thread_2 및 thread_3이라는 스레드가있는 3 개의 스레드 환경을 가정합니다.
> 모든 스레드가 메시지를 기다리는 일시 중단 상태에 있다고 가정하십시오.
>
> thread_1이 메시지를 받으면 즉시 thread_2로 전달합니다. 그런 다음 Thread_2는 메시지를 thread_3로 전달합니다. Thread_3은 메시지를 버립니다.
>
> 각 스레드가 메시지를 처리 한 후 다시 돌아가서 다른 스레드를 기다립니다.

The processing required to execute these three threads varies greatly depending on their priorities. If all of the threads have the same priority, a single context switch occurs between their execution. The context switch occurs when each thread suspends on an empty message queue.

> 이 세 개의 스레드를 실행하는 데 필요한 처리는 우선 순위에 따라 크게 다릅니다.
> 모든 스레드의 우선 순위가 동일한 경우 실행 사이에 단일 컨텍스트 전환이 발생합니다.
> 컨텍스트 전환은 각 스레드가 빈 메시지 큐에서 일시 중단 될 때 발생합니다.

However, if thread_2 is higher-priority than thread_1 and thread_3 is higher-priority than thread_2, the number of context switches doubles. This is because another context switch occurs inside of the tx_queue_send service when it detects that a higherpriority thread is now ready.

> 그러나 thread_2가 thread_1보다 우선 순위가 높고 thread_3이 thread_2보다 우선 순위가 높으면 컨텍스트 전환 수가 두 배가됩니다.
> 이는 우선 순위가 더 높은 스레드가 이제 준비되었음을 감지 할 때 tx_queue_send 서비스 내부에서 다른 컨텍스트 전환이 발생하기 때문입니다.

The ThreadX preemption-threshold mechanism can avoid these extra context switches and still allow the previously mentioned priority selections. This is a really important feature because it allows several thread priorities during scheduling, while at the same time eliminating some of the unwanted context switching between them during thread execution.

> ThreadX 선점 임계 값 메커니즘은 이러한 추가 컨텍스트 전환을 피하고 이전에 언급 한 우선 순위 선택을 계속 허용 할 수 있습니다.
> 이것은 스케줄링 중에 여러 스레드 우선 순위를 허용하는 동시에 스레드 실행 중에 원치 않는 컨텍스트 전환을 제거하기 때문에 정말 중요한 기능입니다.

- **Debugging Pitfalls**

Debugging multi-threaded applications is a little more difficult because the same program code can be executed from multiple threads. In such cases, a break-point alone may not be enough. The debugger must also view the current thread pointer _tx_thread_current_ptr to see if the calling thread is the one to debug.

> 다중 스레드 응용 프로그램을 디버깅하는 것은 동일한 프로그램 코드가 여러 스레드에서 실행될 수 있기 때문에 조금 더 어렵습니다.
>
> 이러한 경우 중단 점만으로는 충분하지 않을 수 있습니다.
>
> 디버거는 현재 스레드 포인터도 확인해야합니다.
>   tx_thread_current_ptr을 사용하여 호출 스레드가 디버깅 할 스레드인지 확인합니다.

Much of this is being handled in multi-threading support packages offered through various development tool vendors. Because of its simple design, integrating ThreadX with different development tools is relatively easy.

> 이 중 대부분은 다양한 개발 도구 공급 업체를 통해 제공되는 멀티 스레딩 지원 패키지에서 처리되고 있습니다.
>
> 단순한 디자인으로 인해 ThreadX를 다른 개발 도구와 통합하는 것은 비교적 쉽습니다.

Stack size is always an important debug topic in multi-threading. Whenever totally strange behavior is seen, it is usually a good first guess to increase stack sizes for all threads—especially the stack size of the last executing thread!

> 스택 크기는 항상 멀티 스레딩에서 중요한 디버그 주제입니다.
> 완전히 이상한 동작이 보일 때마다 일반적으로 모든 스레드의 스택 크기를 늘리는 것이 좋습니다. 특히 마지막 실행 스레드의 스택 크기를 늘리는 것이 좋습니다.

[위로](#threadx-rtos)

## Message Queues

[위로](#threadx-rtos)

Message queues are the primary means of interthread communication in ThreadX. One or more messages can reside in a message queue. A message queue that holds a single message is commonly called a mailbox.

Messages are copied to a queue by tx_queue_send and are copied from a queue by tx_queue_receive. The only exception to this is when a thread is suspended while waiting for a message on an empty queue. In this case, the next message sent to the queue is placed directly into the thread’s destination area.

Each message queue is a public resource. ThreadX places no constraints on how message queues are used.

> 메시지 큐는 ThreadX에서 스레드 간 통신의 기본 수단입니다. 하나 이상의 메시지가 메시지 대기열에있을 수 있습니다. 단일 메시지를 보관하는 메시지 큐를 일반적으로 사서함이라고합니다.
>
> 메시지는 tx_queue_send에 의해 큐로 복사되고 tx_queue_receive에 의해 큐에서 복사됩니다. 이에 대한 유일한 예외는 빈 대기열에서 메시지를 기다리는 동안 스레드가 일시 중단되는 경우입니다. 이 경우 대기열에 전송 된 다음 메시지는 스레드의 대상 영역에 직접 배치됩니다.
>
> 각 메시지 대기열은 공용 리소스입니다. ThreadX는 메시지 큐가 사용되는 방식에 제한을 두지 않습니다.

- **Creating Message Queues**

Message queues are created either during initialization or during run-time by application threads. There are no limits on the number of message queues in an application.

> 메시지 큐는 초기화 중에 또는 런타임 중에 애플리케이션 스레드에 의해 작성됩니다. 애플리케이션의 메시지 큐 수에는 제한이 없습니다.

- **Message Size**

Each message queue supports a number of fixedsized messages. The available message sizes are 1, 2, 4, 8, and 16 32-bit words. The message size is specified when the queue is created. 

Application messages greater than 16 words must be passed by pointer. This is accomplished by creating a queue with a message size of 1 word (enough to hold a pointer) and then sending and receiving message pointers instead of the entire message.

> 각 메시지 대기열은 여러 고정 크기 메시지를 지원합니다. 사용 가능한 메시지 크기는 1, 2, 4, 8 및 16 32 비트 단어입니다. 메시지 크기는 큐가 생성 될 때 지정됩니다.
>
> 16 단어보다 큰 응용 프로그램 메시지는 포인터로 전달되어야합니다. 이는 메시지 크기가 1 단어 (포인터를 보유하기에 충분한)의 큐를 만든 다음 전체 메시지 대신 메시지 포인터를 보내고받는 방식으로 수행됩니다.

- **Message Queue Capactiy**

The number of messages a queue can hold is a function of its message size and the size of the memory area supplied during creation. The total message capacity of the queue is calculated by dividing the number of bytes in each message into the total number of bytes in the supplied memory area. 

For example, if a message queue that supports a message size of 1 32-bit word (4 bytes) is created with a 100-byte memory area, its capacity is 25 messages.

> 큐가 보유 할 수있는 메시지 수는 메시지 크기와 작성 중에 제공된 메모리 영역의 크기에 따라 달라집니다.
>
> 큐의 총 메시지 용량은 다음과 같이 계산됩니다.
> 각 메시지의 바이트 수를 제공된 메모리 영역의 총 바이트 수로 나눕니다.
>
> 예를 들어, 1 개의 32 비트 워드 (4 바이트)의 메시지 크기를 지원하는 메시지 대기열이 100 바이트 메모리 영역으로 생성되는 경우 용량은 25 개 메시지입니다.

- **Queue Memory Area**

As mentioned before, the memory area for buffering messages is specified during queue creation. Like other memory areas in ThreadX, it can be located anywhere in the target’s address space. This is an important feature because it gives the application considerable flexibility. For example, an application might locate the memory area of a very  important queue in high-speed RAM in order to improve performance.

> 앞에서 언급했듯이 메시지 버퍼링을위한 메모리 영역은 큐 생성 중에 지정됩니다.
>
> ThreadX의 다른 메모리 영역과 마찬가지로 대상 주소 공간의 어디에나 위치 할 수 있습니다.
>
> 이것은 애플리케이션에 상당한 유연성을 제공하기 때문에 중요한 기능입니다.
>
> 예를 들어, 응용 프로그램은 성능 향상을 위해 고속 RAM에서 매우 중요한 대기열의 메모리 영역을 찾을 수 있습니다.

- **Thread Suspension**

Application threads can suspend while attempting to send or receive a message from a queue. Typically, thread suspension involves waiting for a message from an empty queue. However, it is also possible for a thread to suspend trying to send a message to a full queue. 

After the condition for suspension is resolved, the service requested is completed and the waiting thread is resumed. If multiple threads are suspended on the same queue, they are resumed in the order they were suspended (FIFO). 

However, priority resumption is also possible if the application calls tx_queue_prioritize prior to the queue service that lifts thread suspension. The queue prioritize service places the highest priority thread at the front of the suspension list, while leaving all other suspended threads in the same FIFO order. 

Time-outs are also available for all queue suspensions. Basically, a time-out specifies the maximum number of timer ticks the thread will stay suspended. If a time-out occurs, the thread is resumed and the service returns with the appropriate error code.

> 큐에서 메시지를 보내거나받는 동안 응용 프로그램 스레드가 일시 중지 될 수 있습니다. 일반적으로 스레드 일시 중단에는 빈 큐에서 메시지를 기다리는 것이 포함됩니다. 그러나 스레드가 전체 큐에 메시지를 보내려고 시도하는 것을 일시 중단 할 수도 있습니다.
>
> 일시 중단 조건이 해결되면 요청 된 서비스가 완료되고 대기중인 스레드가 재개됩니다. 여러 스레드가 동일한 대기열에 일시 중단 된 경우 일시 중단 된 순서 (FIFO)에 따라 다시 시작됩니다.
>
> 그러나 스레드 일시 중단을 해제하는 큐 서비스 이전에 애플리케이션이 tx_queue_prioritize를 호출하는 경우에도 우선 순위 재개가 가능합니다. 큐 우선 순위 지정 서비스는 가장 높은 우선 순위 스레드를 일시 중단 목록의 맨 앞에 배치하고 다른 모든 일시 중단 된 스레드는 동일한 FIFO 순서로 둡니다.
>
> 시간 제한은 모든 대기열 일시 중단에도 사용할 수 있습니다. 기본적으로 시간 제한은 스레드가 일시 중단 된 상태로 유지되는 최대 타이머 틱 수를 지정합니다. 시간 초과가 발생하면 스레드가 재개되고 서비스가 적절한 오류 코드와 함께 반환됩니다.

- **Queue Control Block TX_QUEUE**

The characteristics of each message queue are found in its control block. It contains interesting information such as the number of messages in the queue. This structure is defined in the tx_api.h file. Message queue control blocks can also be located anywhere in memory, but it is most common to make the control block a global structure by defining it outside the scope of any function.

> 각 메시지 큐의 특성은 제어 블록에서 찾을 수 있습니다. 큐에있는 메시지 수와 같은 흥미로운 정보가 포함되어 있습니다.
>
> 이 구조는 tx_api.h 파일에 정의되어 있습니다. 메시지 큐 제어 블록은 메모리의 어느 위치 에나 위치 할 수도 있지만 제어 블록을 모든 기능의 범위 밖에서 정의하여 글로벌 구조로 만드는 것이 가장 일반적입니다.

- **Message Destination Pitfall**

As mentioned previously, messages are copied between the queue area and application data areas. It is very important to insure that the destination for a received message is large enough to hold the entire message. If not, the memory following the message destination will likely be corrupted. 

This is especially lethal when a too-small message destination is on the stack—nothing like corrupting the return address of a function!

> 앞서 언급했듯이 메시지는 큐 영역과 애플리케이션 데이터 영역간에 복사됩니다. 수신 된 메시지의 대상이 전체 메시지를 저장할 수있을만큼 충분히 큰지 확인하는 것이 매우 중요합니다. 그렇지 않으면 메시지 대상 다음의 메모리가 손상 될 수 있습니다.
>
> 이는 함수의 반환 주소를 손상시키는 것과 같이 너무 작은 메시지 대상이 스택에있을 때 특히 치명적입니다.

[위로](#threadx-rtos)

## Counting Semaphores

[위로](#threadx-rtos)

ThreadX provides 32-bit counting semaphores that range in value between 0 and 4,294,967,295. There are two operations for counting semaphores: tx_semaphore_get and tx_semaphore_put. The get operation decreases the semaphore by one. If the semaphore is 0, the get operation is not successful. The inverse of the get operation is the put operation. It increases the semaphore by one. 

Each counting semaphore is a public resource. ThreadX places no constraints on how counting semaphores are used. 

Counting semaphores are typically used for mutual exclusion. However, counting semaphores can also be used as a method for event notification.

> ThreadX는 0에서 4,294,967,295 사이의 값 범위를 갖는 32 비트 카운팅 세마포어를 제공합니다. 세마포를 계산하는 작업에는 tx_semaphore_get과 tx_semaphore_put의 두 가지 작업이 있습니다.
>
> get 작업은 세마포어를 하나씩 줄입니다. 세마포어가 0이면 가져 오기 작업이 성공하지 못합니다. get 작업의 역은 put 작업입니다. 세마포어를 하나씩 증가시킵니다.
>
> 각 계수 세마포어는 공용 리소스입니다. ThreadX는 세마포어를 계산하는 방법에 제한을 두지 않습니다.
>
> 세마포 계산은 일반적으로 상호 배제에 사용됩니다. 그러나 카운트 세마포어는 이벤트 알림을위한 방법으로도 사용할 수 있습니다.

- **Mutual Exclusion**

Mutual exclusion pertains to controlling the access of threads to certain application areas (also called critical sections or application resources). When used for mutual exclusion, the “current count” of a semaphore represents the total number of threads that are allowed access. In most cases, counting semaphores used for mutual exclusion will have an initial value of 1, meaning that only one thread can access the associated resource at a time. Counting semaphores that only have values of 0 or 1 are commonly called binary semaphores. 

If a binary semaphore is being used, the user must prevent the same thread from performing a get operation on a semaphore it already owns. A second get would be unsuccessful and could cause indefinite suspension of the calling thread and permanent unavailability of the resource.

> 상호 배제는 특정 애플리케이션 영역 (중요 섹션 또는 애플리케이션 자원이라고도 함)에 대한 스레드 액세스 제어와 관련이 있습니다.
>
> 상호 배제에 사용되는 경우 세마포어의 "현재 수"는 액세스가 허용 된 총 스레드 수를 나타냅니다. 대부분의 경우 상호 배제에 사용되는 계산 세마포어의 초기 값은 1입니다. 즉, 한 번에 하나의 스레드 만 연결된 리소스에 액세스 할 수 있습니다. 값이 0 또는 1 인 세마포를 계산하는 것을 일반적으로 이진 세마포라고합니다.
>
> 바이너리 세마포어를 사용하는 경우 사용자는 동일한 스레드가 이미 소유 한 세마포어에 대해 get 작업을 수행하지 못하도록해야합니다. 두 번째 가져 오기는 실패하고 호출 스레드가 무기한 중단되고 리소스를 영구적으로 사용할 수 없게됩니다.

- **Event Notification**

It is also possible to use counting semaphores as event notification, in a producer-consumer fashion. The consumer attempts to get the counting semaphore while the producer increases the semaphore whenever something is available. Such semaphores usually have an initial value of 0 and won’t increase until the producer has something ready for the consumer. 

> 생산자-소비자 방식으로 이벤트 알림으로 카운팅 세마포어를 사용할 수도 있습니다.
>
> 소비자는 카운팅 세마포어를 얻으려고 시도하는 반면 생산자는 무언가를 사용할 수있을 때마다 세마포어를 증가시킵니다. 이러한 세마포어는 일반적으로 초기 값이 0이며 생산자가 소비자를 위해 준비 할 때까지 증가하지 않습니다.

- **Creating Counting Semaphores**

Counting semaphores are created either during initialization or during run-time by application threads. The initial count of the semaphore is specified during creation. There are no limits on the number of counting semaphores in an application.

> 카운팅 세마포어는 초기화 중에 또는 런타임 중에 애플리케이션 스레드에 의해 생성됩니다. 세마포어의 초기 개수는 생성 중에 지정됩니다. 응용 프로그램에서 세마포 수를 세는 데 제한이 없습니다.

- **Thread Suspension**

Application threads can suspend while attempting to perform a get operation on a semaphore with a current count of 0. 

Once a put operation is performed, the suspended thread’s get operation is performed and the thread is resumed. If multiple threads are suspended on the same counting semaphore, they are resumed in the same order they were suspended (FIFO). 

However, priority resumption is also possible if the application calls tx_semaphore_prioritize prior to the semaphore put call that lifts thread suspension. The semaphore prioritize service places the highest priority thread at the front of the suspension list, while leaving all other suspended threads in the same FIFO order.

> 현재 개수가 0 인 세마포어에서 가져 오기 작업을 수행하는 동안 응용 프로그램 스레드가 일시 중지 될 수 있습니다.
>
> 넣기 작업이 수행되면 일시 중단 된 스레드의 가져 오기 작업이 수행되고 스레드가 다시 시작됩니다. 여러 스레드가 동일한 계산 세마포어에서 일시 중단 된 경우 일시 중단 된 동일한 순서 (FIFO)로 다시 시작됩니다.
>
> 그러나 스레드 일시 중단을 해제하는 세마포 풋 호출 이전에 애플리케이션이 tx_semaphore_prioritize를 호출하는 경우에도 우선 순위 재개가 가능합니다. 세마포어 우선 순위 지정 서비스는 가장 높은 우선 순위 스레드를 일시 중단 목록 앞에 배치하고 다른 모든 일시 중단 된 스레드는 동일한 FIFO 순서로 둡니다.

- **Semaphore Control Block TX_SEMAPHORE**

The characteristics of each counting semaphore are found in its control block. It contains interesting information such as the current semaphore count. This structure is defined in the tx_api.h file. 

Semaphore control blocks can be located anywhere in memory, but it is most common to make the control block a global structure by defining it outside the scope of any function.

> 각 계수 세마포어의 특성은 제어 블록에서 찾을 수 있습니다. 현재 세마포어 수와 같은 흥미로운 정보가 포함되어 있습니다. 이 구조는 tx_api.h 파일에 정의되어 있습니다.
>
> 세마포어 제어 블록은 메모리의 어느 곳에 나 위치 할 수 있지만 제어 블록을 모든 기능의 범위 밖에서 정의하여 전역 구조로 만드는 것이 가장 일반적입니다.

- **Deadly Embrace** (치명적인 포옹?)

One of the most interesting and dangerous pitfalls associated with semaphores used for mutual exclusion is the deadly embrace. A deadly embrace, or deadlock, is a condition where two or more threads are suspended indefinitely while attempting to get semaphores already owned by other threads. 

This condition is best illustrated by a two thread, two semaphore example. Suppose the first thread owns the first semaphore and the second thread owns the second semaphore. If the first thread attempts to get the second semaphore and at the same time the second thread attempts to get the first semaphore, both threads enter a deadlock condition. In addition, if these threads stay suspended forever, their associated resources are locked-out forever as well. Figure 8 on page 71 illustrates this example.

> 상호 배제에 사용되는 세마포어와 관련된 가장 흥미롭고 위험한 함정 중 하나는 치명적인 포옹입니다. 치명적인 포괄 또는 교착 상태는 다른 스레드가 이미 소유 한 세마포어를 가져 오려고 시도하는 동안 두 개 이상의 스레드가 무기한 일시 중단되는 상태입니다.
>
> 이 조건은 두 개의 스레드, 두 개의 세마포어 예에서 가장 잘 설명됩니다. 첫 번째 스레드가 첫 번째 세마포를 소유하고 두 번째 스레드가 두 번째 세마포를 소유한다고 가정합니다. 첫 번째 스레드가 두 번째 세마포어를 얻으려고 시도하고 동시에 두 번째 스레드가 첫 번째 세마포어를 얻으려고하면 두 스레드가 모두 교착 상태에 들어갑니다. 또한 이러한 스레드가 영구적으로 일시 중단 된 상태로 유지되면 관련 리소스도 영구적으로 잠 깁니다. 71 페이지의 그림 8은이 예를 보여줍니다.

![107336816-eb7be400-6afc-11eb-96bf-fa8e871a0fd5](https://user-images.githubusercontent.com/58545240/113228876-d2a7d780-92d0-11eb-8946-28fceb2452f5.png)

How are deadly embraces avoided? Prevention in the application is the best method for real-time systems. This amounts to placing certain restrictions on how threads obtain semaphores. Deadly embraces are avoided if threads can only have one semaphore at a time. Alternatively, threads can own multiple semaphores if they all gather them in the same order. In the previous example, if the first and second thread obtain the first and second semaphore in order, the deadly embrace is prevented. 

It is also possible to use the suspension time-out associated with the get operation to recover from a deadly embrace.

> 치명적인 포옹은 어떻게 피합니까?
>
> 응용 프로그램의 예방은 실시간 시스템을위한 최선의 방법입니다. 이것은 스레드가 세마포어를 얻는 방법에 특정 제한을 두는 것과 같습니다.
>
> 스레드가 한 번에 하나의 세마포어 만 가질 수있는 경우 치명적인 포용이 방지됩니다.
>
> 또는 스레드가 모두 동일한 순서로 수집하는 경우 여러 세마포를 소유 할 수 있습니다. 앞의 예에서 첫 번째와 두 번째 스레드가 첫 번째와 두 번째 세마포어를 순서대로 획득하면 치명적인 포옹이 방지됩니다.
>
> 치명적인 포옹에서 회복하기 위해 get 작업과 관련된 중단 시간 제한을 사용할 수도 있습니다.

- **Priority Inversion**

Another pitfall associated with mutual exclusion semaphores is priority inversion. This topic is discussed more fully in “Thread Priority Pitfalls” on page 62. 

The basic problem results from a situation where a lower-priority thread has a semaphore that a higherpriority thread needs. This in itself is normal. However, threads with priorities in between them may cause the priority inversion to last a nondeterministic amount of time. This can be handled through careful selection of thread priorities, using preemption- thresholds, and temporarily raising the priority of the thread that owns the resource to that of the high-priority thread.

> 상호 배제 세마포어와 관련된 또 다른 함정은 우선 순위 반전입니다. 이 주제는 62 페이지의 "스레드 우선 순위 함정"에서 자세히 설명합니다.
>
> 기본적인 문제는 우선 순위가 낮은 스레드가 높은 우선 순위 스레드가 필요로하는 세마포어를 갖는 상황에서 발생합니다. 이것은 그 자체로 정상입니다. 그러나 그 사이에 우선 순위가있는 스레드는 우선 순위 반전이 비 결정적 시간 동안 지속되도록 할 수 있습니다. 이것은 스레드 우선 순위를 신중하게 선택하고, 선점 임계 값을 사용하고, 리소스를 소유 한 스레드의 우선 순위를 높은 우선 순위 스레드의 우선 순위로 일시적으로 올려서 처리 할 수 있습니다.

[위로](#threadx-rtos)

## Mutexes

[위로](#threadx-rtos)

In addition to semaphores, ThreadX also provides a mutex object. A mutex is basically a binary semaphore, which means that only one thread can own a mutex at a time. In addition, the same thread may perform a successful mutex get operation on an owned mutex multiple times, 4,294,967,295 to be exact. There are two operations on the mutex object, namely tx_mutex_get and tx_mutex_put. The get operation obtains a mutex not owned by another thread, while the put operation releases a previously obtained mutex. In order for a thread to release a mutex, the number of put operations must equal the number of prior get operations. 

Each mutex is a public resource. ThreadX places no constraints on how mutexes are used. 

ThreadX mutexes are used solely for mutual exclusion. Unlike counting semaphores, mutexes have no use as a method for event notification.

> 세마포어 외에도 ThreadX는 뮤텍스 객체도 제공합니다. 뮤텍스는 기본적으로 이진 세마포어입니다. 즉, 한 번에 하나의 스레드 만 뮤텍스를 소유 할 수 있습니다. 또한 동일한 스레드가 소유 된 뮤텍스에서 성공적인 뮤텍스 가져 오기 작업을 여러 번 수행 할 수 있습니다 (정확히 4,294,967,295). 뮤텍스 객체에는 tx_mutex_get과 tx_mutex_put의 두 가지 작업이 있습니다. get 작업은 다른 스레드가 소유하지 않은 뮤텍스를 가져 오는 반면, 넣기 작업은 이전에 얻은 뮤텍스를 해제합니다. 스레드가 뮤텍스를 해제하려면 넣기 작업의 수가 이전 가져 오기 작업의 수와 같아야합니다.
>
> 각 뮤텍스는 공용 리소스입니다. ThreadX는 뮤텍스가 사용되는 방법에 제한을 두지 않습니다.
>
> ThreadX 뮤텍스는 상호 배제에만 사용됩니다. 세마포를 세는 것과 달리 뮤텍스는 이벤트 알림을위한 방법으로 사용되지 않습니다.

- **Mutex Mutual Exclusion**

Similar to the discussion in the counting semaphore section, mutual exclusion pertains to controlling the access of threads to certain application areas (also called critical sections or application resources). When available, a ThreadX mutex will have an ownership count of 0. Once the mutex is obtained by a thread, the ownership count is incremented once for every get operation performed on the mutex and decremented for every put operation.

> 세마포어 계산 섹션의 논의와 유사하게 상호 배제는 특정 애플리케이션 영역 (중요 섹션 또는 애플리케이션 리소스라고도 함)에 대한 스레드 액세스를 제어하는 것과 관련이 있습니다. 사용 가능한 경우 ThreadX 뮤텍스의 소유권 개수는 0입니다. 스레드가 뮤텍스를 획득하면 소유권 개수는 뮤텍스에서 수행되는 모든 가져 오기 작업에 대해 한 번씩 증가하고 모든 넣기 작업에 대해 감소합니다.

- **Creating Mutexes**

ThreadX mutexes are created either during initialization or during run-time by application threads. The initial condition of a mutex is always “available.” Mutex creation is also where the determination is made as to whether or not the mutex implements priority inheritance.

> ThreadX 뮤텍스는 초기화 중에 또는 애플리케이션 스레드에 의해 런타임 중에 생성됩니다. 뮤텍스의 초기 조건은 항상 "사용 가능"입니다. 뮤텍스 생성은 뮤텍스가 우선 순위 상속을 구현하는지 여부를 결정하는 곳이기도합니다.

- **Thread Suspension**

Application threads can suspend while attempting to perform a get operation on a mutex already owned by another thread. 

Once the same number of put operations are performed by the owning thread, the suspended thread’s get operation is performed, giving it ownership of the mutex, and the thread is resumed. If multiple threads are suspended on the same mutex, they are resumed in the same order they were suspended (FIFO). 

However, priority resumption is done automatically if the mutex priority inheritance was selected during creation. In addition, priority resumption is also possible if the application calls tx_mutex_prioritize prior to the mutex put call that lifts thread suspension. The mutex prioritize service places the highest priority thread at the front of the suspension list, while leaving all other suspended threads in the same FIFO order.

> 다른 스레드가 이미 소유 한 뮤텍스에 대해 가져 오기 작업을 수행하는 동안 응용 프로그램 스레드가 일시 중지 될 수 있습니다.
>
> 소유 스레드가 동일한 수의 넣기 작업을 수행하면 일시 중단 된 스레드의 가져 오기 작업이 수행되어 뮤텍스의 소유권을 부여하고 스레드가 재개됩니다. 여러 스레드가 동일한 뮤텍스에서 일시 중단 된 경우 일시 중단 된 동일한 순서 (FIFO)로 다시 시작됩니다.
>
> 그러나 생성 중에 뮤텍스 우선 순위 상속을 선택한 경우 우선 순위 재개가 자동으로 수행됩니다. 또한 스레드 중단을 해제하는 뮤텍스 풋 호출 이전에 애플리케이션이 tx_mutex_prioritize를 호출하는 경우에도 우선 순위 재개가 가능합니다. 뮤텍스 우선 순위 지정 서비스는 가장 높은 우선 순위 스레드를 일시 중단 목록의 맨 앞에 배치하고 다른 모든 일시 중단 스레드는 동일한 FIFO 순서로 둡니다.

- **Mutex Control Block TX_MUTEX**

The characteristics of each mutex are found in its control block. It contains interesting information such as the current mutex ownership count along with the pointer of the thread that owns the mutex. 

This structure is defined in the tx_api.h file. Mutex control blocks can be located anywhere in memory, but it is most common to make the control block a global structure by defining it outside the scope of any function.

> 각 뮤텍스의 특성은 제어 블록에서 찾을 수 있습니다. 여기에는 뮤텍스를 소유 한 스레드의 포인터와 함께 현재 뮤텍스 소유권 수와 같은 흥미로운 정보가 포함되어 있습니다.
>
> 이 구조는 tx_api.h 파일에 정의되어 있습니다. Mutex 제어 블록은 메모리의 어느 곳에 나 위치 할 수 있지만 제어 블록을 모든 기능의 범위 밖에서 정의하여 글로벌 구조로 만드는 것이 가장 일반적입니다.

- **Deadly Embrace**

One of the most interesting and dangerous pitfalls associated with mutex ownership is the deadly embrace. A deadly embrace, or deadlock, is a condition where two or more threads are suspended indefinitely while attempting to get a mutex already owned by the other threads. The discussion of deadly embrace and its remedies found in the previous semaphore discussion is completely valid for the mutex object as well.

> 뮤텍스 소유권과 관련된 가장 흥미롭고 위험한 함정 중 하나는 치명적인 포용입니다. 치명적인 포괄 또는 교착 상태는 다른 스레드가 이미 소유 한 뮤텍스를 가져 오려고 시도하는 동안 두 개 이상의 스레드가 무기한 일시 중단되는 조건입니다. 이전 세마포어 논의에서 발견 된 치명적인 포용과 그 해결책에 대한 논의는 뮤텍스 객체에도 완전히 유효합니다.

- **Priority Inversion**

As mentioned previously, a major pitfall associated with mutual exclusion is priority inversion. This topic is discussed more fully in “Thread Priority Pitfalls” on page 62. 

The basic problem results from a situation where a lower-priority thread has a semaphore that a higherpriority thread needs. This in itself is normal. However, threads with priorities in between them may cause the priority inversion to last a nondeterministic amount of time. Unlike semaphores discussed previously, the ThreadX mutex object has optional priority inheritance. The basic idea behind priority inheritance is that a lower priority thread has its priority raised temporarily to the priority of a high priority thread that wants the same mutex owned by the lower priority thread. 

When the lower priority thread releases the mutex, its original priority is then restored and the higher priority thread is given ownership of the mutex. This feature eliminates undeterministic priority inversion by bounding the amount of inversion to the time the lower priority thread holds the mutex. Of course, the techniques discussed earlier in this chapter to handle undeterministic priority inversion are also valid with mutexes as well.

> 앞서 언급했듯이 상호 배제와 관련된 주요 함정은 우선 순위 반전입니다. 이 주제는 62 페이지의 "스레드 우선 순위 함정"에서 자세히 설명합니다.
>
> 기본적인 문제는 우선 순위가 낮은 스레드가 높은 우선 순위 스레드가 필요로하는 세마포어를 갖는 상황에서 발생합니다. 이것은 그 자체로 정상입니다. 그러나 그 사이에 우선 순위가있는 스레드는 우선 순위 반전이 비 결정적 시간 동안 지속되도록 할 수 있습니다. 이전에 논의 된 세마포와 달리 ThreadX 뮤텍스 개체에는 선택적 우선 순위 상속이 있습니다.
>
> 우선 순위 상속의 기본 아이디어는 우선 순위가 낮은 스레드가 우선 순위가 낮은 스레드가 소유 한 동일한 뮤텍스를 원하는 높은 우선 순위 스레드의 우선 순위로 일시적으로 우선 순위가 높아진다는 것입니다.
>
> 우선 순위가 낮은 스레드가 뮤텍스를 해제하면 원래 우선 순위가 복원되고 우선 순위가 높은 스레드에 뮤텍스의 소유권이 부여됩니다. 이 기능은 우선 순위가 낮은 스레드가 뮤텍스를 보유하는 시간으로 반전 양을 제한하여 비 결정적 우선 순위 반전을 제거합니다. 물론,이 장의 앞부분에서 비 결정적 우선 순위 반전을 처리하기 위해 논의한 기술은 뮤텍스에서도 유효합니다.

[위로](#threadx-rtos)

## Event Flags

[위로](#threadx-rtos)

Event flags provide a powerful tool for thread synchronization. Each event flag is represented by a single bit. Event flags are arranged in groups of 32. 

Threads can operate on all 32 event flags in a group at the same time. Events are set by tx_event_flags_set and are retrieved by tx_event_flags_get. 

Setting event flags is done with a logical AND/OR operation between the current event flags and the new event flags. The type of logical operation (either an AND or OR) is specified in the tx_event_flags_set call. 

There are similar logical options for retrieval of event flags. A get request can specify that all specified event flags are required (a logical AND). Alternatively, a get request can specify that any of the specified event flags will satisfy the request (a logical OR). The type of logical operation associated with event flag retrieval is specified in the tx_event_flags_get call. 

Event flags that satisfy a get request are consumed, i.e. set to zero, if `TX_OR_CLEAR` or `TX_AND_CLEAR` are specified by the request.

Each event flag group is a public resource. ThreadX places no constraints on how event flag groups are used.

> 이벤트 플래그는 스레드 동기화를위한 강력한 도구를 제공합니다. 각 이벤트 플래그는 단일 비트로 표시됩니다. 이벤트 플래그는 32 개 그룹으로 정렬됩니다.
>
> 스레드는 한 그룹의 모든 32 개 이벤트 플래그에서 동시에 작동 할 수 있습니다. 이벤트는 tx_event_flags_set에 의해 설정되고 tx_event_flags_get에 의해 검색됩니다.
>
> 이벤트 플래그 설정은 현재 이벤트 플래그와 새 이벤트 플래그 사이의 논리적 AND / OR 연산으로 수행됩니다. 논리 연산 유형 (AND 또는 OR)은 tx_event_flags_set 호출에 지정됩니다.
>
> 이벤트 플래그 검색을위한 유사한 논리적 옵션이 있습니다. get 요청은 지정된 모든 이벤트 플래그가 필요함을 지정할 수 있습니다 (논리적 AND). 또는 get 요청은 지정된 이벤트 플래그 중 하나가 요청을 충족하도록 지정할 수 있습니다 (논리적 OR). 이벤트 플래그 검색과 관련된 논리적 연산의 유형은 tx_event_flags_get 호출에 지정됩니다.
>
> get 요청을 충족하는 이벤트 플래그가 사용됩니다. 즉, TX_OR_CLEAR 또는 TX_AND_CLEAR가 요청에 의해 지정된 경우 0으로 설정됩니다.
>
> 각 이벤트 플래그 그룹은 공용 자원입니다. ThreadX는 이벤트 플래그 그룹이 사용되는 방법에 제한을 두지 않습니다.

- **Creating Event Flag Groups**

Event flag groups are created either during initialization or during run-time by application threads. At time of their creation, all event flags in the group are set to zero. There are no limits on the number of event flag groups in an application.

> 이벤트 플래그 그룹은 초기화 중에 또는 런타임 중에 애플리케이션 스레드에 의해 작성됩니다. 생성시 그룹의 모든 이벤트 플래그는 0으로 설정됩니다. 애플리케이션의 이벤트 플래그 그룹 수에는 제한이 없습니다.

- **Thread Suspension**

Application threads can suspend while attempting to get any logical combination of event flags from a group. Once an event flag is set, the get requests of all suspended threads are reviewed. All the threads that now have the required event flags are resumed. 

It is important to emphasize that all suspended threads on an event flag group are reviewed when its event flags are set. This, of course, introduces additional overhead. Therefore, it is generally good practice to limit the number of threads using the same event flag group to a reasonable number

> 그룹에서 이벤트 플래그의 논리적 조합을 가져 오는 동안 애플리케이션 스레드가 일시 중단 될 수 있습니다. 이벤트 플래그가 설정되면 일시 중단 된 모든 스레드의 가져 오기 요청이 검토됩니다. 이제 필수 이벤트 플래그가있는 모든 스레드가 재개됩니다.
>
> 이벤트 플래그가 설정 될 때 이벤트 플래그 그룹에서 일시 중단 된 모든 스레드가 검토된다는 점을 강조하는 것이 중요합니다. 물론 이것은 추가적인 오버 헤드를 야기합니다. 따라서 일반적으로 동일한 이벤트 플래그 그룹을 사용하는 스레드 수를 적절한 수로 제한하는 것이 좋습니다.

- **Event Flag Group Control Block**

The characteristics of each event flag group are found in its control block. It contains information such as the current event flag settings and the number of threads suspended for events. This structure is defined in the `tx_api.h` file. 

Event group control blocks can be located anywhere in memory, but it is most common to make the control block a global structure by defining it outside the scope of any function.

> 각 이벤트 플래그 그룹의 특성은 제어 블록에서 찾을 수 있습니다. 여기에는 현재 이벤트 플래그 설정 및 이벤트에 대해 일시 중단 된 스레드 수와 같은 정보가 포함됩니다. 이 구조는 tx_api.h 파일에 정의되어 있습니다.
>
> 이벤트 그룹 제어 블록은 메모리의 어느 위치 에나 위치 할 수 있지만 모든 기능의 범위 밖에서 정의하여 제어 블록을 글로벌 구조로 만드는 것이 가장 일반적입니다.

[위로](#threadx-rtos)

## Memory Block Pools

[위로](#threadx-rtos)

Allocating memory in a fast and deterministic manner is always a challenge in real-time applications. With this in mind, ThreadX provides the ability to create and manage multiple pools of fixed-size memory blocks. 

Since memory block pools consist of fixed-size blocks, there are never any fragmentation problems. Of course, fragmentation causes behavior that is inherently un-deterministic. In addition, the time required to allocate and free a fixed-size memory is comparable to that of simple linked-list manipulation. Furthermore, memory block allocation and deallocation is done at the head of the available list. This provides the fastest possible linked list processing and might help keep the actual memory block in cache. 

Lack of flexibility is the main drawback of fixed-size memory pools. The block size of a pool must be large enough to handle the worst case memory requirements of its users. Of course, memory may be wasted if many different size memory requests are made to the same pool. A possible solution is to make several different memory block pools that contain different sized memory blocks. 

Each memory block pool is a public resource. ThreadX places no constraints on how pools are used.

> 빠르고 결정적인 방식으로 메모리를 할당하는 것은 실시간 애플리케이션에서 항상 어려운 일입니다. 이를 염두에두고 ThreadX는 고정 크기 메모리 블록의 여러 풀을 만들고 관리하는 기능을 제공합니다.
>
> 메모리 블록 풀은 고정 크기 블록으로 구성되므로 조각화 문제가 전혀 없습니다. 물론 조각화는 본질적으로 결정적이지 않은 동작을 유발합니다. 또한 고정 크기 메모리를 할당하고 해제하는 데 필요한 시간은 단순한 연결 목록 조작의 시간과 비슷합니다. 또한 메모리 블록 할당 및 할당 해제는 사용 가능한 목록의 선두에서 수행됩니다. 이것은 가능한 가장 빠른 연결 목록 처리를 제공하고 실제 메모리 블록을 캐시에 유지하는 데 도움이 될 수 있습니다.
>
> 유연성 부족은 고정 크기 메모리 풀의 주요 단점입니다. 풀의 블록 크기는 사용자의 최악의 메모리 요구 사항을 처리 할 수있을만큼 충분히 커야합니다. 물론 동일한 풀에 대해 서로 다른 크기의 메모리 요청이 많이 발생하면 메모리가 낭비 될 수 있습니다. 가능한 해결책은 다른 크기의 메모리 블록을 포함하는 여러 다른 메모리 블록 풀을 만드는 것입니다.
>
> 각 메모리 블록 풀은 공용 리소스입니다. ThreadX는 풀이 사용되는 방식에 제한을 두지 않습니다.

- **Creating Memory Block Pools**

Memory block pools are created either during initialization or during run-time by application threads. There are no limits on the number of memory block pools in an application.

> 메모리 블록 풀은 초기화 중에 또는 런타임 중에 애플리케이션 스레드에 의해 생성됩니다. 애플리케이션의 메모리 블록 풀 수에는 제한이 없습니다.

- **Memory Block Size**

As mentioned earlier, memory block pools contain a number of fixed-size blocks. The block size, in bytes, is specified during creation of the pool. 

ThreadX adds a small amount of overhead—the size of a C pointer—to each memory block in the pool. In addition, ThreadX might have to pad the block size in order to keep the beginning of each memory block on proper alignment.

> 앞서 언급했듯이 메모리 블록 풀에는 여러 고정 크기 블록이 포함됩니다. 블록 크기 (바이트)는 풀 생성 중에 지정됩니다.
>
> ThreadX는 풀의 각 메모리 블록에 약간의 오버 헤드 (C 포인터 크기)를 추가합니다. 또한 ThreadX는 각 메모리 블록의 시작 부분을 적절하게 정렬하기 위해 블록 크기를 채워야 할 수 있습니다.

- **Pool Capacity**

The number of memory blocks in a pool is a function of the block size and the total number of bytes in the memory area supplied during creation. The capacity of a pool is calculated by dividing the block size (including padding and the pointer overhead bytes) into the total number of bytes in the supplied memory area.

> 풀의 메모리 블록 수는 블록 크기와 생성 중에 제공된 메모리 영역의 총 바이트 수의 함수입니다. 풀의 용량은 블록 크기 (패딩 및 포인터 오버 헤드 바이트 포함)를 제공된 메모리 영역의 총 바이트 수로 나누어 계산합니다.

- **Pool's Memory Area**

As mentioned before, the memory area for the block pool is specified during creation. Like other memory areas in ThreadX, it can be located anywhere in the target’s address space. 

This is an important feature because of the considerable flexibility it gives the application. For example, suppose that a communication product has a high-speed memory area for I/O. This memory area is easily managed by making it into a ThreadX memory block pool.

> 앞에서 언급했듯이 블록 풀의 메모리 영역은 생성 중에 지정됩니다. ThreadX의 다른 메모리 영역과 마찬가지로 대상 주소 공간의 어느 곳에 나 위치 할 수 있습니다.
>
> 이것은 응용 프로그램에 상당한 유연성을 제공하기 때문에 중요한 기능입니다. 예를 들어, 통신 제품에 I / O를위한 고속 메모리 영역이 있다고 가정합니다. 이 메모리 영역은 ThreadX 메모리 블록 풀로 만들어 쉽게 관리 할 수 있습니다.

- **Thread Suspension**

Application threads can suspend while waiting for a memory block from an empty pool. When a block is returned to the pool, the suspended thread is given this block and resumed. 

If multiple threads are suspended on the same memory block pool, they are resumed in the order they were suspended (FIFO). 

However, priority resumption is also possible if the application calls tx_block_pool_prioritize prior to the block release call that lifts thread suspension. The block pool prioritize service places the highest priority thread at the front of the suspension list, while leaving all other suspended threads in the same FIFO order.

> 빈 풀에서 메모리 블록을 기다리는 동안 응용 프로그램 스레드가 일시 중지 될 수 있습니다. 블록이 풀로 반환되면 일시 중단 된 스레드에이 블록이 주어지고 다시 시작됩니다.
>
> 여러 스레드가 동일한 메모리 블록 풀에서 일시 중단 된 경우 일시 중단 된 순서 (FIFO)에 따라 다시 시작됩니다.
>
> 그러나 스레드 중단을 해제하는 블록 해제 호출 이전에 응용 프로그램이 tx_block_pool_prioritize를 호출하는 경우에도 우선 순위 재개가 가능합니다. 블록 풀 우선 순위 지정 서비스는 가장 높은 우선 순위 스레드를 일시 중단 목록의 맨 앞에 배치하고 다른 모든 일시 중단 된 스레드는 동일한 FIFO 순서로 둡니다.

- **Memory Block Pool Control Block `TX_BLOCK_POOL`**

The characteristics of each memory block pool are found in its control block. It contains information such as the number of memory blocks left and their size. This structure is defined in the tx_api.h file. 

Pool control blocks can also be located anywhere in memory, but it is most common to make the control block a global structure by defining it outside the scope of any function.

>각 메모리 블록 풀의 특성은 해당 제어 블록에서 찾을 수 있습니다. 여기에는 남은 메모리 블록 수 및 크기와 같은 정보가 포함됩니다. 이 구조는 tx_api.h 파일에 정의되어 있습니다.
>
>풀 제어 블록은 메모리의 어느 곳에 나 위치 할 수도 있지만 제어 블록을 모든 기능의 범위 밖에서 정의하여 글로벌 구조로 만드는 것이 가장 일반적입니다.

- **Overwriting Memory Blocks**

It is very important to ensure that the user of an allocated memory block does not write outside its boundaries. If this happens, corruption occurs in an adjacent (usually subsequent) memory area. The results are unpredictable and quite often fatal!

> 할당 된 메모리 블록의 사용자가 경계 외부에 쓰지 않도록하는 것이 매우 중요합니다. 이 경우 인접한 (일반적으로 후속) 메모리 영역에서 손상이 발생합니다. 결과는 예측할 수 없으며 매우 치명적입니다!

[위로](#threadx-rtos)

## **RTOS 환경에서 워치독 사용하기**

[위로](#threadx-rtos)

현업 골칫거리 중 하나인 WDT에 대해서 알아보려고 합니다.

먼저 그 중요성에 대해서 알아보겠습니다.

우주 환경에 장기간 노출 된 상태에서 센서와 우주선 구성 요소를 테스트하는 NASA 위성 인 [Clementine](https://en.wikipedia.org/wiki/Clementine_(spacecraft)) 은 1994 년 1 월 25 일에 발사되었습니다. 몇 줄의 감시 코드가 부족하여 1994 년 5 월 7 일에 임무를 잃었습니다.

클레멘 타인은 달 궤도를 떠나 그녀의 다음 목표 인 지구 근처의 소행성 Geographos로 향했을 때 약 2 개월 연속 달지도를 수행했습니다. 그러나 곧 Clementine의 온보드 컴퓨터 중 하나에서 오작동이 발생하여 NASA가 우주선 작동을 효과적으로 차단하고 추진기 중 하나가 제어되지 않은 상태로 발사되도록했습니다.

NASA는 시스템을 다시 작동 시키려고 20 분을 보냈지 만 소용이 없었습니다. 하드웨어 재설정 명령이 마침내 Clementine을 다시 온라인 상태로 만들었지 만 너무 늦었습니다. 그녀는 이미 연료를 모두 사용했고 임무를 취소해야했습니다.
그 후 Clementine의 소프트웨어를 담당하는 개발 팀은 그들이 구현 한 소프트웨어 타임 아웃이 불충분하다는 것이 분명해 졌을 때 하드웨어를 감시할 수 있는 WatchDog Timer를 사용하기를 원했습니다.

### 어떻게 WatchDog이 도움이 되었을까?

워치 독은 마이크로 컨트롤러에 직접 통합되거나 마이크로 컨트롤러에 외부 적으로 연결되는 하드웨어입니다. 주요 목적은 시스템이 중단되었거나 부적절하게 실행되고 있다고 안전하게 가정 할 수있을 때 오류 처리 (일반적으로 하드웨어 재설정)를 수행하는 것입니다.

워치 독의 주요 구성 요소는 초기에 특정 값으로 구성되고 이후에 0으로 카운트 다운되는 카운터입니다. 소프트웨어는 0에 도달하지 않도록이 카운터를 초기 값으로 자주 다시 설정해야합니다. 그렇지 않으면 오작동으로 간주되며 일반적으로 CPU가 재설정됩니다. 이것은 다른 모든 것이 실패했을 때만 취하는 옵션 인 최후의 수단에 대한 감시자를 제안합니다. 클레멘 타인의 경우도 마찬가지였습니다.

### WatchDog 사용방법

그러나 워치 독 타이머를 올바르게 사용하는 것은 카운터를 다시 시작하는 것만 큼 간단하지 않습니다 (이 프로세스는 워치 독을 "`feeding`"또는 "`kick`"한다고 함). 시스템에서 워치 독 타이머를 실행하는 경우 개발자는 오작동하는 시스템이 되돌릴 수없는 악의적인 작업을 수행하기 전에 워치 독이 개입 할 수 있도록 워치 독의 시간 초과 기간을 신중하게 선택해야합니다.

특히 RTOS를 사용하지 않는 간단한 애플리케이션에서 개발자는 일반적으로 메인 루프에서 워치 독을 공급합니다. 이 접근 방식은 적절한 초기 카운터 값을 구성하기만 하면 되며, 이는 전체 메인 루프의 최악의 경우 실행 시간을 적어도 하나의 타이머 주기로 초과하는 값을 선택하는 것만 큼 간단 할 수 있습니다. 이것은 종종 상당히 강력한 접근 방식입니다. 일부 시스템은 즉각적인 복구가 필요하지만 다른 시스템은 무기한 중단되지 않도록하기 만하면됩니다. 그러면 작업이 확실히 완료됩니다.

### 멀티 태스킹(RTOS) 환경

그러나보다 복잡한 시스템, 특히 멀티 태스킹 시스템에서는 다양한 스레드가 다양한 경우와 다양한 이유로 잠재적으로 중단 될 수 있습니다. 잠재적인 네트워크 통신을 기다리는 스레드와 같이 일부 스레드는 오랫동안 실행되지 않는 것이 좋습니다. 워치 독에 주기적으로 공급하는 깨끗한 방법은 각각의 개별 프로세스가 양호한 상태인지 확인하는 동시에 다음과 같은 것을 적절히 체크하는 것이 시스템 개발자에게 중요한 과제가되었습니다.

- OS가 제대로 실행되고 있는지 여부
- 우선 순위가 높은 작업이 CPU를 모두 소모하여 우선 순위가 낮은 작업이 전혀 실행되는 것을 방지하는지 여부
- 하나 또는 여러 작업의 실행을 방해하는 교착 상태가 발생했는지 여부
- 작업 루틴이 적절하고 전체적으로 실행되고 있는지 여부

또한 개발자는 전용 WatchDog 작업이든 모니터링되는 작업에 대한 특정 수정이든 소스 코드에 수행 된 모든 수정이 작게 이루어져야하며 침입을 최소화하고 효율성을 높이기 위해 소스 코드를 최적화 해야합니다.

### RTOS에서 WatchDog 사용하기

따라서 임베디드 전문가에게는 아래 두 가지를 모두 허용하는 포괄적 인 API 기능 세트가 필요하다는 것이 분명해집니다.

- 기본 embOS 감시 모듈을 사용하여 작업, 타이머 및 ISR을 개별적으로 등록하고
- 원하는 상황에서 의도한 감시 조건을 유연하게 테스트 할 수있는 가능성.

### 결론

실제 시스템에서는 **MICOM**에 의해서 reset되거나 **WDT**에 의해서 reset 되는 경우에 어디까지 진행되었고, 어떤 이유로 reset 될 수 밖에 없었는지 파악하기 어렵습니다.

따라서, 디버깅용 WDT를 설정하여 MICOM 등이 reset되기 전에 `ISR` 루틴을 호출하게 해 몇몇 정보를 출력하고 재부팅하게 해야 합니다.

필자가 겪은 많은 `System Reset`의 이유 중 대부분은 **`WDT KICK Handler`**와 동일한 `Handler`에서 많은 실시간 처리를 하는 경우, `WDT KICK Handler`의 처리가 밀리면서 `WDT KICK`을 처리하지 못해 카운트가 0이 되어 종료되는 것이었습니다. 혹은 반복적으로 돌아가는 while문에서 메모리 누수가 발생하여 시스템이 비정상 종료가 될 수도 있을겁니다. 따라서 이러한 것들과 관련된 로직을 면밀히 분석하고 고쳐나가다보면 **WDT에 의한 비정상 시스템 종료 문제**를 해결할 수 있을 것이다.

[위로](#threadx-rtos)

# 부속 개념

[위로](#threadx-rtos)

## Segment(세그먼트)

C언어로 작성된 프로그램은 주기억장치를 효율적으로 운영하리 위해서 일정한 크기, 대개는 64kb크기로 논리적 단위로 나누어서 할당과 할당 해제로 관리하게 된다. 그 논리적 단위를 세그먼트(Segment)라 하고 서로 관련이 있는 데이터와 명령어를 하나의 세그먼트로 관리하는 것이 아니라 데이터를 저장하는 데이터 세그먼트(Data Segment)영역와 명령어를 저장하는 코드 세그먼트 (Code Segment)영역로 구분해서 사용한다. 

또한 데이터 세그먼트영역은 기억 장소의 할당 방법에 따라 동적 할당 (Dynamic Allocation)에 의하여 관리되는 스택(Stack) 세그먼트, 힙(Heap) 세그먼트와 정적 할당(Static Allocation)에 의해서 관리되어지는 데이터(Data) 세그먼트로 구분된다. 

이렇게 구분되어지는 데이터 세그먼트 영역에 데이터를 저장하기 위해서 할당되어진 기억장소를 변수(Variable)라고 한다. 

![image-20210209175820398](https://user-images.githubusercontent.com/58545240/107339821-79a59980-6b00-11eb-8cba-c169524e875e.png)

### C프로그램의 메모리 모델

C 프로그램에 의해서 관리되어지는 세그먼트들은 총 5가지이다.

1. **코드(Code) 세그먼트** : *코드 정적  세그먼트*
   - 프로그램의 명령어가 저장되어지는 주기억장치 영역을 말한다.
   - 이 영역은 프로그램이 시작할 때 할당되었다가 프로그램이 끝날 때 할당이 해제되어지는 정적 할당 영역이다.
   - 대개는 함수 하나당 하나의 코드 세그먼트가 할당되어 진다.
2. **데이터(Data) 세그먼트** : *데이터 정적 세그먼트*
   - 프로그램이 실행될 때 할당되어서 프로그램이 끝날 때 할당 해제되어지는 데이터 세그먼트 영역이다.
   - 따라서 프로그램이 시작될 때부터 끝날 때까지 사용되어야 하는 데이터들을 관리하는 영역이고 1개의 세그먼트만으로 구성되어진다.
   - 코드 세그먼트 바로 다음에 할당되어지는 영역이다.
   - 대개는 문자열 리터럴(혹은 문자열 상수), 전역 변수 또는 정적(static) 변수가 저장되어지는 영역이다.
3. **스택(Stack) 세그먼트** : *데이터 동적 세그먼트*
   - 포그램이 실행하고 있는 동안에 일시적으로 보존하고자 하는 데이터를 순서적으로 쌓아서 저장해 두는 데이터 세그먼트 영역이다.
   - C언어에서의 논리적 모듈인 함수 하나가 실행될 때 할당되었다가 함수가 끝날 때 할당해제되는 데이터 세그먼트 영역이다.
   - 데이터를 관리하는 데 필용한 연산, 일반적으로 삽입, 삭제, 그리고 변경의 경신과 정렬과 검색의 질의를 수행할 때 필요한 데이터들을 관리하는 영역이다.
   - C프로그램에서 가장 빈번하게, 그리고 중요하게 관리되는 기억장소 영역은 스택 데이터 세그먼트이다.
4. **힙(Heap) 데이터 세그먼트** : *데이터 동적 세그먼트*
   - 프로그램의 실행 중에 원시 코드에 의해서 동적으로 할당되고 할당 해제되는 데이터 세그먼트 영역이다.
   - C프로그램을 사용해서 자신만의 데이터들을 관리하고자 하는 사용자들에 의해서 관리되어지는 사용자 데이터들을 관리하는 영역이다.
   - 철저하게 C프로그래머에 의해서 할당과 할당 해제가 결정되어지는 영역이다.
5. **중앙처리장치(CPU) 레지스터**
   - 중앙처리장치가 데이터를 처리하는 동안 사용할 값이나 연산의 중간 결과를 일시적으로 저장해 두기 위해 사용되는 중앙처리장치에 존재하는 고속 처리 기억장치이다.

### 메모리 할당 방법

주기억장치는 여러 프로그램(프로세스, 리소스)에 의해서 사용되어진다. 따라서 어떠한 프로그램(프로세스, 리소스)이 주기억장치를 사용하기 위해서는 다른 프로그램이 사용되는 주기억장치를 사용하면 안된다. *이러한 관리를 운영체제가 한다*

C프로그램에서는 운영체제에서 주기억장치에 사용에 대한 허가를 받아야 하고, 사용이 끝난 경우에는 바로 반환을 해야한다. 이 때 주기억장치에 사용 허가를 받는 작업을 `할당(Allocation)`이라 하고, 사용이 끝나 반환하는 작업을 `할당 해제(Deallocation)`이라고 한다.

C프로그램에서 사용되는 기억장소를 할당하는 방법은 기본적으로 정적과 동적으로 구분되어진다.

1. **정적할당(Static Allocation)**

   - 일반적으로 프로그램이 실행될 때 기억장소에 할당되고 프로그램이 끝날 때 할당 해제될 때까지 그대로 유지되어지는 기억장치 관리방식

2. **동적할당(Dynamic Allocation)**

   - 프로그램 실행 중에 필요할 떄 마다 기억장소를 할당하고, 필요하지 않으면 할당 해제를 시스템 또는 원시코드로 제어하는 기억장소 관리 방식이다. 

   - 동적할당은 기본적으로 시스템에 의해서 관리되는 스택에 의한 동적 할당과 프로그래머의 의해서 원시코드로 관리되어지는 힙을 이용한 동적 할당으로 구분되어 진다. 

   1) 스택에 의한 동적 할당

   ​	- 기억장소 운영의 효율성을 위해서 함수가 실행될 때마다 시스템에 의해서 필요한 기억장소가 할당되고 함수의 실행이 끝날 때 할당이 해제되는 방식

   2) 힙을 이용한 동적 할당

   ​	- 라이브러리 내장함수로 제공되는 할당(calloc, malloc)과 해제(realloc, free) 함수를 이용하여 프로그래머가 주소에 의한 접근으로 기억장소를 관리하는 방식


[위로](#threadx-rtos)