package info.scortar.irina.dogsdatabase.controller;

import info.scortar.irina.dogsdatabase.DTOs.OwnerDTO;
import info.scortar.irina.dogsdatabase.DTOs.VetDTO;
import info.scortar.irina.dogsdatabase.mapper.Mapper;
import info.scortar.irina.dogsdatabase.model.Owner;
import info.scortar.irina.dogsdatabase.model.Vet;
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
public class VetController {

    public static int PAGE_SIZE = 100;
    @Autowired
    private VetService vetService;
    @Autowired
    private Mapper mapper;

    @GetMapping("/vets")
    ResponseEntity<Map<String, Object>> getAllVets(@RequestParam Optional<String> page, @RequestParam Optional<String> size) {
        int p = Integer.parseInt(page.orElse("0"));

        int pSize = Integer.parseInt(size.orElse("0"));

        Map<String, Object> ret = vetService.getAllVets(p, pSize);

        List<VetDTO> dtos = ((List<Vet>) ret.get("vets"))
                .stream()
                .map(mapper::toVetDTO)
                .collect(Collectors.toList());

        dtos = dtos.stream().map(dto -> {
            dto.setNumber_of_dogs(vetService.findDogIdsForVet(dto.getId()));
            return dto;
        }).collect(Collectors.toList());

        ret.put("vets", dtos);

        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @GetMapping("/vets/{id}")
    Optional<VetDTO> getVetById(@PathVariable Long id) {
        return vetService.getVetById(id).stream().map(mapper::toVetDTO).findFirst();
    }

    @PostMapping("/vets")
    void addVet(@RequestBody @Valid VetDTO newVet) {
        Vet vet = mapper.fromVetDTO(newVet);
        this.vetService.addVet(vet);
    }

    @PutMapping("/vets/{id}")
    void updateVet(@RequestBody @Valid VetDTO newVet, @PathVariable Long id) {
        Vet vet = mapper.fromVetDTO(newVet);
        vet.setId(id);
        vetService.updateVet(vet, id);
    }

    @DeleteMapping("/vets/{id}")
    void deleteVet(@PathVariable @Valid Long id) {
        vetService.deleteVet(id);
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
