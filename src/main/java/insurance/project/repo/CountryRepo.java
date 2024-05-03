package insurance.project.repo;

import insurance.project.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CountryRepo extends JpaRepository<Country, UUID> {
}
