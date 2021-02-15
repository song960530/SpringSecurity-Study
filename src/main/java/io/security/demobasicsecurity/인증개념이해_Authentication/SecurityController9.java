package io.security.demobasicsecurity.인증개념이해_Authentication;

import org.springframework.web.bind.annotation.GetMapping;

// @RestController
public class SecurityController9
{

    @GetMapping("/")
    public String index()
    {
        return "home";
    }
}
