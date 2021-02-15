package io.security.demobasicsecurity.인증저장소_SecurityContextHolder_SecurityContext;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;

// @RestController
public class SecurityController10
{

    @GetMapping("/")
    public String index(HttpSession session)
    {
        Authentication authentication = SecurityContextHolder.getContext()
                                                             .getAuthentication();
        SecurityContext context = (SecurityContext) session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
        Authentication authentication1 = context.getAuthentication();

        System.out.println(authentication);
        System.out.println(authentication1);

        return "home";
    }





    @GetMapping("/thread")
    public String thread()
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                Authentication authentication = SecurityContextHolder.getContext()
                                                                     .getAuthentication();
                System.out.println(authentication);
            }
        }).start();
        return "thread";
    }
}
