# Kafka 학습

>   **패스트캠퍼스**에서 진행하는 [Kafka 완전 정복](https://fastcampus.app/course-detail/207099) 강의를 듣고 정리한 내용을 개인 학습을 위해 기록한 문서입니다.



## Apache Kafka란?

기본적으로 Event Streaming Platform이다.

말 그대로 움직이는 데이터를 처리하는 플랫폼!



여기서 이벤트란 무엇인가?

Event는 비즈니스에서 일어나는 모든 일(데이터)를 의미한다.

- 웹 사이트에서 무언가를 클릭하는 것
- 청구서 발행
- 은행에서의 송금 송금
- 배송 물건의 위치 정보
- 택시의 GPS 좌표
- 센서의 온도/압력 데이터



이런 Event는 비즈니스의 모든 영역에서 광범위하게 발생하기 때문에 BigData의 특징을 가진다. 



### 특징

1. Publish & Subscribe

    이벤트 스트림을 안전하게 전송

2. Write to Disk

    이벤트 스트림을 디스크에 저장

3. Processing & Ananlysis

    이벤트 스트림을 분석 및 처리



`RabbitMQ`와 성능테스트를 진행해보았는데 월등히 높은 성능을 보여준다.

`RabbitMQ`의 레이턴시는 30MB/s 보다 높은 처리량에서 크게 저하된다.



*창시자인 **Franz Kafka**는 쓰기에 특화된 카프카의 특징에 의해 작가인 이름을 사용하게 되었다.*



### 사용사례

>   Event(메시지/데이터)가 사용되는 모든 곳

- Messaging System
- IoT 디바이스로부터 데이터 수집
- 애플리케이션에서 발생하는 로그 수집
- Realtime Event Stream Processing (이상 감지)
- DB 동기화 (MSA 기반의 분리된 DB간 동기화, 고객정보, 재고관리 등)
- 실시간 ETL
- Spark, Flink, Storm, Hadoop과 같은 빅데이터 기술과 함께 사용





## Topic, Partition, Segment

<img width="1103" alt="image-20220303094747596" src="https://user-images.githubusercontent.com/58545240/156479976-b3412276-216e-4a43-9b17-c1ff4279ffcb.png">

- **Producer**
    - 메시지를 생산(Product)해서 카프카의 Topic으로 메시지를 보내는 애플리케이션
- **Consumer**
    - Topic의 메시지를 가져와서 소비(Consume)하는 애플리케이션
- **Consumer Group**
    - Topic의 메시지를 사용하기 위해 협력하는 Consumer들의 집합
    - 하나의 Consumer는 하나의 Consumer Group에 포함되면 그룹내의 Consumer들은 협력해서 Topic의 메시지를 분산 병렬처리한다.



### Producer와 Consumer의 기본 동작 방식

Producer와 Consumer는 서로 알이 못하며, Producer와 Counsumer는 각각 고유의 속도로 **Commit Log**에 Write 및 Read를 수행한다.

다른 Consumer Group에 속한 Consumer들은 서로 관련이 없으며, **Commit Log**에 있는 `Event(Message)`를 동시에 다른 위치에서 Read할 수 있다.

<img width="902" alt="image-20220303095237334" src="https://user-images.githubusercontent.com/58545240/156480080-6a8e16c3-8587-4d37-a70a-b758cfdbee0a.png">



여기서 **Commit Log**란 무엇인가?

추가만 가능하고 변경이 불가능한 데이터 구조이다.

데이터(Event)는 항상 로그 끝에 추가되고 변경되지 않는다.

![image-20220303095358065](https://user-images.githubusercontent.com/58545240/156480088-c8a658ac-797c-4835-a172-e5cf53e14716.png)



Producer가 Write하는 `LOG-END-OFFSET`과 Consumer Group의 Consumer가 Read하고 처리한 후에 Commit한 `CURRENT-OFFSET`과의 차이(**Consumer Lag**)가 발생할 수 있다.

![image-20220303095521338](https://user-images.githubusercontent.com/58545240/156480091-f700f9b2-c4a0-47b7-883e-4675cbb04fab.png)





### Topic

>   Kafka안에서 메시지가 저장되는 장소, 논리적인 표현



### Partition

>   Commit Log
>
>   하나의 Topic은 하나 이상의 Partition으로 구성된다.
>
>   병렬처리(Throughput 향상)을 위해서 다수의 파티션을 사용한다.

### Segment

>   메시지(데이터)가 저장되는 실제 물리 File
>
>   Segment File이 지정된 크기보다 크거나 지정된 기간보다 오래되면 새 파일이 열리고 메시지는 새 파일에 추가된다.

![image-20220303095736213](https://user-images.githubusercontent.com/58545240/156480097-127728a8-3604-4e6a-8f72-24bce078a32a.png)



**Topic을 생성 시에 Partition의 개수를 지정(추후에 확장하거나 축소 가능)**하게 되고 각 파티션은 **Broker**들에 분산되며 Segment File로 구성된다.

![image-20220303095859730](https://user-images.githubusercontent.com/58545240/156480101-567b7089-6cd0-4dc6-88cf-2aefa9cddb53.png)

세그먼트 파일을 생성할 때 파일을 **Rolling**하여 파일을 분리하여 생성한다.



Partition당 오직 하나의 Segment가 **활성화(Active)** 되어 있는 데 이는 데이터가 계속 쓰여지고 있음을 의미한다.

맨 끝의 세그먼트에만 메시지가 Write된다.

![image-20220303100006161](https://user-images.githubusercontent.com/58545240/156480102-c842736b-74e3-4597-93ca-234df398e98c.png)





### 요약

- Topic 생성시 Partition 개수를 지정하고 개수가 변경 가능 하나 운영시에는 권장하지 않는다.
- Partition 번호는 0번부터 시작하고 오름차순이다.
- 토픽 내의 Partition들은 서로 독립적이다.
- 이벤트(메시지)의 위치를 나타내는 offset이 존재한다.
- 이 때 offset은 하나의 Partition에서만 의미를 가진다.
- offset값은 계속 증가하고 0으로 돌아가지 않는다.
- 이벤트(메시지)의 순서는 하나의 Partition내에서만 보장한다.
    - 메시지의 순서는 들어온 순서대로 그대로 적재된다.
- 파티션에 저장된 데이터(메시지)는 한번 쓰여지면 변경이 불가능하다.(`immutable`)
- Partition에 쓰여지는 데이터는 맨 끝에 추가되어 저장된다.
- Partition은 Segment File들로 구성된다.
    - 이 때 Rolling 정책을 사용한다.
    - 디폴트는 1GB로 끊거나, 168hours(7일)를 기준으로 끊는다.



## Broker, Zookeeper



### Broker

![image-20220303100841230](https://user-images.githubusercontent.com/58545240/156480104-20912b14-ab81-46f2-819e-689e78ad0d4c.png)



이 Kafka Broker는 Topic과 Partition을 유지하고 관리한다.

![image-20220303101027530](https://user-images.githubusercontent.com/58545240/156480105-7c804e56-1039-4a59-9db3-68b2c83c42e5.png)



Kafka Broker ID와 Partition ID는 아무런 관계가 없다.

![image-20220303101102693](https://user-images.githubusercontent.com/58545240/156480108-eda71313-4db9-4c09-afd5-1b5344a4f0b4.png)



모든 Kafka Broker는 **Bootstrap(부트스트랩) 서버**라고 부른다.



클라이언트가 브로커에 접속할 때는 이 부트스트랩 서버를 통해 접속한다.

- 하나의 Broker에만 연결해도 Cluster 전체에 연결된다.
    - 하지만, 특정 Broker 장애를 대비해서 전체 Broker List(`IP`, `Port`)를 파라미터로 입력하는 것을 권장한다.
- 각각의 Broker는 모든 Broker와 Topic, Partition에 대해 알 고 있다.(`metadata`)

![image-20220303101309446](https://user-images.githubusercontent.com/58545240/156480110-5998dfb2-aeb1-400f-b62a-cab2537fffd3.png)



### Zookeeper

>   Broker를 관리하는 (Broker들의 목록과 설정을 관리)하는 소프트웨어

![image-20220303101426183](https://user-images.githubusercontent.com/58545240/156480112-b99d863a-1d40-42f7-91c0-49c696aa3a69.png)



Zookeeper의 아키텍쳐는 **Leader/Follower** 기반의 **Master/Slave** 아키텍쳐로 구성된다.

Zookeeper는 분산형 Configuration 정보를 유지하고 분산 동기화 서비스를 제공하고, 대용량 분산시스템을 위한 네이밍 레지스트리를 제공하는 소프트웨어 이다.

따라서 분산 작업을 제어하기 위한 `Tree`형태의 데이터 저장소이다.

이 Zookeeper를 사용해서 멀티 Kafka Broker들 간의 정보를 공유하고 동기화를 수행한다.

![image-20220303101621170](https://user-images.githubusercontent.com/58545240/156480113-477435ec-e267-47ea-b7e7-8e9df9709c3e.png)



Zookeeper는 **Quorum** 알고리즘을 기반으로한다.

**Ensembel(앙상블)**은 Zookeeper 서버의 클러스터이다.

여기서 **Quorum(쿼럼)**은 "정족수"이며 합의체가 의사를 진행시키거나 의결을 하는 데 필요한 최소한도의 인원수를 뜻한다.

분산 코디네이션 화경에서 예상치 못한 장애가 발생해도 분산 시스템의 일관성을 유지시키기 위해서 사용된다.


예를 들어

Ensembel이 3대로 구성되어 있다면 Quorum은 2, 즉 Zookeeper 1대가 장애가 발생하더라도 정상동작한다.

Ensembel이 5대로 구성되어 있다면 Quorum은 3, 즉 Zookeeper 2대가 장애가 발생하더라도 정상동작한다.

![image-20220303101848025](https://user-images.githubusercontent.com/58545240/156480116-98b5836d-de30-4f72-8269-925ed7f9d1cb.png)



### 요약

- Broker와 Zookeeper는 서로 다르다
- Broker는 Partition에 대한 Read / Wrtie를 관리하는 소프트웨어이다.
    - Broker는 Topic 내의 Partition들을 분산, 유지 및 관리한다.
    - 최소 3대 이상의 Broker를 하나의 Cluster로 구성해야 한다. (*4대 이상을 권장함*)
- Zookeeper는 Broker를 관리한다. (Broker들의 목록/설정을 관리)
    - Zookeeper는 홀수의 서버로 작동하도록 설계되어 있다. (최소 3, 권장 5)



## Producer란?

<img width="1103" alt="156479976-b3412276-216e-4a43-9b17-c1ff4279ffcb" src="https://user-images.githubusercontent.com/58545240/156486771-284f936a-e448-4dd1-bd48-8fdeaa90f47e.png">



Producer는 기본적으로 메시지를 생산(`Produce`)해서 Kafka 의 Topic으로 메시지를 보내는 애플리케이션이다.

메시지(== 레코드 == 이벤트 == 데이터)의 구조는 아래와 같다.

주로 바디타입(키-밸류, 주로 비즈니스 로직)을 만지게 될 것이다.

![image-20220303112454543](https://user-images.githubusercontent.com/58545240/156486782-d0bc2650-7569-4517-858d-33c16c34d811.png)



또한 Kafa는 이 메시지를 저장할 때 Byte Array로 저장한다.

어떤 데이터를 만들어 `Send`를 하게 되면 **Publish & Subscribe** 라이브러리에서 직렬화 되어 Byte Array로 변환되고 이를 Kafka로 전송되어진다. 이후 Consumer는 이를 사용하기 위해서 다시 역직렬화를 진행한다.

![image-20220303112537619](https://user-images.githubusercontent.com/58545240/156486783-bcd6711a-30f6-40a6-be28-4a88a7a54c76.png)



### Serailizer

>   Key와 Value 용 Serilaizer를 각각 설정할 수 있다.

![image-20220303112922390](https://user-images.githubusercontent.com/58545240/156486787-33405318-5919-4b57-91cd-b9863b6dacbf.png)

CONFIG 파라미터를 사용해서 카프카의 Producer 설정을 진행하게 된다. 각각의 KEY/VALUE Serializer를 이용해서 직렬화를 진행할 수 있다.



카프카의 아키텍처는 다음과 같다.

![image-20220303113043457](https://user-images.githubusercontent.com/58545240/156486788-291d722c-b310-4a72-adc9-cdda25d1241a.png)

`send`를 진행하고 나면 먼저 직렬화를 하게 된다.

 **Partitioner**의 역할은 메시지를 **Topic**의 어떤 **Partition**으로 보낼지 결정한다.



우리가 코딩으로 하는 것은 한가지 뿐이다.

Record를 만들고 Send해주는 것. 그 때 Serilaizer를 이용해 셋팅해주는 것.



![image-20220303113124153](https://user-images.githubusercontent.com/58545240/156486791-dd3c37fb-15c0-4a83-b374-9dac252b397b.png)

이 **Partitioner**의 종류에는 성능과 작동방식이 모두 다양한데 보통은 default Partitioner를 많이 쓴다.

이 때 전제조건은 Key를 반드시 셋팅해주어야 한다.

![image-20220303113206918](https://user-images.githubusercontent.com/58545240/156486793-605c9bf1-32dd-42a9-8bbf-30127a383d6d.png)

배치형태로 하다보니까 메시지가 덜 담겼는데도 날라가는 경우가 발생해서 배치가 닫힐 때까지 채운다음에 한번에 묶어서 보내는 방식으로 버전이 업데이트 되었다.



### 요약

-   Message == Recored == Event == Data
-   Message는 Header와 Key 그리고 Value로 구성
-   Kafka는 Record(데이터)를 Byte Array로 저장한다.
-   Producer는  Serializer, Consumer는 Deserializer를 사용한다.
-   Producer는 Message의 Key 존재 여부에 따라 Partitioner를 통한 메시지 처리 방식이 다르다.



## Consumer란?

>   Partition으로부터 Record를 가져온다.(`Poll`)

Counsumer는 각각 고유의 속도로 **Commit Log**로부터 순서대로 Read(`Poll`)을 수행한다.

다른 Consumer Group에 속한 Consumer들은 서로 관련이 없으며, Commit Log에 있는 Event(Message)를 동시에 다른 위치에서 Read할 수 있다.

![image-20220303131717635](https://user-images.githubusercontent.com/58545240/156500887-1a2cd864-d922-4753-ac11-e1b5de34eac2.png)



Consumer Group이 읽은 위치를 표시하기 위해 **Consumer Offset**이 사용된다.

Counsumer가 자동이나 수동으로 데이터를 읽은 **위치**를 `commit`하여 다시 읽는 것을 방지한다.

**`__consumer_offsets`**라는 Internal Topic에서 Consumer Offset을 저장해 관리한다.

![image-20220303131827694](https://user-images.githubusercontent.com/58545240/156500890-02652e07-4616-4ec7-8956-c67c1e72cfe3.png)



### Single Consumer

모든 Partition에서 Consume하는 것을 **Multi-Partitions with Single Consumer**라고 한다.

![image-20220303131930161](https://user-images.githubusercontent.com/58545240/156500894-fc8d1651-e1fe-42dc-b1cf-75b48faae762.png)



또한, 동일한 `group.id`로 구성된 모든 Consumer들은 하나의 Consumer Group으로 형성된다.

4개의 파티션이 있는 Topic을 consume하는 4개의 Consumer가 하나의 Consumer Group에 있다면 각 Consumer는 정확히 하나의 Parition에서 Record를 consume한다.

Partition은 항상 Consumer Group내의 하나의 Consumer에 의해서만 사용된다.

Consumer는 주어진 Topic에서 0개 이상의 많은 Partition을 사용할 수 있다.

![image-20220303132117590](https://user-images.githubusercontent.com/58545240/156500896-f62a4fd8-9f97-4fd9-8577-d53a3d68ad75.png)



### Multi Consumer Group

Partition을 분배하여 Consume한다.

동일한 `group.id`로 구성된 모든 Consumer들은 하나의 Consumer Group을 형성한다.

Consumer Group의 Consumer들은 작업량을 어느 정도 균등하게 분할한다.

동일한 Topic에서 consume하는 여러 Consumer Group이 있을 수 있다.

![image-20220303132311555](https://user-images.githubusercontent.com/58545240/156500899-247d2699-199e-4f6b-b7a1-639cddc6365a.png)



### Key의 사용

Key를 사용하게 되면 Partition 별로 동일한 Key를 가지는 메시지를 저장할 수 있다.

![image-20220303132359025](https://user-images.githubusercontent.com/58545240/156500902-0fdee1c1-193a-47f1-9f7f-3676738bd344.png)

![image-20220303132417655](https://user-images.githubusercontent.com/58545240/156500905-312d6373-72a5-47ec-9b15-34d8346eafc2.png)



### Message Ordering(순서)

Partition이 2개 이상인 경우 모든 메시지에 대한 **전체 순서는 보장이 불가능**하다.

Partition을 1개로 구성하면 모든 메시지에서 전체 순서를 보장 가능하다. 하지만 **처리량이 저하**된다.

![image-20220303132642734](https://user-images.githubusercontent.com/58545240/156500908-b8afd370-4108-4592-98b9-a5e2a77a21d3.png)



>    *Partition을 1개로 구성해서 모든 메시지에 전체 순서를 보장해야 하는 경우가 얼마나 많을까??* 
>
>   사용자 전체에 대한 순서보장? 데이터 전체에 대한 순서보장? 이런 경우는 흔치 않다.
>
>   **대부분의 경우, 특정 Key(특정 Row)로 구분할 수 있는 메시지들의 순서 보장이 필요한 경우가 많다 !**

![image-20220303132725200](https://user-images.githubusercontent.com/58545240/156500912-37d9414a-3619-4433-88c4-0f4041d18d39.png)



Key를 사용해서 Partition별 메시지 순서를 보장할 수 있다.

동일한 Key를 가진 메시지는 동일한 Partition에만 전달되기 때문에 Key 레벨의 순서를 보장할 수 있다. 따라서 멀티 Partition을 사용할 수 있어 처리량도 증가하게 된다.

**하지만 운영중에 Partition의 개수를 변경하게 된다면 순서는 보장할 수 없다.**

![image-20220303132848536](https://user-images.githubusercontent.com/58545240/156500914-ce14b0aa-3a14-4691-ab85-405b78954189.png)



### Cardinality

>   특정 데이터 집합에서 유니크(Unique)한 값의 개수

![image-20220303133108022](https://user-images.githubusercontent.com/58545240/156500917-406e19a4-2cfd-48af-bb32-ce382c411b73.png)



### Consumer Failure

4개의 파티션이 있는 Topic을 consume하는 4개의 Consumer가 하나의 Consumer Group에 있다면, 각 Consumer는 정확히 하나의 Partition에서 Record를 consume한다.

Partition은 항상 Consumer Group 내의 하나의 Consumer에 의해서만 사용되고

Consumer는 주어진 Topic에서 0개 이상의 많은 Partition을 사용할 수 있다.

![image-20220303133304162](https://user-images.githubusercontent.com/58545240/156500919-aa3fc24a-7839-448f-9396-cd9b4c7249b5.png)



Consumer Group 내의 다른 Consumer가 실패한 Consumer를 대신해 Partition에서 데이터를 가져와 처리한다.

![image-20220303133419569](https://user-images.githubusercontent.com/58545240/156500920-21cc7292-7096-4b81-a0aa-442b116b91bd.png)



### 요약

-   Consumer가 자동이나 수동으로 데이터를 읽은 위치를 commit해서 다시 읽는걸 방지한다.
-   `__consumer_offsets`라는 Internal Topic에서 Consumer Offset을 저장하여 관리한다.
-   동일한 `group.id`로 구성된 모든 Consumer들은 하나의 Consumer Group을 형성한다.
-   다른 Consumer Group의 Consumer들은 분리되어 독립적으로 작동한다.
-   동일한 Key를 가진 메시지는 동일한 Partition에만 전달되어서 Key 레벨의 순서를 보장할 수 있다.
-   Key 선택이 잘못되면 작업 부하가 고르지 않을 수 있다.
-   Consumer Group 내의 다른 Consumer가 실패한 Consumer를 대신해 Partition에서 데이터를 가져와서 처리한다.