package insurance.project.repo;

import insurance.project.entity.PremiumRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PremiumRateRepo extends JpaRepository<PremiumRate, UUID> {
}
