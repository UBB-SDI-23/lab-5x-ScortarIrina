package com.example.dogsdatabase.mapper;

import com.example.dogsdatabase.DTOs.*;
import com.example.dogsdatabase.model.*;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class Mapper {

    public DogDTO toDogDTO(Dog dog) {
        return new DogDTO(dog.getId(), dog.getName(), dog.getBreed(), dog.getAge(), dog.getWeight());
    }

    public OwnerDTO toOwnerDTO(Owner owner) {
        List<String> dog_names = owner
                        .getDogs()
                        .stream()
                        .map(Dog::getName)
                        .collect(toList());

        return new OwnerDTO(owner.getId(), owner.getFirst_name(), owner.getLast_name(), owner.getJob(), dog_names);
    }

    public VetDTO toVetDTO(Vet vet) {
        return new VetDTO(vet.getId(), vet.getFirst_name(), vet.getLast_name(), vet.getSpecialty(),
                vet.getMedicalLicence() != null ? vet.getMedicalLicence().getId() : null);
    }

    public AppointmentDTO toAppointmentDTO(Appointment appointment) {
        return new AppointmentDTO(appointment.getId(), appointment.getDog().getId(), appointment.getVet().getId(),
                appointment.getDate().toString());
    }

    public MedicalLicenceDTO toMedicalLicenceDTO(MedicalLicense medicalLicense) {
        return new MedicalLicenceDTO(medicalLicense.getId(), medicalLicense.getUniversity(), medicalLicense.getCity(),
                medicalLicense.getVet().getFirst_name());
    }

    public OwnerWithDogsDTO toOwnersWithDogsDTO(Owner owner) {
        int nr_of_dogs;
        int total_weight_of_dogs = 0;
        List<Dog> dogs_of_owner = owner.getDogs();
        nr_of_dogs = dogs_of_owner.size();
        for (Dog dog: dogs_of_owner)
            total_weight_of_dogs += dog.getWeight();
        float average_weight = (float) total_weight_of_dogs/nr_of_dogs;
        return new OwnerWithDogsDTO(owner.getId(), owner.getFirst_name(), owner.getLast_name(), nr_of_dogs, average_weight);
    }

    public VetAppointmentsPriceDTO vetAppointmentsPriceDTO(Vet vet) {
        int total_price_of_appointments = 0;
        List<Appointment> appointments_of_vet = vet.getAppointments();
        int nr_of_appointments = appointments_of_vet.size();
        for (Appointment appointment: appointments_of_vet)
            total_price_of_appointments += appointment.getPrice();
        if (nr_of_appointments == 0)
            return new VetAppointmentsPriceDTO(vet.getId(), vet.getId().toString(), 0, 0);
        float average_price = (float) total_price_of_appointments/nr_of_appointments;
        return new VetAppointmentsPriceDTO(vet.getId(), vet.getId().toString(), nr_of_appointments, average_price);
    }
}
