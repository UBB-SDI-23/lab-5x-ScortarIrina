package info.scortar.irina.dogsdatabase.service;

import info.scortar.irina.dogsdatabase.mapper.Mapper;
import info.scortar.irina.dogsdatabase.model.Vet;
import info.scortar.irina.dogsdatabase.DTOs.VetDTO;
import info.scortar.irina.dogsdatabase.repository.VetRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class VetService {

    @Autowired
    private VetRepository vetRepository;

    @Autowired
    private Mapper mapper;

    public List<Vet> getAllVets() {
        List<Vet> vets = new ArrayList<>();
        vetRepository.findAll().forEach(vets::add);
        return vets;
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

    public List<VetDTO> getVetsSortedByMedicalField() {
        List<Vet> vets = getAllVets();
        List<Vet> vetsSorted = vets.stream().sorted(Comparator.comparing(Vet::getSpecialty)).toList();
        return vetsSorted.stream().map((mapper::toVetDTO)).toList();
    }
}
