package sixgarlic.potenday.travelroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sixgarlic.potenday.travelroom.model.Room;
import sixgarlic.potenday.travelroom.model.Travel;
import sixgarlic.potenday.user.model.User;

import java.util.Optional;

@Repository
public interface TravelRepository extends JpaRepository<Travel, Long> {
    Optional<Travel> findByUserAndRoom(User user, Room room);
}
