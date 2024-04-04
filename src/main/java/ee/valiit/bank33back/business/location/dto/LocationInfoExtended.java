package ee.valiit.bank33back.business.location.dto;

import ee.valiit.bank33back.business.transactiontype.dto.TransactionTypeInfoExtended;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link ee.valiit.bank33back.domain.location.Location}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationInfoExtended implements Serializable {
    private Integer cityId;

    @NotNull
    @Size(max = 255)
    private String locationName;

    @NotNull
    @Min(message = "Peab olema vähemalt 1 automaat", value = 1)
    private Integer numberOfAtms;

    private String imageData;

    private List<TransactionTypeInfoExtended> transactionTypes;

}