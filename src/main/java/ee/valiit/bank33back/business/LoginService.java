package ee.valiit.bank33back.business;

import ee.valiit.bank33back.business.dto.LoginResponse;
import ee.valiit.bank33back.domain.user.User;
import ee.valiit.bank33back.domain.user.UserRepository;
import ee.valiit.bank33back.infrastructure.validation.ValidationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginService {

    private UserRepository userRepository;


    public LoginResponse login(String username, String password) {

        Optional<User> optionalUser = userRepository.findUserBy(username, password, Status.ACTIVE);

        User user = ValidationService.getValidExistingUser(optionalUser);

        // todo: Võta MapStruct mapper kasutusele
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUserId(user.getId());
        loginResponse.setRoleName(user.getRole().getName());

        return loginResponse;
    }


}
