package ee.valiit.bank33back.business.location.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionTypeInfoExtended {
    private Integer transactionTypeId;
    private String transactionTypeName;
    private Boolean isAvailable;

}
