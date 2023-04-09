package com.example.dogsdatabase.repository;

import com.example.dogsdatabase.model.Dog;
import org.springframework.data.repository.CrudRepository;

public interface DogRepository extends CrudRepository<Dog, Long> {
}
