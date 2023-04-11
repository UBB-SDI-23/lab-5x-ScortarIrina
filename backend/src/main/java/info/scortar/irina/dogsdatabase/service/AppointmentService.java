package info.scortar.irina.dogsdatabase.service;

import info.scortar.irina.dogsdatabase.model.Appointment;
import info.scortar.irina.dogsdatabase.repository.AppointmentRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        appointmentRepository.findAll().forEach(appointments::add);
        return appointments;
    }

    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    public void addAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    public void updateAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    public List<Appointment> getAppointmentsOfDog(Long dogId) {
        List<Appointment> appointments_of_dog = new ArrayList<>();
        for (Appointment appointment:appointmentRepository.findAll()) {
            if (Objects.equals(appointment.getDog().getId(), dogId)) {
                appointments_of_dog.add(appointment);
            }
        }
        return appointments_of_dog;
    }
}
