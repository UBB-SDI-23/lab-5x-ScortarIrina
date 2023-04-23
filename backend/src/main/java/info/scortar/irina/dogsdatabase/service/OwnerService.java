package info.scortar.irina.dogsdatabase.service;

import info.scortar.irina.dogsdatabase.controller.OwnerController;
import info.scortar.irina.dogsdatabase.mapper.Mapper;
import info.scortar.irina.dogsdatabase.model.Dog;
import info.scortar.irina.dogsdatabase.model.Owner;
import info.scortar.irina.dogsdatabase.DTOs.OwnerDTO;
import info.scortar.irina.dogsdatabase.repository.OwnerRepository;
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
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private Mapper mapper;

    public Map<String, Object> getAllOwners(int page, int pSize) {
        List<Owner> owners = new ArrayList<>();

        Map<String, Object> response = new HashMap<>();

        if (page > -1) {
            Pageable p = PageRequest.of(page, pSize > 0 ? pSize : OwnerController.PAGE_SIZE, Sort.by("id"));

            Page<Owner> pageOwners = ownerRepository.findAll(p);

            response.put("owners", pageOwners.getContent());
            response.put("currentPage", pageOwners.getNumber());
            response.put("totalItems", pageOwners.getTotalElements());
            response.put("totalPages", pageOwners.getTotalPages());

            return response;
        } else {
            ownerRepository.findAll().forEach(owners::add);

            response.put("owners", owners);
            response.put("currentPage", 0);
            response.put("totalItems", owners.size());
            response.put("totalPages", 1);
        }

        return response;
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

//    public List<OwnerDTO> getOwnersSortedByFirstName() {
//        List<Owner> owners = getAllOwners();
//        List<Owner> ownersAlphabetically = owners.stream().sorted(Comparator.comparing(Owner::getFirst_name)).toList();
//        return ownersAlphabetically.stream().map((mapper::toOwnerDTO)).toList();
//    }
}
