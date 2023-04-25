package info.scortar.irina.dogsdatabase.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Objects;

@Entity
public class Owner {
    private @Id @GeneratedValue Long id;
    private String first_name;
    private String last_name;
    private int age;
    private String gender;
    private String job;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @OrderBy("id asc")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")   // mappedBy parameter to indicate that the given column is owned by another entity
    @JsonIgnoreProperties("owner")    // marks a property or a list of properties that Jackson will ignore from serialization
    protected List<Dog> dogs;

    public Owner() {
    }

    public Owner(Long id, String first_name, String last_name, int age, String gender, String job, List<Dog> dogs) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
        this.gender = gender;
        this.job = job;
        this.dogs = dogs;
    }

    //    public Owner(String first_name, String last_name, int age, String gender, String job) {
//        this.first_name = first_name;
//        this.last_name = last_name;
//        this.age = age;
//        this.gender = gender;
//        this.job = job;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setDogs(List<Dog> dogs) {
        this.dogs = dogs;
    }

    public List<Dog> getDogs() {
        return dogs;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.first_name, this.last_name, this.age, this.gender, this.job);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Owner)) return false;
        Owner owner = (Owner) obj;
        return Objects.equals(this.id, owner.id) &&
                Objects.equals(this.first_name, owner.first_name) &&
                Objects.equals(this.last_name, owner.last_name) &&
                Objects.equals(this.age, owner.age) &&
                Objects.equals(this.gender, owner.gender) &&
                Objects.equals(this.job, owner.job);
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", job='" + job + '\'' +
                ", dogs=" + dogs +
                '}';
    }
}