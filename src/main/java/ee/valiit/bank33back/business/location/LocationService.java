package ee.valiit.bank33back.business.location;

import ee.valiit.bank33back.business.location.dto.LocationInfo;
import ee.valiit.bank33back.business.location.dto.LocationRequest;
import ee.valiit.bank33back.business.location.dto.TransactionTypeInfo;
import ee.valiit.bank33back.domain.location.Location;
import ee.valiit.bank33back.domain.location.LocationMapper;
import ee.valiit.bank33back.domain.location.LocationMapperImpl;
import ee.valiit.bank33back.domain.location.LocationRepository;
import ee.valiit.bank33back.domain.location.city.City;
import ee.valiit.bank33back.domain.location.city.CityRepository;
import ee.valiit.bank33back.domain.transaction.locationtransactiontype.LocationTransactionTypeRepository;
import ee.valiit.bank33back.domain.transaction.transactiontype.TransactionType;
import ee.valiit.bank33back.domain.transaction.transactiontype.TransactionTypeMapper;
import ee.valiit.bank33back.infrastructure.validation.ValidationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LocationService {

    private LocationRepository locationRepository;
    private LocationTransactionTypeRepository locationTransactionTypeRepository;
    private CityRepository cityRepository;

    private LocationMapper locationMapper;
    private TransactionTypeMapper transactionTypeMapper;


    public List<LocationInfo> findAtmLocations(Integer cityId) {
        List<Location> locations = locationRepository.findLocationsBy(cityId);
        ValidationService.validateLocationExists(locations);
        return createLocationInfos(locations);
    }

    private List<LocationInfo> createLocationInfos(List<Location> locations) {
        List<LocationInfo> locationInfos = locationMapper.toLocationInfos(locations);
        addTransactionTypes(locationInfos);
        return locationInfos;
    }

    private void addTransactionTypes(List<LocationInfo> locationInfos) {
        for (LocationInfo locationInfo : locationInfos) {
            List<TransactionType> transactionTypes = locationTransactionTypeRepository.findTransactionTypesBy(locationInfo.getLocationId());
            List<TransactionTypeInfo> transactionTypeInfos = transactionTypeMapper.toTransactionTypeInfos(transactionTypes);
            locationInfo.setTransactionTypes(transactionTypeInfos);
        }
    }


    public void addAtmLocation(LocationRequest locationRequest) {
        Location location = createAndSaveLocation(locationRequest);



    }

    private Location createAndSaveLocation(LocationRequest locationRequest) {
        Location location = createLocation(locationRequest);
        locationRepository.save(location);
        return location;
    }

    private Location createLocation(LocationRequest locationRequest) {
        City city = cityRepository.getReferenceById(locationRequest.getCityId());
        Location location = locationMapper.toLocation(locationRequest);
        location.setCity(city);
        return location;
    }
}
