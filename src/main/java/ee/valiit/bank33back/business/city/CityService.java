package ee.valiit.bank33back.business.city;

import ee.valiit.bank33back.domain.city.City;
import ee.valiit.bank33back.domain.city.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CityService {

    private CityRepository cityRepository;

    public void getCities() {
        List<City> cities = cityRepository.findAll();

    }
}
