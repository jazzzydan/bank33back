package ee.valiit.bank33back.domain.transaction.locationtransactiontype;

import ee.valiit.bank33back.domain.location.Location;
import ee.valiit.bank33back.domain.transaction.transactiontype.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LocationTransactionTypeRepository extends JpaRepository<LocationTransactionType, Integer> {
    @Query("select l.transactionType from LocationTransactionType l where l.location.id = :locationId order by l.transactionType.id")
    List<TransactionType> findTransactionTypesBy(Integer locationId);

    @Transactional
    @Modifying
    @Query("delete from LocationTransactionType l where l.location.id = :locationId")
    void deleteLocationTransactionTypesBy(Integer locationId);

}