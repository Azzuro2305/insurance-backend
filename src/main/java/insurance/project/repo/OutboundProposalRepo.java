package insurance.project.repo;

import insurance.project.entity.OutboundProposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OutboundProposalRepo extends JpaRepository<OutboundProposal, UUID> {
}
