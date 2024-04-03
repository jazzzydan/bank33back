package ee.valiit.bank33back.domain.transaction.transactiontype;

import ee.valiit.bank33back.business.location.dto.TransactionTypeInfo;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TransactionTypeMapper {
@Mapping(source = "name", target = "transactionTypeName")
    TransactionTypeInfo toTransactionTypeInfo(TransactionType transactionType);
    List<TransactionTypeInfo> toTransactionTypeInfos(List<TransactionType> transactionTypes);


}