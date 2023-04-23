package info.scortar.irina.dogsdatabase.DTOs;

public class AppointmentDTO {

    private Long appointment_id;
    private Long dog_id;
    private Long vet_id;
    private String date;
    private String description;

    public AppointmentDTO(Long appointment_id, Long dog_id, Long vet_id, String date, String description) {
        this.dog_id = dog_id;
        this.vet_id = vet_id;
        this.date = date;
        this.appointment_id = appointment_id;
        this.description = description;
    }

    public Long getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(Long appointment_id) {
        this.appointment_id = appointment_id;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
