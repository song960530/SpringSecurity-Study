package io.security.demobasicsecurity.인증흐름이해;

import org.springframework.web.bind.annotation.GetMapping;

//@RestController
public class SecurityController
{

    @GetMapping("/")
    public String index() { return "home"; }

}
