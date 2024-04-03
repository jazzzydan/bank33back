package ee.valiit.bank33back.domain.location;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Integer> {
    @Query("select l from Location l where (l.city.id = ?1 or 0 = ?1) order by l.city.name, l.name")
    List<Location> findLocationsBy(Integer cityId);

}