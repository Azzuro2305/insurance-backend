package insurance.project.repo;

import insurance.project.entity.InsuredPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InsuredPersonRepo extends JpaRepository<InsuredPerson, UUID> {
    Optional<InsuredPerson> findByPassportNumber(String passportNumber);
}
