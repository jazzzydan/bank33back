package ee.valiit.bank33back.business.location.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class TransactionTypeInfoExtended extends TransactionTypeInfo {
    private Integer transactionTypeId;
    private Boolean isAvailable;


//    {
//        "transactionTypeId": 1,
//        "transactionTypeName": "raha sisse",
//        "isAvailable": false
//    },

    public Boolean isAvailable() {
        return isAvailable;
    }
}
