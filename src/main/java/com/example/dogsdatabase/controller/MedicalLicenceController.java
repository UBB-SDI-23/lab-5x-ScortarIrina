package com.example.dogsdatabase.controller;

import com.example.dogsdatabase.mapper.Mapper;
import com.example.dogsdatabase.model.MedicalLicense;
import com.example.dogsdatabase.DTOs.MedicalLicenceDTO;
import com.example.dogsdatabase.service.MedicalLicenceService;

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
public class MedicalLicenceController {

    @Autowired
    private MedicalLicenceService medicalLicenceService;
    @Autowired
    private Mapper mapper;

    @GetMapping("/licences")
    List<MedicalLicenceDTO> getAllMedicalLicences() {
        return medicalLicenceService.getAllMedicalLicences()
                .stream()
                .map(mapper::toMedicalLicenceDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/licences/{id}")
    Optional<MedicalLicenceDTO> getMedicalLicenceById(@PathVariable Long id) {
        return medicalLicenceService.getMedicalLicenceById(id)
                .stream()
                .map(mapper::toMedicalLicenceDTO)
                .findFirst();
    }

    @PostMapping("/licences")
    void addMedicalLicence(@RequestBody @Valid MedicalLicense newMedicalLicense) {
        this.medicalLicenceService.addMedicalLicence(newMedicalLicense);
    }

    @PutMapping("/licences/{id}")
    void updateMedicalLicence(@RequestBody @Valid MedicalLicense newMedicalLicense, @PathVariable Long id) {
        newMedicalLicense.setId(id);
        medicalLicenceService.updateMedicalLicence(newMedicalLicense, id);
    }

    @DeleteMapping("/licences/{id}")
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
