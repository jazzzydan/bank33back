package ee.valiit.bank33back.domain.city;

import ee.valiit.bank33back.business.city.dto.CityInfo;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CityMapper {

    @Mapping(source = "id", target = "cityId")
    @Mapping(source = "name", target = "cityName")
    CityInfo toCityInfo(City city);

    List<CityInfo> toCityInfos(List<City> cities);


}