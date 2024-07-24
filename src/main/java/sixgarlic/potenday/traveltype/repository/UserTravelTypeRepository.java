package sixgarlic.potenday.traveltype.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sixgarlic.potenday.traveltype.model.UserTravelType;

@Repository
public interface UserTravelTypeRepository extends JpaRepository<UserTravelType, Long> {
}
