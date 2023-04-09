package com.example.dogsdatabase.repository;

import com.example.dogsdatabase.model.Dog;
import com.example.dogsdatabase.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
}
