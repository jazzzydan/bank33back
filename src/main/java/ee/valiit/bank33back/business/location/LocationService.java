package ee.valiit.bank33back.business.location;

import ee.valiit.bank33back.domain.location.Location;
import ee.valiit.bank33back.domain.location.LocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LocationService {

    private LocationRepository locationRepository;

    public void findAtmLocations(Integer cityId) {
        List<Location> locations = locationRepository.findLocationsBy(cityId);

    }

}
