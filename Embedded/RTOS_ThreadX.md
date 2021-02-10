# THREADX (RTOS)

> 참조글 : [THREADX.PDF](http://www.ece.ualberta.ca/~cmpe490/documents/ghs/405/threadxug_g40c.pdf)
>
> 여기도 도움이 된다. https://ppua.tistory.com/entry/%EC%8B%A4%EC%8B%9C%EA%B0%84-%EC%9A%B4%EC%98%81%EC%B2%B4%EC%A0%9C-RTOS%EC%9D%98-%EC%9D%B4%ED%95%B4

## Thread Execution

Scheduling and executing application threads is the most important activity of ThreadX. What exactly is a thread? A thread is typically defined as semi-independent program segment with a dedicated purpose. The combined processing of all threads makes an application.

How are threads created? Threads are created dynamically by calling tx_thread_create during initialization or during thread execution. Threads are created in either a ready or suspended state.

> 애플리케이션 스레드를 예약하고 실행하는 것은 ThreadX의 가장 중요한 활동입니다. 스레드는 정확히 무엇입니까? 스레드는 일반적으로 전용 목적을 가진 반독립 프로그램 세그먼트로 정의됩니다. 모든 스레드의 결합 된 처리는 응용 프로그램을 만듭니다.
>
> 스레드는 어떻게 생성됩니까? 스레드는 초기화 또는 스레드 실행 중에 tx_thread_create를 호출하여 동적으로 생성됩니다. 스레드는 준비 또는 일시 중지 상태로 생성됩니다.

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

## Message Queues

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

## Counting Semaphores

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

![image-20210209173157686](C:\Users\dhkdghehfdl\Desktop\ksh\Embedded\images\107336816-eb7be400-6afc-11eb-96bf-fa8e871a0fd5.png)

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

## Mutexes

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

# 부속 개념

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

   

   