package com.example.dogsdatabase.DTOs;

import java.util.List;

public class OwnerDTO {

    private Long id;
    private String first_name;
    private String last_name;
    private String job;
    private List<String> dogs;

    public OwnerDTO(Long id, String first_name, String last_name, String job, List<String> dogs) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.job = job;
        this.dogs = dogs;
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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public List<String> getDogs() {
        return dogs;
    }

    public void setDogs(List<String> dogs) {
        this.dogs = dogs;
    }
}