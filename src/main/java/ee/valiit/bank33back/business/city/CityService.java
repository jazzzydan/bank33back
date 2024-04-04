package ee.valiit.bank33back.business.city;

import ee.valiit.bank33back.business.city.dto.CityInfo;
import ee.valiit.bank33back.domain.location.city.City;
import ee.valiit.bank33back.domain.location.city.CityMapper;
import ee.valiit.bank33back.domain.location.city.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    public List<CityInfo> getCities() {
        List<City> cities = cityRepository.findAll();
        return cityMapper.toCityInfos(cities);
    }

}
