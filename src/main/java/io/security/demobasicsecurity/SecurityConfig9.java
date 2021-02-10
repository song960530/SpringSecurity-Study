package io.security.demobasicsecurity;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * TITLE : Authentication
 * 
 * 사용자의 인증 정보를 저장하는 토큰 개념
 * 
 * 구조 :
 * principal : 사용자 아이디 or User 객체 저장
 * credentials : 사용자 비밀번호
 * aythorities : 인증된 사용자의 권한 목록
 * details : 인증 부가 정보
 * Authenticated : 인증 여부
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig9
{

}
