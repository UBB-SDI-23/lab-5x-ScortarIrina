package info.scortar.irina.dogsdatabase.repository;

import info.scortar.irina.dogsdatabase.model.Appointment;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
}
