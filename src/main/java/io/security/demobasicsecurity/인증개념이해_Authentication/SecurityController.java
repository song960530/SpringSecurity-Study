package io.security.demobasicsecurity.인증개념이해_Authentication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController
public class SecurityController {
    @GetMapping("/")
    public String index() { return "home"; }
}
