package ee.valiit.bank33back.business;

import ee.valiit.bank33back.business.dto.LoginResponse;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Resource
    private LoginService loginService;


    @GetMapping("/login")
    public void login(@RequestParam String username, @RequestParam String password) {
        loginService.login(username, password);
    }



}
