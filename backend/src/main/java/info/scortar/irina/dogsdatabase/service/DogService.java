package info.scortar.irina.dogsdatabase.service;

import info.scortar.irina.dogsdatabase.model.Dog;
import info.scortar.irina.dogsdatabase.model.Owner;
import info.scortar.irina.dogsdatabase.repository.DogRepository;
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
public class DogService {

    @Autowired
    private DogRepository dogRepository;

    public List<Dog> getAllDogs() {
        List<Dog> dogs = new ArrayList<>();
        dogRepository.findAll().forEach(dogs::add);
        return dogs;
    }

    public List<Dog> getDogsByOwner(Owner owner) {
        List<Dog> all_dogs = getAllDogs();
        List<Dog> dogs_of_owner = new ArrayList<>();
        for (Dog dog: all_dogs) {
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
        for (Dog dog: dogRepository.findAll()) {
            if (dog.getWeight() > weight) {
                filtered_dogs.add(dog);
            }
        }
        return filtered_dogs;
    }
}
