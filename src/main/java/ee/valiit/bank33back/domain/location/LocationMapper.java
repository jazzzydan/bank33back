package ee.valiit.bank33back.domain.location;


import ee.valiit.bank33back.business.Status;
import ee.valiit.bank33back.business.location.dto.LocationInfo;
import ee.valiit.bank33back.business.location.dto.LocationInfoView;
import ee.valiit.bank33back.business.location.dto.LocationInfoExtended;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface LocationMapper {

    @Mapping(source = "city.name", target = "cityName")
    @Mapping(source = "id", target = "locationId")
    @Mapping(source = "name", target = "locationName")
    LocationInfo toLocationInfo(Location location);

    List<LocationInfo> toLocationInfos(List<Location> locations);


    @Mapping(source = "name", target = "locationName")
    @Mapping(source = "numberOfAtms", target = "numberOfAtms")
    LocationInfoView toLocationInfoView(Location location);


    @Mapping(source = "locationName", target = "name")
    @Mapping(source = "numberOfAtms", target = "numberOfAtms")
    @Mapping(constant = Status.ACTIVE, target = "status")
    Location toLocation(LocationInfoExtended locationInfoExtended);


    @Mapping(source = "city.id", target = "cityId")
    @Mapping(source = "name", target = "locationName")
    @Mapping(source = "numberOfAtms", target = "numberOfAtms")

    LocationInfoExtended toLocationInfoExtended(Location location);




    @Mapping(source = "locationName", target = "name")
    @Mapping(source = "numberOfAtms", target = "numberOfAtms")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Location updateLocation(LocationInfoExtended locationInfoExtended, @MappingTarget Location location);


}