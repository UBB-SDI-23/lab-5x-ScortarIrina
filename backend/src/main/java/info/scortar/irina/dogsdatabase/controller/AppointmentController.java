package info.scortar.irina.dogsdatabase.controller;

import info.scortar.irina.dogsdatabase.mapper.Mapper;
import info.scortar.irina.dogsdatabase.model.Appointment;
import info.scortar.irina.dogsdatabase.DTOs.AppointmentDTO;
import info.scortar.irina.dogsdatabase.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private Mapper mapper;

    @GetMapping("/appointments")
    List<AppointmentDTO> getAllAppointments() {
        return appointmentService.getAllAppointments()
                .stream()
                .map(mapper::toAppointmentDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/appointments/{id}")
    Optional<AppointmentDTO> getAppointmentById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id)
                .stream()
                .map(mapper::toAppointmentDTO)
                .findFirst();
    }

    @GetMapping("/appointments-of-dog/{dog_id}")
    List<AppointmentDTO> getAppointmentsOfDog(@PathVariable Long dog_id) {
        return appointmentService.getAppointmentsOfDog(dog_id)
                .stream()
                .map(mapper::toAppointmentDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/appointments")
    void addAppointment(@RequestBody @Valid Appointment appointment) {
        this.appointmentService.addAppointment(appointment);
    }

    @PutMapping("/appointments/{id}")
    void updateAppointment(@RequestBody @Valid Appointment newAppointment, @PathVariable Long id) {
        newAppointment.setId(id);
        appointmentService.updateAppointment(newAppointment);
    }

    @DeleteMapping("/appointments/{id}")
    void deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName =((FieldError) error).getField();
            String errorMessage =error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
