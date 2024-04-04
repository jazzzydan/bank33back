package ee.valiit.bank33back.business.transactiontype;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/atm")
public class TransactionTypeController {

    private final TransactionTypeService transactionTypeService;

    @GetMapping("/transaction-types")
    public void getTransactionTypes() {
        transactionTypeService.getTransactionTypes();
    }
}
