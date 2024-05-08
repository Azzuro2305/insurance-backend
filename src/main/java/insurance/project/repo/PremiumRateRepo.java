package insurance.project.repo;

import insurance.project.entity.PremiumRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PremiumRateRepo extends JpaRepository<PremiumRate, UUID> {
    @Query("SELECT p FROM PremiumRate p WHERE p.packages = :packages AND p.coveragePlan = :coveragePlan AND p.fromAge >= :fromAge AND p.toAge <= :toAge")
    Optional<PremiumRate> findPremiumRateByPackageAndCoveragePlanAndAgeRange(@Param("packages") String packages, @Param("coveragePlan") int coveragePlan, @Param("fromAge") int fromAge, @Param("toAge") int toAge);

    Double findRateById(UUID Id);
}
