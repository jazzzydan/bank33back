package ee.valiit.bank33back.infrastructure.validation;

import ee.valiit.bank33back.domain.user.User;
import ee.valiit.bank33back.infrastructure.exception.ForbiddenException;

import java.util.Optional;

import static ee.valiit.bank33back.infrastructure.error.Error.INCORRECT_CREDENTIALS;

public class ValidationService {

    public static User getValidExistingUser(Optional<User> optionalUser) {
        if (optionalUser.isEmpty()) {
            throw new ForbiddenException(INCORRECT_CREDENTIALS.getMessage(), INCORRECT_CREDENTIALS.getErrorCode());
        }
        return optionalUser.get();
    }
}
