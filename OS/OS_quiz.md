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