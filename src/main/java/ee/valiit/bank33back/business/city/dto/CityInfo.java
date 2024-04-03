package ee.valiit.bank33back.business.city.dto;

import ee.valiit.bank33back.domain.location.city.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link City}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityInfo implements Serializable {
    private Integer cityId;
    private String cityName;
}