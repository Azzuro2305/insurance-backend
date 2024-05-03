package insurance.project.repo;

import insurance.project.entity.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChildRepo extends JpaRepository<Child, UUID> {
}
