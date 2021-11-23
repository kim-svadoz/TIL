# Spring Security

# LoginForm 인증 API 및 Filter의 이해

---



##  [ Login 인증 ] UsernamePasswordAuthenticationFilter

>   `SecurityContext` : 인증 필터가 사용자의 인증을을 가지고 있는 Context
>
>   `SecurityContextHolder.getContext().getAuthentication()` : 어디서든 현재 어떤 인증을 받았는지 볼 수 있다.

```java
protected void configure(HttpSecurity http) throws Exception {
    http.formLogin()
        .loginPage("/login.html")					 // 사용자 정의 로그인 페이지
        .defaultSuccessUrl("/home") 				// 로그인 성공 후 이동 페이지
        .failureUrl("/login.html?error=true") 		 // 로그인 실패 후 이동 페이지
        .usernameParameter("username") 				// 아이디 파라미터명 설정
        .passwordParameter("password") 				// 패스워드 파라미터명 설정
        .loginProcessingUrl("/login") 				// 로그인 Form Action Url
        .successHandler(loginSuccessHandler())		 // 로그인 성공 후 핸들러
        .failureHandler(loginFailureHandler()) 		 // 로그인 실패 후 핸들러
	;
}
```

```java
// 실제 사용 코드
protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated();
        http
                .formLogin()
                .loginPage("/loginPage") // 직접 만든 로그인 페이지로 이동하도록 설정
                .defaultSuccessUrl("/")
                .failureUrl("/login")
                .usernameParameter("userId")
                .passwordParameter("passwd")
                .loginProcessingUrl("/login_proc")
                .successHandler(new AuthenticationSuccessHandler() { // 로그인 성공 시 호출하는 핸들러
                    // handler
                })
                .failureHandler(new AuthenticationFailureHandler() { // 로그인 실패 시 호출하는 핸들러
                    // handler
                })
                .permitAll() // 로그인 페이지는 인증을 받지 않아도 되도록.
        ;
    }
```



### > 인증과정

![image-20211120130747378](https://user-images.githubusercontent.com/58545240/142721806-d3b77463-2c5f-4fb3-998f-89a5aeaa0528.png)

Request가 들어오면 `UsernamePasswordAuthenticationFilter`로 들어오게 되고 `Authentication`**(Username + Password)** 객체를 `AuthenticationManager`로 보낸다.

`AuthenticationManager`는 `AuthenticationProvider`에게 인증처리를 맡기고, 인증에 실패하면 예외를 발생하고 인증에 성공하면 다시 `AuthenticationManager`가 필터에게 전달해서 Authentication**(User + Authorities)** 객체를 넘기면 이 정보를 `SecurityContext`에 저장하고 `SuccessHandler`로 이후 작업을 수행한다



`FilterChainProxy`는 Filter를 관리하는 Bean인데 이 프록시가 가지는 여러 기본적인 필터 Bean들이 있다. 

`.formLogin()`을 실행하면 여러 API에 관련된 필터들이 생성이 된다. 그래서 이 `FilterChainProxy`는 각각의 필터들을 순서대로 사용자 요청을 처리할 수 있도록 호출해준다.

![image-20211120130855890](https://user-images.githubusercontent.com/58545240/142721828-a68ca13f-05ab-4348-88f8-36143d918b99.png)



## [ Logut 처리 ]  LogoutFilter

Logout 요청(`/logout`)이 들어오면 Security가 이 요청을 받고 로그아웃 처리를 한다.

1.  세션을 무효화하고
2.  인증토큰을 삭제하고 (`SecurityContext` 등)
3.  쿠키정보도 삭제하고
4.  로그인 페이지로 리다이렉트

의 순서로 진행된다.



```java
protected void configure(HttpSecurity http) throws Exception {
    http.logout()											// 로그아웃 처리
        .logoutUrl("/logout")								 // 로그아웃 처리 URL
        .logoutSuccessUrl("/login")						 	  // 로그아웃 성공 후 이동 페이지
        .deleteCookies("JSESSIONID", "remember-me")			   // 로그아웃 후 쿠키 삭제
        .addLogoutHandler(logoutHandler())					  // 로그아웃 핸들러
        .logoutSuccessHandler(logoutSuccessHandler())		   // 로그아웃 성공 후 핸들러
    ;
}
```

```java
// 실제 사용 코드
protected void configure(HttpSecurity http) throws Exception {    
    http
        .logout()
        .logoutUrl("/logout") // default 는 logout , SpringSecurity는 기본적으로는 post방식으로 logout처리한다.
        .logoutSuccessUrl("/login") // logout후 이동할 페이지를 설정
        .addLogoutHandler(new LogoutHandler() {
            @Override
            public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
                HttpSession session = request.getSession();
                session.invalidate();
            }
        })
        .logoutSuccessHandler(new LogoutSuccessHandler() { // logout 후 호출하는 핸들러
            @Override
            public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                response.sendRedirect("/login");
            }
        })
        .deleteCookies("remember-me") // remember-me 라는 쿠키가 삭제된다.
        ;

}
```



### > 인증과정

![image-20211120130814528](https://user-images.githubusercontent.com/58545240/142721842-33a802ed-6f79-4a71-a7f7-3ab06763eadd.png)

기본적으로는 POST 방식으로 Request를 받아서 해당 요청은 `LogoutFilter`로 이동한다.

Filter는 `SecurityContext`로부터 `Authentication` 객체를 꺼내와서 `SecurityContextLogoutHandler`로 가져온다.

이 핸들러가 세션을 무효화 하고, 쿠키를 삭제하고 `SecurityContextHolder.clearContext()`를 수행한다.



*(get 방식으로 로그아웃을 처리할 때도 이 `SecurityContextLogoutHandler`에서 처리한다.)*

로그아웃 핸들러가 성공적으로 종료되면 `SimpleUrlLogoutSuccessHandler`가 login페이지로 리다이렉트 해준다.

![image-20211120130831816](https://user-images.githubusercontent.com/58545240/142721856-636997a4-caeb-48b3-af88-87ead8a3b8f4.png)



## [ Remember Me 인증 ] : RememberMeAuthenticationFilter

>   *Remember-Me ?*
>
>   -   **세션이 만료되고 웹 브라우저가 종료된 이후에도 어플리케이션이 사용자를 기억하는 기능**
>   -    `Remember-Me` 쿠키에 대한 Http 요청을 확인한 후에 토큰 기반 인증을 사용해서 유효성을 검사하고 토큰이 검증되면 사용자는 로그인 된다.
>   -   사용자 Life Cycle
>       -   인증 성공(Remember-Me 쿠키 설정)
>       -   인증 실패(쿠키가 존재하면 쿠키 무효화)
>       -   로그아웃(쿠키가 존재하면 쿠키 무효화)

```java
protected void configure(HttpSecurity http) throws Exception {
    http.rememberMe()
        	.rememberMeParameter("remember")				 // 기본 파라미터명은 remember-me
        	.tokenValiditySeconds(3600)						 // Default는 14일
        	.alwaysRemember(true)							// 리멤버미 기능이 활성화되지 않아도 항상 실행
        	.userDetailsService(userDetailsService)			  // 리멤버미 기능을 수행할때 시스템의 사용자 계정을 조회할 때 필요한 클래스
	;
}
```



*로그인이 되었다는 것은 사용자의 세션이 생성되었고, 그 세션이 성공한 인증객체를 담고 있는 상태이다.*



서버는 인증에 성공한 사용자에게 세션을 생성할 때 가지고 있는 `JSESSIONID`를 응답헤더에 실어서 보내는 것이다. 그럼 이 클라이언트가 `JSESSIONID`를 가지고 있을 것이고, 이 상태에서 클라이언트가 다시 서버에 접속하게 되면 인증을 받지 않아도 되는 이유가 클라이언트가 가지고 온 `JSESSIONID`의 아이디와 매칭되는 세션(SecurityContext -> 인증 객체)을 꺼내서 이 사용자가 인증된 사용자인지 판단하고 처리하게 된다.



만약에 클라이언트에서 쿠키(`JSESSIONID`)를 제거하고 다시 로그인을 하게 되면 해당 사용자가 처음 로그인한 사용자로 판단할 것이고 다시금 인증을 해야한다.



하지만 `remember me` 기능을 선택하고 로그인을 하게 되면 리멤버미 인증을 하게 되었으므로 서버는 **remember-me**라는 이름으로 쿠키를 생성해서 발급한다.

이 때는 사용자의 유저아이디와 패스워드, 쿠키 만료일이 담겨져 있다. 이 기능이 있는 상태에서 동일하게 `JSESSIONID`를 제거하고 다시 사이트를 접속하면 인증을 다시 받지 않아도 인증을 거치지 않는다.



그 이유는 `JSESSIONID`가 없더하더라도 SpringSecurity에서는 `remember-me`라는 쿠키를 가진 값을 가지고 왔을 경우를 체크한다.

**`rememberMeAuthenticationFilter`**에서 이 값을 다시 디코딩 하고 파싱하고 추출해서 userid와 password를 통해서 User 계정을 하나 얻고 이 객체를 통해서 다시금 인증을 시도하고 인증을 성공하게 된다.



### > 인증과정

![image-20211120135454474](https://user-images.githubusercontent.com/58545240/142721868-0362ee62-a0e8-40c6-a280-9943042e17c1.png)



인증을 받은 사용자는 인증 객체가 `Security Context`에 저장되어 있다.

`Security Context`안의 `Authentication`이 null이라는 것은 사용자 세션이 만료되었거나 더이상 세션안에서 `Security Context`를 찾지 못하고 인증객체도 없는 경우이다. **이 때 `RememberMeAuthenticationFilter`가 동작한다.** 

(*Session Timeout으로 만료되었거나, 브라우저가 종료되어서 세션이 끝나서 SecurityContext에서 인증객체를 찾지 못하는 경우*)

다시금 사용자의 인증을 받게 되어서 사용자가 인증이 유지된채로 서버에 접속할 수 있도록 하는 필터이다.



**`RememberMeAuthenticationFilter`**가 동작하는 순서를 정리하자면

1.  인증 객체가 없는 경우
2.  사용자가 리멤버미 쿠키를 가져오는 경우

에 필터가 정상적으로 작동을 하게 된다.



RememberMeService 인터페이스는 실제로 리멤버미 인증 처리를 하는 두 구현체가 있다.

-   `TokenBasedRememberMeservice`
    -   메모리에서 실제로 재생한 토큰과, 사용자가 요청할 때 들고온 쿠키의 토큰과 비교해서 인증처리를 한다.
-   `PersistentTokenBasedRememberMeServices`
    -   영구적인 방식. 디비에서 발급한 토큰과, 사용자가 들고온 온 토큰 값을 비교해서 인증처리를 한다.



사용자의 토큰 쿠키를 추출해서 사용자가 가지고 온 토큰이, `remeber-me`라는 이름의 토큰이 존재하는지 검사한다.

리멤버미 토큰이 존재하지 않으면 그다음 필터로 이동하게 된다.(`chain.doFilter`)

존재할 경우에는

1.  decode token해서 정상적인 규칙을 지키고 있는 토큰인지 확인하고
2.  이 토큰의 값과 서버에 저장된 토큰의 값이 서로 일치한다면
3.  토큰에 포함된 정보 중에서 user 정보가 DB에 존재한다면
4.  새로운 Authentication(인증) 객체를 생성하고 이 인증 객체를
5.  AuthenticationManager에게 전달해서 인증처리를 하게 된다.



![image-20211120135511617](https://user-images.githubusercontent.com/58545240/142721874-829ea1d4-70c7-4780-ac25-a473ff55adbf.png)



## < AnonymousAuthenticationFilter >

>   -   익명 사용자 인증처리 필터
>
>   -   익명 사용자와 인증 사용자를 구분해서 처리하기 위한 용도로 사용되는 필터.
>
>   -   화면에서 인증 여부를 구현할 때 `isAnonymous()` 와 `isAuthenticated()`로 구분해서 사용한다.
>
>       *(login / logout 과 같은 기능을 나누어서 처리 가능)*
>
>   -   인증객체를 세션에 저장하지 않는다.

어떤 사용자가 인증을 받았을 때, 세션에 사용자의 유저 객체를 저장을 한다.

사용자가 어떤 자원에 접근하려고 하면 이 사용자가 저장한 유저 객체가 존재하는지 아닌지 판단을 하게 된다.

만약에 null이면 인증을 받지 않았다고 판단해서 자원에 접근하지 못하게 하는 것이다.



![image-20211120172159864](https://user-images.githubusercontent.com/58545240/142721876-35eb7909-f521-4032-b456-88d989f87082.png)

이 `AnonymousAuthenticationFilter`는 사용자 객체를 null로 처리하는게 아니라 별도의 **Anonymous Authentication Token**의 인증객체를 만들어서 사용한다.

익명 사용자라고 할 지라도 마치 인증 사용자가 인증 성공 이후에 저장하는 방식과 동일하게 Security Context 안에 인증객체를 저장한다.



그래서 Security Context는 **여러 곳이나 여러 필터에서 현재 사용자가 현재 사용자가 익명 사용자인지, 인증 사용자인지 구분할 수 있다는 장점이 있는 것**이다.



## [ 동시 세션 제어, 세션 고정 보호, 세션 정책 ]

### > 동시 세션 제어

동일한 계정으로 인증을 받을 때 생성되는 세션에 허용 개수가 초과 되었을 경우 어떻게 세션을 유지하는 지에 대한 제어를 말한다.

Spring-Security에서는 두 가지 전략으로 동시세션제어를 하게 된다.

![image-20211120173859245](https://user-images.githubusercontent.com/58545240/142721883-d5bacb64-41bd-4b48-9253-7c3b341d63d8.png)

>   *default 최대 세션 허용 개수 : 1개*

동일한 계정으로 들어온 사용자에 대해서 세션 허용 개수



1.  이전 사용자 세션 만료
    -   사용자 1의 세션은 만료 되고 사용자 2의 세션은 사용가능 하다.
2.  현재 사용자 인증 실패
    -   뒤늦게 들어온 사용자 2의 세션을 차단해서 사용자 1의 세션은 사용가능 하다.



```java
protected void configure(HttpSecurity http) throws Exception {
    http.sessionManagement()					
        	.maximumSession(1)							// 최대 허옹 가능 세션 수(default 1)
        	.maxSessionsPreventsLogin(true)				 // 동시 로그인 차단한다 -> true:현재사용자인증실패 // (default)false:기존세션만료
        	.invalidSessionUrl("/invalid")				 // 세션이 유효하지 않을 때 이동 할 페이지
        	.expiredUrl("/expired")						// 세션이 만료된 경우 이동 할 페이지
	;
}
```

이 때 `invalidSessionUrl()`과  `expiredUrl()`을 모두 설정했을 땐 **`invalidSessionUrl()`이 우선적용** 된다.



false(기존 사용자 세션 만료)로 설정하면 사용자 1과 사용자 2 모두 세션이 실행이 되긴한다.

그러나 현재 사용자의 세션이 만료되었는지 안되었는지 `ConcurrentSessionFilter`가 체크를 해서 이전 사용자의 세션을 만료시키게 된다.

>   *This session has been expired (possibly due to multiple concurrent logins being attempted as the same user).*



### > 세션 고정 보호

![image-20211120175513131](https://user-images.githubusercontent.com/58545240/142721888-5c5f40e2-315a-4d14-b48d-6645708f734f.png)



공격자가 임의로 `JSESSIONID` 를 웹에 심어놓고 사용자도 공격자의 세션 쿠키를 사용하게 되면,

사용자와 공격자 모두 동일한 세션을 공유하게 되는 참사가 발생한다.

이런걸 **세션 고정 공격**이라고 한다.



그래서 Spring-Security는 **세션 고정 보호**를 제공한다.

사용자가 비록 공격자가 심어놓은 쿠키로 접속을 해서 인증을 시도한다 하더라도, 인증할 때 마다 새로운 세션을 생성하면 새로운 쿠키가 만들어질 거고 공격자가 심어놓은 `JSESSIONID`는 달라지기 때문에 사용자의 정보를 공유할 수 없다.

**즉, 인증에 성공할 때마다 새로운 세션을 생성되고 새로운 `JSESSIONID`를 발급하도록 하는 것이 세션 고정 보호**이다.



```java
protected void configure(HttpSecurity http) throws Exception {
    http.sessionManagement()
        	.sessionFixation().changeSessionid()		// 기본값, (none, migrateSession, newSession)
	;
}
```

여기서 `changeSessionid()` 는 서블릿 3.1 이상에서의 기본값이고 그 이하 버전에서의 기본값은 `migrateSession()`이다.



changeSessionid과 migrateSession 이 두 개의 옵션은 이전 세션의 설정한 여러 속성값들을 그대로 사용할 수 있도록 지원하며

newSession은 이전 세션에서 설정한 여러 속성값들을 사용하지 못하고 새롭게 속성값들을 설정해야 한다는 특징이 있다.

none 옵션은 새롭게 세션을 생성하지 않고, 공격에 취약하다.



### > 세션 정책

>   세션 관리 기능이 작동하는 정책

```java
protected void configure(HttpSecurity http) throws Exception {
    http.sessionManagement()
        	.sessionCreationPolicy(SessionCreationPolicy.If_Required)
	;
}
```

-   `SessionCreationPolicy.Always` : 스프링 시큐리티가 항상 세션을 생성한다.
-   `SessionCreationPolicy.If_Required` : 스프링 시큐리티가 필요 시에 생성한다.(**기본 값**)
-   `SessionCreationPolicy.Never` : 스프링 시큐리티가 생성하지 않지만 이미 존재하면 사용한다.
-   `SessionCreationPolicy.Stateless` : 스프링 시큐리티가 생성하지 않고 존재해도 사용하지 않는다.
    -   **`JWT`**이라는 웹 토큰을 이용해 토큰에 사용자의 정보나 추가적인 사항들을 저장하고 인증을 하는 방식이다.
    -   이 때 는 Stateless 옵션을 사용한다.



## < SessionManagementFilter, ConcurrentSessionFilter >

-   `SessionManagementFilter`
    1.  세션 관리
        -   인증 시 사용자의 세션정보를 등록, 조회, 삭제 등의 세션 이력을 관리
    2.  동시적 세션 제어
        -   동일 계정으로 접속이 허용 되는 최대 세션 수를 제한
    3.  세션 고정 보호
        -   인증할 때마다 세션쿠키를 새로 발급해서 공격장의 쿠키 조작을 방지
    4.  세션 생성 정책
        -   Always, If_Required, Never, Stateless

-   `ConcurrentSessionFilter`

    -   매 요청마다 현재 사용자의 세션 만료 여부를 체크해서

        세션이 만료되었을 경우에는 즉시 만료 처리를 한다.

    -   `session.isExpired() == true`

         - 로그아웃 처리
         - 즉시 오류 페이지 응답

    -   이 필터는 동시적 세션제어를 하기 위해 SessionManagementFilter와 항상 연계되어 있다.



![image-20211121204044680](https://user-images.githubusercontent.com/58545240/142767019-ae6c7acf-13cd-46a1-8f27-e7ab968a8597.png)



*사용자1과 2의 로그인 과정에서 동시적 세션 접근제어를 하는 과정*

![image-20211121204406623](https://user-images.githubusercontent.com/58545240/142767048-4ef730a0-cdd2-4b42-91a8-25293ae8f108.png)

1.  사용자1이 `UsernamePassworddAuthenticationFilter`는 가장 먼저 `ConcurrentSessionControlAuthenticationStrategy` 클래스를 가장 먼저 호출한다. 이 계정으로 생성된 세션의 개수가 얼마인지 확인한다. 이 때는 session count가 0이므로 문제없이 통과한다.
2.  그 이후 `ChangeSessionIdAuthenticationStrategy` 클래스를 호출해서 세션 고정 보호를 처리한다. 새롭게 세션을 생성하고 새로운 세션 쿠키를 발급한다.
3.  세 번째로 `RegisterSessionAuthenticationStrategy` 클래스를 호출해서 사용자의 세션을 등록하고 저장하는 역할을 한다. 이 때 session count가 1이 된다.
4.  이 상태에서 사용자2가 사용자1가 동일한 계정으로 인증을 시도한다.
5.  해당 계정으로 생성된 세션이 한개가 있고, 최대 허용 세션 개수도 1로 똑같으므로 두 가지 전략을 타게 된다.
    1.  인증 실패 전략일 경우
        -   `SessionAuthenticationException`이 발생하고 인증이 실패가 된다.
        -   더이상 다른 클래스를 호출하지 않고 인증이 끝나버린다!
    2.  이전 사용자의 세션만료 전략일 경우
        -   사용자 1의 세션을 만료 시킨다 : `session.expireNow()`
        -   사용자 2의 세션을 인증한다 : 똑같이 1,2,3의 과정을 거친다.
        -   이 때 사용자 1이 다시금 자원에 접근을 하게 되면 ConcurrentSessionFilter가 사용자의 세션 만료여부를 체크한다 : `session.isExpired() == true`



# 인가 API

---

## [ 권한설정과 표현식 ]

-   선언적 방식

    -   URL

        -   `http.antMatchers("/users/**").hasRole("USER")`

    -   Method

        -   `@PreAuthorize("hasRole('USER')")`

            *public void user() { System.out.println("user"); }*

-   동적 방식 (DB 연동 프로그래밍)

    -    URL
    -   Method



### > 권한 설정

```java
protected void configure(HttpSecurity http) throws Exception {
    http
        .antMatcher("/shop/**")						// 해당 설정한 경로로 요청할 때만 보안 기능이 작동하게 된다. 이걸 생략하면 모든 경로에 적용
        .authorizeRequest()
        	.antMatchers("/shop/login", "/shop/users/**").permitAll() // 해당 경로로 접근하는 모든 요청에 대해 허용 하겠다.(인가 허용)
        	.antMatchers("/shop/mypage").hasRole("USER") // mypage로 접근하는 사용자는 USER라는 역할이어야 한다.
            .antMatchers("/shop/admin/pay").access("hasRole('ADMIN')");
            .antMatchers("/shop/admin/**").access("hasRole('ADMIN') or hasRole('SYS ')");
            .anyRequest().authenticated()		// 위의 요청을 제외하고서는 모든 요청에 대해서 인증을 받은 사용자 만이 어떤 자원에 접근가능하다.
	;
}
// 여기서 모든 절차를 거쳐야 인가를 통과하고 해당 자원에 접근할 수 있다.
```

***이 때 주의사항은 설정시 구체적인 경로가 먼저 오고 그것보다 큰 범위의 경로가 뒤에 와야 한다.***

스프링 시큐리티는 위에서부터 아래로 순서대로 인가처리를 하게 되기 때문이다.



### > 표현식

| 메소드                     | 동작                                                     |
| -------------------------- | -------------------------------------------------------- |
| authenticated()            | 인증된 사용자의 접근을 허용                              |
| fullyAuthenticated()       | 인증된 사용자의 접근을 허용, rememberMe 인증은 제외      |
| permitAll()                | 무조건 접근을 허용                                       |
| denyAll()                  | 무조건 접근을 허용하지 않음                              |
| anonymous()                | 익명사용자의 접근을 허용                                 |
| rememberMe()               | 기억하기를 통해 인증된 사용자의 접근을 허용              |
| access(String)             | 주어진 SpEL 표현식의 평과 결과가 true이면 접그을 허용    |
| hasRole(String)            | 사용자가 주어진 역할이 있다면 접근을 허용                |
| hasAuthority(String...)    | 사용자가 주어진 권한이 있다면 접근을 허용                |
| hasAnyRole(String...)      | 사용자가 주어진 권한이 있다면 접근을 허용                |
| hasAnyAuthority(String...) | 사용자가 주어진 권한 중 어떤 것이라도 있다면 접근을 허용 |
| hasIpAddress(String)       | 주어진 IP로부터 요청이 왔다면 접근을 허용                |



특이한것은 인증된 사용자는 **`anonymous()`**에서 접근이 불가능하다. 익명사용자 메소드는 오로지 **익명사용자만 접근하기 위한 메소드**이다.

>   Spring Security 5부터 password를 암호화 할 때 특정한 패스워드 유형 알고리즘의 방식을 prefix형태로 표시해야 한다.
>
>   그래야 나중에 패스워드를 검사하고 매치할 때 어떤 유형으로 패스워드 알고리즘을 통해 저장하고 암호화 했는지 알 수 있다.
>   *(prefix가 없다면 id가 null로 뜬다.)*
>
>   **{noop}** : 1111 그대로, 평문으로 사용한다는 prefix이다.

```java
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication().withUser("user").password("{noop}1111").roles("USER");
    auth.inMemoryAuthentication().withUser("sys").password("{noop}1111").roles("SYS","USER");
    auth.inMemoryAuthentication().withUser("admin").password("{noop}1111").roles("ADMIN","SYS","USER");
}
```

현재는 메모리형식으로 했지만 실제로는 DB를 통해서 동적으로 관리자 시스템에서 사용자를 생성하고 관리해야 할 것이다.

해당 자원에 대한 권한 설정도 운영서비스에서 실시간적으로 필요에 의해서 설정이 되어야 한다.



# 인증/인가 API

## ExceptionTranslationFilter

>    *FilterSecurityInterceptor(맨 마지막에 위치)가 이를 호출한다*
>
>   ExceptionTranslationFilter 필터는 아래 두가지 예외를 처리하고 있다.

-   `AuthenticationException`

    -   **인증 예외 처리**

        1.  AuthenticationEntryPoint 호출

            -   로그인 페이지 이동
            -   401 오류 코드 전달 등

        2.  인증 예외가 발생하기 전의 요청 정보를 저장

            -   **RequestCache** : 사용자의 이전 요청 정보를 세션에 저장하고 이를 꺼내 오는 캐시 메커니즘을 제공
            -   **SavedRequest ** : 사용자가 요청했던 request 의 파라미터 값들, 그 당시의 헤더값 들이 저장

            *이 둘은 모두 인터페이스 이며 세부 기능을 하는 구현체가 존재한다.*

-   `AccessDeniedException`

    -   **인가 예외 처리**

        -   **AccessDeniedHandler 에서 예외 처리하도록 제공**

        *얘도 인터페이스 이며 세부 기능을 하는 구현체가 존재한다.*



![image-20211121222802392](https://user-images.githubusercontent.com/58545240/142767060-de29abad-3eac-4038-8c46-d031c6eef5b6.png)

유저가 `/user` 자원 접근을 시도하고 있다면 FilterSecurityInterceptor에서 엄밀히 말해서는 익명사용자 이기 때문에 인증 예외가 아니라, 인가 예외로 빠지게 된다.

이 ExceptionTranslationFilter가 인가 예외로 보내긴 하지만 AccessDeniedHandler 로 곧바로 보내지 않고, **HttpSessionRequestCache**로 보내버린다.

따라서, 쉽게 생각해서 인증 예외의 과정으로 보내어진다고 보면 된다.



인증예외파트에서는 두 가지 일을 하게 된다.

1.  인증 실패 이후 처리

    -   Security Context안에 있는 인증 객체를 null로 만든다.

    -   **AuthenticationEntryPoint** 에서 다시 인증할 수 있도록 로그인 페이지로 보내버리고

2.  사용자의 요청관련 정보 저장

    -   예외가 발생하기 이전의 유저정보를 저장한다.
    -   이 정보는 **DefaultSavedRequest** 객체 안에 저장되고 이 객체는 Session에 저장한다.
    -   그 역할을 **HttpSessionRequestCache** 클래스가 수행한다.



만약에 유저가 `/user` 페이지가 아니라, `/admin` 자원에 접근하려고 한다면 인가예외가 발생한다.

ExceptionTranslationFilter는 **AccessDeniedException**이 발생하고 AccessDeniedHandler 를 호출해서 그 안에서 후속처리를 처리한다.
(보통은 `/denied`페이지를 redirect 한다. 또는 이 자원에 다시금 접근할 수 있도록 조정할 수 있는 처리를 하게 된다.)

### > 예외처리 과정

```java
protected void configure(HttpSecurity http) throws Exception {
    http.exceptionHandling()
        	.authenticationEntryPoint(authenticationEntryPoint())	 // 인증실패 시 처리
        	.accessDeniedHandler(accessDeniedHandler())				// 인가실패 시 처리
	;
}
```

```java
protected void configure(HttpSecurity http) throws Exception {
	http
                .successHandler(new AuthenticationSuccessHandler() { // 로그인 성공 시 호출하는 핸들러
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                        System.out.println("authentication: " + authentication.getName());
                        httpServletResponse.sendRedirect("/");

                        // 이미 인증예외 필터에서 RequestCache에 캐시정보가 세션에 담겨 있으므로 불러와서 사용가능하다.
                        RequestCache requestCache = new HttpSessionRequestCache(); 
                        SavedRequest savedRequest = requestCache.getRequest(httpServletRequest, httpServletResponse);
                        String redirectUrl = savedRequest.getRedirectUrl();
                        httpServletResponse.sendRedirect(redirectUrl);
                    }
                })
	;
    
    http
                .exceptionHandling()
                .authenticationEntryPoint(new AuthenticationEntryPoint() {
                    @Override
                    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                         // login만 인증을 받지 않아도 그 자원에 접근할 수 있어야 한다. -> 인가처리를 따로 해줘야 한다.
                        response.sendRedirect("/login");
                    }
                })
                .accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                        response.sendRedirect("/denied");
                    }
                })

        ;
}
```

그리고 이 때 sendRedirect 할 때는 Spring-Security에서 제공하는 페이지가 아니기 때문에 직접 페이지를 만들어줘야 한다.



![image-20211121230810332](https://user-images.githubusercontent.com/58545240/142767082-463ec690-690a-4ed3-ba92-2272337d91c4.png)



# CSRF

>   사이트 간 요청 위조 공격

![image-20211122115952078](https://user-images.githubusercontent.com/58545240/142850129-fc0a148a-fcf5-4cac-a299-91652135b711.png)

사용자의 의도와는 무관하게 공격자가 심어놓은 행위로, 공격자가 의도한대로 응답하는 공격.

그래서 Spring-Security는 이런 취약점을 방지하기 위한 API를 제공한다.



## CsrfFilter

>   CSRF의 취약점을 방지하는 필터이다.
>
>   -   모든 요청에 랜덤하게 생성된 토큰을 HTTP 파라미터로 요구한다.
>   -   요청 시 전달되는 토큰 값과 서버에 저장된 실제 값과 비교한 후 만약 일치하지 않으면 요청은 실패한다.

-   Client
    -   `<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />`
    -   서버의 자원에 접근할 때는 HTTP 메소드(`PATCH, POST, PUT, DELETE`)로 요청하고 반드시 서버에서 발급한 CSRF 토큰값으로 요청을 해야한다.

Spring Security에서는 기본적으로 `http.csrf()` 기능이 기본적으로 활성되 되어있고, `http.csrf().disabled()`로 비활성화 할 수도 있다.

# Spring Security 주요 아키텍쳐

# 1. DelegatingFilterProxy, FilterChainProxy

## DelegatingFilterProxy

 Filter는 Servlet 2.3부터 제공되는 기술이다.

필터의 역할은 어떤 요청이 있을 때 이 요청이 실제로 서블릿으로 들어오는데, 서블릿 자원에 들어오기 전에 처리를 하는 곳이 필터.

요청에 대한 최종적인 접근 전, 후로 어떤 처리를 할 수 있도록 하는 기술이 필터이다.



![image-20211123202705525](https://user-images.githubusercontent.com/58545240/143019224-ebabc4f8-a1f0-4864-8def-82f3b3a4095d.png)

이 필터는 서블릿 스펙에 있는 기술이기 때문에 Servlet 컨테이너에서 생성되고 실행이 되기 때문에, 필터는 Spring에서 만든 Bean을 Injection 해서 사용할 수 없다.

스프링은 모든 요청에 대한 인증/인가 처리를 Filter를 통해서 한다. 근데 이 필터는 Spring Bean으로 주입하거나 Spring이 사용하는 기술을 사용할 수 없다. 하지만, Filter에서도 스프링 기술이 필요하게 되는데.



이러한 요구사항을 만족하기 위해서 존재하는 클래스가 바로 **DelegatingFilterProxy**이다. 

이는 스프링에서 관리하는 필터가 아니고 서블릿에서 관리하는 필터이다.

즉, 요청을 받아서 이 요청을 스프링에서 관리하는 필터에게 요청을 위임하는 역할을 하게 된다.



**특징**

1.  서블릿 필터는 스프링에서 정의된 빈을 주입해서 사용할 수 없음.
2.  특정한 이름을 가진 스프링 빈을 찾아서 그 빈에게 요청을 위임한다.
    -   **springSecurityFilterChain** 이름으로 생성된 빈을 ApplicationContext에서 찾아서 요청을 위임한다.
    -   실제로 보안처리를 하지는 않는다.



## FilterChainProxy

![image-20211123203344038](https://user-images.githubusercontent.com/58545240/143019248-e1216331-9e81-456e-8be1-502914e9bc69.png)



![image-20211123203939439](https://user-images.githubusercontent.com/58545240/143019270-6277541b-4acd-470e-90e6-964676523cb1.png)

왼쪽은 서블릿 컨테이너, 오른쪽은 스프링 컨테이너의 영역이다.



사용자가 처음 요청하면 서블릿 컨테이너에서 요청을 받게 된다. (필터가 그 역할을 하게 된다.)

요청에 대해서 각각의 필터들이 처리를 하게 되고, 그 중에서 `DelegatingFilterProxy` 클래스가 요청을 받게 되면 이 클래스는, 자기가 전달받은 요청 객체를 특정 이름을 가진 빈(**springSecurityFilterChain**)을 찾아서 요청을 위임한다.

실제로는 DelegatinFilterProxy가 필터로 등록될 때, **springSecurityFilterChain**의 이름으로 등록한다. 내부적으로는 이 이름으로 등록한 이름을 찾는다. 그 이름을 가진 Bean이 바로 **FilterChainProxy**이다. 

FilterChainProxy는 요청에 대해서 각각의 필터별로 호출하고 자기가 관리하는 모든 필터들에 대해 보안처리를 하고, 모두 끝났으면 Spring MVC의 **DispatcherServlet**으로 전달해서 실제 요청에 대한 서블릿 처리를 하게 된다.