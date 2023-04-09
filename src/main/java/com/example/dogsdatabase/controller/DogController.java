package com.example.dogsdatabase.controller;

import com.example.dogsdatabase.mapper.Mapper;
import com.example.dogsdatabase.model.Dog;
import com.example.dogsdatabase.DTOs.DogDTO;
import com.example.dogsdatabase.model.Owner;
import com.example.dogsdatabase.service.DogService;
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
public class DogController {

    @Autowired
    private DogService dogService;

    @Autowired
    private Mapper mapper;


    @GetMapping("/dogs")
    List<DogDTO> getAllDogs() {
        return dogService.getAllDogs()
                .stream()
                .map(mapper::toDogDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/dogs/{id}")
    Optional<DogDTO> getDogById(@PathVariable Long id) {
        return dogService.getDogById(id).stream().map(mapper::toDogDTO).findFirst();
    }

    @GetMapping("/dogs-of-owner/{owner_id}")
    List<DogDTO> getDogsOfOwner(@PathVariable Owner owner_id) {
        return dogService.getDogsByOwner(owner_id)
                .stream()
                .map(mapper::toDogDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/dogs-heavier-than-given-number/{weight}")
    List<DogDTO> getDogsHeavierThanGivenWeight(@PathVariable int weight) {
        return dogService.getDogsByWeight(weight)
                .stream()
                .map(mapper::toDogDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/dogs")
    void addDog(@RequestBody @Valid Dog newDog) {
        this.dogService.addDog(newDog);
    }

    @PutMapping("/dogs/{id}")
    void updateDog(@RequestBody @Valid Dog newDog, @PathVariable Long id) {
        newDog.setId(id);
        dogService.updateDog(newDog);
    }

    @DeleteMapping("/dogs/{id}")
    void deleteDog(@PathVariable Long id) {
        dogService.deleteDog(id);
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
