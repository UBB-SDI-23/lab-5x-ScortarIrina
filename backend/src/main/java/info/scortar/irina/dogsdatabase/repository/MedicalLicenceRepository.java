package info.scortar.irina.dogsdatabase.repository;

import info.scortar.irina.dogsdatabase.model.MedicalLicense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MedicalLicenceRepository extends JpaRepository<MedicalLicense, Long> {
}
