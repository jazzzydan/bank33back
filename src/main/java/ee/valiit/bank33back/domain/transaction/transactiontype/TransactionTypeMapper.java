package ee.valiit.bank33back.domain.transaction.transactiontype;

import ee.valiit.bank33back.business.location.dto.TransactionTypeInfo;
import ee.valiit.bank33back.business.location.dto.TransactionTypeInfoExtended;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TransactionTypeMapper {

    @Named("toTransactionTypeInfo")
    @Mapping(source = "name", target = "transactionTypeName")
    TransactionTypeInfo toTransactionTypeInfo(TransactionType transactionType);

    @IterableMapping(qualifiedByName = "toTransactionTypeInfo")
    List<TransactionTypeInfo> toTransactionTypeInfos(List<TransactionType> transactionTypes);

    @Named("toTransactionTypeInfoExtended")
    @Mapping(source = "id", target = "transactionTypeId")
    @Mapping(source = "name", target = "transactionTypeName")
    @Mapping(constant = "false", target = "isAvailable")
    TransactionTypeInfoExtended toTransactionTypeInfoExtended(TransactionType transactionType);

    @IterableMapping(qualifiedByName = "toTransactionTypeInfoExtended")
    List<TransactionTypeInfoExtended> toTransactionTypeInfosExtended(List<TransactionType> transactionTypes);

}