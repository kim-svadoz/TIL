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

---

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

---

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

---

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

---

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

### Quiz

1.  국수를 아주 좋아하는 철학자 5명이 모였습니다. 그들은 원형 테이블에 둘러 앉아 "삶이란 무엇인가?"라는 질문에 대한 해답을 고민하기 시작했습니다. 원형 테이블의 중앙에는 국수가 놓여 있었고, 테이블에는 모두 다섯 개의 젓가락이 놓여 있었습니다. 즉, 철학자들 한 명의 왼쪽과 오른쪽에 각각 한 개의 젓가락이 놓여 있다는 뜻입니다. 따라서 철학자들은 왼쪽과 오른쪽 양쪽의 젓가락을 이용하여 국수를 먹을 수 있습니다. 국수는 무한리필되기 때문에 5명의 철학자들은 해답을 찾을 때까지 생각을 하기로 했습니다. 하지만 어느날 그들은 모두 굶어 죽었습니다. 왜 그랬을까요?

    이 문제는 자원(젓가락)을 공유하는 프로세스(철학자)의 동기화 문제로 유명한 메타포어인 "철학자들의 저녁식사" 문제입니다. 위 문제의 설명으로 적절한 것을 모두 고르시오.

    1.  **시계 방향으로 돌아가면서 먹기로 약속을 정했으면 아무도 굶어죽지 않았을 것이다.**
    2.  모두가 왼쪽에 있는 젓가락을 먼저 집고, 오른쪽 젓가락을 나중에 집기로 약속을 한다면, 아무도 굶어죽지 않을 것이다. 
    3.  인접한 두 철학자들이 서로 동시에 식사를 하지 않기로 약속을 한다면, 아무도 굶어죽지 않을 것이 다. 
    4.  홀수 번호의 철학자는 먼저 왼쪽 젓가락을 집고, 다음에 오른쪽 젓가락을 집도록 하고, 반대로 짝 수 번호인 철학자는 오른쪽 젓가락을 먼저 집고, 다음에 왼쪽 젓가락을 집도록 하면 아무도 굶어죽 지 않을 것이다. 

2.  Counting Semaphore에 대한 설명으로 가장 틀린 것은?

    1.  여러 프로세스 간의 동기화 문제를 해결하는 데 사용할 수 있다. 
    2.  여러 프로세스의 상호배제(mutual exclusion)를 지원하기 위해서 세마포어의 초기값을 n으로 지 정할 수 있다. 
    3.  주로 사용 가능한 자원의 인스턴스가 여러 개인 경우에 카운팅 세마포어를 사용할 수 있다. 
    4.  **카운팅 세마포어를 사용하면 deadlock과 starvation 문제도 해결할 수 있다. **

3.  다음 중 동기화 문제 해결을 위한 monitor 방법에 대한 설명으로 가장 옳지 않은 것은?

    1.  모니터 내부의 자원(변수)에 접근하고자 하는 프로세스는 반드시 모니터 내부의 함수를 호출해야 한다. 
    2.  모니터 내부의 함수를 사용하는 프로세스는 상호배제(mutual exclusion)가 보장된다. 
    3.  **모니터에서는 동기화를 위해 wait()와 signal()을 사용하고, signal() 함수를 호출하면 모니터 큐 에서 대기한다. **
    4.  자바에서는 모니터락을 지원하므로 synchronized 키워드로 간단하게 임계구역을 지정할 수 있다.

4.  주니온은 서울 출장을 가기 위해 KTX 열차표를 온라인으로 발매했는데, 해당 좌석에 가보니 동일한 시간에 동일한 좌석으로 발매된 열차표를 가진 승객이 있었다. 이 경우 KTX 온라인 발권 시스템에 어떤 문제가 발생했다고 보는 것이 가장 합리적일까?

    1.  **좌석표 데이터에 대한 접근을 할 때 mutual exclusion을 제대로 보장해 주지 못했을 것이다.**
    2.  여러 개의 발권 쓰레드가 deadlock에 빠져 progress 조건을 만족하지 못했을 것이다. 
    3.  주니온의 발권 처리 프로세스의 우선순위가 낮아 starvation이 발생했을 것이다.
    4.  발권 처리 과정에서 bounded waiting을 하느라 중복 발행을 하게 되었을 것이다. 

5.  Dijkstra가 제안한 동기화 문제에 대한 소프트웨어 솔루션으로, 경쟁 상황이 발생하는 임계 영역을 가 지는 여러 개의 프로세스에 대해 상호 배제를 보장하는 두 개의 연산인 `P()`와 `V()`를 제공하는 해결책 을 무엇이라고 할까?

    1.  뮤텍스
    2.  **세마포어**
    3.  모니터
    4.  라이브니스

6.  Multi-Level Feedback Queue 스케줄링 알고리즘은 여러 개의 우선순위가 다른 ready queue를 사 용하여 하나의 ready queue에서 처리를 마치지 못한 프로세스를 우선순위가 더 높은 ready queue 에 feedback 시켜줌으로서 우선순위를 높여준다. 이것을 우리는 노화(aging) 기법이라고 부르기도 한 다. aging 기법이 필요한 이유와 가장 관련이 높은 것은?

    1.  mutual exclutioin
    2.  progress
    3.  **bounded waiting**
    4.  critical section

7.  Process Synchronization에 대한 설명으로 가장 옳은 것은?

    1.  어떤 Scheduler를 사용하더라도 race condition은 기피할 수 없으므로 Mutual Exclusion, Progress, Bounded Waiting 을 고려해서 Critical Section Problem 을 해결해야 한다.
    2.  Semaphore 는 정수 변수로서 오로지 wait과 signal 연산만을 통해서 접근할 수 있으며 반드시 0으로 초기화를 해 주어야 한다.
    3.  Spinlock은 busy waiting을 하며 lock을 획득하는 방식이므로 멀티코어 시스템에서는 비효율적 이므로 사용하면 좋지 않다. 
    4.  **Monitor는 모니터 내부에서는 항상 하나의 프로세스만이 활성화되도록 보장해 주므로, 프로그래 가 동기화 제약 조건을 명시적으로 프로그래밍해야 할 필요가 없다는 장점이 있다.**

8.  Semaphore를 사용했을 때 발생할 수 있는 문제점으로 옳은 것을 모두 고르시오.

    1.  반드시 Spinlock(busy waiting)이 발생한다. 
    2.  **Deadlock & Starvation이 발생할 수 있다.**
    3.  **wait(), signal()을 순서에 맞게 사용하지 않으면 mutual exclusion 문제가 발생할 수 있다.**
    4.  mutual exclusion 문제는 절대로 발생할 수가 없다.

9.  Peterson's algorithm과 같은 software적인 해결책은 SMP (Symmetric Multiprocessor System) 환경과 같은 modern computer system에서는 제대로 작동할 수 있다는 보장이 없다. 그 해결책으로 하드웨어적인 instruction이나 mutex, semaphore와 같은 소프트웨어적인 API를 사용하여 프로세스 간 동기화를 보장하는 방법을 사용한다. ① 이런 기법들은 모두 **이것**을 획득하도록 하여 critical section을 보호한다. **이것 혹은 이것을 이용하는 기법**을 무엇이라 하는가? ② 동기화를 위한 이런 방 법을 구현하는 데 있어서, test_and_set()이나 compare_and_swap()과 같은 하드웨어 instruction 이나 mutex/semaphore의 wait(), signal() 구현은 모두 **이 성질**을 만족해야만 한다. **이 성질**을 무 엇이라 하는가?

    1.  **locking, atomicity**
    2.  busy waiting, atomicity
    3.  locking, integrity
    4.  busy waiting, integrity

10.  다음 Java 코드 중에서 mutual exclution이 보장되기 어려운 코드를 모두 고르시오.

     ```java
     // 1)
     class Counter {
         public static int count = 0;
         synchronized public static void increment() {
             Counter.count++;
         }
     }
     
     // 2)
     class Counter {
         public static int count = 0;
         public static Object obj = new Object();
         public static void increment() {
             synchronized (obj) {
                 Counter.count++;
             }
         }
     }
     
     // **3)**
     class Counter {
         public static int count = 0;
         public static void increment() {
             synchronized (this) {
                 Counter.count++;
             }
         }
     }
     
     // **4)**
     class Counter {
         public static int count = 0;
         public static void increment() {
             synchronized (new String("lock") {
                 Counter.count++;
             }
     	}
     }
     ```


----

## Chapter 7. Synchronization Examples

### Quiz

1.  Readers-Writers Problem에서 Reader 프로세스가 다음과 같은 자료 구조를 가지고 있다

    ```cpp
    semaphore rw_mutex = 1; 
    semaphore mutex = 1; 
    int read_count = 0;
    ```

    다음 사례들 중에서 rw_mutex 세마포어를 사용할 필요가 없는 경우로 가장 적절한 것은?

    1.  첫번째 reader 프로세스가 임계구역에 진입할 때.
    2.  마지막 reader 프로세스가 임계구역에 진입할 때.
    3.  writer 프로세스가 쓰기 작업을 완료할 때. 
    4.  **두번째 reader 프로세스가 임계구역에 진입할 때.**

2.  Bounded Buffer Problem의 consumer process 구조를 아래와 같이 구현했다.

    ```cpp
    while (true) {
        (A) wait(full);
        (B) wait(mutex);
        …
        /* remove an item from buffer to next_consumd */
        …
    	(C) signal(mutex);
         (D) signal(empty);
        …
    }
    ```

    위 슈도 코드에 대한 설명으로 가장 적절한 것은?

    1.  (A)에서 wait(full)을 했을 때 signal(full)을 해 주지 않으므로 deadlock이 발생할 것이다.
    2.  (A)에서 wait(full)을 했을 때 signal(full)을 해 주지 않으므로 deadlock이 발생할 것이다.
    3.  (C)에서 signal(mutex)를 호출하는 것은 다른 consumer 프로세스가 임계구역에 진입할 수 있 게 해 준다.
    4.  **(D)에서 signal(empty)를 호출했으므로 producer 프로세스가 임계구역에 진입할 수 있을 것이다.**

3.  `<잠자는 TA 문제>` 주니온 교수는 운영체제 과목을 어려워하는 학생들을 도와줄 수 있는 TA를 한 명 배정하기로 했다. TA가 대기하는 IT융복합관 999호는 좁아서 한 명의 TA와 한 명의 학생만 들어갈 수 있다. 999호 바깥 복도에는 3개의 의자가 있으므로 TA가 한 명을 돕고 있을 때는 여기서 기다릴 수 있다. 도움을 요청하는 학생들이 없으면 잠이 많은 TA는 항상 졸고 있다. TA의 도움이 필요한 학생들은 999호에 도착해서 TA가 졸고 있으면 깨워서 도움을 받을 수 있다. 만약 이미 도움을 받는 학생이 있으면 빈 의자에서 대기하고, 빈 의자가 없으면 돌아갔다가 다시 와야 한다. 위 문제에 대해서 동기화 방법을 조율하는 해결책에 대한 설명으로 옳다고 할 수 없는 것을 모두 고르 시오.

    1.  TA 1명과 도움이 필요한 n명의 학생들을 프로세스, 혹은 쓰레드로 구현하면 좋겠다.
    2.  TA가 졸고 있을 때 도착한 학생은 조교를 깨우기 위해 뮤텍스 락을 이용해 깨우면 좋겠다.
    3.  빈 의자에 앉을 때는 의자의 수로 초기화된 카운팅 세마포어를 이용하면 좋겠다. 
    4.  **빈 의자가 가득찼을 때, 학번이 더 높은 학생이 도착하면 학번이 낮은 학생을 선점(premption) 하도록 하면 deadlock 문제가 발생할 수 있다.** 
    5.  **TA의 도움을 받은 학생이 나가면서 바깥 의자에 대기하는 학생들에게 끝났다고 알려주지 않으면 starvation 문제가 발생할 수 있다.**

4.  강의자료에 제공된 Dining Philosophers Problem에 대한 **Pthread / Java 솔루션**에 대한 설명으로 옳다고 할 수 없는 것을 모두 고르시오.

    1.  **철학자들의 저녁식사 문제의 Pthread 솔루션은 Pthread에서 제공하는 monitor lock을 사용하고 있다.**
    2.  pthread_cond_wait() 함수가 호출되기 전에 condition 변수와 연계된 mutex 락이 먼저 잠겨져 야 한다. 
    3.  공유 데이터를 변경하는 쓰레드는 pthread_cond_signal() 함수를 호출하여 condition 변수가 참 이 되기를 기다리는 쓰레드에게 신호를 보낸다.
    4.  **Java 솔루션에서는 DiningPhilosopherMonitor 클래스가 monitor 기능을 제공하고 있으며, Philosopher 쓰레드의 갯수만큼의 monitor를 생성하여 각 Philosopher 객체가 monitor instance 하나씩을 가지고 있다.**
    5.  **Java 솔루션에서는 n 명의 철학자 쓰레드를 생성하기 위해서 카운팅 세마포어를 정의하고 초기값 으로 n을 설정해 주었다.**

5.  Thread-safe Concurrent Application을 개발하기 위한 여러 가지 대안에 대한 설명으로 가장 틀린 것은?

    1.  transactional memory를 통해 메모리 읽기와 쓰기 연산을 원자적 연산(atomic operation)으로 만들 수 있다. 
    2.  OpenMP에서 #pragma omp critical 컴파일러 디렉티브로 임계 구역을 지정해 줄 수 있다. 
    3.  함수형 프로그래밍 언어들은 명령형 프로그래밍 언어와 달리 상태를 유지하지 않으므로 경쟁조건이 나 교착상태가 아예 발생하지 않는다. 
    4.  **여러 개의 CPU가 존재하는 다중 코어 시스템에서는 각 코어에서 별도의 프로세스(혹은 쓰레드)가 독립적으로 실행되기 때문에 동기화 문제는 크게 문제가 되지 않는다**

---

## Chapter 8. Deadlocks

### Quiz

1.  다음 여섯개의 resource-allocation graph의 그림 중에서 데드락이 발생한 경우만 모아 놓은 것을 고르시오.

    ![image-20210515225458115](C:\Users\USER\AppData\Roaming\Typora\typora-user-images\image-20210515225458115.png)

    1.  a, b, d
    2.  b, c, d
    3.  **b, d**
    4.  b, d, e, f
    5.  b, c, d, e, f

2.  어떤 시스템이 세 개의 쓰레드 T1, T2, T3로 구성되어 있고 이 쓰레드들은 동일한 자원인 R의 인스턴 스 세 개를 필요로 하고 있다. 이 시스템이 절대로 데드락에 빠지지 않게 하기 위해서는 R이 최소한 몇 개 이상의 인스턴스를 가져야 하는가?

    1.  6
    2.  **7**
    3.  8
    4.  9
    5.  10

3.  데드락 방지 (deadlock prevetion) 방법에 대한 설명으로 가장 틀린 설명은?

    1.  새로운 자원을 요청(request)하기 전에 모든 자원을 반납(release)하면 데드락은 발생하지 않는 다. 
    2.  모든 자원에 유일한 번호를 부여하고, 자원을 요청할 때 반드시 오름차순으로 요청을 하면 데드락 을 발생하지 않도록 할 수 있다. 
    3.  새로운 자원을 요청하는 쓰레드가 있으면 해당 자원을 소유한 쓰레드를 선점(preemption)시켜 버 리는 데드락이 발생하지 않는다. 
    4.  **뮤텍스락이나 세마포어를 이용하여 상호 배제(mutual exclusion)를 하도록 하면 데드락은 발생하 지 않는다. **

4.  한 집합의 쓰레드들이 공유하는 자원이 단 하나의 인스턴스를 가진 자원 딱 하나일 경우에 대한 설명으 로 가장 옳은 것은?

    1.  **데드락이 발생할 일이 전혀 없다.**
    2.  두 개 이상의 쓰레드가 이 자원에 대한 경쟁 상황이 발생하면 데드락이 발생한다.
    3.  세 개 이상의 쓰레드가 이 자원에 대한 경쟁 상황이 발생하면 데드락이 발생한다.
    4.  한 쓰레드가 이 자원을 점유하고 있을 때 다른 쓰레드가 이 자원을 요청하는 상황이 되면 데드락 이 발생한다. 

5.  다음 중 데드락이 발생할 수 있는 조건 네 가지와 거리가 가장 먼 것은?

    1.  상호 배제 (mutual exclustion)
    2.  점유 대기 (hold and wait)
    3.  선점 불가 (no preemption)
    4.  **한정 대기 (bounded waiting)**

6.  교착상태(deadlock)의 방지(prevention)와 회피(avoidance)와 관련하여 가장 적절한 설명은?

    1.  **데드락 회피(avoidance)를 위해서, 어떤 요청을 수락(grant)했을 때의 시스템 상태가 safe state라면 그 요청을 수락해도 된다. **
    2.  데드락 방지(prevention)을 위해서, 어떤 시스템의 상태가 safe state에 있을 때 자원 요청이 들 어오면 그 요청을 수락해도 된다.
    3.  데드락 방지(prevention)는 데드락을 발생하지 않도록 하고, 데드락 회피(avoidance)는 데드락 이 발생하도록 두고, 데드락이 발생하면 이를 감지하여 복구한다. 
    4.  데드락 회피(avoidance)를 위한 알고리즘은 어떤 시스템의 요청(request)에 대한 선행지식(piori knowledge)을 필요로 하지 않는다. 

7.  다음 시스템 스냅샷을 고려해보자.

    쓰레드 집합 T = { T0, T1, T2 }, 자원 집합 R = { A, B, C } 세 개의 자원 유형 A, B, C의 인스턴스 개수는 각각 (8, 5, 4)이다. Allocation은 현재 프로세스가 점유한 자원의 수이고, Max는 각 프로세스가 필요로 하는 자원 수의 최대값이라고 할 때, Banker's Algorithm을 적용했을 때 올바른 설명으로만 묶인 것은?

    ```bash
    	Allocation	 	Max
    	A B C 			A B C
    T0  0 0 1			8 4 3
    T1  3 2 0 			6 2 0
    T2  2 1 1 			3 3 3
    ```

    1.  Banker's Algorithm에서 Available의 값은 (3, 2, 3)이다.
    2.  T0 쓰레드의 Need 벡터 (Need[0]) 의 값은 (8, 4, 3)이다.
    3.  **현재 이 시스템의 상태는 safe state이다.**
    4.  위 상태에서 T0가 자원을 다음과 같이 요청했다.(A=2, B=0, C=2) Request[0] = (2, 0, 2) 이 때 Resource-Request 알고리즘을 적용하면 Allocation[0] 벡터의 값은 (1, 0, 3)이 된다.
    5.  위 상태에서 T1이 자원을 다음과 같이 요청했다. (A=2, B=0, C=0) Request[1] = (2, 0, 0) 이 때 Safety 알고리즘을 적용하면 Work 벡터의 값은 (8, 5, 4)가 된다.

8.  만약 어떤 시스템이 4개의 인스턴스를 가진 자원을 하나 가지고 있다고 해보자. 이 때 3개의 쓰레드가 이 자원을 공유하고 있는데, 이 쓰레드는 2개의 인스턴스가 필요한 상황이다. 이런 시스템 상황에 대한 설명으로 가장 옳은 것은?

    1.  한 쓰레드가 2개를 점유하고 나면, 2개의 자원만 남기 때문에 데드락이 발생한다. 
    2.  한 쓰레드가 한 개씩의 자원을 점유하고 나면, 1개의 자원만 남기 때문에 데드락이 발생한다.
    3.  두 개의 쓰레드가 두 개씩의 자원을 점유하고 나면, 한 개의 쓰레드가 자원을 얻지 못해 데드락이 발생한다. 
    4.  **이런 시스템의 상황에서는 데드락이 발생할 수가 없다. **

9.  다음은 연습문제 8.3의 시스템 스냅샷이다.

    ![image-20210515225951904](C:\Users\USER\AppData\Roaming\Typora\typora-user-images\image-20210515225951904.png)

    위 시스템 상황에 대해 올바른 설명으로만 묶여진 것은? 

    a) P4의 Need 벡터는 Need[4] = (0, 6, 4, 2)이다. 

    b) 이 시스템의 상태는 safe state이다. 

    c) Request1 = (0, 4, 2, 0) (P1의 자원요청)에 대해서 grant해도 된다.

    d) 위 c)번의 Request1의 요청을 처리한 후에 Available1은 (1, 1, 0, 0)이 된다. e) 프로세스 시퀀스 의 순서는 safe sequence이다. 

    1.  **a, b, c, d, e**
    2.  a, b, d, c
    3.  a, b, e
    4.  a, b, c, e
    5.  a, b, d, e