package ee.valiit.bank33back.business.location;

import ee.valiit.bank33back.business.location.dto.LocationInfo;
import ee.valiit.bank33back.domain.location.Location;
import ee.valiit.bank33back.domain.location.LocationMapper;
import ee.valiit.bank33back.domain.location.LocationRepository;
import ee.valiit.bank33back.infrastructure.validation.ValidationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static ee.valiit.bank33back.infrastructure.validation.ValidationService.validateLocationExists;

@Service
@AllArgsConstructor
public class LocationService {

    private LocationRepository locationRepository;
    private LocationMapper locationMapper;

    public List<LocationInfo> findAtmLocations(Integer cityId) {
        List<Location> locations = locationRepository.findLocationsBy(cityId);
        ValidationService.validateLocationExists(locations);
        return locationMapper.toLocationInfos(locations);
    }



}
