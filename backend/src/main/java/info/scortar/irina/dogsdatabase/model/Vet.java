package info.scortar.irina.dogsdatabase.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Entity
public class Vet {

    private @Id @GeneratedValue Long id;
    @NotEmpty(message = "The first name cannot be empty!")
    private String first_name;
    @NotEmpty(message = "The last name cannot be empty!")
    private String last_name;
    @NotEmpty(message = "The specialty cannot be empty!")
    private String specialty;
    private int age;
    @Min(0)
    private int years_of_experience;
    private int salary;
    @OneToMany(mappedBy = "vet", cascade = CascadeType.ALL)
    private List<Appointment> appointments;
    @OneToOne(mappedBy = "vet")
    private MedicalLicense medicalLicense;

    public Vet() {
    }

    public Vet(Long id, String first_name, String last_name, String specialty, int age, int years_of_experience,
               int salary, List<Appointment> appointments, MedicalLicense medicalLicense) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.specialty = specialty;
        this.age = age;
        this.years_of_experience = years_of_experience;
        this.appointments = appointments;
        this.salary = salary;
        this.medicalLicense = medicalLicense;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getYears_of_experience() {
        return years_of_experience;
    }

    public void setYears_of_experience(int years_of_experience) {
        this.years_of_experience = years_of_experience;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public MedicalLicense getMedicalLicence() {
        return medicalLicense;
    }

    public void setMedicalLicence(MedicalLicense medicalLicense) {
        this.medicalLicense = medicalLicense;
    }
}
