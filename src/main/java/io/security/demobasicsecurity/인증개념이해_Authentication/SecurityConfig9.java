package io.security.demobasicsecurity.인증개념이해_Authentication;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * TITLE : Authentication
 * 
 * 사용자의 인증 정보를 저장하는 토큰 개념
 * 
 * 구조 :
 * principal : 사용자 아이디 or User 객체 저장
 * credentials : 사용자 비밀번호
 * authorities : 인증된 사용자의 권한 목록
 * details : 인증 부가 정보
 * Authenticated : 인증 여부
 *
 * 각 구조에 해당 정보를 담은 뒤 SecurityContext 인증 객체에 담김(전역에서 사용)
 */
//@Configuration
//@EnableWebSecurity
public class SecurityConfig9 extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .formLogin();
    }
}
