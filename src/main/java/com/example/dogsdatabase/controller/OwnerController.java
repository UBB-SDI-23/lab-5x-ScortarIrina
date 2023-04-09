package com.example.dogsdatabase.controller;

import com.example.dogsdatabase.DTOs.DogDTO;
import com.example.dogsdatabase.exceptions.OwnerNotFoundException;
import com.example.dogsdatabase.mapper.Mapper;
import com.example.dogsdatabase.model.Dog;
import com.example.dogsdatabase.model.Owner;
import com.example.dogsdatabase.DTOs.OwnerDTO;
import com.example.dogsdatabase.service.DogService;
import com.example.dogsdatabase.service.OwnerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class OwnerController {

    @Autowired
    private OwnerService ownerService;
    @Autowired
    private DogService dogService;
    @Autowired
    private Mapper mapper;

    @GetMapping("/owners")
    List<OwnerDTO> getAllOwners() {
        return ownerService.getAllOwners()
                .stream()
                .map(mapper::toOwnerDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/owners/{id}")
    Optional<OwnerDTO> getOwnerById(@PathVariable Long id) {
        return ownerService.getOwnerById(id).stream().map(mapper::toOwnerDTO).findFirst();
    }

    @PostMapping("/owners")
    void addOwner(@RequestBody @Valid Owner newOwner) {
        this.ownerService.addOwner(newOwner);
    }

    @PostMapping("/dog-to-owner/{owner_id}/dog")
    void addDogToOwner(@PathVariable Long owner_id, @Valid @RequestBody Dog newDog) {
        Owner owner = ownerService.getOwnerById(owner_id).orElseThrow(()->new OwnerNotFoundException(owner_id));
        newDog.setOwner(owner);
        dogService.addDog(newDog);
        owner.getDogs().add(newDog);
        ownerService.addOwner(owner);
    }

    @PostMapping("/dogs-to-owner/{owner_id}/dogs")
    void addDogsToOwner(@PathVariable Long owner_id, @RequestBody List<@Valid Dog> newDogs) {
        Owner owner = ownerService.getOwnerById(owner_id).orElseThrow(()->new OwnerNotFoundException(owner_id));
        for(Dog dog: newDogs) {
            dog.setOwner(owner);
            dogService.addDog(dog);
            owner.getDogs().add(dog);
        }
        ownerService.addOwner(owner);
    }

    @PutMapping("/owners/{id}")
    void updateOwner(@RequestBody Owner newOwner, @PathVariable Long id) {
        newOwner.setId(id);
        ownerService.updateOwner(newOwner, id);
    }

    @DeleteMapping("/owners/{id}")
    void deleteOwner(@PathVariable Long id) {
        ownerService.deleteOwner(id);
    }

    @GetMapping("/owners-alphabetically-by-first-name")
    List<OwnerDTO> getOwnersSortedByFirstName() {
        return ownerService.getOwnersSortedByFirstName();
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
