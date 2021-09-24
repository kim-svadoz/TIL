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

# Autowired

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

# JpaRepository

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

# 영속성 컨텍스트의 이점 : 1차캐시, 쓰기지연, 로딩지연, 변경감지

# DDD: 도메인주도설계