package io.security.demobasicsecurity.인증흐름이해;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * TITLE : 인증 흐름 설명
 *
 * 1. Client - Client로부터 받은 Login Request를 UsernamePassword Authentication Filter가 받음
 * 2. UsernamePassword Authentication Filter - id와 password를 담은 토큰 객체(Authentication) 생성하여 Authenticationmanager로 전달
 * 3. Authenticationmanager - 인증의 전반적인 관리를 하여 실제 인증 역할을 하지않고 적절한 AuthenticationProvider에 위임
 * 4. AuthenticationProvider - 유저 유효성 검증(패스워드 체크 등)후 UserDetailsServie로 이동
 * 5. UserDetailsServie - UserDetails 타입으로 Repository에 반환
 *
 */
//@Configuration
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
    }
}