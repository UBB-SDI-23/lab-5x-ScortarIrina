package info.scortar.irina.dogsdatabase.service;

import info.scortar.irina.dogsdatabase.controller.AppointmentController;
import info.scortar.irina.dogsdatabase.controller.DogController;
import info.scortar.irina.dogsdatabase.model.Appointment;
import info.scortar.irina.dogsdatabase.model.Dog;
import info.scortar.irina.dogsdatabase.repository.AppointmentRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Map<String, Object> getAllAppointments(int page, int pSize) {
        List<Appointment> appointments = new ArrayList<>();

        Map<String, Object> response = new HashMap<>();

        if (page > -1) {
            Pageable p = PageRequest.of(page, pSize > 0 ? pSize : AppointmentController.PAGE_SIZE, Sort.by("id"));

            Page<Appointment> pageAppointments = appointmentRepository.findAll(p);

            response.put("appointments", pageAppointments.getContent());
            response.put("currentPage", pageAppointments.getNumber());
            response.put("totalItems", pageAppointments.getTotalElements());
            response.put("totalPages", pageAppointments.getTotalPages());

            return response;
        } else {
            appointmentRepository.findAll().forEach(appointments::add);

            response.put("appointments", appointments);
            response.put("currentPage", 0);
            response.put("totalItems", appointments.size());
            response.put("totalPages", 1);
        }

        return response;
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
        for (Appointment appointment : appointmentRepository.findAll()) {
            if (Objects.equals(appointment.getDog().getId(), dogId)) {
                appointments_of_dog.add(appointment);
            }
        }
        return appointments_of_dog;
    }
}
