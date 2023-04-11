package info.scortar.irina.dogsdatabase.repository;

import info.scortar.irina.dogsdatabase.model.Dog;
import org.springframework.data.repository.CrudRepository;

public interface DogRepository extends CrudRepository<Dog, Long> {
}
