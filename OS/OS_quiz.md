# :deer: 운영체제 공룡책 강의 퀴즈 모음

---

## Chapter 1- 2 Introduction & O/S Structures

### Quiz

1.  내 친구가 4지선다형 시험 문제의 답 하나를 알려주었다. 이 친구가 나에게 준 정보량은 얼마인가?
    1.   1
    2.  **2**
    3.   3
    4.   4
2.  범용 컴퓨터(general-purpose computer)는 소프트웨어를 통해 여러 목적으로 사용할 수 있는 컴퓨 터를 의미한다. 범용 컴퓨터를 구현하려고 논리 회로를 만들려고 할 때 필요한 최소한의 논리 게이트 집합을 모두 고르시오. 
    1.  **NOT, AND, OR**
    2.  NOT, AND, OR, NAND
    3.  NOT, AND, OR, XOR
    4.  NOT, AND, OR, XOR, NAND, NOR
    5.  **NAND**
3.  Turing Machine에 대해서 틀린 설명은?
    1.  Alan Turing이 제안한 현대적 컴퓨터의 원형이다. 
    2.  Universal Turing Machine은 현대적 컴퓨터에서 운영체제가 되었다. 
    3.  **Turing Machine은 정지 문제를 포함하여 계산가능한 모든 문제를 풀 수 있다고 증명되었다.**
    4.  Turing Machine의 설계는 CPU와 Memory와 같은 하드웨어의 설계를 포함하고 있었다. 
4.  A _______ is defined as a set of insructions that directs a computer to perform a cetain job to do.
    1.  process
    2.  software
    3.  von Neumann Architecture
    4.  **program**
5.  A _____ is the core of operating system that is running at all times on the computer.
    1.  **kernel**
    2.  sthell
    3.  bootstrap
    4.  system call
6.  A ________ program is the first program to run on computer power-on, and then loads the operating system into the main memory.
    1.  kernel
    2.  shell
    3.  **bootstrap**
    4.  system call
7.  ________ is a technology that allow us to abstract the hardware of a single computer into several different execution environments to enable for several OSes to run concurrently.
    1.  **Virtualization**
    2.  Multiprocessing
    3.  Multitasking
    4.  Multiprogramming
8.  A _________ provides an interface to the services made available by the operating system.
    1.  **system call**
    2.  direct memory access
    3.  shell
    4.  process

## Chapter 3. Proccess

### Quiz

1.  In the memory layout of a process, the ______ section is an area of memory that is dynamically allocated during program run time.

    1.  **heap**
    2.  stack
    3.  data
    4.  code

2.  운영체제에서 프로세스의 상태에 대한 설명으로 가장 틀린 것은?

    1.  fork() 시스템 콜로 새로운 프로세스를 생성하면 항상 NEW 상태가 된다.
    2.  **READY 상태에 있는 프로세스에 interrupt를 걸면 WAITING 상태로 천이해서 응답이 올 때까지 대기한다. **
    3.  RUNNING 상태의 프로세스가 I/O 처리를 하면 event가 응답할 때까지 WAITING 상태로 천이한 다. 
    4.  RUNNING 상태의 프로세스가 time out이 되면 CPU 스케줄러는 READY 상태의 프로세스 하나 를 dispatch한다. 

3.  다음 중 PCB(Process Control Block)에 저장해야 할 정보가 아닌 것은?

    1.  Program Counter
    2.  Instruction Register
    3.  Process State
    4.  Stack Pointer
    5.  **BSS**

4.  Concurrency(동시성, 병행성)에 대한 설명으로 가장 틀린 것은?

    1.  multiprogramming은 동시에 여러 개의 프로그램을 메모리에 상주시키는 것을 말한다. 
    2.  **multitasking은 여러 개의 CPU Core에서 한순간에 여러 명령어를 여러 Core에서 동시에 실행하 는 것을 말한다. **
    3.  시분할(time-sharing)은 여러 개의 프로세스가 하나의 CPU를 시간상으로 분할하여 사용하는 것 을 말한다. 
    4.  multiprocessing은 CPU 자원을 효율적으로 사용하고, 사용자에 대한 응답 시간을 줄이는 목적으 로 사용한다. 

5.  Context Switch(문맥 교환)에 대한 설명으로 가장 틀린 것은?

    1.  CPU 스케줄러가 프로세스에게 CPU를 배정해 주기 위해 문맥을 교환한다. 
    2.  Context 정보는 Process Control Block에 저장하거나, PCB로부터 로드한다. 
    3.  Context Switch는 interrupt 또는 system call에 의해 실행된다.
    4.  **Context Switch의 대상이 되는 두 프로세스는 각각 RUNNING 상태, WAITING 상태에 있다. **

6.  다음 프로그램에서 LINE X 라고 표기된 위치의 실행 순서로 올바른 것은?

    ```c
    int main() {
        pid_t pid = fork();
        if (pid > 0) {
            wait(NULL);
            // LINE A
        } else {
            pid = fork();
            if (pid == 0) {
                // LINE B
            } else {
                wait(NULL);
                // LINE C
            }
        }
        // LINE D
    }
    ```

    1.  B - C - A - D - D - D
    2.  B - C - D - A - D - D
    3.  B - A - D - C - D - D
    4.  **B - D - C - D - A - D**

7.  다음 프로그램의 출력 결과로 올바른 것은?

    ```c
    int x = 10;
    int main() {
        pid_t pid = fork();
        if (pid == 0) {
            x += 10;
        } else {
            wait(NULL);
            pid = fork();
            x += 10;
            if (pid > 0) {
                wait(NULL);
            } else {
                x += 10;
            }
        }
        printf("%d ", x);
    }
    ```

    1.  20 20 30
    2.  **20 30 20**
    3.  20 30 30
    4.  20 20 20

### Quiz

1.  Two fundamental models of inter-process communication are:
    1.  **shared-memory and message-passing**
    2.  pipes and sockets
    3.  sockets and remote procedure call
    4.  ordinary pipes and named pipes
2.  생산자-소비자 문제를 shared memory로 해결하는 방법에 대한 설명으로 가장 옳은 것은?
    1.  운영체제가 알아서 shared memory의 생성과 소멸을 처리해 주므로, 구현하기가 편하다.
    2.  POSIX 표준에서는 shared memory를 지원하지 않는다.
    3.  shared memory는 memory-mapped file로만 만들 수 있다.
    4.  **생산자는 공유 버퍼에 메시지를 write()하고, 소비자는 공유 버퍼로부터 read()한다.**
3.  Message-Passing 방식의 IPC에 대한 설명으로 가장 옳은 것은?
    1.  message-passing 방식은 두 개의 프로세스간 통신에서만 사용할 수 있다.
    2.  message를 생산자가 소비자에게 직접 전달하는 direct 통신 방식이다.
    3.  메시지의 전송이 완료될 때까지 block되는 send를 사용하면 asynchronous 통신을 할 수 있다.
    4.  **mailbox(또는 port)를 사용한 message-passing은 indirect 통신을 가능하게 한다.**
4.  UNIX의 pipe에 대한 설명으로 가장 틀린 것은?
    1.  ordinary pipe는 생산자-소비자 방식으로 두 개의 프로세스가 서로 통신하는 메커니즘이다.
    2.  ordinary pipe는 한쪽 끝단에서 write를 하고, 다른쪽 끝단에서 read를 하므로 단방향 통신만 할 수 있다. 
    3.  ordinary pipe로 양방향 통신을 하기 위해서는 두 개의 파이프를 사용하면 된다.
    4.  **ordinary pipe를 사용하는 두 개의 프로세스가 반드시 부모-자식 관계일 필요는 없다.**
5.  소켓에 대한 설명으로 가장 옳은 것은?
    1.  원격에 있는 프로시저를 호출하기 위한 용도로 활용된다. 
    2.  클라이언트 영역의 stub과 서버 영역의 skeleton으로 구현이 나뉘어 진다. 
    3.  **IP 주소와 port 번호를 결합하여 하나의 소켓을 특정(identify)할 수 있다.**
    4.  일반적으로 소켓은 connection-oriented (TCP) 용으로만 사용할 수 있다. 

## Chapter 4. Thread & Concurrency

### Quiz

1.  `From execise 4.2`: Using Amdahl's Law, calculate the speedup gain of an application that has a 60 percent  parallel component for (a) two processing cores and (b) four processing cores. 위 연습문제의 정답으로 가장 옳은 것은?

    1.  **(a) 1.43 (b) 1.8**
    2.  (a) 1.81 (b) 1.43
    3.  (a) 2.56 (b) 2.13
    4.  (a) 2.13 (b) 2.56

2.  user-thread와 kernel-thread에 대한 설명으로 가장 틀린 것은?

    1.  user thread는 사용자 모드에서 동작하고, kernel thread는 커널 모드에서 동작한다.
    2.  **Many-to-one 쓰레드 모델에서는 다수의 kernel thread가 1개의 user thread를 지원한다.**
    3.  user thread와 kernel thread는 반드시 생성한 process에 결합되어 있어야만 한다.
    4.  One-to-one 쓰레드 모델은 concurrency의 측면에서 Many-to-one 모델보다 우수하다.

3.  `From Exercise 4.10`: Which of the following components of program state are shared across threads in a  multithreaded process? 위 연습문제의 정답에 해당하는 것을 모두 고르시오.

    1.  register values
    2.  **heap memory**
    3.  **global variables**
    4.  stack memory

4.  멀티쓰레드 프로그래밍 모델의 장점에 대한 설명으로 가장 틀린 것은?

    1.  반응성이 좋다: 프로세스가 유저 인터페이스를 처리하느라 blocked 되어 있을 때도 실행을 계속할  수 있다.
    2.  **자원 공유에 유리하다: 다른 프로세스와 shared memory를 사용할 수 있으므로 자원 공유에 유리하다.**
    3.  경제성이 좋다: 프로세스간 context switch보다 쓰레드간 context switch가 훨씬 가볍다.
    4.  확장성이 좋다: CPU가 여러 개이거나 core가 여러 개인 경우에 이를 잘 활용할 수 있다.

5.  Java에서의 멀티쓰레드 프로그래밍에 대한 설명으로 가장 틀린 것은?

    1.  Thread 클래스를 상속하여 public void run() 메소드를 오버라이딩한다.
    2.  Runnable 인터페이스를 상속하여 public void run()을 오버라이딩한다.
    3.  **Thread 클래스의 인스턴스를 생성하여 해당 인스턴스의 run() 메소드를 호출한다.**
    4.  Thread 클래스 생성자의 매개변수로 Runnable 인터페이스를 상속한 객체 인스턴스를 줄 수 있다.

6.  OpenMP에 대한 설명으로 가장 틀린 것은?

    1.  컴파일러 지시어(directive)를 이용하여 병렬 처리를 할 수 있다. 
    2.  병렬 처리 가능한 코드 영역을 #pragma omp parallel 로 지정할 수 있다.
    3.  omp_set_num_threads() 함수로 병렬 처리할 쓰레드 갯수를 설정할 수 있다.
    4.  **OpenMP는 쓰레드를 미리 생성하여 pool에 저장해 놓기 때문에 thread 생성 시간을 절약한다.**

7.  다음 Pthread 예제의 출력으로 가장 옳은 것은?

    ```c
    int x = 10;
    
    void *runner(void *param) {
        x += 10;
        pthread_exit(0);
    }
    
    int main() {
        int i;
        pid_t pid1, pid2;
        pthread_t tid;
        pid1 = fork();
        if (pid1 == 0) {
            pthread_create(&tid, NULL, runner, NULL);
            pthread_join(tid, NULL);
            pid2 = fork();
            if (pid2 > 0) {
                wait(NULL);
                x += 10;
            }
            printf("%d ", x);
        }
        else {
            wait(NULL);
            printf("%d ", x);
        }
    }
    ```

    1.  10 20 30
    2.  **20 30 10**
    3.  30 20 10
    4.  10 30 20

    ```bash
    # 해설
    main에서 처음 fork()를 시작하고 child(pid==0)이 실행된다.
    create하고 join이 되기때문에 runner가 다 돌기 까지에 기다린다.
    다 끝나면 20이 되고 pid2 = fork()가 실행된다. pid2 > 0은 childprocess 0 이므로 조건문을 타지 않고 바로 내려간다.
    그렇게 20을 프린트 하면 if(pid2 > 0) {x+=10} 이 되고 30이 리턴되고 마지막에 기다리던 10이 프린트된다.
    ```

8.  다음 Java 프로그램 예제의 출력으로 가장 옳은 것은?

    ```java
    class Runner implements Runnable {
        public void runt() {
            for (int i = 0; i < 3; i++) {
                System.out.pirntf("A ");
            }
        }
    }
    public class ThreadQuiz {
        public static final void main(String[] args) throws Exception {
            Thread thread = new Thread(new Runner());
            System.out.printf("B ");
            thread.start();
            System.out.printf("C ");
            thread.join();
            System.out.printf("D ");
        }
    }
    ```

    1.  **B C A A A D**
    2.  B C D A A A
    3.  B A A A C D
    4.  B C A D A A

## Chapter 5. CPU Scheduling

### Quiz

1.  The ________ is a module that gives control of the CPU's core to the process selected by the CPU scheduler.

    1.  **dispatcher**
    2.  distributor
    3.  deployer
    4.  dissipator

2.  다음 중 CPU 스케줄러를 설계할 때 목표로 삼기에 가장 어색한 것은?

    1.  CPU의 사용효율(utilization)을 높이겠다
    2.  단위시간당 처리하는 프로세스의 개수(throughput)을 늘리겠다.
    3.  프로세스가 대기하는 시간(waiting time)을 줄이겠다
    4.  **프로세스의 응답시간(response time)을 늘리겠다.**

3.  선점형(preemptive), 비선점형(nonpreemptive) 스케줄러에 대한 설명으로 가장 옳은 것은?

    1.  Shortest-Job-First(SJF)는 짧은 CPU burst를 가진 프로세스를 먼저 처리하는 preemptive 스케줄러다. 
    2.  First-Come, First-Served(FCFS)는 먼저 도착한 프로세스를 먼저 처리하는 preemptive 스케 줄러다. 
    3.  CPU를 점유한 프로세스가 waiting 상태에서 ready 상태로 갈 때는 반드시 non-preemptive 스케줄링을 해야 한다. 
    4.  **Round-Robin 스케줄러는 time quantime이 지나면 CPU를 점유한 프로세스를 내보내는 preemptive 스케줄러다.**

4.  CPU scheuler에 대한 설명으로 가장 틀린 것은?

    1.  FCFS를 구현하기 위해서는 FIFO Queue를 ready-queue의 자료구조로 사용할 수 있다.
    2.  Shortest-Remaining-Time-First 스케줄러는 Preemptive SJF라고 할 수 있다. 
    3.  Round-Robin 스케줄러는 Preemptive FCFS라고 할 수 있다. 
    4.  **Multi-Level Feedback Queue 스케줄러는 여러 개의 ready-queue에 우선순위에 따라 영구적 으로 한 개의 큐에 프로세스를 배정한다. **
    5.  Soft-real-time 요구사항을 만족하는 Real-Time OS의 스케줄러는 Priority-based CPU 스케 줄러를 사용한다.

5.  | Process | Arrival Time | CPU Burst |
    | ------- | ------------ | --------- |
    | p1      | 0            | 5         |
    | p2      | 1            | 7         |
    | p3      | 3            | 4         |

    위와 같이, P1, P2, P3 프로세스의 도착시간과 CPU Burst가 주어졌다. FCFS와 RR 스케줄러를 사용 하면 프로세스의 완료 순서가 각각 어떻게 될까? (RR 스케줄러의 time quantum은 2를 사용한다.)

    1.  `FCFS`: P1, P2, P3,  `RR2`: P1, P2, P3
    2.  `FCFS`: P1, P3, P2,  `RR2`: P1, P3, P2
    3.  **`FCFS`: P1, P2, P3,  `RR2`: P1, P3, P2**
    4.  `FCFS`: P1, P3, P2,  `RR2`: P1, P2, P3

6.  | Process | Arrival Time | CPU Burst |
    | ------- | ------------ | --------- |
    | p1      | 0ms          | 9ms       |
    | p2      | 1ms          | 4ms       |
    | p3      | 2ms          | 9ms       |

    위와 같이 프로세스 P1, P2, P3의 도착시간과 CPU Burst가 주어졌다. 만약 Preemptive SJF 스케줄러를 사용한다면 평균 대기시간(average waiting time)은 얼마인가? 단, 스케줄러는 프로세스가 도착할 때와 프로세스가 완료할 때만 동작한다고 가정한다.

    1.  **5.00ms**
    2.  4.33ms
    3.  6.33ms
    4.  7.33ms

7.  | Process | Arrival Time | CPU Burst |
    | ------- | ------------ | --------- |
    | p1      | 0            | 10        |
    | p2      | 3            | 6         |
    | p3      | 7            | 8         |
    | p4      | 8            | 3         |

    위와 같이 P1, P2, P3, P4 프로세스의 도착시간과 CPU Burst가 주어졌다. Preemptive SRTF(Shortest-Remaining-Time-First) 알고리즘을 사용하다고 했을 때, 평균 총처리시간 (average turnaround time)은 얼마인가?

    1.  10.25
    2.  11.25
    3.  **12.25**
    4.  13.25
    5.  14.25

8.  | Process | Arrival Time | CPU Burst |
    | ------- | ------------ | --------- |
    | p1      | 10           | 3         |
    | p2      | 1            | 1         |
    | p3      | 2            | 3         |
    | p4      | 1            | 4         |
    | p5      | 5            | 2         |

    위와 같이 5개의 프로세스에 대한 CPU Burst와 우선순위가 주어졌다. 적용하는 스케줄러별로 total waiting time이 잘못 짝지어진 것은?

    1.  **nonpreemptive FCFS = 47**
    2.  nonpreemptive SJF = 16
    3.  **RR (time quantum = 1) = 26**
    4.  nonpreemptive Priority-based (smaller number, higher priority) = 41

## Chapter 6. Synchronization Tools

### Quiz

1.  프로세스 동기화(synchronization)에 대한 설명으로 가장 옳은 것은?

    1.  여러 프로세스가 공유 자원에 접근할 때, 병렬적(parallel)인 처리를 할 때는 항상 동기화가 필요 하지만, 병행적(concurrent)인 처리를 할 때는 항상 동기화가 필요하지는 않다. 
    2.  생산자-소비자 문제를 두 개의 프로세스가 shared memory로 처리할 때는 동기화를 해 주어야 하지만, 두 개의 쓰레드가 같은 주소 공간에서 buffer를 저장할 때는 동기화가 필요하지 않다. 
    3.  **여러 쓰레드가 어떤 공유하는 변수에 접근하여 값을 변경하지 않고 읽기만 하는 경우에는 경쟁 상황이 발생하지 않으므로 동기화를 해 줄 필요가 없다. **
    4.  프로세스 동기화(synchronization)는 프로세스가 공유하는 자원에 접근하는 일련의 순서를 정하도 록 한다. 따라서 공유하는 프로세스들이 항상 같은 순서로 순차적으로 자원에 접근할 수 있게 하여 경쟁 상황을 방지한다. 

2.  임계영역(Critical Section)에 대한 설명으로 가장 틀린 것은?

    1.  critical-section은 어떤 프로세스의 코드 영역 중에서 여러 프로세스가 공유하는 자원에 접근하는 코드 영역을 말하고, entry-section과 exit-section 사이에 위치한다.
    2.  entry-section은 임계 영역에 진입하기 위한 권한을 획득하는 코드 영역을 말하고, 항상 critical-section 이전에 위치해야 한다.
    3.  exit-section은 임계 영역을 빠져나와서 권한을 반환하는 코드 영역을 말하고, 항상 critical-section 이후에 위치해야 한다. 
    4.  **remainder-section은 entry-, exit-, critical-section이 아닌 코드 영역을 말하고, 항상 exit-section 이후에 와야 한다. **

3.  임계 영역 문제 (Critical-Section Problem)에 대한 솔루션이 해결해야 할 요구사항에 포함하지 않아 도 되는 것은?

    1.  Mutual Exclusion
    2.  Progress (No deadlock)
    3.  Bounded Waiting (No starvation)
    4.  **Scalability**

4.  임계 영역 문제 (Critical Section Problem)의 해결책에 대한 설명으로 가장 옳은 것은?

    1.  Single-core 시스템에서는 단순하게 인터럽트를 방지하는 것만으로 임계영역 문제를 해결할 수 없다.
    2.  **비선점형(nonpreemptive) 커널에서는 경쟁 상황이 발생하지 않으므로 임계영역 문제를 고려할 필요가 없다.**
    3.  피터슨 알고리즘은 상호 배제 문제를 확실하게 해결했지만, 데드락과 기아 문제를 해결하기 위한 하드웨어적인 지원이 필요하다.
    4.  원자적 변수(Atomic Variable)는 공유 변수에 대한 접근을 하는 명령어(instruction)를 하드웨어 적으로 만들어 지원해 주는 임계 영역 문제 해결책이다. 

5.  아래와 같이 피터슨 알고리즘을 구현했을 때, A, B, C, D에 들어갈 값으로 잘못 짝지어진 것을 모두 고르시오.

    ```java
    while (true) {
        /* entry section */
        flag[0] = true;
        trun = "A";
        while (flag["B"] && turn == "C") 
            ;
        	/* critical section*/
        
        /* exit section */
        flag["D"] = false;
        	
        	/* remainder section */
    }
    ```

    1.  **A = 0**
    2.  B = 1
    3.  **C = 0**
    4.  **D = 0**

6.  수업시간에 다룬 producer-consumer 예제에서, producer가 두 개의 쓰레드로 실행되고, consumer가 두 개의 쓰레드로 실행되었다고 가정해 보자. 만약 count의 값이 현재 5인 상태에서, 네 개의 쓰레드가 한 번씩 concurrent하게 실행되었다면, 다음 중 최종 count의 값을 가능한 값을 모두 고르시오. 

    1.  **3**
    2.  **4**
    3.  **5**
    4.  **6**
    5.  **7**

7.  하드웨어 솔루션으로 임계구역 문제를 해결할 때, 이에 대한 설명으로 가장 틀린 것은?

    1.  임계영역 문제를 해결하기 위한 하드웨어 솔루션은 atomicity(원자성)를 보장하는 명령어를 제공 한다.
    2.  test_and_set 명령어는 불린 변수인 lock을 이용한다.
    3.  compare_and_swap 명령어는 전역 변수인 lock을 이용한다.
    4.  **atomic_variable 명령어는 변수에 대한 접근을 제어하는 하드웨어 명령어**

8.  임계구역 문제에 대한 설명으로 가장 틀린 것은?

    1.  경쟁 상황(race condition)이 발생할 수 있는 코드 영역을 임계구역(critical section)이라 한다.
    2.  피터슨 알고리즘은 임계구역 문제에 대한 소프트웨어 해결책이고, compare_and_swap은 하드웨 어 해결책이다. 
    3.  atomic variable을 이용하여 생산자-소비자 문제의 동기화를 해결할 수 있다. 
    4.  **Java에서 Peterson 알고리즘을 그대로 구현하면 data inconsistency가 발생하지 않는다.**

