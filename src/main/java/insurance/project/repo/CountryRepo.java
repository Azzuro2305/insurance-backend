package insurance.project.repo;

import insurance.project.entity.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.UUID;

@Repository
public interface CountryRepo extends JpaRepository<Country, UUID> {
    Country findByCountryName(String countryName);
    Country findByCountryID(UUID countryID);
    ArrayList<Country> findAll();

//    Page<Country> findAll(Pageable pageable);
}
