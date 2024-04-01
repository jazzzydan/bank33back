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
    public LoginResponse login(@RequestParam String username, @RequestParam String password) {



        // SEE siin all on juba äriloogika
        LoginResponse loginResponse = new LoginResponse(1, "admin");
        return loginResponse;
    }



}
