package info.scortar.irina.dogsdatabase.service;

import info.scortar.irina.dogsdatabase.controller.OwnerController;
import info.scortar.irina.dogsdatabase.controller.VetController;
import info.scortar.irina.dogsdatabase.mapper.Mapper;
import info.scortar.irina.dogsdatabase.model.Owner;
import info.scortar.irina.dogsdatabase.model.Vet;
import info.scortar.irina.dogsdatabase.DTOs.VetDTO;
import info.scortar.irina.dogsdatabase.repository.VetRepository;
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
public class VetService {

    @Autowired
    private VetRepository vetRepository;

    @Autowired
    private Mapper mapper;

    public Map<String, Object> getAllVets(int page, int pSize) {
        List<Vet> vets = new ArrayList<>();

        Map<String, Object> response = new HashMap<>();

        if (page > -1) {
            Pageable p = PageRequest.of(page, pSize > 0 ? pSize : VetController.PAGE_SIZE, Sort.by("id"));

            Page<Vet> pageVets = vetRepository.findAll(p);

            response.put("vets", pageVets.getContent());
            response.put("currentPage", pageVets.getNumber());
            response.put("totalItems", pageVets.getTotalElements());
            response.put("totalPages", pageVets.getTotalPages());

            return response;
        } else {
            vetRepository.findAll().forEach(vets::add);

            response.put("vets", vets);
            response.put("currentPage", 0);
            response.put("totalItems", vets.size());
            response.put("totalPages", 1);
        }

        return response;
    }

    public Optional<Vet> getVetById(Long id) {
        return vetRepository.findById(id);
    }

    public void addVet(Vet vet) {
        vetRepository.save(vet);
    }

    public void updateVet(Vet vet, Long id) {
        vetRepository.save(vet);
    }

    public void deleteVet(Long id) {
        vetRepository.deleteById(id);
    }

//    public List<VetDTO> getVetsSortedByMedicalField() {
//        List<Vet> vets = getAllVets();
//        List<Vet> vetsSorted = vets.stream().sorted(Comparator.comparing(Vet::getSpecialty)).toList();
//        return vetsSorted.stream().map((mapper::toVetDTO)).toList();
//    }
}
