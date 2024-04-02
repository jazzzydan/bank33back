package ee.valiit.bank33back.business.city;

import ee.valiit.bank33back.business.city.dto.CityInfo;
import ee.valiit.bank33back.domain.city.City;
import ee.valiit.bank33back.domain.city.CityMapper;
import ee.valiit.bank33back.domain.city.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CityService {

    private CityRepository cityRepository;
    private CityMapper cityMapper;

    public List<CityInfo> getCities() {
        List<City> cities = cityRepository.findAll();
        return cityMapper.toCityInfos(cities);
    }

}
