package info.scortar.irina.dogsdatabase.DTOs;

public class DogDTO {

    private Long id;
    private String name;
    private String breed;
    private int age;
    private int weight;
    private Long owner_id;
    private int number_of_appointments;

    public DogDTO(Long id, String name, String breed, int age, int weight, Long owner_id, int number_of_appointments) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.weight = weight;
        this.owner_id = owner_id;
        this.number_of_appointments = number_of_appointments;
    }

    public Long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Long owner_id) {
        this.owner_id = owner_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getNumber_of_appointments() {
        return number_of_appointments;
    }

    public void setNumber_of_appointments(int number_of_appointments) {
        this.number_of_appointments = number_of_appointments;
    }
}
