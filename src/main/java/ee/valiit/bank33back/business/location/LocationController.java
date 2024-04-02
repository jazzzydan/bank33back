package ee.valiit.bank33back.business.location;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class LocationController {

    private LocationService locationService;

    @GetMapping("/atm/locations/city/{cityId}")
    public void findAtmLocations(@PathVariable Integer cityId) {
        locationService.findAtmLocations(cityId);
    }
}
