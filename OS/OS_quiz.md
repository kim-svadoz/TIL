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

