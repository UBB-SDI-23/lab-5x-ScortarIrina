package info.scortar.irina.dogsdatabase.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import java.util.Date;

@Entity
public class Appointment {

    private @Id @GeneratedValue Long id;

    @Column(name = "dog_id", insertable = false, updatable = false)
    protected Long dog_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dog_id")
    private Dog dog;

    @Column(name = "vet_id", insertable = false, updatable = false)
    protected Long vet_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vet_id")
    private Vet vet;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Min(value = 0, message = "The price of an appointment cannot be a negative number!")
    private float price;

    private String description;

    public Appointment() {
    }

    public Appointment(Long id, Dog dog, Vet vet, Date date, float price, String description) {
        this.id = id;
        this.dog = dog;
        this.vet = vet;
        this.date = date;
        this.price = price;
        this.description = description;
    }

    public Long getDog_id() {
        return dog_id;
    }

    public void setDog_id(Long dog_id) {
        this.dog_id = dog_id;
    }

    public Long getVet_id() {
        return vet_id;
    }

    public void setVet_id(Long vet_id) {
        this.vet_id = vet_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public Vet getVet() {
        return vet;
    }

    public void setVet(Vet vet) {
        this.vet = vet;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
