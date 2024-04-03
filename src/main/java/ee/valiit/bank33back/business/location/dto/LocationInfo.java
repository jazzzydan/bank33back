package ee.valiit.bank33back.business.location.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link ee.valiit.bank33back.domain.location.Location}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationInfo implements Serializable {
    private Integer locationId;
    private String cityName;
    private String locationName;
}