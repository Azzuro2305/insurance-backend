package insurance.project.repo;

import insurance.project.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AgentRepo extends JpaRepository<Agent, UUID> {
    Optional<Agent> findAgentByLicenseNumber(String licenseNumber);
}
