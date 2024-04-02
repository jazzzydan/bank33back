package ee.valiit.bank33back.business.city;

import ee.valiit.bank33back.business.city.dto.CityInfo;
import ee.valiit.bank33back.domain.city.City;
import ee.valiit.bank33back.domain.city.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CityService {

    private CityRepository cityRepository;

    public List<CityInfo> getCities() {
        List<City> cities = cityRepository.findAll();
        return toCityInfos(cities);
    }

    private static List<CityInfo> toCityInfos(List<City> cities) {

        List<CityInfo> cityInfos = new ArrayList<>();
        for (City city : cities) {
            CityInfo cityInfo = toCityInfo(city);
            cityInfos.add(cityInfo);
        }
        return cityInfos;
    }

    private static CityInfo toCityInfo(City city) {
        CityInfo cityInfo = new CityInfo();
        cityInfo.setCityId(city.getId());
        cityInfo.setCityName(city.getName());
        return cityInfo;
    }
}
