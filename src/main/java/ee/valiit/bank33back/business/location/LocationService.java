package ee.valiit.bank33back.business.location;

import ee.valiit.bank33back.business.Status;
import ee.valiit.bank33back.business.location.dto.*;
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
import ee.valiit.bank33back.util.StringConverter;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Transactional
    public void addAtmLocation(LocationInfoExtended locationInfoExtended) {
        handleLocationNameAvailabilityValidation(locationInfoExtended);
        Location location = createAndSaveLocation(locationInfoExtended);
        handleImageData(locationInfoExtended, location);
        createAndSaveLocationTransactionTypes(locationInfoExtended, location);
    }

    public LocationInfoExtended getAtmLocation(Integer locationId) {
        Location location = locationRepository.getReferenceById(locationId);
        String imageData = getImageData(locationId);
        List<TransactionType> transactionTypes = locationTransactionTypeRepository.findTransactionTypesBy(locationId);
        List<TransactionTypeInfoExtended> transactionTypeInfosExtended = transactionTypeMapper.toTransactionTypeInfosExtended(transactionTypes);
        LocationInfoExtended locationInfoExtended = locationMapper.toLocationInfoExtended(location);
        locationInfoExtended.setImageData(imageData);
        locationInfoExtended.setTransactionTypes(transactionTypeInfosExtended);
        return locationInfoExtended;
    }

    @Transactional
    public void updateAtmLocation(Integer locationId, LocationInfoExtended locationInfoExtended) {
        Location location = locationRepository.getReferenceById(locationId);
        locationMapper.updateLocation(locationInfoExtended, location);
        handleCityUpdate(locationInfoExtended, location);
        handleImageDataUpdate(locationInfoExtended, location);
        handleLocationTransactionTypesUpdate(locationInfoExtended, location);
        locationRepository.save(location);
    }

    public void removeAtmLocation(Integer locationId) {
        Location location = locationRepository.getReferenceById(locationId);
        location.setStatus(Status.DEACTIVATED);
        locationRepository.save(location);
    }

    public List<LocationInfo> findAtmLocations(Integer cityId) {
        List<Location> locations = locationRepository.findLocationsBy(cityId, Status.ACTIVE);
        ValidationService.validateLocationExists(locations);
        return createLocationInfos(locations);
    }

    private String getImageData(Integer locationId) {
        Optional<LocationImage> optionalLocationImage = locationImageRepository.findLocationImageBy(locationId);

        String imageData = "";
        if (optionalLocationImage.isPresent()) {
            imageData = StringConverter.bytesToString(optionalLocationImage.get().getData());
        }
        return imageData;
    }

    private void handleLocationNameAvailabilityValidation(LocationInfoExtended locationInfoExtended) {
        boolean locationNameExists = locationRepository.locationNameExists(locationInfoExtended.getLocationName());
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

    private Location createAndSaveLocation(LocationInfoExtended locationInfoExtended) {
        Location location = createLocation(locationInfoExtended);
        locationRepository.save(location);
        return location;
    }

    private Location createLocation(LocationInfoExtended locationInfoExtended) {
        City city = cityRepository.getReferenceById(locationInfoExtended.getCityId());
        Location location = locationMapper.toLocation(locationInfoExtended);
        location.setCity(city);
        return location;
    }

    private void handleImageData(LocationInfoExtended locationInfoExtended, Location location) {
        if (hasImage(locationInfoExtended.getImageData())) {
            createAndSaveLocationImage(locationInfoExtended, location);
        }
    }

    private static boolean hasImage(String imageData) {
        return !imageData.isEmpty();
    }

    private void createAndSaveLocationImage(LocationInfoExtended locationInfoExtended, Location location) {
        LocationImage locationImage = createLocationImage(locationInfoExtended, location);
        locationImageRepository.save(locationImage);
    }

    private LocationImage createLocationImage(LocationInfoExtended locationInfoExtended, Location location) {
        LocationImage locationImage = locationImageMapper.toLocationImage(locationInfoExtended);
        locationImage.setLocation(location);
        return locationImage;
    }

    private void createAndSaveLocationTransactionTypes(LocationInfoExtended locationInfoExtended, Location location) {
        List<LocationTransactionType> locationTransactionTypes = createLocationTransactionTypes(locationInfoExtended, location);
        locationTransactionTypeRepository.saveAll(locationTransactionTypes);
    }

    private List<LocationTransactionType> createLocationTransactionTypes(LocationInfoExtended locationInfoExtended, Location location) {
        List<LocationTransactionType> locationTransactionTypes = new ArrayList<>();
        for (TransactionTypeInfoExtended transactionTypeInfo : locationInfoExtended.getTransactionTypes()) {

            if (transactionTypeInfo.getIsAvailable()) {
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

    private void handleCityUpdate(LocationInfoExtended locationInfoExtended, Location location) {
        if (!haveSameCityId(locationInfoExtended, location)) {
            City city = cityRepository.getReferenceById(locationInfoExtended.getCityId());
            location.setCity(city);
        }
    }

    private static boolean haveSameCityId(LocationInfoExtended locationInfoExtended, Location location) {
        return location.getCity().getId().equals(locationInfoExtended.getCityId());
    }

    private void handleImageDataUpdate(LocationInfoExtended locationInfoExtended, Location location) {
        if (hasImage(locationInfoExtended.getImageData())) {
            locationImageRepository.deleteLocationImageBy(location.getId());
            createAndSaveLocationImage(locationInfoExtended, location);
        }
    }

    private void handleLocationTransactionTypesUpdate(LocationInfoExtended locationInfoExtended, Location location) {
        locationTransactionTypeRepository.deleteLocationTransactionTypesBy(location.getId());
        createAndSaveLocationTransactionTypes(locationInfoExtended, location);
    }

}
