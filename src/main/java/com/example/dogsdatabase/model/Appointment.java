package com.example.dogsdatabase.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Appointment {

    private @Id @GeneratedValue Long id;
    @ManyToOne
    private Dog dog;
    @ManyToOne
    private Vet vet;
    @Temporal(TemporalType.DATE)
    private Date date;
    @Min(value = 0, message = "The price of an appointment cannot be a negative number!")
    private float price;

    public Appointment() {
    }

    public Appointment(Long id, Dog dog, Vet vet, Date date, float price) {
        this.id = id;
        this.dog = dog;
        this.vet = vet;
        this.date = date;
        this.price = price;
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
}
