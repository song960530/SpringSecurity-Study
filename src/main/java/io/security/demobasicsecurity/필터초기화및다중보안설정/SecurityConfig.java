package io.security.demobasicsecurity.필터초기화및다중보안설정;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * TITLE : 필터 초기화 및 다중 보안 설정 하는 방법
 * 
 * 동작원리 :
 * WebSecurityConfigurerAdapter 클래스를 상속받은 Securi tyConfig 클래스 설정(보안 API, 필터생성..)
 * -> 설정 클래스별로 생성된 필터가 SecurityFilterChain 클래스 안에 담김
 * -> FilterChainProxy 클래스 안에 SecurityFilterChains 리스트 변수에 각각의 SecurityFilterChain클래스가 담김
 * 
 * 참고사항 :
 * 여러개의 SecurityConfig클래스를 설정해줄 땐 @Order 어노테이션으로 순서를 지정해줘야함
 * 순서는 세부내용 순서대로 순번을 지정해줘야함.
 */

// @Configuration
// @EnableWebSecurity
// @Order(0)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.antMatcher("/admin/**")
            .authorizeRequests()
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic();
    }
}

// @Configuration
// @Order(1)
class SecurityConfig_1 extends WebSecurityConfigurerAdapter
{

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
            .anyRequest()
            .permitAll()
            .and()
            .formLogin();
    }
}
