package com.example.dogsdatabase.service;

import com.example.dogsdatabase.model.MedicalLicense;
import com.example.dogsdatabase.model.Vet;
import com.example.dogsdatabase.repository.MedicalLicenceRepository;
import com.example.dogsdatabase.repository.VetRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class MedicalLicenceService {

    @Autowired
    private MedicalLicenceRepository medicalLicenceRepository;

    @Autowired
    private VetRepository vetRepository;

    public List<MedicalLicense> getAllMedicalLicences() {
        List<MedicalLicense> medicalLicenses = new ArrayList<>();
        medicalLicenceRepository.findAll().forEach(medicalLicenses::add);
        return medicalLicenses;
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
