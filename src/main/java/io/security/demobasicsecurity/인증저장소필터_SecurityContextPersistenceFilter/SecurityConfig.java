package io.security.demobasicsecurity.인증저장소필터_SecurityContextPersistenceFilter;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * TITLE :SecurityContext 객체의 생성, 저장, 조회
 *
 *  - 익명사용자
 *   - 새로운 SecurityContext 객체를 생성하여 SecurityContextHolder 에 저장
 *
 *  - 인증 시
 *   - 새로운 SecurityContext 객체를 생성하여 SecurityContextHolder 에 저장
 *
 *  - 인증 후
 *   - Session 에서 SecurityContext를 꺼내어 SecurityContextHolder 에 저장
 *
 *   최종 응답 시 SecurityContextHolder.clearContext()를 실행함
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