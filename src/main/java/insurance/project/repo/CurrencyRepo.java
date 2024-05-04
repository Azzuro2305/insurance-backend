package insurance.project.repo;

import insurance.project.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CurrencyRepo extends JpaRepository<Currency, UUID> {
}
