package info.scortar.irina.dogsdatabase.controller;

import info.scortar.irina.dogsdatabase.DTOs.VetDTO;
import info.scortar.irina.dogsdatabase.mapper.Mapper;
import info.scortar.irina.dogsdatabase.model.MedicalLicense;
import info.scortar.irina.dogsdatabase.DTOs.MedicalLicenceDTO;
import info.scortar.irina.dogsdatabase.model.Vet;
import info.scortar.irina.dogsdatabase.service.MedicalLicenceService;

import info.scortar.irina.dogsdatabase.service.VetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class MedicalLicenceController {

    public static int PAGE_SIZE = 100;

    @Autowired
    private MedicalLicenceService medicalLicenceService;

    @Autowired
    private VetService vetService;

    @Autowired
    private Mapper mapper;

    @GetMapping("/licenses")
    ResponseEntity<Map<String, Object>> getAllMedicalLicenses(@RequestParam Optional<String> page,
                                                              @RequestParam Optional<String> size) {
        int p = Integer.parseInt(page.orElse("0"));

        int pSize = Integer.parseInt(size.orElse("0"));

        Map<String, Object> ret = medicalLicenceService.getAllMedicalLicenses(p, pSize);

        List<MedicalLicenceDTO> dtos = ((List<MedicalLicense>) ret.get("licenses"))
                .stream()
                .map(mapper::toMedicalLicenceDTO)
                .collect(Collectors.toList());

        ret.put("licenses", dtos);

        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @GetMapping("/licenses/{id}")
    Optional<MedicalLicenceDTO> getMedicalLicenceById(@PathVariable Long id) {
        return medicalLicenceService.getMedicalLicenceById(id)
                .stream()
                .map(mapper::toMedicalLicenceDTO)
                .findFirst();
    }

    @PostMapping("/licenses")
    void addMedicalLicence(@RequestBody @Valid MedicalLicenceDTO newMedicalLicense) {
        Optional<Vet> vet = vetService.getVetById(newMedicalLicense.getVet_id());
        MedicalLicense medicalLicense = mapper.fromMedicalLicenseDTO(newMedicalLicense);
        medicalLicense.setVet(vet.orElse(null));
        this.medicalLicenceService.addMedicalLicence(medicalLicense);
    }

    @PutMapping("/licenses/{id}")
    void updateMedicalLicence(@RequestBody @Valid MedicalLicenceDTO newMedicalLicense, @PathVariable Long id) {
        Optional<Vet> vet = vetService.getVetById(newMedicalLicense.getVet_id());
        MedicalLicense medicalLicense = mapper.fromMedicalLicenseDTO(newMedicalLicense);
        medicalLicense.setVet(vet.orElse(null));
        newMedicalLicense.setId(id);
        medicalLicenceService.updateMedicalLicence(medicalLicense, id);
    }

    @DeleteMapping("/licenses/{id}")
    void deleteMedicalLicence(@PathVariable Long id) {
        medicalLicenceService.deleteMedicalLicence(id);
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
