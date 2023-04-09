package com.example.dogsdatabase.repository;

import com.example.dogsdatabase.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
