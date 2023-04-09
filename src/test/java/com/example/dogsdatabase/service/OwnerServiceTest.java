package com.example.dogsdatabase.service;

import com.example.dogsdatabase.mapper.Mapper;
import com.example.dogsdatabase.model.Owner;
import com.example.dogsdatabase.DTOs.OwnerDTO;
import com.example.dogsdatabase.repository.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.dogsdatabase.model.Dog;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerServiceTest {

    @Mock
    private OwnerRepository ownerRepository;
    private OwnerService ownerService;
    private List<Owner> ownerList;
    private List<Dog> dogList;

    void addInDogList() {
        dogList = Arrays.asList(
                new Dog(1L, "dog1", "breed1", 12, 34, ownerList.get(1), new ArrayList<>()),
                new Dog(2L, "dog2", "breed2", 11, 32, ownerList.get(0), new ArrayList<>()),
                new Dog(3L, "dog3", "breed3", 5, 5, ownerList.get(1), new ArrayList<>()),
                new Dog(4L, "dog4", "breed4", 8, 4, ownerList.get(0), new ArrayList<>()),
                new Dog(5L, "dog5", "breed5", 2, 40, ownerList.get(2), new ArrayList<>())
        );
    }

    @BeforeEach
    void setUp() {
        Mapper mapper = new Mapper();
        this.ownerService = new OwnerService(ownerRepository, mapper);

        Owner owner1 = new Owner(1L, "firstName1", "lastName1", 33, "f", "job1", new  ArrayList<>());
        Owner owner2 = new Owner(2L, "firstName42", "lastName2", 33, "f", "job2", new ArrayList<>());
        Owner owner3 = new Owner(3L, "firstName2", "lastName4", 33, "f", "job3", new ArrayList<>());
        this.ownerList = Arrays.asList(owner1, owner2, owner3);

        addInDogList();

        owner1.setDogs(Arrays.asList(dogList.get(1), dogList.get(3)));
        owner2.setDogs(Arrays.asList(dogList.get(0), dogList.get(2)));
        owner3.setDogs(Collections.singletonList(dogList.get(4)));

        when(ownerRepository.findAll()).thenReturn(ownerList);
    }

    @Test
    void getOwnersSortedByFirstName() {
        ownerService.addOwner(ownerList.get(0));
        ownerService.addOwner(ownerList.get(1));
        ownerService.addOwner(ownerList.get(2));
        List<OwnerDTO> ownersSortedByFirstName = ownerService.getOwnersSortedByFirstName();

        assertEquals(ownersSortedByFirstName.size(), 3);
        assertEquals(ownersSortedByFirstName.get(0).getFirst_name(), "firstName1");
        assertEquals(ownersSortedByFirstName.get(1).getFirst_name(), "firstName2");
        assertEquals(ownersSortedByFirstName.get(2).getFirst_name(), "firstName42");
    }
}