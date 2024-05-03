package insurance.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Currency;
import java.util.UUID;

@Repository
public interface CurrencyRepo extends JpaRepository<Currency, UUID> {
}
