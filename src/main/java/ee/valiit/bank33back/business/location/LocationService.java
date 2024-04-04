package ee.valiit.bank33back.business.location;

import ee.valiit.bank33back.business.location.dto.LocationInfo;
import ee.valiit.bank33back.business.location.dto.LocationRequest;
import ee.valiit.bank33back.business.location.dto.TransactionTypeInfo;
import ee.valiit.bank33back.business.location.dto.TransactionTypeInfoExtended;
import ee.valiit.bank33back.domain.location.Location;
import ee.valiit.bank33back.domain.location.LocationMapper;
import ee.valiit.bank33back.domain.location.LocationRepository;
import ee.valiit.bank33back.domain.location.city.City;
import ee.valiit.bank33back.domain.location.city.CityRepository;
import ee.valiit.bank33back.domain.location.locationimage.LocationImage;
import ee.valiit.bank33back.domain.location.locationimage.LocationImageMapper;
import ee.valiit.bank33back.domain.location.locationimage.LocationImageRepository;
import ee.valiit.bank33back.domain.transaction.locationtransactiontype.LocationTransactionType;
import ee.valiit.bank33back.domain.transaction.locationtransactiontype.LocationTransactionTypeRepository;
import ee.valiit.bank33back.domain.transaction.transactiontype.TransactionType;
import ee.valiit.bank33back.domain.transaction.transactiontype.TransactionTypeMapper;
import ee.valiit.bank33back.domain.transaction.transactiontype.TransactionTypeRepository;
import ee.valiit.bank33back.infrastructure.validation.ValidationService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class LocationService {

    private final CityRepository cityRepository;
    private final LocationRepository locationRepository;
    private final LocationImageRepository locationImageRepository;
    private final TransactionTypeRepository transactionTypeRepository;
    private final LocationTransactionTypeRepository locationTransactionTypeRepository;

    private final LocationMapper locationMapper;
    private final LocationImageMapper locationImageMapper;
    private final TransactionTypeMapper transactionTypeMapper;


    public List<LocationInfo> findAtmLocations(Integer cityId) {
        List<Location> locations = locationRepository.findLocationsBy(cityId);
        ValidationService.validateLocationExists(locations);
        return createLocationInfos(locations);
    }

    @Transactional
    public void addAtmLocation(LocationRequest locationRequest) {
        handleLocationNameAvailabilityValidation(locationRequest);
        Location location = createAndSaveLocation(locationRequest);
        handleImageData(locationRequest, location);
        createAndSaveLocationTransactionTypes(locationRequest, location);
    }

    private void handleLocationNameAvailabilityValidation(LocationRequest locationRequest) {
        boolean locationNameExists = locationRepository.locationNameExists(locationRequest.getLocationName());
        ValidationService.validateLocationNameAvailable(locationNameExists);
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

    private void handleImageData(LocationRequest locationRequest, Location location) {
        if (hasImage(locationRequest.getImageData())) {
            createAndSaveLocationImage(locationRequest, location);
        }
    }

    private static boolean hasImage(String imageData) {
        return !imageData.isEmpty();
    }

    private void createAndSaveLocationImage(LocationRequest locationRequest, Location location) {
        LocationImage locationImage = createLocationImage(locationRequest, location);
        locationImageRepository.save(locationImage);
    }

    private LocationImage createLocationImage(LocationRequest locationRequest, Location location) {
        LocationImage locationImage = locationImageMapper.toLocationImage(locationRequest);
        locationImage.setLocation(location);
        return locationImage;
    }

    private void createAndSaveLocationTransactionTypes(LocationRequest locationRequest, Location location) {
        List<LocationTransactionType> locationTransactionTypes = createLocationTransactionTypes(locationRequest, location);
        locationTransactionTypeRepository.saveAll(locationTransactionTypes);
    }

    private List<LocationTransactionType> createLocationTransactionTypes(LocationRequest locationRequest, Location location) {
        List<LocationTransactionType> locationTransactionTypes = new ArrayList<>();
        for (TransactionTypeInfoExtended transactionTypeInfo : locationRequest.getTransactionTypes()) {

            if (transactionTypeInfo.isAvailable()) {
                TransactionType transactionType = transactionTypeRepository.getReferenceById(transactionTypeInfo.getTransactionTypeId());
                LocationTransactionType locationTransactionType = createLocationTransactionType(location, transactionType);
                locationTransactionTypes.add(locationTransactionType);
            }

        }
        return locationTransactionTypes;
    }

    private static LocationTransactionType createLocationTransactionType(Location location, TransactionType transactionType) {
        LocationTransactionType locationTransactionType = new LocationTransactionType();
        locationTransactionType.setLocation(location);
        locationTransactionType.setTransactionType(transactionType);
        return locationTransactionType;
    }

}
