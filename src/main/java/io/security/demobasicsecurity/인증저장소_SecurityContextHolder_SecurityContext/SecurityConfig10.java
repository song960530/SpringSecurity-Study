package io.security.demobasicsecurity.인증저장소_SecurityContextHolder_SecurityContext;

import io.security.demobasicsecurity.SecurityController;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * TITLE : SecurityContext, SecurityContextHolder
 *
 * 정의 :
 * SecurityContext
 *  - Authentication 객체가 저장되는 보관소(클래스)
 *  - ThreadLocal에 저장되여 아무곳에서나 참조 가능
 *  - 인증 완료시 HttpSession에 저장되어 전역적인 참조 가능
 *
 * SecurityContextHolder
 *  저장방식 :
 *   - MODE_THREADLOCAL : 스레드당 SecurityContext 객체를 할당. 기본값
 *   - MODE_INHERITABLETHREADLOCAL : 메인 스레드와 자식 스레드에 관하여 동일한 SecurityContext 유지
 *   - MODE_GLOBAL : 응용프로그램에서 단 하나의 SecurityContext 저장
 *  초기화 방법 :
 *   - SecurityContextHolder.clearContext();
 *
 * 꺼내 쓰는 방법 :
 * Authentication authentication = SecurityContextHolder.getContext().getAuthentication()
 */
//@Configuration
//@EnableWebSecurity
public class SecurityConfig10 extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
            .anyRequest().authenticated();
        http.formLogin();

        //SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_THREADLOCAL);
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
        //SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_GLOBAL);
    }
}
