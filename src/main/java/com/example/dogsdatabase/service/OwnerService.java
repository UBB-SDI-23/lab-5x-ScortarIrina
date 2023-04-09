package com.example.dogsdatabase.service;

import com.example.dogsdatabase.mapper.Mapper;
import com.example.dogsdatabase.model.Owner;
import com.example.dogsdatabase.DTOs.OwnerDTO;
import com.example.dogsdatabase.repository.OwnerRepository;
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
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private Mapper mapper;

    public List<Owner> getAllOwners() {
        List<Owner> owners = new ArrayList<>();
        ownerRepository.findAll().forEach(owners::add);
        return owners;
    }

    public Optional<Owner> getOwnerById(Long id) {
        return ownerRepository.findById(id);
    }

    public void addOwner(Owner owner) {
        ownerRepository.save(owner);
    }

    public void updateOwner(Owner owner, Long id) {
        ownerRepository.save(owner);
    }

    public void deleteOwner(Long id) {
        ownerRepository.deleteById(id);
    }

    public List<OwnerDTO> getOwnersSortedByFirstName() {
        List<Owner> owners = getAllOwners();
        List<Owner> ownersAlphabetically = owners.stream().sorted(Comparator.comparing(Owner::getFirst_name)).toList();
        return ownersAlphabetically.stream().map((mapper::toOwnerDTO)).toList();
    }
}
