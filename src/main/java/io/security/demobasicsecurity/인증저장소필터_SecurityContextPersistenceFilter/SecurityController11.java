package io.security.demobasicsecurity.인증저장소필터_SecurityContextPersistenceFilter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController11
{

    @GetMapping("/")
    public String index() { return "home"; }

}
