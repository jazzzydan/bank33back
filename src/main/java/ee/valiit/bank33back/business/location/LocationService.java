package ee.valiit.bank33back.business.location;

import ee.valiit.bank33back.business.location.dto.LocationInfo;
import ee.valiit.bank33back.domain.location.Location;
import ee.valiit.bank33back.domain.location.LocationMapper;
import ee.valiit.bank33back.domain.location.LocationRepository;
import ee.valiit.bank33back.domain.transaction.locationtransactiontype.LocationTransactionTypeRepository;
import ee.valiit.bank33back.domain.transaction.transactiontype.TransactionType;
import ee.valiit.bank33back.infrastructure.validation.ValidationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LocationService {

    private LocationRepository locationRepository;
    private LocationMapper locationMapper;
    private LocationTransactionTypeRepository locationTransactionTypeRepository;

    public List<LocationInfo> findAtmLocations(Integer cityId) {
        List<Location> locations = locationRepository.findLocationsBy(cityId);
        ValidationService.validateLocationExists(locations);

        List<LocationInfo> locationInfos = locationMapper.toLocationInfos(locations);

        for (LocationInfo locationInfo : locationInfos) {
            List<TransactionType> transactionTypes = locationTransactionTypeRepository.findTransactionTypesBy(locationInfo.getLocationId());

        }


        return locationInfos;
    }



}
