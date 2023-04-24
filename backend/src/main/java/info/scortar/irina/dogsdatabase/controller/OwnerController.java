package info.scortar.irina.dogsdatabase.controller;

import info.scortar.irina.dogsdatabase.exceptions.OwnerNotFoundException;
import info.scortar.irina.dogsdatabase.mapper.Mapper;
import info.scortar.irina.dogsdatabase.model.Dog;
import info.scortar.irina.dogsdatabase.model.Owner;
import info.scortar.irina.dogsdatabase.DTOs.OwnerDTO;
import info.scortar.irina.dogsdatabase.service.DogService;
import info.scortar.irina.dogsdatabase.service.OwnerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class OwnerController {

    public static int PAGE_SIZE = 100;
    @Autowired
    private OwnerService ownerService;
    @Autowired
    private DogService dogService;
    @Autowired
    private Mapper mapper;

    @GetMapping("/owners")
    ResponseEntity<Map<String, Object>> getAllOwners(@RequestParam Optional<String> page, @RequestParam Optional<String> size) {
        int p = Integer.parseInt(page.orElse("0"));

        int pSize = Integer.parseInt(size.orElse("0"));

        Map<String, Object> ret = ownerService.getAllOwners(p, pSize);

        List<OwnerDTO> dtos = ((List<Owner>) ret.get("owners")).stream().map(mapper::toOwnerDTO).collect(Collectors.toList());

        ret.put("owners", dtos);

        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @GetMapping("/owners/{id}")
    Optional<OwnerDTO> getOwnerById(@PathVariable Long id) {
        return ownerService.getOwnerById(id).stream().map(mapper::toOwnerDTO).findFirst();
    }

    @PostMapping("/owners")
    void addOwner(@RequestBody @Valid Owner newOwner) {
        this.ownerService.addOwner(newOwner);
    }

    @PostMapping("/owners/dog-to-owner/{owner_id}/dog")
    void addDogToOwner(@PathVariable Long owner_id, @Valid @RequestBody Dog newDog) {
        Owner owner = ownerService.getOwnerById(owner_id).orElseThrow(()->new OwnerNotFoundException(owner_id));
        newDog.setOwner(owner);
        dogService.addDog(newDog);
        owner.getDogs().add(newDog);
        ownerService.addOwner(owner);
    }

    @PostMapping("/owners/dogs-to-owner/{owner_id}/dogs")
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

//    @GetMapping("/owners-alphabetically-by-first-name")
//    List<OwnerDTO> getOwnersSortedByFirstName() {
//        return ownerService.getOwnersSortedByFirstName();
//    }

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
