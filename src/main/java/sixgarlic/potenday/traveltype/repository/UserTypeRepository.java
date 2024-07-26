package sixgarlic.potenday.traveltype.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sixgarlic.potenday.traveltype.model.UserType;

import java.util.Optional;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Long> {

    @Query(value = "select ut from UserType ut where ut.id = :userId and ut.isDefault = :isDefault")
    Optional<UserType> findByUserIdAndIsDefault(@Param("userId") Long userId, @Param("isDefault") boolean isDefault);
}
