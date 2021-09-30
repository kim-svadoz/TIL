# SpringBoot + JPA + ...

---

# @SpringBootApplication

스프링부트 템플릿을 통해 프로젝트를 생성하면 아래와 같이 자동으로 `xxxApplication`에 메인메소드가 생성이 된다.

```java
@SpringBootApplication
public class FileDbWorkApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileDbWorkApplication.class, args);
	}

}
```

그 곳에 **`@SpringBootApplication`**이 존재하게 되며 그 안을 확인해보면

```java
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(
    excludeFilters = {@Filter(
    type = FilterType.CUSTOM,
    classes = {TypeExcludeFilter.class}
), @Filter(
    type = FilterType.CUSTOM,
    classes = {AutoConfigurationExcludeFilter.class}
)}
)
public @interface SpringBootApplication {
    ...
```

여러 어노테이션으로 설정되어있는 것을 볼 수 있는데, 여기서 중요한 것은
`@ComponentScan`이랑 `EnableAutoConfiguration`이다.



## @ComponentScan

@SpringBootApplication 어노테이션이 붙은, 즉 컴포넌트 스캔이 선언된 하위 패키지에서

`@Component` `@Configuration` `@Controller` `@RestController` `@Service` `@Repository`

와 같은 Annotation이 선언된 클래스를 스프링 컨테이너의 메모리로 읽어와 애플리케이션에서 사용할 수 있는 Bean 형태로 등록하게 된다.



## @EnableAutoConfiguration

컴포넌트 스캔 이후 EnableAUtoConfiguration으로 추가적인 Bean들을 읽어서 등록한다. 

spring.factories 내부에 여러 Configuration들이 있고, 조건에 따라서 Bean을 등록한다.

```bash
# Auto Configure
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration,\
org.springframework.boot.autoconfigure.aop.AopAutoConfiguration,\
org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration,\
org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration,\
...
org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration,\
...
```

`org.springframework.boot.autoconfigure.EnableAutoConfiguration`라는 Key 값으로 많은 Class를 가지고 있다. 해당 클래스들은 상단에 `@Configuration`이라는 Annotation을 가지고 있어 이러한 키값을 통해 명시된 많은 클래스들이 AutoConfiguration의 대상이 된다.

# Spring Bean LifeCycle

Spring Bean이라고 하는 것은 일반적으로 객체인데, IoC 컨테이너가 관리하는 객체를 **Bean**이라고 한다.

( *`new`로 만드는 객체는 **Bean**이 아니다* )



대략적으로 스프링 빈의 life cycle은 아래와 같다.

```bash
# 객체 생성 > 의존 설정 > 초기화 > 소멸 단계
```

스프링 컨테이너를 초기화 할 때 스프링 컨테이너는 빈 객체를 생성하게 되고 이 시점에 DI를 통한 의존성이 주입된다.

모든 의존 설정이 완료되면 빈 객체를 초기화 하기 위해서 스프링은 빈 객체의 지정된 메서드를 호출하게 된다.

마지막으로 스프링 컨테이너가 `close()` 메서드로 종료될 시점에 컨테이너는 빈 객체의 소멸을 처리하고 이때도 지정된 메서드를 호출하게 된다.



그럼 어떻게 Spring Container안에 특정한 인스턴스를 빈으로 만들 수 있는가?

>   1.  Component-Scan
>   2.  직접 Bean으로 등록

IoC 컨테이너가 사용하는 여러 인터페이스가 있는데, 그런 인터페이스들을 lifecycle callback 이라고 한다.

그 여러 Lifecycle CallBack 중에서는 `@Component`로 선언된 클래스를 빈으로 등록하는 처리기가 등록되어 있다.



그것이 바로 `@SpringBootApplication` 안에서 @ComponentScan이 알려주는 위치의 모든 하위패키지부터 훑어보면서 `@Component`로 등록된 클래스를 빈으로 등록한다!



*특정한 Repository를 상속받을 때 인터페이스 안에서 내부적으로 Bean을 만들어서 관리한다.*



```java
@Configuration
public class SampleConfig {
    
    @Bean
    public SampleController sampleController() {
        return new SampleController();
    }
}
```

`@Configuration`도 Component이기 때문에 Bean으로 등록되고

위와 같이 `@Controller`를 사용하지 않고 직접 Controller를 만들어서 Bean으로 등록할 수 도 있다.



그러면 이제 스프링 빈의 초기화 및 종료 방법 3가지를 알아보겠다.(Spring Bean Lifecycle Callback)

## 1. 인터페이스 활용

`InitializingBean, DisposableBean` 인터페이스를 활용한다.

```java
public class CustomClient implements InitailizingBean, DisposableBean {
    private String url;
    
    public CustomClient() {
        System.out.println("생성자 호출, url: " + url);
    }
	public void setUrl(String url) {
        this.url = url;
    }

    // 애플리케이션 시작 시 호출
	public void connect() {
        System.out.println("연결 url: " + url);
    }
    
    // 서비스 중에 호출
    public void call(String message) {
        System.out.println("url: " + url + "/message = " + message);
    }
    
    // 서비스 종료 시 호출
    public void disconnect() {
        System.out.println("서비스 종료");
    }
    
	// 빈 생성 후 의존관계 주입이 완료되고 호출된다.
	@Override
	public void afterPropertiesSet() throws Exception {
        connect();
    }
    
    // 스프링 컨테이너 종료 직전에 호출된다.
    @Override
    public void destroy() throws Exception {
        disconnect();
    }
}
```

```java
public class BeanTest {
    @Test
    public void beanTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(MyConfig.class);
        CustomClient client = ac.getBean(CustomClient.class);
        ac.close();
    }
    
    @Configuration
    static class MyConfig {
        @Bean
        public CustomClient customClient() {
            CustomClient customClient = new CustomClient();
            customClient.setUrl("http://...");
            return customClient;
        }
    }
}
```



위와 같이 커스텀 빈을 만들고, 이 객체에 `InitializingBean, DisposableBean` 인터페이스를 장착하면 빈 생명주기를 관리해주는 콜백함수들을 사용할 수 있다.

이렇게 작성하고 실제로 Configuration에서 빈을 등록하고 사용하면 의존관계가 주입 된 후에 `afterPropertiesSet()`으로  connect()와 call() 메소드가 호출됨을 확인할 수 있다.



하지만 이처럼 인터페이스로 콜백을 사용하는 방법은 스프링 전용 인터페이스이며, 스프링에 의존적이고 외부 라이브러리에 적용할 수 없다는 단점이 있다.



## 2. 빈 등록 시 초기화/소멸 메서드 등록

Bean을 등록할 때 `@Bean(initMethod = "init", destroyMethod = "close")` 처럼 초기화, 소멸 메서드를 지정할 수 있다.

```java
public class BeanTest {
    @Test
    public void beanTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(MyConfig.class);
        CustomClient client = ac.getBean(CustomClient.class);
        ac.close();
    }
    
    @Configuration
    static class MyConfig {
        // 빈 설정 시 초기화, 소멸 메서드를 지정해준다.
        @Bean(initMethod = "init", destroyMethod = "close")
        public CustomClient customClient() {
            CustomClient customClient = new CustomClient();
            customClient.setUrl("http://...");
            return customClient;
        }
    }
}
```

CustomClient.class 에 지정해줬던 함수명들로 init, close method를 작성한다.

```java
public class CustomClient implements InitailizingBean, DisposableBean {
    /*
    	위는 모두 동일
    */
    
	public void init() throws Exception {
        connect();
    }
    
    public void close() throws Exception {
        disconnect();
    }
}
```

첫 번째 방법과 같은 결과를 도출할 수 있따.



여기서 특이한 점은 Spring이 `@Bean`의 종료메서드에 대해서 임의로 추론해서 자동으로 호출해주는 기능이 있다.

```java
@Bean(initMethod = "init", destroyMethod = "close")
->
@Bean(initMethod = "init")
```

종료메서드 지정을 없어도 같은 기능을 하는 것을 볼 수 가 있는데, 이는 등록된 Bean 안에 일반적으로 많이 쓰는 종료 메서드 들의 이름 (ex. `close`, `shutdown`, ...)들의 메서드가 있으면 스프링이 종료될 때 자동으로 호출해준다! ~~와 신기~~

추론기능을 사용하지 않으려면 `destroyMethod = ""`로 공백을 쓰면 된다.



## 3. Annotation Callback

콜백으로 등록하고 싶은 메소드에 어노테이션을 달아서 Bean의 콜백 메서드로 등록하는 방법이다.

```java
// CustomClient.class

@PostConstruct
public void init() throws Exception {
    connect();
}

@PreDestroy
public void close() throws Exception {
    disconnect();
}

```

CustomClient라는 Bean 안에 init과 close를 콜백으로 등록하고 싶을 때 각각

`@PostConstruct`, `@PreDestroy` 어노테이션을 사용하면 편리하게 초기화와 종료를 실행할 수 있다.



이 방법은 최신 스프링에서 가장 권장하는 방법으로 매우 편리하게 쓸 수 있다.

또한, 스프링에 종속적인 기술이 아니라 자바 표준이므로 다른 컨테이너에서도 동작가능하다.

하지만 외부 라이브러리에 적용하지 못한다는 점이 있는데 이 때는 `@Bean`의 기능을 사용하면 될 것 같다.



참조 : https://chung-develop.tistory.com/55

# Autowired

>   IoC 컨테이너에 등록된 빈을 어떻게 꺼내서 사용할 것인가 ?
>
>   스프링에는 다양한 의존성 주입 방법이 있는데,
>
>   생성자로 직접 주입을 받아도 되지만 `@Autowired`를 사용하면 IoC 컨테이너에 들어있는 Bean을 주입받아서 사용할 수 있다.

Spring 4.3부터 어떠한 클래스에 생성자가 하나 뿐이고, 그 생성자가 주입받는 레퍼런스 변수들이 Bean으로 등록되어 있다면 그 Bean을 자동으로 주입하는 기능으로, `@Autowired`를 생략할 수 있다.



# Lombok

Lombok은 자바의 반복 메서드 작성 코드를 줄여주는 **코드 다이어트 라이브러리**이다.

보통 DTO, Model, Entity의 경우 여러 속성이 존재하고 이들의 프로퍼티에 대해서 Getter, Setter, 생성자 등 매번 작성해줘야 하는 부분을 자동으로 만들어주는 라이브러리이다.

코딩과정에서는 어노테이션만 보이지만, 실제 컴파일된 `.class` 파일에는 연관 코드들이 생성되어 있게 된다.

귀찮은 과정을 줄여주고 반복되는 코드작성을 대신하기 때문에 많은 개발자들이 사용하고 있지만 호불호가 갈리는 라이브러리이기도 하며, 개별 작동방식을 잘 알고 사용하는 것이 좋다.



- **@Getter, @Setter**

  - @Getter와 @Setter를 클래스 이름 위에 적용 시키면 모든 non-static 변수들에 대한 getter, setter 메소드를 사용할 수 있고, 변수이름 위에 따로 사용할 수도 있다.
  - 자동으로 생성되는 메소드의 기본은 public이며 `AccessLevel`을 통해 명시적으로  생성할 수도 있다.
  - 열거형 변수에는 getter를 사용할 수 있지만, setter를 사용할 수 없다.
  - 이름이 같고 매개변수의 수가 같은 메소드가 존재한다면 혼동을 방지하여 메소드가 생성되지 않는다.

- **@NonNull**

  - 메소드나 생성자에 사용하게 되면 null check를 해준다.

- **@AllArgsConstructor**

  - 모든 변수를 사용하는 생성자를 만들어준다.

- **@NoArgsConstructor**

  - 파라미터가 없는 기본 생성자를 만들어준다.

- **@RequiredArgsConstructor**

  - 특정 변수만을 활용하는 생성자를 만들어준다.
  - 초기화 되지 않은 `final 필드나, `@NonNull` 어노테이션이 붙여진 필드에 대해 생성자를 만들어준다.

- **@EqaulsAndHashCode**

  - 클래스에 대한 eqauls 함수와 hashCode 함수를 생성해준다.

  ```java
  @EqualsAndHashCode(of = {"name", "description"}, callSuper = false)
  public class Example extends Common {
      String name;
      String description;
  }
  ```

  - 서로 다른 두 객체에서 특정 변수의 이름이 같은 경우 같은 객체로 판단하고자 할 때 위와 같이 사용한다.
  - `callSuper=false`는 Common을 상속하는데, 이 상위 클래스는 적용시키지 않을 때 사용한다.
  - **`.include`나 `.Exclude`를 활용해서 명시적으로 선택할 수도 있다.**
  - 자기 자신을 포함하는 배열을 가지거나 순환 참조가 존재하는 경우 명시적으로 제외하지 않으면 `StackOverFlowError`가 발생한다.

- **@ToString**

  - 클래스 변수들의 `ToString()` 메소드를 생성해준다.
  - 출력을 원하지 않는 변수는 `@ToString.Exclude` 어노테이션을 붙여주면 제외할 수 있다.
  - `callSuper=true`는 마찬가지로 상위클래스에 대해서 적용하고자 할때 사용된다.
  - **`.include`나 `.Exclude`를 활용해서 명시적으로 선택할 수도 있다.**

- **@Data**

  - `@Getter, @Setter, @RequiredArgsConstructor, @EqaulsAndHashCode, @ToString` 어노테이션을 자동생성한다.

- **@Value**

  - `@Data`의 불변 클래스 버전으로 모든 필드를 `private final`로 만들고 setter는 생성되지 않는다.
  - 클래스 또한 `final`로 만든다.

- **@Builder**

  - 해당 클래스의 객체 생성에 빌더 패턴을 적용시켜준다.
  - 모든 변수들에 대해 빌더 패턴을 적용하려면 클래스 위에 어노테이션을 붙이고, 특정 변수만 하고 싶은 경우는 원하는 생성자를 생성하고 생성자 위에 어노테이션을 붙여준다.
  - 오직 `@NoArgsConstructor`와 함께 사용하면 컴파일 에러가 난다. **빌더 패턴을 위한 모든 프로퍼티를 필요로 하기 때문에 `@AllArgsConstructor`도 반드시 함께 사용해야 한다.**
  - ~~모든 생성자를 직접 만들어도 된다~~

- **@Delegate**

  - 한 객체의 메소드를 다른 객체로 위임한다.
  - ~~음.. 잘 쓰지 않는 것 같다~~

- **@Log4j2**

  - 해당 클래스의 로그 클래스를 자동완성 시켜준다.

- **@CleanUp**

  - close()를 호출해준다.



# RestController

전통적인 Spring MVC에서의 `@Controller`는 주로 `View`를 반환하기 위한 용도로 사용되었다. MVC 컨테이너는 Client의 요청으로부터 View를 반환한다.

Spring MVC의 `@Controller`에서 데이터를 반환하기 위해서는 컨트롤러의 각 메소드에 `@ResponseBody` 어노테이션을 사용하면 JSON 형태로 데이터를 반환할 수 있다. 이 때 `ViewResolver`를 통해 데이터를 담을 View를 찾는 과정을 거치게 된다.



Spring 4.0에서 Spring 프레임워크에서 `RESTful` 웹 서비스를 쉽게 개발할 수 있도록 `@RestController`라는 것이 추가됨. 



**`RestController`는 `@Controller`와 `@ResponseBody`의 조합으로 단순히 객체만을 반환하고 객체 데이터는 XML / JSON 형식으로 HTTP 응담에 담아서 전송된다.**



## - HttpMessageConverter

>  스프링 프레임워크에서 제공하는 인터페이스 中

HTTP 요청의 본문을 객체로 변경하건, 객체를 HTTP 응답 본문으로 변경할 때 사용된다.

뷰가 아니라 객체를 응답할 때는 viewResolver 대신에 **HttpMessageConverter**가 동작하는데, **HttpMessageConverter**에는 여러 Converter가 등록되어 있고 반환하는 데이터에 따라 사용되는 Converter가 달라진다는 특징이 있다. 

리턴 타입이 `application/json`인 경우에는 **MappingJacson2HttpMessageConverter**가 사용되고, 클라이언트의 Http Accept 헤더와 서버의 컨트롤러 return type 정보를 좋바해 적절한 HttpMessageConverter가 채택된다.

그냥 `@Controller`를 사용할 때는 `@ResponseBody`를 넣어줘야 MessageConverter가 적용되고, 선언하지 않으면 **BeanNameViewResolver**에 의해서 viewName에 해당하는 뷰를 찾으려고 할 것이다.

>   즉, 클라이언트의 요청이 들어오면 디스패처 서블릿에 의해서 선택된 컨트롤러가 api를 실행하고 이 때 `@ResponseBody` 어노테이션이 선언되어있으면 Obejct 값을 Body에 넘겨주기 위해서 HttpMessageConverter가 사용된다. 이 때는 반환값에 따라서 각기 다른 Converter가 사용됩니다.



# ResponseEntity

먼저 Spring framework에 **`HttpEntity`**라는 클래스가 존재한다.

이 클래스는 **HTTP 요청(request)이나 응답(response)에 해당하는 HttpHeader와 HttpBody를 포함하는 클래스**이다.

```java
public class HttpEntity<T> {
    private final HttpHeaders = headers;
    
    @Nullable
    private final T body;
}
```

```java
public class RequestEntity<T> extends HttpEntity<T>
public class ResponseEntity<T> extends HttpEntity<T>
```

RequestEntity와 ResponseEntity는 이렇게 HttpEntity 클래스를 상속받아 구현한 클래스이다.

`ResponseEntity`는 사용자의 HttpRequest에 대한 응답 데이터를 포함하는 클래스이다. 따라서, **HttpStatus, HttpHeaders, HttpBody를 포함**한다.

`ResponseEntity`의 생성자는 this()를 통해서 매개변수가 3개인 생성자로 들어간다.

```java
public ResponseEntity(HttpStatus status) {
    this(null, null, status);
}
```

```java
public ResponseEntity(@Nullable T body, HttpStatus status) {
    this(body, null, status);
}
```



또한 상태코드(Status), 헤더(headers), 응답데이터(ResponseData)를 담는 생성자도 존재한다.

```java
public class ResponseEntity<T> extends HttpEntity<T> {
    public ResponseEntity(@Nullabe T body, @Nullable MultiValueMap<String, String> headers,
                         HttpStatus status) {
        super(body, headers);
        Assert.notNull(status, "HttpStatus must not be null");
        this.status = status;
    }
}
```





클라이언트에게 응답을 보내는 예제를 만들어보자.

```java
@RestController
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserController {
    private final UserService userservice;
    
    @GetMapping("/api/user/{id}")
    public ResponseEntity<Message> findById(@PathVariable int id) {
        User user = userservice.findById(id);
        Message message = new Message();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MeidaType("application", "json", Charset.forName("UTF-8")));
        
        message.setStatus(StatusEnum.OK);
        message.setMessage("성공했어요");
        message.setData(user);
        
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
}
```



REST API 개발 시 ResponseEntity의 값(헤더, 상태코드)들을 적절히 활용해보자.



# RestTemplate

> 스프링에서 제공하는 HTTP 통신에서 유용하게 쓸 수 있는 템플릿
>
> *Spring 3.xx부터 추가되었고 REST API 호출 이후 응답을 받을 때까지 기다리는 동기 방식이다*
>
> - **HttpClient** : HTTP를 사용해 통신하는 범용 라이브러리
> - RestTemplate은 HttpClient를 추상화 해서 제공한다.

## > RestTemplate 메서드

![image-20210924155919175](https://user-images.githubusercontent.com/58545240/134637515-c53b82a0-fb24-4181-97af-2771164e94d5.png)

- `getForObject()`

  ```java
  Product product = restTemplate.getForObject(BASE_URL + "/{id}", Prodcut.class);
  ```

  Prodcut로의 매핑은 기본적으로 **jackson-databind**가 담당한다.

- `getForEntity()`

  - 응답을 `ResponseEntity` 객체로 받는다.
  - getForObject()와 달리 HTTP 응답에 대한 추가 정보를 담고 있어 GET 요청에 대한 응답 코드, 실제 데이터를 확인할 수 있다.
  - 또, ResponseEntity<T> 제네릭 타입으로 응답을 String이나 Object 객체로 유연하게 받는 것이 가능하다.

  ```java
  ResponseEntity<String> responseEntity = restTemplate.getForEntity(BASE_URL + "/{id}", String.class, 25);
  log.info("statusCode: {}", responseEntity.getStatusCode());
  log.info("getBody: {}", responseEntity.getBody());
  ```

  - getForEntity()에 여러 값을 담을 params를 같이 넘겨줄 수 있다. **LinkedMultiValueMap** 객체에 담아서 parmas로 넘겨줄 수 있다.

  ```java
  MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
  params.add("name", "book");
  params.add("description", "best-seller");
  
  ResponseEntity<Product> responseEntity = restTemplate.getForEntity(BASE_URL + "/{name}/{description}", Product.class, params);
  log.info("statusCode: {}", responseEntity.getStatusCode());
  log.info("getBody: {}", responseEntity.getBody());
  ```

- get 요청에 header가 필요한 경우

  - get Method에서는 header를 추가할 수가 없으므로 **exchange** method를 사용해야 한다.

  ```java
  HttpHeaders headers = new HttpHeaders();
  headers.set("header", header);
  headers.set("header2", header2);
  
  HttpEntity request = new HttpEntity(headers);
  
  ResponseEntity<String> response = restTemplate.exchange(
  	URL_PATH,
      HttpMethod.GET,
      request,
      String.class
  );
  ```

- get 요청에 header 값과 쿼리 스트링(query String, param)이 필요한 경우

  - post처럼 HttpEntity에 넣어서 요청할 수가 없다.

  ```java
  HttpHeaders headers = new HttpHeaders();
  headers.set("header", header);
  headers.set("header2", header2);
  
  HttpEntity request = new HttpEntity(headers);
  
  UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(URL_PATH).queryParam("keywords", "11");
  
  ResopnseEntity<String> response = resTemplate.exchange(
  	uriBuilder.toUriString(),
      HttpMethod.GET,
      request,
      String.class
  );
  ```

  - 이렇게 **UriBuilder**를 사용해서 넣는 수 밖에 없다.
  - post방식과 달리 httpEntity에 같이 넣거나 exchange의 parameter로 넘길 수 가 없다.
  - 굳이 uriBuilder를 쓰지 않고 map에 파라미터를 추가하고 map을 parameter 문자열로 변환해주는 메소드를 만들어서 사용하는 것이 편할 수 있다.

  ```java
  HttpHeaders headers = new HttpHeaders();
  headers.set("header", header); 
  headers.set("header2", header2);
  
  HttpEntity request = new HttpEntity(headers);
  
  Map<String, String> params = new HashMap<String, String>();
  params.put("query1", "test1");
  params.put("query2", "test2");
  
  ResponseEntity<String> response = restTemplate.exchange(
  	URL_PATH + "?" + this.mapToUriParam(params),
      HttpMethod.GET,
      request,
      String.class
  );
  
  ...
      
  private static String mapToUriParam(Map<String, Object> params) {
      StringBuffer paramData = new StringBuffer();
      for (Map.Entry<String, Object> param : params.entrySet()) {
          if (paramData.length() != 0) {
              paramData.append("&");
          }
          paramData.append(param.getKey());
          paramData.append("=");
          paramData.append(String.valueOf(param.getValue()));
      }
      return paramData.toString();
  }
  
  ```

- postForObject() Method에 header 값이 없는 경우

  ```java
  Product newProduct = Product.builder()
      .name("book")
      .description("best-seller").build();
  
  Product product = restTemplate.postForObject(BASE_URL + "/product", newProduct, Prodcut.class);
  ```

- postForObject() Method에 header 포함해서 보내기

  ```java
  Prodcut newProduct = Product.builder()
      .name("book")
      .description("best-seller").build();
  
  HttpHeaders headers = new HttpHeaders();
  headers.set("headerTest", "headerValue");
  
  HttpEntity<Product> request = new HttpEntity<newProduct, headers);
  
  Product product = restTemplate.postForObject(BASE_URL + "/product", request, Product.class);
  ```

- post에 form data를 사용

  ```java
  HttpHeaders headers = new HttpHeaders();
  headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
  
  MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
  
  HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
  
  ResponseEntity<String> response = restTemplate.postForEntity(BASE_URL + "/form", request, String.class);
  ```

- TimeOut 설정하기

  - timeOut을 설정하려면 ClientHttpRequestFactory와 같은 팩토리 메소드를 만들고 RestTemplate의 생성자에 추가해야 한다.

  ```java
  RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
  
  private ClientHttpRequestFactory getClientHttpRequestFactory() {
      int timeout = 5000;
      HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
      clientHttpRequestFactory.setConnetTimeout(timeout);
      
      return clientHttpRequestFactory;
  }
  // timeout = 0 : 무제한 설정
  ```

- Execute()

  - `Execute()`는 콜백을 통해 요청 준비와 응답 추출을 완벽하게 제어하여 요청을 수행하는 가장 일반적인 메서드를 ResTemplate에서 제공한다.
  - getForObejct()나 postForObject() 등은 `execute()`를 내부적으로 호출한다.

## > RestTemplate의 동작원리

![image-20210924160054392](https://user-images.githubusercontent.com/58545240/134637214-58987cf3-9cb2-4df8-882e-849f351e9a45.png)

1. 어플리케이션이 RestTemplate을 생성하고, URI, HTTP 메소드 등의 헤더를 담아 요청한다.
2. RestTemplate은 HttpMessageConverter를 사용해 requestEntity를 요청 메세지로 변환한다.
3. RestTemplate은 ClientHttpRequestFactory로부터 ClientHttpRequest를 가져와서 요청을 보낸다.
4. ClientHttpRequest는 요청 메시지를 만들어서 HTTP 프로토콜을 통해 서버와 통신한다.
5. restTemplate은 ResponseErrorHandler로 오류를 확인하고 있다면 처리로직을 태운다.
6. ResponseErrorHandler는 오류가 있다면 ClientHttpResponse 에서 응답데이터를 가져와서 처리한다.
7. RestTemplate은 HttpMessageConverter를 이용해 응답메세지를 Java Object(Class `ResponseType`)로 변환한다.
8. 어플리케이션에 반환한다.



*참조*

https://velog.io/@soosungp33/%EC%8A%A4%ED%94%84%EB%A7%81-RestTemplate-%EC%A0%95%EB%A6%AC%EC%9A%94%EC%B2%AD-%ED%95%A8

https://juntcom.tistory.com/141

# Entity에 관하여

>   엔티티 클래스는 테이블과 매핑되어 사용되는 클래스이다.
>
>   `@Entity` 어노테이션으로 JPA에게 테이블과 매핑될 클래스이니 관리 할 것임을 알리고
>
>   `@Table(name = 'product')`로 DB와 매핑될 테이블 네임을 지정한다. class name과 table name이 같다면 생략해도 된다.
>
>   `@Column(name = 'id')`도 마찬가지로 DB의 컬럼과 매핑될 필드로 name이 같다면 생략해도 된다.
>
>   
>
>   *++ 일반적으로 id를 `Long`타입의 래퍼클래스로 받는 이유?*
>   : long값의 경우 기본값으로 0이 데이터베이스에 들어갈 수 있는데, 기존 데이터베이스에도 id가 0값으로 들어있다면 이게 기존데이터인지 추가된 데이터인지 구분이 어렵다.
>   따라서, `Long`타입으로 하면 null로 들어가므로 구분이 쉬워진다.

## > Entity의 상태

엔티티 클래스를 바탕으로 생성된 엔티티는 생성부터 소멸까지 총 4가지의 상태를 가지며, 데이터의 입출력 즉 트랜잭션과 연관이 깊다.

>   -   New / 비영속
>   -   Managed / 영속
>   -   Detached / 준영속
>   -   Removed / 삭제

### - NEW / 비영속

엔티티를 생성한 시점부터 트랜잭션 구간에 진입하기 전까지, 엔티티는 비영속 상태이다.

**이 상태에서 엔티티는 데이터베이스와 전혀 관계가 없고 JPA의 어떤 특징도 보이지 않는 평범한 객체**이다.

```java
Product prodcut = new Product();
```



### - MANAGED / 영속

엔티티가 트랜잭션 영역에 진입해서 엔티티 매니저의 관리 하에 들어가면  해당 트랜잭션 구간동안 엔티티는 영속 상태가 된다.

```java
em.persist(product)
```

`persist()` method는 주로 JPARepository에서 save시 일어나게 된다.



엔티티는 이 영속 상태에서 몇 가지 중요한 특징을 가지게 된다.

-   **1차 캐시 사용**
-   **같은 키(식별값)를 사용하는 여러 객체의 동일성 보장**
-   **쓰기 지연과, 변경감지**

이에 대한 자세한 내용은 아래에서 다룬다.



### - DETACHED / 준영속

엔티티가 커밋되어 트랜잭션 구간에서 빠져나오는 경우, 아 엔티티는 준영속 상태가 된다.

비영속 상태와 거의 같지만, **영속 상태를 한 번 거쳤기 때문에, 준영속 상태의 엔티티는 식별값을 가지고 있다.**

이렇게 한번 DB에 저장된 기존 식별자(`ID`)를 갖는 엔티티는 영속성 컨텍스트가 더 이상 관리되지 않고, 따라서 변경 감지도 적용되지 않는다.



이러한 준영속 상태의 엔티티를 수정하는 방법은 2가지가 있다.

>   1.  변경 감지(dirty checking)
>   2.  병합 (merge)
>
>   
>
>   **변경 감지 사용**
>
>   ```java
>   // 변경감지 기능 사용 예시
>   // 병합기능(merge)의 단점은 엔티티의 모든 속성이 변경되기 때문에 null이 잘못 들어 갈 수가 있다는 것
>   // 실무는 복잡하기 때문에 가급적이면 병합기능를 쓰지 않고, 조금 귀찮더라도 변경감지 기능을 이용하는 것이 좋다.
>   @Transactional
>   public void updateItem(Long itemId, String name, int price, int stockQuantity) {
>       // 현재 findItem은 Transaction 영역에 진입했으므로 영속 상태이다. 따라서. @Transactional에 의해서 commit이 되고 JPA에서 flush가 되서, 변경감지가 일어나서 바뀐 값을 업데이트 쿼리를 날려서 업데이트가 된다.
>       Item findItem = itemRepository.findOne(itemId);
>   
>       findItem.setName(name);
>       findItem.setPrice(price);
>       findItem.setStockQuantity(stockQuantity);
>   }
>   ```
>
>   EntityManager로 Entity를 직접 꺼내서 원하는 값을 수정한다.
>
>   이렇게 하면 자동으로 dirtyChecking이 일어난다.
>
>   
>
>   위와 같이 단발성으로 업데이트(`setMethod()`)를 하는 것 보다는 의미 있는 `method`를 만들어서 사용하는 것이 역추적이 좋고 유지보수에 편하다.
>
>   *ex. findItem.change(price, name, stockQuantity)*
>
>   
>
>   **병합 사용**
>
>   ```java
>   // 병합 기능 사용 예시
>   ...
>       
>   private fianl EntityManager em;
>   
>   public void save(Item item) {
>       if (item.getId() == null) {
>           em.persist(item);
>       } else {
>           // 여기서 param으로 넘어온 item은 영속성 컨텍스트가 아니고
>           // merge후 반환된 아이가 영속성 컨텍스트이므로 나중에 Item을 쓰려면 쓸려면 얘를 활용해야 한다.
>           Item merge = em.merge(item);
>       }
>   }
>   ```
>
>   Item을 저장할 때, `id`를 직접 설정되지 않았다면 새로 생성하는 것이므로 `persist()`를, `id`를 설정된 객체라면 이미 있는 엔티티를 수정하는 것으로 알고 `merge()`를 호출하는 식으로 사용된다.
>
>   여기서는 준영속 상태의 item 자체를 영속성 컨텍스트에 넣는 것이 아니라, 준영속 객체의 식별자와 일치하는 엔티티를 DB에서 가져와 값을 대입받고, 그에 해당 하는 엔티티를 반환하는 것이다.
>
>   또, **`merge()`는 엔티티의 모든 필드를 그대로 변경하기 때문에 item의 name만 변경하고자 셋팅후 merge()한다면 나머지 필드는 기존 값을 잃어버리고 null값이 대입된다.**
>
>   **따라서 실무에서는 가급적 `em.find()`로 엔티티를 가져와서 직접 값을 수정하는 변경감지 기법을 이용하는 것이 권장된다.**



### - REMOVED / 삭제

엔티티가 트랜잭션 구간 내에서 관련 메서드에 의해 삭제되는 경우, 매핑되는 데이터의 삭제와 함께 엔티티 또한 삭제 상태가 된다.

객체는 사용 가능한 상태이지만, 재활용 하지 않는 편이 좋다.



## > Entity Manager

>   엔티티의 저장, 수정, 조회, 삭제 와 같이 엔티티를 관리하는 객체이다.
>
>   *Thread-Safe한 구조이다.*

매니저의 책임이 전부 영속성 엔티티의 CRUD와 관련이 있다. 

엔티티 매니저는 영속성 상태의 엔티티 관리를 위해서 DB 세션과 밀접한 연관을 가지고 있기 때문에, 하나의 엔티티를 여러 스레드에서 공유하여 사용하면 위험하다.

Thread-Safe한 엔티티 매니저 팩토리를 공유해 각 스레드에서 엔티티 매니저를 생성하는 방식이 권장된다.

-   Entity Manager Factory로부터 Entity Manager를 생성
-   Entity Manager는 DB의 Connection Pool로부터 커넥션 획득
-   획득한 Connection을 통해서 엔티티의 CRUD를 관리



## > Entity Manager와 영속성 컨텍스트

Entity Manager에 의해 관리되는 영속성(`MANAGED`) 상태의 엔티티는 고유한 식별값(`ID`)로 구분되어 관리된다.

즉, 영속성 상태에 있는 모든 엔티티는 식별값을 가지고 있어야 한다.

영속성 상태에 있는 엔티티는 아래의 특징을 가진다.



### - 1차 캐시 / 엔티티의 동일성 보장

-   영속성 컨텍스트는 내부에 캐시를 갖고 있고, `(id, instatnce)`의 `map` 형태로 Entity가 저장된다.
-   데이터베이스에 읽은 이력이 있는 데이터는 이 1차 캐시에 저장되어 재사용된다.
-   트랜잭션 단위의 굉장히 짧은 메모리 공간이다.

![image-20210925174709866](https://user-images.githubusercontent.com/58545240/134795582-c88fc046-aaaf-4f5d-9433-6d4514518557.png)

-   `em.persist(member)`로 member가 영속성 컨텍스트에 영속되면, 1차 캐시는 이를 담는다.
-   이후에 조회 시에 DB에 접근해서 member1을 찾는 것이 아니라, 1차 캐시를 먼저 훑어서 member1을 바로 찾을 수 있고, 캐시에 없다면 DB에서 검색 후 해당 객체를 1차 캐시에 저장하고 반환한다.
-   이렇게 1차캐시를 거친 조회로 엔티티의 동일성이 보장되는 것이다.



### - 쓰기 지연

```java
transaction.begin();

em.persist(member1);
em.persist(member2);

// -------1-------
em.flush();
// -------2-------

transaction.commit();
```

-   1의 영역에서 member insert query를 바로 보내는 것이 아니라, 해당 쿼리를 바로 전송할 수도, 원하는 시점으로 지연시킬 수도 있다.

-   이는 **쓰기 지연 SQL 버퍼**에 쿼리를 담아뒀다가, 영속성 컨텍스트의 명령(트랜잭션이 종료되는 시점)에 따라 쿼리들이 DB에 전송되기 때문이다.

    

    1.  그 과정은, member1이 컨텍스트에 영속되면, 우선 1차 캐시로 들어가고 쿼리는 SQL 버퍼에 들어간다.

    2.  member2가 따라서 영속되면 마찬가지로 1차 캐시로 들어가고 쿼리는 SQL 버퍼에 들어간다.

        ![image-20210925183024178](https://user-images.githubusercontent.com/58545240/134795590-2edf3d5d-b397-4bfc-9ff2-f419b1d4570e.png)

    3.  이후에, transaction이 `commit()` 메소드 호출 시 컨텍스트에 버퍼를 비우도록 명령하는 `flush()`메소드를 호출하면서 그 때 버퍼에 있던 쿼리들이 DB에 넘어가서 쿼리를 반영한다.

        ![image-20210925183037506](https://user-images.githubusercontent.com/58545240/134795599-9efefee5-a295-431c-8b62-84e64c051e3e.png)

    

    

### - 지연 로딩

>   JPA에서 테이블 간의 연관관계는 객체간의 참조를 통해 이루어진다.
>
>   서비스가 커질수록 참조하는 개체가 많아지고, 객체가 가지는 데이터의 양이 많아지게 된다.
>
>   이렇게 객체가 커질수록 DB로부터 참조하는 객체들의 데이터까지 한꺼번에 가져오는 행동은 부담이 되기 때문에, JPA는 참조하는 객체들의 데이터를 가져오는 시점을 정할 수가 있는데 이를 **Fetch Type**이라고 한다.
>
>   Fetch Type 에는 두 가지가 있다.
>
>   -   **`EAGER`**
>       -   성실한, 열심인
>       -   말 그대로 데이터를 가져오는 데 성실하기 때문에 하나의 객체를 DB로부터 참조 객체들의 데이터까지 전부 읽어오는 방식이다.
>   -   **`LAZY`**
>       -   게으른
>       -   말 그대로 참조 객체들의 데이터들은 무시하고 해당 엔티티의 데이터만을 가져온다.
>
>   테이블 설계가 복잡해질 수록 하나의 엔티티가 참조하는 테이블들은 점점 증가하고, 그에 따른 쿼리문도 굉장히 길어진다.
>
>   이렇게 복잡한 쿼리문을 본 개발자들은 해당 도메인이 어떻게 설계되었는지 확인해봐야 하고, 논리적인 레이어의 분리가 어렵게 된다.
>
>   따라서 `EAGER` 타입은 유지보수를 힘들게 할 수 있으며, 예상치 못한 SQL이 발생할 수 있다.

지연로딩은 엔티티 조회 시점이 아닌 엔티티 내 연관관계를 참조할 때 해당 연관관계에 대한 SQL이 질의되는 기능이며 `fetch = FetchType.LAZY` 옵션으로 설정한다.

엔티티 조회 시 연관관계 필드는 프록시 객체로 제공된다.

```java
Member member = EntityManagter.find(Member.class, 1L);

member.getTeam(); // 프록시 객체 초기화 X

member.getTeam().getclass(); // 프록시 객체

member.getTeam().getName(); // 프록시 객체 초기화 및 SQL 질의
```

위 코드와 같이 지연로딩 되는 연관관계를 참조하기 전까지는 프록시 객체가 초기화되지 않고, 프록시 객체를 참조할 때 프록시 객체가 초기화 되고 SQL이 질의된다.

`LAZY` 옵션을 사용해 엔티티와 관련 있는 **데이터의 로드를 해당 데이터가 필요한 시점까지 지연시킬 수 있다**는 장점이 있다.



### - 변경감지

-   트랜잭션 종료 시점에 Entity Manager는 엔티티의 변경사항을 감지해 데이터 베이스에 업데이트 한다.
-   SQL이 flush 되기 전에 SQL 버퍼에 저장될 당시의 객체값(스냅샷 값)과 엔티티의 현재 값을 비교해서 수정이 있을 경우 SQL을 일괄 변경하여 DB에 업데이트 한다.



참조

https://awayday.github.io/2017-04-30/jpa-and-entity/

https://ecsimsw.tistory.com/entry/JPA-%EC%98%81%EC%86%8D%EC%84%B1-%EC%BB%A8%ED%85%8D%EC%8A%A4%ED%8A%B8-1%EC%B0%A8-%EC%BA%90%EC%8B%9C-%EC%93%B0%EA%B8%B0-%EC%A7%80%EC%97%B0

# JpaRepository

Spring-Data-Jpa에서는 반복되는 코드없이 쉽게 JPA Repository를 만들 수 있다. `extends JpaRespository<Product, Long>`으로 인터페이스를 상속하고 커스텀이 필요한 메소드는 오버라이딩하면 된다.

스프링의 변경감지는 `EntityManger`별로 수행한다.

**같은 쓰레드에서 Spring-Data가 제공하는 Repository들은 하나의 EntityManager를 공유한다. 그래서 하나의 컨테이너에서 여러 Repository가 사용하는 EntityManager는 동일하다.**



## 트랜잭션 커밋은 어디에서 일어날까? 

레파지토리를 만들 때 `Spring-Data-Jpa`의 `JpaRepository` 인터페이스를 상속하였는데, 스프링 데이터에서 기본 구현체를 제공해주기 때문이다.

`Spring-Data-Jpa`에서 제공하는 `JpaRepository`의 기본 구현체는 `SimpleJpaRepository`이다. 

(`CrudRepository<>`는 단순히 인터페이스이다.)

`SimpleJpaRepository`의 `save()`메소드에는 스프링 **`@Transactional`**이 붙어있으므로 해당 클래스에 있는 수많은 메소드에 트랜잭션이 걸리게 되고, 메소드 성공적으로 return하게 되면 commit도 이루어지게 되는것이다.

```java
@Transactional
public <S extends T> S save(S entity) {
    Assert.notNull(entity, "Entity must not be null.");
    if (this.entityInformation.isNew(entity)) {
        this.em.persist(entity);
        return entity;
    } else {
        return this.em.merge(entity);
    }
}
```

따라서, `save()` 메소드와 같이 CRUD `method()`를 수행하는 시점이 트랜잭션의 시작 과 종료 커밋 시점임을 알 수 있다.

# AOP를 활용한 REST API의 Error Handling

SpringBoot에서 기본적으로 오류처리에 대한 동작 흐름에 대해 알아보자.



SpringBoot는 모든 오류를 적절한 방식으로 처리하며 `/error`로 매핑하는 전역 오류 페이지 등록을 제공한다.

또, http 상태와 예외에 대한 메시지를 JSON으로 응답하거나 html 형식으로 렌더링 하는 `whitelabel` 페이지 뷰를 제공한다.



## BasicErrorController

>   SpringBoot의 기본 오류 처리

SpringBoot는 오류가 발생하면 `server.error.path`에 설정된 경로에서 요청을 처리하게 된다.

기본적으로 BasicErrorController가 등록이 되어 있어서 해당 요청을 처리하게 된다.

```java
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}") // 1)
public class BasicErrorController extends AbstractErrorController {

  @Override
  public String getErrorPath() {
    return this.errorProperties.getPath();
  }

  @RequestMapping(produces = MediaType.TEXT_HTML_VALUE) // 2)
  public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
            
    HttpStatus status = getStatus(request);
    Map<String, Object> model =
      getErrorAttributes(request, isIncludeStackTrace(request, MediaType.TEXT_HTML)));
		
    response.setStatus(status.value());
    ModelAndView modelAndView = resolveErrorView(request, response, status, model);
    return (modelAndView != null) ? modelAndView : new ModelAndView("error", model);
  }

  @RequestMapping // 3)
  public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
    
    // 4)
    Map<String, Object> body =
      getErrorAttributes(request, isIncludeStackTrace(request, MediaType.ALL));
    HttpStatus status = getStatus(request);
    return new ResponseEntity<>(body, status);
  }    
}
```

>   -   `1` : Spring 환경 내에 `server.error.path` 혹은 `error.path`로 등록된 property의 값을 넣거나, 없는 경우 `/error`를 사용
>   -   `2` : HTML로 응답을 주는경우 `errorHtml`에서 응답을 처리
>   -   `3` : HTML 외 응답이 필요한 경우 `error`에서 처리
>   -   `4` : 실직적으로 view에 보낼 모델을 생성

따라서 **`BasicErrorController`에서는 HTML 요청, 그 외의 요청을 나누어서 처리할 핸들러를 등록하고 `getErrorAttributes`를 통해 응답을 위한 모델을 생성한다.**



## AbstractErrorController

`getErrorAttributes`는 `BasicErrorController`의 상위 클래스인 `AbstractErrorController`에 구현되어 있다.

```java
public abstract class AbstractErrorController implements ErrorController {
    private final ErrorAttributes errorAttributes;
    
    protected Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        WebRequest webRequest = new ServletWebRequest(request);
        return this.errorAttributes.getErrorAttributes(webRequest, includeStackTrace);
    }
}
```

`ErrorAttributes` 인터페이스의 `getErrorAttributes`를 호출한다. (위임자 패턴)

별도로 `ErrorAttributes`를 등록하지 않았다면 Spring Boot는 `DefaultErrorAttributes`를 사용한다.

```java
public interface ErrorAttributes {
    // 요청을 기반으로 모델 생성
    Map<String, Obejct> getErrorAttributes(WebRequest webRequest, boolean includeStactTrace);
    // 요청에서 Throwable 획득
    Throwable getError(WebRequest webRequest);
}

public DefaultErrorAttributes {
    // 생성자 및 메서드
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest request, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = new LinkedHashMap<>();
        errorAttributes.put("timestamp", new Date()); // timestamp 생성
        addStatus(errorAttributes, request); // status 생성
        addErrorDetails(errorAttributes, request, includeStackTrace); // 오류 상세 내용 생성
        addPath(errorAttributes, request); // path 생성
        return errorAttributes;
    }
}
```

 `ErrorAttributes`에서 가져온 모델로 Response를 생성한다.

```json
{
    "timestamp": "2021-09-25T04:24:11.447+0000",
    "status": 404,
    "error": "Not Found",
    "message": "No message available",
    "path": "/mypath"
}
```



## ErrorAttributes

여기서 `ErrorAttributes`는 오류가 발생했을 때 응답을 내려줄 모델을 생성하는데 여기서 우리는 이 `ErrorAttributes` 인터페이스를 마음껏 구현할 수 가 있다. (Spring에서 제공하는 확장 포인트이다!)

개발자가 `ErrorAttributes`를 별도로 구현하여 bean으로 등록하면 `BasicErrorController`는 해당 `ErrorAttributes`를 사용한다.

임의로 모델에 `"greeting" : "HelloWorld"`를 추가한 예제이다.

```java
@Component
public class CustromErrorAttributes extends DefaultErrorAttributes {
    
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> result = super.getErrorAttributes(webRequest, includeStackTrace);
        result.put("greeting", "HelloWorld");
        return result;
    }
}
```

```json
{
    "timestamp": "2021-09-25T04:24:11.447+0000",
    "status": 404,
    "error": "Not Found",
    "message": "No message available",
    "path": "/mypath",
    "greeting": "HelloWorld"
}
```



`ErrorAttributes`와 마찬가지로 `ErrorController`의 구현체를 개발자가 직접 bean으로 등록한다면, SpringBoot는 해당 Bean을 먼저 찾아서 `BasicErrorController` 대신 오류 처리를 위해 사용하게 된다.

위임자 패턴을 사용해서 기본적인 처리는 `BasicErrorController`에게 위임하고, 나머지 필요한 처리를 추가할 수 있다.

```java
@Slf4j
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class CustomErrorController extends BasicErrorController {

    public CustomErrorController(ErrorAttributes errorAttributes,
                                 ServerProperties serverProperties,
                                 List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, serverProperties.getError(), errorViewResolvers);
    }

    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView errorHtml(HttpServletRequest request,
                                  HttpServletResponse response) {
        log(request); // 로그 추가
        return super.errorHtml(request, response);
    }

    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        log(request);
        return super.error(request);
    }

    private void log(HttpServletRequest request) {
        log.error("error");
    }
}
```



### > `ErrorController`의 호출 흐름

1.  서블릿 컨테이너(ex. `tomcat`)에서 등록된 서블릿에서 요청을 처리하다가
2.  오류가 발생해서
3.  해당 서블릿에서 처리하지 못하고 서블릿 컨테이너까지 오류가 전파 되었을때 (`SevletException`으로 래핑된다)
4.  서블릿 컨테이너가 오류를 처리하기 위해 특정 경로(`server.error.path`)로 해당 요청처리를 위임 (`ErrorController`를 호출한다)



## @ExceptionHandler

스프링에서는 발생한 Exception을 기반으로 오류를 처리할 수 있도록 `@ExceptionHandler`를 제공한다.

```java
@RestController
@RequestMapping("/products") {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    pubilc Map<String, String> handle(ProductNotFoundException e) {
        log.error(e.getMessage(), e);
        Map<String, String> errorAttributes = new HashMap<>();
        errorAttributes.put("code", "PRODUCT_NOT_FOUND");
        errorAttributes.put("message", e.getMessage());
        return errorAttributes;
    }
}
```

특정 컨트롤러에서 예외가 발생한 경우, Spring은 `@ExceptionHandler`를 검색해서 해당 Annotation에 선언된 예외 및 하위 예외에 대해서 특정 메서드가 처리할 수 있도록 한다.

보통의 핸들러와 마찬가지로 `ModelAndView`나 `String`을 반환해 View를 Resolve할 수 있고, `ResponseEntity<T>`를 반환할 수도 있다.



## @ControllerAdvice

Spring에서는 Bean으로 등록되는 `@Controller`들을 선택적으로, 혹은 전역으로 공통 설정을 적용할 수 있는 `@ControllerAdvice`를 사용할 수 있다.

이 `@ControllerAdvice`에서 사용할 수 있는 것 중 하나가 `@ExceptionHandler`이다.

```java
@Slf4j
@ControllerAdvice
public class GlobalControllerAdvice {
    
    @ReponseStatus(HttpSTatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public Object handle(ProdcutNotFoundException e, HttpServletRequest request) {
        if (...) {
            return makeJson(e);
        } else {
            return "/error/404";
        }
    }
}
```

위의 예제는 하나의 method에서 JSON응답과 HTML 응답을 나누었는데,

HTML view를 사용할 경우와 JSON view를 사용할 경우를 나누어서 `ControllerAdvice`를 등록하고 `@Order`를 사용해 우선순위를 부여하면 분기처리 없이 나누어서 오류를 처리할 수 있다.

```java
@Slf4j
@Order(ORDER)
@RestControllerAdvice(annotations = RestController.class)
public class GlobalRestControllerAdvice {
    public static final int ORDER = 0;
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public Map<String, String> handle(ProductNotFoundException e) {
        log.error(e.getMessage(), e);
        Map<String, String> errorAttributes = new HashMap<>();
        errorAttributes.put("code", "BOARD_NOT_FOUND");
        errorAttributes.put("message", e.getMessage());
        return errorAttributes;
    }
}

@Slf4j
@Order(GlobalRestControllerAdvice.ORDER + 1)
@ControllerAdvice
public class GlobalHtmlControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public String handle(ProductNotFoundException e, Model model, HttpServletRequest request) {
        log.error(e.getMessage(), e);
        model.addAttribute("timestamp", LocalDateTime.now());
        model.addAttribute("error", "BOARD_NOT_FOUND");
        model.addAttribute("path", request.getRequestURI());
        model.addAttribute("message", e.getMessage());
        return "/error/404";
    }
}
```



## ResponseEntityExceptionHandler

`ControllerAdvice`를 사용해서 Exception처리를 한 곳으로 모으는 경우에는 `ResponseEntityExceptionHandler`를 상속받아서 Spring MVC에서 기본으로 제공되는 Exception들의 처리를 간단하게 등록할 수 있다.

갹 Exception 처리를 위한 메소드들은 모두 `protected`로 선언되어 있으며 하위 클래스에서 필요에 따라 Override할 수 있다.

```java
public abstract class ResponseEntityExceptionHandler {
  @ExceptionHandler({
    HttpRequestMethodNotSupportedException.class, // 405
    HttpMediaTypeNotSupportedException.class, // 415
    HttpMediaTypeNotAcceptableException.class, // 406
    MissingPathVariableException.class, // 500
    MissingServletRequestParameterException.class, // 400
    ServletRequestBindingException.class, // 400
    ConversionNotSupportedException.class, // 500
    TypeMismatchException.class, // 400
    HttpMessageNotReadableException.class, // 400
    HttpMessageNotWritableException.class, // 500
    MethodArgumentNotValidException.class, // 400
    MissingServletRequestPartException.class, // 400
    BindException.class,
    NoHandlerFoundException.class, // 404
    AsyncRequestTimeoutException.class // 503
  })
  @Nullable
  public final ResponseEntity<Object> handleException(Exception ex, WebRequest request) throws Exception {
    // 각 예외에 대한 분기처리 로직(상속 구현 가능하도록 protected로 메서드가 선언되어 있음)
  }
  
  // 각 예외 처리를 위한 protected 메서드들이 있음
  protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
      HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    // 예외 처리
  }
}
```

이를 활용해서 실제 적용한 예제를 보자.

```java
@RestController
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ExceptionResponse.builder()
                .timestamp(new Date())
                .message(ex.getMessage())
                .details(request.getDescription(false)).build());

    }

    @ExceptionHandler(ProductNotFoundException.class)
    public final ResponseEntity<Object> handleProductNotFoundExceptions(Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ExceptionResponse.builder()
                .timestamp(new Date())
                .message(ex.getMessage())
                .details(request.getDescription(false)).build());

    }
}
```

`@ExceptionHandler`의 인자로 `~~Exception.class`를 정의하여 Exception의 대상을 다양하게 처리할 수 있다.



SpringBoot가 제공하는 ErrorAttributes는 단일 구현으로 에러를 처리하기 때문에 모듈별로 Exception을 상속해서 별도 정의하는 경우에는 다양한 에러에 대응을 하나의 구현으로 처리하는 것은 무리가 있다.

따라서 다양한 Exception에 대해 별도 정의가 가능한 Global Exception Handler 방식을 선호한다.

그 이외에도 **`HandlerExceptionResolver`** 인터페이스를 사용해서 요청, 응답, 핸들러, 예외를 파라미터로 받아서 `ModelAndView`를 반환값으로 하는 `resolveException` 메소드가 있는데, 이는 추후에 다시 알아보도록 할 예정이다.



## **Filter와 Interceptor**

Filter와 Intercepter는 실행되는 위치가 다르므로 Exception이 발생했을 때 처리하는 방법도 달라진다.

Interceptor는 DispatcherServlet 내부에서 발생하기 때문에 `ContollerAdvice`를 적용할 수 있지만
Filter는 DispatcherServlet 외부에서 발생하기 때문에 `ErrorController`에서 처리해야 한다.

![image-20210925045804677](https://user-images.githubusercontent.com/58545240/134735841-2399bc60-5c2e-475f-be3c-27c12444742e.png)



참조 : https://supawer0728.github.io/2019/04/04/spring-error-handling/

# Swagger, Postman

# JPQL?

# 쿠키, 세션 그리고 인터셉터

# MSA 맛보기

# Hibernate의 기본키 생성전략

# 트랜잭션과 영속성 컨텍스트

# DDD: 도메인주도설계