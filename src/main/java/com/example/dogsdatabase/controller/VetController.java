package com.example.dogsdatabase.controller;

import com.example.dogsdatabase.DTOs.VetDTO;
import com.example.dogsdatabase.mapper.Mapper;
import com.example.dogsdatabase.model.*;
import com.example.dogsdatabase.service.VetService;
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
public class VetController {

    @Autowired
    private VetService vetService;
    @Autowired
    private Mapper mapper;

    @GetMapping("/vets")
    List<VetDTO> getAllVets() {
        return vetService.getAllVets()
                .stream()
                .map(mapper::toVetDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/vets/{id}")
    Optional<VetDTO> getVetById(@PathVariable Long id) {
        return vetService.getVetById(id).stream().map(mapper::toVetDTO).findFirst();
    }

    @PostMapping("/vets")
    void addVet(@RequestBody @Valid Vet newVet) {
        this.vetService.addVet(newVet);
    }

    @PutMapping("/vets/{id}")
    void updateVet(@RequestBody @Valid Vet newVet, @PathVariable Long id) {
        newVet.setId(id);
        vetService.updateVet(newVet, id);
    }

    @DeleteMapping("/vets/{id}")
    void deleteVet(@PathVariable @Valid Long id) {
        vetService.deleteVet(id);
    }

    @GetMapping("/vets-sorted-by-medical-field")
    List<VetDTO> getVetsSortedByMedicalField() {
        return vetService.getVetsSortedByMedicalField();
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
