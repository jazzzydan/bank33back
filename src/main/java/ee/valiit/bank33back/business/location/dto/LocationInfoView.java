package ee.valiit.bank33back.business.location.dto;

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
public class LocationInfoView implements Serializable {
    private String locationName;
    private Integer numberOfAtms;
    private String imageData;
    private List<TransactionTypeInfo> transactionTypes;
}