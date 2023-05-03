package info.scortar.irina.dogsdatabase.controller;

import info.scortar.irina.dogsdatabase.DTOs.DogDTO;
import info.scortar.irina.dogsdatabase.mapper.Mapper;
import info.scortar.irina.dogsdatabase.model.Appointment;
import info.scortar.irina.dogsdatabase.DTOs.AppointmentDTO;
import info.scortar.irina.dogsdatabase.model.Dog;
import info.scortar.irina.dogsdatabase.model.Vet;
import info.scortar.irina.dogsdatabase.service.AppointmentService;
import info.scortar.irina.dogsdatabase.service.DogService;
import info.scortar.irina.dogsdatabase.service.VetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class AppointmentController {

    public static int PAGE_SIZE = 100;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DogService dogService;

    @Autowired
    private VetService vetService;

    @Autowired
    private Mapper mapper;

    @GetMapping("/appointments")
    ResponseEntity<Map<String, Object>> getAllAppointments(@RequestParam Optional<String> page,
                                                           @RequestParam Optional<String> size) {
        int p = Integer.parseInt(page.orElse("0"));

        int pSize = Integer.parseInt(size.orElse("0"));

        Map<String, Object> ret = appointmentService.getAllAppointments(p, pSize);

        List<AppointmentDTO> dtos = ((List<Appointment>) ret.get("appointments"))
                .stream()
                .map(mapper::toAppointmentDTO)
                .collect(Collectors.toList());

        ret.put("appointments", dtos);

        return new ResponseEntity<>(ret, HttpStatus.OK);
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
    void addAppointment(@RequestBody @Valid AppointmentDTO appointment) throws ParseException {
        Appointment appointment2;
        appointment2 = mapper.fromAppointmentDTO(appointment);
        Optional<Dog> dog = dogService.getDogById(appointment.getDog_id());
        Optional<Vet> vet = vetService.getVetById(appointment.getVet_id());
        appointment2.setDog(dog.orElse(null));
        appointment2.setVet(vet.orElse(null));
        this.appointmentService.addAppointment(appointment2);
    }

    @PutMapping("/appointments/{id}")
    void updateAppointment(@RequestBody @Valid AppointmentDTO newAppointment, @PathVariable Long id) throws ParseException {
        Appointment appointment1 = mapper.fromAppointmentDTO(newAppointment);
        Optional<Dog> dog = dogService.getDogById(newAppointment.getDog_id());
        Optional<Vet> vet = vetService.getVetById(newAppointment.getVet_id());
        appointment1.setDog(dog.orElse(null));
        appointment1.setVet(vet.orElse(null));
        appointment1.setId(id);
        appointmentService.updateAppointment(appointment1);
    }

    @DeleteMapping("/appointments/{id}")
    void deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
    }

    @GetMapping("/appointments/number-vets-of-dog/{dog_id}")
    int numberVetsOfDog(@PathVariable Long dog_id) {
        List<AppointmentDTO> appointmentsOfDog = getAppointmentsOfDog(dog_id);
        List<Long> vetIDs = new ArrayList<>();
        for (AppointmentDTO appointmentDTO : appointmentsOfDog) {
            if (!vetIDs.contains(appointmentDTO.getVet_id())) {
                vetIDs.add(appointmentDTO.getVet_id());
            }
        }
        return vetIDs.size();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors;
        errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
