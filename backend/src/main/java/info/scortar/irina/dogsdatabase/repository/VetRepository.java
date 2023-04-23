package info.scortar.irina.dogsdatabase.repository;

import info.scortar.irina.dogsdatabase.model.Vet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends JpaRepository<Vet, Long> {
}
