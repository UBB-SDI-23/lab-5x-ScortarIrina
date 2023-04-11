package info.scortar.irina.dogsdatabase.service;

import info.scortar.irina.dogsdatabase.mapper.Mapper;
import info.scortar.irina.dogsdatabase.model.Vet;
import info.scortar.irina.dogsdatabase.DTOs.VetDTO;
import info.scortar.irina.dogsdatabase.repository.VetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VetServiceTest {

    @Mock
    private VetRepository vetRepository;
    private VetService vetService;
    private List<Vet> vetList;

    @BeforeEach
    void setUp() {
        Mapper mapper = new Mapper();
        this.vetService = new VetService(vetRepository, mapper);

        Vet vet1 = new Vet(1L, "firstName1", "lastName1", "dermatology", 34, 3, 123, new ArrayList<>(), null);
        Vet vet2 = new Vet(2L, "firstName1", "lastName1", "cardiology", 34, 3, 123, new ArrayList<>(), null);
        Vet vet3 = new Vet(3L, "firstName3", "lastName3", "pediatrics", 34, 3, 123, new ArrayList<>(), null);
        this.vetList = Arrays.asList(vet1, vet2, vet3);

        when(vetRepository.findAll()).thenReturn(vetList);
    }

    @Test
    void getVetsSortedByMedicalField() {
        vetService.addVet(vetList.get(0));
        vetService.addVet(vetList.get(1));
        vetService.addVet(vetList.get(2));

        List<VetDTO> vetsSortedBySpecialty = vetService.getVetsSortedByMedicalField();

        assertEquals(vetsSortedBySpecialty.size(), 3);
        assertEquals(vetsSortedBySpecialty.get(0).getSpecialty(), "cardiology");
        assertEquals(vetsSortedBySpecialty.get(1).getSpecialty(), "dermatology");
        assertEquals(vetsSortedBySpecialty.get(2).getSpecialty(), "pediatrics");
    }
}