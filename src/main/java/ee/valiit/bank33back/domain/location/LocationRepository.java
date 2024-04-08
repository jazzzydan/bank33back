package ee.valiit.bank33back.domain.location;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Integer> {
    @Query("select l from Location l where (l.city.id = :cityId or 0 = :cityId) and l.status = :status order by l.city.name, l.name")
    List<Location> findLocationsBy(Integer cityId, String status);

    @Query("select (count(l) > 0) from Location l where upper(l.name) = upper(:locationName)")
    Boolean locationNameExists(String locationName);

}