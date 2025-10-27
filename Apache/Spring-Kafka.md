# 토픽을 생성할 때 고려해야할 점

## 1. 토픽명

-   토픽명은 한번 정하면 바꾸기가 매우 어렵기 때문에
-   Rule 을 정해 패턴화해서 의미를 부여해야 한다
    -   Ex) `event.click.fast-campus-lecture.v1`, `event.write.comment.v1`

## 2. 토픽의 파티션 개수 계산

-   1초당 메시지 발행 수 / Consumer Thread 1개가 1초당 처리하는 메시지 수 -> 기준점이 된다.
    -   Ex) 1000 / 100 = 10개의 파티션 필요
-   실시간성이 중요하고 메시지가 많이 들어올 가능성이 존재한다면 기준이 되는 파티션수보다 조금 더 많이 파티션 수를 설정하고
-   처리는 지연되도 되지만 처리만 잘 되면 된다고 하면 파티션 수를 조금 적게 잡아도 문제가 없다.
-   파티션 수를 늘릴 수는 있지만 줄일 수 는 없다.

## 3. Retention 시간

이 Retetion 시간은 카프카 브로커가 메시지를 어느정도 보관할 지 시간이나 바이트 사이즈에 따라서 이걸 초과하면 자동으로 지우는 설정이다.

만약 디스크가 작은데 시간이 길어서 메시지가 많이 쌓여 디스크가 가득 차면 대형 장애가 발생한다.

그렇기 때문에 이 시간이나 바이트 사이즈를 잘 잡는게 중요하다.



만약에 이 시간을 엄청 짧게 잡았다면 메시지가 엄청 빨리 삭제 되기 때문에 잘 고려해야 한다.



# Publish Messages

메시지 발행



## - KafkaTemplate

>   스프링부트를 이용하면 기본적으로 만들어지는 객체이다.

### KafkaTemplate 설정

직접만들기 위해선 ProducerFactory클래스를 이용해 생성한다.

-   트랜잭션을 사용하지 않는 경우, Singleton으로 생성한다.
-   `flush()`를 사용할 경우 같은 Producer를 사용하는 다른 쓰레드에서 지연현상이 발생할 수 있다.
    -   2.3 이후부터는 `producerPerThread` 속성을 추가할 수 있다.
    -   true로 설정하게 되면, 각 쓰레드에서 별도의 생성자를 만들고 캐시처리를 해준다.
    -   생성자가 많이 생성되면 또 메모리를 많이 차지하므로 생성자가 더이상 필요하지 않을 경우 `closeThreadBoundProducer()`를 호출하면 된다.
-   2.5.10 부터는 설정을 업데이트하거나 제거할 수 있는 메서드를 제공한다.
    -   SSL 키 변경 등 유용하게 사용할 수 있다.
    -   `reset()`을 사용할 경우 기존 Producer는 닫고 새로운 설정으로 Producer를 생성한다.
    -   하지만 Transaction, Non-Transactional 로의 변경은 불가능하다.



### KafkaTemplate 메시지 발송

-   기본적으로는 **비동기 처리** 이다.

    -   동기로 처리할 수 있으나, 카프카의 목적이 빠른 스트림 처리이므로 사용하지 않는게 좋다.

-   발송방법

    1.   `Message<?>` 객체를 사용하는 방법
         -   메시지에 헤더로 정보를 제공할 수 있다. (`TOPIC, PARTITION_ID, MESSAGE_KEY, TIMESTAMP`)

    2.   `ProducerRecord<K, V>`를 사용하는 방법

    토픽, 파티션, 오프셋 설정 후 전송한다.



### KafkaTemplate Listener

메시지를 발송했을 때 리스너를 제공한다.

-   2.5버전 이전에는 `ListenableFutureCallback` 라는 인터페이스를 직접 구현한다.
    -   이 인터페이스에서는 실패했을 경우 익셉션만 날려주고 어떤 메시지가 실패했는지 정보를 알기가 어려웠다.
-   2.5버전 이후에는 `KafkaSendCallback`라는 인터페이스를 제공해준다. 이는 위 인터페이스를 상속받은 것이다.
    -   좀 더 쉽게 실패한 메시지를 확인할 수 있게 되었다.



## - RoutingKafkaTemplate

-   Spring 2.5부터 지원
-   전송하는 토픽별로 옵션을 다르게 설정할 수 있다.
    -   토픽명을 Regular Expression(정규식)으로 표현할 수 있다.
    -   토픽별로 다른 Producer Factory를 사용할 수 있는 것이다.
-   transactions, execute, flush, metric 의 커맨드를 지원하지 않는다.



## - ReplyingKafkaTemplate

-   Spring 2.1.3부터 지원
-   Consumer가 특정 데이터를 전달 받았는지 여부를 확인할 수 있다.
-   강제화를 해주는 것이 포인트이다.
-   이 템플릿은 발송했을 때 이미 토픽이 지정되어있기 때문에 강제로 내가 지정했던 default 토픽을 명시할 수 있다.
-   기본적으로 3개의 Header가 정의되어있다.
    -   `KafkaHeaders.CORRELATION_ID` : 요청과 응답을 연결시키는 데 사용
    -   `KafkaHeaders.REPLY_TOPIC` : 응답 토픽
    -   `KafkaHeaders.REPLY_PARTITION` : (optional) 응답 토픽의 파티션
-   `AggregationReplyingKafkaTemplate`
    -   여러 응답을 한번에 처리한다.



# Consume Messages

메시지 구독



## - Message Listener

>   -   Record : 단일 메시지를 하나씩 하나씩 처리
>       -   `MessageListener` : Auto Commit 제공, 컨테이너에 제공하는 ack모드를 그대로 사용
>       -   `AcknowledgingMessageListener` : Manual Commit 제공
>       -   `ConsumerAwareMessageListener` : Record객체에 더하여 Consumer 객체를 활용,
>       -   `AcknowledgingConsumerAwareMessageListener` : Manual Commit, Record객체에 더하여 Consumer 객체를 활용
>   -   Batch : 레코드를 리스트 타입으로 받아와서 여러 개의 메시지를 한번에 처리
>       -   `BatchMessageListener` : Auto Commit
>       -   `BatchAcknowldgingMessageListener` : Manual Commit
>       -   `BatchConsumerAwareMessageListener` : Record객체에 더하여 Consume 객체를 활용,
>       -   `BatchAcknowledgingConsumerAwareMessageListener` : Manual Commit, Record객체에 더하여 Consumer 객체를 활용
>
>   레코드와 배치의 차이는 단일이냐 리스트냐의 차이이다.

Consumer 객체는 Thread-Safe 하지 않으므로 Listener를 호출하는 쓰레드에서만 호출되어야 한다.



### AckMode

7가지 타입의 커밋 메서드를 제공한다.

-   **`RECORD`** : 레코드를 처리한 후 리스너가 반환할 때 커밋

-   **`BATCH`** : 

    `poll()` 메서로 호출된 레코드가 모두 처리된 이후 커밋

    스프링 Kafka Consumer의 AckMode **기본값**

-   **`TIME`** :

    ackTime만큼 지난 이후에 커밋

    시간 간격을 선언하는 ackTime 옵션을 설저애햐 한다.

-   **`COUNT`** : 

    ackCount로 설정된 개수만큼 레코드가 처리된 이후에 커밋

    레코드 개수를 선언하는 ackCount 옵션을 설정해야 한다.

    *메시지가 적게 들어오는 경우 주의해야 한다.*

-   **`COUNT_TIME`** : COUNT, TIME 중 맞는 조건이 나오면 커밋

-   **`MANUAL`** :

    `Acknowledgement.acknowledge()` 메서드가 호출되면 다음번 `poll()` 메서드 호출 시 커밋

    매번, `acknowledge()`를 호출하면 BATCH 옵션과 동일하다.

    `AcknowledgingMessageListener` 또는 `BatchAcknowledgingMessageListener`를 사용해야 한다.

-   **`MANUAL_IMMEDIATE`** :

    `Acknowledgement.acknowledge()` 메서드가 호출되면 커밋

    `AcknowledgingMessageListener` 또는 `BatchAcknowledgingMessageListener`를 사용해야 한다.



### Message Listener Container

스프링에서는 두 가지 리스너 컨테이너를 지원한다.

-   KafkaMessageListenerContainer
    -   Single Thread
-   ConcurrentMessageListenerContainer
    -   KafkaMessageListenerConatiner 인스턴스를 1개 이상 사용하는 Multi-Thread
    -   start, stop 등 메소드를 foreach로 순차적으로 실행



어떤 장점이 있나?

1.   `start, stop, pause, resume`이 용이해진다.
2.   풍부한 AckMode를 지원한다.



## - @KafkaListener

-   예전의 경우 `@EnableKafka`를 사용하려면, `@Configuration` 중 Bean 이름이 `kafkaListenerContainerFactory` 인 `ConcurrentMessageListenerContainer` 객체가 필요하다. 그렇지 않으면 에러가 발생했다..
-   하지만 현재 SpringBoot에는 모든 것이 기본 세팅되어 있다.
    -   KafkaAutoConfiguration
    -   ConcurrentKafkaListenerConatinerFactoryConfigurer에서 이들을 관리가 가능하다.
-   다양한 설정을 property로 손쉽게 가능하다.
    -   `clientIdPrefix`, `autoStartup`, `concurrency`, `topicPartitions`, ...
-   메타 데이터
    -   `OFFSET, RECEIVED_MESSAGE_KEY, RECEIVED_TOPIC, RECEIVED_PARTITION_IN, RECEIVED_TIMESTAMP, TIMESTAMP_TYPE`
    -   `ConsumerRecordMetadata`를 이용해서 수신할 수 도 있다.



## - Payload Validator

>   메시지 컨테이너 안에서 부가적인 기능으로 제공된다.
>
>   실제로 내가 수신하는 메시지 객체가 유효한지 아닌지 판단할 수 있게 지원하는 기능이다.

-   스프링 2.2 이후부터 손쉽게 추가할 수 있다.
-   이전에는 `DefaultMessageHandlerMethodFactory`에 등록하고 추가했어야 하지만 이제 `KafkaListenerEndpointRegistrar`에 등록해서 사용할 수 있다.
-   스프링에서 제공되는 Validator를 이용하려면, `LocalValidatorFactoryBean`을 이용해 javax.validation(JSR-303)에 정의된 `@NotEmpty`, `@Min`, `@Max`, `@Email`과 같은 기본적인 유효성 체크도 할 수 있다.



## - Retyring Deliveries

>   재시도

### Retrying Deliveries

-   기본적으로 리스너에 에러가 발생되면 Container Error Handler가 동작하여 Exception이 발생한다.

-   하지만 이런 에러들이 극복할만 한 이슈였다면 (ex. 네트워크 단절) 재시도를 할 수 있다.

-   `RetryingMessageListenerAdapter`를 통해 Retry 기능을 호출한다.

-   `RetryTemplate`과 `RecoveryCallback<Void>`를 ContainerFactory에 설정하여 사용한다.

    -   `RecoveryCallback`이 설정되지 않으면 모든 재시도가 실패시 Container Error가 발생한다.

    -   `RecoveryCallback` 안에서 발생하면 다시 Container Error Handler가 동작하기 때문에 주의가 필요하다.

### Retry Stateful

-   **BackOffPolicy**를 이용해 재시도하는 과정에서 Consumer Thread가 중지될 수 있다.

    *BackOffPolicy란 어느 시간 단위로 재시도를 할 지 지정하는 것*

    -   너무 오랫동안 재시도를 하게 되면 `poll()`이 수행되지 않기 때문이다.
    -   `session.timeout.ms` : 설정된 시간안에 **heartbeat**를 받지 못하면 Consumer Group에서 제거하고 rebalance를 발생한다.
    -   `max.poll.interval.ms` : 설정된 시간안에 **`poll()`**이 호출되지 않으면 Consumer가 죽었다고 판단되어서 할당파티션이 revoke되고 rebalance를 발생한다.