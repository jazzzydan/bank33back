package ee.valiit.bank33back.domain.location.locationimage;

import ee.valiit.bank33back.business.location.dto.LocationRequest;
import ee.valiit.bank33back.util.StringConverter;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface LocationImageMapper {


    @Mapping(source = "imageData", target = "data", qualifiedByName = "toImageData")
    LocationImage toLocationImage(LocationRequest locationRequest);

    @Named("toImageData")
    static byte[] toImageData(String imageData) {
        byte[] bytes = StringConverter.stringToBytes(imageData);
        return bytes;
    }


}
