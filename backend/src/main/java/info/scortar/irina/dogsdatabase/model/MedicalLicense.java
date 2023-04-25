package info.scortar.irina.dogsdatabase.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class MedicalLicense {

    private @Id @GeneratedValue Long id;
    @NotEmpty(message = "The serial number of a medical license cannot be empty!")
    private String serial_number;
    @NotEmpty(message = "The university cannot be empty!")
    private String university;
    @NotEmpty(message = "The city cannot be empty!")
    private String city;
    private String country;

    @OneToOne(optional = false)
    @JoinColumn(name = "vet_id", referencedColumnName = "id")
    private Vet vet;

    public MedicalLicense() {
    }

    public MedicalLicense(Long id, String serial_number, String university, String city, String country, Vet vet) {
        this.id = id;
        this.serial_number = serial_number;
        this.university = university;
        this.city = city;
        this.country = country;
        this.vet = vet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Vet getVet() {
        return vet;
    }

    public void setVet(Vet vet) {
        this.vet = vet;
    }
}
