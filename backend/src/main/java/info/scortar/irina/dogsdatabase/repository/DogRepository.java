package info.scortar.irina.dogsdatabase.repository;

import info.scortar.irina.dogsdatabase.model.Dog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DogRepository extends JpaRepository<Dog, Long> {

    @Query(value = "select count(1) from Dog d inner join d.appointments a inner join a.vet v where d.id = ?1")
    Long findVetIdsForDog(Long dog_id);
}
