package sixgarlic.potenday.commandment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sixgarlic.potenday.commandment.model.Commandment;

public interface CommandmentRepository extends JpaRepository<Commandment, Long> {
}
