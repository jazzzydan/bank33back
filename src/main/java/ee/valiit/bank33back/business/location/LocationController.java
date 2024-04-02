package ee.valiit.bank33back.business.location;


import ee.valiit.bank33back.business.location.dto.LocationInfo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class LocationController {

    private LocationService locationService;

    @GetMapping("/atm/locations/city/{cityId}")
    public List<LocationInfo> findAtmLocations(@PathVariable Integer cityId) {
        return locationService.findAtmLocations(cityId);
    }
}
