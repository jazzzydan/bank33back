package ee.valiit.bank33back.business.city.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link ee.valiit.bank33back.domain.city.City}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityInfo implements Serializable {
    private Integer cityId;
    private String cityName;
}