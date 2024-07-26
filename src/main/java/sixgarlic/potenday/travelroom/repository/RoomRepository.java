package sixgarlic.potenday.travelroom.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sixgarlic.potenday.travelroom.model.Room;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query(value = "select r from Room r join fetch r.travels t where t.user.id = :userId")
    List<Room> findAllByUserId(@Param("userId") Long userId, Sort sort);

    @Query(value = "select r from Room r join fetch r.travels t where t.room.id = :roomId ")
    Optional<Room> findByRoomId(@Param("roomId") Long roomId);
}
