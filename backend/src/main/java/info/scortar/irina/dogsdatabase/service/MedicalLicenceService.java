package info.scortar.irina.dogsdatabase.service;

import info.scortar.irina.dogsdatabase.controller.MedicalLicenceController;
import info.scortar.irina.dogsdatabase.controller.VetController;
import info.scortar.irina.dogsdatabase.model.MedicalLicense;
import info.scortar.irina.dogsdatabase.model.Vet;
import info.scortar.irina.dogsdatabase.repository.MedicalLicenceRepository;
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
public class MedicalLicenceService {

    @Autowired
    private MedicalLicenceRepository medicalLicenceRepository;

    @Autowired
    private VetRepository vetRepository;

    public Map<String, Object> getAllMedicalLicenses(int page, int pSize) {
        List<MedicalLicense> licenses = new ArrayList<>();

        Map<String, Object> response = new HashMap<>();

        if (page > -1) {
            Pageable p = PageRequest.of(page, pSize > 0 ? pSize : MedicalLicenceController.PAGE_SIZE, Sort.by("id"));

            Page<MedicalLicense> pageMedicalLicenses = medicalLicenceRepository.findAll(p);

            response.put("licenses", pageMedicalLicenses.getContent());
            response.put("currentPage", pageMedicalLicenses.getNumber());
            response.put("totalItems", pageMedicalLicenses.getTotalElements());
            response.put("totalPages", pageMedicalLicenses.getTotalPages());

            return response;
        } else {
            medicalLicenceRepository.findAll().forEach(licenses::add);

            response.put("licenses", licenses);
            response.put("currentPage", 0);
            response.put("totalItems", licenses.size());
            response.put("totalPages", 1);
        }

        return response;
    }

    public Optional<MedicalLicense> getMedicalLicenceById(Long id) {
        return medicalLicenceRepository.findById(id);
    }

    public void addMedicalLicence(MedicalLicense medicalLicense) {
        medicalLicenceRepository.save(medicalLicense);
        Vet vet = vetRepository.findById(medicalLicense.getVet().getId()).orElse(null);
        if(vet != null) {
            vet.setMedicalLicence(medicalLicense);
            vetRepository.save(vet);
        }
    }

    public void updateMedicalLicence(MedicalLicense medicalLicense, Long id) {
        medicalLicenceRepository.save(medicalLicense);
    }

    public void deleteMedicalLicence(Long id) {
        medicalLicenceRepository.deleteById(id);
    }
}
