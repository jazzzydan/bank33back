package ee.valiit.bank33back.business.city;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CityController {

    private CityService cityService;


    @GetMapping("/cities")
    public void getCities() {
        cityService.getCities();
    }
}
