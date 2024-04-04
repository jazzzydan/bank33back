package ee.valiit.bank33back.business.location;

import ee.valiit.bank33back.business.location.dto.LocationInfo;
import ee.valiit.bank33back.business.location.dto.LocationRequest;
import ee.valiit.bank33back.business.location.dto.TransactionTypeInfo;
import ee.valiit.bank33back.business.location.dto.TransactionTypeInfoExtended;
import ee.valiit.bank33back.domain.location.Location;
import ee.valiit.bank33back.domain.location.LocationMapper;
import ee.valiit.bank33back.domain.location.LocationMapperImpl;
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
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
        handleImageData(locationRequest, location);
        // todo: käime läbi kõik request transaction typed
        // todo: konrtrollime kas isAvailable on true
        // todo: kui on true siis
        // todo: Loome uue entity objekti 'locationTransactionType
        // todo: lisame talle külge locationi
        // todo: transactionTypeId väärtuse abil leiame repositoriga ülesse entity (TransactionType) objekti
        // todo: ja lisame selle transacctionType objekti  'locationTransactionType' entity külge
        // todo: salvestame locationTransactionType andmebaasi


        for (TransactionTypeInfoExtended transactionTypeInfo : locationRequest.getTransactionTypes()) {

            if (transactionTypeInfo.isAvailable()) {
                TransactionType transactionType = transactionTypeRepository.getReferenceById(transactionTypeInfo.getTransactionTypeId());
                createAndSaveLocationTransactionType(location, transactionType);
            }

        }


    }


    private static LocationTransactionType createLocationTransactionType(Location location, TransactionType transactionType) {
        LocationTransactionType locationTransactionType = new LocationTransactionType();
        locationTransactionType.setLocation(location);
        locationTransactionType.setTransactionType(transactionType);
        return locationTransactionType;
    }

    private void createAndSaveLocationTransactionType(Location location, TransactionType transactionType) {
        LocationTransactionType locationTransactionType = createLocationTransactionType(location, transactionType);
        locationTransactionTypeRepository.save(locationTransactionType);
    }



    private void handleImageData(LocationRequest locationRequest, Location location) {
        if (hasImage(locationRequest.getImageData())) {
            createAndSaveLocationImage(locationRequest, location);
        }
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

    private static boolean hasImage(String imageData) {
        return !imageData.isEmpty();
    }

}
