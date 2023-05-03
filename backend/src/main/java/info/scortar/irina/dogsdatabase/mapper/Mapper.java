package info.scortar.irina.dogsdatabase.mapper;

import info.scortar.irina.dogsdatabase.DTOs.*;
import info.scortar.irina.dogsdatabase.model.*;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class Mapper {

    public DogDTO toDogDTO(Dog dog) {
        return new DogDTO(dog.getId(), dog.getName(), dog.getBreed(), dog.getAge(), dog.getWeight(),
                dog.getOwner().getId(), 0L);
    }

    public Dog fromDogDTO(DogDTO dogDTO) {
        return new Dog(dogDTO.getId(), dogDTO.getName(), dogDTO.getBreed(), dogDTO.getAge(), dogDTO.getWeight(),
                null, null);
    }

    public OwnerDTO toOwnerDTO(Owner owner) {
        List<String> dog_names = owner
                        .getDogs()
                        .stream()
                        .map(Dog::getName)
                        .collect(toList());

        return new OwnerDTO(owner.getId(), owner.getFirst_name(), owner.getLast_name(), owner.getJob(), owner.getAge(),
                dog_names, dog_names.size());
    }

    public VetDTO toVetDTO(Vet vet) {
        return new VetDTO(vet.getId(), vet.getFirst_name(), vet.getLast_name(), vet.getSpecialty(), vet.getAge(),
                vet.getMedicalLicence() != null ? vet.getMedicalLicence().getId() : null, 0L);
    }

    public Vet fromVetDTO(VetDTO vetDTO) {
        return new Vet(vetDTO.getId(), vetDTO.getFirst_name(), vetDTO.getLast_name(), vetDTO.getSpecialty(),
                vetDTO.getAge(),  0, 0, null, null);
    }

    public AppointmentDTO toAppointmentDTO(Appointment appointment) {
        return new AppointmentDTO(appointment.getId(), appointment.getDog_id(), appointment.getVet_id(),
                appointment.getDate().toString(), appointment.getPrice(), appointment.getDescription());
    }

    public Appointment fromAppointmentDTO(AppointmentDTO appointment) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(appointment.getDate());
        return new Appointment(appointment.getId(), null, null, date, appointment.getPrice(), appointment.getDescription());
    }

    public MedicalLicenceDTO toMedicalLicenceDTO(MedicalLicense medicalLicense) {
        return new MedicalLicenceDTO(medicalLicense.getId(), medicalLicense.getSerial_number(),
                medicalLicense.getUniversity(), medicalLicense.getCity(), medicalLicense.getVet().getId());
    }

    public MedicalLicense fromMedicalLicenseDTO(MedicalLicenceDTO medicalLicenceDTO) {
        return new MedicalLicense(medicalLicenceDTO.getId(), medicalLicenceDTO.getSerial_number(),
                medicalLicenceDTO.getUniversity(), medicalLicenceDTO.getCity(), null, null);
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

    public Owner fromOwnerDTO(OwnerDTO newOwner) {
        return new Owner(newOwner.getId(), newOwner.getFirst_name(), newOwner.getLast_name(), 0, null, newOwner.getJob(), null);
    }
}
