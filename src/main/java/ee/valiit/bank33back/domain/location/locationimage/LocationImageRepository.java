package ee.valiit.bank33back.domain.location.locationimage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LocationImageRepository extends JpaRepository<LocationImage, Integer> {

    @Query("select l from LocationImage l where l.location.id = :locationId")
    Optional<LocationImage> findLocationImageBy(Integer locationId);

}