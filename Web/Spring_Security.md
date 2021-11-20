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



## AnonymousAuthenticationFilter

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