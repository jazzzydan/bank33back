package ee.valiit.bank33back.domain.location.locationimage;

import ee.valiit.bank33back.business.location.dto.LocationInfoExtended;
import ee.valiit.bank33back.util.StringConverter;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, imports = {StringConverter.class})
public interface LocationImageMapper {

//    Nimelise meetodiga lahendus
//    @Mapping(source = "imageData", target = "data", qualifiedByName = "toImageData")
//    LocationImage toLocationImage(LocationRequest locationRequest);

    @Mapping( expression = "java(StringConverter.stringToBytes(locationRequest.getImageData()))", target = "data")
    LocationImage toLocationImage(LocationInfoExtended locationInfoExtended);





    @Named("toImageData")
    static byte[] toImageData(String imageData) {
        byte[] bytes = StringConverter.stringToBytes(imageData);
        return bytes;
    }


}
