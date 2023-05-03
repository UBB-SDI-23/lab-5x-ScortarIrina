package info.scortar.irina.dogsdatabase.DTOs;

import java.util.List;

public class OwnerDTO {

    private Long id;
    private String first_name;
    private String last_name;
    private String job;
    private int age;
    private List<String> dogs;
    private int number_of_dogs;

    public OwnerDTO(Long id, String first_name, String last_name, String job, int age, List<String> dogs, int number_of_dogs) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.job = job;
        this.dogs = dogs;
        this.age = age;
        this.number_of_dogs = number_of_dogs;
    }

    public int getNumber_of_dogs() {
        return number_of_dogs;
    }

    public void setNumber_of_dogs(int number_of_dogs) {
        this.number_of_dogs = number_of_dogs;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
