package com.example.dogsdatabase.service;

import com.example.dogsdatabase.model.Dog;

import com.example.dogsdatabase.model.Owner;
import com.example.dogsdatabase.repository.DogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DogServiceTest {

    @Mock
    private DogRepository dogRepository;
    private DogService dogService;
    private List<Dog> dogList;

    @BeforeEach
    void setUp() throws RuntimeException {
        this.dogService = new DogService(dogRepository);
        this.dogList = Arrays.asList(
                new Dog(1L, "dog1", "breed1", 12, 34, new Owner(), new ArrayList<>()),
                new Dog(2L, "dog2", "breed2", 11, 32, new Owner(), new ArrayList<>()),
                new Dog(3L, "dog3", "breed3", 5, 5, new Owner(), new ArrayList<>()),
                new Dog(4L, "dog4", "breed4", 8, 4, new Owner(), new ArrayList<>())
        );
        when(dogRepository.findAll()).thenReturn(dogList);
    }

    @Test
    void getDogsByWeight() {
        List<Dog> dogsFilteredByWeight = dogService.getDogsByWeight(20);

        assertEquals(dogsFilteredByWeight.size(), 2);
        assertNotEquals(dogsFilteredByWeight.size(), dogList.size());
        assertEquals(dogsFilteredByWeight.get(0).toString(), dogList.get(0).toString());
        assertEquals(dogsFilteredByWeight.get(1).toString(), dogList.get(1).toString());
    }
}