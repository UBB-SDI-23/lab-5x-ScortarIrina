package info.scortar.irina.dogsdatabase.repository;

import info.scortar.irina.dogsdatabase.model.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepository extends JpaRepository<Dog, Long> {
}
