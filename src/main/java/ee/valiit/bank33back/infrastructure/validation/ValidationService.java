package ee.valiit.bank33back.infrastructure.validation;

import ee.valiit.bank33back.domain.location.Location;
import ee.valiit.bank33back.domain.user.User;
import ee.valiit.bank33back.infrastructure.exception.DataNotFoundException;
import ee.valiit.bank33back.infrastructure.exception.ForbiddenException;

import java.util.List;
import java.util.Optional;

import static ee.valiit.bank33back.infrastructure.error.Error.*;

public class ValidationService {

    public static User getValidExistingUser(Optional<User> optionalUser) {
        if (optionalUser.isEmpty()) {
            throw new ForbiddenException(INCORRECT_CREDENTIALS.getMessage(), INCORRECT_CREDENTIALS.getErrorCode());
        }
        return optionalUser.get();
    }

    public static void validateLocationExists(List<Location> locations) {
        if (locations.isEmpty()) {
            throw new DataNotFoundException(NO_LOCATION_FOUND.getMessage(), NO_LOCATION_FOUND.getErrorCode());
        }
    }

    public static void validateLocationNameAvailable(boolean locationNameExists) {
        if (locationNameExists) {
            throw new ForbiddenException(LOCATION_UNAVAILABLE.getMessage(), LOCATION_UNAVAILABLE.getErrorCode());
        }
    }


}
