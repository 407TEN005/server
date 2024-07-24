package sixgarlic.potenday.traveltype.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sixgarlic.potenday.traveltype.model.TravelType;

import java.util.Optional;

@Repository
public interface TravelTypeRepository extends JpaRepository<TravelType, String> {
}
