package insurance.project.repo;

import insurance.project.entity.Country;
import insurance.project.entity.InsuredPerson;
import insurance.project.entity.OutboundProposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface InsuredPersonRepo extends JpaRepository<InsuredPerson, UUID> {
    Optional<InsuredPerson> findByPassportNumber(String passportNumber);
    List<InsuredPerson> findByPassportNumberAndCountryId(String passportNumber, UUID passportIssuedCountry);
}
