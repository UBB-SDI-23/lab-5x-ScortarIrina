package info.scortar.irina.dogsdatabase.repository;

import info.scortar.irina.dogsdatabase.model.Vet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends JpaRepository<Vet, Long> {

    @Query(value = "select count(1) from Vet v inner join v.appointments a inner join a.dog d where v.id = ?1")
    Long findDogIdsForVet(Long vet_id);
}
