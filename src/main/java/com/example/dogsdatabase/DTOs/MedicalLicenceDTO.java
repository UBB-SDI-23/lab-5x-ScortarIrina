package com.example.dogsdatabase.DTOs;

public class MedicalLicenceDTO {

    private Long id;
    private String university;
    private String city;
    private String vet_name;

    public MedicalLicenceDTO(Long id, String university, String city, String vet_name) {
        this.id = id;
        this.university = university;
        this.city = city;
        this.vet_name = vet_name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getVet_name() {
        return vet_name;
    }

    public void setVet_name(String vet_name) {
        this.vet_name = vet_name;
    }
}
