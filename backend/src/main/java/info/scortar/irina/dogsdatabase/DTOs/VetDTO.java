package info.scortar.irina.dogsdatabase.DTOs;

public class VetDTO {

    private Long id;
    private String first_name;
    private String last_name;
    private String specialty;
    private int age;
    private Long medical_licence_id;
    private Long number_of_dogs;

    public VetDTO(Long id, String first_name, String last_name, String specialty, int age, Long medical_licence_id, Long number_of_dogs) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.specialty = specialty;
        this.age = age;
        this.medical_licence_id = medical_licence_id;
        this.number_of_dogs = number_of_dogs;
    }

    public Long getNumber_of_dogs() {
        return number_of_dogs;
    }

    public void setNumber_of_dogs(Long number_of_dogs) {
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
