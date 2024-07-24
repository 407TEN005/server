package sixgarlic.potenday.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sixgarlic.potenday.test.model.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
}
