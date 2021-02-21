package io.security.demobasicsecurity.필터초기화및다중보안설정;

import org.springframework.web.bind.annotation.GetMapping;

//@RestController
public class SecurityController
{

    @GetMapping("/")
    public String index() { return "home"; }





    @GetMapping("loginPage")
    public String loginPage()
    {
        return "loginPage";
    }





    @GetMapping("/user")
    public String user()
    {
        return "user";
    }





    @GetMapping("/admin/pay")
    public String adminPay()
    {

        return "admin/pay";
    }





    @GetMapping("/admin/**")
    public String admin()
    {

        return "admin";
    }





    @GetMapping("/denied")
    public String denied()
    {
        return "Access is denied";
    }





    @GetMapping("/login")
    public String login()
    {
        return "login";
    }

}
