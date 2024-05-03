package insurance.project.repo;

import insurance.project.entity.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BeneficiaryRepo extends JpaRepository<Beneficiary, UUID> {
}
