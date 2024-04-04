package ee.valiit.bank33back.business.transactiontype;

import ee.valiit.bank33back.business.transactiontype.dto.TransactionTypeInfoExtended;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/atm")
public class TransactionTypeController {

    private final TransactionTypeService transactionTypeService;

    @GetMapping("/transaction-types")
    @Operation(summary = "Leiab süsteemist kõik tehingutüübid")
    public List<TransactionTypeInfoExtended> getTransactionTypes() {
       return transactionTypeService.getTransactionTypes();
    }
}
