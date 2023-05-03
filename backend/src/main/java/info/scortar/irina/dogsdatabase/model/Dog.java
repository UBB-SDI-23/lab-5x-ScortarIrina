package info.scortar.irina.dogsdatabase.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Entity
public class Dog {

    private @Id @GeneratedValue Long id;
    private String name;
    private String breed;
    private int age;
    private int weight;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ManyToOne(optional = true)
    @JoinColumn(name = "owner_id")   // marks a column as a join column for an entity association
    private Owner owner;
    @OneToMany(
            mappedBy = "dog",
            cascade = CascadeType.ALL
    )
    private List<Appointment> appointments;

    public Dog() {
    }

    public Dog(Long id, String name, String breed, int age, int weight, Owner owner, List<Appointment> appointments) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.weight = weight;
        this.owner = owner;
        this.appointments = appointments;
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

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                '}';
    }
}
