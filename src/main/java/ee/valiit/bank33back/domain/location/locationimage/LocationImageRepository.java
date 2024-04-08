package ee.valiit.bank33back.domain.location.locationimage;

import ee.valiit.bank33back.domain.location.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface LocationImageRepository extends JpaRepository<LocationImage, Integer> {

    @Query("select l from LocationImage l where l.location.id = :locationId")
    Optional<LocationImage> findLocationImageBy(Integer locationId);

    @Transactional
    @Modifying
    @Query("delete from LocationImage l where l.location.id = :locationId")
    void deleteLocationImageBy(Integer locationId);


}