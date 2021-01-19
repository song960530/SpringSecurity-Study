package io.security.demobasicsecurity;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  UserDetailsService userDetailsService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/user").hasRole("USER")
        .antMatchers("/admin/pay").hasRole("ADMIN")
        .antMatchers("/admin/**").access("hasRole('ADMIN') or hasRole('SYS')")
        .anyRequest()
        .authenticated()
    ;

    http.formLogin()
        //.loginPage("/loginPage")  //  사용자 정의 로그인 페이지
        .defaultSuccessUrl("/")   //  로그인 성공 후 이동 페이지
        .failureUrl("/login")     //  로그인 실패 후 이동 페이지
        .usernameParameter("userId")  //  아이디 파라미터명 설정
        .passwordParameter("passwd")  //  패스워드 파라미터명 설정
        //.loginProcessingUrl("/login_proc")  //  로그인 Form Action Url
        .successHandler(new AuthenticationSuccessHandler() {  //  로그인 성공 후 핸들러
          @Override
          public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
              HttpServletResponse httpServletResponse, Authentication authentication)
              throws IOException, ServletException {
            System.out.println("authentication : " + authentication.getName());
            RequestCache requestCache = new HttpSessionRequestCache();
            SavedRequest savedRequest = requestCache
                .getRequest(httpServletRequest, httpServletResponse);
          }
        })
        .failureHandler(new AuthenticationFailureHandler() {  //  로그인 실패 후 핸들러
          @Override
          public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
              HttpServletResponse httpServletResponse, AuthenticationException e)
              throws IOException, ServletException {
            System.out.println("exception : " + e.getMessage());
            httpServletResponse.sendRedirect("login");
          }
        })
        .permitAll()
    ;

    http.logout()
        .logoutUrl("/logout")
        .logoutSuccessUrl("/login")
        .addLogoutHandler(new LogoutHandler() {
          @Override
          public void logout(HttpServletRequest httpServletRequest,
              HttpServletResponse httpServletResponse, Authentication authentication) {
            HttpSession session = httpServletRequest.getSession();
            session.invalidate();
          }
        })
        .logoutSuccessHandler(new LogoutSuccessHandler() {
          @Override
          public void onLogoutSuccess(HttpServletRequest httpServletRequest,
              HttpServletResponse httpServletResponse, Authentication authentication)
              throws IOException, ServletException {
            httpServletResponse.sendRedirect("/login");
          }
        })
        //.deleteCookies("remember")
        .and()
        .rememberMe()
        .rememberMeParameter("remember")  //  기본 파라미터명은 remember-me
        .tokenValiditySeconds(3600) //  Default 14일, 초단위로 입력
        .userDetailsService(userDetailsService)
    ;

    http.sessionManagement() // 세션 관리 기능이 작동함
        .maximumSessions(1) //  최대 허용 가능 세션수, -1 : 무제한 로그인 세션 허용
        .maxSessionsPreventsLogin(true) //  동시 로그인 차단, false : 기존 세션 만료(default)
        .expiredUrl("/expired") //  세션이 만료된 경우 이동 할 페이지
    ;

    http.sessionManagement()
        .sessionFixation()
        .changeSessionId()  //  기본값, none, migrateSession, newSession
    ;

    http.exceptionHandling()
        .authenticationEntryPoint(new AuthenticationEntryPoint() {
          @Override
          public void commence(HttpServletRequest httpServletRequest,
              HttpServletResponse httpServletResponse, AuthenticationException e)
              throws IOException, ServletException {
            httpServletResponse.sendRedirect("/login");
          }
        })
        .accessDeniedHandler(new AccessDeniedHandler() {
          @Override
          public void handle(HttpServletRequest httpServletRequest,
              HttpServletResponse httpServletResponse, AccessDeniedException e)
              throws IOException, ServletException {
            httpServletResponse.sendRedirect("/denined");
          }
        })
    ;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
        .withUser("user")
        .password("{noop}1111")
        .roles("USER")
    ;
    auth.inMemoryAuthentication()
        .withUser("sys")
        .password("{noop}1111")
        .roles("SYS")
    ;
    auth.inMemoryAuthentication()
        .withUser("admin")
        .password("{noop}1111")
        .roles("ADMIN", "USER", "SYS")
    ;
  }
}
