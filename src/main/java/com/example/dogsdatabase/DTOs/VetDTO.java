package com.example.dogsdatabase.DTOs;

public class VetDTO {

    private Long id;
    private String first_name;
    private String last_name;
    private String specialty;
    private Long medical_licence_id;

    public VetDTO(Long id, String first_name, String last_name, String specialty, Long medical_licence_id) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.specialty = specialty;
        this.medical_licence_id = medical_licence_id;
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

    public Long getMedical_licence_id() {
        return medical_licence_id;
    }

    public void setMedical_licence_id(Long medical_licence_id) {
        this.medical_licence_id = medical_licence_id;
    }
}
