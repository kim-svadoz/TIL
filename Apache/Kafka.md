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



## Replication

만약 Broker에 장애가 발생한다면 Broker에 있는 Partition들을 모두 사용할 수 없게 되는 문제가 발생한다.

![image-20220303144400869](https://user-images.githubusercontent.com/58545240/156507874-7f04d216-db47-4b60-ad04-3944f997cb1b.png)



또한, Producer가 Write하는 `LOG-END-OFFSET`과 Consumer Group의 Counser가 Read하고 처리한 후에 Commit한 `CURRENT-OFFSET`과의 차이(**Consumer Lag**)가 발생할 수 있다.

![image-20220303144537078](https://user-images.githubusercontent.com/58545240/156507885-5de9b112-052b-4c9c-93bb-b197ddd589da.png)

이 때 다른 Broker에서 Partition을 새로 만들 수 있으면 장애 해결이 될까?

물론 서비스는 될 수 있을 것이다. 하지만  **메시지** 및 **Offset의 유실**에 대한 문제도 발생한다.



그래서 이러한 장애를 대비하기 위해 나온 기술이 **Replication(복제)**이다.



이 기술은 Partition을 복제해서 다른 Broker상에서 복제물(Replicas)를 만들어 장애를 대비하는 것이다.

Replicas에는 `Leader Partition`과 `Follower Partition`으로 나뉜다.

![image-20220303144748083](https://user-images.githubusercontent.com/58545240/156507889-08b180b7-966e-444f-a38e-32290c428540.png)



Producer와 Consumer는 `Leader Partition`과만 통신하고 `Follower Patition`은 복제만 담당한다.

다시말해, Producer는 Leader에만 Write하고 Consumer는 Leader로부터만 Read한다.

Follower는 Broker 장애시 안정성을 제공하기 위해서만 존재한다. 서비스를 위한 용도는 오직 Leader이다.



그럼 어떻게 복제해서 가져오나?

Follower는 Leader의 Commit Log에서 데이터를 가져오기 요청(**Fetch Request**)으로 복제한다. (Follower가 Leader에게 요청을 보내는 것)

![image-20220303144946532](https://user-images.githubusercontent.com/58545240/156507890-e2e5f44f-9228-46fa-a04c-bda101ee8a76.png)



만약 Leader에 장애가 발생한다면?

Kafka 클러스터는 Follower 중에서 새로운 Leader를 선출하고 Clients(Producer/Consumer)는 자동으로 새 Leader로 전환한다.

![image-20220303145822011](https://user-images.githubusercontent.com/58545240/156507892-cbc355e5-9387-42e8-a70f-8ad9a55eedd5.png)

리더를 선출하는 방법은 현재 단계에서는 카프카 클러스터가 리더를 선출하게 된다고만 알고 있자.



### Hot Spot 방지

하나의 Broker에만 Partition의 Leader들이 몰려있다면 특정 Broker에 부하가 집중될 것이다.

이를 방지하기 위해서 일련의 옵션 설정이 필요하다.

![image-20220303150010092](https://user-images.githubusercontent.com/58545240/156507893-140159d2-bce8-4264-964e-84a4c972783d.png)



### Rack Awareness

Rack 을 분산하여 Rack 장애를 대비할 수 도 있다.

동일한 Rack 혹은 Avaiable Zone 상의 Broker들에 동일한 "rack name"을 지정해준다.

복제본 (Replica- Leader/Follower)은 최대한 Rack 간에 균형을 유지해서 Rack 장애를 대비할 수 있도록 한다.



Topic 생성시 혹은 **Auto Data Balancer/Self Balancing Cluster** 동작 때만 실행하도록 한다.

![image-20220303150340126](https://user-images.githubusercontent.com/58545240/156507896-4fb2f24e-487e-45de-80b3-d20a0bc9e0ee.png)



### 요약

-   Partition을 복제(Replication)하여 다른 Broker상에서 복제물(Replicas)을 만들어 장애를 미리 대비한다.
-   Replicas는 Leader Partition과 Follower Partition으로 나뉜다.
-   Producer는 Leader에만 Writegkrh Consumer는 Leader로부터만 Read한다.
-   Follower는 Leader의 Commit Log에서 데이터를 가져오기 요청(**Fetch Request**)로 복제한다.
-   복제본은 최대한 Rack 간 균형을 유지해 Rack 장애를 대비하는 Rack Awareness 기능이 있다.



## In-Sync Replicas

>   ISR -> 정말 잘 복제해가고 있는가 확인하는 지표!
>
>   **In-Sync Replicas(`ISR`)**는 High Water Mark라고 하는 지점까지 동일한 Replicas(Leader, Follower 모두)의 목록이다.
>
>   이는 Leader 장애시 새로운 Leader를 선출하는데 사용된다.

![image-20220303153453039](https://user-images.githubusercontent.com/58545240/156717741-6472c3e4-e57d-47f2-92c3-a48ebac74761.png)



### ISR 판단 방법

**replica.lag.max.messages**로 `ISR`을 판단할 때 나타날 수 있는 몇 가지 문제점이 있다.

-   메시지가 항상 일정 비율(초당 유입되는 메시지, 3msg/sec 이하)로 Kafka로 들어올 때 `replica.lag.max.messages = 5`로 하면 5개 이상으로 지연되는 경우가 없으므로 `ISR`들이 정상적으로 동작한다.
-   하지만 메시지 유입량이 갑자기 늘어날 경우(ex. 10msg/sec), 지연으로 판단하고 `OSR(Out-of-Sync Replica)`로 상태를 변경시킨다.
-   실제 Follower는 정상적으로 동작하고 단지 잠깐 지연만 발생했을 뿐인데, replica.lag.max.messages 옵션을 이용하면 `OSR`로 판단하게 되는 문제가 발생한다.
    -   이는 운영중에 불필요한 error를 발생시키고 그로 인한 불필요한 retry를 유발한다



따라서 **replica.lag.time.max.ms**로 판단해야 한다!

-   Follower가 Leader로 Fetch 요청을 보내는 Interval을 체크한다.
-   Ex) `replica.lag.time.max.ms = 10000`이라면 Follower가 Leader로 Fetch 요청을 10000ms 내에만 요청을 하면 정상으로 판단한다.
-   Confluent에서는 복잡성을 제거하기 위해 replica.lag.time.max.ms 옵션만 제공한다.



### ISR의 관리

ISR은 Leader가 관리한다.

Zookeeper에 ISR을 업데이트하면 Controller가 Zookeeper로부터 수신한다.

1.   Follower가 너무 느리면 Leader는 `ISR`에서 Follower를 제거하고 Zookeeper에 `ISR`을 유지한다.
2.   Controller는 Partition Metadata에 대한 변경 사항에 대해 Zookeeper로부터 수신받는다.

![image-20220303154957575](https://user-images.githubusercontent.com/58545240/156717749-7d28b175-2fbc-4232-a536-1a28618d5fc7.png)



### Controller

-   Kafka의 Cluster 내의 Broker 중 하나가 Controller가 된다.
-   Controller는 Zookeeper를 통해 Broker Liveness를 모니터링한다.
-   Controller는 Leader와 Replica 정보를 Cluster 내의 다른 Broker들에게 전달한다.
-   **Controller는 Zookeeper에 Replicas 정보의 복사본을 유지한 다음 더 빠른 액세스를 위해 클러스터의 모든 Broker들에게 동일한 정보를 캐싱한다.**
-   **Controller가 Leader 장애시 Leader Election을 수행한다.**
-   Controller가 장애가 나면 다른 Active Broker들 중에서 재 선출된다.



### Consumer 관련 Position(Offset)

-   `Last Committed Offset (Current Offset)`
    -   Consumer가 최종 Commit한 Offset
-   `Current Position`
    -   Consumer가 읽어간 위치 (Commit 전, 처리 중 상태)
-   `High Water Mark (Committed)`
    -   ISR(Leader-Follower)간에 복제된 Offset
-   `Log End Offset`
    -   Producer가 메시지를 보내서 저장된, 로그의 맨 끝 Offset

![image-20220303155527575](https://user-images.githubusercontent.com/58545240/156717755-496c415e-f86f-4e77-992f-c8eca77e4b39.png)



***여기서 Committed의 의미는?***

>   ISR 목록의 Replicas가 메시지를 받으면 `Committed` 상태이다.

-   `ISR` 목록의 모든 Replicas가 메시지를 성공적으로 가져오면 `Committed`
-   Consumer는 `Committed` 메시지만 읽을 수 있다.
-   `Committed` 메시지는 모든 Follower에서 동일한 Offset을 갖도록 보장해준다.
-   **즉, 어떤 Replica가 Leader인지에 관계없이(장애가 발생해도) 모든 Consumer는 해당 Offset에서 같은 데이터를 바라본다.**
-   Broker가 다시 시작할 때 `Committed` 메시지 목록을 유지하도록 하기 위해 Broker의 모든 Partition에 대한 마지막 Committed Offset은 `replication-offset-checkpoint`라는 파일에 기록된다.

![image-20220303155820610](https://user-images.githubusercontent.com/58545240/156717758-2702c258-19fa-4420-bdaf-1c004bbf11d4.png)





### Replicas 동기화

-   **Hight Water Mark**
    -   가장 최근에 Committed 메시지의 Offset 추적
    -   `replication-offset-checkpoint` 파일에 checkpoint 기록
-   **Leader Epoch**
    -   새 Leader가 선출된 시점을 Offset으로 표시한다.
    -   Broker 복구 중에 메시지를 체크포인트로 자른 다음 현재 Leader를 따르기 위해 사용된다.
    -   Controller가 새 Leader를 선택하면 Leader Epoch를 업데이트하고 해당 정보를 `ISR` 목록의 모든 구성원에게 보낸다.
    -   `leader-epoch-checkpoint` 파일에 checkpoint 기록



### Message Commit 과정

>   Follower에서 Leader로 Fetch만 수행한다.

1.   Offset 5까지 복제가 완료되어 있는 상황에서,  Producer가 메시지를 보내면 Leader가 offset 6에 새 메시지를 추가

     ![image-20220303160522616](https://user-images.githubusercontent.com/58545240/156717763-50a82b52-2180-43ed-9a51-39e0864fe3ab.png)

2.   각 Follower들의 Fetcher Thread가 독립적으로 fetch를 수행하고, 가져온 메시지를 offset 6에 메시지를 Write

     ![image-20220303160644385](https://user-images.githubusercontent.com/58545240/156717764-430fa767-acf4-4b95-9a4e-ea34a6a0a3f1.png)

3.   각 Follower들의 Fetcher Thread가 독립적으로 다시 fetch를 수행하고 null을 받은 Leader는 High Water Mark로 이동

     ![image-20220303160704020](https://user-images.githubusercontent.com/58545240/156717769-7ee422e9-c499-498a-8e9f-eb78107b5884.png)

4.   각 Follower들의 Fetcher Thread가 독립적으로 다시 fetch를 수행하고 High Water Mark를 받는다.

     ![image-20220303160715871](https://user-images.githubusercontent.com/58545240/156717771-64783aba-df41-41ed-a4c4-37fbb0015bf9.png)



### 요약

-   In-Sync Replicas(`ISR`)는 High Water Mark라고 하는 지점까지 동일한 Replicas(Leader와 Follower 모두)의 목록
-   High Water Mark(Committed)
    -   `ISR`간에 복제된 Offset
-   Consumer는 Committed 메시지만 읽을 수 있다.
-   Kafka Cluster 내의 Broker중 하나가 Controller가 된다.
-   Controller는 Zookeeper를 통해 Broker Liveness를 모니터링한다.