package info.scortar.irina.dogsdatabase.repository;

import info.scortar.irina.dogsdatabase.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
}
