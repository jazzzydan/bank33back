package ee.valiit.bank33back.business;

import ee.valiit.bank33back.business.dto.LoginResponse;
import ee.valiit.bank33back.domain.user.User;
import ee.valiit.bank33back.domain.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {

    private UserRepository userRepository;


    public LoginResponse login(String username, String password) {
        User user = userRepository.findUserBy(username, password, Status.ACTIVE);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUserId(user.getId());
        loginResponse.setRoleName(user.getRole().getName());

        return loginResponse;
    }
}
