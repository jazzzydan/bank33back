package ee.valiit.bank33back.business.login;

import ee.valiit.bank33back.business.Status;
import ee.valiit.bank33back.business.login.dto.LoginResponse;
import ee.valiit.bank33back.domain.user.User;
import ee.valiit.bank33back.domain.user.UserMapper;
import ee.valiit.bank33back.domain.user.UserRepository;
import ee.valiit.bank33back.infrastructure.validation.ValidationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public LoginResponse login(String username, String password) {
        Optional<User> optionalUser = userRepository.findUserBy(username, password, Status.ACTIVE);
        User user = ValidationService.getValidExistingUser(optionalUser);
        return userMapper.toLoginResponse(user);
    }

}
