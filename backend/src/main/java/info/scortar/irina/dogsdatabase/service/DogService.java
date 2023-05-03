package info.scortar.irina.dogsdatabase.service;

import info.scortar.irina.dogsdatabase.controller.DogController;
import info.scortar.irina.dogsdatabase.model.Appointment;
import info.scortar.irina.dogsdatabase.model.Dog;
import info.scortar.irina.dogsdatabase.model.Owner;
import info.scortar.irina.dogsdatabase.repository.AppointmentRepository;
import info.scortar.irina.dogsdatabase.repository.DogRepository;
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
public class DogService {

    @Autowired
    private DogRepository dogRepository;

    public Map<String, Object> getAllDogs(int page, int pSize) {
        List<Dog> dogs = new ArrayList<>();

        Map<String, Object> response = new HashMap<>();

        if (page > -1) {
            Pageable p = PageRequest.of(page, pSize > 0 ? pSize : DogController.PAGE_SIZE,
                    Sort.by("id"));

            Page<Dog> pageDogs = dogRepository.findAll(p);

            response.put("dogs", pageDogs.getContent());
            response.put("currentPage", pageDogs.getNumber());
            response.put("totalItems", pageDogs.getTotalElements());
            response.put("totalPages", pageDogs.getTotalPages());

            return response;
        } else {
            dogRepository.findAll().forEach(dogs::add);

            response.put("dogs", dogs);
            response.put("currentPage", 0);
            response.put("totalItems", dogs.size());
            response.put("totalPages", 1);
        }

        return response;
    }

    public List<Dog> getDogsByOwner(Owner owner) {
        List<Dog> all_dogs = (List<Dog>) getAllDogs(-1, 0).get("dogs");
        List<Dog> dogs_of_owner = new ArrayList<>();
        for (Dog dog : all_dogs) {
            if (dog.getOwner() == owner)
                dogs_of_owner.add(dog);
        }
        return dogs_of_owner;
    }

    public Optional<Dog> getDogById(Long id) {
        return dogRepository.findById(id);
    }

    public void addDog(Dog dog) {
        dogRepository.save(dog);
    }

    public void updateDog(Dog dog) {
        dogRepository.save(dog);
    }

    public void deleteDog(Long id) {
        dogRepository.deleteById(id);
    }

    public List<Dog> getDogsByWeight(int weight) {
        List<Dog> filtered_dogs = new ArrayList<>();
        for (Dog dog : dogRepository.findAll()) {
            if (dog.getWeight() > weight) {
                filtered_dogs.add(dog);
            }
        }
        return filtered_dogs;
    }
}
