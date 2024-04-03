package ee.valiit.bank33back.business.city;

import ee.valiit.bank33back.business.city.dto.CityInfo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CityController {

    private CityService cityService;


    @GetMapping("/cities")
    @Operation(
            summary = "Leiab süsteemist (andmebaasist city tabelist) kõik linnad.",
            description = "Tagastab info koos cityId ja cityName'ga")
    public List<CityInfo> getCities() {
        return cityService.getCities();
    }
}
