package ee.valiit.bank33back.business.city;

import ee.valiit.bank33back.business.city.dto.CityInfo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CityController {

    private CityService cityService;


    @GetMapping("/cities")
    public List<CityInfo> getCities() {
        return cityService.getCities();
    }
}
