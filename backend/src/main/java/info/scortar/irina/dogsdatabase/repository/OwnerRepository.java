package info.scortar.irina.dogsdatabase.repository;

import info.scortar.irina.dogsdatabase.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
