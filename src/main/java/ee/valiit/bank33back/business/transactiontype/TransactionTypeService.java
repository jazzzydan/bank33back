package ee.valiit.bank33back.business.transactiontype;

import ee.valiit.bank33back.business.transactiontype.dto.TransactionTypeInfoExtended;
import ee.valiit.bank33back.domain.transaction.transactiontype.TransactionType;
import ee.valiit.bank33back.domain.transaction.transactiontype.TransactionTypeMapper;
import ee.valiit.bank33back.domain.transaction.transactiontype.TransactionTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TransactionTypeService {

    private final TransactionTypeRepository transactionTypeRepository;
    private final TransactionTypeMapper transactionTypeMapper;


    public List<TransactionTypeInfoExtended> getTransactionTypes() {
        List<TransactionType> transactionTypes = transactionTypeRepository.findAll();
        return transactionTypeMapper.toTransactionTypeInfosExtended(transactionTypes);
    }
}
